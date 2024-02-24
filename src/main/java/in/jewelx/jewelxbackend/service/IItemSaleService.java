package in.jewelx.jewelxbackend.service;

import java.util.List;

import in.jewelx.jewelxbackend.dto.itemsale.ItemSaleDto;
import in.jewelx.jewelxbackend.dto.itemsale.ItemSaleResponse;

public interface IItemSaleService {

	String addItemSale(ItemSaleDto dto);

	List<ItemSaleResponse> getItemSaleBySaleId(Long saleId);

}
