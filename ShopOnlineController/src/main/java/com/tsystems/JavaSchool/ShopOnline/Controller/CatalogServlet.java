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
        if (req.getSession().getAttribute("products") == null) {
            Map<String, Product> products = tryGetCatalog();
            if (products != null) {
                req.getSession().setAttribute("products", products);
                req.getSession().setAttribute("productsKeySet", new ArrayList<>(products.keySet()));
            }
        }
        restoreCartCookie(req);
        req.getRequestDispatcher(forward).forward(req, res);
    }

    private Map<String,Product> tryGetCatalog() {
        try {
            return new AddProductDAO().getCatalog();
        }
        catch (Exception ex) {
            logger.error("Can't get catalog from db. " +
                    ex.getMessage() + " " + ex.getStackTrace().toString());
            return null;
        }
    }

    private void restoreCartCookie(HttpServletRequest req) {
        if (req.getSession().getAttribute("cart") == null) {
            logger.info("Restoring cart from cookie");
            //todo: restore cart from cookie, don;t forget attr cartsize
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       doGet(req,res);
    }
}
