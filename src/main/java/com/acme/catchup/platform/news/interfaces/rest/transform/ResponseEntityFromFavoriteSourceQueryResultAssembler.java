package com.acme.catchup.platform.news.interfaces.rest.transform;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Interface layer translator converting favorite source query results into HTTP ResponseEntity values.
 *
 * @since 1.0
 */
public class ResponseEntityFromFavoriteSourceQueryResultAssembler {
    /**
     * Converts a single favorite source into a 200 response.
     *
     * @param favoriteSource favorite source to map
     * @return 200 response with resource body
     */
    public static ResponseEntity<?> toResponseEntityFromFavoriteSource(FavoriteSource favoriteSource) {
        return ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(favoriteSource));
    }

    /**
     * Converts a list query result into a 200 response.
     *
     * @param favoriteSources list result from a query
     * @return 200 response with resource list body
     */
    public static ResponseEntity<?> toResponseEntityFromList(List<FavoriteSource> favoriteSources) {
        var favoriteSourceResources = favoriteSources.stream()
                .map(FavoriteSourceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(favoriteSourceResources);
    }

    /**
     * Builds a localized 400-response body.
     *
     * @param messageSource source for localized messages
     * @param messageKey message key to resolve
     * @param args optional interpolation values for localized messages
     * @return 400 response containing localized {@link ProblemDetail}
     */
    public static ResponseEntity<ProblemDetail> badRequest(MessageSource messageSource, String messageKey, Object... args) {
        return problemDetail(HttpStatus.BAD_REQUEST, messageSource, messageKey, args);
    }

    /**
     * Builds a localized 404-response body.
     *
     * @param messageSource source for localized messages
     * @param messageKey message key to resolve
     * @param args optional interpolation values for localized messages
     * @return 404 response containing localized {@link ProblemDetail}
     */
    public static ResponseEntity<ProblemDetail> notFound(MessageSource messageSource, String messageKey, Object... args) {
        return problemDetail(HttpStatus.NOT_FOUND, messageSource, messageKey, args);
    }

    private static ResponseEntity<ProblemDetail> problemDetail(HttpStatus status, MessageSource messageSource, String messageKey, Object... args) {
        var detail = messageSource.getMessage(messageKey, args, messageKey, LocaleContextHolder.getLocale());
        return ResponseEntity.status(status).body(ProblemDetail.forStatusAndDetail(status, detail));
    }
}


