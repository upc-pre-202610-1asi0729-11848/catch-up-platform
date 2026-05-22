package com.acme.catchup.platform.news.interfaces.rest.resources;

/**
 * Response resource representing a favorite source.
 *
 * @param id persistent identifier
 * @param newsApiKey news API key associated with the favorite source
 * @param sourceId source identifier marked as favorite
 * @since 1.0
 */
public record FavoriteSourceResource(Long id, String newsApiKey, String sourceId) {
}
