package in.jewelx.jewelxbackend.dto.metal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetalDto {

	private String metalDescription;

	private Double metalRate;

	private String metalName;

	private Long userID;

}
