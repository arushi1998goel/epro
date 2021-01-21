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

public class FPTC_1281_AddNotesToVisit_SIP extends BaseTest {

	private String visit, ranndomNoteText, enteredNoteText, noteText = "TestNotes", noteText2 = "TestNotes123";
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1281_AddNotesToVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyWithCustomVisits");
		visit = properties.getProperty("AutoCalendarVisit2");
		ranndomNoteText = "AutoText" + generateRandomAlphanumericString(255);

		reportLog("Creating a subject from user and configure studies accordingly");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,AT_PRODSiteCoordinatorUserName,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1281 Test Case Name: Add notes to a visit.
	 * =========================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1281_Add notes to a visit ", groups = { "" })
	public void FPTC_1281_AddNotesToVisit() throws Exception {

		reportLog("1.2: Login to Site Portal");
		loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("1.3: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.4: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("1.5: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATSiteAssignedRater_10);

		reportLog("2.1: Select subject");
		studyNavigatorDashBoardPage.clickOnSubject(screeningNum);

		reportLog("2.1: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("2.2: Verify Visit Category Filters are displayed.");
		subjectDetailPage.verifyVisitCategoryFiltersDisplayed();

		reportLog("2.3: Select All From Visit Category");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("2.4: Select visit row ");
		subjectDetailPage.clickOnCalendarVisitRow(visit);

		reportLog("2.5: Select Initiate button for visit ");
		subjectDetailPage.clickOnInitiateVisitIcon();

		reportLog("2.6: verify Notes section is displayed");
		subjectDetailPage.verifyNoteSectionIsDisplayed();

		reportLog("2.7:Verify Control to edit Notes is available");
		subjectDetailPage.verifyEditNotesButtonIsDisplayed();

		reportLog("3.1:Select Control to edit Note");
		subjectDetailPage.clickOnEditNotesButtonIcon();

		reportLog("3.2:Verify Visit Notes modal window is displayed");
		subjectDetailPage.verifyVisitNotesModalWindowIsDisplayed();

		reportLog("3.3:Verify Textbox is available to enter a text");
		subjectDetailPage.verifyVisitNotesTextBoxUnderModalWindowIsDisplayedAndEditable();

		reportLog("3.4:Verify Save control is displayed and not active");
		subjectDetailPage.verifyInactiveSaveControlIsDisplayedOnModalWindow();

		reportLog("3.4:Verify Cancel control is displayed");
		subjectDetailPage.verifyCancleButtonIsDisplayedOnModalWindow();

		reportLog("4.1:Enter text into textbox");
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(noteText);

		reportLog("4.2: Verify entered text is displayed");
		subjectDetailPage.verifyTextEnteredInEditNoteUnderModelWindow(noteText);

		reportLog("4.3:Verify Save control is now enable");
		subjectDetailPage.verifyActiveSaveControlIsDisplayedOnModalWindow();

		reportLog("5.1: Select cancel control");
		subjectDetailPage.clickOnCancleButtonIsDisplayedOnModalWindow();

		reportLog("5.2: Verify Visit Notes modal window is closed");
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();

		reportLog("5.3: Verify Entered text is not saved");
		subjectDetailPage.verifyAbsenceOfUnsavedAddedNoteTextOnNotesSection(noteText);

		reportLog("6.1:Select Control to edit Note");
		subjectDetailPage.clickOnEditNotesButtonIcon();

		reportLog("6.2: Enter 263 symbols into the textbox");
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(ranndomNoteText);

		reportLog("6.3: Verify entered text contains only 255 symbols");
		enteredNoteText = subjectDetailPage.verifyLenthOfTextEnteredInEditNoteUnderModelWindow(255);

		reportLog("7.1: Select save control");
		subjectDetailPage.selectSaveControlOnModalWindow();

		reportLog("7.2: Verify Visit Notes modal window is closed");
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();

		reportLog("7.3: Verify Entered text is displayed in the Notes section");
		subjectDetailPage.verifyNoteTextOnNotesSection(enteredNoteText);

		reportLog("8.1:Select Control to edit Note");
		subjectDetailPage.clickOnEditNotesButtonIcon();

		reportLog("8.2: Clear value in the textbox");
		subjectDetailPage.clearValueInEditNoteUnderModelWindow();

		reportLog("8.3: Select save control");
		subjectDetailPage.selectSaveControlOnModalWindow();

		reportLog("8.4: Verify Visit Notes modal window is closed");
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();

		reportLog("8.5: Verify Entered text is displayed in the Notes section");
		subjectDetailPage.verifyNoteTextOnNotesSection(null);

		reportLog("9.1:Select Control to edit Note");
		subjectDetailPage.clickOnEditNotesButtonIcon();

		reportLog("9.2:Enter text into textbox");
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(noteText2);

		reportLog("9.3: Select save control");
		subjectDetailPage.selectSaveControlOnModalWindow();

		reportLog("9.4: Verify Visit Notes modal window is closed");
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();

		reportLog("9.5: Verify Entered text is displayed in the Notes section");
		subjectDetailPage.verifyNoteTextOnNotesSection(noteText2);

		reportLog("10.1: Logout from the Application");
		loginPage.logoutApplication();

		reportLog("10.2: Verify User Logout from the system");
		loginPage.verifyUserLogout();

		reportLog("10.3: Login to Sponsor Portal");
		loginPage.sponsorLogin(AT_PRODSponsorUserTypeNew, AT_Password);

		reportLog("10.4: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("10.5: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("10.6: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.All_SiteText);

		reportLog("11.1: Select subject");
		studyNavigatorDashBoardPage.clickOnSubject(screeningNum);

		reportLog("11.2: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("11.3: Verify Visit Category Filters are displayed.");
		subjectDetailPage.verifyVisitCategoryFiltersDisplayed();

		reportLog("11.4: Select All From Visit Category");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("11.5: Select visit row ");
		subjectDetailPage.clickOnCalendarVisitRow(visit);

		reportLog("11.6: verify Notes section is displayed");
		subjectDetailPage.verifyNoteSectionIsDisplayed();

		reportLog("11.7: Verify Entered text is displayed in the Notes section");
		subjectDetailPage.verifyNoteTextOnNotesSection(noteText2);

		reportLog("11.8: Verify Control to edit Notes is not displayed");
		subjectDetailPage.verifyEditNotesButtonIsNotDisplayed();

		reportLog("11.9: Logout from the Application");
		loginPage.logoutApplication();

		reportLog("11.10: Verify User Logout from the system");
		loginPage.verifyUserLogout();

	}
}
