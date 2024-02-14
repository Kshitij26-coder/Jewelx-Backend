package in.jewelx.jewelxbackend.service.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import in.jewelx.jewelxbackend.dto.uom.UOMDto;
import in.jewelx.jewelxbackend.dto.uom.UOMResponseDto;
import in.jewelx.jewelxbackend.entity.UnitOfMeasurementEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.UOMMapper;
import in.jewelx.jewelxbackend.repository.UOMRepository;
import in.jewelx.jewelxbackend.service.IUOMService;


public class UOMService implements IUOMService {

	UOMRepository uomRepo;
	
	@Override
	public String createUOM(UOMDto uomDto) throws SQLIntegrityConstraintViolationException {
		if(uomDto == null) {
			throw new NullObjectException("UOMDto is null");
		}
		uomRepo.save(UOMMapper.dtoToUOMEntity(uomDto));
		return null;
	}

	@Override
	public List<UOMResponseDto> getAllUOM() {
		List<UnitOfMeasurementEntity> allUOMs = uomRepo.findAll();
		return allUOMs.stream().map(UOMMapper::uomEntityToUOMRespDto).collect(Collectors.toList());
	}

	@Override
	public String deleteUOMById(Long uomId) {
		getOneUOM(uomId);
		uomRepo.deleteById(uomId);
		return "Unit OF Measurement deleted successfully";
	}

	@Override
	public UOMResponseDto getOneUOM(Long uomId) {
		UnitOfMeasurementEntity uomEntity = uomRepo.findById(uomId).orElseThrow(() -> new IdNotFoundException("Invalid ID"));
		return UOMMapper.uomEntityToUOMRespDto(uomEntity);
	}

	@Override
	public String updateUOM(Long uomId, UOMDto uomDto) {
		UnitOfMeasurementEntity updatedUOM = UOMMapper.dtoToUOMEntity(uomDto);
		UnitOfMeasurementEntity foundUOM = uomRepo.findById(uomId).orElseThrow(() -> new IdNotFoundException("Invalid ID"));
		
		if(updatedUOM.getUomName() != null) {
			foundUOM.setUomName(updatedUOM.getUomName());
		}
		if(updatedUOM.getDescription() != null) {
			foundUOM.setDescription(updatedUOM.getDescription());
		}
		if(updatedUOM.getUomCode() != null) {
			foundUOM.setUomCode(updatedUOM.getUomCode());
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(uomDto.getUserID());
		foundUOM.setUpdatedBy(userEntity);
		uomRepo.save(foundUOM);
		return null;
	}

}
