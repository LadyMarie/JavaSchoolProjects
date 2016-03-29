package com.tsystems.JavaSchool.ShopOnline.controller.validator;


import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

	@Autowired
	IPersonService personService;

	Person user;

	@Override
	public boolean supports(final Class calzz) {
		return Person.class.isAssignableFrom(calzz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {
		String email = ((Person)obj).getEmail();
		String pass = ((Person)obj).getPassword();
		if ((email != null) && (pass != null)) {
			user = personService.getPerson(email, pass);
			if (user == null)
				errors.rejectValue("password", null, "Please, enter valid password for this user");
		}
		//if password or email null, do nothing, because these fields has not null annotations,
		//and error messages will appear automatically
	}

	public Person getUser() {
		return user;
	}
}