package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.*;
import net.medavante.portal.utilities.Constants;

public class FPTC_1510VerifyRequiredFieldsInAssessmentDetailsPage_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1510VerifyRequiredFieldsInAssessmentDetailsPage_SIP(String browser) {
		super(browser);
	}

	private String userName, visitName1, studyName, startedTime="12:30", durationTime="15:30"; 
	private String observerRelation1,observerAlias="Auto"+generateRandomString(2);

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName1 = properties.getProperty("Auto_Paper_ClinRo_Visit1");
		observerRelation1= properties.getProperty("Auto_Observer_Relation1");		

		reportLog("1.1  Login to the portal as the user exists having claim canManageAssessments i.e. with " + userName);
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2: Verify User Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);	
		
		reportLog("1.5: Click on add subject button to create new subject to configure the test prerequiste");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);
		
		reportLog("1.6: Input "+screeningNum+" Screening num to configure the test prerequiste");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);
		
		reportLog("1.7: Select "+studyLanguage+" language from the configured study language options to configure the test prerequiste");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);
		
		reportLog("1.8: Click on save button to save the subject to configure the test prerequiste and verify subject detail page openend");
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
		
		reportLog("1.9: Adding observer to configure the test case prerequiste");
		subjectDetailPage.clickOnReportedOutComeButton();
		
		reportLog("1.10: Click on reported outcome button to configure test pre requiste");
		subjectDetailPage.clickOnAddObserverBtN();
		
		reportLog("1.11: Enter " + observerRelation1+" in relation field to configure test pre requiste");
		subjectDetailPage.inputObserverRelationName(observerRelation1);
		
		reportLog("1.12: Enter " + observerAlias+" in alias field to configure test pre requiste");
		subjectDetailPage.inputObserverAliasName(observerAlias);
		
		reportLog("1.13: Click on save button to update the observer details to configure test pre requiste");
		subjectDetailPage.clickOnObserverSaveBTN();
		
		reportLog("1.14: Click on save button of reported outcomes to configure test pre requiste");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		reportLog("1.15: Verify " + visitName1 + " visit is displayed");
		subjectDetailPage.verifyVisitAddedIsPresentInList(visitName1);

		reportLog("1.16: Click on " + visitName1 + " visit to configure the test case prerequiste");
		subjectDetailPage.clickOnVisitRow(visitName1);

		reportLog("1.17: Click on Add visit button to configure the test case prerequiste");
		subjectDetailPage.clickOnAddVisitIcon(); 

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1510 Test Case Name:Required fields in assessment
	 * details page is showing correctly
	 * 
	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FPTC_1510_Verify Required fields in assessment details page is showing correctly", groups = {
			"R1" })
	public void FPTC_1510_verifyRequiredFieldsInAssessmentDetailsPageIsShowingCorrectly() throws Exception {

		reportLog("2.1 Verify Paper Transcription LINK Is Displayed");
		subjectDetailPage.verifyEnterPaperTranscriptionLINKIsDisplayed();

		reportLog("2.2 Click on Enter Paper Transcription");
		subjectDetailPage.clickOnEnterPaperTranscription();

		reportLog("2.3 Verify reason for change popup appears");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("3.1 Select "+ Constants.Visit_Reason_For_Change + " Reason for change");
		subjectDetailPage.selectReasonForChangeOption(Constants.Visit_Reason_For_Change);

		reportLog("3.2 complete e-sign and save");
		assessmentDetailPage = subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("3.3 Verify Assessment detail page is opened ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.3 Verify Details are editable");
		assessmentDetailPage.verifyRequiredFieldsAreEditable();

		reportLog("4.1 Verify "+ studyName+" study name displayed");
		assessmentDetailPage.verifyDisplayedStudy(studyName);

		reportLog("4.2 Verify "+AT_PRODSiteCoordinatorUserName+" site name display properly");
		assessmentDetailPage.verifyDisplayedSite(AT_PRODSiteCoordinatorUserName);

		reportLog("4.3 Verify "+screeningNum+" subject name display correct");
		assessmentDetailPage.verifyDisplayedSubject(screeningNum);

		reportLog("4.4 Verify Required fields are marked as Red ");
		assessmentDetailPage.verifyRequiredFieldsAreRed();

		reportLog("4.5 Verify Required field names Started, Duration, Source Document are displayed");
		assessmentDetailPage.verifyRequiredFieldsNameIsDisplayed();

		reportLog("5.1 Select "+ observerRelation1+" Rater");
		assessmentDetailPage.selectRater(observerRelation1);

		reportLog("5.2 Select Started Date");
		assessmentDetailPage.setStartedDate();

		reportLog("5.3 Select "+ startedTime+" Started time");
		assessmentDetailPage.setStartedTime(startedTime, "AM");

		reportLog("5.4 Select "+ durationTime+" duration time");
		assessmentDetailPage.setDurationTime(durationTime);

		reportLog("5.5 Verify started date are filled with user input");
		assessmentDetailPage.verifyStartedDateIsUserSelected(currentDate());

		reportLog("5.6 Verify "+ startedTime+" started time are filled with user input");
		assessmentDetailPage.verifyStartedTimeIsUserSelected(startedTime);

		reportLog("5.7 Verify "+ durationTime+" duration time are filled with user input");
		assessmentDetailPage.verifyDurationIsUserSelected(durationTime);

		reportLog("6.1 Select the upload sign of the Source document");
		assessmentDetailPage.clickOnSourceDocumentBtn();

		reportLog("6.2 Verify A window appears for uploading files from user PC");
		assessmentDetailPage.verifyUploadFilesPopUpIsDisplayed();

		reportLog("7.1 Select the cross (x) button of the window");
		assessmentDetailPage.clickOnSourceUploadCloseIcon();

		reportLog("7.2 Verify Window is closed");
		assessmentDetailPage.verifyUploadFilesPopUpIsNotDisplayed();

		reportLog("7.3 Verify started date are filled on assessment detail page");
		assessmentDetailPage.verifyStartedDateIsUserSelected(currentDate());

		reportLog("7.4 Verify "+ startedTime+" started time are filled on assessment detail page");
		assessmentDetailPage.verifyStartedTimeIsUserSelected(startedTime);

		reportLog("7.5 Verify "+ durationTime+" duration time are filled on assessment detail page");
		assessmentDetailPage.verifyDurationIsUserSelected(durationTime);

		reportLog("8.1 Select the upload sign of the Source document");
		assessmentDetailPage.clickOnSourceDocumentBtn();

		reportLog("8.2 Verify A window appears for uploading files from user PC");
		assessmentDetailPage.verifyUploadFilesPopUpIsDisplayed();

		reportLog("9.1 Select Cancel option");
		assessmentDetailPage.clickOnSourceUploadCancelBtn();

		reportLog("9.2 Verify Window is closed");
		assessmentDetailPage.verifyUploadFilesPopUpIsNotDisplayed();

		reportLog("9.3 Verify started date are filled on assessment detail page");
		assessmentDetailPage.verifyStartedDateIsUserSelected(currentDate());

		reportLog("9.4 Verify "+ startedTime+" started time are filled on assessment detail page");
		assessmentDetailPage.verifyStartedTimeIsUserSelected(startedTime);

		reportLog("9.5 Verify "+ durationTime+" duration time are filled on assessment detail page");
		assessmentDetailPage.verifyDurationIsUserSelected(durationTime);

		reportLog("10.1 Select the upload sign of the Source document");
		assessmentDetailPage.clickOnSourceDocumentBtn();

		reportLog("10.2 Verify A window appears for uploading files from user PC");
		assessmentDetailPage.verifyUploadFilesPopUpIsDisplayed();
 
		reportLog("11.1 Press Escape button from keyboard");
		assessmentDetailPage.pressEscapeKey();

		reportLog("11.2 Verify Window is closed");
		assessmentDetailPage.verifyUploadFilesPopUpIsNotDisplayed();
		
		reportLog("11.3 Verify started date are filled on assessment detail page");
		assessmentDetailPage.verifyStartedDateIsUserSelected(currentDate());
		
		reportLog("11.4 Verify "+ startedTime+" started time are filled on assessment detail page");
		assessmentDetailPage.verifyStartedTimeIsUserSelected(startedTime);
		
		reportLog("11.5 Verify "+ durationTime+" duration time are filled on assessment detail page");
		assessmentDetailPage.verifyDurationIsUserSelected(durationTime);

		reportLog("12.1 Select Refresh icon of the page");
		assessmentDetailPage.clickOnRefreshBtn();

		reportLog("12.2 Verify All given inputs are gone and Mandatory fields are highlighted");
		assessmentDetailPage.verifyRequiredFieldsAreRed();

		reportLog("13.1 Now Click on Cancel(x) option of the assessment detail page");
		assessmentDetailPage.clickOnCancelAssessmentChangesIcon();

		reportLog("13.2 Verify User is redirected to subject details page and "+ screeningNum+ " screening number displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("14.1 Select "+ visitName1+" visit ");
		subjectDetailPage.clickOnVisitRow(visitName1);
		
		reportLog("14.2 Click on paper transcription link");
		subjectDetailPage.clickOnEnterPaperTranscription();
		
		reportLog("14.3 verify Paper Transcription popup is displayed");
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();

		reportLog("14.4 Select "+ Constants.Visit_Reason_For_Change+ " reason for change");
		subjectDetailPage.selectReasonForChangeOption(Constants.Visit_Reason_For_Change);

		reportLog("14.5 complete e-sign and save");
		assessmentDetailPage = subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("14.6 Verify Assessment detail page is opened ");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("14.7 Verify Details are editable");
		assessmentDetailPage.verifyRequiredFieldsAreEditable();
		
		reportLog("15.1 Click on Subject name link ");
		subjectDetailPage=assessmentDetailPage.clickOnSubjectLink();
		
		reportLog("15.2: Verify User is redirected to subject details page and "+ screeningNum +" subject name display correctly");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("16.1 Again go back to assessment detail page ");
		subjectDetailPage.navigateBackToPreviousPage();

		reportLog("16.2 Verify Assessment detail page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("16.3 Verify  study name display correctly");
		assessmentDetailPage.verifyDisplayedStudy(studyName);
		
		reportLog("16.4 Verify"+ AT_PRODSiteCoordinatorUserName+ " site name display correctly");
		assessmentDetailPage.verifyDisplayedSite(AT_PRODSiteCoordinatorUserName);
		
		reportLog("16.5 Verify  "+screeningNum+" Screening display correctly");
		assessmentDetailPage.verifyDisplayedSubject(screeningNum);
		
		reportLog("16.6 Verify  required fields are highlighted");
		assessmentDetailPage.verifyRequiredFieldsAreRed();
		
		reportLog("16.7 Verify Required field names Started, Duration, Source Document are displayed");
		assessmentDetailPage.verifyRequiredFieldsNameIsDisplayed();

		reportLog("17.1 Click on Visit name link");
		visitDetaiLPage = assessmentDetailPage.clickOnVisitLink();

		reportLog("17.2 Verify User is redirected to visit details page");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("18.1 Again go back to assessment detail page ");
		visitDetaiLPage.navigateBackToPreviousPage();

		reportLog("18.2 Verify Assessment detail page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("18.3 Verify "+studyName+ " study name display correctly");
		assessmentDetailPage.verifyDisplayedStudy(studyName);
		
		reportLog("18.4 Verify "+AT_PRODSiteCoordinatorUserName+" site name display correctly");
		assessmentDetailPage.verifyDisplayedSite(AT_PRODSiteCoordinatorUserName);
		
		reportLog("18.5 Verify "+screeningNum+"  Screening display correctly");
		assessmentDetailPage.verifyDisplayedSubject(screeningNum);

		reportLog("19.1 Select the upload sign of the Source document");
		assessmentDetailPage.clickOnSourceDocumentBtn();

		reportLog("19.2 Verify A window appears for uploading files from user PC");
		assessmentDetailPage.verifyUploadFilesPopUpIsDisplayed();

		reportLog("20.1 Upload "+Constants.PDFFileToUpload+" a file and save");
		assessmentDetailPage.uploadFile(Constants.PDFFileToUpload);

		reportLog("20.2 Verify "+Constants.PDFFileToUpload+" Source PDF file is uploaded and saved successfully");
		assessmentDetailPage.verifyDocumentIsUploaded(Constants.PDFFileToUpload);

		reportLog("21.1 Logout the user");
		loginPage.logoutApplication();

		reportLog("21.2 Verify user Logout");
		loginPage.verifyUserLogout();
	}

}
