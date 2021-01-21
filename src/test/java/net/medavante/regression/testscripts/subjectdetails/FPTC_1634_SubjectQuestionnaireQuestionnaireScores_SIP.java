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

public class FPTC_1634_SubjectQuestionnaireQuestionnaireScores_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1634_SubjectQuestionnaireQuestionnaireScores_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1634 Test Case Name:Subject Questionnaire -
	 * Questionnaire Scores
	 * 
	 * ====================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1634_Subject Questionnaire - Questionnaire Scores", groups = {})
	public void FPTC_1634_VerifySubjectQuestionnaireQuestionnaireScores() {

		reportLog("1.1:Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSystemAdministrator, AT_Password);

		reportLog("1.2:	User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.2:Navigate to the Subject Details page of Pr#2");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.3:Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3.1: Select Questionnaires category in the drop-down list ");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);
		
		reportLog("3.3:'Past Week' filter is displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.CategoryFilter_PastWeek);
		
		reportLog("3.4:Subject' filter is displayed by default");
		subjectDetailPage.verifyUserBlockFilterSelectedByDefault(Constants.Participant_Filter);

		reportLog("3.2: List of Questionnaires Pr.#4 is displayed");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);
		subjectDetailPage.verifyQuestionnaireBlockListGridDisplayed();

		reportLog("4.1:Select Completed Questionnaire Pr.#4 ");
		subjectDetailPage.selectQuestionnariesByStatus(Constants.Mobile_Form, Constants.Questionnaire_CompletedStatus);

		reportLog("4.2:	Questionnaire details is displayed");
		subjectDetailPage.verifyQuestionnairestDetailBlock();
		subjectDetailPage
				.verifyQuestionnairesStatusPresentInDetailsBlockWithTime(Constants.Questionnaire_CompletedStatus);

		reportLog("5.1:Select Expired Questionnaire Pr.#4");
		subjectDetailPage.selectQuestionnariesByStatus(Constants.Mobile_Form, Constants.Questionnaire_Icon_ExpiredStatus);

		reportLog("5.2:Corresponding to 'Expired' status icon is displayed.");
		subjectDetailPage
				.verifyQuestionnairesStatusIconDisplayedUnderDetailssBlock(Constants.Questionnaire_Icon_ExpiredStatus);

		reportLog("5.3:Expired status and time is displayed");
		subjectDetailPage.verifyQuestionnairesStatusPresentInDetailsBlockWithTime(Constants.Questionnaire_Icon_ExpiredStatus);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
