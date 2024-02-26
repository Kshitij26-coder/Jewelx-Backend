package in.jewelx.jewelxbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.customer.CustomerDto;
import in.jewelx.jewelxbackend.service.impl.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping
	public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.createCustomer(customerDto));
	}

	@GetMapping
	public ResponseEntity<?> getAllCustomers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam Long brand, @RequestParam Long subsidiary,
			String role) {
		return ResponseEntity.ok(customerService.getAllCustomers(page, size, brand, subsidiary, role));
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable("customerId") UUID id) {
		return ResponseEntity.ok(customerService.getOneCustomer(id));
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomerById(@PathVariable("customerId") UUID id,
			@RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.updateCustomer(id, customerDto));
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") UUID id) {
		return ResponseEntity.ok(customerService.deleteCustomerById(id));
	}
	
	@GetMapping("/byBrand")
	public ResponseEntity<?> getAllCustomerByBrandId(@RequestParam Long brand){
		return ResponseEntity.ok(customerService.getAllCustomerByBrandId(brand));
	}
}
