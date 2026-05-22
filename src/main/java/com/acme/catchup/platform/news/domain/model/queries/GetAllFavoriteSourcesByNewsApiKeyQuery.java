package com.acme.catchup.platform.news.domain.model.queries;

/**
 * Query for retrieving all favorite sources by news API key.
 *
 * @param newsApiKey news API key used to filter favorite sources
 */
public record GetAllFavoriteSourcesByNewsApiKeyQuery(String newsApiKey) {
    public GetAllFavoriteSourcesByNewsApiKeyQuery {
        if (newsApiKey == null || newsApiKey.isBlank())
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");

    }
}

