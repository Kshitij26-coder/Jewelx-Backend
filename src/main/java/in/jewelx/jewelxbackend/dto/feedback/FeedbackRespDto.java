package in.jewelx.jewelxbackend.dto.feedback;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRespDto {

	private Long id;
	private String name;
	private String email;
	private String mobileNumber;
	private String description;
}
