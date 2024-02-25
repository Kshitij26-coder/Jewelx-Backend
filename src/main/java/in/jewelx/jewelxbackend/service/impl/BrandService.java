package in.jewelx.jewelxbackend.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import in.jewelx.jewelxbackend.dto.brand.BrandDto;
import in.jewelx.jewelxbackend.dto.brand.BrandResponseDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.BrandMapper;
import in.jewelx.jewelxbackend.repository.BrandRepository;
import in.jewelx.jewelxbackend.service.IBrandService;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class BrandService implements IBrandService {

	@Autowired
	private BrandRepository brandRepo;

	@Autowired
	private Cloudinary cloudinary;

	@Override
	public String upload(MultipartFile file, Long brandId) {
		BrandEntity existingEntity = brandRepo.findById(brandId)
				.orElseThrow(() -> new IdNotFoundException("Invalid id"));
		try {
			Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
			existingEntity.setImageUrl(data.get("url").toString());
			brandRepo.save(existingEntity);
			;
			return data.get("url").toString();

		} catch (IOException e) {
			throw new RuntimeException("Image uploading fail !!!");
		} // creating map and passing

	}

	@Override
	@Transactional
	public BrandEntity createBrand(BrandEntity brandEntity) {
		System.out.println(brandEntity);
		if (brandEntity == null) {
			throw new NullObjectException("Brand Entity does not contains any data");
		}
		return brandRepo.save(brandEntity);
	}

	@Override
	public Page<BrandResponseDto> getAllBrands(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<BrandEntity> brandsPage = brandRepo.findAll(pageRequest);
		return brandsPage.map(BrandMapper::brandEntityToBrandRespDto);
	}

	// created to populate drop-down
	@Override
	public List<BrandResponseDto> getAllBrandsPaginationFalse() {
		List<BrandEntity> brands = brandRepo.findAll();
		return brands.stream().map(BrandMapper::brandEntityToBrandRespDto).collect(Collectors.toList());
	}

	@Override
	public String deleteBrandById(Long id) {
		getOneBrand(id);
		brandRepo.deleteById(id);
		return "Brand with id: " + id + " is deleted Successfully";
	}

	@Override
	public BrandResponseDto getOneBrand(Long id) {
		BrandEntity foundBrand = brandRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Invalid id"));
		return BrandMapper.brandEntityToBrandRespDto(foundBrand);
	}

	@Override
	public String updateBrand(Long id, BrandDto brandDto) {
		BrandEntity updatedBrand = BrandMapper.dtoToBrandEntity(brandDto);
		BrandEntity foundBrand = brandRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Invalid id"));
		if (updatedBrand.getName() != null) {
			foundBrand.setName(updatedBrand.getName());
		}
		if (updatedBrand.getDescription() != null) {
			foundBrand.setDescription(updatedBrand.getDescription());
		}
		if (updatedBrand.getImageUrl() != null) {
			foundBrand.setImageUrl(updatedBrand.getImageUrl());
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(brandDto.getUserId());
		foundBrand.setUpdatedBy(userEntity);
		brandRepo.save(foundBrand);
		return "Brand updated Successfully";
	}

}
