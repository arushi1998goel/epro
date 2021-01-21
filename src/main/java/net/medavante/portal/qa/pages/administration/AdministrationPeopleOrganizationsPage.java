package net.medavante.portal.qa.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationPeopleOrganizationsPage extends BasePage {
	
	@FindBy(xpath="//div[@class='row grid-header']/div[@id='dropdownContainer']//label[contains(text(),'Link Organization')]")
	private WebElement linkOrganizationIconLabelOnPeople;

	public AdministrationPeopleOrganizationsPage(WebDriver driver) {
		super(driver);
	}

	public void verifyAdministrationPeopleOrganizationsPageIsOpen()
	{
		Assert.assertTrue(isElementPresent(linkOrganizationIconLabelOnPeople));
	}
		
	
}
