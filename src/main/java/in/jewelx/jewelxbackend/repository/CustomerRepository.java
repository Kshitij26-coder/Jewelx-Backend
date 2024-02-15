package in.jewelx.jewelxbackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jewelx.jewelxbackend.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	Optional<CustomerEntity> findByCustomerId(UUID id);

	void deleteByCustomerId(UUID id);
}
