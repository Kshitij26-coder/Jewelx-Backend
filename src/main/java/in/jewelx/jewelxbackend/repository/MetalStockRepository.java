package in.jewelx.jewelxbackend.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.MetalStockEntity;

@Repository
public interface MetalStockRepository extends JpaRepository<MetalStockEntity, Long> {

	@Query("SELECT ms.closingWeight FROM MetalStockEntity ms WHERE ms.metal.id = :metalId ORDER BY ms.stockId DESC LIMIT 1")
	BigDecimal findClosingWeightByMetalId(@Param("metalId") Long metalId);

	Page<MetalStockEntity> findByBrand_BrandId(Long brandId, Pageable pageable);

	Page<MetalStockEntity> findBySubsidiary_IdxId(Long subsidiaryId, Pageable pageable);

	@Query("SELECT ms FROM MetalStockEntity ms WHERE (ms.metal.id, ms.updatedOn) IN "
			+ "(SELECT m.metal.id, MAX(m.updatedOn) FROM MetalStockEntity m GROUP BY m.metal.id)")
	List<MetalStockEntity> findLatestClosingValuesForAllMetals();
}
