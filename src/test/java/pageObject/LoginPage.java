package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "input[id*='email']")
    WebElement txtEmailAddress;

    @FindBy(css="input[id*='password']")
    WebElement txtPassWord;

    @FindBy(css="input[value*='Login']")
    WebElement btnLogin;

    public void setEmail(String email){
        txtEmailAddress.sendKeys(email);
    }

    public void setPassWord(String pwd){
        txtPassWord.sendKeys(pwd);
    }

    public void clickLogin(){
        btnLogin.click();
    }
}
