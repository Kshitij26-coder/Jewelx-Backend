package in.jewelx.jewelxbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryRequestDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryResponseDto;
import in.jewelx.jewelxbackend.dto.subsidiary.UpdateActiveStatusDto;
import in.jewelx.jewelxbackend.service.impl.SubsidiaryService;

@RestController
@RequestMapping("/subsidiary")
@CrossOrigin(origins = "http://localhost:5173")
public class SubsidiaryController {
	@Autowired
	private SubsidiaryService subsidiaryService;

	@GetMapping("/brand/{id}")
	public ResponseEntity<?> getSubsidiariesByBrandId(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@PathVariable("id") Long brandId) {
		return ResponseEntity.ok(subsidiaryService.getSubsidiariesByBrandId(page, size,brandId));
	}

	@PostMapping
	public ResponseEntity<String> createSubsidiary(@RequestBody SubsidiaryRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(subsidiaryService.createSubsidiary(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubsidiaryResponseDto> getSubsidiaryById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(subsidiaryService.getSubsidiaryDtoById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateSubsidiaryById(@PathVariable("id") UUID id,
			@RequestBody SubsidiaryRequestDto updatedDto) {
		return ResponseEntity.ok(subsidiaryService.updateSubsidiaryById(id, updatedDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSubsidiaryById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(subsidiaryService.deleteSubsidiaryById(id));
	}

	@PutMapping("/update-active-status")
	public ResponseEntity<String> updateActiveStatus(@RequestBody UpdateActiveStatusDto dto) {
		System.out.println(dto);
		subsidiaryService.updateActiveStatus(dto);
		return new ResponseEntity<>("Active status updated successfully", HttpStatus.OK);
	}
}
