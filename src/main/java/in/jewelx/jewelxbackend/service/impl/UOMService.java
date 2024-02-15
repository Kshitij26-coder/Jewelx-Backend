package in.jewelx.jewelxbackend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.uom.UOMDto;
import in.jewelx.jewelxbackend.dto.uom.UOMResponseDto;
import in.jewelx.jewelxbackend.entity.UnitOfMeasurementEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.UOMMapper;
import in.jewelx.jewelxbackend.repository.UOMRepository;
import in.jewelx.jewelxbackend.service.IUOMService;

@Service
public class UOMService implements IUOMService {

	UOMRepository uomRepo;

	@Override
	public String createUOM(UOMDto uomDto) {
		if (uomDto == null) {
			throw new NullObjectException("UOMDto is null");
		}
		uomRepo.save(UOMMapper.dtoToUOMEntity(uomDto));
		return null;
	}

	@Override
	public Page<UOMResponseDto> getAllUOM(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<UnitOfMeasurementEntity> uomPage = uomRepo.findAll(pageRequest);
		return uomPage.map(UOMMapper::uomEntityToUOMRespDto);
	}

	@Override
	public String deleteUOMById(Long uomId) {
		getOneUOM(uomId);
		uomRepo.deleteById(uomId);
		return "Unit OF Measurement deleted successfully";
	}

	@Override
	public UOMResponseDto getOneUOM(Long uomId) {
		UnitOfMeasurementEntity uomEntity = uomRepo.findById(uomId)
				.orElseThrow(() -> new IdNotFoundException("Invalid ID"));
		return UOMMapper.uomEntityToUOMRespDto(uomEntity);
	}

	@Override
	public String updateUOM(Long uomId, UOMDto uomDto) {
		UnitOfMeasurementEntity updatedUOM = UOMMapper.dtoToUOMEntity(uomDto);
		UnitOfMeasurementEntity foundUOM = uomRepo.findById(uomId)
				.orElseThrow(() -> new IdNotFoundException("Invalid ID"));

		if (updatedUOM.getUomName() != null) {
			foundUOM.setUomName(updatedUOM.getUomName());
		}
		if (updatedUOM.getDescription() != null) {
			foundUOM.setDescription(updatedUOM.getDescription());
		}
		if (updatedUOM.getUomCode() != null) {
			foundUOM.setUomCode(updatedUOM.getUomCode());
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(uomDto.getUserID());
		foundUOM.setUpdatedBy(userEntity);
		uomRepo.save(foundUOM);
		return "Unit of Measurement Entity Updated Successfully";
	}

}
