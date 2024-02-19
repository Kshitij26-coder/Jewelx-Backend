package in.jewelx.jewelxbackend.controller;

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

import in.jewelx.jewelxbackend.dto.uom.UOMDto;
import in.jewelx.jewelxbackend.service.impl.UOMService;

@RestController
@RequestMapping("/uom")
public class UOMController {

	@Autowired
	UOMService uomService;

	@PostMapping
	public ResponseEntity<?> createUOM(@RequestBody UOMDto uomDto) {
		return ResponseEntity.ok(uomService.createUOM(uomDto));
	}

	@GetMapping
	public ResponseEntity<?> getAllUOMs(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(uomService.getAllUOM(page, size));
	}

	@GetMapping("/{uomId}")
	public ResponseEntity<?> getOneCustomer(@PathVariable("uomID") Long id) {
		return ResponseEntity.ok(uomService.getOneUOM(id));
	}

	@PutMapping("/{uomId}")
	public ResponseEntity<?> updateCustomer(@PathVariable("uomId") Long id, @RequestBody UOMDto uomDto) {
		return ResponseEntity.ok(uomService.updateUOM(id, uomDto));
	}

	@DeleteMapping("/{uomId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("uomId") Long id) {
		return ResponseEntity.ok(uomService.deleteUOMById(id));
	}
}
