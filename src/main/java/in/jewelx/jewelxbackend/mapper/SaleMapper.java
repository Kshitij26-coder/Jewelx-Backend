package in.jewelx.jewelxbackend.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import in.jewelx.jewelxbackend.dto.sale.SaleDto;
import in.jewelx.jewelxbackend.dto.sale.SaleResponseById;
import in.jewelx.jewelxbackend.dto.sale.SaleResponseDto;
import in.jewelx.jewelxbackend.entity.AccountingEntity;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.CustomerEntity;
import in.jewelx.jewelxbackend.entity.CustomerOrderEntity;
import in.jewelx.jewelxbackend.entity.SaleEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;

public class SaleMapper {

	@Autowired
	ModelMapper modelMapper;

	public static SaleEntity dtoToEntity(SaleDto dto) {
		SaleEntity sale = new SaleEntity();
		sale.setCustomer(new CustomerEntity(dto.getCustomerId()));
		sale.setSgst(dto.getSgst());
		sale.setCgst(dto.getCgst());
		sale.setTotalMakingCharges(dto.getTotalMakingCharges());
		sale.setDiscount(dto.getDiscount());
		sale.setNetAmount(dto.getNetAmount());
		sale.setPayableAmount(dto.getPayableAmount());
		sale.setSubsidiary(new SubsidiaryEntity(dto.getSubsidiaryId()));
		sale.setAccounting(new AccountingEntity(dto.getAccountingId()));
		sale.setOrder(
				(dto.getOrderId() == null || dto.getOrderId() == 0) ? null : new CustomerOrderEntity(dto.getOrderId()));
		sale.setBrand(new BrandEntity(dto.getBrandId()));
		return sale;
	}

	public static SaleResponseDto entityToDto(SaleEntity entity) {
		SaleResponseDto dto = new SaleResponseDto();
		dto.setSaleIdxId(entity.getIdxId());
		dto.setSaleId(entity.getSaleId());
		dto.setCreatedOn(entity.getCreatedOn());
		dto.setCustomer(CustomerMapper.entityToCustomerShortDto(entity.getCustomer()));
		dto.setNetAmount(entity.getNetAmount());
		dto.setPayableAmount(entity.getPayableAmount());
		dto.setCustomerOrderId(entity.getOrder() == null ? null : entity.getOrder().getIdxId());
		dto.setAccounting(AccountingMapper.entityToShortDto(entity.getAccounting()));
		dto.setUser(UserMapper.UserEntityToUserShortDetailsDto(entity.getCreatedBy()));
		return dto;
	}

	public static SaleResponseById entityToResponseDtoByUUID(SaleEntity entity) {
		
		SaleResponseById dto = new SaleResponseById();
		dto.setSaleIdxId(entity.getIdxId());
		dto.setSaleId(entity.getSaleId());
		dto.setCreatedOn(entity.getCreatedOn());
		dto.setCustomer(CustomerMapper.entityToCustomerShortDto(entity.getCustomer()));
		dto.setNetAmount(entity.getNetAmount());
		dto.setPayableAmount(entity.getPayableAmount());
		dto.setCustomerOrderId(entity.getOrder() == null ? null : entity.getOrder().getIdxId());
		dto.setAccounting(AccountingMapper.entityToShortDto(entity.getAccounting()));
		dto.setUser(UserMapper.UserEntityToUserShortDetailsDto(entity.getCreatedBy()));
		dto.setItemSaleList(null);
		return dto;
	}
}
