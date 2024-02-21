package in.jewelx.jewelxbackend.dto.subsidiary;

import java.math.BigDecimal;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubsidiaryRequestDto {

	private Long brandId;

	@Pattern(regexp = "^\\d{12}$", message = "invalid shop act number format, must be 12 charcter long numberic value")
	private String shopActNumber;

	private String subsidiaryName;

	private String address;

	private String city;

	@Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[A-Z0-9]{1}$", message = "Invalid GSTIN format")
	private String gstin;

	private BigDecimal cashBalance;

	private String logoUrl;

	private String formHeader;

	private String formFooter;

	private Long userIdxId;
	
	private Long pinCode;

}
