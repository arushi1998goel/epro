package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1530_MoveAssessmentWithEqualForm_SIP extends BaseTest {

	private String VisitFirstCompleted, visitSecondCompleted, visitThirdCompleted, visitNonCrAssessmentDiiferentForm,
			visitCDRDifferentVersion, visitCDRSameVersion,
			subjectName="Auto2175"+generateRandomAlphanumericString(4);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1530_MoveAssessmentWithEqualForm_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		VisitFirstCompleted = properties.getProperty("CDRVisitCompletedFirst2175");
		visitSecondCompleted = properties.getProperty("CDRVisitSecondCompleted");
		visitThirdCompleted = properties.getProperty("CDRVisitThirdCompleted");
		visitCDRSameVersion = properties.getProperty("CDRSameVersion2175");
		visitCDRDifferentVersion = properties.getProperty("CDRDifferentFormVersion");
		visitNonCrAssessmentDiiferentForm = properties.getProperty("visitClinRoSubmitted");
		
		
		
		reportLog("Creating a subject from user and configure studies accordingly");
		
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		/*First visit assign rater for completion*/
		subjectDetailPage.clickOnVisitRow(VisitFirstCompleted);
		subjectDetailPage.clickOnAddVisitIcon();		
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		
		/*Second visit assign rater for completion*/
		
		subjectDetailPage.clickOnVisitRow(visitSecondCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		
		/*Third visit assign rater for completion*/
		subjectDetailPage.clickOnVisitRow(visitThirdCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectClinRoDropDownOption(Constants.ATAssignedRater_10);
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
	
		/*Navigating to assessment list for First visit completion*/
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("Search the subject in assessment page");
		reportLog("Click on Assessment link");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(VisitFirstCompleted, subjectName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN,SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToStudyNavigator();
		/*Navigating to assessment list for Second visit completion*/
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(visitSecondCompleted, subjectName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN,SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		dashBoardPage.navigateToStudyNavigator();
		/*Navigating to assessment list for Second visit completion*/
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(visitThirdCompleted, subjectName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN,SuperAdminPW);		
		loginPage.logoutApplication();
		
	}

	/**
	 * ===================================================================================================================
	 * Test Case Id: FP-TC-1530 Test Case Name: Move Assessment with equal form
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1530_Move Assessment with equal form ", groups = { "" })
	public void FPTC_1530_VerifyMoveAssessmentWithEqualForm() {

		reportLog("1.1:Log in to the Portal with PR#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User from PR#1 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:->Open the Study Dashboard -> Select a Study and a Site from PR#2 ");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("2.2:Navigate to the Assessment Listing screen and select the 1st Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage =
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
		 Constants.Assessment_CdrFormName, VisitFirstCompleted, subjectName);
		
		reportLog("2.3:Visit Assessment screen from PR#3 is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("2.4:Action option is displayed for an Assessment ");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("2.5:Select action to moving an Assessment");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("2.6:Check that Visit PR#4.1 is displayed in Move To Assessment drop-down list  ");
		reportLog("2.7 :Assessment from PR#4.1 is displayed in Move To Assessment drop-down list ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(visitCDRSameVersion);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();

		reportLog("2.8:Move Assessment PR#3 to Assessment PR#4.1 ");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.changeVisitDropDown(visitCDRSameVersion);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("2.9:Save changes");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("2.9:The Assessment was moved");
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("3.1:Navigate to the Assessment Listing screen and select the 2nd Assessment from PR#3 ");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				 Constants.Assessment_CdrFormName, visitSecondCompleted, subjectName);

		reportLog("3.2:Visit Assessment screen from PR#3 is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.3:Action option is displayed for an Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("3.4:Select action to moving an Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("3.5:Check that Visit PR#4.2 is displayed in Move To Assessment drop-down list ");
		reportLog("3.6:Assessment from PR#4.2 is displayed in Move To Assessment drop-down list ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(visitCDRDifferentVersion);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();

		reportLog("3.7:Move Assessment PR#3 to Assessment PR#4.2 ");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.changeVisitDropDown(visitCDRDifferentVersion);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("3.8:Save changes");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("3.9:The Assessment was moved");
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("4.1:Navigate to the Assessment Listing screen and select the 3d Assessment from PR#3");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				 Constants.Assessment_CdrFormName, visitThirdCompleted, subjectName);

		reportLog("4.2:Assessment Detail screen is displayed ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("4.3:Action option is displayed for an Assessment");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("4.4:Select action to moving an Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("4.5:Check that Visit PR#5 NOT displayed in Move To Assessment drop-down list");
		reportLog("4.6:Assessment from PR#5 NOT displayed in Move To Assessment drop-down list");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName);
		assessmentDetailPage.clickOnChangeToVisitDropDown();
		assessmentDetailPage.verifyVisitIsNotPresentInChangeToVisitDropDown(visitNonCrAssessmentDiiferentForm);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}


}
