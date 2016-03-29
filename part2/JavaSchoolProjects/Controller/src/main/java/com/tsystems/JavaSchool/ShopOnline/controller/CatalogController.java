package com.tsystems.JavaSchool.ShopOnline.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import com.tsystems.JavaSchool.ShopOnline.Services.IGetCatalogService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes({"products","productsKeySet"})
public class CatalogController implements HandlerExceptionResolver{

	private Logger logger = Logger.getLogger(CatalogController.class);

    @Autowired
    IGetCatalogService getCatalogService;


    @RequestMapping(value = "/")
    public String getCatalog(ModelMap model) throws IOException{

        logger.info("Started");
        updateCatalog(model);
        restoreCartCookie(model);
		return "Index";
	}

    private void restoreCartCookie(ModelMap model) {
        logger.info("Restoring cart from cookie");
        //todo: restore cart from cookie, don;t forget attr cartsize
    }

    //redirect here from some forms, because we need method get in browser instead of post
    @RequestMapping(value = "/Main", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        updateCatalog(model);
        return "Index";
    }

    private void updateCatalog(ModelMap model) {
        //always load catalog from db, bacause another user
        //could add new products to db during this session
        Map<String, Product> products = getCatalogService.getCatalog();
        if (products != null) {
            model.put("products", products);
            model.put("productsKeySet", new ArrayList<String>(products.keySet()));
        }
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("Error", e);
        return new ModelAndView("error");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception e) {
        logger.error("Error", e);
        return new ModelAndView("error");
    }
}
