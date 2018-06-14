package br.com.qparceria.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.qparceria.domain.User;
import br.com.qparceria.dto.FeedbackEmailDTO;

public interface EmailService {

	void sendFeedbackEmail(FeedbackEmailDTO feedback, User userLogged);
	
	void sendEmail(SimpleMailMessage msg);
}
