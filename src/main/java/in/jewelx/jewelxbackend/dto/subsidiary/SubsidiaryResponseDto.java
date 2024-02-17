package in.jewelx.jewelxbackend.dto.subsidiary;

import java.util.UUID;

import in.jewelx.jewelxbackend.dto.brand.BrandShortDetailsDto;
import in.jewelx.jewelxbackend.dto.user.UserShortDetailsDto;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubsidiaryResponseDto {

	private Long idxId;

	private UUID subsidiaryId;

	private BrandShortDetailsDto brand;

	@Pattern(regexp = "^\\d{12}$", message = "invalid shop act number format, must be 12 charcter long numberic value")
	private String shopActNumber;

	private String subsidiaryName;

	private String address;

	private String city;

	@Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[A-Z0-9]{1}$", message = "Invalid GSTIN format")
	private String gstin;

	private double cashBalance;

	private String logoUrl;

	private String formHeader;

	private String formFooter;

	private UserShortDetailsDto createdBy;
}
