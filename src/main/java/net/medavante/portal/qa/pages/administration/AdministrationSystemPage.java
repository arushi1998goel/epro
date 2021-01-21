package net.medavante.portal.qa.pages.administration;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationSystemPage extends BasePage {

	@FindBy(xpath = "//a[@ng-click='showDashboardManagement()']")
	private WebElement dashBoardManagementLNK;

	@FindBy(xpath = "//nav[@class='iq-nav']")
	private WebElement dashBoardManagmentPage;
	
	@FindBy(xpath="//div[@class='claims-view-right-pane row']")
	private WebElement roleManagmentPage;

	@FindBy(xpath = "//span[@ng-click='addDashboard()']")
	private WebElement addDashBoardBtn;

	@FindBy(xpath = "//input[@id='dashboard-display-name']")
	private WebElement newDashBoardDisplayNameINP;
	
	@FindBy(xpath="//div[contains(@for,'display')]")
	private WebElement newDashBoardDisplayValidationMSG; 
	
	@FindBy(xpath="//div[contains(@for,'system')]")
	private WebElement newDashBoardSystemValidationMSG;
	
	@FindBy(xpath="//p[@data-ng-if='!areAccessTypesValid(dashboardForm)']")
	private WebElement newDashBoardAccessCheckBoxValidationMSG; 
	
	@FindBy(xpath = "//input[@id='dashboard-system-name']")
	private WebElement newDashBoardSystemNameINP;

	@FindBy(xpath = "//div[@class='field-row']//label[text()='Access']/following-sibling::span/following-sibling::label[text()='Internal']")
	private WebElement newDashBoardAccessInternalCHKBOX;

	@FindBy(xpath = "//div[@class='field-row']//label[text()='Access']/following-sibling::span/following-sibling::label[text()='Study']")
	private WebElement newDashBoardAccessStudyCHKBOX;;

	@FindBy(xpath = "//div[@class='modal-footer']//button[contains(@data-ng-disabled,'!isFormValid(dashboardForm)')]")
	private WebElement newDashBoardSaveBTN;

	@FindBy(xpath = "//div[@class='modal-footer']//button//span[@class='icon-small icon-cancel']")
	private WebElement newDashBoardCancelBTN;

	@FindBy(xpath = "//div[@class='modal-header has-action']//button[@class='close']")
	private WebElement newDashBoardClosePopUpBtn;

	@FindBy(xpath = "//div[contains(@class,'grid-content')]//table//tbody/tr//td[@role='gridcell'][1]//span[@class='ng-binding']")
	private List<WebElement> existingdashBoardManagementLIST;

	public AdministrationSystemPage(WebDriver driver) {
		super(driver);
	}

	/** Verify System DashBoard Managment Page is open */
	public void verifyAdministrationSystemDashBoardManagmentPageIsOpen() {
		Assert.assertTrue(isElementPresent(dashBoardManagmentPage));
		reportInfo();
	}
	
	/** Verify System Role Managment Page is open */
	public void verifyAdministrationSystemRoleManagmentPageIsOpen() {
		Assert.assertTrue(isElementPresent(roleManagmentPage));
		reportInfo();
	}

	/** Verify Add DashBoard Pop up is open */
	public void verifyAddDashBoardPopIsDisplayed() {
		Assert.assertTrue(isElementPresent(newDashBoardDisplayNameINP));
		reportInfo();
	}
	
	/** Verify Validation message is displayed for display,System name and access checkBox  */
	public void verifyAllRequiredFieldsValidationMessagesAreDisplayed() {
		Assert.assertTrue(isElementPresent(newDashBoardDisplayValidationMSG) && isElementPresent(newDashBoardSystemValidationMSG) && isElementPresent(newDashBoardAccessCheckBoxValidationMSG));
		reportInfo();
	}

	/** click On DashBoard Management link from left panel option */
	public void clickOnDashBoardManagementOption() {
		waitAndClick(dashBoardManagementLNK);
	}

	/** click On Add DashBoard button to add the dashboard */
	public void clickOnAddDashBoard() {
		waitForElementClickable(newDashBoardSaveBTN, 5);
		waitAndClick(addDashBoardBtn);
	}

	/** Input text of display name of new dashboard */
	public void inputDashBoardDisplayName(String displayName) {
		inputText(newDashBoardDisplayNameINP, displayName);
		reportInfo();
	}

	/** Input text of system name of new dashboard */
	public void inputDashBoardSystemName(String systemName) {
		inputText(newDashBoardSystemNameINP, systemName);
		reportInfo();
	}

	/** select internal checkbook for new dashboard */
	public void selectInternalCheckBox() {
		waitForElement(newDashBoardAccessInternalCHKBOX);
		waitAndClick(newDashBoardAccessInternalCHKBOX);
	}

	/** click On save button to add the dashboard */
	public void clickOnSaveBtn() {
		waitForElement(newDashBoardSaveBTN);
		waitAndClick(newDashBoardSaveBTN);
		_normalWait(3000);
	}

	
	public void verifySaveButtonIsDisabled() {
		Assert.assertFalse("Save Button should Disabled", newDashBoardSaveBTN.isEnabled());
		reportInfo();
	}
	/** click On cancel button to cancel the add dashboard popup */
	public void clickOnCancelBtn() {
		waitForElement(newDashBoardCancelBTN);
		waitAndClick(newDashBoardCancelBTN);
	}

	/** click On close popup button to close the add dashboard popup */
	public void closeAddNewDashBoardPopUP() {
		waitForElement(newDashBoardClosePopUpBtn);
		waitAndClick(newDashBoardClosePopUpBtn);
	}

	/** Verify new added dashboard has been added in the list */
	public void verifyDashBoardIsAdded(String dashBoardName) {
		boolean flag = false;
		for (WebElement dashBoardListDisplayName : existingdashBoardManagementLIST) {
			if (dashBoardName.equalsIgnoreCase(getText(dashBoardListDisplayName))) {
				flag=true; 
				break;
			}else {
				continue; 
			}

		}
		reportInfo();
		Assert.assertTrue(dashBoardName+ " Added in the dashboard management list",flag);
		

	}

	/** Verify new added dashboard has not been added in the list */
	public void verifyDashBoardIsNotAdded(String dashBoardName) {
		boolean flag = true;
		for (WebElement dashBoardListDisplayName : existingdashBoardManagementLIST) {
			if (dashBoardName.equalsIgnoreCase(getText(dashBoardListDisplayName))) {
				flag=false; 
				break;
			}else {
				continue; 
			}
		}
		Assert.assertTrue(dashBoardName+ " Not Added in the dashboard management list",flag); 
		reportInfo();

	}

}
