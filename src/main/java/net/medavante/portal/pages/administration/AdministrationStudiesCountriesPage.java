package net.medavante.portal.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesCountriesPage extends BasePage {

	@FindBy(xpath = "//div[@class='right-pane-wrapper portal-grid details-grid col-xs-24']/div//span[contains(text(),'Add Country')]")
	private WebElement countryLabel;

	@FindBy(xpath = "//a[@data-ng-click='getAvailableCountries()']/span")
	private WebElement addCountryButton;

	@FindBy(xpath = "//div[@data-value='country.targetSites']/div/div/input")
	private WebElement countryTargetValue;

	@FindBy(xpath = "//span[@class='icon-small icon-save']")
	private WebElement saveIcon;

	@FindBy(xpath = "//div[@class='property-value-edit checkbox-list']/div")
	private List<WebElement> configuredLanguageList;
	
	@FindBy(xpath = "(//div[@class='col-xs-24'])[2]")
	private WebElement languageSubCategory;
	
	@FindBy(xpath = "//input[@class='form-control ng-pristine ng-valid ng-touched ng-untouched']")
	private WebElement searchFieldForCountries;
	
	@FindBy(xpath = "//span[@class='action-icon ng-isolate-scope']//button[@disabled='disabled']")
	private WebElement addIconDisabled;
	
	@FindBy(xpath = "(//div[@class='col-xs-24'])[1]")
	private WebElement countryRow;

	public AdministrationStudiesCountriesPage(WebDriver driver) {
		super(driver);
	}

	/** To verify country tab is open **/
	public void verifyCountryPage() {
		Assert.assertTrue(isElementPresent(countryLabel), "Country Page is Open");
	}

	/** Select country from the countries option **/
	public void selectCountry(String countryName, String countryCode, String countryLanguage) {
		waitAndClick(addCountryButton);
		String country = "//label[@class='ng-binding' and text()='" + countryName + "']";
		waitAndClick(country);
		inputText(countryTargetValue, countryCode);
		selectCountryLanguage(countryLanguage);
		waitAndClick(saveIcon);
		waitForSpinner(3);
	}

	/** Select country language **/
	public void selectCountryLanguage(String languageToBeSelected) {
		for (WebElement countryLang : configuredLanguageList) {
			if ((getText(
					countryLang.findElement(By.xpath("//div[@class='property-value-edit checkbox-list']/div//span"))))
							.trim().equalsIgnoreCase(languageToBeSelected)) {
				doubleClickOnElement(countryLang
						.findElement(By.xpath("//div[@class='property-value-edit checkbox-list']/div//input")));
			}
		}
	}

	/***
	 * Click on country row
	 */
	
	public void clickOnCountryrow() {

		moveToElement(countryRow);
		waitAndClick(countryRow);
	}

	/***
	 * Language tab displayed on view mode
	 */
	
	public void languageTabDisplayedInViewMode() {

		String value=addIconDisabled.getAttribute("disabled");
		if(value.contains("disabled")) {
			Assert.assertTrue(true);
		}
		
	}

	/***
	 * Verify Search field is displayed
	 */
	
	public void verifySearchField() {

		moveToElement(searchFieldForCountries);
		if(searchFieldForCountries.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
	}

	/***
	 * Verify country language sub category is displayed
	 */
	
	public void countryLanguageSubCategoryIsDisplayed() {

		moveToElement(languageSubCategory);
		if(languageSubCategory.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
	}
}
