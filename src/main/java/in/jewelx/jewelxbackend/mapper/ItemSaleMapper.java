package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.itemsale.ItemSaleResponse;
import in.jewelx.jewelxbackend.entity.ItemSaleEntity;

public class ItemSaleMapper {

	public static ItemSaleResponse dtoToEntity(ItemSaleEntity entity) {

		ItemSaleResponse dto = new ItemSaleResponse(ArticleStockMapper.entityToShortDto(entity.getTagId()),
				entity.getIdxId(), entity.getItemId(), entity.getItemAmount(), entity.getMetalRate(),
				entity.getArtifactAmount(), entity.getMakingCharges(), entity.getPayableAmount());
		return dto;
	}
}
