package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseTest;

public class TC002_LoginTest extends BaseTest {

    @Test(groups = {"Sanity","Master"})
    public void verifyLogin(){
        try{
            logger.info("*************starting TC002_LoginTest***********");
            HomePage hp = new HomePage(driver);

            hp.clickMyAccount();
            logger.info("Clicked on My account Link");
            hp.clickLogin();
            logger.info("Clicked on Login Page");

            logger.info("Providing Valid Login Crenditals");
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(prop.getProperty("email"));
            lp.setPassWord(prop.getProperty("password"));
            lp.clickLogin();

            MyAccountPage ap = new MyAccountPage(driver);

            logger.info("Validating My Account Page");
            Assert.assertTrue(ap.isMyAccountPageExists());
        }
        catch (AssertionError e){
            logger.error(e);
            logger.debug(e);
            Assert.fail();
        }
        logger.info("*************Finished TC002_LoginTest***********");

    }
}
