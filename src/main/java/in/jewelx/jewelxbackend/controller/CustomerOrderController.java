package in.jewelx.jewelxbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import in.jewelx.jewelxbackend.dto.customerorder.CustomerOrderDto;
import in.jewelx.jewelxbackend.dto.customerorder.CustomerOrderRespDto;
import in.jewelx.jewelxbackend.service.impl.CustomerOrderService;

@RestController
@RequestMapping("customer-order")
public class CustomerOrderController {
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@PostMapping
	public ResponseEntity<String> addCustomerOrder(@RequestBody CustomerOrderDto dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(customerOrderService.addCustomerOrder(dto));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllCustomerOrders(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok(customerOrderService.getAllCustomerOrder(page, size));
	}
	
	@PutMapping("/{orderId}")
	public ResponseEntity<String> updateCustomerOrderByUUID(@PathVariable("orderId")UUID id, @RequestBody CustomerOrderDto dto){
		return ResponseEntity.ok(customerOrderService.updateCustomerOrderByUUID(id, dto));
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<String> deleteCustomerOrderByUUID(@PathVariable("orderId")UUID id){
		return ResponseEntity.ok(customerOrderService.deleteCustomerOrderByUUID(id));
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<CustomerOrderRespDto> getCustomerOrderByUUID(@PathVariable("orderId")UUID id)	{
		return ResponseEntity.ok(customerOrderService.getCustomerOrderByUUID(id));
	}
}
