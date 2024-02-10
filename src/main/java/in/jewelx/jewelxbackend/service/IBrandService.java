package in.jewelx.jewelxbackend.service;

import java.util.List;

import in.jewelx.jewelxbackend.dto.BrandDto;
import in.jewelx.jewelxbackend.dto.BrandResponseDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;

public interface IBrandService {
	BrandEntity createBrand(BrandEntity brandEntity);

	List<BrandResponseDto> getAllBrands();

	String deleteBrandById(Integer id);

	BrandResponseDto getOneBrand(Integer id);
	
	String updateBrand(Integer id, BrandDto brandDto);

}
