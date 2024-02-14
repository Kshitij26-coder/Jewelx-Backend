package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.UOMDto;
import in.jewelx.jewelxbackend.dto.UOMResponseDto;
import in.jewelx.jewelxbackend.entity.UnitOfMeasurementEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class UOMMapper {
	
	public static UnitOfMeasurementEntity dtoToUOMEntity(UOMDto uomDto) {
		
		UnitOfMeasurementEntity uomEntity = new UnitOfMeasurementEntity();
		uomEntity.setUomCode(uomDto.getUomCode());
		uomEntity.setUomName(uomDto.getUomName());
		uomEntity.setDescription(uomDto.getDescription());
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(uomDto.getUserID());
		uomEntity.setCreatedBy(userEntity);
		uomEntity.setUpdatedBy(userEntity);
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
