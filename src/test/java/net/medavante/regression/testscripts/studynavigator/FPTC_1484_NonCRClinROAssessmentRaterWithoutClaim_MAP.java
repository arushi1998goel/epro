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

public class FPTC_1484_NonCRClinROAssessmentRaterWithoutClaim_MAP extends BaseTest {

	private String subjectName="Auto2217"+generateRandomAlphanumericString(5),visitName, visitNameObsroNotCompleted;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1484_NonCRClinROAssessmentRaterWithoutClaim_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("visitClinRoSubmitted");
		visitNameObsroNotCompleted = properties.getProperty("Visit2217ObsroNotCompleted");
		
		/* Creating Subject For Configuring Pre-requisite */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnVisitRow(visitNameObsroNotCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		loginPage.logoutApplication();
		/* Subject Created Successfully */
	}

	/**
	 * =============================================================================================================================================
	 * Test Case Id: FP-TC-1484 Test Case Name:Non-CR ClinRO assessment
	 * (PRO/ObsRO) and not Assigned rater without claim
	 * =============================================================================================================================================
	 * 
	 * @throws Exception
	 * 
	 */
	@Test(description = "FP-TC-1484_Non-CR ClinRO assessment (PRO/ObsRO) and not Assigned rater without claim", groups = {})
	public void FPTC_1484_VerifyNonCRClinROAssessmentProObsroAndNotAssignedRaterWithoutClaim() throws Exception {

		reportLog(
				"1.1:As logged in user from PR#2 go to the Study Dashboard > Assessments and open Assessment from PR#4 in Pending status for NOT Assigned person");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
	
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assessment_S_STS, visitNameObsroNotCompleted, subjectName);

		reportLog("1.2:Assessment Details page is opened successfully ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("1.3:No flag Mark as 'Not Administered for PRO/ObsRO forms is displayed. ");
		assessmentDetailPage.verifyCheckBoxMarkAsNotAdministeredNotExist();

		reportLog("1.4:Mark as 'Not Completed' is displayed");
		assessmentDetailPage.verifyCheckboxMarkAsNotCompletedAndAdminsiteredExistAndEditable(Constants.MarkAsNotCompletedLabel);
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog(
				"2.1:Re-login with user from PR#1 and go to the Study Dashboard > Assessments and open Assessment from PR#5");
		dashBoardPage = loginPage.siteLogin(AT_PRODInvestigator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitName, subjectName);

		reportLog("2.2:Assessment Details page is opened successfully.  ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.3:No flag Mark as 'Not Administered' for non-CR ClinRo form is displayed.");
		assessmentDetailPage.verifyCheckBoxMarkAsNotAdministeredNotExist();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}