package in.jewelx.jewelxbackend.security.model;

import java.util.UUID;

import in.jewelx.jewelxbackend.dto.brand.BrandShortDetailsDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryShortDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

	private String username;

	private String jwtToken;

	private String email;

	private String role;

	private UUID userId;

	private Long subsidiaryId;

	private Long brandId;

	private Long idxId;

	private BrandShortDetailsDto brand;

	private SubsidiaryShortDetailsDto subsidiary;

	private String mobileNumber;

}
