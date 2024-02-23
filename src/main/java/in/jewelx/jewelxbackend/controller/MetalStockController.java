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

import in.jewelx.jewelxbackend.dto.metalstock.MetalStockDto;
import in.jewelx.jewelxbackend.dto.metalstock.MetalStockRepoDto;
import in.jewelx.jewelxbackend.service.impl.MetalStockService;

@RestController
@RequestMapping("/metal-stock")
public class MetalStockController {

	@Autowired
	private MetalStockService metalStockService;

	@PostMapping
	public ResponseEntity<String> addMetalStock(@RequestBody MetalStockDto metalStockDto) {
		metalStockService.addMetalStock(metalStockDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Added Metal Stock");
	}

	@GetMapping
	public ResponseEntity<?> getAllMetalStocks(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(metalStockService.getAllMetalStocks(page, size));
	}

	@DeleteMapping("/{metalStockId}")
	public ResponseEntity<String> deleteMetalStockById(@PathVariable("metalStockId") Long stockId) {
		return ResponseEntity.ok(metalStockService.deleteById(stockId));
	}

	@PutMapping("/{metalStockId}")
	public ResponseEntity<String> updateMetalStockById(@RequestBody MetalStockDto metalStockDto,
			@PathVariable("metalStockId") Long stockId) {
		return ResponseEntity.ok(metalStockService.updateMetalStockById(stockId, metalStockDto));
	}

	@GetMapping("/{metalStockId}")
	public ResponseEntity<MetalStockRepoDto> getMetalStockById(@PathVariable("metalStockId") Long stockId) {
		MetalStockRepoDto foundMetalStock = metalStockService.getMetalStockById(stockId);
		return ResponseEntity.ok(foundMetalStock);
	}
}
