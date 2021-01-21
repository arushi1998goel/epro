package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1545_CantMoveAssSubjectsOfDiffStudyOrSite_SIP extends BaseTest {

	private String subjectName2225_1, subjectName2225_2, visitForSite2, visitForCR,
			subjectForSite2, visit1, optionToBeVerify, siteNameForSite1, siteNameSite2;
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1545_CantMoveAssSubjectsOfDiffStudyOrSite_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyNameNonCR");
		studyNameCR = properties.getProperty("AutomationStudyNameCR");
		visit1 = properties.getProperty("visittobetest");
		visitForCR = properties.getProperty("visitToBeVerify");
		subjectName2225_1 = properties.getProperty("subjectName2225_1");
		subjectName2225_2 = properties.getProperty("subjectName2225_2");
		subjectForSite2 = properties.getProperty("subjectForSite2");
		siteNameForSite1 = properties.getProperty("siteNameForSite1");
		siteNameSite2 = properties.getProperty("siteNameSite2");
		optionToBeVerify = properties.getProperty("changeassesment");
		visitForSite2 = properties.getProperty("visitForSite1");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1545 Test Case Name: Cannot Move Assessments Between
	 * Subjects Of Different Study Or Site
	 * 
	 * ====================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1545_Cannot Move Assessments Between Subjects Of Different StudyOrSite", groups = {})
	public void FPTC_1545_CantMoveAssSubjectsOfDiffStudyOrSite() {

		reportLog("1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.1 Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2: Navigate to study navigator");
		/*studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);*/
		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("2.1: Study Dashboard is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("2.2 Select Site From DropDown");
		//studyNavigatorDashBoardPage.selectSite(siteNameForSite1);
		
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.VTAssignedRater_21);
		

		reportLog("3:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("3.1: Search and click on assessment");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormName, visit1, subjectName2225_1);

		reportLog("3.2: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.3Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(optionToBeVerify);

		reportLog("3.4Navigate to home page");
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("4.1: Navigate to study navigator");
		/*studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyNameCR);*/
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyNameCR,Constants.ATAssignedRater_10);
		
		
		reportLog("4.2 Study Dashboard is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("4.3:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("4.4: Search and click on assessment");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitForCR, subjectName2225_2);

		reportLog("4.5Check that Assessment Pr.#3 NOT displayed in ‘Move To’ Assessment drop-down list");
		assessmentDetailPage.verifyOptionIsNotPresentInActionDropDown();

		reportLog("5: Navigate to home page");
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("5.1: Navigate to study navigator");
		/*studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);*/
		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("5.2 Select Site From DropDown");
		studyNavigatorDashBoardPage.selectSite(siteNameSite2);

		reportLog("5.3: Study Dashboard is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("5.4:navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("5.5: Search and click on assessment");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormNameSecondType, visitForSite2, subjectForSite2);

		reportLog("5.6: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("5.7Verify Action button is displaying");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(optionToBeVerify);

		reportLog("6: Navigate to home page");
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("6.1: Navigate to study navigator");
		/*studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);*/
		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);		

		reportLog("6.2: Select Site From DropDown");
		studyNavigatorDashBoardPage.selectSite(siteNameForSite1);

		reportLog("6.3: navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("6.4: Search and click on assessment");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormNameSecondType, visitForSite2, subjectName2225_2);

		reportLog("6.5: Check that Assessment Pr.#3 NOT displayed in ‘Move To’ Assessment drop-down list");
		assessmentDetailPage.verifyOptionIsNotPresentInActionDropDown();

		reportLog("7.1:Logout application");
		loginPage.logoutApplication();

		reportLog("7.4:Verify user is logout");
		loginPage.verifyUserLogout();
	}
}
