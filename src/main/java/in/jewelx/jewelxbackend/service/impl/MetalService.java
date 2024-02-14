package in.jewelx.jewelxbackend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class MetalService implements IMetalService {

	@Autowired
	MetalRepository metalRepo;

	@Override
	public String createMetal(MetalDto metalDto) {
		if (metalDto == null) {
			throw new NullObjectException("Metal not Found");
		} else {
			metalRepo.save(MetalMapper.metalDtoToMetalEntity(metalDto));
			return "Successfull save metal data";
		}
		
	}

	@Override
	public List<MetalResponseDto> getAllMetals() {
		List<MetalEntity> allMetals = metalRepo.findAll();
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
			if (updatedMetal.getMetalName() != null) {
				existingMetal.setMetalName(updatedMetal.getMetalName());
			}
			if (updatedMetal.getMetalDescription() != null) {
				existingMetal.setMetalDescription(updatedMetal.getMetalDescription());
			}
			if (updatedMetal.getMetalRate() != null) {
				existingMetal.setMetalRate(updatedMetal.getMetalRate());
			}
			UserEntity userEntity = new UserEntity();
			userEntity.setUserId(metalDto.getUserID());
			existingMetal.setUpdatedBy(userEntity);
			metalRepo.save(existingMetal);
			return "Updated Successfully Metal of id: " + id;
		}
		throw new IdNotFoundException("Invalid Metal Id");
	}

}
