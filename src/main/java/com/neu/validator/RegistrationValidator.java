package com.neu.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.model.User;

public class RegistrationValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User)target;
			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName","Please enter UserName");
		
		if(user.getContactNumber().length() < 10) {
			
			errors.rejectValue("contactNumber","contactNumber.required","Mobile No must be 10 digits");
		}
		
	}

}
