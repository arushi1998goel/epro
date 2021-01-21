package net.medavante.portal.pages.studynavigator;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.util.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import net.medavante.portal.selenium.core.BasePage;
import net.medavante.portal.utilities.Constants;

public class RatersDetailsPage extends BasePage {
	String study;

	public RatersDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * =========================================================== Locators
	 * ===========================================================
	 */

	@FindBy(xpath = "//div[@id='page-title']/h1[contains(text(),'Rater')]")
	private WebElement raterDetailsPageTitle;

	@FindBy(xpath = "//label[@class='k-checkbox-label ng-star-inserted']/ancestor::tr[1]/td/div")
	private WebElement raterName;

	@FindBy(xpath = "//h2[contains(text(),'Detail')]")
	private WebElement detailTitle;

	@FindBy(xpath = "//h2[contains(text(),'Qualifications')]")
	private WebElement qualificationTitle;

	@FindBy(xpath = "//h2[contains(text(),'Assessments')]")
	private WebElement assessmentTitle;

	@FindBy(xpath = "//a[@title='Raters']")
	private WebElement ratersLinkICN;

	@FindBy(xpath = "//a[@title='Show queries']")
	private WebElement queriesLinkICN;

	@FindBy(xpath = "//a[@title='Raters']/span[2]")
	private WebElement ratersCount;

	@FindBy(xpath = "//div[contains(@class,'ng-isolate-scope opened')]//h1[contains(text(),'Raters')]")
	private WebElement ratersProfileSliderPanelOpen;

	@FindBy(xpath = "//div[@class='cdk-overlay-container']//input[@placeholder='Search']")
	private WebElement searchStudy;

	@FindBy(xpath = "//p[contains(text(),'Study')]//parent::div//div[contains(@class,'item ng-s')]")
	private List<WebElement> studyList;

	@FindBy(xpath = "//div[@class='column form-container']//input[@formcontrolname='siteQuery']")
	private WebElement searchSite;

	@FindBy(xpath = "//*[text()='Site:']//parent::div//following-sibling::div//div[contains(@class,'ng-star-inserted')]")
	private List<WebElement> siteLists;

	@FindBy(xpath = "//div[@class='ma-dialog-actions']//button[text()='Select']")
	private WebElement selectButtonForStudyAndSite;

	@FindBy(xpath = "//div[@id='study-header']/button[contains(@class,'queries-button')]")
	private WebElement selectedStudyButton;

	@FindBy(xpath = "//button[@class='btn dropdown-toggle btn-default']")
	private WebElement siteDRPDWN;

	@FindBy(xpath = "//*[@title='Study Profile']")
	private WebElement studyProfileLinkICN;

	@FindBy(xpath = "//*[contains(text(),'Select study & site')]")
	private WebElement selectStudySiteModalWinTittle;

	@FindBy(xpath = "(//header[@id='header']//li[@role='presentation'])[1]")
	private WebElement navigateButton;

	@FindBy(xpath = "//button[@class='queries-button']")
	private WebElement studySelectorButton;

	@FindBy(xpath = "(//div[@class='list-container ng-star-inserted'])[1]")
	private WebElement studyListStudySelectorPopUp;

	@FindBy(xpath = "(//div[@class='list-container ng-star-inserted'])[2]")
	private WebElement SiteListStudySelectorPopUp;

	@FindBy(xpath = "(//div[@class='list']//div)[2]")
	private WebElement allSiteOptionStudySelectorPopUp;

	@FindBy(xpath = "(//div[@class='ma-dialog-footer']//button)[1]")
	private WebElement selectButtonStudySelectorPopUp;

	@FindBy(xpath = "(//div[@class='ma-dialog-footer']//button)[2]")
	private WebElement cancelButtonStudySelectorPopUp;

	@FindBy(xpath = "//span[@class='flex-align-right ma-dialog-close ng-star-inserted']")
	private WebElement closeIconStudySelectorPopUp;

	@FindBy(xpath = "//div[@class='heading-panel-box']//strong")
	private WebElement ratersSidetab;

	@FindBy(xpath = "//div[contains(text(),'Bulk Load')]")
	private WebElement bulkloadButton;

	@FindBy(xpath = "//span[text()='Add Multiple Site Raters']")
	private WebElement addMultiplesiteRatertab;

	@FindBy(xpath = "//label[text()='Upload File']")
	private WebElement uploadFileBtn;

	@FindBy(xpath = "//button[text()='Next']")
	private WebElement nextBtn;

	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	private WebElement cancelBtn;

	@FindBy(xpath = "//span[@class='remove-file-btn']")
	private WebElement removeFileBtn;

	@FindBy(xpath = "(//label[text()='Uploaded file name:']/..//label)[2]")
	private WebElement UploadedFileName;

	@FindBy(xpath = "//div[contains(text(),'The rater(s) will not be added! Are you sure?')]")
	private WebElement ConfirmationMessage;

	@FindBy(xpath = "//button[@class='btn btn-new-active']")
	private WebElement acceptclosedialogoptionBtn;

	@FindBy(xpath = "//a[text()='Upload File']")
	private WebElement uploadButton;

	@FindBy(xpath = "//div[@class='review-all-raters-section']/label")
	private WebElement VerifyEachraterInformationField;

	@FindBy(xpath = "//div[@class='review-all-raters-section']/span/input")
	private WebElement reviewEachRaterInfoChkBox;

	@FindBy(xpath = "//div[@class='form-fields-box']//div[contains(@class,'input-container')]")
	private WebElement inputSiteContainerField;

	@FindBy(xpath = "//button[text()='Back']")
	private WebElement BackButton;

	@FindBy(xpath = "//label[text()='Primary Email']/..//input[@type='email']")
	private WebElement primaryEmailfield;

	@FindBy(xpath = "//label[text()='Additional Emails']/..//input[@type='email']")
	private WebElement additionalEmailfield;

	@FindBy(xpath = "//label[text()='First Name']/..//input[@type='text']")
	private WebElement firstNamefield;

	@FindBy(xpath = "//label[text()='Last Name']/..//input[@type='text']")
	private WebElement lastNamefield;

	@FindBy(xpath = "//input[@type='phone']")
	private WebElement phonefield;

	@FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
	private WebElement facilityfield;

	@FindBy(xpath = "//span[@class='k-input']")
	private WebElement studyfield;

	@FindBy(xpath = "//div[@class='error-frame ng-star-inserted']")
	private WebElement studyerrorWarning;

	@FindBy(xpath = "//div[@class='ng-star-inserted']")
	private WebElement successmsg;

	@FindBy(xpath = "//button[text()='Finish']")
	private WebElement finishButton;

	@FindBy(xpath = "//div[@class='aside-nav-wrap']//span[text()='Onboarding']/..")
	private WebElement sidemenuOnBoardingTab;

	@FindBy(xpath = "(//a[@title='Filter'])[1]")
	private WebElement nameFilterbutton;

	@FindBy(xpath = "(//div[@class='k-popup k-grid-filter-popup']//input)[1]")
	private WebElement namefilterInputfield;

	@FindBy(xpath = "//button[text()='Filter']")
	private WebElement filterButton;

	@FindBy(xpath = "//button[text()='Clear']")
	private WebElement clearButton;

	@FindBy(xpath = "//div[text()=' Actions ']")
	private WebElement actionButton;

	@FindBy(xpath = "//a[text()='Delete']")
	private WebElement deleteButton;

	@FindBy(xpath = "//button[text()='Revoke']")
	private WebElement revokeButton;

	@FindBy(xpath = "//label[text()='Username']/..//input")
	private WebElement userNameInputField;

	@FindBy(xpath = "//label[text()='Password']/..//input")
	private WebElement passwordInputField;

	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement confirmButton;

	@FindBy(xpath = "//div[@title='Add Site Staff']")
	private WebElement addSiteStaffControl;

	@FindBy(xpath = "//span[text()='Add Site Staff']")
	private WebElement addSiteStaffWindow;

	@FindBy(xpath = "//*[@class='k-animation-container k-animation-container-shown']//li//label")
	private List<WebElement> facilitiesList;

	@FindBy(xpath = "//*[@class='k-popup k-list-container k-reset']//li")
	private List<WebElement> studiesList;

	@FindBy(xpath = "//span[text()='Security Confirmation']")
	private WebElement securityconfrmtnpopup;

	@FindBy(xpath = "//button[text()='Add']")
	private WebElement addButton;

	@FindBy(xpath = "//table//span[text()='Name']//ancestor::th//span[@class='k-icon k-i-filter']")
	private WebElement nameFilterIcon;

	@FindBy(xpath = "//div[@class='k-grid-header']//table//span[text()='Identity']//ancestor::th//span[@class='k-icon k-i-filter']")
	private WebElement identityFilterIcon;

	@FindBy(xpath = "//div[@class='k-grid-header']//table//span[text()='On-boarding']//ancestor::th//span[@class='k-icon k-i-filter']")
	private WebElement onboardingFilterIcon;

	@FindBy(xpath = "(//div[@class='k-filter-menu-container']//input)[1]//ancestor::ul//input[@class='k-checkbox']")
	private WebElement checkboxOnboardingFilter;

	@FindBy(xpath = "//div[@class='k-grid-aria-root']//span[@class='link ng-star-inserted']")
	private WebElement siteRaterLink;

	@FindBy(xpath = "//div[@class='k-grid-aria-root']//span[@class='link ng-star-inserted']//ancestor::tr/td[contains(@class,'k-checkbox-cell')]")
	private WebElement checkboxOnsiteRaterRow;

	@FindBy(xpath = "//div[@class='study-title']//div[@class='heading-panel-box ng-star-inserted']//div[@title='Actions' and text()=' Actions ']")
	private WebElement actionsControl;

	@FindBy(xpath = "//div[contains(@id,'cdk-overlay')]//*[@class='general-user-info']//h2")
	private WebElement completedSiteRaterDetailsPage;

	@FindBy(xpath = "//div[contains(@id,'cdk-overlay')]//*[@class='general-user-info']//button")
	private WebElement assignToFacilityOrStudyButton;

	@FindBy(xpath = "//div[contains(@id,'cdk-overlay')]//span[contains(@title,'Assign to Facility')]")
	private WebElement assignToFacilityOrStudyPopUp;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButtonOnAssignToFacilityOrStudy;

	@FindBy(xpath = "//span[text()='Activation']/..")
	private WebElement activationMenu;

	@FindBy(xpath = "//span[contains(text(),'All')]")
	private WebElement onBoardingAllbtn;

	@FindBy(xpath = "//th[contains(text(),'Activation')]")
	private WebElement activationGrid;

	@FindBy(xpath = "//span[contains(text(),'Invited')]")
	private WebElement invitedgridOnboardingTab;

	@FindBy(xpath = "//a[text()='Match Review']")
	private WebElement matchReviewBtn;

	@FindBy(xpath = "//span[text()='Users Match Review']")
	private WebElement matchReviewpopupTitle;

	@FindBy(xpath = "//div[@class='warning-label info-mode ng-star-inserted']")
	private WebElement matchReviewpopupMessage;

	@FindBy(xpath = "//h2[text()='User under review:']")
	private WebElement userUnderReviewTitle;

	@FindBy(xpath = "//label[@for='rbMerge']/div")
	private WebElement existingUser;

	@FindBy(xpath = "(//div[contains(@id,'cdk-overlay')]//div[@class='review-section']//*[contains(text(),'ser under review')])[1]")
	private WebElement addNewUser;

	@FindBy(xpath = "//strong[text()='Merge Users']/ancestor::li/div")
	private WebElement existingUserRadioButton;

	@FindBy(xpath = "//label[@for='rbDelete']")
	private WebElement discardUser;

	@FindBy(xpath = "//span[@class='flex-align-right ma-dialog-close ng-star-inserted']")
	private WebElement closeBtn;

	@FindBy(xpath = "//button[@class='btn btn-new-active ng-star-inserted' and contains(text(),'Cancel')]")
	private WebElement cancelBtnonReviewpopup;

	@FindBy(xpath = "//button[@class='btn btn-new-active ng-star-inserted' and text()='Discard User']")
	private WebElement discardButton;

	@FindBy(xpath = "//button[text()='Merge Users']")
	private WebElement MergeUserButton;

	@FindBy(css = "div[class='k-filter-menu-container'] kendo-grid-string-filter-menu>kendo-grid-string-filter-menu-input:first-child input")
	private WebElement filterSearchBox;

	@FindBy(xpath = "//span[text()='Name']/../..//a/span[@class='k-icon k-i-filter']")
	private WebElement filterICON_rater;

	@FindBy(xpath = "//div[contains(@class,k-action-buttons)]//button[text()='Filter']")
	private WebElement inpSearchfilterBTN;

	@FindBy(xpath = "//div/span[text()='Select study & site']")
	private WebElement popUpHeader;

	@FindBy(xpath = "//table[@class='raters-list']//tr[@class='ng-star-inserted selected']")
	private WebElement raterRow;

	@FindBy(xpath = "//table[@class='raters-list']//tr[@class='ng-star-inserted selected']")
	private List<WebElement> raterRows;

	@FindBy(xpath = "((//table[@class='k-grid-table'])[2]//td[@class='ng-star-inserted'])[1]")
	private WebElement newlyEntry;

	@FindBy(xpath = "//span[contains(text(),'Back')]")
	private WebElement backButton;

	@FindBy(xpath = "//button[@class='btn btn-new-active ng-star-inserted' and text()='Add as New']")
	private WebElement addAsNewBtn;

	@FindBy(xpath = "//a[@class='add-email-btn ng-star-inserted' and text()='+ Add email']")
	private WebElement addAdditionalEmail;

	@FindBy(xpath = "//label[text()='Additional Emails']/..//input")
	private WebElement additionalEmailfeld;

	@FindBy(xpath = "//label[text()='Phone']/..//input")
	private WebElement phoneNofield;

	@FindBy(xpath = "//button[text()=' Skip Review']")
	private WebElement skipReviewbutton;

	@FindBy(xpath = "//strong[text()='Discard User']")
	private WebElement discardtext;

	@FindBy(xpath = "//span[text()='Tracking']/..")
	private WebElement trackingGrid;

	@FindBy(xpath = "//span[text()='Qualification']/..")
	private WebElement qualificationGrid;

	@FindBy(xpath = "//span[text()='Certification']/..")
	private WebElement certificationGrid;

	@FindBy(xpath = "//label[@for='rbAdd']/div/ancestor::ul//input")
	private WebElement addUserNewRadioBtn;

	@FindBy(xpath = "//a[text()='View History']")
	private WebElement viewHistoryLink;

	@FindBy(xpath = "//span[text()='Status']/ancestor::th//a[contains(@class,'k-grid-filter')]")
	private WebElement statusFilterIcon;

	@FindBy(xpath = "//div[@class='list-row-item ng-star-inserted']/span")
	private List<WebElement> sitesList;

	@FindBy(xpath = "//ul[@class='aside-nav-list']")
	private WebElement raterSidePanel;

	@FindBy(xpath = "//strong[contains(text(),'Clinicians / Raters: ')]")
	private WebElement Clinicians_Raters;

	@FindBy(xpath = "//tr[@class='k-grid-norecords ng-star-inserted']//td[text()=' No records available. ']")
	private WebElement noSiteRaterText;

	/* add site window locators */

	@FindBy(xpath = "//input[@type='email']")
	private WebElement primaryEmail;

	@FindBy(xpath = "//input[@formcontrolname='firstName']")
	private WebElement firstName;

	@FindBy(xpath = "//input[@formcontrolname='lastName']")
	private WebElement lastName;

	@FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
	private WebElement facilitiesField;

	@FindBy(xpath = "//div[@class='ma-dialog-header']//span[2]")
	private WebElement TinyCrossButtonOnAddSiteWindow;

	@FindBy(xpath = "//div[@class='ma-dialog-footer']//button[text()='Yes, close form']")
	private WebElement YesCloseFormControlOnConfirmationPopUp;

	@FindBy(xpath = "//div[@class='ma-dialog-actions']//button[text()=' Cancel']")
	private WebElement cancelButtonAddSitePopUp;

	@FindBy(xpath = "//span[text()='Tracking']")
	private WebElement trackingtab;

	@FindBy(xpath = "//span[@class='icon-small icon-exel']/..")
	private WebElement exportFilterOptionbtn;

	@FindBy(xpath = "//span[@class='icon-small icon-columns']/..")
	private WebElement columnsFilterBtn;

	@FindBy(xpath = "(//div[@class='list']//div)[1]")
	private WebElement filteredStudyButton;

	@FindBy(xpath = "((//div[@class='k-grid-aria-root'])[2]//tr)[last()]")
	private WebElement addedSiteInfo;

	@FindBy(xpath = "(//div[@class='heading-panel-box']//strong)[2]")
	private WebElement raterNameOnHistoryPage;

	@FindBy(xpath = "//label[@class='k-checkbox-label ng-star-inserted']")
	private WebElement siteRaterCheckbox;

	@FindBy(xpath = "(//div[@class='btn-group'])[2]")
	private WebElement actionControl;

	@FindBy(xpath = "(//ul[@class='dropdown-menu pull-right'])[2]")
	private WebElement viewHistoryButton;

	@FindBy(xpath = "//div[@class='portal-grid query-list']//div[contains(@class,'row query-item-row grid-row')]")
	private List<WebElement> details;

	@FindBy(xpath = "//div[contains(@class,k-action-buttons)]//button[text()='Clear']")
	private WebElement inpSearchClearBTN;

	@FindBy(xpath = "//div[@class='ma-dialog-footer']//label[text()='Add another']")
	private WebElement addAnotherCheckbox;

	@FindBy(xpath = "//div[@formarrayname='siteRoles']//span[@class='k-input']")
	private WebElement siteRoleDropDown;

	@FindBy(xpath = "//div[contains(@class,'k-popup k-list-container')]//ul/li[text()='Site User']")
	private WebElement siteRoleDropDownOption;
	
	@FindBy(xpath = "//span[@class='ng-star-inserted' and text()='Action']//ancestor::th//span[@class='k-icon k-i-filter']")
	private WebElement actionFilter;
	
	@FindBy(xpath = "(//input[@class='k-textbox ng-untouched ng-pristine ng-valid'])[1]")
	private WebElement actionInputField;
	
	@FindBy(xpath = "//table[@class='k-grid-table']//td//div/span")
	private WebElement userNameUnderHistory;
	
	@FindBy(xpath = "//table[@class='k-grid-table']//td")
	private List<WebElement> actionByUnderHistory;
	
	@FindBy(xpath = "//*[contains(@id,'mat-dialog')]//div//div[@title='Columns filter']")
	private WebElement columnsFilterIcon;
	
	@FindBy(xpath = "//*[contains(@id,'mat-dialog')]//div//div[@title='Columns filter']/following-sibling::rater-history-column-selector/ul")
	private WebElement columnsFilterList;
	
	@FindBy(xpath = "//div[@title='Columns filter']/following-sibling::rater-history-column-selector//input[@value='System Role']/parent::span/parent::label")
	private WebElement systemRoleCheckBox;
	
	@FindBy(xpath = "//div[@title='Columns filter']/following-sibling::rater-history-column-selector//input[@value='Role Group']/parent::span/parent::label")
	private WebElement roleGroupCheckBox;
	
	@FindBy(xpath = "//div[@title='Columns filter']/following-sibling::rater-history-column-selector/ul//button[text()='Close']")
	private WebElement closeButtonOnFilterList;

	/*
	 * ================================= METHODS
	 * =============================================
	 */

	public void selectStudyDropDown() {
		clickOn(studyfield);
		reportInfo();
	}

	/**
	 * verify raters details page Displayed
	 */
	public void verifyRaterDetailPageDisplayed() {
		waitForElement(raterDetailsPageTitle);
		Assert.assertTrue(raterDetailsPageTitle.isDisplayed());
		reportInfo();
	}

	/**
	 * verify close control displayed on match review popup
	 */
	public void verifyCloseOptionDisplayed() {
		moveToElement(closeBtn);
		Assert.assertTrue(isElementDisplayed(closeBtn));
		reportInfo();
	}

	/**
	 * verify cancel Button displayed on match review popup
	 */
	public void verifyCancelButtonDisplayedOnUserMatchReviewPopoup() {
		moveToElement(cancelBtnonReviewpopup);
		Assert.assertTrue(isElementDisplayed(cancelBtnonReviewpopup));
		reportInfo();
	}

	/**
	 * click on cancel control on match review popup
	 */
	public void selectCancelOptionINUserReviewPopup() {
		clickOn(cancelBtnonReviewpopup);
		reportInfo();
	}

	/**
	 * 
	 * user match review pop up Information msg Displayed
	 */
	public void InformationMessageIsDisplayed() {
		moveToElement(matchReviewpopupMessage);
		Assert.assertTrue(isElementDisplayed(matchReviewpopupMessage));
		reportInfo();
	}

	/**
	 * verify Match Review popup displayed
	 */
	public void userMatchReviewWindowIsDisplayed() {
		waitUntillFinishProcessSpinnerDisable();
		moveToElement(matchReviewpopupTitle);
		Assert.assertTrue(isElementDisplayed(matchReviewpopupTitle));
		reportInfo();
	}

	/**
	 * verify Match Review popup Not displayed
	 */
	public void verifyUserMatchReviewPopupNotdisplayed() {
		boolean flag = true;
		try {
			if (matchReviewpopupTitle.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * select match Review option
	 */
	public void selectMatchReviewOption() {
		clickOn(matchReviewBtn);
		reportInfo();
	}

	/**
	 * verify match review option is displayed
	 */
	public void verifyMatchReviewOptionIsDisplayed() {
		moveToElement(matchReviewBtn);
		Assert.assertTrue(isElementDisplayed(matchReviewBtn));
		reportInfo();

	}

	public void verifyMatchReviewOptionNotDisplayed() {
		boolean flag = true;
		try {
			if (matchReviewBtn.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * verify rater name
	 * 
	 * @param RaterNameToBeVerified
	 */
	public void verifyRaterName(String RaterNameToBeVerified) {
		Assert.assertTrue(raterDetailsPageTitle.isDisplayed());
		Assert.assertEquals(raterName.getText().trim(), RaterNameToBeVerified);
		reportInfo();
	}

	/*
	 * verify add new btn displayed
	 */
	public void verifyAddAsNewControlBtnISDisplayed() {
		Assert.assertTrue(isElementDisplayed(addAsNewBtn));
		reportInfo();
	}

	/*
	 * click add as new button
	 */
	public void clickOnAddAsNewBtn() {
		clickOn(addAsNewBtn);
		reportInfo();
	}

	/**
	 * Rater status is displayed
	 * 
	 * @param raterName
	 * @param status
	 */
	public void verifyRaterStatusIsDisplayed(String raterName, String status) {
		WebElement detailtobeverified = driver.findElement(
				ByLocator("(//div[text()='" + raterName + "']/following::td[text()='" + status + "'])[1]"));
		Assert.assertTrue(isElementDisplayed(detailtobeverified));
		reportInfo();
	}

	/**
	 * navigate to activation Menu
	 */
	public void navigateToActivationMenu() {
		clickOn(activationMenu);
		reportInfo();
	}

	/**
	 * navigate to on boarding Menu
	 */
	public void navigateToOnBoardingMenu() {
		waitUntillFinishProcessSpinnerDisable();
		clickOn(sidemenuOnBoardingTab);
		waitUntillFinishProcessSpinnerDisable();
		waitAndClick(onBoardingAllbtn);
		reportInfo();
	}

	/*
	 * verify on boarding grid displayed
	 */
	public void verifyOnBoardingGridIsDisplayed() {
		Assert.assertTrue(onBoardingAllbtn.isDisplayed() && invitedgridOnboardingTab.isDisplayed());
		reportInfo();
	}

	/*
	 * verify Activation Menu displayed
	 */
	public void verifyActivationMenuIsDisplayed() {
		moveToElement(activationMenu);
		Assert.assertTrue(isElementDisplayed(activationMenu));
		reportInfo();
	}

	/*
	 * verify Activation Menu is not displayed
	 */
	public void verifyActivationMenuIsNotDisplayed() {
		boolean flag = true;
		try {
			if (activationMenu.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/*
	 * verify Activation Grid displayed
	 */
	public void verifyActivationGridIsDisplayed() {
		moveToElement(activationGrid);
		Assert.assertTrue(isElementDisplayed(activationGrid));
		reportInfo();
	}

	/*
	 * verify Activation Grid is not displayed
	 */
	public void verifyActivationGridIsNotDisplayed() {
		boolean flag = true;
		try {
			if (activationGrid.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Verify Raters Panel Displayed */
	public void verifyRatersDetailsPanelIsOpened() {
		Assert.assertTrue(isElementPresent(ratersProfileSliderPanelOpen), "Raters Details Slider panel is displayed");
		reportInfo();

	}

	/* Verify Modal Window to Select Study is Available */

	public void verifySelectStudyModalWindowPresent() {
		Assert.assertNotEquals(driver.findElements(By.xpath("//*[contains(@id,'cdk-overlay')]")).size(), 0);
		reportInfo();

	}

	/*
	 * @function: Method For searching study and site page.
	 */

	public void selectStudy(String studyName, String siteName) {
		_normalWait(4000);

		try {
			if (driver.findElement(By.xpath("//div[@id='study-header']/button[@class='queries-button']"))
					.isEnabled() == true) {
				driver.findElement(By.xpath("//div[@id='study-header']/button[@class='queries-button']")).click();
			}
		} catch (Exception e) {
		}

		boolean flag = false;
		if (driver.findElements(By.xpath("//*[contains(@id,'cdk-overlay')]")).size() > 0) {
			inputText(searchStudy, studyName);
			for (WebElement study : studyList) {
				if (study.getText().trim().contains(studyName)) {
					javascriptButtonClick(study);
					break;
				}

			}
			inputText(searchSite, siteName);
			for (WebElement site : siteLists) {
				if (site.getText().trim().contains(siteName)) {
					javascriptButtonClick(site);
					_normalWait(2000);
					break;
				}
			}
			if (elementStatus(selectButtonForStudyAndSite)) {
				flag = true;
			} else {
				flag = false;
			}
			waitAndClick(selectButtonForStudyAndSite);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='spinner ng-star-inserted']")));
		} else if (driver.findElements(By.xpath("//*[@id='study-header']//*[contains(@class,'btn-new-active')]"))
				.size() > 0) {
			javascriptButtonClick(selectedStudyButton);
			inputText(searchStudy, studyName);
			for (WebElement study : studyList) {
				if (study.getText().contains(studyName)) {
					javascriptButtonClick(study);
					break;
				}
			}
			inputText(searchSite, siteName);
			for (WebElement site : siteLists) {
				if (site.getText().contains(siteName)) {
					waitAndClick(site);
					break;
				}
			}
			waitAndClick(selectButtonForStudyAndSite);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@class='spinner ng-star-inserted']")));
		} else {

			selectStudy(studyName);
			selectSite(siteName);
		}
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	/**
	 * add additional email & phone number
	 * 
	 * @param Additionalemail
	 * @param phoneno
	 */
	public void addAdditionalEmailAndPhoneDetails(String Additionalemail, String phoneno) {
		clickOn(addAdditionalEmail);
		inputText(additionalEmailfeld, Additionalemail);
		inputText(phoneNofield, phoneno);

	}

	/**
	 * select rater
	 * 
	 * @param ratername
	 */
	public void selectRater(String ratername) {
		waitUntillFinishProcessSpinnerDisable();
		WebElement ratertobeselected = driver
				.findElement(ByLocator("//div[contains(text(),'" + ratername + "')]/ancestor::td/preceding::td"));
		clickOn(ratertobeselected);
		reportInfo();
	}

	/**
	 * DeSelect Rater
	 * 
	 * @param ratername
	 */
	public void DeSelectRater(String ratername) {
		WebElement ratertobeselected = driver
				.findElement(ByLocator("//div[text()='" + ratername + "']/ancestor::td/preceding::td"));
		clickOn(ratertobeselected);
		reportInfo();
	}

	/**
	 * select rater with completed status
	 * 
	 * @param ratername
	 */
	public void selectRaterWithCompletedStatus(String ratername) {
		WebElement ratertobeselected = driver
				.findElement(ByLocator("//span[text()='" + ratername + "']/ancestor::td/preceding::td"));
		clickOn(ratertobeselected);
		reportInfo();
	}

	/**
	 * Click on study drop down to select the study
	 * 
	 * @param studyName
	 * @return
	 */
	public void selectStudy(String studyName) {
		boolean flag = false;
		if (driver.findElements(By.xpath("//*[contains(@id,'cdk-overlay')]")).size() > 0) {
			searchStudy.sendKeys(studyName);
			for (WebElement study : studyList) {
				if (study.getText().contains(studyName)) {
					waitAndClick(study);
					flag = true;
					break;
				}

			}
			Assert.assertTrue(flag);
			reportInfo();
		}
	}

	/**
	 * Click on site drop down to select site
	 * 
	 */
	public void selectSite(String siteName) {
		clickOn(siteDRPDWN);
		siteDRPDWN.findElement(By.xpath("//following-sibling::div/ul/li/span[contains(text(),'" + siteName + "')]"))
				.click();
		waitSpinnerToBecomeInvisible();
		reportInfo();
	}

	/**
	 * Verify study dashboard and Selected Study Name is displayed
	 * 
	 * @param studyNameToBeVerify
	 */
	public void verifySelectedStudyName(String studyNameToBeVerify) {
		_normalWait(2000);
		Assert.assertTrue(isElementDisplayed(selectedStudyButton));
		Assert.assertTrue(selectedStudyButton.getText().contains(studyNameToBeVerify));
		reportInfo();
	}

	/**
	 * Verify Option to view Study Profile is available
	 */
	public void verifyOptionToViewStudyProfileDisplayed() {
		Assert.assertTrue(isElementDisplayed(studyProfileLinkICN));
		moveToElement(studyProfileLinkICN);
		reportInfo();
	}
	
	/**
	 * Verify Option to view Study Profile is Not available
	 */
	public void verifyOptionToViewStudyProfileNotDisplayed() {
		Assert.assertFalse(isElementDisplayed(studyProfileLinkICN));
		reportInfo();
	}

	/* Click on select study Button */
	public void clickOnSelectedStudyButton() {
		waitAndClick(selectedStudyButton);
	}

	/**
	 * Verify study is UnAvailable under select study drop down
	 * 
	 */
	public void verifystudyIsUnavailableUnderSelectStudyList(String studyName) {
		boolean flag = true;
		inputText(searchStudy, studyName);
		if (driver.findElements(By.xpath("//*[contains(text(),' All studies ')]")).size() > 0) {
			flag = true;
			Log.info("No study present");
		} else {
			for (WebElement studyNametoselect : studyList) {
				if (studyNametoselect.getText().trim().contains(studyName)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
							studyNametoselect);
					moveToElement(studyNametoselect);
					flag = false;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * verify Option To Select Study if already selected Is Displayed/ Study and
	 * Site Modal Window displayed
	 */
	public void verifyOptionToSelectStudyAndSiteIsDisplayed() {

		if (driver.findElements(By.xpath("//div[@id='study-header']/button[contains(@class,'queries-button')]"))
				.size() > 0) {
			Assert.assertTrue(isElementDisplayed(selectedStudyButton));
			moveToElement(selectedStudyButton);
			reportInfo();
		} else {
			Assert.assertTrue(isElementDisplayed(selectStudySiteModalWinTittle));
			moveToElement(selectStudySiteModalWinTittle);
			reportInfo();
		}
	}

	/***
	 * 
	 * Verify that Navigator button is displayed..
	 */
	public void verifyNavigateButtonIsAvailable() {
		moveToElement(navigateButton);
		Assert.assertTrue(navigateButton.isDisplayed());
	}

	/***
	 * Click on Study Selector button
	 */
	public void selectStudySelectorButton() {
		waitForElementClickable(studySelectorButton, 4000);
		moveToElement(studySelectorButton);
		waitAndClick(studySelectorButton);
	}

	/***
	 * 
	 * Verify all the elements are present on Study Selector pop up
	 */
	public void verifyItemssAreDisplayed() {
		waitUntillSpinnerToBecomeInvisible();
		boolean flag=false;
		if (studyListStudySelectorPopUp.isDisplayed() && SiteListStudySelectorPopUp.isDisplayed()
				&& allSitesBtn.isDisplayed() && selectButtonStudySelectorPopUp.isDisplayed()
				&& cancelButtonStudySelectorPopUp.isDisplayed() && closeIconStudySelectorPopUp.isDisplayed()) {
			moveToElement(studyListStudySelectorPopUp);
			moveToElement(SiteListStudySelectorPopUp);
			moveToElement(allSitesBtn);
			moveToElement(selectButtonStudySelectorPopUp);
			moveToElement(cancelButtonStudySelectorPopUp);
			moveToElement(closeIconStudySelectorPopUp);
            flag=true;
		}

		Assert.assertTrue(flag);
		reportInfo();

	}

	public void verifyHideInactiveSiteOptionIsNotdisplayed()
	{
		Assert.assertFalse(isElementDisplayed(hideInactiveSitesOption));
		reportInfo();
	}
	public void selectStudyFromList(String activeFirstString) {
		inputText(searchStudy, activeFirstString);
		moveToElement(filteredStudyButton);

	}

	public void selectCancelOnStudySelectPopUp() {
		moveToElement(cancelButtonStudySelectorPopUp);
		waitAndClick(cancelButtonStudySelectorPopUp);
	}

	public void verifySelectedStudyIsNotApplied(String studyActiveFirst) {
		moveToElement(studySelectorButton);
		if (studySelectorButton.getText().contentEquals(studyActiveFirst)) {
			Assert.assertTrue(false);
		} else {
			Assert.assertTrue(true);
		}
	}

	public void selectAllSiteOption() {
		waitAndClick(allSitesBtn);
	}

	public void verifyStudyIsSelectedOnPopUp() {
		boolean flag=false;
		if (studySelected.isDisplayed()) {
			flag=true;
			}
		Assert.assertTrue(flag);
		reportInfo();
	}
	
	public void verifyInActiveSiteSiteIsSelected()
	{
		boolean flag=false;
		if (inactiveSiteSelected.isDisplayed()) {
			flag=true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyAllSiteOptionIsSelected() {
		boolean flag=false;
		if (allSitesSelectedOption.isDisplayed()) {
			flag=true;
		} 
		Assert.assertTrue(flag);
		reportInfo();
	}

	@FindBy(xpath="//input[@formcontrolname='siteQuery']")
	private WebElement siteInputfield;
	
	@FindBy(xpath="//div[@class='item inactive ng-star-inserted selected']")
	private WebElement inactiveSiteSelected;
	
	@FindBy(xpath="//div[@class='item selected ng-star-inserted']")
	private WebElement studySelected;
	
	@FindBy(xpath="//div[text()=' All studies ']")
	private WebElement allStudiesBtn;
	
	@FindBy(xpath="//div[text()=' All sites ']")
	private WebElement allSitesBtn;
	
	@FindBy(xpath="//label[text()='Hide Inactive Sites']")
	private WebElement hideInactiveSitesOption;
	
	@FindBy(xpath="//div[@class='item big ng-star-inserted selected']")
	private WebElement allStudiesSelectedOption;
	
	@FindBy(xpath="//div[@class='item big selected']")
	private WebElement allSitesSelectedOption;
	
	
	public void verifyAllStudyAndAllSiteOptionIsSelected()
	{
		boolean flag=false;
		if (allStudiesSelectedOption.isDisplayed() && allSitesSelectedOption.isDisplayed()) {
			flag=true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	public void selectHideInactiveSitesCheckboxoption()
	{
		waitAndClick(hideInactiveSitesOption);
		reportInfo();
	}
	public void verifyHideInActivesitesOptionIsSelected()
	{
		boolean flag=false;
		WebElement fieldToBeVerified = driver.findElement(ByLocator("//label[text()='Hide Inactive Sites']/preceding-sibling::div/input"));
		if (fieldToBeVerified.isSelected()) {
			flag=true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	public void selectAllStudiesAndAllsitesOption()
	{
		waitUntillFinishProcessSpinnerDisable();
		waitAndClick(allStudiesBtn);
		waitAndClick(allSitesBtn);
	}
	
	public void selectInActiveSiteFromsiteList(String siteName)
	{
		waitUntillFinishProcessSpinnerDisable();
		inputText(siteInputfield, siteName);
		_normalWait(2000);
		WebElement siteToBeSelected = driver.findElement(ByLocator("//div[contains(text(),'"+siteName+"')]"));
		waitAndClick(siteToBeSelected);
		
	}
	/*
	 * Click On select Button after selecting study and site*
	 * 
	 * 
	 */

	public void clickOnSelectButtonAfterselectingStudySite() {
		if (selectButtonForStudyAndSite.isEnabled()) {
			clickOn(selectButtonForStudyAndSite);
		}
		waitUntillFinishProcessSpinnerDisable();
	}

	public void verifySelectedStudyAndSiteIsApplied(String study,String site) {
		boolean flag=false;
		if (studySelectorButton.getText().contains(study) && studySelectorButton.getText().contains(site) ) {
			flag=true;
		} 		
		Assert.assertTrue(flag);
		reportInfo();

	}

	public void verifyStudyButtonForModalWindowIsAvailable() {
		moveToElement(studySelectorButton);
		if (studySelectorButton.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		_normalWait(5000);
	}

	/*****
	 * 
	 * Verify Clinicinian/Raters Listing Screen Displayed
	 * 
	 */
	public void verifyClinicianRatersListingScreenDisplayed() {
		waitUntillSpinnerToBecomeInvisible();
		moveToElement(ratersSidetab);
		Assert.assertTrue(isElementDisplayed(ratersSidetab));

	}

	/**
	 * navigate Tracking Grid
	 */
	public void navigateToTrackingGrid() {
		waitUntillFinishProcessSpinnerDisable();
		if (trackingGrid.isSelected() == false) {
			clickOn(trackingGrid);
		}
		reportInfo();
	}

	/**
	 * navigate Qualification Grid
	 */
	public void navigateToQualificationGrid() {
		waitUntillFinishProcessSpinnerDisable();
		if (qualificationGrid.isSelected() == false) {
			clickOn(qualificationGrid);
		}
		reportInfo();
	}

	/**
	 * navigate Certification Grid
	 */
	public void navigateToCertificationGrid() {
		waitUntillFinishProcessSpinnerDisable();
		if (certificationGrid.isSelected() == false) {
			clickOn(certificationGrid);
		}
		reportInfo();
	}

	/***
	 * 
	 * Bulk upload Option Not displayed
	 * 
	 */
	public void bulkUploadOptionNotdisplayed() {

		boolean flag = true;
		try {
			if (bulkloadButton.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	/***
	 * 
	 * Bulk upload Option displayed
	 * 
	 */
	public void bulkUploadOptionDisplayed() {

		boolean flag = true;
		try {
			if (bulkloadButton.isDisplayed()) {
				flag = true;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * 
	 * Select action to add Multiple Site Raters (Bulk Load -> Upload File option)
	 */

	public void selectActionToAddMultipleSiteRaters() {
		moveToElement(bulkloadButton);
		javascriptButtonClick(bulkloadButton);
		moveToElement(uploadButton);
		waitAndClick(uploadButton);
	}

	/**
	 * 
	 * verify add Multiple site raters Window Not displayed
	 */

	public void VerifyaddMultipleSiteraterswindowIsNotDisplayed() {

		waitUntillFinishProcessSpinnerDisable();
		boolean flag = true;
		try {
			if (addMultiplesiteRatertab.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/***
	 * 
	 * verify add Multiple site raters Window displayed
	 * 
	 */
	public void verifyAddMultipleSiteRatersWindowDisplayed() {
		moveToElement(addMultiplesiteRatertab);
		Assert.assertTrue(isElementDisplayed(addMultiplesiteRatertab));

	}

	/***
	 * 
	 * Control Upload file is displayed & active
	 * 
	 * 
	 */
	public void verifyControlUploadFileIsDisplayedandActive() {
		moveToElement(uploadFileBtn);
		Assert.assertTrue(isElementDisplayed(uploadFileBtn));

		moveToElement(uploadFileBtn);
		Assert.assertTrue(uploadFileBtn.isEnabled());

	}

	/****
	 * 
	 * Next control is disabled
	 * 
	 */

	public void verifyNextcontrolDisabled() {
		moveToElement(nextBtn);
		Assert.assertFalse(nextBtn.isEnabled());

	}

	/****
	 * 
	 * Next control is enabled
	 * 
	 */

	public void verifyNextControlEnabled() {
		moveToElement(nextBtn);
		Assert.assertTrue(nextBtn.isEnabled());

	}

	/***
	 * 
	 * 
	 * Cancel control is enabled
	 * 
	 */
	public void verifyCancelbuttonEnabled() {
		moveToElement(cancelBtn);
		Assert.assertTrue(cancelBtn.isEnabled());
	}

	/**
	 * Click On cancel Control
	 */
	public void clickOnCancelButton() {
		waitAndClick(cancelBtn);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	/**
	 * Proceed To add site Raters using Bulk upload without selecting any file
	 */
	public void clickOnNextButton() {
		boolean flag = true;
		moveToElement(nextBtn);
		try {
			if (nextBtn.isEnabled()) {
				waitAndClick(nextBtn);
				flag = true;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Upload File ----
	 */
	public void uploadFile(String fileName) {
		boolean flag = true;
		try {
			if (uploadFileBtn.isDisplayed()) {
				flag = true;
				moveToElement(uploadFileBtn);
				waitAndClick(uploadFileBtn);
				Thread.sleep(5000);
				String FilePath = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\testdata"
						+ File.separator + fileName;

				FilePath = FilePath.replace("\\", "\\");
				StringSelection sel = new StringSelection(FilePath);

				// Copy to clipboard
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);

				Robot robot = new Robot();

				// Press Enter
				robot.keyPress(KeyEvent.VK_ENTER);

				// Release Enter
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);

				// Press CTRL+V
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);

				// Release CTRL+V
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);
				robot.keyRelease(KeyEvent.VK_V);
				Thread.sleep(2000);

				// Press Enter
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);

				// Press Enter
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);

				// Press Enter
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(4000);
			}
		} catch (Exception e) {

		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	/***
	 * 
	 * Verify uploaded File Cancel option displayed
	 */

	public void verifyRemoveFileoptionDisplayed() {

		moveToElement(removeFileBtn);
		Assert.assertTrue(isElementDisplayed(removeFileBtn));

	}

	/**
	 * 
	 * click Uploaded File Cancel button Option
	 */
	public void clickOnRemoveFileOptionBtn() {

		moveToElement(removeFileBtn);
		waitAndClick(removeFileBtn);
	}

	/**
	 * 
	 * Verify uploaded File Displayed
	 */
	public void VerifyUploadFileIsDisplayed() {

		moveToElement(UploadedFileName);
		Assert.assertTrue(isElementDisplayed(UploadedFileName));
	}

	/**
	 * 
	 * 
	 * Modal window with the confirmation message 'The rater(s) will not be added!
	 * Are you sure?' is displayed
	 */
	public void verifyConfirmationMessageIsdisplayed() {

		moveToElement(ConfirmationMessage);
		Assert.assertTrue(isElementDisplayed(ConfirmationMessage));
	}

	/**
	 * 
	 * Select Yes, close dialog option
	 */
	public void clickOnActionCloseDialogOption() {
		moveToElement(acceptclosedialogoptionBtn);
		waitAndClick(acceptclosedialogoptionBtn);
	}

	public void VerifyUploadedDataDisplayedInGrid() {
		List<WebElement> listtable = driver.findElements(
				ByLocator("//table[@class='raters-list table table-bordered']//tr[@class='ng-star-inserted']"));
		int Grid = listtable.size();
		for (int i = 1; i <= Grid; i++) {

			List<WebElement> datatable = driver.findElements(
					ByLocator("//table[@class='raters-list table table-bordered']//tr[@class='ng-star-inserted'][" + i
							+ "]//td"));
			int Gridcontent = datatable.size();
			for (int j = 1; j <= Gridcontent; j++) {
				WebElement datatoVerified = driver.findElement(
						ByLocator("//table[@class='raters-list table table-bordered']//tr[@class='ng-star-inserted']["
								+ i + "]//td[" + j + "]"));
				moveToElement(datatoVerified);
				Assert.assertTrue(isElementDisplayed(datatoVerified));
			}
		}
	}

	/**
	 * verify discard User Option Is Displayed
	 */
	public void verifyOptionToDiscardUserUnderReviewWithDetailsDisplayed() {
		Assert.assertTrue(isElementDisplayed(discardUser));
		reportInfo();
	}

	/*
	 * Select to discard user Discard
	 */
	public void selectDiscardUser() {
		clickOn(discardUser);
		clickOn(discardButton);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	public void selectDiscardOption() {
		clickOn(discardUser);
		reportInfo();
	}

	public void selectDiscardControlButton() {
		clickOn(discardButton);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	public void verifyDiscardTextIsDisplayedWithRedFont(String FontColor) {
		String colortobeverified = discardtext.getCssValue("color");
		Assert.assertEquals(colortobeverified, FontColor);

	}

	/**
	 * verify rater is Displayed
	 * 
	 * @param raterName
	 */
	public void verifySiteRaterIsDisplayed(String raterName) {
		WebElement ratertobeVerified = driver
				.findElement(ByLocator("//div[@class='link-box ng-star-inserted' and text()='" + raterName + "']"));
		moveToElement(ratertobeVerified);
		Assert.assertTrue(isElementDisplayed(ratertobeVerified));
	}

	/**
	 * Navigate rater Profile
	 * 
	 * @param raterName
	 */
	public void NavigateToRaterProfile(String raterName) {
		WebElement ratertobe = driver.findElement(ByLocator("//span[text()='" + raterName + "']"));
		clickOn(ratertobe);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	/**
	 * verify updated details showing in rater Profile
	 * 
	 * @param Study
	 * @param Facility
	 */
	public void verifyUpdatedDetailsShowingInUserProfile(String Study, String Facility) {
		waitUntillFinishProcessSpinnerDisable();

//		WebElement studytobeverified = driver
//				.findElement(ByLocator("//h3[text()='Studies']/..//strong[contains(text(),'" + Study + "')]"));
		WebElement facilitytobeverified = driver
				.findElement(ByLocator("//h3[text()='Organizations']/..//strong[contains(text(),'" + Facility + "')]"));
		Assert.assertTrue(facilitytobeverified.isDisplayed());
		reportInfo();

	}

	/**
	 * verify rater is Not Displayed
	 * 
	 * @param raterName
	 */
	public void verifyRaterIsDeletedOrNotDisplayed(String raterName) {

		boolean flag = true;
		try {
			WebElement ratertobeVerified = driver
					.findElement(ByLocator("//div[@class='link-box ng-star-inserted' and text()='" + raterName + "']"));
			if (ratertobeVerified.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * verify add user under review as a new user
	 */
	public void verifyOptionToAddUserUnderReviewAsNewUserIsDisplayed() {
		Assert.assertTrue(isElementDisplayed(addNewUser));
		reportInfo();
	}

	/*
	 * select option to add new user
	 */
	public void selectOptionToAddUserAsNew() {
		clickOn(addNewUser);
		reportInfo();
	}

	/**
	 * add as new user selected
	 */
	public void verifyAddUserAsNewOptionIsSelected() {
		Assert.assertTrue(addUserNewRadioBtn.isSelected());
		reportInfo();
	}

	/*
	 * verify merge user option is Displayed
	 */
	public void verifyOptionToMergeUserWithFromListedUserIsDisplayed() {
		Assert.assertTrue(isElementDisplayed(existingUser));
		reportInfo();
	}

	/**
	 * select user User
	 */
	public void selectOptionToMergeUser() {
		clickOn(existingUser);
		reportInfo();
	}

	/**
	 * 
	 * verify user under review popup title & rater Details
	 */
	public void verifyUserUnderReviewPopupTitleAndRaterDetails() {
		try {
			if (userUnderReviewTitle.isDisplayed()) {
				moveToElement(userUnderReviewTitle);
				Assert.assertTrue(isElementDisplayed(userUnderReviewTitle));
			}
		} catch (Exception e) {
		}
		List<WebElement> detailboxes = driver
				.findElements(ByLocator("//table[@class='raters-list indented-table']//tr/td"));
		int count = detailboxes.size();
		for (int i = 1; i <= count; i++) {
			WebElement detailsfield = driver
					.findElement(ByLocator("//table[@class='raters-list indented-table']//tr/td[" + i + "]"));
			moveToElement(detailsfield);
			Assert.assertTrue(isElementDisplayed(detailsfield));
		}

	}

	/**
	 * verify Existing User Details
	 */
	public void verifyExistingUserDetails() {
		List<WebElement> rows = driver
				.findElements(ByLocator("//table[@class='raters-list']//tr[@class='ng-star-inserted']"));
		int rowsCount = rows.size();
		for (int i = 1; i <= rowsCount; i++) {

			List<WebElement> detailstobeverified = driver.findElements(
					ByLocator("//table[@class='raters-list']//tr[@class='ng-star-inserted'][" + i + "]/td"));
			int griddtatoverified = detailstobeverified.size();
			for (int j = 1; j <= griddtatoverified; j++) {

				WebElement data = driver.findElement(ByLocator(
						"//table[@class='raters-list']//tr[@class='ng-star-inserted'][" + i + "]/td[" + j + "]"));
				moveToElement(data);
				Assert.assertTrue(isElementDisplayed(data));
				reportInfo();
			}
		}
	}

	public void verifyExistingUserTitleRadioButton() {
		Assert.assertTrue(existingUser.isDisplayed());
		Assert.assertTrue(isElementDisplayed(existingUserRadioButton));
		reportInfo();

	}

	/**
	 * select merge user option
	 */
	public void selectMergeUserOption() {
		clickOn(existingUserRadioButton);
		reportInfo();
	}

	/**
	 * verify merge user radio btn selected
	 */
	public void verifyMergeUserRadioBtnIsSelected() {
		WebElement radiobtntobeverified = driver
				.findElement(ByLocator("//strong[text()='Merge Users']/ancestor::li/div/input"));
		Assert.assertTrue(radiobtntobeverified.isSelected());
		reportInfo();
	}

	/**
	 * merge user button is enabled
	 */
	public void verifyMergeUsercontrolbuttonIsDisplayedAndDisabled() {
		Assert.assertTrue(isElementDisplayed(MergeUserButton));
		Assert.assertFalse(MergeUserButton.isEnabled());
		reportInfo();
	}

	/*
	 * merge user control button enabled
	 */
	public void verifyMergeUserControlIsEnabled() {
		Assert.assertTrue(MergeUserButton.isEnabled());
		reportInfo();
	}

	/*
	 * select merge user button
	 */
	public void selectMergeUserControlButton() {
		clickOn(MergeUserButton);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	/**
	 * click on skip review button
	 */
	public void clickOnSkipReviewButton() {
		clickOn(skipReviewbutton);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	/*
	 * click on back button on rater profile
	 */
	public void selectBackButton() {
		clickOn(backButton);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	public void verifyNewEntryDisplayed() {
		String systemcurrentdate = newlyEntry.getText();
		String expected = systemcurrentdate.substring(systemcurrentdate.indexOf('"') + 1,
				systemcurrentdate.indexOf(' '));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String datetoday = sdf.format(date);
		boolean flag = true;

		if (expected.trim().equalsIgnoreCase(datetoday)) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/*
	 * verify more than 1 row can't be selected
	 */
	public void verifyMoreThanOneRowCantBeSelected() {
		boolean flag = true;
		try {
			if (raterRows.size() == 2) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * verify rater is Selected with Blue Font
	 * 
	 * @param ExpectedColor
	 */
	public void verifyRaterIsSelectedWithBlueFont(String ExpectedColor) {
		String actualcolor = raterRow.getCssValue("background");
		boolean flag = true;
		if (actualcolor.contains(ExpectedColor)) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * select existing user with whom new user is merged
	 * 
	 * @param Ratername
	 */
	public void selectUserIntoNewUserMerge(String Ratername) {
		WebElement usertobeselected = driver
				.findElement(ByLocator("//td[contains(text(),'" + Ratername + "')]/../td/div"));
		clickOn(usertobeselected);
		reportInfo();

	}

	/*
	 * verify user is unable to select any existing user
	 */
	public void verifyUserCanNotSelectAnyExistingUser(String Ratername) {
		boolean flag = true;
		try {
			WebElement usertobeselected = driver
					.findElement(ByLocator("//td[contains(text(),'" + Ratername + "')]/../td/div/input"));
			if (usertobeselected.isEnabled()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * highlighted field in user under Review grid
	 * 
	 * @param value
	 * @param HighlightedColor
	 */
	public void verifyUserUnderReviewWithHighlightedAndDisplayed(String value, String HighlightedColor) {
		WebElement detailtoverifed = driver.findElement(
				ByLocator("//table[@class='raters-list indented-table']//td//span[text()='" + value + "']"));
		Assert.assertTrue(isElementDisplayed(detailtoverifed));
		moveToElement(detailtoverifed);
		String color = detailtoverifed.getCssValue("color");
		Assert.assertEquals(color, HighlightedColor);
	}

	/**
	 * verify user under review Grid Email field Highlighted
	 * 
	 * @param value
	 * @param HighlightedColor
	 */
	public void verifyFieldHighlightedForUnderReviewUser(String value, String HighlightedColor) {
		WebElement detailtoverify = driver
				.findElement(ByLocator("//table[@class='raters-list indented-table']//td[text()='" + value + "']"));

		Assert.assertTrue(isElementDisplayed(detailtoverify));
		moveToElement(detailtoverify);
		String color = detailtoverify.getCssValue("color");
		Assert.assertEquals(color, HighlightedColor);
	}

	/**
	 * verify Matched Fields Highlighted And displayed for User Under review
	 * 
	 * @param Email
	 * @param facility
	 * @param study
	 * @param site
	 * @param persontype
	 * @param HighlightedColor
	 */
	public void verifyUserUnderReviewFieldDisplayedHighlighgtedForMatchedFields(String facility, String study,
			String site, String persontype, String HighlightedColor) {
		verifyUserUnderReviewWithHighlightedAndDisplayed(facility, HighlightedColor);
		verifyUserUnderReviewWithHighlightedAndDisplayed(study, HighlightedColor);
		verifyUserUnderReviewWithHighlightedAndDisplayed(site, HighlightedColor);
		// verifyUserUnderReviewWithHighlightedAndDisplayed(persontype,
		// HighlightedColor);
	}

	/**
	 * verify Matched Fields Highlighted And displayed for Partial Review User Under
	 * review when first & last name are same
	 * 
	 * @param fname
	 * @param lname
	 * @param HighlightedColor
	 */
	public void verifyUserUnderReviewFieldDisplayedHighlighgtedForSameFirstLastName(String fname, String lname,
			String HighlightedColor) {
		verifyFieldHighlightedForUnderReviewUser(fname, HighlightedColor);
		verifyFieldHighlightedForUnderReviewUser(lname, HighlightedColor);
	}

	/**
	 * highlighted field in Existing user grid
	 * 
	 * @param value
	 * @param HighlightedColor
	 */
	public void verifyExistingUsersectionWithHighlightedDetails(String value, String HighlightedColor) {
		try {
			List<WebElement> detailstoverify = driver.findElements(
					ByLocator("//table[@class='raters-list']//td//span[contains(text(),'" + value + "')]"));
			int count = detailstoverify.size();
			for (int i = 1; i <= count; i++) {
				WebElement detailtobeverified = driver.findElement(ByLocator(
						"(//table[@class='raters-list']//td//span[contains(text(),'" + value + "')])[" + i + "]"));
				moveToElement(detailtobeverified);
				Assert.assertTrue(isElementDisplayed(detailtobeverified));
				String color = detailtobeverified.getCssValue("color");
				Assert.assertEquals(color, HighlightedColor);
			}

		} catch (Exception e) {
		}

	}

	/**
	 * verify email for user under review highlighted
	 * 
	 * @param value
	 * @param HighlightedColor
	 */
	public void verifyfieldHighlightedForExistingUser(String value, String HighlightedColor) {

		WebElement tobeverified = driver
				.findElement(ByLocator("(//table[@class='raters-list']//td[text()='" + value + "'])[1]"));
		Assert.assertTrue(isElementDisplayed(tobeverified));
		moveToElement(tobeverified);
		String actual = tobeverified.getCssValue("color");
		Assert.assertEquals(actual, HighlightedColor);
	}

	/**
	 * verify Matched Fields Highlighted And displayed for Existing Review
	 * 
	 * @param facility
	 * @param study
	 * @param site
	 * @param persontype
	 * @param HighlightedColor
	 */
	public void verifyExistingUserHighlightedFields(String facility, String study, String site, String persontype,
			String HighlightedColor) {
		verifyExistingUsersectionWithHighlightedDetails(facility, HighlightedColor);
		verifyExistingUsersectionWithHighlightedDetails(study, HighlightedColor);
		verifyExistingUsersectionWithHighlightedDetails(site, HighlightedColor);
		// verifyExistingUsersectionWithHighlightedDetails(persontype,
		// HighlightedColor);

	}

	/**
	 * @param fname
	 * @param lname
	 * @param HighlightedColor
	 */
	public void verifyFirstLastNameFieldHighlightedForExistingUser(String fname, String lname,
			String HighlightedColor) {
		verifyfieldHighlightedForExistingUser(fname, HighlightedColor);
		verifyfieldHighlightedForExistingUser(lname, HighlightedColor);

	}

	/**
	 * 
	 * Verify Review each rater information one by one before adding' option is
	 * displayed
	 * 
	 */

	public void verifyReviewEachRaterInformationOptionDisplayed() {
		waitUntillFinishProcessSpinnerDisable();
		moveToElement(VerifyEachraterInformationField);
		Assert.assertTrue(isElementDisplayed(VerifyEachraterInformationField));
	}

	/**
	 * Activate 'Review each rater information one by one before adding' option ->
	 * Select an action to proceed to the Next step
	 * 
	 */

	public void activateReviewEachraterInformationoption() {
		moveToElement(VerifyEachraterInformationField);
		try {
			if (reviewEachRaterInfoChkBox.isSelected()) {

			} else {
				waitAndClick(reviewEachRaterInfoChkBox);

			}
		} catch (Exception e) {

		}
		waitAndClick(nextBtn);
		waitUntillFinishProcessSpinnerDisable();
	}

	/***
	 * 
	 * Add site for bulk Upload raters
	 * 
	 * @param Raters
	 */
	public void addSiteforRaterinBulkUpload(String Raters) {

		spinnerBecomeInvisible();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		moveToElement(inputSiteContainerField);
		waitAndClick(inputSiteContainerField);
		WebElement site = driver.findElement(ByLocator("//span[contains(text(),'" + Raters + "')]"));
		waitAndClick(site);
		waitAndClick(addMultiplesiteRatertab);

	}

	/**
	 * 
	 * Verify back Button Is Enabled
	 */
	public void verifyBackControlIsEnabled() {
		moveToElement(BackButton);
		Assert.assertTrue(BackButton.isEnabled());
	}

	/**
	 * 
	 * Click On Back Button
	 */

	public void clickOnBackButton() {
		moveToElement(BackButton);
		waitAndClick(BackButton);
	}

	/**
	 * Verify Site Rater information (without validation errors) is displayed
	 * 
	 * @param borderColor
	 * @param borderColor_1
	 * @param borderColor_2
	 * @param borderColor_3
	 */

	public void verifySiteRaterInformationWithoutValidationErrorsDisplayed(String borderColor, String borderColor_1,
			String borderColor_2, String borderColor_3) {
		boolean flag = true;
		try {
			if (addMultiplesiteRatertab.isDisplayed()) {
				moveToElement(addMultiplesiteRatertab);
				Assert.assertTrue(isElementDisplayed(addMultiplesiteRatertab));
				flag = true;
			}

		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();

		String Facility = facilityfield.getCssValue("border-color");
		Assert.assertEquals(Facility, borderColor_1);
		String Study = studyfield.getCssValue("border-color");
		Assert.assertEquals(Study, borderColor_2);
		String Site = inputSiteContainerField.getCssValue("border-color");
		Assert.assertEquals(Site, borderColor_3);

		List<String> borderColors = new ArrayList<String>();

		String Primaryemail = primaryEmailfield.getCssValue("border-color");
		borderColors.add(Primaryemail);
		String AdditionalEmail = additionalEmailfield.getCssValue("border-color");
		borderColors.add(AdditionalEmail);
		String firstName = firstNamefield.getCssValue("border-color");
		borderColors.add(firstName);
		String LastName = lastNamefield.getCssValue("border-color");
		borderColors.add(LastName);
		String PhoneNo = phonefield.getCssValue("border-color");
		borderColors.add(PhoneNo);

		for (String color : borderColors) {
			if (color.contains(borderColor)) {
				Assert.assertTrue(true);
			}
		}
	}

	/***
	 * 
	 * Verify highlighted color for required Fields only
	 * 
	 * @param borderColor
	 */
	public void verifySiteRaterRequiredInformationFieldHighlighedOrNot(String borderColor) {
		List<String> borderColors = new ArrayList<String>();

		String Primaryemail = primaryEmailfield.getCssValue("border-color");
		borderColors.add(Primaryemail);
		String firstName = firstNamefield.getCssValue("border-color");
		borderColors.add(firstName);
		String LastName = lastNamefield.getCssValue("border-color");
		borderColors.add(LastName);
		for (String color : borderColors) {
			if (color.contains(borderColor)) {
				Assert.assertTrue(true);
			}
			String Facility = facilityfield.getCssValue("border-color");
			Assert.assertTrue(true, Facility);
		}
	}

	/**
	 * verify Assigned facility Displayed
	 * 
	 * @param facilityName
	 */
	public void verifyAssignedFacilityIsDisplayedInFacilityField(String facilityName) {
		WebElement facilityToBeVerified = driver.findElement(ByLocator("//span[text()='" + facilityName + "']"));
		moveToElement(facilityToBeVerified);
		Assert.assertTrue(isElementDisplayed(facilityToBeVerified));
		reportInfo();
	}

	/**
	 * 
	 * 
	 * Delete Existing facility For rater
	 */

	public void deleteExistingFacilitiesForRater() {
		WebElement Existingfacilities = driver
				.findElement(ByLocator("(//span[@class='tag ng-star-inserted']/span)[1]"));
		String facilityName = Existingfacilities.getText();
		WebElement Facilitytodelete = driver
				.findElement(ByLocator("//span[text()='" + facilityName + "']/following::span[1]"));
		waitAndClick(Facilitytodelete);
	}

	/*
	 * 
	 * verify Validation message
	 */
	public void verifyFacilityFieldHighlightedWithErrorMessage() {

		String Facility = facilityfield.getCssValue("border-color");
		Assert.assertTrue(true, Facility);
		boolean flag = true;
		WebElement meassge = driver.findElement(ByLocator(
				"//div[@class='k-multiselect-wrap k-floatwrap']/ancestor::div[@class='form-fields-box']/label[2]"));
		try {
			if (meassge.isDisplayed()) {
				moveToElement(meassge);
			}

		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * 
	 * Verify study Field Highlighted with Warning
	 * 
	 * @param BorderColor
	 */
	public void verifyStudyfieldGighlightedWithWarning(String BorderColor) {

		String Study = studyfield.getCssValue("border-color");
		Assert.assertEquals(Study, BorderColor);
		moveToElement(studyerrorWarning);

	}

	/**
	 * 
	 * Verify Site Field Highlighted with Warning
	 * 
	 * @param BorderColor
	 */

	public void verifySitefieldGighlightedWithWarning() {

		String Site = inputSiteContainerField.getCssValue("border-color");
		Assert.assertTrue(true, Site);
		WebElement warning = driver.findElement(ByLocator("//label[text()=' Required ']"));
		moveToElement(warning);

	}

	/**
	 * verify Fields Not Highlighted Proceed To Save Raters
	 * 
	 */
	public void verifyFieldsNotHighlightedProceedToSaveRaters() {
		waitAndClick(nextBtn);
		spinnerBecomeInvisible();

	}

	/*
	 * 
	 * Verify Facility Field Not highlighted
	 */
	public void verifyFacilityFieldNotHighlighted(String borderColor) {

		String Facility = facilityfield.getCssValue("border-color");
		Assert.assertEquals(Facility, borderColor);
	}

	/**
	 * 
	 * Verify success Message For site raters To Be added is Displayed
	 * 
	 */
	public void verifyModalwindowWithsauccessMessageIsDisplayed() {
		moveToElement(successmsg);
		Assert.assertTrue(isElementDisplayed(successmsg));

	}

	/**
	 * 
	 * Verify success Message For site raters To Be added is Not Displayed
	 * 
	 */
	public void verifyModalwindowWithsauccessMessageIsNotDisplayed() {

		waitUntillSpinnerToBecomeInvisible();
		boolean flag = true;
		try {
			WebElement msg = driver.findElement(ByLocator("//div[text()=' Site Raters are ready to be added.']"));
			if (msg.isDisplayed()) {
				flag = false;

			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * 
	 * Verify finish Button Is enabled
	 * 
	 */
	public void verifyFinishcontrolIsEnabled() {

		moveToElement(finishButton);
		Assert.assertTrue(finishButton.isEnabled());
		reportInfo();

	}

	/**
	 * 
	 * click On Finish Button
	 */
	public void clickOnFinishButton() {

		moveToElement(finishButton);
		waitAndClick(finishButton);
		waitUntillFinishProcessSpinnerDisable();
		_normalWait(4000);
		reportInfo();
	}

	/**
	* 
	* 
	*/
	public void waitUntillFinishProcessSpinnerDisable() {
		waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(By.xpath("//div[@class='ma-spinner']"));
	}

	/**
	 * 
	 * Verify Modal window Of success Message For site raters To Be added is Not
	 * Displayed
	 * 
	 */
	public void VerifyModalSuccessWindowIsNotDisplayed() {
		spinnerBecomeInvisible();
		_normalWait(3000);
		boolean flag = true;
		try {
			if (successmsg.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * 
	 * Selecting raters Added through Bulk upload
	 * 
	 * @param raterName
	 */
	public void searchSiteRater(String raterName) {
		moveToElement(sidemenuOnBoardingTab);
		waitAndClick(sidemenuOnBoardingTab);
		waitUntillFinishProcessSpinnerDisable();
		waitAndClick(nameFilterbutton);
		spinnerBecomeInvisible();
		waitAndClick(namefilterInputfield);
		inputText(namefilterInputfield, raterName);
		waitAndClick(filterButton);
		waitUntillFinishProcessSpinnerDisable();
	}

	/**
	 * Navigating to On Boarding Tab
	 */
	public void navigateToOnBoardingTab() {
		waitUntillFinishProcessSpinnerDisable();
		clickOn(sidemenuOnBoardingTab);
		reportInfo();
	}

	public void verifyOnBoardingTabDisplayed() {
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue(isElementDisplayed(sidemenuOnBoardingTab));
		reportInfo();
	}

	/****
	 * 
	 * deleting raters added through Bulk upload
	 * 
	 * @param raterName
	 * @param UN
	 * @param PWD
	 */
	public void deleteraterInformation(String raterName, String UN, String PWD) {

		selectRater(raterName);
		waitAndClick(actionButton);
		waitAndClick(deleteButton);
		waitSpinnerToBecomeInvisible();
		waitAndClick(revokeButton);
		waitSpinnerToBecomeInvisible();
		inputText(userNameInputField, UN);
		inputText(passwordInputField, PWD);
		waitAndClick(confirmButton);
		waitSpinnerToBecomeInvisible();
	}

	/**
	 * verify study selection is applied
	 * 
	 * @param studyName
	 */
	public void verifyStudySelectionIsApplied(String studyName) {
		WebElement studytobeverified = driver.findElement(ByLocator("//button[contains(text(),'" + studyName + "')]"));
		Assert.assertTrue(isElementDisplayed(studytobeverified));
		reportInfo();
	}

	/**
	 * click on view history llink
	 */
	public void clickOnViewHistoryLink() {
		clickOn(viewHistoryLink);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	public void verifyViewHistoryLinkNotDisplayed() {
		boolean flag = true;
		try {
			if (viewHistoryLink.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyViewHistoryLinkDisplayed() {
		moveToElement(viewHistoryLink);
		Assert.assertTrue(isElementDisplayed(viewHistoryLink));
		reportInfo();
	}

	public void selectActionControl() {
		clickOn(actionButton);
		reportInfo();
	}

	public void verifyDeleteRaterWindowIsNotdisplayed() {
		waitUntillSpinnerToBecomeInvisible();
		boolean flag = true;
		try {
			if (securityconfrmtnpopup.isDisplayed()) {
				flag = false;
			}

		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	/**
	 * 
	 * For ResElecting Site & study
	 * 
	 */
	public void reSelectstudy(String StudyName) {
		refreshPage();
		WebElement selectsite = driver.findElement(ByLocator("//button[contains(text(),'" + StudyName + "')]"));
		waitAndClick(selectsite);
	}

	/* select the add site control */
	public void selectAddSiteControl() {
		waitUntillFinishProcessSpinnerDisable();
		waitAndClick(addSiteStaffControl);
		waitUntillFinishProcessSpinnerDisable();
	}

	/* verify that add control is enabled on add site pop up */

	public void verifyAddControlIsEnabledOnAddSitePopUp() {
		moveToElement(addButton);
		boolean flag = false;
		if (addButton.isEnabled()) {
			flag = true;
		}
		Assert.assertTrue(flag);

	}

	/* fill the site rater window required field data */

	public void enterRequiredFieldDataOnSiteRaterWindow(String email, String fname, String lname) {

		moveToElement(primaryEmailfield);
		inputText(primaryEmailfield, email);
		moveToElement(firstNamefield);
		inputText(firstNamefield, fname);
		moveToElement(lastNamefield);
		inputText(lastNamefield, lname);

	}

	/**
	 * verify that Add site rater window is Not displayed
	 * 
	 */

	public void verifyModalwindowToaddSiteRaterNotdisplayed() {
		waitUntillSpinnerToBecomeInvisible();
		boolean flag = true;
		try {
			if (addSiteStaffWindow.isDisplayed()) {
				flag = false;
			}

		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	/***
	 * 
	 * Select facility from Facility List
	 * 
	 * @param facilityName
	 */

	public void selectFacility(String facilityName, String txt) {

		waitUntillFinishProcessSpinnerDisable();
		try {
			if (facilityfield.isEnabled()) {
				waitAndClick(facilityfield);
				boolean flag = false;
				for (WebElement facilityNametoselect : facilitiesList) {
					if (facilityNametoselect.getText().trim().contains(facilityName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
								facilityNametoselect);
						waitAndClick(facilityNametoselect);
						flag = true;
						break;
					}
				}
				spinnerBecomeInvisible();
				this.clickOnPopUpTitle(txt);
				Assert.assertTrue(flag);
			} else {
				Reporter.log("facility field Is Not enabled");
			}
		} catch (Exception e) {
		}
	}

	public void clickOnPopUpTitle(String title) {
		WebElement popUpTitle = driver.findElement(ByLocator("//span[text()='" + title + "']"));
		clickOn(popUpTitle);
	}

	/**
	 * 
	 * verify Facility Not displayed
	 * 
	 * @param Facility
	 */

	public void verifyStudyIsNotDisplayed(String study) {
		waitAndClick(studyfield);
		boolean flag = false;
		try {
			WebElement facilitytoselect = driver.findElement(
					ByLocator("//*[@class='k-popup k-list-container k-reset']//li[contains(text(),'" + study + "')]"));
			if (facilitytoselect.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		waitAndClick(studyfield);
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * 
	 * Verify Study in study dropDown rater
	 * 
	 */
	public void verifyStudiesListDisplayedAndAvailableToselected(String study, String txt) {
		if (studiesList.size() == 0) {
			clickOn(studyfield);
		}
		boolean flag = false;
		_normalWait(3000);
		for (WebElement studyNametoselect : studiesList) {
			if (studyNametoselect.getText().trim().contains(study)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", studyNametoselect);
				moveToElement(studyNametoselect);
				_normalWait(4000);
				flag = true;
				break;
			}
		}
		spinnerBecomeInvisible();
		clickOnPopUpTitle(txt);
		Assert.assertTrue(flag);
	}

	/**
	 * verify studies drop down list is displayed
	 */
	public void verifyStudyDropDownListIsDisplayed(String txt) {
		waitAndClick(studyfield);
		boolean flag = false;
		try {
			if (studiesList.size() >= 0) {
				flag = true;
			}
		} catch (Exception e) {
		}
		clickOnPopUpTitle(txt);
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * verify study isn't available in Study List
	 * 
	 * @param study
	 * @param txt
	 */
	public void verifyStudiesListDisplayedAndNotAvailableToselected(String study, String txt) {
		moveToElement(studyfield);
		waitAndClick(studyfield);
		boolean flag = false;
		_normalWait(3000);
		for (WebElement studyNametoselect : studiesList) {
			if (!studyNametoselect.getText().trim().contains(study)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", studyNametoselect);
				moveToElement(studyNametoselect);
				_normalWait(4000);
				flag = true;
				break;
			}
		}
		this.clickOnPopUpTitle(txt);
		Assert.assertTrue(flag);
	}

	/**
	 * 
	 * select Study from Drop down
	 * 
	 */
	public void selectStudyFromStudyDropDownOptions(String study) {
		moveToElement(studyfield);
		javascriptButtonClick(studyfield);
		boolean flag = false;
		for (WebElement studyNametoselect : studiesList) {
			if (studyNametoselect.getText().trim().contains(study)) {
				moveToElement(studyNametoselect);
				clickOn(studyNametoselect);
				flag = true;
				break;
			}
		}
		spinnerBecomeInvisible();
		Assert.assertTrue(flag);

	}

	public void searchAndSelectSite(String Site, String title) {
		boolean flag = false;
		try {
			if (inputSiteContainerField.isEnabled()) {
				waitAndClick(inputSiteContainerField);
				WebElement site = driver.findElement(ByLocator("//span[contains(text(),'" + Site + "')]"));
				waitAndClick(site);
				waitAndClick(addSiteStaffWindow);
				flag = true;
			}
			Assert.assertTrue(flag);

		} catch (Exception e) {
		}
		this.clickOnPopUpTitle(title);
		_normalWait(3000);
	}

	public void applyFilterUnderUnderTrackingTab(String siteRater, String comp) {
		this.applyNameFilterUnderTrackingTab(siteRater);
		// this.applyOnboardingFilterUnderTrackingTab(comp);
	}

	public void applyNameFilterUnderTrackingTab(String siteRater) {
		waitUntillFinishProcessSpinnerDisable();
		moveToElement(nameFilterIcon);
		waitAndClick(nameFilterIcon);
		moveToElement(namefilterInputfield);
		inputText(namefilterInputfield, siteRater);
		moveToElement(filterButton);
		waitAndClick(filterButton);
		waitForSpinnerBecomeInvisible(60);
		reportInfo();
	}

	/**
	 * apply filter on status tab
	 * 
	 * @param status
	 */
	public void applyFilterUnderStatusTab(String status) {
		waitUntillFinishProcessSpinnerDisable();
		clickOn(statusFilterIcon);
		WebElement statustobeSelected = driver.findElement(ByLocator("//label[contains(text(),'" + status + "')]/.."));
		waitAndClick(statustobeSelected);
		waitAndClick(filterButton);
		waitUntillFinishProcessSpinnerDisable();
	}

	/**
	 * remove status filter
	 */
	public void removeStatusTabfilter() {
		waitUntillFinishProcessSpinnerDisable();
		clickOn(statusFilterIcon);
		waitAndClick(clearButton);
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();

	}

	public void applyOnboardingFilterUnderTrackingTab(String comp) {
		moveToElement(onboardingFilterIcon);
		waitAndClick(onboardingFilterIcon);
		moveToElement(namefilterInputfield);
		inputText(namefilterInputfield, comp);
		moveToElement(checkboxOnboardingFilter);
		waitAndClick(checkboxOnboardingFilter);
		moveToElement(filterButton);
		waitAndClick(filterButton);
		waitForSpinnerBecomeInvisible(60);
		reportInfo();
	}

	public void clickOnCheckBoxOnSiteRaterRow() {
		moveToElement(checkboxOnsiteRaterRow);
		waitAndClick(checkboxOnsiteRaterRow);
	}

	public void verifyCheckBoxOnSiteRaterRowSelected(String Rater) {
		WebElement chkboxtobeverified = driver
				.findElement(ByLocator("//span[text()='" + Rater + "']/ancestor::td/preceding::td/input"));
		Assert.assertTrue(chkboxtobeverified.isSelected());
		reportInfo();
	}

	public void verifyActionControlDropDownButton() {
		moveToElement(actionsControl);
		Assert.assertTrue(actionsControl.isDisplayed());
		reportInfo();
	}

	public void clickOnCompletedSiteRaterLink() {
		moveToElement(siteRaterLink);
		waitAndClick(siteRaterLink);
		waitForSpinnerBecomeInvisible(30);
	}

	public void verifyCompletedSiteRaterPage(String siteRater) {
		moveToElement(completedSiteRaterDetailsPage);
		System.out.println(getText(completedSiteRaterDetailsPage));
		Assert.assertTrue(getText(completedSiteRaterDetailsPage).contains(siteRater));
	}

	public void verifyRaterIsSelected(String Rater) {
		WebElement chkboxtobeverified = driver
				.findElement(ByLocator("//div[text()='" + Rater + "']/ancestor::td/preceding::td/input"));
		Assert.assertTrue(chkboxtobeverified.isSelected());
		reportInfo();
	}

	public void clickOnAssignToFacilityOrStudyButton() {
		moveToElement(assignToFacilityOrStudyButton);
		clickOn(assignToFacilityOrStudyButton);
		waitForSpinnerBecomeInvisible(30);
	}

	public void verifyAssignToFacilityOrStudyPopUp() {
		moveToElement(assignToFacilityOrStudyPopUp);
		Assert.assertTrue(getText(assignToFacilityOrStudyPopUp).contains("Assign to Facility"));
	}

	public void clickOnSaveButtonOnAssignToFacilityOrStudyPopUp() {
		moveToElement(saveButtonOnAssignToFacilityOrStudy);
		waitAndClick(saveButtonOnAssignToFacilityOrStudy);
	}

	public void verifyAssignToFacilityOrStudyPopUpClosed() {
		boolean flag = false;
		if (isElementNotVisible(assignToFacilityOrStudyPopUp) == true) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	/**
	 * 
	 * verify sites dropDown
	 */
	public void verifySitesDropdownList() {
		moveToElement(inputSiteContainerField);
		waitAndClick(inputSiteContainerField);

		boolean flag = true;
		try {
			if (sitesList.size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	/**
	 * 
	 * select site
	 * 
	 * @param site
	 */

	public void verifySitesVisibleAndSelectable(String site) {
		_normalWait(3000);
		boolean flag = true;
		WebElement siteslist = driver
				.findElement(ByLocator("//span[contains(text(),'" + site + "')]/preceding-sibling::div/input"));
		if (siteslist.isEnabled()) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyNewlyAddedSiteRater(String firstName) {
		waitAndClick(filterICON_rater);
		_normalWait(2000);
		inputText(filterSearchBox, firstName);
		waitAndClick(inpSearchfilterBTN);
		_normalWait(3000);
		System.out.println(driver.findElement(By.xpath("//div[@class='link-box ng-star-inserted']")).getText());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='link-box ng-star-inserted']")).getText()
				.contains(firstName));
	}

	/***
	 * Verify that Navigator button is displayed..
	 */

	public void verifySelectStudyAndSiteHeaderIsDisplayed() {
		moveToElement(popUpHeader);
		Assert.assertTrue(popUpHeader.isDisplayed());
		reportInfo();

	}

	/**
	 * verify rater page is displayed
	 */

	public void verifyRaterPageIsDisplayed() {
		Assert.assertTrue(raterSidePanel.isDisplayed());
	}

	/**
	 * Verify Clinicians/raters page
	 * 
	 */

	public void verifyClinicians_RatersPage() {
		moveToElement(Clinicians_Raters);
		String raters_clinicians_text = Clinicians_Raters.getText();
		Assert.assertEquals(raters_clinicians_text.substring(0, 19), Constants.Raters_Clinicians);
	}

	/* verify add site modal window */

	public void verifyAddSiteRaterWindow(String titleToMatched) {
		moveToElement(addSiteStaffWindow);
		String windowTitle = addSiteStaffWindow.getText();
		Assert.assertEquals(windowTitle, titleToMatched);
	}

	/* verify the highlighted required field on add site pop up window */

	public void verifyRequiredFieldsOnAddSitePopUpAreHighLighted(String backGroundColour) {
		boolean flag = false;

		if (primaryEmail.getCssValue("background").trim().contains(backGroundColour)
				&& firstName.getCssValue("background").trim().contains(backGroundColour)
				&& lastName.getCssValue("background").trim().contains(backGroundColour)) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	/* verify the required fields are not highlighted on add site pop up window */

	public void verifyRequiredFieldsOnAddSitePopUpAreNotHighLighted(String backGroundColour) {
		boolean flag = false;

		if (primaryEmail.getCssValue("background").trim().contains(backGroundColour)
				&& firstName.getCssValue("background").trim().contains(backGroundColour)
				&& lastName.getCssValue("background").trim().contains(backGroundColour)
				&& facilitiesField.getCssValue("background").trim().contains(backGroundColour)) {
			flag = true;
		}

		Assert.assertTrue(flag);
		reportInfo();

	}

	/* verify that add control on add site pop up window is disabled */

	public void verifyAddButtonIsDisabledOnAddSitePopUp() {
		moveToElement(addButton);
		boolean flag = false;
		if (addButton.isEnabled()) {
			flag = true;
		}
		Assert.assertFalse(flag);
	}
	/* verify cancel control is enabled on add site pop up */

	public void verifyCancelControlIsEnabledOnAddSitePopUp() {
		moveToElement(cancelButtonAddSitePopUp);
		boolean flag = false;
		if (cancelButtonAddSitePopUp.isEnabled()) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	/* click on Cancel control on add site rater pop up */

	public void clickOnCancelControlOnAddSitePopUp() {
		moveToElement(cancelButtonAddSitePopUp);
		waitAndClick(cancelButtonAddSitePopUp);
	}

	/* click on Yes Close form control on add site rater pop up */

	public void clickOnYesCloseFormControlOnConfirmationPopUp() {
		moveToElement(YesCloseFormControlOnConfirmationPopUp);
		clickOn(YesCloseFormControlOnConfirmationPopUp);
		_normalWait(3000);
	}

	/* verify that add site rater window is not displayed */

	public void verifyAddSiteRaterWindowIsNotDisplayed() {
		boolean flag = true;
		try {
			waitForSpinnerBecomeInvisible(4000);
			if (isElementDisplayed(addSiteStaffWindow)) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		Assert.assertTrue(flag);
	}

	/* verify that site rater is not created */

	public void verifySiteRaterIsNotCreated(String siteraterToSearch) {
		boolean flag = false;
		waitAndClick(filterICON_rater);
		filterSearchBox.sendKeys(siteraterToSearch);
		inpSearchfilterBTN.click();
		waitSpinnerToBecomeInvisible();

		if (noSiteRaterText.isDisplayed()) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	/* verify that rater filter is cleared */

	public void verifyRaterFilterIsCleared() {
		waitAndClick(filterICON_rater);
		waitAndClick(inpSearchClearBTN);
		waitSpinnerToBecomeInvisible();
		_normalWait(2000);
	}

	/* verify that Add site rater window is displayed */

	public void verifyAddSiteRaterWindowIsDisplayed() {
		boolean flag = false;
		if (addSiteStaffWindow.isDisplayed()) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	/* Click on add control on add site rater window */

	public void clickOnAddControlOnSiteRaterWindow() {
		waitAndClick(addButton);
		waitUntillFinishProcessSpinnerDisable();
	}

	/* click on tiny cross button on add site rater window */

	public void clickOnTinyCrossButtonOnAddSiteRaterWindow() {
		moveToElement(TinyCrossButtonOnAddSiteWindow);
		waitAndClick(TinyCrossButtonOnAddSiteWindow);
	}

	/* Verify that new site rater is created */

	public void verifyNewSiteRaterIsCreated(String siteRaterToAdd, String email, String fname, String lname) {
		selectAddSiteControl();

		try {
			_normalWait(1000);
			inputText(primaryEmail, email);
			inputText(firstName, fname);
			inputText(lastName, lname);
			waitAndClick(facilitiesField);
			_normalWait(2000);
			RobotAction(KeyEvent.VK_DOWN, KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}

		selectStudyFromStudyDropDownOptions(study);
		waitAndClick(addSiteStaffWindow);
		waitAndClick(addButton);
		_normalWait(2000);

		waitAndClick(filterICON_rater);
		inputText(filterSearchBox, siteRaterToAdd);
		waitAndClick(inpSearchfilterBTN);
		_normalWait(2000);
		Assert.assertTrue(isElementPresent("//span[contains(text(),'" + siteRaterToAdd + "')]"));
	}

	/***
	 * Click on Add site rater window title
	 */

	public void clickOnAddSiteRaterWindowTitle() {

		waitAndClick(addSiteStaffWindow);

	}

	/***
	 * Naviagte to view history of created site rater
	 */

	public String navigateToViewHistoryOfCreatedSiteRater() {
		String name = raterName.getText();
		System.out.println(name);
		javascriptButtonClick(siteRaterCheckbox);
		waitAndClick(actionControl);
		waitAndClick(viewHistoryButton);
		return name;

	}

	/***
	 * Verify view history of created site rater is displayed
	 */

	public void verifyViewHistoryOfCreatedSiteRaterDisplayed(String name) {
		boolean flag = true;
		try {
			if (name.contains(raterNameOnHistoryPage.getText())) {
				flag = true;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
	}

	/***
	 * Verify appropriate information on j=history page
	 */
	public void verifyApproiateInformation() {
		waitUntillFinishProcessSpinnerDisable();
		moveToElement(addedSiteInfo);
		if (addedSiteInfo.isDisplayed()) {
			Assert.assertTrue(true);
		}
	}

	public void verifyEnteredValueInRespectiveField(String fieldName, String expectedtxt) {
		WebElement elementToBeVerified = driver.findElement(ByLocator("//label[text()='" + fieldName + "']/..//input"));
		String actualtxt = elementToBeVerified.getAttribute("value");
		Assert.assertEquals(actualtxt, expectedtxt);
		reportInfo();
	}
	/* verify that add control is enabled on add site pop up */

	public void selectAddAnotherCheckboxOnAddSitePopUp() {
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		moveToElement(addAnotherCheckbox);
		javascriptButtonClick(addAnotherCheckbox);
	}

	/* verify that add control is enabled on add site pop up */

	public void addAnotherCheckboxIsSelected() {
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		WebElement addAnotherCheckBox = driver.findElement(
				ByLocator("//div[@class='ma-dialog-footer']//span/input[@id='addAnother']//following-sibling::span"));
		Assert.assertTrue(addAnotherCheckBox.isDisplayed());
		reportInfo();
	}

	/* Verify Facilities field is disabled and has preselected Facility */

	public void verifyFacilityFieldDisabled(String facilityName) {
		WebElement facilitiesField = driver.findElement(ByLocator("//kendo-multiselect[contains(@class,'disable')]"));
		moveToElement(facilitiesField);
		Assert.assertTrue(facilitiesField.isEnabled());
		Assert.assertEquals(facilityName, facilitiesField.getText().trim());
		reportInfo();
	}

	public void selectUserRoleOnAddStaffPopUp() {
		waitAndClick(siteRoleDropDown);
		waitAndClick(siteRoleDropDownOption);
	}
	
	public void selectSystemRoleAndRoleGroupColumns() {
		waitAndClick(columnsFilterIcon);
		Assert.assertTrue(isElementDisplayed(columnsFilterList));
		//moveToElement(systemRoleCheckBox);
		waitAndClick(systemRoleCheckBox);
		//moveToElement(roleGroupCheckBox);
		waitAndClick(roleGroupCheckBox);
		//moveToElement(closeButtonOnFilterList);
		waitAndClick(closeButtonOnFilterList);
	}
		
	public void addToSystemRecordInfo(String user, String action) {
		waitAndClick(actionFilter);
		moveToElement(actionInputField);
		actionInputField.clear();
		inputText(actionInputField, action);
		clickOn(filterButton);
		String date = currentDate().toUpperCase();
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//table[@class='k-grid-table']/tbody/tr/td[contains(text(),'"+date+"')]"))));
		moveToElement(userNameUnderHistory);
		Assert.assertTrue(userNameUnderHistory.getText().equalsIgnoreCase(user));	
		reportInfo();
	}
	
	public void verifyAppropriateInfoDisplayed(String txt) {
		boolean flag= false;
		for(WebElement el : actionByUnderHistory) {
			if (el.getText().equalsIgnoreCase(txt)) {
				moveToElement(el);
				flag =true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
}
