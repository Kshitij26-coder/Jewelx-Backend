package in.jewelx.jewelxbackend.dto.user;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDetailsDto {
	private Long idxId;
	private UUID userId;
	private String username;
	private String email;

}
