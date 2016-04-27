package com.registration.form.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.registration.form.model.Customer;
import com.registration.form.service.RegistrationService;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class CustomerFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Autowired
	RegistrationService registrationService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		String zipcodeFormat = "^[0-9]{5}(?:-[0-9]{4})?$";
		Pattern pattern = Pattern.compile(zipcodeFormat);

		Customer customer = (Customer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_name", "NotEmpty.customerForm.user_name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.customerForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "NotEmpty.customerForm.first_name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "NotEmpty.customerForm.last_name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address_1", "NotEmpty.customerForm.address_1");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty.customerForm.city");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state","NotEmpty.customerForm.state");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.customerForm.country");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip_code", "NotEmpty.customerForm.zip_code");
		
		if(!emailValidator.valid(customer.getEmail())){
			errors.rejectValue("email", "Pattern.customerForm.email");
		}
		
		if(!customer.getZip_code().equals(null) && !customer.getZip_code().equals("")){
			
			Matcher matcher = pattern.matcher(customer.getZip_code());
			if(!matcher.matches()){
				errors.rejectValue("zip_code", "Pattern.customerForm.zip_code");
			}
			
		}
		if(!customer.getCountry().equals("") && !customer.getCountry().equals(null)){
			if(!customer.getCountry().equalsIgnoreCase("USA")){
				errors.rejectValue("country","Pattern.customerForm.country");
			}
		}
		
	}

}