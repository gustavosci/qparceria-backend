package br.com.qparceria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.User;
import br.com.qparceria.dto.FeedbackEmailDTO;
import br.com.qparceria.security.UserSS;
import br.com.qparceria.services.exceptions.AuthorizationException;

@Service
public class FeedbackService {

	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	
	public void sendEmailFeedback(FeedbackEmailDTO feedback) {
		UserSS userSS = UserLoggedService.authenticated();
		if (userSS == null) {
			throw new AuthorizationException("Acesso negado");
		}

		emailService.sendFeedbackEmail(feedback, loadUser(userSS.getId()));
	}

	private User loadUser(Integer id) {
		return userService.find(id);
	}	
}
