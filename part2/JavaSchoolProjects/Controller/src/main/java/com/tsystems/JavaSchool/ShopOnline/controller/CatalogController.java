package com.tsystems.JavaSchool.ShopOnline.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
//import com.tsystems.JavaSchool.ShopOnline.Dao.Product;
import com.tsystems.JavaSchool.ShopOnline.Services.IGetCatalogService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CatalogController {

	private Logger logger = Logger.getLogger(CatalogController.class);

    @Autowired
    IGetCatalogService getCatalogService;


    @RequestMapping(value = "/")
	public String getCatalog(HttpServletRequest req) throws IOException{

        logger.info("Started");

        //always load catalog from db, bacause another user
        //could add new products to db during this session
        Map<String, Product> products = getCatalogService.getCatalog();
        //todo: maybe better use modelAndView here
        if (products != null) {
            req.getSession().setAttribute("products", products);
            req.getSession().setAttribute("productsKeySet", new ArrayList<String>(products.keySet()));

        }
        restoreCartCookie(req);
		return "Index";
	}

    private void restoreCartCookie(HttpServletRequest req) {
        logger.info("Restoring cart from cookie");
        //todo: restore cart from cookie, don;t forget attr cartsize
    }
}
