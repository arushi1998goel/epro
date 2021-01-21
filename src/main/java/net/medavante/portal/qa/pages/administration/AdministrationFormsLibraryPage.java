package net.medavante.portal.qa.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationFormsLibraryPage extends BasePage {

	public AdministrationFormsLibraryPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='details-grid search-block']//div[@class='input-group']//input")
	private WebElement formSearchINP;

	@FindBy(xpath = "//div[@class='scroll-wrapper']//div[@data-ng-click='selectItem(item)']//span[@class='item-name ng-binding']")
	private List<WebElement> formsList;

	@FindBy(xpath = "//span[contains(@class,'k-state-default')]//input")
	private WebElement VformsInput;

	@FindBy(xpath = "//div[@class='k-animation-container']//ul[@data-role='staticlist']//li")
	private List <WebElement> VformsDropdownList;

	@FindBy(xpath = "//div[contains(@class,'drop-popup')]//div[@class='input-group']//input")
	private WebElement langaugeSearchINP;

	@FindBy(xpath = "//div[@class='popup-scroll']//input[@data-ng-model='model.isChecked']")
	private List<WebElement> languageList;

	@FindBy(xpath = "//a[@data-ng-click='addCultures()']")
	private WebElement addLanguageButton;

	@FindBy(xpath = "//span[@data-action='addCultureVersion(culture)']")
	private WebElement addSubForm;

	@FindBy(xpath = "//div[@class='popup-scroll']")
	private WebElement languageDropdownList;

	@FindBy(xpath = "//div[@data-text-id='Models.forms.abbreviation']//div[2]//label")
	private WebElement selectedForm;

	@FindBy(xpath = "//a[@title='Remove']")
	private WebElement removeForm;

	@FindBy(xpath = "//span[@data-action='addItem()']")
	private WebElement addForm;

	@FindBy(xpath = "//span[@data-action='save()']")
	private WebElement saveForm;

	@FindBy(xpath = "//span[@data-action='saveModel()']")
	private WebElement saveVersion;

	@FindBy(xpath = "//span[@class='icon-small icon-cancel']")
	private WebElement cancelIcon;

	@FindBy(xpath = "//div[@data-value='model.abbreviation']//input")
	private WebElement abbreviationTextBox;

	@FindBy(xpath = "//div[@data-value='model.description']//textarea")
	private WebElement descriptionTextInput;

	@FindBy(xpath = "//div[@data-value='model.parentScaleId']//div[2]")
	private WebElement parentScalesDropdown;

	@FindBy(xpath = "//ul[@class='dropdown-menu']/li")
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

	// version options

	@FindBy(xpath = "//div[@data-display-value='selectedType.value']")
	private WebElement formTemplateTypeDropdown;

	@FindBy(xpath = "//ul[@class='dropdown-menu']/li")
	private List<WebElement> formTemplateTypeOptions;

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
	
	@FindBy(xpath="//*[@data-ng-if='isVForm']//span[contains(@class,'k-combobox')]//span[@class='k-select']")
	private WebElement formLangDRPDWN;
	
	
	// =============Form Factor locators============//
	@FindBy(xpath = "//div[@data-values='model.formFactors']//div[2]")
	private WebElement formFactorDropdown;
	
	@FindBy(xpath = "//div[@data-values='model.formFactors']//ul[@class='dropdown-menu']/li")
	private List<WebElement> formFactorOptions;
	
	
	
	
	/** Search Form from the form Library and click on the same */
	public void clickOnSearchForm(String formName) {
		inputText(formSearchINP, formName);
		_waitForJStoLoad();
		waitSpinnerToBecomeInvisible();
		for (WebElement displayForms : formsList) {
			if(getText(displayForms).equalsIgnoreCase(formName)){
			clickOn(displayForms);
			break;
			}
		}
		waitSpinnerToBecomeInvisible();
	}

	public void verifySelectedFormDisplayed(String searchForm) {
		Assert.assertEquals(selectedForm.getText(), searchForm, searchForm + " is dispalyed and selected");
	}

	/* verify remove form option display */

	public void verifyRemoveFormDisplayed() {
		Assert.assertTrue(isElementPresent(removeForm));
	}

	/* verify remove form option display */

	public void verifyEditIconDisplayed() {
		_normalWait(4000);
		Assert.assertTrue(isElementPresent(editIcon));
	}

	public void clickOnEditIcon() {
		waitAndClick(editIcon);
	}

	public void clickOnAddLanguage() {
		waitAndClick(addLanguage);
		// scrollDown();
		waitForSpinner(3);
	}

	public void valiateHeaderName(String headerName) {

		WebElement header = driver
				.findElement(By.xpath(String.format("//div[contains(@class,'-header')]//h2[text()='%s']", headerName)));
		Assert.assertTrue(isElementPresent(header));
	}

	/* Click on Add form */

	public void clickOnAddForm() {
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		waitAndClick(addForm);

	}

	/* Click on Add form */

	public void clickOnAddVersion() {
		waitAndClick(addVersion);
		_normalWait(3000);

	}

	public void validateFormLablesArePresent(String[] lablesArray) throws Exception {
		_normalWait(3000);
		for (int i = 0; i < lablesArray.length; i++) {
			waitForElementPresent(By.xpath("//*[contains(@class,'grid-header')]"),DEFAULT_WAIT_4_PAGE );
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

	public void createFormWithRequiredFields(String abbreText, String desText, String scaleType, String hourToBeSelect,
			String minutesToBeSelected,String formFactorToBeSelect) {

		waitForElement(saveForm);
		inputText(abbreviationTextBox, abbreText);
		inputText(descriptionTextInput, desText);
		setDurationTime(hourToBeSelect, minutesToBeSelected);
		waitAndClick(parentScalesDropdown);
		selectDropdownOption(parentScalesOptions, scaleType);
		waitAndClick(formFactorDropdown);
		selectDropdownOption(formFactorOptions, formFactorToBeSelect);
		waitAndClick(saveForm);
		waitForSpinner(5);

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

		waitAndClick(formTemplateTypeDropdown);
		selectDropdownOption(formTemplateTypeOptions, formOption);
		waitAndClick(saveVersion);
		waitForSpinner(5);

	}

	/** Search Form from the form Library and click on the same */
	public void selectSearchLanguageOption(String languageName) {
		inputText(langaugeSearchINP, languageName);
		_waitForJStoLoad();
		_normalWait(1000);
		for (WebElement displayLanguage : languageList) {
			waitAndClick(displayLanguage);
			
		}

		waitAndClick(addLanguageButton);
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
	}

	public void verifyLanguageListDisplayed() {
		Assert.assertTrue(isElementPresent(languageDropdownList), "Langauage List is displaying");
	}

	public void valiateAddedLanguage(String headerName) {

		WebElement header = driver
				.findElement(By.xpath(String.format("//div[contains(@class,'-header')]//h2[text()='%s']", headerName)));
		Assert.assertTrue(isElementPresent(header));
	}

	public void addSubForm() {
		_normalWait(2000);
		waitAndClick(addSubForm);

		
	}

	@FindBy(xpath="//div[@data-ng-form='formEditor']")
	private WebElement divFirstForm;
	
	public void verifyVFormDropdown() {
		waitSpinnerToBecomeInvisible();
		waitForElementPresent(By.xpath("//span[contains(@class,'k-state-default')]//input"),DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(isElementPresent(VformsInput), "VForm Dropdwon is displaying");
		divFirstForm.click();
		reachToEndOfThePage();
	}
	/*Verify DropDown Present*
	 *
	 */
	public void verifyDropDownForForm()
	{
		Assert.assertTrue(isElementPresent(formLangDRPDWN));
		reportInfo();
	}

	
	/*Select First Form Language*/
	public void selectVFromInLanguage() {
		clickOn(formLangDRPDWN);
		for (int formIndex=0;formIndex<=VformsDropdownList.size();formIndex++) {
			WebElement formLang=VformsDropdownList.get(0);
			clickOn(formLang);
			break;
			}
			waitAndClick(saveVersion);
			waitForSpinner(5);		
	}

	public void clickPDFIcon() {
		Assert.assertTrue(isElementPresent(pdfIcon), "PDF icon is displaying");
//		waitAndClick(pdfIcon);
		clickOn(pdfIcon);
	}

	public void verifyWarningMessage(String actual) {
       scrollToTopOfThePage();
       reportInfo();
		String msg = warningMassage.getText();
		Reporter.log("Warning message is" + msg);
		Assert.assertEquals(actual, msg);
		waitForElementToBecomeInvisible(By.xpath("//label[@class='warning-message ng-binding']"));
	
		
	}
	
	public void verifySelectedFormIsDisplayed(String searchForm) {
		_normalWait(2000);
		Assert.assertEquals(selectedForm.getText(), searchForm,searchForm + " is dispalyed and selected");
	}

}
