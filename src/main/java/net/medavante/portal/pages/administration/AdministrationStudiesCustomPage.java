package net.medavante.portal.pages.administration;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BasePage;
import net.medavante.portal.utilities.Constants;

public class AdministrationStudiesCustomPage extends BasePage {

	@FindBy(xpath = "//div[contains(@class,'custom-tab-container')]")
	private WebElement customPageContainer;

	@FindBy(xpath = "//div[contains(@class,'right-pane-wrapper')]//div[@class='ng-isolate-scope']/div/div[1]/label[text()='Screening#']")
	private WebElement ScreeningOfCustomLabel;

	@FindBy(xpath = "//div[@id='portal-grid-page-content']//input[@name='search']")
	private WebElement searchINP; 

	@FindBy(xpath = "//div[@class='scroll-wrapper']//div[@data-ng-click='selectItem(item)']//span[@class='item-name ng-binding']")
	private List<WebElement> studyList;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'subjectStatusAlias')][1]//div[@class='value']/textarea")
	private WebElement subjectNewStatusDescription;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'subjectStatusAlias')][2]//div[@class='value']/textarea")
	private WebElement subjectScreenedStatusDescription;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'subjectStatusAlias')][3]//div[@class='value']/textarea")
	private WebElement subjectScreenFailedStatusDescription;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'subjectStatusAlias')][4]//div[@class='value']/textarea")
	private WebElement subjectEnrolledStatusDescription;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'subjectStatusAlias')][5]//div[@class='value']/textarea")
	private WebElement subjectCompletedStatusDescription;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'subjectStatusAlias')][6]//div[@class='value']/textarea")
	private WebElement subjectDiscontinueStatusDescription;

	@FindBy(xpath = "//a[@title='Save']")
	private WebElement saveIcon;

	@FindBy(xpath = "//span[@class='icon-small icon-home']")
	private WebElement homeIcon;

	@FindBy(xpath = "//span[@class='icon-small icon-restart']")
	private WebElement resetButton;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'subjectStatusAlias')]//div[@class='value']//textarea")
	private List<WebElement> subjectStatusDescriptionINPLIST;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'subjectStatusAlias')]//span[@class='icon-small icon-restart']")
	private List<WebElement> subjectStatusDescriptionResetBTN;

	// ================ Subject Field Format =====================//
	@FindBy(xpath = "(//div[@class='row no-border top-indent'])[1]")
	private WebElement subjectFieldFormatHeader;

	@FindBy(xpath = "(//label[text()='Screening#']/parent::div/following-sibling::div/input)")
	private WebElement subjectFieldFormatScreeningNumINP;

	@FindBy(xpath = "(//label[text()='Randomization#']/parent::div/following-sibling::div/input)")
	private WebElement subjectFieldFormatRandomizationNumINP;

	@FindBy(xpath = "(//label[text()='TemporaryID']/parent::div/following-sibling::div/input)")
	private WebElement subjectFieldFormatTemporaryIDINP;

	@FindBy(css = "div[role='tooltip']")
	private WebElement hintBlockForMaskingFormat;

	@FindBy(xpath = "//a[@title='Cancel']")
	private WebElement cancelIcon;

	@FindBy(xpath = "(//label[text()='Screening#']/parent::div/following-sibling::div/input/preceding-sibling::label)")
	private WebElement subjectFieldFormatScreeningNumTXT;

	@FindBy(xpath = "(//label[text()='Randomization#']/parent::div/following-sibling::div/input/preceding-sibling::label)")
	private WebElement subjectFieldFormatRandomizationNumTXT;

	@FindBy(xpath = "(//label[text()='TemporaryID']/parent::div/following-sibling::div/input/preceding-sibling::label)")
	private WebElement subjectFieldFormatTemporaryIDTXT;

	@FindBy(xpath = "(//label[text()='Screening#']/parent::div/following-sibling::div/label)")
	private WebElement subjectFieldFormatScreeningNumTXT1;

	// ================================Subject Field
	// Visibility====================//
	@FindBy(xpath = "//div[contains(@data-text-id,'subjectAutoGenerateTemporaryId')]//input[@type='checkbox']")
	private WebElement autoGenerateTemporaryIDCHKBOX;

	@FindBy(xpath = "//div[contains(@data-text-id,'subjectRandomizationNumberFieldVisibility')]//input[@type='checkbox']")
	private WebElement randomizationNumberCHKBOX;

	@FindBy(xpath = "//div[contains(@data-text-id,'subjectDobNumberFieldVisibility')]//input[@type='checkbox']")
	private WebElement dateOfBirthCHKBOX;

	@FindBy(xpath = "//div[contains(@data-text-id,'subjectYobNumberFieldVisibility')]//input[@type='checkbox']")
	private WebElement yearOfBirthCHKBOX;

	@FindBy(xpath = "//div[contains(@data-text-id,'subjectTemporaryIdFieldVisibility')]//input[@type='checkbox']")
	private WebElement temporaryIdCHKBOX;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'subjectStatusAlias')]//input")
	private List<WebElement> subjectAliasFieldINP;

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'subjectStatusAlias')]//div[contains(@class,'col-xs-15')]//label")
	private List<WebElement> subjectCustomRenameStatusTxt;

	@FindBy(xpath = "//div[@data-ng-switch-when='studyCustom']//span[@data-action='save()']")
	private WebElement saveButtonUnderStudiesCustomtab;

	@FindBy(xpath = "//div[@data-text-id='Models.studyCustom.subjectTemporaryIdMask']//div[@class='value col-xs-15']/label")
	private WebElement tempIDFormat;

	@FindBy(xpath = "//h4[@id='queryConfirmationLabel']/ancestor::div[@class='modal-content']")
	private WebElement confirmationModalPopUp;

	@FindBy(css = "div[data-ng-click='okClick()']")
	private WebElement yesButtonOnConfirmationModalPopUp;

	@FindBy(css = "div[data-ng-click='cancelClick()']")
	private WebElement cancelButtonOnConfirmationModalPopUp;
	
	@FindBy(xpath = "(//input[@class='checkbox-big ng-pristine ng-untouched ng-valid' and @disabled='disabled'])[1]")
	private WebElement disableCheckbox;
	
	
	public AdministrationStudiesCustomPage(WebDriver driver) {
		super(driver);
	}

	/** Verify Custom Page */

	public void verifyCustomPage() {
		Assert.assertTrue(customPageContainer.isDisplayed());
		reportInfo();
	}

	/**
	 * Custom Page in edit mode
	 * 
	 * @author Mrinal
	 * @date 12/09/2019
	 */

	public void customPageInEditMode() {
		boolean flag;
		clickOn(ScreeningOfCustomLabel);
		_normalWait(800);
		if (getAttributeValueOfElement(customPageContainer, "class").contains("edit")) {
			flag = true;
		} else {
			flag = false;
		}
		reportInfo();
		Assert.assertTrue(flag);
	}

	/** Edit Subject Description */

	public void editSubjectDescription(String newStatus, String screenedStatus, String screenFailedStatus,
			String enrolledStatus, String completedStatus, String discontinueStatus) {

		clickOn(ScreeningOfCustomLabel);
		waitForElement(subjectNewStatusDescription);
		subjectNewStatusDescription.clear();
		inputText(subjectNewStatusDescription, newStatus);
		subjectScreenedStatusDescription.clear();
		_normalWait(1000);
		inputText(subjectScreenedStatusDescription, screenedStatus);
		subjectScreenFailedStatusDescription.clear();
		_normalWait(1000);
		inputText(subjectScreenFailedStatusDescription, screenFailedStatus);
		subjectEnrolledStatusDescription.clear();
		_normalWait(1000);
		inputText(subjectEnrolledStatusDescription, enrolledStatus);
		subjectCompletedStatusDescription.clear();
		_normalWait(1000);
		inputText(subjectCompletedStatusDescription, completedStatus);
		subjectDiscontinueStatusDescription.clear();
		_normalWait(1000);
		inputText(subjectDiscontinueStatusDescription, discontinueStatus);
		clickOn(saveIcon);
		_normalWait(1000);
		clickOn(homeIcon);

	}

	/** Empty Subject Description */

	public void emptySubjectDescription() {
		clickOn(ScreeningOfCustomLabel);
		subjectNewStatusDescription.clear();
		subjectScreenedStatusDescription.clear();
		subjectScreenFailedStatusDescription.clear();
		subjectEnrolledStatusDescription.clear();
		subjectCompletedStatusDescription.clear();
		subjectDiscontinueStatusDescription.clear();
		_normalWait(1000);
		clickOn(saveIcon);
		clickOn(homeIcon);

	}

	/** Set Subject Status Description To Default */

	public void setSubjectDescriptionToDefault() {

		clickOn(ScreeningOfCustomLabel);
		waitForElement(subjectNewStatusDescription);

		for (WebElement subjectStatus : subjectStatusDescriptionINPLIST) {
			inputText(subjectStatus, "Auto_" + " " + generateRandomString(4));
		}
		for (WebElement resetButton : subjectStatusDescriptionResetBTN) {
			clickOn(resetButton);
			_normalWait(1000);
		}
		clickOn(saveIcon);
		_normalWait(1000);
		clickOn(homeIcon);

	}

	/** Enter the Screening Number, Randomization Number and Temporary ID */
	public void inputScreeningRandomizationAndTempID(String screeningNum, String randomizationNum, String tempID) {
		waitForElement(subjectFieldFormatHeader);
		waitAndClick(subjectFieldFormatHeader);
		inputText(subjectFieldFormatScreeningNumINP, screeningNum);
		inputText(subjectFieldFormatRandomizationNumINP, randomizationNum);
		inputText(subjectFieldFormatTemporaryIDINP, tempID);
		waitAndClick(saveIcon);
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[@data-ng-show='ctx.isBusy' and @class='ng-isolate-scope']")));

	}

	/** Verify the entered Subject Field Format are as configured */
	public void verifyFieldFormatsAreSameAsConfigured(String screeningNumFormat, String randomizationNumFormat,
			String tempIDFormat) {

		clickOn(ScreeningOfCustomLabel);
		Assert.assertTrue((subjectFieldFormatScreeningNumTXT.getAttribute("innerHTML")).contains(screeningNumFormat));
		Assert.assertTrue(
				(subjectFieldFormatRandomizationNumTXT.getAttribute("innerHTML")).contains(randomizationNumFormat));
		Assert.assertTrue((subjectFieldFormatTemporaryIDTXT.getAttribute("innerHTML")).contains(tempIDFormat));

	}

	/**
	 * Verify Login on different browser with user having canManageSubjects claim
	 */
	public void verifyLaunchAppInDifferentBrowser() {

		switchToTab();
	}

	/**
	 * Click on Auto Generate Check Box to check the checkbox
	 */
	public void checkAutoGenerateCheckBox() {
		waitForElement(subjectFieldFormatHeader);
		waitAndClick(subjectFieldFormatHeader);
		if (autoGenerateTemporaryIDCHKBOX.isSelected() == false) {
			autoGenerateTemporaryIDCHKBOX.click();
		}
	}

	/**
	 * Click on Auto Generate Check Box to Un check the checkbox
	 */
	public void uncheckAutoGenerateCheckBox() {
		clickOn(subjectFieldFormatHeader);
		if (autoGenerateTemporaryIDCHKBOX.isSelected() == true) {
			autoGenerateTemporaryIDCHKBOX.click();
		}
	}

	/**
	 * Click on Temporary Id Check Box to check the checkbox
	 */
	public void checkTemporaryIdCheckBox() {
		clickOn(subjectFieldFormatHeader);
		if (temporaryIdCHKBOX.isSelected() == false) {
			temporaryIdCHKBOX.click();
		}
	}

	/**
	 * Click on temporary Id Check Box to Uncheck the checkbox
	 */
	public void uncheckTemporaryIdCheckBox() {
		clickOn(subjectFieldFormatHeader);
		if (temporaryIdCHKBOX.isSelected() == true) {
			temporaryIdCHKBOX.click();
		}
	}

	/**
	 * Click on Randomization Number Check Box to check the checkbox
	 */
	public void checkRandomizationNumberCheckBox() {
		clickOn(subjectFieldFormatHeader);
		if (randomizationNumberCHKBOX.isSelected() == false) {
			randomizationNumberCHKBOX.click();
		}
	}

	/**
	 * Click on Randomization Number Check Box to Uncheck the checkbox
	 */
	public void uncheckRandomizationNumberCheckBox() {
		clickOn(subjectFieldFormatHeader);
		if (randomizationNumberCHKBOX.isSelected() == true) {
			randomizationNumberCHKBOX.click();
		}
	}

	/**
	 * verify Date Of Birth Check Box Unselected
	 * 
	 * @author Mrinal
	 * @date 12/09/2019
	 */
	public void verifyDateOfBirthCHKBOXUnselected() {
		moveToElement(dateOfBirthCHKBOX);
		Assert.assertFalse(dateOfBirthCHKBOX.isSelected());
	}

	/**
	 * Verify date Of Birth Check Box to displayed
	 * 
	 * @author Mrinal
	 * @date 16/09/2019
	 */
	public void verifyDateOfBirthCheckBoxDisplayed() {
		moveToElement(dateOfBirthCHKBOX);
		Assert.assertTrue(isElementDisplayed(dateOfBirthCHKBOX));
	}
	
	/**
	 * 
	 * verify date Of Birth check box Selected
	 */
	public void verifyDateOfBirthCheckBoxIsSelected()
	{
		
		moveToElement(dateOfBirthCHKBOX);
		Assert.assertTrue(dateOfBirthCHKBOX.isSelected());
	}

	/**
	 * Click on date Of Birth CHKBOX to check the check-box.
	 * 
	 * @author Mrinal
	 * @date 16/09/2019
	 */
	public void checkDateOfBirthCheckBox() {
		if (dateOfBirthCHKBOX.isSelected() == false) {
			dateOfBirthCHKBOX.click();
		}
	}

	/**
	 * Verify year Of Birth Check Box to displayed
	 * 
	 * @author Mrinal
	 * @date 12/09/2019
	 */
	public void verifyYearOfBirthCHKBOXDisplayed() {
		moveToElement(yearOfBirthCHKBOX);
		Assert.assertTrue(isElementDisplayed(yearOfBirthCHKBOX));
	}

	/**
	 * Click on year Of Birth CHKBOX to check the checkbox
	 * 
	 * @author Mrinal
	 * @date 12/09/2019
	 */
	public void checkYearOfBirthCHKBOXCheckBox() {
		if (yearOfBirthCHKBOX.isSelected() == false) {
			yearOfBirthCHKBOX.click();
		}
		
	}
	
	/****
	 * @param Unselect Year Of Year CheckBox
	 * 
	 */
	public void unselectYearOfBirthChkBx()
	{
		customPageInEditMode();
		waitAndClick(yearOfBirthCHKBOX);
		
	}

	/**
	 * Verify year Of Birth CHKBOX Selected
	 * 
	 * @author Mrinal
	 * @date 13/09/2019
	 */
	public void verifyYearOfBirthCHKBOXSelected() {
		moveToElement(yearOfBirthCHKBOX);
		Assert.assertTrue(yearOfBirthCHKBOX.isSelected());
	}
	
	/******
	 * @param year of Birth chkBOX UnSelected
	 */
	
	public void verifyYearOfBirthCHKBOXUnselected() {
		moveToElement(yearOfBirthCHKBOX);
		Assert.assertFalse(yearOfBirthCHKBOX.isSelected());
	}

	/**
	 * Click on save button to apply the changes
	 * @author Mrinal
	 * @date 16/6/2019
	 */
	public void clickOnSaveBtn() {
		if (elementStatus(saveIcon))
			moveToElement(saveIcon);
			clickOn(saveIcon);
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']//div[@class='spinner']")));
		reportInfo();
	}

	/** Enter the Temporary ID Format */
	public void inputTempID(String tempID) {
		clickOn(subjectFieldFormatHeader);
		inputText(subjectFieldFormatTemporaryIDINP, tempID);
	}

	public void editSubjectStatusAliasField(String renameAlias) {
		clickOn(ScreeningOfCustomLabel);
		for (WebElement subjectStatusAlias : subjectAliasFieldINP) {
			inputText(subjectStatusAlias, renameAlias + "-" + generateRandomString(3));
		}

	}

	public void verifySubjectRenameStatus(String renameAlias) {

		for (WebElement customRenameStatus : subjectCustomRenameStatusTxt) {
			Assert.assertTrue(customRenameStatus.getText().contains(renameAlias));
		}

	}

	public String getSubjectAliasStatusList(int statusIndex) {
		List<String> statusSubjectAliasList = new ArrayList<>();
		for (int i = 0; i < subjectCustomRenameStatusTxt.size(); i++) {
			String renameStatus = subjectCustomRenameStatusTxt.get(i).getText();
			statusSubjectAliasList.add(renameStatus);
		}
		String statusForRenameSubjectAlias = statusSubjectAliasList.get(statusIndex);
		return statusForRenameSubjectAlias;
	}

	public void navigateToHome() {
		selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.HomeNavText);
	}

	public void clearCustomSubjectStatus() {
		clickOn(ScreeningOfCustomLabel);
		for (WebElement subjectStatusAlias : subjectAliasFieldINP) {
			subjectStatusAlias.clear();
		}

	}

	/** Enter the Screening Number */
	public void inputScreening(String screeningNum) {
		moveToElement(subjectFieldFormatHeader);
		clickOn(subjectFieldFormatHeader);
		inputText(subjectFieldFormatScreeningNumINP, screeningNum);
		clickOn(saveIcon);

	}

	/* Fill ScreenFailed Alias For Subject */
	public void editSubjectAlias(String subjectStatusToBeModify, String aliasName) {
		clickOn(ScreeningOfCustomLabel);
		WebElement aliasList = driver.findElement(By.xpath(
				"//label[text()='" + subjectStatusToBeModify + "']//parent::div//following-sibling::div//input"));
		aliasList.clear();
		inputText(aliasList, aliasName);
		clickOn(saveIcon);
		selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.HomeNavText);
	}

	/* Clear ScreenFailed Alias For Subject */

	public void clearSubjectAlias(String subjectStatusToBeModify) {
		clickOn(ScreeningOfCustomLabel);
		WebElement aliasList = driver.findElement(By.xpath(
				"//label[text()='" + subjectStatusToBeModify + "']//parent::div//following-sibling::div//input"));
		aliasList.clear();
		clickOn(saveIcon);
		selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.HomeNavText);
	}

	/*****
	 * Verify Custom tab in the edit mode
	 */

	public void verifyCustomTabInTheEditMode() {
		boolean flag = false;
		if (isElementDisplayed(saveButtonUnderStudiesCustomtab)) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	/***
	 * Temporary ID and "Auto-generate Temporary ID" option selected.
	 */
	public void verifyTemporaryIDOptionsSelected() {
		Assert.assertTrue(temporaryIdCHKBOX.isSelected());

	}

	/**
	 * 
	 * Verify Temporary ID field is required and marked in red.
	 */
	public void verifyTempIDFieldRequiredAndMarked(String HiglightWithColor) {
		Assert.assertTrue(subjectFieldFormatTemporaryIDINP.getAttribute("required").equalsIgnoreCase("true"));
		subjectFieldFormatTemporaryIDINP.getCssValue("background-color").contains(HiglightWithColor);
		moveToElement(subjectFieldFormatTemporaryIDINP);
		reportInfo();
	}

	/**
	 * click on the Temporary ID field And Verify hint for the Temporary ID masking
	 * format displayed
	 */
	public void clickOnTemporaryIDFieldAndVerifyHintForMaskingFormatDisplayed() {
		clickOn(subjectFieldFormatTemporaryIDINP);
		Assert.assertTrue(isElementDisplayed(hintBlockForMaskingFormat));
		reportInfo();
	}

	/** clear value Temporary ID text box */

	public void clearTempID() {
		clearTextBox(subjectFieldFormatTemporaryIDINP);
		reportInfo();
	}

	/**
	 * Verify "Auto-generate Temporary ID" option appeared
	 */

	public void verifyAutoGenerateCheckBoxAppeared() {
		Assert.assertEquals(autoGenerateTemporaryIDCHKBOX
				.findElement(By.xpath("./ancestor::div[@data-on-change='onAutogenerateTemporaryIdChanged(value)']"))
				.getAttribute("class"), "ng-isolate-scope");
		reportInfo();
	}

	/**
	 * Verify "Auto-generate Temporary ID" option Not appeared
	 */
	public void verifyAutoGenerateCheckBoxNotAppeared() {
		Assert.assertEquals(autoGenerateTemporaryIDCHKBOX
				.findElement(By.xpath("./ancestor::div[@data-on-change='onAutogenerateTemporaryIdChanged(value)']"))
				.getAttribute("class"), "ng-isolate-scope ng-hide");
		reportInfo();
	}

	/**
	 * Click on cancel button
	 */
	public void clickOnCancelBtn() {
		clickOn(cancelIcon);
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']//div[@class='spinner']")));
		reportInfo();
	}

	/**
	 * Verify Temporary Id Check Box is not checked
	 */
	public void verifyTempIDCheckBoxNotChecked() {
		boolean flag = false;
		if (temporaryIdCHKBOX.isSelected() == false) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/** Verify confirmation Modal PopUp displayed */
	public void verifyConfirmationModalPopUpDisplayed() {
		boolean flag = false;
		waitForElement(confirmationModalPopUp);
		if (confirmationModalPopUp.findElement(By.xpath("./ancestor::div[@id='queryConfirmation']"))
				.getAttribute("class").contains("modalshow in")) {
			flag = true;
			moveToElement(confirmationModalPopUp);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Select Yes on confirmation Modal PopUp
	 */
	public void selectYesOnConfirmationModal() {
		clickOn(yesButtonOnConfirmationModalPopUp);
	}

	/**
	 * Select No on confirmation Modal PopUp
	 */
	public void selectNoOnConfirmationModal() {
		clickOn(cancelButtonOnConfirmationModalPopUp);
	}

	/**
	 * Verify Custom tab in the edit mode
	 */
	public void verifyCustomTabInEditMode() {
		boolean flag = true;
		try {
			if (cancelIcon.getAttribute("disabled").equalsIgnoreCase("true")) {
				flag = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Temporary ID field is not required
	 */
	public void verifyTempIDFieldNotRequired() {
		boolean flag = true;
		try {
			if (subjectFieldFormatTemporaryIDINP.getAttribute("required").equalsIgnoreCase("true")) {
				flag = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Edit mode is hidden
	 */
	public void verifyEditModeIsHidden() {
		boolean flag = false;
		if (cancelIcon.getAttribute("disabled").equalsIgnoreCase("true")) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyTempIDFormatDisplayed() {
		boolean flag = false;
		if (isElementDisplayed(tempIDFormat)) {
			flag = true;
		}
		Assert.assertTrue(flag);

	}

	/**
	 * Verify Temporary Id Check Box is checked
	 */
	public void verifyTempIDCheckBoxChecked() {
		boolean flag = false;
		if (temporaryIdCHKBOX.isSelected() == true) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify auto Generate Temporary Id Check Box is checked
	 */
	public void verifyAutoGenerateTemporaryIdCheckBoxIsChecked() {
		boolean flag = false;
		if (autoGenerateTemporaryIDCHKBOX.isSelected() == true) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/****
	 * Verify textBox does not contains more than 50 digits
	 */
	public void verifyTextBoxDoesContainsMoreThanMaximumValue(int sizeToBeVerify) {
		boolean flag = true;
		if (tempIDFormat.getText().length() > sizeToBeVerify) {
			flag = false;

		}
		Assert.assertTrue(flag);
	}

	/* Clear all field */
	public void clearAllField() {

		int screeningLength = subjectFieldFormatScreeningNumTXT.getAttribute("innerHTML").length();
		int tempLength = subjectFieldFormatTemporaryIDTXT.getAttribute("innerHTML").length();
		int randomLength = subjectFieldFormatRandomizationNumTXT.getAttribute("innerHTML").length();
		clickOn(ScreeningOfCustomLabel);
		if (screeningLength > 0 || tempLength > 0 || randomLength > 0) {
			clearTextBox(subjectFieldFormatTemporaryIDINP);
			clearTextBox(subjectFieldFormatRandomizationNumINP);
			clearTextBox(subjectFieldFormatScreeningNumINP);
			clickOn(saveIcon);
		} else {
			clickOn(cancelIcon);
		}
	}
	
	
	/***
	 * 
	 * @param message
	 */
	public void verifyMessage(String message)
	{
		
		WebElement msg = driver.findElement(ByLocator("//label[text()='There are unsaved changes. Do you want to continue and lose them?']"));
		String expected=msg.getText();
		String Actual=message;
		Assert.assertEquals(Actual, expected);
	}

	
	public void clickOnConfirmation(String Confirmation)
	{
		
	WebElement cnfrmtn = driver.findElement(ByLocator("//div[@id='queryConfirmation']//div[contains(text(),'"+Confirmation+"')]"));
	waitAndClick(cnfrmtn);	
	}

	/***
	 * Verify custom tab is displayed in view mode
	 */
	
	
	public void customTabIsDisplayedInViewMode() {

		moveToElement(disableCheckbox);
		String value= disableCheckbox.getAttribute("disabled");
		if(value.contains("disabled")) {
			Assert.assertTrue(true);
		}
		
	}
}
