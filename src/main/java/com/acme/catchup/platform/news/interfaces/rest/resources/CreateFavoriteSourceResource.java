package com.acme.catchup.platform.news.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

/**
 * Resource record for creating a favorite source.
 * @summary
 * This record represents the resource for creating a favorite source.
 * It contains the news API key and source ID.
 * The validation annotations are used to validate the resource and provide localized error messages.
 * @since 1.0
 */
public record CreateFavoriteSourceResource(
        @NotBlank(message = "{favorite.source.error.newsApiKey.notBlank}")
        String newsApiKey,
        @NotBlank(message = "{favorite.source.error.sourceId.notBlank}")
        String sourceId) { }
