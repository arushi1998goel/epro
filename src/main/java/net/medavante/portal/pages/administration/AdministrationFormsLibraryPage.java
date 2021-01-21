package net.medavante.portal.pages.administration;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationFormsLibraryPage extends BasePage {

	public AdministrationFormsLibraryPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='details-grid search-block']//div[@class='input-group']//input")
	private WebElement formSearchINP;

	@FindBy(xpath = "//a[@class='btn circle-button btn-white' and @title='Add']")
	private WebElement addFormBTN;

	@FindBy(xpath = "//div[@class='scroll-wrapper']//div[@data-ng-click='selectItem(item)']//span[@class='item-name ng-binding']")
	private List<WebElement> formsList;

	@FindBy(xpath = "//span[contains(@class,'k-state-default')]//input")
	private WebElement VformsInput;

	@FindBy(xpath = "//div[@class='k-animation-container']//ul[@data-role='staticlist']//li")
	private List<WebElement> VformsDropdownList;

	@FindBy(xpath = "//*[@class='dropdown-menu drop-popup']//*[@class='search-block']//input")
	private WebElement langaugeSearchINP;

	@FindBy(xpath = "//div[@class='popup-scroll']//input[@data-ng-model='model.isChecked']")
	private List<WebElement> languageList;

	@FindBy(xpath = "//a[@data-ng-click='addCultures()']")
	private WebElement addLanguageButton;

	@FindBy(xpath = "//span[@data-action='addCultureVersion(culture)']")
	private WebElement addSubForm;

	@FindBy(xpath = "//div[@class='popup-scroll']")
	private WebElement languageDropdownList;

	@FindBy(xpath = "//div[@data-text-id='Models.forms.abbreviation']//div[2]/label")
	private WebElement selectedForm;

	@FindBy(xpath = "//a[@title='Remove']")
	private WebElement removeForm;

	@FindBy(xpath = "//span[text()='Add Form']/ancestor::div[1]//a/span")
	private WebElement addForm;

	@FindBy(xpath = "//span[@data-action='save()']//span[@class='icon-small icon-save']")
	private WebElement saveForm;

	@FindBy(xpath = "//span[@data-action='saveModel()']")
	private WebElement saveVersion;

	@FindBy(xpath = "//label[text()='Abbreviation']/ancestor::div[6]//span[@class='icon-small icon-cancel']")
	private WebElement cancelIcon;

	@FindBy(xpath = "//div[@data-value='model.abbreviation']//input")
	private WebElement abbreviationTextBox;

	@FindBy(xpath = "//div[@data-value='model.description']//textarea")
	private WebElement descriptionTextInput;

	@FindBy(xpath = "//div[@data-value='model.parentScaleId']//button")
	private WebElement parentScalesDropdown;

	@FindBy(xpath = "(//div[@class='dropdown-menu'])[2]//ul//li")
	private List<WebElement> parentScalesOptions;

	@FindBy(xpath = "//div[@id='durationtimepicker']")
	private WebElement adminTimeIcon;

	@FindBy(xpath = "//span[@data-action='addVersion()']")
	private WebElement addVersion;

	@FindBy(xpath = "//div[@data-items='templateTypes']")
	private WebElement formTemplateTypes;

	@FindBy(xpath = "//span[@data-action='addVersion()']")
	private WebElement addLanguages;

	// required Fields

	@FindBy(xpath = "//input[@title='Field is required']")
	private WebElement abbreviationFieldValidates;

	@FindBy(xpath = "//textarea[@title='Field is required']")
	private WebElement descriptionFieldValidates;

	@FindBy(xpath = "//div[@data-required='isAdminTimeRequired()']")
	private WebElement administrationTimeFieldValidates;

	@FindBy(xpath = "//div[@title='Field is required']")
	private WebElement parentScalesFieldValidates;

	@FindBy(xpath = "//label[text()='Abbreviation']/ancestor::div[1]/following-sibling::div//label")
	private WebElement abbreviationField;

	// version options

	@FindBy(xpath = "//div[@data-display-value='selectedType.value']")
	private WebElement formTemplateTypeDropdown;

	@FindBy(xpath = "//ul[@class='dropdown-menu']/li")
	private List<WebElement> formTemplateTypeOptions;

	@FindBy(xpath = "//div[@data-display-value='selectedType.value']//ul//li/span")
	private List<WebElement> formTemplateType;

	@FindBy(xpath = "//span[@data-text-id='Edit']")
	private WebElement editIcon;

	@FindBy(xpath = "//input[@data-ng-change='setControlsModified()']")
	private WebElement paperForm;

	@FindBy(xpath = "//h2[text()='Languages']/..//span[contains(@class,'icon-add')]")
	private WebElement addLanguage;

	@FindBy(xpath = "//span[@class='icon-file-pdf']")
	private WebElement pdfIcon;

	@FindBy(xpath = "//label[@class='warning-message ng-binding']")
	private WebElement warningMassage;

	// =============Date Calendar locators============//

	@FindBy(xpath = "//span[contains(@class,'icon-small icon-duration')]")
	private WebElement datePickerBTN;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]")
	private WebElement timePickerPopup;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//span[@class='timepicker-hour']")
	private WebElement hourLINK;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//div[@data-action='selectHour']//tr//td")
	private List<WebElement> hourLIST;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//span[@class='timepicker-minute']")
	private WebElement minutesLINK;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//div[@data-action='selectMinute']//tr//td")
	private List<WebElement> minutesLIST;

	@FindBy(xpath = "//button[@data-action='togglePeriod']")
	private WebElement timeAMarker;

	@FindBy(xpath = "//*[@data-ng-if='isVForm']//span[contains(@class,'k-combobox')]//span[@class='k-select']")
	private WebElement formLangDRPDWN;

	// =============Form Factor locators============//
	@FindBy(xpath = "//div[@data-values='model.formFactors']//div[2]")
	private WebElement formFactorDropdown;

	@FindBy(xpath = "(//div[@data-values='model.formFactors']//div[2]//div)[2]")
	private WebElement formFactor;

	@FindBy(xpath = "(//div[@class='dropdown-menu'])[5]//ul//li")
	private List<WebElement> formFactorOptions;

	@FindBy(xpath = "//div[@data-ng-form='formEditor']")
	private WebElement divFirstForm;

	@FindBy(xpath = "//span[contains(@data-text-id,'DeleteButton')]")
	private WebElement deleteButtonSubForm;

	@FindBy(xpath = "//div[@class='admin-grid-row ng-scope selected even']//a")
	private WebElement deleteFormButton;

	@FindBy(xpath = "//label[contains(text(),'Groups')]/../..//button")
	private WebElement formGroupDropDown;

	@FindBy(xpath = "//label[contains(text(),'Risk Form')]/../following-sibling::div/input")
	private WebElement risKAssesmentCheckBox;

	@FindBy(xpath = "//input[@title='Form Time Point']")
	private WebElement formTimePoint;

	@FindBy(xpath = "//label[contains(text(),'Customized Form')]/../following-sibling::div/input")
	private WebElement customizedFormCheckBox;

	@FindBy(xpath = "//span[contains(text(),'16Org')]")
	private WebElement organizationText;

	@FindBy(xpath = "//label[text()='Comments']/../following-sibling::div/textarea")
	private WebElement commentSection;

	@FindBy(xpath = "(//label[contains(text(),'Groups')]/../..//button/following-sibling::div//li)[1]")
	private WebElement formGroupValues;

	@FindBy(xpath = "(//h4[text()='Confirm']/../..//div[text()='Yes'])[1]")
	private WebElement confirmYesButton;

	@FindBy(xpath = "//div[@class='admin-grid-row ng-scope even selected']")
	private List<WebElement> formName;

	@FindBy(xpath = "//label[@disabled='disabled' and text()='Cancel']")
	private WebElement cancelControl;

	/** Search Form from the form Library and click on the same */
	public void clickOnSearchForm(String formName) {
		_normalWait(3000);
		inputText(formSearchINP, formName);
		_normalWait(2000);
		waitSpinnerToBecomeInvisible();
		for (WebElement displayForms : formsList) {
			if (getText(displayForms).equalsIgnoreCase(formName)) {
				javascriptButtonClick(displayForms);

				break;
			}
		}
		waitForSpinnerBecomeInvisible(5);
	}

	public void verifySelectedFormDisplayed(String searchForm) {
		moveToElement(selectedForm);
		Assert.assertTrue(isElementPresent(selectedForm));

	}

	/* verify remove form option display */

	public void verifyRemoveFormDisplayed() {
		Assert.assertTrue(isElementPresent(removeForm));
	}

	/* verify remove form option display */

	public void verifyEditIconDisplayed() {
		waitForElement(editIcon);
		Assert.assertTrue(isElementPresent(editIcon));
	}

	public void clickOnEditIcon() {
		waitAndClick(editIcon);
		row.click();
		reachToEndOfThePage();
	}

	public void clickOnAddLanguage() {
		waitAndClick(addLanguage);
		// scrollDown();
		_normalWait(1000);
		scrollToDocumentHeight();
	}

	public void valiateHeaderName(String headerName) {

		WebElement header = driver
				.findElement(By.xpath(String.format("//div[contains(@class,'-header')]//h2[text()='%s']", headerName)));
		Assert.assertTrue(isElementPresent(header));
	}

	/* Click on Add form */

	public void clickOnAddForm() {
	    waitAndClick(addForm);
	    waitForElementToBecomeInvisible(By.xpath("//div[@class='ng-isolate-scope' and @data-ng-show='ctx.isBusy']/div/div[@class='spinner']"));
	    reportInfo();
	
	}

	/* Click on Add form */

	public void clickOnAddVersion() {
		waitAndClick(addVersion);
		_normalWait(1000);

	}

	public void validateFormLablesArePresent(String[] lablesArray) throws Exception {
		_normalWait(1000);
		for (int i = 0; i < lablesArray.length; i++) {
			waitForElementPresent(By.xpath("//*[contains(@class,'grid-header')]"), DEFAULT_WAIT_4_PAGE);
			validateFormLabels(lablesArray[i], driver, true);
		}
	}

	public void validateFormLabels(String label, WebDriver driver, boolean labelPesent) throws Exception {
		if (labelPesent) {
			WebElement lableName = driver.findElement(By.xpath(String.format("//label[text()='%s']", label)));
			lableName.isDisplayed();
			Reporter.log(label + " Label is present");

		} else {

			Reporter.log(label + " label is not present");
		}
	}

	/** Verify mandatory fields **/
	public void verifyRequiredFieldsAreRed() {
		Assert.assertTrue(isElementPresent(abbreviationFieldValidates));
		Assert.assertTrue(isElementPresent(descriptionFieldValidates));
		Assert.assertTrue(isElementPresent(administrationTimeFieldValidates));
		Assert.assertTrue(isElementPresent(parentScalesFieldValidates));
	}

	/* Create Form */

	public void createFormWithRequiredFields(String abbreText, String desText, String hourToBeSelect,
			String minutesToBeSelected, String scaleType, String formFactorToBeSelect) {

		//_normalWait(3000);
		//waitForElement(saveForm);
	   // waitForElement(cancelIcon);
	    waitForElementPresent(cancelIcon,DEFAULT_WAIT_4_PAGE);
         _normalWait(2000);
		inputText(abbreviationTextBox, abbreText);
		inputText(descriptionTextInput, desText);
		setDurationTime(hourToBeSelect, minutesToBeSelected);
		waitAndClick(parentScalesDropdown);
		selectDropdownOption(parentScalesOptions, scaleType);
		waitAndClick(formFactorDropdown);
		selectDropdownOption(formFactorOptions, formFactorToBeSelect);
		waitAndClick(saveForm);
		waitForAjaxRequestsToComplete();
		waitForSpinnerBecomeInvisible(10);

	}

	/**
	 * Set Time
	 * 
	 * @param hourToBeSelect
	 *            (Range Select from 01 To 24)
	 * @param minutesToBeSelected
	 *            (Range Select from 00 To 55 with the difference of 5 Minute
	 *            interval)
	 * @param hourToBeSelect
	 * @param minutesToBeSelected
	 */
	public void setDurationTime(String hourToBeSelect, String minutesToBeSelected) {

		clickOn(datePickerBTN);
		clickOn(hourLINK);
		for (WebElement hourTXT : hourLIST) {
			if (getText(hourTXT).equalsIgnoreCase(hourToBeSelect)) {
				clickOn(hourTXT);
				break;
			}
		}
		clickOn(minutesLINK);
		for (WebElement minuteTXT : minutesLIST) {
			if (getText(minuteTXT).equalsIgnoreCase(minutesToBeSelected)) {
				clickOn(minuteTXT);
				break;
			}
		}

	}

	/* Select VForm as the form Template Type and Save */

	public void selectVForm(String formOption) {
        waitAndClick(row);
		reachToEndOfThePage();
		waitAndClick(formTemplateTypeDropdown);
		scrollToDocumentHeight();
		boolean flag = false;
		for (WebElement element : formTemplateType) {
			if (getText(element).trim().equalsIgnoreCase(formOption)) {
				clickOn(element);
				flag = true;
				break;

			}
		}
		Assert.assertTrue(flag);
		reportInfo();
		waitAndClick(saveVersion);
		waitForSpinnerBecomeInvisible(5);

	}

	/** Search Form from the form Library and click on the same */
	public void selectSearchLanguageOption(String languageName) {
		langaugeSearchINP.click();
		inputText(langaugeSearchINP, languageName);
		_waitForJStoLoad();
		_normalWait(1000);
		for (WebElement displayLanguage : languageList) {
			waitAndClick(displayLanguage);
			break;

		}

		waitAndClick(addLanguageButton);
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
	}

	public void verifyLanguageListDisplayed() {
		scrollToDocumentHeight();
		Assert.assertTrue(isElementPresent(languageDropdownList), "Langauage List is displaying");
	}

	public void valiateAddedLanguage(String headerName) {

		WebElement header = driver
				.findElement(By.xpath(String.format("//div[contains(@class,'-header')]//h2[text()='%s']", headerName)));
		Assert.assertTrue(isElementPresent(header));
	}

	@FindBy(xpath = "//*[@class='col-xs-24 editor-collapse-row']")
	private WebElement row;

	public void addSubForm() {
	//	_normalWait(2000);
		waitAndClick(addSubForm);
		row.click();
		reachToEndOfThePage();

	}

	public void verifyVFormDropdown() {
		waitSpinnerToBecomeInvisible();
		waitForElementPresent(By.xpath("//span[contains(@class,'k-state-default')]//input"), DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(isElementPresent(VformsInput), "VForm Dropdwon is displaying");
		divFirstForm.click();
		reachToEndOfThePage();
	}

	/*
	 * Verify DropDown Present*
	 *
	 */
	public void verifyDropDownForForm() {
		_normalWait(1000);
		Assert.assertTrue(isElementPresent(formLangDRPDWN));
		reportInfo();
		scrollPageThroughWebElement(deleteButtonSubForm);
	}

	/* Select First Form Language */
	public void selectVFromInLanguage() {
		scrollPageThroughWebElement(formLangDRPDWN);
		_normalWait(1000);
		javascriptButtonClick(formLangDRPDWN);
		WebElement formLang = VformsDropdownList.get(0);
		_normalWait(1000);
		javascriptButtonClick(formLang);
		waitAndClick(saveVersion);
		waitForSpinnerBecomeInvisible(5);
	}

	public void clickPDFIcon() {
		Assert.assertTrue(isElementPresent(pdfIcon), "PDF icon is displaying");
		// waitAndClick(pdfIcon);
		clickOn(pdfIcon);
		_normalWait(500);
	}

	public void verifyWarningMessage(String actual) {
		String msg = warningMassage.getText();
		Assert.assertEquals(actual, msg);
		waitForElementToBecomeInvisible(By.xpath("//label[@class='warning-message ng-binding']"));
		reportInfo();
		_normalWait(1000);

	}

	public void verifySelectedFormIsDisplayed(String searchForm) {
		_normalWait(2000);
		Assert.assertEquals(selectedForm.getText(), searchForm, searchForm + " is dispalyed and selected");
	}

	public void verifyFormPageWithAddFormIconDisplay() {
		Assert.assertTrue(isElementDisplayed(addFormBTN));
	}

	/**
	 * Click on Save Form
	 */
	public void selectSave() {
		waitAndClick(saveForm);
		_normalWait(1000);
	}

	/***
	 * Verify Added Form Is Displayed In the List
	 */

	public void verifyAddedFormIsDisplayedInTheList(String FormAdded) {
		boolean flag = false;
		_normalWait(2000);
		inputText(formSearchINP, FormAdded);
		for (WebElement we : formsList) {
			if (we.getText().trim().equalsIgnoreCase(FormAdded)) {
				flag = true;
				break;

			}
			Assert.assertTrue(flag);

		}

	}

	/**
	 * Verify Selected Form Delete options is displayed
	 */

	public void verifyFormAddedDeleteButtonIsDisplayed(String formname) {
		Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='" + formname + "']/preceding::span[1]"))
				.isDisplayed());
	}

	/**
	 * Specify All other Form Attributes other than abbreviation and Form factor
	 */

	public void verifyOtherAttributes() {
		//waitAndClick(driver.findElement(By.xpath("(//div[@class='details-grid portal-grid  ng-scope']/div/div/div/div[@class='value col-xs-15']/label)[1]")));
		waitAndClick(formGroupDropDown);
		waitAndClick(formGroupValues);
		waitAndClick(risKAssesmentCheckBox);
		inputText(formTimePoint, "test");
		waitAndClick(customizedFormCheckBox);
		driver.findElement(By.xpath("//label[contains(text(),'Organization')]/../..//button")).click();
		waitAndClick(organizationText);
		inputText(commentSection, "Abcd");

	}

	/**
	 * Verify Abbreviation and Form Factors are Must be UniQue
	 */

	public void verifYAbbreviationAndFormFactorsAreUnique() {
		_normalWait(2000);
		Assert.assertTrue(abbreviationTextBox.getAttribute("title")
				.equalsIgnoreCase("Abbreviation is already used for another form"));
		Assert.assertTrue(
				formFactor.getAttribute("title").equalsIgnoreCase("Abbreviation is already used for another form"));

	}

	/**
	 * Select cancel icon
	 */

	public void selectCancelIcon() {
		waitAndClick(cancelIcon);
	}

	/**
	 * Select Yes Form confirm Popup
	 */

	public void selectYesPopup() {
		waitAndClick(confirmYesButton);
		waitForElementToBecomeInvisible(By.xpath("//div[@class='tab-content']/div/div/div[@class='spinner']"));

	}

	

	
	/***
	 * verify that save button is disabled on forms library page
	 */
	
public void verifySaveButtonIsDisabled() {
	moveToElement(saveForm);
	_normalWait(2000);
	verifyElementIsEnabledOrDisabled(true,saveForm);
	waitForSpinnerBecomeInvisible(3000);
	
}

/**
 * Verify Forms Are Not Added
 */
	public void verifyFormsAreNotAdded() {
		Assert.assertEquals(formName.size(), 1);

	}

	/***
	 * Deleting the forms
	 */

	public void deleteTheCreatedForms(String formname) {
		_normalWait(1000);
		waitAndClick(getDriver().findElement(By.xpath("//span[text()='" + formname + "']/preceding::span[1]")));

	}

	/***
	 * verify that attributes of forms library are in view mode
	 */

	public void verifyAttributesOfFormsLibraryAreInViewMode() {
		moveToElement(cancelIcon);
		verifyElementIsEnabledOrDisabled(true, cancelIcon);
	}

	

	

	/***
	 * Select an action to edit form details
	 */

	public void selectActionToEditForm() {
		waitForElement(abbreviationField);
		waitAndClick(abbreviationField);
		_normalWait(2000);
	}

	/***
	 * Verify Cancel icon is enabled
	 */

	public void verifyCancelIconIsEnabled() {
		moveToElement(cancelIcon);
		verifyElementIsEnabledOrDisabled(true, cancelIcon);
	}

	/***
	 * update form attributes
	 */

	public void updateFormAttributes(String updatedValue) {
		moveToElement(abbreviationTextBox);
		inputText(abbreviationTextBox, updatedValue);
	}

	/***
	 * Verify save button is enabled
	 */

	public void verifySaveButtonIsEnabled() {
		moveToElement(saveForm);
		verifyElementIsEnabledOrDisabled(true, saveForm);
	}

	/***
	 * Clear the required attribute on form details page
	 */

	public void clearRequiredAttribute() {
		clearTextBox(abbreviationTextBox);
	}

	/***
	 * specify the cleared value
	 */

	public void specifyClearedValueIntextBox(String clearedValue) {
		inputText(abbreviationTextBox, clearedValue);
	}

	/***
	 * Verify save changes on form details page
	 */

	public void verifySaveChanges(String updatedData) {

		String actual = abbreviationField.getText();
		_normalWait(3000);
		waitAndClick(abbreviationField);
		inputText(abbreviationTextBox, updatedData);
		waitAndClick(saveForm);
		_normalWait(3000);
		waitForElement(abbreviationField);
		String expected = abbreviationField.getText();
		if (actual.equalsIgnoreCase(expected)) {
			Assert.assertTrue(false);
		}

		else {
			Assert.assertTrue(true);
		}

	}

	/***
	 * verify required field only accept unique value
	 */

	public void verifyrequiredFieldOnlyAcceptUniqueValue() {
		String textColor = getTextColor(abbreviationTextBox);
		Assert.assertTrue(true, textColor);

	}

	/* Verify that field accept only unique value */

	public void verifyThatAbbreviationMustBeUnique(String titleToVerified) {
		selectSave();
		angularHasFinishedProcessing();
		_normalWait(4000);

		String getTitle = getAttributeValueOfElement(abbreviationTextBox, "title");
		if (StringUtils.containsIgnoreCase(getTitle, titleToVerified)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/* verify changes are not saved on form deatail page */

	public void verifyChangedAreNotSaved(String changedAbbreviation) {
		moveToElement(abbreviationField);
		if (abbreviationField.getText().equalsIgnoreCase(changedAbbreviation)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/* Click on abbreviation field */

	public void selectAbbreviationField() {
		waitAndClick(abbreviationField);
	}

	/***
	 * verify there is no option to clear standard administration time value
	 */

	public void verifyNoOptionToClearStandardAdministrationTimeValue(String hours, String minutes) {
		setDurationTime(hours, minutes);
		clickOn(datePickerBTN);
		_normalWait(3000);
		// verifyElementIsEnabled(saveForm);
	}

	/***
	 * reset the administration time value field with previously set value
	 */

	public void resetAdministrationTimeValue(String hours, String minutes) {
		setDurationTime(hours, minutes);
		clickOn(datePickerBTN);
	}
	
	
}
