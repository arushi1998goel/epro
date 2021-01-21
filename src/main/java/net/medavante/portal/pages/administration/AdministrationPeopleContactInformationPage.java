package net.medavante.portal.pages.administration;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.profiles.activation.DetectedProfileActivator;
import org.openqa.selenium.By;
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
	
	@FindBy(css="a[title='Add Address']>span")
	private WebElement addAddress;
	
	@FindBy(css="(//div[@class='col-xs-offset-1 col-xs-14'])[1]")
	private WebElement addNewAddressModal;
	
	@FindBy(xpath="(//div[@class='long-text ng-isolate-scope'])[1]//textarea")
	private WebElement addressTextArea;
	
	@FindBy(css="div[class='invalid-required']>:first-child")
	private WebElement Country;
	
	@FindBy(xpath="(//button[@class='btn dropdown-toggle btn-default'])[2]")
	private WebElement countryDropdown;
	
	@FindBy(xpath="(//div[@class='btn-group ng-isolate-scope'])[2]/button")
	private WebElement timeZone;
	
	@FindBy(xpath="(//a[@class='btn btn-default ng-binding'])[1]")
	private WebElement timeZoneLookup;
	
	@FindBy(xpath="(//span[@class='icon-small icon-save'])[1]/..")
	private WebElement saveIcon;
	
	@FindBy(xpath="(//a[@title='Cancel'])[1]")
	private WebElement cancelButton;
	
	@FindBy(xpath = "(//input[contains(@class,'ng-pristine ng-untouched ng-valid')])[1]")
	private WebElement cityTextBox;
	
	@FindBy(xpath="(//input[contains(@class,'ng-pristine ng-untouched ng-valid')])[3]")
	private WebElement stateTextBox;
	
	@FindBy(xpath="(//input[contains(@class,'ng-pristine ng-untouched')])[4]")
	private WebElement zipPostalCode;
	
	@FindBy(xpath="//span[@class='small-title ng-binding ng-scope']")
	private WebElement timeZoneMessage;
	
	@FindBy(css = "div[title='Field is required']>button")
	private WebElement TimeZoneDropdown;
	
	@FindBy(xpath="(//ul[@class='dropdown-menu' and @role='menu'])[3]//li/span")
	private WebElement timeZoneDropDownValue;
	
	@FindBy(css="a[title='Delete Address']")
	private WebElement deleteAddress;
	
	public AdministrationPeopleContactInformationPage(WebDriver driver) {
		super(driver);
	}

	public void verifyContactInformationPageIsOpen()
	{
		Assert.assertTrue(isElementPresent(addAddressIconLabelOnConInfo) && isElementPresent(addEmailIconLabelOnConInfo) && isElementPresent(addPhoneIconLabelOnConInfo) && isElementPresent(addFaxIconLabelOnConInfo));
	}
	
	/**
	 * Click on Add address button
	 * @author siddharth
	 * @date 17/09/2019
	 * @version 1.0
	 */
	public void clickOnAddAddressButton() {
		waitAndClick(addAddress);
	}
	
	/**
	 * Verify add new address modal is displayed
	 * @author siddharth
	 * @date 18/09/2019
	 * @version 1.0
	 */
	public AdministrationPeopleContactInformationPage addNewAddressModalIsDisplayed() {
		isElementDisplayed(addNewAddressModal);
		return this;
	}
	
	/**
	 * Verify empty fields on add new address page
	 */
	public AdministrationPeopleContactInformationPage verifyFieldsAreEmpty() {
		/*
		 * // if(getAttributeValueOfElement(addressTextArea, "value")== null) { //
		 * Assert.assertTrue(true); // }
		 * 
		 */
		System.out.println();
		Assert.assertTrue(StringUtils.isEmpty(getAttributeValueOfElement(addressTextArea, "value")), "address text is not empty");
		Assert.assertTrue(StringUtils.isEmpty(cityTextBox.getText()),"City text box is not empty");
		Assert.assertTrue(StringUtils.isEmpty(timeZone.getText()) ,"Timezone is not empty");
		Assert.assertTrue(StringUtils.isEmpty(stateTextBox.getText()),"State text box is not empty");
		Assert.assertTrue(StringUtils.isEmpty(zipPostalCode.getText()),"Zip postal code is not empty");
		return this;
	}
		
	
	/**
	 * The method will enter the address in the address text area
	 * @author siddharth
	 * @date 18/09/2019
	 * @version 1.0
	 * @param address
	 * @return {@link AdministrationPeopleContactInformationPage}
	 */
	public AdministrationPeopleContactInformationPage  enterAddress(String address) {
		waitForWebElementPresent(addressTextArea);
		addressTextArea.clear();
		addressTextArea.sendKeys(address);
		return this;
	}

	/**
	 * This method will verify weather the address is displayed or not
	 * @author siddharth
	 * @date 18/09/2019
	 * @version 1.0
	 * @param address
	 * @return {@link AdministrationPeopleContactInformationPage}
	 */
	public AdministrationPeopleContactInformationPage verifyAddressIsDisplayed(String address) {
		isElementPresent("//label[contains(text(),'"+address+"')]");
		return this;
	}
	
	/**
	 * enter country name to select the country from dropdown
	 * @author siddharth
	 * @date 18/09/2019
	 * @version 1.0
	 * @param countryName
	 */
	public void enterCountry(String countryName) {
		waitAndClick(countryDropdown);
		String countyLocator="(//ul[@role='menu']/li//span[contains(text(),'"+countryName+"')])[1]";
		WaitForElementPresent(countyLocator, 10);
		clickOn(countyLocator);
	}
	
	/**
	 * verify Country name is displayed
	 * @author siddharth
	 * @date 18/09/2019
	 * @version 1.0
	 * @param countryName
	 */
	public void verifyCountryNameIsDisplayed(String countryName) {
		String country="(//div[@class='btn-group ng-isolate-scope'])[1]/button/span[contains(text(),'"+countryName+"')]";
		isElementPresent(country);
	}
	
	/**
	  *<i>isEnabledCheck :- Pass 'True' if we want to check weather time zone  is enabled</i>
	 * <i>Pass 'False' if we want to check weather time zone  is disabled</i>
	 * @author siddharth
	 * @date 18/09/2019
	 * @version 1.0
	 * @param stateOfTimeZone
	 */
	
	public AdministrationPeopleContactInformationPage verifyTimeZoneIsActiveOrInactive(boolean isEnabledCheck ) {
			boolean isTimeZoneDropDownEnable= isEnabledCheck ? elementStatus(timeZone) : !elementStatus(timeZone);
			Assert.assertTrue(isTimeZoneDropDownEnable , "The ["+timeZone+"] is not correct");
			return this;
	}
	
	/**
	 * Clear address text address
	 */
	public void clearAddressField() {
	  waitForWebElementPresent(addressTextArea);
	  clearTextBox(addressTextArea);
	}
	
	/**
	 * Select Time Zone Lookup Control
	 */
	public void selectTimeZoneLookupControl() {
		waitAndClick(timeZoneLookup);
	}
	
	/**
	 * <i>isEnabledCheck :- Pass 'True' if we want to check weather time zone look Up is enabled</i>
	 * <i>Pass 'False' if we want to check weather time zone lookup is disabled</i>
	 * Verify Time Zone Lookup Control is Enabled or not
	 * 
	 */
	public void verifyTimeZoneLookUpControl(boolean isEnabledCheck) {
		String isTimeZoneLookUpEnabled=getAttributeValueOfElement(timeZoneLookup, "disabled");
		boolean isTimeZoneLookEnabled = isEnabledCheck ? StringUtils.isEmpty(isTimeZoneLookUpEnabled): Boolean.valueOf(isTimeZoneLookUpEnabled);
		Assert.assertTrue(isTimeZoneLookEnabled);
	}
	
	public void verifyTimeZoneFieldIsRequired() {
		String timeZoneRequired=timeZone.findElement(By.xpath("./..")).getAttribute("required");
		Assert.assertTrue(Boolean.valueOf(timeZoneRequired), "The time zone field is not required");
	}

	/**
	 * This method check that Time Zone dropDown contains value
	 */
	public void verifyValueIsDisplayedInTimeZone() {
		_normalWait(4000);
		waitForElementPresent(timeZone, 10);
		Assert.assertFalse(Objects.isNull(getText(timeZone.findElement(By.xpath("./span[1]")))));
	}
	
	/**
	 * <i>isEnabledCheck :- Pass 'True' if we want to check weather time zone look Up is enabled</i>
	 * <i>Pass 'False' if we want to check weather time zone lookup is disabled</i>
	 * verify save control is active or inactive
	 * @param isEnabledCheck
	 */
	public void verifySaveControlIsActiveOrInactive(boolean isEnabledCheck) {
		String saveControl = getAttributeValueOfElement(saveIcon, "disabled");
		boolean isSaveControlActive = isEnabledCheck ? StringUtils.isEmpty(saveControl) : Boolean.valueOf(saveControl);
		Assert.assertTrue(isSaveControlActive);
	}
	
	public void clickOnSaveButton() {
		waitAndClick(saveIcon);
	}
	
	/**
	 * click to cancel 
	 */
	public void clickOnCancelButton() {
		waitAndClick(cancelButton);
	}
	
	/**
	 * <i>Pass is the address to click on edit button</i>
	 * click on address to edit address 
	 * @param address
	 */
	public void editAddress(String address) {
		String editAddress= "//div[@class='col-xs-offset-1 col-xs-22 long-text']/label[contains(text(),'"+address+"')]";
		_normalWait(1000);
		waitAndClick(editAddress);
		inputText(addressTextArea, address);
	}
	
	public void timeZoneErrorMessage(String message) {
		String text=timeZoneMessage.getText();
		Assert.assertEquals(text, message);
	}
	
	public void selectTimeZoneFieldFromDropDown() {
		waitAndClick(TimeZoneDropdown);
		waitAndClick(timeZoneDropDownValue);
	}
	
	public void deleteAddress() {
		waitAndClick(deleteAddress);
	}
}
