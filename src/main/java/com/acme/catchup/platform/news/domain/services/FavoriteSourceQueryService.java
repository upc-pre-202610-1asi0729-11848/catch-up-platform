package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Domain query service providing read access to favorite sources.
 * Defines query specifications essential to the domain model, supporting
 * query handlers within the bounded context that retrieve and build
 * representations necessary for command and response operations.
 *
 * @since 1.0.0
 */
public interface FavoriteSourceQueryService {
    /**
     * Retrieves all favorite sources associated with a news API key.
     *
     * @param query query containing the target news API key
     * @return list of favorite sources, possibly empty
     * @throws IllegalArgumentException If newsApiKey is null or empty
     * @see GetAllFavoriteSourcesByNewsApiKeyQuery
     */
    List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query);

    /**
     * Retrieves a favorite source by its identifier.
     *
     * @param query query containing the favorite source identifier
     * @return favorite source when found, otherwise empty
     * @throws IllegalArgumentException If id is null or empty
     * @see GetFavoriteSourceByIdQuery
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query);

    /**
     * Retrieves a favorite source by the composite key of news API key and source ID.
     *
     * @param query query containing news API key and source ID
     * @return favorite source when found, otherwise empty
     * @throws IllegalArgumentException If newsApiKey or source ID is null or empty
     * @see GetFavoriteSourceByNewsApiKeyAndSourceIdQuery
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query);
}
