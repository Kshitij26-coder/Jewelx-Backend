package in.jewelx.jewelxbackend.service;

import org.springframework.data.domain.Page;
import in.jewelx.jewelxbackend.dto.metal.MetalDto;
import in.jewelx.jewelxbackend.dto.metal.MetalResponseDto;

public interface IMetalService {
	String createMetal(MetalDto metalDto);

	String deleteMetalById(Long id);

	MetalResponseDto getOneMetal(Long id);

	String updateMetal(Long id, MetalDto metalDto);

	Page<MetalResponseDto> getAllMetals(int pageNumber, int pageSize);
}
