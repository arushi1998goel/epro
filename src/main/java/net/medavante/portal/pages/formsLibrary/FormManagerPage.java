package net.medavante.portal.pages.formsLibrary;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import net.medavante.portal.selenium.core.BasePage;

public class FormManagerPage extends BasePage{

	public FormManagerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public void verifyFormManagerPage(String txt) {
		String txt1 = getDriver().getTitle();
		boolean flag = false;
		if(txt1.equalsIgnoreCase(txt)) {
			flag=true;
		}
		Assert.assertTrue(flag);
	}

}
