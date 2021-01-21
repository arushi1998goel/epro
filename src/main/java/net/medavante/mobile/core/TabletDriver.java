package net.medavante.mobile.core;

import java.io.File;
import java.net.SocketException;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Utilities;
import net.medavante.tablet.pages.TabletLoginPage;

public class TabletDriver {
	String appPath, WiniumPath, winiumDriver;
	protected DesktopOptions capabilities;
	protected static WiniumDriver driver;
	protected TabletLoginPage tabletLoginPage = new TabletLoginPage(driver);
	
	public TabletLoginPage tabletSetUp() throws Exception, SocketException {
		
		appPath = Configuration.readApplicationFile("APPPATH");
		winiumDriver = Configuration.readApplicationFile("WINIUMDRIVER");
		WiniumPath = Utilities.getPath() + "\\src\\test\\resources\\webdriver\\" + winiumDriver +"";
		capabilities=new DesktopOptions();
		
		capabilities.setApplicationPath(Utilities.getPath() + "\\src\\test\\resources\\webdriver\\" + appPath + "");
		Thread.sleep(5000);
		File drivePath = new File(WiniumPath);
		WiniumDriverService service = new WiniumDriverService.Builder().usingDriverExecutable(drivePath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
		//windriver = new WiniumDriver(new URL("http://localhost:9999"), capabilities);
		
		service.start();
		Thread.sleep(30000);
		driver = new WiniumDriver(service, capabilities); 
		Thread.sleep(30000);
		
		PageFactory.initElements(driver, tabletLoginPage);
		Thread.sleep(6000);
		return tabletLoginPage;
	}
}
