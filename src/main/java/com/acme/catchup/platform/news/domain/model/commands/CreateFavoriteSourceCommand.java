package com.acme.catchup.platform.news.domain.model.commands;

import com.acme.catchup.platform.news.domain.model.valueobjects.NewsApiKey;
import com.acme.catchup.platform.news.domain.model.valueobjects.SourceId;

/**
 * Command for creating a favorite news source.
 *
 * @param newsApiKey news API key value object
 * @param sourceId   source identifier value object
 * @since 1.0
 */
public record CreateFavoriteSourceCommand(NewsApiKey newsApiKey, SourceId sourceId) {
    /**
     * Validates the command.
     * Null-checks guard against a null reference being passed for either parameter.
     * Further validation (blank value, exceeded length, invalid characters) is enforced
     * by the {@link NewsApiKey} and {@link SourceId} value object constructors.
     *
     * @throws IllegalArgumentException if {@code newsApiKey} or {@code sourceId} is null,
     *                                  or if either value is blank, exceeds 256 characters,
     *                                  or contains characters outside {@code [A-Za-z0-9._:-]}
     */
    public CreateFavoriteSourceCommand {
        if (newsApiKey == null)
            throw new IllegalArgumentException("newsApiKey cannot be null");
        if (sourceId == null)
            throw new IllegalArgumentException("sourceId cannot be null");
    }

}
