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

public class FPTC_1633_SubjectQuestionnaireAddEditDeleteNotesForAQuestionnaire_SIP extends BaseTest {
	
	private String noteText=generateRandomAlphanumericString(3),updatedNotesText=generateRandomAlphanumericString(5);;
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1633_SubjectQuestionnaireAddEditDeleteNotesForAQuestionnaire_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1633 Test Case Name:Subject Questionnaire -
	 * Add/Edit/Delete notes for a questionnaire
	 * 
	 * ====================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1633_Subject Questionnaire - Add/Edit/Delete notes for a questionnaire", groups = {})
	public void FPTC_1633_VerifySubjectQuestionnaireAddEditDeleteNotesForAQuestionnaire() {

		reportLog("1.1:Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSystemAdministrator, AT_Password);

		reportLog("1.2:	User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.2:Study Pr.#1 Subject Listing screen");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("2.3:Select Subject Pr.#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.4:Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3.1:Select Questionnaire from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);

		reportLog("3.2:List of Questionnaires is displayed");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);
		subjectDetailPage.verifyQuestionnaireBlockListGridDisplayed();

		reportLog("4.1:Select Questionnaire Pr.#4");	
		subjectDetailPage.clickOnEventAndQuestionnaireRow("Mobile_Form");

		reportLog("4.2:	Questionnaire Pr.#4 detail is displayed");
		subjectDetailPage.verifyQuestionnariesDetailsBlockDisplayed();
		
		reportLog("5.1:	Add Note text");
		subjectDetailPage.clickOnEditNotesButtonIcon();
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(noteText);

		reportLog("5.2:	Created Note text is displayed for corresponding Questionnaire Pr.#4");
		subjectDetailPage.selectSaveControlOnModalWindow();
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();
		subjectDetailPage.verifyNoteTextOnNotesSection(noteText);

		reportLog("6.1:Edit Note text from Step#5");
		subjectDetailPage.clickOnEditNotesButtonIcon();
		subjectDetailPage.clearValueInEditNoteUnderModelWindow();
		subjectDetailPage.enterValueInEditNoteUnderModelWindow(updatedNotesText);
		subjectDetailPage.selectSaveControlOnModalWindow();
		
		reportLog("6.2:Edited Note text is displayed for corresponding Questionnaire Pr.#4");
		subjectDetailPage.verifyTextEnteredInEditNoteUnderModelWindow(updatedNotesText);

		reportLog("7.1:Delete Note text from Step#6");
		subjectDetailPage.clickOnEditNotesButtonIcon();
		subjectDetailPage.clearValueInEditNoteUnderModelWindow();
		subjectDetailPage.selectSaveControlOnModalWindow();

		reportLog("7.2:Deleted Note text isn't displayed for corresponding Questionnaire Pr.#4");
		subjectDetailPage.verifyVisitNotesModalWindowIsClosed();
		subjectDetailPage.verifyNoteTextOnNotesSection(null);
		
		reportLog("LogOut application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
