package com.acme.catchup.platform.news.domain.model.queries;

/**
 * Query for retrieving a favorite source by its identifier.
 *
 * @param id favorite source identifier
 */
public record GetFavoriteSourceByIdQuery(Long id) {
    public GetFavoriteSourceByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("favorite.source.error.id.invalid");
        }
    }
}
