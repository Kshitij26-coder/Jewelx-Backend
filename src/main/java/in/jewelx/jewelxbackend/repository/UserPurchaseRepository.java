package in.jewelx.jewelxbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.UserPurchaseEntity;

@Repository
public interface UserPurchaseRepository extends JpaRepository<UserPurchaseEntity, Short> {
	
	Page<UserPurchaseEntity> findByBrand_BrandId(Long brandId,  Pageable pageable);
	
	Page<UserPurchaseEntity> findBySubsidiary_IdxId(Long subsidiaryId, Pageable pageable);
}
