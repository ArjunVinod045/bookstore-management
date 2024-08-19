package com.ust.customer.convertor;

import com.ust.customer.dto.CustomerDto;
import com.ust.customer.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Convert {
    public Customer toEntity(CustomerDto customerDto){
        return Customer.builder()
                .name(customerDto.name())
                .email(customerDto.email())
                .phoneNo(customerDto.phoneNo())
                .build();
    }

    public CustomerDto toDto(Customer customer){
        return new CustomerDto(customer.getId(),customer.getName(),customer.getEmail(),customer.getPhoneNo());
    }

    public List<CustomerDto> toDto(List<Customer> customers){
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        for(Customer customer: customers){
            customerDtos.add(toDto(customer));
        }
        return customerDtos;
    }
}
