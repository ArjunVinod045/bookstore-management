package com.ust.customer.controller;

import com.ust.customer.convertor.Convert;
import com.ust.customer.dto.CustomerDto;
import com.ust.customer.exception.CustomerNotFound;
import com.ust.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    Convert convert;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers(){
        return new ResponseEntity<>(convert.toDto(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable long id){
        return new ResponseEntity<>(convert.toDto(customerService.findCustomerById(id).orElseThrow(() -> new CustomerNotFound("No Such Customer found with given id"))),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(convert.toDto(customerService.addNewCustomer(convert.toEntity(customerDto))),HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id,@Valid @RequestBody CustomerDto customerDto){
        var customerFetched = customerService.findCustomerById(id).orElseThrow(() -> new CustomerNotFound("No such customer exists inorder to update"));
        return new ResponseEntity<>(convert.toDto(customerService.updateCustomer(convert.toEntity(customerDto),id)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable long id){
        var customerFetched = customerService.findCustomerById(id).orElseThrow(() -> new CustomerNotFound("No such book exists inorder to delete"));
        customerService.deleteCustomer(customerFetched);
        return new ResponseEntity<>("Customer Deleted Successfully",HttpStatus.OK);
    }
}
