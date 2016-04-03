package com.tsystems.JavaSchool.ShopOnline.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import com.tsystems.JavaSchool.ShopOnline.Services.Filter;
import com.tsystems.JavaSchool.ShopOnline.Services.IProductService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes({"products","productsKeySet"})
public class CatalogController implements HandlerExceptionResolver{

	private Logger logger = Logger.getLogger(CatalogController.class);

    @Autowired
    IProductService productService;


    @RequestMapping(value = "/")
    public String getCatalog(ModelMap model) throws IOException{

        logger.info("Started");
        updateCatalog(model);
        restoreCartCookie(model);
        initFilter(model);
		return "Index";
	}

    private void initFilter(ModelMap model) {
        if (model.get("Filter") == null )
            model.put("Filter", new Filter());

    }

    private void restoreCartCookie(ModelMap model) {
        logger.info("Restoring cart from cookie");
        //todo: restore cart from cookie, don;t forget attr cartsize
    }

    //redirect here from some forms, because we need method get in browser instead of post
    @RequestMapping(value = "/Main", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        updateCatalog(model);
        initFilter(model);
        return "Index";
    }

    @RequestMapping(value = "/editProducts", method = RequestMethod.GET)
    public String editProduct(ModelMap model) {
        updateCatalog(model);
        model.put("editMode", true);
        initFilter(model);
        return "Index";
    }

    private void updateCatalog(ModelMap model) {
        //always load catalog from db, bacause another user
        //could add new products to db during this session
        Map<String, Product> products = productService.getCatalog();
        if (products != null) {
            model.put("products", products);
            model.put("productsKeySet", new ArrayList<String>(products.keySet()));
        }
    }

    @RequestMapping(value="/filter")
    public String showFilter(ModelMap model, HttpServletRequest req) {
        model.put("showFilter",true);
        //recover edit mode property
        if (req.getParameter("editMode") != null)
            model.put("editMode", true);
        initFilter(model);
        return "Index";
    }

    //Init filter if someone types in browser '/addFilter', accessing form directly
    @ModelAttribute("Filter")
    public void initFilterModel(ModelMap model) {
        if (model.get("Filter") == null )
            model.put("Filter", new Filter());
    }

    @RequestMapping(value="/addFilter")
    public String login(@Valid @ModelAttribute("Filter") Filter filter, BindingResult result,
                        ModelMap model,HttpServletRequest req) {
        model.put("showFilter",true);
        if (req.getParameter("editMode") != null)
            model.put("editMode", true);
        if (result.hasErrors()) {
            logger.info("Trying to filter with params." + filter.toString() + "Errors " + result.toString());
            return "Index";
        } else {
            logger.info("Filtered successfully " + filter.toString());
            filterCatalog(filter, model);
            return "Index";
        }

    }

    private void filterCatalog(Filter filter, ModelMap model) {
        Map<String, Product> products = productService.filterCatalog(filter);
        if (products != null) {
            model.put("products", products);
            model.put("productsKeySet", new ArrayList<String>(products.keySet()));
        }
    }

    //filter forward here if user attempted to access .jsp pages,
    //containing form, directly
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage(ModelMap model) {
        return "error";
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
