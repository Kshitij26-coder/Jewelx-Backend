package in.jewelx.jewelxbackend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.sale.SaleDto;
import in.jewelx.jewelxbackend.dto.sale.SaleResponseById;
import in.jewelx.jewelxbackend.dto.sale.SaleResponseDto;

public interface ISaleService {

	String addSale(SaleDto dto);

	Page<SaleResponseDto> getAllSalesByUser(int pageNumber, int pageSize, String role, Long brandId, Long subsidiaryId);

	SaleResponseById getSaleByUUID(UUID saleId);

}
