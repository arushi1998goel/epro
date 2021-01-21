package net.medavante.portal.report;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.itextpdf.text.BadElementException;
import com.relevantcodes.extentreports.LogStatus;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.utilities.DateCalendar;
import net.medavante.portal.utilities.Utilities;

public class MobileScreenRecorder {

	public static String imgPath; 

	public static String captureScreenCasts() {
		String fileName = System.getProperty("className");
		String screenshotName = Utilities.getFileName(fileName);
		String path = BaseTest.resultPath + "/" + fileName;
		if(BaseTest.exceutionOn.equals("Mobile")|| BaseTest.exceutionOn.equals("") || BaseTest.exceutionOn.equals("MobileAndWebExecution")){	
			new File(path).mkdirs();
		}
			try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			imgPath=path +"/" +screenshotName+ ".png";
			Robot robot = null;
			robot = new Robot();
			BufferedImage img = robot.createScreenCapture(new Rectangle(screenSize));  //1366, 768
			ImageIO.write(img, "png", new File(imgPath));
			BaseTest.test.log(LogStatus.PASS,fileName + "_" + "\n Snapshot below: " + BaseTest.test.addScreenCapture(fileName + "/" +
					 screenshotName + ".png"));
			return imgPath;
			}catch(Exception ex){	
			}
		return imgPath;
	}
	
	public static String captureScreenBaseTestExtent()  {
		String fileName = System.getProperty("className");
		String screenshotName = Utilities.getFileName(fileName);
		String path = BaseTest.resultPath + "/" + fileName;
		
		if((BaseTest.exceutionOn.equals("Mobile")) || (BaseTest.exceutionOn.equals("MobileAndWebExecution"))){	
			new File(path).mkdirs();
		}
			try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			imgPath=path +"/" + DateCalendar.dateTimeFileName()+ ".png";
			Robot robot = null;
			robot = new Robot();	
			if((BaseTest.getMessage().contains("MobileView"))){
			BufferedImage img = robot.createScreenCapture(new Rectangle(370, 675)); 
			ImageIO.write(img, "png", new File(imgPath));
			}
			else if(!(BaseTest.getMessage().contains("MobileView"))) {
		     Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			 BufferedImage img  = robot.createScreenCapture(area);
			 ImageIO.write(img, "png", new File(imgPath));
			}	
			BaseTest.test.log(LogStatus.PASS,fileName + "_" + "\n Snapshot below: " + BaseTest.test.addScreenCapture(imgPath));
			Reporter.log("<a href= '" + imgPath + "'target='_blank' ><img src='" + imgPath + "'>" + screenshotName+ "</a>");
			return imgPath;
			}
			catch(Exception ex){	
			}
			return imgPath;
	
	}
	}
		