package selenium.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import selenium.pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingpage;
    public WebDriver InitializeDriver() throws IOException {

        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\selenium\\Resources\\GlobalData.properties");
        prop.load(fis);
        String browsername=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
        //String browsername=prop.getProperty("browser");
        if (browsername.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
//            if (browsername.contains("headless")) {
//                options.addArguments("headless");
//            }
             driver=new ChromeDriver(options);

        } else if (browsername.equalsIgnoreCase("firefox")) {
            driver=new FirefoxDriver();

        } else if (browsername.equalsIgnoreCase("edge")) {
            driver=new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filepath));
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;
    }

    public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        File file=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
    }
    @BeforeMethod(alwaysRun = true)
    public LandingPage LaunchApplication() throws IOException {
        driver=InitializeDriver();
        landingpage=new LandingPage(driver);
        landingpage.goTo();
        return landingpage;
    }
    @AfterMethod(alwaysRun = true)
    public void Teardown(){
        driver.close();
    }
}
