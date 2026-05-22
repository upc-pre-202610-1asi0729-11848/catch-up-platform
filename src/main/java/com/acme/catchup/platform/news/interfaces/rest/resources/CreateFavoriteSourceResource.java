package com.acme.catchup.platform.news.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Request resource, used to create a favorite source.
 *
 * @param newsApiKey news API key associated with the favorite source
 * @param sourceId source identifier to be marked as favorite
 * @since 1.0
 */
public record CreateFavoriteSourceResource(
        @NotBlank(message = "{favorite.source.error.newsApiKey.notBlank}")
        @Size(max = 256, message = "{favorite.source.error.newsApiKey.size}")
        @Pattern(regexp = "^[A-Za-z0-9._:-]+$", message = "{favorite.source.error.newsApiKey.pattern}")
        String newsApiKey,

        @NotBlank(message = "{favorite.source.error.sourceId.notBlank}")
        @Size(max = 256, message = "{favorite.source.error.sourceId.size}")
        @Pattern(regexp = "^[A-Za-z0-9._:-]+$", message = "{favorite.source.error.sourceId.pattern}")
        String sourceId) { }
