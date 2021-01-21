package net.medavante.portal.pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationOrganizationAddressesPage extends BasePage {
	public AdministrationOrganizationAddressesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='row grid-header']/span/span/label[contains(text(),'Add Address')]")
	private WebElement addressIconLabel;

	@FindBy(xpath = "//a[contains(@class,'btn circle-button') and @title='Add Address']/span")
	private WebElement addAddress;

	@FindBy(xpath = "//div[@data-value='geoAddress.address']//textarea")
	private WebElement addressField;

	@FindBy(xpath = "//div[@data-value='geoAddress.city']//input[@type='text']")
	private WebElement cityField;

	@FindBy(xpath = "//div[@data-value='geoAddress.state']//input[@type='text']")
	private WebElement stateField;

	@FindBy(xpath = "//div[@data-value='geoAddress.zip']//input[contains(@class,'ng-pristine')]")
	private WebElement zipField;

	@FindBy(xpath = "//div[@data-values='dictionaries.countries']//*[contains(@class,'ng-isolate-scope')]")
	private WebElement countryDropDown;

	@FindBy(xpath = "//div[@data-values='geoAddress.timeZones']//*[contains(@title,'Field')]")
	private WebElement timeZoneDropDown;

	@FindBy(xpath = "//div[@class='row']/a[contains(@class,'btn btn-default')]")
	private WebElement timeZoneLookUpButton;
	
	@FindBy(xpath = "//div[contains(@class,'editing-controls')]//a[@title='Save']")
	private WebElement saveIcon;
		
	@FindBy(xpath = "//div[contains(@class,'editing-controls')]//a[@title='Cancel']")
	private WebElement cancelIcon;
	
	@FindBy(xpath = "//div[@class='row']/span[contains(@class,'small-title ng-binding ng-scope')]")
	private WebElement timezoneMessage;
	

	public void verifyAddressesPageIsOpen() {
		Assert.assertTrue(isElementPresent(addressIconLabel));
	}

	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Click on Add Address icon
	 */
	public void clickOnAddAddress() {
		waitAndClick(addAddress);
	}

	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Assert Address field is empty.
	 */
	public void assertAddressFieldsEmpty() {
		Assert.assertTrue(getAttributeValueOfElement(addressField, "title").equals("Field is required"));
	}

	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Assert city field is empty.
	 */
	public void assertCityFieldsEmpty() {
		Assert.assertTrue(getAttributeValueOfElement(cityField, "title").equals("Field is required"));
	}

	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Assert country drop down is blank.
	 */
	public void assertCountryDropDownEmpty() {
		Assert.assertTrue(getText(countryDropDown).isEmpty());
	}

	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Assert State field is empty.
	 */
	public void assertStateFieldsEmpty() {
		Assert.assertTrue(getText(stateField).isEmpty());
	}

	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Assert zip code field is empty.
	 */
	public void assertZipFieldsEmpty() {
		Assert.assertTrue(getText(zipField).isEmpty());
	}

	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Assert Time zone drop down is blank.
	 */
	public void assertTimezoneDropDownEmpty() {
		Assert.assertTrue(getText(timeZoneDropDown).isEmpty());
	}

	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Assert Time zone look up button is disabled.
	 */
	public void assertTimezoneLookUpButtonDisabled() {
		_normalWait(2000);
		Assert.assertTrue(getAttributeValueOfElement(timeZoneLookUpButton, "disabled").contains("true"));		
	}

	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Assert Time zone look up button is active.
	 */
	public void assertTimeZoneLookUpButtonBecomeActive() {
		_normalWait(2000);
		Assert.assertTrue(timeZoneLookUpButton.isEnabled());
	}
	
	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Reset city field.
	 */
	public void resetCityField() {
		waitAndClick(cityField);
		cityField.clear();
	}
	
	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * In this method, Assert All fields and drop down are empty and time zone look
	 * up disabled.
	 */
	public void assertAllFieldsEmptyAndTimeZoneLookUpButtonDisabled() {
		this.assertAddressFieldsEmpty();
		this.assertCityFieldsEmpty();
		this.assertStateFieldsEmpty();
		this.assertZipFieldsEmpty();
		this.assertCountryDropDownEmpty();
		this.assertTimezoneLookUpButtonDisabled();
		this.assertTimezoneDropDownEmpty();
	}

	/**
	 * @author Mrinal
	 * @date 16/09/2019
	 * 
	 * Function: Enter Address in address field.
	 */
	public void enterAddress(String address) {
		inputText(addressField, address);
	}

	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Enter City name in city field.
	 */
	public void enterCity(String city) {
		inputText(cityField, city);
	}

	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Click on Add Address icon
	 */
	public void enterState(String state) {
		waitAndClick(stateField);
		inputText(stateField, state);
	}

	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Enter zip code in zip code field.
	 */
	public void enterZip(String zip) {
		inputText(zipField, zip);
	}

	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: In this method select country value from country drop down
	 */
	public void selectCountry(String country) {
		waitAndClick(countryDropDown);
		_normalWait(2000);
		waitAndClick("//div[@class='dropdown-menu']//li/span[contains(text(),'"+country+"')]");
	}

	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Click on Time zone Look up button
	 */
	public void clickOnTimeZoneLookUpButton() {
		waitAndClick(timeZoneLookUpButton);
		waitForSpinner(10000);
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Click on Time zone drop down and select valid time zone.
	 */
	public void clickOnTimezoneDropDownAndSelectValue(String timezone) {
		waitAndClick(timeZoneDropDown);
		waitAndClick("//li[@class='ng-scope']/span[contains(text(),'"+timezone+"')]");
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Enter city and country value in both field.
	 */
	public void enterCityAndCountry(String city, String country) {
		this.enterCity(city);
		this.selectCountry(country);
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Assert Time zone field fill with valid value.
	 */
	public void assertTimezoneFieldFillWithValue(String timezone) {
		Assert.assertTrue(isElementPresent(By.xpath("//span[@id='selectedStudy' and text()='"+timezone+"']")));
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Assert save icon is active.
	 */
	public void assertSaveIconActive() {
		Assert.assertTrue(saveIcon.isEnabled());
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Click on save icon.
	 */
	public void clickOnSaveIcon() {
		moveToElement(saveIcon);
		waitAndClick(saveIcon);
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Click on Cancel icon
	 */
	public void clickOnCancelIcon() {
		waitAndClick(cancelIcon);
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Assert added address in the address list.
	 */
	public void assertAddedAddressInTheAddressList(String address) {
		_normalWait(2000);
		Assert.assertTrue(isElementPresent(By.xpath("//div[@class='portal-grid']//label[contains(text(),'"+address+"')]")));
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Click on newly Add Address from Address list.
	 */
	public void clickOnNewlyAddedAddress(String address) {
		_normalWait(2000);
		waitAndClick("//div[@class='portal-grid']//label[@class='ng-binding' and contains(text(),'"+address+"')]");
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Reset Address Field.
	 */
	public void resetAddressField() {
		waitAndClick(addressField);
		addressField.clear();
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Reset State Field.
	 */
	public void resetStateField() {
		waitAndClick(stateField);
		stateField.clear();
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Reset address, city and state fields.
	 */
	public void resetAddressCityAndStateFields() {
		this.resetAddressField();
		this.resetCityField();
		this.resetStateField();
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Assert Time zone message.
	 */
	public void assertTimezoneMessage(String message) {
		_normalWait(2000);
		Assert.assertTrue(getText(timezoneMessage).contains(message));
	}
	
	/**
	 * @author Mrinal
	 * @date 17/09/2019
	 * 
	 * Function: Assert save icon is disabled.
	 */
	public void assertSaveIconIsDisabled() {
		Assert.assertTrue(getAttributeValueOfElement(saveIcon, "disabled").contains("true"));
	}
}