package com.ust.book.service;

import com.ust.book.model.Book;
import com.ust.book.repository.BookRepository;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id).stream().findFirst();
    }

    @Override
    public Book addNewBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public Book updateBook(Book book,long id,long quantity) {
        return bookRepository.save(Book.builder()
                        .id(id)
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .price(book.getPrice())
                        .stock(quantity)
                .build());
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
