package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.tsystems.JavaSchool.ShopOnline.Dao.AddProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.Product;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus on 28.02.2016.
 */
public class CatalogServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(CatalogServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        logger.info("Started");
        String forward = "./pages/Index.jsp";
        Map<String, Product> map = new AddProductDAO().getCatalog();
        req.getSession().setAttribute("products", map);
        //// TODO: 28.02.2016 null
        req.getSession().setAttribute("productsKeySet", new ArrayList<>(map.keySet()));
        req.getRequestDispatcher(forward).forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       doGet(req,res);
    }
}
