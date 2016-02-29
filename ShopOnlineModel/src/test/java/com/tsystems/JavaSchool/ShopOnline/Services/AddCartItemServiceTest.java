package com.tsystems.JavaSchool.ShopOnline.Services;

import com.tsystems.JavaSchool.ShopOnline.Dao.AddProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Dao.IAddProductDAO;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by asus on 29.02.2016.
 */
public class AddCartItemServiceTest {

    @Test
    public void testAddCartItem() throws Exception {
        IAddProductDAO addProductMock = Mockito.mock(AddProductDAO.class);

    }
}