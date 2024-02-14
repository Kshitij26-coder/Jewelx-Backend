package in.jewelx.jewelxbackend.controller;

import java.util.List;

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

import in.jewelx.jewelxbackend.dto.uom.UOMDto;
import in.jewelx.jewelxbackend.dto.uom.UOMResponseDto;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.service.impl.UOMService;

@RestController
@RequestMapping("/uom")
public class UOMController {
	
	@Autowired
	UOMService uomService;
	
	@PutMapping
	public ResponseEntity<?> createUOM(@RequestBody UOMDto uomDto) {
		try {
			return ResponseEntity.ok(uomService.createUOM(uomDto));
		} catch (DataIntegrityViolationException | ConstraintViolationException err) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Integrity constraint violation: " + err.getMessage());
		} catch (Exception err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
		}
	}

	@GetMapping
	public List<UOMResponseDto> getAllUOMs() {
		return uomService.getAllUOM();
	}

	@GetMapping("/{uomId}")
	public ResponseEntity<?> getOneCustomer(@PathVariable("uomID") Long id) {
		try {
			return ResponseEntity.ok(uomService.getOneUOM(id));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/{uomId}")
	public ResponseEntity<?> updateCustomer(@PathVariable("uomId") Long id, @RequestBody UOMDto uomDto) {
		try {
			return ResponseEntity.ok(uomService.updateUOM(id, uomDto));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{uomId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("uomId") Long id) {
		try {
			return ResponseEntity.ok(uomService.deleteUOMById(id));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
