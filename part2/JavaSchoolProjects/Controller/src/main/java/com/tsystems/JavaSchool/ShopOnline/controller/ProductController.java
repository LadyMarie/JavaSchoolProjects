package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import com.tsystems.JavaSchool.ShopOnline.Services.IProductService;
import com.tsystems.JavaSchool.ShopOnline.config.MvcConfiguration;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Map;

@Controller
@SessionAttributes({"User","products","Product"})
public class ProductController implements HandlerExceptionResolver {

    private Logger logger = Logger.getLogger(ProductController.class);

    @Autowired
    IProductService productService;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value="product", method= RequestMethod.GET)
    public String addLoginForm(ModelMap model, HttpServletRequest req) {
        Person user = (Person) model.get("User");
        if ((user != null) && (user.getRole()!= null) && (user.getRole().equals("Employee"))) {
            return initProduct(model, req);
        }
        else {
            //protect from anonimys user and client
            return "notEmployed";
        }
    }

    private String initProduct(ModelMap model, HttpServletRequest req) {
        //check if editProduct button pressed by employee
        String prodId = req.getParameter("id");
        if (prodId !=null) {
            Map<String,Product> products = (Map<String,Product>)model.get("products");
            model.put("Product", products.get(prodId));
        }
        else
            model.put("Product", new Product());
        return "productEmployee";
    }


    @RequestMapping(value="addProduct")
    public String addProduct(@Valid @ModelAttribute("Product") Product product,
                           BindingResult result, ModelMap model, HttpServletRequest req) throws IOException {
        logger.info("Started");
        if (result.hasErrors()) {
            logger.info("Trying to add product. " + product.toString() + ". Errors: " + result.toString());
            return "productEmployee";
        }
        else
            return loadPic(product, model);

    }

    private String loadPic(Product product, ModelMap model) {
        try {
            String redirect;
            //check if product was previously initialized, it means, that it is edit mode
            //if id not zero, it means, product is got from db
            if (product.getId() != 0)
                redirect = "manageOrders";
            else
                redirect = "Main";
            String product_id = productService.addProductGetId(product);
            tryLoadPic(product_id, product.getPic());
            //forward to next page only in case of success
            return "redirect:" + redirect;
        } catch (FileUploadException ex) {
            model.put("fileUploadError", "true");
            logger.error("Can't load file. " + ex);
            return "productEmployee";
        } catch (IOException ex) {
            model.put("fileUploadError", "true");
            logger.error("Error while loading file" + ex);
            return "productEmployee";
        }

    }

    private void tryLoadPic(String product_id, MultipartFile pic) throws FileUploadException, IOException {
        //to make pic names unique (so their files won't be overriden by each other in folder)
        //retrieve product id from db while adding it there
        //don't add any pic info to db, as all pics are at the same dir
        //and named like [product_id].jpeg, so we always know how to get
        //nesessary pic

        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(MvcConfiguration.getUploads() + product_id + ".jpg"));
        FileCopyUtils.copy(pic.getInputStream(), stream);
        stream.close();

    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
        if (e instanceof MaxUploadSizeExceededException)
        {
            ModelAndView productModelView = new ModelAndView("productEmployee");
            productModelView.getModel().put("Product",new Product());
            productModelView.getModel().put("fileToBig",true);
            return productModelView;
        }
        else {
            logger.error("Error", e);
            return new ModelAndView("error");
        }
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception e) {
        logger.error("Error", e);
        return new ModelAndView("error");
    }
}