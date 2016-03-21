package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Services.ISignupService;
import com.tsystems.JavaSchool.ShopOnline.controller.validator.PersonValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by asus on 16.03.2016.
 */
@Controller
@SessionAttributes({"User","userExists"})
public class SignupController {

    Logger logger = Logger.getLogger(SignupController.class);

    @Autowired
    PersonValidator validator;

    @Autowired
    ISignupService signupService;

    //not existing user wants to register
    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String addSignupForm(ModelMap model) {
           model.put("User", new Person());
        return "signup";
    }

    //existing user wants to edit profile
    @RequestMapping(value="/editProfile", method= RequestMethod.GET)
    public String addEditProfileForm(ModelMap model) {
        //Don't create new Person, because button 'editProfile' can only be seen in .jsp
        //when 'user' attribute already exits in model. It was added there when login.
        //So set 'userExists' flag;
        Person user = (Person)model.get("User");
        model.addAttribute("userExists",true);
        addBoolRole(user);
        model.put("User",user);
        return "signup";
    }

    private void addBoolRole(Person user) {
        if ((user != null) && (user.getRole() != null)) {
            if (user.getRole().equals("Employee"))
                user.setIsEmployee(true);
            else
                user.setIsEmployee(false);
        }
        else {
            logger.error("Can't got user from db correctly");
        }

    }

    //Init person if someone types in browser '/addPerson', accessing form directly
    @ModelAttribute("User")
    public void initPerson(ModelMap model) {
        if (model.get("User") == null )
            model.put("User", new Person());
    }

    @RequestMapping(value="/addPerson")
    public String signup(@Valid @ModelAttribute("User") Person user, BindingResult result, ModelMap model, HttpServletRequest req) {
        if (model.containsAttribute("userExists")) {
            validator.validate(user, result);
        }
        if (result.hasErrors())
            return "signup";
        else {
            signupService.addOrUpdateUserDB(user);
            model.put("User",user);
            return "redirect:Main";
        }
    }


}
