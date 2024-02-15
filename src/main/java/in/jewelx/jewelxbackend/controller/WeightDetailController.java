package in.jewelx.jewelxbackend.controller;

import java.util.UUID;

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

import in.jewelx.jewelxbackend.dto.weightdetails.WeightDetailsDto;
import in.jewelx.jewelxbackend.service.impl.WeightDetailService;

@RestController
@RequestMapping("/weight-detail")
public class WeightDetailController {

	@Autowired
	private WeightDetailService weightDetailService;

	@PostMapping
	public ResponseEntity<?> addWeightDetail(@RequestBody WeightDetailsDto weightDetailsDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(weightDetailService.addWeightDetail(weightDetailsDto));
	}

	@GetMapping
	public ResponseEntity<?> getAllWeightDetails(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(weightDetailService.getAllWeightDetails(page, size));
	}

	@GetMapping("/{weightDetailId}")
	public ResponseEntity<?> getWeightDetailById(@PathVariable("weightDetailId") UUID id) {
		return ResponseEntity.ok(weightDetailService.getWeightDetailById(id));
	}

	@PutMapping("/{weightDetailId}")
	public ResponseEntity<?> updateWeightDetailById(@PathVariable("weightDetailId") UUID id,
			@RequestBody WeightDetailsDto weightDetailsDto) {
		return ResponseEntity.ok(weightDetailService.updateWeightDetail(id, weightDetailsDto));
	}

	@DeleteMapping("/{weightDetailId}")
	public ResponseEntity<?> deleteWeightDetailById(@PathVariable("weightDetailId") UUID id) {
		return ResponseEntity.ok(weightDetailService.deleteWeightDetailById(id));
	}
}
