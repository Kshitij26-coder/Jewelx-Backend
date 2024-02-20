package in.jewelx.jewelxbackend.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import in.jewelx.jewelxbackend.dto.accounting.AccountingDto;
import in.jewelx.jewelxbackend.dto.customer.CustomerShortDto;
import in.jewelx.jewelxbackend.dto.customerorder.CustomerOrderDto;
import in.jewelx.jewelxbackend.dto.customerorder.CustomerOrderRespDto;
import in.jewelx.jewelxbackend.dto.metal.MetalShortDto;
import in.jewelx.jewelxbackend.dto.metalstock.MetalStockDto;
import in.jewelx.jewelxbackend.dto.weightdetails.WeightDetailsDto;
import in.jewelx.jewelxbackend.entity.CustomerEntity;
import in.jewelx.jewelxbackend.entity.CustomerOrderEntity;
import in.jewelx.jewelxbackend.entity.MetalEntity;

public class CustomerOrderMapper {
	
	@Autowired
	ModelMapper modelMapper;
	public static CustomerOrderEntity dtoToEntity(CustomerOrderDto dto) {
		MetalEntity metal = new MetalEntity();
		metal.setMetalId(dto.getMetalId());
		
		CustomerEntity customer = new CustomerEntity();
		customer.setIdxId(dto.getCustomerId());
		
		//remaining fields are set using constructor
		CustomerOrderEntity entity = new CustomerOrderEntity(metal, customer, dto.getPurity(), dto.getArticleDescription(), dto.getGrossWeight(), dto.getNetWeight(), dto.getPaidAmount(), dto.getOrderStatus(), dto.getFullfillDate(), dto.getMetalRate());;
		return entity;
	}
	
	public static CustomerOrderRespDto entityToDto(CustomerOrderEntity entity) {
		
		MetalShortDto metal = MetalMapper.entityToMetalShortDto(entity.getMetal());
		
		CustomerShortDto customer = CustomerMapper.entityToCustomerShortDto(entity.getCustomer());
		
		CustomerOrderRespDto dto = new CustomerOrderRespDto(entity.getIdxId(), entity.getOrderId(),metal,  customer, entity.getPurity(), entity.getArticleDescription(), entity.getGrossWeight(), entity.getNetWeight(), entity.getOrderStatus(), entity.getFulfillDate(), entity.getOrderDate());
		
		return dto;
	}
	
	public static AccountingDto customerOrderDtoToAccountingDto(CustomerOrderDto dto) {
		AccountingDto accountingDto = new AccountingDto();
		accountingDto.setTransactionType(dto.getTransactionType());
		accountingDto.setDescription(dto.getTransactionDescription());
		accountingDto.setTransactionMode(dto.getTransactionMode());
		accountingDto.setChequeNo(dto.getChequeNo());
		accountingDto.setChequeAmount(dto.getChequeAmount());
		accountingDto.setCashAmount(dto.getCashAmount());
		accountingDto.setNetBankingUTR(dto.getNetBankingUTR());
		accountingDto.setNetBankingAmount(dto.getNetBankingAmount());
		accountingDto.setUserId(dto.getUserID());
		return accountingDto;
	}
	
	public static MetalStockDto customerOrderDtoToMetalStockDto(CustomerOrderDto dto) {
		MetalStockDto metalStockDto = new MetalStockDto();
		metalStockDto.setWeight(dto.getNetWeight());
		metalStockDto.setUserId(dto.getUserID());
		metalStockDto.setMetalId(dto.getMetalId());
		return metalStockDto;
	}
	
	public static WeightDetailsDto cutomerOrderDtoToWeightDetailsDto(CustomerOrderDto dto) {
		WeightDetailsDto weightDetailsDto = new WeightDetailsDto();
		weightDetailsDto.setCustomer(dto.getCustomerId());
		weightDetailsDto.setMetalID(dto.getMetalId());
		weightDetailsDto.setMetalWeight(dto.getNetWeight());
		weightDetailsDto.setUomId(dto.getUomId());
		weightDetailsDto.setUserID(dto.getUserID());
		weightDetailsDto.setWeightTransactionType(dto.getWeightTransactionType());
		return weightDetailsDto;
	}
}
