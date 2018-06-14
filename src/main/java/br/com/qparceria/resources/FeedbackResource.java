package br.com.qparceria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.qparceria.dto.FeedbackEmailDTO;
import br.com.qparceria.services.FeedbackService;

@RestController
@RequestMapping(value="/feedback")
public class FeedbackResource {
	
	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value="/sendemail", method=RequestMethod.POST)
	public ResponseEntity<Void> sendEmailFeedback(@RequestBody FeedbackEmailDTO feedback){
		feedbackService.sendEmailFeedback(feedback);
		return ResponseEntity.ok().build();
	}
}
