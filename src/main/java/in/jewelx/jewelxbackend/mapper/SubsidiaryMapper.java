package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.accounting.AccountingDto;
import in.jewelx.jewelxbackend.dto.brand.BrandShortDetailsDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryRequestDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryResponseDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryShortDetailsDto;
import in.jewelx.jewelxbackend.dto.user.UserShortDetailsDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class SubsidiaryMapper {

	/*
	 * Used to convert SubsidiaryRequestDto to SubsidiaryEntity
	 */
	public static SubsidiaryEntity mapToSubsidiaryEntity(SubsidiaryRequestDto dto) {
		BrandEntity brand = null;
		UserEntity user = null;
		if (dto.getBrandId() != null && dto.getBrandId() != 0) {
			brand = new BrandEntity();
			brand.setBrandId(dto.getBrandId());
		}

		if (dto.getUserIdxId() != null && dto.getUserIdxId() != 0) {
			user = new UserEntity();
			user.setIdxId(dto.getUserIdxId());
		}

		SubsidiaryEntity subsidiary = new SubsidiaryEntity(brand, dto.getShopActNumber(), dto.getSubsidiaryName(),
				dto.getAddress(), dto.getCity(), dto.getGstin(), dto.getLogoUrl(), dto.getFormHeader(),
				dto.getFormFooter(), user, dto.getPinCode());
		System.out.println("-------------------------" + subsidiary);
		return subsidiary;

	}

	/*
	 * Used to convert SubsidiaryEntity to SubsidiaryResponseDto
	 */
	public static SubsidiaryResponseDto mapToResponseDto(SubsidiaryEntity subsidiary) {

		BrandShortDetailsDto brandDto = BrandMapper.brandEntitytoBrandShortDetails(subsidiary.getBrand());

		UserShortDetailsDto userDto = UserMapper.UserEntityToUserShortDetailsDto(subsidiary.getCreatedBy());

		SubsidiaryResponseDto dto = new SubsidiaryResponseDto(subsidiary.getIdxId(), subsidiary.getSubsidiaryId(),
				brandDto, subsidiary.getShopActNumber(), subsidiary.getSubsidiaryName(), subsidiary.getAddress(),
				subsidiary.getCity(), subsidiary.getGstin(), subsidiary.getLogoUrl(), subsidiary.getFormHeader(),
				subsidiary.getFormFooter(), userDto, subsidiary.getPinCode(), subsidiary.isActive());

		return dto;

	}

	/*
	 * Used to convert SubsidiaryEntity to SubsidiaryShortDetailsDto
	 */
	public static SubsidiaryShortDetailsDto mapToSubsidiaryShortDetailsDto(SubsidiaryEntity subsidiary) {
		if (subsidiary != null) {
			SubsidiaryShortDetailsDto dto = new SubsidiaryShortDetailsDto(subsidiary.getIdxId(),
					subsidiary.getSubsidiaryId(), subsidiary.getSubsidiaryName());
			return dto;
		} else {
			return null; // Or handle the case where the subsidiary is null
		}
	}

	// Used to convert Subsidiary Dto To Accounting Dto

	public static AccountingDto subsidiaryDtoToAccountingDto(SubsidiaryRequestDto dto) {
		AccountingDto accounting = new AccountingDto();
		accounting.setTransactionType("C");
		accounting.setDescription("Initial Balance when Subsidiary is Created");
		accounting.setTransactionMode("ca");
		accounting.setCashAmount(dto.getCashBalance());
		accounting.setUserId(dto.getUserIdxId());
		accounting.setBrandId(dto.getBrandId());
		return accounting;
	}
}
