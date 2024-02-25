package in.jewelx.jewelxbackend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.subsidiary_maintenance.SubsidiaryMaintenanceDto;
import in.jewelx.jewelxbackend.dto.subsidiary_maintenance.SubsidiaryMaintenanceRespDto;

public interface ISubsidiaryMaintenanceService {

	String addSubMaintainence(SubsidiaryMaintenanceDto dto);

	Page<SubsidiaryMaintenanceRespDto> getAllMaintenance(int pageNumber, int pageSize, Long brand, Long subsidiary,
			String role);

	SubsidiaryMaintenanceRespDto getOneMaintenance(UUID id);

	String updateMaintenace(UUID id, SubsidiaryMaintenanceDto dto);

}
