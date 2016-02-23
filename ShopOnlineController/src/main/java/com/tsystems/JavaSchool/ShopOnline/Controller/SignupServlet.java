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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asus on 21.02.2016.
 */
public class SignupServlet extends HttpServlet {

        String forward = "./pages/signup.jsp";
        Person person = new Person();

        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String email;
            String pass;


                email = req.getParameter("email");
                pass = req.getParameter("password");
            if (req.getSession().getAttribute("User") != null)
                person = (Person) req.getSession().getAttribute("User");
            else {
                //Try to restore previously filled fields if parameters are empty
                if (StringUtils.isNullOrEmpty(pass) && (person.getPassword() != null))
                    pass = person.getPassword();
            }
            if (StringUtils.isNullOrEmpty(email) && (person.getEmail() != null))
                email = person.getEmail();

            if (!StringUtils.isNullOrEmpty(pass))
                addCheckPerson(email, pass, req);
            else
                forward = "./pages/signup.jsp";


            req.getRequestDispatcher(forward).forward(req, resp);
        }



    private void addCheckPerson(String email, String pass, HttpServletRequest req) {
        if (validate(email, pass, req)) {
            addOrUpdatePersonDB(makePerson(req));
            req.getSession().setAttribute("User", person);
            //empty person
            person = new Person();
            forward = "./pages/Index.jsp";
        }
        else {
            //Try to save already filled fields
            req.setAttribute("User",makePerson(req));
            forward = "./pages/signup.jsp";
        }
    }

    private boolean validate(String email, String pass, HttpServletRequest req) {
        boolean matches = true;
             if (!match(email,".*@.*[.].*")) {
                 req.setAttribute("NoEmail", "true");
                 matches = false;
             }
             if (!match(pass,"^[0-9A-Za-z]+$") || !validateIfSession(pass, req)) {
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

    private void addOrUpdatePersonDB(Person person) {
        try {
            //Finally making person, because during previous call of this method
            //some fields might not be filled
            new SignupDAO().addOrUpdateUser(person);
        }
        catch(Exception ex) {
            //Todo: write stacktrace to log
            forward = "./pages/error.jsp";
        }

    }

    private Person makePerson(HttpServletRequest req) {
        //field not null means it is changed, overriding it
        String email = (String)req.getParameter("email");
        //save valid email only
        //don't support changing email
        if (StringUtils.isNullOrEmpty((String)req.getAttribute("NoEmail"))
                && StringUtils.isNullOrEmpty(person.getEmail()))
           person.setEmail(email);
        String password = (String)req.getParameter("password");
        //don't support changing password yet
        //Save valid password only
        if (StringUtils.isNullOrEmpty((String)req.getAttribute("NoPassword"))
                && StringUtils.isNullOrEmpty(person.getPassword()))
            person.setPassword(password);
        String name = (String)req.getParameter("name");
        if (!StringUtils.isNullOrEmpty(name))
            person.setName(name);
        String surname = (String)req.getParameter("surname");
        if (!StringUtils.isNullOrEmpty(surname))
            person.setSurname(surname);
        setDate(req);
        addRole(req);
        return person;
    }

    private void setDate(HttpServletRequest req) {
    }

    private void addRole(HttpServletRequest req) {
            String isEmployee = (String) req.getParameter("isEmployee");
            if ((!StringUtils.isNullOrEmpty(isEmployee)) && (isEmployee.equals("on")))
                person.setStrRole("Employee");
            else
                person.setStrRole("Client");

    }



    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);
        }
}

