package com.actionict.customer.service;

import com.actionict.customer.entity.Customer;
import com.actionict.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Page<Customer> findAll(String name, Pageable pageable) {
        if (name != null && name.length()>1) return customerRepository.findByName(name, pageable);
        else return findAllPage(pageable);
    }

    public Page<Customer> findAllPage(final Pageable pageable) { return customerRepository.findAll(pageable); }

    public Customer findById(Integer id) throws Exception { return customerRepository.findById(id).orElseThrow(() -> new Exception("Customer with id "+id+" not found")); }

    public Customer save(Customer customer) {
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    public void update(Customer newCustomer, Integer id) {
        customerRepository.findById(id).map(customer -> {
            customer.setStoreId(newCustomer.getStoreId());
            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            customer.setEmail(newCustomer.getEmail());
            customer.setAddress(newCustomer.getAddress());
            customer.setActive(newCustomer.isActive());
            customer.setLastUpdate(LocalDateTime.now());
            return  customerRepository.save(customer);
        });
    }

    public void delete(Integer id) { customerRepository.deleteById(id); }
}