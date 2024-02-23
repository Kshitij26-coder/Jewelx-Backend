package in.jewelx.jewelxbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.userpurchase.UserPurchaseDto;
import in.jewelx.jewelxbackend.service.impl.UserPurchaseService;

@RestController
@RequestMapping("/user-purchase")
public class UserPurchaseController {

	@Autowired
	UserPurchaseService userPurchaseService;

	@PostMapping
	public ResponseEntity<String> addUserPurchase(@RequestBody UserPurchaseDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userPurchaseService.addUserPurchase(dto));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllUserPurchase(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size, @RequestParam Long brand, @RequestParam Long subsidiary, @RequestParam String role){
		return ResponseEntity.ok(userPurchaseService.getAllUserPurchase(page, size, brand, subsidiary, role));
	}
}
