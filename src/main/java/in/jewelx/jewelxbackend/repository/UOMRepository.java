package in.jewelx.jewelxbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.UnitOfMeasurementEntity;

@Repository
public interface UOMRepository extends JpaRepository<UnitOfMeasurementEntity, Long> {

}
