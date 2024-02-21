package in.jewelx.jewelxbackend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryRequestDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryResponseDto;
import in.jewelx.jewelxbackend.dto.subsidiary.UpdateActiveStatusDto;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;

public interface ISubsidiaryService {

	String createSubsidiary(SubsidiaryRequestDto dto);

	SubsidiaryResponseDto getSubsidiaryDtoById(UUID id);

	SubsidiaryEntity getSubsidiaryById(UUID id);

	String updateSubsidiaryById(UUID id, SubsidiaryRequestDto updatedDto);

	String deleteSubsidiaryById(UUID id);

	void updateActiveStatus(UpdateActiveStatusDto dto);

	Page<SubsidiaryResponseDto> getSubsidiariesByBrandId(int pageNumber, int pageSize, Long id);

}
