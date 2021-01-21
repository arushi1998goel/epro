package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1642_VerifySubjectLoggedEventsAddEditDeleteNotes_SIP extends BaseTest {

	
	private String noteText=generateRandomAlphanumericString(3),updatedNotesText=generateRandomAlphanumericString(5);
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1642_VerifySubjectLoggedEventsAddEditDeleteNotes_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
		subjectName = properties.getProperty("SubjectEventName");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1642 Test Case Name:Subject Logged Events -
	 * Add/Edit/Delete Notes
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1642_Subject Logged Events - Add/Edit/Delete Notes", groups = {})
	public void FPTC_1642_VerifySubjectLoggedEventsAddEditDeleteNotes() {

		reportLog("1.1:Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2:Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.3:Study Pr.#1 Subject Listing screen");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("1.4:Select Subject Pr.#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("1.5:Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2.1:Select Logged Events from Subject categories drop-down list and click Past Week control");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_LoggedEvents);
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_PastWeek);
	
		reportLog("2.2:List of Logged Events Pr.#4 is displayed ");
		subjectDetailPage.verifyEventListDisplayed();

		reportLog("2.3:'Past Week' filter is displayed");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.CategoryFilter_PastWeek);

		reportLog("2.4:Newest today's records are shown in the top of the list");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();

		reportLog("2.5:An Event section presented and reflects content of the event");
		
		subjectDetailPage.clickOnEventAndQuestionnaireRow(Constants.Event_FormName);
		subjectDetailPage.verifyLoggedEventDetailBlock();

		reportLog("2.6:Notes with corresponding control are displayed.");
	
		subjectDetailPage.verifyNoteSectionIsDisplayed();
		subjectDetailPage.verifyEditNotesButtonIsDisplayed();

		reportLog("3.1:Select option to create a Note for the selected logged event.");
		subjectDetailPage.clickOnEditNotesButtonIcon();
		
		reportLog("3.2:	Modal window is presented and text field of the Note is editable");
		subjectDetailPage.verifyVisitNotesModalWindowIsDisplayed();
		subjectDetailPage.verifyVisitNotesTextBoxUnderModalWindowIsDisplayedAndEditable();

		reportLog("4.1:Enter any text into the Notes section");
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(noteText);

		reportLog("4.2:Text is displayed");
		subjectDetailPage.verifyTextEnteredInEditNoteUnderModelWindow(noteText);

		reportLog("5.1:	Select option to Cancel changes");
		subjectDetailPage.clickOnCancleButtonIsDisplayedOnModalWindow();

		reportLog("5.2:	Note was not saved");
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();
		subjectDetailPage.verifyAbsenceOfUnsavedAddedNoteTextOnNotesSection(noteText);
	
		reportLog("6.1:	Select option to create a Note for the selected logged event");
		subjectDetailPage.clickOnEditNotesButtonIcon();

		reportLog("6.2:Modal window is presented and text field of the Note is editable");
		subjectDetailPage.verifyVisitNotesModalWindowIsDisplayed();
		subjectDetailPage.verifyVisitNotesTextBoxUnderModalWindowIsDisplayedAndEditable();

		reportLog("7.1:Enter any text into the Notes section");
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(noteText);
		
		reportLog("7.2:Select option to save changes");
		subjectDetailPage.selectSaveControlOnModalWindow();

		reportLog("7.3:Dialog is closed and entered Note is presented in the Event section.");
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();
		subjectDetailPage.verifyNoteTextOnNotesSection(noteText);

		reportLog("8.1:Select option to edit an existing note text");
		subjectDetailPage.clickOnEditNotesButtonIcon();

		reportLog("8.2:Modal window is presented and text field of the Note is editable");
		subjectDetailPage.verifyVisitNotesModalWindowIsDisplayed();
		subjectDetailPage.verifyVisitNotesTextBoxUnderModalWindowIsDisplayedAndEditable();

		reportLog("9.1:Change a Note text and select Cancel option");
		subjectDetailPage.clearValueInEditNoteUnderModelWindow();
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(updatedNotesText);
		subjectDetailPage.clickOnCancleButtonIsDisplayedOnModalWindow();
	
		reportLog("9.2:Changes to Note was not saved");
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();
		subjectDetailPage.verifyAbsenceOfUnsavedAddedNoteTextOnNotesSection(updatedNotesText);

		reportLog("10.1:Select option to edit an existing note text");
		subjectDetailPage.clickOnEditNotesButtonIcon();

		reportLog("10.2:Modal window is presented and text field of the Note is editable");
		subjectDetailPage.verifyVisitNotesModalWindowIsDisplayed();
		subjectDetailPage.verifyVisitNotesTextBoxUnderModalWindowIsDisplayedAndEditable();

		reportLog("11.1:Change a Note text and select option to Savechanges");
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(noteText);
		subjectDetailPage.selectSaveControlOnModalWindow();
		
		reportLog("11.2:Dialog closed and edited Note is presented in the Event section");
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();
		subjectDetailPage.verifyNoteTextOnNotesSection(noteText);

		reportLog("12.1:Select option to edit an existing note text");
		subjectDetailPage.clickOnEditNotesButtonIcon();

		reportLog("12.2:Modal window is presented and text field of the Note is editable");
		subjectDetailPage.verifyVisitNotesModalWindowIsDisplayed();
		subjectDetailPage.verifyVisitNotesTextBoxUnderModalWindowIsDisplayedAndEditable();

		reportLog("13.1: Delete all text in the presented dialog related to the Notes");
		subjectDetailPage.clearValueInEditNoteUnderModelWindow();
		
		reportLog("13.2:Save changes");
		subjectDetailPage.selectSaveControlOnModalWindow();

		reportLog("13.3:Dialog closed and empty Note is presented in the Event section");
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();
		subjectDetailPage.verifyNoteTextOnNotesSection(null);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
