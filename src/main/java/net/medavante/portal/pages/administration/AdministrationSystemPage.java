package net.medavante.portal.pages.administration;


import java.util.List;

import org.openqa.selenium.By;
//import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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
	
	@FindBy(xpath = "//tr[contains(@class,'row-opened')]//td[7]//a[@title='Edit']")
	private WebElement SelectedDashboardEditButton;
	
	@FindBy(xpath = "//tr[contains(@class,'row-opened')]//td[7]//a[@title='Delete']")
	private WebElement SelectedDashboardDeleteButton;
	
	@FindBy(xpath = "//tr[contains(@class,'ng-scope')]//td[4]//a[@title='Edit']")
	private WebElement VersionEditButton;
	
	@FindBy(xpath = "//tr[contains(@class,'ng-scope')]//td[4]//a[@title='Delete']")
	private WebElement VersionDeleteButton;
	
	
	
	
	 public AdministrationSystemPage(WebDriver driver) {
		super(driver);
	}

	/** Verify System DashBoard Managment Page is open */
	public void verifyAdministrationSystemDashBoardManagmentPageIsOpen() {
		_normalWait(2000);
		moveToElement(dashBoardManagmentPage);
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
		waitForElementToBecomeInvisible(By.xpath("//div[@class='ng-isolate-scope' and @data-ng-show='isBusy']//div[@class='spinner']"));
        waitAndClick(dashBoardManagementLNK);
        _normalWait(2000);
	}

	/** click On Add DashBoard button to add the dashboard */
	public void clickOnAddDashBoard() {
		waitForElementClickable(newDashBoardSaveBTN, 3);
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
		waitForElementToBecomeInvisible(By.xpath("//div[@class='ng-isolate-scope' and @data-ng-show='isBusy']//div[@class='spinner']"));
	}

	
	public void verifySaveButtonIsDisabled() {
		Assert.assertFalse(newDashBoardSaveBTN.isEnabled(), "Save Button should Disabled");
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
		Assert.assertTrue(flag,dashBoardName+ " Added in the dashboard management list");
		

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
		Assert.assertTrue(flag,dashBoardName+ " Not Added in the dashboard management list"); 
		reportInfo();

	}
	
	/**
	 * This method will select the item type from the Radio Button
	 * @param item
	 * @author siddharth
	 * @date 04/09/2019
	 */
	public void selectItemTypeRadioButton(String item) {
		waitAndClick("css=input[id='"+item+"-button']");
		reportInfo();
	}
	

	/***
	 * Verify that dashboard version displayed with following attributes
	 * @param versionname
	 * @param activation
	 * @param deactivation
	 * @param noversionadded
	 */
	public void verifyVersionInformationIsDispalyed(String versionname, String activation, String deactivation,
			String noversionadded) {
		moveToElement(getDriver().findElement(By.xpath("//th[text()='"+versionname+"']")));
		Assert.assertTrue(getDriver().findElement(By.xpath("//th[text()='"+versionname+"']")).isDisplayed());
		
		moveToElement(getDriver().findElement(By.xpath("//th[text()='"+activation+"']")));
		Assert.assertTrue(getDriver().findElement(By.xpath("//th[text()='"+activation+"']")).isDisplayed());
		
		moveToElement(getDriver().findElement(By.xpath("//th[text()='"+deactivation+"']")));
		Assert.assertTrue(getDriver().findElement(By.xpath("//th[text()='"+deactivation+"']")).isDisplayed());
		
		
		moveToElement(getDriver().findElement(By.xpath("//div[text()='"+noversionadded+"']")));
		Assert.assertTrue(getDriver().findElement(By.xpath("//div[text()='"+noversionadded+"']")).isDisplayed());
		
	}

	/***
	 * Select add version control
	 */
	@FindBy(xpath = "//a[@title='Add Version']")
	private WebElement addVersionControl;
	
	public void selectAddVersionButton() {

		moveToElement(addVersionControl);
		waitAndClick(addVersionControl);
	}
	
	/***
	 * Section to add new version is displayed
	 */
	
	@FindBy(xpath = "//h4[@class='modal-title ng-binding ng-scope']")
	private WebElement addNewVersionPopUpHeading;
	
	@FindBy(xpath = "//label[text()='Dashboard Version Name']/../input")
	private WebElement versionNameInput;
	
	@FindBy(xpath = "//label[text()='Dashboard Reference']/..//div")
	private WebElement dashBoardReference;
	
	@FindBy(xpath = "//label[text()='Activation Date']/..//span//input")
	private WebElement activationDate;
	
	@FindBy(xpath = "//label[text()='Deactivation Date']/..//span//input")
	private WebElement deActivationDate;
	
	@FindBy(xpath = "(//div[@class='modal-dialog'])[2]//div[@class='modal-footer']//button[@class='btn btn-default']")
	private WebElement addDasboardCancelButton;
	
	@FindBy(xpath = "(//div[@class='modal-dialog'])[2]//div[@class='modal-footer']//button[@class='btn btn-active']")
	private WebElement addDasboardSaveButton;
	

	public void verifyAddNewVersionPopInformation() {
       moveToElement(addNewVersionPopUpHeading);
	   Assert.assertTrue(addNewVersionPopUpHeading.isEnabled());
	   
	   moveToElement(versionNameInput);
	   Assert.assertTrue(versionNameInput.isEnabled());
	   
	   moveToElement(dashBoardReference);
	   Assert.assertTrue(dashBoardReference.isEnabled());
	   
	   moveToElement(activationDate);
	   Assert.assertTrue(activationDate.isEnabled());
	   
	   moveToElement(deActivationDate);
	   Assert.assertTrue(deActivationDate.isEnabled());
	   
	   moveToElement(addDasboardCancelButton);
	   Assert.assertTrue(addDasboardCancelButton.isEnabled());
	   
	   moveToElement(addDasboardSaveButton);
	   Assert.assertTrue(addDasboardSaveButton.isDisplayed());
	   
	   
	}

	/***
	 */
	public void cancelAddDashboardPopUp() {

		moveToElement(addDasboardCancelButton);
		waitAndClick(addDasboardCancelButton);
	}

	public void clickOnExpandIconOnAddedDashboard(String automationDashboard) {

		moveToElement(getDriver().findElement(By.xpath("(//span[text()='"+automationDashboard+"'])[2]/../../..//..//td[@class='k-hierarchy-cell']")));
		waitAndClick(getDriver().findElement(By.xpath("(//span[text()='"+automationDashboard+"'])[2]/../../..//..//td[@class='k-hierarchy-cell']")));
	}

	/***
	 * Select DashBoard
	 */
	public void moveOnExistingDashboard(String DashboardName) {
		
		scrollIntoView(getDriver().findElement(By.xpath("//span[text()='"+DashboardName+"']/following-sibling::span")));
		moveToElement(getDriver().findElement(By.xpath("//span[text()='"+DashboardName+"']/following-sibling::span")));
		
	}
	
	/***
	 * Verify add Dashboard button is displayed
	 */

	public void verifyAddDashboradButtonisDisplayed() {
		 Assert.assertTrue(addDashBoardBtn.isDisplayed());
		 moveToElement(addDashBoardBtn);
		
	}

	/***
	 * Verify edit Dashboard button is displayed
	 */

	public void verifyEditDashboardActionIsDisplayed() {
		Assert.assertTrue(SelectedDashboardEditButton.isDisplayed());
		 moveToElement(SelectedDashboardEditButton);
		
	}
	/***
	 * Verify Delete Dashboard button is displayed
	 */

	public void verifyDeleteDashboardActionIsDisplayed() {
		Assert.assertTrue(SelectedDashboardDeleteButton.isDisplayed());
		 moveToElement(SelectedDashboardDeleteButton); 
	}
	/***
	 * Verify Add version button is displayed
	 */

	public void verifyAddVersionButtonIsDisplayed() {
		Assert.assertTrue(addVersionControl.isDisplayed());
		 moveToElement(addVersionControl);
	}
	/***
	 * Verify  version 
	 */
	
	public void VerifyVersionIsDisplayed(String versionname) {
		Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='"+versionname+"']")).isDisplayed());
		moveToElement(getDriver().findElement(By.xpath("//span[text()='"+versionname+"']")));
		
	}

	/***
	 * Verify  version edit button
	 */
	
	public void verifyVersionEditButton() {
		_normalWait(2000);
		Assert.assertTrue(VersionEditButton.isDisplayed());
			 moveToElement(VersionEditButton);
		
	}
	/***
	 * Verify  version Delete button
	 */
	
	public void verifyVersionDeleteButton() {
		Assert.assertTrue(VersionDeleteButton.isDisplayed());
		moveToElement(VersionDeleteButton);
		 		
	}
	

	/***
	 * Verify add Dashboard button is not displayed
	 */

	public void verifyAddDashboradButtonisNotDisplayed() {
		
		boolean flag=true;
		try {
			if (addDashBoardBtn.isDisplayed()) {
				flag=true;
			}
			} catch (Exception e) {
		
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
		/***
		 * Verify edit Dashboard button is not displayed 
		 */

		public void verifyEditDashboardActionIsNotDisplayed() {
			boolean flag=true;
				try {
					if (SelectedDashboardEditButton.isDisplayed()) {
						flag=true;
					}
					} catch (Exception e) {
				}
				Assert.assertTrue(flag);
				reportInfo();
		}
		/***
		 * Verify Delete Dashboard button is not displayed
		 */

		public void verifyDeleteDashboardActionIsNotDisplayed() {
			 boolean flag=true;
				try {
					if (SelectedDashboardDeleteButton.isDisplayed()) {
						flag=true;
					}
					} catch (Exception e) {
				}
				Assert.assertTrue(flag);
				reportInfo();
			 
		}
		/***
		 * Verify Add version button is not displayed
		 */

		public void verifyAddVersionButtonIsNotDisplayed() {
			 boolean flag=true;
				try {
					if (addVersionControl.isDisplayed()) {
						flag=true;
					}
					} catch (Exception e) {
				}
				Assert.assertTrue(flag);
				reportInfo();
		}
		/***
		 * Verify Add version button is not displayed
		 */

		public void verifyAddVersionButtonNotDisplayed() {
			 boolean flag=true;
				try {
					if (addVersionControl.isDisplayed()) {
						flag=true;
					}
					} catch (Exception e) {
				}
				Assert.assertTrue(flag);
				reportInfo();
		}
		
		/***
		 * Verify  version edit button not displayed
		 */
		
		public void verifyVersionEditButtonIsNotDisplayed() {
				 boolean flag=true;
					try {
						if (VersionEditButton.isDisplayed()) {
							flag=true;
						}
						} catch (Exception e) {
					}
					Assert.assertTrue(flag);
					reportInfo();
			
		}
		/***
		 * Verify  version Delete button Not displayed
		 */
		
		public void verifyVersionDeleteButtonIsNotDisplayed() {
			 boolean flag=true;
				try {
					if (VersionDeleteButton.isDisplayed()) {
						flag=true;
					}
					} catch (Exception e) {
				}
				Assert.assertTrue(flag);
				reportInfo();
			
		}
		
		 
		
	}

	

	



