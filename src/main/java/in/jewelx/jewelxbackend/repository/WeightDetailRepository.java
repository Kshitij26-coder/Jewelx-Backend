package in.jewelx.jewelxbackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jewelx.jewelxbackend.entity.WeightDetailEntity;

public interface WeightDetailRepository extends JpaRepository<WeightDetailEntity, Long> {

	void deleteByWeightDetailId(UUID id);

	Optional<WeightDetailEntity> findByWeightDetailId(UUID id);

}
