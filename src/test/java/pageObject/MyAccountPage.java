package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h2[contains(text(),'My Account')]")
    WebElement msgHeading;

    @FindBy(css = "a[class*='list-group-item']:last-child")
    WebElement lnkLogout;

    public  boolean isMyAccountPageExists(){
        try {
            return msgHeading.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void clickLogOut(){
        lnkLogout.click();
    }


}
