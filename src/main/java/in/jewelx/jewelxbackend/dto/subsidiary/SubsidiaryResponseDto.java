package in.jewelx.jewelxbackend.dto.subsidiary;

import java.util.UUID;

import in.jewelx.jewelxbackend.dto.brand.BrandShortDetailsDto;
import in.jewelx.jewelxbackend.dto.user.UserShortDetailsDto;
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

	private String shopActNumber;

	private String subsidiaryName;

	private String address;

	private String city;

	private String gstin;

	private String logoUrl;

	private String formHeader;

	private String formFooter;

	private UserShortDetailsDto createdBy;

	private Long pinCode;

	private boolean isActive;
}
