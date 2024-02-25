package in.jewelx.jewelxbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.subsidiary_maintenance.SubsidiaryMaintenanceDto;
import in.jewelx.jewelxbackend.service.impl.SubsidiaryMaintenanceService;

@RestController
@RequestMapping("subsidiary-maintenance")
public class SubsidiaryMaintenanceController {

	@Autowired
	private SubsidiaryMaintenanceService subMaintenanceService;

	@PostMapping
	public ResponseEntity<String> addSubsidiaryMaintenance(@RequestBody SubsidiaryMaintenanceDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(subMaintenanceService.addSubMaintainence(dto));
	}

	@GetMapping
	public ResponseEntity<?> getAllMaintenance(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam Long brand, @RequestParam Long subsidiary,
			String role) {
		return ResponseEntity.ok(subMaintenanceService.getAllMaintenance(page, size, brand, subsidiary, role));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getMaitenanceById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(subMaintenanceService.getOneMaintenance(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMaitenanceById(@PathVariable("id") UUID id, @RequestBody SubsidiaryMaintenanceDto dto) {
		return ResponseEntity.ok(subMaintenanceService.updateMaintenace(id, dto));
	}
}
