package com.edu.pharmacy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;


/**
 * Represents an article entity in the system.
 * This entity is mapped to the "articles" table in the database.
 */
@Entity
@Table(name = "articles")
@AllArgsConstructor // Generates a constructor with all fields as parameters.
@NoArgsConstructor  // Generates a no-argument constructor.
@Builder            // Enables the builder pattern for creating instances of this class.
public class ArticleEntity {

    /**
     * The unique identifier for the article.
     * This field is auto-generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the article.
     */
    private String title;

    /**
     * A collection of tags associated with the article.
     * This is stored as an element collection in the database.
     */
    @ElementCollection
    private HashSet<String> tags;

    /**
     * The estimated time to read the article, in minutes.
     */
    private int timeToRead;

    /**
     * The content of the article.
     */
    private String content;

    /**
     * The author of the article.
     */
    private String author;
}