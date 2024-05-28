package selenium.AbstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pageobjects.CartPage;
import selenium.pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    public AbstractComponent(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "[routerlink*='cart']")
    WebElement addtoccart;
    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;

    public void waitforElementToApepar(By findby){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
    }
    public void waitforWebElementToApepar(WebElement ele){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitforElementToDisappear(WebElement ele){
        WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(5));

        wait1.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage gotoCartPage(){
        addtoccart.click();
        CartPage cartPage=new CartPage(driver);
        return cartPage;
    }
    public OrderPage goToOrdersPage(){
        orderHeader.click();
        OrderPage orderpage= new OrderPage(driver);
        return orderpage;

    }
}
