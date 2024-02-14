package in.jewelx.jewelxbackend.controller;

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
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.metal.MetalDto;
import in.jewelx.jewelxbackend.dto.metal.MetalResponseDto;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.service.impl.MetalService;
import in.jewelx.jewelxbackend.utils.ResponseBuilder;

@RestController
@RequestMapping("/metal")
public class MetalController {

	@Autowired
	MetalService metalService;

	@PostMapping
	public ResponseEntity<String> createMetal(@RequestBody MetalDto metalDto) {

		return ResponseBuilder.status(HttpStatus.CREATED).body(metalService.createMetal(metalDto));

	}

	@GetMapping
	public ResponseEntity<?> getAllMetals() {
		return ResponseEntity.ok(metalService.getAllMetals());

	}

	@DeleteMapping("/{metalId}")
	public ResponseEntity<String> deleteUserById(@PathVariable("metalId") Long id) {
		try {
			return ResponseEntity.ok(metalService.deleteMetalById(id));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/{metalId}")
	public ResponseEntity<String> updateMetal(@RequestBody MetalDto metalDto, @PathVariable("metalId") Long id) {
		try {
			return ResponseEntity.ok(metalService.updateMetal(id, metalDto));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

	@GetMapping("/{metalId}")
	public ResponseEntity<?> getOneMetal(@PathVariable("metalId") Long id) {
		try {
			MetalResponseDto foundMetal = metalService.getOneMetal(id);
			return ResponseEntity.ok(foundMetal);
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
