package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1631_SubjectQuestionnaireUserLevelFilter_SIP extends BaseTest {

	protected String subjectName = "SUBJ_" + generateRandomString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1631_SubjectQuestionnaireUserLevelFilter_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1631 Test Case Name: Subject Questionnaire - User level
	 * Filter
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1631_Subject Questionnaire - User level Filter", groups = {})
	public void FPTC_1631_SubjectQuestionnaireUserLevelFilter() throws InterruptedException {

		reportLog("1: Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSystemAdministrator, AT_Password);

		reportLog("2: Navigate to Study Navigator -> Study Pr.#1 Subject Listing screen -> Select Subject Pr.#3");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.1: Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3: Select Questionnaires from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);

		reportLog("3.1: List of Questionnaires Pr.#4 is displayed - 'Past Week' filter is displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.CategoryFilter_PastWeek);

		reportLog("3.2: 'Subject' filter is displayed by default");
		subjectDetailPage.verifyUserBlockFilterSelectedByDefault(Constants.Participant_Filter);

		reportLog("4: Select 'Observer' filter");
		subjectDetailPage.selectUserBlockFilter(Constants.Observer_Filter);

		reportLog("5: Select 'Subject' filter");
		subjectDetailPage.selectUserBlockFilter(Constants.Participant_Filter);
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);

		reportLog("5.1: Questionnaires Pr.#4.1 filled by Subject are displayed");
		subjectDetailPage.verifyQuestionnaireBlockListGridDisplayed();

		reportLog("6: Navigate to the Administration -> Study Pr.#1 -> General -> Unset supporting Observers for Mobile PRO configuration -> Save changes");
		subjectDetailPage.navigateToHomePage();
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		
		reportLog("6.1:Navigate To Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		
		reportLog("6.2: Navigate To General");
		adminstrationStudyGeneralPage=adminstrationStudiesPage.navigateToStudyGeneralTab();		
		adminstrationStudyGeneralPage.verifyAdministrationStudiesGeneralPageIsOpen(studyName);
		
		reportLog("6.3: Unset supporting Observers for Mobile PRO configuration and Save changes");
		adminstrationStudyGeneralPage.verifyProductTypeCheckboxSelected(Constants.ProductType_Observer);
		adminstrationStudyGeneralPage.selectProductTypeCheckbox(Constants.ProductType_Observer);
		
		
		reportLog("7: Navigate to Study Navigator -> Study Pr.#1 Subject Listing screen -> Select Subject Pr.#3");
		adminstrationStudyGeneralPage.navigateToHomePage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("7.1: Only 'Subject' filter is displayed");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);
		subjectDetailPage.verifyUserBlockFilterSelectedByDefault(Constants.Participant_Filter);

		reportLog("7.2: 'Observer' filter isn't displayed");
		subjectDetailPage.verifyUserBlockFilterSelectedByDefault(Constants.Observer_Filter);
		
		reportLog("8: Again enable the observer checkbox for the same study");
		
		subjectDetailPage.navigateToHomePage();
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		
		
		reportLog("Navigate To Studies");
		adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		
		reportLog("Navigate To General");
		adminstrationStudiesPage.navigateToStudyGeneralTab();		
		adminstrationStudyGeneralPage.verifyAdministrationStudiesGeneralPageIsOpen(studyName);
		
		reportLog("Set supporting Observers for Mobile PRO configuration and Save changes");
		adminstrationStudyGeneralPage.verifyProductTypeCheckboxSelected(Constants.ProductType_Observer);
		adminstrationStudyGeneralPage.selectProductTypeCheckbox(Constants.ProductType_Observer);		

		reportLog("9: Logout application");
		loginPage.logoutApplication();

		reportLog("10: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
