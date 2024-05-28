package selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //WebElement useremail= driver.findElement(By.xpath("//*[@id=\"userEmail\"]"));
    //PageFactory
    @FindBy(id="userEmail")
    WebElement useremail;
    @FindBy(id="userPassword")
    WebElement password;
    @FindBy(id="login")
    WebElement submit;
    @FindBy(css="[class*='flyInOut']")
    WebElement errormessage;

    public ProductCatalogue LoginApplication(String email,String pass){
        useremail.sendKeys(email);
        password.sendKeys(pass);
        submit.click();
        ProductCatalogue productCatalogue=new ProductCatalogue(driver);
        return productCatalogue;
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }
    public String getErrorMessage(){
        waitforWebElementToApepar(errormessage);
        return errormessage.getText();
    }

}
