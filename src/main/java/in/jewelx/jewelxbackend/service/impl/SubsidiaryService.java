package in.jewelx.jewelxbackend.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.accounting.AccountingDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryRequestDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryResponseDto;
import in.jewelx.jewelxbackend.dto.subsidiary.UpdateActiveStatusDto;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.mapper.SubsidiaryMapper;
import in.jewelx.jewelxbackend.repository.SubsidiaryRepository;
import in.jewelx.jewelxbackend.service.ISubsidiaryService;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class SubsidiaryService implements ISubsidiaryService {

	@Autowired
	SubsidiaryRepository subsidiaryRepo;

	@Autowired
	AccountingService accountingService;

	@Autowired
	ModelMapper modelMapper;

	/*
	 * get subsidiary by id
	 */
	@Override
	public Page<SubsidiaryResponseDto> getSubsidiariesByBrandId(int pageNumber, int pageSize, Long id) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<SubsidiaryEntity> subsidiaries = subsidiaryRepo.findByBrand_BrandId(id, pageRequest);
		return subsidiaries.map(SubsidiaryMapper::mapToResponseDto);
	}

	public List<SubsidiaryResponseDto> getSubsidiaryResponseDtos(Long id) {
		return subsidiaryRepo.findByBrand_BrandId(id).stream().map(SubsidiaryMapper::mapToResponseDto)
				.collect(Collectors.toList());
	}

	/*
	 * create subsidiary
	 */
	@Override
	public String createSubsidiary(SubsidiaryRequestDto dto) {
		SubsidiaryEntity subsidiary = SubsidiaryMapper.mapToSubsidiaryEntity(dto);
		UserEntity user = new UserEntity(dto.getUserIdxId());
		subsidiary.setCreatedBy(user);
		subsidiary.setUpdatedBy(user);
		SubsidiaryEntity subsidiaryEntity = subsidiaryRepo.save(subsidiary);
		AccountingDto accountingDto = SubsidiaryMapper.subsidiaryDtoToAccountingDto(dto);
		accountingDto.setSubsidiaryId(subsidiaryEntity.getIdxId());
		System.out.println(subsidiaryEntity.getIdxId());
		System.out.println(accountingDto);
		accountingService.addAccounting(accountingDto);
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
