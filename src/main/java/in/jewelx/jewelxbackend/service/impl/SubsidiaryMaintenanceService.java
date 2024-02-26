package in.jewelx.jewelxbackend.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.subsidiary_maintenance.SubsidiaryMaintenanceDto;
import in.jewelx.jewelxbackend.dto.subsidiary_maintenance.SubsidiaryMaintenanceRespDto;
import in.jewelx.jewelxbackend.entity.AccountingEntity;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryMaintenance;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.SubsidiaryMaintenanceMapper;
import in.jewelx.jewelxbackend.repository.SubsidiaryMaintenanceRepository;
import in.jewelx.jewelxbackend.service.ISubsidiaryMaintenanceService;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class SubsidiaryMaintenanceService implements ISubsidiaryMaintenanceService {

	@Autowired
	private SubsidiaryMaintenanceRepository subMaintainenceRepo;

	@Autowired
	private AccountingService accountingService;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public String addSubMaintainence(SubsidiaryMaintenanceDto dto) {
		if (dto == null) {
			throw new NullObjectException("Customer Order Dto is null");
		} else {
			SubsidiaryMaintenance entity = new SubsidiaryMaintenance();
			entity.setMaintenanceDescription(dto.getMaintenanceDescription());

			AccountingEntity accountingEntity = accountingService
					.addAccounting(SubsidiaryMaintenanceMapper.subsidiaryMaintenanceDtoToAccountingDto(dto));

			UserEntity userEntity = new UserEntity(dto.getUserId());

			entity.setCreatedBy(userEntity);
			entity.setUpdatedBy(userEntity);

			entity.setAccounting(accountingEntity);

			BrandEntity brand = new BrandEntity(dto.getBrandId());
			entity.setBrand(brand);

			SubsidiaryEntity subsidiary = new SubsidiaryEntity(dto.getSubsidiaryId());
			entity.setSubsidiary(subsidiary);

			subMaintainenceRepo.save(entity);
			return "Subsidiary maintenance added Successfully !!!";
		}
	}

	@Override
	public Page<SubsidiaryMaintenanceRespDto> getAllMaintenance(int pageNumber, int pageSize, Long brand,
			Long subsidiary, String role) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<SubsidiaryMaintenance> allMaintenance = null;
		if (role.equals("O")) {
			allMaintenance = subMaintainenceRepo.findByBrand_BrandId(brand, pageRequest);
		} else if (role.equals("A") || role.equals("E")) {
			allMaintenance = subMaintainenceRepo.findBySubsidiary_IdxId(subsidiary, pageRequest);
		}
		return allMaintenance.map(SubsidiaryMaintenanceMapper::entityToDto);
	}

	@Override
	public SubsidiaryMaintenanceRespDto getOneMaintenance(UUID id) {
		SubsidiaryMaintenance foundMaintenance = subMaintainenceRepo.findByMaintenanceId(id)
				.orElseThrow(() -> new IdNotFoundException("Invalid Subsidiary Maintenance id"));
		return SubsidiaryMaintenanceMapper.entityToDto(foundMaintenance);
	}

	@Override
	public String updateMaintenace (UUID id, SubsidiaryMaintenanceDto dto) {
		SubsidiaryMaintenance updatedMaintenance = new SubsidiaryMaintenance();
		
		SubsidiaryMaintenance foundMaintenace = subMaintainenceRepo.findByMaintenanceId(id).orElseThrow(() -> new IdNotFoundException("Invalid Id"));
		AccountingEntity accountingEntity = accountingService
				.addAccounting(SubsidiaryMaintenanceMapper.subsidiaryMaintenanceDtoToAccountingDto(dto));
		updatedMaintenance.setAccounting(accountingEntity);
		UserEntity userEntity = new UserEntity(dto.getUserId());
		updatedMaintenance.setUpdatedBy(userEntity);
		updatedMaintenance.setMaintenanceDescription(dto.getMaintenanceDescription());
		BrandEntity brand = new BrandEntity(dto.getBrandId());
		updatedMaintenance.setBrand(brand);

		SubsidiaryEntity subsidiary = new SubsidiaryEntity(dto.getSubsidiaryId());
		updatedMaintenance.setSubsidiary(subsidiary);

		
//		mapper.map(updatedMaintenance, foundMaintenace);
		subMaintainenceRepo.save(foundMaintenace);
		return "Subsidiary Maintenace Updated Successfully";
	}
}
