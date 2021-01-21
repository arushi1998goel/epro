package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1529_CannotMoveNotCompletedAssessment_SIP extends BaseTest {

	private String VisitFirstNotCompleted, visitSecondNotCompleted,
			subjectName = "Auto2174" + generateRandomAlphanumericString(4);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1529_CannotMoveNotCompletedAssessment_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		VisitFirstNotCompleted = properties.getProperty("visitClinRoSubmitted");
		visitSecondNotCompleted = properties.getProperty("visitForRenameStatus");

		/* Creating Subject and Configuring Not Completed Assessment */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		/* Subject Created Successfully */
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(VisitFirstNotCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.clickOnVisitRow(visitSecondNotCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		loginPage.logoutApplication();

	}

	/**
	 * ==========================================================================================================================
	 * Test Case Id: FP-TC-1529 Test Case Name:Cannot Move NotCompleted Assessment
	 * 
	 * ==========================================================================================================================
	 */

	@Test(description = "FP-TC-1529_Can not Move Not Completed Assessment", groups = { " " })

	public void FPTC_1529_verifyCannotMoveNotCompletedAssessment() {

		reportLog("1.1:Log in to the Portal with PR#1");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2:User from PR#1 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3:-> Open the Study Dashboard -> Select a Study and a Site from PR#2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("1.4:Dashboard for the PR#2 displayed");
		studyNavigatorDashBoardPage.verifyStudyDashBoardPage();

		reportLog("2.1:Navigate to the Assessment Listing screen and select the Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, VisitFirstNotCompleted, subjectName);

		reportLog("2.2:	Visit Assessment screen from PR#3 is displayed ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.3:Action to Move Assessment not displayed for an Assessment  ");
		assessmentDetailPage.verifyActionButtonIsNotDisplayed();
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class, Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("3.1:	Navigate to the Assessment Listing screen and select the Non-CR Assessment_3 from PR#4");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitSecondNotCompleted, subjectName);

		reportLog("3.2:	Non-CR Assessment_3 screen from PR#4 is displayed ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.3:	Action to Move Assessment not displayed for an Assessment");
		assessmentDetailPage.verifyActionButtonIsNotDisplayed();

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
