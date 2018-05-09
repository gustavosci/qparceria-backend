package br.com.qparceria.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.qparceria.dto.ActivityDTO;
import br.com.qparceria.resources.exceptions.FieldMessage;

public class ActivitySaveValidator implements ConstraintValidator<ActivitySave, ActivityDTO> {

	@Override
	public void initialize(ActivitySave ann) {
	}
 
	@Override
	public boolean isValid(ActivityDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getCityStartId() <= 0) {
			list.add(new FieldMessage("Cidade inicial", "Cidade inicial não informada"));
		}
		
		if(objDto.getCityEndId() <= 0) {
			list.add(new FieldMessage("Cidade final", "Cidade final não informada"));
		}

		if(objDto.getSportId() <= 0) {
			list.add(new FieldMessage("Esporte", "Esporte não informado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}