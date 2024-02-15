package in.jewelx.jewelxbackend.service.impl;

import java.util.Optional;

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
	public Page<MetalResponseDto> getAllMetals(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<MetalEntity> allMetals = metalRepo.findAll(pageRequest);
		return allMetals.map(MetalMapper::metalEntityToMetalRespDto);
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
