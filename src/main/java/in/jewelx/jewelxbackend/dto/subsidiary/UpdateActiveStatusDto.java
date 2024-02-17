package in.jewelx.jewelxbackend.dto.subsidiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateActiveStatusDto {
	Long userIdxId;
	Boolean isActive;
	Long subsidiaryId;
}
