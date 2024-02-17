package in.jewelx.jewelxbackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	Optional<CustomerEntity> findByCustomerId(UUID id);

	void deleteByCustomerId(UUID id);
}
