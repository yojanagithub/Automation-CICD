package selenium.Test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.TestComponents.BaseTest;
import selenium.pageobjects.CartPage;
import selenium.pageobjects.CheckOutPage;
import selenium.pageobjects.ConfiromationPage;
import selenium.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest {
    @Test
    public  void loginErrorValidation() throws IOException {

        String productname="ADIDAS ORIGINAL";
        ProductCatalogue productCatalogue=landingpage.LoginApplication("yojanayoju00@gmail.com","Selenium");
        Assert.assertEquals(landingpage.getErrorMessage(),"Incorrect email or password.");

    }
    @Test
    public  void ProductloginErrorValidation() throws IOException {

        String productname="ADIDAS ORIGINAL";
        ProductCatalogue productCatalogue=landingpage.LoginApplication("yojanayoju00@gmail.com","Selenium@123");
        List<WebElement> products=productCatalogue.getProduct();
        productCatalogue.addProductToCart(productname);
        CartPage cartPage=productCatalogue.gotoCartPage();
        Boolean match=cartPage.VerifyProductDisplay("ADIDAS ORIGINAL");
        System.out.println(match);
        Assert.assertTrue(match);


    }

}
