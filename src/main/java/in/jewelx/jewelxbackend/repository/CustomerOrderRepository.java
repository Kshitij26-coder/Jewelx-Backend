package in.jewelx.jewelxbackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jewelx.jewelxbackend.entity.CustomerOrderEntity;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Long> {

	void deleteByOrderId(UUID id);
	
	Optional<CustomerOrderEntity> findByOrderId(UUID orderId);

}
