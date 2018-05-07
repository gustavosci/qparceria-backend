package br.com.qparceria.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.qparceria.dto.ActivityDTO;
import br.com.qparceria.repositories.ActivityRepository;
import br.com.qparceria.resources.exceptions.FieldMessage;

public class ActivitySaveValidator implements ConstraintValidator<ActivitySave, ActivityDTO> {

	@Autowired
	private ActivityRepository repoAct;
	@Autowired
	private HttpServletRequest req;
	
	@Override
	public void initialize(ActivitySave ann) {
	}
 
	@Override
	public boolean isValid(ActivityDTO objDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = 0;
		if (map.get("id") != null) {
			uriId = Integer.parseInt(map.get("id"));
		}		

		// Lista de erros
		List<FieldMessage> list = new ArrayList<>();
		
		// Inserir validações aqui, quando existir

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}