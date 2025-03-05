package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseTest {

    @Test(dataProvider="LoginData" , dataProviderClass = DataProviders.class,groups = {"dataprovider","Master"})
    public void verify_loginDDT(String email,String pwd,String exp){

        logger.info("*************starting TC003_LoginDDT***********");
        try{
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassWord(pwd);
            lp.clickLogin();

            MyAccountPage ap = new MyAccountPage(driver);
            boolean targetPage = ap.isMyAccountPageExists();

            if(exp.equalsIgnoreCase("Valid") && targetPage){
                ap.clickLogOut();
                Assert.assertTrue(true);
            }
            else if (exp.equalsIgnoreCase("Valid") && !targetPage) {
                Assert.assertTrue(false);
            }
            else if (exp.equalsIgnoreCase("Invalid") && !targetPage)
            {
                Assert.assertTrue(true);
            }
            else{
                ap.clickLogOut();
                Assert.assertTrue(false);
            }
        }
        catch (Exception e){
            System.out.println(e);
            Assert.fail();
        }
        logger.info("*************Finished TC003_LoginDDT***********");
    }
}
