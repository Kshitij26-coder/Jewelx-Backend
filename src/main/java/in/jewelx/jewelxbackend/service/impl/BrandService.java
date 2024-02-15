package in.jewelx.jewelxbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.brand.BrandDto;
import in.jewelx.jewelxbackend.dto.brand.BrandResponseDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.mapper.BrandMapper;
import in.jewelx.jewelxbackend.repository.BrandRepository;
import in.jewelx.jewelxbackend.service.IBrandService;
import jakarta.transaction.Transactional;

@Service
public class BrandService implements IBrandService {

	@Autowired
	private BrandRepository brandRepo;

	@Override
	@Transactional
	public BrandEntity createBrand(BrandEntity brandEntity) {
		System.out.println(brandEntity);
		if (brandEntity == null) {
			throw new NullPointerException("Brand Entity does not contains any data");
		}
		return brandRepo.save(brandEntity);
	}

	@Override
	public Page<BrandResponseDto> getAllBrands(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<BrandEntity> brandsPage = brandRepo.findAll(pageRequest);
		return brandsPage.map(BrandMapper::brandEntityToBrandRespDto);
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
