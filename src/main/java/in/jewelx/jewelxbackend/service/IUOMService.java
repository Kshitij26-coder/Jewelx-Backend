package in.jewelx.jewelxbackend.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import in.jewelx.jewelxbackend.dto.uom.UOMDto;
import in.jewelx.jewelxbackend.dto.uom.UOMResponseDto;

public interface IUOMService {
	
	String createUOM(UOMDto UOMDto) throws SQLIntegrityConstraintViolationException;

	List<UOMResponseDto>getAllUOM();
	
	String deleteUOMById(Long uomId);
	
	UOMResponseDto getOneUOM(Long uomId);
	
	String updateUOM(Long uomId, UOMDto uomDto);
}
