package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.MetalDto;
import in.jewelx.jewelxbackend.dto.MetalResponseDto;
import in.jewelx.jewelxbackend.entity.MetalEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class MetalMapper {
	public static MetalEntity metalDtoToMetalEntity(MetalDto metalDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(metalDto.getUserID());
		MetalEntity metalEntity = new MetalEntity(metalDto.getMetalDescription(), metalDto.getMetalRate(),
				metalDto.getMetalName(), userEntity, userEntity);
		return metalEntity;
	}
	
	public static MetalResponseDto metalEntityToMetalRespDto(MetalEntity metalEntity) {
		MetalResponseDto metalRespDto = new MetalResponseDto();
		metalRespDto.setMetalId(metalEntity.getMetalId());
		metalRespDto.setMetalName(metalEntity.getMetalName());
		metalRespDto.setMetalDescription(metalEntity.getMetalDescription());
		metalRespDto.setMetalRate(metalEntity.getMetalRate());
		return metalRespDto;
	}

}
