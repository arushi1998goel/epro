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

public class FPTC_1548_MoveAssessmentWhenPageOpenFromVisitDetails_SIP extends BaseTest {

	private String VisitCompleted, visitNotAssigned,
			subjectName = "AutoSubject2238" + generateRandomAlphanumericString(5);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1548_MoveAssessmentWhenPageOpenFromVisitDetails_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyMoveVisit");
		VisitCompleted = properties.getProperty("visitClinRoSubmitted");
		visitNotAssigned = properties.getProperty("visitClinRoNotAssigned");

		/* Creating Subject and Configuring Not Completed Assessment */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		/* Subject Created Successfully */

		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(VisitCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, VisitCompleted, subjectName);
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}
	

	/**
	 * ===================================================================================================================
	 * Test Case Id: FP-TC-1548 Test Case Name: Move Assessment. Move an Assessment when the Assessment Details page was opened from Visit Details page 
	 * page
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1548_Move Assessment. Move an Assessment when the Assessment Details page was opened from Visit Details page  ", groups = {
			"" })
	public void FPTC_1548_VerifyMoveAnAssessmentWhenTheAssessmentDetailsPageWasOpenedFromVisitDetailsPage() {

		reportLog("1.1:Log in to the Portal as a User from Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User from PR#2 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Navigate to Visit Listing screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToVisitsListing();
	
		reportLog("2.2:The list of Visits are displayed ");
		studyNavigatorDashBoardPage.verifyVisitListIsOpened();

		reportLog("2.3:The Visit from Pr#3 is displayed in the list");
		studyNavigatorDashBoardPage.verifyVisitIsDisplayedInVisitList(subjectName,VisitCompleted);

		reportLog("3.1:Select the Visit from Pr#3 in the list of Visits");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(VisitCompleted, subjectName);

		reportLog("3.2:Visit Details page is opened");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("4.1:Navigate to the Assessment Details page by clicking on Assessment thumbnail");
		assessmentDetailPage=visitDetaiLPage.clickOnAfterNotAdministeredThumbnailImage();

		reportLog("4.2:Assessment Details page is opened ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("4.3:Action' control to Move the Assessment is available on the page");
		assessmentDetailPage.verifyActionOptionIsDisplayed();

		reportLog("5.1: Select an action to move Assessment ");
		assessmentDetailPage.selectActionToMoveAssessment();

		reportLog("5.2:Move Assessment window is displayed ");
		assessmentDetailPage.verifyMoveAssessmentPopUpWindowIsDisplayed();

		reportLog("5.3: Select the Subject and Visit from Pr#4 in the Move to filter ");
		assessmentDetailPage.clickOnChangeToSelectSubjectDropDown();
		assessmentDetailPage.selectChangeToSubject(subjectName);
		assessmentDetailPage.changeVisitDropDown(visitNotAssigned);
		
		reportLog("5.4: Subject and Visit are selected in the Move To filter");
		assessmentDetailPage.verifyChangeToVisitSelected(visitNotAssigned);
		assessmentDetailPage.verifyChangeToSubjectSelected(subjectName);

		reportLog("6.1: Confirm moving by clicking on corresponding control");
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();

		reportLog("6.2: Reason for Change window appears");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
	
		reportLog("7.1:Select the reason for change - Enter username and password Select the confirmation control");
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("7.2:The Assessment is moved ");
		assessmentDetailPage.verifyAssesmentChanged();
	
		reportLog("7.3:The message appears on the screen, that Assessment was successfully moved");
		assessmentDetailPage.verifyAssessmentSuccessfullyMessage();
		assessmentDetailPage.closeAssesmentSuccessMessage();
		
		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
