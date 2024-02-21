package in.jewelx.jewelxbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.subsidiary_maintenance.SubsidiaryMaintenanceDto;
import in.jewelx.jewelxbackend.entity.AccountingEntity;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryMaintenance;
import in.jewelx.jewelxbackend.entity.UserEntity;
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
}
