package com.acme.catchup.platform.news.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Resource record for creating a favorite source.
 *
 * @summary
 * This record represents the resource for creating a favorite source.
 * It contains the news API key and source ID.
 * The validation annotations are used to validate the resource and provide localized error messages.
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
