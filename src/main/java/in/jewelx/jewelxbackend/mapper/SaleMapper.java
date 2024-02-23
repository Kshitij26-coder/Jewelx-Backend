package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.sale.SaleDto;
import in.jewelx.jewelxbackend.entity.AccountingEntity;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.CustomerEntity;
import in.jewelx.jewelxbackend.entity.CustomerOrderEntity;
import in.jewelx.jewelxbackend.entity.SaleEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;

public class SaleMapper {

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
		sale.setOrder(new CustomerOrderEntity(dto.getOrderId()));
		sale.setBrand(new BrandEntity(dto.getBrandId()));
		return sale;
	}

}
