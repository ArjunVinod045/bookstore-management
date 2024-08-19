package com.ust.book.service;

import com.ust.book.model.Book;
import com.ust.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(long id);
    Book addNewBook(Book book);
    Book updateBook(Book book,long id,long quantity);
    void deleteBook(Book book);
}
