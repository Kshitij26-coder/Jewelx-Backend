package in.jewelx.jewelxbackend.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.customer.CustomerDto;
import in.jewelx.jewelxbackend.dto.customer.CustomerResponseDto;
import in.jewelx.jewelxbackend.service.impl.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PutMapping
	public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.createCustomer(customerDto));
	}

	@GetMapping
	public List<CustomerResponseDto> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<?> getOneCustomer(@PathVariable("customerId") UUID id) {
		return ResponseEntity.ok(customerService.getOneCustomer(id));
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomer(@PathVariable("customerId") UUID id, @RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.updateCustomer(id, customerDto));
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("customerId") UUID id) {
		return ResponseEntity.ok(customerService.deleteCustomerById(id));
	}
}
