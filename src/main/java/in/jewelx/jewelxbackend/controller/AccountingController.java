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

import in.jewelx.jewelxbackend.dto.accounting.AccountRespDto;
import in.jewelx.jewelxbackend.dto.accounting.AccountingDto;
import in.jewelx.jewelxbackend.service.impl.AccountingService;

@RestController
@RequestMapping("/accounting")
public class AccountingController {

	@Autowired
	private AccountingService accountingService;

	@PostMapping
	public ResponseEntity<?> addAccounting(@RequestBody AccountingDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(accountingService.addAccounting(dto));
	}

	@GetMapping
	public ResponseEntity<?> getAllAccoutings(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,@RequestParam Long brand,@RequestParam Long subsidiary, @RequestParam String role) {
		return ResponseEntity.ok(accountingService.getAllAccountings(page, size,brand,subsidiary, role));
	}

	@GetMapping("/{accountingId}")
	public ResponseEntity<AccountRespDto> getAccountingByUUID(@PathVariable("accountingId") UUID id) {
		return ResponseEntity.ok(accountingService.getAccountingByUUID(id));
	}
	@GetMapping("/daily-transaction")
	public ResponseEntity<?> getDailyTransaction(@RequestParam String role, @RequestParam Long brand, @RequestParam Long subsidiary){
		return ResponseEntity.ok(accountingService.dailyTransaction(role, brand, subsidiary));
	}
	
	@GetMapping("/five-transaction")
	public ResponseEntity<?> getFiveTransaction(@RequestParam Long brand, @RequestParam Long subsidiary){
		return ResponseEntity.ok(accountingService.fiveDayTransaction(brand, subsidiary));
	}
	
}
