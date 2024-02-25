package in.jewelx.jewelxbackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import in.jewelx.jewelxbackend.entity.ItemCategoryEntity;

public interface ItemCategoryRepository extends JpaRepository<ItemCategoryEntity, Short> {
	List<ItemCategoryEntity> findByBrand_BrandId(Long brandId);

	Page<ItemCategoryEntity> findByBrand_BrandId(Long brand, Pageable page);
}
