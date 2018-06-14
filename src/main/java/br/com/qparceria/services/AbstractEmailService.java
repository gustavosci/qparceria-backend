package br.com.qparceria.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.qparceria.domain.User;
import br.com.qparceria.dto.FeedbackEmailDTO;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	@Value("${default.recipient}")
	private String recipient;
	
	@Override
	public void sendFeedbackEmail(FeedbackEmailDTO feedback, User userLogged) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromFeedback(feedback, userLogged);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromFeedback(FeedbackEmailDTO feedback, User userLogged) {
		SimpleMailMessage sm = new SimpleMailMessage();
		
		sm.setTo(recipient);
		sm.setFrom(sender);
		sm.setCc(userLogged.getEmail());
		sm.setSubject("Feedback QParceria - Usuário " + userLogged.getId() + " - " + userLogged.getName());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(feedback.getMessage());
		
		return sm;
	}
}
