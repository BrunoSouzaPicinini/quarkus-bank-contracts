package com.bspicinini.service;

import com.bspicinini.controller.CustomerController.CustomerInput;
import com.bspicinini.mapper.CustomerMapper;
import com.bspicinini.repository.CustomerRepository;
import com.bspicinini.repository.entity.Customer;
import com.bspicinini.service.dto.CustomerDto;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> findAllCustomers() {
        return customerRepository.listAll().stream()
                .map(CustomerMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDto findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id);
        return CustomerMapper.INSTANCE.toDto(customer);
    }

    public CustomerDto createCustomer(CustomerInput customerInput) {
        Customer customer = CustomerMapper.INSTANCE.toEntity(customerInput);
        customerRepository.persist(customer);
        return CustomerMapper.INSTANCE.toDto(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}