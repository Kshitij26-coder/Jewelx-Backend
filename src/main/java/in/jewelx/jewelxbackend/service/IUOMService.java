package in.jewelx.jewelxbackend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.uom.UOMDto;
import in.jewelx.jewelxbackend.dto.uom.UOMResponseDto;

public interface IUOMService {

	String createUOM(UOMDto UOMDto);

	String deleteUOMById(Long uomId);

	UOMResponseDto getOneUOM(Long uomId);

	String updateUOM(Long uomId, UOMDto uomDto);

	Page<UOMResponseDto> getAllUOMByBrand(int pageNumber, int pageSize, Long brandId);

	List<UOMResponseDto> getAllUOMByBrand(Long brandId);

}
