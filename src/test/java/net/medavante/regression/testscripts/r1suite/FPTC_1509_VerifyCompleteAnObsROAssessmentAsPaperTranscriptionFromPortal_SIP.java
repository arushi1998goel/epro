package net.medavante.regression.testscripts.r1suite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;
import net.medavante.portal.utilities.SubjectsModuleConstants;

public class FPTC_1509_VerifyCompleteAnObsROAssessmentAsPaperTranscriptionFromPortal_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1509_VerifyCompleteAnObsROAssessmentAsPaperTranscriptionFromPortal_SIP(String browser) {
		super(browser);
	}

	private String studyName, visitName1, startedTime = "12:05", durationTime = "14:30";
	private String observerRelation1, observerAlias = "Auto" + generateRandomString(2);

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
	
		studyName = properties.getProperty("AutomationStudyName");
		visitName1 = properties.getProperty("Auto_Paper_OBSRO_Visit1");
		observerRelation1 = properties.getProperty("Auto_Observer_Relation1");

		reportLog("1.1  Login to the portal as the user exists having claim canManageAssessments i.e. with "
				+ AT_PRODInvestigatorRater);
		dashBoardPage = loginPage.loginInApplication(AT_PRODInvestigatorRater, AT_Password);

		reportLog("1.2: Verify User Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);
	
		reportLog("1.5: Click on add subject button to create new subject to configure the test prerequiste");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("1.6: Input " + screeningNum + " Screening num to configure the test prerequiste");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);

		reportLog("1.7: Select " + studyLanguage
				+ " language from the configured study language options to configure the test prerequiste");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("1.8: Click on save button to configure the test prerequiste and verify subject is created");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("1.9: Click on reported outcome button to configure test pre requiste");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("1.10: Click on reported outcome button to configure test pre requiste");
		subjectDetailPage.clickOnAddObserverBtN();

		reportLog("1.11: Enter " + observerRelation1 + " in relation field to configure test pre requiste");
		subjectDetailPage.inputObserverRelationName(observerRelation1);

		reportLog("1.13: Enter " + observerAlias + " in alias field to configure test pre requiste");
		subjectDetailPage.inputObserverAliasName(observerAlias);

		reportLog("1.14: Click on save button to update the observer details to configure test pre requiste");
		subjectDetailPage.clickOnObserverSaveBTN();

		reportLog("1.15: Click on save button of reported outcomes to configure test pre requiste");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		reportLog("1.16: Click on " + visitName1 + " visit to configure the test case prerequiste");
		subjectDetailPage.clickOnVisitRow(visitName1);

		reportLog("1.17: Click on Add visit button to configure the test case prerequiste");
		subjectDetailPage.clickOnAddVisitIcon();
	}

	/**
	 * =========================================================================
	 * =========================================== Test Case Id: FP-TC-1509
	 * Test Case Name:Complete an 'ObsRO' assessment as 'Paper transcription'
	 * from portal
	 * 
	 * =========================================================================
	 * ===========================================
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1509_Verify Complete an 'ObsRO' assessment as 'Paper transcription' from portal", groups = {
			"R1" })
	public void FPTC_1509_verifyCompleteAnObsROAssessmentAsPaperTranscriptionFromPortal()
			throws InterruptedException, Exception {

		reportLog("1.18: Verify " + visitName1 + " visit is displayed");
		subjectDetailPage.verifyVisitAddedIsPresentInList(visitName1);

		reportLog("2.1: Select " + visitName1 + " visit");
		subjectDetailPage.clickOnVisitRow(visitName1);

		reportLog("2.2: Verify Option to complete the assessment as Paper Transcription is visible");
		subjectDetailPage.verifyEnterPaperTranscriptionLINKIsDisplayed();

		reportLog("3.1 Select the option Enter Paper Transcription");
		subjectDetailPage.clickOnEnterPaperTranscription();

		reportLog("3.2 Verify Option to provide reason for change appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("4.1 Provide a " + Constants.Visit_Reason_For_Change + " reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Visit_Reason_For_Change);

		reportLog("4.2 complete e-sign and save");
		assessmentDetailPage = subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODInvestigatorRater,
				AT_Password);

		reportLog("4.3 verify Assessment details page is opened ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("4.4 Verify All the required fields are highlighted ");
		assessmentDetailPage.verifyRequiredFieldsAreRed();

		reportLog("4.5 Verify 'Save' control is display and disabled");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndDisabled();

		reportLog("4.6 Verify 'cancel' control is display and enabled");
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();

		reportLog("4.7 Verify Paper transcription checkbox is selected and disabled");
		assessmentDetailPage.verifyPaperTranscriptionCheckBoxIsSelectedAndDisabled();

		reportLog("4.8 Verify Required Fields are editable");
		assessmentDetailPage.verifyRequiredFieldsAreEditable();

		reportLog("4.9 Verify Scores and Attachments tab are not displayed");
		assessmentDetailPage.verifyScoreAndAttachmentsTabIsNotDisplayed();

		reportLog("5.1 Select " + observerRelation1 + " rater to make save button enabled");
		assessmentDetailPage.selectRater(observerRelation1);

		reportLog("5.2 Select started date to make save button enabled");
		assessmentDetailPage.setStartedDate();

		reportLog("5.3 set " + startedTime + " started time to make save button enabled");
		assessmentDetailPage.setStartedTime(startedTime, "AM");

		reportLog("5.4 Select " + durationTime + " duration time to make save button enabled");
		assessmentDetailPage.setDurationTime(durationTime);

		reportLog("5.5 Click on source button to upload a file so that save button enabled");
		assessmentDetailPage.clickOnSourceDocumentBtn();

		
		//String filePath = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\testdata" + File.separator + "TestPDF.pdf";
		 
		reportLog("5.6 Upload a " + Constants.exeFilePath + " paper transcription file so that save button enabled");
       //assessmentDetailPage.uploadSourceFile(Constants.exeFilePath, Constants.PDFFileToUpload, Constants.windowTitle);
		assessmentDetailPage.uploadFile(Constants.PDFFileToUpload);

		reportLog("5.7 Verify save button enabled");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndEnabled();

		reportLog("5.8 Verify Option to cancel' remains active");
		assessmentDetailPage.verifyCancelButtonIsDisplayedAndEnabled();

		reportLog("6.1 Select to cancel");
		assessmentDetailPage.clickOnCancelAssessmentChangesIcon();

		reportLog("6.2 Verify User redirects to subject details without applying the changes and diplay " + screeningNum
				+ " screening number");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("6.3 Click on " + visitName1 + " visit");
		subjectDetailPage.clickOnVisitRow(visitName1);

		reportLog("6.4 Verify Option to complete the assessment as Paper Transcription is visible");
		subjectDetailPage.verifyEnterPaperTranscriptionLINKIsDisplayed();

		reportLog("7.1 Select the option for Paper Transcription ");
		subjectDetailPage.clickOnEnterPaperTranscription();

		reportLog("7.2 Provide a " + Constants.Visit_Reason_For_Change + " reason");
		subjectDetailPage.selectReasonForChangeOption(Constants.Visit_Reason_For_Change);

		reportLog("7.3 E-sign for paper transcription");
		assessmentDetailPage = subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODInvestigatorRater,
				AT_Password);

		reportLog("7.4 Select " + observerRelation1 + " observer");
		assessmentDetailPage.selectRater(observerRelation1);

		reportLog("7.5 Select started date");
		assessmentDetailPage.setStartedDate();

		reportLog("7.6 set " + startedTime + " started time");
		assessmentDetailPage.setStartedTime(startedTime, "AM");

		reportLog("7.7 set " + durationTime + " duration time");
		assessmentDetailPage.setDurationTime(durationTime);

		reportLog("7.8 Click on source button to upload the paper file");
		assessmentDetailPage.clickOnSourceDocumentBtn();

		reportLog("7.9 Select " + Constants.PDFFileToUpload + " paper file and upload the same");		
		//assessmentDetailPage.uploadSourceFile(Constants.exeFilePath, Constants.PDFFileToUpload, Constants.windowTitle);
		assessmentDetailPage.uploadFile(Constants.PDFFileToUpload);

		reportLog("7.10 Verify Option to save is activated upon completion of the required fields");
		assessmentDetailPage.verifySaveButtonIsDisplayedAndEnabled();

		reportLog("8.1 Select the option to save");
		assessmentDetailPage.clickOnSaveBTN();

		reportLog("8.2 Verify started date is saved");
		assessmentDetailPage.verifyStartedDateIsUserSelected(currentDate());

		reportLog("8.3 Verify " + startedTime + " started time is saved");
		assessmentDetailPage.verifyStartedTimeIsUserSelected(startedTime);

		reportLog("8.4 Verify " + durationTime + " duration is saved");
		assessmentDetailPage.verifyUserSelectedDurationIsSaved(durationTime);

		reportLog("8.5 Verify Edit control is displayed");
		assessmentDetailPage.verifyEditControlIsDisplayedAndEnabled();

		reportLog("8.6 Click on Refresh button to update the page");
		assessmentDetailPage.clickOnRefreshBtn();

		reportLog(
				"8.7 Verify Form is saved to the system as Paper Transcription with Paper clipped thumbnail and shown accordingly on the Portal.'Pending' status is displayed on form thumbnail");
		assessmentDetailPage.verifyPdfStatusTextAndClosedThumbnailImageIsDisplayed(Constants.Pending_Status);

		reportLog("8.8 Verify Scores and Attachments tabs are displayed");
		assessmentDetailPage.verifyScoreAndAttachmentsTabIsDisplayed();

		reportLog("8.9 Verify Edit scores control is displayed on the Scores tab");
		assessmentDetailPage.verifyScoreTabEditBTNIsDisplayed();

		reportLog("8.10 Verify View mode drop-down is available");
		assessmentDetailPage.verifyViewModeDropDownIsDisplayed();

		reportLog("8.11 Verify " + SubjectsModuleConstants.allDetailViewMode + " and "
				+ SubjectsModuleConstants.sourceViewMode + " are available in the View Mode options");
		ArrayList<String> modeViw = new ArrayList<String>(
				Arrays.asList(SubjectsModuleConstants.allDetailViewMode, SubjectsModuleConstants.sourceViewMode));
		assessmentDetailPage.verifyViewModeDropDownOptionIsDisplayed(modeViw);

		reportLog("8.12 Change View Mode options as " + SubjectsModuleConstants.sourceViewMode
				+ " to verify scores editing is available.");
		assessmentDetailPage.changeViewMode(SubjectsModuleConstants.sourceViewMode);

		reportLog("8.13 Verify scores editing is available for " + SubjectsModuleConstants.sourceViewMode);
		assessmentDetailPage.verifyScoreTabEditBTNIsDisplayed();

		reportLog("8.14 Change View Mode options as " + SubjectsModuleConstants.allDetailViewMode);
		assessmentDetailPage.changeViewMode(SubjectsModuleConstants.allDetailViewMode);

		reportLog("9.1 Edit the assessment scores");
		assessmentDetailPage.clickOnScoreTabEditBTN();

		reportLog("9.2 Change the assessment scores");
		assessmentDetailPage.changeTheScore(Constants.Score_Number);

		reportLog("9.3 click on save button to modified the assessment scores");
		assessmentDetailPage.clickOnSourceTabSaveBTN();

		reportLog("9.4 Esign to modified the assessment scores");
		subjectDetailPage.eSignReasonForChangeAndSubmit(AT_PRODInvestigatorRater,
				AT_Password);	

		reportLog("9.5 Verify " + Constants.Complete_Status + "  status is displayed on form thumbnail.");
		assessmentDetailPage.verifyPdfStatusTextAndClosedThumbnailImageIsDisplayed(Constants.Complete_Status);

		reportLog("9.6 Navigate back to Subject Details page by click on subject link");
		subjectDetailPage = assessmentDetailPage.clickOnSubjectLink();

		reportLog("9.7: Select " + visitName1 + " visit");
		subjectDetailPage.clickOnVisitRow(visitName1);

		reportLog("9.8: Verify -Assessment of " + visitName1
				+ " visit displayed with 'Paper Transcription' label and Paper clipped thumbnail");
		subjectDetailPage.verifyPaperClippedThumbnailIsDisplayed();

		reportLog("9.9: User name " + AT_PRODInvestigatorRater + " appears as 'Submitted by");
		subjectDetailPage.verifySubmittedVisitRaterName(AT_PRODInvestigatorRaterUserName);

		reportLog("9.10: Verify Value of Submitted date(UTC) appears as current date");
		subjectDetailPage.verifySubmittedVisitDate(currentDate());

		reportLog("9.11: Logout from application");
		loginPage.logoutApplication();

		reportLog("9.12: Verify User Logout from application");
		loginPage.verifyUserLogout();
	}

}
