package net.medavante.portal.qa.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesApplicationsPage extends BasePage{

	
	@FindBy(xpath="//button[contains(text(),'Patient App')]")
	private WebElement patientAppBTN_Applcation;
	
	@FindBy(xpath="(//span[@class='icon-small icon-edit'])[1]")
	private WebElement regEditIconBTN_Applcation;
	
	
	public AdministrationStudiesApplicationsPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * @function: Verification of Application Page
	 *
	 */
	public void verifyAdministrationStudiesApplicationPage() {
		Assert.assertTrue(isElementPresent(patientAppBTN_Applcation) && isElementPresent(regEditIconBTN_Applcation));
		
	}
}
