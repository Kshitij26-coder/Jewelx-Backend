package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.uom.UOMDto;
import in.jewelx.jewelxbackend.dto.uom.UOMResponseDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.UnitOfMeasurementEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class UOMMapper {

	public static UnitOfMeasurementEntity dtoToUOMEntity(UOMDto uomDto) {

		UnitOfMeasurementEntity uomEntity = new UnitOfMeasurementEntity();
		uomEntity.setUomCode(uomDto.getUomCode());
		uomEntity.setUomName(uomDto.getUomName());
		uomEntity.setDescription(uomDto.getDescription());
		UserEntity userEntity = new UserEntity();
		BrandEntity brandEntity = new BrandEntity(uomDto.getBrandId());
		userEntity.setIdxId(uomDto.getUserID());
		uomEntity.setBrand(brandEntity);
		uomEntity.setCreatedBy(userEntity);
		uomEntity.setUpdatedBy(userEntity);
		BrandEntity brand = new BrandEntity(uomDto.getBrandId());
		uomEntity.setBrand(brand);
		return uomEntity;
	}

	public static UOMResponseDto uomEntityToUOMRespDto(UnitOfMeasurementEntity uomEntity) {
		UOMResponseDto uomResp = new UOMResponseDto();
		uomResp.setUomId(uomEntity.getUomId());
		uomResp.setUomName(uomEntity.getUomName());
		uomResp.setDescription(uomEntity.getDescription());
		uomResp.setUomCode(uomEntity.getUomCode());
		return uomResp;
	}
}
