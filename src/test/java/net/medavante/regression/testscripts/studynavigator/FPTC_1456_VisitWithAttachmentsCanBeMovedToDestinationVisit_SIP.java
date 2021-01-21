package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.ApplicationVerificationMessage;
import net.medavante.portal.utilities.Constants;

public class FPTC_1456_VisitWithAttachmentsCanBeMovedToDestinationVisit_SIP extends BaseTest {
	private String visitNameWithAttachmentCompleted, notAssignedClinroVisit,subjectName="Auto2148Subject"+generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1456_VisitWithAttachmentsCanBeMovedToDestinationVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
		visitNameWithAttachmentCompleted = properties.getProperty("VisitToSubject1");
		notAssignedClinroVisit = properties.getProperty("VisitNotAssigned");
		
		/*Creating Subject For Configuring Pre-requisite*/
		
		reportLog("1:Login To Application And Setting Up The Pre-Requisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("Subject Created Successfully");
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog("Completing Assessment By Adding Attachment In Visit");		
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(visitNameWithAttachmentCompleted);
	    subjectDetailPage.clickOnInitiateVisitIcon();
	    subjectDetailPage.verifyDetailsSectionIsdisplayed();
	    subjectDetailPage.clickOnCalenderVisitdDocumentToShow(Constants.SubjectCategoryFilter_Attachments);
	    subjectDetailPage.clickOnAddAttachmentIcon();
	    subjectDetailPage.verifyUploadFilesPopUpIsDisplayed();

	    reportLog("Upload a " + Constants.exeFilePath + " File");
	    subjectDetailPage.uploadFile(Constants.PDFFileToUpload);   
	    
	    subjectDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
	    studyNavigatorDashBoardPage.navigateToAssessmentsListing();
	    assessmentDetailPage=studyNavigatorDashBoardPage.selectByVisitAndSubjectName(visitNameWithAttachmentCompleted,subjectName);
	    assessmentDetailPage.markAsNotAdministred(SuperAdminUN, SuperAdminPW);
		assessmentDetailPage.navigateBackToDashBoard();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(notAssignedClinroVisit);
		subjectDetailPage.verifyDetailsSectionIsdisplayed();
		subjectDetailPage.clickOnInitiateVisitIcon();
		loginPage.logoutApplication();	
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1456 Test Case Name:Move Visit -Move Visit - option
	 * to move Visit with attachments can be moved to destination Visit
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1456 _Move Visit - option to move Visit with attachments can be moved to destination Visit", groups = {})
	public void FPTC_1456_VerifyMoveVisitOptionToMoveVisitWithAttachmentsCanBeMovedToDestinationVisit() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:	User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.2:Navigate to Visit listing screen ");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("2.3:select Visit from Pr.#2");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitNameWithAttachmentCompleted, subjectName);

		reportLog("2.4:Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();
		
		reportLog("3.3:'Action' option is displayed");
		visitDetaiLPage.verifyActionButtonIsDisplayed();
		
		reportLog("3.1:Select action to moving visit ");
		visitDetaiLPage.selectActionToMoveVisit();
		
		reportLog("3.2:Check that Visit Pr.#3 is displayed in ‘Move To’ Visit drop down list");
		reportLog("3.4:Visit from Pr.#3 is displayed in ‘Move To’ Visit drop down list");
		visitDetaiLPage.clickOnChangeToSelectSubjectDropDown();
		visitDetaiLPage.selectChangeToSubject(subjectName);
		visitDetaiLPage.clickOnChangeToVisitDropDown();
		visitDetaiLPage.verifyVisitIsPresentInChangeToVisitDropDown(notAssignedClinroVisit);
		
		reportLog("4.1:Select the Visit from Pr.#3");
		visitDetaiLPage.selectChangeToVisit(notAssignedClinroVisit);

		reportLog("4.2:	The Visit from Pr.#3 is selected");
		visitDetaiLPage.verifyChangeToVisitSelected(notAssignedClinroVisit);
	
		reportLog("5.1: Select an action to Save the changes (Control 'Save')");
		visitDetaiLPage. clickOnSaveButtonOnChangeAssesment();
		
		reportLog("5.2: Select an action to Confirm the changes (Control 'Confirm')");
		visitDetaiLPage. clickOnConfirmButtonOfChangeAssesment();
		
		reportLog("5.3:Check that E-signature window appears after the moving has been confirmed");
		reportLog("5.4: E-signature window appears after the moving has been confirmed");
		visitDetaiLPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		

		reportLog("6.1:Select control to sign the Reasons to Change ('Ok')");
		visitDetaiLPage.selectReasonForChangeOption(Constants.Visit_Move_TO_IncorrectAdministered_Option);
		visitDetaiLPage.inputReasonComment(generateRandomString(2));
		visitDetaiLPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);

		reportLog("6.2:Visit successfully moved");
		visitDetaiLPage.verifySuccessMessage(ApplicationVerificationMessage.moveVisitSuccessfulMessage);
		visitDetaiLPage.closeSuccessMessage();

		reportLog("6.3:Visit detail screen is displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("7.1:	Check that status for moved Visit is Completed");
		reportLog("7.2:Status for moved Visit is Completed");
		visitDetaiLPage.verifyVisitDetails(Constants.StudyDashBoard_columnName_Status, Constants.Complete_Status);
		visitDetaiLPage.navigateBack();
		visitDetaiLPage.navigateBack();
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		
		reportLog("8.1:	Open 'Source Subject Status' for Subject Pr#2");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog(
				"8.2:Check that Source Visit becomes blank after a successful moving. And all assessments are not assigned to any rater");
		reportLog(
				"8.3:	Source Visit becomes blank after a successful moving. And all assessments are not assigned to any rater");
		subjectDetailPage.clickOnCalendarVisitRow(visitNameWithAttachmentCompleted);
		subjectDetailPage.verifyInitiateButtonIsDisplayed();
		subjectDetailPage.clickOnInitiateVisitIcon();
		subjectDetailPage.verifyAssesmentNotAssignedForCalenderVisit();

		reportLog("9.1:Open 'Subject Status History' Pr#3");
		subjectDetailPage.clickOnShowHistory();
		subjectDetailPage.verifyHistoryPopUpDisplayed();
		reportLog(
				"9.2: Check that 'Subject Status History' is updated and have the latest status in order of visit completion by system change.");
		reportLog(
				"9.3:'Subject Status History' is updated and have the latest status in order of visit completion by system change");
		
		subjectDetailPage.verifySubjectStatusHistoryContainsInformation();
		subjectDetailPage.clickOnCloseHistory();
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("10.1:Open corresponding assessments");
		
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage=studyNavigatorDashBoardPage.selectByVisitAndSubjectName(notAssignedClinroVisit,subjectName);
	
		reportLog(
				"10.2:Check that the Destination Visit has received all corresponding data from the source Assessment:");
		reportLog("10.8: Destination Visit has received all corresponding data from the source Assessment:");
		reportLog("10.3:Assessments detail screen");
		reportLog("10.9:Assessments detail screen");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("10.4:Cover page on PDF Assessment");
		reportLog("10.10:Cover page on PDF Assessment");
		assessmentDetailPage.clickOnPdfImage();

		reportLog("10.5:Footer on PDF Assessment");
		reportLog("10.11:Footer on PDF Assessment");
		assessmentDetailPage.verifyFooterDataOnEachPage(studyName);

		reportLog("10.7: Navigte Back ");
		assessmentDetailPage.clickOnCancelButton();
		assessmentDetailPage.navigateBack();
		
		reportLog("11.1: Check that all the visit level attachments will be moved to destination visit");
		reportLog("11.2: All the visit level attachments is moved to destination visit");
        studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(notAssignedClinroVisit);
		subjectDetailPage.clickOnCalenderVisitdDocumentToShow( Constants.SubjectCategoryFilter_Attachments);
		subjectDetailPage.verifyCalenderVisitAttachmentPresent();
		
		
		reportLog("11.3:Logout from the application");
		loginPage.logoutApplication();

		reportLog("11.4:Verify user is logout");
		loginPage.verifyUserLogout();
		

	}

}
