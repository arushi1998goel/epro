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

public class FPTC_1535_ChangeAssessmentWithQueries_SIP extends BaseTest {

	private String VisitCompleted, visitNotAssigned,
			subjectName = "AutoSubject2186" + generateRandomAlphanumericString(5), QueryText = generateRandomString(6);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1535_ChangeAssessmentWithQueries_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		VisitCompleted = properties.getProperty("visitClinRoSubmitted");
		visitNotAssigned = properties.getProperty("visitClinRoNotAssigned");

		/*-------------------- Creating Subject and Configuring Not Completed Assessment--------------------- */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputNotes("notesText");
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName);
		studyNavigatorDashBoardPage.selectSubjectLanguage("English (US)");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		/* Subject Created Successfully */

		/*---------Completing Assessment With Query------------------------------------------------------------*/
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(VisitCompleted);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.navigateBackFromSubjectDetailsPage();
		
		dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, VisitCompleted, subjectName);
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();
		assessmentDetailPage.conifrmButtonAppears();
		assessmentDetailPage.clickOnConfirmButton();
		assessmentDetailPage.clickOnNotAdminsteredPopUpYesButton();
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.addNewQuery(QueryText);
		assessmentDetailPage.clickOnQueriesCollpaseIcon();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * ===================================================================================================================
	 * Test Case Id: FP-TC-1535 Test Case Name: Change Assessment with queries
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1535_Change Assessment with queries  ", groups = { "" })
	public void FPTC_1535_VerifyChangeAssessmentWithQueries() {

		reportLog("1.1:Log in to the Portal as a User from Pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User from PR#1 successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3:Open the Study Navigator-> Select a Study and a Site from PR#2 ");
		
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);		

		reportLog("1.4: Navigate to the Assessment Listing screen and select the Assessment from PR#3");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, VisitCompleted, subjectName);
		
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
		assessmentDetailPage.verifyVisitIsPresentInChangeToVisitDropDown(visitNotAssigned);
		assessmentDetailPage.clickOnCrossIconOfMoveAssessmentPopUp();

		reportLog("1.10:Change Assessment PR#3 to Assessment PR#4  ");
		assessmentDetailPage.selectActionToMoveAssessment();
		assessmentDetailPage.changeVisitDropDown(visitNotAssigned);
		assessmentDetailPage.clickOnSaveButtonOnChangeAssesment();

		reportLog("1.11:Save changes ");
		assessmentDetailPage.clickOnConfirmButtonOfChangeAssesment();
		assessmentDetailPage.selectReasonForChangeOption(Constants.IncorrectAssessmentAdministered);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog("1.12:The Assessment was changed ");
		assessmentDetailPage.verifyAssesmentChanged();
		assessmentDetailPage.closeAssesmentSuccessMessage();
		assessmentDetailPage.navigateBackToDashBoard();

		reportLog("2.1:Check that assessment level queries will be changed to destination Assessment");
		dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List2FormNameSecondType, visitNotAssigned, subjectName);
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		assessmentDetailPage.clickOnQueriesIcon();
		assessmentDetailPage.verifyQueriesDetailsPanelIsOpened();
		assessmentDetailPage.verifyPresenceOfQueriesList();
		assessmentDetailPage.clickOnQueriesCollpaseIcon();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
	}

}
