package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.jfree.util.Log;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1536_MoveAssessmentWithFeedback_SIP extends BaseTest {
  
	private String VisitCompleted,visitNotCompleted;
	
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1536_MoveAssessmentWithFeedback_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		subjectName=properties.getProperty("SubjectWithAssessmentFeedBack");
		VisitCompleted = properties.getProperty("CDRVisitCompletedFirstVisit2187");
		visitNotCompleted = properties.getProperty("Visit2187CDRVisitThirdCompleted");
	}
	
	/**
	 * ===================================================================================================================
	 * Test Case Id: SFB-TC-2187 Test Case Name: Move Assessment With Feedback
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1536_Move Assessment With Feedback ", groups = { "" })
	public void FPTC_1536_VerifyMoveAssessmentWithFeedback() {

		reportLog("1.1:Log in to the Portal as a User from Pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User from PR#1 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3:Open the Study Navigator-> Select a Study and a Site from PR#2 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("1.4: Navigate to the Assessment Listing screen and select the 1st Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assessment_CdrFormName, VisitCompleted, subjectName);
		
		reportLog("1.5:Visit Assessment screen from PR#3 is displayed  ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("1.6:Action option is displayed for the Assessment ");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("1.7:Select - Action to Change Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("1.8:Check that Visit PR#4 is displayed in Change To Assessment drop-down list  ");
		reportLog("1.9:Assessment from PR#4 is displayed in Change To Assessment drop down list  ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(visitNotCompleted);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();

		reportLog("1.10:Change Assessment PR#3 to Assessment PR#4  ");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.changeVisitDropDown(visitNotCompleted);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("1.11:Save changes ");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("1.12:The Assessment was changed ");
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("2.1:	Check that assessment level feedback will be moved to destination Assessment");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assessment_CdrFormName, visitNotCompleted, subjectName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
	}
	
	@AfterMethod
	public void updateVisitValueInPropertiesFile(ITestResult result) {
		if(ITestResult.SUCCESS==result.getStatus()) {
			Configuration.updatePropertyTestData("RegressionTestData", "CDRVisitCompletedFirstVisit2187",visitNotCompleted);
			Configuration.updatePropertyTestData("RegressionTestData", "Visit2187CDRVisitThirdCompleted",VisitCompleted);
		}else {
			Log.error(screeningNum + " subject is not added for " + studyName+ " study.");
		}
	}

}
