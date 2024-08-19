package com.ust.order.feignClient;

import com.ust.order.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer",url = "http://localhost:24100/customers")
public interface CustomerClient {
    @GetMapping("/{id}")
    Optional<Customer> getCustomerById(@PathVariable long id);
}
