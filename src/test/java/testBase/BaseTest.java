package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Logger logger;
    public Properties prop;


    @BeforeClass(alwaysRun = true)
    @Parameters({"os","browser"})
    public void setup(String os,String browser) throws IOException {
        logger = LogManager.getLogger(this.getClass());

        prop = new Properties();
        prop.load(Files.newInputStream(Paths.get(System.getProperty("user.dir") + "//src//test//resources//config.properties")));

        if(prop.getProperty("execution_env").equalsIgnoreCase("remote")){

            DesiredCapabilities cap = new DesiredCapabilities();
            if(os.equalsIgnoreCase("windows")) cap.setPlatform(Platform.WIN11);
            else if (os.equalsIgnoreCase("mac")) cap.setPlatform(Platform.MAC);
            else{
                System.out.println("No Mathching OS");
                return;
            }
            switch (browser.toLowerCase()){
                case "chrome" : cap.setBrowserName("chrome"); break;
                case "edge"  :cap.setBrowserName("MicrosoftEdge"); break;
                case "firefox" : cap.setBrowserName("firefox"); break;
                default:
                    System.out.println("Invalid Browser Name"); return;
            }
            driver = new RemoteWebDriver(new URL("http://192.168.2.7:4444/wd/hub"),cap);
        }

        if(prop.getProperty("execution_env").equalsIgnoreCase("local")){
            switch (browser.toLowerCase()){
                case "chrome" : driver = new ChromeDriver();break;
                case "edge" : driver = new EdgeDriver(); break;
                case "firefox" : driver = new FirefoxDriver(); break;
                default:
                    System.out.println("Invalid Broswer");
                    return;
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("URL"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDowm(){
        driver.quit();
    }

    public String captureScreen(String tname){
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        File tar = new File(System.getProperty("user.dir")+"//screenshots//"+tname+" - " +new SimpleDateFormat("YYYY.DD.MM.HH.MM.SS").format(new Date())+".png");
        src.renameTo(tar);

        return tar.getAbsolutePath();
    }

    public String randomString(){
        return (new RandomStringUtils()).nextAlphabetic(5);
    }

    public String randomNumeric(){
        return (new RandomStringUtils()).nextNumeric(10);
    }

    public String randomAlphaNumeric(){
        return (new RandomStringUtils()).nextAlphanumeric(10);
    }
}
