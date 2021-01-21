package net.medavante.portal.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesIdentityPage extends BasePage{

	
	@FindBy(xpath="//h2[contains(text(),'Authentication Type')]")
	private WebElement authenticationTypeHeading;
	
	@FindBy(xpath="(//span[@class='icon-small icon-edit'])[1]")
	private WebElement siteBasedPROEditIcon;
	
	
	public AdministrationStudiesIdentityPage(WebDriver driver) {
		super(driver);
	}
	
	/*
	 * @function: Verification of Identity Page
	 *
	 */
	public void verifyAdministrationStudiesIdentityPage() {
		Assert.assertTrue(isElementPresent(authenticationTypeHeading) && isElementPresent(siteBasedPROEditIcon));
		
	}

}
