package in.jewelx.jewelxbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.MetalEntity;

@Repository
public interface MetalRepository extends JpaRepository<MetalEntity, Long> {
	Page<MetalEntity> findByBrand_BrandId(Long brandId, Pageable pageable);
}
