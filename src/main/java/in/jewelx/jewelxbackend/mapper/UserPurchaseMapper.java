package in.jewelx.jewelxbackend.mapper;

import java.math.BigDecimal;

import in.jewelx.jewelxbackend.dto.userpurchase.UserPurchaseDto;
import in.jewelx.jewelxbackend.dto.userpurchase.UserPurchaseResponseDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.CustomerEntity;
import in.jewelx.jewelxbackend.entity.MetalEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;
import in.jewelx.jewelxbackend.entity.UserPurchaseEntity;

public class UserPurchaseMapper {

	public static UserPurchaseEntity dtoToEntity(UserPurchaseDto dto) {
		UserPurchaseEntity entity = new UserPurchaseEntity();
		entity.setMetal(new MetalEntity(dto.getMetalId()));
		entity.setCustomer(new CustomerEntity(dto.getCustomerId()));

		entity.setPurity(dto.getPurity());
		entity.setArticleDescription(dto.getArticleDescription());
		entity.setNetWeight(dto.getNetWeight());
		entity.setGrossWeight(dto.getGrossWeight());
		entity.setMetalRate(dto.getMetalRate());
		// entity.setTotalAmount(dto.getTotalAmount());
		BigDecimal totalAmount = new BigDecimal("0");
		if (dto.getChequeAmount() != null) {
			totalAmount = totalAmount.add(dto.getChequeAmount());
		}
		if (dto.getCashAmount() != null) {
			totalAmount = totalAmount.add(dto.getCashAmount());
		}
		if (dto.getNetBankingAmount() != null) {
			totalAmount = totalAmount.add(dto.getNetBankingAmount());
		}
		entity.setTotalAmount(totalAmount);
		entity.setSubsidiary(new SubsidiaryEntity(dto.getSubsidiaryId()));
		entity.setBrand(new BrandEntity(dto.getBrandId()));
		return entity;
	}

	public static UserPurchaseResponseDto entityToDto(UserPurchaseEntity entity) {
		UserPurchaseResponseDto dto = new UserPurchaseResponseDto(entity.getIdxId(), entity.getPurchaseId(),
				MetalMapper.entityToMetalShortDto(entity.getMetal()),
				CustomerMapper.entityToCustomerShortDto(entity.getCustomer()), entity.getPurity(),
				entity.getArticleDescription(), entity.getNetWeight(), entity.getGrossWeight(), entity.getMetalRate(),
				entity.getTotalAmount(), entity.getAccounting().getIdxId(),
				SubsidiaryMapper.mapToSubsidiaryShortDetailsDto(entity.getSubsidiary()),
				entity.getBrand().getBrandId());
		return dto;
	}

}
