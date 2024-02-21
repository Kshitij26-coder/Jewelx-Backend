package in.jewelx.jewelxbackend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String userName);

	Page<UserEntity> findByUserRoleAndBrandAndSubsidiary_IdxIdOrderByUserNameAsc(String roleName, BrandEntity brand,
			Long subsidiaryId, Pageable pageable);

	Optional<UserEntity> findByUserId(UUID id);

	Page<UserEntity> findByUserRoleInAndBrand_BrandIdOrderByUserNameAsc(List<String> roles, Long brandId,
			Pageable pageable);

}
