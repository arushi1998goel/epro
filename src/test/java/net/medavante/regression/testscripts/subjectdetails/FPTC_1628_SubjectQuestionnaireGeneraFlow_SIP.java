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

public class FPTC_1628_SubjectQuestionnaireGeneraFlow_SIP extends BaseTest {
	
	private String durationTimeOnTime="1min";
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1628_SubjectQuestionnaireGeneraFlow_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1628
	 * Test Case Name: SubjectQuestionnaireGeneraFlow
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1628_SubjectQuestionnaireGeneraFlow", groups = { "" })

	public void FPTC_1628_verifySubjectQuestionnaireGeneraFlow() {

		reportLog("1.1:Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.1:User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("2.1:Navigate to Study Navigator  Study Pr.#1 Subject Listing screen -> Select Subject Pr.#3");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		
		
		reportLog("2.2:Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("3.1:Select Questionnaires from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);
	
		
		reportLog("3.3:Past Week' filter is displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.CategoryFilter_PastWeek);
		
		reportLog("3.4:'Subject' filter is displayed by default");
		subjectDetailPage.verifyUserBlockFilterSelectedByDefault(Constants.Participant_Filter);
		
		reportLog("4.1:Select All Questionnaires filter");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);
		
		reportLog("4.2:Select On Time Questionnaire Pr.#4");
		subjectDetailPage.selectQuestionnariesByStatus(Constants.Mobile_Form, Constants.Questionnaire_CompletedStatus);
		
		reportLog("4.3:The following information is displayed:");
		reportLog("4.4:Flag is set");
		subjectDetailPage.verifySelectedEventAndQuestionnaireDisplayedWithFlagIconOnList();
		
		reportLog("4.5:Questionnaire details:");
		subjectDetailPage.verifyQuestionnairestDetailBlock();
		
		reportLog("4.6:Name");
		subjectDetailPage.verifyQuestionnairesInformationUnderDetailsBlock(Constants.QuestionnairesDetails_NameField,Constants.Mobile_Form);
		
		reportLog("4.7:Duration");
		subjectDetailPage.verifyQuestionnairesInformationUnderDetailsBlock(Constants.QuestionnairesDetails_DurationField,durationTimeOnTime);
		
		reportLog("4.8:Status");
		subjectDetailPage.verifyQuestionnairesStatusPresentInDetailsBlockWithTime(Constants.Questionnaire_CompletedStatus);
		
		reportLog("4.9:Notes field");
		subjectDetailPage.verifyNoteSectionIsDisplayed();
		
		reportLog("4.10:Scores list");
		subjectDetailPage.verifyQuestionnairesScoreBlockPresent();

		
	    reportLog("5.1:	Select Expired Questionnaire Pr.#4");
	    subjectDetailPage.selectQuestionnariesByStatus(Constants.Mobile_Form, Constants.Questionnaire_Icon_ExpiredStatus);;
	
		reportLog("5.2:Flag is set");
		subjectDetailPage.verifySelectedEventAndQuestionnaireDisplayedWithFlagIconOnList();
		
		reportLog("5.3:Questionnaire details:");
		reportLog("5.4:Name");
		subjectDetailPage.verifyQuestionnairesInformationUnderDetailsBlock(Constants.QuestionnairesDetails_NameField,Constants.Mobile_Form);
		
		reportLog("5.5:Duration");
		subjectDetailPage.verifyQuestionnairesInformationUnderDetailsBlock(Constants.QuestionnairesDetails_DurationField,durationTimeOnTime);
		
		reportLog("5.6:Status");
		subjectDetailPage.verifyQuestionnairesStatusPresentInDetailsBlockWithTime(Constants.Questionnaire_ExpiredStatus);
		
		reportLog("5.7:Notes field");
		subjectDetailPage.verifyNoteSectionIsDisplayed();
	
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
	
	}
}
