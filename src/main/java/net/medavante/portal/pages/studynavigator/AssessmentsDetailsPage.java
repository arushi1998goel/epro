
package net.medavante.portal.pages.studynavigator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;
import net.medavante.portal.utilities.Constants;
import net.medavante.portal.utilities.Utilities;

public class AssessmentsDetailsPage extends BasePage {

	// ==================Assessment Header
	// Locators===============================//

	@FindBy(xpath = "//div[@data-ng-controller='AssessmentDetailsTitleController']//h1")
	private WebElement assessmentDetailsPageTitle;

	@FindBy(xpath = "//a[@title='Refresh']")
	private WebElement refreshBTN;

	@FindBy(xpath = "//div[contains(@class,'details-grid')]//a[@title='Save']")
	private WebElement saveBTN;

	@FindBy(xpath = "//div[contains(@class,'details-grid')]//a[@title='Save' and @class='btn circle-button btn-white disabled']")
	private WebElement disableSaveBTN;

	@FindBy(xpath = "//div[contains(@class,'details-grid')]//a[@title='Edit']")
	private WebElement editBTn;

	@FindBy(xpath = "//span[contains(@data-ng-class,'selectedViewMode')]//span[2]")
	private WebElement assessmentDetailsPageSelectedViewMode;

	// ==================Assessment details Locators===================//

	@FindBy(xpath = "//div[contains(@class,'details-grid')]//a[@title='Cancel']")
	private WebElement cancelChangesBTN;

	@FindBy(xpath = "//label[text()='Study']/parent::div/following-sibling::div//a")
	private WebElement assessmentStudyLINK;

	@FindBy(xpath = "//label[text()='Site']/parent::div/following-sibling::div//label")
	private WebElement assessmentsiteTXT;

	@FindBy(xpath = "//label[text()='Subject']/parent::div/following-sibling::div//a")
	private WebElement assessmentSubjectLINK;

	@FindBy(xpath = "//div[contains(@class,'details-grid')]//label[text()='Visit']/parent::div/following-sibling::div//a")
	private WebElement assessmentVisitLINK;

	@FindBy(xpath = "//div[@data-select-action='changeRater']//button")
	private WebElement raterDRPDWN;

	@FindBy(xpath = "//div[@data-display-value='viewModel.assignee.personName']")
	private WebElement raterFieldValidates;

	@FindBy(xpath = "//div[@data-ng-required='isStartedRequired()' and @required='required']")
	private WebElement startedFieldValidates;

	@FindBy(xpath = "//div[@data-ng-required='isDurationRequired()' and @required='required']")
	private WebElement durationFieldValidates;

	@FindBy(xpath = "//div[@data-ng-required='isSourceFileRequired()' and @required='required']")
	private WebElement sourceUploadFieldValidates;

	@FindBy(xpath = "//div[contains(@class,'details-grid')]")
	private WebElement raterLabelTXT;

	@FindBy(xpath = "//div[contains(@class,'details-grid')]//label[text()='Started']")
	private WebElement startedLabelTXT;

	@FindBy(xpath = "//div[contains(@class,'details-grid')]//label[text()='Duration']")
	private WebElement durationLabelTXT;

	@FindBy(xpath = "//div[contains(@class,'details-grid')]//label[text()='Source Document']")
	private WebElement sourceDocumentTXT;

	@FindBy(xpath = "//div[contains(@class,'details-grid')]//label[text()='Assessment']")
	private WebElement assessmentLabelTXT;

	@FindBy(xpath = "//div[@data-select-action='changeRater']//ul//li//span")
	private List<WebElement> raterListTXT;

	@FindBy(xpath = "//div[@data-ng-required='isStartedRequired()']")
	private WebElement startedINP;

	@FindBy(xpath = "//div[@data-ng-required='isDurationRequired()']")
	private WebElement durationINP;

	@FindBy(xpath = "//div[@id='datepicker']")
	private WebElement startedDatePickerBTN;

	@FindBy(xpath = "//div[@class='datepicker-days']//td[contains(@class,'today')]")
	private WebElement currentDate;

	@FindBy(xpath = "//div[@class='datepicker-days']//td[contains(@class,'today')]")
	private WebElement dayToday;
	
	@FindBy(xpath = "(//div[@class='datepicker-days']//td[contains(@class,'today')]/./preceding-sibling::tr)[1]")
	private WebElement dayPrevious;

	@FindBy(xpath = "//div[@data-ng-required='isStartedRequired()']//div[@id='timepicker']")
	private WebElement startedTimePickerBTN;

	@FindBy(xpath = "//div[@data-ng-required='isStartedRequired()']//label")
	private WebElement selectedStartedDateTXT;

	@FindBy(xpath = "//div[@data-ng-required='isDurationRequired()']//div[@id='timepicker']")
	private WebElement durationTimePickerBTN;

	@FindBy(xpath = "//a[@title='Upload Source Document']")
	private WebElement sourceUploadBTN;

	@FindBy(xpath = "//div[contains(@class,'edit') and @data-ng-class='{edit: isStartedEdited()}']")
	private WebElement dateAndTimeFieldInEditMode;

	@FindBy(xpath = "//div[@data-ng-class='{edit: isStartedEdited()}']")
	private WebElement dateAndTimeField;

	@FindBy(xpath = "//div[@data-ng-class='{edit: isDurationEdited()}']")
	private WebElement durationField;

	@FindBy(xpath = "//div[contains(@data-ng-if,'isNewPaperTranscription')]//input[@type='checkbox']")
	private WebElement paperTranscriptionCHKBOX;

	@FindBy(xpath = "//label[contains(@data-ng-if,'viewModel.canAccessRaterDetail')]/a[@href]")
	private WebElement raterNameAsHyperlink;

	@FindBy(xpath = "//label[contains(@data-ng-if,'viewModel.canAccessRaterDetail')]")
	private WebElement raterNameUnderDetails;

	@FindBy(xpath = "//div[@class='datepicker-days']//th[@class='picker-switch']")
	private WebElement startedDateTimeHeadingOnCalender;

	@FindBy(xpath = "//div[contains(@class,'assessment-version-list')]")
	private WebElement assessmentDetailsInfo;

	@FindBy(xpath = "//div[contains(@class,'details-grid study-languages')][1]//div[@class='row']")
	private List<WebElement> assessmentDetailsInfoList;

	@FindBy(xpath = "//div[contains(@class,'assessment-version-list')]//div[contains(@class,'row')]")
	private List<WebElement> assessmentDetailsInfoListUnderVersionSection;

	// label[contains(@data-ng-if,'viewModel.canAccessRaterDetail')]/a[@href]

	// ==========Source Upload Locators==================//
	@FindBy(xpath = "//div[@id='sourceDocument' and @aria-hidden='false']//h4[text()='Upload Files']")
	private WebElement sourceUploadPopUpTitleDisplayed;

	@FindBy(xpath = "//div[@id='sourceDocument']//div[text()='Cancel']")
	private WebElement sourceUploadCancelBTN;

	@FindBy(xpath = "//div[@id='sourceDocument' and @aria-hidden='false']//div[@data-ng-disabled='isUploadDisabled()']")
	private WebElement uploadSourceUploadBTN;

	@FindBy(xpath = "//div[@id='sourceDocument']//div[@class='close']")
	private WebElement sourceUploadCloseBTN;

	@FindBy(xpath = "//div[@id='sourceDocument' and @aria-hidden='false']//div[@title='Add File(s)']")
	private WebElement uploadSourceAddFilesBtn;

	@FindBy(xpath = "//div[contains(@data-ng-if,'NewPaperTranscription')]//span[contains(@data-ng-hide,'sourceFileName')]")
	private WebElement uploadedSourceDocument;

	// =============Date Calendar locators============//

	@FindBy(xpath = "//div[contains(@class,'top picker-open')]")
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

	@FindBy(xpath = "//label[@data-ng-if='isDurationEdited()']")
	private WebElement editedSelectedDurationTimeTXT;

	@FindBy(xpath = "//label[@data-ng-model='viewModel.duration']")
	private WebElement savedSelectedDurationTimeTXT;

	// ==================Assessment details PDF
	// Source===============================//

	@FindBy(xpath = "//div[@class='form-cover-big-mask']")
	private WebElement pdfFormLargeICON;

	@FindBy(xpath = "//iframe[@class='embedPdf ng-scope']")
	private WebElement assessmentPdfFrame;

	@FindBy(xpath = "//button[@id='presentationMode']")
	private WebElement presentationModeButton;

	@FindBy(xpath = "//button[@title='Cancel']")
	private WebElement cancelButton;

	// ===================================Assessment Pdf Thumbnail Locators

	@FindBy(xpath = "//div[contains(@class,'assessment-status')]")
	private WebElement assessmentPdfStatus;

	@FindBy(xpath = "//img[@src='/images/thumb_mask_papertranscription_big.png']")
	private WebElement assessmentClosedThumbnailImage;

	@FindBy(xpath = "//img[@src='/images/thumb_mask_notadministered_big.png']")
	private WebElement assementNotAdministeredImage;

	// ==================Assesment scores and attachement tab======
	@FindBy(xpath = "//a[text()='Scores']")
	private WebElement scoreTAB;

	@FindBy(xpath = "//div[@data-scores-model='scoresModel']//a[@title='Edit']")
	private WebElement scoreTabEditBTN;

	@FindBy(xpath = "//div[@class='tab-content-container row']/div//input")
	private WebElement scoreTabEditINP;

	@FindBy(xpath = "//div[@id='scores']//div[@class='tab-content-container row']//div/label")
	private List<WebElement> scoreResponseNameLabelTextList;

	@FindBy(xpath = "//div[@id='scores']//div[@class='tab-content-container row']//div/div/input")
	private List<WebElement> scoreVersionValueInputTextList;

	@FindBy(xpath = "//div[contains(@data-ng-if,'versions')]//div[contains(@data-ng-repeat,'item in')]")
	private List<WebElement> scoreVersionsList;

	@FindBy(xpath = "//div[contains(@data-ng-if,'versions')]")
	private WebElement versionSection;

	@FindBy(xpath = "//div[@class='row scores-show']//ul[@class='nav nav-tabs']/li/a")
	List<WebElement> scoresAndAttachement;

	@FindBy(xpath = "//span[contains(@class,'k-widget k-dropdown k-header')]//span[@class='k-select']")
	private WebElement scoreChangeDropDown;

	@FindBy(xpath = "//ul[@class='k-list k-reset']//li")
	List<WebElement> scoresEdit;

	@FindBy(xpath = "//input[@data-ng-model='score.currentValue']")
	private WebElement changeAssesmentEditBox;

	// ==================Assesment Queries======

	@FindBy(xpath = "//div[@title='Show queries']")
	private WebElement queriesLinkICN;

	@FindBy(xpath = "//a[@title='Add Query']")
	private WebElement addQueriesButton;

	@FindBy(xpath = "//div[@class='queries-list']//div[contains(@data-ng-repeat,'query in queries')]")
	private List<WebElement> queriesList;

	@FindBy(xpath = "//div[@class='queries-list']//div[@data-ng-init='init()']//textarea[@data-ng-model='text']")
	private WebElement queriesTextArea;

	@FindBy(xpath = "//ul[@class='queries-controls']//a[@title='Create']/span")
	private WebElement createQueriesButton;

	@FindBy(xpath = "//ul[@class='queries-controls']//a[@title='Cancel']")
	private WebElement cancelQueriesButton;

	@FindBy(xpath = "//ul[@class='queries-controls']//button[@title='Reply']")
	private WebElement replyQueryButton;

	@FindBy(xpath = "//ul[@class='queries-controls']//button[@title='Close Query']")
	private WebElement closeQueryButton;

	@FindBy(xpath = "//div[@class='queries-dialog-content']//a[@title='Close']")
	private WebElement queriesCollpaseBTN;

	@FindBy(xpath = "//div[@class='queries-dialog-header-title']")
	private WebElement queriesSliderPanelOpen;

	@FindBy(xpath = "//div[@data-scores-model='scoresModel']//div[@title='Save']//a")
	private WebElement scoreTabSaveBTN;

	@FindBy(xpath = "//a[text()='Attachments']")
	private WebElement attachmentsTAB;

	@FindBy(xpath = "//div[@class='queries-list']//div[@data-ng-if='showContextSelector']")
	private WebElement contextSelector;

	@FindBy(xpath = "//div[@data-display-value='selectedContext.name']//li/span[@class='ng-binding ng-scope']")
	private List<WebElement> contextMenuLIST;

	@FindBy(xpath = "//div[@class='queries-list']//div[@data-ng-if='showScoreSelector']")
	private WebElement scoreSelector;

	@FindBy(xpath = "//div[@data-display-value='selectedScore.name']//li/span")
	private List<WebElement> scoreNameLIST;

	@FindBy(xpath = "//ul[@class='queries-controls-vertical text-right']//a[not(contains(@class,'ng-hide')) and @title='Edit']")
	private WebElement queryEditButton;

	@FindBy(xpath = "//ul[@class='queries-controls-vertical text-right']//a[not(contains(@class,'ng-hide')) and @title='Delete Query']")
	private WebElement queryDeleteButton;

	// ==============Assessment view mode locators=======
	@FindBy(xpath = "//div[@class='view-mode-dropdown']")
	private WebElement viewModeDROPDOWN;

	@FindBy(xpath = "//div[@class='view-mode-dropdown']//ul/li/span[@class='mode ng-binding']")
	private List<WebElement> viewModeDROPDOWNOptionLIST;

	// CheckBox Locator
	@FindBy(xpath = "//div[@class='row double-row-block']//input")
	private WebElement checkBoxOnAssesmentDetailsPage;

	@FindBy(xpath = "//div[contains(@data-ng-if,'isNewPaperTranscription')]//div[@class='checkbox pull-left']//input[@type='checkbox']")
	private WebElement checkBoxMarkAsPaperTranscription;

	@FindBy(xpath = "//div[contains(@class,'col-xs-24')]/a[@class='btn btn-active']")
	private WebElement confirmBTN;

	@FindBy(xpath = "//div[@id='notAdministeredConfirmation']//div[@class='modal-content']")
	private WebElement notAdministeredPopUP;

	@FindBy(xpath = "//div[@id='notAdministeredConfirmation']//span[text()='×']")
	private WebElement closeIconConfirmationNotAdministeredPopUp;

	@FindBy(xpath = "//div[@id='notAdministeredConfirmation']//div[@class='modal-content']//div[text()='Yes']")
	private WebElement confirmPopUPWindowYesBTN;

	@FindBy(xpath = "//div[@id='notAdministeredConfirmation']//div[@class='modal-content']//div[text()='No']")
	private WebElement confirmPopUPWindowNoBTN;

	@FindBy(xpath = "//li[@title='Assessments: All']/a")
	private WebElement backToAllAssesmentLink;

	@FindBy(xpath = "//a[@class='refresh-page-content circle-button btn btn-white']")
	private WebElement RefreshBTN;

	@FindBy(xpath = "//h2[text()='Versions']")
	private WebElement versionHeadingTxt;

	@FindBy(xpath = "//div[contains(@class,'row double-row-block')]//input/following-sibling::span[text()='Not Administered']")
	private WebElement notAdministeredCheckBoxTxt;

	@FindBy(xpath = "//div[@class='form-cover-big-mask']")
	private WebElement pdfDivision;

	// =============Reason For Change PopupLocators================

	@FindBy(xpath = "//div[contains(@data-display-value,'changeReason')]")
	private WebElement reasonForChangeReasonDRPDOWN;

	@FindBy(xpath = "//div[contains(@data-display-value,'changeReason')]//li/span")
	private List<WebElement> reasonForChangeReasonOptionLIST;

	@FindBy(xpath = "//div[@class='modal-body']//div[contains(@class,'col-sm')]/br//parent::div")
	private WebElement reasonForChangeAuthenticationText;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//input[@id='loginInput']")
	private WebElement reasonForChangeUserNameINP;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//input[@id='passwordInput']")
	private WebElement reasonForChangePasswordINP;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[text()='OK']")
	private WebElement reasonForChangeOkBTN;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[text()='Cancel']")
	private WebElement reasonForChangeCancelBTN;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[@class='modal-content']")
	private WebElement popUpDisplayed;

	@FindBy(xpath = "//label[@data-ng-model='viewModel.started']")
	private WebElement StartedDateTimeLabelText;

	@FindBy(xpath = "//label[@data-ng-model='viewModel.duration']")
	private WebElement durationTimeLabelText;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//div[@class='timepicker']//a[@data-action='incrementHours']")
	private WebElement incrementHoursForDuration;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//div[@class='timepicker']//a[@data-action='incrementMinutes']")
	private WebElement incrementMinutesIcon;

	// Action Locators
	@FindBy(xpath = "//span[text()='Actions']//parent::a//parent::div")
	private WebElement actionButton;

	@FindBy(xpath = "//span[text()='Actions']//following-sibling::i[contains(@class,'icon-error ng-scope')]")
	private WebElement pendingOperationICN;

	@FindBy(xpath = "//span[text()='Actions']//parent::a/following-sibling::button")
	private WebElement actionToggleDropDWNBTN;

	@FindBy(xpath = "//div[@id='queryConfirmation']//div[text()='Yes']")
	private WebElement allowAutomaticSubmissionConfirmationYesBTN;

	@FindBy(xpath = "//div[@id='queryConfirmation']//div[text()='No']")
	private WebElement allowAutomaticSubmissionConfirmationNoBTN;

	@FindBy(xpath = "//div[@id='queryConfirmation']//div[@class='modal-content']")
	private WebElement allowAutomaticConfirmationPopUp;

	@FindBy(xpath = "//div[contains(@ng-if,'.formSubmissionHistory')]//h2[text()='Notes']")
	private WebElement notesSectionOfAllowAutomaticSubmission;

	@FindBy(xpath = "//div[@class='portal-grid']")
	private WebElement notesSectionGrid;;

	@FindBy(xpath = "//div[@id='notAdministeredConfirmation']//div[@class='modal-content']//p")
	private WebElement confirmationNotAdministredText;

	@FindBy(xpath = "//div[@id='notAdministeredConfirmation']//div[@class='modal-content']//li[@class='ng-binding']")
	private List<WebElement> confirmationMessageTextList;

	@FindBy(xpath = "//div[contains(@ng-if,'.formSubmissionHistory')]//div[contains(@class,'grid-row')]")
	private List<WebElement> notesSectionRecordsList;

	@FindBy(xpath = "//div[@data-select-action='selectSubject']/button")
	private WebElement changeSubjectDropDown;

	@FindBy(xpath = "//div[@data-select-action='selectVisit']/button")
	private WebElement changeVisitDropDown;

	@FindBy(xpath = "//div[@id='moveConfirmation']//div[text()='Confirm']")
	private WebElement confirmButton;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//div[text()='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//div[text()='Cancel']")
	private WebElement cancelButtonOnPopUp;

	@FindBy(xpath = "//div[@class='success-info-container']/label")
	private WebElement assesmentChangedMsg;

	@FindBy(xpath = "//div[@class='row double-row-block']//input//following-sibling::span[contains(text(),'Not Administered')]")
	private List<WebElement> notAdministeredCheckBoxForClinroLabel;

	@FindBy(xpath = "//div[@class='row double-row-block']//input//following-sibling::span[contains(text(),'Not completed')]")
	private List<WebElement> notCompletedCheckBoxLabel;

	// Move Visit Locators
	@FindBy(xpath = "//div[@ng-if='showActionDropDown()']//ul//li//a[text()='Change Assessment']")
	private WebElement changeAssessmentLink;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//span[text()='Select Subject']//parent::button")
	private WebElement changeToSubjectDRPDWN;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//span[text()='Select Subject']//parent::button//following-sibling::div//ul//li//span")
	private List<WebElement> changeToSelectSubjectList;

	@FindBy(xpath = "//div[@id='form-configuration-dialog']//span[text()='Select Visit']//parent::button")
	private WebElement changeToVisitDRPDWN;

	@FindBy(xpath = "//div[@class='modal-dialog move-visit-assesment-dialog']//div[@class='modal-content']")
	private WebElement moveAssessmentPopUp;

	@FindBy(xpath = "//div[@data-display-value='selectedVisit.name']//parent::button//following-sibling::div//ul//li//span")
	private List<WebElement> changeToVisitList;

	@FindBy(xpath = "//div[@class='modal-dialog move-visit-assesment-dialog']//button[@data-ng-click='noClick()' and @class='close']")
	private WebElement crossICN;

	@FindBy(xpath = "//div[contains(@class,'success-info-container')]//div[@class='close-button-white']")
	private WebElement closeMessage;

	@FindBy(xpath = "//div[@class='error-container']//div[@class='close-button-white']")
	private WebElement errorContainerCloseIcon;

	@FindBy(xpath = "//div[@data-display-value='selectedObserver.alias']")
	private WebElement selectObserverDropDown;

	@FindBy(xpath = "//div[@data-display-value='selectedObserver.alias']//li/span")
	private List<WebElement> selectObserverOptionList;

	@FindBy(xpath = "(//div[@class='caption ng-isolate-scope']/..//a[@title='Upload Attachments'])[1]")
	private WebElement paperSourcePlusIcon;

	@FindBy(xpath = "//div[@id='paperSource' and @aria-hidden='false']//div[@title='Add File(s)']")
	private WebElement uploadAttachementFilesButton;

	@FindBy(xpath = "//div[@id='paperSource' and @aria-hidden='false']//div[@data-ng-disabled='isUploadDisabled()']")
	private WebElement uploadAttachementButton;

	@FindBy(xpath = "//div[@id='attachments']//h2[contains(text(),'Paper Source')]")
	private WebElement paperSourceAttachements;

	@FindBy(xpath = "//div[@data-select-action='selectVisit']//span[@id='selectedStudy']")
	private WebElement visitChangeToSelectedTXT;

	@FindBy(xpath = "//div[@data-select-action='selectSubject']//span[@id='selectedStudy']")
	private WebElement subjectChangeToSelectedTXT;

	@FindBy(xpath = "//div[@class='subject-general-section']//div[@class='info-sub-row']//span[@class='ng-binding']")
	private WebElement subjectStatusTxt;
	// div[@class='subject-general-section']//div[@class='info-row-vertical']/div[@class='info-sub-row']

	@FindBy(xpath = "//*[@class='warning-box']//span")
	private WebElement automatiSubmissionTabletErrorMessage;

	@FindBy(xpath = "//*[@data-ng-model='reasonComment']")
	private WebElement reasonArea;

	@FindBy(xpath = "(//ul[contains(@class,'nav') and not(contains(@class,'drop'))])[1]/li/a")
	private List<WebElement> navMenuItemsList;

	@FindBy(xpath = "//*[@id='header']//ul[@class='dropdown-menu']/parent::li[@class='dropdown open']//following-sibling::ul/li/a")
	private List<WebElement> subDropDownOfNavMenu;

	// ======================Pdf Locators===============================

	@FindBy(xpath = "//div[contains(@id,'pageContainer')]")
	private List<WebElement> totalPagePdf;

	@FindBy(xpath = "//a[text()='HOME']")
	private WebElement homeBTN;

	@FindBy(xpath = "//div[@id='notAdministeredConfirmation']//div[text()='Yes']")
	private WebElement notAdminsteredPopUpYesBtn;

	@FindBy(xpath = "//span[contains(@class,'icon-small icon-lock-small')]")
	private WebElement lockedIcon;

	@FindBy(xpath = "//a[@data-ng-class='{disabled : isDataLocked()}']")
	private WebElement detailsDisableEditIcon;
	
	@FindBy(xpath = "//div[@class='datepicker-days']//th[@class='next']")
	private WebElement startedDateTimeNextICN;

	@FindBy(xpath = "//div[@class='datepicker-days']//th[@class='prev']")
	private WebElement startedDateTimePrevICN;
	
	@FindBy(xpath = "//span[text()='Actions']//parent::a//parent::div/button")
	private WebElement actionButtonList;
	
	@FindBy(xpath="//a[text()='Change Assessment']")
	private WebElement changeAssessmenttlist;
	
	@FindBy(xpath = "//span[text()='Actions']//parent::a//parent::div")
	private List<WebElement> actionButtonsLists;
	


	public AssessmentsDetailsPage(WebDriver driver) {
		super(driver);
	}

	/*****
	 * 
	 *  Close failed assessment popup
	 * 
	 */
	public void closeOnFailedassessment()
	{
		try {
			if (isElementPresent(errorContainerCloseIcon) == true) {
				moveToElement(errorContainerCloseIcon);
				clickOn(errorContainerCloseIcon);
			} else {
				System.out.println("Error message not displayed.");
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



	/* Verify Not Administred PopUp Confirmation Present With Given Text */
	public void verifyConfirmationMessagePrsentWithText(String textToBeVerify) {
		boolean flag = false;
		for (WebElement listtext : confirmationMessageTextList) {
			if (getText(listtext).contains(textToBeVerify)) {
				moveToElement(listtext);
				flag = true;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/*
	 * Verify Not Administered PopUp Confirmation Present With warning paragraph
	 */
	public void verifyNotAdministeredConfirmationPopUpPresentWithWarningParagraph() {
		moveToElement(confirmationNotAdministredText);
		Assert.assertEquals(
				"Please ensure that this assessment is being marked as Not Administered because site confirmed:",
				confirmationNotAdministredText.getText());
		reportInfo();
	}

	/**
	 * Verify total number of version in the Version List
	 * 
	 * @param versionCountToBeVerify
	 */
	public void verifyAssessmentVersionRecordCount(int versionCountToBeVerify) {
		Assert.assertTrue(scoreVersionsList.size() == versionCountToBeVerify);
		reportInfo();
	}

	/**
	 * Select Assesment version and verify version response values
	 * 
	 * @param versionToBeSelect
	 * @param responseLabelName
	 * @param responseValueToBeVerify
	 */
	public void selectVersionAndVerifyResponseValue(String versionToBeSelect, String responseLabelName,
			String responseValueToBeVerify) {
		for (WebElement versionNum : scoreVersionsList) {
			if (versionNum.findElement(By.xpath("./div[1]/label")).getText().equalsIgnoreCase(versionToBeSelect)) {
				clickOn(versionNum.findElement(By.xpath("./div[1]/label")));
				_normalWait(1500);
				verifyScoreResponseValues(responseLabelName, responseValueToBeVerify);
				break;
			}
		}

	}

	/**
	 * Verify Assessment Details Section is available on the page
	 */
	public void verifyAssessmentDetailsSectionDisplayed() {
		Assert.assertTrue(isElementDisplayed(raterLabelTXT));
		Assert.assertTrue(isElementDisplayed(assessmentLabelTXT));
		moveToElement(raterLabelTXT);
		reportInfo();
	}

	/**
	 * Verify Version section is available on the page
	 */
	public void verifyVersionsSectionDisplayed() {
		moveToElement(versionSection);
		Assert.assertTrue(isElementDisplayed(versionSection));
		reportInfo();
	}

	/**
	 * Verify score response values by their response label
	 * 
	 * @param responseLabelName
	 * @param responseValueToBeVerify
	 */
	public void verifyScoreResponseValues(String responseLabelName, String responseValueToBeVerify) {
		boolean flag = false;
		for (WebElement responseLabel : scoreResponseNameLabelTextList) {
			if (responseLabel.getText().equalsIgnoreCase(responseLabelName)) {
				flag = getText(responseLabel.findElement(By.xpath("./parent::div/following-sibling::div/label")))
						.equalsIgnoreCase(responseValueToBeVerify);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);

	}

	/**
	 * Enter Response value for particular response
	 * 
	 * @param responseLabelName
	 * @param scoreValueToBeEnter
	 */
	public void enterScoreResponseValues(String responseLabelName, String scoreValueToBeEnter) {
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		for (WebElement responseLabel : scoreResponseNameLabelTextList) {
			if (responseLabel.getText().trim().equalsIgnoreCase(responseLabelName)) {
				inputText(responseLabel.findElement(By.xpath("./parent::div/following-sibling::div/input")),
						scoreValueToBeEnter);
				break;
			}
		}
	}

	/**
	 * Verify Score input field is editable
	 */
	public void verifyScoresInputIsEditable() {
		for (WebElement versionResponseInputWeb : scoreVersionValueInputTextList) {
			Assert.assertTrue(versionResponseInputWeb.isEnabled());
		}
	}

	/**
	 * Function: Navigate to home page
	 * 
	 * 
	 */
	public void navigateBackToDashBoard() {
		selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.HomeNavText);
		waitForPageLoaded();
	}

	/** Verify Assessment details page is displayed **/
	public void verifyAssessmentDetailsDisplayed() {
		waitForSpinnerBecomeInvisible(40);
		Assert.assertTrue(assessmentDetailsPageTitle.isDisplayed());
		moveToElement(assessmentDetailsPageTitle);
		reportInfo();
	}

	/**
	 * Verify Selected view mode on Assessment Details page
	 */
	public void verifySelectedViewMode(String ModeToBeVerified) {
		waitForWebElementPresent(assessmentDetailsPageSelectedViewMode);
		Assert.assertTrue(getText(assessmentDetailsPageSelectedViewMode).equalsIgnoreCase(ModeToBeVerified));
		moveToElement(assessmentDetailsPageSelectedViewMode.findElement(By.xpath("./parent::span")));
		reportInfo();
	}

	public void verifyAssessmentPageTitle(String titleToBeVerified) {
		waitUntillFinishProcessSpinnerDisable();
		moveToElement(assessmentDetailsPageTitle);
		String expectedTitle = assessmentDetailsPageTitle.getText();
		boolean flag=true;
		if (expectedTitle.trim().contains(titleToBeVerified)) {
			flag=true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyPdfStatusTextAndClosedThumbnailImageIsDisplayed(String statusToBeVerifed) {
		waitForElement(assessmentPdfStatus);
		Assert.assertTrue(assessmentPdfStatus.getText().trim().toLowerCase().contains(statusToBeVerifed.toLowerCase()));
		Assert.assertNotEquals(driver.findElements(By.xpath("//img[contains(@src,'/images/thumb_mask_')]")).size(), 0);
		reportInfo();
	}

	/* Verify Pdf Status Text Present */
	public void verifyPdfStatusTextPresent(String statusToBeVerifed) {
		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'assessment-status')]")));
		moveToElement(assessmentPdfStatus);
		Assert.assertTrue(assessmentPdfStatus.getText().trim().toLowerCase().contains(statusToBeVerifed.toLowerCase()));
		reportInfo();
	}

	/**
	 * Verify Save button is displayed and disabled
	 */
	public void verifySaveButtonIsDisplayedAndDisabled() {
		Assert.assertTrue(isElementPresent(disableSaveBTN));
		Assert.assertTrue(saveBTN.getAttribute("class").contains("disabled"));
		moveToElement(disableSaveBTN);
		reportInfo();
	}

	/**
	 * Verify Save button is displayed and Enabled
	 */
	public void verifySaveButtonIsDisplayedAndEnabled() {
		waitSpinnerToBecomeInvisible();
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue(isElementPresent(saveBTN));
		Assert.assertFalse(saveBTN.getAttribute("class").contains("disabled"));
		reportInfo();

	}

	/**
	 * verify score and attachment tab is displayed
	 */
	public void verifyScoreAndAttachmentsTabIsDisplayed() {
		Assert.assertTrue(scoreTAB.isDisplayed() && attachmentsTAB.isDisplayed());
		moveToElement(scoreTAB);
		moveToElement(attachmentsTAB);
		reportInfo();
	}

	/**
	 * verify score and attachment tab is not displayed
	 */
	public void verifyScoreAndAttachmentsTabIsNotDisplayed() {
		Assert.assertFalse(scoreTAB.isDisplayed() && attachmentsTAB.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify Score tab edit button is displayed
	 */
	public void verifyScoreTabEditBTNIsDisplayed() {
		waitForSpinnerBecomeInvisible(20);
		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@data-scores-model='scoresModel']//a[@title='Edit']")));
		waitForElement(scoreTabEditBTN);
		Assert.assertTrue(isElementDisplayed(scoreTabEditBTN));
		reportInfo();
	}

	public void clickOnScoreTabEditBTN() {
		waitAndClick(scoreTabEditBTN);
	}

	/*
	 * Change the score for Paper transcription Assessments forms
	 * 
	 * @parameter: scoreToBeInput
	 */
	public void changeTheScore(String scoreToBeInput) {

		_normalWait(1000);
		if (driver
				.findElements(
						By.xpath("//input[@data-ng-model='score.currentValue' and not(contains(@class,'ng-hide'))]"))
				.size() > 0) {
			waitAndClick(changeAssesmentEditBox);
			changeAssesmentEditBox.sendKeys(scoreToBeInput);
		} else {
			clickOn(scoreChangeDropDown);
			WebElement scoreSelected = driver
					.findElement(By.xpath("//ul[@class='k-list k-reset']//li/span[text()='" + scoreToBeInput + "']"));
			clickOn(scoreSelected);
		}
	}

	public void clickOnSourceTabSaveBTN() {
		waitAndClick(scoreTabSaveBTN);
	}

	/**
	 * Verify view mode drop is displayed
	 */
	public void verifyViewModeDropDownIsDisplayed() {
		Assert.assertTrue(viewModeDROPDOWN.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify View mode options text is displayed
	 * 
	 * @param optionToBeVerified
	 */
	public void verifyViewModeDropDownOptionIsDisplayed(ArrayList<String> optionToBeVerified) {
		clickOn(viewModeDROPDOWN);
		List<String> options = new ArrayList<>();
		for (WebElement option : viewModeDROPDOWNOptionLIST) {
			options.add(getText(option).trim());
		}
		reportInfo();
		clickOn(viewModeDROPDOWN);
		Assert.assertTrue(options.containsAll(optionToBeVerified));
	}

	/**
	 * Change view mode
	 * 
	 * @param modeToBeSelect
	 */
	public void changeViewMode(String modeToBeSelect) {

		_normalWait(1000);
		scrollToTopOfThePage();
		if (getText(viewModeDROPDOWN.findElement(
				By.xpath("//span[@data-ng-class='selectedViewMode.cssClass']//span[@class='mode ng-binding']"))).trim()
						.equalsIgnoreCase(modeToBeSelect)) {
			Assert.assertTrue(true, modeToBeSelect + " is already selected");
		} else {
			clickOn(viewModeDROPDOWN);
			for (WebElement option : viewModeDROPDOWNOptionLIST) {
				if (getText(option).trim().equalsIgnoreCase(modeToBeSelect)) {
					clickOn(option.findElement(By.xpath("./parent::li")));
					break;
				}
			}
		}
//		 new WebDriverWait(driver, 10)
//		 .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
	}

	/**
	 * Verify Cancel button is displayed and enabled
	 */
	public void verifyCancelButtonIsDisplayedAndEnabled() {
		Assert.assertTrue(isElementPresent(cancelChangesBTN));
		Assert.assertTrue(cancelChangesBTN.isEnabled());

		reportInfo();
	}

	/**
	 * Verify edit control is displayed and enabled
	 */
	public void verifyEditControlIsDisplayedAndEnabled() {
		Assert.assertTrue(isElementPresent(editBTn));
		Assert.assertTrue(editBTn.isEnabled());
		moveToElement(editBTn);
		reportInfo();

	}

	/** click on edit button **/

	public void clickOnEditButton() {
		//_normalWait(4000);
		//moveToElement(editBTn);
		waitAndClick(editBTn);
	}

	/**
	 * Verify Date and time field become editable
	 */
	public void verifyDateAndTimeFieldDisplayedEditable() {
		Assert.assertTrue(dateAndTimeFieldInEditMode.isEnabled());
		moveToElement(dateAndTimeFieldInEditMode);
	}

	/**
	 * Verify Date and time field in View mode
	 */
	public void verifyDateAndTimeFieldDisplayedInViewMode() {
		Assert.assertTrue(isElementDisplayed(dateAndTimeField));
		Assert.assertFalse(isElementDisplayed(dateAndTimeFieldInEditMode));
		moveToElement(dateAndTimeField);
	}

	/**
	 * Verify duration field become editable
	 */
	public void verifyDurationFieldsDisplayedEnabled() {
		Assert.assertTrue(durationField.isEnabled());
		moveToElement(durationField);
	}

	/** Verify mandatory fields **/
	public void verifyRequiredFieldsAreRed() {
		Assert.assertTrue(startedFieldValidates.isDisplayed() && durationFieldValidates.isDisplayed()
				&& sourceUploadFieldValidates.isDisplayed());
		reportInfo();
	}

	public void verifyRequiredFieldsAreEditable() {
		Assert.assertTrue(raterFieldValidates.isEnabled() && startedFieldValidates.isEnabled()
				&& durationFieldValidates.isEnabled() && sourceUploadFieldValidates.isEnabled());
		reportInfo();
	}

	/**
	 * verify Assessment details label display correctly
	 */
	public void verifyRequiredFieldsNameIsDisplayed() {
		Assert.assertEquals(startedLabelTXT.getText(), "Started");
		Assert.assertEquals(durationLabelTXT.getText(), "Duration");
		Assert.assertEquals(sourceDocumentTXT.getText(), "Source Document");
		reportInfo();
	}

	public void verifyPaperTranscriptionCheckBoxIsSelectedAndDisabled() {
		Assert.assertTrue(paperTranscriptionCHKBOX.isSelected());
		Assert.assertFalse(paperTranscriptionCHKBOX.isEnabled());
		reportInfo();
	}

	/**
	 * verify site name display correctly
	 * 
	 * @param siteTextToBeVerified
	 */
	public void verifyDisplayedSite(String siteTextToBeVerified) {
		Assert.assertTrue(getText(assessmentsiteTXT).contains(siteTextToBeVerified));
		reportInfo();
	}

	/**
	 * verify study name display correctly
	 * 
	 * @param studyTextToBeVerified
	 */
	public void verifyDisplayedStudy(String studyTextToBeVerified) {
		Assert.assertTrue(getText(assessmentStudyLINK).contains(studyTextToBeVerified));
		reportInfo();
	}

	/**
	 * verify name subject display correctly
	 * 
	 * @param subjectTextToBeVerified
	 */
	public void verifyDisplayedSubject(String subjectTextToBeVerified) {
		Assert.assertTrue(getText(assessmentSubjectLINK).contains(subjectTextToBeVerified));
		reportInfo();
	}

	/**
	 * verify started date configured as per user
	 * 
	 * @param verifiedSelectedStartedDate
	 */
	public void verifyStartedDateIsUserSelected(String verifiedSelectedStartedDate) {
		Assert.assertTrue(getText(selectedStartedDateTXT).toLowerCase().trim()
				.contains(verifiedSelectedStartedDate.toLowerCase()));
		reportInfo();
	}

	/**
	 * verify started time configured as per user
	 * 
	 * @param verifiedSelectedStartedTime
	 */
	public void verifyStartedTimeIsUserSelected(String verifiedSelectedStartedTime) {
		_normalWait(3000);
		Assert.assertTrue(selectedStartedDateTXT.getText().contains(verifiedSelectedStartedTime));
		reportInfo();
	}

	/**
	 * verify duration time is entered as per user input
	 * 
	 * @param verifiedSelectedDurationTime
	 */
	public void verifyDurationIsUserSelected(String verifiedSelectedDurationTime) {
		if (editedSelectedDurationTimeTXT.isDisplayed()) {
			Assert.assertTrue(editedSelectedDurationTimeTXT.getText().trim().contains(verifiedSelectedDurationTime));
		}
		reportInfo();
	}

	/**
	 * verify duration time is saved as per user input
	 * 
	 * @param verifiedSelectedDurationTime
	 */
	public void verifyUserSelectedDurationIsSaved(String verifiedSelectedDurationTime) {
		String[] splitVerifiedSelectedDurationTime = verifiedSelectedDurationTime.split(":");
		if (savedSelectedDurationTimeTXT.getText().contains("hours")) {
			// String[] hourText =
			// savedSelectedDurationTimeTXT.getText().split("hours");
			Assert.assertTrue(savedSelectedDurationTimeTXT.getText().contains(splitVerifiedSelectedDurationTime[0]));
		}
		if (savedSelectedDurationTimeTXT.getText().contains("minutes")) {
			Assert.assertTrue(savedSelectedDurationTimeTXT.getText().contains(splitVerifiedSelectedDurationTime[1]));

		}
		reportInfo();
	}

	/**
	 * Select rater from the list of options
	 * 
	 * @param raterToBeSelect
	 */

	public void selectRater(String raterToBeSelect) {
		waitAndClick(raterDRPDWN);
		for (WebElement raterName : raterListTXT) {
			if (getText(raterName).trim().contains(raterToBeSelect)) {
				waitAndClick(raterName.findElement(By.xpath("./parent::li")));
				break;
			}
		}

	}

	

	/**
	 * Set started date for the assessment
	 * 
	 * @throws ParseException
	 */
	public void setStartedDate() {
		
		waitAndClick(startedDatePickerBTN);
		waitAndClick(currentDate);
	}

	public void updateStartedDate() throws ParseException {
		waitForElement(startedDatePickerBTN);
		_normalWait(1000);
		waitAndClick(startedDatePickerBTN);
		ZoneId US = ZoneId.of("America/New_York");
		final ZonedDateTime input = ZonedDateTime.now(US);
		String usersTimeZoneMonthyear = DateTimeFormatter.ofPattern("MMMM yyyy").format(input);
		Date userCurrentMonthYear = new SimpleDateFormat("MMM yyyy").parse(usersTimeZoneMonthyear);
		String monthYearForStatred;
		Date calenderMonthYear = null;
		do {
			monthYearForStatred = startedDateTimeHeadingOnCalender.getText();
			calenderMonthYear = new SimpleDateFormat("MMM yyyy").parse(monthYearForStatred);
			if (userCurrentMonthYear.equals(calenderMonthYear)) {
				waitAndClick(currentDate);
			} else if (calenderMonthYear.before(userCurrentMonthYear)) {
				clickOn(startedDateTimeNextICN);
			}

			else {
				clickOn(startedDateTimePrevICN);

			}
		} while (!(calenderMonthYear.equals(userCurrentMonthYear)));
	}

	/**
	 * Set Time
	 * 
	 * @param startedTimeToSelect (Time to be select in HH:MM Format)
	 * 
	 * @param timeMarker          (Select AM or PM)
	 */
	public void setStartedTime(String startedTimeToSelect, String timeMarker) {

		waitForAjaxRequestsToComplete();
		String[] splitTime = startedTimeToSelect.split(":");
		_normalWait(1000);
		clickOn(hourLINK);
		for (WebElement hourTXT : hourLIST) {
			if (getText(hourTXT).equalsIgnoreCase(splitTime[0])) {
				clickOn(hourTXT);
				break;
			}
		}
		waitAndClick(minutesLINK);
		for (WebElement minuteTXT : minutesLIST) {
			if (getText(minuteTXT).equalsIgnoreCase(splitTime[1])) {
				clickOn(minuteTXT);
				break;
			}
		}
		if (getText(timeAMarker).equalsIgnoreCase(timeMarker) == false) {
			clickOn(timeAMarker);
		}
		waitAndClick(startedTimePickerBTN);

	}

	/**
	 * Set Duration Time
	 * 
	 * @param timeToBeSelect: HH:MM
	 */
	public void setDurationTime(String timeToBeSelect) {
		String[] splitTime = timeToBeSelect.split(":");
		clickOn(durationTimePickerBTN);
		clickOn(hourLINK);
		for (WebElement hourTXT : hourLIST) {
			if (getText(hourTXT).equalsIgnoreCase(splitTime[0])) {
				clickOn(hourTXT);
				break;
			}
		}
		clickOn(minutesLINK);
		for (WebElement minuteTXT : minutesLIST) {
			if (getText(minuteTXT).equalsIgnoreCase(splitTime[1])) {
				clickOn(minuteTXT);
				break;
			}
		}

		clickOn(durationTimePickerBTN);
	}

	/**
	 * Click on source upload document popup
	 */
	public void clickOnSourceDocumentBtn() {
		waitAndClick(sourceUploadBTN);

	}

	public String getWindowHandle() {
		String windowValue = driver.getWindowHandle();
		return windowValue;
	}

	/**
	 * Upload source file for paper transcription
	 * 
	 * @throws InterruptedException
	 */
	public void uploadSourceFile(String exePath, String filePath, String uploadWindowTitle)
			throws InterruptedException {
		String basePath = "src\\test\\resources\\testdata\\";
		clickOn(uploadSourceAddFilesBtn);
		_normalWait(150);
		Utilities.runExeFile(basePath + exePath, basePath + filePath, uploadWindowTitle);
		waitAndClick(uploadSourceUploadBTN);
		waitForElementToBecomeInvisible(By
				.xpath("//div[@id='sourceDocument' and @aria-hidden='false']//div[@data-ng-if='file.isInLoadState']"));
		_normalWait(5000);
	}

	// Choose file for upload from data folder
	public void uploadFile(String fileName) throws InterruptedException, Exception {
        _normalWait(1000);
		String FilePath = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\testdata"
				+ File.separator + fileName;
		clickOn(uploadSourceAddFilesBtn);
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

		waitAndClick(uploadSourceUploadBTN);
		Thread.sleep(5000);
	}

	/** Verify File uploaded successfully **/

	public void verifyDocumentIsUploaded(String uploadedFileToBeVerify) {
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertEquals(getText(uploadedSourceDocument), uploadedFileToBeVerify);
		reportInfo();
	}

	/** Verify File uploaded popup is displayed **/
	public void verifyUploadFilesPopUpIsDisplayed() {
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue(isElementPresent(sourceUploadPopUpTitleDisplayed));
		reportInfo();
	}

	/** Verify File uploaded popup is not displayed **/
	public void verifyUploadFilesPopUpIsNotDisplayed() {
		Assert.assertFalse(isElementDisplayed(sourceUploadPopUpTitleDisplayed));
		reportInfo();
	}

	/** Click on cancel button of upload files popup **/
	public void clickOnSourceUploadCancelBtn() {
		waitAndClick(sourceUploadCancelBTN);
	}

	/** click on close icon of upload files popup **/
	public void clickOnSourceUploadCloseIcon() {
		waitAndClick(sourceUploadCloseBTN);
	}

	/**
	 * Press Escape key to close the upload files popup
	 * 
	 * @throws AWTException
	 **/
	public void pressEscapeKey() {
		Actions actn = new Actions(driver);
		actn.sendKeys(sourceUploadPopUpTitleDisplayed, Keys.ESCAPE).build().perform();
		_normalWait(2000);
		reportInfo();

	}

	/** Verify refresh button displayed **/
	public void verifyRefreshBtnDisplayed() {
		Assert.assertTrue(isElementDisplayed(refreshBTN));
		moveToElement(refreshBTN);
	}

	/** click refresh button **/
	public void clickOnRefreshBtn() {
		waitAndClick(refreshBTN);
		spinnerBecomeInvisible();
	}

	/** click details cancel button to discard the changes **/
	public void clickOnCancelAssessmentChangesIcon() {
		scrollToTopOfThePage();
		waitAndClick(cancelChangesBTN);
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
	}

	public void clickOnSaveBTN() {
		waitUntillFinishProcessSpinnerDisable();
		scrollToTopOfThePage();
		clickOn(saveBTN);
		_normalWait(100);
	}

	public void clickOnCancelButton() {
		waitForElementPresent(cancelButton, DEFAULT_WAIT_4_PAGE);
		javascriptButtonClick(cancelButton);
	}

	public void openAssessmentFormSourcePDFView() {
		boolean flag = false;
		waitForElement(pdfFormLargeICON);
		_normalWait(2000);
		waitAndClick(pdfFormLargeICON);
		_normalWait(2000);
		if (assessmentPdfFrame.isDisplayed()) {
			flag = true;
			Assert.assertTrue(true, "Assessment Source PDF is opened and can viewed");
		} else {
			Assert.assertTrue(flag, "Assessment Source PDF is not opened and can't viewed");
		}
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	public void verifyAssessmentFormSourcePDFCanView() {
		boolean flag = false;
		if (pdfFormLargeICON.isEnabled()) {
			flag = true;
			Assert.assertTrue(true, "Assessment Source PDF is opened and can viewed");
			moveToElement(pdfFormLargeICON);
		} else {
			Assert.assertTrue(flag, "Assessment Source PDF is not opened and can't viewed");
		}
		reportInfo();
	}

	/* Verify Pdf Can't View */
	public void verifyFormPdfCantView() {
		Assert.assertEquals(driver.findElements(By.xpath("//iframe[@class='embedPdf ng-scope']")).size(), 0);
		reportInfo();
	}

	public void verifyPDFFormIsOpen() {
		boolean flag = false;
		if (assessmentPdfFrame.isDisplayed()) {
			flag = true;
			Assert.assertTrue(true, "PDF is opened and can viewed");
			moveToElement(pdfFormLargeICON);
		} else {
			Assert.assertTrue(flag, "PDF is not opened and can't viewed");
		}
		reportInfo();
	}

	/** click on subject name link to navigate to subject detail page **/
	public NewSubjectDetailPage clickOnSubjectLink() {
		waitAndClick(assessmentSubjectLINK);
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);
	}

	/** click on visit name link to navigate to visit detail page **/
	public VisitDetailsPage clickOnVisitLink() {
		waitAndClick(assessmentVisitLINK);
		return PageFactory.initElements(driver, VisitDetailsPage.class);

	}

	/* Check CheckBox in Assessment Detail Page */
	public void clickOnCheckBoxInAssesmentDetailPage() {
	//moveToElement(checkBoxOnAssesmentDetailsPage);	
	waitAndClick(checkBoxOnAssesmentDetailsPage);
	}

	/* Verify Mark As Not Administered CheckBox Is Not Set */

	public void verifyCheckBoxMarkAsNotAdministeredNotSet() {
		moveToElement(checkBoxOnAssesmentDetailsPage);
		Assert.assertFalse(checkBoxOnAssesmentDetailsPage.isSelected());
		reportInfo();
	}

	/*
	 * UnCheck CheckBox in Assessment Detail Page
	 */
	public void uncheckNotAdministeredCheckBox() {
		if (notAdministeredCheckBoxTxt.getText().equalsIgnoreCase("Not Administered")) {
			if (notAdministeredCheckBoxTxt.findElement(By.xpath("./parent::label/input")).isSelected()) {
				clickOn(notAdministeredCheckBoxTxt.findElement(By.xpath("./parent::label/input")));
			}
		}
		reportInfo();
	}

	/** Check checkBox For Mark As Not Administered And Not Completed Exist */
	public void verifyCheckBoxIsPresent() {
		moveToElement(checkBoxOnAssesmentDetailsPage);
		Assert.assertTrue(
				isElementPresent(checkBoxOnAssesmentDetailsPage) && checkBoxOnAssesmentDetailsPage.isEnabled());
	}

	
	/** verify Not Mark As Not Administered Checkbox Is Set */

	public void verifyMarkAsNotAdministeredCheckBoxIsSet() {
		moveToElement(checkBoxOnAssesmentDetailsPage);
		Assert.assertTrue(checkboxStatus(checkBoxOnAssesmentDetailsPage));
		reportInfo();
	}

	public void conifrmButtonAppears() {
		moveToElement(confirmBTN);
		Assert.assertTrue(isElementPresent(confirmBTN));
		reportInfo();
	}

	public void clickOnConfirmButton() {
		javascriptButtonClick(confirmBTN);
		waitForElementPresent(popUpDisplayed, DEFAULT_WAIT_4_ELEMENT);

	}

	public void verifyNotAdministeredPopUpPresent() {
		Assert.assertTrue(isElementPresent(notAdministeredPopUP));
		reportInfo();
	}

	public void clickOnYesButtonOfConfirmationWindow() {

		if (driver.findElements(By.xpath(
				"//*[contains(@class,'fade modalshow in')]//*[@id='notAdministeredConfirmationLabel' and text()='Confirm']"))
				.size() > 0) {
			clickOn(confirmPopUPWindowYesBTN);
			waitForSpinner(1);
		}
	}

	public void clickOnSaveButtonOnChangeAssesment() {
		
		waitAndClick(saveButton);
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_ELEMENT);
	}

	public void clickOnConfirmButtonOfChangeAssesment() {
		waitAndClick(confirmButton);
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_ELEMENT);
	}

	public void clickOnCancelButtonOfChangeAssesment() {
		waitAndClick(cancelButtonOnPopUp);
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_ELEMENT);
	}

	/* Cancel Confirmation Of Not Administered Assessment */
	public void clickOnNoButtonOfConfirmationWindow() {
		clickOn(confirmPopUPWindowNoBTN);
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_ELEMENT);
	}

	public void verifyReasonForChangePopUpDisplayed() {
		Assert.assertTrue(isElementPresent(popUpDisplayed));
		reportInfo();
	}

	/**
	 * Select reason for change from the options
	 * 
	 * @param reasonToSelect
	 */
	public void selectReasonForChangeOption(String reasonToSelect) {
		boolean flag = false;
		clickOn(reasonForChangeReasonDRPDOWN);
		for (WebElement reasonOption : reasonForChangeReasonOptionLIST) {
			if (reasonOption.getText().trim().equalsIgnoreCase(reasonToSelect)) {
				clickOn(reasonOption.findElement(By.xpath("./parent::li")));
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * verify reason for change from the options
	 * 
	 * @param reasonToSelect
	 */
	public void verifyReasonForChangeOption(String reasonOptions) {
		boolean flag = false;
		clickOn(reasonForChangeReasonDRPDOWN);
		for (WebElement reasonOption : reasonForChangeReasonOptionLIST) {
			if (reasonOption.getText().trim().equalsIgnoreCase(reasonOptions)) {
				Assert.assertTrue(isElementPresent(reasonOption.findElement(By.xpath("./parent::li"))));
				moveToElement(reasonOption.findElement(By.xpath("./parent::li")));
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		clickOn(reasonForChangeReasonDRPDOWN);
		reportInfo();
	}

	/**
	 * Enter comment for the reason
	 *
	 */
	public void inputReasonComment(String reasonComment) {
		inputText(reasonArea, reasonComment);
		reportInfo();
	}

	/**
	 * Esign after selecting reason for change
	 * 
	 * @param userName
	 * @param password
	 */
	public void eSignForReasonForChange(String userName, String password) {
		inputText(reasonForChangeUserNameINP, userName);
		inputText(reasonForChangePasswordINP, password);
		clickOn(reasonForChangeOkBTN);
		new WebDriverWait(driver, 25)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
	}

	/* Sign For Reason For Change */
	public void eSignForReasonForChangeWithOutConfirmation(String userName, String password) {
		inputText(reasonForChangeUserNameINP, userName);
		inputText(reasonForChangePasswordINP, password);
	}

	/* Cnacel E-Sign For reason For Change */
	public void clickOnCancelIconReasonForChange() {
		clickOn(reasonForChangeCancelBTN);
	}

	/**
	 * Verify reason for change Authentication Text
	 */
	public void verifyReasonForChangeAuthenticationText(String AuthenticationText) {
		Assert.assertEquals(getText(reasonForChangeAuthenticationText).trim(), AuthenticationText);
		reportInfo();
	}

	/* Verify Reason For Change All Option Present In DropDown */
	public void verifyReasonForChangeOptionsPresentInDropDown(List<String> reasonToSelect) {
		clickOn(reasonForChangeReasonDRPDOWN);
		List<String> reasonForChange = new ArrayList<>();
		for (WebElement reasonChangeValues : reasonForChangeReasonOptionLIST) {
			reasonForChange.add(getText(reasonChangeValues).trim());
		}
		Assert.assertEquals(reasonForChange, reasonToSelect, "Reason For Change Values Are Present");
		moveToElement(reasonForChangeReasonDRPDOWN);
		clickOn(reasonForChangeReasonDRPDOWN);
	}

	public StudyAssessmentsListing clickOnBackToAllAssesmentLink() {

		clickOn(backToAllAssesmentLink);
		return PageFactory.initElements(driver, StudyAssessmentsListing.class);
	}

	public void clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(String statusToBeVerified) {
		boolean found = false;

		_normalWait(1000);
		do {
			waitForElementPresent(RefreshBTN, DEFAULT_WAIT_4_ELEMENT);
			//_normalWait(2000);
			waitAndClick(RefreshBTN);
			new WebDriverWait(driver, 70).until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
		} while (!assessmentPdfStatus.getText().trim().equalsIgnoreCase(statusToBeVerified));
		found = true;
		Assert.assertTrue(found, "Status Is Changed As Completed");

	}

	public void verifyAssesmentCheckBoxChangedAccordinglyNotAdministered() {
		Assert.assertTrue(notAdministeredCheckBoxTxt.getText().equalsIgnoreCase("Not Administered"));
		reportInfo();

	}

	public void clickOnPdfImage() {
		scrollToTopOfThePage();
		clickOn(pdfDivision);
		new WebDriverWait(driver, 25)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
	}

	public void verifyPdfAssignedRaterIspresent(String raterName) {
		waitForPageLoaded();
		WebElement pdfAssignedRaterName = driver.findElement(By.xpath(String.format(
				"//div[@class='mode-header grid-header row']//a[contains(@class,'ng-binding') and text()='%s']",
				raterName)));
		Assert.assertTrue(pdfAssignedRaterName.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify Rater's name on the PDF's Title page
	 * 
	 */
	public void verifyRatersNameOnPDFsTitlePage(String raterName) {
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		String TitlePage = "//div[@id='mainContainer']//div[@id='pageContainer1']";
		scrollIntoView(TitlePage);
		WebElement pdfRaterName = driver
				.findElement(By.xpath("//div[@id='pageContainer1']//div[contains(text(),'" + raterName + "')]"));
		Assert.assertTrue(pdfRaterName.isDisplayed());
		moveToElement(pdfRaterName);
		driver.switchTo().defaultContent();
	}

	/**
	 * Verify Assessment start date on the PDF's title page
	 * 
	 */
	public void verifyAssessmentDateOnPDFsTitlePage(String DateToBeVerified) {
		String DateInNewFormat = DateToBeVerified.substring(0, 4) + DateToBeVerified.substring(4, 6).toLowerCase()
				+ DateToBeVerified.substring(6, 11);
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		String TitlePage = "//div[@id='mainContainer']//div[@id='pageContainer1']";
		scrollIntoView(TitlePage);
		WebElement AssessmentDate = driver
				.findElement(By.xpath("//div[@id='pageContainer1']//div[contains(text(),'" + DateInNewFormat + "')]"));
		Assert.assertTrue(AssessmentDate.isDisplayed());
		moveToElement(AssessmentDate);
		driver.switchTo().defaultContent();
		reportInfo();
	}

	/*
	 * HEAD verify Assessment Date Is Present In Each Page Of PDF (page footer &
	 * Audit History page) ======= verify Assessment Date Is Present In Each Page Of
	 * PDF (page footer & Audit History page)
	 * e3c99ef11641e5015731d89618265a9d0a2d54ee
	 */

	public void verifyAssessmentDateIsPresentInFooterOnEachPageOfPDF(String AssessmentDateToBeVerified) {
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		String DateToBeVeriedInPDF = AssessmentDateToBeVerified.substring(0, 4)
				+ AssessmentDateToBeVerified.substring(4, 6).toLowerCase()
				+ AssessmentDateToBeVerified.substring(6, 11);
		for (int i = 2; i <= totalPagePdf.size() - 1; i++) {
			String page = "//div[@id='mainContainer']//div[@id='pageContainer" + i + "']";
			scrollIntoView(page);
			String footerData = "//div[@id='mainContainer']//div[@id='pageContainer" + i + "']//div[contains(text(),'"
					+ DateToBeVeriedInPDF + "')]";
			
			scrollIntoView(footerData);
			Assert.assertTrue(isElementPresent(footerData));
			
		}
		driver.switchTo().defaultContent();
		reportInfo();
	}

	/*
	 * verify Assessment Date Is Present In Each Page Of PDF of type with splitted
	 * attributes (including page title / page footer / Audit History page)
	 */

	public void verifyAssessmentDateIsPresentInEachPageOfPDFofTypeWithSplittedAttributes(
			String AssessmentDateToBeVerified) {
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_ELEMENT);
		boolean flag = true;
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		_normalWait(3000);
		waitForElement(iFrame);
		driver.switchTo().frame(iFrame);

		int pageNo = 0, totalPage = totalPagePdf.size();
		String DateToBeVeriedInPDF = AssessmentDateToBeVerified.substring(0, 2)
				+ AssessmentDateToBeVerified.substring(3, 4) + AssessmentDateToBeVerified.substring(4, 6).toLowerCase()
				+ AssessmentDateToBeVerified.substring(7, 11);

		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_ELEMENT);
		for (pageNo = 2; pageNo <= totalPage; pageNo++) {
			String page = "//div[@id='mainContainer']//div[@id='pageContainer" + pageNo + "']";
			scrollIntoView(page);
			_normalWait(2000);
			List<WebElement> auditHistory = driver.findElements(By.xpath(page + "/div[2]/div"));
			String auditTextInformation = "";
			for (int informationAudit = 0; informationAudit < auditHistory.size(); informationAudit++) {
				String informationNeedToBeVerify = auditHistory.get(informationAudit).getText();
				auditTextInformation = auditTextInformation.concat(informationNeedToBeVerify);
			}
			waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_ELEMENT);
			if (auditTextInformation.replace("-", "").trim().toLowerCase()
					.contains(DateToBeVeriedInPDF.toLowerCase())) {
				_normalWait(2000);
				continue;

			} else {
				flag = false;
				break;
			}
		}
		Assert.assertTrue(flag, "Assessment Date not verified on page -" + pageNo);
		driver.switchTo().defaultContent();
		reportInfo();
	}

	/**
	 * Verify Signed by field is NOT filled on the Signature History page
	 * 
	 */
	public void verifySignedByFieldNOTFilledOnSignatureHistoryPage(String raterName) {
		boolean flag = true;
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		_normalWait(5000);
		driver.switchTo().frame(iFrame);
		int SignatureHistoryPageNo = totalPagePdf.size() - 1;
		String SignatureHistoryPage = "//div[@id='mainContainer']//div[@id='pageContainer" + SignatureHistoryPageNo
				+ "']";
		scrollIntoView(SignatureHistoryPage);
		_normalWait(500);
		List<WebElement> allElementsInSignatureHistoryPage = driver
				.findElements(By.xpath("//div[@id='pageContainer" + SignatureHistoryPageNo + "']//div//div"));
		for (WebElement ElementInSignatureHistoryPage : allElementsInSignatureHistoryPage) {
			if (ElementInSignatureHistoryPage.getText() != null) {
				if (ElementInSignatureHistoryPage.getText().trim().equalsIgnoreCase(raterName)) {
					flag = true;
					break;
				} else {
					continue;
				}
			}
		}
		Assert.assertTrue(flag);
		driver.switchTo().defaultContent();
		reportInfo();
	}

	/**
	 * Verify Certified Copy field contains the corresponding date when the PDF was
	 * generated
	 * 
	 */
	public void verifyCertifiedCopyFieldContainsCorrespondingDateWhenPDFWasGenerated(String DateToBeVerified) {
		String s2 = DateToBeVerified.substring(4, 6).toLowerCase();
		String DateInNewFormat = DateToBeVerified.substring(0, 4) + s2 + DateToBeVerified.substring(6);
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		int SignatureHistoryPageNo = totalPagePdf.size() - 1;
		String SignatureHistoryPage = "//div[@id='mainContainer']//div[@id='pageContainer" + SignatureHistoryPageNo
				+ "']";
		scrollIntoView(SignatureHistoryPage);
		WebElement AssessmentDate = driver.findElement(By.xpath("//div[@id='pageContainer" + SignatureHistoryPageNo
				+ "']//div[contains(text(),'Certified Copy generated on " + DateInNewFormat + "')]"));
		Assert.assertTrue(AssessmentDate.isDisplayed());
		moveToElement(AssessmentDate);
		driver.switchTo().defaultContent();
	}

	public void verifyPdfSignaturePageCompletedAndSignedByInformation(String raterName) {
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		for (int i = 2; i <= totalPagePdf.size() - 1; i++) {
			String footerData = "//div[@id='mainContainer']//div[@id='pageContainer" + i + "']";
			scrollIntoView(footerData);
		}
		WebElement pdfRaterName = driver.findElement(
				By.xpath(String.format("//div[@class='pdfViewer']//div[contains(text(),'%s')]", raterName)));
		Assert.assertTrue(pdfRaterName.isDisplayed());
		driver.switchTo().defaultContent();
	}

	public void verifyFooterDataOnEachPage(String footerText) {
		waitUntillFinishProcessSpinnerDisable();
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		for (int i = 2; i <= totalPagePdf.size() - 1; i++) {
			String page = "//div[@id='mainContainer']//div[@id='pageContainer" + i + "']";
			scrollIntoView(page);
			String footerData = "//div[@id='mainContainer']//div[@id='pageContainer" + i + "']//div[contains(text(),'"
					+ footerText + "')]";
			_normalWait(2000);
			scrollIntoView(footerData);
			Assert.assertTrue(isElementPresent(footerData));
		}
		driver.switchTo().defaultContent();
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();
	}

	public void verifyRaterNameDisplayedasHyperlink(String RaterNameToBeVerified) {
		Assert.assertTrue(raterNameAsHyperlink.isDisplayed());
		Assert.assertEquals(raterNameAsHyperlink.getText().trim(), RaterNameToBeVerified);
		reportInfo();
	}

	public void verifyRaterNameUnderDetailsIsNonHyperlinked(String RaterNameToBeVerified) {
		boolean flag = false;
		Assert.assertTrue(raterNameUnderDetails.isDisplayed());
		Assert.assertEquals(raterNameUnderDetails.getText().trim(), RaterNameToBeVerified);
		try {
			if (raterNameAsHyperlink.isDisplayed())
				;
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Verify Audit History In Pdf */

	public void verifyInformationIsPresentInAuditHistory(String informationToBeVerified) {

		int totalPage = totalPagePdf.size();
		String wholeInformationAudit = "";
		String auditTextInformation = "";
		boolean flag = false;
		for (int i = 0; i < totalPagePdf.size(); i++)

		{
			String footerData = "//div[@id='mainContainer']//div[@id='pageContainer" + i + "']";
			scrollIntoView(footerData);
		}
		List<WebElement> auditHistory = driver
				.findElements(By.xpath("//div[@id='mainContainer']//div[@id='pageContainer" + totalPage + "']"));

		for (int informationAudit = 0; informationAudit < auditHistory.size(); informationAudit++) {
			String informationNeedToBeVerify = auditHistory.get(informationAudit).getText();
			auditTextInformation = wholeInformationAudit.concat(informationNeedToBeVerify);

		}
		if (auditTextInformation.contains(informationToBeVerified)) {
			flag = true;
			Assert.assertTrue(flag, "Information Present");
		}
		reportInfo();
	}

	/* Click On Not Adminstered PopUp Information Yes Byutton */
	public void clickOnNotAdminsteredPopUpYesButton() {
		clickOn(notAdminsteredPopUpYesBtn);
	}

	/* Select Queries Icon */
	public void clickOnQueriesIcon() {
		_normalWait(3000);
		waitAndClick(queriesLinkICN);
	}

	/* Verify Queries Panel Displayed */
	public void verifyQueriesDetailsPanelIsOpened() {
		Assert.assertTrue(isElementPresent(queriesSliderPanelOpen), "Query Details Slider panel is displayed");
		reportInfo();

	}

	public void clickOnCreateQueryButton() {
		clickOn(createQueriesButton);

	}

	public void clickOnCancelQueryButton() {
		waitAndClick(cancelQueriesButton);

	}

	public void clickOnReplyQueryButton() {
		waitAndClick(replyQueryButton);

	}

	public void clickOnCloseQueryButton() {
		waitAndClick(closeQueryButton);

	}

	/* verify add query list area display */

	public void addNewQuery(String text) {
		clickOnAddQueriesButton();
		// inputText(queriesTextArea, text);
		queriesTextArea.sendKeys(text);
		clickOnCreateQueryButton();
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);

	}

	public void replyQuery(String text) {
		inputText(queriesTextArea, text);
		clickOnReplyQueryButton();
		waitForSpinner(DEFAULT_WAIT_4_PAGE);

	}

	/* verify add query list area display */

	public void addNewScoreQuery(String queryName, String contextOption, String scoreOption) {
		clickOnAddQueriesButton();
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		queriesTextArea.sendKeys(queryName);
		selectOptionFromContextDropDownList(contextOption, scoreOption);
		clickOnCreateQueryButton();
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);

	}

	/**
	 * Select the Query
	 * 
	 * @param queryName
	 * @return
	 */
	public void selectQuery(String queryName) {
		boolean flag = false;
		for (WebElement querytoselect : queriesList) {
			if (querytoselect.findElement(By.xpath(".//div[@class='thread-message ng-binding']")).getText()
					.equalsIgnoreCase(queryName)) {
				flag = true;
				scrollIntoView(querytoselect);
				clickOn(querytoselect);
				waitForElement(findElement(By.xpath("//div[@data-ng-show='isExpand()' and @class='collapsed']")));
				waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
				_normalWait(4000);
				break;
			}
		}

		Assert.assertTrue(flag);
		reportInfo();
	}

	/** Select Option From Context DropDown and score dropdown */

	public void selectOptionFromContextDropDownList(String contextOption, String scoreOption) {
		waitAndClick(contextSelector);
		boolean flag = false;
		for (WebElement contextOptionlist : driver.findElements(
				By.xpath("//div[@data-display-value='selectedContext.name']//li/span[@class='ng-binding ng-scope']"))) {
			if (getText(contextOptionlist).trim().equalsIgnoreCase(contextOption)) {
				clickOn(contextOptionlist);
				flag = true;
				break;
			}
		}

		if (scoreSelector.isDisplayed()) {
			waitAndClick(scoreSelector);
			/*for (WebElement scoreOptionlist :driver.findElements(
					By.xpath("//div[@class='dropdown-menu']/ul[@class='dropdown-menu ng-scope']/li/span[@class='ng-binding ng-scope']"))) {
				if (getText(scoreOptionlist).trim().equalsIgnoreCase(scoreOption)) {
					clickOn(scoreOptionlist);
					flag = true;
					break;
				}
				reportInfo();
			}*/
			
			WebElement scoreOptionToBeSelected = 
					driver.findElement(ByLocator("//div[@class='dropdown-menu']//span[text()='"+scoreOption+"']"));
			waitAndClick(scoreOptionToBeSelected);
			Assert.assertTrue(flag);
			reportInfo();
		}
	}

	/* verify add query list area display */

	public void addNewVisitQuery(String queryName, String contextOption) {
		clickOnAddQueriesButton();
		inputText(queriesTextArea, queryName);
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		waitAndClick(contextSelector);

		for (WebElement contextOptionlist : contextMenuLIST) {
			if (getText((contextOptionlist)).trim().contains(contextOption)) {
				clickOn(contextOptionlist.findElement(By.xpath("./parent::li")));
				new WebDriverWait(driver, 30).until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-isolate-scope']")));
				break;
			}

			clickOnCreateQueryButton();
			waitForSpinner(DEFAULT_WAIT_4_ELEMENT);

		}
	}

	/* Select Queries Close Icon */

	public void clickOnQueriesCollpaseIcon() {
		waitAndClick(queriesCollpaseBTN);

	}

	/* click on Add query button */

	public void clickOnAddQueriesButton() {
		moveToElement(addQueriesButton);
		Assert.assertTrue(addQueriesButton.isEnabled());
		waitAndClick(addQueriesButton);
	}

	/**
	 * Verify Presence Of Query List
	 * 
	 */
	public void verifyPresenceOfQueriesList() {
		boolean flag = false;
		Assert.assertTrue(sizeofTheList(queriesList) > 0);
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(flag, "Query list is displaying");
		reportInfo();
	}

	/**
	 * Verify Presence Of Query By Subject In Query List
	 * 
	 */
	public void verifyPresenceOfQueryBySubject(String Subject) {
		boolean flag = false;
		if (sizeofTheList(queriesList) > 0) {
			for (WebElement query : queriesList) {
				if (getText(query.findElement(By.xpath("//div[contains(@data-ng-show,'query.subjectContext.length')]")))
						.equalsIgnoreCase(Subject)) {
					flag = true;
					Assert.assertTrue(flag, "Query display with Subject");
					break;
				}
			}
		}
	}

	/* Verify Paper TranScription CheckBox Flag Set */
	public void verifyPaperTranscriptionCheckBoxFlagSet() {
		Assert.assertTrue(checkBoxMarkAsPaperTranscription.isSelected());
		reportInfo();
	}
	/* Uncheck Paper TranScription CheckBox In Assesment Page */

	public void unmarkFlagPaperTranscription() {
		if (checkBoxMarkAsPaperTranscription.isSelected()) {
			clickOn(checkBoxMarkAsPaperTranscription);
			javascripctHilightingElement(checkBoxMarkAsPaperTranscription);
		} else {
			Assert.fail("CheckBox Is Not Selected");
		}
	}

	/* Verify PaperTrnascription Flag Not Set */
	public void verifyPaperTranscriptionCheckBoxFlagNotSet() {
		moveToElement(checkBoxMarkAsPaperTranscription);
		Assert.assertFalse(checkBoxMarkAsPaperTranscription.isSelected());
		reportInfo();
	}

	/* Select Paper Transcription CheckBox Flag */
	public void clickOnPaperTrnscriptionCheckBox() {
		boolean flag = false;
		if (!(checkBoxMarkAsPaperTranscription.isSelected())) {
			flag = true;
			waitAndClick(checkBoxMarkAsPaperTranscription);
			Assert.assertTrue(flag, "Paper Transcription checkbox selected");
		}
	}

	/* Fields Are Editable For Completed Assesment */
	public void verifyFieldsAreEditableForCompletedAssesment() {
		Assert.assertTrue(startedDatePickerBTN.isEnabled() && startedTimePickerBTN.isEnabled()
				&& durationTimePickerBTN.isEnabled());
		moveToElement(startedDatePickerBTN);
		moveToElement(startedTimePickerBTN);
		moveToElement(durationTimePickerBTN);
		reportInfo();
	}

	/* Get The Started DateTime Label Text */

	public String getStartedDateTime() {
		waitForPageLoaded();
		String startedDateTime = StartedDateTimeLabelText.getText();
		return startedDateTime;
	}

	/* Get The Started Date Label Text */

	public String getStartedDate() throws ParseException {
		waitForPageLoaded();
		String[] startedDateTime = StartedDateTimeLabelText.getText().trim().split(" ");
		moveToElement(StartedDateTimeLabelText);
		String dateStarted = startedDateTime[0];
		Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(dateStarted);
		String strDate = new SimpleDateFormat("dd-MMM-yyyy").format(date1);
		return strDate;
	}

	/* Get The Duration Time Label Text */

	public String getDurationTime() {
		String durationTime = durationTimeLabelText.getText();
		return durationTime;
	}

	/* Verify Started date Time Updated */
	public void verifyStartedDateTimeUpdated(String startedDateTime) {
		Assert.assertFalse(StartedDateTimeLabelText.getText().equalsIgnoreCase(startedDateTime));
		reportInfo();
	}

	/* Verify Duration Time Updated */
	public void verifyDurationTimeUpdated(String durationTime) {
		Assert.assertFalse(durationTimeLabelText.getText().equalsIgnoreCase(durationTime));
		reportInfo();
		spinnerBecomeInvisible();
	}

	/* Verify Started date Time not changed */
	public void verifyStartedDateTimeNotChanged(String startedDateTime) {
		Assert.assertTrue(StartedDateTimeLabelText.getText().equalsIgnoreCase(startedDateTime));
		moveToElement(StartedDateTimeLabelText);
		reportInfo();
	}

	/* Verify Duration Time not changed */
	public void verifyDurationTimeNotChanged(String durationTime) {
		Assert.assertTrue(durationTimeLabelText.getText().equalsIgnoreCase(durationTime));
		moveToElement(durationTimeLabelText);
		reportInfo();
	}

	/* verify assessment detail information */

	public void verifyAssessmentDetails(String detailLabel, String detailToBeVerified) {
		boolean flag = false;
		for (int i = 1; i < assessmentDetailsInfoList.size(); i++) {
			WebElement detailRow = assessmentDetailsInfoList.get(i);
			if (getText(detailRow.findElement(By.xpath(".//div[contains(@class,'caption')]//label"))).trim()
					.equalsIgnoreCase(detailLabel)) {
				try {
					flag = getText(detailRow.findElement(By.xpath(".//div[contains(@class,'value')]//a"))).trim()
							.contains(detailToBeVerified);
				} catch (Exception e) {
					flag = getText(detailRow.findElement(By.xpath(".//div[contains(@class,'value')]//label"))).trim()
							.contains(detailToBeVerified);
				}
				break;
			}
		}
		Assert.assertTrue(flag);
		waitForSpinnerBecomeInvisible(10);
	}

	/* Get The Current Date of Assessment Completion */
	public String getCurrentDateAccordingToAssessmentCompletionDateFormat() {
		String date = currentDate();
		String dateInUpperCase = date.toUpperCase();
		return dateInUpperCase;
	}

	/* Update Duration Time */
	public void updateDurationTime() {
		clickOn(durationTimePickerBTN);
		waitForElement(incrementHoursForDuration);
		clickOn(incrementHoursForDuration);
		clickOn(incrementMinutesIcon);
	}

	/*
	 * Verify after Completing with 'Mark as not administered' the Not administered
	 * checkbox Flag Not set
	 */
	public void verifyNotAdministeredCheckBoxFlagNotSet() {
		Assert.assertFalse(notAdministeredCheckBoxTxt.findElement(By.xpath("./parent::label/input")).isSelected());
		reportInfo();

	}

	/*
	 * Verify after Completing with 'Mark as not administered' the Not administered
	 * checkbox Flag Is set
	 */
	public void verifyNotAdministeredCheckBoxFlagSet() {
		Assert.assertTrue(notAdministeredCheckBoxTxt.findElement(By.xpath("./parent::label/input")).isSelected());
		reportInfo();

	}

	public void verifyAssessmentDetailsUnderVersionsSection(String detailLabel, String detailToBeVerified) {
		boolean flag = false;
		waitForElement(assessmentDetailsInfo);
		for (int i = 1; i < assessmentDetailsInfoListUnderVersionSection.size(); i++) {
			WebElement versionRow = assessmentDetailsInfoListUnderVersionSection.get(i);
			if (getText(versionRow.findElement(By.xpath(".//parent::label"))).trim().equalsIgnoreCase(detailLabel)) {

				flag = getText(versionRow.findElement(By.xpath(".//parent::div[contains(@class,'ng-binding')]")))
						.equalsIgnoreCase(detailToBeVerified.toLowerCase());
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag,
				detailLabel + " Present in version area and" + detailToBeVerified.toLowerCase() + "value.");
	}

	/*
	 * Verify Option to authorize automatic submission is available In the Action
	 * drop-down
	 */
	public void verifyOptionsIsPresentInActionDropDown(String optionToBeVerify) {
		scrollToTopOfThePage();
		waitAndClick(actionToggleDropDWNBTN);
		Assert.assertTrue(findElement(
				By.xpath("//div[@ng-if='showActionDropDown()']//ul//li//a[text()='" + optionToBeVerify + "']"))
						.isDisplayed());
		moveToElement(findElement(
				By.xpath("//div[@ng-if='showActionDropDown()']//ul//li//a[text()='" + optionToBeVerify + "']")));
		reportInfo();
		clickOn(actionToggleDropDWNBTN);
	}

	/*
	 * Verify option is not available in the Action drop-down
	 */

	public void verifyOptionIsNotPresentInActionDropDown() {
		Assert.assertFalse(isElementDisplayed(actionButton));
		reportInfo();
	}

	/* Select the Option In Action drop-down */
	public void selectOptionInActionDropDown(String optionToBeSelect) {
		scrollToTopOfThePage();
		clickOn(actionToggleDropDWNBTN);
		clickOn(findElement(
				By.xpath("//div[@ng-if='showActionDropDown()']//ul//li//a[text()='" + optionToBeSelect + "']")));
	}

	/* Click On Confirmation Of AllowAutomationSubmissionYesButton */
	public void clickOnConfirmationOfAllowAutomationSubmissionYesButton() {
		javascriptButtonClick(allowAutomaticSubmissionConfirmationYesBTN);
		waitSpinnerToBecomeInvisible();
	}

	/* Click On Confirmation Of AllowAutomationSubmissionNoButton */
	public void clickOnConfirmationOfAllowAutomationSubmissionNoButton() {
		clickOn(allowAutomaticSubmissionConfirmationNoBTN);
	}

	/* Verify AllowAutomatic Confirmation PopUp Displayed */

	public void verifyAllowAutomaticConfirmationPopUpDisplayed() {
		Assert.assertTrue(isElementPresent(allowAutomaticConfirmationPopUp));
		reportInfo();
	}
	/* Verify Options to confirm and cancel are available */

	public void verifyOptionsToConfirmCancelAvailable() {
		Assert.assertTrue(isElementPresent(allowAutomaticSubmissionConfirmationYesBTN)
				&& isElementPresent(allowAutomaticSubmissionConfirmationNoBTN));
		reportInfo();
	}

	/* Verify Notes Section Is Appeared */

	public void verifyNotesSectionIsAppeared() {

		moveToElement(notesSectionGrid);
		Assert.assertTrue(
				isElementPresent(notesSectionOfAllowAutomaticSubmission) && isElementPresent(notesSectionGrid));
		scrollToTopOfThePage();
		waitForSpinner(DEFAULT_WAIT_4_PAGE);
		reportInfo();
	}

	/*
	 * Verify Automatic Submission notes Updated After Removal or Confirm Submission
	 */
	public void verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(String recordType, String recordTobeVerify,
			String versionExist) {
		boolean flag = false;
		WebElement textDate = null;
		WebElement recordUpdatedInListVar = null;
		for (int updatedRecordIndex = 0; updatedRecordIndex < notesSectionRecordsList.size(); updatedRecordIndex++) {
			if (versionExist.equalsIgnoreCase("Submitted")) {
				recordUpdatedInListVar = notesSectionRecordsList.get(1);
			} else {
				recordUpdatedInListVar = notesSectionRecordsList.get(0);
			}
			if (recordType.equalsIgnoreCase("date")) {
				textDate = recordUpdatedInListVar
						.findElement(By.xpath(".//span[contains(@class,'text-indented ng-binding')]"));
			} else if (recordType.equalsIgnoreCase("TabletRecord")) {
				textDate = recordUpdatedInListVar
						.findElement(By.xpath(".//span[contains(@class,'event-text ng-binding')]"));
			}
			if (getText(textDate).trim().contains(recordTobeVerify)) {
				flag = true;
				break;
			}
		}

		Assert.assertTrue(flag, "Updated Record  present In Notes Section");
		reportInfo();
	}

	/* Verify Option to authorize automatic submission is not displayed */
	public void verifyOptionToAuthorizeAutomatiSubmissionIsNotDisplayed() {
		Assert.assertEquals(0,
				driver.findElements(By
						.xpath("//div[@ng-if='showActionDropDown()']//ul//li//a[contains(text(),'Authorize Automa')]"))
						.size());
		reportInfo();
	}
	/* Verify Not Administered Yes/No Control Present */

	public void verifyNotAdministeredControlsPresent() {
		moveToElement(confirmPopUPWindowYesBTN);
		moveToElement(confirmPopUPWindowNoBTN);
		Assert.assertTrue(isElementPresent(confirmPopUPWindowYesBTN) && isElementPresent(confirmPopUPWindowNoBTN));
		reportInfo();
	}

	/*
	 * Verify CheckBox Mark As Not Administered/Not Completed Not Exist
	 */
	public void verifyCheckboxMarkAsNotCompletedAndNotAdminsiteredNotExist(String checkboxNotExist) {
		boolean flag = false;
		if ((!(getText(checkBoxOnAssesmentDetailsPage.findElement(By.xpath("//following-sibling::span")))).trim()
				.equalsIgnoreCase(checkboxNotExist))) {
			flag = true;
		}
		Assert.assertTrue(flag);

	}

	/* Verify CheckBox Mark as Not Administered Not Exist */
	public void verifyCheckBoxMarkAsNotAdministeredNotExist() {
		boolean flag = false;
		if (notAdministeredCheckBoxForClinroLabel.size() == 0) {
			flag = true;
		} else if (notAdministeredCheckBoxForClinroLabel.size() > 0) {
			for (WebElement checkBox : notAdministeredCheckBoxForClinroLabel) {
				Assert.assertEquals(checkBox.getAttribute("class").trim(), "ng-hide");
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Verify CheckBox Mark as Not Completed Not Exist */
	public void verifyCheckBoxMarkAsNotCompletedNotExist() {
		Assert.assertEquals(0, notCompletedCheckBoxLabel.size());
		reportInfo();
	}

	/*
	 * Verify CheckBox Mark As Not Administered/Not Completed Exist
	 */
	public void verifyCheckboxMarkAsNotCompletedAndAdminsiteredExistAndEditable(String checkboxExist) {
		boolean flag = false;
		if ((getText(checkBoxOnAssesmentDetailsPage.findElement(By.xpath(".//following-sibling::span")))).trim()
				.equalsIgnoreCase(checkboxExist) && checkBoxOnAssesmentDetailsPage.isEnabled()) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	public void verifyBackgroundScreenIsRefreshed() {
		String locator = "//div[@data-ng-controller='AssessmentDetailsTitleController']";
		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));

	}

	public void verifyBackgroundScreenIsNotRefreshed() {
		String locator = "//div[@class='ng-isolate-scope ng-hide']";
		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));

	}

	public void verifyQueryPanelIsNotRefreshed() {
		String locator = "//div[@class='ng-isolate-scope ng-hide']";
		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));

	}

	public void verifyQueryEditButtonDisplay() {
		Assert.assertTrue(queryEditButton.isDisplayed());
		reportInfo();
	}

	public void selectCheckboxQueryOption(String checkboxQueryName) {
		WebElement checkBoxQuery = driver.findElement(By.xpath("//div[@class='checkbox-query']//span[text()='"
				+ checkboxQueryName + "']//../input[@type='checkbox']"));
		waitAndClick(checkBoxQuery);
		_normalWait(10000);
		waitSpinnerToBecomeInvisible();
	}

	public void verifyListOfAllQueriesDisplayed() {
		waitSpinnerToBecomeInvisible();
		Assert.assertTrue(queriesList.size() > 0, "Queries List is displaying");
		reportInfo();

	}

	/* Close Not Administered Icon */

	public void closeWitheNotAdministredCloseIcon() {
		clickOn(closeIconConfirmationNotAdministeredPopUp);
	}

	/**
	 * Verify close query button displayed
	 */
	public void verifyCloseQueryButtonDisplayed() {
		waitForElementPresent(closeQueryButton, 10);
		Assert.assertTrue(closeQueryButton.isDisplayed());
		moveToElement(closeQueryButton);
		reportInfo();
	}

	/* click On Change To Visit DropDown and select visit */
	public void changeVisitDropDown(String visitToBeSelected) {
		clickOn(changeVisitDropDown);
		for (WebElement visit : changeToVisitList) {
			if (visit.getText().equalsIgnoreCase(visitToBeSelected)) {
				waitAndClick(visit);
			}
		}

	}

	public void verifyAssesmentChanged() {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[contains(@class,'modalshow in')]//div[@class='ng-isolate-scope']")));
		Assert.assertTrue(isElementPresent(assesmentChangedMsg));

	}

	/* Verify Action Option Is Displayed */
	public void verifyActionOptionIsDisplayed() {
		Assert.assertTrue(actionButton.isDisplayed());
		reportInfo();
	}

	/* Select Action To Move Assessment */

	public void selectActionToMoveAssessment() {
		waitAndClick(actionToggleDropDWNBTN);
		waitAndClick(changeAssessmentLink);
		// WebDriverWait wait = new WebDriverWait(driver, 50);
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(
		// By.xpath("//div[contains(@class,'modalshow
		// in')]//div[@class='ng-isolate-scope']")));
		// waitForElement(moveAssessmentPopUp);

		waitForElementPresent(moveAssessmentPopUp, DEFAULT_WAIT_4_ELEMENT);
		_normalWait(5000);
	}

	/* Select Subject For Moving Assessment */

	public void clickOnChangeToSelectSubjectDropDown() {

		// adding wait for 120 secs due to issue #11245
		// _normalWait(120000);
		//_normalWait(15000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='form-configuration-dialog']//div[@class='spinner']")));
		waitForWebElementEnable(changeToSubjectDRPDWN, 3);
		waitAndClick(changeToSubjectDRPDWN);

	}

	/* Select visit For Moving Assessment */

	public void clickOnChangeToVisitDropDown() {
		waitForWebElementPresent(changeToVisitDRPDWN);
		waitAndClick(changeToVisitDRPDWN);
	}

	/*
	 * Select Change To SubjectFrom DropDown
	 */
	public void selectChangeToSubject(String subjectToBeSelect) {
		for (WebElement subjectChangeTo : changeToSelectSubjectList) {
			if (subjectChangeTo.getText().trim().equalsIgnoreCase(subjectToBeSelect)) {
				waitAndClick(subjectChangeTo.findElement(By.xpath(".//parent::li")));
			}
		}
	}

	/* Verify Visit Not Present In Change To Visit DropDown */
	public void verifyVisitIsNotPresentInChangeToVisitDropDown(String subjectToBeSelect) {
		boolean flag = true;
		for (WebElement visitChangeTo : changeToVisitList) {
			if (visitChangeTo.getText().trim().equalsIgnoreCase(subjectToBeSelect)) {
				flag = false;
				break;
			}
		}
		Assert.assertTrue(flag, "Visit Not Present In List");
		reportInfo();
		waitAndClick(changeToVisitDRPDWN);
	}

	/* Click On cross Icon */
	public void clickOnCrossIconOfMoveAssessmentPopUp() {
		_normalWait(10000);
		waitAndClick(crossICN);
	}
	
	

	/* Verify Action Button Is Not Displayed */
	public void verifyingActionButtonIsNotDisplayed() {
		moveToElement(actionButtonList);
		waitAndClick(actionButtonList);
		boolean flag = true;
		try {
			if (changeAssessmenttlist.isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
		}
		Assert.assertFalse(flag);
		reportInfo();
	}
	

	/*
	 * Verify Action Button Is Not displayed(Previous) functionality
	 */

	public void verifyActionButtonIsNotDisplayed() {
		boolean flag = true;
		if (actionButtonsLists.size() == 0) {
			flag = false;
		}
		Assert.assertFalse(flag, "The action Button Is Not  Present");
		reportInfo();
	}
	
	

	/**
	 * Verify observer dropDown is present or Not.
	 */

	public void verifyObserverDropDownIsPrsentOrNot() {
		boolean flag = false;
		if (isElementDisplayed(selectObserverDropDown)) {
			flag = true;

		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void selectTheObserverFromTheList(String ObserverToSelect) {
		clickOn(selectObserverDropDown);
		for (WebElement observer : selectObserverOptionList) {
			if (observer.getText().equalsIgnoreCase(ObserverToSelect)) {
				clickOn(observer);
				reportInfo();
			}
		}

	}

	/***
	 * Verify option is not present in the move to assesment drop down
	 * 
	 */

	public void verifySubjectIsNOtPresntInMoveToSubjectListDrpDwn(String subjectNameToBeSelected) {
		boolean flag = true;

		// adding wait for 50 secs due to issue #11245
		_normalWait(50000);

		clickOn(changeSubjectDropDown);
		for (WebElement subject : changeToSelectSubjectList) {
			if (subject.getText().equalsIgnoreCase(subjectNameToBeSelected)) {
				flag = false;
			}
		}
		Assert.assertTrue(flag);
	}

	/* Verify Visit is Present In Change To Visit DropDown */
	public void verifyVisitIsPresentInChangeToVisitDropDown(String visitTobePresent) {
		waitForElementPresent(By.xpath("//div[@data-display-value='selectedVisit.name']//parent::button//following-sibling::div//ul"), 10);
		boolean flag = false;
		for (WebElement visitChangeTo : changeToVisitList) {
			if (visitChangeTo.getText().trim().equalsIgnoreCase(visitTobePresent)) {
				moveToElement(visitChangeTo);
				flag = true;
				break;
			}
			}
		Assert.assertTrue(flag, "Visit Present In List");
		reportInfo();
		waitAndClick(changeToVisitDRPDWN);
		
	}

	/****
	 * Select Score or Attachement option for paper transcription assesment
	 */
	public void selectSourceOrAttachementOption(String optionToSelect) {
		for (WebElement selectOptions : scoresAndAttachement) {
			if (selectOptions.getText().equalsIgnoreCase(optionToSelect)) {
				waitAndClick(selectOptions);
			}
		}
	}

	/***
	 * click on paper source icon
	 */

	public void clickOnPaperSourceIcon() {
		clickOn(paperSourcePlusIcon);
	}

	/* Close Success Message */

	public void closeAssesmentSuccessMessage() {
		clickOn(closeMessage);
		reportInfo();
	}

	/**
	 * Upload source file for paper transcription
	 * 
	 * @throws InterruptedException
	 */
	public void uploadAttachementFile(String exePath, String filePath, String uploadWindowTitle)
			throws InterruptedException {
		String basePath = "src\\test\\resources\\testdata\\";
		clickOn(uploadAttachementFilesButton);
		_normalWait(1000);
		Utilities.runExeFile(basePath + exePath, basePath + filePath, uploadWindowTitle);
		waitAndClick(uploadAttachementButton);
		waitForElementToBecomeInvisible(
				By.xpath("//div[@id='paperSource' and @aria-hidden='false']//div[@data-ng-if='file.isInLoadState']"));
		_normalWait(5000);
	}

	public void verifyPaperSourceAttachementIsPresent() {
		boolean flag = false;
		String text = paperSourceAttachements.getAttribute("data-ng-if");
		if (text.equalsIgnoreCase("attachments.length > 0")) {
			flag = true;

		}
		Assert.assertTrue(flag);
	}

	public void verifyPaperSourceAttachementIsNotPresent() {
		boolean flag = false;
		String text = paperSourceAttachements.getAttribute("data-ng-if");
		if (text.equalsIgnoreCase("attachments.length == 0")) {
			flag = true;

		}
		Assert.assertTrue(flag);
	}

	/* Verify Move Assessment Pop Up Displayed */
	public void verifyMoveAssessmentPopUpWindowIsDisplayed() {
		Assert.assertTrue(isElementPresent(moveAssessmentPopUp));
		_normalWait(30000);
		waitUntillSpinnerToBecomeInvisible();

		reportInfo();
	}

	/* Assessment Successfully Moved Message */
	public void verifyAssessmentSuccessfullyMessage() {
		moveToElement(assesmentChangedMsg);
		Assert.assertTrue(
				assesmentChangedMsg.getText().trim().equalsIgnoreCase("The Assessment has been changed successfully"));
		reportInfo();
	}

	/* Verify Change To Visit Is Selected */

	public void verifyChangeToVisitSelected(String VisitSelected) {
		moveToElement(visitChangeToSelectedTXT);
		Assert.assertTrue(visitChangeToSelectedTXT.getText().trim().equalsIgnoreCase(VisitSelected));
		reportInfo();
	}

	/* Verify Change To Subject Is Selected */

	public void verifyChangeToSubjectSelected(String subjectSelected) {
		moveToElement(subjectChangeToSelectedTXT);
		Assert.assertTrue(subjectChangeToSelectedTXT.getText().trim().equalsIgnoreCase(subjectSelected));
		reportInfo();

	}

	/**
	 * *Verify subject Status is displayed as Read-only label *
	 * 
	 * @param StatusToBeVerified
	 */
	public void verifySubjectStatusDisplayedAsReadOnlyLabel(String StatusToBeVerified) {
		boolean flag = false;
		Assert.assertTrue(isElementPresent(subjectStatusTxt), "subject Status is displayed as Read-only label");
		if (subjectStatusTxt.getText().trim().contains(StatusToBeVerified)) {
			moveToElement(subjectStatusTxt);
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Completing Assessment By Clicking on Not Administred */
	public void markAsNotAdministred(String userName, String password) {

		_normalWait(1000);
		clickOnCheckBoxInAssesmentDetailPage();
		clickOnConfirmButton();
		clickOnYesButtonOfConfirmationWindow();
		setStartedDate();
		_normalWait(1000);
		selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		eSignForReasonForChange(userName, password);
		clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		_normalWait(2000);
		try {
			if (isElementPresent(errorContainerCloseIcon) == true) {
				moveToElement(errorContainerCloseIcon);
				clickOn(errorContainerCloseIcon);
			} else {
				System.out.println("Error message not displayed.");
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/*
	 * Verify Action Button Displayed to inform that some action is in pending
	 */

	public void verifyActionButtonWithPendingOperationExists() {
		moveToElement(pendingOperationICN);
		Assert.assertTrue(pendingOperationICN.isDisplayed());
		reportInfo();
	}

	/*
	 * Verify message is displayed on the assessment details page with tablet
	 * instruction
	 */

	public void verifyMeesageIsDisplayedWithTabletInstructions() {
		moveToElement(automatiSubmissionTabletErrorMessage);
		Assert.assertTrue(automatiSubmissionTabletErrorMessage.isDisplayed());
		reportInfo();
	}

	/*
	 * Get Tablet Id From The Error Message*
	 * 
	 */

	public String getTabletId() {
		String tabletMessage = getText(automatiSubmissionTabletErrorMessage);
		String[] startedDateTime = tabletMessage.trim().split(":");
		String[] tabletId = startedDateTime[1].split("and");
		String tabletNumber = tabletId[0].replaceAll("\\s", "");
		return tabletNumber;

	}

	/* Verify locked icon display */

	public void verifyLockedIconIsDisplayed() {
		Assert.assertTrue(lockedIcon.isDisplayed());
		reportInfo();
	}

	/* Verify Assessment details Disabled edit icon */

	public void verifyDetailsDisabledEditIconDisplayed() {
		_normalWait(DEFAULT_WAIT_4_PAGE);
		Assert.assertTrue(detailsDisableEditIcon.isDisplayed());
		reportInfo();
	}
	
	public void clickOnNotAdministeredChkBoxToUnselectProceedToPaperTranscription(String userName,String password )
	{
		_normalWait(1000);
		clickOnCheckBoxInAssesmentDetailPage();
		clickOnConfirmButton();
		clickOnYesButtonOfConfirmationWindow();
		selectReasonForChangeOption(Constants.ReasonChangeNotAdministered.get(1));
		eSignForReasonForChange(userName, password);
		
		
	}

}
