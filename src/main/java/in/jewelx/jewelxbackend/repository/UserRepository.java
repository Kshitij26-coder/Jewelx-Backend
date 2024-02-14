package in.jewelx.jewelxbackend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

	UserEntity findByEmail(String userName);
	
	Page<UserEntity> findByUserRole(String userRole,Pageable pageable);
}
