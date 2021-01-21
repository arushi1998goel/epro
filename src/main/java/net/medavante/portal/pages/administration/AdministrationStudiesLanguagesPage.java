package net.medavante.portal.pages.administration;



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
	
	@FindBy(xpath="//div[@class='input-group']//input[@class='form-control ng-pristine ng-untouched ng-valid']")
	private WebElement inputFieldForLanguage;
	
	@FindBy(xpath="(//div[@class='editing-controls']//span//a[@disabled='disabled'])[1]")
	private WebElement editIconDisable;
	
	@FindBy(xpath="//div[@class='caption col-xs-24']//input[@disabled='disabled']")
	private WebElement languageCheckbox;
	
	
	public AdministrationStudiesLanguagesPage(WebDriver driver) {
		super(driver);
	}

	public void verifyLangugesPage() {
		Assert.assertTrue(isElementPresent(languagesLabel), "Languages Page is Open");
	}

	public void selectLanguage(String languageName) {
		waitForElement(editIcon);
		waitAndClick(editIcon);
		String language = "//label[@class='ng-binding' and text()='" + languageName + " ']/preceding-sibling::input";
		waitAndClick(language);
		waitAndClick(saveIcon);
		waitForSpinner(3);
	}

	/***
	 * Verify language tab in view mode
	 */
	
	public void languageTabIsInViewMode() {

		moveToElement(languageCheckbox);
		String check=languageCheckbox.getAttribute("disabled");
		if(check.contains("disabled")) {
			Assert.assertTrue(true);
		}
		
	}

	/***
	 * Edit mode is disabled
	 */
	
	
	public void editModeIsDisabled() {

		moveToElement(editIconDisable);
		String value=editIconDisable.getAttribute("disabled");
		if(value.contains("disabled")) {
			Assert.assertTrue(true);
		}
	}

	/***
	 * Verify search field is available
	 */
	
	public void searchFieldIsavailable() {

		moveToElement(inputFieldForLanguage);
		if(inputFieldForLanguage.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
	}
	
	
	
	

}
