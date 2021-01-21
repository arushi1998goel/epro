package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.AssessmentsDetailsPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1178_UpdateItemValuesOfPaperTranscriptionAssessment_SIP extends BaseTest {
	private String studyName, visitName1,completedButNotAssignedObserver;
	private String startedTime = "10:30", durationTime = "14:30";
	private AssessmentsDetailsPage assessmentsDetailsPage;
	private String observerAlias = "Auto" + generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1178_UpdateItemValuesOfPaperTranscriptionAssessment_SIP(String browser) {
		super(browser);
	}

	private String version1Value="AutoVersion"+generateRandomString(2); 
	private String version2Value="AutoVersion"+generateRandomString(2);  
	private String version3Value="AutoVersion"+generateRandomString(2);  
	private String version4Value="AutoVersion"+generateRandomString(2);  
	private String version5Value="AutoVersion"+generateRandomString(2);  
	
	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties testData = Configuration.readTestData("RegressionTestData");		
		studyName = testData.getProperty("AutomationStudyName");
		visitName1 = testData.getProperty("Auto_Paper_OBSRO_Visit1");
		completedButNotAssignedObserver = testData.getProperty("Auto_Observer_Relation1");
				
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog(" Verify user login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("Click on add subject button to create new subject to configure the test prerequiste");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(AT_PRODSiteCoordinatorUserName);

		reportLog("Input " + screeningNum + " screening number to configure the test prerequiste");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);

		reportLog("Select " + studyLanguage
				+ " language from the configured study language options to configure the test prerequiste");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("Click on save button to configure the test prerequiste and verify subject is created");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("Click on reported outcome button to add " + completedButNotAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("Click on add observer button to add " + completedButNotAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnAddObserverBtN();

		reportLog("Enter " + completedButNotAssignedObserver + " in relation field to configure test pre requiste");
		subjectDetailPage.inputObserverRelationName(completedButNotAssignedObserver);

		reportLog("Enter " + observerAlias + " in alias field to to add " + completedButNotAssignedObserver
				+ " observer configure test pre requiste");
		subjectDetailPage.inputObserverAliasName(observerAlias);

		reportLog("Click on save button to to add " + completedButNotAssignedObserver
				+ " observer to configure test pre requiste");
		subjectDetailPage.clickOnObserverSaveBTN();

		reportLog("Click on save button of reported outcomes");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		reportLog("Click on " + visitName1 + " visit to configure the test case prerequiste");
		subjectDetailPage.clickOnVisitRow(visitName1);

		reportLog("Click on Add visit button to configure the test case prerequiste");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("Select the option Enter Paper Transcription");
		subjectDetailPage.clickOnEnterPaperTranscription();

		reportLog("Provide a " + Constants.Visit_Reason_For_Change + " reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Visit_Reason_For_Change);

		reportLog("complete e-sign and save");
		assessmentsDetailsPage = subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("Select " + completedButNotAssignedObserver + " rater to make save button enabled");
		assessmentsDetailsPage.selectRater(completedButNotAssignedObserver);

		reportLog("Select started date to make save button enabled");
		assessmentsDetailsPage.setStartedDate();

		reportLog("set " + startedTime + " started time to make save button enabled");
		assessmentsDetailsPage.setStartedTime(startedTime, "AM");

		reportLog("Select " + durationTime + " duration time to make save button enabled");
		assessmentsDetailsPage.setDurationTime(durationTime);

		reportLog("Click on source button to upload a file so that save button enabled");
		assessmentsDetailsPage.clickOnSourceDocumentBtn();

		reportLog("Upload a " + Constants.PDFFileToUpload
				+ " paper transcription file so that save button enabled");
		assessmentsDetailsPage.uploadSourceFile(Constants.exeFilePath, Constants.PDFFileToUpload,
				Constants.windowTitle);

		reportLog("Select the option to save");
		assessmentsDetailsPage.clickOnSaveBTN();

		reportLog("Click on Refresh button to update the page");
		assessmentsDetailsPage.clickOnRefreshBtn();
		
		reportLog("Edit the assessment scores");
		assessmentsDetailsPage.clickOnScoreTabEditBTN();
		
		reportLog("The item values become editable"); 	
		assessmentsDetailsPage.verifyScoresInputIsEditable();

		reportLog("Change the assessment scores");
		assessmentsDetailsPage.enterScoreResponseValues("Response1", version1Value);

		reportLog("click on save button to modified the assessment scores");
		assessmentsDetailsPage.clickOnSourceTabSaveBTN();

		reportLog("Esign to modified the assessment scores");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("Navigate back to Subject Details page by click on subject link");
		subjectDetailPage = assessmentsDetailsPage.clickOnSubjectLink();
		
		reportLog("Verify Subject detail page has been open"); 
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
		
		reportLog("Click on " + visitName1 + " visit to open the assessment page");
		subjectDetailPage.clickOnVisitRow(visitName1);


	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1178 Test Case Name: In portal - Update item values
	 * of a paper transcription assessment
	 * =========================================================================
	 * 
	 */

	@Test(description = "FP-TC-1178_In portal - Update item values of a paper transcription assessment", groups = {
			"StudyModule" })
	public void FPTC_1178_VerifyInPortalUpdateItemValuesOfAPaperTranscriptionAssessment() {
		
		reportLog("Navigate to the assessment details page ");
		subjectDetailPage.clickOnPaperTranscriptedCompletedFormToOpenAssesmentPage();
	
		reportLog("Verify Assessment details page is visible");
		assessmentsDetailsPage.verifyAssessmentDetailsDisplayed();

		reportLog("Select the option to edit the item values of the assessment");
		assessmentsDetailsPage.clickOnScoreTabEditBTN();
		
		reportLog("The item values become editable"); 	
		assessmentsDetailsPage.verifyScoresInputIsEditable();

		reportLog("Change the assessment scores");
		assessmentsDetailsPage.enterScoreResponseValues("Response1", version2Value);

		reportLog("Click on save button to modified the assessment scores");
		assessmentsDetailsPage.clickOnSourceTabSaveBTN();

		reportLog("Verify Modal window for electronic signature appears");
		assessmentsDetailsPage.verifyReasonForChangePopUpDisplayed();
		
		reportLog("Esign to modified the assessment scores");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		
		reportLog("Verify A new version of the assessment is created");
		assessmentsDetailsPage.verifyAssessmentVersionRecordCount(2);
		
		reportLog("Select the previous version and verify Item values of the previous version are displayed");
		assessmentsDetailsPage.selectVersionAndVerifyResponseValue("1", "Response1", version1Value);
		
		reportLog("Select the new version and Verify Item values of the new version are displayed");
		assessmentsDetailsPage.selectVersionAndVerifyResponseValue("2", "Response1", version2Value);
		
		reportLog("Select the option to edit the item values of the assessment");
		assessmentsDetailsPage.clickOnScoreTabEditBTN();
		
		reportLog("The item values become editable"); 	
		assessmentsDetailsPage.verifyScoresInputIsEditable();

		reportLog("Change the assessment scores");
		assessmentsDetailsPage.enterScoreResponseValues("Response1", version3Value);

		reportLog("Click on save button to modified the assessment scores");
		assessmentsDetailsPage.clickOnSourceTabSaveBTN();

		reportLog("Verify Modal window for electronic signature appears");
		assessmentsDetailsPage.verifyReasonForChangePopUpDisplayed();
		
		reportLog("Esign to modified the assessment scores");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		
		reportLog("Verify A new version of the assessment is created");
		assessmentsDetailsPage.verifyAssessmentVersionRecordCount(3);
		
		reportLog("Select the previous version and verify Item values of the previous version are displayed");
		assessmentsDetailsPage.selectVersionAndVerifyResponseValue("2", "Response1", version2Value);
		
		reportLog("Select the new version and Verify Item values of the new version are displayed");
		assessmentsDetailsPage.selectVersionAndVerifyResponseValue("3", "Response1", version3Value);
		
		reportLog("Select the option to edit the item values of the assessment");
		assessmentsDetailsPage.clickOnScoreTabEditBTN();
		
		reportLog("The item values become editable"); 	
		assessmentsDetailsPage.verifyScoresInputIsEditable();

		reportLog("Change the assessment scores");
		assessmentsDetailsPage.enterScoreResponseValues("Response1", version4Value);

		reportLog("Click on save button to modified the assessment scores");
		assessmentsDetailsPage.clickOnSourceTabSaveBTN();

		reportLog("Verify Modal window for electronic signature appears");
		assessmentsDetailsPage.verifyReasonForChangePopUpDisplayed();
		
		reportLog("Esign to modified the assessment scores");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		
		reportLog("Verify A new version of the assessment is created");
		assessmentsDetailsPage.verifyAssessmentVersionRecordCount(4);
		
		reportLog("Select the previous version and verify Item values of the previous version are displayed");
		assessmentsDetailsPage.selectVersionAndVerifyResponseValue("3", "Response1", version3Value);
		
		reportLog("Select the new version and Verify Item values of the new version are displayed");
		assessmentsDetailsPage.selectVersionAndVerifyResponseValue("4", "Response1", version4Value);
		
		reportLog("Select the option to edit the item values of the assessment");
		assessmentsDetailsPage.clickOnScoreTabEditBTN();
		
		reportLog("The item values become editable"); 	
		assessmentsDetailsPage.verifyScoresInputIsEditable();

		reportLog("Change the assessment scores");
		assessmentsDetailsPage.enterScoreResponseValues("Response1", version5Value);

		reportLog("Click on save button to modified the assessment scores");
		assessmentsDetailsPage.clickOnSourceTabSaveBTN();

		reportLog("Verify Modal window for electronic signature appears");
		assessmentsDetailsPage.verifyReasonForChangePopUpDisplayed();
		
		reportLog("Esign to modified the assessment scores");
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		
		reportLog("Verify A new version of the assessment is created");
		assessmentsDetailsPage.verifyAssessmentVersionRecordCount(5);
		
		reportLog("Select the previous version and verify Item values of the previous version are displayed");
		assessmentsDetailsPage.selectVersionAndVerifyResponseValue("4", "Response1", version4Value);
		
		reportLog("Select the new version and Verify Item values of the new version are displayed");
		assessmentsDetailsPage.selectVersionAndVerifyResponseValue("5", "Response1", version5Value);
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
		
	}
}
