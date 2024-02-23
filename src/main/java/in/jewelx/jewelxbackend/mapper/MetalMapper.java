package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.metal.MetalDto;
import in.jewelx.jewelxbackend.dto.metal.MetalResponseDto;
import in.jewelx.jewelxbackend.dto.metal.MetalShortDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.MetalEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class MetalMapper {
	public static MetalEntity metalDtoToMetalEntity(MetalDto metalDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(metalDto.getUserID());
		MetalEntity metalEntity = new MetalEntity(metalDto.getMetalDescription(), metalDto.getMetalRate(),
				metalDto.getMetalName(), userEntity, new BrandEntity(metalDto.getBrandId()));
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

	public static MetalShortDto entityToMetalShortDto(MetalEntity entity) {
		MetalShortDto dto = new MetalShortDto();
		dto.setMetalId(entity.getMetalId());
		dto.setMetalName(entity.getMetalName());
		dto.setMetalRate(entity.getMetalRate());
		return dto;
	}
}
