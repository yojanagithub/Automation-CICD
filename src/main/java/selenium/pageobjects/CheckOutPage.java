package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;
    @FindBy(css = ".action__submit")
    WebElement submit;
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement selectcountry;
    By results=By.cssSelector(".ta-results");

    public void SelectCountry(String countryname){
        Actions a=new Actions(driver);
        a.sendKeys(country,countryname).build().perform();
        waitforElementToApepar(results);
        selectcountry.click();
    }
    public ConfiromationPage SubmitOrder(){
        submit.click();
        return new ConfiromationPage(driver);
    }
}
