package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.controller.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by asus on 16.03.2016.
 */
@Controller
public class SignupController {

    @Autowired
    PersonValidator validator;

    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String addSignupForm(ModelMap model, HttpServletRequest req) {
        if (req.getSession().getAttribute("User") == null)
            model.addAttribute("User", new Person());
        else {
            Person user = (Person) req.getSession().getAttribute("User");
            user.setIsEmployee(isEmployee(user));
            model.addAttribute("User", user);
        }
        return "signup";
    }

    public boolean isEmployee(Person user) {
        if (user.getRole().getName().equals("Employee"))
            return true;
        else
            return false;
    }

    @RequestMapping(value="/addPerson")
    public String signup(@Valid @ModelAttribute("User") Person user, BindingResult result, HttpServletRequest req) {
        if (req.getSession().getAttribute("User") != null)
            validator.validate(user, result);
        if (result.hasErrors())
            return "signup";
        else {
            return "Index";
        }
    }


}
