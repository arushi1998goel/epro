package net.medavante.portal.qa.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesAnalyticsPage extends BasePage {

	@FindBy(xpath = "//div[@class='action-heading-frame']//span[contains(text(),'Add Dashboard')]")
	private WebElement addDashBoardText;

	@FindBy(xpath = "//div[@class='action-heading-frame']//*[@class='btn circle-button btn-white']")
	private WebElement addDashBoardBTN;

	@FindBy(xpath = "//div[contains(@class,'modal-dialog')]//strong")
	private WebElement addDashBoardTitleTXT;

	@FindBy(xpath = "//div[@id='dashboard-dropdownlist']")
	private WebElement addDashBoardTypeDRPDOWN;

	@FindBy(xpath = "//div[@id='dashboard-dropdownlist']//span[@data-ng-show='displayValue']")
	private WebElement selectedDashBoardTXT;

	@FindBy(xpath = "//div[@id='dashboard-dropdownlist']//ul//li/span")
	private List<WebElement> dashboardLIST;

	@FindBy(xpath = "//div[@id='dashboard-version-dropdownlist']")
	private WebElement addDashBoardVersionDRPDOWN;

	@FindBy(xpath = "//div[@id='dashboard-version-dropdownlist']//span[@data-ng-show='displayValue']")
	private WebElement selectedVersionTXT;

	@FindBy(xpath = "//div[@id='dashboard-version-dropdownlist']//div//ul//li//span")
	private List<WebElement> versionLIST;

	@FindBy(xpath = "//div[contains(@for,'dashboard-drop')]")
	private WebElement addDashBoardValidationMSG;

	@FindBy(xpath = "//div[contains(@for,'dashboard-role')]")
	private WebElement addDashBoardRoleValidationMSG;

	@FindBy(xpath = "//input[@id='dashboard-alias-textbox']")
	private WebElement aliasINP;

	@FindBy(xpath = "//div[@id='dashboard-activation-date-datepicker']")
	private WebElement activationDateINP;

	@FindBy(xpath = "//div[@id='dashboardDeactivationDateDatepicker']")
	private WebElement deActivationDateINP;

	@FindBy(xpath = "//div[@id='dashboardDeactivationDateDatepicker']//a[@class='add-on icon-calendar datepickerbutton']")
	private WebElement deactivationDatePickerBTN;

	@FindBy(xpath = "//div[contains(@style,'display: block; ')]//div[@class='datepicker-days']//tr//th[@class='next']")
	private WebElement calendarNextMonthSelectorIcon;

	@FindBy(xpath = "//div[@id='dashboardDeactivationDateDatepicker']//div//label")
	private WebElement deactivationDateTXT;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//tbody//td[@class='day']")
	private List<WebElement> activeDateList;

	@FindBy(xpath = "//div[contains(@class,'multiselect k-header')]")
	private WebElement roleAccessDrpDwn;

	@FindBy(xpath = "//div[contains(@class,'multiselect k-header')]//ul[@role='listbox']/li/span[1]")
	private List<WebElement> selectedRoleAccessTXT;

	@FindBy(xpath = "//ul[@id='dashboard-role-access-select_listbox']//li//label")
	private List<WebElement> roleAccessList;

	@FindBy(xpath = "//div[@class='modal-header has-action']//button")
	private WebElement closePopUpBtn;

	@FindBy(xpath = "//div[@class='modal-footer']//button[@data-ng-click='close()']")
	private WebElement cancelBTN;

	@FindBy(xpath = "//div[@class='modal-footer']//button[@data-ng-click='save()']")
	private WebElement saveBTN;

	@FindBy(xpath = "//div[@id='study-dashboards-grid']//div[contains(@class,'content')]//tbody/tr//td[1]//span[@class='ng-binding']")
	private List<WebElement> selectedDashboardList;

	@FindBy(xpath = "//div[@data-ng-click='okClick()']")
	private WebElement confirmPopUpYesBTN;

	@FindBy(xpath = "//div[@id='study-dashboards-grid']//tbody/tr")
	private List<WebElement> configuredDashBoardLST;

	public AdministrationStudiesAnalyticsPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Delete all the existing configured dashboard to set the study as per test
	 * case pre-requisite
	 * 
	 */
	public void deleteConfiguredDashBoard() {
		if (configuredDashBoardLST.size() > 0) {
			for (int totalDashboard = 0; totalDashboard <= configuredDashBoardLST.size() - 1; totalDashboard++) {
				_normalWait(DEFAULT_WAIT_4_ELEMENT);
				configuredDashBoardLST = driver.findElements(By.xpath("//div[@id='study-dashboards-grid']//tbody/tr"));
				moveToElement(configuredDashBoardLST.get(0));
				waitAndClick(configuredDashBoardLST.get(0)
						.findElement(By.xpath(".//span[@class='centered-btns']//button[@title='Delete']")));
				clickOn(confirmPopUpYesBTN);
				spinnerBecomeInvisible();
			}
		}
	}

	/** Click on add dashboard button **/

	public void clickOnAddDashBoardBTN() {
		clickOn(addDashBoardBTN);
		_normalWait(3000);
	}

	/** Select dashboard option from the list **/

	public void selectDashBoard(String dashBoardListToBeSelected) {
		clickOn(addDashBoardTypeDRPDOWN);
		for (WebElement dashBoardName : dashboardLIST) {
			if (getText(dashBoardName).equalsIgnoreCase(dashBoardListToBeSelected)) {
				waitForElement(dashBoardName);
				clickOn(dashBoardName);
				break;
			}
		}
	}

	/** Select version option from the list **/
	public void selectVersion(String versionToBeSelected) {
		clickOn(addDashBoardVersionDRPDOWN);
		for (WebElement versionNum : versionLIST) {
			if (getText((versionNum)).trim().equalsIgnoreCase(versionToBeSelected)) {
				clickOn(versionNum);
				break;
			}
		}
	}

	/** Select role access option from the list **/
	public void selectRoleAccess(String accessToBeSelected) {
		clickOn(roleAccessDrpDwn);
		_normalWait(3000);
		for (WebElement accessName : roleAccessList) {
			if (getText((accessName)).trim().contains(accessToBeSelected)) {
				scrollPageThroughWebElement(accessName);
				moveToElement(accessName);
				javascriptButtonClick(accessName);
				break;
			}
		}
		aliasINP.click();
	}

	/** Select deactivation date from calendar **/

	public void selectDeactivationDate() {
		clickOn(deactivationDatePickerBTN);
		if (activeDateList.size() == 0 || activeDateList.size() < Integer.parseInt(dateCalendarRef.getFutureDate())) {
			calendarNextMonthSelectorIcon.click();
			clickOn(activeDateList.get(1));
		} else {
			for (WebElement activeDate : activeDateList) {
				if (getText(activeDate).equalsIgnoreCase(dateCalendarRef.getFutureDate())) {
					clickOn(activeDate);
					break;
				}
			}
		}
	}

	/** click on cancel button to discard the selected option **/

	public void clickOnCancelBTN() {
		waitForElement(cancelBTN);
		clickOn(cancelBTN);
	}

	/** click on save button to save the changes **/

	public void clickOnSaveBTN() {
		_normalWait(1000);
		clickOn(saveBTN);
		_normalWait(3000);
	}

	/** click On close popup button to close the add dashboard popup */

	public void closeAddNewDashBoardPopUP() {
		waitForElement(closePopUpBtn);
		waitAndClick(closePopUpBtn);
	}

	/** Verify save button is disabled **/

	public void verifySaveButtonIsDisabled() {
		Assert.assertFalse(saveBTN.isEnabled(), "Save Button should Disabled");
		reportInfo();
	}

	/** Verify Selected dashboard has been added **/
	public void verifySelectedDashBoardIsSaved(String verifyDashboardName) {
		boolean flag = false;
		for (WebElement dashboardName : selectedDashboardList) {
			if (getText(dashboardName).trim().equalsIgnoreCase(verifyDashboardName)) {
				flag = true;
				break;
			} else {
				continue;
			}
		}
		reportInfo();
		Assert.assertTrue(flag, verifyDashboardName + " Added in the dashboard management list");
	}

	/** Verify Selected dashboard has not been added **/
	public void verifySelectedDashBoardIsNotSaved(String verifyDashboardName) {
		boolean flag = false;
		for (WebElement dashboardName : selectedDashboardList) {
			if (getText(dashboardName).trim().equalsIgnoreCase(verifyDashboardName)) {
				flag = true;
				break;
			} else {
				continue;
			}
		}
		reportInfo();
		Assert.assertFalse(flag, verifyDashboardName + " is added in analytics dashboard management list");
	}

	/** Verify Analytics page is displayed **/

	public void verifyAnalyticsPageIsDisplayed() {
		Assert.assertTrue(isElementPresent(addDashBoardText));
		reportInfo();
	}

	/** Verify save button is Eanbled **/

	public void verifySaveButtonIsEnabled() {
		Assert.assertTrue(saveBTN.isEnabled(), "Save Button should Enabled");
		reportInfo();
	}

	/** Verify Version Is Selected from the list **/
	public void verifyVersionIsSelected(String selectedVersion) {
		Assert.assertEquals(getText(selectedVersionTXT), selectedVersion);
		reportInfo();
	}

	/** Verify Alias Is Selected by default **/
	public void verifyAliasIsSelectedByDefaulted() {
		String toenable = "document.getElementById('dashboard-alias-textbox').removeAttribute('disabled');";
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript(toenable);
		_normalWait(3000);

	}

	/** Verify Deactivation Date Is Filled **/
	public void verifyDeactivationIsFilled() {
		Assert.assertTrue(getText(deactivationDateTXT).length()>0,"Deactivation Field is not selected");
		reportInfo();
	}

	/** Verify Role access fields are filled in **/
	public void verifyRoleAccessIsFilled(String SelectedRoleAccess) {
		boolean flag = false;
		for (WebElement roleAccessText : selectedRoleAccessTXT) {
			if (getText(roleAccessText).contains(SelectedRoleAccess)) {
				flag = true;
				break;
			}

		}
		reportInfo();
		Assert.assertTrue(flag, SelectedRoleAccess + " Is not selected in role access field");
	}

	/** Verify dashboard Is Selected from the list **/
	public void verifyDashBoardIsSelected(String selectedDashBoard) {
		Assert.assertEquals(getText(selectedDashBoardTXT), selectedDashBoard);
		reportInfo();
	}

	/**
	 * verify The Dashboard drop-down list displayed with Dashboards that have
	 * not been added to the study yet
	 */
	public void verifySelectedDashBoardNotInDropDown(String selectedDashboard) {
		boolean flag = false;
		clickOn(addDashBoardTypeDRPDOWN);
		for (WebElement dashBoardName : dashboardLIST) {
			if (getText(dashBoardName).equalsIgnoreCase(selectedDashboard)) {
				flag = true;
			}
		}
		reportInfo();
		Assert.assertFalse(flag, selectedDashboard + " should not in list as it already selected");
		clickOn(addDashBoardTypeDRPDOWN);
	}

	/** Verify validation (Mandatory) Fields message is displayed **/

	public void verifyAllRequiredFieldsValidationMessagesAreDisplayed() {
		Assert.assertTrue(isElementPresent(addDashBoardValidationMSG));
		Assert.assertTrue(isElementPresent(addDashBoardRoleValidationMSG));
		reportInfo();
	}

	/** Verify add dashboard popup is displayed **/

	public void verifyAddDashBoardPopUpIsDisplayed() {
		Assert.assertTrue(addDashBoardTitleTXT.isDisplayed(), "Add DashBoard PopUp Text should Displayed");
		Assert.assertTrue(closePopUpBtn.isDisplayed());
		Assert.assertTrue(isElementPresent(addDashBoardTypeDRPDOWN),
				"DashBoard drop down should Displayed to select the value");
		Assert.assertTrue(isElementPresent(addDashBoardVersionDRPDOWN),
				"DashBoard version drop down should Displayed to select the value");
		Assert.assertTrue(aliasINP.isDisplayed(), "Alias input field should Displayed");
		Assert.assertTrue(activationDateINP.isDisplayed(), "DashBoard Activation date should Displayed");
		Assert.assertTrue(deActivationDateINP.isDisplayed(), "DashBoard De Activation date should Displayed");
		Assert.assertTrue(roleAccessDrpDwn.isDisplayed(),
				"DashBoard role access drop down should Displayed to select the value");
		Assert.assertTrue(saveBTN.isDisplayed() && cancelBTN.isDisplayed());
		reportInfo();
	}

	/** Verify add dashboard popup is not displayed **/
	public void verifyAddDashBoardPopUpIsNotDisplayed() {
		Assert.assertFalse(addDashBoardTitleTXT.isDisplayed(), "Add DashBoard PopUp should closed");
		Assert.assertFalse(addDashBoardTypeDRPDOWN.isDisplayed(), "Add DashBoard PopUp should closed");
		Assert.assertFalse(addDashBoardVersionDRPDOWN.isDisplayed(), "Add DashBoard PopUp should closed");
		Assert.assertFalse(aliasINP.isDisplayed(), "Add DashBoard PopUp should closed");
		Assert.assertFalse(activationDateINP.isDisplayed(), "Add DashBoard PopUp should closed");
		Assert.assertFalse(deActivationDateINP.isDisplayed(), "Add DashBoard PopUp should closed");
		Assert.assertFalse(roleAccessDrpDwn.isDisplayed(), "Add DashBoard PopUp should closed");
	}

	/** Verify Add DashBoard Button **/
	public void verifyAddDashBoardButtonIsDisabled() {
		Assert.assertFalse(addDashBoardBTN.isEnabled(), "Dashboard Button should disabled");
		reportInfo();
	}
}
