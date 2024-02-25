package in.jewelx.jewelxbackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.UnitOfMeasurementEntity;

@Repository
public interface UOMRepository extends JpaRepository<UnitOfMeasurementEntity, Long> {

	Page<UnitOfMeasurementEntity> findByBrand_BrandId(Long brand_id, Pageable page);

	List<UnitOfMeasurementEntity> findByBrand_BrandId(Long brand_id);
}
