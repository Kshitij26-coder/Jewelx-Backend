package in.jewelx.jewelxbackend.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryRequestDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryResponseDto;
import in.jewelx.jewelxbackend.dto.subsidiary.UpdateActiveStatusDto;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.mapper.SubsidiaryMapper;
import in.jewelx.jewelxbackend.repository.SubsidiaryRepository;
import in.jewelx.jewelxbackend.service.ISubsidiaryService;

@Service
public class SubsidiaryService implements ISubsidiaryService {

	@Autowired
	SubsidiaryRepository subsidiaryRepo;

	@Autowired
	ModelMapper modelMapper;

	/*
	 * get subsidiary by id
	 */
	@Override
	public List<SubsidiaryResponseDto> getSubsidiariesByBrandId(Long id) {
		List<SubsidiaryEntity> subsidiaries = subsidiaryRepo.findByBrandId(id);
		return subsidiaries.stream().map(SubsidiaryMapper::mapToResponseDto).collect(Collectors.toList());
	}

	/*
	 * create subsidiary
	 */
	@Override
	public String createSubsidiary(SubsidiaryRequestDto dto) {
		subsidiaryRepo.save(SubsidiaryMapper.mapToSubsidiaryEntity(dto));
		return "Subsidirary Created Successfully";
	}

	/*
	 * To prevent enumeration attacks passing UUID in URL and searching with
	 * specified UUID
	 */
	@Override
	public SubsidiaryResponseDto getSubsidiaryDtoById(UUID id) {

		return SubsidiaryMapper.mapToResponseDto(this.getSubsidiaryById(id));
	}

	/*
	 * This is a helper method
	 */
	@Override
	public SubsidiaryEntity getSubsidiaryById(UUID id) {
		return subsidiaryRepo.findBySubsidiaryId(id).orElseThrow(() -> new IdNotFoundException(id + " not found"));
	}

	/*
	 * Update the current tuple in DB
	 */
	@Override
	public String updateSubsidiaryById(UUID id, SubsidiaryRequestDto updatedDto) {
		SubsidiaryEntity exsistingSubsidiary = this.getSubsidiaryById(id);
		SubsidiaryEntity updatedSubsidiary = SubsidiaryMapper.mapToSubsidiaryEntity(updatedDto);
		// Because every time object is created it will have an UUID, we want to retain
		// the UUID which is already in DB
		UserEntity updatedBy = new UserEntity();
		updatedBy.setIdxId(updatedDto.getUserIdxId());
		updatedSubsidiary.setSubsidiaryId(null);
		exsistingSubsidiary.setUpdatedBy(updatedBy);
		System.out.println(exsistingSubsidiary);
		modelMapper.map(updatedSubsidiary, exsistingSubsidiary);
		System.out.println(exsistingSubsidiary);
		System.out.println(updatedSubsidiary);
		subsidiaryRepo.save(exsistingSubsidiary);
		return "updated successfully";
	}

	/*
	 * Delete tuple from DB by id
	 */
	@Override
	public String deleteSubsidiaryById(UUID id) {
		SubsidiaryEntity exsistingSubsidiary = this.getSubsidiaryById(id);
		subsidiaryRepo.deleteById(exsistingSubsidiary.getIdxId());
		return "Deleted successfully";
	}

	@Override
	public void updateActiveStatus(UpdateActiveStatusDto dto) {
		SubsidiaryEntity exsistingSubsidiary = subsidiaryRepo.findById(dto.getSubsidiaryId())
				.orElseThrow(() -> new IdNotFoundException("id: " + dto.getSubsidiaryId() + " not found"));
		UserEntity updatedBy = new UserEntity();
		updatedBy.setIdxId(dto.getUserIdxId());
		exsistingSubsidiary.setUpdatedBy(updatedBy);
		exsistingSubsidiary.setActive(dto.getIsActive());
		System.out.println(dto);
		System.out.println(exsistingSubsidiary);
		subsidiaryRepo.save(exsistingSubsidiary);
	}

}
