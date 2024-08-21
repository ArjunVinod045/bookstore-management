package com.ust.book.repository;
import com.ust.book.model.Book;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookRepositoryTest {
    Book book;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        Book book = new Book(1L,"Goat Life","Benyamin",499.00,12);
        bookRepository.save(book);
    }

    @AfterEach
    void tearDown(){
        bookRepository.deleteAll();
        book = null;
    }

    @Test
    @DisplayName("Testing the findById method")
    void testFindById(){
        assertEquals(book,bookRepository.findById(1L).get(0));
    }
}
