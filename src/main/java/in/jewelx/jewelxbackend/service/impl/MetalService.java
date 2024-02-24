package in.jewelx.jewelxbackend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.metal.MetalDto;
import in.jewelx.jewelxbackend.dto.metal.MetalResponseDto;
import in.jewelx.jewelxbackend.entity.MetalEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.MetalMapper;
import in.jewelx.jewelxbackend.repository.MetalRepository;
import in.jewelx.jewelxbackend.service.IMetalService;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class MetalService implements IMetalService {

	@Autowired
	MetalRepository metalRepo;

	@Override
	public String createMetal(MetalDto metalDto) {
		if (metalDto == null) {
			throw new NullObjectException("Metal Dto is null");
		} else {
			MetalEntity metal = MetalMapper.metalDtoToMetalEntity(metalDto);
			metal.setCreatedBy(new UserEntity(metalDto.getUserID()));
			metalRepo.save(metal);
			return "Successfully Add metal data";
		}

	}

	@Override
	public Page<MetalResponseDto> getAllMetals(int pageNumber, int pageSize, Long brandId) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<MetalEntity> allMetals = metalRepo.findByBrand_BrandId(brandId, pageRequest);
		return allMetals.map(MetalMapper::metalEntityToMetalRespDto);
	}

	public List<MetalResponseDto> getAllMetalsWohoutPagination(Long brandId) {
		List<MetalEntity> allMetals = metalRepo.findByBrand_BrandId(brandId);
		return allMetals.stream().map(MetalMapper::metalEntityToMetalRespDto).collect(Collectors.toList());
	}

	@Override
	public String deleteMetalById(Long id) {
		getOneMetal(id);
		metalRepo.deleteById(id);
		return "Metal with id: " + id + " deleted successfully";
	}

	@Override
	public MetalResponseDto getOneMetal(Long id) {
		MetalEntity foundMetal = null;
		Optional<MetalEntity> opt = metalRepo.findById(id);
		if (!opt.isEmpty()) {
			foundMetal = opt.get();

			return MetalMapper.metalEntityToMetalRespDto(foundMetal);
		}
		throw new IdNotFoundException("Metal not found of id :" + id);
	}

	@Override
	public String updateMetal(Long id, MetalDto metalDto) {
		MetalEntity updatedMetal = MetalMapper.metalDtoToMetalEntity(metalDto);
		Optional<MetalEntity> opt = metalRepo.findById(id);
		if (!opt.isEmpty()) {
			MetalEntity existingMetal = opt.get();

			existingMetal.setMetalName(updatedMetal.getMetalName());

			existingMetal.setMetalDescription(updatedMetal.getMetalDescription());

			existingMetal.setMetalRate(updatedMetal.getMetalRate());

			UserEntity userEntity = new UserEntity();
			userEntity.setIdxId(metalDto.getUserID());
			existingMetal.setUpdatedBy(userEntity);
			metalRepo.save(existingMetal);
			return "Updated Successfully Metal of id: " + id;
		}
		throw new IdNotFoundException("Invalid Metal Id");
	}

}
