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

public class FPTC_1635_SubjectQuestionnairePossibilityToViewQuestionnaireContent_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1635_SubjectQuestionnairePossibilityToViewQuestionnaireContent_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1635 Test Case Name:Subject Questionnaire -
	 * Possibility to view questionnaire content
	 * 
	 * ====================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1635_Subject Questionnaire - Possibility to view questionnaire content", groups = {})
	public void FPTC_1635_VerifySubjectQuestionnairePossibilityToViewQuestionnaireContent() {
		
		
		/*-----------Login With User can view questionnaire content for the Subject--------------- */
		reportLog("1.1:Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

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
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2.3: Select Questionnaires category in the drop-down list ");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);

		reportLog("2.4:Select the Questionnaire from Pr#3");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);
		subjectDetailPage.selectQuestionnariesByStatus(Constants.Mobile_Form, Constants.Questionnaire_CompletedStatus);

		reportLog("2.5: Questionnaire content(questions and scores) is displayed");
		subjectDetailPage.verifyQuestionnairestDetailBlock();
		subjectDetailPage.verifyQuestionnairesScoreBlockPresent();

		reportLog("3.1:Log out from the Portal ");
		loginPage.logoutApplication();
		
		/*-----------Login With User cannot view questionnaire content for the Subject--------------- */

		reportLog("3.2:Log in to the Portal as User in PR#5");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("3.3:User is logged in ");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("4.1:Navigate to the Subject Details page of Pr#2 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("4.2:Select Questionnaires category in the drop-down list ");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);

		reportLog("4.3:Select the Questionnaire from Pr#3 ");
		subjectDetailPage.selectCategorySubTab(Constants.CategoryFilter_All);
		subjectDetailPage.selectQuestionnariesByStatus(Constants.Mobile_Form, Constants.Questionnaire_CompletedStatus);

		reportLog("4.4:Questionnaire content(questions and scores) is not displayed");
		subjectDetailPage.verifyQuestionnariesContentScoresNotDisplayed();
	
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
