package in.jewelx.jewelxbackend.controller;

import java.util.List;
import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.CustomerDto;
import in.jewelx.jewelxbackend.dto.CustomerResponseDto;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.service.impl.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PutMapping
	public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
		try {
			return ResponseEntity.ok(customerService.createCustomer(customerDto));
		} catch (DataIntegrityViolationException | ConstraintViolationException err) {
			System.out.println("+++++++ " + err.getMessage() + " +++++");
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Integrity constraint violation: " + err.getMessage());
		} catch (Exception err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
		}
	}

	@GetMapping
	public List<CustomerResponseDto> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<?> getOneCustomer(@PathVariable("customerId") UUID id) {
		try {
			return ResponseEntity.ok(customerService.getOneCustomer(id));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomer(@PathVariable("customerId") UUID id, @RequestBody CustomerDto customerDto) {
		try {
			return ResponseEntity.ok(customerService.updateCustomer(id, customerDto));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("customerId") UUID id) {
		try {
			return ResponseEntity.ok(customerService.deleteCustomerById(id));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
