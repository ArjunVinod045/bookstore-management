package com.ust.book.convertor;

import com.ust.book.dto.BookDto;
import com.ust.book.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Convert {
    public Book toEntity(BookDto bookDto){

        return Book.builder()
                .title(bookDto.title())
                .author(bookDto.author())
                .price(bookDto.price())
                .stock(bookDto.stock())
                .build();
    }

    public BookDto toDto(Book book){
        return new BookDto(book.getId(), book.getTitle(),book.getAuthor(),book.getPrice(),book.getStock());
    }

    public List<BookDto> toDto(List<Book> books){
        ArrayList<BookDto> bookDtos = new ArrayList<>();
        for(Book book: books){
            bookDtos.add(toDto(book));
        }
        return bookDtos;
    }
}
