package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1630_SubjectQuestionnaireDurationBasedFilter_SIP extends BaseTest {

	protected String subjectName2 = "SUBJ_" + generateRandomString(5), language, subjectName1;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1630_SubjectQuestionnaireDurationBasedFilter_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		language = properties.getProperty("studyLanguage");
		studyName = properties.getProperty("StudyName2645");
		subjectName1 = properties.getProperty("SubjectEventName");

		reportLog("Creating a New subject from user");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName2);
		studyNavigatorDashBoardPage.selectSubjectLanguage(language);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.waitSpinnerToBecomeInvisible();
		loginPage.logoutApplication();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1630 Test Case Name: Subject Questionnaire - Duration
	 * Based Filter
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1630_Subject Questionnaire - Duration Based Filter ", groups = {})
	public void FPTC_1630_SubjectQuestionnaireDurationBasedFilter() throws InterruptedException {

		reportLog("1: Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSystemAdministrator, AT_Password);

		reportLog("2: Navigate to Study Navigator -> Study Pr.#1 Subject Listing screen -> Select Subject Pr.#3");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName1);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName1);

		reportLog("2.1: Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3: Select Questionnaires from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);
		subjectDetailPage.verifyQuestionnaireBlockListGridDisplayed();

		reportLog("3.1: List of Questionnaires Pr.#4 is displayed - 'Past Week' filter is displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.CategoryFilter_PastWeek);

		reportLog("4: Select 'All' filter");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);

		reportLog("4.1: All past questionnaires (Completed or Expired) Pr.#4.1-4.3 are displayed");
		subjectDetailPage.selectQuestionnariesByStatus("Test_MobForm", Constants.Complete_Status);
		subjectDetailPage.selectQuestionnariesByStatus("Mobile_Form", Constants.Complete_Status);

		reportLog("5: Select 'Past Week' filter");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_PastWeek);

		reportLog(
				"5.1: Questionnaires Pr.#4.1 that were to be conducted in the last 7 days up to the current timestamp are displayed");
		subjectDetailPage.selectQuestionnariesByStatus("Test_MobForm", Constants.Complete_Status);
		subjectDetailPage.selectQuestionnariesByStatus("Mobile_Form", Constants.Complete_Status);

		reportLog("6: Select 'Past Month' filter");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_PastMonth);

		reportLog(
				"6.1: Questionnaires Pr.#4.2 that were supposed to be completed in the last 30 days up to the current timestamp is displayed");
		subjectDetailPage.selectQuestionnariesByStatus("Test_MobForm", Constants.Complete_Status);
		subjectDetailPage.selectQuestionnariesByStatus("Mobile_Form", Constants.Complete_Status);

		reportLog("7: Select 'Flagged' filter");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_Flagged);

		reportLog("7.1: Flagged Questionnaire Pr.#4.3 is displayed");
		subjectDetailPage.selectQuestionnariesByStatus("Test_MobForm", Constants.Complete_Status);

		reportLog("8: Navigate to Study Navigator -> Study Pr.#1 Subject Listing screen -> Select Subject Pr.#5");
		subjectDetailPage.navigateToHomePage();
		dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName2);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName2);
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("9: Select Questionnaires from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);
		subjectDetailPage.verifyQuestionnaireBlockListGridDisplayed();

		reportLog("8: Logout application");
		loginPage.logoutApplication();

		reportLog("9: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
