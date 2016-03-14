package com.tsystems.JavaSchool.ShopOnline.Controller;

import com.mysql.jdbc.StringUtils;
import com.tsystems.JavaSchool.ShopOnline.Dao.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class AddProductServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(AddProductServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        logger.info("Started");
        String forward = "./pages/addProductEmployee.jsp";
        //Check if form not empty. If it is, forward to add product form
        //to make user be able to fill it
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            logger.info("User add smth to form, it will be loaded.");
            loadData(req);
            forward = (String)req.getAttribute("forward");
        }
        req.getRequestDispatcher(forward).forward(req, res);
    }

    private void loadData(HttpServletRequest req) {
        try {
            tryLoadData(req);
            //forward to next page only in case of success
            req.setAttribute("forward","/catalog");
        } catch (FileUploadException ex) {
            req.setAttribute("fileUploadError", "true");
            req.setAttribute("forward", "./pages/addProductEmployee.jsp");
            logger.error("Can't load file. " + ex.getMessage() + " " + ex.getStackTrace().toString());
        } catch (NumberFormatException ex){
            req.setAttribute("forward","./pages/addProductEmployee.jsp");
        } catch (Exception e) {
            req.setAttribute("fileUploadError", "true");
            req.setAttribute("forward","./pages/addProductEmployee.jsp");
            logger.error(e.getMessage() + " " + e.getStackTrace().toString());
        }
    }

    private void tryLoadData(HttpServletRequest req) throws Exception {
        //initialization
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(req);

        //usefull work
        Product product = loadAllFields(items, req);
        //to make pic names unique (so their files won't be overriden by each other in folder)
        //retrieve product id from db while adding it there
        String filename = (new AddProductDAO()).addProductGetId(product);
        //don't add any pic info to db, as all pics are at the same dir
        //and named like [product_id].jpeg, so we always know how to get
        //nesessary pic
        loadPic(items, filename);
    }



    private Product loadAllFields(List<FileItem> items, HttpServletRequest req) {
        Product product = new Product();
        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();
                if (item.isFormField())
                    product = setValue(product, item.getFieldName(), item.getString(), req);
        }
        return product;
    }


    private Product setValue(Product product, String name, String value, HttpServletRequest req) {
        //N.B.! Don't forget to add case here when adding new field to form
        logger.info("Set field name: " + name + "value: " + value);
        switch (name) {
            case "name": {
                product.setName(value);
                break;
            }
            case "price": {
                product.setPrice(tryParseInt("price", value, req));
                break;
            }
            case "category": {
                product.setCategory(value);
                break;
            }
            case "params": {
                product.setParams(value);
                break;
            }
            case "weight": {
                product.setWeight(value);
                break;
            }
            case "volume": {
                product.setVolume(value);
                break;
            }
            case "amount": {
                product.setAmount(tryParseInt("amount", value, req));
                break;
            }
            default: {
                //todo:write this to log, it's strange
            }
        }
        return product;
    }

    private Integer tryParseInt(String name, String value, HttpServletRequest req) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            req.setAttribute("wrongNumber", name);
            logger.error("Not valid. Name: " + name + "value: " + value);
            //catch here to save field name in attribute,
            //throw it to stop method
            throw ex;
        }
    }

    private void loadPic(List<FileItem> items, String filename) throws Exception{
        //need to iterate twice through iter, because before process file
        //we have to get from iter all formfields and handle them.
        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (!item.isFormField()) {
                //this is a file
                String fileToWrite = this.getServletConfig().getServletContext().getInitParameter("file-upload");
                logger.info("Writing file to " + fileToWrite + filename +".jpg");
                File uploadedFile = new File(fileToWrite + filename +".jpg");
                item.write(uploadedFile);
            }
        }
    }



    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}