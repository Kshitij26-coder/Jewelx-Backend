package in.jewelx.jewelxbackend.service;

import java.util.List;

import in.jewelx.jewelxbackend.dto.metal.MetalDto;
import in.jewelx.jewelxbackend.dto.metal.MetalResponseDto;

public interface IMetalService {
	String createMetal(MetalDto metalDto);

	List<MetalResponseDto> getAllMetals();

	String deleteMetalById(Long id);

	MetalResponseDto getOneMetal(Long id);

	String updateMetal(Long id, MetalDto metalDto);
}
