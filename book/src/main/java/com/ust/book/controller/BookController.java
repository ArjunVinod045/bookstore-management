package com.ust.book.controller;

import com.ust.book.dto.BookDto;
import com.ust.book.exception.BookNotFound;
import com.ust.book.service.BookService;
import com.ust.book.convertor.Convert;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    Convert convert;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return new ResponseEntity<List<BookDto>>(convert.toDto(bookService.getAllBooks()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable long id){
        return new ResponseEntity<>(convert.toDto(bookService.getBookById(id).orElseThrow(() -> new BookNotFound("No such book found"))),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDto> postBook(@Valid @RequestBody BookDto bookDto){
        return new ResponseEntity<>(convert.toDto(bookService.addNewBook(convert.toEntity(bookDto))),HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{quantity}")
    public ResponseEntity<BookDto> updateBook(@PathVariable long id,@PathVariable long quantity){
        var bookFetched = bookService.getBookById(id).orElseThrow(() -> new BookNotFound("No such book exists inorder to update"));
        return new ResponseEntity<>(convert.toDto(bookService.updateBook(bookFetched,id,quantity)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable long id){
        var bookFetched = bookService.getBookById(id).orElseThrow(() -> new BookNotFound("No such book exists inorder to delete"));
        bookService.deleteBook(bookFetched);
        return new ResponseEntity<>("Book Deleted Successfully",HttpStatus.OK);
    }
}
