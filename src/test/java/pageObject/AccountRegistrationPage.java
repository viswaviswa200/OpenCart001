package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "input[id*='firstname']")
    WebElement txtFirstName;

    @FindBy(css = "input[id*='lastname']")
    WebElement txtLastName;

    @FindBy(css="input[id*='email']")
    WebElement txtEmail;

    @FindBy(css="input[id*='telephone']")
    WebElement txtTelePhone;

    @FindBy(css="input[id*='password']")
    WebElement txtPassWord;

    @FindBy(css="input[id*='confirm']")
    WebElement txtConfrimPassWord;

    @FindBy(css="input[name*='agree']")
    WebElement chkdPolicy;

    @FindBy(css="input[type*='submit']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfrimation;

    public void setFirstName(String fname){
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname){
        txtLastName.sendKeys(lname);
    }

    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }

    public void setTelePhone(String tel){
        txtTelePhone.sendKeys(tel);
    }

    public void setPassWord(String pwd){
        txtPassWord.sendKeys(pwd);
    }

    public void setConfrimPassWord(String pwd){
        txtConfrimPassWord.sendKeys(pwd);
    }

    public void setPrivacyPolicy(){
        chkdPolicy.click();
    }

    public void clickContinue(){
        btnContinue.click();
    }

    public String getConfrimationMessage(){
        try{
            return msgConfrimation.getText();
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
