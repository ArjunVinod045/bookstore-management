package com.ust.order.feignClient;

import com.ust.order.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@FeignClient(name = "book",url = "http://localhost:24000/books")
public interface BookClient {
    @GetMapping("/{id}")
    Optional<Book> getBookById(@PathVariable long id);

    @PutMapping("/{id}/{quantity}")
    void updateBook(@PathVariable long id,@PathVariable long quantity);
}
