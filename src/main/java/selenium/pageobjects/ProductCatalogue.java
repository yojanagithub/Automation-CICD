package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponent.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
    //PageFactory
    @FindBy(css = ".mb-3")
    List<WebElement> products;
    By productsby= By.cssSelector(".mb-3");
    By addToCart=By.cssSelector(".card-body button:last-of-type");
    By toastmessage=By.cssSelector(".toast-container");
    @FindBy(css = ".ng-animating")
    WebElement spinner;

    public List<WebElement>getProduct(){
        waitforElementToApepar(productsby);
        return products;
    }

    public WebElement getProductByName(String productname){
        WebElement prod = getProduct().stream()
                .filter(product -> product.findElement(By.cssSelector("b"))
                        .getText().equals(productname))
                .findFirst()
                .orElse(null);
        return prod;
    }

    public void addProductToCart(String productname){
        WebElement prod=getProductByName(productname);
        prod.findElement(addToCart).click();
        waitforElementToApepar(toastmessage);
        waitforElementToDisappear(spinner);
    }

}