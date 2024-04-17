package com.practice.Library.management.system.service;

import com.practice.Library.management.system.entity.Book;
import com.practice.Library.management.system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;


@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        String authorName = book.getAuthor();
        if (authorName==null || authorName.isBlank() || authorName.isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be blank.");
        }
        for (char ch : authorName.toCharArray()) {
            if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) {
                throw new IllegalArgumentException("Author name cannot contain digits or special characters.");
            }
        }

        int currentYear = Year.now().getValue();
        if(book.getPublicationYear()>currentYear){
            throw new IllegalArgumentException("Publication year cannot be greater than the current year.");
        }

        if(book.getTitle()==null || book.getTitle().isBlank()){
            throw new IllegalArgumentException("Title should not be empty");
        }
        if (bookRepository.existsByAuthorAndTitle(book.getAuthor(), book.getTitle())) {
            throw new IllegalArgumentException("A book with the same author and title already exists.");
        }
        return bookRepository.save(book);
    }
}
