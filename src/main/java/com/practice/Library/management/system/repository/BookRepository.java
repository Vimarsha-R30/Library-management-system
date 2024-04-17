package com.practice.Library.management.system.repository;

import com.practice.Library.management.system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByAuthorAndTitle(String author, String title);

}
