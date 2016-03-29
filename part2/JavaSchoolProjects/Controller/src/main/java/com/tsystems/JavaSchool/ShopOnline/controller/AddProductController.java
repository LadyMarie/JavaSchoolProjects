package com.tsystems.JavaSchool.ShopOnline.controller;

import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Product;
import com.tsystems.JavaSchool.ShopOnline.Services.IAddProductService;
import com.tsystems.JavaSchool.ShopOnline.config.MvcConfiguration;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
public class AddProductController implements HandlerExceptionResolver {

    private Logger logger = Logger.getLogger(AddProductController.class);

    @Autowired
    IAddProductService addProductService;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value="product", method= RequestMethod.GET)
    public String addLoginForm(ModelMap model) {
        model.put("Product", new Product());
        return "addProductEmployee";
    }


    @RequestMapping(value="addProduct")
    public String addProduct(@Valid @ModelAttribute("Product") Product product,
                           BindingResult result, ModelMap model, HttpServletRequest req) throws Exception {
        logger.info("Started");
        if (result.hasErrors()) {
            logger.info("Trying to add product. " + product.toString() + ". Errors: " + result.toString());
            return "addProductEmployee";
        }
        else
            return loadPic(product, model);

    }

    private String loadPic(Product product, ModelMap model) {
        try {
            String product_id = addProductService.addProductGetId(product);
            tryLoadPic(product_id, product.getPic());
            //forward to next page only in case of success
            return "redirect:Main";
        } catch (FileUploadException ex) {
            model.put("fileUploadError", "true");
            logger.error("Can't load file. " + ex);
            return "addProductEmployee";
        } catch (IOException ex) {
            model.put("fileUploadError", "true");
            logger.error("Error while loading file" + ex);
            return "addProductEmployee";
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
            ModelAndView productModelView = new ModelAndView("addProductEmployee");
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