package com.ust.book.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Testing book object initialization")
public class BookTest {

    @Nested
    @DisplayName("Testing constructors")
    class ArgsConstructorTest {
        @Test
        @DisplayName("Testing constructor initialization with arguments")
        void testNoArg(){
            Book book = new Book(1L,"Goat Life","Benyamin",499.00,12);
            assertEquals(1,book.getId());
            assertEquals("Goat Life",book.getTitle());
            assertEquals("Benyamin",book.getAuthor());
            assertEquals(499.00,book.getPrice());
            assertEquals(12,book.getStock());
        }

        @Test
        @DisplayName("Testing constuctor initialisation without arguments")
        void testArg(){
            Book book = new Book();
            assertEquals(0L,book.getId());
            assertNull(book.getTitle());
            assertNull(book.getAuthor());
            assertEquals(0.0,book.getPrice());
            assertEquals(0,book.getStock());
        }
    }
}
