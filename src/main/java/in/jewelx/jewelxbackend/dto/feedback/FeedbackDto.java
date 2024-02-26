package in.jewelx.jewelxbackend.dto.feedback;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDto {

    private String name;
    private String email;
    private String mobileNumber;
    private String description;
}