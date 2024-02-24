package in.jewelx.jewelxbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.sale.SaleDto;
import in.jewelx.jewelxbackend.service.impl.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	private SaleService saleService;

	@PostMapping
	public ResponseEntity<String> addSale(@RequestBody SaleDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(saleService.addSale(dto));
	}

	@GetMapping
	public ResponseEntity<?> getAllSales(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam String role, @RequestParam Long brand,
			@RequestParam Long subsidiaryId) {
		return ResponseEntity.ok(saleService.getAllSalesByUser(page, size, role, brand, subsidiaryId));
	}

	@GetMapping("/{saleId}")
	public ResponseEntity<?> getOneSaleBySaleId(@PathVariable("saleId") UUID saleId) {
		return ResponseEntity.ok(saleService.getSaleByUUID(saleId));
	}

}
