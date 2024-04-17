package com.practice.Library.management.system.controller;

import com.practice.Library.management.system.entity.Book;
import com.practice.Library.management.system.repository.BookRepository;
import com.practice.Library.management.system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }
    @GetMapping("/all")
    public List<Book> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books;
    }
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        Book book = bookRepository.findById(id).get();
        return book;
    }
    @PostMapping("/create")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        try {
            Book savedBook = bookService.addBook(book);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public Book updatingBook(@PathVariable Long id,@RequestBody Book book){
        book.setId(id);
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
