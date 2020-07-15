package com.notnull.dependent.dependentnotnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ValidationService {

	@Autowired
	private Validator validator;

	public Errors validation(User user) {
		Errors errors = new BeanPropertyBindingResult(user, "User" + user.getUserId());
		validator.validate(user, errors);

		return errors;
	}

}
