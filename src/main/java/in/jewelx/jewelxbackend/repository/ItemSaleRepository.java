package in.jewelx.jewelxbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jewelx.jewelxbackend.entity.ItemSaleEntity;

public interface ItemSaleRepository  extends JpaRepository<ItemSaleEntity, Long>{
	
	List<ItemSaleEntity> findBySale_IdxId(Long saleId);
}
