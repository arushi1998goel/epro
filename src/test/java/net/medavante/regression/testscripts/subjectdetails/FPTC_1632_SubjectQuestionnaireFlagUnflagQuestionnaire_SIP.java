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

public class FPTC_1632_SubjectQuestionnaireFlagUnflagQuestionnaire_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1632_SubjectQuestionnaireFlagUnflagQuestionnaire_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1632 Test Case Name:Subject Questionnaire -
	 * Flag/Unflag questionnaire
	 * 
	 * ====================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1632_Subject Questionnaire - Flag/Unflag questionnaire", groups = {})
	public void FPTC_1632_VerifySubjectQuestionnaireFlagUnflagQuestionnaire() {

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

		reportLog("3.1:	Select Questionnaire from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);

		reportLog("4.1:	Select Questionnaire Pr.#4");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);
		
		reportLog("3.2:List of Questionnaires is displayed");
		subjectDetailPage.verifyQuestionnaireBlockListGridDisplayed();

		reportLog("4.2:Questionnaire Pr.#4 detail is displayed");
		subjectDetailPage.verifyQuestionnairestDetailBlock();

		reportLog("4.3:Flag is not set");
		subjectDetailPage.clickOnQuestionnarireRowWithFlagNotSet();

		reportLog("4.4:Action to set flag is enabled");
		subjectDetailPage.verifyFlagSectionForEventAndQuestionnaireDisplayedWithFlagSetIcon();

		reportLog("5.1:Set Flag for Questionnaire Pr.#4");
		subjectDetailPage.clickOnEventAndQuestionnaireLoggedFlag();

		reportLog("5.2:Flag is set");
		subjectDetailPage.verifySelectedEventAndQuestionnaireDisplayedWithFlagIconOnList();

		reportLog("5.3:Action to unset flag is enabled");
		subjectDetailPage.verifyFlagSectionForEventAndQuestionnaireDisplayedWithClearIcon();

		reportLog("6.1:	Unset Flag for Questionnaire Pr.#4");
		subjectDetailPage.clickOnEventAndQuestionnaireLoggedFlag();

		reportLog("6.2:Flag is not set");
		subjectDetailPage.verifySelectedEventNotDisplayedWithFlagIconOnEventList();

		reportLog("6.3:Action to set flag is enabled");
		subjectDetailPage.verifyFlagSectionForEventAndQuestionnaireDisplayedWithFlagSetIcon();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
