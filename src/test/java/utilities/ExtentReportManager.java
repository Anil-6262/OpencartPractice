package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	public void onStart(ITestContext context)
	{

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());
		repName = "Test-Report "+ timeStamp+ ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName);

		sparkReporter.config().setDocumentTitle("OpenCart Automation report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includeGroups.toString());
		}



	}

	public void onTestSuccess(ITestResult result)
	{

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "Got successful executed");


	}


	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

	    test.log(Status.FAIL, result.getName()+" Test Failed");
	    test.log(Status.INFO, result.getThrowable().getMessage());
	    try
	    {
	    	String imgPath = new BaseClass().captureScreenshot(result.getName());
	    	test.addScreenCaptureFromPath(imgPath);
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}


	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" Test skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}


	public void onFinish(ITestContext context)
	{

		extent.flush();

		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);

		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(Exception e)
		{
			e.printStackTrace();
		}



		// Send report to mail

		/*
		try {
		    URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);

		    // Create the email message
		    ImageHtmlEmail email = new ImageHtmlEmail();
		    email.setDataSourceResolver(new DataSourceUrlResolver(url));
		    email.setHostName("smtp.googlemail.com");  // it should change accourding to our gmail like:- yahoo,company mail, gamil
		    email.setSmtpPort(465);
		    email.setAuthenticator(new DefaultAuthenticator("ar0505985@gmail.com", "password"));
		    email.setSSLOnConnect(true);
		    email.setFrom("ar0505985@gmail.com"); //Sender
		    email.setSubject("Test Results");
		    email.setMsg("Please find Attached Report....");
		    email.addTo("anilrana6262@gmail.com"); //Receiver
		    email.attach(url, "extent report", "please check report...");
		    email.send(); // send the email
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		}
		 */

	}







}
