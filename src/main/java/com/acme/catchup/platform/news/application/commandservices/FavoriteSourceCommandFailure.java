package com.acme.catchup.platform.news.application.commandservices;

/**
 * Failure types for favorite source command execution.
 */
public sealed interface FavoriteSourceCommandFailure permits FavoriteSourceCommandFailure.Duplicate {
    /**
     * @return message key associated with the failure
     */
    String messageKey();

    /**
     * Duplicate favorite source failure.
     */
    record Duplicate() implements FavoriteSourceCommandFailure {
        @Override
        public String messageKey() {
            return "favorite.source.error.duplicate";
        }
    }
}

