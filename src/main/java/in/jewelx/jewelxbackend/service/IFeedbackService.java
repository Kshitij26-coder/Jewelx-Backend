package in.jewelx.jewelxbackend.service;

import in.jewelx.jewelxbackend.dto.feedback.FeedbackDto;
import in.jewelx.jewelxbackend.dto.feedback.FeedbackRespDto;

public interface IFeedbackService {
	FeedbackRespDto submitFeedback(FeedbackDto feedbackDto);
}
