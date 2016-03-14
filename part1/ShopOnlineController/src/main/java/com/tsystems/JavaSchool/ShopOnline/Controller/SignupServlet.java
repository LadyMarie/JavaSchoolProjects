package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.mysql.jdbc.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import com.tsystems.JavaSchool.ShopOnline.Dao.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asus on 21.02.2016.
 */
public class SignupServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(SignupServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            logger.info("Signup servlet started");
            //process fields if at least one was changed
            if (userChanged(req)) {
                logger.info("User changed something, will process it");
                addCheckPerson(req);
            }
            else {
                logger.info("Emptu form, will be forwarded to itself");
                req.setAttribute("forward", "./pages/signup.jsp");
            }

            String forward = (String)req.getAttribute("forward");
            req.getRequestDispatcher(forward).forward(req, resp);
        }

    private boolean userChanged(HttpServletRequest req) {
        List<String> fieldsList = getFieldsList();
        for (String field:fieldsList) {
            if (fieldChanged(field,req))
                return true;
        }
        return false;
    }

    //N.B.! Here should be listed ALL fields in form,
    //values in list should be EXACTLY the same, as attribute "name"
    //of control in .jsp page
    private List<String> getFieldsList() {
        List<String> fieldList = new ArrayList<String>();
        fieldList.add("email");
        fieldList.add("password");
        fieldList.add("name");
        fieldList.add("surname");
        fieldList.add("birthDay");
        fieldList.add("birthMonth");
        fieldList.add("birthYear");
        //this field is always automatically changed by .jsp
        //fieldList.add("isEmployee");
        return fieldList;
    }

    private boolean fieldChanged(String field, HttpServletRequest req) {
        String fieldValue = (String)req.getParameter(field);
        if ((!StringUtils.isNullOrEmpty(fieldValue)))
            return true;
        else
            return false;
    }

    private void addCheckPerson(HttpServletRequest req) {
        //got credenitials either from form, either
        //from user session(for existing user in edit mode)
        getCredenitials(req);
        if (validate(req)) {
            logger.info("Everything is valid");
            Person person = makePerson(req);
            addOrUpdatePersonDB(person, req);
            req.getSession().setAttribute("User", person);
            //release tempUser data
            req.getSession().setAttribute("TempUser",new Person());
            req.setAttribute("forward", "/catalog");
        }
        else {
            logger.error("Some data is invalid, suggest user try one more time.");
            Person person = makePerson(req);
            //Try to save already filled fields
            req.getSession().setAttribute("TempUser",person);
            //Save to show filled fields in form
            req.setAttribute("User",person);
            req.setAttribute("forward","./pages/signup.jsp");
        }
    }

    private void getCredenitials(HttpServletRequest req) {
        String email;
        if (req.getSession().getAttribute("User") == null) {
            //if this is not-registered user
            email = (String) req.getParameter("email");
            logger.info("This is new user.");
        }
        else {
            logger.info("This is existing user.");
            //under existing user, got credenitials from session
            email = ((Person)req.getSession().getAttribute("User")).getEmail();
        }
        //Todo: make a blurred password placeholder, then we can get it from
        //session too (don't forget to decrypt it)
        String pass = (String) req.getParameter("password");
        req.setAttribute("email",email);
        req.setAttribute("pass",pass);
    }

    private boolean validate(HttpServletRequest req) {
        String email = (String) req.getAttribute("email");
        String pass = (String) req.getAttribute("pass");
        boolean matches = true;
             if (StringUtils.isNullOrEmpty(email) || !match(email,".*@.*[.].*")) {
                 req.setAttribute("NoEmail", "true");
                 matches = false;
             }
             if (StringUtils.isNullOrEmpty(pass) || !match(pass,"^[0-9A-Za-z]+$")
                     || !validateIfSession(pass, req)) {
                 req.setAttribute("NoPassword", "true");
                 matches = false;
             }
             return matches;
        }

    private boolean validateIfSession(String pass, HttpServletRequest req) {
        //some validation if user already signed up
        Person sessionPerson = (Person)req.getSession().getAttribute("User");
        if (sessionPerson != null) {
            String sessionPassword = sessionPerson.getPassword();
            if ((sessionPassword != null) && (sessionPassword.equals(DigestUtils.md5Hex(pass))))
                return true;
            else
                return false;
        }
        else
            return true;
    }

    private boolean match(String str, String patternStr) {
        if (str != null) {
            Pattern p = Pattern.compile(patternStr);
            Matcher matcher = p.matcher(str);
            return matcher.matches();
        }
        return false;
    }

    private Person makePerson(HttpServletRequest req) {
        Person person = new Person();
        if (req.getSession().getAttribute("User") != null)
        {
            person = (Person)req.getSession().getAttribute("User");
            String email = (String)req.getParameter("email");
            //save valid email only
            //don't support changing email
            if (StringUtils.isNullOrEmpty((String)req.getAttribute("NoEmail"))
                    && StringUtils.isNullOrEmpty(person.getEmail())
                    && !StringUtils.isNullOrEmpty(email))
                person.setEmail(email);
            String password = (String)req.getParameter("password");
            //don't support changing password yet
            //Save valid password only
            if (StringUtils.isNullOrEmpty((String)req.getAttribute("NoPassword"))
                    && StringUtils.isNullOrEmpty(person.getPassword())
                    && !StringUtils.isNullOrEmpty(password))
                person.setPassword(DigestUtils.md5Hex(password));
        }
        else {
            //try to restore saved data from previous non-validated form
            if (req.getSession().getAttribute("TempUser") != null)
                person = (Person)req.getSession().getAttribute("TempUser");
            String email = (String)req.getParameter("email");
            //save valid email only
            if (StringUtils.isNullOrEmpty((String)req.getAttribute("NoEmail"))
                    && !StringUtils.isNullOrEmpty(email))
                person.setEmail(email);
            String password = (String)req.getParameter("password");
            //Save valid password only
            if (StringUtils.isNullOrEmpty((String)req.getAttribute("NoPassword"))
                    && !StringUtils.isNullOrEmpty(password))
                person.setPassword(DigestUtils.md5Hex(password));
        }
        String name = (String)req.getParameter("name");
        if (!StringUtils.isNullOrEmpty(name))
            person.setName(name);
        String surname = (String)req.getParameter("surname");
        if (!StringUtils.isNullOrEmpty(surname))
            person.setSurname(surname);
        //Saving to db as a string, later services will parse it, if they'll need
        String birthDay = (String)req.getParameter("birthDay");
        if (!StringUtils.isNullOrEmpty(birthDay))
            person.setBirthDay(birthDay);
        String birthYear = (String)req.getParameter("birthYear");
        if (!StringUtils.isNullOrEmpty(birthYear))
            person.setBirthYear(birthYear);
        String month = (String)req.getParameter("month");
        person.setBirthMonth(month);
        person.setStrRole(addRole(req));
        return person;
    }

    private String addRole(HttpServletRequest req) {
        String isEmployee = (String) req.getParameter("isEmployee");
        if ((!StringUtils.isNullOrEmpty(isEmployee)) && (isEmployee.equals("on")))
            return "Employee";
        else
            return "Client";

    }

    private void addOrUpdatePersonDB(Person person, HttpServletRequest req) {
        try {
            new SignupDAO().addOrUpdateUser(person);
        }
        catch(Exception ex) {
            logger.error("Something wrong with db." + ex.getMessage() + " " + ex.getStackTrace().toString());
            req.setAttribute("forward", "./pages/error.jsp");
        }

    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);
        }
}

