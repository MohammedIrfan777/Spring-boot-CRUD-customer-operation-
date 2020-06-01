package com.example.springbootcustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootcustomer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
