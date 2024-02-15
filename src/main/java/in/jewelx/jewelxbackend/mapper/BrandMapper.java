package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.brand.BrandDto;
import in.jewelx.jewelxbackend.dto.brand.BrandResponseDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class BrandMapper {

	public static BrandEntity dtoToBrandEntity(BrandDto brandDto) {
		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setDescription(brandDto.getDescription());
		brandEntity.setImageUrl(brandDto.getImageUrl());
		brandEntity.setName(brandDto.getName());
		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(brandDto.getUserId());
		brandEntity.setUpdatedBy(userEntity);
		return brandEntity;
	}

	public static BrandResponseDto brandEntityToBrandRespDto(BrandEntity brandEntity) {
		BrandResponseDto brandRespDto = new BrandResponseDto();
		brandRespDto.setBrandId(brandEntity.getBrandId());
		brandRespDto.setName(brandEntity.getName());
		brandRespDto.setDescription(brandEntity.getDescription());
		brandRespDto.setImageUrl(brandEntity.getImageUrl());
		return brandRespDto;
	}
}
