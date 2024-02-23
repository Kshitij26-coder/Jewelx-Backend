package in.jewelx.jewelxbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.SaleEntity;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

	Page<SaleEntity> findByBrand_BrandId(Long brandId, Pageable pageable);

	Page<SaleEntity> findBySubsidiary_IdxId(Long subsidiaryId, Pageable pageable);

	SaleEntity findBySaleId(UUID saleId);

}
