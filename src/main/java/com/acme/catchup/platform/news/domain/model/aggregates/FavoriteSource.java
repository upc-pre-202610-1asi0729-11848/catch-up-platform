package com.acme.catchup.platform.news.domain.model.aggregates;

import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * FavoriteSource Aggregate Root
 *
 * @summary
 * The FavoriteSource class is an aggregate root that represents a favorite news source.
 * It is responsible for handling the {@link CreateFavoriteSourceCommand} command.
 * @since 1.0
 */
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"news_api_key", "source_id"}, name = FavoriteSource.NEWS_API_KEY_SOURCE_ID_UNIQUE_CONSTRAINT)
})
public class FavoriteSource extends AbstractAggregateRoot<FavoriteSource> {

    /** Unique constraint name shared with persistence layer. */
    public static final String NEWS_API_KEY_SOURCE_ID_UNIQUE_CONSTRAINT = "uk_favorite_source_news_api_key_source_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String newsApiKey;

    @Column(nullable = false)
    private String sourceId;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant updatedAt;

    protected FavoriteSource() {}

    /**
     * @summary Constructor.
     * It creates a new FavoriteSource instance based on the CreateFavoriteSourceCommand command.
     * @param command - the CreateFavoriteSourceCommand command
     */
    public FavoriteSource(CreateFavoriteSourceCommand command) {
        this.newsApiKey = command.newsApiKey();
        this.sourceId = command.sourceId();
    }


}
