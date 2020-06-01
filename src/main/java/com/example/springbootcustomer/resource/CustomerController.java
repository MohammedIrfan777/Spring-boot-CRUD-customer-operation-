package com.example.springbootcustomer.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootcustomer.model.Customer;
import com.example.springbootcustomer.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
	
	@Autowired
	CustomerRepository cutomerRepository;
	
	
	@GetMapping(value = "/all")
	public List<Customer> getAllCustomer() {
		return cutomerRepository.findAll();
	}
	
	@PostMapping(value = "/saverecord")
	public List<Customer> loadData(@RequestBody final Customer customer) {
		cutomerRepository.save(customer);
		return cutomerRepository.findAll();
	}
	
	@PutMapping("/updaterecord")
	public List<Customer> updateData(@RequestBody final Customer customer) {
		Optional<Customer> optional = cutomerRepository.findById(customer.getCustomerId());
		Customer customer2 = optional.isPresent() ? optional.get() : null;
		if(customer2 != null) {
			customer2.setCountry(customer.getCountry());
			customer2.setFirstName(customer.getFirstName());
			customer2.setDob(customer.getDob());
			customer2.setCustomerId(customer.getCustomerId());
			customer2.setLastName(customer.getLastName());
			customer2.setPhoneNum(customer.getPhoneNum());
			cutomerRepository.save(customer2);
		}	
		return cutomerRepository.findAll();
	}
	
	@GetMapping("/loadbyid/{id}")
	public Customer fetchById(@PathVariable int id) {
		Optional<Customer> customer = cutomerRepository.findById(id);	
		return customer.isPresent() ? customer.get() : null;
	}
	
	@DeleteMapping("/deleteById/{id}")
	public List<Customer> deleteById(@PathVariable int id) {
		cutomerRepository.deleteById(id);
		return cutomerRepository.findAll();
	}

}
