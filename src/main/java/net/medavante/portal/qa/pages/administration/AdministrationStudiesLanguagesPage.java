package net.medavante.portal.qa.pages.administration;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;


import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesLanguagesPage extends BasePage {

	@FindBy(xpath = "//div[@class='details-grid row']//div/label[contains(text(),'Language')]")
	private WebElement languagesLabel;

	@FindBy(xpath = "//a[@title='Edit']")
	private WebElement editIcon;

	@FindBy(xpath = "//span[@class='icon-small icon-save']")
	private WebElement saveIcon;
	
	
	public AdministrationStudiesLanguagesPage(WebDriver driver) {
		super(driver);
	}

	public void verifyLangugesPage() {
		Assert.assertTrue(isElementPresent(languagesLabel), "Languages Page is Open");
	}

	public void selectLanguage(String languageName) {
		waitForElement(editIcon);
		waitAndClick(editIcon);
		String language = "//label[@class='ng-binding' and text()='" + languageName + "']/preceding-sibling::input";
		waitAndClick(language);
		waitAndClick(saveIcon);
		waitForSpinner(3);
	}
	
	
	
	

}
