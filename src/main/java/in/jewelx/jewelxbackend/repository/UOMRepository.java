package in.jewelx.jewelxbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jewelx.jewelxbackend.entity.UnitOfMeasurementEntity;

public interface UOMRepository extends JpaRepository<UnitOfMeasurementEntity, Long> {

}
