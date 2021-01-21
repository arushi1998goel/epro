package net.medavante.portal.pages.administration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BasePage;
import net.medavante.portal.utilities.Constants;

public class AdministrationStudiesGeneralPage extends BasePage {

	// Locators For Add Study
	@FindBy(xpath = "//a[@title='Add']")
	private WebElement addNewStudyBTN;

	@FindBy(xpath = "//*[@class='details-grid portal-grid row']/div/div[2]//div[@class='value col-xs-15']/label")
	private WebElement studiesNameLabel;

	@FindBy(xpath = "//div[@class='property-value-edit checkbox-list product-types-checkbox-list']")
	private WebElement productTypeSection;

	@FindBy(xpath = "//div[@class='checkbox-list-item value']//input")
	private List<WebElement> listOfSections;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.abbreviation']/div/div[2]/input")
	private WebElement studyAbbreviationINP;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.sponsor']//button[@data-toggle='dropdown']")
	private WebElement sponsorDRPDWN;

	@FindBy(xpath = "//ul[@class='dropdown-menu']/li/span")
	private List<WebElement> studyDRPDWNOptions;

	@FindBy(xpath = "//div[@id='portal-grid-page-content']//div[@class='input-group']//input")
	private WebElement searchINP;

	@FindBy(xpath = "//input[contains(@ng-model,'MobilePro')]")
	private WebElement MobilePROBTN;

	@FindBy(xpath = "//input[contains(@ng-model,'Participant')]")
	private WebElement subjectbtn;

	@FindBy(xpath = "//input[contains(@ng-model,'Observer')]")
	private WebElement observerbtn;
	// Locators of the elements present on General Page

	@FindBy(xpath = "//div[@class='details-grid portal-grid row']/div[@class='col-xs-11']/div/div//div[2]/label[contains(@class,'ng-hide')]")
	private List<WebElement> gridElementsInEditableModeLIST;

	@FindBy(xpath = "//div[@data-product-types='model.productTypes' ]//div/span")
	private WebElement productTypeStringArray;

	@FindBy(xpath = "//span[@ng-hide='detailsEditorService.isEditing']")
	private WebElement productTypeLabel;

	@FindBy(xpath = "//div[@ng-show='detailsEditorService.isEditing']/div")
	private List<WebElement> productTypeList;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.name']")
	private WebElement genralStudyName;

	@FindBy(xpath = "//div[@title='Activated']/label")
	private WebElement actvatedDateELE;

	@FindBy(xpath = "//div[@title='Deactivated']/label")
	private WebElement deActvatedDateELE;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.status']/div")
	private WebElement statusWE;

	@FindBy(xpath = "//div[@title='Status']//div/ul/li")
	private List<WebElement> statusDrPDwnLIST;

	@FindBy(xpath = "//div[@title='Sponsor']/button")
	private WebElement sponserDrpDwnWE;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.sponsor']//div[@class='value col-xs-15']/div/div")
	private WebElement sponserFieldWE;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.sponsor']//div/ul//li")
	private List<WebElement> sponserDrpDwnLIST;

	@FindBy(xpath = "//div[@data-value='model.awsBucket']//label[@data-ng-show='isEdited == false || readOnly == true']")
	private WebElement awsBucketDataWE;

	@FindBy(xpath = "//div[@data-value='model.awsBucket']//div//input")
	private WebElement awsBucketDataINP;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.protocolTitle' ]//textarea")
	private WebElement protocolTitleAreaWE;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.name']//div//input")
	private WebElement studyNameINP;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.name']//div[@class='value col-xs-15']/label")
	private WebElement studyNameTXT;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.abbreviation']//div//input")
	private WebElement abberviationINP;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.abbreviation']//div[@class='value col-xs-15']//label")
	private WebElement abberviationTXT;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.phase']//div//input")
	private WebElement phaseINP;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.phase']//div[@class='value col-xs-15']//label")
	private WebElement phaseTXT;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.trainingStudy']//div/input")
	private WebElement trainingStudyCHKBOX;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.indication']//div/ul//li")
	private List<WebElement> indicationsDrPDwnLIST;

	@FindBy(xpath = "//div[@data-text-id='Models.studies.indication']//div[@class='value col-xs-15']")
	private WebElement indicationDrpDwnWE;

	@FindBy(xpath = "//a[contains(text(),'Schedule')]")
	private WebElement scheduleTAB;

	@FindBy(xpath = "//a[contains(text(),'Applications')]")
	private WebElement applicationsTAB;

	@FindBy(xpath = "//a[contains(text(),'Identity')]")
	private WebElement identityTAB;

	// Save and Cancel buttons available on Grid

	@FindBy(xpath = "//a[@title='Save' and @data-ng-disabled='isDisabled()']/span")
	private WebElement saveButton;

	@FindBy(xpath = "//a[@title='Save' and @disabled='disabled']")
	private List<WebElement> disabledSaveBTN;

	@FindBy(xpath = "//a[@title='Cancel' and @disabled='disabled']")
	private List<WebElement> disabledCancelBTN;

	@FindBy(xpath = "//a[@title='Cancel']")
	private WebElement cancelBTN;

	@FindBy(xpath = "//div[@id='breadcrumbs']/a[@class='home']")
	private WebElement homeICN;

	@FindBy(xpath = "//div[@class='inline item-icon ng-scope']/div")
	private WebElement lockIcon;

	// String values to validate data
	List<String> statusElements = new ArrayList<>(
			Arrays.asList("Active", "Closed", "Closing", "Completed", "On Hold", "Pending Release", "Terminated"));
	List<String> indicationsElements = new ArrayList<>(
			Arrays.asList("ADHD", "Alzheimer's Disease", "Bipolar Depression", "Depression", "Downs Syndrome",
					"Generalized Anxiety Disorder", "Major Depressive Disorder", "Parkinson's Syndrome",
					"Psychotic Depression", "Retinopathy", "Schizophrenia", "Suicidality"));

	List<String> stringList = new ArrayList<>();
	List<String> indicationStringLIST = new ArrayList<>();

	private String modifiedProtocolValue = "Changed Test Protocol TC1196";

	public AdministrationStudiesGeneralPage(WebDriver driver) {
		super(driver);
	}

	public void verifyAdministrationStudiesGeneralPageIsOpen(String studyNameAdmin) {
		Assert.assertEquals(studyNameAdmin, studiesNameLabel.getText(), "studies General page is open Sucessfully");
		reportInfo();
	}

	/**
	 * @function: To verification of elements that they are only in read only mode
	 */
	public void verifyGenralTabElementsAreInReadOnlyMode() {
		boolean flag = false;
		if (sizeofTheList(gridElementsInEditableModeLIST) < 0) {
			flag = true;
			Assert.assertTrue(flag, "Grid elements are in read only mode");
			reportInfo();
		} else {
			Assert.assertFalse(flag);
		}
	}

	/**
	 * @function: Verification of Save and Cancel button's status
	 * 
	 */

	public void verifySaveAndCancelControlStatus() {

		boolean flag3 = isElementVisibleListSize(disabledSaveBTN);
		boolean flag4 = isElementVisibleListSize(disabledCancelBTN);
		boolean flag1 = isElementVisibleListSize(disabledSaveBTN);
		boolean flag2 = isElementVisibleListSize(disabledCancelBTN);

		if (flag1 == true && flag2 == true) {
			Assert.assertTrue(flag1, "Save button is disabled");
			Assert.assertTrue(flag2, "Cancel button is disabled");
		} else if (flag3 == true && flag4 == true) {
			Assert.assertFalse(flag1, "Save button is enabled");
			Assert.assertFalse(flag2, "Cancel button is enabled");
		} else if (flag1 == true && flag2 == false) {
			Assert.assertTrue(flag1, "Save button is disabled");
			Assert.assertFalse(flag2, "Cancel button is enabled");
		}
	}

	/**
	 * @function: Verification of elements
	 */
	public void verifyProductTypeElements() {
		boolean flag = false;
		String str1 = productTypeStringArray.getText();
		List<String> productTypeList = Arrays.asList(str1.split("\\s*,\\s*"));
		int length = productTypeList.size();
		if (length >= 1) {
			flag = true;
			Assert.assertTrue(flag, " field have more than one element");
		} else {
			Assert.assertFalse(flag);
		}
	}

	/**
	 * @function: Verification of elements that they are only in edit mode
	 */
	public void verifyGenralTabElementsAreInEditMode() {
		boolean flag = false;
		clickOn(genralStudyName);
		if (sizeofTheList(gridElementsInEditableModeLIST) > 0) {
			flag = true;
			Assert.assertTrue(flag, "Grid elements are in edit mode");
			reportInfo();
		} else {
			Assert.assertFalse(flag);
		}
		verifyDateFormat(actvatedDateELE);
		verifyDateFormat(deActvatedDateELE);
		verifyStatusDropDownLIST();
	}

	/**
	 * @function: Verification of Sponsor and AWS bucket data in read only mode
	 */
	public void verifySponsorAndAWSBucketDataStatus() {

		boolean flag = false;
		if (elementStatus(sponserDrpDwnWE) == true) {
			flag = true;
			Assert.assertTrue(flag, "Sponser drop down is in read only mode");
		} else {
			Assert.assertFalse(flag);
		}
		if (awsBucketDataWE.isDisplayed()) {
			flag = true;
			Assert.assertTrue(flag, "AWS Bucket data is in read only mode");
		} else {
			Assert.assertFalse(flag);
		}
	}

	/**
	 * @function: Verify status of Save Button after modifying a field value
	 * 
	 */
	public void verifySaveBTNStatusAfterModifyAFieldValue() {
		inputText(protocolTitleAreaWE, modifiedProtocolValue);
		reportInfo();
		verifySaveAndCancelControlStatus();

	}

	/**
	 * @function: Verification of required fields
	 */
	public void verifyRequiredFieldHighlightedAfterClearingData() {
		List<WebElement> inputFieldsList = Arrays.asList(studyNameINP, abberviationINP, phaseINP);
		List<String> bgColorBeforeList = new ArrayList<>();
		List<String> bgColorAfterList = new ArrayList<>();
		boolean flag = true;

		for (WebElement inputFieldElement : inputFieldsList) {
			String color = getBackgroundColor(inputFieldElement);
			bgColorBeforeList.add(color);
		}
		for (WebElement inputFieldEle : inputFieldsList) {
			clearTextBox(inputFieldEle);
		}
		reportInfo();
		for (WebElement inputFieldElement : inputFieldsList) {
			String color = getBackgroundColor(inputFieldElement);
			bgColorAfterList.add(color);
		}
		if ((bgColorBeforeList.toString().contentEquals(bgColorAfterList.toString())) == false) {
			flag = false;
			Assert.assertFalse(flag, "Fields are highlighted");
		} else {
			Assert.assertTrue(flag);
		}
	}

	/**
	 * @function: Verification required fields are highlighted
	 */
	public void requiredFieldsOnGeneralTabArehighLighted(String backGroundColour) {

		boolean flag = false;
		if (studyNameINP.getCssValue("background").contains(backGroundColour)
				&& phaseINP.getCssValue("background").contains(backGroundColour)
				&& abberviationINP.getCssValue("background").contains(backGroundColour)) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void sponserFieldIsHighlighted(String backGroundColour) {
		boolean flag = false;
		if (sponserFieldWE.getCssValue("background").contains(backGroundColour)) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	/**
	 * @function: Verify changes are not saved after clicking on Cancel and fields
	 *            become read only
	 */
	public void verifyChangesNotSavedAfterClickingOnCancel() {
		boolean flag = false;
		String TCID = "SFBTC97";
		clickOn(cancelBTN);
		verifyGenralTabElementsAreInReadOnlyMode();
		clickOn(statusWE);
		if (!(getText(protocolTitleAreaWE).equalsIgnoreCase(modifiedProtocolValue))) {
			flag = true;
			Assert.assertTrue(flag, "Changes are not saved as in Step#3");
		} else {
			Assert.assertFalse(flag);
		}
		clickOn(cancelBTN);
		List<WebElement> inputFieldsList = Arrays.asList(studyNameTXT, abberviationTXT, phaseTXT);
		for (WebElement inputFieldElement : inputFieldsList) {
			String elementTXT = getText(inputFieldElement);
			stringList.add(elementTXT);
		}
		if (stringList.stream().anyMatch((s) -> s.contains(TCID))) {
			flag = true;
			Assert.assertTrue(flag, "Fields have proper values");
			reportInfo();
		} else {
			Assert.assertFalse(flag);
		}
	}

	/**
	 * @function: Verification of fields while creating a new Study
	 */
	public void fieldsVerificationForCreatingNewStudy() {
		boolean flag = false;
		List<WebElement> inputFieldsList = Arrays.asList(studyNameINP, abberviationINP, phaseINP);
		clickOn(addNewStudyBTN);
		String requriedStr = "Field is required";

		for (WebElement inputFieldElement : inputFieldsList) {
			String attributeValue = getAttributeValueOfElement(inputFieldElement, "title");
			if (attributeValue.contains(requriedStr)) {
				flag = true;
				Assert.assertTrue(flag, "Field is Mandatory");
			} else {
				Assert.assertFalse(flag);
			}
		}
		if (getAttributeValueOfElement(sponserFieldWE, "title").contains(requriedStr)) {
			flag = true;
			Assert.assertTrue(flag, "Field is Mandatory");
		} else {
			Assert.assertFalse(flag);
		}
		boolean trainingStudyCheckBoxStatus = checkboxStatus(trainingStudyCHKBOX);
		Assert.assertFalse(trainingStudyCheckBoxStatus, "Training Check Box is not Selected");
		if ((awsBucketDataINP.getText()).length() <= 0) {
			flag = true;
			Assert.assertTrue(flag, "AWS Bucket data is NULL");
		} else {
			Assert.assertFalse(flag);
		}
	}

	/**
	 * @function: Verify Sponsor Drop Down List
	 * 
	 */
	public void verifySponsorDropDownList() {
		boolean flag = false;
		String sponser = "sponser";

		waitAndClick(sponserFieldWE);
		for (WebElement inputFieldElement : sponserDrpDwnLIST) {
			String elementTXT = getText(inputFieldElement);
			stringList.add(elementTXT);
		}
		if (stringList.stream().anyMatch((s) -> s.contains(sponser))) {
			flag = true;
			Assert.assertTrue(flag, "Fields have proper values");
			reportInfo();
		} else {
			Assert.assertFalse(flag);
		}
	}

	/**
	 * @function: Verify Status Drop Down List
	 */
	public void verifyStatusDropDownLIST() {
		boolean flag = false;
		clickOn(statusWE);
		for (WebElement statusEle : statusDrPDwnLIST) {
			String statusTXT = statusEle.getText();
			if (statusElements.contains(statusTXT)) {
				flag = true;
				Assert.assertTrue(flag, "Status is present");
			} else {
				Assert.assertFalse(flag);
			}
		}
		clickOn(statusWE);
	}

	/**
	 * @function: Verify Indications Drop Down have Indications Types
	 */
	public void verifyIndicationDropDownLIST() {

		boolean flag = false;
		clickOn(indicationDrpDwnWE);
		for (WebElement statusEle : indicationsDrPDwnLIST) {
			String indicationsTXT = getText(statusEle);
			indicationStringLIST.add(indicationsTXT);
		}
		if (indicationStringLIST.containsAll(indicationsElements)) {
			flag = true;
			Assert.assertTrue(flag, "Indications are present");
		} else {
			Assert.assertFalse(flag);
		}
		clickOn(indicationDrpDwnWE);
	}

	public void selectProductTypeCheckbox(String option) {

		WebElement productTypeToSelect = driver.findElement(
				By.xpath("//div[@ng-show='detailsEditorService.isEditing']//label[text()= '" + option + "']"));
		waitAndClick(productTypeToSelect.findElement(By.xpath(".//parent::div//input")));
		clickOnSaveButton();
	}

	public void verifyProductTypeCheckboxSelected(String option) {
		boolean flag = false;
		waitAndClick(productTypeLabel);
		WebElement productTypeToSelect = driver.findElement(
				By.xpath("//div[@ng-show='detailsEditorService.isEditing']//label[text()= '" + option + "']"));

		if (productTypeToSelect.findElement(By.xpath(".//parent::div//input")).isSelected()) {
			flag = true;
			moveToElement(productTypeToSelect);
			Assert.assertTrue(flag, " checkbox is Selected");

		}

	}

	public void clickOnSaveButton() {
		waitAndClick(saveButton);
		waitSpinnerToBecomeInvisible();
	}

	/** Return Back to Home Page */
	public MedAvantePortalPage navigateToHomePage() {
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.HomeNavText);
		return (PageFactory.initElements(driver, MedAvantePortalPage.class));
	}

	public void switchToAny(String tabName, Class className) {
		navigateToSubTabs(tabName, className);
	}

	/*
	 * public void IsIconEditable(String value) {
	 * 
	 * if(value) { Assert.assertTrue(true); }else { Assert.assertTrue(false); } }
	 */

	public <T> T lockunlockStudy(Class<T> className, String confirmation, String state) {

		WebElement lockUnlockButton = driver.findElement(ByLocator("//div[text()='" + state + "']"));
		waitAndClick(lockUnlockButton);
		WebElement cnfrmtn = driver
				.findElement(ByLocator("//div[@id='queryConfirmation']//div[text()='" + confirmation + "']"));
		waitAndClick(cnfrmtn);
		_normalWait(3000);
		Assert.assertTrue(true);

		return PageFactory.initElements(driver, className);
	}

	public void verifyStudyLockIsdisplayed(String Study) {
		WebElement studylock = driver.findElement(
				ByLocator("//span[contains(text(),'" + Study + "')]/..//div[@class='inline item-icon ng-scope']/div"));

		moveToElement(studylock);
		Assert.assertTrue(isElementDisplayed(studylock));
		reportInfo();

	}

	public void verifystudyLockIsNOtdisplayed(String study) {
		boolean flag = true;

		try {
			WebElement studylock = driver.findElement(ByLocator(
					"//span[contains(text(),'" + study + "')]/..//div[@class='inline item-icon ng-scope']/div"));
			if (studylock.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}

		Assert.assertTrue(flag);
		reportInfo();

	}

	/***
	 * Verify product type section
	 */
	public void verifyProductTypeSectionIsAvailable() {

		Assert.assertTrue(isElementDisplayed(productTypeSection));
		moveToElement(productTypeSection);
		reportInfo();
	}

	/***
	 * Verify no options are selected in product type section
	 */
	public void verifyNoOptionsAreSelectedInProductTypeSection() {
		int size = listOfSections.size();
		for (int i = 0; i < size; i++) {
			if (listOfSections.get(i).isSelected() == false) {
				Assert.assertTrue(true);
			}
		}
	}

	/***
	 * enter data to create study
	 */
	public void enterDataToCreateStudy(String studyName, String studyAbbreviation, String studyPhase,
			String studySponsor) {
		inputText(studyNameINP, studyName);
		inputText(studyAbbreviationINP, studyAbbreviation);
		inputText(phaseINP, studyPhase);
		waitAndClick(sponsorDRPDWN);
		WebElement sponsortoselect = driver.findElement(ByLocator("//span[text()='" + studySponsor + "']"));
		waitAndClick(sponsortoselect);
		waitForSpinner(3);

	}

	/***
	 * click on cancel button
	 */

	public void clickOncancelbutton() {
		clickOn(cancelBTN);

	}

	/***
	 * Verify study is not saved by search field
	 */
	public void VerifyStudyIsNotSaved(String study) {
		inputText(searchINP, study);
		boolean flag = true;
		try {
			WebElement studylist = driver.findElement(ByLocator("//span[contains(text(),'" + study + "']"));
			if (studylist.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Select Mobile PRO checkbox
	 */
	public void selectMobileProCheckBox() {
		if (MobilePROBTN.isSelected() == false) {
			clickOn(MobilePROBTN);
		} else {
			logger.info("MobilePROBTN Checkbox is already selected");
		}
	}

	/**
	 * Verify subject check box is auto selected after selecting Mobile PRO
	 */
	public void verifySelectedCheckboxisdisable() {
		boolean flag = true;
		if (subjectbtn.isEnabled()) {
			flag = false;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Observer check box is not selected
	 */
	public void verifyObserverCheckBoxIsnotSelected() {
		boolean flag = true;
		if (observerbtn.isSelected()) {
			flag = false;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/***
	 * click on observer checkbox
	 */

	public void selectObserverCheckbox() {
		waitAndClick(observerbtn);
	}

	/**
	 * Verify product type input field
	 */
	public void verifyProductTypeInputSection(String ProductField) {
		WebElement productFieldinput = driver.findElement(ByLocator("//span[contains(text(),'" + ProductField + "')]"));
		Assert.assertTrue(isElementDisplayed(productFieldinput));
		moveToElement(productFieldinput);
		reportInfo();
	}

	/**
	 * Verify Schedule tab is available for Study in Pr#2
	 */

	public void verifyScheduleTabIsDisplayed() {
        waitForElementPresent(scheduleTAB,DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(isElementDisplayed(scheduleTAB));
		moveToElement(scheduleTAB);
		reportInfo();

	}

	/**
	 * Verify Applications tab is available for Study in Pr#2
	 */
	public void verifyApplicationTabIsDisplayed() {
		Assert.assertTrue(isElementDisplayed(applicationsTAB));
		moveToElement(applicationsTAB);
		reportInfo();
	}

	/**
	 * Verify Identity tab is available for Study in Pr#3
	 */

	public void verifyIdentityTabIsDisplayed() {
		Assert.assertTrue(isElementDisplayed(identityTAB));
		moveToElement(identityTAB);
		reportInfo();
	}

	/**
	 * unselect all selected check box from product type
	 */
	public void unselectallSelectedCheckBox() {
		_normalWait(2000);
		boolean flag = false;
		if (observerbtn.isSelected()) {
			//waitAndClick(MobilePROBTN);
			javascriptButtonClick(MobilePROBTN);
			flag=true;
		}
		Assert.assertTrue(flag);

	}

	public void clickOnSaveIcon() {
		javascriptButtonClick(saveButton);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath("//*[contains(@data-ng-show,'isBusy') and @class='ng-isolate-scope']//div[@class='spinner']")));
	}
}
