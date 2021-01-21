package net.medavante.portal.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationOrganizationPeoplePage extends BasePage {
	public AdministrationOrganizationPeoplePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='row grid-header']/div/label[contains(text(),'Link Person')]")
	private WebElement peopleIconLabel;

	public void verifyPeoplePageIsOpen() {
		Assert.assertTrue(isElementPresent(peopleIconLabel));
	}

}