package in.jewelx.jewelxbackend.service;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.uom.UOMDto;
import in.jewelx.jewelxbackend.dto.uom.UOMResponseDto;

public interface IUOMService {

	String createUOM(UOMDto UOMDto);

	String deleteUOMById(Long uomId);

	UOMResponseDto getOneUOM(Long uomId);

	String updateUOM(Long uomId, UOMDto uomDto);

	Page<UOMResponseDto> getAllUOM(int pageNumber, int pageSize, Long brandId);
}
