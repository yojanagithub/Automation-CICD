package selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.AbstractComponent.AbstractComponent;

public class ConfiromationPage extends AbstractComponent {
    WebDriver driver;
    public ConfiromationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".hero-primary")
    WebElement confirmationmessage;
    public String GetConfirmationMesasge(){
        return confirmationmessage.getText();
    }
}
