package in.jewelx.jewelxbackend.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.weightdetails.WeightDetailsDto;
import in.jewelx.jewelxbackend.dto.weightdetails.WeightDetailsResponseDto;
import in.jewelx.jewelxbackend.entity.CustomerEntity;
import in.jewelx.jewelxbackend.entity.MetalEntity;
import in.jewelx.jewelxbackend.entity.UnitOfMeasurementEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.entity.WeightDetailEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.WeightDetailMapper;
import in.jewelx.jewelxbackend.repository.WeightDetailRepository;
import in.jewelx.jewelxbackend.service.IWeightDetailService;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class WeightDetailService implements IWeightDetailService {

	@Autowired
	private WeightDetailRepository weightDetailRepo;

	@Override
	public String addWeightDetail(WeightDetailsDto weightDetailDto) {
		if (weightDetailDto == null)
			throw new NullObjectException("WeightDetails Dto is null");
		weightDetailRepo.save(WeightDetailMapper.dtoToEntity(weightDetailDto));
		return "Weight Detail Added Successfully";
	}

	@Override
	public Page<WeightDetailsResponseDto> getAllWeightDetails(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<WeightDetailEntity> allWeightDetails = weightDetailRepo.findAll(pageRequest);
		return allWeightDetails.map(WeightDetailMapper::entityToDto);
	}

	@Override
	public String deleteWeightDetailById(UUID id) {
		getWeightDetailById(id);
		weightDetailRepo.deleteByDetailId(id);
		return "Weight Detail deleted successfully";
	}

	@Override
	public WeightDetailsResponseDto getWeightDetailById(UUID id) {
		WeightDetailEntity foundWeightDetailEntity = weightDetailRepo.findByDetailId(id)
				.orElseThrow(() -> new IdNotFoundException("Invalid Weight Detail Id"));
		return WeightDetailMapper.entityToDto(foundWeightDetailEntity);
	}

	@Override
	public String updateWeightDetail(UUID id, WeightDetailsDto weightDetailsDto) {
		WeightDetailEntity updatedWeightDetail = WeightDetailMapper.dtoToEntity(weightDetailsDto);
		WeightDetailEntity foundWeightDetail = weightDetailRepo.findByDetailId(id)
				.orElseThrow(() -> new IdNotFoundException("Invalid Weight Detail Id"));

		foundWeightDetail.setMetalWeight(updatedWeightDetail.getMetalWeight());

		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(updatedWeightDetail.getIdxId());
		foundWeightDetail.setUpdatedBy(userEntity);

		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setIdxId(updatedWeightDetail.getIdxId());
		foundWeightDetail.setUpdatedBy(userEntity);

		MetalEntity metalEntity = new MetalEntity();
		metalEntity.setMetalId(updatedWeightDetail.getIdxId());
		foundWeightDetail.setMetal(metalEntity);

		UnitOfMeasurementEntity unitOfMeasurement = new UnitOfMeasurementEntity();
		unitOfMeasurement.setUomId(updatedWeightDetail.getIdxId());
		foundWeightDetail.setUom(unitOfMeasurement);

		weightDetailRepo.save(foundWeightDetail);
		return "Weight Detail updated successfully";
	}

}
