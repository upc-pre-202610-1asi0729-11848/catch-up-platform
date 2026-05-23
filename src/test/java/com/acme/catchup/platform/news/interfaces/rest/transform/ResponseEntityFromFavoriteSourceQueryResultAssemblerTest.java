package com.acme.catchup.platform.news.interfaces.rest.transform;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.news.domain.model.valueobjects.NewsApiKey;
import com.acme.catchup.platform.news.domain.model.valueobjects.SourceId;
import com.acme.catchup.platform.news.interfaces.rest.resources.FavoriteSourceResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResponseEntityFromFavoriteSourceQueryResultAssemblerTest {
    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUpLocale() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
    }

    @AfterEach
    void clearLocale() {
        LocaleContextHolder.resetLocaleContext();
    }

    @Test
    void convertsFavoriteSourceToOkResponseEntity() {
        var command = new CreateFavoriteSourceCommand(new NewsApiKey("news-api-key"), new SourceId("source-id"));
        var favoriteSource = new FavoriteSource(command);

        ResponseEntity<?> response = ResponseEntityFromFavoriteSourceQueryResultAssembler
                .toResponseEntityFromFavoriteSource(favoriteSource);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(FavoriteSourceResource.class, response.getBody());
        var resource = (FavoriteSourceResource) response.getBody();
        assertEquals("news-api-key", resource.newsApiKey());
        assertEquals("source-id", resource.sourceId());
        assertNull(resource.id());
    }

    @Test
    void convertsFavoriteSourceListToOkResponseEntity() {
        var first = new FavoriteSource(new CreateFavoriteSourceCommand(new NewsApiKey("key-one"), new SourceId("source-one")));
        var second = new FavoriteSource(new CreateFavoriteSourceCommand(new NewsApiKey("key-two"), new SourceId("source-two")));

        ResponseEntity<?> response = ResponseEntityFromFavoriteSourceQueryResultAssembler
                .toResponseEntityFromList(List.of(first, second));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
        @SuppressWarnings("unchecked")
        var resources = (List<FavoriteSourceResource>) response.getBody();
        assertEquals(2, resources.size());
        assertEquals("key-one", resources.getFirst().newsApiKey());
        assertEquals("source-two", resources.getLast().sourceId());
    }

    @Test
    void buildsLocalizedBadRequestProblemDetail() {
        when(messageSource.getMessage(eq("favorite.source.error.newsApiKey.invalid"), any(), eq("favorite.source.error.newsApiKey.invalid"), eq(Locale.ENGLISH)))
                .thenReturn("Invalid parameter: newsApiKey.");

        ResponseEntity<ProblemDetail> response = ResponseEntityFromFavoriteSourceQueryResultAssembler
                .badRequest(messageSource, "favorite.source.error.newsApiKey.invalid");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        var body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getStatus());
        assertEquals("Invalid parameter: newsApiKey.", body.getDetail());
    }

    @Test
    void buildsLocalizedNotFoundProblemDetail() {
        when(messageSource.getMessage(eq("favorite.source.error.notFoundById"), any(), eq("favorite.source.error.notFoundById"), eq(Locale.ENGLISH)))
                .thenReturn("Favorite source not found for id 99.");

        ResponseEntity<ProblemDetail> response = ResponseEntityFromFavoriteSourceQueryResultAssembler
                .notFound(messageSource, "favorite.source.error.notFoundById", 99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        var body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.NOT_FOUND.value(), body.getStatus());
        assertEquals("Favorite source not found for id 99.", body.getDetail());
    }
}


