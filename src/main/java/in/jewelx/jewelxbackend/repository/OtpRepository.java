package in.jewelx.jewelxbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.jewelx.jewelxbackend.entity.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {

	OtpEntity findByEmail(String email);

	OtpEntity findByOtpCode(String otp);

	@Query("SELECT o FROM OtpEntity o WHERE o.email = ?1 ORDER BY o.generatedAt DESC LIMIT 1")
	Optional<OtpEntity> findLatestByEmail(String email);

}
