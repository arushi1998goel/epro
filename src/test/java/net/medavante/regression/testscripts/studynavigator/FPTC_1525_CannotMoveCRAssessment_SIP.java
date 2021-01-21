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

public class FPTC_1525_CannotMoveCRAssessment_SIP extends BaseTest {

	private String subjectNameCompletedCR, VisitFirstCompletedNonCR, visitSecondNotCompletedCR, visitCompletedCR,
			subject2 = "AutoSubject22171" + generateRandomAlphanumericString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1525_CannotMoveCRAssessment_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		subjectNameCompletedCR = properties.getProperty("Subject2172MoveAssessment");
		VisitFirstCompletedNonCR = properties.getProperty("visitClinRoSubmitted");
		visitCompletedCR = properties.getProperty("Auto_CR_Visit1");
		visitSecondNotCompletedCR = properties.getProperty("Automation_CR_Visit3");

		/* Creating Subject and Configuring Not Completed Assessment */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subject2);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		/* Subject Created Successfully */

		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitSecondNotCompletedCR);
		subjectDetailPage.clickOnAddVisitIcon();
		loginPage.logoutApplication();

	}

	/**
	 * ==========================================================================================================================
	 * Test Case Id: FP-TC-1525 Test Case Name:Can not Move CR Assessment
	 * 
	 * ==========================================================================================================================
	 */

	@Test(description = "FP-TC-1525_Can not Move CR Assessment", groups = { " " })

	public void FPTC_1525_verifyCannotMoveCRAssessment() {

		reportLog("1.1:Log in to the Portal with PR#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User from PR#1 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3:Open the Study Dashboard -> Select a Study and a Site from PR#2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.1:Navigate to the Assessment listing screen and select the Assessment from PR#4.1");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitCompletedCR, subjectNameCompletedCR);

		reportLog("2.2:Assessment detail screen is displayed ");

		reportLog("2.3:Check that actions for moving an Assessment isn't displayed");
		reportLog("2.4:Action option isn't displayed for the CR Assessment");
		assessmentDetailPage.verifyActionButtonIsNotDisplayed();

		reportLog("2.5:Navigate to the Assessment listing screen and select the Assessment from PR#4.2");
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, VisitFirstCompletedNonCR, subjectNameCompletedCR);

		reportLog("2.6:Assessment detail screen is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2.7:Check that Visit Pr.#4.3 isn't displayed in Move To Visit drop down list");
		reportLog("2.8:Assessment from PR#4.3 isn't displayed in Move To Assessment drop-down list");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectNameCompletedCR);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visitSecondNotCompletedCR);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		assessmentDetailPage.navigateBackToDashBoard();
		
		reportLog("3.1:Navigate to the Assessment listing screen and select the Assessment from PR#4.2");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, VisitFirstCompletedNonCR, subjectNameCompletedCR);

		reportLog("3.2:Assessment detail screen is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.3:Check that Visit Pr.#5 isn't displayed in Move To Visit drop down list");
		reportLog("3.4:Assessment from PR#5 isn't displayed in Move To Assessment drop-down list");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subject2);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visitSecondNotCompletedCR);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();
		assessmentDetailPage.navigateBackToDashBoard();
		
		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}