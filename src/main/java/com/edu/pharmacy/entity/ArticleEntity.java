package com.edu.pharmacy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Entity
@Table(name = "articles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String title;
    @ElementCollection
    private HashSet<String> tags;

    private int timeToRead; // in minutes
    private String content;
    private String author;


}
