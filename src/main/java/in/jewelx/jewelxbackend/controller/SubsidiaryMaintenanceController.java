package in.jewelx.jewelxbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
