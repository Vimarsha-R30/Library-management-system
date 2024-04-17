package com.practice.Library.management.system.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String author;

    @Column
    @Nonnull
    private String title;

    @Column(name = "published_on")
    private int publicationYear;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
