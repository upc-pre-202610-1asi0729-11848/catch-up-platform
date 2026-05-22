package com.acme.catchup.platform.news.domain.model.queries;

/**
 * Query for retrieving a favorite source by news API key and source ID.
 *
 * @param newsApiKey news API key of the favorite source
 * @param sourceId source identifier of the favorite source
 */
public record GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(String newsApiKey, String sourceId) {
    public GetFavoriteSourceByNewsApiKeyAndSourceIdQuery {
        if (newsApiKey == null || newsApiKey.isBlank())
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");
        if (sourceId == null || sourceId.isBlank())
            throw new IllegalArgumentException("sourceId cannot be null or empty");
    }
}
