package selenium.Test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.TestComponents.BaseTest;
import selenium.TestComponents.Retry;
import selenium.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getdata",groups={"purchase"},retryAnalyzer = Retry.class)
    public  void SubmitOrder(HashMap<String,String> input) throws IOException {


        ProductCatalogue productCatalogue=landingpage.LoginApplication(input.get("email"),input.get("password"));
        List<WebElement> products=productCatalogue.getProduct();
        productCatalogue.addProductToCart(input.get("productname"));
        CartPage cartPage=productCatalogue.gotoCartPage();
        Boolean match=cartPage.VerifyProductDisplay(input.get("productname"));
        Assert.assertTrue(match);
        CheckOutPage checkout=cartPage.goToCheckout();
        checkout.SelectCountry("india");
        ConfiromationPage confiromationPage=checkout.SubmitOrder();
       String confirmationmessage=confiromationPage.GetConfirmationMesasge();
        Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }
    @Test(dependsOnMethods ={"SubmitOrder"},dataProvider = "getdata" )
    public void orderHistoryTest(HashMap<String,String> input){
        ProductCatalogue productCatalogue=landingpage.LoginApplication(input.get("email"),input.get("password"));
        OrderPage orderPage=productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(input.get("productname")));
    }
    public String getScreenshot(String testcaseName) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        File file=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
    }

    @DataProvider
    public Object[][] getdata() throws IOException {
//        HashMap<String,String> map=new HashMap<String,String>();
//        map.put("email","yojanayoju00@gmail.com");
//        map.put("password","Selenium@123");
//        map.put("productname","ADIDAS ORIGINAL");
//        HashMap<String,String> map1=new HashMap<String,String>();
//        map1.put("email","abcdz@gmail.com");
//        map1.put("password","Abc@1234");
//        map1.put("productname","ZARA COAT 3");
        List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\selenium\\Data\\PurchaseOrder.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

}
