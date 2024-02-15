package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.weightdetails.WeightDetailsDto;
import in.jewelx.jewelxbackend.dto.weightdetails.WeightDetailsResponseDto;
import in.jewelx.jewelxbackend.entity.CustomerEntity;
import in.jewelx.jewelxbackend.entity.MetalEntity;
import in.jewelx.jewelxbackend.entity.UnitOfMeasurementEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.entity.WeightDetailEntity;

public class WeightDetailMapper {

	public static WeightDetailEntity dtoToEntity(WeightDetailsDto weightDetailsDto) {
		WeightDetailEntity weightDetailEntity = new WeightDetailEntity();
		weightDetailEntity.setMetalWeight(weightDetailsDto.getMetalWeight());
		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(weightDetailsDto.getUserID());
		weightDetailEntity.setCreatedBy(userEntity);
		weightDetailEntity.setUpdatedBy(userEntity);

		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setIdxId(weightDetailsDto.getCustomer());
		weightDetailEntity.setCustomer(customerEntity);

		MetalEntity metalEntity = new MetalEntity();
		metalEntity.setMetalId(weightDetailsDto.getMetalID());
		weightDetailEntity.setMetal(metalEntity);

		UnitOfMeasurementEntity unitOfMeasurementEntity = new UnitOfMeasurementEntity();
		unitOfMeasurementEntity.setUomId(weightDetailsDto.getUomId());
		weightDetailEntity.setUom(unitOfMeasurementEntity);
		return weightDetailEntity;

	}

	public static WeightDetailsResponseDto entityToDto(WeightDetailEntity weightDetailEntity) {
		WeightDetailsResponseDto weightDetailsRespDto = new WeightDetailsResponseDto();
		weightDetailsRespDto.setIdxId(weightDetailEntity.getIdxId());
		weightDetailsRespDto.setDetailId(weightDetailEntity.getDetailId());

//		weightDetailsRespDto.setCustomer(weightDetailEntity.getCustomer().getIdxId());
//		weightDetailsRespDto.setMetalID(weightDetailEntity.getMetal().getMetalId());

		weightDetailsRespDto.setMetalWeight(weightDetailEntity.getMetalWeight());
		return weightDetailsRespDto;

	}
}
