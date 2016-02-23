package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.mysql.jdbc.StringUtils;
import com.tsystems.JavaSchool.ShopOnline.Dao.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asus on 21.02.2016.
 */
public class SignupServlet extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            //process fields if at least one was changed
            req.setAttribute("forward","./pages/signup.jsp");
            if (userChanged(req)) {
                addCheckPerson(req);
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
            Person person = makePerson(req);
            addOrUpdatePersonDB(person, req);
            req.getSession().setAttribute("User", person);
            //release tempUser data
            req.getSession().setAttribute("TempUser",new Person());
            req.setAttribute("forward", "./pages/Index.jsp");
        }
        else {
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
        }
        else {
            //under existing user, got credenitials from session
            email = ((Person)req.getSession().getAttribute("User")).getEmail();
        }
        //Todo: make a blurred password placeholder, then we can get it from
        //session too
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
            if ((sessionPassword != null) && (sessionPassword.equals(pass)))
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
                person.setPassword(password);
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
                person.setPassword(password);
        }
        String name = (String)req.getParameter("name");
        if (!StringUtils.isNullOrEmpty(name))
            person.setName(name);
        String surname = (String)req.getParameter("surname");
        if (!StringUtils.isNullOrEmpty(surname))
            person.setSurname(surname);
        setDate(req);
        person.setStrRole(addRole(req));
        return person;
    }

    private void setDate(HttpServletRequest req) {
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
            //Finally making person, because during previous call of this method
            //some fields might not be filled
            new SignupDAO().addOrUpdateUser(person);
        }
        catch(Exception ex) {
            //Todo: write stacktrace to log
            req.setAttribute("forward", "./pages/error.jsp");
        }

    }





    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);
        }
}

