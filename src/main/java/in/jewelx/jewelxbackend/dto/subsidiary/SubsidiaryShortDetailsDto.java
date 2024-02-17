package in.jewelx.jewelxbackend.dto.subsidiary;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubsidiaryShortDetailsDto {
	private long idxId;
	private UUID subsidiaryId;
	private String subsidiaryName;
}
