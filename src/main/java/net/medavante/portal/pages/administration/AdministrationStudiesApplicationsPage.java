package net.medavante.portal.pages.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesApplicationsPage extends BasePage {

	@FindBy(xpath = "//button[contains(text(),'Patient App')]")
	private WebElement patientAppBTN_Applcation;

	@FindBy(xpath = "//label[@class='ng-binding' and text()='Registration']//following-sibling::button/span")
	private WebElement regEditIconBTN_Applcation;

	@FindBy(xpath = "//label[@class='ng-binding' and text()='Registration']")
	private WebElement registrationTab;

	@FindBy(xpath = "//label[@class='ng-binding' and text()='Registration']//parent::div//parent::div/div/a")
	private WebElement regCollapsedIcon;

	@FindBy(xpath = "//label[@class='ng-binding' and text()='Top Menu']")
	private WebElement topMenuTab;

	@FindBy(xpath = "//label[@class='ng-binding' and text()='Top Menu']//following-sibling::button/span")
	private WebElement topMenuEditIconBTN;

	@FindBy(xpath = "//label[@class='ng-binding' and text()='Top Menu']//parent::div//parent::div/div/a")
	private WebElement topMenuTabCollapsedIcon;

	@FindBy(xpath = "//label[@class='ng-binding' and text()='Side Menu']")
	private WebElement sideMenuTab;

	@FindBy(xpath = "//label[@class='ng-binding' and text()='Side Menu']//following-sibling::button/span")
	private WebElement sideMenuEditIconBTN;

	@FindBy(xpath = "//label[@class='ng-binding' and text()='Side Menu']//parent::div//parent::div/div/a")
	private WebElement sideMenuTabCollapsedIcon;

	@FindBy(xpath = "//div[@id='edit-patient-app-registration-settings-dialog']//*[text()='Edit Registration Settings']")
	private WebElement editRegSettingPopUp;

	@FindBy(xpath = "//input[@id='termsAndConditionsCheckbox']")
	private WebElement regTermAndConditionCheckbox;

	@FindBy(xpath = "//div[@id='registration-settings-dialog']//div[@name='versionDropDown']//span[@id='selectedStudy']")
	private WebElement regDefaultVersion;

	@FindBy(xpath = "//div[@id='registration-settings-dialog']//div[@class='agreement-block']")
	private WebElement regAgreeMentTxtField;

	@FindBy(xpath = "//div[@id='edit-patient-app-registration-settings-dialog']//button[contains(@class,'btn-save')]")
	private WebElement regSaveButton;

	@FindBy(xpath = "//div[@id='edit-patient-app-registration-settings-dialog']//span[text()='Cancel']")
	private WebElement regCancelButton;

	@FindBy(xpath = "//div[@class='collapsed-block']//span[text()='Terms And Conditions']")
	private WebElement termAndConditionLabel;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//*[text()='Top Menu']")
	private WebElement topMenuModelWindow;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//label[text()='Subject']")
	private WebElement settingOfSubjectLabel;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//label[text()='Observer']")
	private WebElement settingOfObserverLabel;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//span[text()='Cancel']")
	private WebElement topMenuCancelButton;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//span[text()='Back']")
	private WebElement topMenuBackButton;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//span[text()='Save']")
	private WebElement topMenuSaveButton;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//span[text()='Next']")
	private WebElement topMenuNextButton;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//form[@name='primarySettingsForm']/div[1]/div")
	private List<WebElement> subjectSettingOptions;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//form[@name='primarySettingsForm']/div[1]/div/input")
	private List<WebElement> subjectSettingOptionsOnTopMenu;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//form[@name='primarySettingsForm']/div[2]/div")
	private List<WebElement> observerSettingOptions;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-settings-dialog']//form[@name='primarySettingsForm']/div[2]/div/input")
	private List<WebElement> observerSettingOptionsOnTopMenu;

	@FindBy(xpath = "//div[@id='edit-patient-app-primary-home-settings-dialog']//div[@class='settings-list']/div")
	private List<WebElement> homeSectionSettingOptions;

	@FindBy(xpath = "//form[@name='questionnairesForm']/div/div[1]/div[contains(@class,'radio-control')]")
	private List<WebElement> showQuestioForOptions;

	@FindBy(xpath = "//form[@name='questionnairesForm']/div/div[2]/div[contains(@class,'radio-control') or contains(@class,'row flex')]")
	private List<WebElement> showCompAndExpiQuestioForOptions;

	@FindBy(xpath = "//form[@name='messageForm']/div/label[text()='Show in Messages Section']")
	private WebElement showInMessagesSection;

	@FindBy(xpath = "//form[@name='messageForm']/div/label[text()='Site Messages']")
	private WebElement siteMessagesSection;

	@FindBy(xpath = "(//div[@class='col-xs-24 collapse section-body in'])[2]//label")
	private WebElement homeLabelUnderTopMenu;

	@FindBy(xpath = "//div[@id='edit-patient-app-side-settings-dialog']//*[text()='Side Menu']")
	private WebElement sideMenuPopUp;

	@FindBy(xpath = "//div[@id='side-settings-dialog']//div[@data-ng-show='showParticipantFlags']/div")
	private List<WebElement> subjectOptionsUnderSideMenu;

	@FindBy(xpath = "//div[@id='side-settings-dialog']//div[@data-ng-show='showParticipantFlags']/div/input")
	private List<WebElement> subjectOptionsChkboxUnderSideMenu;

	@FindBy(xpath = "//div[@id='side-settings-dialog']//div[@data-ng-show='showObserverFlags']/div")
	private List<WebElement> observerOptionsUnderSideMenu;

	@FindBy(xpath = "//div[@id='side-settings-dialog']//div[@data-ng-show='showObserverFlags']/div/input")
	private List<WebElement> observerOptionsChkboxUnderSideMenu;

	@FindBy(xpath = "//div[@id='edit-patient-app-side-settings-dialog']//span[text()='Save']")
	private WebElement saveButtonOnSideMenu;

	@FindBy(xpath = "//div[@id='edit-patient-app-side-settings-dialog']//span[text()='Cancel']")
	private WebElement cancelButtonOnSideMenu;

	@FindBy(xpath = "//div[@class='collapsed-block']/div[@data-ng-show='showParticipantFlags']/span[2]")
	private WebElement subjectInSideMenu;

	@FindBy(xpath = "//div[@class='collapsed-block']/div[@data-ng-show='showObserverFlags']/span[2]")
	private WebElement observerInSideMenu;

	public AdministrationStudiesApplicationsPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * @function: Verification of Application Page
	 *
	 */
	public void verifyAdministrationStudiesApplicationPage() {
		Assert.assertTrue(isElementPresent(patientAppBTN_Applcation) && isElementPresent(regEditIconBTN_Applcation));
	}

	public void verifyRegistrationTabWithCollapsedAndEditIcon() {
		this.verifyRegistrationTabText();
		this.verifyRegistrationTabEditIcon();
		this.verifyRegistrationTabCollapsedIcon();
		reportInfo();
	}

	public void verifyTopMenuTabWithCollapsedAndEditIcon() {
		this.verifyTopMenuTabText();
		this.verifyTopMenuTabEditIcon();
		this.verifyTopMenuTabCollapsedIcon();
		reportInfo();
	}

	public void verifySideMenuTabWithCollapsedAndEditIcon() {
		this.verifySideMenuTabText();
		this.verifySideMenuTabEditIcon();
		this.verifySideMenuTabCollapsedIcon();
		reportInfo();
	}

	public void verifyAllTabsCollapsed() {
		this.verifyRegistrationTabCollapsed();
		this.verifyTopMenuTabCollapsed();
		this.verifySideMenuTabCollapsed();
	}

	public void clickOnRegistrationTopMenuAndSideMenuCollapsedIcon() {
		this.clickOnRegistrationTabCollapseIcon();
		this.clickOnTopMenuTabCollapseIcon();
		this.clickOnSideMenuTabCollapseIcon();
	}

	public void verifyRegistrationTabText() {
		Assert.assertTrue(isElementPresent(registrationTab));
	}

	public void verifyTopMenuTabText() {
		Assert.assertTrue(isElementPresent(topMenuTab));
	}

	public void verifySideMenuTabText() {
		Assert.assertTrue(isElementPresent(sideMenuTab));
	}

	public void verifyRegistrationTabEditIcon() {
		Assert.assertTrue(isElementPresent(regEditIconBTN_Applcation));
	}

	public void verifyTopMenuTabEditIcon() {
		Assert.assertTrue(isElementPresent(topMenuEditIconBTN));
	}

	public void verifySideMenuTabEditIcon() {
		Assert.assertTrue(isElementPresent(sideMenuEditIconBTN));
	}

	public void verifyRegistrationTabCollapsedIcon() {
		Assert.assertTrue(isElementPresent(regCollapsedIcon));
	}

	public void verifyTopMenuTabCollapsedIcon() {
		Assert.assertTrue(isElementPresent(topMenuTabCollapsedIcon));
	}

	public void verifySideMenuTabCollapsedIcon() {
		Assert.assertTrue(isElementPresent(sideMenuTabCollapsedIcon));
	}

	public void clickOnRegistrationTabCollapseIcon() {
		waitAndClick(regCollapsedIcon);
	}

	public void clickOnTopMenuTabCollapseIcon() {
		waitAndClick(topMenuTabCollapsedIcon);
	}

	public void clickOnSideMenuTabCollapseIcon() {
		waitAndClick(sideMenuTabCollapsedIcon);
	}

	public void verifyRegistrationTabCollapsed() {
		boolean flag = false;
		if (regCollapsedIcon.getAttribute("class").equalsIgnoreCase("collapsed")) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyTopMenuTabCollapsed() {
		boolean flag = false;
		if (topMenuTabCollapsedIcon.getAttribute("class").equalsIgnoreCase("collapsed")) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifySideMenuTabCollapsed() {
		boolean flag = false;
		if (sideMenuTabCollapsedIcon.getAttribute("class").equalsIgnoreCase("collapsed")) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyRegistrationTabExpanded() {
		boolean flag = false;
		if (regCollapsedIcon.getAttribute("class").equalsIgnoreCase("collapsed")) {
			flag = true;
		}
		Assert.assertFalse(flag);
		reportInfo();
	}

	public void verifyTopMenuTabExpanded() {
		boolean flag = false;
		if (topMenuTabCollapsedIcon.getAttribute("class").equalsIgnoreCase("collapsed")) {
			flag = true;
		}
		Assert.assertFalse(flag);
		reportInfo();
	}

	public void verifySideMenuTabExpanded() {
		boolean flag = false;
		if (sideMenuTabCollapsedIcon.getAttribute("class").equalsIgnoreCase("collapsed")) {
			flag = true;
		}
		Assert.assertFalse(flag);
		reportInfo();
	}

	public void verifyAllTabsExpandedAfterRefresh() {
		refreshPage();
		this.verifyRegistrationTabExpanded();
		this.verifyTopMenuTabExpanded();
		this.verifySideMenuTabExpanded();
	}

	public void clickOnEditIconOnRegistrationMenu() {
		moveToElement(regEditIconBTN_Applcation);
		clickOn(regEditIconBTN_Applcation);
	}

	public void verifyEditRegistrationSettingsPopUp() {
		waitForSpinnerBecomeInvisible(10);
		moveToElement(editRegSettingPopUp);
		boolean flag = false;
		if (isElementPresent(editRegSettingPopUp)) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyTermsAndConditionCheckboxUnchecked() {
		boolean flag = true;
		if (regTermAndConditionCheckbox.getAttribute("class").contains("ng-pristine")) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void verifyDefaultVersionSelectedInDropDown() {
		boolean flag = true;
		if (getText(regDefaultVersion).equalsIgnoreCase("Default")) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void verifyRegAggrementTxtField() {
		Assert.assertTrue(isElementDisplayed(regAgreeMentTxtField));
	}

	public void verifyrRegSaveButtonDisabled() {
		moveToElement(regSaveButton);
		boolean flag = true;
		if (regSaveButton.getAttribute("disabled").equalsIgnoreCase("true")) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void verifyRegCancelButton() {
		moveToElement(regCancelButton);
		Assert.assertTrue(isElementDisplayed(regCancelButton));
	}

	public void clickOnTermsAndConditionCheckbox() {
		clickOn(regTermAndConditionCheckbox);
	}

	public void clickOnSaveButtonOnRegistrationPopUp() {
		clickOn(regSaveButton);
	}

	public void verifyEditRegSettingModelWindowClosed() {
		waitForSpinnerBecomeInvisible(15);
		boolean flag = false;
		if (isElementNotVisible(editRegSettingPopUp)) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void verifyRegistrationSettingUpdated() {
		boolean flag = false;
		if (isElementDisplayed(termAndConditionLabel)) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void verifyRegistrationSettingNotShown() {
		waitForSpinnerBecomeInvisible(15);
		boolean flag = false;
		if (isElementNotVisible(termAndConditionLabel)) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void uncheckedTermAndConditionCheckbox() {
		this.clickOnEditIconOnRegistrationMenu();
		this.verifyEditRegistrationSettingsPopUp();
		this.clickOnTermsAndConditionCheckbox();
		this.clickOnSaveButtonOnRegistrationPopUp();
	}

	public void clickOnEditIconOnTopMenuTab() {
		moveToElement(topMenuEditIconBTN);
		clickOn(topMenuEditIconBTN);
	}

	public void verifyTopMenuPopUp() {
		waitForSpinnerBecomeInvisible(10);
		moveToElement(topMenuModelWindow);
		Assert.assertTrue(isElementDisplayed(topMenuModelWindow));
		reportInfo();
	}

	public void verifySubjectSettingOptions() {
		String sub = "Subject, Home, Questionnaires, Messages, Log an Event";
		boolean flag = false;
		for (WebElement webElement : subjectSettingOptions) {
			if (sub.contains(webElement.getText())) {
				flag = true;
			}
			Assert.assertTrue(flag);
		}
	}

	public void verifyObserverSettingOptions() {
		String sub = "Observer, Home, Questionnaires, Messages";
		boolean flag = false;
		for (WebElement webElement : observerSettingOptions) {
			if (sub.contains(webElement.getText())) {
				flag = true;
			}
			Assert.assertTrue(flag);
		}
	}

	public void verifyNextButtonOnTopMenuPopUp() {
		Assert.assertTrue(isElementDisplayed(topMenuNextButton));
	}

	public void verifyCancelButtonOnTopMenuPopUp() {
		Assert.assertTrue(isElementDisplayed(topMenuCancelButton));
	}

	public void verifyBackButtonOnTopMenuPopUp() {
		Assert.assertTrue(isElementDisplayed(topMenuBackButton));
	}

	public void verifySaveButtonOnTopMenuPopUp() {
		Assert.assertTrue(isElementDisplayed(topMenuSaveButton));
	}

	public void selectSubjectSettingOptions() {
		for (WebElement webElement : subjectSettingOptionsOnTopMenu) {
			if (webElement.isSelected()) {
			} else {
				clickOn(webElement);
			}
		}
	}

	public void selectObserverSettingOptions() {
		for (WebElement webElement : observerSettingOptionsOnTopMenu) {
			if (webElement.isSelected()) {
			} else {
				clickOn(webElement);
			}
		}
	}

	public void clickOnNextButtonUnderTopMenu() {
		moveToElement(topMenuNextButton);
		clickOn(topMenuNextButton);
	}

	public void clickOnSaveButtonUnderTopMenu() {
		moveToElement(topMenuSaveButton);
		clickOn(topMenuSaveButton);
	}

	public void verifyShowInHomeSectionOptions() {
		String sub = "Pending Questionnaire, Next Questionnaire, Unread Messages, Recent Message, Next Visit";
		boolean flag = false;
		for (WebElement webElement : homeSectionSettingOptions) {
			if (sub.contains(webElement.getText())) {
				flag = true;
			}
			Assert.assertTrue(flag);
		}
	}

	public void selectPendingQuestionnaireOption(String option) {
		boolean flag = false;
		for (WebElement webElement : homeSectionSettingOptions) {
			if (option.contains(webElement.getText())) {
				WebElement cK = webElement.findElement(By.xpath(".//input"));
				if (cK.isSelected()) {
				} else {
					clickOn(cK);
				}
				flag = true;
			}
			Assert.assertTrue(flag);
		}
	}

	public void verifyShowQuestionnairesForOptions() {
		String sub = "Today, Today and Tomorrow, Today and Later, Today, Tomorrow and Later";
		boolean flag = false;
		for (WebElement webElement : showQuestioForOptions) {
			if (sub.contains(webElement.getText())) {
				flag = true;
			}
			Assert.assertTrue(flag);
		}
	}

	public void verifyShowCompletedAndExpiredQuestionnairesOptions() {
		String sub = "Until Uploaded, All Day, For";
		boolean flag = false;
		for (WebElement webElement : showCompAndExpiQuestioForOptions) {
			if (sub.contains(webElement.getText())) {
				flag = true;
			}
			Assert.assertTrue(flag);
		}
	}

	public void verifySiteMessagesAndShowInMessagesSection() {
		Assert.assertTrue(isElementDisplayed(showInMessagesSection));
		Assert.assertTrue(isElementDisplayed(siteMessagesSection));
	}

	public void verifyTopMenuPopUpClosed() {
		waitForSpinnerBecomeInvisible(10);
		boolean flag = false;
		if (isElementNotVisible(topMenuModelWindow)) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void verifyTopMenuSettingsDisplayed(String option) {
		boolean flag = false;
		if (homeLabelUnderTopMenu.getText().contains(option)) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void clickOnEditIconOnSideMenuTab() {
		moveToElement(sideMenuEditIconBTN);
		clickOn(sideMenuEditIconBTN);
	}

	public void verifySideMenuPopUp() {
		waitForSpinnerBecomeInvisible(5);
		Assert.assertTrue(isElementDisplayed(sideMenuPopUp));
	}

	public void verifySubjectOptionUnderSideMenuPopUp() {
		String sub = "Subject, My Account, Medications, My Schedule, Study Information, Contacts, Settings, Help & Tutorials";
		boolean flag = false;
		for (WebElement webElement : subjectOptionsUnderSideMenu) {
			if (sub.contains(webElement.getText())) {
				flag = true;
			}
			Assert.assertTrue(flag);
		}
	}

	public void verifyObserverOptionUnderSideMenuPopUp() {
		String sub = "Observer, My Account, My Schedule, Study Information, Contacts, Settings, Help & Tutorials";
		boolean flag = false;
		for (WebElement webElement : observerOptionsUnderSideMenu) {
			if (sub.contains(webElement.getText())) {
				flag = true;
			}
			Assert.assertTrue(flag);
		}
	}

	public void selectSubjectOptionUnderSideMenuPopUp(String option) {
		for (WebElement webElement : subjectOptionsChkboxUnderSideMenu) {
			if (getText(webElement.findElement(By.xpath(".//following-sibling::label"))).contains(option)) {
				if (webElement.isSelected()) {
					clickOn(webElement);
				}
				waitAndClick(webElement);
			}
		}
	}

	public void selectObserverOptionUnderSideMenuPopUp(String option) {
		for (WebElement webElement : observerOptionsChkboxUnderSideMenu) {
			if (getText(webElement.findElement(By.xpath(".//following-sibling::label"))).contains(option)) {
				if (webElement.isSelected()) {
					clickOn(webElement);
				}
				waitAndClick(webElement);
			}
		}
	}

	public void verifySaveAndCancelButtonUnderSideMenuPopUp() {
		Assert.assertTrue(isElementDisplayed(saveButtonOnSideMenu));
		Assert.assertTrue(isElementDisplayed(cancelButtonOnSideMenu));
	}

	public void clickOnSaveButtonOnSideMenuPopUp() {
		moveToElement(saveButtonOnSideMenu);
		clickOn(saveButtonOnSideMenu);
	}

	public void verifySideMenuPopUpClosed() {
		waitForSpinnerBecomeInvisible(10);
		boolean flag = false;
		if (isElementNotVisible(sideMenuPopUp)) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void verifySideMenuSettingsUpdated(String opts) {
		_normalWait(10);
		moveToElement(subjectInSideMenu);
		Assert.assertTrue(getText(subjectInSideMenu).contains(opts));
		moveToElement(observerInSideMenu);
		Assert.assertTrue(getText(observerInSideMenu).contains(opts));
	}
	
	public void verifyPatientTabDisplayedInViewMode() {
		boolean flag = false;
		if(regEditIconBTN_Applcation.findElement(By.xpath(".//parent::button")).getAttribute("disabled").equalsIgnoreCase("true")) {
			flag=true;
		}
		Assert.assertTrue(flag);
	}
}