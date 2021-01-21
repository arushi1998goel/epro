package net.medavante.portal.qa.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesQualificationsPage extends BasePage {
	
	
	@FindBy(xpath = "//div[@class='right-pane-wrapper right-content-scroll portal-grid details-grid col-xs-24']//div[@class='col-xs-15 form-details']/h2")
	private WebElement formQualification;

	public AdministrationStudiesQualificationsPage(WebDriver driver) {
		super(driver);
	}

	public void verifyAdministrationStudiesQualificationsPage() {
		Assert.assertEquals("Form Qualification",formQualification.getText());
	}

}

