package in.jewelx.jewelxbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
