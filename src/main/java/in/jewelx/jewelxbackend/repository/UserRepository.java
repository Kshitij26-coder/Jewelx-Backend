package in.jewelx.jewelxbackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.OtpEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String userName);

	Page<UserEntity> findByUserRole(String userRole, Pageable pageable);

	Optional<UserEntity> findByUserId(UUID id);

}
