
package net.medavante.mobile.appium.core;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jfree.util.Log;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.winium.DesktopOptions;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import net.medavante.mobile.pages.MBLogAnEventPage;
import net.medavante.mobile.pages.MBMessagesPage;
import net.medavante.mobile.pages.MBQuestionnaires;
import net.medavante.mobile.pages.MobileDashBoardPage;
import net.medavante.mobile.pages.MobileLoginPage;
import net.medavante.mobile.pages.MobileSideMenuPage;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Utilities;
import net.medavante.tablet.pages.TabletLoginPage;

public class MobileDriver {
	
	private AppiumDriver<MobileElement> appiumDriver;
	protected MobileLoginPage mobileLoginPage;
	protected TabletLoginPage tabletLoginPage;
	protected MobileDashBoardPage dashborad;
	protected MBLogAnEventPage logAnEvent;
	protected String registrationCode, registrationCodeSubject, registrationObserver;
	protected MobileSideMenuPage sideMenu;
	protected MBQuestionnaires questionnairesPage;
	protected MBMessagesPage messgaePage;
	
	private String apkPath;
	

	/**
	 * read all connect devices uuid
	 *
	 * @return : it return list of uuid of connected devices
	 */
	public static List<String> getAttachedDevicesList() {

		List<String> devicesID = new ArrayList<>();
		try {
			
			Process process = Runtime.getRuntime().exec(getAndroidPath() + "//platform-tools//adb devices");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while ((s = reader.readLine()) != null) {
				if (s.contains("device") && !s.contains("attached")) {
					String[] device = s.split("\t");
					devicesID.add(device[0]);
				}
			}

		} catch (IOException e) {
		}
		return devicesID;
	}

	/**
	 * get android home path
	 *
	 * @return : return ANDROID HOME path
	 */
	public static String getAndroidPath() {
		String androidHome = System.getProperty("ANDROID_HOME");
		if (androidHome == null) {
			androidHome = System.getenv("ANDROID_HOME");
		}
		return androidHome;
	}

	/* Run Connect */

	public void runConnectForCapturingTheScreenshot() throws AWTException, InterruptedException {
		try{
		Robot robot = new Robot();

		// Press Enter
		robot.keyPress(KeyEvent.VK_WINDOWS);

		// Release Enter
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		Thread.sleep(2000);

		// Press Key  Words CONNECT
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);		
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_O);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		
		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(4000);
		}
		catch(Exception e)
		{
			Log.info("Please Install Windows Connect");
		}
	}

	/**
	 * Android set up to invoke the Virgil pro application
	 * 
	 * @return
	 * @throws Exception
	 */
	public MobileLoginPage runConnectAndConnectToDevice() throws Exception {
		runConnectForCapturingTheScreenshot();
		androidSetUp();
		return mobileLoginPage;
	}

	/**
	 * Android set up to invoke the Virgil pro application
	 * 
	 * @return
	 * @throws Exception
	 */
	public MobileLoginPage androidSetUp() throws Exception {
		//this.clickOnConnectAppIcon();
		
		apkPath=Configuration.readApplicationFile("APKPath");
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("platformName", Configuration.readApplicationFile("AndroiPlatformName"));
//
//		capabilities.setCapability("deviceName", Configuration.readApplicationFile("AndroidDeviceName"));
//		
//		//capabilities.setCapability("automationName","UiAutomator2");              
//		capabilities.setCapability("udid", Configuration.readApplicationFile("AndoidUdid")/* getAttachedDevicesList().get(0) */);
//		capabilities.setCapability("app", Utilities.getPath() + "\\src\\test\\resources\\webdriver\\" + apkPath + "");
//		capabilities.setCapability("deviceName", Configuration.readApplicationFile("AndroidDeviceName"));       
//		capabilities.setCapability("udid", Configuration.readApplicationFile("AndoidUdid")/* getAttachedDevicesList().get(0) */);
//		capabilities.setCapability("app",
//				Utilities.getPath() + "\\src\\test\\resources\\webdriver\\" + apkPath + "");
//	
//		capabilities.setCapability("udid", Configuration.readApplicationFile("AndoidUdid"));
//		capabilities.setCapability("platformVersion", Configuration.readApplicationFile("AndroidPlatformVersion"));
//		capabilities.setCapability("autoGrantPermissions", "true");
//		capabilities.setCapability("noReset", "true");
//		capabilities.setCapability("fullReset", "false");
//		appiumDriver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
		
		  DesiredCapabilities capabilities = new DesiredCapabilities();
	  		//capabilities.setCapability("BROWSER_NAME", "Android");
	  		//capabilities.setCapability("browserName", "Android");

	  		//capabilities.setCapability("VERSION", "9.0");
	  		capabilities.setCapability("platformVersion", "9.0"); 

	  		//capabilities.setCapability("deviceName","Emulator");
	  		capabilities.setCapability("deviceName","Android Emulator");
			//capabilities.setCapability("app", Utilities.getPath() + "\\src\\test\\resources\\webdriver\\" + apkPath + "");

	  		capabilities.setCapability("platformName","Android");
	  		capabilities.setCapability("appPackage", "com.MAV.PRO.App.Android.Forms");
	  		capabilities.setCapability("appActivity","md59cbe8238a5c2793500b4a11c8bef2f85.MainActivity");
	  		appiumDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(3000);
		
		mobileLoginPage = new MobileLoginPage(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), mobileLoginPage);
		Thread.sleep(5000);
		return mobileLoginPage;                                                                                                                                                                  
	}
	

	
	public void clickOnConnectAppIcon(){
		try{
		Screen s=new Screen();
		String imagePath=Utilities.getPath()+"//src//test//resources//webdriver//Connect.PNG";
		Pattern p=new Pattern(imagePath);
		Match m=s.exists(p);
		if(m!=null){
			Thread.sleep(1000);
			s.click(p);
		}else{
			this.connectToApp();
		}
		}catch (Exception e) {
			Reporter.log("Unable to connect to the mobile ");
		}
	}
	
	public void connectToApp()throws Exception{
		Robot r=new Robot();
		String appName="Connect" ;
		StringSelection stringSelection =new StringSelection(appName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		
		r.keyPress(KeyEvent.VK_WINDOWS);
		r.keyRelease(KeyEvent.VK_WINDOWS);
		
		 Thread.sleep(1000);
		 r.keyPress(KeyEvent.VK_CONTROL);
		 r.keyPress(KeyEvent.VK_V);
		 
		 r.keyRelease(KeyEvent.VK_CONTROL);
		 r.keyRelease(KeyEvent.VK_V);
		 Thread.sleep(2000);
		
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	

}
