package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.brand.BrandDto;
import in.jewelx.jewelxbackend.dto.brand.BrandResponseDto;
import in.jewelx.jewelxbackend.dto.brand.BrandShortDetailsDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class BrandMapper {

	/*
	 * Used to convert brandDto to BrandEntity
	 */
	public static BrandEntity dtoToBrandEntity(BrandDto brandDto) {
		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setDescription(brandDto.getDescription());
		//brandEntity.setImageUrl(brandDto.getImageUrl());
		brandEntity.setName(brandDto.getName());
		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(brandDto.getUserId());
		brandEntity.setUpdatedBy(userEntity);
		return brandEntity;
	}

	/*
	 * Used to convert BrandEntity to BrandResponseDto
	 */
	public static BrandResponseDto brandEntityToBrandRespDto(BrandEntity brandEntity) {
		BrandResponseDto brandRespDto = new BrandResponseDto();
		brandRespDto.setBrandId(brandEntity.getBrandId());
		brandRespDto.setName(brandEntity.getName());
		brandRespDto.setDescription(brandEntity.getDescription());
		brandRespDto.setImageUrl(brandEntity.getImageUrl());
		return brandRespDto;
	}

	/*
	 * Used to convert BrandEntity to BrandShortDetailsDto
	 */
	public static BrandShortDetailsDto brandEntitytoBrandShortDetails(BrandEntity brand) {
		if (brand != null) {
			BrandShortDetailsDto dto = new BrandShortDetailsDto();
			dto.setBrandId(brand.getBrandId());
			dto.setName(brand.getName());
			dto.setImageUrl(brand.getImageUrl());
			return dto;
		} else {
			return null; // Or handle the case where the brand is null
		}

	}
}
