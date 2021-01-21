package net.medavante.portal.pages.centralrating;

import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.selenium.core.BasePage;

public class CentralRatingAppointmentPage extends BasePage {

	public CentralRatingAppointmentPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[@class='btn btn-active' and @title='Initiate']")
	private WebElement initiateBTN;

	@FindBy(xpath = "//button[@class='btn btn-active' and @title='Initiate']")
	private List<WebElement> initiateBT;

	@FindBy(xpath = "//label[contains(text(),'Alias')]/following-sibling::input")
	private WebElement subjectAliasINP;

	@FindBy(xpath = "//div[@class='btn btn-default' and @data-ng-click='cancelClick()']")
	private WebElement popUpNoBTN;

	@FindBy(xpath = "//div[@class='btn btn-default' and @data-ng-click='onCancelAppointmentCancelation()']")
	private WebElement popUpNoBTNForCancelAppointment;

	@FindBy(xpath = "//div[@id='queryConfirmation']//div[@class='btn btn-active']")
	private WebElement popUpYesBTN;

	@FindBy(xpath = "//div[contains(@data-ng-disabled,'isNeedReason')]")
	private WebElement cancelAppointmentPopUpYesBTN;

	@FindBy(xpath = "//h2[@class='ng-binding']")
	private WebElement visitStatusText;

	@FindBy(xpath = "//button[@class='btn btn-active' and @title='Recall Assessment']")
	private WebElement recallBTN;

	@FindBy(xpath = "//button[@data-ng-click='cancelAppointment()']")
	private WebElement cancelAppointmentBTN;
	
	@FindBy(xpath = "//span[@data-ng-if='isUpdateAppointmentOption()']")
	private WebElement updateAppointmentBTN;

	@FindBy(xpath = "//button[@title='Back']")
	private WebElement backBTN;

	@FindBy(xpath = "//div[@class='modal fade modalshow in']//div[@class='modal-body']//label[contains(@class,'ng-binding')]")
	private WebElement popUpConfirmationMeassage;

	@FindBy(xpath = "//div[@id='page-title']/h1[text()='Appointment']")
	private WebElement appointmentTitle;

	@FindBy(xpath = "//label[@class='small-title' and text()='Study']/following-sibling::label")
	private WebElement studyNameLabelTXT;

	@FindBy(xpath = "//label[@class='small-title' and text()='Site']/following-sibling::label")
	private WebElement siteNameLabelTXT;

	@FindBy(xpath = "//label[@class='small-title' and text()='Subject']/following-sibling::div/span//a")
	private WebElement subjectNameLabelTXT;

	@FindBy(xpath = "//a[@class='circle-button btn btn-white' and @title='Clear Notification Date']")
	private WebElement notificationDateClearICN;

	@FindBy(xpath = "//a[@class='circle-button btn btn-white' and @title='Clear Schedule Date']")
	private WebElement scheduleDateClearICN;

	@FindBy(xpath = "//div[@data-date='notificationDate']//div[@id='datepicker']")
	private WebElement notificationDatePickerBTN;

	@FindBy(xpath = "//div[@data-date='scheduledDate']//div[@id='datepicker']")
	private WebElement scheduleDatePickerBTN;

	@FindBy(xpath = "//td[contains(@class,'today')]")
	private WebElement notificationCurrentDate;

	@FindBy(xpath = "(//td[contains(@class,'today')])[2]")
	private WebElement scheduleCurrentDate;

	@FindBy(xpath = "(//td[contains(@class,'today')]/preceding-sibling::td[3]")
	private WebElement schedulePastDate;
	
	@FindBy(xpath = "(//td[contains(@class,'today')]/following-sibling::td[3]")
	private WebElement scheduleFutureDate;
	
	@FindBy(xpath = "(//td[contains(@class,'today')])[2]/following-sibling::td[2]")
	private WebElement scheduleDelayDate;

	@FindBy(xpath = "//a[@class='circle-button btn btn-white' and @title='Save']")
	private WebElement saveBTN;

	@FindBy(xpath = "//label[@class='title-grey']/a[text()='Pick One']")
	private WebElement pickClinicianLink;

	@FindBy(xpath = "//label[@class='title-grey']//a[@class='a-color ng-binding ng-scope']")
	private WebElement clinicanNamelinkTXT;

	@FindBy(xpath = "//div[@class='row grid-row ng-scope']//div[@class='slot ng-scope']/label")
	private List<WebElement> timeSlotList;

	@FindBy(xpath = "//div[@class='row grid-row ng-scope']//div[@class='slot ng-scope']")
	private WebElement timeSlotdiv;

	@FindBy(xpath = "//div[@class='row grid-row ng-scope']/div/label")
	private List<WebElement> columnOfClinicanList;

	@FindBy(xpath = "//div[@class='col-xs-24 portal-grid']//div[@class='btn-wrapper']//a")
	private WebElement refreshICN;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//span[@class='timepicker-hour']")
	private WebElement hourLINK;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//div[@data-action='selectHour']//tr//td")
	private List<WebElement> hourLIST;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//span[@class='timepicker-minute']")
	private WebElement minutesLINK;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//div[@data-action='selectMinute']//tr//td")
	private List<WebElement> minutesLIST;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//button[@data-action='togglePeriod']")
	private WebElement timeAMarker;

	@FindBy(xpath = "//div[@id='timepicker']")
	private WebElement startedTimePickerBTN;

	@FindBy(xpath = "//button[@class='btn btn-active ng-scope' and @data-ng-click='scheduleAppointment()']")
	private WebElement scheduleAppointmentBTN;

	@FindBy(xpath = "//div[@class='btn-group ng-isolate-scope']//button")
	private WebElement reasonDRPDWN;

	@FindBy(xpath = "//div[@class='btn-group ng-isolate-scope open']//ul/li/span")
	private List<WebElement> reasonForChangeList;

	@FindBy(css = "*[class*='ng-scope'] textarea")
	private WebElement reasonCommentTxtArea;

	@FindBy(xpath = "//div[@class='date-info']//label[@data-ng-model='notificationDate']")
	private WebElement notificationDateTimeText;

	@FindBy(xpath = "//div[@class='date-info']//label[@data-ng-model='scheduledDate']")
	private WebElement appointmentDateTimeText;

	@FindBy(xpath = "//label[@data-ng-model='model.scheduleDetails.scheduleTimeZoneAbbreviation']")
	private WebElement appointmentTimeZoneText;

	@FindBy(xpath = "//div[contains(@class,'picker-open')]//tbody//td[@class='day']")
	private List<WebElement> activeDateList;

	@FindBy(xpath = "//div[@class='row grid-row ng-scope']/div/label[@class='a-color ng-binding']")
	private List<WebElement> clinicanNamesList;

	@FindBy(xpath = "//label[contains(@class,'selected')]")
	private WebElement selectedClinicianTime;

	@FindBy(xpath = "//div[@class='row grid-row ng-scope']")
	private List<WebElement> clinicanRowList;

	@FindBy(xpath = "//label[@class='none time-slot ng-binding ng-scope']")
	private WebElement noneTimeSlot;

	@FindBy(xpath = "//div[@class='row grid-header']//label")
	private List<WebElement> Text;

	@FindBy(xpath = "//div[@id='breadcrumbs']/a[@class='home']")
	private WebElement homeICN;

	@FindBy(xpath = "//div[@class='btn-wrapper']/a[@title='Visit History']")
	private WebElement visitHistoryModelWindowICN;

	@FindBy(xpath = "//div[@class='table-frame-body']/div[@class='row no-padding-left ng-scope']")
	private List<WebElement> modelWiindowVisitHistoryRow;

	@FindBy(xpath = "//div[@id='subj-visit-history-dialog']//div[@class='modal-content']")
	private WebElement modelWindowScreen;

	// Modal Window
	@FindBy(xpath = "(//div[@class='filter']//button)")
	private WebElement allDetailDRPDWNonModalWindow;

	@FindBy(xpath = "//div[@class='filter']//li/span")
	private List<WebElement> allDetailDRPDWNonModalWindowLIST;

	@FindBy(xpath = "(//div[@class='col-xs-8 date-col']/span)")
	private WebElement modalWindowNotesDateAndTimeTXT;

	@FindBy(xpath = "(// div[@class='col-xs-16']//b)")
	private WebElement modalWindowNoteAddedByTXT;

	@FindBy(css = "div[class='col-xs-16'] div[class='ng-binding']")
	private List<WebElement> modalWindowAddedEvents;

	@FindBy(xpath = "//div[@ng-repeat='item in filteredHistoryItems']//div[2]/div")
	private WebElement modalWindowNoteTextTXT;

	@FindBy(xpath = "//div[@class='modal-header has-action']//button[@data-ng-click='cancelClick()']")
	private WebElement modalWindowCancelIcon;

	@FindBy(css = "div[class='modal-footer no-border'] div[data-ng-click='cancelClick()']")
	private WebElement modalWindowCloseIcon;

	@FindBy(xpath = "//a[@class='btn circle-button btn-white' and @title='Save']")
	private WebElement modalWindowSaveIcon;

	@FindBy(xpath = "//a[@class='btn circle-button btn-white disabled' and @title='Save']")
	private WebElement modalWindowSaveIcon_disabled;

	@FindBy(xpath = "//a[@class='btn circle-button btn-white' and @title='Cancel']")
	private WebElement modalWindowAddNoteCancelIcon;

	@FindBy(xpath = "//a[@title='Add Note']")
	private WebElement modalWindowAddNewNoteBTN;

	@FindBy(css = "html body[contenteditable='true']")
	private WebElement modalWindowAddEditNoteTextBox;

	@FindBy(xpath = "//div[@class='modal-content']//div[@data-ng-if='isAddNote']")
	private WebElement modalWindowAddEditAddNote;

	@FindBy(xpath = "(//a[@data-action='incrementMinutes'])[1]")
	private WebElement incrementMinuteICN;

	@FindBy(xpath = "//div[@class='btn btn-active ng-binding' and @data-ng-click='cancelClick()']")
	private WebElement closeModelWindowVisitHistoryBTN;

	@FindBy(xpath = "//div[contains(@class,'success-info-container')]//label[contains(text(),'successfully')]")
	private WebElement successMessage;

	@FindBy(xpath="//div[contains(@class,'picker-open')]//*[@class='datepicker-days']//tr//th[@class='next']")
	private WebElement nextCalenderBTN;
	
	@FindBy(xpath="//div[contains(@class,'picker-open')]//*[@class='datepicker-days']")
	private WebElement visitProjectionScheduleCalenderDateWindow;
	
	@FindBy(xpath="//div[contains(@class,'picker-open')]//*[@class='timepicker-picker']")
	private WebElement timePickerOpenWindow;
	
	@FindBy(xpath="//div[contains(@class,'date-info')]//label[@data-ng-model='notificationDate']")
	private WebElement notificationdatefield;

	@FindBy(xpath="//div[@class='date-info']//label[@data-ng-model='scheduledDate']")
	private WebElement scheduledateField;
	
/***
 * ========
 */
	@FindBy(xpath = "(//td[contains(@class,'today')])[1]")
	private WebElement notificationCurrentDate1;

	@FindBy(xpath = "(//td[contains(@class,'today')])[1]/preceding-sibling::td[3]")
	private WebElement notificationPastDate1;
	
	@FindBy(xpath = "(//td[contains(@class,'today')])[1]/following-sibling::td[3]")
	private WebElement notificationFutureDate1;
	
	@FindBy(xpath = "(//td[contains(@class,'today')])[2]")
	private WebElement scheduleCurrentDate1;

	@FindBy(xpath = "(//td[contains(@class,'today')])[2]/preceding-sibling::td[3]")
	private WebElement schedulePastDate1;
	
	@FindBy(xpath = "(//td[contains(@class,'today')])[2]/following-sibling::td[3]")
	private WebElement scheduleFutureDate1;
	
	@FindBy(xpath = "(//div[@id='timepicker'])[1]")
	private WebElement notificationtimePicker;
	
	@FindBy(xpath ="(//div[@id='timepicker'])[2]")
	private WebElement scheduletimePicker;
	
	@FindBy(xpath="(//span[@class='icon-chevron-down'])[1]")
	private WebElement notificationHourDown;
	
	@FindBy(xpath="(//span[@class='icon-chevron-down'])[2]")
	private WebElement notificationMinDown;
	
	@FindBy(xpath="(//span[@class='icon-chevron-down'])[3]")
	private WebElement scheduleHourDown;
	
	@FindBy(xpath="(//span[@class='icon-chevron-down'])[4]")
	private WebElement scheduleMinDown;
	

	
	

	List<String> reasonChangeValuesList = new ArrayList<>(
			Arrays.asList("Scheduling Adjustment", "Site Request", "Rater Not Available", "Technical Difficulties"));

	protected String date = currentDate();

	/** Verify Initiate Button Is Disabled */
	public void verifyInitiateButtonIsDisabledAfterRemovingSubjectAlias() {
		waitSpinnerToBecomeInvisible();
		subjectAliasINP.clear();
		Assert.assertFalse(initiateBTN.isEnabled());
	}

	/** Verify Initiate Button Is Enabled */
	public void verifyInitiateButtonIsEnabled() {
		Assert.assertTrue(initiateBTN.isEnabled());
	}

	/** Fill Subject Alias Field */
	public void fillSubjectAliasField() {
		new WebDriverWait(driver, DEFAULT_WAIT_4_ELEMENT).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//label[contains(text(),'Alias')]/following-sibling::input[contains(@class,'ng-invalid')]")));
		waitForElementPresent(subjectAliasINP, DEFAULT_WAIT_4_PAGE);
		inputText(subjectAliasINP, "Sub" + generateRandomString(3));
	}

	/** Click On Initiate Button */
	public void clickOnInitiateAppointment() {
		boolean flag = false;
		try {
			Assert.assertTrue(initiateBTN.isEnabled());
			waitAndClick(initiateBTN);
			new WebDriverWait(driver, DEFAULT_WAIT_4_PAGE).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='queryConfirmation']//div[@class='modal-content']")));
			flag = true;

		} catch (Exception exception) {
			waitAndClick(initiateBTN);
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();

	}

	/** Cancel Pop Up Confirmation */
	public void cancelConfirmation() {
		clickOn(popUpNoBTN);
	}

	/** Verify Visit status */
	public void verifyVisitStatus(String status) {
		waitForPageLoaded();
		String[] splitString = getText(visitStatusText).split("\\:");
		Assert.assertEquals(status, splitString[1].trim());
	}

	/** Confirmation Of PopUp Message */
	public NewSubjectDetailPage confirmationOfPopUpMessage() {
		popUpYesBTN.click();
		waitForElementToBecomeInvisible(By.xpath("//div[@data-ng-show='isBusy' and @class='ng-isolate-scope']"));
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);

	}

	/** Navigate To Listing Page */
	public CentralRatingAssessmentsListingPage navigateToListingPage() {
		clickOn(backBTN);
		return PageFactory.initElements(driver, CentralRatingAssessmentsListingPage.class);
	}

	/**
	 * Verify Initiate Button Not Visible
	 * 
	 * @return
	 */
	public boolean initiateButtonNotVisible() {
		int size = initiateBT.size();
		boolean flag = true;
		if (size < 0) {
			flag = false;
			Assert.assertFalse(flag, "Element Not present");
		} else {
			return flag;
		}
		return flag;
	}

	/** Verify Recall Button Is Disabled */
	public void verifyRecallButtonIsEnabled() {
		waitSpinnerToBecomeInvisible();
		Assert.assertTrue(recallBTN.isEnabled());
	}

	/** Click On Recall Button */
	public void clickOnRecall() {
		boolean flag = true;
		try {
			clickOn(recallBTN);
			Assert.assertTrue(popUpConfirmationMeassage.isDisplayed());
			flag = true;
		} catch (Exception e) {
			clickOn(recallBTN);
			flag = true;
		}
		Assert.assertTrue(flag);
		reportInfo();
		Assert.assertTrue(popUpConfirmationMeassage.isDisplayed());

	}

	/** Verify Appointment Page */
	public void verifyAppointmentPage() {
//		waitSpinnerToBecomeInvisible();
		waitUntillFinishProcessSpinnerDisable();
		Assert.assertTrue(isElementPresent(appointmentTitle));
		reportInfo();
	}

	/** Verify Visit Information */
	public void verifyVisitInformation(String studyNameAppointmentPage, String siteNameAppointmentPage) {
		waitSpinnerToBecomeInvisible();
		Assert.assertTrue(studyNameLabelTXT.getText().contains(studyNameAppointmentPage));
		Assert.assertTrue(siteNameLabelTXT.getText().contains(siteNameAppointmentPage));
	}

	/** Select Notification current Date and Time */
	public void setNotificationDate() {
		waitAndClick(notificationDatePickerBTN);
		clickOn(notificationCurrentDate);

	}
	/** Select Notification current Date  */
	
	public void setNotificationCurrentDate() {
		waitAndClick(notificationDatePickerBTN);
		  waitAndClick(notificationCurrentDate1);
		  }
	
		/**
	 * notification past date
	 */
	public void setNotificationPastDate() {
		waitAndClick(notificationDatePickerBTN);
       waitAndClick(notificationPastDate1);
	}
	
	/**
	 * notification future date
	 */
	public void setNotificationFutureDate() {
		waitAndClick(notificationDatePickerBTN);
	       waitAndClick(notificationFutureDate1);

	}
	/***
	 * Select Schedule current Date
	 */
	public void setScheduleCurrentDate() {
		waitAndClick(scheduleDatePickerBTN);
     waitAndClick(scheduleCurrentDate1);
	}

	/***
	 * Select Schedule past Date
	 */
	public void setSchedulePastDate() {
		waitAndClick(scheduleDatePickerBTN);
       waitAndClick(schedulePastDate1);		
}

	/***
	 * Select Schedule future Date
	 */
	public void setScheduleFutureDate() {
		waitAndClick(scheduleDatePickerBTN);
     waitAndClick(scheduleFutureDate1);
	
	}
	/***
	 * change notification time
	 */
	public void changeNotificationTime() {
    waitAndClick(notificationHourDown);
	waitAndClick(notificationMinDown);
	clickOn(notificationtimePicker);	
	}
	
	/***
	 * change schedule time
	 */
	public void changeScheduleTime() {
		waitAndClick(scheduletimePicker);
		waitAndClick(scheduleHourDown);
		waitAndClick(scheduleMinDown);
		
	}
	

	/** Select Appointment Date and Time */

	public void setAppointmentDate() {
		waitAndClick(scheduleDatePickerBTN);
		waitAndClick(scheduleCurrentDate);

	}
	/*Select Current date*/
	public void selectAnyDateOnProjectWindow()
	{
		clickOn(scheduleCurrentDate);
	}

	/** Select Appointment Date and Time For Tommorrow */

	/** Verify Notification Date ,Time Has Been Set */
	public void verifyNotificationDateTimeSet(String timeForNotification) {
		String date = currentDate();
		String[] splitString = getText(notificationDateTimeText).split("\\s");
		Assert.assertTrue(splitString[0].equalsIgnoreCase(date));
		Assert.assertTrue(splitString[1].equalsIgnoreCase(timeForNotification));

	}

	/** Verify Appointment Date,Time Has Been Set */
	public void verifyAppointmentDateTimeSet(String timeForAppointment) {
		String[] splitString = getText(appointmentDateTimeText).split("\\s");
		Assert.assertTrue(splitString[0].equalsIgnoreCase(date));
		Assert.assertTrue(splitString[1].equalsIgnoreCase(timeForAppointment));

	}

	/** Save Option Is Available */
	public void saveOptionEnabled() {
		Assert.assertTrue(saveBTN.isEnabled());
	}

	/** Click On Refresh icon */
	public void clickOnrefreshIcon() {
		waitAndClick(refreshICN);

	}

	/** Click On Pick One Clinician Link */
	public void clickOnPickClinician() {
		
		waitAndClick(pickClinicianLink);
		waitUntillSpinnerToBecomeInvisible();
	}

	/**
	 * Click On Previously Schedule Clinican lINK
	 * 
	 */
	public void clickOnPreviouslyScheduleClinicianLink() {
		waitAndClick(clinicanNamelinkTXT);
		waitSpinnerToBecomeInvisible();

	}

	/** Save Option Disabled */
	public void saveOptiondisabled() {
		waitSpinnerToBecomeInvisible();
		Assert.assertFalse(!saveBTN.isEnabled());
	}

	/** Deselect Clinician */
	public void deselectClinician() {
		waitForElementClickable(selectedClinicianTime, DEFAULT_WAIT_4_PAGE);
		javascriptButtonClick(selectedClinicianTime);
	}

	/** Click On Save Appointment Option */
	public void clickOnSave() {
		waitAndClick(saveBTN);
		waitSpinnerToBecomeInvisible();
	}

	/**
	 * Set Time
	 * 
	 * @param hourToBeSelect
	 *            (Range Select from 01 To 12)
	 * @param minutesToBeSelected
	 *            (Range Select from 00 To 55 with the difference of 5 Minute
	 *            interval)
	 * @param timeMarker
	 *            (Select AM or PM)
	 */
	public void setStartedTime(String hourToBeSelect, String minutesToBeSelected, String timeMarker) {
    
		_normalWait(2000);
		waitAndClick(hourLINK);
		for (WebElement hourTXT : hourLIST) {
			if (getText(hourTXT).equalsIgnoreCase(hourToBeSelect)) {
				clickOn(hourTXT);
				break;
			}
		}
		waitAndClick(minutesLINK);
		for (WebElement minuteTXT : minutesLIST) {
			if (getText(minuteTXT).equalsIgnoreCase(minutesToBeSelected)) {
				clickOn(minuteTXT);
				break;
			}
		}
		if (getText(timeAMarker).equalsIgnoreCase(timeMarker) == false) {
			clickOn(timeAMarker);
		}
		waitAndClick(startedTimePickerBTN);

	}

	/* Click On Schedule Appointment Button */
	public void clickOnScheduleAppointment() {
		waitAndClick(scheduleAppointmentBTN);
		waitForElementToBecomeInvisible(By.xpath("//div[@data-ng-show='isBusy' and @class='ng-isolate-scope']"));

	}

	/** Click On Cancel Appointment */
	public void clickOnCancelAppointment() {
		_normalWait(2000);
		waitAndClick(cancelAppointmentBTN);
	}

	/** Verify Cancel Button Is Present And Enabled */
	public void verifyCancelButtonIsPresentAndEnabled() {
		Assert.assertTrue(isElementPresent(cancelAppointmentBTN));
		if (cancelAppointmentBTN.isEnabled()) {
			Assert.assertTrue(true, "Cancel Appointment Button Is Enabled");
		} else {

			Assert.assertFalse(false, "Cancel Appointment Button Is Disabled");
		}

	}

	/** Verify Update Button Is Present And Enabled */
	public void verifyUpdateScheduledButtonIsPresentAndEnabled() {
		Assert.assertTrue(isElementPresent(scheduleAppointmentBTN));
		if (scheduleAppointmentBTN.isEnabled()) {
			Assert.assertTrue(true, "Scheduled Appointment Button Is Enabled");
		} else {

			Assert.assertFalse(false, "Scheduled Appointment Button Is Disabled");
		}

	}

	/**
	 * Cancel Appointment reason to select
	 * 
	 * @param reasonToSelect
	 */
	public void selectAppointmentReasonAndComment(String reasonToSelect, String CommentText) {
		for (WebElement reason : reasonForChangeList) {
			waitForElement(reason);
			if (getText(reason).trim().equalsIgnoreCase(reasonToSelect)) {
				waitAndClick(reason.findElement(By.xpath("./parent::li")));
				break;
			}

		}
		inputText(reasonCommentTxtArea, CommentText);
	}

	/** Click On Reason dropDown **/
	public void clickOnReasonDropDown() {
		waitForWebElementPresent(reasonDRPDWN);
		clickOn(reasonDRPDWN);

	}

	/** Verify Reason Option To Select And Reason Comment Is Available */
	public void verifyOptionToSelectAndReasonCommentIsAvailable() {
		List<String> reasonForChange = new ArrayList<>();
		for (WebElement reasonChangeValues : reasonForChangeList) {
			reasonForChange.add(getText(reasonChangeValues).trim());
		}
		Assert.assertEquals(reasonForChange, reasonChangeValuesList, "Reason For Change Values Are Present");
		Assert.assertTrue(isElementPresent(reasonCommentTxtArea));
	}

	/** Cancel Confirmation For Cancel Appointment */
	public void cancelConfimartionForCancelAppointment() {
		clickOn(popUpNoBTNForCancelAppointment);
	}

	/** Confirm Confirmation Of Cancel Appointment */

	public void confirmConfirmationOfReasonPopUp() {
		clickOn(cancelAppointmentPopUpYesBTN);
		try {
			if (isElementPresent(By.xpath("//div[@data-ng-show='isBusy' and @class='ng-isolate-scope']"))) {
				waitForElementToBecomeInvisible(
						By.xpath("//div[@data-ng-show='isBusy' and @class='ng-isolate-scope']"));
			}
		} catch (Exception e) {
		}
	}

	/** Verify Clinician With Calender Is Showing */
	public void verifyClinicianWithCalenderIsDisplayed() {
		boolean flag = false;
		for (int clinicianRow = 0; clinicianRow < clinicanRowList.size(); clinicianRow++) {
			for (int informationOfClinician = clinicianRow; informationOfClinician < columnOfClinicanList
					.size(); informationOfClinician++) {
				WebElement element = columnOfClinicanList.get(informationOfClinician);
				if (element.getText().equalsIgnoreCase("Fully")) {
					flag = true;
					Assert.assertTrue(flag, "The clinician with calender is Present");
					return;
				} else {
					Assert.assertFalse(flag, "The clinician with calender is not Present");
				}
			}
		}
	}

	/** Verify Confirmation Pop Up Message For Appointment */
	public void verifyPopUPConfirmationMessageForAppointment(String firstMessage) {
		
		Assert.assertTrue((popUpConfirmationMeassage.getText().toLowerCase().contains(firstMessage.toLowerCase())));

	}

	/** Click On Date Time Clear Icon **/

	public void clearDateTime() {
		clickOn(notificationDateClearICN);
		clickOn(scheduleDateClearICN);
	}

	public void clearNotoficationDateTime() {
		clickOn(notificationDateClearICN);

	}

	public void clearScheduleDateTime() {
		clickOn(scheduleDateClearICN);
	}

	/** Schedule Appointment For Future Day */
	public void scheduleAppointmentForNextDate() {
		
		waitAndClick(scheduleDatePickerBTN);
		String dateToday=scheduleCurrentDate.getText();
		int date=Integer.parseInt(dateToday);
		String attributeToday=scheduleCurrentDate.getAttribute("class");
		
		if(date == 28 || date== 29 || date==30 || date==31)
		{
			if(attributeToday.contains("today"))
			{
			 waitForElementClickable(nextCalenderBTN, DEFAULT_WAIT_4_PAGE);
			 waitAndClick(nextCalenderBTN);
			}
		}
		selectFurtureDateFromCalender();
		
	}
	
	/*Select Future date From Visit Calendar*/
	public void selectFurtureDateFromCalender()
	{
		boolean flag=false;
		for (WebElement activeDate : activeDateList) {
			if (getText(activeDate).equalsIgnoreCase(dateCalendarRef.getFutureDate())) {
				clickOn(activeDate);
				flag=true;
				break;
			}
		}
		Assert.assertTrue(flag," Date selected ");
	}

	/** Schedule Notification For Future Day */
	public void scheduleNotificationAndAppointmentDateForNextDate() {
		waitForAjaxRequestsToComplete();
		waitAndClick(notificationDatePickerBTN);
		for (WebElement activeDate : activeDateList) {
			if (getText(activeDate).equalsIgnoreCase(dateCalendarRef.getFutureDate())) {
				clickOn(activeDate);
				break;
			}
		}
	}

	/** Verify Currently Scheduled Clinician Is Shown */
	public void verifyScheduledClinicanIsShown() {
		Assert.assertTrue(clinicanNamelinkTXT.getText().contains("VP Tester"));
	}

	/** Verify Option To Pick Clinician Shown */
	public void verifyOptionToPickClinicianShown() {
		waitForElement(pickClinicianLink);
		Assert.assertTrue(isElementPresent(pickClinicianLink));

	}

	/** Get Clinican Name */
	public String getPreviouslyScheduledClinicanName() {
		String ClinicanName = clinicanNamelinkTXT.getText();
		return ClinicanName;
	}

	/** Navigate To Subject Details Page */
	public NewSubjectDetailPage navigateToSubjectDetailsPage() {
		clickOn(backBTN);
		return PageFactory.initElements(driver, NewSubjectDetailPage.class);
	}

	/** Click On Clinican Link **/
	public void clickOnClinicanLink() {
		clickOn(clinicanNamelinkTXT);
		waitSpinnerToBecomeInvisible();
	}

	/* Get middle Message */
	public String getMiddleMessage() {
		String timeZone = appointmentTimeZoneText.getText();
		String timez = timeZone.substring(1, 4);
		String[] splitString = getText(appointmentDateTimeText).split("\\s");
		String dateForPop = splitString[0];
		String TimeForPopUp = splitString[1];
		String meridiem = splitString[2];
		String middlePopUpMessage = " " + dateForPop + " " + "at" + " " + TimeForPopUp + " " + meridiem + " " + timez;
		return middlePopUpMessage;
	}

	/** Get Other Clinician Name Which Is not Currently Scheduled */
	public String getNameOfNewClinician() {
		String currentClinicanName = getPreviouslyScheduledClinicanName();
		String name = null;
		for (int i = 0; i < clinicanNamesList.size(); i++) {
			WebElement element = clinicanNamesList.get(i);
			if ((!element.getText().equalsIgnoreCase(currentClinicanName))) {
				name = element.getText();
				break;
			}
		}
		return name;

	}

	/**
	 * Click on row on behalf of column name
	 * 
	 * @param columnName
	 * @param rowValue
	 */
	public void clickOnRowByColumnAndRowValue(String columnName, String rowValue, String time) {
		_normalWait(1000);
		for (int columnNameIndex = 0; columnNameIndex < Text.size(); columnNameIndex++) {
			if (getText(Text.get(columnNameIndex)).trim().equalsIgnoreCase(columnName)) {
				int columnNameIndex1 = columnNameIndex + 2;
				for (int recordRow = 0; recordRow < clinicanRowList.size(); recordRow++) {
					if (getText(clinicanRowList.get(recordRow)
							.findElement(By.xpath("./div[" + columnNameIndex1 + "]//label"))).trim()
									.contains(rowValue)) {
						List<WebElement> timeSlotSelect = clinicanRowList.get(recordRow)
								.findElements(By.xpath(".//div//label[contains(@class,'time-slot')]"));
						for (WebElement element : timeSlotSelect) {
							if (getText(element).equalsIgnoreCase(time)) {
								waitAndClick(element);
								break;
							}

						
						}
						break;

					}
				}
				break;
			}
		}
		_normalWait(4000);
	}

	/** Return Back to Home Page */
	public MedAvantePortalPage navigateToHomePage() {
		clickOn(homeICN);
		return (PageFactory.initElements(driver, MedAvantePortalPage.class));
	}

	/** Click On Model Window Icon */

	public void clickOnVisitHistoryModelWindow() {
		clickOn(visitHistoryModelWindowICN);
	}

	/** Verify Model Window Displayed */
	public void verifyModelWindowDisplayed() {
		waitSpinnerToBecomeInvisible();
		waitForElement(modelWindowScreen);
		Assert.assertTrue(isElementPresent(modelWindowScreen));
	}

	/** Check Appointment History Contains The Information */
	public void checkAppointmentHistoryContainsInformation(String clinicianName, String Time, String changeReason,
			String changeComment, String appointmentType) {
		boolean flag = false;
		for (int i = 0; i < modelWiindowVisitHistoryRow.size(); i++) {
			WebElement element = modelWiindowVisitHistoryRow.get(i);
			String text = getText(element.findElement(By.xpath(".//div[@class='ng-binding']")));
			String[] splitString = text.split("\\n");
			String appointmentTypeToVerify = splitString[0].substring(11, 21);
			if (appointmentTypeToVerify.trim().equalsIgnoreCase(appointmentType)) {
				if (appointmentType.equalsIgnoreCase("Scheduled")) {
					String clicianNameHistory = splitString[1].substring(9);
					String ScheduleDateHistory = splitString[2].substring(14);
					Assert.assertTrue(clicianNameHistory.trim().equalsIgnoreCase(clinicianName)
							&& ScheduleDateHistory.trim().equalsIgnoreCase(Time));
					break;
				} else if (appointmentType.equalsIgnoreCase("Cancelled")) {
					String clicianNameHistory = splitString[1].substring(9);
					String ScheduleDateHistory = splitString[2].substring(14);
					String changeReasonHistory = splitString[3].substring(14);
					String changeCommentHistory = splitString[4].substring(22);
					Assert.assertTrue(clicianNameHistory.trim().equalsIgnoreCase(clinicianName)
							&& ScheduleDateHistory.trim().equalsIgnoreCase(Time)
							&& changeReasonHistory.trim().equalsIgnoreCase(changeReason)
							&& changeCommentHistory.trim().equalsIgnoreCase(changeComment));
					break;
				} else if (appointmentType.equalsIgnoreCase("Requested")) {
					String ScheduleDateHistory = splitString[1].substring(14);
					Assert.assertTrue(ScheduleDateHistory.trim().equalsIgnoreCase(Time));
					break;
				} else {
					Assert.assertTrue(flag, "Status Not Matched");
				}

			}
		}

	}

	/** Click On CLOSE Button Of Model Window Visit History */
	public void clickOnModelWindowVisitHisotryCloseButton() {
		clickOn(closeModelWindowVisitHistoryBTN);
	}

	/**
	 * Verify Appointment Details displayed*
	 * 
	 */
	public void verifyAppointmentDetailsDisplayed() {

		boolean flag = false;
		if (modelWiindowVisitHistoryRow.size() > 0) {
			flag = true;
			Assert.assertTrue(flag, "Details Is Present");
		} else {
			Assert.assertFalse(flag, "Details Is Not Present");
		}
	}

	/** Get Date Time form THe Appointment Area */
	public String getDateTimeForSchedulingHistory(String selectType) {
		String dateTimeAndTimeZone = findElement(
				By.xpath("//div[@class='date-info']//label[@data-ng-model='" + selectType + "']")).getText();
		String[] splitString = dateTimeAndTimeZone.split("\\s");
		String date = splitString[0];
		String time1 = splitString[1];
		String zone = splitString[2];
		String time = "";
		String dateTime = "";
		if (time1.startsWith("0")) {
			time = splitString[1].substring(1);
			dateTime = date + " " + time + " " + zone;

			time = splitString[1].substring(1);
			dateTime = date + " " + time + " " + zone;

		} else {
			time = splitString[1].substring(0);
			dateTime = date + " " + time + " " + zone;
		}

		return dateTime;
	}

	/**
	 * Select language from the drop down options to create the subject in add
	 * subject popup
	 * 
	 * @param languageToBeSelect
	 */
	public void modalWindowAllDetailsDropdown(String detailToBeSelected) {
		clickOn(allDetailDRPDWNonModalWindow);
		for (WebElement detail : allDetailDRPDWNonModalWindowLIST) {
			if (detail.getText().trim().equalsIgnoreCase(detailToBeSelected)) {
				clickOn(detail);
				break;
			}
		}
	}

	/** Verify Modal Window opened after clicking on Visit History Icon */
	public void verifyModalWindowOpenedWithNote() {

		clickOn(visitHistoryModelWindowICN);
		_normalWait(500);
		reportInfo();
		modalWindowAllDetailsDropdown("View Notes");
		reportInfo();

	}

	/** Verify details on the Note */
	public void verifyDeatilsDisplayedOnNote() {

		boolean timeFormatFlag = false;
		boolean dateFormatFlag = false;
		boolean noteAddedByFlag = false;
		boolean noteTextFlag = false;
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-uuuu");
		String systemFormatDateTime = currentTime.format(formatter);
		String dateStr = systemFormatDateTime.replace("-", "").replace(" ", "");

		String dateAndTimeSTR = modalWindowNotesDateAndTimeTXT.getText();
		String formattedDate = dateAndTimeSTR.replace("-", "").replace(":", "");

		int k = formattedDate.indexOf(" ", formattedDate.indexOf(" ") + 1);
		String dateFormat1 = formattedDate.substring(0, k);

		int i = dateFormat1.indexOf(" ", dateFormat1.indexOf(" "));
		String dateFormat3 = dateFormat1.substring(0, i);

		if (dateStr.length() == dateFormat3.length()) {
			dateFormatFlag = true;
			Assert.assertTrue(dateFormatFlag, "Date displayed in DDMMMYYYY Format");

		} else {
			Assert.assertFalse(dateFormatFlag);
		}
		if (dateAndTimeSTR.contains("AM") || dateAndTimeSTR.contains("PM")) {
			timeFormatFlag = true;
			Assert.assertTrue(timeFormatFlag, "Time displayed in HH::MM Format");
		} else {
			Assert.assertFalse(timeFormatFlag);
		}

		if (modalWindowNoteAddedByTXT.getText().length() > 0) {
			noteAddedByFlag = true;
			Assert.assertTrue(noteAddedByFlag, "Note Added By Clinician is present");
		} else {
			Assert.assertFalse(noteAddedByFlag);
		}

		if (modalWindowNoteTextTXT.getText().length() >= 0) {
			noteTextFlag = true;
			Assert.assertTrue(noteTextFlag, "Note Text Present.");
		} else {
			Assert.assertFalse(noteTextFlag);
		}
		clickOn(modalWindowCancelIcon);
	}

	public void verifyNoteTextOnModalWindow(String textToBeVerified) {
		boolean flag = false;
		if (modalWindowAddedEvents.size() >= 0) {
			for (WebElement noteText : modalWindowAddedEvents) {
				String[] addedNotes = noteText.getText().split(":");
				if (addedNotes[addedNotes.length - 1].equalsIgnoreCase(textToBeVerified)) {
					flag = true;
					Assert.assertTrue(flag, "Note Text Present.");
				}
			}
		} else {
			Assert.assertFalse(flag);
		}
	}

	public void verifyAbsenceOfUnsavedAddedNoteTextOnModalWindow(String textToBeVerified) {
		boolean flag = true;
		if (modalWindowAddedEvents.size() == 0) {

		} else if (modalWindowAddedEvents.size() >= 0) {
			for (WebElement noteText : modalWindowAddedEvents) {
				String[] addedNotes = noteText.getText().split(":");
				if (addedNotes[addedNotes.length - 1].equalsIgnoreCase(textToBeVerified)) {
					flag = false;
					break;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Verify Modal Window opened after clicking on Visit History Icon with
	 * Recent History
	 */
	public void verifyModalWindowOpenedWithRecentHistory() {

		clickOn(visitHistoryModelWindowICN);
		_normalWait(500);
		reportInfo();
		modalWindowAllDetailsDropdown("View History");
		reportInfo();
		clickOn(modalWindowCancelIcon);
		String dateAndTimeSTR = modalWindowNotesDateAndTimeTXT.getText();
		if (dateAndTimeSTR.contains("AM") || dateAndTimeSTR.contains("PM")) {

		}
	}

	public void closeModalWindow() {
		waitAndClick(modalWindowCloseIcon);
		_normalWait(500);
		reportInfo();
	}

	/** Verify Modal Window has Add note button available */
	public void verifyPresenceOfAddNoteButtonOnModalWindow() {
		clickOn(visitHistoryModelWindowICN);
		_normalWait(500);
		boolean flag = false;
		if (modalWindowAddNewNoteBTN.isDisplayed() || modalWindowAddNewNoteBTN.isEnabled()) {
			flag = true;
			Assert.assertTrue(flag, "Add New Button is available on the Modal Window");
			javascripctHilightingElement(modalWindowAddNewNoteBTN);
			reportInfo();
		} else {

			Assert.assertFalse(flag);
		}
		clickOn(modalWindowCancelIcon);
	}

	public void clickONAddNoteButtonOnModalWindow() {
		boolean flag = false;
		if (modalWindowAddNewNoteBTN.isDisplayed() || modalWindowAddNewNoteBTN.isEnabled()) {
			flag = true;
			Assert.assertTrue(flag, "Add New Button is available on the Modal Window");
			clickOn(modalWindowAddNewNoteBTN);
			reportInfo();
		} else {
			Assert.assertFalse(flag);
		}
	}

	public void verifyInactiveSaveControlIsDisplayedOnModalWindow() {
		boolean flag = false;
		waitForWebElementPresent(modalWindowAddEditAddNote);
		if (modalWindowAddEditAddNote.isDisplayed()) {
			Assert.assertTrue(modalWindowSaveIcon_disabled.isDisplayed());
		} else {
			Assert.assertFalse(flag);
		}
		reportInfo();
	}

	public void verifyActiveSaveControlIsDisplayedOnModalWindow() {
		boolean flag = false;
		if (modalWindowAddEditAddNote.isDisplayed()) {
			Assert.assertTrue(modalWindowSaveIcon.isDisplayed());
		} else {
			Assert.assertFalse(flag);
		}
		reportInfo();
	}

	public void selectSaveControlOnModalWindow() {
		boolean flag = false;
		if (modalWindowAddEditAddNote.isDisplayed()) {
			waitAndClick(modalWindowSaveIcon);
		} else {
			Assert.assertFalse(flag);
		}
		reportInfo();
	}

	public void verifyCancelControlIsDisplayedOnModalWindow() {
		boolean flag = false;
		if (modalWindowAddEditAddNote.isDisplayed()) {
			waitForElementClickable(modalWindowAddNoteCancelIcon, 5);
			Assert.assertTrue(modalWindowAddNoteCancelIcon.isDisplayed());
		} else {
			Assert.assertFalse(flag);
		}
		reportInfo();
	}

	public void selectCancelControlOnModalWindow() {
		boolean flag = false;
		if (modalWindowAddEditAddNote.isDisplayed()) {
			waitAndClick(modalWindowAddNoteCancelIcon);
		} else {
			Assert.assertFalse(flag);
		}
		reportInfo();
	}

	public void verifyVisitNotesModalWindowIsClosed() {
		boolean flag = false;
		try {
			if (modalWindowAddEditAddNote.isDisplayed())
				Assert.assertFalse(flag, "Visit Notes modal window is not closed ");
		} catch (Exception e) {
			flag = true;
		}
		Assert.assertTrue(flag, "Visit Notes modal window is closed ");
		reportInfo();
	}

	public void VerifyEditNoteIsAvailableUnderModelWindow() {
		Assert.assertTrue(modalWindowAddEditAddNote.isDisplayed());
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		Assert.assertTrue(modalWindowAddEditNoteTextBox.isEnabled());
		driver.switchTo().defaultContent();
		reportInfo();
	}

	public void enterValueInEditNoteUnderModelWindow(String ValueToBeEntered) {
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		inputText(modalWindowAddEditNoteTextBox, ValueToBeEntered);
		driver.switchTo().defaultContent();
		reportInfo();
	}

	public void verifyTextEnteredInEditNoteUnderModelWindow(String ValueToBeEntered) {
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		if (modalWindowAddEditNoteTextBox.getText() != null) {
			Assert.assertEquals(modalWindowAddEditNoteTextBox.getText().trim(), ValueToBeEntered);
		} else {
			Assert.assertFalse(false, "value is not displayed");
		}
		driver.switchTo().defaultContent();
		reportInfo();
	}

	public String verifyLenthOfTextEnteredInEditNoteUnderModelWindow(int lenthOfString) {
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		if (modalWindowAddEditNoteTextBox.getText() != null) {
			Assert.assertEquals(modalWindowAddEditNoteTextBox.getText().trim().length(), lenthOfString);
		} else {
			Assert.assertFalse(false, "value is not displayed");
		}
		driver.switchTo().defaultContent();
		reportInfo();
		return modalWindowAddEditNoteTextBox.getText().trim();
	}

	/** Verify Modal Window has Add note button displays Add Note in tool tip */
	public void verifyTooTipTextOfAddNoteButton() {
		clickOn(visitHistoryModelWindowICN);
		_normalWait(500);
		boolean flag = false;
		String toolTipTXT = getToolTip(modalWindowAddNewNoteBTN);
		if (toolTipTXT.contains("Add Note")) {
			flag = true;
			Assert.assertTrue(flag, "Add Note displays in tool tip of the button");
			reportInfo();
		} else {
			Assert.assertFalse(flag);
		}
		clickOn(modalWindowCancelIcon);
		navigateBack();
		spinnerBecomeInvisible();
	}

	public String getSystemTime() {
		// "hh" in pattern is for 12 hour time format and "aa" is for AM/PM
		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd-MMM-yyyy hh:mm aa");
		// Setting the time zone
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		String timeZone = dateTimeInGMT.format(new Date());
		return timeZone;

	}

	/** Verify Notification/Appointment Time Set AS Users TimeZone */
	public void verifyTimeZoneIsSameAsGivenTimeZone(String selectType, String usersTimeZone) {
		String timeZoneUser=usersTimeZone.toLowerCase();
		waitForElement(startedTimePickerBTN);
		String timezone = findElement(By.xpath("//div[@class='date-info']//label[@data-ng-model='" + selectType + "']"))
				.getText().toLowerCase();
		Assert.assertTrue(timezone.contains(timeZoneUser));
	}

	/** Verify Notification Time Updated */
	public void verifyUpdatedDateTime(String userTime) {
		String updatedTime = notificationDateTimeText.getText();
		Assert.assertNotSame("The Time Is Updated", userTime, updatedTime);

	}

	/** Increase Minute Time Interval */

	public void increaseOnemiuteTimeInterval() {
		waitAndClick(startedTimePickerBTN);
		clickOn(incrementMinuteICN);
		//waitAndClick(startedTimePickerBTN);

	}

	/** Verify Time Can Updated By One Minute Interval */

	public void verifyTimeUpdatedByOneMinuteIntervals(String updatedTimeset, String oneminuteIntervalTimeSet) {
		boolean flag = false;
		String[] splitString = updatedTimeset.split("\\s");
		String updateTime = splitString[1];
		String[] splitStringoneMinuteTime = oneminuteIntervalTimeSet.split("\\s");
		String oneMinuteTime = splitStringoneMinuteTime[1];
		int minuteFromUpdatedTimeSet = Integer.parseInt(updateTime.substring(3));
		int minuteFromOneminuteIntervalTimeSet = Integer.parseInt(oneMinuteTime.substring(3));

		int diffrenceOfTimeInterval = minuteFromOneminuteIntervalTimeSet - minuteFromUpdatedTimeSet;

		if (diffrenceOfTimeInterval == 1) {
			flag = true;
			Assert.assertTrue(flag, "Time Updated In One minute Interval");
		} else {
			Assert.assertFalse(flag, "Time Can Not Updated In One minute Interval");
		}
	}

	/** Get Time And Date */

	public String getDateTime(String type) {
		String dateTimeAndTimeZone = findElement(
				By.xpath("//div[@class='date-info']//label[@data-ng-model='" + type + "']")).getText();
		return dateTimeAndTimeZone;

	}

	/** Verify Success Message **/

	public void verifyMessage(String message) {
		clickOnRecall();
		clickOn(popUpYesBTN);
//		Assert.assertTrue(successMessage.isDisplayed());
		 Assert.assertEquals(successMessage.getText(), message);
		reportInfo();
		waitForElementToBecomeInvisible(
				By.xpath("//div[contains(@class,'success-info-container')]//div[@class='close-button-white']"));

	}

	/* Configuring Pre-Requisite Schedule Appointment by Central Rating */
	public void scheduleAppointment(String hourAppointment, String minuteAppointment, String timeMar) {
		setAppointmentDate();
		setStartedTime(hourAppointment, minuteAppointment, timeMar);
		clickOnSave();
	}

	/*Click On Schedule date Calendar*/
	
	public void selectToOpenScheduleDateCalender()
	{
		clickOn(scheduleDatePickerBTN);
	}
	
	
	
	/*Verify Schedule Date Displayed*
	 * 
	 * 
	 */
	
	public void verifyScheduledCalenderDateofVisitProjectionWindowDisplayed()
	{
		Assert.assertTrue(visitProjectionScheduleCalenderDateWindow.isDisplayed());
		reportInfo();
	}
	/*Verify Time Table Displayed Automatically*/
	
	public void verifyTimeTableDisplayedAutomatically()
	{
		moveToElement(timePickerOpenWindow);
		Assert.assertTrue(isElementPresent(timePickerOpenWindow));
		reportInfo();
	}
	/*Verify date is Displayed in Schedule date Field*/
	public void verifySelectedDateDisplayedOnScheduledCalenderField(String date)
	{
		String[] splitString = getText(appointmentDateTimeText).split("\\s");
		
		Assert.assertTrue(splitString[0].toLowerCase().equalsIgnoreCase(date));
		reportInfo();
	}
	/***
	 * verify notification date is current date
	 */
	public void verifyNotiFicationDateIsCurrentDate() {
		String systemcurrentdate = notificationdatefield.getText();
		String expected = systemcurrentdate.substring(systemcurrentdate.indexOf('"')+1, systemcurrentdate.indexOf(' '));
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		String datetoday = sdf.format(date);
		boolean flag=true;
		
		if (expected.trim().equalsIgnoreCase(datetoday)) {
		flag=true;
		}
		Assert.assertTrue(flag);
		reportInfo();
}
	/**
	 *  get noticication field date & Time
	 * @return
	 */
	public String GetNotiFicationFieldTime()
	{
		String systemcurrentdate = notificationdatefield.getText();
		
		return systemcurrentdate;
	}
	/**
	 *  get scheduled field date & Time
	 * @return
	 */
	public String GetScheduledFieldDateTimeValue()
	{
		String fielddateTime = appointmentDateTimeText.getText();
		return fielddateTime;
	}
	public void verifyScheduledFieldReflectsAccordinglyDateTime(String expected)
	{
		String actual = appointmentDateTimeText.getText();
        Assert.assertEquals(actual, expected);
	}
	public void verifyNotificationFieldReflectsAccordinglyDateTime(String expected)
	{
		String actual = notificationdatefield.getText();
		Assert.assertEquals(actual, expected);

	}
	/***
	 * verify schedule date field is empty
	 */
	public void scheduleDateFieldIsEmpty() {
		boolean flag=true;
		try {
			if(scheduledateField.getText().isEmpty()) {
				flag=true;
			}
			}
			catch (Exception e) {
			}
		        moveToElement(scheduledateField);
				Assert.assertTrue(flag);
				reportInfo();
		}

	  /***
	 * verify Notification date field is empty
	 */
	public void NotificationDateFieldIsEmpty() {
		boolean flag=true;
		try {
			if(notificationdatefield.getText().isEmpty()) {
				flag=true;
			}
			}
			catch (Exception e) {
			}
		        moveToElement(scheduledateField);
				Assert.assertTrue(flag);
				reportInfo();
		}

	 /***
	 * verify Notification Date/Time picker field reflects the corresponding date and time
	 */
	public void VerifyCorrespondingDate() {
	 waitAndClick(notificationDatePickerBTN);
     String Pickerdate = notificationCurrentDate.getText();
     String[] noteDate = notificationdatefield.getText().split(" ");
     String[] note =noteDate[0].split("-");
     String st = note[0];
     Assert.assertEquals(st, Pickerdate);
    	   
       }

	public void clickonNotificationtimepicker() {
		clickOn(notificationtimePicker);
		
	}	
	
	public void clickOnScheduledFieldtimePicker()
	{
		clickOn(scheduletimePicker);
		reportInfo();
	}
}

