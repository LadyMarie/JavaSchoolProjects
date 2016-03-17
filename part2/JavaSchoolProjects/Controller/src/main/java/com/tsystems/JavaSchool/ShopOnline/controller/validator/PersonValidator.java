package com.tsystems.JavaSchool.ShopOnline.controller.validator;


import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Services.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

	@Autowired
	ILoginService loginService;

	Person user;

	@Override
	public boolean supports(final Class calzz) {
		return Person.class.isAssignableFrom(calzz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {
		String email = ((Person)obj).getEmail();
		String pass = ((Person)obj).getPassword();
		user = loginService.getPerson(email, pass);
		if (user == null)
		    errors.rejectValue("password", null, "Please, enter valid password for this user");
	}

	public Person getUser() {
		return user;
	}
}