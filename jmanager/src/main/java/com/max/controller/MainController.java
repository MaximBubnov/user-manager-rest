package com.max.controller;

import com.max.domain.Customer;
import com.max.repository.CustomerRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/manage")
public class MainController {

    private final CustomerRepo customerRepo;

    @Autowired
    public MainController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping("/customers")
    public List<Customer> list(){
        return customerRepo.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer customer(@PathVariable("id") Customer customer) {
        return customer;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepo.save(customer);
    }

    @PutMapping("/update/{id}")
    public Customer updateCar(@PathVariable("id") Customer customerFromDb,
                         @RequestBody Customer customer) {

        BeanUtils.copyProperties(customer, customerFromDb, "id");

        return customerRepo.save(customerFromDb);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Customer customer) {
         customerRepo.delete(customer);
    }

}
