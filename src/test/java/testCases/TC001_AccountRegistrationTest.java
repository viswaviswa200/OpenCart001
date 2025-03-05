package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseTest;

import java.time.Duration;

public class TC001_AccountRegistrationTest extends BaseTest {


    @Test(groups = {"Regression","Master"})
    public void verify_account_registration(){
        try {
            logger.info("*************starting TC001_AccountRegistrationTest***********");

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My account Link");


            hp.clickRegister();
            logger.info("Clicked on Register Link");

            logger.info("Providing Customer Details");
            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
            regpage.setFirstName(randomString().toUpperCase());
            regpage.setLastName(randomString().toLowerCase());
            regpage.setEmail(randomString() + "@gmail.com");
            regpage.setTelePhone(randomNumeric());

            String alphnumeric = randomAlphaNumeric();

            regpage.setPassWord(alphnumeric);
            regpage.setConfrimPassWord(alphnumeric);
            regpage.setPrivacyPolicy();
            regpage.clickContinue();

            logger.info("Validating Execpected Message");
            String confmsg = regpage.getConfrimationMessage();
            Assert.assertEquals(confmsg, "Your Account Has Been Created!");
        }
        catch (AssertionError e){
            logger.error(e);
            logger.debug(e);
            Assert.fail();
        }
        logger.info("*************Finished TC001_AccountRegistrationTest***********");
    }

}
