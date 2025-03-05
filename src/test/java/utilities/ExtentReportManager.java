package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseTest;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
    ExtentSparkReporter sparkReporter;
    ExtentReports extent;
    ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext context) {
        repName = "Test-report-" + new SimpleDateFormat("YYYY.MM.dd.HH.MM.SS").format(new Date()) + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        sparkReporter.config().setDocumentTitle("Open Cart Automation Testing");
        sparkReporter.config().setReportName("Open Cart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application","OpenCart");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("SubModule","Customer");
        extent.setSystemInfo("User Name",System.getProperty("user.name"));
        extent.setSystemInfo("Enivorment","QA");

        extent.setSystemInfo("Operating System",context.getCurrentXmlTest().getParameter("os"));
        extent.setSystemInfo("Browser",context.getCurrentXmlTest().getParameter("browser"));

        if(!context.getCurrentXmlTest().getIncludedGroups().isEmpty()){
            extent.setSystemInfo("Groups",context.getCurrentXmlTest().getIncludedGroups().toString());
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getInstance().getClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS,result.getName()+" got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getInstance().getClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL,result.getName() + " got failed");
        test.log(Status.INFO,result.getThrowable().getMessage());
        try {
            test.addScreenCaptureFromPath(new BaseTest().captureScreen(result.getName()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getInstance().getClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,result.getName()+" got skipped");
        test.log(Status.INFO,result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        File report = new File(System.getProperty("user.dir")+"//reports//"+repName);
        try{
            Desktop.getDesktop().browse(report.toURI());
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
