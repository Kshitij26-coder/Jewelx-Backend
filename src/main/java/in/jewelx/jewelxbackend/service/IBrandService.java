package in.jewelx.jewelxbackend.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import in.jewelx.jewelxbackend.dto.brand.BrandDto;
import in.jewelx.jewelxbackend.dto.brand.BrandResponseDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;

public interface IBrandService {
	BrandEntity createBrand(BrandEntity brandEntity);

	String deleteBrandById(Long id);

	BrandResponseDto getOneBrand(Long id);

	String updateBrand(Long id, BrandDto brandDto);

	Page<BrandResponseDto> getAllBrands(int pageNumber, int pageSize);

	List<BrandResponseDto> getAllBrandsPaginationFalse();

	String upload(MultipartFile file, Long brandId);

	// BrandEntity createBrand(BrandDto brandDto);

}
