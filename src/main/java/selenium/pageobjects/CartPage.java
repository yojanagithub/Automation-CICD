package selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponent.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//*[@class='cartSection']/h3")
    List<WebElement> productslistinCart;
    @FindBy(css = ".totalRow button")
    WebElement checkoutele;

    public Boolean VerifyProductDisplay(String productname){
        Boolean match=productslistinCart.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
        return match;

    }

    public CheckOutPage goToCheckout(){
        checkoutele.click();
        return new CheckOutPage(driver);

    }

}
