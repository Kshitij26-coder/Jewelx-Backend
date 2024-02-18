package in.jewelx.jewelxbackend.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.MetalStockEntity;

@Repository
public interface MetalStockRepository extends JpaRepository<MetalStockEntity, Long> {

	
	@Query("SELECT ms.closingWeight FROM MetalStockEntity ms WHERE ms.metal.id = :metalId ORDER BY ms.stockId DESC LIMIT 1")
    BigDecimal findClosingWeightByMetalId(@Param("metalId") Long metalId);
}
