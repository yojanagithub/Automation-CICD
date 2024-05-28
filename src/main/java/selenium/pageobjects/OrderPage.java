package selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponent.AbstractComponent;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productnames;
    @FindBy(css = ".totalRow button")
    WebElement checkoutele;

    public Boolean VerifyOrderDisplay(String productname){
        Boolean match=productnames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
        return match;

    }



}
