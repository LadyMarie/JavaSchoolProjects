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
            if (req.getSession().getAttribute("User") == null) {
                email = req.getParameter("email");
                pass = req.getParameter("password");
                //Try to restore previously filled fields
                if (StringUtils.isNullOrEmpty(email) || StringUtils.isNullOrEmpty(pass))
                    email = (String)req.getAttribute("User.email");
                    pass = (String)req.getAttribute("User.pass");
            }
            else {
                //profile edit mode
                person = (Person)req.getSession().getAttribute("User");
                email = person.getEmail();
                pass = person.getPassword();
            }



            if(!StringUtils.isNullOrEmpty(email) && !StringUtils.isNullOrEmpty(pass))
                addCheckPerson(email,pass,req);
            else
                forward = "./pages/signup.jsp";


            req.getRequestDispatcher(forward).forward(req, resp);
        }

    private void addCheckPerson(String email, String pass, HttpServletRequest req) {
        //Try to save already filled fields
        req.setAttribute("User",makePerson(req));
        if (validate(email, pass, req)) {
            addOrUpdatePersonDB(req);
            req.getSession().setAttribute("User", person);
            forward = "./pages/Index.jsp";
        }
        else
            forward = "./pages/signup.jsp";
    }

    private boolean validate(String email, String pass, HttpServletRequest req) {
        boolean matches = true;
             if (!match(email,".*@.*[.].*")) {
                 req.setAttribute("NoEmail", true);
                 matches = false;
             }
             if (!match(pass,"^[0-9A-Za-z]+$")) {
                 req.setAttribute("NoPassword", true);
                 matches = false;
             }
             return matches;
        }

    private boolean match(String str, String patternStr) {
        if (str != null) {
            Pattern p = Pattern.compile(patternStr);
            Matcher matcher = p.matcher(str);
            return matcher.matches();
        }
        return false;
    }

    private void addOrUpdatePersonDB(HttpServletRequest req) {
        try {
            Person person = makePerson(req);
            new SignupDAO().addOrUpdateUser(person);

        }
        catch(Exception ex) {
            forward = "./pages/error.jsp";
        }

    }

    private Person makePerson(HttpServletRequest req) {
        //field not null means it is changed, overriding it
        String email = (String)req.getParameter("email");
        if (email != null)
           person.setEmail(email);
        String password = (String)req.getParameter("password");
        if (password != null)
            person.setPassword(password);
        String name = (String)req.getParameter("name");
        if (name != null)
            person.setName(name);
        String surname = (String)req.getParameter("surname");
        if (surname != null)
            person.setSurname(surname);
        String isEmployee = (String)req.getParameter("isEmployee");
        if ((isEmployee != null) & (isEmployee.equals("on")))
            person.setStrRole("Employee");
        else
            person.setStrRole("Client");
        return person;
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);
        }
}

