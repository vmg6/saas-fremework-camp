package tests.ui;

import base.core.TestBaseTNG;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/**
 * Created by @v.matviichenko
 */
public class TestHomeWorkThird extends TestBaseTNG {

    @Test(groups = {"ui"})
    public void testIsNewProductAddedToCatalog() {
        app.openAdminPage();
        app.addNewProductToCatalog();

        Assert.assertTrue(app.isProductAdd(), "Can not find product");

        app.openMainPage();
        app.addNewProductToCart();
        app.addRandomProductToCart();
        app.openCart();
        app.removeAllProductsFromCart();

        Assert.assertTrue(app.isEmptyCart(), "Products are not deleted from cart");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        app.quit();
    }
}
