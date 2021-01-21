package net.medavante.portal.qa.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationPeopleGeneralPage extends BasePage {
	
	@FindBy(xpath="//*[@class='details-grid portal-grid row']/div/div[3]/*[@class='col-xs-18 value long-text']/label")
	private WebElement peopleName;
	
	@FindBy(xpath="//div[@class='details-grid portal-grid row']/div/div[@class='row'][3]//div[@class='col-xs-18 name-value']/div/div[3]//label")
	private WebElement textBoxPersonLastName;
	
	public AdministrationPeopleGeneralPage(WebDriver driver) {
		super(driver);
	}

	public void verifyAdministrationPeopleGeneralPageIsOpen(String personName) throws InterruptedException {
		Assert.assertTrue(peopleName.getText().contains(personName));
	}
}
