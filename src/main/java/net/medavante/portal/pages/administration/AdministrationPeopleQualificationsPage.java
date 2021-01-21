package net.medavante.portal.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationPeopleQualificationsPage extends BasePage {
	
	@FindBy(xpath="//div[@class='right-pane-wrapper right-content-scroll details-grid col-xs-24']//label[contains(text(),'Language Qualifications')]")
	private WebElement languagesQualificationLabelOnQualification;
	
	@FindBy(xpath="//div[@class='col-xs-12 ng-isolate-scope']/label[contains(text(),'Scale Qualifications')]")
	private WebElement scaleQualificationLabelOnQualification;
	
	public AdministrationPeopleQualificationsPage(WebDriver driver) {
		super(driver);
	}

	public void verifyAdministrationPeopleQualificationsPageIsOpen()
	{
		Assert.assertTrue(isElementPresent(languagesQualificationLabelOnQualification) && isElementPresent(scaleQualificationLabelOnQualification));
	}
}

