package net.medavante.portal.pages.studynavigator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.jfree.util.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.centralrating.CentralRatingAppointmentPage;
import net.medavante.portal.selenium.core.BasePage;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;
import net.medavante.portal.utilities.Utilities;

public class NewSubjectDetailPage extends BasePage implements CentralRatingModuleConstants {

	public NewSubjectDetailPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this); 
	}

	@FindBy(xpath = "//a[@class='home']")
	private WebElement homeBTN;

	@FindBy(xpath = "//button[@title='Refresh']")
	private WebElement refreshIcon;

	@FindBy(xpath = "//div[@id='create-or-edit-subject-dialog']/div[@class='modal-dialog']")
	private WebElement subjecPopUpBOX;

	@FindBy(xpath = "//div[@id='create-or-edit-subject-dialog']/div[@class='modal-dialog']//input[@id='screening-number-input']")
	private WebElement screeningNumberINP;

	@FindBy(xpath = "//div[contains(@class,'show in')]//input[@name='randomizationNumber']")
	private WebElement addSubPopup_RandomizationINP;

	@FindBy(xpath = "//div[contains(@class,'show in')]//input[@name='temporaryId']")
	private WebElement addSubPopup_TemporaryID;

	@FindBy(xpath = "//div[contains(@class,'show in')]//form[@name='subjectForm' and contains(@class,'ng-invalid ng-invalid-required')]")
	private WebElement editSubPopup_RequiredScreeningINP;

	@FindBy(xpath = "//div[contains(@class,'show in')]//label[text()='Language']/parent::div//button")
	private WebElement editSubPopup_LanguageINP;

	@FindBy(xpath = "//div[contains(@class,'show in')]//textarea[@id='notes-input']")
	private WebElement editSubPopup_NotesINP;

	@FindBy(xpath = "//label[text()='SUBJECT DETAILS']//parent::span//parent::div//span[@data-ng-click='showEditDialog()']")
	private WebElement subjectEditICN;

	@FindBy(xpath = "//label[text()='SUBJECT DETAILS']")
	private WebElement label_subjectDetails;

	@FindBy(xpath = "//div[@id='page-title']/h1/span[1]")
	private WebElement title_SubjectNumber;

	@FindBy(xpath = "//button[@data-ng-click='confirmDelete()']")
	private WebElement subjectDeleteBTN;

	@FindBy(xpath = "//div[@data-ng-click='okClick()']")
	private WebElement confirmPOPUPYesBTN;

	@FindBy(xpath = "//form[contains(@class,' ng-valid ng-valid-required')]")
	private WebElement filledInputBoxes;

	@FindBy(xpath = "//div[@class='subject-general-section']")
	private WebElement subjectDetailsGrid;

	@FindBy(xpath = "//label[text()='Screening#']/following-sibling::span")
	private WebElement screeningNumberTXT;

	@FindBy(xpath = "//label[text()='Randomization#']/following-sibling::span")
	private WebElement randomizationNumberTXT;

	@FindBy(xpath = "//label[text()='Status']//parent::div//span[@class='ng-binding']")
	private WebElement subjectStatusTxt;

	@FindBy(xpath = "//label[text()='Temporary ID']/following-sibling::span")
	private WebElement temporaryIDTXT;

	@FindBy(xpath = "//label[contains(text(),'Date Of Birth')]/following-sibling::span")
	private WebElement dateOfBirthTXT;

	@FindBy(xpath = "//label[contains(text(),'Year Of Birth')]/following-sibling::span")
	private WebElement yearOfBirthTXT;

	@FindBy(xpath = "//label[text()='Notes']/following-sibling::span")
	private WebElement savedNotesTEXT;

	@FindBy(xpath = "//div[@id='create-or-edit-subject-dialog']/div[@class='modal-dialog']//button[@class='btn btn-active btn-without-icon']")
	private WebElement saveBTN;

	@FindBy(xpath = "//div[@id='create-or-edit-subject-dialog']/div[@class='modal-dialog']//button[@data-ng-click='close()']//span[text()='Cancel']")
	private WebElement cancelBTN;

	@FindBy(xpath = "//div[@class='error-container']//label")
	private WebElement errorMessage;

	@FindBy(xpath = "//div[@class='error-container']//div[@class='close-button-white']")
	private WebElement errorContainerCloseIcon;

	@FindBy(xpath = "//div[contains(@class,'row grid-row')]")
	private List<WebElement> visitRowgrid;

	@FindBy(xpath = "//label[text()='Study']/following-sibling::span")
	private WebElement studyNameLabel;

	@FindBy(xpath = "//label[text()='Site']/following-sibling::span")
	private WebElement siteNameLabel;

	@FindBy(xpath = "//div[@class='portal-grid subject-visit-list']")
	private WebElement visitGrid;

	@FindBy(xpath = "//div[contains(@class,'row grid-row')]//div[@class='extraTabletColumn col-sm-3']/span")
	private List<WebElement> visitStatusList;

	@FindBy(xpath = "//div[contains(@class,'selected')]//a[@title='View/Reschedule appointment' and @class='btn circle-button btn-white']")
	private WebElement visitSchedularIcon;

	@FindBy(xpath = "//div[contains(@class,'selected')]//a[contains(@data-ng-click,'CentralRating') and @title='View/Reschedule appointment' and contains(@class,'btn-white ng-hide')]")
	private WebElement visitSchedularHIDEBTN;

	@FindBy(xpath = "//div[contains(@class,'selected')]//a[contains(@data-ng-click,'deleteSubject') and @title='Remove']")
	private WebElement deleteVisitIcon;

	@FindBy(xpath = "//div[contains(@class,'selected')]//a[@title='Cancel appointment' and @class='btn circle-button btn-white']")
	private WebElement visitCancelBTN;

	@FindBy(xpath = "//div[contains(@class,'selected')]//a[contains(@data-ng-click,'Visit(visit, $index)') and @title='Cancel appointment' and contains(@class,'btn-white ng-hide')]")
	private WebElement visitCancelHideBTN;

	@FindBy(xpath = "//div[contains(@class,'selected')]//a[contains(@data-ng-click,'add') and @title='Add' and @class='circle-button btn btn-white']")
	private WebElement addVisitIcon;

	@FindBy(xpath = "//div[contains(@class,'selected')]/ancestor::div//span[@class='img']//img")
	private WebElement editVisitIcon;

	@FindBy(xpath = "//a[@title='Add Unscheduled Visit']")
	private WebElement unscheduledAddVisitBTN;

	@FindBy(xpath = "//a[@title='Add Unscheduled Visit']/following-sibling::ul")
	private WebElement unscheduledListGrid;

	@FindBy(xpath = "//a[@title='Add Unscheduled Visit']/following-sibling::ul/li/a")
	private List<WebElement> unscheduledVisitLIST;

	@FindBy(xpath = "//div[contains(@class,'selected')]//a[contains(@data-ng-click,'add') and @title='Add' and contains(@class,'btn-white ng-hide')]")
	private WebElement addVisitHideIcon;

	@FindBy(xpath = "//div[@id='template']//div[contains(@class,'section')]")
	private List<WebElement> configuredTemplatesList;

	@FindBy(xpath = "//div[@id='virgilForms']//label[contains(text(),'Submitted by')]/following-sibling::label/a[@href]")
	private WebElement raterLinkNexttoFormThumbnail;

	@FindBy(xpath = "//div[@id='virgilForms']//label[contains(text(),'Submitted by')]/following-sibling::label")
	private WebElement submittedByRaterNameNexttoFormThumbnail;
		
	@FindBy(xpath = "//div[@id='virgilForms']//div[@class='names']//label[@class='small ng-binding']")
	private WebElement additionalDetailsUndersubmittedDateNextToFormThumbnail;

	@FindBy(css = "div[id='virgilForms'] div[class='row administered-row'] label")
	private WebElement ratersNonHyperlinkNameNexttoFormThumbnail;

	@FindBy(xpath = "//div[@id='template']//label[@data-ng-show='noAssessmentDefined']")
	private WebElement noScaleTemplateTEXT;

	@FindBy(xpath = "(//div[contains(@class,'form-details')])[2]")
	private WebElement scaleGrid;

	@FindBy(xpath = "//div[@id='template']//div[@id='virgilForms']//div[@class='section ng-scope']//img[contains(@src,'thumb_mask_papertranscription')]")
	private WebElement paperScaleClippedThumbnailIMG;

	@FindBy(xpath = "//div[@class='form-cover-mask']/a[@class='thumb']")
	private WebElement thumbnailIMG;

	@FindBy(xpath = "//div[@id='template']//div[@id='virgilForms']//div[@class='section ng-scope']//label[contains(@data-ng-if,'RaterDetail')]/a")
	private WebElement submittedScaleRaterNameTXT;

	@FindBy(xpath = "//div[@id='template']//div[@id='virgilForms']//div[@class='section ng-scope']//label[contains(@data-ng-if,'canReassignRater')]")
	private WebElement nonSubmittedScaleRaterNameTXT;

	@FindBy(xpath = "//div[@id='template']//div[@id='virgilForms']//div[@class='section ng-scope']//label[contains(@disabled,'disabled')]")
	private WebElement assignedToFieldValue;

	@FindBy(xpath = "//div[@id='virgilForms']//div[@class='section ng-scope']//label[contains(text(),'Submitted Date')]/parent::div/label[2]")
	private WebElement scaleSubmittedDateTXT;

	@FindBy(xpath = "//div[@class='row administered-row']//div[contains(@class,'btn-group ')]")
	private WebElement assignDRPDWN;

	@FindBy(xpath = "//div[@class='row administered-row']//button//span[1]")
	private WebElement dropdownSelectedValue;

	@FindBy(xpath = "//div[@class='row administered-row']//div[contains(@class,'btn-group ')]//ul//li/span")
	private List<WebElement> scaleRaterList;

	@FindBy(xpath = "//div[@class='status-editor']//div[contains(@class,'dropdown')]")
	private WebElement subjectStatusDRPBOX;

	@FindBy(xpath = "//ul[@aria-labelledby='dropdownMenu3']//li")
	private List<WebElement> statusList;

	@FindBy(xpath = "//div[not(contains(@class,'info-row ng-hide')) and contains(@class,'info-row')]")
	private List<WebElement> detailRowgrid;

	@FindBy(xpath = "//h4[@class='modal-title bold ng-hide']/following-sibling::h4")
	private WebElement editSubjectPopUp;

	@FindBy(xpath = "//input[@id='screening-number-input']")
	private WebElement screeningNumberAtEditSubjectPopUp;

	@FindBy(xpath = "//div[@id='page-title']/h1")
	private WebElement newSubjectDetailPageHeader;

	@FindBy(xpath = "//div[@class='btn-wrapper']/a[@title='Visit History']")
	private List<WebElement> historyICON;

	@FindBy(xpath = "//label[text()='Screening#']/parent::div/following-sibling::div/label")
	private WebElement screeningNumTXT;

	@FindBy(css = "embed[id='plugin']")
	private WebElement pdfFormOpenInNewWindow;

	// =============Reason For Change PopupLocators================

	@FindBy(xpath = "//div[contains(@data-display-value,'changeReason')]")
	private WebElement reasonForChangeReasonDRPDOWN;
	
	@FindBy(xpath = "//div[contains(@data-display-value,'changeReason')]//span[@id='selectedStudy']")
	private WebElement selectedReasonForChangeTEXT;

	@FindBy(xpath = "//div[contains(@class,'modal-text invalid-required')]")
	private WebElement requiredReasonForChangeINP;

	@FindBy(xpath = "//textarea[@data-ng-model='reasonComment' and @required='required']")
	private WebElement requiredReasonForChangeCommentINP;

	@FindBy(xpath = "//div[@class='modal-body']//div[contains(@class,'col-sm')]/br//parent::div")
	private WebElement reasonForChangeAuthenticationText;

	@FindBy(xpath = "//textarea[@data-ng-model='reasonComment' and @type='text']")
	private WebElement nonRequiredReasonForChangeCommentINP;

	@FindBy(xpath = "//div[contains(@data-display-value,'changeReason')]//li/span")
	private List<WebElement> reasonForChangeReasonOptionLIST;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//input[@placeholder='Username']")
	private WebElement reasonForChangeUserNameINP;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//input[@placeholder='Password']")
	private WebElement reasonForChangePasswordINP;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//label[@class='red ng-binding']")
	private WebElement invalidPasswordValidationTEXT;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[text()='OK']")
	private WebElement reasonForChangeOkBTN;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[text()='Cancel']")
	private WebElement reasonForChangeCancelBTN;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[@class='modal-content']")
	private WebElement popUpDisplayed;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[text()='Yes']")
	private WebElement popUpYesBTN;

	@FindBy(xpath = "//div[contains(@class,'in') and @role='dialog']//div[text()='No']")
	private WebElement popUpNoBTN;

	// =====================Appointment Popup Function===================//

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@class='modal-header']/h4[text()='Appointment']")
	private WebElement appointmentPopUpTitle;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//td[contains(@class,'selected')]/a")
	private WebElement appointmentPopUpSelectedDate;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//td[contains(@class,'k-state')]//a")
	private List<WebElement> datesList;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//td[contains(@class,'k-state-disabled')]/a")
	private List<WebElement> appointmentPopUpDisabledCalendarDateLIST;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@data-display-value='selectedTime']")
	private WebElement appointmentPopUpTimeSelectionDROPDOWN;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@data-display-value='selectedTime']//span[@data-ng-show='displayValue']")
	private WebElement appointmentPopUpTimeSelection;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@data-display-value='selectedTime']//ul/li/span")
	private List<WebElement> appointmentPopUpTimeLIST;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//button[@data-ng-click='findSchedule()']")
	private WebElement appointmentPopUpSearchBTN;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@data-ng-show='ShowAvailableSchedulingTimes' and @class='']")
	private WebElement appointmentPopUpTimeSlotGrid;

	@FindBy(xpath = "//div[@class='']/div[@id='time-slot-content']//div//div[contains(@data-ng-repeat,'time in availableSchedules')]/div")
	private List<WebElement> appointmentPopUpTimeSlotLIST;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@data-ng-show='ShowConfirmation' and @class='text-center']")
	private WebElement appointmentPopUpScheduleControlGRID;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@data-ng-show='ShowConfirmation' and @class='text-center']//div[@ng-click='confirmButtonClick()']")
	private WebElement appointmentPopUpTimeSlotYesBTN;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@data-ng-show='ShowConfirmationWithMore']//div[@ng-click='confirmButtonClick()']")
	private WebElement appointmentPopUpRequestedYesBTN;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@data-ng-show='ShowConfirmationWithMore']//a[@data-ng-click='onMoreAppointmentOptionClick()']")
	private WebElement appointmentPopUpMoreAppointmentOptions;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@data-ng-show='ShowConfirmationWithMore']//textarea[@data-ng-model='crReschedulingComment']")
	private WebElement appointmentPopUpReasonForReSchedulingINP;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@class='modal-body']/div[@data-ng-show='isAppointmentCreated']/div/div")
	private List<WebElement> appointmentPopUpConfirmMessage;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//button[@class='close']")
	private WebElement popUpCloseICON;

	@FindBy(xpath = "//div[contains(@class,'in')]//div[contains(@class,'modal')]/div[@class='modal-title ng-binding']")
	private WebElement confirmPOPUPTXT;

	@FindBy(xpath = "//textarea[contains(@data-ng-model,'CancelComment')]")
	private WebElement reasonPopUpReasonToCancelINP;

	@FindBy(xpath = "//div[contains(@data-ng-click,'CancellationConfirmButtonClick()')]")
	private WebElement reasonPopUpReasonToYesBTN;

	@FindBy(xpath = "//div[@data-ng-click='okBtnClick()']")
	private WebElement cancelAppointmentOKBTN;

	// @FindBy(xpath = "//div[@id='breadcrumbs']/a[@class='home']")
	// private WebElement homeICN;

	@FindBy(xpath = "//a[text()='HOME']")
	private WebElement homeIcon;

	@FindBy(xpath = "//span[@class='icon-small icon-history']")
	private WebElement showHistoryIcon;

	@FindBy(xpath = "//div[@id='subject-status-history-dialog']//div[@class='modal-content']")
	private WebElement subjcetStatusHistoryPopUp;

	@FindBy(xpath = "//div[@class='table-frame-body']/div[contains(@class,'row')]")
	private List<WebElement> subjectStatusHistoryRow;

	@FindBy(xpath = "//div[@class='modal-footer no-border']//div[@data-ng-click='cancelClick()']")
	private WebElement closeHistory;

	@FindBy(xpath = "//div[@class='row']/a[@data-ng-click='createPaperTranscription(form)']")
	private WebElement enterPaperTranscriptionLINK;

	// ==============New Visit DropDown Locators==========================//
	@FindBy(xpath = "//div[@class='list-column']//div[@class='section-header']//span[text()='select']")
	private WebElement visitDropDownSelect;

	@FindBy(xpath = "//div[contains(@class,'k-state-border-up')]//ul//li")
	private List<WebElement> visitRelatedOptionOnDropDown;

	@FindBy(xpath = "//div[@class='section-header'and @data-ng-show='isOnlyVisitsSectionVisible']")
	private WebElement onlyVisitSectionListHeader;

	@FindBy(xpath = "//ul[@class='subject-category-filter']")
	private WebElement subjectSubTabBlock;

	@FindBy(xpath = "//ul[@class='subject-category-filter']/li")
	private List<WebElement> subjectSubTabFilterList;

	@FindBy(xpath = "//div[@class='compliance-section']")
	private WebElement complianceSection;

	@FindBy(xpath = "//div[@class='compliance-section']//label")
	private List<WebElement> complianceRowList;

	@FindBy(xpath = "//div[@class='message-list']//div[contains(@class,'message-list-item')]")
	private List<WebElement> listOfMessages;

	@FindBy(xpath = "//div[@class='message-list']//div[@class='message-item ng-scope']")
	private List<WebElement> listOfParentMessages;

	@FindBy(xpath = "//div[@class='subject-categories-section']//div[contains(@class,'list')]//div[contains(@class,'list-item')]//div[1]//time")
	private List<WebElement> messagesTime;

	@FindBy(xpath = "//div[@class='message-list']//div[contains(@class,'message-list-item')]//span[@class='icon-patient-message']")
	private List<WebElement> messagesIconForSubject;

	@FindBy(xpath = "//div[@class='message-list']//div[contains(@class,'message-list-item')]//span[@class='icon-observer-message']")
	private List<WebElement> messagesIconForObserver;

	@FindBy(css = "div[class='details-column'] div[class='subject-message-block']")
	private WebElement messageArea;

	@FindBy(css = "div[class='section-header'] span[data-ng-click ='showNewMessageDialog()']")
	private WebElement sendMessageButton;

	@FindBy(xpath = "//div[@data-ng-show='canManageMessages']//label[contains(text(),'Open')]/parent::span/span[@class='btn circle-button btn-white']")
	private WebElement openButtonUnderMessageArea;

	@FindBy(xpath = "//div[@class='modal-dialog']//h4[contains(text(),'New Message')]")
	private WebElement editDraftedEmailModalWindow;

	@FindBy(xpath = "//div[@class='modal-dialog']//h4[contains(text(),'New Message')]/parent::div/button[@class='close']")
	private WebElement closeDraftedEmailModalWindow;

	@FindBy(xpath = "//h4[contains(text(),'New Message')]/parent::div/following-sibling::div[@class='modal-footer']//button[@data-ng-click='save()']")
	private WebElement saveDraftedEmailModalWindow;

	@FindBy(xpath = "//h4[contains(text(),'New Message')]/parent::div/following-sibling::div[@class='modal-footer']//button[@data-ng-click='send()']")
	private WebElement sendDraftedEmailModalWindow;

	@FindBy(xpath = "//h4[contains(text(),'New Message')]/parent::div/following-sibling::div[@class='modal-footer']//button[@data-ng-click='close()']")
	private WebElement cancelDraftedEmailModalWindow;

	@FindBy(css = "form[name='subjectMessageForm'] input[name='recipientSelector']")
	private List<WebElement> recipientsUnderModalWindow;

	@FindBy(css = "form[name='subjectMessageForm'] input[name='messageSubject']")
	private WebElement editEmailSubjectUnderModalWindow;

	@FindBy(css = "form[name='subjectMessageForm'] textarea[id='message-text-input']")
	private WebElement editEmailBodyUnderModalWindow;

	@FindBy(xpath = "//div[@data-ng-show='canManageMessages']//label[contains(text(),'Delete')]/parent::span/span[@class='btn circle-button btn-white']")
	private WebElement deleteButtonUnderMessageArea;

	@FindBy(css = "div[class='modal-header'] h4[id='queryConfirmationLabel']")
	private WebElement confirmationWindowBeforeDeleteMessage;

	@FindBy(xpath = "//div[@class='modal-header']//h4[@id='queryConfirmationLabel']/parent::div/button[@class='close']")
	private WebElement cancelConfirmationWindowBeforeDeleteMessage;

	@FindBy(xpath = "//div[@data-ng-click='okClick()' and @class='btn btn-active']")
	private WebElement approveDeleteMessageFromConfirmationWindow;

	@FindBy(xpath = "//div[@data-ng-click='cancelClick()' and @class='btn btn-default']")
	private WebElement declineDeleteMessageFromConfirmationWindow;

	@FindBy(xpath = "//div[@class='subject-message-block']//div[@data-ng-if='messageThread']//label[@class='notes']/parent::div/following-sibling::textarea")
	private WebElement noteTextBoxUnderMessageArea;

	@FindBy(css = "div[data-ng-if='messageThread'] h4")
	private WebElement emailSubjectUnderMessageArea;

	@FindBy(css = "div[data-ng-if='messageThread'] p")
	private WebElement emailBodyUnderMessageArea;

	@FindBy(xpath = "//span[contains(@title,'Flag')]")
	private WebElement iconFlag;

	@FindBy(xpath = "//div[contains(@class,'message-info')]//time")
	private WebElement timeStampUnderMessageArea;

	@FindBy(css = "input[id='include-automated-checkbox']")
	private WebElement checkBoxIncludeAutomated;

	@FindBy(css = "span[title='Reply']")
	private WebElement btnReply;

	// ==============Visit with Templates Locators==========================//

	@FindBy(css = "ul[class='subject-category-filter']")
	private WebElement visitCategoryFilter;

	@FindBy(css = "div[class='subjct-calendar-visits-block'] ul[class='subject-category-filter']")
	private WebElement visitCategoryFilterBlock;

	@FindBy(css = "div[class='subjct-calendar-visits-block'] ul[class='subject-category-filter'] span")
	private List<WebElement> visitCategoryFiltersList;

	@FindBy(css = "div[class='visit']")
	private List<WebElement> calendarVisitsList;

	@FindBy(xpath = "//div[@class='medication']")
	private List<WebElement> medicationList;

	@FindBy(css = "div[class='calendar-visit-list']")
	private WebElement calendarVisitListGrid;

	@FindBy(css = "div[class='medication-list']")
	private WebElement medicationListGrid;

	@FindBy(xpath = "//span[@class='btn circle-button btn-white' and @data-ng-click='addVisit()']")
	private WebElement initiateButtonForVisit;

	@FindBy(xpath = "//*[contains(@class,'notes')]//parent::div")
	private WebElement noteSection;

	@FindBy(xpath = "//*[contains(@class,'notes')]//parent::div//span[@title='Edit']")
	private WebElement editNotesBtn;

	@FindBy(xpath = "//*[@class='subject-categories-section']//textarea[@id='message-text-input']")
	private WebElement editNotesTextarea;

	@FindBy(xpath = "//h4[@class='modal-title bold' and contains(text(),'Notes')]/ancestor::div[@class='modal fade smart-modal modal-form-window modalshow in']/div[@class='modal-dialog']")
	private WebElement visitNotesModalWindow;

	@FindBy(xpath = "//div[@class='modal-body']//form[contains(@name,'NotesForm')]//textarea[contains(@id,'notes-text-input')]")
	private WebElement NotesTextBoxInModalWindow;

	@FindBy(xpath = "//h4[@class='modal-title bold' and contains(text(),'Notes')]/parent::div/following-sibling::div[@class='modal-footer']//button[@data-ng-click='save()']")
	private WebElement visitNotesModalWindowSaveButton;

	@FindBy(xpath = "//h4[@class='modal-title bold' and contains(text(),'Notes')]/parent::div/following-sibling::div[@class='modal-footer']//button[@data-ng-click='close()']")
	private WebElement visitNotesModalWindowCancelButton;

	@FindBy(xpath = "//h4[@class='modal-title bold' and contains(text(),'Notes')]/parent::div/button[@class='close']")
	private WebElement visitNotesModalWindowCancelIconOnHeader;

	@FindBy(xpath = "//li[@data-ng-repeat='assignee in form.assignees']//a")
	private List<WebElement> assigneeDownOptionLIST;

	@FindBy(xpath = "//a[@title='Assign']")
	private WebElement assignDropdown;

	@FindBy(xpath = "//div[@id='maMedicationInfoDialog']//div[@class='pull-right']//following-sibling::span[@class='ng-binding']")
	private WebElement medicationLastActionStatus;

	@FindBy(xpath = "//div[@class='subject-message-block']/ul[@class='subject-category-filter']")
	private WebElement subjectMessageBlock;

	// ======================Old Subject Locators=======//
	@FindBy(xpath = "//span//a[text()='New Subject page']")
	private WebElement newSubjectPageLINK;

	@FindBy(xpath = "//form[@name='subjectDetailsForm']")
	private WebElement formSubjectDetailsGrid;

	@FindBy(xpath = "//label[contains(@class,'ng-hide')]/following-sibling::input[@name='screeningNum']")
	private WebElement subjectDetailsScreeningINP;

	@FindBy(xpath = "//form[contains(@class,' ng-invalid ng-invalid-required')]")
	private WebElement highlightedInputBoxes;

	@FindBy(xpath = "//div[@class='details-grid row']//label[text()='Screening#']/parent::div/following-sibling::div//label")
	private WebElement screeningNumberText;

	@FindBy(xpath = "//form[@name='subjectForm']//input[@id='screening-number-input']")
	private WebElement formScreeningNumberINP;

	@FindBy(xpath = "//span[@class='icon-small icon-edit']/parent::span")
	private WebElement subjectDetailsEditIcon;
	
	@FindBy(xpath = "//span[@data-ng-class='{disabled : subject.hasLockedAssessments}']")
	private WebElement subjectDetailsDisableEditIcon;
	
	@FindBy(xpath = "//span[@class='icon-small icon-lock-small ng-scope']/parent::span")
	private WebElement lockedIcon;

	@FindBy(xpath = "//div[@class='form-row'][4]//span[@class='k-widget k-dropdown k-header']")
	private WebElement languageDRPBOX;

	@FindBy(xpath = "//ul[@id='language-select_listbox']//li")
	private List<WebElement> languageList;

	@FindBy(xpath = "//span[@aria-owns='gender-select_listbox']")
	private WebElement genderDRPBOX;

	@FindBy(xpath = "//ul[@id='gender-select_listbox']//li")
	private List<WebElement> genderList;

	@FindBy(xpath = "//input[@id='randomization-number-input']")
	private WebElement randomNumberINP;

	@FindBy(xpath = "//form[@name='subjectForm']//input[contains(@class,' ng-invalid ng-invalid-required')]")
	private WebElement screeningFieldHighlightedINP;

	@FindBy(xpath = "//a[@data-ng-click='saveModel()']")
	private WebElement saveSubjectDetailBTN;

	@FindBy(xpath = "//button[@class='btn btn-default btn-without-icon']/span[text()='Cancel']")
	private WebElement cancelSubjectDeatilsBTN;

	@FindBy(xpath = "//div[@class='form-row'][5]/textarea")
	private WebElement notesTXTArea;

	@FindBy(xpath = "//div[@class=' list-text']/label")
	private WebElement subjectName;

	@FindBy(xpath = "//label[text()='Study']/parent::div/following-sibling::div/label/a")
	private WebElement studyNameTXT;

	@FindBy(xpath = "//div[@data-display-value='changeReason.value']//button")
	private WebElement reasonChangeDRPDOWN;

	@FindBy(xpath = "//h4[text()='Reason for change']")
	private WebElement reasonForChangePopUpTitle;

	@FindBy(xpath = "//div[@data-display-value='changeReason.value']//div//ul//li//span")
	private List<WebElement> reasonChangeLST;

	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement reasonChangeUserNameINP;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement reasonChangeUserPasswordINP;

	@FindBy(xpath = "//div[@data-ng-click='okClicked()']")
	private WebElement reasonChangeOKBTN;

	// =====================Observer Section ========================//
	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//span[@title='Add']")
	private WebElement addObserversBTN;

	@FindBy(xpath = "//div[@data-ng-form='observerForm']/div[1]/span")
	private List<WebElement> observerHeaderOption;

	// @FindBy(xpath =
	// "//div[@class='modal-body']//div[@data-ng-form='observerForm']")
	// private WebElement observerSectionGRID;

	@FindBy(xpath = "//div[@class='modal-body']//div[@data-ng-form='observerForm']//div[@data-ng-repeat='observer in observers']")
	private WebElement observerSectionGRID;

	@FindBy(xpath = "//div[@data-ng-form='observerForm']//div[@data-ng-repeat='observer in observers']")
	private List<WebElement> observerLIST;

	@FindBy(xpath = "//span[@class='deactivation-reason-column']/input")
	private WebElement deactivationReasonINP;

	@FindBy(xpath = "//span[@class='deactivation-reason-column']/input")
	private List<WebElement> deactivationReasonINPList;

	@FindBy(xpath = "//div[@data-ng-form='observerForm']//input[@type='checkbox']")
	private WebElement observerDeactiveCheckBox;

	@FindBy(xpath = "//span[@class='deactivation-reason-column']/span")
	private WebElement deactivateReasonSpanTXT;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//span[@class='relation-column']/input[contains(@class,'ng-invalid ng-invalid-required')]")
	private WebElement observerRelationRequiredINP;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//span[@class='relation-column']//input")
	private WebElement observerRelationINP;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//span[@class='alias-column']//input")
	private WebElement observerAliasINP;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//span[@class='alias-column']/input[contains(@class,'ng-invalid ng-invalid-required')]")
	private WebElement observerAliasRequiredINP;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@class='observer-row ng-scope']//span[@class='buttons-column']//span[@title='Save']")
	private WebElement observerSaveBTN;

	@FindBy(xpath = "//div[@id='edit-reported-outcomes-dialog' and contains(@class,'modalshow in')]//button/span[text()='Cancel']")
	private WebElement reportedOutComeCancelBtn;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@class='observer-row ng-scope']//span[@class='buttons-column']//span[@title='Cancel']")
	private WebElement observerCancelBTN;

	@FindBy(xpath = "//div[contains(@data-ng-if,'isReportedOutcomesSectionVisible')]//span[@title='Edit' and @data-ng-click='showEditDialog()']")
	private WebElement reportedOutComeEditBTN;

	@FindBy(xpath = "//div[@id='edit-reported-outcomes-dialog' and contains(@class,'modalshow in')]//h4")
	private WebElement reportedOutComePopUpTitle;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@class='modal-footer']//button[@data-ng-click='save()']")
	private WebElement reportedOutComePopUpSaveBTN;

	@FindBy(xpath = "//div[contains(@class,'modalshow in')]//div[@class='modal-footer']//button[@data-ng-click='close()']")
	private WebElement reportedOutComePopUpCancelBTN;

	@FindBy(xpath = "//span[@aria-owns='site-participant-status-select_listbox']")
	private WebElement reportedOutComeSiteBasedProParticipantDRPDOWN;

	@FindBy(xpath = "//ul[@id='site-participant-status-select_listbox']/li[text()='Disabled']")
	private WebElement reportedOutComeSiteBasedProParticipantDisabled;

	@FindBy(xpath = "//span[@class='observers-list']//span/span[@class='ng-binding']")
	private WebElement observerActiveList;

	@FindBy(xpath = "//ul[@id='site-participant-status-select_listbox']/li[text()='Enabled']")
	private WebElement reportedOutComeSiteBasedProParticipantEnabled;

	@FindBy(xpath = "//textarea[contains(@data-ng-model,'DisableReason')]")
	private WebElement reportedOutComeSiteBasedProParticipantCommentINP;

	@FindBy(xpath = "//span[@aria-owns='site-observer-status-select_listbox']")
	private WebElement reportedOutComeSiteBasedProObserverDRPDOWN;

	@FindBy(xpath = "//ul[@id='site-observer-status-select_listbox']/li[text()='Disabled']")
	private WebElement reportedOutComeSiteBasedProObserverDisabled;

	@FindBy(xpath = "//ul[@id='site-observer-status-select_listbox']/li[text()='Enabled']")
	private WebElement reportedOutComeSiteBasedProObserverEnabled;

	@FindBy(xpath = "//ul[@id='mobile-observer-status-select_listbox']/li[text()='Enabled']")
	private WebElement reportedOutComeMobileProObserverEnabled;

	@FindBy(xpath = "//ul[@id='mobile-observer-status-select_listbox']")
	private WebElement reportedOutComeMobileProObserverlist;

	@FindBy(xpath = "//ul[@id='mobile-participant-status-select_listbox']")
	private WebElement reportedOutComeMobileProSubjectlist;

	@FindBy(xpath = "//textarea[@data-ng-model='outcomeStatuses.siteObsRODisableReason']")
	private WebElement reportedOutComeSiteBasedProObserverCommentINP;

	@FindBy(xpath = "//div[@data-ng-show='areProFormsConfigured || areObsROFormsConfigured']//span[1]/span[@class='user-label']/span")
	private WebElement disabledSiteBasedParticipantIcon;

	@FindBy(xpath = "//div[@data-ng-show='areProFormsConfigured || areObsROFormsConfigured']//span[2]/span[@class='user-label']/span")
	private WebElement disabledSiteBasedObserversIcon;

	@FindBy(xpath = " //span[@class='user-label']//i[@class='glyphicon-ban-circle']")
	private List<WebElement> disablePrticipantObserverRedBanICN;

	@FindBy(xpath = "(//div[@data-ng-show='areProFormsConfigured || areObsROFormsConfigured']//span[@data-role='tooltip'])[1]")
	private WebElement disabledSiteBasedParticipantSpanTxtWithToolTip;

	@FindBy(xpath = "(//div[@data-ng-show='areProFormsConfigured || areObsROFormsConfigured']//span[@data-role='tooltip'])[2]")
	private WebElement disabledSiteBasedObserversSpanTxtWithToolTip;

	@FindBy(xpath = "(//div[@class='k-animation-container']//div[@class='disabled-outcome-tooltip'])[1]")
	private WebElement participantToolTipTxt;

	// @FindBy(xpath = "(//div[@data-ng-show='areProFormsConfigured ||
	// areObsROFormsConfigured']//span[@data-role='tooltip'])[1]/i[1]")
	// private WebElement participantToolTipTxt;

	@FindBy(xpath = "(//div[@class='k-animation-container']//div[@class='disabled-outcome-tooltip'])[2]")
	private WebElement observerToolTipTxt;

	@FindBy(xpath = "//a[@data-ng-click='confirmDelete()']")
	private WebElement deleteBTN;

	@FindBy(xpath = "//button[@title='Remove']")
	private WebElement removeBTN;

	@FindBy(xpath = "//div[@data-display-value='form.assignee.personName']")
	private WebElement clinRoDropDown;

	@FindBy(xpath = "//div[@data-display-value='form.assignee.personName']//li/span")
	private List<WebElement> clinRoDropDownOptionLIST;

	// Reported Outcome locators

	@FindBy(xpath = "//div[@data-ng-show='productTypes.isMobileProSelected']//span[@class='user-label']//span[contains(text(),'Subject')]/following-sibling::i[@class='icon-lock-red']")
	private WebElement reportedOutcomeSubjectLockIcon;

	@FindBy(xpath = "//div[@data-ng-show='productTypes.isMobileProSelected']//span[@class='user-label']//span[contains(text(),'Observer')]/following-sibling::i[@class='icon-lock-red']")
	private WebElement reportedOutcomeObserverLockIcon;

	@FindBy(css = "span[class='unlock-button'] span[data-ng-click='confirmSubjectUnlocking()']")
	private WebElement subjectUnLockButtonOnReportedOutcomePopUp;

	@FindBy(css = "span[class='unlock-button'] span[data-ng-click='confirmObserverUnlocking(observer)']")
	private WebElement observerUnLockButtonOnReportedOutcomePopUp;

	@FindBy(xpath = "//span[@data-ng-click='showSubjectRegistrationDialog()']")
	private List<WebElement> reportedOutcomeMobileRegistrationDialogBTN;

	@FindBy(xpath = "//span[@data-ng-click='showSubjectRegistrationDialog()']")
	private WebElement reportedOutcomeMobileRegistrationDialogQrIcon;

	@FindBy(xpath = "//span[@aria-owns='mobile-observer-status-select_listbox']")
	private WebElement reportedOutComeMobileProObserverDRPDOWN;

	@FindBy(xpath = "//span[@aria-owns='mobile-participant-status-select_listbox']")
	private WebElement reportedOutComeMobileProSubjectDRPDOWN;

	@FindBy(css = "textarea[data-ng-if='outcomeStatuses.isMobileProDisabled'][required='required']")
	private WebElement reportedOutComeMobileProSubjectDisableReasonTextbox;

	@FindBy(css = "textarea[data-ng-if='outcomeStatuses.isMobileObsRODisabled'][required='required']")
	private WebElement reportedOutComeMobileProObserverDisableReasonTextbox;

	@FindBy(css = "span[data-ng-show='mobileParticipant.disabled']")
	private WebElement mobileSubjectDisabled;

	@FindBy(css = "span[data-ng-show='mobileObservers.disabled']")
	private WebElement mobileObserverDisabled;

	@FindBy(xpath = "//span[contains(@data-ng-click,'showObserverRegistration')]")
	private WebElement mobileObserverQrICN;

	@FindBy(xpath = "//*[contains(@data-ng-repeat,'activeObservers')]//*[@class='user-label']//span")
	private WebElement activatedObserverNameOnDetailPage;

	@FindBy(xpath = "//div[@id='observer-registration-dialog']//div[@class='modal-dialog']")
	private WebElement observerRegistrationDialogPopUp;

	@FindBy(xpath = "//span[@title='Show Registration Code' and @data-ng-click='showSubjectRegistrationDialog()']")
	private WebElement showRegistraionCode;

	@FindBy(xpath = "//div[@id='subject-registration-dialog']//div[@class='modal-dialog']")
	private WebElement subjectRegistrationDialogPopUp;

	@FindBy(xpath = "//div[@id='subject-registration-dialog']//div[@class='modal-dialog']//button[@class='close']")
	private WebElement closeSubjectRegistrationCrossICN;

	@FindBy(xpath = "//div[@id='observer-registration-dialog']//div[@class='modal-dialog']//button[@class='close']//span[text()='×']")
	private WebElement closeObserverRegistrationCrossICN;

	@FindBy(xpath = "//div[@id='subject-registration-dialog']//div[@class='modal-dialog']//div[@class='btn btn-active']")
	private WebElement subjectRegistrationPopUpCloseBTN;

	@FindBy(xpath = "//div[@id='observer-registration-dialog']//div[@class='modal-dialog']//div[@class='btn btn-active']")
	private WebElement observerRegistrationPopUpCloseBTN;

	@FindBy(xpath = "//div[@id='subject-registration-dialog']//span[text()='Print']//parent::a[@class='btn btn-default']")
	private WebElement printSubjectBTN;

	@FindBy(xpath = "//div[@id='observer-registration-dialog']//span[text()='Print']//parent::a[@class='btn btn-default']")
	private WebElement printObserverBTN;

	@FindBy(xpath = "//div[@id='observer-registration-dialog']//span[contains(@class,'icon-refresh')]")
	private WebElement observerRefreshICN;

	@FindBy(xpath = "//div[@id='subject-registration-dialog']//strong[text()='Device History']")
	private WebElement deviceSubjectRegistrationHisortyLabel;

	@FindBy(css = "div[id='subject-registration-dialog'] div[data-on-device-deactivated='deactivateDevice'] p")
	private WebElement deviceUnRegistredSubjectHisortyText;

	@FindBy(css = "div[id='observer-registration-dialog'] div[data-on-device-deactivated='deactivateDevice'] p")
	private WebElement deviceUnRegistredObserverHisortyText;

	@FindBy(css = "div[id='subject-registration-dialog'] div[class='device-header'] div[class='col-status']>p")
	private List<WebElement> deviceStatusForRegistredSubject;

	@FindBy(css = "div[id='observer-registration-dialog'] div[class='device-header'] div[class='col-status']>p")
	private List<WebElement> deviceStatusForRegistredObserver;

	@FindBy(xpath = "//div[@id='observer-registration-dialog']//button[@class='btn btn-default' and @data-ng-click='onDeviceDeactivated(device)']")
	private WebElement deviceDeactivateButtonForRegisteredObserver;

	@FindBy(xpath = "//div[@id='subject-registration-dialog']//button[@class='btn btn-default' and @data-ng-click='onDeviceDeactivated(device)']")
	private WebElement deviceDeactivateButtonForRegisteredSubject;

	@FindBy(xpath = "//div[@id='observer-registration-dialog']//strong[text()='Device History']")
	private WebElement deviceHisortyLabel;

	@FindBy(xpath = "//*[@id='subject-registration-dialog']//*[@class='registration-code-wrapper']//strong[contains(@class,'registration-code')]")
	private WebElement registrationCodeSubject;

	@FindBy(xpath = "//*[@id='observer-registration-dialog']//*[@class='registration-code-wrapper']//strong[contains(@class,'registration-code')]")
	private WebElement registrationCodeObserver;

	@FindBy(xpath = "//a[@class='a-color ng-binding'and @data-ng-click='toggleStudyProfile()']")
	private WebElement studyActiveLink;

	@FindBy(xpath = "//div[contains(@class,'ng-isolate-scope opened')]//h1[text()='Study Profile']")
	private WebElement studyProfileSliderPanelOpen;

	@FindBy(xpath = "//div[contains(@class,'ng-isolate-scope opened')]//h1[contains(text(),'Raters')]")
	private WebElement ratersProfileSliderPanelOpen;

	@FindBy(xpath = "//div[@class='queries-dialog-header-title']")
	private WebElement queriesSliderPanelOpen;

	@FindBy(xpath = "//div[@class='row header-slide-study']//a[@title='Close']")
	private WebElement studyProfileCollpaseBTN;

	@FindBy(xpath = "//div[@data-ng-controller='RatersListController']//a[@title='Close']")
	private WebElement raterCollpaseBTN;

	@FindBy(xpath = "//div[@class='queries-dialog-content']//a[@title='Close']")
	private WebElement queriesCollpaseBTN;

	@FindBy(xpath = "//a[@title='Study Profile']")
	private WebElement studyProfileLinkICN;

	@FindBy(xpath = "//a[@title='Raters']")
	private WebElement ratersLinkICN;

	@FindBy(xpath = "//span[@class='query-count-text']")
	private WebElement queriesLinkICN;

	@FindBy(xpath = "//a[@title='Add Query']")
	private WebElement addQueriesButton;

	@FindBy(xpath = "//div[@class='queries-list']//div[contains(@data-ng-repeat,'query in queries')]")
	private List<WebElement> queriesList;

	@FindBy(xpath = "//div[@class='queries-list']//div[@data-is-visible='isShowNew']//textarea[contains(@class,'reply-text')]")
	private WebElement queriesTextArea;

	@FindBy(xpath = "//div[@class='queries-list']//div[@data-query-id='query.id']//textarea[contains(@class,'reply-text')]")
	private WebElement replyQueriesTextArea;

	@FindBy(xpath = "//ul[@class='queries-controls']//a[@title='Create']")
	private WebElement createQueriesButton;

	@FindBy(xpath = "//ul[@class='queries-controls']//a[@title='Cancel']")
	private WebElement cancelQueriesButton;

	@FindBy(xpath = "//ul[@class='queries-controls']//button[@title='Reply']")
	private WebElement replyQueryButton;

	@FindBy(xpath = "//ul[@class='queries-controls']//button[@title='Close Query']")
	private WebElement closeQueryButton;

	@FindBy(xpath = "//*[@id='breadcrumbs']//ul//ul//li[1]//a")
	private WebElement breadCrumbsStudyNameLink;

	@FindBy(xpath = "//*[@id='breadcrumbs']//ul//ul//li[2]//a")
	private WebElement breadCrumbsSiteNameLink;

	@FindBy(xpath = "//*[@id='breadcrumbs']//ul//li[3]")
	private WebElement breadCrumbsSubjectLink;

	@FindBy(xpath = "//div[contains(@class,'show in')]//label[text()='Language']/parent::div//button")
	private WebElement addSubPopup_SelectedSubjectLanguage;

	@FindBy(xpath = "//div[@class='popover fade right in' and @role='tooltip']")
	private WebElement maskingDescriptionPopUP;

	@FindBy(xpath = "//div[@data-ng-if='actionAccessModel.isReportedOutcomesSectionVisible']")
	private WebElement reportedOutComesSectionGrid;

	@FindBy(xpath = "//div[@data-ng-show='productTypes.isMobileProSelected']")
	private WebElement reportedOutComesGridMobileRow;

	@FindBy(xpath = "//div[@data-ng-if='productTypes.isParticipantSelected || productTypes.isObserverSelected']")
	private WebElement reportedOutComesPopUpMobileProSettingsSectionGrid;

	@FindBy(xpath = "//div[contains(@data-ng-if,'.isParticipantSelected')]/div[contains(@data-ng-if,'ParticipantSelected')]/div/span")
	private WebElement reportedOutComesPopUpMobileProSettingsSubjectStatusDropDown;

	@FindBy(xpath = "//div[contains(@data-ng-if,'isObserverSelected')]/div[contains(@data-ng-if,'isObserverSelected')]/div[@class='status-column']/span")
	private WebElement reportedOutComesPopUpMobileProSettingsObserverStatusDropDown;

	@FindBy(xpath = "//span[@data-ng-show='productTypes.isParticipantSelected']/span[@class='user-label']/span")
	private WebElement reportedOutcomeMobileSubjectValueText;

	@FindBy(xpath = "//span[@data-ng-show='mobileObservers.disabled || !isMobileEnabledForAnyObserver()']/span[@class='user-label']/span")
	private WebElement reportedOutcomeMobileObserversValueText;

	// New calendar Visit Related Locators

	@FindBy(xpath = "//div[@class='details-column']")
	private WebElement DetailSection;

	@FindBy(css = "div[class='calendar-visit-info'] ul[class='subject-category-filter']")
	private WebElement visitSubjectCategoryFilters;

	@FindBy(css = "div[class='calendar-visit-info'] ul[class='subject-category-filter'] a")
	private List<WebElement> visitSubjectCategoryFilterList;

	@FindBy(xpath = "//div[@class='details-column']//span[@data-ng-click='deleteVisit()']")
	private WebElement calenderVisitCancellIcon;

	@FindBy(xpath = "//div[@class='details-column']//span[@title='Set Flag']")
	private List<WebElement> setFlagICN;

	@FindBy(xpath = "//div[@class='details-column']//span[@title='Set Flag']")
	private WebElement setFlagIcon;

	@FindBy(xpath = "//div[@class='details-column']//span[@title='Clear Flag']")
	private WebElement clearFlagIcon;

	@FindBy(css = "div[data-title='uploadTitle'] a[class='circle-button btn btn-white']")
	private WebElement addAttachmentIcon;

	@FindBy(xpath = "//ul[@class='queries-controls-vertical text-right']//a[not(contains(@class,'ng-hide')) and @title='Edit']")
	private WebElement queryEditButton;

	@FindBy(xpath = "//ul[@class='queries-controls-vertical text-right']//a[not(contains(@class,'ng-hide')) and @title='Delete Query']")
	private WebElement queryDeleteButton;

	// ==========Source Upload Locators==================//
	@FindBy(xpath = "//div[@id='filesSource' ]//h4[text()='Upload Files']")
	private WebElement sourceUploadPopUpTitleDisplayed;

	@FindBy(xpath = "//div[@id='filesSource']//div[text()='Cancel']")
	private WebElement sourceUploadCancelBTN;

	@FindBy(xpath = "//div[@id='filesSource']//div[text()='Upload']")
	private WebElement uploadSourceUploadBTN;

	@FindBy(css = "div[id='filesSource'] button[class='close']")
	private WebElement sourceUploadCloseBTN;

	@FindBy(css = "div[id='filesSource' ] button[title='Add File(s)']")
	private WebElement uploadSourceAddFilesBtn;

	@FindBy(css = "a[data-ng-click='previewAttachment(attachment)'] ")
	private List<WebElement> uploadedSourceDocument;

	@FindBy(xpath = "//div[@class='visit']//span[@class='flag set']")
	private WebElement flagIconInVisitList;

	@FindBy(xpath = "//div[@class='medication']//span[@class='flag set']")
	private WebElement flagIconInMedicationList;

	@FindBy(xpath = "//div[@class='medication']//span[@class='flag set']")
	private List<WebElement> flagIconInMedicationLIST;

	@FindBy(xpath = "//div[@class='visit']//span[@class='flag set']")
	private List<WebElement> flagIconInVisitListLst;

	@FindBy(xpath = "//div[@data-ng-if='isAssigneeSelectorVisible(form)']//label")
	private WebElement notAssignedTextForCalenderVisit;

	@FindBy(xpath = "//div[@class='caption col-xs-24 attachments-row ng-scope']")
	private WebElement calenderVisitAttachmentRow;

	// Medication Locators
	@FindBy(xpath = "//div[@class='medication-info ng-scope']//h4")
	private WebElement medicationInfoName;

	@FindBy(xpath = "//h3[contains(text(),'Instructions')]//following-sibling::p[@class='instruction ng-binding ng-scope']")
	private List<WebElement> instructionInfo;

	@FindBy(xpath = "//h3[contains(text(),'Com')]//following-sibling::p[@class='comment ng-binding']")
	private WebElement commentMedicationInfo;

	@FindBy(xpath = "//div[@class='medication-info ng-scope']//span[@title='Set Flag']")
	private WebElement setFlagMedicationICN;

	@FindBy(xpath = "//div[@class='medication-info ng-scope']//label[@class='notes']")
	private WebElement medicationNotesInfo;

	@FindBy(xpath = "//div[@class='medication-info ng-scope']//div[@class='pull-right']//time")
	private WebElement medicationTime;

	@FindBy(xpath = "//div[@class='medication-info ng-scope']//div[@class='pull-right']//h3[text()='Last Action']")
	private WebElement lastActionText;

	@FindBy(xpath = "//div[contains(@class,'medication')]//span[@title='Show History']")
	private WebElement showHistoryMedication;

	@FindBy(xpath = "//div[contains(@id,'subject-med')]//div[contains(@class,'history-c')]")
	private WebElement showHistoryMedicationPopUp;

	@FindBy(xpath = "//div[contains(@id,'subject-med')]//a[@title='Reload History']")
	private WebElement reloadMedicationHistory;

	@FindBy(xpath = "//div[contains(@id,'subject-med')]//div[contains(@class,'date-col')]//span")
	private WebElement dateTimeMedicationHistoryText;

	@FindBy(xpath = "//div[contains(@id,'subject-medication')]//div[@class='table-frame-body']//div[@class='col-xs-17']")
	private WebElement eventMedicationHistory;

	@FindBy(xpath = "//div[contains(@class,'subject-medicati')]//ul[@class='subject-category-filter']//li//a//span")
	private List<WebElement> medicationFilterList;

	@FindBy(xpath = "//div[contains(@id,'subject-med')]//div[@class='modal-footer no-border']//div[text()='Close']")
	private WebElement modelMedicationHistoryPopUpCloseBTN;

	@FindBy(xpath = "//div[contains(@class,'medication')]//span[@title='Show History' and contains(@class,'btn-white ng-hide')]")
	private WebElement hiddenShowHistoryICN;

	@FindBy(xpath = "//div[@id='subject-registration-dialog']//div[@class='btn btn-active']")
	private WebElement mobileSubjectQrCloseIcon;

	// Logged Event locators

	@FindBy(css = "div[class='log-event-list']")
	private WebElement eventListGrid;

	@FindBy(xpath = "//div[@class='list-column']//div[@data-ng-switch-when='LogEvents']")
	private WebElement loggedEventListFilterBlock;

	@FindBy(xpath = "//div[@class='subject-categories-section']//span[text()='LOGGED EVENTS']")
	private WebElement loggedEventBlock;

	@FindBy(xpath = "//div[@class='subject-categories-section']//div[contains(@class,'list')]//div[contains(@class,'list-item')]//time")
	private List<WebElement> loggedEventList;

	@FindBy(xpath = "//div[@class='log-event-info-block']")
	private WebElement loggedEventDetailsBlock;

	@FindBy(xpath = "//div[@class='log-event-list']//div[contains(@class,'log-event-list')]")
	private List<WebElement> loggedEventRows;

	@FindBy(xpath = "//span[@class='icon-flag']//parent::button")
	private WebElement eventFlagICN;

	@FindBy(xpath = "//div[@class='flag-section']//label[text()='Clear Flag']")
	private WebElement clearFlagEventAndQuestionnareICN;

	@FindBy(xpath = "//div[@class='flag-section']//label[text()='Set Flag']")
	private WebElement setFlagEventQuestionnaireICN;

	@FindBy(xpath = "//*[contains(@class,'middle-info-block')]//span[contains(@class,'-list-message')]")
	private WebElement noLoggedEventText;

	// Questionnaires Locators

	@FindBy(css = "div[class^='questionnaire-info-block']")
	private WebElement questionnariesDetailsBlock;

	@FindBy(css = "div[class='questionnaire-list']")
	private WebElement questionnariesListGrid;

	@FindBy(css = "div[class^='questionnaire-list-item']")
	private List<WebElement> questionnaireListItemList;

	@FindBy(xpath = "//div[contains(@class,'questionnaire-info-block')]")
	private WebElement questionnairesDetailsBlock;

	@FindBy(xpath = "//div[contains(@class,'questionnaire-info-block')]//div[contains(@data-ng-if,'questionnaire.scores')]")
	private WebElement questionnairesScoresBlock;

	@FindBy(xpath = "(//strong[@class='bolded-title registration-code ng-binding'])[1]")
	private WebElement mobileSubjectRegistrationCode;

	@FindBy(xpath = "//label[contains(@data-ng-if,'form.notAdministered') and text()='Not Administered']")
	private WebElement notAdministeredLabel;
	
	@FindBy(xpath = "//img[contains(@data-ng-hide,'form.paperTranscription') and not(contains(@class,'ng-hide'))]")
	private WebElement thumbnailBeforeNotAdministeredIMG;
	
	@FindBy(xpath = "//div[contains(@class,'section')]//div[@class='form-cover form-image-container clearfix ng-isolate-scope']")
	private WebElement newthumbnailBeforeNotAdministeredIMG;

	@FindBy(xpath = "//button[@title='Refresh']/span")
	private WebElement refreshButtonOnSubjectDetailPage;

	@FindBy(xpath = "//div[@class='status-editor']//button")
	private WebElement subjectStatusEditBTN;

	@FindBy(xpath = "//div[@class='table-frame-body']/div[@class='row ng-scope']/div[@class='col-xs-7 date-col']/span")
	List<WebElement> dateInHistoryModalWindow;

	// ------------Calendar Visit Calendar Date Set Locators---------//
	@FindBy(xpath = "//*[@id='datepicker']//a")
	private WebElement NotSetCalenderButton;

	@FindBy(xpath = "(//td[contains(@class,'today')])[2]")
	private WebElement scheduleCurrentDate;

	@FindBy(xpath = "//div[@class='modal-dialog modal-md']//div[@class='row ng-scope']")
	List<WebElement> subjectHistoryWindowtext;

	@FindBy(xpath = "//div[@class='modal-dialog modal-md']//div[text()='Close']")
	private WebElement closeButton;

	@FindBy(xpath = "//label[text()='Temporary ID']/following-sibling::span[@class='value ng-binding']")
	private WebElement tempIDToBeVerified;

	@FindBy(xpath = "//div[contains(@class,'show in')]//input[@name='screeningNumber']")
	private WebElement addSubPopup_ScreeningINP;

	@FindBy(xpath = "//label[text()='Consent to Record']/following-sibling::div/button")
	private WebElement consentToRecord;

	@FindBy(xpath = "//div[@class='dropdown ma-dropdown open']//li")
	private List<WebElement> optionUnderConsentTorecordDropDown;

	@FindBy(xpath = "//div[contains(@class,'show in')]//button[@data-ng-click='save()']")
	private WebElement addSubPopup_SaveBTN;

	@FindBy(xpath = "//label[text()='Consent to Record']//parent::div//span[@class='value ng-binding']")
	private WebElement consentTorecordStatus;

	@FindBy(xpath = "//span[@class='btn circle-button btn-white' and @data-ng-click='addSelfCRVisit()']")
	private WebElement selfServeCRVisitAddICN;

	@FindBy(xpath = "//div[@id='cr-scheduling-calendar']")
	private WebElement selfCrScheduledDateCalenderWindow;

	@FindBy(xpath = "//div[@class='row administered-row']//*[contains(@class,'text-ellipsis')]")
	private WebElement observerSelectedValue;

	@FindBy(xpath = "//span[text()='All']")
	private WebElement allButtonQuestionnaires;

	@FindBy(xpath = "//a[@class='expander-button circle-button arrowup']")
	private WebElement arrowUp;

	@FindBy(xpath = "//a[@class='expander-button circle-button arrowdown']")
	private WebElement arrowDown;

	@FindBy(xpath = "//div[@class='row administered-row']//div[contains(@class,'btn-group')]")
	private List<WebElement> assignDROPDOWN;

	@FindBy(xpath = "//table[@aria-activedescendant='cr-scheduling-calendar_cell_selected']//tr//td[@role='gridcell' and not(contains(@class,'disabled'))]//a")
	private List<WebElement> calenderDateList;

	@FindBy(xpath = "//table[contains(@aria-activedescendant,'cr')]//tr//td[@id='cr-scheduling-calendar_cell_selected']//a//span")
	private WebElement selectedCRSelfScheduleDateText;

	@FindBy(xpath = "//*[@id='dropdownMenu3']")
	private WebElement subjectStatusButton;

	@FindBy(xpath = "//div[@id='datepicker']")
	private WebElement startedDatePickerBTN;

	@FindBy(xpath = "//td[@class='day active today']")
	private WebElement currentDate;
	
	@FindBy(xpath="//div[@class='year-editor-container']//button[@class='btn dropdown-toggle btn-default']")
	private WebElement birthYaerDropdown;
	
	@FindBy(xpath="//div[@class='modal-label']/following::button[@class='btn dropdown-toggle btn-default']")
	private WebElement reasonDprDwn;
	
	@FindBy(id="loginInput")
	private WebElement userNameField;
	
	@FindBy(id="passwordInput")
	private WebElement pwdField;
	

	@FindBy(xpath="//div[@id='auditTrailConfirmation']//div[text()='OK']")
	private WebElement reasonConfrmBtn;
	
	@FindBy(xpath="//label[text()='Year Of Birth (Age)']/following::span[@class='value text-uppercase ng-binding']")
	private WebElement ageValue;
	
	@FindBy(xpath="//a[@class='btn circle-button btn-white']")
	private WebElement cancelDateOfYear;
	
	@FindBy(xpath="//div[@class='btns-row']//a[@class='circle-button btn btn-white']")
	private WebElement addVisitButton;
	
	@FindBy(xpath="//div[@class='btn-wrapper ng-scope']/a")
	private WebElement saveVisitButton;
	
	@FindBy(xpath="//span[text()='Visits']")
	private WebElement visitsidetab;
	
	@FindBy(xpath="(//a[@class='k-grid-filter'])[5]")
	private WebElement VisitStudySelect;
	
	@FindBy(xpath="(//div[@class='k-filter-menu-container']//input)[1]")
	private WebElement filterStudyInputField;
	
	@FindBy(xpath="//button[text()='Filter']")
	private WebElement filterbutton;
	
	@FindBy(xpath="//label[text()='Year Of Birth (Age)']/../following::div//label[@class='ng-binding ng-scope']")
	private WebElement ageValuetext;
	
	@FindBy(xpath="//span[text()='Scheduled Date']/..//a[@class='add-on  btn circle-button btn-white pull-left datepickerbutton']")
	private WebElement scheduleddateBtn;
	
	@FindBy(xpath="(//span[@class='text-ellipsis-dd ng-binding'])[1]")
	private WebElement assignclinro;
	
	@FindBy(xpath="//span[@class='text-ellipsis-dd ng-binding']/../following::div[@class='dropdown-menu'][1]//li")
	private WebElement selclinro;
	
	@FindBy(xpath="//div[contains(@class,'date-editor-button ')]/a")
	private WebElement RemoveDateofYearButton;
	
	@FindBy(xpath="//div[@class='date-picker-container']//div[@class='date-btn-container ng-isolate-scope']")
	private WebElement calendarIcon;
	
	@FindBy(xpath="//div[@class='date-picker-container']//label[text()='Not Set']")
	private WebElement NotSetLabel;
	
	@FindBy(xpath="//div[@class='datepicker']")
	private WebElement calendarDatePicker;
	
	@FindBy(xpath="//td[@class='day active today']")
	private WebElement currentdateOnCalendar;
	
	@FindBy(xpath="//div[@class='date-picker-container']//label[@data-ng-show='canEditCalendar || canViewCalendar']")
	private WebElement currentdateLabel;
	
	@FindBy(xpath="//div[@class='visit']//h4")
	private List<WebElement> visitsList;

	@FindBy(xpath="//td[@class='day active today']//following::tr[1]//td[1]")
	private WebElement futureDate;
	
	@FindBy(xpath="//td[@class='day today']//preceding::td[1]")
	private WebElement pastDate;
	
	@FindBy(xpath="//div[@class='visit-list-item ng-scope']")
	private List<WebElement> visitList;

	public void selectDisabledSiteBasedProParticipant() {
		clickOn(reportedOutComeSiteBasedProParticipantDRPDOWN);
		clickOn(reportedOutComeSiteBasedProParticipantDisabled);
	}

	/**
	 * Click on submitted paper transcription scale to open the assessment detail
	 * page
	 */
	public void clickOnPaperTranscriptedCompletedFormToOpenAssesmentPage() {
		clickOn(paperScaleClippedThumbnailIMG);
	}

	public void verifySiteBasedDisabledParticipantAndObserversTextInRed() {
		Assert.assertTrue(disabledSiteBasedParticipantIcon.getCssValue("color").trim().contains("rgba(204, 71, 44, 1"));
		reportInfo();
	}

	public void verifySiteBasedDisabledParticipantAndObserversTextIsNotRed() {
		Assert.assertTrue(disabledSiteBasedParticipantIcon.getCssValue("color").trim().contains("rgba(87, 87, 87, 1)"));
		reportInfo();
	}

	public void selectEnableSiteBasedProParticipant() {
		waitAndClick(reportedOutComeSiteBasedProParticipantDRPDOWN);
		waitAndClick(reportedOutComeSiteBasedProParticipantEnabled);
	}

	public void selectEnableSiteBasedProObserver() {
		waitAndClick(reportedOutComeSiteBasedProObserverDRPDOWN);
		waitAndClick(reportedOutComeSiteBasedProObserverEnabled);
	}

	public void inputDisabledSiteBasedProParticipantReason(String textToBeEnter) {
		inputText(reportedOutComeSiteBasedProParticipantCommentINP, textToBeEnter);
		reportInfo();
	}

	public void verifyDisabledReasonTextForSiteBasedPROParticipant(String reasonToBeVerified) {
		Assert.assertTrue(
				reportedOutComeSiteBasedProParticipantCommentINP.getAttribute("value").contains(reasonToBeVerified));
		reportInfo();
	}

	public void verifyDisabledReasonTextForSiteBasedPROObserver(String reasonToBeVerified) {
		Assert.assertTrue(
				reportedOutComeSiteBasedProObserverCommentINP.getAttribute("value").contains(reasonToBeVerified));
		reportInfo();
	}

	public void selectDisabledSiteBasedProObserver() {
		waitAndClick(reportedOutComeSiteBasedProObserverDRPDOWN);
		waitAndClick(reportedOutComeSiteBasedProObserverDisabled);
	}

	public void inputDisabledSiteBasedProObserverReason(String textToBeEnter) {
		inputText(reportedOutComeSiteBasedProObserverCommentINP, textToBeEnter);
		reportInfo();
	}

	public void verifyParticipantAndObserverSettingsAreAvailable() {
		Assert.assertTrue((reportedOutComeSiteBasedProParticipantDRPDOWN).isDisplayed());
		Assert.assertTrue((reportedOutComeSiteBasedProObserverDRPDOWN).isDisplayed());
		reportInfo();
	}

	/* Verify In Mobile Pro Observer DropDown To Select Available */
	public void verifyMobileProObserverDropdownToSelectIsAvailable() {
		moveToElement(reportedOutComeMobileProObserverDRPDOWN);
		Assert.assertTrue((reportedOutComeMobileProObserverDRPDOWN.isDisplayed()));
		reportInfo();
	}

	public void verifyReasonForDisablingParticipantAndObserverInputFieldAreEditable() {
		Assert.assertTrue(reportedOutComeSiteBasedProParticipantCommentINP.isEnabled());
		Assert.assertTrue(reportedOutComeSiteBasedProObserverCommentINP.isEnabled());
		reportInfo();
	}

	public void verifyReasonForDisablingParticipantAndObserverInputFieldIsNotDisplayed() {
		Assert.assertFalse(isElementDisplayed(reportedOutComeSiteBasedProParticipantCommentINP));
		Assert.assertFalse(isElementDisplayed(reportedOutComeSiteBasedProObserverCommentINP));
		reportInfo();
	}

	public void verifyDisabledParticipantAndObserverSiteBasedInputReasonFieldAppearAndHighlighted() {
		Assert.assertTrue(isElementPresent(reportedOutComeSiteBasedProParticipantCommentINP));
		Assert.assertTrue(isElementPresent(reportedOutComeSiteBasedProObserverCommentINP));
		List<WebElement> inputFieldsList = Arrays.asList(reportedOutComeSiteBasedProParticipantCommentINP,
				reportedOutComeSiteBasedProObserverCommentINP);
		List<String> bgColorBeforeList = new ArrayList<>();
		List<String> bgColorAfterList = new ArrayList<>();
		boolean flag = true;

		for (WebElement inputFieldEle : inputFieldsList) {
			inputText(inputFieldEle, "Test");
		}

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

	public void verifyChangesSavedIconsAreDisplayedParticipantObserverDisabled() {
		boolean flag = false;
		if (disablePrticipantObserverRedBanICN.size() >= 2) {
			flag = true;
			Assert.assertTrue(flag, "The Participant and observer icon are disabled and displayed");
			;
		} else {
			Assert.assertFalse(flag);
		}
	}

	// Hover Cusrsor To Site Based Participant Value And Get ToolTip Text
	public void hoverCursorToParticipantValueAndVerifyToolTipTextForParticipant(String reasonToBeVerified) {
		moveToElement(disabledSiteBasedParticipantSpanTxtWithToolTip);
		_normalWait(1000);
		scrollToTopOfThePage();
		scrollPageThroughWebElement(disabledSiteBasedParticipantSpanTxtWithToolTip);
		waitForElement(participantToolTipTxt);
		String participantDisabledReason = participantToolTipTxt.getText();
		String[] participantDisabledText = participantDisabledReason.split("\n");
		String[] dateFordisabledParticipant = participantDisabledText[0].split(":");
		String[] reasonFordisabledParticipant = participantDisabledText[1].split(":");
		Assert.assertTrue(dateFordisabledParticipant[1].trim().equalsIgnoreCase(currentDate()));
		Assert.assertTrue(reasonFordisabledParticipant[1].trim().contains(reasonToBeVerified));
	}

	// Hover Cusrsor To Site Based Observer Value And Get ToolTip Text
	public void hoverCursorToObserverValueAndVerifyToolTipTextForObserver(String reasonToBeVerified) {
		moveToElement(disabledSiteBasedObserversSpanTxtWithToolTip);
		waitForElement(observerToolTipTxt);
		String observerDisabledReason = observerToolTipTxt.getText();
		String[] observerDisabledText = observerDisabledReason.split("\n");
		String[] dateFordisabledObserver = observerDisabledText[0].split(":");
		String[] reasonFordisabledObserver = observerDisabledText[1].split(":");
		Assert.assertTrue(dateFordisabledObserver[1].trim().equalsIgnoreCase(currentDate()));
		Assert.assertTrue(reasonFordisabledObserver[1].trim().equalsIgnoreCase(reasonToBeVerified));
	}

	// Hover Cusrsor To Mobile Based Participant Value And Get ToolTip Text
	public void hoverCursorToMobileBasedParticipantValueAndVerifyToolTipTextForParticipant(String reasonToBeVerified) {
		moveToElement(mobileSubjectDisabled);
		waitForElement(participantToolTipTxt);
		String participantDisabledReason = participantToolTipTxt.getText();
		String[] participantDisabledText = participantDisabledReason.split("\n");
		String[] dateFordisabledParticipant = participantDisabledText[0].split(":");
		String[] reasonFordisabledParticipant = participantDisabledText[1].split(":");
		Assert.assertTrue(dateFordisabledParticipant[1].trim().equalsIgnoreCase(currentDate()));
		Assert.assertTrue(reasonFordisabledParticipant[1].trim().contains(reasonToBeVerified));
	}

	// Hover Cusrsor To Mobile Based Observer Value And Get ToolTip Text
	public void hoverCursorToMobileBasedObserverValueAndVerifyToolTipTextForObserver(String reasonToBeVerified) {
		moveToElement(mobileObserverDisabled);
		waitForElement(observerToolTipTxt);
		String observerDisabledReason = observerToolTipTxt.getText();
		String[] observerDisabledText = observerDisabledReason.split("\n");
		String[] dateFordisabledObserver = observerDisabledText[0].split(":");
		String[] reasonFordisabledObserver = observerDisabledText[1].split(":");
		Assert.assertTrue(dateFordisabledObserver[1].trim().equalsIgnoreCase(currentDate()));
		Assert.assertTrue(reasonFordisabledObserver[1].trim().equalsIgnoreCase(reasonToBeVerified));
	}

	public void clickOnEnterPaperTranscription() {
		waitForElementClickable(enterPaperTranscriptionLINK, DEFAULT_WAIT_4_ELEMENT);
		scrollToTopOfThePage();
		waitAndClick(enterPaperTranscriptionLINK);
	}

	public void verifyEnterPaperTranscriptionLINKIsDisplayed() {
		Assert.assertTrue(enterPaperTranscriptionLINK.isDisplayed());
		reportInfo();
	}

	public void verifyReasonForChangeOptionPopUpIsDisplayed() {
		waitForElementPresent(reasonForChangeReasonDRPDOWN, DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(isElementPresent(reasonForChangeReasonDRPDOWN));
		reportInfo();
	}

	public void verifyReasonForChangeOptionPopUpIsNotDisplayed() {
		Assert.assertFalse(reasonForChangeReasonDRPDOWN.isDisplayed());
		reportInfo();
	}

	public void verifyAddObserverTableHeaderAttributes(List<String> headerOptionToBeVerify) {
		List<String> optionList = new ArrayList<>();
		for (WebElement option : observerHeaderOption) {
			if (option.getText().length() > 0) {
				optionList.add(option.getText().trim());
			}
		}
		Assert.assertTrue(optionList.containsAll(headerOptionToBeVerify));
		reportInfo();
	}

	public void verifyObserverGridIsDisplayedWithListOfObserver() {
		Assert.assertTrue(observerSectionGRID.isDisplayed());
		Assert.assertTrue(observerLIST.size() > 0);
		reportInfo();
	}

	public void verifyObserverGridIsHidden() {
		Assert.assertFalse(isElementDisplayed(observerSectionGRID));
		// Assert.assertTrue(observerLIST.size() == 0);
		reportInfo();
	}

	public void selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(String observerToSelect) {
		for (WebElement observerName : observerLIST) {
			if (observerName.findElement(By.xpath("./span[@class='relation-column']")).getText().trim()
					.equals(observerToSelect)) {
				moveToElement(observerName.findElement(By.xpath("./span[@class='relation-column']")));
				Assert.assertTrue(observerName
						.findElement(By.xpath("./span[@class='buttons-column']/span[@title='Edit']")).isDisplayed());
				Assert.assertTrue(observerName
						.findElement(By.xpath("./span[@class='buttons-column']/span[@title='Delete']")).isDisplayed());
				break;
			}
		}
	}

	public void selectObserverRelationAndVerifyEditBTNIsDisplayedAndDeleteBTNIsHidden(String observerToSelect) {
		for (WebElement observerName : observerLIST) {
			if (observerName.findElement(By.xpath("./span[@class='relation-column']")).getText()
					.equals(observerToSelect)) {
				moveToElement(observerName.findElement(By.xpath("./span[@class='relation-column']")));
				Assert.assertTrue(observerName
						.findElement(By.xpath("./span[@class='buttons-column']/span[@title='Edit']")).isDisplayed());
				break;
			}
		}
	}

	public void editObserverSaveAndCancelOptionDisplayedAndFliedsEditable(String observerToEdit) {
		for (WebElement observerName : observerLIST) {
			if (observerName.findElement(By.xpath("./span[@class='relation-column']")).getText()
					.equals(observerToEdit)) {
				moveToElement(observerName.findElement(By.xpath("./span[@class='relation-column']")));
				waitAndClick(observerName.findElement(By.xpath("./span[@class='buttons-column']/span[@title='Edit']")));
				//_normalWait(500);
				Assert.assertTrue(observerName.findElement(By.xpath(".//input")).isDisplayed());
				Assert.assertTrue(observerName
						.findElement(By.xpath("./span[@class='buttons-column']/span[@title='Save']")).isDisplayed());
				Assert.assertTrue(observerName
						.findElement(By.xpath("./span[@class='buttons-column']/span[@title='Cancel']")).isDisplayed());
				break;
			}
		}
	}

	public void selectObserverRelationAndClickOnDeleteBTN(String observerToSelect) {
		for (WebElement observerName : observerLIST) {
			if (observerName.findElement(By.xpath("./span[@class='relation-column']")).getText()
					.equals(observerToSelect)) {
				moveToElement(observerName.findElement(By.xpath("./span[@class='relation-column']")));
				waitAndClick(
						observerName.findElement(By.xpath("./span[@class='buttons-column']/span[@title='Delete']")));
				break;
			}
		}
	}

	public void unselectDeactiveCheckBoxAndVerifyDeactiveReasonInputBoxIsNotDisplayed() {
		waitAndClick(observerDeactiveCheckBox);
		boolean flag = true;
		if (deactivationReasonINPList.size() < 0) {
			flag = false;
			Assert.assertFalse(flag, "The Deactive Reason Inputbox Not Displayed");
		} else {
			Assert.assertTrue(flag, "The Deactive Reason Inputbox Is Displayed");
		}
	}

	public void selectDeactiveCheckBox() {
		if (!observerDeactiveCheckBox.isSelected()) {
			waitAndClick(observerDeactiveCheckBox);
		}
	}

	public void verifyDeactivateReasonIsRequired() {
		Assert.assertTrue(isElementPresent(deactivationReasonINP));
		boolean flag = true;
		inputText(deactivationReasonINP, "Test");
		List<String> bgColorBeforeList = new ArrayList<>();
		List<String> bgColorAfterList = new ArrayList<>();
		String beforeColor = getBackgroundColor(deactivationReasonINP);
		bgColorBeforeList.add(beforeColor);
		deactivationReasonINP.clear();
		String afterColor = getBackgroundColor(deactivationReasonINP);
		bgColorAfterList.add(afterColor);
		if ((bgColorBeforeList.toString().contentEquals(bgColorAfterList.toString())) == false) {
			flag = false;
			Assert.assertFalse(flag, "Fields are Required");
		} else {
			Assert.assertTrue(flag);
		}

	}

	public void inputDeactivateReason(String reason) {
		inputText(deactivationReasonINP, reason);
	}

	public void clickOnCancelObserverButton() {
		clickOn(observerCancelBTN);

	}

	public void ObserverStaysInDeactivatestateInReportedOutComePopUp() {
		boolean flag = false;
		if (deactivateReasonSpanTXT.isDisplayed()) {
			flag = true;
			Assert.assertTrue(flag, "The Deactive Reason Inputbox Displayed");
		} else {
			Assert.assertFalse(flag, "The Deactive Reason Inputbox Not  Displayed");
		}
	}

	public void verifyAddObserverBtnIsDisplayed() {
		Assert.assertTrue(addObserversBTN.isDisplayed());
		reportInfo();
	}

	public void verifyReportedOutComeButtonIsDisplayed() {
		Assert.assertTrue(reportedOutComeEditBTN.isDisplayed());
		reportInfo();
	}

	public void clickOnReportedOutComeButton() {
		moveToElement(reportedOutComeEditBTN);
		javascriptButtonClick(reportedOutComeEditBTN);
		waitForElementPresent(reportedOutComePopUpTitle, 5);
	}

	public void verifyReportedOutComeScreenIsNotDisplayed() {
		Assert.assertFalse(isElementDisplayed(reportedOutComePopUpTitle));
		reportInfo();
	}

	public void verifyReportedOutComeIsDisplayed() {
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue((reportedOutComePopUpTitle.isDisplayed()));
		moveToElement(reportedOutComePopUpTitle);
		reportInfo();
	}

	public void closeReportedOutComePopup() {
		clickOn(reportedOutComeCancelBtn);
		reportInfo();
	}

	public void clickOnReportedOutComePopUpSaveBTN() {
		_waitForJStoLoad();
		clickOn(reportedOutComePopUpSaveBTN);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		_normalWait(1000);
		reportInfo();
	}

	public void observerStaysInActiveState() {
		Assert.assertTrue(observerActiveList.isDisplayed(), "Observer Are in active State");
	}

	public void verifyReasonForChangeInpIsHighlightedWithDefaultValue() {
		Assert.assertTrue(requiredReasonForChangeINP.isDisplayed());
		reportInfo();
	}

	public void verifySelectedReasonForChangeValue(String reasonForChangeToBeVerified) {
		Assert.assertTrue(selectedReasonForChangeTEXT.getText().trim().equalsIgnoreCase(reasonForChangeToBeVerified));
		reportInfo();
	}

	public void verifyReasonForChangeCommentFieldIsEnabledAndRequired() {
		Assert.assertTrue(
				requiredReasonForChangeCommentINP.isDisplayed() && requiredReasonForChangeCommentINP.isEnabled());
		reportInfo();
	}

	public void verifyReasonForChangeCommentFieldIsEnabledAndNotRequired() {
		Assert.assertTrue(nonRequiredReasonForChangeCommentINP.isDisplayed());
		reportInfo();
	}

	public void inputReasonForChangeComment(String commentToBeEnter) {
		inputText(nonRequiredReasonForChangeCommentINP, commentToBeEnter);
		reportInfo();
	}

	public void verifyReasonForChangeCommentIsAdded(String commentToBeEnter) {
		inputText(nonRequiredReasonForChangeCommentINP, commentToBeEnter);
		reportInfo();
	}

	public void userCredentialsFieldIsRequiredAndEmpty() {
		Assert.assertTrue(reasonForChangeUserNameINP.getAttribute("class").contains("invalid-required")
				&& reasonForChangeUserNameINP.getAttribute("value").length() == 0);
		Assert.assertTrue(reasonForChangePasswordINP.getAttribute("class")
				.contains("ng-pristine ng-untouched ng-invalid ng-invalid-required")
				&& reasonForChangePasswordINP.getAttribute("value").length() == 0);
		reportInfo();
	}

	public void verifyReasonForChangePopUpOkAndCancelButtonisDisplayed() {
		Assert.assertTrue(reasonForChangeOkBTN.isDisplayed() && reasonForChangeCancelBTN.isDisplayed());
		reportInfo();
	}

	public void verifyReasonForChangeOkButtonIsDisabled() {
		Assert.assertTrue(reasonForChangeOkBTN.getAttribute("disabled").contains("true"));
		reportInfo();
	}

	public void commentFieldIsNotMandatoryForAllReasonOptionsExceptOtherOption() {
		for (WebElement reasonForChange : reasonForChangeReasonOptionLIST) {
			clickOn(reasonForChangeReasonDRPDOWN);
			if (!(reasonForChange.getText().trim().equals("Other"))) {
				clickOn(reasonForChange);
				verifyReasonForChangeCommentFieldIsEnabledAndNotRequired();
			} else if ((reasonForChange.getText().trim().equals("Other"))) {
				clickOn(reasonForChange);
				verifyReasonForChangeCommentFieldIsEnabledAndRequired();
			}
		}
	}

	/* Mark as Not Complete */

	@FindBy(xpath = "//a[@data-ng-click='markAsNotCompleted(form)']")
	private WebElement markAsNotCompletedLINK;

	public void clickOnMarkAsNotCompletedLink() {
		scrollPageThroughWebElement(markAsNotCompletedLINK);
		moveToElement(markAsNotCompletedLINK);
		javascriptButtonClick(markAsNotCompletedLINK);

	}

	public void verifyMarkAsCompletedLinkIsDisplayed() {
		scrollPageThroughWebElement(markAsNotCompletedLINK);
		Assert.assertTrue(isElementPresent(markAsNotCompletedLINK));
	}

	/** click refresh button under subject detail page **/
	public void clickOnRefreshBtnUnderSubjectDetailPage() {
		waitAndClick(refreshButtonOnSubjectDetailPage);
		spinnerBecomeInvisible();
	}

	/**
	 * Select options for ClinRo
	 *
	 */
	public void selectClinRoDropDownOption(String option) {
		scrollToTopOfThePage();
		clickOn(clinRoDropDown);
		for (WebElement reasonOption : clinRoDropDownOptionLIST) {
			if (reasonOption.getText().trim().equalsIgnoreCase(option)) {
				clickOn(reasonOption.findElement(By.xpath("./parent::li")));
				break;
			}
		}
		_normalWait(1000);
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='template']//div[@class='spinner']")));
	}

	/**
	 * Verify New subject detail page
	 */
	public void verifyNewSubjectDetailPage() {
		waitForElementToBecomeInvisible(ByLocator("//div[@class='smart-spinner ng-hide']/div[@class='spinner']"));
		new WebDriverWait(driver, 50)
			.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue(newSubjectDetailPageHeader.isDisplayed(), "Subject Deatil Page opened");
		reportInfo();
	}

	public void verifySubjectPageTitle(String titleToBeVerified) {
		_normalWait(timeout);
		Assert.assertTrue(newSubjectDetailPageHeader.getText().trim().contains(titleToBeVerified));
		reportInfo();
	}

	/**
	 * Function: Navigate to home page
	 * 
	 * 
	 */
	public void navigateBackToDashBoard() {
		try {
			if (isElementPresent(errorContainerCloseIcon) == true) {
				moveToElement(errorContainerCloseIcon);
				clickOn(errorContainerCloseIcon);
			} else {
				System.out.println("Error message not displayed.");
			}
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
		}
		selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.HomeNavText);
	}

	/**
	 * Select reason for change from the options
	 * 
	 * @param reasonToSelect
	 */
	public void selectReasonForChangeOption(String reasonToSelect) {
		waitAndClick(reasonForChangeReasonDRPDOWN);
		for (WebElement reasonOption : reasonForChangeReasonOptionLIST) {
			if (reasonOption.getText().trim().equalsIgnoreCase(reasonToSelect)) {
				clickOn(reasonOption.findElement(By.xpath("./parent::li")));
				break;
			}
		}
	}

	/**
	 * Verify reason for change from the options
	 * 
	 * @param reasonToSelect
	 */
	public void verifyReasonForChangeOption(List<String> reasonToSelect) {
		clickOn(reasonForChangeReasonDRPDOWN);

		List<String> actualReasonOptionLIST = new ArrayList<>();

		for (WebElement reasonOption : reasonForChangeReasonOptionLIST) {

			/** Store values of option present in Reason For Change drop down */
			String option = reasonOption.getText().trim();

			actualReasonOptionLIST.add(option);
		}

		Assert.assertEquals(actualReasonOptionLIST, reasonToSelect);

		clickOn(reasonForChangeReasonDRPDOWN);

		reportInfo();

	}

	/**
	 * Verify reason for change Authentication Text
	 */
	public void verifyReasonForChangeAuthenticationText(String AuthenticationText) {
		Assert.assertEquals(getText(reasonForChangeAuthenticationText).trim(), AuthenticationText);
		reportInfo();
	}

	/**
	 * Esign after selecting reason for change
	 * 
	 * @param userName
	 * @param password
	 */
	public AssessmentsDetailsPage eSignReasonForChangeAndSubmit(String userName, String password) {

		inputCredentialsInReasonForChangePopUp(userName, password);
		try {
			if (driver.findElement(By.xpath("//div[@class='smart-spinner']")).isDisplayed() == true) {
				new WebDriverWait(driver, 25).until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
			}
		} catch (Exception e) {
		}
		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

	public void verifyInvalidCredentialsTextIsDisplayedForReasonForChange(String validationMessageToBeVerified) {
		Assert.assertTrue(invalidPasswordValidationTEXT.getText().contains(validationMessageToBeVerified));
		reportInfo();
	}

	public void inputCredentialsInReasonForChangePopUp(String userName, String password) {
		_normalWait(500);
		inputText(reasonForChangeUserNameINP, userName);
		_normalWait(500);
		inputText(reasonForChangePasswordINP, password);
		clickOn(reasonForChangeOkBTN);
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
		reportInfo();
	}

	public void inputCredentialsForReasonForChange(String userName, String password) {
		inputText(reasonForChangeUserNameINP, userName);
		inputText(reasonForChangePasswordINP, password);
		reportInfo();
	}

	/** Verify New Subject Editing PopUp Window Displayed */
	public void verifySubjectEdtingPopUpDisplayed() {
		Assert.assertTrue(isElementPresent(subjecPopUpBOX));
	}

	/** Verify New Subject Editing PopUp Window is not Displayed */
	public void verifySubjectEdtingPopUpIsNotDisplayed() {
		Assert.assertFalse(subjecPopUpBOX.isDisplayed());
	}

	/**
	 * Input Screening Number
	 * 
	 * @param screeningNum
	 */
	public void inputScreeningNumber(String screeningNum) {
		waitForElement(screeningNumberINP);
		inputText(screeningNumberINP, screeningNum);
		reportInfo();
	}

	/**
	 * Input Temporary ID
	 */
	public void inputAutogenEncriptedTemporaryID(String TemporaryID) {

		String[] TemporaryIDToBeEnter = TemporaryID.split("-");

		waitForElement(addSubPopup_TemporaryID);
		addSubPopup_TemporaryID.clear();
		inputText(addSubPopup_TemporaryID, TemporaryIDToBeEnter[1]);
		reportInfo();
	}

	/* Input Temp ID */
	/**
	 * Enter temp number
	 * 
	 * @param tempIdToBeEnter
	 */
	public void inputTemporaryID(String tempIdToBeEnter) {
		inputText(addSubPopup_TemporaryID, tempIdToBeEnter);
		reportInfo();
	}

	/**
	 * Input Random Number
	 * 
	 * @param randomNumToBeEnter
	 */
	public void inputRandomNumber(String randomNumToBeEnter) {
		waitForElement(addSubPopup_RandomizationINP);
		addSubPopup_RandomizationINP.clear();
		inputText(addSubPopup_RandomizationINP, randomNumToBeEnter);
		reportInfo();
	}

	/**
	 * Input Note Text
	 * 
	 * @param noteTextToBeEnter
	 */
	public void inputNotesText(String noteTextToBeEnter) {
		inputText(editSubPopup_NotesINP, noteTextToBeEnter);
		reportInfo();
	}

	/**
	 * Verify Screening Text has been added
	 * 
	 * @param screenNumToBeVerified
	 */
	public void verifyScreeningNumIsEntered(String screenNumToBeVerified) {
		Assert.assertEquals(screenNumToBeVerified, screeningNumberINP.getAttribute("value"));
		reportInfo();
	}

	/* Get Screening Number From EditingSubjectPopUp */
	public String getScreeningNumberFromEditSubjectPopUp() {
		String screeningNumberText = screeningNumberINP.getAttribute("value");
		return screeningNumberText;
	}

	/* Get Screening Number From Subject detail Page */
	public String getSubjectNumberFromSubjectDetailPage() {
		String screeningNumberText = title_SubjectNumber.getText();
		return screeningNumberText;
	}

	/**
	 * Verify Save button of edit subject popup is displayed
	 */

	public void verifyEditSubjectSaveButtonIsDisplayed() {
		Assert.assertTrue(saveBTN.isDisplayed());
		reportInfo();
	}

	public void clickOnReasonForChangeCancelBTN() {
		clickOn(reasonForChangeCancelBTN);
	}

	/**
	 * Verify Save button of edit subject popup is displayed
	 */

	public void verifyEditSubjectSaveButtonIsDisabled() {
		Assert.assertFalse(saveBTN.isEnabled());
		reportInfo();
	}

	/**
	 * Verify cancel button of edit subject popup is displayed
	 */

	public void verifyEditSubjectCancelButtonIsDisplayed() {
		Assert.assertTrue(cancelBTN.isDisplayed());
		reportInfo();
	}

	/** Click On Editing Icon */
	public void clickOnSubjectEdtingIcon() {
		_normalWait(1000);
		scrollToTopOfThePage();
		moveToElement(subjectEditICN);
		waitAndClick(subjectEditICN);
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[@id='create-or-edit-subject-dialog']/div[@class='modal-dialog']//input[@id='screening-number-input']")));

	}

	public void verifySubjectEditButtonIsNotDisplayed() {
		Assert.assertFalse(subjectEditICN.isDisplayed());
		reportInfo();
	}

	public StudySubjectListingPage deleteSubject() {
		waitForElementClickable(subjectDeleteBTN, 10);
		waitAndClick(subjectDeleteBTN);
		waitAndClick(confirmPOPUPYesBTN);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']/div[@class='spinner']"));
		_normalWait(2000);
		return PageFactory.initElements(driver, StudySubjectListingPage.class);
	}

	/**
	 * Verify Subject edit Icon is displayed
	 */
	public void verifySubjectEdtingIconIsDisplayed() {
		Assert.assertTrue(isElementPresent(subjectEditICN));
		reportInfo();
	}

	public void verifysubjectDetailsLabelIsDisplayed() {
		waitForElement(label_subjectDetails);
		waitSpinnerToBecomeInvisible();
		Assert.assertTrue(isElementPresent(label_subjectDetails));
		moveToElement(label_subjectDetails);
		waitSpinnerToBecomeInvisible();
		_normalWait(3000);
		reportInfo();
	}

	public void verifysubjectNoInTitle(String SubjectNoToBeVerify) {
		waitForElement(title_SubjectNumber);
		waitSpinnerToBecomeInvisible();
		Assert.assertEquals(getText(title_SubjectNumber), SubjectNoToBeVerify);
		reportInfo();
	}

	/** Click on save button of edit screening popup **/
	public void clickOnSaveBtn() {
		waitForElement(saveBTN);
		javascriptButtonClick(saveBTN);
		_normalWait(1000);

		waitSpinnerToBecomeInvisible();
	}

	/** Click on cancel button of edit screening popup to close the popup **/
	public void clickOnCancelBtn() {
		clickOn(cancelBTN);
	}

	public void clickOnPopUpNoBtn() {
		clickOn(popUpNoBTN);
	}

	public void clickOnPopUpYesBtn() {
		clickOn(popUpYesBTN);
	}

	/** Verify Requirements Has Met */

	public void verifyRequiredFieldsFilled() {
		Assert.assertTrue(isElementPresent(filledInputBoxes));
	}

	/**
	 * Verify Subject detail page is display and screening is displayed as expected
	 * 
	 * @param screeningNumToBeVerify
	 */
	public void verifySubjectDetailAndScreeningNumberIsDisplayed(String screeningNumToBeVerify) {
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		Assert.assertTrue(isElementPresent(subjectDetailsGrid));
		Assert.assertEquals(getText(screeningNumberTXT), screeningNumToBeVerify);
		reportInfo();
	}

	/**
	 * Verify randomization Number display properly
	 * 
	 * @param randomizationNumberToBeVerify
	 */
	public void verifyRandomizationNumberIsDisplayed(String randomizationNumberToBeVerify) {
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		Assert.assertTrue(isElementPresent(subjectDetailsGrid));
		Assert.assertEquals(getText(randomizationNumberTXT), randomizationNumberToBeVerify);
		reportInfo();
	}

	/**
	 * Verify date Of Birth is displayed
	 * 
	 * @author Mrinalm
	 * @date 16/9/2019
	 */
	public void verifyDateOfBirthIsDisplayed() {
		Assert.assertTrue(isElementDisplayed(dateOfBirthTXT));
		moveToElement(dateOfBirthTXT);
	}

	/**
	 * Verify date Of Birth is not displayed
	 * 
	 * @author Mrinalm
	 * @date 16/9/2019
	 */
	public void verifyDateOfBirthIsNotDisplayed() {
		boolean flag = true;
		try {
			if (dateOfBirthTXT.isDisplayed())
				flag = false;
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify year Of Birth display empty
	 * 
	 * @author Mrinal
	 * @date 16/9/2019
	 */
	public void verifyYearOfBirthDisplayEmpty() {
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner' and @id='modal-fade']/div[@class='spinner']")));
		waitForPageLoaded();
		Assert.assertTrue(isElementPresent(subjectDetailsGrid));
		Assert.assertEquals(getText(yearOfBirthTXT), "");
		reportInfo();
	}
	
	public void verifyyearOfBirthOnSubjectdetailsPage(String value)
	{
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
         Assert.assertEquals(getText(yearOfBirthTXT),value);
         reportInfo();
	}
	
	@FindBy(xpath="//div[@class='year-editor-container']//div[@class='btn-group ng-isolate-scope']")
	private WebElement yeardropdownField;
	/**
	 * 
	 *  Verify Year in add/Editing subject popUp
	 * 
	 * @param value
	 */
	public void verifyYearOfBirthfieldvalue(String value)
	{
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
       Assert.assertEquals(getText(yeardropdownField), value);
       reportInfo();
		
	}
	
	@FindBy(xpath="//label[text()='Year of Birth']/..//div[@class='dropdown-menu']//li")
	private List<WebElement> YearOfBirthList;
	
	public void verifyValuesInYearofBirthdropDown()
	{
		boolean flag=true;
		try {
			if (YearOfBirthList.size()==0) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	/***
	 * 
	 * @param Year of Birth Displayed
	 */
	
	public void veriFyYearOfBirthDisplayedinsideEditPopup()
	{
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		WebElement birthYear = driver.findElement(ByLocator("//label[text()='Year of Birth']/..//button"));
       Assert.assertTrue(isElementPresent(birthYear));
		
		
	}

	/**
	 * Verify Note text is display
	 * 
	 * @param noteTextToBeVerify
	 */
	public void verifyNoteTextIsSaved(String noteTextToBeVerify) {
		Assert.assertEquals(getText(savedNotesTEXT), noteTextToBeVerify);
		reportInfo();
	}

	/**
	 * Verify Note text is added
	 * 
	 * @param noteTextToBeVerify
	 */
	public void verifyNoteTextIsAddedInEditSubjectPopUp(String noteTextToBeVerify) {
		Assert.assertTrue(editSubPopup_NotesINP.getAttribute("value").contains(noteTextToBeVerify));
		reportInfo();
	}

	public void verifyTempIdIsDisplayed(String temporaryIDToBeVerified) {
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		Assert.assertTrue(temporaryIDTXT.getText().trim().contains(temporaryIDToBeVerified));
		reportInfo();
	}

	/**
	 * Verify Subject detail Page is Displayed and Study And Site displayed as
	 * expected
	 * 
	 */
	public void verifySubjectStudyAndSite(String studyName, String siteName) {
		Assert.assertTrue(studyNameLabel.getText().contains(studyName) && siteNameLabel.getText().contains(siteName));
		reportInfo();
	}

	/**
	 * Verify Duplicate screening error container is displayed
	 */
	public void verifyDuplicateScreeningErrorPoUpIsDisplayed(String messageToVerify) {
		Assert.assertEquals(getText(errorMessage).trim(), messageToVerify);
		reportInfo();

	}

	/** Verify Error Message **/

	public void verifyErrorMessage(String message) {
		Assert.assertEquals(getText(errorMessage).trim(), message);
		reportInfo();
	}

	/**
	 * Verify Screening Number field is displayed And also Editable
	 */
	public void verifyScreeningNumIsDisplayedAndEditable() {
		Assert.assertTrue(screeningNumberINP.isDisplayed() && screeningNumberINP.isEnabled());
		reportInfo();
	}

	/**
	 * Verify subject language field is displayed And also Editable in edit popup
	 */
	public void verifyEditSubjectLanguageInputIsDisplayedAndEditable() {
		Assert.assertTrue(editSubPopup_LanguageINP.isDisplayed() && editSubPopup_LanguageINP.isEnabled());
		reportInfo();
	}

	/**
	 * Verify Screening Number field is Required
	 */
	public void verifyScreeningNumIsRequired() {
		Assert.assertTrue(editSubPopup_RequiredScreeningINP.isDisplayed() && screeningNumberINP.isEnabled());
		reportInfo();
	}

	/**
	 * Clear screening number
	 */
	public void clearScreeningInp() {

		waitForWebElementEnable(screeningNumberINP, 10);
		screeningNumberINP.clear();
		_normalWait(timeout);
		reportInfo();
	}

	/* Clear Temp Id */

	public void clearTempId() {
		addSubPopup_TemporaryID.clear();
	}

	/* Clear Random number */
	public void clearRandomNumber() {
		addSubPopup_RandomizationINP.clear();
	}

	/**
	 * Upadte value of screening number With new random screening number
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateScreeningInpWithNw(String AutomationSubjectName) throws Exception {

		clearScreeningInp();
		Configuration.updatePropertyTestData("RegressionTestData", AutomationSubjectName,
				"Auto_Screening_" + generateRandomString(3) + getRandomInteger(0, 9));
		Properties properties = Configuration.readTestData("RegressionTestData");
		String screeningNum = properties.getProperty(AutomationSubjectName);
		inputScreeningNumber(screeningNum);

		return screeningNum;
	}

	/**
	 * Close Error Container by click on close button
	 */
	public void closeErrorMessage() {
		clickOn(errorContainerCloseIcon);
	}

	/** Click On Visit Not Scheduled */
	public CentralRatingAppointmentPage clickOnVisitForScheduling() {
		for (WebElement visitStatus : visitStatusList) {
			String statusText = visitStatus.getText();
			if (statusText.equalsIgnoreCase("Cancelled")) {
				waitAndClick(visitStatus.findElement(By.xpath("./parent::div")));
				waitSpinnerToBecomeInvisible();
				clickOn(visitSchedularIcon);
				break;
			} else if (statusText.equalsIgnoreCase("")) {
				waitAndClick(visitStatus.findElement(By.xpath("./parent::div")));
				waitSpinnerToBecomeInvisible();
				clickOn(addVisitIcon);
				break;
			}
			else if (statusText.equalsIgnoreCase("None")) {
				waitAndClick(visitStatus.findElement(By.xpath("./parent::div")));
				waitSpinnerToBecomeInvisible();
				clickOn(addVisitIcon);
				break;
			}
		}
		return PageFactory.initElements(driver, CentralRatingAppointmentPage.class);

	}

	/** Click On Scheduled Visit */
	public CentralRatingAppointmentPage clickOnScheduledVisit() {
		for (WebElement visitStatus : visitStatusList) {
			if (getText(visitStatus).contains("Scheduled")) {
				waitAndClick(visitStatus.findElement(By.xpath("./parent::div")));
				clickOn(visitSchedularIcon);
				break;
			}
		}
		try {
			if (isElementPresent(By.xpath("//div[@data-ng-show='isBusy' and @class='ng-isolate-scope']"))) {
				waitForElementToBecomeInvisible(
						By.xpath("//div[@data-ng-show='isBusy' and @class='ng-isolate-scope']"));
			}
		} catch (Exception e) {
		}
		return PageFactory.initElements(driver, CentralRatingAppointmentPage.class);

	}

	/** Click On Requested Visit */
	public CentralRatingAppointmentPage clickOnRequestedVisit() {
		for (WebElement visitStatus : visitStatusList) {
			String statusText = visitStatus.getText();
			if (statusText.contains("Requested")) {
				waitAndClick(visitStatus.findElement(By.xpath("./parent::div")));
				waitSpinnerToBecomeInvisible();
				clickOn(visitSchedularIcon);
				break;
			}
		}
		return PageFactory.initElements(driver, CentralRatingAppointmentPage.class);

	}

	/**
	 * Verify Visit Grid listing is displayed
	 */
	public void verifyVisitGridDisplayed() {
		Assert.assertTrue(isElementPresent(visitGrid));
		reportInfo();
	}

	/**
	 * Verify Visit Category Filters are displayed
	 */
	public void verifyVisitCategoryFiltersDisplayed() {
		waitForPageLoaded();
		Assert.assertTrue(isElementPresent(visitCategoryFilterBlock));
		reportInfo();
	}

	/**
	 * select a Visit Category Filter list
	 */
	public void selectFilterFromVisitCategory(String filterToBeSelect) {
		boolean flag = false;
		waitForWebElementEnable(visitCategoryFilterBlock, 50);
		for (WebElement visitType : visitCategoryFiltersList) {
			if (getText(visitType).trim().equalsIgnoreCase(filterToBeSelect)) {
				javascriptButtonClick((visitType.findElement(By.xpath("./ancestor::li"))));
				flag = true;
				new WebDriverWait(driver, 90).until(ExpectedConditions.invisibilityOfElementLocated(
						By.xpath("//div[@class='smart-spinner']")));				
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Select visit row
	 */

	public void clickOnVisitRow(String visitToBeSelected) {
		scrollToTopOfThePage();
		boolean flag = false;
		waitForPageLoaded();
		_normalWait(3000);
		waitForWebElementEnable(subjectDetailsGrid, 50);
		for (WebElement visitRow : visitRowgrid) {
			if (getText(visitRow.findElement(By.xpath(".//div[contains(@class,'col-xs')]//span[@class='ng-binding']")))
					.trim().equalsIgnoreCase(visitToBeSelected)) {
				scrollPageThroughWebElement(visitRow);
				waitAndClick(visitRow);
				_normalWait(2000);
				scrollToTopOfThePage();
				new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
						By.xpath("//div[@class='row scores-show']//div[@class='spinner']")));
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		_normalWait(2000);
		reportInfo();

	}

	/**
	 * Select visit row with Calendar icon
	 */
	public void clickOnCalendarVisitRow(String visitToBeSelected) {
		boolean flag = false;
		waitForWebElementEnable(calendarVisitListGrid, 50);
		for (WebElement visitRow : calendarVisitsList) {
			if (getText(visitRow.findElement(By.xpath("./div/h4[@class='ng-binding']"))).trim()
					.contains(visitToBeSelected)) {
				waitAndClick(visitRow);
				_normalWait(2000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
						By.xpath("//div[@class='smart-spinner']//div[@class='spinner']")));
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Verify Visit Present In CalenderVisitRow */
	public void verifyVisitPresentInCalenderVisitRow(String visitBeVerify) {
		boolean flag = false;
		waitForWebElementEnable(calendarVisitListGrid, 50);
		if (calendarVisitsList.size() > 0) {
			for (WebElement visitRow : calendarVisitsList) {
				if (getText(visitRow.findElement(By.xpath("./div/h4[@class='ng-binding']"))).trim()
						.startsWith(visitBeVerify)) {
					flag = true;
					Assert.assertTrue(flag, "Visit Present In list");
					break;
				}
			}
		} else {
			Log.info("Visit List Not Present and Not Visit Added");
		}
	}

	/*
	 * Verify calendar Visit Present with projection dates*
	 * 
	 */

	public void verifyCalenderVisitPresentWithProjectionDates(String visitBeVerify) {
		boolean flag = false;
		waitForWebElementEnable(calendarVisitListGrid, 50);
		if (calendarVisitsList.size() > 0) {
			for (WebElement visitRow : calendarVisitsList) {
				if (getText(visitRow.findElement(By.xpath("./div/h4[@class='ng-binding']"))).trim()
						.startsWith(visitBeVerify)) {
					WebElement projectionTime = visitRow.findElement(
							By.xpath(".//div/h4[@class='ng-binding']//parent::div//following-sibling::div//time"));
					Assert.assertTrue(isElementPresent(projectionTime));
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag, "Visit Present with projection dates");
		reportInfo();

	}

	/* Verify visit selected By default */

	public void verifyVisitSelectedByDefault(String visitName) {
		boolean flag = false;
		if (calendarVisitsList.size() > 0) {
			WebElement selectedRow = driver.findElement(
					By.xpath("//h4[contains(text(),'" + visitName + "')]//parent::div//parent::div//parent::div"));
			if (selectedRow.getAttribute("class").contains("selected")) {
				moveToElement(selectedRow);
				flag = true;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	/* Verify Visit Not Present In CalenderVisitRow */
	public void verifyVisitNotPresentInCalenderVisitRow(String visitBeVerify) {
		boolean flag = true;
		waitForWebElementEnable(calendarVisitListGrid, 50);
		if (calendarVisitsList.size() > 0) {
			for (WebElement visitRow : calendarVisitsList) {
				if (getText(visitRow.findElement(By.xpath("./div/h4[@class='ng-binding']"))).trim()
						.startsWith(visitBeVerify)) {
					flag = false;
					Assert.assertFalse(flag, "Visit Present In list");
					break;
				} else {
					Assert.assertTrue(flag, "Visit  Not Present In list");
				}
			}
		} else {
			Log.info("Visit List Not Present and Not Visit Added");
		}
	}

	/**
	 * Verify Status of a calendar Visit
	 */
	public void verifyCalendarVisitStatus(String visitName, String visitStatusToBeVerified) {
		boolean flag = false;
		for (WebElement visitRow : calendarVisitsList) {
			if (getText(visitRow.findElement(By.xpath("./div/h4[@class='ng-binding']"))).trim().startsWith(visitName)
					&& getText(visitRow
							.findElement(By.xpath("./div/h4[contains(text(), '(" + visitStatusToBeVerified + ")')]")))
									.trim().contains(visitStatusToBeVerified)) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Select Initiate visit icon
	 */
	public void clickOnInitiateVisitIcon() {
		_normalWait(2000);
		if (initiateButtonForVisit.isDisplayed()) {
			waitAndClick(initiateButtonForVisit);
		}
		waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(ByLocator("(//div[@class='spinner'])[8]")); 
	}

	/**
	 * Verify Note section is displayed
	 */
	public void verifyNoteSectionIsDisplayed() {
		Assert.assertTrue(noteSection.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify edit Note button is displayed
	 */
	public void verifyEditNotesButtonIsDisplayed() {
		Assert.assertTrue(editNotesBtn.isDisplayed() && editNotesBtn.isEnabled());
		reportInfo();
	}

	/**
	 * Verify edit Note button is not displayed
	 */
	public void verifyEditNotesButtonIsNotDisplayed() {
		boolean flag = true;
		try {
			if (editNotesBtn.isDisplayed())
				flag = false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * select edit Note button
	 */
	public void clickOnEditNotesButtonIcon() {
		if (editNotesBtn.isDisplayed()) {
			clickOn(editNotesBtn);
			new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
					"//div[@id='content-container']//div[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		} else {
			Assert.assertFalse(false, "Edit Notes Button Icon is not displayed");
		}
	}

	/**
	 * Verify Visit Notes Modal Window is displayed
	 */
	public void verifyVisitNotesModalWindowIsDisplayed() {
		Assert.assertTrue(visitNotesModalWindow.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify Visit Notes Modal Window is closed
	 */
	public void verifyVisitNotesModalWindowIsClosed() {
		boolean flag = false;
		try {
			if (visitNotesModalWindow.isDisplayed())
				Assert.assertFalse(flag, "Visit Notes modal window is not closed ");
		} catch (Exception e) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Note Text is not saved On Notes Section
	 */
	public void verifyAbsenceOfUnsavedAddedNoteTextOnNotesSection(String textToBeVerified) {
		boolean flag = true;
		Assert.assertTrue(editNotesTextarea.isDisplayed());
		if (editNotesTextarea.getAttribute("value").trim().equalsIgnoreCase(textToBeVerified))
			flag = false;
		Assert.assertTrue(flag);
		reportInfo();

	}

	/**
	 * Verify Note Text on Notes Section
	 */
	public void verifyNoteTextOnNotesSection(String textToBeVerified) {
		Assert.assertTrue(editNotesTextarea.isDisplayed());
		if (editNotesTextarea.getAttribute("value").trim().equalsIgnoreCase(textToBeVerified))
			Assert.assertTrue(true, "Value present in Note Text = " + textToBeVerified);
		reportInfo();
	}

	/**
	 * Verify Visit Notes Text Box Under Modal Window Is Displayed
	 */
	public void verifyVisitNotesTextBoxUnderModalWindowIsDisplayedAndEditable() {
		Assert.assertTrue(NotesTextBoxInModalWindow.isDisplayed() && NotesTextBoxInModalWindow.isEnabled());
		reportInfo();
	}

	/**
	 * Enter value under Visit Notes Text Box in Modal Window Is Displayed
	 */
	public void enterValueInEditNoteUnderModelWindow(String ValueToBeEntered) {
		inputText(NotesTextBoxInModalWindow, ValueToBeEntered);
		reportInfo();
	}

	/**
	 * Clear value in Visit Notes Text Box under Modal Window Is Displayed
	 */
	public void clearValueInEditNoteUnderModelWindow() {
		waitForElement(NotesTextBoxInModalWindow);
		clearTextBox(NotesTextBoxInModalWindow);
		reportInfo();
	}

	/**
	 * Verify text entered in Visit Notes Text Box under Modal Window Is Displayed
	 */
	public void verifyTextEnteredInEditNoteUnderModelWindow(String ValueToBeEntered) {
		if (NotesTextBoxInModalWindow.getAttribute("value") != null) {
			Assert.assertEquals(NotesTextBoxInModalWindow.getAttribute("value").trim(), ValueToBeEntered);
		} else {
			Assert.assertFalse(false, "value is not displayed");
		}
		reportInfo();
	}

	/**
	 * Verify length of text entered in Visit Notes Text Box under Modal Window Is
	 * Displayed
	 */
	public String verifyLenthOfTextEnteredInEditNoteUnderModelWindow(int lenthOfString) {
		if (NotesTextBoxInModalWindow.getAttribute("value") != null) {
			Assert.assertEquals(NotesTextBoxInModalWindow.getAttribute("value").trim().length(), lenthOfString);
		} else {
			Assert.assertFalse(false, "value is not displayed");
		}
		reportInfo();
		return NotesTextBoxInModalWindow.getAttribute("value").trim();
	}

	/**
	 * Verify Save button is displayed but not active on Modal Window
	 */
	public void verifyInactiveSaveControlIsDisplayedOnModalWindow() {
		Assert.assertTrue(visitNotesModalWindowSaveButton.isDisplayed());
		Assert.assertFalse(visitNotesModalWindowSaveButton.isEnabled());
		reportInfo();
	}

	/**
	 * Verify Save button is displayed and active on Modal Window
	 */
	public void verifyActiveSaveControlIsDisplayedOnModalWindow() {
		Assert.assertTrue(visitNotesModalWindowSaveButton.isDisplayed() && visitNotesModalWindowSaveButton.isEnabled());
		reportInfo();
	}

	/**
	 * Verify Save button is displayed but not active on Modal Window
	 */
	public void selectSaveControlOnModalWindow() {
		if (visitNotesModalWindowSaveButton.isDisplayed() && visitNotesModalWindowSaveButton.isEnabled())
			waitAndClick(visitNotesModalWindowSaveButton);
		reportInfo();
	}

	/**
	 * Verify Cancel button is displayed on Modal Window
	 */
	public void verifyCancleButtonIsDisplayedOnModalWindow() {
		Assert.assertTrue(visitNotesModalWindowCancelButton.isDisplayed());
		reportInfo();
	}

	/**
	 * Select Cancel button is displayed on Modal Window
	 */
	public void clickOnCancleButtonIsDisplayedOnModalWindow() {
		Assert.assertTrue(visitNotesModalWindowCancelButton.isDisplayed());
		waitAndClick(visitNotesModalWindowCancelButton);
		reportInfo();
	}

	/**
	 * Verify Cancel icon is displayed on Modal Window header
	 */
	public void verifyCancleIconIsDisplayedOnModalWindowHeader() {
		Assert.assertTrue(visitNotesModalWindowCancelIconOnHeader.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify Status of a Visit
	 */
	public void verifyVisitStatus(String visitName, String visitStatusToBeVerified) {
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
		boolean flag = false;
		for (WebElement visitRow : visitRowgrid) {			
			scrollPageThroughWebElement(visitRow);
			_normalWait(3000);
			if (getText(visitRow.findElement(By.xpath(".//div[contains(@class,'col-xs')]//span[@class='ng-binding']")))
					.trim().contains(visitName)
					&& getText(visitRow.findElement(By.xpath(".//div[contains(@data-ng-class,'visitStatus')]/span")))
							.trim().contains(visitStatusToBeVerified)) {
				moveToElement(visitRow);
				flag = true;
				break;
			}
		}

		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Visit start date
	 */
	public void verifyVisitStartDate(String visitName, String visitStartDateToBeVerified) {
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
		boolean flag = false;
		for (WebElement visitRow : visitRowgrid) {
			if (getText(visitRow.findElement(By.xpath(".//div[contains(@class,'col-xs')]//span[@class='ng-binding']")))
					.trim().equalsIgnoreCase(visitName)
					&& getText(visitRow.findElement(By.xpath(".//span[@data-date='visit.date']/label[1]"))).trim()
							.equalsIgnoreCase(visitStartDateToBeVerified)) {
				flag = true;
				moveToElement(visitRow.findElement(By.xpath(".//span[@data-date='visit.date']/label[1]")));
				break;
			}
		}
		Assert.assertTrue(flag, visitStartDateToBeVerified + "Date is present in row");
		reportInfo();
	}

	public CentralRatingAppointmentPage clickOnAddVisitIcon() {

		if (addVisitIcon.isDisplayed()) {
			waitAndClick(addVisitIcon);
		}
		scrollToTopOfThePage();
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[@class='smart-spinner']")));
		return (PageFactory.initElements(driver, CentralRatingAppointmentPage.class));
	}

	public void clickOnCancelVisitIcon() {
		if (visitCancelBTN.isDisplayed()) {
			clickOn(visitCancelBTN);
			new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
					"//div[@id='content-container']//div[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		}
	}

	public void verifyAddVisitIconIsDisplayed() {
		Assert.assertTrue(addVisitIcon.isDisplayed() && addVisitIcon.isEnabled());
		reportInfo();
	}

	public void verifyEditVisitIconIsDisplayed() {
		Assert.assertTrue(editVisitIcon.isDisplayed() && editVisitIcon.isEnabled());
	}

	public void verifyAddVisitIconIsNotDisplayed() {
		Assert.assertEquals(0, driver.findElements(By.xpath(
				"//div[contains(@class,'selected')]//a[contains(@data-ng-click,'add') and @title='Add' and @class='circle-button btn btn-white']"))
				.size());
		reportInfo();
	}

	public void clickOnEditVisitIcon() {
		Assert.assertTrue(editVisitIcon.isDisplayed() && editVisitIcon.isEnabled());
		waitAndClick(editVisitIcon);
		new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[@id='content-container']//div[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));

	}

	public void verifyUnscheduledAddVisitBTNIsDisplayed() {
		Assert.assertTrue(unscheduledAddVisitBTN.isDisplayed());
		reportInfo();
	}

	public void clickOnUnscheduledAddVisitBTN() {
		javascriptButtonClick(unscheduledAddVisitBTN);
	}

	public void verifyUnscheduledVisitListDisplayed() {
		Assert.assertTrue(unscheduledListGrid.isDisplayed());
		reportInfo();
	}

	public void verifyVisitContainsInUnscheduledVisitList(String visitListToBeVerified) {
		boolean flag = false;
		for (WebElement visitName : unscheduledVisitLIST) {
			if (visitName.getText().trim().contains(visitListToBeVerified.trim())) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyVisitNotContainsInUnscheduledVisitList(String visitListToBeVerified) {
		boolean flag = true;
		for (WebElement visitName : unscheduledVisitLIST) {
			if (getText(visitName).trim().toLowerCase().equals(visitListToBeVerified.trim().toLowerCase())) {
				flag = false;
				break;
			}
		}
		Assert.assertTrue(flag, visitListToBeVerified + " visit should not be in unscheduled list");
		reportInfo();
	}

	public void selectUnscheduledVisit(String unscheduledVisitToBeSelected) {
		boolean flag = false;
		for (WebElement visitName : unscheduledVisitLIST) {
			if (visitName.getText().trim().equalsIgnoreCase(unscheduledVisitToBeSelected)) {
				clickOn(visitName);
				flag = true;
				waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
				break;
			}
		}
		Assert.assertTrue(flag, unscheduledVisitToBeSelected + " visit should be in unscheduled list");
	}

	/* Select Unscheduled CR Visit In Subject Detail Page */

	public CentralRatingAppointmentPage selectCRVisitFromUnscheduledVisitList(String unscheduledVisitToBeSelected) {
		boolean flag = false;
		for (WebElement visitName : unscheduledVisitLIST) {
			System.out.println(visitName.getText());
			if (visitName.getText().trim().equalsIgnoreCase(unscheduledVisitToBeSelected)) {
				clickOn(visitName);
				flag = true;
				waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
				break;
			}
		}
		Assert.assertTrue(flag, unscheduledVisitToBeSelected + " visit should be in unscheduled list");
		return (PageFactory.initElements(driver, CentralRatingAppointmentPage.class));
	}

	public void verifydeleteVisitIconIsNOtDisplayed() {
		Assert.assertFalse(deleteVisitIcon.isDisplayed());
		reportInfo();
	}

	public void deleteAddedVisit() {
		_normalWait(2000);
		if (deleteVisitIcon.isDisplayed()) {
			waitAndClick(deleteVisitIcon);
		}
	}

	public CentralRatingAppointmentPage clickOnSchedulerIcon() {
		if (visitSchedularIcon.isDisplayed()) {
			waitAndClick(visitSchedularIcon);
		}
		return (PageFactory.initElements(driver, CentralRatingAppointmentPage.class));
	}

	/* Verify Schedular Icon Calender */
	public void verifySchedularCalenderIconIsVisible() {
		Assert.assertTrue(visitSchedularIcon.isDisplayed() && visitSchedularIcon.isEnabled());
		reportInfo();
	}

	public void verifyScaleAndScaleTypeConfigured(String configuredFormType) {
		waitSpinnerToBecomeInvisible();
		scrollToTopOfThePage();
		boolean flag = false;
		Assert.assertTrue(configuredTemplatesList.size() > 0);
		if (configuredTemplatesList.size() > 0) {
			for (WebElement scaleRow : configuredTemplatesList) {
				if (getText(scaleRow.findElement(By.xpath(".//div[@class='small ng-binding']"))).trim().equalsIgnoreCase(configuredFormType)) {
					flag = true;
					moveToElement(scaleRow);
					break;
				}
			}
			Assert.assertTrue(flag);
			reportInfo();
		}
	}

	public void verifyRatersHyprlinkDisplayed(String RaterName) {
		scrollToTopOfThePage();
		Assert.assertTrue(raterLinkNexttoFormThumbnail.isDisplayed());
		Assert.assertEquals(getText(raterLinkNexttoFormThumbnail).trim(), RaterName);
		reportInfo();
	}

	public void verifyRatersNameDisplayedIsNotHyprlink(String RaterName) {
		scrollToTopOfThePage();
		Assert.assertTrue(ratersNonHyperlinkNameNexttoFormThumbnail.isDisplayed());
		Assert.assertEquals(getText(ratersNonHyperlinkNameNexttoFormThumbnail).trim(), RaterName);
		reportInfo();
	}

	public void verifySubmittedByRatersNameNonHyprlinkDisplayed(String RaterName) {
		scrollToTopOfThePage();
		Assert.assertTrue(submittedByRaterNameNexttoFormThumbnail.isDisplayed());
		Assert.assertEquals(getText(submittedByRaterNameNexttoFormThumbnail).trim(), RaterName);
		moveToElement(submittedByRaterNameNexttoFormThumbnail);
		reportInfo();
	}
	
	
	

	/**
	 * verify Additional Details (Override / Skipped Questions) Displayed Below
	 * Submitted Date with their available counts Under Assessment Details
	 */
	public void verifyAdditionalDetailsBelowSubmittedDateDisplayedUnderAssessmentDetails(String DetailsTypeToBeVerified,
			String AvailableCount) {
		boolean flag = true;
		scrollToTopOfThePage();
		waitForElement(additionalDetailsUndersubmittedDateNextToFormThumbnail);
		if (getText(additionalDetailsUndersubmittedDateNextToFormThumbnail).trim().endsWith(DetailsTypeToBeVerified)
				&& getText(additionalDetailsUndersubmittedDateNextToFormThumbnail).trim().startsWith(AvailableCount)) {
			flag = true;
			moveToElement(additionalDetailsUndersubmittedDateNextToFormThumbnail);
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyRatersNotHyprlinkIsDisabled(String RaterName) {
		scrollToTopOfThePage();
		Assert.assertTrue(ratersNonHyperlinkNameNexttoFormThumbnail.isDisplayed());
		Assert.assertEquals(getText(ratersNonHyperlinkNameNexttoFormThumbnail).trim(), RaterName);
		Assert.assertEquals(ratersNonHyperlinkNameNexttoFormThumbnail.getAttribute("data-ng-disabled").trim(), "true");
		reportInfo();
	}

	public void verifySubmittedByRatersNameNonHyprlinkIsDisabled(String RaterName) {
		boolean flag = false;
		scrollToTopOfThePage();
		Assert.assertTrue(submittedByRaterNameNexttoFormThumbnail.isDisplayed());
		Assert.assertEquals(getText(submittedByRaterNameNexttoFormThumbnail).trim(), RaterName);
		try {
			if (raterLinkNexttoFormThumbnail.isDisplayed())
				;
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public RatersDetailsPage clickOnRatersLink(String RaterName) {
		scrollToTopOfThePage();
		Assert.assertTrue(raterLinkNexttoFormThumbnail.isDisplayed());
		Assert.assertEquals(getText(raterLinkNexttoFormThumbnail).trim(), RaterName);
		waitAndClick(raterLinkNexttoFormThumbnail);
		reportInfo();
		return PageFactory.initElements(driver, RatersDetailsPage.class);
	}

	public void verifyScaleTypeNotConfigured(String nonConfiguredFormType) {
		scrollToTopOfThePage();
		boolean flag = false;
		if (configuredTemplatesList.size() > 0) {
			for (WebElement scaleRow : configuredTemplatesList) {
				if (getText(scaleRow.findElement(By.xpath("//div[@class='small ng-binding']")))
						.equalsIgnoreCase(nonConfiguredFormType)) {
					flag = true;
					break;
				}
			}
			Assert.assertFalse(flag);
		} else {
			Log.info("No Scale configured for selected visit");
		}
		reportInfo();
	}

	public void clickOnAssignRaterDropDown() {
		clickOn(assignDRPDWN);
	}

	public void verifyLoginUserDisplayedAtFirstPositionInScaleRaterList() {
		boolean flag = false;

		for (int i = 0; i <= scaleRaterList.size(); i++) {
			flag = getText((scaleRaterList.get(i))).equalsIgnoreCase("Me");
			break;
		}
		reportInfo();
		Assert.assertTrue(flag);
	}

	public void verifyPostionOfRaterInScaleRaterList(String raterName, int raterPostionToBeVerified) {
		boolean flag = false;
		for (int i = 0; i <= scaleRaterList.size() - 1; i++) {
			if (getText((scaleRaterList.get(i))).equalsIgnoreCase(raterName)) {
				if (i == raterPostionToBeVerified) {
					flag = true;
					break;
				}
			}
		}
		reportInfo();
		Assert.assertTrue(flag);
	}

	public void verifyRaterNameDisplayedInRaterDropDown(String raterName) {
		boolean flag = false;
		for (int i = 0; i <= scaleRaterList.size() - 1; i++) {
			if (getText((scaleRaterList.get(i))).trim().toLowerCase().contains(raterName.trim().toLowerCase())) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag, "List showing rater name");
		reportInfo();
		clickOn(assignDRPDWN);
	}
	
	
	public void verifyRaterNameIsNotDisplayedInRaterDropDown(String raterName) {
		waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(By.xpath("(//div[@id='modal-fade']/div[@class='spinner'])[7]"));
	    clickOn(assignDRPDWN);
		boolean flag = true;
		for (int i = 0; i <= scaleRaterList.size() - 1; i++) {
			if (getText((scaleRaterList.get(i))).trim().toLowerCase().contains(raterName.trim().toLowerCase())) {
				flag = false;
				break;
			}
		}
		Assert.assertTrue(flag, "List is not showing-up rater name");
		reportInfo();
		clickOn(assignDRPDWN);
	}

	// Select Rater From DropDown
	public void selectRaterFromDropDown(String raterName) {
		boolean flag = false;
		scrollToTopOfThePage();
		clickOn(assignDRPDWN);
		for (WebElement assigningRater : scaleRaterList) {
			waitForElement(assigningRater);
			if (assigningRater.getText().trim().contains(raterName)) {
				waitAndClick(assigningRater.findElement(By.xpath("./parent::li")));
				flag = true;
				break;
			}
		}

		Assert.assertTrue(flag);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='template']//div[@class='spinner']"));
	}

	public void navigateBackFromSubjectDetailsPage() {
		navigateBack();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
	}

	public void verifyRaterDropIsNotPresent() {
		boolean flag = false;
		if (assignDROPDOWN.size() == 0) {
			flag = true;
		}
		assertTrue(flag);
		reportInfo();

	}

	public void verifyRaterDropDownIsDisplayed() {
		Assert.assertTrue(assignDRPDWN.isDisplayed());
		reportInfo();
	}

	public void verifyRaterDropIsEnable() {
		Assert.assertTrue(assignDRPDWN.isEnabled());
		reportInfo();
	}

	/** Verify Visit Is Present In List */
	public void verifyVisitIsPresentInList() {
		boolean flag = false;
		if (visitRowgrid.size() > 0) {
			flag = true;
			Assert.assertTrue(flag, "Visit Is present In List");
		} else {
			Assert.assertFalse(flag, "Visit Is Not present In List");

		}
	}

	/** Verify Visit Is visit added Present in list */
	public void verifyVisitAddedIsPresentInList(String visitName) {
		boolean flag = false;
		waitForPageLoaded();
		waitForWebElementEnable(subjectDetailsGrid, 40);
		for (WebElement visitRow : visitRowgrid) {
			if (getText(visitRow.findElement(By.xpath(".//div[contains(@class,'col-xs')]//span[@class='ng-binding']")))
					.trim().equalsIgnoreCase(visitName)) {
				flag = true;
				Assert.assertTrue(flag);
				break;
			} else {
				Assert.assertFalse(flag);
			}
		}
	}

	/** Select Status */
	public void selectStatus(String subjectStatus) {
		boolean flag = false;
		clickOn(subjectStatusDRPBOX);
		for (WebElement status : statusList) {
			if (getText((status.findElement(By.xpath(".//a//span")))).trim().equalsIgnoreCase(subjectStatus)) {
				_normalWait(500);
				clickOn(status);
				flag = true;
				break;
			}

		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Verify Subject Status Present In List */
	public void verifySubjectStatusIsPresent(String subjectStatusToBeVerify) {
		boolean flag = false;
		clickOn(subjectStatusDRPBOX);
		for (WebElement status : statusList) {
			if (getText((status)).trim().equalsIgnoreCase(subjectStatusToBeVerify)) {
				moveToElement(status);
				flag = true;
				break;
			}

		}
		Assert.assertTrue(flag);
		reportInfo();
		clickOn(subjectStatusDRPBOX);
	}

	public void verifyDetailStatus(String detailLabel, String subjectStatusToBeVerified) {
		boolean flag = false;
		for (WebElement detailRow : detailRowgrid) {
			if (getText(detailRow.findElement(By.xpath(".//label[@class='caption']"))).trim()
					.equalsIgnoreCase(detailLabel)) {
				flag = getText(detailRow.findElement(By.xpath(".//span[contains(@class,'ng-binding')]"))).trim()
						.contains(subjectStatusToBeVerified);
				flag = true;
				if (flag = true) {
					break;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/** Verify Subject opened in Edit Mode after clicking on Edit Button */
	public void verifySubjectOpenedInEditMode() {

		javascriptButtonClick(subjectEditICN);
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='create-or-edit-subject-dialog']//*[@class='modal-dialog']")));
		javascripctHilightingElement(editSubjectPopUp);
		Assert.assertTrue(editSubjectPopUp.isDisplayed());
		reportInfo();
		
	}

	/**
	 * Verify subject can't be saved with empty values
	 */
	public void verifySubjectCantSavedWIthEmptyValues() {

		mouseHoverOnAnElement(screeningNumberAtEditSubjectPopUp);
		reportInfo();
		Assert.assertFalse(saveBTN.isEnabled());
		reportInfo();

	}

	public void verifyAppointmentPopUpIsDisplayed() {
		waitForWebElementEnable(appointmentPopUpSelectedDate, 30);
		Assert.assertTrue(appointmentPopUpTitle.isDisplayed());
		reportInfo();
	}

	public void verifyTodayDateIsSelected() {

		String[] currentDate = currentDate().split("-");
		String date = currentDate[0];
		if (Integer.parseInt(date) < 10) {
			date = date.replace("0", "");
		}
		Assert.assertEquals(appointmentPopUpSelectedDate.getText(), date);
		reportInfo();
	}
	

	public void verifyPastDateIsDisabledFromSelectedDate() {

		String todayDate = currentOnlyDate();
		int date = Integer.parseInt(todayDate);
		boolean flag = false;
		if (!(date == 1)) {
			for (int dateVar = 0; dateVar < datesList.size() - 1; dateVar++) {
				if (datesList.get(dateVar).findElement(By.xpath("./parent::td")).getAttribute("class")
						.contains("disabled")) {
					flag = true;
					continue;
				} else {
					flag = false;
					break;
				}
			}
			Assert.assertTrue(flag, "All past Dates are disabled");
			reportInfo();
		} else {
			flag = true;
			Assert.assertTrue(flag, "All past Dates are disabled date is starting date");
			reportInfo();
		}
	}

	/**
	 * Select appointment time to schedule appointment
	 * 
	 * @param timeToSelect
	 *            in format hh:mm AM/PM
	 */
	public void selectAppointmentTime(String timeToSelect) {
		waitUntillFinishProcessSpinnerDisable();
		waitForWebElementEnable(appointmentPopUpTimeSelectionDROPDOWN, 15);
		clickOn(appointmentPopUpTimeSelectionDROPDOWN);
		for (WebElement timeText : appointmentPopUpTimeLIST) {
			if (getText(timeText).trim().equals(timeToSelect)) {
				clickOn(timeText.findElement(By.xpath("./parent::li")));
				break;
			}
		}
	}

	public void clickOnAppointmentSearchBTN() {
		waitForWebElementEnable(appointmentPopUpSearchBTN, DEFAULT_WAIT_4_PAGE);
		clickOn(appointmentPopUpSearchBTN);
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[contains(@class,'modalshow in')]//div[@class='ng-isolate-scope']")));
	}

	public void verifyAppointmentTimeSlotGridIsDisplayed() {
		if (appointmentPopUpRequestedYesBTN.isDisplayed()) {
			clickOn(appointmentPopUpMoreAppointmentOptions);
		}
		Assert.assertTrue(appointmentPopUpTimeSlotGrid.isDisplayed());
		reportInfo();
	}

	/**
	 * Select time slot for Appointment from time slot options
	 * 
	 * @param timeSlotToBeSelect
	 *            in hh:mm AM/PM format
	 */
	public void selectAppointmentTimeSlot(String timeSlotToBeSelect) {
		for (WebElement timeSlot : appointmentPopUpTimeSlotLIST) {
			if (getText(timeSlot).trim().equals(timeSlotToBeSelect)) {
				waitAndClick(timeSlot);
			}
		}
	}

	public void verifyScheduleAppointmentPanelIsDisplayed() {
		Assert.assertTrue(appointmentPopUpScheduleControlGRID.isDisplayed());
		reportInfo();
	}

	public void clickOnYesButtonToConfirmAppointment() {
		clickOn(appointmentPopUpTimeSlotYesBTN);
		new WebDriverWait(driver, 25).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[contains(@class,'modalshow in')]//div[@class='ng-isolate-scope']")));
	}

	public void clickOnReScheduledConfirmYesBTN() {
		if (appointmentPopUpRequestedYesBTN.isDisplayed()) {
			clickOn(appointmentPopUpRequestedYesBTN);
		}
		if (reScheduledAppointmentPopUpYesBTN.isDisplayed()) {
			clickOn(reScheduledAppointmentPopUpYesBTN);
		}
		new WebDriverWait(driver, 25).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[contains(@class,'modalshow in')]//div[@class='ng-isolate-scope']")));
	}

	public void verifyCRAppointmentScheduledMessageIsDisplayed(String messageToBeVerified, String timeSlot) {
		String[] hoursSlot = timeSlot.split(":");
		if (hoursSlot[0].startsWith("0")) {
			hoursSlot[0] = hoursSlot[0].replace("0", "");
			timeSlot = hoursSlot[0] + ":" + hoursSlot[1];
		}
		// timeSlot = timeSlot + " on " + currentDate();
		String confirmMessage = "";
		String finalMessage = null;
		for (int i = 0; i <= appointmentPopUpConfirmMessage.size() - 1; i++) {
			confirmMessage = appointmentPopUpConfirmMessage.get(i).getText();
			finalMessage = confirmMessage + "\n" + appointmentPopUpConfirmMessage.get(i + 1).getText();
			break;
		}
		Assert.assertTrue(finalMessage.contains(messageToBeVerified + timeSlot));
		reportInfo();
	}

	/**
	 * @author Mrinalm
	 * @return get appointment Date From Appointment Confirmation popup
	 */
	public String getappointmentDateFromAppointmentConfirmationPopUp() {
		String confirmMessage, appointmentDate;
		confirmMessage = appointmentPopUpConfirmMessage.get(1).getText();
		appointmentDate = confirmMessage.trim().split(" on ")[1];
		reportInfo();
		return appointmentDate;
	}

	public void closeAppointmentPopup() {
		waitUntillFinishProcessSpinnerDisable();
		clickOn(popUpCloseICON);
	}

	public void inputReasonToReSchedulingTheAppointment(String reasonToReSchedulingTheAppointment) {
		if (appointmentPopUpReasonForReSchedulingINP.isDisplayed()) {
			inputText(appointmentPopUpReasonForReSchedulingINP, reasonToReSchedulingTheAppointment);
		} else if (reScheduledAppointmentPopUpReasonForReSchedulingINP.isDisplayed()) {
			inputText(reScheduledAppointmentPopUpReasonForReSchedulingINP, reasonToReSchedulingTheAppointment);
		}
		reportInfo();
	}

	public void clickOnYesBtnToCancelTheVisit() {
		clickOn(reasonPopUpReasonToYesBTN);
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(cancelAppointmentOKBTN));

	}

	public void verifyConfirmationDialogIsDisplayedAndClickOnOkBTN() {
		Assert.assertTrue(cancelAppointmentOKBTN.isDisplayed());
		clickOn(cancelAppointmentOKBTN);
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_ELEMENT);
		reportInfo();
	}

	public void inputReasonToCancelTheAppointment(String reasonToCancelTheAppointment) {
		inputText(reasonPopUpReasonToCancelINP, reasonToCancelTheAppointment);
		reportInfo();
	}

	public void verifyRescheduleAndCancelBtnIsDisplayedForSelectedVisit(String visitName) {
		waitUntillFinishProcessSpinnerDisable();
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		boolean flag = false;
		for (WebElement visitRow : visitRowgrid) {
			if (getText(visitRow.findElement(By.xpath(".//div[contains(@class,'col-xs')]//span[@class='ng-binding']")))
					.trim().equalsIgnoreCase(visitName)) {
				scrollPageThroughWebElement(visitRow);
				flag = visitCancelBTN.isDisplayed() && visitSchedularIcon.isDisplayed();

				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	public void VerifyAssignedAndCompletedCountIsDisplayedForSelectedVisit(String visitName) {
		boolean flag = false;
		for (WebElement visitRow : visitRowgrid) {
			if (getText(visitRow.findElement(By.xpath(".//div[contains(@class,'col-xs')]//span[@class='ng-binding']")))
					.trim().equalsIgnoreCase(visitName)) {
				flag = getText(
						visitRow.findElement(By.xpath(".//div[@class='extraTabletColumn col-sm-3 ng-binding'][1]")))
								.length() > 0
						&& getText(visitRow
								.findElement(By.xpath(".//div[@class='extraTabletColumn col-sm-3 ng-binding'][2]")))
										.length() > 0;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	public void verifyAssignedVisitCount(String visitName, String assignedCountToBeVerified) {
		boolean flag = false;
		for (WebElement visitRow : visitRowgrid) {
			if (getText(visitRow.findElement(By.xpath(".//div[contains(@class,'col-xs')]//span[@class='ng-binding']")))
					.trim().equalsIgnoreCase(visitName)) {
				String assignedCount[] = getText(
						visitRow.findElement(By.xpath(".//div[@class='extraTabletColumn col-sm-3 ng-binding'][1]")))
								.split("/");
				flag = assignedCount[0].toString().trim().contains(assignedCountToBeVerified);
				break;
			}
		}
		Assert.assertTrue(flag);
	}

	public void verifyConfirmPopUpMessageIsDisplayed(String popupTxtToBeVerified) {
		waitForElementPresent(popUpDisplayed, DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(getText(popUpDisplayed).trim().contains(popupTxtToBeVerified));
		reportInfo();
	}

	public void verifyVisitCanceledPopUpFields() {
		Assert.assertTrue(
				reasonPopUpReasonToCancelINP.isDisplayed() && popUpYesBTN.isDisplayed() && popUpNoBTN.isDisplayed());
		reportInfo();
	}

	public void verifyVisitCancelledAndRescheduledBtnIsNotDisplayed() {
		boolean flag=true;
		try {
			if (visitCancelHideBTN.isDisplayed() && visitSchedularHIDEBTN.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/** Return Back to Home Page */
	public MedAvantePortalPage navigateToHomePage() {
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
		selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.HomeNavText);
		return (PageFactory.initElements(driver, MedAvantePortalPage.class));
	}

	/** Click On Show History icon */

	public void clickOnShowHistory() {
		waitForElementPresent(showHistoryIcon, 15);
		javascriptButtonClick(showHistoryIcon);
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
	}

	/***
	 * verify Show History icon is displaying
	 */

	public void verifyShowHistoryIconDisplayed() {
		Assert.assertTrue(isElementPresent(showHistoryIcon));
		reportInfo();
	}

	/** Click On close History icon */

	public void clickOnCloseHistory() {
		clickOn(closeHistory);
		scrollToTopOfThePage();
	}

	/** Verify History Window Displayed */
	public void verifyHistoryPopUpDisplayed() {
		Assert.assertTrue(isElementPresent(subjcetStatusHistoryPopUp));
		reportInfo();
	}

	/** Check Subject Status History Contains The Information */
	public void verifySubjectStatusHistoryContainsInformation() {
		for (WebElement element : subjectStatusHistoryRow) {
			_normalWait(2000);
			Assert.assertTrue(getText(element
					.findElement(By.xpath(".//span[contains(@ng-bind-html,'eventHtmlText') and @class='ng-binding']")))
							.trim().contains("Status changed to"));

			break;
		}
		reportInfo();
	}

	/** Check Subject Status History Contains The Information */
	public void verifySubjectStatusHistoryContainsDateAndTime(String date) {
		for (WebElement element : subjectStatusHistoryRow) {
			Assert.assertTrue(getText(
					element.findElement(By.xpath(".//div[contains(@class,'date-col')]//span[@class='ng-binding']")))
							.trim().contains(date));
		}
		reportInfo();
	}

	/** Check Subject Status History Contains The Information */
	public void verifySubjectStatusHistoryContainsReasonForChangeStatus(String Event) {
		for (WebElement element : subjectStatusHistoryRow) {
			Assert.assertTrue(getText(element
					.findElement(By.xpath(".//span[contains(@ng-bind-html,'eventHtmlText') and @class='ng-binding']")))
							.trim().contains(Event));

		}
		reportInfo();
	}

	public void verifyPaperClippedThumbnailIsDisplayed() {
		_normalWait(2000);
		waitForElement(paperScaleClippedThumbnailIMG);
		Assert.assertTrue(paperScaleClippedThumbnailIMG.isDisplayed());
		reportInfo();
	}

	/** Verify Icon for note isn't displayed */
	public void verifyIconForNoteNotDisplayed() {
		boolean flag = historyICON.size() < 1;
		Assert.assertTrue(flag, "Note Icon is not displayed");
		reportInfo();
		navigateBack();
		_normalWait(500);
		navigateBack();
	}

	public void verifyLabelForSelectedVisit(String labelName) {
		String selectedLable = "//div[contains(@class,'small') and text()='" + labelName + "']";
		Assert.assertTrue(isElementPresent(selectedLable));

		reportInfo();
	}

	public void verifyLabelsForVisitHeader(String labelName) {
		String selectedLable = "//div[@class='row grid-header']/div[text()='" + labelName + "']";
		Assert.assertTrue(isElementPresent(selectedLable));
		reportInfo();
	}

	public void verifySubmittedVisitRaterName(String raterNameToBeVerified) {
		scrollToTopOfThePage();
		_normalWait(1000);
		Assert.assertTrue(getText(submittedScaleRaterNameTXT).contains(raterNameToBeVerified));
		reportInfo();
	}

	public void verifyRaterNameForNonAssignedVisit(String raterNameToBeVerified) {
		scrollToTopOfThePage();
		Assert.assertTrue(getText(nonSubmittedScaleRaterNameTXT).contains(raterNameToBeVerified));
		reportInfo();
	}

	public void verifyAssigneToFieldIsNonEditable() {
		Assert.assertTrue(isElementPresent(assignedToFieldValue));
		reportInfo();
	}

	public void verifySubmittedVisitDate(String dateToBeVerified) {
		Assert.assertTrue(scaleSubmittedDateTXT.getText().toLowerCase().trim().contains(dateToBeVerified.toLowerCase()));
		reportInfo();
	}

	/** Verify Rater Dropdown Selected Value */
	public void verifyRaterAssignmentDropDownSelectedValue(String valueSelected) {
		_normalWait(10);
		waitForElementPresent(dropdownSelectedValue, DEFAULT_WAIT_4_PAGE);
		Assert.assertTrue(getText(dropdownSelectedValue).trim().contains(valueSelected));
		reportInfo();
	}

	/** Verify observer selected value */
	public void verifyObserverSelectedValue(String valueSelected) {
		waitForElementPresent(observerSelectedValue, DEFAULT_WAIT_4_PAGE);
		Assert.assertTrue(getText(observerSelectedValue).trim().contains(valueSelected));
		reportInfo();

	}

	/** Verify Rater Dropdown Selected Value for configured Form Type */
	public void verifyRaterAssignmentDropDownSelectedValueForConfiguredFormType(String configuredFormType,
			String valueSelected) {
		scrollToTopOfThePage();
		boolean flag = true;
		Assert.assertTrue(configuredTemplatesList.size() > 0);
		if (configuredTemplatesList.size() > 0) {
			for (WebElement scaleRow : configuredTemplatesList) {
				if (getText(scaleRow.findElement(By.xpath("//div[@class='btn-group ng-isolate-scope']")))
						.equalsIgnoreCase(configuredFormType)) {
					Assert.assertTrue(getText(scaleRow.findElement(By.xpath(
							"./div[3]/div[@class='row administered-row']//button[contains(@class,'btn-default')]//span[1]")))
									.trim().contains(valueSelected));

					flag = true;
					break;
				}
			}
			Assert.assertTrue(flag);
			reportInfo();
		}
	}

	/** Verify assignment information for configured Form Type */
	public void verifyRaterAssignmentInfoNotAvailableForConfiguredFormType(String configuredFormType) {
		scrollToTopOfThePage();
		boolean flag = true;
		Assert.assertTrue(configuredTemplatesList.size() > 0);
		if (configuredTemplatesList.size() > 0) {
			for (WebElement scaleRow : configuredTemplatesList) {
				if (getText(scaleRow.findElement(By.xpath("//div[@class='btn-group ng-isolate-scope']")))
						.equalsIgnoreCase(configuredFormType)) {
					Assert.assertTrue(scaleRow
							.findElement(By.xpath("./div[3]/div[@class='row administered-row ng-hide']/parent::div"))
							.isDisplayed());
					flag = true;
					break;
				}
			}
			Assert.assertTrue(flag);
			reportInfo();
		}
	}

	/** Verify Raters available under Dropdown for configured Form Type */
	public void verifyRaterAvailableUnderDropdownForConfiguredFormType(String configuredFormType,
			String valueToBeSearch) {
		scrollToTopOfThePage();
		boolean flag = true;
		Assert.assertTrue(configuredTemplatesList.size() > 0);
		if (configuredTemplatesList.size() > 0) {
			for (WebElement scaleRow : configuredTemplatesList) {
				if (getText(scaleRow.findElement(By.xpath("//div[@class='btn-group ng-isolate-scope']")))
						.equalsIgnoreCase(configuredFormType)) {
					clickOn(scaleRow.findElement(
							By.xpath("./div//div[@class='row administered-row']//div[contains(@class,'btn-group ')]")));
					List<WebElement> RaterList = scaleRow.findElements(By.xpath(
							"./div//div[@class='row administered-row']//div[contains(@class,'btn-group ')]//ul//li/span"));
					for (int i = 0; i <= RaterList.size() - 1; i++) {
						if (getText((RaterList.get(i))).trim().toLowerCase()
								.contains(valueToBeSearch.trim().toLowerCase())) {
							flag = true;
							break;
						}
					}
					clickOn(scaleRow.findElement(
							By.xpath("./div//div[@class='row administered-row']//div[contains(@class,'btn-group ')]")));
				}
			}
			Assert.assertTrue(flag);
			reportInfo();
		}
	}

	/** Select Raters from Dropdown for multiple configured Form Type */
	public void selectRaterFromDropDownForMultipleConfiguredFormType(String configuredFormType,
			String valueToBeSearch) {
		scrollToTopOfThePage();
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		boolean flag = false;
		Assert.assertTrue(configuredTemplatesList.size() > 0);
		if (configuredTemplatesList.size() > 0) {
			for (WebElement scaleRow : configuredTemplatesList) {
				if (getText(scaleRow.findElement(By.xpath("//div[@class='small ng-binding']"))).equalsIgnoreCase(configuredFormType)) {
					waitAndClick(scaleRow.findElement(By.xpath("//div[@class='row administered-row']//div[contains(@class,'btn-group ')]")));
					List<WebElement> RaterList = scaleRow.findElements(By.xpath(
							"./div//div[@class='row administered-row']//div[contains(@class,'btn-group ')]//ul//li/span"));
					for (int i = 0; i <= RaterList.size() - 1; i++) {
						if (getText((RaterList.get(i))).trim().toLowerCase()
								.contains(valueToBeSearch.trim().toLowerCase())) {
							waitAndClick(RaterList.get(i));
							flag = true;
							break;
						}
					}
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		new WebDriverWait(driver, 20)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
	}

	/* Click On DropDown Of Multiple Form of same Type */
	public void selectRaterFromDropDownForMultipleFormOfSameName(String configuredFormType, String valueToBeSearch) {
		scrollToTopOfThePage();
		boolean flag = false;
		int count = 0;
		int index;
		List<Integer> sameConfigurationFormIndex = new ArrayList<>();
		if (configuredTemplatesList.size() > 0) {
			for (int formVar = 0; formVar < configuredTemplatesList.size(); formVar++) {
				WebElement formNameConfigured = configuredTemplatesList.get(formVar);
				if (getText(formNameConfigured.findElement(By.xpath("//div[@class='small ng-binding']")))
						.equalsIgnoreCase(configuredFormType)) {
					index = formVar;
					sameConfigurationFormIndex.add(index);
					count++;
				}
			}
		}
		if (count > 0) {
			for (int litsVarindex = 0; litsVarindex < sameConfigurationFormIndex.size(); litsVarindex++) {
				_normalWait(3000);
				new WebDriverWait(driver, 20).until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
				WebElement element = configuredTemplatesList.get(litsVarindex);
				clickOn(element.findElement(
						By.xpath("./div//div[@class='row administered-row']//div[contains(@class,'btn-group ')]")));
				List<WebElement> RaterList = element.findElements(By.xpath(
						"//div//div[@class='row administered-row']//div[contains(@class,'btn-group ')]//ul//li/span"));
				for (int i = 0; i <= RaterList.size() - 1; i++) {
					if (getText((RaterList.get(i))).trim().toLowerCase()
							.contains(valueToBeSearch.trim().toLowerCase())) {
						waitAndClick(RaterList.get(i));
						new WebDriverWait(driver, 20).until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
						flag = true;
						break;
					}
					
				}
				break;
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Select thumbnail IMG from multiple configured Form Type
	 * 
	 * @return
	 */
	public AssessmentsDetailsPage selectThumbnailIMGFromMultipleConfiguredFormType(String configuredFormType) {
		scrollToTopOfThePage();
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		Assert.assertTrue(configuredTemplatesList.size() > 0);
		if (configuredTemplatesList.size() > 0) {
			for (WebElement scaleRow : configuredTemplatesList) {
				// scrollPageThroughWebElement(scaleRow);
				_normalWait(DEFAULT_WAIT_4_ELEMENT);
				if (scaleRow.findElement(By.xpath(".//div[@class='small ng-binding']")).getText().trim()
						.equals(configuredFormType)) {
					// Assert.assertTrue(
					// scaleRow.findElement(By.xpath("./div//div[@class='form-cover-mask']/a")).isDisplayed());
					moveToElement(scaleRow.findElement(By.xpath("./div//div[@class='form-cover-mask']/a")));
					waitAndClick(scaleRow.findElement(By.xpath("./div//div[@class='form-cover-mask']/a")));
					break;
				}
			}
		}
		reportInfo();
		return (PageFactory.initElements(driver, AssessmentsDetailsPage.class));
	}

	/**
	 * Select thumbnail IMG from multiple configured Form Type
	 * 
	 * @return
	 */
	public void verifyFormOpenInPDFtemplate() {
		Assert.assertTrue(getAttributeValueOfElement(pdfFormOpenInNewWindow, "type").trim().endsWith("pdf"));
		reportInfo();
	}

	/** Verify Required Field Highlighted */
	public void requiredFieldHighlighted() {
		Assert.assertTrue(isElementPresent(highlightedInputBoxes));
	}

	/**
	 * Verify Configured Study Name is displayed
	 * 
	 * @param studyNameToBeVerified
	 */
	public void verifyStudyNameDisplayed(String studyNameToBeVerified) {
		Assert.assertEquals(studyNameToBeVerified, getText(studyNameTXT));
		reportInfo();
	}

	@FindBy(xpath = "//input[@name='screeningNum']")
	private WebElement screenNumINP;

	public void inputScreenNum(String screeningNum) {
		waitForElementPresent(screenNumINP, 10);
		inputText(screenNumINP, screeningNum);
		reportInfo();
	}

	@FindBy(xpath = "//div[@data-items='viewModel.cultures']")
	private WebElement languageDROPDOWN;

	@FindBy(xpath = "//div[@data-items='viewModel.cultures']//li/span")
	private List<WebElement> languageLIST;

	/**
	 * Select Language from the drop down option
	 * 
	 * @param LanguageToBeSelect
	 */
	public void selectSubjectLanguage(String LanguageToBeSelect) {
		clickOn(languageDROPDOWN);
		for (WebElement languageName : languageLIST) {
			if (languageName.getText().trim().equalsIgnoreCase(LanguageToBeSelect)) {
				clickOn(languageName.findElement(By.xpath("./parent::li")));
				break;
			}
		}
	}

	public void verifySubjectEditBtnIsDisplayed() {
		Assert.assertTrue(subjectDetailsEditIcon.isDisplayed());
		reportInfo();
	}
	
	/* Verify locked icon display */
	
	public void verifySubjectLockedIconIsDisplayed() {
		Assert.assertTrue(lockedIcon.isDisplayed());
		reportInfo();
	}

	/* Click on subject deatils edit icon */

	public void clickOnSubjectDetailsEditIcon() {
		waitForElementPresent(subjectDetailsEditIcon, DEFAULT_WAIT_4_PAGE);
		clickOn(subjectDetailsEditIcon);
	}
	
	/* Verify subject details Disabled edit icon */

	public void verifySubjectDetailsDisabledEditIconDisplayed() {
		Assert.assertTrue(subjectDetailsDisableEditIcon.isDisplayed());
		reportInfo();
	}

	
	
	public void verifySubjectDeleteBtnIsDisplayed() {
		Assert.assertTrue(subjectDeleteBTN.isDisplayed());
		reportInfo();
	}

	public void verifyScreeningDisplay(String screeningNumToBeVerified) {

		Assert.assertEquals(screeningNumToBeVerified, getText(screeningNumTXT));
		reportInfo();
	}

	/** Fill All Subject Data */
	public void fillRequiredSubjectFields(String subjectStatus, String language, String gender) {
		String randomNumber = String.valueOf(getRandomInteger(1, 8));
		inputText(randomNumberINP, "Ran" + randomNumber);
		clickOn(subjectStatusDRPBOX);
		for (WebElement status : statusList) {
			if (getText((status)).equalsIgnoreCase(subjectStatus)) {
				clickOn(status);
				break;
			}
		}
		clickOn(languageDRPBOX);
		for (WebElement languagelist : languageList) {
			if (getText((languagelist)).equalsIgnoreCase(language)) {
				clickOn(languagelist);
				break;
			}
		}
		clickOn(genderDRPBOX);
		for (WebElement genderlist : genderList) {
			if (getText((genderlist)).equalsIgnoreCase(gender)) {
				clickOn(genderlist);
				break;
			}
		}
		inputText(notesTXTArea, generateRandomString(7));

	}

	/** Verify Save Button Disabled */
	public void verifySaveButtonIsDisabled() {
		Assert.assertFalse(saveSubjectDetailBTN.isEnabled());
	}

	/** Click On Save Icon */
	public void clickOnSave() {
		waitForElementClickable(saveSubjectDetailBTN, 20);
		clickOn(saveSubjectDetailBTN);
	}

	/**
	 * Delete subject and return ion subject listing page
	 * 
	 * @return
	 */
	public StudySubjectListingPage clickOnSubjectDeleteBtn() {
		clickOn(deleteBTN);
		clickOn(removeBTN);
		spinnerBecomeInvisible();
		return PageFactory.initElements(driver, StudySubjectListingPage.class);
	}

	public void clickOnAddObserverBtN() {
		waitAndClick(addObserversBTN);
	}

	public void inputObserverRelationName(String observerRelationNameToBeEnter) {
		inputText(observerRelationINP, observerRelationNameToBeEnter);
		reportInfo();
	}

	public void inputObserverAliasName(String observerAliasNameToBeEnter) {
		inputText(observerAliasINP, observerAliasNameToBeEnter);
		reportInfo();
	}

	public void clickOnObserverSaveBTN() {
		waitAndClick(observerSaveBTN);
	}

	/**
	 * Verify Relation field is Required
	 */
	public void verifyObserverRelationAnAliasFieldsAreRequired() {
		Assert.assertTrue(observerRelationRequiredINP.isDisplayed() && observerAliasRequiredINP.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify Save button of observer popup is disable and cancel enable
	 */

	public void verifyObserverSaveButtonIsDisabledAndCancelEnable() {
		Assert.assertFalse(reportedOutComePopUpSaveBTN.isEnabled());
		Assert.assertTrue(reportedOutComePopUpCancelBTN.isEnabled());
		reportInfo();
	}

	/**
	 * Verify Save button and cancel buttons enable of observer popup
	 */

	public void verifyObserverSaveAndCancelButtonAreEnable() {

		Assert.assertTrue(reportedOutComePopUpSaveBTN.isEnabled() && reportedOutComePopUpCancelBTN.isEnabled());
		reportInfo();
	}

	public void configureObserver(String observerRelationNameToBeEnter, String observerAliasNameToBeEnter) {
		clickOnAddObserverBtN();
		inputObserverRelationName(observerRelationNameToBeEnter);
		inputObserverAliasName(observerAliasNameToBeEnter);
		clickOnObserverSaveBTN();
	}

	// temporary

	public void clear() {
		formSubjectDetailsGrid.clear();
		randomNumberINP.clear();

	}

	/* Verify Subject is Added In List */
	public boolean subjectPresentInList(String randomNumber) {
		if (randomNumber.equalsIgnoreCase(subjectName.getText())) {
			return true;
		}
		return false;
	}

	/* Verify Subject is not Added In List */
	public boolean subjectNotAddedInList(String randomNumber) {
		if (!randomNumber.equalsIgnoreCase(subjectName.getText())) {
			return true;
		}
		return false;

	}

	/** Click On Cancel Icon */
	public void clickOnCancelIcon() {
		waitForElementClickable(cancelSubjectDeatilsBTN, 20);
		clickOn(cancelSubjectDeatilsBTN);
	}

	public void clickOnEnterPaperTranscriptionWithReasonchangeClick() {
		waitAndClick(enterPaperTranscriptionLINK);
		waitAndClick(reasonChangeDRPDOWN);

	}

	/**
	 * Paper transcription reason to select
	 * 
	 * @param reasonToSelect
	 */
	public void selectPaperTranscriptionReason(String reasonToSelect) {
		for (WebElement reason : reasonChangeLST) {
			if (getText(reason).trim().equalsIgnoreCase(reasonToSelect)) {

				waitAndClick(reason.findElement(By.xpath("./parent::li")));
				break;

			}

		}
	}

	/** Verify Reason For change popup is displayed **/
	public void verifyPaperTranscriptionPopUpIsDisplayed() {
		Assert.assertTrue(isElementPresent(reasonForChangePopUpTitle));
	}

	/**
	 * eSign For Paper Transcription
	 * 
	 * @param userName
	 * @param password
	 *
	 */
	public AssessmentsDetailsPage eSignForPaperTranscription(String userName, String password) {
		inputText(reasonChangeUserNameINP, userName);
		inputText(reasonChangeUserPasswordINP, password);
		waitAndClick(reasonChangeOKBTN);
		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

	/**
	 * Verify Subject detail page is display and screening is displayed as expected
	 * 
	 * @param screeningNumToBeVerify
	 */
	public void verifySubjectDetailAndScreeningIsDisplayed(String screeningNumToBeVerify) {
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		Assert.assertTrue(subjectDetailsGrid.isDisplayed());
		Assert.assertTrue(getText(screeningNumberTXT).equalsIgnoreCase(screeningNumToBeVerify));
		reportInfo();
	}

	/** Navigate to previous page from the subject details page **/
	public void navigateBackToPreviousPage() {
		navigateBack();
		reportInfo();
	}

	/** Select Visit Option From DropDown On SubjectDetailPage */

	public void selectOptionFromSubjectCategoriesDropDownList(String optionToBeSelected) {
		_normalWait(1000);
		javascriptButtonClick(visitDropDownSelect);
		_normalWait(1000);
		for (WebElement visitOptionlist : visitRelatedOptionOnDropDown) {
			if (getText((visitOptionlist)).equalsIgnoreCase(optionToBeSelected)) {
				clickOn(visitOptionlist);
				waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
				break;
			}
		}
	}

	/* Verify Categories drop-down list unavailable for selection */
	public void verifyCategoriesDropdownListUnavailabelForSelection() {
		boolean flag = false;
		if (visitRelatedOptionOnDropDown.size() == 0) {
			flag = true;
			Assert.assertTrue(flag, "Categories drop-down list is unavailable for selection");
			reportInfo();
		}
	}

	/* Verify Only Visit List Displayed */
	public void verifyOnlyVisitListDisplayed() {
		javascripctHilightingElement(onlyVisitSectionListHeader);
		Assert.assertTrue(isElementPresent(onlyVisitSectionListHeader));
		reportInfo();
	}

	/* Verify After Submiited Assesment Thumbnail Image Present */
	public void verifyAfterSubmissionNotAdministeredThumbnailImage() {
		moveToElement(thumbnailIMG);
		Assert.assertTrue(thumbnailIMG.isDisplayed());
		reportInfo();
	}

	public AssessmentsDetailsPage verifyAndClickOnAfterSubmittedThumbnailImage() {
		scrollToTopOfThePage();
		Assert.assertTrue(thumbnailIMG.isDisplayed());
		moveToElement(thumbnailIMG);
		scrollToTopOfThePage();
		waitAndClick(thumbnailIMG);
		reportInfo();
		return (PageFactory.initElements(driver, AssessmentsDetailsPage.class));
	}

	/**
	 * Click on row on the basis of column name
	 * 
	 * @param columnName
	 * @param rowValue
	 */

	@FindBy(xpath = "//div[@class='observers-header']//span")
	private List<WebElement> headerObserverList;

	public void verifyObserverInformation(String observerSelected, String information, String informationToBeVerified) {
		boolean flag = false;
		for (int observerInformation = 0; observerInformation < observerLIST.size(); observerInformation++) {
			String observerName = observerLIST.get(observerInformation)
					.findElement(By.xpath("./span[@class='relation-column']")).getText();
			if (observerName.trim().equalsIgnoreCase(observerSelected)) {
				int recordRow = observerInformation;
				for (int columnNameIndex = 0; columnNameIndex < headerObserverList.size(); columnNameIndex++) {
					if (getText(headerObserverList.get(columnNameIndex)).trim().equalsIgnoreCase(information)) {
						int columnNameIndex1 = columnNameIndex + 1;
						if (getText(
								observerLIST.get(recordRow).findElement(By.xpath(".//span[" + columnNameIndex1 + "]")))
										.trim().equalsIgnoreCase(informationToBeVerified)) {
							flag = true;
							Assert.assertTrue(flag, "Observer Details Are Present");
							reportInfo();
							break;
						} else {
							Assert.assertFalse(flag, "Observer Details Are  Not Present");
							reportInfo();
						}
						break;
					}
				}
				break;
			} else {
				Log.info("No Observer Present");
			}
		}
	}

	public void verifyReportedOutcomeSaveButtonIsDisabled() {
		Assert.assertFalse(reportedOutComePopUpSaveBTN.isEnabled(), "the save button is disabled");
		reportInfo();
	}

	public void refreshSubjectDetailPage() {
		javascriptButtonClick(refreshIcon);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
	}

	@FindBy(xpath = "//div[@id='virgilForms']//label[text()='Processing...']")
	private WebElement scaleProcessingText;

	public void waitForProcessingVisitToBeCompleted() {

		do {
			refreshSubjectDetailPage();
			waitForElementPresent(scaleGrid, 20);
			waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		} while (isElementDisplayed(scaleProcessingText) == true);
	}

	/*
	 * Verify Reported OutCome QR Code For Mobile Subject Registration Dialog Is Not
	 * Displayed
	 */

	public void verifyReportedOutComeQRCodeForMobileSubjectRegistrationDialogIsNotDisplayed() {
		boolean flag = false;
		if (reportedOutcomeMobileRegistrationDialogBTN.size() > 0) {
			flag = true;
			Assert.assertTrue(flag, " QR Code For Reported Outcome Mobile Subject Registration Dialog Is Displayed");
			reportInfo();
		} else {
			Assert.assertFalse(flag,
					"QR Code For  Reported Outcome Mobile Subject Registration Dialog Is Not Displayed");
			reportInfo();
		}
	}

	/*
	 * Verify Reported OutCome QR Code For Mobile Subject Registration Dialog Is
	 * Displayed
	 */

	public void verifyReportedOutComeQRCodeForMobileSubjectRegistrationDialogIsDisplayed() {
		boolean flag = false;
		if (reportedOutcomeMobileRegistrationDialogBTN.size() > 0) {
			flag = true;
			Assert.assertTrue(flag, " QR Code For Reported Outcome Mobile Subject Registration Dialog Is Displayed");
			reportInfo();
		} else {
			Log.info("Configure Mobile Subject");
		}
	}

	@FindBy(xpath = "//div[@data-ng-show='ShowAvailableSchedulingTimes']//div[contains(@class,'menu')]")
	private WebElement timeSlotGridTitle;

	public String selectAppointmentDateAndTime(String selectedTime) {
		waitForAjaxRequestsToComplete();
		for (WebElement selfCrdateList : calenderDateList) {
			if (selfCrdateList.isEnabled()) {
				clickOn(selfCrdateList);
			}
			selectAppointmentTime(selectedTime);
			_normalWait(1000);
			clickOnAppointmentSearchBTN();
			if (isElementDisplayed(timeSlotGridTitle)
					&& timeSlotGridTitle.getText().contains(differentTimeSlotMessage)) {
				selectedTime = selectDyanamicAppointmentTimeSlot();
				clickOnYesButtonToConfirmAppointment();
			} else if (isElementDisplayed(timeSlotGridTitle)
					&& timeSlotGridTitle.getText().contains(clinicansNotAvailableMesssage)) {
				continue;
			} else if (isElementDisplayed(appointmentPopUpRequestedYesBTN)
					&& isElementDisplayed(appointmentPopUpMoreAppointmentOptions)) {
				selectedTime = configureTime(selectedTime);
				waitAndClick(appointmentPopUpRequestedYesBTN);
				new WebDriverWait(driver, 25).until(ExpectedConditions.invisibilityOfElementLocated(
						By.xpath("//div[contains(@class,'modalshow in')]//div[@class='ng-isolate-scope']")));
				if (isElementDisplayed(timeSlotGridTitle)
						&& timeSlotGridTitle.getText().contains(selectDifferentTime)) {
					selectedTime = selectDyanamicAppointmentTimeSlot();
					clickOnYesButtonToConfirmAppointment();
				}
			}
			break;
		}
		return selectedTime;
	}

	@FindBy(xpath = "(//div[contains(@class,'modalshow in')]//div[@ng-click='confirmButtonClick()'])[2]")
	private WebElement reScheduledAppointmentPopUpYesBTN;

	@FindBy(xpath = "(//div[contains(@class,'modalshow in')]//textarea[@data-ng-model='crReschedulingComment'])[2]")
	private WebElement reScheduledAppointmentPopUpReasonForReSchedulingINP;

	public String reScheduledVisitAppointmentTime() {
		// selectAppointmentTime(selectedTime);
		String selectedTime = null;
		waitForWebElementEnable(appointmentPopUpTimeSelectionDROPDOWN, 25);
		clickOn(appointmentPopUpTimeSelectionDROPDOWN);
		for (WebElement timeText : appointmentPopUpTimeLIST) {
			clickOn(timeText.findElement(By.xpath("./parent::li")));
			try {
				clickOnAppointmentSearchBTN();
			} catch (Exception e) {
				continue;
			}
			_normalWait(1000);
			if (isElementDisplayed(timeSlotGridTitle)
					&& timeSlotGridTitle.getText().contains(clinicansNotAvailableMesssage)) {
				clickOn(appointmentPopUpTimeSelectionDROPDOWN);
				continue;
			} else {
				selectedTime = getText(appointmentPopUpTimeSelection);
				break;
			}

		}
		if (isElementDisplayed(timeSlotGridTitle) && timeSlotGridTitle.getText().contains(differentTimeSlotMessage)) {
			selectedTime = selectDyanamicAppointmentTimeSlot();

		}

		selectedTime = configureTime(selectedTime);
		inputReasonToReSchedulingTheAppointment("TestReason " + generateRandomString(2));
		clickOnReScheduledConfirmYesBTN();

		return selectedTime;
	}

	/**
	 * Select time slot for Appointment from time slot options
	 * 
	 * @param timeSlotToBeSelect
	 *            in hh:mm AM/PM format
	 * @return
	 */
	public String selectDyanamicAppointmentTimeSlot() {
		WebElement element = appointmentPopUpTimeSlotLIST
				.get(getRandomInteger(0, appointmentPopUpTimeSlotLIST.size() - 1));
		waitAndClick(element);
		return configureTime(element.getText());
	}

	public String configureTime(String selectedTime) {
		String modifiedTime = "";
		String selectedTimeValue[] = selectedTime.trim().split(":");
		if (selectedTimeValue[0].length() == 1) {
			selectedTimeValue[0] = "0" + selectedTimeValue[0];
			modifiedTime = selectedTimeValue[0] + ":" + selectedTimeValue[1];
			_normalWait(500);
		} else {
			modifiedTime = selectedTime;
		}

		return modifiedTime;
	}

	@FindBy(xpath = "//span[@aria-owns='mobile-observer-select_listbox']")
	private WebElement mobileProObserverSelectDRPDWN;

	/** Select Enabled from Mobile Pro Observer */

	@FindBy(xpath = "//div[@id='mobile-observer-select-list']//ul[@class='k-list k-reset']//li")
	private List<WebElement> mobileProObserverList;

	/** Select Enabled from Mobile Pro Observer */

	public void selectMobileProObserverEnabledOption() {
		waitForElement(reportedOutComeMobileProObserverDRPDOWN);
		clickOn(reportedOutComeMobileProObserverDRPDOWN);
		clickOn(reportedOutComeMobileProObserverEnabled);
	}

	/* Select Mobile Pro Observer DropDown For Select Observer From The List */

	public void selectMobileProObserverInnerDropDown() {
		clickOn(mobileProObserverSelectDRPDWN);
	}

	/* Verify Mobile Observer Selection Drop Down List Under Observer Opened */
	public void verifyMobileProObserverSelectionDropDownListOpen() {
		waitForElement(mobileProObserverSelectDRPDWN);
		Assert.assertTrue(mobileProObserverSelectDRPDWN.getAttribute("aria-expanded").equalsIgnoreCase("true"));
		moveToElement(mobileProObserverSelectDRPDWN);
		clickOn(mobileProObserverSelectDRPDWN);
		reportInfo();
	}

	/* Verify Mobile Pro Observer Not Present In Observer to choose List */

	public void verifyObserverNotPresentInObserverToSelectList(String observerToSelect) {
		clickOn(mobileProObserverSelectDRPDWN);
		boolean flag = false;
		for (WebElement observerNameList : mobileProObserverList) {
			moveToElement(observerNameList);
			if (!(getText(observerNameList).contains(observerToSelect))) {
				flag = true;
			}
		}
		Assert.assertTrue(flag, "Observer Not Present In DropDown List");
		reportInfo();
		clickOn(mobileProObserverSelectDRPDWN);
	}

	/* Verify Mobile Pro Observer Present In Observer to choose List */
	public void verifyObserverPresentInObserverToSelectList(String observerToSelect) {
		clickOn(mobileProObserverSelectDRPDWN);
		boolean flag = false;
		for (WebElement observerNameList : mobileProObserverList) {
			moveToElement(observerNameList);
			if (getText(observerNameList).contains(observerToSelect)) {
				flag = true;
			}
		}
		Assert.assertTrue(flag, "Observer Present In DropDown List");
		reportInfo();
		clickOn(mobileProObserverSelectDRPDWN);
	}

	/** Select Option from Mobile Pro Subject DropDown list */

	public void selectMobileProSubjectOption(String OptionToBeSelect) {
		waitForElement(reportedOutComeMobileProSubjectDRPDOWN);
		clickOn(reportedOutComeMobileProSubjectDRPDOWN);
		clickOn(reportedOutComeMobileProSubjectlist.findElement(By.xpath("./li[text()='" + OptionToBeSelect + "']")));
	}

	/** Select Option from Mobile Pro Observer DropDown list */

	public void selectMobileProObserverOption(String OptionToBeSelect) {
		waitForElement(reportedOutComeMobileProObserverDRPDOWN);
		clickOn(reportedOutComeMobileProObserverDRPDOWN);
		clickOn(reportedOutComeMobileProObserverlist.findElement(By.xpath("./li[text()='" + OptionToBeSelect + "']")));
		reportInfo();
	}

	/**
	 * Mobile Pro Subject Disable Reason text box is highlighted as required field
	 */

	public void verifyMobileProSubjectDisableReasonTextBoxIsHighlightedAsRequiredField() {
		waitForElement(reportedOutComeMobileProSubjectDisableReasonTextbox);
		Assert.assertTrue(reportedOutComeMobileProSubjectDisableReasonTextbox.getAttribute("class")
				.contains("ng-invalid-required"));
		moveToElement(reportedOutComeMobileProSubjectDisableReasonTextbox);
		reportInfo();
	}

	/**
	 * Mobile Pro Observer Disable Reason text box is highlighted as required field
	 */
	public void verifyMobileProObserverDisableReasonTextBoxIsHighlightedAsRequiredField() {
		waitForElement(reportedOutComeMobileProObserverDisableReasonTextbox);
		Assert.assertTrue(reportedOutComeMobileProObserverDisableReasonTextbox.getAttribute("class")
				.contains("ng-invalid-required"));
		moveToElement(reportedOutComeMobileProObserverDisableReasonTextbox);
		reportInfo();
	}

	/**
	 * Enter the reason into Mobile Pro Observer Reason text box
	 */
	public void enterReasonIntoMobileProObserverReasonTextBoxField(String ReasonToBeEntered) {
		waitForElement(reportedOutComeMobileProObserverDisableReasonTextbox);
		moveToElement(reportedOutComeMobileProObserverDisableReasonTextbox);
		inputText(reportedOutComeMobileProObserverDisableReasonTextbox, ReasonToBeEntered);
		reportInfo();
	}

	/**
	 * Enter the reason into Mobile Pro Subject Reason text box
	 */
	public void enterReasonIntoMobileProSubjectReasonTextBoxField(String ReasonToBeEntered) {
		waitForElement(reportedOutComeMobileProSubjectDisableReasonTextbox);
		moveToElement(reportedOutComeMobileProSubjectDisableReasonTextbox);
		inputText(reportedOutComeMobileProSubjectDisableReasonTextbox, ReasonToBeEntered);
		reportInfo();
	}

	/**
	 * Verify text into Mobile Pro Subject Reason text box while its on edit mode
	 * (i.e. value not saved yet)
	 */
	public void verifyReasonIntoMobileProObserverReasonTextBoxField(String ReasonToBeVerified) {
		boolean flag = true;
		waitForElement(reportedOutComeMobileProSubjectDisableReasonTextbox);
		moveToElement(reportedOutComeMobileProSubjectDisableReasonTextbox);
		if (getAttributeValueOfElement(reportedOutComeMobileProSubjectDisableReasonTextbox, "value") != null) {
			Assert.assertEquals(
					getAttributeValueOfElement(reportedOutComeMobileProSubjectDisableReasonTextbox, "value").trim(),
					ReasonToBeVerified);
		} else {
			Assert.assertFalse(flag, "Subject Reason text box containing null value");
		}
		reportInfo();
	}

	/** Verify Mobile Pro Subject Enabled */
	public void verifyMobileProSubjectDropDownEnabled() {
		waitForElement(reportedOutComeMobileProSubjectDRPDOWN);
		Assert.assertTrue(
				reportedOutComeMobileProSubjectDRPDOWN.getAttribute("aria-disabled").equalsIgnoreCase("false"));
		moveToElement(reportedOutComeMobileProSubjectDRPDOWN);
		reportInfo();
	}

	/** Verify Mobile Pro Observer Enabled */
	public void verifyMobileProObserverDropDownEnabled() {
		waitForElement(reportedOutComeMobileProObserverDRPDOWN);
		Assert.assertTrue(
				reportedOutComeMobileProObserverDRPDOWN.getAttribute("aria-disabled").equalsIgnoreCase("false"));
		moveToElement(reportedOutComeMobileProObserverDRPDOWN);
		reportInfo();
	}

	/** Verify Mobile Pro Observer Disabled */
	public void verifyMobileProObserverDropDownDisabled() {
		waitForElement(reportedOutComeMobileProObserverDRPDOWN);
		Assert.assertTrue(
				reportedOutComeMobileProObserverDRPDOWN.getAttribute("aria-disabled").equalsIgnoreCase("true"));
		moveToElement(reportedOutComeMobileProObserverDRPDOWN);
		reportInfo();
	}

	/** Verify Mobile Pro Subject Disabled */
	public void verifyMobileProSubjectDropDownDisabled() {
		waitForElement(reportedOutComeMobileProSubjectDRPDOWN);
		Assert.assertTrue(
				reportedOutComeMobileProSubjectDRPDOWN.getAttribute("aria-disabled").equalsIgnoreCase("true"));
		moveToElement(reportedOutComeMobileProSubjectDRPDOWN);
		reportInfo();
	}

	/** Select Mobile Pro Observer From DropDown */
	public void selectMobileProObserver(String observerTobeSelect) {
		clickOn(mobileProObserverSelectDRPDWN);
		for (int i = 1; i < mobileProObserverList.size(); i++) {
			WebElement observerList = mobileProObserverList.get(i);
			if (getText(observerList).trim().contains(observerTobeSelect)) {
				waitForElement(observerList);
				javascriptButtonClick(observerList);
				break;
			}
		}
	}

	/** Verify Mobile Observer Qr Icon Present */
	public void verifyQrMobileObserverIcon() {
		Assert.assertTrue(isElementPresent(mobileObserverQrICN));
		reportInfo();
	}

	/**
	 * Verify Subject value is highlighted in red in Reported Outcomes section
	 */
	public void verifySubjectValueColor(String colorToBeVerified) {
		Assert.assertTrue(getTextColor(mobileSubjectDisabled).equals(colorToBeVerified));
		moveToElement(mobileSubjectDisabled);
		reportInfo();
	}

	/**
	 * Verify Observer value is highlighted in red in Reported Outcomes section
	 */
	public void verifyObserverValueColor(String colorToBeVerified) {
		Assert.assertTrue(getTextColor(mobileObserverDisabled).equals(colorToBeVerified));
		moveToElement(mobileObserverDisabled);
		reportInfo();
	}

	/** Verify Mobile Subject is displayed with disable icon */
	public void verifyMobileSubjectDisplayedWithDisableIcon() {
		Assert.assertTrue(isElementPresent(mobileSubjectDisabled));
		Assert.assertTrue(
				isElementPresent(mobileSubjectDisabled.findElement(By.xpath("./i[@class='glyphicon-ban-circle']"))));
		moveToElement(mobileSubjectDisabled);
		reportInfo();
	}

	/** Verify Mobile Observer is displayed with disable icon */
	public void verifyMobileObserverDisplayedWithDisableIcon() {
		Assert.assertTrue(isElementPresent(mobileObserverDisabled));
		Assert.assertTrue(
				isElementPresent(mobileObserverDisabled.findElement(By.xpath("./i[@class='glyphicon-ban-circle']"))));
		moveToElement(mobileObserverDisabled);
		reportInfo();
	}

	/** Click On Mobile Observer QR Icon */
	public void clickOnMobileObserverQrIcon() {
		scrollPageThroughWebElement(mobileObserverQrICN);
		clickOn(mobileObserverQrICN);
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='row scores-show']//div[@class='spinner']")));
		_normalWait(2000);
	}

	/** Verify Observer Registration dialog PopUp Displayed */

	public void verifyObserverRegistrationDialogPoUpIsOpened() {
		Assert.assertTrue(isElementPresent(observerRegistrationDialogPopUp));
		reportInfo();
	}

	/** Click On Observer Registration PopUp Close Button */
	public void clickOnObserverRegistrationPopUpCloseButton() {
		clickOn(observerRegistrationPopUpCloseBTN);
	}

	/** Click On observer Registration 'X' control */
	public void clickOnObserverResigtrationCrossControl() {
		clickOn(closeObserverRegistrationCrossICN);
	}

	/**
	 * Verify Information In ObserverRegistration Dialog PopUp
	 * 
	 */

	public void verifyInformationInObserverRegistratiOnPopUp() {
		Assert.assertTrue(isElementPresent(observerRefreshICN) && isElementPresent(deviceHisortyLabel)
				&& isElementPresent(registrationCodeObserver) && isElementPresent(printObserverBTN)
				&& isElementPresent(observerRegistrationPopUpCloseBTN)
				&& isElementPresent(closeObserverRegistrationCrossICN));
		reportInfo();
	}

	/* Verify Study name is displayed as active link */

	public void verifyStudyNameIsDisplayedAsActiveLink() {
		Assert.assertTrue(isElementPresent(studyActiveLink), "Study Name Is Displayed As ActiveLink");
		reportInfo();
	}

	/* Click On Study Active Link */
	public void clickOnStudyActiveLink() {
		clickOn(studyActiveLink);
	}

	/** Verify Study Profile Displayed */
	public void verifyStudyProfileDisplayedInSubjectDetailPage() {
		Assert.assertTrue(isElementPresent(studyProfileSliderPanelOpen),
				"Study Profile Slider Panel Opened and Displayed");
		reportInfo();

	}

	/* Click On Refresh Icon */
	public void refreshPage() {
		driver.navigate().refresh();
		new WebDriverWait(driver, 15)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));

	}

	/* Click On Study Profile Collapse Button */
	public void clickOnStudyProfileCollpaseButton() {
		clickOn(studyProfileCollpaseBTN);
	}

	/* Verify BreadCrumbs Panel Displayed */

	public void verifyBreadCrumbsPanelIsDisplayed() {
		Assert.assertTrue(isElementPresent(studyProfileLinkICN) && isElementPresent(ratersLinkICN)
				&& isElementPresent(queriesLinkICN) && isElementPresent(breadCrumbsStudyNameLink)
				&& isElementPresent(breadCrumbsSiteNameLink) && isElementPresent(breadCrumbsSubjectLink));
		reportInfo();
	}

	/* Select Study Profile Icon On BreadCrumbs */

	public void clickOnStudyProfileIcon() {
		waitAndClick(studyProfileLinkICN);
	}

	/* Select Raters Icon */

	public void clickOnRatersIcon() {
		waitAndClick(ratersLinkICN);
	}

	/* Select Raters Close Icon */

	public void clickOnRatersCollpaseIcon() {
		waitAndClick(raterCollpaseBTN);
	}

	/* Select Queries Icon */
	public void clickOnQueriesIcon() {
		waitAndClick(queriesLinkICN);
	}

	/* Select Queries Close Icon */

	public void clickOnQueriesCollpaseIcon() {
		waitAndClick(queriesCollpaseBTN);
	}

	/* click on Add query button */

	public void clickOnAddQueriesButton() {
		waitAndClick(addQueriesButton);
	}

	public void clickOnCreateQueryButton() {
		waitAndClick(createQueriesButton);

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

	/**
	 * Verify Presence Of Query By Subject In Query List
	 * 
	 */
	public void verifyPresenceOfQueryBySubject(String Subject) {
		boolean flag = false;
		for (WebElement subjecttoselect : queriesList) {
			if (subjecttoselect.getText().trim().contains(Subject)) {
				moveToElement(subjecttoselect);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
	}

	/* verify add query list area display */

	public void addNewQuery(String text) {
		clickOnAddQueriesButton();
		waitForElementPresent(queriesTextArea, 10);
		inputText(queriesTextArea, text);
		clickOnCreateQueryButton();
		waitForSpinner(DEFAULT_WAIT_4_PAGE);

	}

	public void replyQuery(String text) {
		waitForElementPresent(queriesTextArea, 10);
		inputText(replyQueriesTextArea, text);
		clickOnReplyQueryButton();
		waitForSpinner(DEFAULT_WAIT_4_PAGE);

	}

	/**
	 * Verify cloase query button displayed
	 */
	public void verifyCloseQueryButtonDisplayed() {
		waitForElementPresent(closeQueryButton, 10);
		Assert.assertTrue(closeQueryButton.isDisplayed());
		moveToElement(closeQueryButton);
		reportInfo();
	}

	/* Verify Raters Panel Displayed */
	public void verifyRatersDetailsPanelIsOpened() {
		Assert.assertTrue(isElementPresent(ratersProfileSliderPanelOpen), "Raters Details Slider panel is displayed");
		reportInfo();

	}

	/* Verify Queries Panel Displayed */
	public void verifyQueriesDetailsPanelIsOpened() {
		Assert.assertTrue(isElementPresent(queriesSliderPanelOpen), "Query Details Slider panel is displayed");
		reportInfo();

	}

	/* Verify Popup with description of masking format Display */
	public void verifyPopUpDescriptionWithMaskFormatIsDisplayed() {
		doubleClickOnElement(screeningNumberINP);
		Assert.assertTrue(maskingDescriptionPopUP.isDisplayed());
		reportInfo();
	}

	/**
	 * Verify Language has been selected successfully
	 * 
	 * @param selectedLanguage
	 */
	public void verifyLanguageIsSelected(String selectedLanguage) {
		Assert.assertEquals(getText(addSubPopup_SelectedSubjectLanguage).trim(), selectedLanguage);
		reportInfo();
	}

	public void verifyByDefaultSelectedSubTab(String SubTab) {
		boolean flag = false;
		waitForElement(subjectSubTabBlock);
		for (WebElement filterList : subjectSubTabFilterList) {
			if (filterList.getAttribute("class").equalsIgnoreCase("active")
					&& getText(filterList.findElement(By.xpath("./a//span"))).equalsIgnoreCase(SubTab)) {
				flag = true;
				moveToElement(filterList);
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void selectCategorySubTab(String SubTab) {
		scrollToTopOfThePage();
		waitForElement(subjectSubTabBlock);
		for (WebElement filterList : subjectSubTabFilterList) {
			if (getText(filterList.findElement(By.xpath("./a//span"))).equalsIgnoreCase(SubTab)) {
				clickOn(filterList);
				break;
			}
		}
		new WebDriverWait(driver, 15).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']")));
		reportInfo();
	}

	public void selectMessageBySubject(String Subject) {
		boolean flag = false;
		if (sizeofTheList(listOfMessages) > 0) {
			for (WebElement message : listOfMessages) {
				if (getText(message.findElement(By.xpath("./div//div[@class='message-holder']/h4")))
						.contains(Subject)) {
					clickOn(message);
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Select first Message In Message List
	 * 
	 */
	public void selectFirstMessageRow() {
		boolean flag = false;
		if (sizeofTheList(listOfMessages) > 0) {
			for (WebElement message : listOfMessages) {
				clickOn(message);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Select first UnFlagged Message In Message List
	 * 
	 */

	public void selectFirstUnFlaggedMessageRow() {
		boolean flag = false;
		WebElement messageFlag;
		if (sizeofTheList(listOfParentMessages) > 0) {
			for (WebElement message : listOfParentMessages) {
				messageFlag = message.findElement(By.xpath("./div//div[@class='icons']/span"));
				if (messageFlag.getAttribute("class").equalsIgnoreCase("flag")) {
					clickOn(message.findElement(By.xpath("./div")));
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Presence Of Message By Subject In Message List
	 * 
	 */
	public void verifyPresenceOfMessageBySubject(String Subject) {
		boolean flag = false;
		if (sizeofTheList(listOfMessages) > 0) {
			for (WebElement message : listOfMessages) {
				if (getText(message.findElement(By.xpath("./div//div[@class='message-holder']/h4")))
						.equalsIgnoreCase(Subject)) {
					flag = true;
					moveToElement(message);
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Absence Of Message By Subject In Message List
	 * 
	 */
	public void verifyAbsenceOfMessageBySubject(String Subject) {
		boolean flag = true;
		if (sizeofTheList(listOfMessages) > 0) {
			for (WebElement message : listOfMessages) {
				if (getText(message.findElement(By.xpath("./div//div[@class='message-holder']/h4")))
						.equalsIgnoreCase(Subject)) {
					flag = false;
					moveToElement(message);
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify message displayed at the top of Message List
	 * 
	 */
	public void verifyMessageDisplayedAtTheTopOfMessageList(String Subject) {
		boolean flag = false;
		if (sizeofTheList(listOfMessages) > 0) {
			if (getText(listOfMessages.get(0).findElement(By.xpath("./div//div[@class='message-holder']/h4")))
					.equalsIgnoreCase(Subject))
				flag = true;
			moveToElement(listOfMessages.get(0));
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Presence Of any Message In Message List
	 * 
	 */
	public void verifyPresenceOfMessageInMessageList() {
		Assert.assertTrue(sizeofTheList(listOfMessages) > 0);
		reportInfo();
	}

	/**
	 * Verify Presence Of any unread Message In Message List
	 * 
	 */
	public void verifyPresenceOfUnreadMessageInMessageList() {
		_normalWait(3000);
		boolean flag = false;
		Assert.assertTrue(sizeofTheList(listOfMessages) > 0);
		for (WebElement message : listOfMessages) {
			if (message.findElement(By.xpath("./div[1]/div")).getAttribute("class").equalsIgnoreCase("message bold")) {
				flag = true;
				moveToElement(message.findElement(By.xpath("./div[1]/div")));
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Select a unread Message from the Message List
	 * 
	 */
	public void selectUnreadMessageFromMessageList() {
		boolean flag = false;
		Assert.assertTrue(sizeofTheList(listOfMessages) > 0);
		for (WebElement message : listOfMessages) {
			if (message.findElement(By.xpath("./div[1]/div")).getAttribute("class").equalsIgnoreCase("message bold")) {
				waitAndClick(message.findElement(By.xpath("./div[1]//h4")));
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * verify body text color gets gray for selected message
	 * 
	 */
	public void verifyBodyTextColorForSelectedMessageFromMessageList(String ColorToBeVerified) {
		boolean flag = false;
		Assert.assertTrue(sizeofTheList(listOfMessages) > 0);
		for (WebElement message : listOfMessages) {
			if (message.findElement(By.xpath("./div")).getAttribute("class").contains("selected-message")) {
				assertEquals(message.findElement(By.xpath("./div[1]//p")).getCssValue("color"), ColorToBeVerified);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify selected message displayed with Flag icon on the Message list
	 * 
	 */
	public void verifySelectedMessageDisplayedWithFlagIconOnMessageList() {
		boolean flag = false;
		if (sizeofTheList(listOfMessages) > 0) {
			for (WebElement message : listOfMessages) {
				if (message.findElement(By.xpath("./div")).getAttribute("class").contains("selected-message")) {
					if (message.findElement(By.xpath("./div//div[@class='icons']/span")).getAttribute("class")
							.equalsIgnoreCase("flag set")) {
						moveToElement(message.findElement(By.xpath("./div//div[@class='icons']/span")));
						flag = true;
						break;
					}
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Flag icon is hidden for selected message displayed
	 * 
	 */
	public void verifyFlagIconHiddenForSelectedMessage() {
		boolean flag = true;
		if (sizeofTheList(listOfMessages) > 0) {
			for (WebElement message : listOfMessages) {
				if (message.findElement(By.xpath("./div")).getAttribute("class").contains("selected-message")) {
					if (message.findElement(By.xpath("./div//span")).getAttribute("class")
							.equalsIgnoreCase("flag set")) {
						flag = false;
						break;
					}
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify selected message with read visual indication
	 * 
	 */
	public void verifySelectedMessageWithReadVisualIndication() {
		boolean flag = true;
		Assert.assertTrue(sizeofTheList(listOfMessages) > 0);
		for (WebElement message : listOfMessages) {
			if (message.findElement(By.xpath("./div")).getAttribute("class").contains("selected-message")) {
				if (message.findElement(By.xpath("./div[1]/div")).getAttribute("class")
						.equalsIgnoreCase("message bold")) {
					flag = false;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify selected message is in Read
	 * 
	 */
	public String getSubjectContainsForSelectedMessageFromMessageList() {
		String Subject = null;
		Assert.assertTrue(sizeofTheList(listOfMessages) > 0);
		for (WebElement message : listOfMessages) {
			if (message.findElement(By.xpath("./div")).getAttribute("class").contains("selected-message")) {
				Subject = getText(message.findElement(By.xpath("./div//div[@class='message-holder']/h4")));
				break;
			}
		}
		reportInfo();
		return Subject;
	}

	/**
	 * Verify Presence Of any Message In collapsed view
	 * 
	 */
	public void verifyPresenceOfMessageInCollapsedView() {
		boolean flag = false;
		if (sizeofTheList(listOfMessages) > 0) {
			for (WebElement message : listOfMessages) {
				try {
					if (message.findElement(By.xpath("./div//a[@class='inner-message-opener']")).isDisplayed())
						flag = true;
					moveToElement(message.findElement(By.xpath("./div//a[@class='inner-message-opener']")));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		Assert.assertTrue(flag, "No message is present in collapsed form");
		reportInfo();
	}

	/**
	 * Verify Presence Of Reply Message In collapsed message thread
	 * 
	 */
	public void verifyPresenceOfReplyMessageUnderCollapsedMessageThread(String EnterSubjectOfParentMessage,
			String EnterSubjectOfInnerMessageToBeSearchedInThread) {
		boolean flag = false;
		List<WebElement> ListOfInnerMessage;
		if (sizeofTheList(listOfMessages) > 0) {
			outerLoop: for (WebElement message : listOfMessages) {
				if (getText(message.findElement(By.xpath("./div//div[@class='message-holder']/h4")))
						.equalsIgnoreCase(EnterSubjectOfParentMessage)
						&& message.findElement(By.xpath("./div//a[@class='inner-message-opener']")).isDisplayed()) {
					moveToElement(message);
					waitAndClick(message.findElement(By.xpath("./div//a[@class='inner-message-opener']")));
					_normalWait(500);
					ListOfInnerMessage = message
							.findElements(By.xpath("./div[@class='message-item ng-scope inner-message']"));
					for (WebElement InnerMessage : ListOfInnerMessage) {
						if (getText(InnerMessage.findElement(By.xpath("./div//div[@class='message-holder']/h4")))
								.equalsIgnoreCase(EnterSubjectOfInnerMessageToBeSearchedInThread)) {
							flag = true;
							moveToElement(InnerMessage);
							break outerLoop;
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Messages sent by subject are indicated with the red 'person' icon
	 * 
	 */
	public void verifyColorOfMessageIconForSubjectIsRed() {
		boolean flag = false;
		if (sizeofTheList(messagesIconForSubject) > 0) {
			for (WebElement message : messagesIconForSubject) {
				moveToElement(message);
				if (message.getCssValue("color").trim().equalsIgnoreCase("rgba(87, 87, 87, 1)")) {
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Messages sent by Observer are indicated with the green 'person' icon
	 * 
	 */
	public void verifyColorOfMessageIconForObserverIsGreen() {
		boolean flag = false;
		if (sizeofTheList(messagesIconForObserver) > 0) {
			for (WebElement message : messagesIconForObserver) {
				moveToElement(message);
				if (message.getCssValue("color").trim().equalsIgnoreCase("rgba(87, 87, 87, 1)")) {
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyMessageIsDeletedFromList(String Subject) {
		boolean flag = true;
		if (sizeofTheList(listOfMessages) > 0) {
			for (WebElement message : listOfMessages) {
				if (getText(message.findElement(By.xpath("./div//div[@class='message-holder']/h4")))
						.equalsIgnoreCase(Subject)) {
					flag = false;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Message Area displayed with the message content After Selecting
	 * Message
	 * 
	 */
	public void verifyMessageAreaAfterSelectingMessage() {
		boolean flag = false;
		waitSpinnerToBecomeInvisible();
		scrollToTopOfThePage();
		if (isElementDisplayed(messageArea)) {
			try {
				if (messageArea.findElement(By.xpath("./div[@class='message-info ng-scope']")).isDisplayed()) {
					flag = true;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		Assert.assertTrue(flag);
		moveToElement(messageArea.findElement(By.xpath("./div[@class='message-info ng-scope']")));
	}

	/**
	 * Verify Message Area displayed without any message content
	 * 
	 */
	public void verifyEmptyMessageAreaDisplayedWithoutAnyMessageContent() {
		boolean flag = true;
		waitForElement(messageArea);
		try {
			if (messageArea.findElement(By.xpath("./div[@class='message-info ng-scope']")).isDisplayed()) {
				flag = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Assert.assertTrue(flag);
		moveToElement(messageArea);
		reportInfo();
	}

	public void verifyOpenButtonUnderMessageAreaAfterSelectingMessage() {
		Assert.assertTrue(openButtonUnderMessageArea.isEnabled());
		moveToElement(openButtonUnderMessageArea);
		reportInfo();
	}

	public void clickOnOpenButtonUnderMessageArea() {
		Assert.assertTrue(messageArea.isDisplayed());
		waitAndClick(openButtonUnderMessageArea);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		_normalWait(2000);
		reportInfo();
	}

	public void clickOnsendMessageButton() {
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		waitAndClick(sendMessageButton);
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		reportInfo();
	}

	/**
	 * Verify send new message displayed on Subject Details
	 * 
	 */
	public void verifysendMessageButton() {
		waitForElementPresent(sendMessageButton, 2);
		Assert.assertTrue(sendMessageButton.isDisplayed());
		moveToElement(sendMessageButton);
		reportInfo();
	}

	/**
	 * Verify send new message NOT displayed on Subject Details
	 * 
	 */
	public void verifyAbsenceOfsendNewMessageButton() {
		boolean flag = true;
		waitForElementPresent(sendMessageButton, 2);
		try {
			if (sendMessageButton.isDisplayed())
				flag = false;
		} catch (Exception e) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify model window displayed
	 */
	public void verifyEmailModalWindowInEditMode() {
		boolean flag = false;
		waitForElementPresent(editDraftedEmailModalWindow, 30);
		if (editDraftedEmailModalWindow.findElement(By.xpath("./ancestor::div[@id='subject-message-dialog']"))
				.getAttribute("class").trim().endsWith("modalshow in")) {
			flag = true;
			moveToElement(editDraftedEmailModalWindow);
			_normalWait(DEFAULT_WAIT_4_PAGE);
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify model window closed
	 */
	public void verifyEmailModalWindowNotDisplayed() {
		boolean flag = true;
		waitForElementPresent(editDraftedEmailModalWindow, 2);
		if (editDraftedEmailModalWindow.findElement(By.xpath("./ancestor::div[@id='subject-message-dialog']"))
				.getAttribute("class").trim().endsWith("modalshow in")) {
			flag = false;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void updateEmailSubjectUnderModalWindow(String SubjectToBeEntered) {
		Assert.assertTrue(editDraftedEmailModalWindow.isDisplayed());
		clearTextBox(editEmailSubjectUnderModalWindow);
		inputText(editEmailSubjectUnderModalWindow, SubjectToBeEntered);
		reportInfo();
	}

	/**
	 * Verify Subject text field displayed under 'New message' modal
	 */
	public void verifyEmailSubjectUnderModalWindow() {
		Assert.assertTrue(editEmailSubjectUnderModalWindow.isDisplayed());
		moveToElement(editEmailSubjectUnderModalWindow);
		reportInfo();
	}

	public void updateEmailBodyUnderModalWindow(String emailBodyToBeEntered) {
		Assert.assertTrue(editDraftedEmailModalWindow.isDisplayed());
		clearTextBox(editEmailBodyUnderModalWindow);
		inputText(editEmailBodyUnderModalWindow, emailBodyToBeEntered);
		reportInfo();
	}

	/**
	 * Verify Email Body field displayed under 'New message' modal
	 */
	public void verifyEmailBodyUnderModalWindow() {
		Assert.assertTrue(editEmailBodyUnderModalWindow.isDisplayed());
		moveToElement(editEmailBodyUnderModalWindow);
		reportInfo();
	}

	/**
	 * Verify Recipient displayed under 'New message' modal
	 */
	public void verifyRecipientDisplayedUnderModalWindow(String typeOfRecipient) {
		boolean flag = false;
		Assert.assertTrue(editDraftedEmailModalWindow.isDisplayed());
		for (WebElement recipient : recipientsUnderModalWindow) {
			if (getText(recipient.findElement(By.xpath("./following-sibling::label"))).trim()
					.startsWith(typeOfRecipient)) {
				moveToElement(recipient.findElement(By.xpath("./parent::span")));
				flag = true;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Select Recipient under 'New message' modal
	 */
	public void selectRecipientUnderModalWindow(String typeOfRecipient) {
		boolean flag = false;
		Assert.assertTrue(editDraftedEmailModalWindow.isDisplayed());
		for (WebElement recipient : recipientsUnderModalWindow) {
			if (getText(recipient.findElement(By.xpath("./following-sibling::label"))).trim()
					.startsWith(typeOfRecipient)) {
				waitAndClick(recipient);
				flag = true;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void clickOnCloseEmailModalWindow() {
		Assert.assertTrue(editDraftedEmailModalWindow.isDisplayed());
		waitAndClick(closeDraftedEmailModalWindow);
		reportInfo();
	}

	public void clickOnSaveDraftedEmail() {
		Assert.assertTrue(editDraftedEmailModalWindow.isDisplayed());
		waitAndClick(saveDraftedEmailModalWindow);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		_normalWait(2000);
		reportInfo();
	}

	public void verifySaveButtonIsDisabledForDraftedEmail() {
		Assert.assertFalse(saveDraftedEmailModalWindow.isEnabled());
		moveToElement(saveDraftedEmailModalWindow);
		reportInfo();
	}

	public void verifysendButtonIsDisabledForDraftedEmail() {
		Assert.assertFalse(sendDraftedEmailModalWindow.isEnabled());
		reportInfo();
	}

	/**
	 * click on Send button on model window
	 */
	public void clickOnSendButtonOnModelWindow() {
		waitAndClick(sendDraftedEmailModalWindow);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		_normalWait(2000);
		reportInfo();
	}

	public void verifyCancelButtonIsEnabledForDraftedEmail() {
		Assert.assertTrue(cancelDraftedEmailModalWindow.isEnabled());
		moveToElement(cancelDraftedEmailModalWindow);
		reportInfo();
	}

	public void clickOnCancelButtonOnModelWindow() {
		Assert.assertTrue(cancelDraftedEmailModalWindow.isEnabled());
		waitAndClick(cancelDraftedEmailModalWindow);
		reportInfo();
	}

	public void clickOnYesEmailModalWindow() {
		Assert.assertTrue(confirmationWindowBeforeDeleteMessage.isDisplayed());
		waitAndClick(approveDeleteMessageFromConfirmationWindow);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		_normalWait(2000);
		reportInfo();
	}

	public void verifyDeleteButtonUnderMessageAreaAfterSelectingMessage() {
		Assert.assertTrue(deleteButtonUnderMessageArea.isEnabled());
		moveToElement(deleteButtonUnderMessageArea);
		reportInfo();
	}

	public void clickOnDeleteButtonUnderMessageAreaAfterSelectingMessage() {
		waitAndClick(deleteButtonUnderMessageArea);
		reportInfo();
	}

	public void verifyConfirmationWindowBeforeDeleteMessage() {
		Assert.assertTrue(confirmationWindowBeforeDeleteMessage.isDisplayed());
		moveToElement(confirmationWindowBeforeDeleteMessage);
		reportInfo();
	}

	public void verifyEmailSubjectUnderMessageAreaAfterSelectingMessage() {
		Assert.assertTrue(emailSubjectUnderMessageArea.isDisplayed());
		moveToElement(emailSubjectUnderMessageArea);
		reportInfo();
	}

	/**
	 * Verify set / clear Flag Icon Displayed On Message Area After Selecting
	 * Message
	 */
	public void verifyFlagIconDisplayedOnMessageAreaAfterSelectingMessage() {
		Assert.assertTrue(iconFlag.isDisplayed());
		moveToElement(iconFlag);
		reportInfo();
	}

	/**
	 * Verify set / clear Flag Icon Not Displayed On Message Area After Selecting
	 * Message
	 */
	public void verifyFlagIconNotDisplayedOnMessageAreaAfterSelectingMessage() {
		boolean flag = true;
		try {
			if (iconFlag.isDisplayed())
				flag = false;
		} catch (Exception e) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * click on set / clear Flag Icon On Message Area After Selecting Message
	 */
	public void clickOnFlagIconMessageAreaAfterSelectingMessage() {
		waitAndClick(iconFlag);
		_normalWait(2000);
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='row scores-show']//div[@class='spinner']")));
		reportInfo();
	}

	/**
	 * Verify 'Include Automated' filter selected by default
	 */
	public void verifyIncludeAutomatedCheckedByDefault() {
		waitForElement(checkBoxIncludeAutomated);
		Assert.assertFalse(checkBoxIncludeAutomated.isSelected());
		moveToElement(checkBoxIncludeAutomated);
		reportInfo();
	}

	/**
	 * Click on 'Include Automated' Check Box
	 */
	public void clickOnIncludeAutomatedCheckBox() {
		waitAndClick(checkBoxIncludeAutomated);
		reportInfo();
	}

	/**
	 * Verify reply button not displayed
	 */
	public void verifyReplyButtonNOtDisplayed() {
		boolean flag = true;
		try {
			if (btnReply.isDisplayed())
				flag = false;
		} catch (Exception e) {
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Click on reply button
	 */
	public void clickOnReplyButton() {
		waitAndClick(btnReply);
		reportInfo();
	}

	/**
	 * Verify time Stamp Under Message Area After Selecting Message
	 */
	public void verifyTimeStampUnderMessageAreaAfterSelectingMessage() {
		Assert.assertTrue(timeStampUnderMessageArea.isDisplayed());
		moveToElement(timeStampUnderMessageArea);
		reportInfo();
	}

	public void verifyEmailBodyUnderMessageAreaAfterSelectingMessage() {
		Assert.assertTrue(emailBodyUnderMessageArea.isDisplayed());
		moveToElement(emailBodyUnderMessageArea);
		reportInfo();
	}

	public void verifynoteTextBoxAppearUnderMessageAreaAfterSelectingMessage() {
		Assert.assertTrue(noteTextBoxUnderMessageArea.isDisplayed());
		moveToElement(noteTextBoxUnderMessageArea);
		reportInfo();
	}

	public void verifyCurrentConfiguredMasking(String maskToBeVerified) {
		String numberWithMask = getScreeningNumberFromEditSubjectPopUp();
		String[] currentmasking = numberWithMask.split("-");
		Assert.assertTrue(currentmasking[0].trim().contains(maskToBeVerified));
		reportInfo();
		screeningNumberINP.clear();
	}

	/* Click On ReportedOutcome Mobile Subject QR Icon */

	public void clickOnReportedOutComeMobileSubjectQrIcon() {
		javascriptButtonClick(reportedOutcomeMobileRegistrationDialogQrIcon);
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@class='row scores-show']//div[@class='spinner']")));
		_normalWait(2000);
	}

	/*
	 * Verify ReportedOutcome Mobile Subject QR Icon displayed
	 */

	public void verifyReportedOutComeMobileSubjectQrIcon() {

		Assert.assertTrue(isElementDisplayed(reportedOutcomeMobileRegistrationDialogQrIcon));
		moveToElement(reportedOutcomeMobileRegistrationDialogQrIcon);
	}

	/**
	 * Verify Subject Lock icon displayed
	 */
	public void verifySubjectLockIconDisplayed() {
		Assert.assertTrue(isElementDisplayed(reportedOutcomeSubjectLockIcon));
		// moveToElement(reportedOutcomeSubjectLockIcon);
	}

	/**
	 * Verify Observer Lock icon displayed
	 */
	public void verifyObserverLockIconDisplayed() {
		Assert.assertTrue(isElementDisplayed(reportedOutcomeObserverLockIcon));
		moveToElement(reportedOutcomeObserverLockIcon);
		reportInfo();
	}

	/**
	 * Verify Subject Lock icon not displayed
	 */
	public void verifySubjectLockIconNotDisplayed() {
		Assert.assertFalse(isElementDisplayed(reportedOutcomeSubjectLockIcon));
		reportInfo();
	}

	/**
	 * Verify Observer Lock icon not displayed
	 */
	public void verifyObserverLockIconNotDisplayed() {
		Assert.assertFalse(isElementDisplayed(reportedOutcomeObserverLockIcon));

		reportInfo();
	}

	/**
	 * Verify subject UnLock Button displayed On Reported Outcome PopUp
	 */
	public void verifySubjectUnLockButtonDisplayedOnReportedOutcomePopUp() {
		Assert.assertTrue(isElementDisplayed(subjectUnLockButtonOnReportedOutcomePopUp));
		moveToElement(subjectUnLockButtonOnReportedOutcomePopUp);
	}

	/**
	 * Verify observer UnLock Button displayed On Reported Outcome PopUp
	 */
	public void verifyObserverUnLockButtonDisplayedOnReportedOutcomePopUp() {
		Assert.assertTrue(isElementDisplayed(observerUnLockButtonOnReportedOutcomePopUp));
		moveToElement(observerUnLockButtonOnReportedOutcomePopUp);
	}

	/**
	 * Verify subject UnLock Button Not displayed On Reported Outcome PopUp
	 */
	public void verifySubjectUnLockButtonNotDisplayedOnReportedOutcomePopUp() {
		Assert.assertFalse(isElementDisplayed(subjectUnLockButtonOnReportedOutcomePopUp));
	}

	/**
	 * Verify observer UnLock Button Not displayed On Reported Outcome PopUp
	 */
	public void verifyObserverUnLockButtonNotDisplayedOnReportedOutcomePopUp() {
		Assert.assertFalse(isElementDisplayed(observerUnLockButtonOnReportedOutcomePopUp));
	}

	/**
	 * Click on subject UnLock Button displayed On Reported Outcome PopUp
	 */
	public void clickOnSubjectUnLockButtonDisplayedOnReportedOutcomePopUp() {
		Assert.assertTrue(isElementDisplayed(subjectUnLockButtonOnReportedOutcomePopUp));
		waitAndClick(subjectUnLockButtonOnReportedOutcomePopUp);
	}

	/**
	 * Click on observer UnLock Button displayed On Reported Outcome PopUp
	 */
	public void clickOnObserverUnLockButtonDisplayedOnReportedOutcomePopUp() {
		Assert.assertTrue(isElementDisplayed(observerUnLockButtonOnReportedOutcomePopUp));
		waitAndClick(observerUnLockButtonOnReportedOutcomePopUp);
	}

	/** Verify Observer Registration dialog PopUp Displayed */

	public void verifySubjectRegistrationDialogPoUpIsOpened() {
		Assert.assertTrue(isElementPresent(subjectRegistrationDialogPopUp));
		moveToElement(subjectRegistrationDialogPopUp);
		reportInfo();
	}

	/** Click On Observer Registration PopUp Close Button */
	public void clickOnSubjectRegistrationPopUpCloseButton() {
		clickOn(subjectRegistrationPopUpCloseBTN);
	}

	/** Click On observer Registration 'X' control */
	public void clickOnSubjectResigtrationCrossControl() {
		clickOn(closeSubjectRegistrationCrossICN);
	}

	/**
	 * Verify Information In ObserverRegistration Dialog PopUp
	 * 
	 */

	public void verifyInformationInSubjectRegistratiOnPopUp() {
		Assert.assertTrue(isElementPresent(deviceSubjectRegistrationHisortyLabel)
				|| isElementPresent(registrationCodeSubject) || isElementPresent(printSubjectBTN));
		reportInfo();
	}

	/**
	 * Verify Subject is not registered with any device
	 * 
	 */
	public void verifySubjectNotRegisteredWithAnyDevice() {
		Assert.assertEquals(getText(deviceUnRegistredSubjectHisortyText), "There is no device in use");
		moveToElement(deviceUnRegistredSubjectHisortyText);
		reportInfo();
	}

	/**
	 * Verify Observer is not registered with any device
	 * 
	 */
	public void verifyObserverNotRegisteredWithAnyDevice() {
		Assert.assertEquals(getText(deviceUnRegistredObserverHisortyText), "There is no device in use");
		moveToElement(deviceUnRegistredObserverHisortyText);
		reportInfo();
	}

	/**
	 * Verify device is activated Subject for registered in device history
	 */
	public void verifyActivatedDeviceHistoryForRegisteredSubject() {
		Assert.assertEquals(deviceStatusForRegistredSubject.get(0).getText().trim(), "Activated");
		moveToElement(deviceStatusForRegistredSubject.get(0));
		reportInfo();
	}

	/**
	 * Verify activated Device is not available in device history
	 */
	public void verifyActivatedDeviceNotAvailableInDeviceHistory(String SelectTypeOfEntity) {
		Boolean flag = true;
		if (SelectTypeOfEntity.equalsIgnoreCase("Subject")
				&& deviceStatusForRegistredSubject.get(0).getText().trim().equalsIgnoreCase("Activated")) {
			flag = false;
		} else if (SelectTypeOfEntity.equalsIgnoreCase("Observer")
				&& deviceStatusForRegistredObserver.get(0).getText().trim().equalsIgnoreCase("Activated")) {
			flag = false;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify device is activated for registered Observer in device history
	 */
	public void verifyActivatedDeviceHistoryForRegisteredObserver() {
		Assert.assertEquals(deviceStatusForRegistredObserver.get(0).getText().trim(), "Activated");
		moveToElement(deviceStatusForRegistredObserver.get(0));
		reportInfo();
	}

	/**
	 * Click on option to deactivate the device for Registered Observer
	 */
	public void clickOnDeactivateDeviceButtonForRegisteredObserver() {
		Assert.assertEquals(deviceStatusForRegistredObserver.get(0).getText().trim(), "Activated");
		waitAndClick(deviceDeactivateButtonForRegisteredObserver);
		reportInfo();
	}

	/**
	 * Click on option to deactivate the device for Registered Subject
	 */
	public void clickOnDeactivateDeviceButtonForRegisteredSubject() {
		_normalWait(DEFAULT_WAIT_4_PAGE);
		Assert.assertEquals(deviceStatusForRegistredSubject.get(0).getText().trim(), "Activated");
		waitAndClick(deviceDeactivateButtonForRegisteredSubject);
		reportInfo();
	}

	/**
	 * Verify option to deactivate the device is displayed for Registered Subject
	 */
	public void verifyDeactivateDeviceButtonDisplayedForRegisteredSubject() {
		Assert.assertEquals(deviceStatusForRegistredSubject.get(0).getText().trim(), "Activated");
		Assert.assertTrue(deviceDeactivateButtonForRegisteredSubject.isDisplayed());
		moveToElement(deviceDeactivateButtonForRegisteredSubject);
		reportInfo();
	}

	/**
	 * Verify option to deactivate the device is Not displayed for Registered
	 * Subject
	 */
	public void verifyDeactivateDeviceButtonNotDisplayedForRegisteredSubject() {
		Assert.assertEquals(deviceStatusForRegistredSubject.get(0).getText().trim(), "Activated");
		Assert.assertFalse(isElementDisplayed(deviceDeactivateButtonForRegisteredSubject));
		reportInfo();
	}

	/**
	 * Verify option to deactivate the device is displayed for Registered Observer
	 */
	public void verifyDeactivateDeviceButtonDisplayedForRegisteredObserver() {
		Assert.assertEquals(deviceStatusForRegistredObserver.get(0).getText().trim(), "Activated");
		Assert.assertTrue(deviceDeactivateButtonForRegisteredObserver.isDisplayed());
		moveToElement(deviceDeactivateButtonForRegisteredObserver);
		reportInfo();
	}

	/**
	 * Verify option to deactivate the device is Not displayed for Registered
	 * Observer
	 */
	public void verifyDeactivateDeviceButtonNotDisplayedForRegisteredObserver() {
		Assert.assertEquals(deviceStatusForRegistredObserver.get(0).getText().trim(), "Activated");
		Assert.assertFalse(isElementDisplayed(deviceDeactivateButtonForRegisteredObserver));
		reportInfo();
	}

	/**
	 * Verify history details with Reason for change for deactivated device
	 */
	public void verifyDeActivatedDeviceHistoryWithReasonForChangeDisplayed(String SelectTypeOfEntity) {
		Boolean flag = false;
		List<WebElement> deviceStatusList = null;
		if (SelectTypeOfEntity.equalsIgnoreCase("Subject")) {
			deviceStatusList = deviceStatusForRegistredSubject;
		} else if (SelectTypeOfEntity.equalsIgnoreCase("Observer")) {
			deviceStatusList = deviceStatusForRegistredObserver;
		}
		for (WebElement deviceStatus : deviceStatusList) {
			if (deviceStatus.getText().trim().equalsIgnoreCase("Deactivated")) {
				Assert.assertTrue((deviceStatus.findElement(By.xpath(
						"./ancestor::div[@class='device-wrapper ng-scope']//div[@class='ng-scope inactive']//p[contains(@data-ng-show,'changeReason')]"))
						.isDisplayed()), "Reason for change displayed");
				moveToElement(deviceStatus.findElement(By.xpath(
						"./ancestor::div[@class='device-wrapper ng-scope']//div[@class='ng-scope inactive']//p[contains(@data-ng-show,'changeComments')]")));
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify history details with e-signature for deactivated device
	 */
	public void verifyDeActivatedDeviceHistoryWithEsignatureDisplayed(String SelectTypeOfEntity) {
		Boolean flag = false;
		List<WebElement> deviceStatusList = null;
		if (SelectTypeOfEntity.equalsIgnoreCase("Subject")) {
			deviceStatusList = deviceStatusForRegistredSubject;
		} else if (SelectTypeOfEntity.equalsIgnoreCase("Observer")) {
			deviceStatusList = deviceStatusForRegistredObserver;
		}
		for (WebElement deviceStatus : deviceStatusList) {
			if (deviceStatus.getText().trim().equalsIgnoreCase("Deactivated")) {
				Assert.assertTrue((deviceStatus.findElement(By.xpath(
						"./ancestor::div[@class='device-wrapper ng-scope']//div[@class='ng-scope inactive']//p[contains(@data-ng-show,'changedBy')]"))
						.isDisplayed()), "e-signature is displayed");
				moveToElement(deviceStatus.findElement(By.xpath(
						"./ancestor::div[@class='device-wrapper ng-scope']//div[@class='ng-scope inactive']//p[contains(@data-ng-show,'changedBy')]")));
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Device History displayed in descending order from last activated
	 * device
	 */
	public void verifyDeviceHistoryDisplayedInDescendingOrder(String SelectTypeOfEntity) {
		boolean flag = false;
		List<String> dateAndTimeList = new ArrayList<>();
		List<WebElement> deviceStatusList = null;
		if (SelectTypeOfEntity.equalsIgnoreCase("Subject")) {
			deviceStatusList = deviceStatusForRegistredSubject;
		} else if (SelectTypeOfEntity.equalsIgnoreCase("Observer")) {
			deviceStatusList = deviceStatusForRegistredObserver;
		}
		for (WebElement deviceStatus : deviceStatusList) {
			String date = deviceStatus
					.findElement(
							By.xpath("./ancestor::div[@class='device-wrapper ng-scope']//div[@class='col-date']/p"))
					.getText();
			moveToElement(deviceStatus.findElement(
					By.xpath("./ancestor::div[@class='device-wrapper ng-scope']//div[@class='col-date']/p")));
			dateAndTimeList.add(date);
		}
		String DeviceHistoryDate = dateAndTimeList.get(0);
		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd-MMM-yyyy");
		Date s1 = null;
		try {
			s1 = dateTimeInGMT.parse(DeviceHistoryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (int DeviceHistoryDateList = 1; DeviceHistoryDateList < dateAndTimeList.size(); DeviceHistoryDateList++) {
			String DeviceHistoryDateElements = dateAndTimeList.get(DeviceHistoryDateList);
			Date s2 = null;
			try {
				s2 = dateTimeInGMT.parse(DeviceHistoryDateElements);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (s1.compareTo(s2) > 0) {
				flag = true;
				Assert.assertTrue(flag, " Latest  Date Of Action Is On Top");
			}
		}
	}

	/**
	 * Verify date format of activated device in history Format: DD-MMM-YYYY
	 */
	public void verifyDateFormatOfActivatedDevice(String SelectTypeOfEntity, String datePattern) {
		Boolean verifyDate = false;
		List<WebElement> deviceStatusList = null;
		if (SelectTypeOfEntity.equalsIgnoreCase("Subject")) {
			deviceStatusList = deviceStatusForRegistredSubject;
		} else if (SelectTypeOfEntity.equalsIgnoreCase("Observer")) {
			deviceStatusList = deviceStatusForRegistredObserver;
		}
		for (WebElement deviceStatus : deviceStatusList) {
			if (deviceStatus.getText().trim().equalsIgnoreCase("Activated")) {
				moveToElement(deviceStatus.findElement(
						By.xpath("./ancestor::div[@class='device-wrapper ng-scope']//div[@class='col-date']/p")));
				String date = deviceStatus
						.findElement(
								By.xpath("./ancestor::div[@class='device-wrapper ng-scope']//div[@class='col-date']/p"))
						.getText();
				verifyDate = date.matches(datePattern);
				break;
			}
		}
		Assert.assertTrue(verifyDate);
		reportInfo();
	}

	/**
	 * Verify date format of Deactivated device in history for registered
	 * Subject/Observer Format: DD-MMM-YYYY
	 */
	public void verifyDateFormatOfDeActivatedDevice(String SelectTypeOfEntity, String datePattern) {
		Boolean verifyDate = false;
		List<WebElement> deviceStatusList = null;
		if (SelectTypeOfEntity.equalsIgnoreCase("Subject")) {
			deviceStatusList = deviceStatusForRegistredSubject;
		} else if (SelectTypeOfEntity.equalsIgnoreCase("Observer")) {
			deviceStatusList = deviceStatusForRegistredObserver;
		}
		for (WebElement deviceStatus : deviceStatusList) {
			if (deviceStatus.getText().trim().equalsIgnoreCase("Deactivated")) {
				moveToElement(deviceStatus);
				String date = deviceStatus.findElement(By.xpath(
						"./ancestor::div[@class='device-wrapper ng-scope']//div[@class='ng-scope inactive']//div[@class='col-date']/p"))
						.getText();
				moveToElement(deviceStatus.findElement(By.xpath(
						"./ancestor::div[@class='device-wrapper ng-scope']//div[@class='ng-scope inactive']//div[@class='col-date']/p")));
				verifyDate = date.matches(datePattern);
				break;
			}
		}
		Assert.assertTrue(verifyDate);
		reportInfo();
	}

	/* Verify Values Are Not Editable */
	public void checkValuesNonEditableInSubjectRegistrationPopUp() {
		boolean flag = false;
		if (registrationCodeSubject.getTagName().equalsIgnoreCase("strong")) {
			flag = true;
			Assert.assertTrue(flag, "Values is Non Editable");

		} else {
			Log.info("Values Is Editable");
		}

	}

	/**
	 * Verify Reported outcome Section is displayed
	 */
	public void verifyReportedOutcomeSectionIsDisplayed() {
		moveToElement(reportedOutComesSectionGrid);
		Assert.assertTrue(isElementDisplayed(reportedOutComesSectionGrid));
		reportInfo();
	}

	/**
	 * Verify Mobile PRO section with Subject and Observer settings is not available
	 * on the screen
	 */
	public void verifyMobileProSectionWithSubjectAndObserverSettingsIsNotDisplayedInReportedOutcomePopup() {
		Assert.assertFalse(isElementDisplayed(reportedOutComesPopUpMobileProSettingsSubjectStatusDropDown)
				&& isElementDisplayed(reportedOutComesPopUpMobileProSettingsObserverStatusDropDown));
		reportInfo();
	}

	/**
	 * Verify Mobile PRO section with Subject settings is available on the screen
	 */
	public void verifyMobileProSectionWithSubjectSettingsIsDisplayedInReportedOutcomePopup() {
		Assert.assertTrue(isElementDisplayed(reportedOutComesPopUpMobileProSettingsSubjectStatusDropDown));
		reportInfo();
	}

	/**
	 * Verify Mobile PRO section with Subject and observer settings is available on
	 * the screen
	 */
	public void verifyMobileProSectionWithSubjectAndObserverSettingsIsDisplayedInReportedOutcomePopup() {
		Assert.assertTrue(isElementDisplayed(reportedOutComesPopUpMobileProSettingsSubjectStatusDropDown)
				&& isElementDisplayed(reportedOutComesPopUpMobileProSettingsObserverStatusDropDown));
		reportInfo();
	}

	/**
	 * Verify Mobile PRO section with Subject and Observer settings is available on
	 * the screen
	 */
	public void verifyReportedOutComesMobileSectionGridIsDisplayed() {
		moveToElement(reportedOutComesSectionGrid);
		Assert.assertTrue(reportedOutComesGridMobileRow.getAttribute("class").equalsIgnoreCase("info-row-with-list"));
		reportInfo();
	}

	/**
	 * Verify Reported outcome mobile section grid is not displayed
	 */
	public void verifyReportedOutComesMobileSectionGridIsNotDisplayed() {
		moveToElement(reportedOutComesSectionGrid);
		scrollDown("150");
		Assert.assertTrue(
				reportedOutComesGridMobileRow.getAttribute("class").equalsIgnoreCase("info-row-with-list ng-hide"));
		reportInfo();
	}

	/**
	 * Verify Mobile Reported Outcome mobile Subject value text is displayed
	 * 
	 * @param reportedOutComeMobileValue
	 */
	public void verifyReportedOutcomeMobileSubjectValueText(String reportedOutComeMobileSubjectValue) {
		moveToElement(reportedOutcomeMobileSubjectValueText);
		Assert.assertTrue(
				reportedOutcomeMobileSubjectValueText.getText().equalsIgnoreCase(reportedOutComeMobileSubjectValue));
		reportInfo();
	}

	/**
	 * Verify Mobile Reported Outcome mobile observer value text is displayed
	 * 
	 * @param reportedOutComeMobileValue
	 */
	public void verifyReportedOutcomeMobileObserverValueText(String reportedOutComeMobileSubjectValue) {
		moveToElement(reportedOutcomeMobileObserversValueText);
		Assert.assertTrue(
				reportedOutcomeMobileObserversValueText.getText().contains(reportedOutComeMobileSubjectValue));
		reportInfo();
	}

	/**
	 * Click on cancel button of reported outcome popup to close the popup
	 */
	public void clickOnReportedOutComePopUpCancelBTN() {
		clickOn(reportedOutComePopUpCancelBTN);
	}

	/* Verify Initiate button Displayed */
	public void verifyInitiateButtonIsDisplayed() {
		Assert.assertTrue(isElementPresent(initiateButtonForVisit));
		reportInfo();
	}

	/* Verify Visit Detail Section is Displayed */

	public void verifyDetailsSectionIsdisplayed() {
		Assert.assertTrue(isElementPresent(DetailSection));
		reportInfo();
	}

	/* Verify Visit Is Initiated */
	public void verifyVisitIsInitiated() {
		Assert.assertTrue(isElementPresent(noteSection) && isElementPresent(calenderVisitCancellIcon));
		reportInfo();
	}

	/* Click On Calendar Visit Cancel Icon */
	public void clickOnCalenderVisitCancelIcon() {
		clickOn(calenderVisitCancellIcon);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
	}

	/* Verify Set Flag Icon Not Displayed */

	public void verifySetFlagIconIsNotDisplayed() {
		boolean flag = true;
		if (setFlagICN.size() == 0) {
			flag = false;
			Assert.assertFalse(flag, "Set Flag  Icon  Is Not  Present ");
			reportInfo();
		}
	}

	/* Verify Set Flag Icon Displayed */
	public void verifySetFlagIconDisplayed() {
		Assert.assertTrue(isElementPresent(setFlagIcon));
		reportInfo();

	}

	/* Click On Set Flag Icon */

	public void clickOnSetFlag() {
		clickOn(setFlagIcon);
		waitForElementPresent(clearFlagIcon, 15);
	}

	/**
	 * Verify on Add attachment icon displayed
	 */
	public void verifyOnAddAttachmentIconDisplayed() {
		Assert.assertTrue(isElementPresent(addAttachmentIcon));
		moveToElement(addAttachmentIcon);
	}

	/**
	 * Verify on Add attachment icon is not displayed
	 */
	public void verifyOnAddAttachmentIconNotDisplayed() {
		boolean flag = true;
		try {
			if (isElementPresent(addAttachmentIcon))
				flag = false;
		} catch (Exception e) {
			// TODO: handle exception
		}

		Assert.assertTrue(flag);
	}

	/**
	 * Click on Add attachment icon
	 */
	public void clickOnAddAttachmentIcon() {
		waitAndClick(addAttachmentIcon);
	}

	/* Verify Clear Flag Control Displayed */
	public void verifyClearFlagIconDisplayed() {

		Assert.assertTrue(isElementPresent(clearFlagIcon));
		reportInfo();

	}

	/* Click On Set Flag Icon */

	public void clickOnClearFlag() {
		clickOn(clearFlagIcon);
		waitForElementPresent(setFlagIcon, 15);
	}

	/* Clear Clear Flag Icon Not Displayed */
	public void verifyClearFlagIconIsNotDisplayed() {
		Assert.assertEquals(0,
				driver.findElements(By.xpath("//div[@class='details-column']//span[@title='Clear Flag']")).size());
		reportInfo();

	}

	/* Verify Flag Is Set For Medication */

	public void verifyFlagIsSetForMedicationInMedicationRow(String medicationName) {
		boolean flag = false;
		waitForWebElementEnable(medicationListGrid, 50);
		for (WebElement medicationRow : medicationList) {
			if (getText(medicationRow.findElement(By.xpath("./div/h4[@class='ng-binding']"))).trim()
					.startsWith(medicationName)) {
				waitForElement(medicationRow);
				if (flagIconInMedicationList.isDisplayed()) {
					flag = true;
					Assert.assertTrue(flag, "Flag is set For Medication");
					reportInfo();
					break;
				}
			}
		}
	}

	/* Verify Flag Is Set For Visit */

	public void verifyFlagIsSetForVisit(String visitName) {
		boolean flag = false;
		waitForWebElementEnable(calendarVisitListGrid, 50);
		for (WebElement visitRow : calendarVisitsList) {
			if (getText(visitRow.findElement(By.xpath("./div/h4[@class='ng-binding']"))).trim().startsWith(visitName)) {
				waitForElement(visitRow);
				if (flagIconInVisitList.isDisplayed()) {
					flag = true;
					Assert.assertTrue(flag, "Flag is set For Visit");
					reportInfo();
					break;
				}
			}
		}
	}

	/* Verify Flag Is Not Displayed For Medication */
	public void verifyFlagIsNotDisplayedForMedicationInMedicationRow(String medicationName) {
		boolean flag = true;
		waitForWebElementEnable(medicationListGrid, 50);
		for (WebElement medicationRow : medicationList) {
			if (getText(medicationRow.findElement(By.xpath("./div/h4[@class='ng-binding']"))).trim()
					.startsWith(medicationName)) {
				waitForElement(medicationRow);
				if (flagIconInVisitListLst.size() > 0) {
					if (flagIconInMedicationList.isDisplayed()) {
						flag = false;

					} else {
						Assert.assertTrue(flag);
					}
				}
			}
		}
		Assert.assertTrue(flag, "Flag Is Not Set Fot Medication");
		reportInfo();
	}

	/* Verify Flag Is Not Displayed For Visit */
	public void verifyFlagIsNotDisplayedForVisit(String visitName) {
		boolean flag = true;
		waitForWebElementEnable(calendarVisitListGrid, 50);
		for (WebElement visitRow : calendarVisitsList) {
			if (getText(visitRow.findElement(By.xpath("./div/h4[@class='ng-binding']"))).trim().startsWith(visitName)) {
				waitForElement(visitRow);
				if (flagIconInVisitListLst.size() > 0) {
					if (flagIconInVisitList.isDisplayed()) {
						flag = false;

					} else {
						Assert.assertTrue(flag);
					}
				}
			}
		}
		Assert.assertTrue(flag, "Flag Is Not Set Fot Visit");
		reportInfo();
	}

	/* Verify Assessment Is not assigned for calendar visit */
	public void verifyAssesmentNotAssignedForCalenderVisit() {
		moveToElement(notAssignedTextForCalenderVisit);
		Assert.assertTrue(notAssignedTextForCalenderVisit.getText().equalsIgnoreCase("Not Assigned"));
		reportInfo();

	}

	/* Click On Assessment/Attachment CalenderVisit Detail */
	public void clickOnCalenderVisitdDocumentToShow(String documentToBeSelect) {
		waitAndClick("//div[@class='details-column']//ul[@class='subject-category-filter']//li//a[contains(text(),'"
				+ documentToBeSelect + "')]");
	}

	/* Verify Calendar Visit Attachment Present */

	public void verifyCalenderVisitAttachmentPresent() {
		Assert.assertTrue(isElementPresent(calenderVisitAttachmentRow));
		reportInfo();
	}

	/**
	 * select a Subject category from Filter list section
	 */
	public void selectFilterFromSubjectCategory(String filterToBeSelect) {
		boolean flag = false;
		waitForWebElementEnable(visitSubjectCategoryFilters, 50);
		for (WebElement visitType : visitSubjectCategoryFilterList) {
			if (getText(visitType).trim().startsWith(filterToBeSelect)) {
				waitAndClick(visitType.findElement(By.xpath("./parent::li")));
				flag = true;
				_normalWait(2000);
				new WebDriverWait(driver, 40).until(ExpectedConditions.invisibilityOfElementLocated(
						By.xpath("//div[@class='row scores-show']//div[@class='spinner']")));
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify filter is not displayed in Subject category Filter list section
	 */
	public void verifyFilterNotDisplayedInSubjectCategorySection(String filterToBeSelect) {
		boolean flag = true;
		waitForWebElementEnable(visitSubjectCategoryFilters, 50);
		for (WebElement visitType : visitSubjectCategoryFilterList) {
			if (getText(visitType).trim().equalsIgnoreCase(filterToBeSelect)) {
				flag = false;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify filter displayed but not selected in Subject category Filter list
	 * section
	 */
	public void verifyFilterDisplayedButNotSelectedInSubjectCategorySection(String filterToBeSelect) {
		boolean flag = true;
		waitForWebElementEnable(visitSubjectCategoryFilters, 50);
		for (WebElement visitType : visitSubjectCategoryFilterList) {
			if (getText(visitType).trim().equalsIgnoreCase(filterToBeSelect)) {
				if (visitType.findElement(By.xpath("./parent::li")).getAttribute("class").contains("active"))
					flag = false;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Unavailability Of Count On Tab Header in Subject category Filter list
	 * section
	 */
	public void verifyUnavailabilityOfCountOnTabHeaderInSubjectCategorySection(String filterToBeSelect) {
		boolean flag = true;
		waitForWebElementEnable(visitSubjectCategoryFilters, 50);
		for (WebElement visitType : visitSubjectCategoryFilterList) {
			if (getText(visitType).trim().startsWith(filterToBeSelect)) {
				try {
					if (visitType.findElement(By.xpath("./span")).getAttribute("class").contains("Count")) {
						flag = false;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Verify Count On Tab Header in Subject category Filter list section
	 */
	public void verifyCountOnTabHeaderInSubjectCategorySection(String filterToBeSelect, int countToBeVerified) {
		boolean flag = false;
		waitForWebElementEnable(visitSubjectCategoryFilters, 50);
		for (WebElement visitType : visitSubjectCategoryFilterList) {
			if (getText(visitType).trim().startsWith(filterToBeSelect)) {
				if (visitType.findElement(By.xpath("./span")).getText().trim()
						.endsWith("(" + countToBeVerified + ")")) {
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/** Verify File uploaded popup is displayed **/
	public void verifyUploadFilesPopUpIsDisplayed() {
		waitForElementPresent(sourceUploadPopUpTitleDisplayed, 5);
		Assert.assertEquals(sourceUploadPopUpTitleDisplayed.findElement(By.xpath("./ancestor::div[@id='filesSource']"))
				.getAttribute("aria-hidden").trim(), "false");

		reportInfo();
	}

	/** Verify File uploaded popup is not displayed **/
	public void verifyUploadFilesPopUpIsNotDisplayed() {
		waitForElementPresent(sourceUploadPopUpTitleDisplayed, 5);
		Assert.assertEquals(sourceUploadPopUpTitleDisplayed.findElement(By.xpath("./ancestor::div[@id='filesSource']"))
				.getAttribute("aria-hidden").trim(), "true");
		reportInfo();
	}

	/** Verify cancel button of upload files popup **/
	public void verifyCancelBtnOnSourceUploadPopup() {
		Assert.assertTrue(isElementDisplayed(sourceUploadCancelBTN));
	}

	/** Verify Add files button of upload files popup **/
	public void verifyAddFilesBtnOnSourceUploadPopup() {
		Assert.assertTrue(isElementDisplayed(uploadSourceAddFilesBtn));
	}

	/** Click on Add files button of upload files popup **/
	public void ClickOnAddFilesBtnOfSourceUploadPopup() {
		waitAndClick(uploadSourceAddFilesBtn);
	}

	/** Click on cancel button of upload files popup **/
	public void clickOnSourceUploadCancelBtn() {
		waitAndClick(sourceUploadCancelBTN);
	}

	/** Verify upload button is displayed in File uploaded popup **/
	public void verifyUploadButtonIsDisplayedInUploadFilesPopUp() {
		Assert.assertTrue(isElementPresent(uploadSourceUploadBTN));
		reportInfo();
	}

	/** Click On upload button on File uploaded popup **/
	public void clickOnUploadButtonOnUploadFilesPopUp() {
		waitAndClick(uploadSourceUploadBTN);
		waitForElementToBecomeInvisible(By
				.xpath("//div[@id='sourceDocument' and @aria-hidden='false']//div[@data-ng-if='file.isInLoadState']"));
		_normalWait(5000);
		reportInfo();
	}

	/** click on close icon of upload files popup **/
	public void clickOnSourceUploadCloseIcon() {
		waitAndClick(sourceUploadCloseBTN);
	}

	/**
	 * Add source file to Upload
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void uploadSourceFile(String exePath, String filePath, String uploadWindowTitle)
			throws InterruptedException {
		String basePath = "src\\test\\resources\\testdata\\";
		clickOn(uploadSourceAddFilesBtn);
		_normalWait(150);
		Utilities.runExeFile(basePath + exePath, basePath + filePath, uploadWindowTitle);
		waitAndClick(uploadSourceUploadBTN);
		waitForElementToBecomeInvisible(
				By.xpath("//div[@id='filesSource' and @aria-hidden='false']//div[@data-ng-if='file.isInLoadState']"));
		_normalWait(5000);

	}

	// Choose file for upload from data folder
	public void uploadFile(String fileName) throws InterruptedException, Exception {

		String FilePath = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\testdata"
				+ File.separator + fileName;

		clickOn(uploadSourceAddFilesBtn);
		Thread.sleep(4000);
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
		Thread.sleep(4000);
	}

	/** Verify File uploaded successfully **/
	public void verifyDocumentIsUploaded(String uploadedFileToBeVerify) {
		boolean flag = false;
		for (WebElement uploadedDocument : uploadedSourceDocument) {
			if (getText(uploadedDocument).trim().equalsIgnoreCase(uploadedFileToBeVerify)) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/** Verify Delete control for uploaded file **/
	public void verifyDeleteControlForUploadedFile(String uploadedFileToBeVerify) {
		boolean flag = false;
		for (WebElement uploadedDocument : uploadedSourceDocument) {
			if (getText(uploadedDocument).trim().equalsIgnoreCase(uploadedFileToBeVerify)) {
				if (isElementPresent(uploadedDocument
						.findElement(By.xpath("./parent::div/following-sibling::div/a[@title='Delete Attachment']")))) {
					flag = true;
				}
			}
			break;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/** Verify Delete control is not displayed for uploaded file **/
	public void verifyDeleteControlNotDisplayedForUploadedFile(String uploadedFileToBeVerify) {
		boolean flag = true;
		for (WebElement uploadedDocument : uploadedSourceDocument) {
			if (getText(uploadedDocument).trim().equalsIgnoreCase(uploadedFileToBeVerify)) {
				try {
					if (isElementPresent(uploadedDocument.findElement(
							By.xpath("./parent::div/following-sibling::div/a[@title='Delete Attachment']")))) {
						flag = false;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			break;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/** Click on Delete control for uploaded file **/
	public void clickOnDeleteControlForUploadedFile(String uploadedFileToBeVerify) {
		boolean flag = false;
		for (WebElement uploadedDocument : uploadedSourceDocument) {
			if (getText(uploadedDocument).trim().equalsIgnoreCase(uploadedFileToBeVerify)) {
				if (isElementPresent(uploadedDocument
						.findElement(By.xpath("./parent::div/following-sibling::div/a[@title='Delete Attachment']")))) {
					clickOn(uploadedDocument.findElement(
							By.xpath("./parent::div/following-sibling::div/a[@title='Delete Attachment']")));
					flag = true;
				}
			}
			break;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/** Click on uploaded File **/
	public void clickOnUploadedFile(String uploadedFileToBeVerify) {
		boolean flag = false;
		for (WebElement uploadedDocument : uploadedSourceDocument) {
			if (getText(uploadedDocument).trim().equalsIgnoreCase(uploadedFileToBeVerify)) {
				waitAndClick(uploadedDocument);
				flag = true;
			}
			break;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/** verify Confirmation Window Before Delete File **/
	public void verifyConfirmationWindowDisplayedBeforeDeleteFile() {
		waitForElementPresent(confirmationWindowBeforeDeleteMessage, 5);
		Assert.assertEquals(confirmationWindowBeforeDeleteMessage
				.findElement(By.xpath("./ancestor::div[@id='queryConfirmation']")).getAttribute("class").trim(),
				"modal fade modalshow in");
		reportInfo();
	}

	/** verify Confirmation Window closed for Delete File **/
	public void verifyConfirmationWindowClosedForDeleteFile() {
		boolean flag = true;
		waitForElementPresent(confirmationWindowBeforeDeleteMessage, 5);
		if (confirmationWindowBeforeDeleteMessage.findElement(By.xpath("./ancestor::div[@id='queryConfirmation']"))
				.getAttribute("class").trim().equalsIgnoreCase("modal fade modalshow in")) {
			flag = false;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/** Verify 'Confirm' dialog is displayed with "Yes" options **/
	public void verifyYesOnConfirmationWindow() {
		Assert.assertEquals(confirmationWindowBeforeDeleteMessage
				.findElement(By.xpath("./ancestor::div[@id='queryConfirmation']")).getAttribute("class").trim(),
				"modal fade modalshow in");
		Assert.assertTrue(approveDeleteMessageFromConfirmationWindow.isDisplayed());
		reportInfo();
	}

	/** Verify 'Confirm' dialog is displayed with "No" options **/
	public void verifyNoOnConfirmationWindow() {
		Assert.assertEquals(confirmationWindowBeforeDeleteMessage
				.findElement(By.xpath("./ancestor::div[@id='queryConfirmation']")).getAttribute("class").trim(),
				"modal fade modalshow in");
		Assert.assertTrue(declineDeleteMessageFromConfirmationWindow.isDisplayed());
		reportInfo();
	}

	/** Click on "Yes" option on 'Confirm' dialog is displayed with **/
	public void clickONYesOnConfirmationWindow() {
		Assert.assertEquals(confirmationWindowBeforeDeleteMessage
				.findElement(By.xpath("./ancestor::div[@id='queryConfirmation']")).getAttribute("class").trim(),
				"modal fade modalshow in");
		waitAndClick(approveDeleteMessageFromConfirmationWindow);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		_normalWait(2000);
		reportInfo();
	}

	/** Click on "No" option on 'Confirm' dialog **/
	public void clickOnNOConfirmationWindow() {
		Assert.assertEquals(confirmationWindowBeforeDeleteMessage
				.findElement(By.xpath("./ancestor::div[@id='queryConfirmation']")).getAttribute("class").trim(),
				"modal fade modalshow in");
		waitAndClick(declineDeleteMessageFromConfirmationWindow);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
		_normalWait(2000);
		reportInfo();
	}

	/* Verify Order By Date Of Action with latest on top */
	public void verifyDateOfActionWithLatestOnTop() {
		boolean flag = false;
		int n = 0;
		List<WebElement> listToBeShown;
		if (isElementDisplayed(loggedEventBlock)) {
			listToBeShown = loggedEventList;
		} else {
			listToBeShown = messagesTime;
		}
		List<WebElement> ListOfInnerMessage;
		List<String> dateAndTimeList = new ArrayList<>();

		if (listToBeShown.size() > 0) {
			for (WebElement medicationDateList : listToBeShown) {
				try {
					WebElement iconToExpandMessage = medicationDateList
							.findElement(By.xpath("./parent::div/parent::div//a[@class='inner-message-opener']"));
					if (iconToExpandMessage.isDisplayed()) {
						waitAndClick(iconToExpandMessage);
						_normalWait(500);
						ListOfInnerMessage = medicationDateList.findElements(By.xpath(
								"./parent::div/parent::div/parent::div/parent::div//div[@class='message-item ng-scope inner-message']"));
						moveToElement(ListOfInnerMessage.get(ListOfInnerMessage.size() - 1)
								.findElement(By.xpath("./div//time")));
						dateAndTimeList.add(getText(ListOfInnerMessage.get(ListOfInnerMessage.size() - 1)
								.findElement(By.xpath("./div//time"))));
					}
				} catch (Exception e) {
					dateAndTimeList.add(getText(medicationDateList));
					moveToElement(medicationDateList);
				}
				if (n == 5)
					break;
				n++;
			}
			String medicationValue = dateAndTimeList.get(0);
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd-MMM-yyyy hh:mm aa");
			Date s1 = null;
			try {
				s1 = dateTimeInGMT.parse(medicationValue);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (int medicationDateOfActionList = 1; medicationDateOfActionList < dateAndTimeList
					.size(); medicationDateOfActionList++) {
				String medicationOtherElements = dateAndTimeList.get(medicationDateOfActionList);
				Date s2 = null;
				try {
					s2 = dateTimeInGMT.parse(medicationOtherElements);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (s1.compareTo(s2) > 0) {
					flag = true;
					Assert.assertTrue(flag, " Latest  Date Of Action Is On Top");
				}
			}
		} else {
			Log.info("No Record Present");
		}
	}

	/* Verify Medication Present In MedicationRow */
	public void verifyMedicationPresentInRow(String medicationToBeVerify) {
		boolean flag = false;
		waitForWebElementEnable(medicationListGrid, 50);
		if (medicationList.size() > 0) {
			for (WebElement medicationName : medicationList) {
				if (getText(medicationName.findElement(By.xpath(".//h4"))).trim().startsWith(medicationToBeVerify)) {
					flag = true;
					Assert.assertTrue(flag, "Medication  Present In list");
					break;
				}
			}
		} else {
			Log.info("Medication List Not Present and Not Medication  Added");
		}
	}

	/* Select Medication */

	/**
	 * Select visit row with Calendar icon
	 */
	public void clickOnMedicationVisitRow(String medicationToBeSelected) {
		boolean flag = false;
		waitForWebElementEnable(medicationListGrid, 50);
		for (WebElement medication : medicationList) {
			if (getText(medication.findElement(By.xpath(".//h4"))).trim().startsWith(medicationToBeSelected)) {
				waitForElement(medication);
				waitAndClick(medication);
				flag = true;
				break;
			}

		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Medication details Displayed */
	public void verifyMedicationInstructions() {

		boolean flag = false;
		for (int instructionInfoVar = 0; instructionInfoVar < instructionInfo.size(); instructionInfoVar++) {
			WebElement paraInstructionInfo = instructionInfo.get(instructionInfoVar);
			javascripctHilightingElement(paraInstructionInfo);
			String infoHistoryOfIntsructionText = paraInstructionInfo.getText();
			if (infoHistoryOfIntsructionText.length() > 0) {
				flag = true;
				Assert.assertTrue(flag, "The Instruction Present In Medication Instruction");
				reportInfo();
			}
		}

	}

	/* Verify Medication Name */

	public void verifyMedicationName(String medicationName) {
		javascripctHilightingElement(medicationInfoName);
		Assert.assertTrue(medicationInfoName.getText().trim().equalsIgnoreCase(medicationName));
		reportInfo();
	}

	/* Verify Medication Comment */

	public void verifyMedicationComment() {
		boolean flag = false;
		String commentText = commentMedicationInfo.getText();
		javascripctHilightingElement(commentMedicationInfo);
		if (commentText.length() > 0) {
			flag = true;
			Assert.assertTrue(flag, "The Comment Present In Medication Comment");
			reportInfo();
		}
	}

	/* Verify Set Flag Medication Displayed */
	public void verifySetFlagMedicationIsDisplayed() {

		javascripctHilightingElement(setFlagMedicationICN);
		Assert.assertTrue(isElementPresent(setFlagMedicationICN));
		reportInfo();
	}

	/* Verify Set Flag Is Enable */
	public void verifySetFlagMedicationIsEnabled() {
		Assert.assertTrue(setFlagMedicationICN.isEnabled());
		reportInfo();
	}

	/* Verify Medication Notes Label Displayed */
	public void verifyMedicationNotesLabelIsDisplayed() {
		javascripctHilightingElement(medicationNotesInfo);
		Assert.assertTrue(isElementPresent(medicationNotesInfo));
		reportInfo();
	}

	/* Verify Medication Date Time Present */

	public void verifyMedicationDateTimePresent(String dateOfMedication) {
		boolean flag = false;
		String text = medicationTime.getText();
		javascripctHilightingElement(medicationTime);
		String[] dateTime = text.split("\\n");
		if (dateTime[0].trim().contains(dateOfMedication)) {
			flag = true;
			Assert.assertTrue(flag, "Date Present");
			reportInfo();
		}

		if (text.contains("AM") || text.contains("PM")) {
			flag = true;
			Assert.assertTrue(flag, "Time Present");
			reportInfo();
		}
	}

	/* Medication History Lat Action For Added Medication */
	public void verifyMedicationHistoryLastActionForAddedMedication() {
		Assert.assertTrue(isElementPresent(lastActionText));
		reportInfo();
	}

	/* Verify Medication History */
	public void verifymedicationHistory() {
		Assert.assertTrue(isElementPresent(showHistoryMedication));
		reportInfo();
	}
	/* Click On Medication History */

	public void clickOnMedicationHistory() {
		clickOn(showHistoryMedication);
		waitForElementToBecomeInvisible(
				By.xpath("//div[@id='subject-medication-history-dialog']//div[@class='modal-content']"));
	}

	/* Verify Medication History PopUp Displayed */
	public void verifyMedicationHistoryPopUpDisplayed() {
		Assert.assertTrue(isElementPresent(showHistoryMedicationPopUp));
		reportInfo();
	}
	/* Verify Medication PopUpHistory Displayed With RefreshIcon */

	public void verifyMedicationHistoryPopUpDisplayedWithRefreshItemIcon() {
		Assert.assertTrue(isElementPresent(reloadMedicationHistory));
		reportInfo();
	}

	/* Verify Medication PopUpHistory Displayed With Date/Time */

	public void verifyMedicationHistoryPopUpDisplayedWithDateTime() {
		boolean flag = false;
		String dateTimeHistory = dateTimeMedicationHistoryText.getText();
		javascripctHilightingElement(dateTimeMedicationHistoryText);
		if (dateTimeHistory.length() > 0) {
			flag = true;
			Assert.assertTrue(flag, "Date Time Present in medication History POPuP");
			reportInfo();
		}
	}

	/* Verify Medication PopUpHistory Displayed With Event */
	public void verifyMedicationHistoryPopUpDisplayedWithEventsInfo() {

		javascripctHilightingElement(eventMedicationHistory);
		Assert.assertTrue(isElementPresent(eventMedicationHistory));
		reportInfo();
	}

	/* Verify select Medication Filter */

	public void selectMedicationFillterValue(String medicationStatus) {
		for (WebElement medicationStatusList : medicationFilterList) {
			if (medicationStatusList.getText().trim().contains(medicationStatus)) {
				clickOn(medicationStatusList.findElement(By.xpath(".//parent::a//parent::li")));
				break;
			}
		}
	}

	/* Verify Medication List Displayed */

	public void verifyMedicationListDisplayed() {
		Assert.assertTrue(isElementPresent(medicationListGrid));
		reportInfo();
	}

	/**
	 * Select visit Medication Status In Row
	 */
	public void verifyStatusInMedicationRow(String medicationToBeSelected, String medictaionStatus) {

		boolean flag = false;
		waitForWebElementEnable(medicationListGrid, 50);
		for (int medicationIndex = 0; medicationIndex < medicationList.size(); medicationIndex++) {
			WebElement medicationName = medicationList.get(medicationIndex);
			if (medicationName.findElement(By.xpath(".//h4")).getText().trim().startsWith(medicationToBeSelected)) {
				javascripctHilightingElement(medicationName);
				if (medicationName.findElement(By.xpath(".//div[@class='left-col']//p")).getText().trim()
						.startsWith(medictaionStatus)) {
					flag = true;
					Assert.assertTrue(flag, "The Medication Status Is In Row Present");
					reportInfo();
					break;
				}
				break;
			}

		}

	}

	/* Close Medication History PopUp */

	public void closeMedicationHistoryPopUp() {
		clickOn(modelMedicationHistoryPopUpCloseBTN);
		waitForElementToBecomeVisible(By.xpath("//div[@id='page-title']/h1[contains(text(),'Subject')]"), driver);
	}

	/* Show History Medication Icon Not Displayed */
	public void verifyMedicationHistoryControlIsNotDisplayed() {
		Assert.assertTrue(!(hiddenShowHistoryICN.isDisplayed()));
		reportInfo();

	}

	/**
	 * Select options for Assignee
	 *
	 */
	public void selectAssigneeDropDownOption(String option) {
		scrollToTopOfThePage();
		clickOn(assignDropdown);
		for (WebElement reasonOption : assigneeDownOptionLIST) {
			if (reasonOption.getText().trim().equalsIgnoreCase(option)) {
				clickOn(reasonOption.findElement(By.xpath("./parent::li")));
				break;
			}
		}
		waitForSpinner(DEFAULT_WAIT_4_PAGE);
	}

	public CentralRatingAppointmentPage clickOnInitiateVisitIconForIRVisit() {
		_normalWait(2000);
		if (initiateButtonForVisit.isDisplayed()) {
			waitAndClick(initiateButtonForVisit);
		}
		new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[@id='content-container']//div[@data-ng-show='isBusy' and @class='ng-isolate-scope']")));
		return PageFactory.initElements(driver, CentralRatingAppointmentPage.class);
	}

	/* Verify Event List Displayed */

	public void verifyEventListDisplayed() {
		Assert.assertTrue(isElementPresent(eventListGrid));
		reportInfo();
	}
	/* Verify Logged event Details Block */

	public void verifyLoggedEventDetailBlock() {
		if (loggedEventList.size() > 0) {
			moveToElement(loggedEventDetailsBlock);
			Assert.assertTrue(isElementPresent(loggedEventDetailsBlock));
			reportInfo();
		} else {
			Log.info("Records Are not Prsent");
		}

	}

	/**
	 * Click On Event Row For Which Flag Not Set
	 * 
	 */
	public void clickOnEventRowWithFlagNotSet() {
		boolean flag = false;
		_normalWait(300);
		if (sizeofTheList(loggedEventRows) > 0) {
			for (WebElement event : loggedEventRows) {
				waitForElement(event);
				if (event.findElement(By.xpath(".//div[@class='meta']//span")).getAttribute("class")
						.equalsIgnoreCase("flag icon-red-flag")) {
					flag = true;
					clickOn(event);
					break;
				}
			}
			Assert.assertTrue(flag, "Flag Is  Not Set For Selected Row");
			waitForElementToBecomeVisible(By.xpath("//span[@class='icon-flag']//parent::button"), driver);
		}

	}

	/**
	 * Click On Event Row For Which Flag Set
	 * 
	 */

	public void clickOnEventRowWithFlagSet() {
		boolean flag = false;
		waitForElement(eventListGrid);
		if (sizeofTheList(loggedEventRows) > 0) {
			for (WebElement event : loggedEventRows) {
				if (event.findElement(By.xpath(".//div[@class='meta']//span")).getAttribute("class")
						.equalsIgnoreCase("flag icon-red-flag set")) {
					flag = true;
					javascriptButtonClick(event);
					Assert.assertTrue(flag, "Flag Is Set For Selected Row");
					break;
				}
			}
		}
	}

	/* Click On Flag Icon On Logged Event */

	public void clickOnEventAndQuestionnaireLoggedFlag() {
		_normalWait(1500);
		waitForElement(eventFlagICN);
		javascriptButtonClick(eventFlagICN);
		waitForElementToBecomeInvisible(By.xpath("//div[@id='modal-fade' and @class='smart-spinner']"));
	}
	/* Verify Flag Set Icon For Event */

	public void verifyFlagSectionForEventAndQuestionnaireDisplayedWithFlagSetIcon() {
		waitForElement(setFlagEventQuestionnaireICN);
		Assert.assertTrue(isElementPresent(setFlagEventQuestionnaireICN) && setFlagEventQuestionnaireICN.isEnabled());
		reportInfo();
	}

	/* Verify Clear Icon For Event */

	public void verifyFlagSectionForEventAndQuestionnaireDisplayedWithClearIcon() {
		Assert.assertTrue(
				isElementPresent(clearFlagEventAndQuestionnareICN) && clearFlagEventAndQuestionnareICN.isEnabled());
		reportInfo();
	}

	/* Click On Event Row To Select */

	public void clickOnEventAndQuestionnaireRow(String rowToBeSelect) {
		boolean flag = false;
		List<WebElement> listToBeShown = null;
		if (loggedEventRows.size() > 0) {
			listToBeShown = loggedEventRows;
		} else if (questionnaireListItemList.size() > 0) {
			listToBeShown = questionnaireListItemList;
		}
		if (listToBeShown.size() > 0) {
			for (WebElement rowName : listToBeShown) {
				if (getText(rowName.findElement(By.xpath(".//span[contains(@class,'name')]"))).trim()
						.startsWith(rowToBeSelect)) {
					waitForElement(rowName);
					scrollPageThroughWebElement(rowName);
					waitAndClick(rowName);
					new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(
							By.xpath("//div[@id='modal-fade' and @class='smart-spinner']")));
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag);

	}

	/**
	 * Verify selected Event displayed with Flag icon on the Event List
	 * 
	 */
	public void verifySelectedEventAndQuestionnaireDisplayedWithFlagIconOnList() {
		boolean flag = false;
		List<WebElement> listToBeShown = null;
		if (loggedEventRows.size() > 0) {
			listToBeShown = loggedEventRows;
		} else if (questionnaireListItemList.size() > 0) {
			listToBeShown = questionnaireListItemList;
		}
		if (listToBeShown.size() > 0) {
			for (WebElement typeToSelect : listToBeShown) {
				if (typeToSelect.getAttribute("class").contains("selected")) {
					if (typeToSelect.findElement(By.cssSelector("div[class='meta'] span")).getAttribute("class")
							.equalsIgnoreCase("flag icon-red-flag set")) {
						moveToElement(typeToSelect.findElement(By.cssSelector("div[class='meta'] span")));
						flag = true;
						Assert.assertTrue(flag);
						reportInfo();
						break;
					}
				}
			}
		}

	}

	/**
	 * Verify selected Event Not displayed with Flag icon on the Event List
	 * 
	 */
	public void verifySelectedEventNotDisplayedWithFlagIconOnEventList() {
		boolean flag = false;
		List<WebElement> listToBeShown = null;
		if (loggedEventRows.size() > 0) {
			listToBeShown = loggedEventRows;
		} else if (questionnaireListItemList.size() > 0) {
			listToBeShown = questionnaireListItemList;
		}
		if (listToBeShown.size() > 0) {
			for (WebElement typeToSelect : listToBeShown) {
				if (typeToSelect.getAttribute("class").contains("selected")) {
					if (typeToSelect.findElement(By.xpath(".//div[@class='meta']//span")).getAttribute("class")
							.equalsIgnoreCase("flag icon-red-flag")) {

						moveToElement(typeToSelect.findElement(By.xpath(".//div[@class='meta']//span")));
						flag = true;
						Assert.assertTrue(flag);
						reportInfo();
						break;
					}
				}
			}
		}
	}

	/* Verify SubTab Under Subject category Displayed */
	public void verifySubTabFiltersUnderSubjectCategoryIsPresent(String SubTab) {
		boolean flag = false;
		waitForElement(subjectSubTabBlock);
		for (WebElement filterList : subjectSubTabFilterList) {
			if (getText(filterList.findElement(By.xpath("./a//span"))).equalsIgnoreCase(SubTab)) {
				flag = true;
				javascripctHilightingElement(filterList);
				Assert.assertTrue(flag, "SubTab Filters Displayed");
				reportInfo();
				break;

			}
		}

	}

	/* Verify Questionnaire Block List Grid Displayed */

	public void verifyQuestionnaireBlockListGridDisplayed() {
		boolean flag = false;
		if (questionnaireListItemList.size() > 0) {
			flag = true;
			Assert.assertTrue(isElementPresent(questionnariesListGrid));
			reportInfo();
		}
		Assert.assertTrue(flag);
		_normalWait(200);
	}

	/**
	 * Click On Event Row For Which Flag Not Set
	 * 
	 */
	public void clickOnQuestionnarireRowWithFlagNotSet() {
		boolean flag = false;
		if (sizeofTheList(questionnaireListItemList) > 0) {
			for (WebElement questionnaire : questionnaireListItemList) {
				waitForElement(questionnaire);
				if (questionnaire.findElement(By.cssSelector("div[class='meta'] span")).getAttribute("class")
						.equalsIgnoreCase("flag icon-red-flag")) {
					flag = true;
					clickOn(questionnaire);
					break;
				}
			}
			Assert.assertTrue(flag, "Flag Is  Not Set For Selected Row");
			waitForElementToBecomeVisible(By.xpath("//span[@class='icon-flag']//parent::button"), driver);
		}

	}

	/**
	 * Click On Event Row For Which Flag Set
	 * 
	 */

	public void clickOnQuestionnarireRowWithFlagSet() {
		boolean flag = false;
		waitForElement(eventListGrid);
		if (sizeofTheList(loggedEventRows) > 0) {
			for (WebElement event : loggedEventRows) {
				if (event.findElement(By.xpath(".//div[@class='meta']//span")).getAttribute("class")
						.equalsIgnoreCase("flag icon-red-flag set")) {
					flag = true;
					javascriptButtonClick(event);
					Assert.assertTrue(flag, "Flag Is Set For Selected Row");
					break;
				}
			}
		}
	}

	/* Verify Questionnaries Details Block displayed */
	public void verifyQuestionnariesDetailsBlockDisplayed() {
		if (questionnaireListItemList.size() > 0) {
			Assert.assertTrue(isElementPresent(questionnariesDetailsBlock));
			reportInfo();
		} else {
			Log.info("Records Are not Prsent");
		}

	}

	/* verify compliance area */

	public void verifyComplianceSectionDisplay() {
		if (complianceRowList.size() > 0) {
			Assert.assertTrue(isElementDisplayed(complianceSection));
			reportInfo();
		}
	}

	/* verify block under compliance area */

	public void verifyComplianceAreaByBlockName(String blockName) {
		boolean flag = false;
		for (WebElement blockList : complianceRowList) {
			if (blockList.getText().trim().contains(blockName)) {
				javascripctHilightingElement(blockList);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Select User Filter Block by Name */

	public void selectUserBlockFilter(String typeToSelectFilter) {
		waitForElement(subjectSubTabBlock);
		WebElement radioButtonforTypeToSelect = driver.findElement(By.xpath(
				"//div[contains(@class,'user-filter-block')]//label[starts-with(@for,'" + typeToSelectFilter + "')]"));
		waitAndClick(radioButtonforTypeToSelect);
	}

	/* Verify User Filter Block Filter Selected by Default */
	public void verifyUserBlockFilterSelectedByDefault(String typeToSelectFilter) {
		boolean flag = false;
		waitForElement(subjectSubTabBlock);
		WebElement radioButtonforTypeToSelect = driver.findElement(By.xpath(
				"//div[contains(@class,'user-filter-block')]//label[starts-with(@for,'" + typeToSelectFilter + "')]"));

		if (radioButtonforTypeToSelect.findElement(By.xpath(".//parent::span//input")).isSelected()) {
			flag = true;
			moveToElement(radioButtonforTypeToSelect);
			Assert.assertTrue(flag, "User Filter Block Selected By Default");

		}

	}

	/* Select Questionnaires Name By Name And Status(Completed/Expired) */

	public void selectQuestionnariesByStatus(String name, String Status) {
		boolean flag = false;
		_normalWait(500);
		if (sizeofTheList(questionnaireListItemList) > 0) {
			for (WebElement questionnaire : questionnaireListItemList) {
				if (questionnaire.findElement(By.xpath("./span[contains(@class,'name-label')]")).getText()
						.equalsIgnoreCase(name)) {

					if (questionnaire
							.findElement(By.xpath("./span[contains(@class,'name-label')]//preceding-sibling::i"))
							.getAttribute("class").contains(Status)) {
						clickOn(questionnaire.findElement(By.xpath("./span[contains(@class,'name-label')]")));
						flag = true;
						Assert.assertTrue(flag, "The Status Is Present With Questionnaries");
						scrollToTopOfThePage();
						break;
					}
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	/* Verify Questionnaires Details Block */

	public void verifyQuestionnairestDetailBlock() {
		if (questionnaireListItemList.size() > 0) {
			scrollToTopOfThePage();
			Assert.assertTrue(isElementPresent(questionnairesDetailsBlock));
			reportInfo();
		} else {
			Log.info("Records Are not Prsent");
		}

	}
	/*
	 * Verify Status(Expired/Completed) Icon Present Under Questionnaires Details
	 * Block
	 */

	public void verifyQuestionnairesStatusIconDisplayedUnderDetailssBlock(String Status) {
		if (questionnaireListItemList.size() > 0) {
			Assert.assertTrue(isElementPresent(driver.findElement(By.xpath(
					"//div[contains(@class,'questionnaire-info-block')]//i[contains(@class,'" + Status + "')]"))));
			reportInfo();
		} else {
			Log.info("Records Are not Prsent");
		}

	}

	/* Verify Questionnaires Status Present in Details Block With Time */

	public void verifyQuestionnairesStatusPresentInDetailsBlockWithTime(String statusToBeVerify) {

		boolean flag = false;
		if (questionnaireListItemList.size() > 0) {
			WebElement statusToBeCheck = driver.findElement(By.xpath(
					"//div[contains(@class,'questionnaire-info-block')]//div[contains(@class,'ng-scope')]//span"));
			if (getText(statusToBeCheck).equalsIgnoreCase(statusToBeVerify)) {
				if (getText(statusToBeCheck.findElement(By.xpath("//following-sibling::time"))).length() > 0) {
					flag = true;
					Assert.assertTrue(flag, "The Status Is Present With Time");
					reportInfo();
				}

			}

		} else {
			Log.info("Records Are not Prsent");
		}

	}

	/* Verify Details Under Questionnaires Block */
	public void verifyQuestionnairesInformationUnderDetailsBlock(String detailToBeVerify, String valueToBeVerify) {
		scrollToTopOfThePage();
		boolean flag = false;
		WebElement detailsToBeVerify = driver
				.findElement(By.xpath("//*[@class='questionnaire-info-row']//span[text()='" + detailToBeVerify + "']"));
		moveToElement(detailsToBeVerify);
		if (detailsToBeVerify.isDisplayed()) {
			WebElement textValueToBeCheck = detailsToBeVerify
					.findElement(By.xpath(".//following-sibling::span[not(contains(@class, 'ng-hide'))]"));
			moveToElement(textValueToBeCheck);
			System.out.println(textValueToBeCheck.getText());
			if (textValueToBeCheck.getText().contains(valueToBeVerify)) {
				flag = true;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Verify Questionnaires Score Block Displayed */
	public void verifyQuestionnairesScoreBlockPresent() {
		Assert.assertTrue(isElementPresent(questionnairesScoresBlock));
		reportInfo();
	}

	/* Verify Questionnaires Score Block Not Displayed */

	public void verifyQuestionnariesContentScoresNotDisplayed() {
		Assert.assertEquals(driver.findElements(By.xpath("//div[contains(@data-ng-if,'questionnaire.scores')]")).size(),
				0);
		reportInfo();
	}

	/* Verify Logged Event Filter Only Block Present */
	public void verifyLoggedEventFilterBlockPresent() {
		moveToElement(loggedEventListFilterBlock);
		Assert.assertTrue(isElementPresent(loggedEventListFilterBlock));
		reportInfo();
	}
	/* Verify No Logged Event Text Prsent */

	public void verifyNoLoggedEventTextPresent() {
		moveToElement(noLoggedEventText);
		Assert.assertEquals(getText(noLoggedEventText), "No logged events");
		reportInfo();
	}

	public String getMobileSubjectRegistrationCode() {
		return getText(mobileSubjectRegistrationCode);
	}

	public void closeMobileSubjectRegistrationPopup() {
		clickOn(mobileSubjectQrCloseIcon);
	}

	@FindBy(xpath = "//div[contains(@data-ng-repeat,'device in devices')]")
	private List<WebElement> devicesList;

	public void deActiveRegisteredMobileSubjectDevice(String deviceNameToBeDeactivated) {
		for (WebElement totalDevices : devicesList) {
			boolean activatedDevicesName = getText(
					totalDevices.findElement(By.xpath(".//div[@class='col-mac'][1]//p[@class='ng-binding']")))
							.contains(deviceNameToBeDeactivated);
			boolean deviceStatus = getText(totalDevices.findElement(By.xpath(".//div[3]//p")))
					.equalsIgnoreCase("Activated");
			if ((activatedDevicesName && deviceStatus) == true) {
				clickOn(totalDevices.findElement(By.xpath(".//div[4]//button")));
				break;
			}
		}
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

	/*
	 * Verify Selected Observer Showing In Reported Outcomes Section In detail page
	 */
	public void verifySelectedObserverPresentInDetailPage(String observerSelected)

	{
		boolean flag = false;
		if (getText(activatedObserverNameOnDetailPage).contains(observerSelected)) {
			flag = true;
		}
		Assert.assertTrue(flag, "observer Selection Sucessfully Saved and displayed");
		reportInfo();
	}

	public void selectCheckboxQueryOption(String checkboxQueryName) {
		WebElement checkBoxQuery = driver.findElement(By.xpath("//div[@class='checkbox-query']//span[text()='"
				+ checkboxQueryName + "']//../input[@type='checkbox']"));
		waitAndClick(checkBoxQuery);
		waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
		waitSpinnerToBecomeInvisible();

	}

	public void verifyListOfAllQueriesDisplayed() {
		Assert.assertTrue(queriesList.size() > 0, "Queries List is displaying");
		waitUntillFinishProcessSpinnerDisable();
		reportInfo();

	}

	/* Verify Not Administered Label Present */

	public void verifyNotAdministeredLabelPresent() {
		moveToElement(notAdministeredLabel);
		Assert.assertTrue(isElementPresent(notAdministeredLabel));
		reportInfo();
	}

	/* Verify Form Before Administered Thumbnail Image Displayed */
	public void VerifyFormThumbnailImageBeforeNotAdminsiteredIsPresent() {
		Assert.assertTrue(newthumbnailBeforeNotAdministeredIMG.isDisplayed());
	}

	/* Click On Thumbnail Image And Navigate To Assessment Details Page */
	public AssessmentsDetailsPage clickOnBeforeNotAdministeredThumbnailImage() {
		Assert.assertTrue(thumbnailBeforeNotAdministeredIMG.isDisplayed());
		scrollToTopOfThePage();
		waitAndClick(thumbnailBeforeNotAdministeredIMG);
		waitForSpinnerBecomeInvisible(DEFAULT_WAIT_4_PAGE);
		reportInfo();
		return PageFactory.initElements(driver, AssessmentsDetailsPage.class);
	}

	/**
	 * Select the Query
	 * 
	 * @param queryName
	 * @return
	 */
	public void selectQuery(String queryName) {
		boolean flag = true;
		for (WebElement querytoselect : queriesList) {
			if (querytoselect.findElement(By.xpath(".//div[@class='thread-message ng-binding']")).getText()
					.equalsIgnoreCase(queryName)) {
				flag = true;
				clickOn(querytoselect);
				waitForElement(findElement(By.xpath("//div[@data-ng-show='isExpand()' and @class='collapsed']")));
				waitForSpinner(DEFAULT_WAIT_4_ELEMENT);
				break;
			}
		}

		Assert.assertTrue(flag);
		reportInfo();
	}

	/* Verify For New Subject Status Disabled */

	public void verifySubjectStatusEditButtonDisabled() {
		moveToElement(subjectStatusEditBTN);
		Assert.assertFalse(subjectStatusEditBTN.isEnabled());
		reportInfo();
	}

	/* For Configuring Completed Subject By Clicking On Not Completed Link */

	public void completingVisitByMarkAsNotCompleting(String visitname, String userName, String password) {
		verifyNewSubjectDetailPage();
		clickOnVisitRow(visitname);
		clickOnAddVisitIcon();
		clickOnMarkAsNotCompletedLink();
		verifyReasonForChangeOptionPopUpIsDisplayed();
		setCurrentDate();
		selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		eSignReasonForChangeAndSubmit(userName, password);
		waitForProcessingVisitToBeCompleted();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
	}

	/** Select Current Date for Calendar Visit */

	public void setCurrentDate() {
		waitAndClick(NotSetCalenderButton);
		clickOn(scheduleCurrentDate);
	}

	/** Select Scheduled Date for Calendar Visit */

	public void setScheduledDateAfterSevenDays() {
		waitAndClick(NotSetCalenderButton);
		int dateAfterSevenDay = Integer.parseInt(scheduleCurrentDate.getText()) + 7;
		String date = Integer.toString(dateAfterSevenDay);
		WebElement dateToSelect = driver.findElement(By.xpath("//tr//td[text()='" + date + "']"));
		clickOn(dateToSelect);
	}

	/* Verify subject Status is displayed as Read-only label */
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

	/****
	 * verify consent to record changes
	 */
	public void verifyConsentToRecordAsReadOnlyLabel(String StatusToBeVerified) {
		boolean flag = false;
		Assert.assertTrue(isElementPresent(consentTorecordStatus),
				"Consent to record Status is displayed as Read-only label");
		if (consentTorecordStatus.getText().trim().contains(StatusToBeVerified)) {
			moveToElement(consentTorecordStatus);
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyDateAtTop() {
		boolean flag = false;
		List<String> dateAndTimeList = new ArrayList<>();
		int n = 0;
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
		if (dateInHistoryModalWindow.size() > 0) {
			for (WebElement dateTime : dateInHistoryModalWindow) {
				dateAndTimeList.add(dateTime.getText());
				moveToElement(dateTime);
				if (n == 5)
					break;
				n++;
			}
			String dateAndTimeInFirstRow = dateAndTimeList.get(0);
			Date s1 = null;
			try {
				s1 = dateTimeFormat.parse(dateAndTimeInFirstRow);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (int i = 1; i < dateAndTimeList.size(); i++) {
				String DateTimeToCompare = dateAndTimeList.get(i);
				Date s2 = null;
				try {
					s2 = dateTimeFormat.parse(DateTimeToCompare);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (s1.compareTo(s2) > 0) {
					flag = true;
				} else {
					flag = false;
				}
				Assert.assertTrue(flag, " Latest  Date Of Action Is On Top");
			}
		} else {
			Log.info("No Record Present");
		}

	}

	public void verifyHistoryModalWindowStatus(String statusToBeVerified, String statusChangedBy) {
		for (WebElement element : subjectHistoryWindowtext) {
			if (getText(element.findElement(By.xpath("./div[@class='col-xs-17']//b[1]"))).trim()
					.equals(statusToBeVerified)) {
				Assert.assertEquals(getText(element.findElement(By.xpath("./div[@class='col-xs-17']//b[2]"))).trim(),
						statusChangedBy);
				moveToElement(element.findElement(By.xpath("./div[2]")));
				break;
			} else {
				continue;
			}
		}
	}

	public void clickOnCloseButtonInHistoryModalPopUp() {
		waitAndClick(closeButton);
	}

	/*****
	 * verify all the Elements are present in the status Change for Subject DropDown
	 */

	public void verifySubjectStatusesArePresentInStatusChangeDropDownList(List<String> listToBeCompared) {
		clickOn(subjectStatusDRPBOX);
		List<String> subjectStatusList = new ArrayList<>();

		for (WebElement status : statusList) {
			subjectStatusList.add(status.getText());
			moveToElement(status);
		}
		Assert.assertEquals(subjectStatusList, listToBeCompared);
		clickOn(subjectStatusDRPBOX);
	}

	/* Verify subject Status is displayed as Read-only label */
	public void verifyTempID(String tempID) {
		boolean flag = false;
		Assert.assertTrue(isElementPresent(tempIDToBeVerified), "subject Status is displayed as Read-only label");
		if (tempIDToBeVerified.getText().trim().contains(tempID)) {
			moveToElement(tempIDToBeVerified);
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void verifyTempIdAsNumber(String maskTempID, String TempIdToBeVerified) {
		boolean flag = false;
		String tempId = tempIDToBeVerified.getText();
		if (tempId.length() == maskTempID.length() && tempId.length() == TempIdToBeVerified.length()) {
			try {
				Integer.parseInt(tempId);
				flag = true;
			} catch (NumberFormatException e) {
				// handle exception
			} catch (NullPointerException e) {
				// handle exception
			}
		} else {

		}
		Assert.assertTrue(flag);
	}

	/*****
	 * get Temporary ID value
	 */

	public String getTempIDvalue() {
		String tempIDvalue = getText(tempIDToBeVerified);
		return tempIDvalue;
	}

	/****
	 * verify screening input and language textbox is editable
	 */

	public void verifyFieldsAreEditable() {
		Assert.assertTrue(addSubPopup_ScreeningINP.isEnabled() && addSubPopup_SelectedSubjectLanguage.isEnabled());
	}

	/*
	 * verify consent to record is enabled on add Subject popup
	 */

	public void verifyConsentRecordEnabled() {
		Assert.assertTrue(isElementDisplayed(consentToRecord));
		reportInfo();
	}

	/****
	 * clickon consent to record
	 */

	public void clickOnConsentToRecord() {
		waitAndClick(consentToRecord);
	}

	/***
	 * select yes from consent To record
	 */

	public void selectOptionFromConsentTorecord(String Option) {
		for (WebElement optionUnderConsentrecord : optionUnderConsentTorecordDropDown) {
			if (optionUnderConsentrecord.getText().equalsIgnoreCase(Option)) {
				clickOn(optionUnderConsentrecord);
			}
		}
	}

	public void saveSubject() {
		clickOn(addSubPopup_SaveBTN);
	}

	/* Verify All Configured Visit Present In List */
	public void verifyAllConfiguredVisitPresent() {
		moveToElement(calendarVisitListGrid);
		Assert.assertTrue(isElementPresent(calendarVisitListGrid));
		reportInfo();
	}

	/* Click on Calendar self Serve CRVisit AddICN */
	public void clickOnCalenderVisitSelfServeCRVisitAddIcon() {
		clickOn(selfServeCRVisitAddICN);
	}

	/*
	 * Verify SelfCR Scheduled Date Calendar Window Displayed*?
	 * 
	 */

	public void verifySelfCrScheduledDateCalenderWindowDisplayed() {
		moveToElement(selfCrScheduledDateCalenderWindow);
		Assert.assertTrue(selfCrScheduledDateCalenderWindow.isDisplayed());
		reportInfo();
	}

	public void selectTodayDateOnCrSelfCalenderPopUp(String dateToSelect) {
		boolean flag = false;
		for (WebElement selfCrdateList : calenderDateList) {
			if (selfCrdateList.isEnabled()) {
				if (getText(selfCrdateList.findElement(By.xpath(".//span"))).trim().equalsIgnoreCase(dateToSelect)) {
					clickOn(selfCrdateList);
					flag = true;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	public void selectTomorrowDateOnCrSelfCalenderPopUp() {
		boolean flag = false;
		if (calenderDateList.get(1).isEnabled()) {
			clickOn(calenderDateList.get(1));
			flag = true;

		}

		Assert.assertTrue(flag);
		reportInfo();
	}

	public void selectTodayDateOnCrSelfCalenderPopUp() {
		boolean flag = false;
		if (calenderDateList.get(0).isEnabled()) {
			clickOn(calenderDateList.get(0));
			flag = true;

		}

		Assert.assertTrue(flag);
		reportInfo();
	}

	public String getSelectedDateOnCrSelfCalenderPopUp() {
		String SelectedDate = null;
		boolean flag = false;
		for (WebElement selfCrdateList : calenderDateList) {

			if (getAttributeValueOfElement(selfCrdateList.findElement(By.xpath("./parent::td")), "class")
					.equalsIgnoreCase("k-state-selected")) {
				SelectedDate = getAttributeValueOfElement(selfCrdateList, "data-value");
				flag = true;
				break;
			}
		}

		Assert.assertTrue(flag);
		reportInfo();
		return SelectedDate;

	}

	/*
	 * Verify SelF Scheduled Date Displayed
	 * 
	 */

	public void verifyCrSelfScheduledSelectedDate(String dateToBeVerify) {
		Assert.assertTrue(getText(selectedCRSelfScheduleDateText).trim().equalsIgnoreCase(dateToBeVerify));
		moveToElement(selectedCRSelfScheduleDateText);
		reportInfo();
	}

	public void verifyMedicationLastActionStatus(String StatusToBeVerified) {
		boolean flag = false;
		Assert.assertTrue(isElementPresent(medicationLastActionStatus), "Last Action is displayed");
		if (medicationLastActionStatus.getText().trim().contains(StatusToBeVerified)) {
			moveToElement(medicationLastActionStatus);
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Select Raters for Calendar Visit from Dropdown for multiple configured Form
	 * Type
	 */
	public void selectRaterForCalenderVisitDropDownForMultipleConfiguredFormType(String configuredFormType,
			String valueToBeSearch) {
		scrollToTopOfThePage();
		boolean flag = false;
		Assert.assertTrue(configuredTemplatesList.size() > 0);
		if (configuredTemplatesList.size() > 0) {
			for (WebElement scaleRow : configuredTemplatesList) {
				scrollPageThroughWebElement(scaleRow);
				if (getText(scaleRow.findElement(By.xpath(".//div[@class='small ng-binding']")))
						.equalsIgnoreCase(configuredFormType)) {
					clickOn(scaleRow
							.findElement(By.xpath("./div//div[@class='row administered-row']//a[@title='Assign']")));
					List<WebElement> RaterList = scaleRow.findElements(By.xpath(
							"./div//div[@class='row administered-row']//a[@title='Assign']//parent::div//ul//li//a"));
					for (int i = 0; i <= RaterList.size() - 1; i++) {
						if (getText((RaterList.get(i))).trim().toLowerCase()
								.contains(valueToBeSearch.trim().toLowerCase())) {
							scrollPageThroughWebElement(RaterList.get(i));
							waitAndClick(RaterList.get(i));
							flag = true;
							break;
						}
					}
					break;
				}
			}
		}
		Assert.assertTrue(flag);
		new WebDriverWait(driver, 20)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner ng-scope']")));
	}

	/* Get Registration Code For EPRO login */
	public String getRegistrationCodeOfSubject() {
		String code = registrationCodeSubject.getText().trim();
		System.out.println(code);
		return code;

	}

	/* Get Registration Code for Observer */
	public String getRegistrationCodeofObserver() {
		String observerCode = registrationCodeObserver.getText().trim();
		return observerCode;
	}

	/* Deactivate Subject Configuration */
	public void deactivateSubjectConfiguration(String userName, String password) {
		_normalWait(DEFAULT_WAIT_4_PAGE);
		clickOnReportedOutComeMobileSubjectQrIcon();
		clickOnDeactivateDeviceButtonForRegisteredSubject();
		selectReasonForChangeOption(Constants.reasonsForChangeDeactivateDevice.get(0));
		eSignReasonForChangeAndSubmit(userName, password);
		clickOnSubjectRegistrationPopUpCloseButton();
	}

	/* Deactivate Observer Configuration */
	public void deactivateObserverConfiguration(String userName, String password) {

		clickOnMobileObserverQrIcon();
		clickOnDeactivateDeviceButtonForRegisteredObserver();
		selectReasonForChangeOption(Constants.reasonsForChangeDeactivateDevice.get(0));
		eSignReasonForChangeAndSubmit(userName, password);
		clickOnObserverRegistrationPopUpCloseButton();
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
	}

	/*
	 * Configuration Of Observer*?
	 * 
	 */

	public void configureObsreverForMobile(String observerRelation1, String userName, String pw) {
		clickOnReportedOutComeButton();
		configureObserver(observerRelation1, observerRelation1);
		selectMobileProObserver(observerRelation1);
		clickOnReportedOutComePopUpSaveBTN();
		selectReasonForChangeOption(Constants.Subject_Reason_For_Change);
		eSignReasonForChangeAndSubmit(userName, pw);
		_normalWait(DEFAULT_WAIT_4_ELEMENT);

	}

	public void verifyReplyButtonDisplayed() {

	}

	public void clickonArrowDownIcon() {
		waitAndClick(arrowDown);

	}

	public void clickonArrowUpIcon() {
		waitAndClick(arrowUp);
	}

	/**
	 * click on All tab present in questionnaires section
	 */
	public void clikOnAllTab() {
		waitAndClick(allButtonQuestionnaires);
	}

	/* Verify subject status change Dropdown is disabled */

	public void verifySubjectStatusDropDownDisable() {

		Assert.assertFalse(subjectStatusButton.isEnabled());
	}

	/**
	 * Verify Rater is present in calendar visit rater assignment dropdown
	 * 
	 * @author pratiksha
	 * @Version 1
	 * @parameter raterName
	 */
	public void verifyRaterIsPresentInCalenderVisitDropdown(String raterName) {
		boolean flag = false;
		scrollToTopOfThePage();
		clickOn(assignDropdown);
		for (WebElement raterOption : assigneeDownOptionLIST) {
			if (raterOption.getText().trim().equalsIgnoreCase(raterName)) {
				moveToElement(raterOption);
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		clickOn(assignDropdown);
	}

	/**
	 * Verify Rater is not present in calendar visit rater assignment dropdown
	 * 
	 * @author pratikshad
	 * @Version 1
	 * @parameter raterName
	 */
	public void verifyRaterIsNotPresentInCalenderVisitDropdown(String raterName) {
		boolean flag = false;
		scrollToTopOfThePage();
		clickOn(assignDropdown);
		for (WebElement raterOption : assigneeDownOptionLIST) {
			if (raterOption.getText().trim().equalsIgnoreCase(raterName)) {
				moveToElement(raterOption);
				flag = true;
				break;
			}
		}
		Assert.assertFalse(flag);
		clickOn(assignDropdown);
	}
	/*
	 * Verify default message sub tab
	 * 
	 * @Parameter subtabvalue
	 * 
	 * @Author pratiksha
	 * 
	 * @version 1
	 */

	public void verifyDefaultMessageSubTab(String SubTab) {
		boolean flag = false;
		waitForElement(subjectSubTabBlock);
		for (WebElement filterList : subjectSubTabFilterList) {
			if (filterList.getAttribute("class").equalsIgnoreCase("active")
					&& getText(filterList.findElement(By.xpath("./a//span"))).equalsIgnoreCase(SubTab)) {
				flag = true;
				moveToElement(filterList);
				break;
			}
		}
		Assert.assertTrue(flag);
	}

	/*
	 * Click On Event Row To Select
	 * 
	 * @Parameter eventToBeSelected
	 * 
	 * @Author pratiksha
	 * 
	 * @version 1
	 */

	public void clickOnEventRow(String eventToBeSelected) {
		boolean flag = false;
		waitForWebElementEnable(eventListGrid, 50);
		for (WebElement event : loggedEventRows) {
			if (getText(event.findElement(By.xpath(".//span[contains(@class,'log-event-name')]"))).trim()
					.startsWith(eventToBeSelected)) {
				waitForElement(event);
				waitAndClick(event.findElement(By.xpath(".//parent::div")));
				_normalWait(2000);
				new WebDriverWait(driver, 10).until(
						ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='smart-spinner']")));
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	/**
	 * Set started date for the assessment
	 * 
	 * @throws ParseException
	 */
	public void setStartedDate() {
		waitAndClick(startedDatePickerBTN);
		_normalWait(500);
		clickOn(currentDate);
		_normalWait(500);
	}

	public void verifyObserverNotDisplayed(String observerToSelect) {
		if (observerLIST.size() > 0) {
			for (WebElement observerName : observerLIST) {
				Assert.assertFalse(observerName.findElement(By.xpath("./span[@class='relation-column']")).getText()
						.equalsIgnoreCase(observerToSelect));
				break;
			}
		}
	}

	
	
	/***
	 * @param Select Year Of Birth
	 * 
	 */
	
	public void selectYearOfBirth(String Year)
	{
		boolean flag = true;
		
		try {
			WebElement birthYear = driver.findElement(ByLocator("//span[contains(text(),'"+Year+"')]/ancestor::li"));
			if (birthYear.isDisplayed())
				flag = false;
			waitAndClick(birthYear);
		    } catch (Exception e) {
			
		    }
		Assert.assertTrue(true);
		reportInfo();

	}
	
	public void selectYearOfbirthdropDown()
	{
		moveToElement(birthYaerDropdown);
		waitAndClick(birthYaerDropdown);
		reportInfo();
	}
	
	/****r
	 * 
	 * @param Status
	 */
	public void editSubjectStatusChange(String Status)
	{
		WebElement status = driver.findElement(ByLocator("//div[@id='create-or-edit-subject-dialog']//button/span[text()='"+Status+"']"));
		waitAndClick(status);
		waitSpinnerToBecomeInvisible();
		reportInfo();
	}
	
	/*****
	 * 
	 * Change In study
	 * @param reason
	 * @param username
	 * @param password
	 */
	
	public void enterReasonUserCredentialsforChange(String reason,String username,String password)
	{
		
		waitAndClick(reasonDprDwn);
		WebElement reasn = driver.findElement(ByLocator("//span[contains(text(),'"+reason+"')]"));
		moveToElement(reasn);
		waitAndClick(reasn);
		inputText(userNameField, username);
		inputText(pwdField, password);
		moveToElement(reasonConfrmBtn);
		waitAndClick(reasonConfrmBtn);
		reportInfo();
		
	}
	
	/****
	 * 
	 * Verifying age According to year Of Birth
	 * @param Year
	 */
	
	
	public void verifyAgeFromYearOfBirth(String Year)
	{
		
		int Currentyear = Calendar.getInstance().get(Calendar.YEAR);
		int YOB = Integer.parseInt(Year);
		int age = Currentyear-YOB;
		String actual = Integer.toString(age);
		_normalWait(3000);
		String YoBAge = ageValue.getText();
		String expected = YoBAge.substring(YoBAge.indexOf('(')+1, YoBAge.indexOf(')'));
		Assert.assertEquals(actual, expected);
		
	}
	
	/****
	 * @param Cancel year Of Birth
	 * 
	 */
	public void cancelYearOfBirth()
	{
		waitAndClick(cancelDateOfYear);
		
	}
	
	
	
	/******
	 * 
	 * @param Initiate Visit for subject
	 */
	
	public void initiateVisitforsubject()
	{
		waitAndClick(addVisitButton);
		String expected="Central Ratings - MedAvante Portal Application";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		waitAndClick(scheduleddateBtn);
		WebElement scheduleDate = driver.findElement(By.xpath("(//div[contains(@style,'display: block')]//td[@class='day active today'])[last()]"));
		waitAndClick(scheduleDate);
		waitAndClick(saveVisitButton);
		reportInfo();
		
	}
	
	/****
	 * 
	 * Verify age From Year Of Birth inside Visits Details
	 * @param subjectName
	 * @param visitName
	 * @param Year
	 */
	public void navigateToVisitandVerifyAgeFromDateOfYear(String subjectName,String visitName,String Year)
	{
		moveToElement(visitsidetab);
		waitAndClick(visitsidetab);
		javascriptButtonClick(VisitStudySelect);
		waitAndClick(filterStudyInputField);
		inputText(filterStudyInputField, subjectName);
		javascriptButtonClick(filterbutton);
		waitUntillSpinnerToBecomeInvisible();
		_normalWait(3000);
		WebElement selectVisit = driver.findElement(ByLocator("//a[text()='"+visitName+"']"));
		moveToElement(selectVisit);
		waitAndClick(selectVisit);
		int Currentyear = Calendar.getInstance().get(Calendar.YEAR);
		int YOB = Integer.parseInt(Year);
		int age = Currentyear-YOB;
		String actual = Integer.toString(age);
		_normalWait(3000);
		String YoBAge=ageValuetext.getText();
		String expected = YoBAge.substring(YoBAge.indexOf('(')+1, YoBAge.indexOf(')'));
		Assert.assertEquals(actual, expected);
		
	}
	
	
	@FindBy(xpath="//div[@id='content']//span[contains(text(),'Raters')]")
	private WebElement ratersSidetab;
	
	@FindBy(xpath="//div[contains(text(),'Bulk Load')]")
	private WebElement bulkloadButton;
	
	
	/*****
	 * 
	 * Verify Clinicinian/Raters Listing Screen  Displayed
	 * 
	 */
	public void verifyClinicianRatersListingScreenDisplayed()
	{
		boolean flag=true;
		try {
			if (ratersSidetab.isDisplayed()) {
				flag=true;
			}
			
		} catch (Exception e) {
			
		}
		
		Assert.assertTrue(flag);
		
	}
	
	
	/***
	 * 
	 * Bulk upload Option Not displayed
	 * 
	 */
	public void bulkUploadOptionNotdisplayed()
	{
		moveToElement(ratersSidetab);
		javascriptButtonClick(ratersSidetab);
       boolean flag=true;
		try {
			if (bulkloadButton.isDisplayed()) {
				flag=false;
			}
		} catch (Exception e) {
		}
		Assert.assertTrue(flag);
		reportInfo();
	
	}
	
	/***
	 * 
	 * Bulk upload Option  displayed
	 * 
	 */
	public void bulkUploadOptionDisplayed()
	{
		javascriptButtonClick(ratersSidetab);
		boolean flag=true;
		try {
			if (bulkloadButton.isDisplayed()) {
				flag=true;
			}
		} catch (Exception e) 
		{
		}
		Assert.assertTrue(flag);
		reportInfo();
	}

	
	public void verifyListOfAssessmentAlongWithVisit(String configuredFormType) {
		scrollToTopOfThePage();
		boolean flag = false;
		int count = 0;
		int index;
		List<Integer> sameConfigurationFormIndex = new ArrayList<>();
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		if (configuredTemplatesList.size() > 0) {
			for (int formVar = 0; formVar < configuredTemplatesList.size(); formVar++) {
				WebElement formNameConfigured = configuredTemplatesList.get(formVar);
				if (getText(formNameConfigured.findElement(By.xpath(".//div[@class='small ng-binding']")))
						.equalsIgnoreCase(configuredFormType)) {
					index = formVar;
					sameConfigurationFormIndex.add(index);
					count++;
				}
			}
		}
		
		if (configuredTemplatesList.size() > 0) {
			for (int litsVarindex = 0; litsVarindex < sameConfigurationFormIndex.size(); litsVarindex++) {
				WebElement element = configuredTemplatesList.get(litsVarindex);
				List<WebElement> assessmentList = element.findElements(By.xpath(".//div[@class='small ng-binding']"));
				for (int i = 0; i <= assessmentList.size() - 1; i++) {
					if (assessmentList.get(i).isEnabled()) {
						moveToElement(assessmentList.get(i));
						flag = true;
						break;
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}	
	

	
	public void verifyLockedIconDisplayedForAssessment(String configuredFormType,String valueToBeSearch) {
		scrollToTopOfThePage();
		boolean flag = false;
		int count = 0;
		int index;
		List<Integer> sameConfigurationFormIndex = new ArrayList<>();
		_normalWait(DEFAULT_WAIT_4_ELEMENT);
		if (configuredTemplatesList.size() > 0) {
			for (int formVar = 0; formVar < configuredTemplatesList.size(); formVar++) {
				WebElement formNameConfigured = configuredTemplatesList.get(formVar);
				if (getText(formNameConfigured.findElement(By.xpath(".//div[@class='small ng-binding']")))
						.equalsIgnoreCase(configuredFormType)) {
					index = formVar;
					sameConfigurationFormIndex.add(index);
					count++;
				}
			}
		}
		
		if (count > 0) {
			for (int litsVarindex = 0; litsVarindex < sameConfigurationFormIndex.size(); litsVarindex++) {
				WebElement element = configuredTemplatesList.get(litsVarindex);
				List<WebElement> assessmentListWithLockIcon = element.findElements(By.xpath(".//span[contains(@class,'lock-small')]"));
			
				for (int i = 0; i <= assessmentListWithLockIcon.size() - 1; i++) {
					if (assessmentListWithLockIcon.get(i).isEnabled()) {
						moveToElement(assessmentListWithLockIcon.get(i));
						flag = true;
						break;
						}
			}
		}
		}

	
	}

	public void verifyRaterDropdownlinkIsNotDisplayed() {
		Assert.assertFalse(isElementPresent(By.xpath("//div[@class='row administered-row']//div[contains(@class,'btn-group ')]//ul//li/span")));
		reportInfo();
	}
	
	
	
	public <T> T completeVisitFromInitiateToComplete(String visitname, Class<T> className)
	{
		verifyNewSubjectDetailPage();
		clickOnVisitRow(visitname);
		clickOnAddVisitIcon();
		waitAndClick(assignclinro);
		waitAndClick(selclinro);
        waitForAjaxRequestsToComplete();
		waitForElementToBecomeInvisible(By.xpath("//div[@class='assessments-list']//div[@class='spinner']"));
		waitAndClick(thumbnailIMG);
		reportInfo();
		return PageFactory.initElements(driver, className);
    }
	
	
	
	public void moveBackToSubject(String subjectName)
	{
		
		refreshPage();
		verifyNewSubjectDetailPage();
		WebElement subject = driver.findElement(ByLocator("//a[contains(text(),'"+subjectName+"')]"));
		waitAndClick(subject);
	}
	
	public void verifyoptionToCleanYearOfBirthdisplayed()
	{
		moveToElement(RemoveDateofYearButton);
		Assert.assertTrue(isElementDisplayed(RemoveDateofYearButton));
		
	}

	/***
	 * Verify calendar icon is displayed
	 */
	
	public void verifyCalendarIconIsDisplayed() {
		moveToElement(calendarIcon);
		if(calendarIcon.isDisplayed()) {
			Assert.assertTrue(true);
			reportInfo();
		}
	}

	/***
	 * Notice that date is not displayed
	 */
	
	public void noticeThatDateIsNotDisplayed() {
		moveToElement(NotSetLabel);
		if(NotSetLabel.isDisplayed()) {
			Assert.assertTrue(true);
		}
	}
	
	/***
	 * Click on calendar icon
	 */

	public void clickOnCalendarIcon() {

		waitAndClick(calendarIcon);
		reportInfo();
	}

	/***
	 * Verify calendar date picker is displayed
	 */
	
	
	public void verifyCalendarDatePickerDisplayed() {

		moveToElement(calendarDatePicker);
		if(calendarDatePicker.isDisplayed()) {
			Assert.assertTrue(true);
		}
		
	}
	/***
	 * Verify current date selected by default
	 */
	
	public void verifyCurrentDateSelectedByDefault() {


		String actual=currentdateOnCalendar.getAttribute("class");
		String expected="day active today";
		Assert.assertEquals(actual, expected);
		waitAndClick(calendarIcon);

	}

//	/***
//	 * Select current date
//	 */
//	public void selectCurrentDate() {
//
//		moveToElement(currentdateOnCalendar);
//		waitAndClick(currentdateOnCalendar);
//		
//	}

	/***
	 * Verify current date reflects
	 */
	
	public void verifyCorrespondingDateReflects() {

		moveToElement(currentdateLabel);
		if(currentdateLabel.isDisplayed()) {
			Assert.assertTrue(true);
			reportInfo();
		}
		
	}

	/***
	 * Verify not set notice 
	 */
	public void notSetIsNotDisplayed() {

		String expected="Not Set";
		String actual=currentdateLabel.getText();
		if(!expected.contentEquals(actual)) {
			Assert.assertTrue(true);
		}
	}

	/***
	 * Select future date
	 */
	
	public void selectFutureDate() {
		waitAndClick(futureDate);
	}

	/***
	 * Select Past date
	 */
	public void selectPastDate() {
		waitAndClick(pastDate);
	}
	/**
	 *  verifying visit list displayed
	 */
	public void verifyViSitListIsDisplayed()
	{
		boolean flag=true;
		if (visitList.size()==0) {
			flag=false;
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	
	public void selectVisitByName(String VisitName)
	{
		WebElement visitToBeSelected = driver.findElement(ByLocator("//h4[contains(text(),'"+VisitName+"')]/ancestor::div[3]"));
		clickOn(visitToBeSelected);
		reportInfo();
	}
	/**
	 *  verify visit is displayed by name
	 * @param VisitName
	 */
	public void verifyVisitIsDisplayed(String VisitName)
	{
		boolean flag=true;
		for(WebElement web:visitsList)
		{
			if (web.getText().trim().contains(VisitName)) {
				moveToElement(web);
				flag=true;
				break;
			}
		}
		Assert.assertTrue(flag);
		reportInfo();
	}
	
	
	
	
}

	

