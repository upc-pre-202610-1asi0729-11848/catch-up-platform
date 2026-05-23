package com.acme.catchup.platform.news.application.commandservices;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.shared.application.result.Result;

/**
 * Application service contract for favorite source command operations.
 * The command returns a Result that distinguishes successful creation from a
 * duplicate favorite source conflict.
 *
 * @since 1.0
 */
public interface FavoriteSourceCommandService {
    /**
     * Handles creation of a favorite source.
     *
     * @param command create a command containing the news API key and source ID
     * @return success when the favorite source is created, failure when the request represents a duplicate favorite source
     *
     * @throws IllegalArgumentException If newsApiKey or source ID is null or empty
     * @see CreateFavoriteSourceCommand
     */
    Result<FavoriteSource, FavoriteSourceCommandFailure> handle(CreateFavoriteSourceCommand command);
}


