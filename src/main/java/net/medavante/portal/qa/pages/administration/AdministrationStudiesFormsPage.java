package net.medavante.portal.qa.pages.administration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesFormsPage extends BasePage {

	@FindBy(xpath = "//div[@class='editing-controls no-border']//span[@class='icon-small icon-edit']")
	private WebElement formEditBTN;

	@FindBy(xpath = "//div[@class='editing-controls no-border']//span[@class='icon-small icon-add']")
	private WebElement formAddBTN;

	@FindBy(xpath = "//div[@class='editing-controls no-border']//span[@class='icon-small icon-save']")
	private WebElement formSaveBTN;

	@FindBy(xpath = "//div[@class='editing-controls no-border']//div[@class='input-group']//input")
	private WebElement scaleSearchBoxInput;

	@FindBy(xpath = "//div[@class='editing-controls no-border']//span[@class='icon-small icon-cancel']")
	private WebElement formCancelBTN;

	@FindBy(xpath = "//div[@class='popup-scroll popupcontent']//div//div")
	private List<WebElement> formList;

	@FindBy(xpath = "//div[@class='col-xs-24 portal-grid']//div[contains(@class,'drop-down-editor ng-pristine')]//label")
	private List<WebElement> formNameList;

	@FindBy(xpath = "//div[contains(@class,'btn-group ng-isolate-scope open')]//li/span")
	private List<WebElement> formTypeDropDownValues;

	@FindBy(xpath = "//div[contains(@class,'editing-controls pull-right')]//a")
	private WebElement tabletFormSettingsICN;

	@FindBy(xpath = "//div[contains(@class,'modal-dialog')]/div[@class='modal-content']/div[@class='modal-body']/form[@id='tabletSettingsForm']/div[contains(@class,'input-form-config ng-isolate-scope')]//label")
	private List<WebElement> tabletFormSettingsUpperOptions;

	@FindBy(xpath = "//div[contains(@class,'modal-dialog')]/div[@class='modal-content']/div[@class='modal-body']/form[@id='tabletSettingsForm']/div[contains(@class,'input-form-config ng-isolate-scope')]//div/input")
	private List<WebElement> tabletFormSettingsPopUpUpperOptionsCHKBOX;

	@FindBy(xpath = "//form[@id='tabletSettingsForm']//div[@class='row no-border']//label")
	private WebElement tabletFormSettingsLowerOption;

	@FindBy(xpath = "//form[@id='tabletSettingsForm']//div[@class='row no-border']//div/input")
	private WebElement tabletFormSettingsPopUpLowerOptionCHKBOX;

	@FindBy(xpath = "//form[@id='tabletSettingsForm']//div[@class='row no-border ng-hide']")
	private WebElement tabletFormSettingsHiddenOptions;

	@FindBy(xpath = "//div[@class='modal-footer']//div[contains(@disabled,'dis')]")
	private WebElement savedisableButton;

	@FindBy(xpath = "//div[@class='modal-footer']//div[@class='btn btn-active']")
	private WebElement saveEnableButton;

	@FindBy(xpath = "//div[@class='modal-footer']//div[@class='btn btn-default']")
	private WebElement cancelButton;

	@FindBy(xpath = "//form[@id='tabletSettingsForm']//div[contains(@class,'input-form')]//div[contains(@class,'value')]")
	private WebElement selectedCheckbox;

	@FindBy(xpath = "//label[text()='Turn-Off Section/Page Menu.']/parent::div/preceding-sibling::div//input")
	private WebElement CheckboxSectionPage;

	@FindBy(xpath = "//label[text()='Turn-Off Backward Navigation.']/parent::div/preceding-sibling::div//input")
	private WebElement CheckboxBackwardNavigation;

	@FindBy(xpath = "//label[text()='Turn-Off Forward Navigation.']/parent::div/preceding-sibling::div//input")
	private WebElement CheckboxForwardNavigation;

	@FindBy(xpath = "//label[contains(text(),'Take Break')]/parent::div/preceding-sibling::div//input")
	private WebElement turnOffTakeBreak;

	@FindBy(xpath = "//label[contains(text(),'answered in the assessment.')]/parent::div/preceding-sibling::div//input")
	private WebElement turnOffImDone;

	@FindBy(xpath = "(//span[@class='icon-small icon-edit'])[1]")
	private WebElement formViewEditBTN;

	@FindBy(xpath = "//div[@data-label='Add Scale']")
	private WebElement formViewAddScaleBTN;

	@FindBy(xpath = "(//span[@class='ng-scope']/a[@title='Cancel'])[1]")
	private WebElement formViewCancelBTN;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'studyForms')]")
	private List<WebElement> formsDivisionsList;
	
	@FindBy(xpath = "//div[@data-ng-repeat='culture in cultures()']//div[contains(@class,'row language-name')]")
	private List<WebElement> language;

	@FindBy(xpath = "//div[@data-ng-form='studyFormsEditor']//div[contains(@class,'editing-controls pull-left')]//span[@data-icon='icon-edit']/span/a")
	private WebElement languageConfigureEditButton;

	@FindBy(xpath = "//div[@data-ng-form='studyFormsEditor']//div[contains(@class,'editing-controls pull-left')]//span[@data-icon='icon-save']")
	private WebElement languageConfigureSaveButton;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'version')]//input[@type='checkbox']")
	private List<WebElement> ScaleVersionList;

	protected List<String> listOfTabletSettingOption = new ArrayList<>(
			Arrays.asList("Turn-Off Section/Page Menu.", "Turn-Off Backward Navigation.",
					"Allow backward navigation for signature page.", "Turn-Off Forward Navigation.",
					"Turn-Off Forward Navigation until all required questions are answered on the page.",
					"Turn-Off Form Overrides."));

	public AdministrationStudiesFormsPage(WebDriver driver) {
		super(driver);
	}

	public void verifyFormsPage() {
		Assert.assertTrue(isElementPresent(formEditBTN));
	}

	public void addStudyScale(String formNameToBeSelected) {
		waitAndClick(formEditBTN);
		waitAndClick(formAddBTN);
		for (WebElement formNameWE : formList) {
			if ((getText(formNameWE)).trim().equalsIgnoreCase(formNameToBeSelected)) {
				clickOn(formNameWE);
				clickOn(formSaveBTN);
				waitSpinnerToBecomeInvisible();
				break;
			}
		}
	}

	/**
	 * Select different scale from user specific list of forms
	 * 
	 * @param scaleToBeSelect
	 * @param formType
	 */
	public void selectMultiScaleAndConfiguredFormType(List<String> scaleToBeSelect, String formType) {
		for (String formName : scaleToBeSelect) {
			waitAndClick(formEditBTN);
			waitAndClick(formAddBTN);
			waitForElement(scaleSearchBoxInput);
			inputText(scaleSearchBoxInput, formName);
			formList = driver.findElements(By.xpath("//div[@class='popup-scroll popupcontent']//div//div"));
			for (int i = 0; i < formList.size(); i++) {
				if ((getText(formList.get(i))).trim().equalsIgnoreCase(formName)) {
					clickOn(formList.get(i));
					break;
				}
			}
			for (int i = 0; i < formsDivisionsList.size(); i++) {
				WebElement list = formsDivisionsList.get(i);
				WebElement scaleName = list.findElement(By.xpath(".//label[@class='form-name ng-binding']"));
				if (scaleName.getText().equalsIgnoreCase(formName)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
							list.findElement(By.xpath(".//button")));
					clickOn(list.findElement(By.xpath(".//button")));
					break;
				}

			}
			for (WebElement formTypeOptions : formTypeDropDownValues) {
				if (formTypeOptions.getText().trim().equalsIgnoreCase(formType)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
							formTypeOptions.findElement(By.xpath("./parent::li")));
					doubleClickOnElement(formTypeOptions.findElement(By.xpath("./parent::li")));
					break;
				}
			}
			clickOn(formSaveBTN);
			waitSpinnerToBecomeInvisible();
		}
	}



	public void selectScaleVersion(List<String> formName) {
		for (String formNameToSelect : formName) {
			formsDivisionsList = driver.findElements(By.xpath("//div[contains(@data-ng-repeat,'studyForms')]"));
			for (int i = 0; i < formsDivisionsList.size(); i++) {
				if (formsDivisionsList.get(i).findElement(By.xpath(".//label[@class='form-name ng-binding']")).getText()
						.equalsIgnoreCase(formNameToSelect)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
							formsDivisionsList.get(i).findElement(By.xpath(".//label[@class='form-name ng-binding']")));
					waitAndClick(
							formsDivisionsList.get(i).findElement(By.xpath(".//label[@class='form-name ng-binding']")));
					waitForElementToBecomeInvisible(By.xpath("//div[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']"));
					moveToElement(languageConfigureEditButton);
					waitAndClick(languageConfigureEditButton);
					for (WebElement versionCheckBox : ScaleVersionList) {
						if (versionCheckBox.isSelected() == false) {
							waitAndClick(versionCheckBox);
							waitAndClick(languageConfigureSaveButton);
							waitForElementToBecomeInvisible(By.xpath("//div[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']"));
							break;
						}

					}
				}
			}
		}

	}

	/** Select ClinRo Form **/
	public void selectClinRoForm(String formScaleToBeSelected) {
		for (WebElement clinRO : formNameList) {
			if ((getText(clinRO)).equalsIgnoreCase(formScaleToBeSelected)) {
				clickOn(clinRO);
				new WebDriverWait(driver, 25).until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
				break;
			}
		}
	}

	/** Select ProRo Form **/
	public void selectProForm(String formScaleToBeSelected) {
		for (WebElement proRO : formNameList) {
			if ((getText(proRO)).equalsIgnoreCase(formScaleToBeSelected)) {
				clickOn(proRO);
				waitSpinnerToBecomeInvisible();
				break;
			}
		}
	}

	/** Select ObsRo Form **/
	public void selectObsRoForm(String formScaleToBeSelected) {
		for (WebElement obsRO : formNameList) {
			if ((getText(obsRO)).equalsIgnoreCase(formScaleToBeSelected)) {
				clickOn(obsRO);
				waitSpinnerToBecomeInvisible();
				break;
			}
		}
	}

	/**
	 * Verify Tablet Form Settings Icon And By Default Icon Is Enabled
	 * 
	 * 
	 */
	public void verifyTabletFormSettingIconPresentAndEnabled() {
		Assert.assertTrue(isElementPresent(tabletFormSettingsICN));
		Assert.assertTrue(tabletFormSettingsICN.isEnabled());
	}

	/** Select Open The Tablet Form Settings */

	public void clickOnTabletFormSettings() {
		clickOn(tabletFormSettingsICN);
	}

	/** Check Tablet Form Settings Options Are Selected And Disabled */

	public void verifyTabletFormSettingsUpperOptionsPresenceAndItsStatus() {
		boolean flag;
		List<String> tabletUpperOptionsList = new ArrayList<>();
		for (WebElement result : tabletFormSettingsUpperOptions) {
			tabletUpperOptionsList.add(getText(result));
		}
		for (WebElement checkBox : tabletFormSettingsPopUpUpperOptionsCHKBOX) {
			if (!(checkBox.isEnabled())) {
				flag = true;
				Assert.assertTrue(flag, "The " + tabletUpperOptionsList + "is present and Disable ");
			} else if (checkBox.isEnabled()) {
				flag = false;
				Assert.assertFalse(flag, "The " + tabletUpperOptionsList + " is present but Enable");
			} else {
			}

		}
	}

	/** Check Tablet Form Settings For ClinRo Only Options */

	public void verifyTabletFormSettingsClinRoAssessmentOnlyOptions() {
		boolean flag = false;
		if (tabletFormSettingsPopUpLowerOptionCHKBOX.isEnabled()) {
			flag = true;
			Assert.assertTrue(flag, "The check box for ClinRo Assessment is enable");
		} else {
			Assert.assertFalse(flag, "The check box for ClinRo Assessment is disable");
		}
		Assert.assertEquals("Form should resume from where user previously left off.",
				getText(tabletFormSettingsLowerOption));

	}

	/** Check Tablet Form Settings For Hidden Options */
	public void verifyTabletFormSettingsForHiddenOptions() {
		Assert.assertTrue(isElementNotVisible(tabletFormSettingsHiddenOptions));
	}

	/** Check Save Button Is Disabled And and Cancel Enabled */
	public void verifySaveButtonDisbledAndCancelEnabled() {
		Assert.assertTrue(isElementPresent(savedisableButton));
		Assert.assertTrue(cancelButton.isEnabled());
	}

	/** Select Checkbox From list */
	public void selectCheckBox() {
		waitForElement(CheckboxBackwardNavigation);
		clickOn(CheckboxBackwardNavigation);

	}

	/** Verify Checkbox Is Selected */
	public void verifyCheckboxIsSelectedAndSaveEnabled() {
		waitForElement(CheckboxSectionPage);
		waitForElement(CheckboxBackwardNavigation);
		Assert.assertTrue(checkboxStatus(CheckboxSectionPage) && checkboxStatus(CheckboxBackwardNavigation));
		Assert.assertTrue(saveEnableButton.isEnabled());
	}

	/** Select To Save Options */

	public void saveOptions() {
		waitForElement(saveEnableButton);
		clickOn(saveEnableButton);
		new WebDriverWait(driver, 25)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));

	}

	/** Verify Study Forms View */
	public void verifyStudyFormsView() {
		waitSpinnerToBecomeInvisible();
		waitForElement(formEditBTN);
		Assert.assertTrue(isElementPresent(formEditBTN));
	}

	/** Verify Changes Found */
	public void verifyChangesFound() {
		waitForElement(CheckboxSectionPage);
		waitForElement(CheckboxBackwardNavigation);
		Assert.assertTrue(checkboxStatus(CheckboxSectionPage) && checkboxStatus(CheckboxBackwardNavigation));
	}

	/** Revert changes Deselect Options */

	public void deselectCheckBox() {
		waitForElement(CheckboxSectionPage);
		clickOn(CheckboxSectionPage);
	}

	/** Select Checkbox Forward Navigation */
	public void selectCheckBoxForwardNavigation() {
		waitForElement(CheckboxForwardNavigation);
		clickOn(CheckboxForwardNavigation);
	}

	/** Verify Forward Navigation and page section checkbox selected */
	public void verifySelectForwardNavigationAndPageSectionSelected() {
		waitForElement(CheckboxSectionPage);
		waitForElement(CheckboxForwardNavigation);
		Assert.assertTrue(checkboxStatus(CheckboxSectionPage) && checkboxStatus(CheckboxForwardNavigation));
	}

	/** Verify Take Break And I'm Done CheckBoxes */
	public void verifyTakeBreakAndDone() {
		/*
		 * waitForElement(turnOffTakeBreak); waitForElement(turnOffImDone);
		 */
		if (turnOffTakeBreak.isEnabled() && turnOffImDone.isEnabled()) {
			Assert.assertTrue(true,
					"Take Break And I'm Done CheckBoxes are visible along with other options and in enable status.");
		} else {

			Assert.assertFalse(false,
					"Take Break And I'm Done CheckBoxes are visible along with other options and in disable status.");
		}

	}

	/** Verify after click on cancel button Study Form View is visible */
	public void verifyStudyFormViewPageDisplayedAfterClickOnCancelBTNOnTabletSettingPopUP() {

		clickOn(cancelButton);
		waitAndClick(formViewEditBTN);
		if (formViewAddScaleBTN.isDisplayed() && formViewAddScaleBTN.isEnabled()) {
			Assert.assertTrue(true, "Study Form View is visible");
		} else {
			Assert.assertFalse(false, "Unable to load Syudy Form View");
		}
		clickOn(formViewCancelBTN);
		waitSpinnerToBecomeInvisible();

	}

	/** For Setting Pre-requisite Modify Changes For Clinro form */
	public void modifyingCLinRoFormChanges() {
		selectClinRoForm("ClinRO");
		waitSpinnerToBecomeInvisible();
		clickOn(tabletFormSettingsICN);
		clickOn(CheckboxForwardNavigation);
		clickOn(CheckboxSectionPage);
		clickOn(saveEnableButton);
	}

	/** Verify form page by Tablet Setting Icon */
	public void verifyFormPageIsDisplayed() {
		waitSpinnerToBecomeInvisible();
		Assert.assertTrue(tabletFormSettingsICN.isDisplayed() && formViewEditBTN.isDisplayed(), "Form page loaded.");
		reportInfo();
	}

	/** For Setting Pre-requisite Modify Changes For Pro Form */
	public void modifyingProFormChanges() {
		selectProForm("PRO");
		waitSpinnerToBecomeInvisible();
		waitSpinnerToBecomeInvisible();
		clickOn(tabletFormSettingsICN);
		clickOn(CheckboxForwardNavigation);
		clickOn(CheckboxSectionPage);
		clickOn(saveEnableButton);
	}

	/** Verify FormAdded In Study Exist In List */
	public void verifyFormsConfiguredInStudyPresent() {
		boolean listElementPresent = false;
		if (formNameList.size() > 0) {
			listElementPresent = true;
			Assert.assertTrue(listElementPresent, "List of forms selected for study is displayed in view mode");
		} else {
			Assert.assertTrue(listElementPresent, "List of forms selected for study is Not displayed in view mode");
		}

	}

	/** Click To Edit Form */
	public void clickOnEditForm() {
		clickOn(formEditBTN);
	}

	/** Verify Forms All Forms Displayed In Editable Mode */
	 public void verifyAllFormsPresentInEditableModeAndClinRoTypeIsFirstBlindOptionPresentWithSaveCancel(String clinroType, String mobileTypeValue ) {
		    boolean type = false;
		    for (int i = 0; i < formsDivisionsList.size(); i++) {
		     WebElement list = formsDivisionsList.get(i);
		     WebElement typeDropDownElement = list.findElement(By.xpath(".//button"));
		     Assert.assertTrue(isElementPresent(typeDropDownElement));
		     if (list.findElement(By.xpath(".//span[@id='selectedStudy']")).getText().equalsIgnoreCase("ClinRO")) {
		      WebElement blindOptionBox = list
		        .findElement(By.xpath("(.//div[@class='col-xs-3 ng-isolate-scope']//input)[2]"));
		      Assert.assertTrue(isElementPresent(blindOptionBox));
		     }
		     clickOn(typeDropDownElement);
		    String clinroTypeDropValue = list.findElement(By.xpath(".//ul/li[1]/span")).getText();
		     if (clinroTypeDropValue.trim().equalsIgnoreCase(clinroType)) {
		      type = true;
		      Assert.assertTrue(type, "The First Type Is ClinRoType Present");
		      clickOn(typeDropDownElement);
		     }
		     else if (clinroTypeDropValue.trim().equalsIgnoreCase("Mobile")) {
		      type = true;
		      Assert.assertTrue(type, "The First Type Is MobileType Present");
		      clickOn(typeDropDownElement);
		     
		     }

		    }
		    Assert.assertTrue(isElementPresent(formSaveBTN) && isElementPresent(formCancelBTN));
		   }

	/**
	 * Click On Type DropDown And Select Form Type
	 */

	public void clickOnDropDownAndSelectFormType(String formName, String formType) {

		for (int i = 0; i < formsDivisionsList.size(); i++) {
			WebElement list = formsDivisionsList.get(i);
			WebElement scaleName = list.findElement(By.xpath(".//label[@class='form-name ng-binding']"));
			if (scaleName.getText().equalsIgnoreCase(formName)) {
				clickOn(list.findElement(By.xpath(".//button")));
				break;
			}

		}
		for (WebElement formTypeOptions : formTypeDropDownValues) {
			if (formTypeOptions.getText().trim().equalsIgnoreCase(formType)) {
				clickOn(formTypeOptions.findElement(By.xpath("./parent::li")));
				break;
			}

		}
	}

	/** Verify Type DropDownOption */
	public void verifyTypeDropDownOptionAndBlindOptionDisabled(String formName, String formType) {
			  boolean flag = true;
			  for (int i = 0; i < formsDivisionsList.size(); i++) {
			   WebElement list = formsDivisionsList.get(i);
			   WebElement scaleName = list.findElement(By.xpath(".//label[@class='form-name ng-binding']"));
			   if (scaleName.getText().equalsIgnoreCase(formName)) {
			    String typeDropValue = list.findElement(By.xpath(".//button/span")).getText();
			    if (typeDropValue.trim().equalsIgnoreCase(formType)) {
			         flag = true;
			         Assert.assertTrue(flag, "The Form type Label Is Displayed On Type Label");
			         WebElement blindOptionCheckBox = list
			           .findElement(By.xpath("(.//div[@class='col-xs-3 ng-isolate-scope']//input)[2]"));
			         blindOptionCheckBox.getAttribute("disabled").contains("disabled");
			         flag=true;
			         Assert.assertTrue(true, "The Blind Option Is Disabled");
			         break;
			        }
			    break;
			}

		}

	}

	/** Click On Save Button */
	public void clickOnSaveScaleButton() {
		clickOn(formSaveBTN);
		waitForElementToBecomeInvisible(By.xpath("//div[@class='spinner']"));
	}

	/** Verify Form type label Shown After Selecting FormType */
	public void verifyFormTypeAfterSavingFormType(String formName, String formType) {
		boolean flag = true;
		for (int i = 0; i < formsDivisionsList.size(); i++) {
			WebElement list = formsDivisionsList.get(i);
			WebElement scaleName = list.findElement(By.xpath(".//label[@class='form-name ng-binding']"));
			if (scaleName.getText().contains(formName)) {
				String typeValue = list.findElement(By.xpath(".//label[@data-ng-hide='isEdited']")).getText();
				if (typeValue.trim().contains(formType)) {
					flag = true;
					Assert.assertTrue(flag, "The Form type Label Is Displayed On List Of Form Displayed");
					break;
				}
				break;
			}

		}
	}

	public void deselectAllCheckBoxOptions() {
		int count = 0;
		for (WebElement element : tabletFormSettingsPopUpUpperOptionsCHKBOX) {
			if (element.isEnabled() && element.isSelected()) {
				clickOn(element);
				count++;
			}
		}
		if (count > 0) {
			waitForElement(saveEnableButton);
			clickOn(saveEnableButton);
			new WebDriverWait(driver, 25)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		} else {
			clickOn(cancelButton);
			new WebDriverWait(driver, 15).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[contains(@class,'editing-controls pull-right')]//a")));

		}

	}
}
