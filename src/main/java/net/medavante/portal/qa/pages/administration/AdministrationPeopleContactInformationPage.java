package net.medavante.portal.qa.pages.administration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationPeopleContactInformationPage extends BasePage {
	
	
	@FindBy(xpath="//div[@class='row grid-header']/span/span/label[contains(text(),'Add Address')]")
	private WebElement addAddressIconLabelOnConInfo;
	
	@FindBy(xpath="//div[@class='row grid-header']/span/span/label[contains(text(),'Add Email')]")
	private WebElement addEmailIconLabelOnConInfo;
	
	@FindBy(xpath="//div[@class='row grid-header top-indent']/span/span/label[contains(text(),'Add Phone')]")
	private WebElement addPhoneIconLabelOnConInfo;
	
	@FindBy(xpath="//div[@class='row grid-header top-indent']/span/span/label[contains(text(),'Add Fax')]")
	private WebElement addFaxIconLabelOnConInfo;
	
	public AdministrationPeopleContactInformationPage(WebDriver driver) {
		super(driver);
	}

	public void verifyContactInformationPageIsOpen()
	{
		Assert.assertTrue(isElementPresent(addAddressIconLabelOnConInfo) && isElementPresent(addEmailIconLabelOnConInfo) && isElementPresent(addPhoneIconLabelOnConInfo) && isElementPresent(addFaxIconLabelOnConInfo));
	}

}
