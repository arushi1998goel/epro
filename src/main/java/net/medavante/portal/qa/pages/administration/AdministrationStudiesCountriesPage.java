package net.medavante.portal.qa.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesCountriesPage extends BasePage {

	@FindBy(xpath = "//div[@class='right-pane-wrapper portal-grid details-grid col-xs-24']/div//label[contains(text(),'Add Country')]")
	private WebElement countryLabel;

	@FindBy(xpath = "//a[@data-ng-click='getAvailableCountries()']/span")
	private WebElement addCountryButton;

	@FindBy(xpath = "//div[@data-value='country.targetSites']/div/div/input")
	private WebElement countryTargetValue;

	@FindBy(xpath = "//span[@class='icon-small icon-save']")
	private WebElement saveIcon;

	@FindBy(xpath = "//div[@class='property-value-edit checkbox-list']/div")
	private List<WebElement> configuredLanguageList;

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
}
