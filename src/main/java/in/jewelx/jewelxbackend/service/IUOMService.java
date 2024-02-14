package in.jewelx.jewelxbackend.service;

import java.util.List;

import in.jewelx.jewelxbackend.dto.uom.UOMDto;
import in.jewelx.jewelxbackend.dto.uom.UOMResponseDto;

public interface IUOMService {
	
	String createUOM(UOMDto UOMDto);

	List<UOMResponseDto>getAllUOM();
	
	String deleteUOMById(Long uomId);
	
	UOMResponseDto getOneUOM(Long uomId);
	
	String updateUOM(Long uomId, UOMDto uomDto);
}
