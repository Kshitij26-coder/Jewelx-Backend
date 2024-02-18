package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.metalstock.MetalStockDto;
import in.jewelx.jewelxbackend.dto.metalstock.MetalStockRepoDto;
import in.jewelx.jewelxbackend.dto.user.UserShortDetailsDto;
import in.jewelx.jewelxbackend.entity.MetalEntity;
import in.jewelx.jewelxbackend.entity.MetalStockEntity;

public class MetalStockMapper {

	public static MetalStockEntity dtoToEntity(MetalStockDto dto) {
		
		// for metal entity
		MetalEntity metalEntity = new MetalEntity();
		metalEntity.setMetalId(dto.getMetalId());
		
		MetalStockEntity entity = new MetalStockEntity(metalEntity, dto.getWeight(), dto.getWeight());
		
		return entity;
	}
	
	public static MetalStockRepoDto entityToResponseDto(MetalStockEntity entity) {
		UserShortDetailsDto updatedBy = UserMapper.UserEntityToUserShortDetailsDto(entity.getUpdatedBy());
		UserShortDetailsDto createdBy = UserMapper.UserEntityToUserShortDetailsDto(entity.getCreatedBy());
		MetalStockRepoDto dto = new MetalStockRepoDto(entity.getStockId(), entity.getOpeningWeight(), entity.getClosingWeight(),updatedBy,createdBy);
		return dto;
	}
}
