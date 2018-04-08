package br.com.qparceria.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.qparceria.domain.User;
import br.com.qparceria.dto.UserSaveDTO;
import br.com.qparceria.repositories.UserRepository;
import br.com.qparceria.resources.exceptions.FieldMessage;

public class UserSaveValidator implements ConstraintValidator<UserSave, UserSaveDTO> {

	@Autowired
	private UserRepository repoUser;

	@Override
	public void initialize(UserSave ann) {
	}
 
	@Override
	public boolean isValid(UserSaveDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		User userAux = null;
		
		// E-mail único
		userAux = repoUser.findByEmail(objDto.getEmail());
		if (userAux != null) {
			list.add(new FieldMessage("email", "E-mail já existente"));
		}
		// Username único
		userAux = repoUser.findByUsername(objDto.getUsername());
		if (userAux != null) {
			list.add(new FieldMessage("username", "Username já existente"));
		}


		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}