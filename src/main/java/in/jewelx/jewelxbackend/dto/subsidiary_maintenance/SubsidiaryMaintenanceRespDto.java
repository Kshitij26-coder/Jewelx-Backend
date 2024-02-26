package in.jewelx.jewelxbackend.dto.subsidiary_maintenance;

import java.util.UUID;

import in.jewelx.jewelxbackend.dto.accounting.AccountingShortDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryShortDetailsDto;
import in.jewelx.jewelxbackend.dto.user.UserShortDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubsidiaryMaintenanceRespDto {
	
	private Long idxId;

	private UUID maintenanceId;

	private String maintenanceDescription;

	private AccountingShortDto accounting;

	private UserShortDetailsDto createdBy;

	private Long brand;

	private SubsidiaryShortDetailsDto subsidiary;
}
