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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.metal.MetalDto;
import in.jewelx.jewelxbackend.dto.metal.MetalResponseDto;
import in.jewelx.jewelxbackend.service.impl.MetalService;
import in.jewelx.jewelxbackend.utils.ResponseBuilder;

@RestController
@RequestMapping("/metal")
public class MetalController {

	@Autowired
	MetalService metalService;

	@PostMapping
	public ResponseEntity<?> createMetal(@RequestBody MetalDto metalDto) {
		return ResponseBuilder.status(HttpStatus.CREATED).body(metalService.createMetal(metalDto));
	}

	@GetMapping
	public ResponseEntity<?> getAllMetals(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(metalService.getAllMetals(page,size));
	}

	@DeleteMapping("/{metalId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("metalId") Long id) {
		return ResponseEntity.ok(metalService.deleteMetalById(id));
	}

	@PutMapping("/{metalId}")
	public ResponseEntity<?> updateMetal(@RequestBody MetalDto metalDto, @PathVariable("metalId") Long id) {
		return ResponseEntity.ok(metalService.updateMetal(id, metalDto));
	}

	@GetMapping("/{metalId}")
	public ResponseEntity<?> getOneMetal(@PathVariable("metalId") Long id) {
		MetalResponseDto foundMetal = metalService.getOneMetal(id);
		return ResponseEntity.ok(foundMetal);
	}
}
