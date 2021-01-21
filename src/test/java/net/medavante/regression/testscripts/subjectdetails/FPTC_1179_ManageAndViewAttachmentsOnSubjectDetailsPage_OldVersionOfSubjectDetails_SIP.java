package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1179_ManageAndViewAttachmentsOnSubjectDetailsPage_OldVersionOfSubjectDetails_SIP extends BaseTest {

	private String visit;
	private String Subject = "AutoSub_" + generateRandomAlphanumericString(4);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1179_ManageAndViewAttachmentsOnSubjectDetailsPage_OldVersionOfSubjectDetails_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyWithCustomVisits");
		visit = properties.getProperty("AutoCalendarVisit1");

		reportLog("Creating a subject from user and configure studies accordingly");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, Subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(Subject);
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1179 Test Case Name: Manage and view attachments on
	 * Subject details page (Old version of Subject details)
	 * =========================================================================
	 * 
	 * @throws InterruptedException
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1179_ManageAndViewAttachmentsOnSubjectDetailsPage_OldVersionOfSubjectDetails_SIP", groups = {
			"" })
	public void FPTC_1179_ManageAndViewAttachmentsOnSubjectDetailsPage_OldVersionOfSubjectDetails()
			throws InterruptedException, Exception {

		reportLog("1.2: Login to Site Portal");
		loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.3: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.4: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.5: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("1.6: Select subject");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				Subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(Subject);

		reportLog("1.7: Verify Subject details page is opened");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2.1: Verify Visit Category Filters are displayed.");
		subjectDetailPage.verifyVisitCategoryFiltersDisplayed();

		reportLog("2.2: Select All From Visit Category");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("2.3: Select visit row ");
		subjectDetailPage.clickOnCalendarVisitRow(visit);

		reportLog("2.4: Verify attachment tab not appears");
		subjectDetailPage.verifyFilterNotDisplayedInSubjectCategorySection(Constants.SubjectCategoryFilter_Attachments);

		reportLog("2.5: Verify Initiate visit controls is available ");
		subjectDetailPage.verifyInitiateButtonIsDisplayed();

		reportLog("3.1: Select Initiate button for visit ");
		subjectDetailPage.clickOnInitiateVisitIcon();

		reportLog("3.2: Verify Attachments tab appears and is not selected.");
		subjectDetailPage.verifyFilterDisplayedButNotSelectedInSubjectCategorySection(
				Constants.SubjectCategoryFilter_Attachments);

		reportLog("3.3: Verify No attachment count appears on the tab header");
		subjectDetailPage.verifyUnavailabilityOfCountOnTabHeaderInSubjectCategorySection(
				Constants.SubjectCategoryFilter_Attachments);

		reportLog("4.1: Select Attachment tab in Subject category Filter list section");
		subjectDetailPage.selectFilterFromSubjectCategory(Constants.SubjectCategoryFilter_Attachments);

		reportLog("4.2: Verify control to add new attachments present");
		subjectDetailPage.verifyOnAddAttachmentIconDisplayed();

		reportLog("5.1: Click on the add attachment control");
		subjectDetailPage.clickOnAddAttachmentIcon();

		reportLog("5.2: Verify 'Upload Files' pop up is displayed.");
		subjectDetailPage.verifyUploadFilesPopUpIsDisplayed();

		reportLog("5.3: Verify upload button is displayed in File uploaded popup");
		subjectDetailPage.verifyUploadButtonIsDisplayedInUploadFilesPopUp();

		reportLog("5.4: Verify Add files button of upload files popup");
		subjectDetailPage.verifyAddFilesBtnOnSourceUploadPopup();

		reportLog("5.5: Verify cancel button of upload files popup");
		subjectDetailPage.verifyCancelBtnOnSourceUploadPopup();

		reportLog("6.1: Click on the add attachment control and add " + Constants.PDFFileToUpload + " file");
		 subjectDetailPage.uploadFile(Constants.PDFFileToUpload); 

		reportLog("7.1: Verify 'Upload Files' pop up is closed.");
		subjectDetailPage.verifyUploadFilesPopUpIsNotDisplayed();

		reportLog("7.2 Verify " + Constants.PDFFileToUpload + " Source PDF file is uploaded and saved successfully");
		subjectDetailPage.verifyDocumentIsUploaded(Constants.PDFFileToUpload);

		reportLog("7.3 Verify delete control is available for " + Constants.PDFFileToUpload + " Source PDF file");
		subjectDetailPage.verifyDeleteControlForUploadedFile(Constants.PDFFileToUpload);

		reportLog("7.4: Verify The attachment count displayed on the tab header becomes '1'");
		subjectDetailPage.verifyCountOnTabHeaderInSubjectCategorySection(Constants.SubjectCategoryFilter_Attachments,
				1);

		reportLog("8.1: Click on the add attachment control");
		subjectDetailPage.clickOnAddAttachmentIcon();

		reportLog("8.2: Verify 'Upload Files' pop up is displayed.");
		subjectDetailPage.verifyUploadFilesPopUpIsDisplayed();

		reportLog("8.3: Verify upload button is displayed in File uploaded popup");
		subjectDetailPage.verifyUploadButtonIsDisplayedInUploadFilesPopUp();

		reportLog("8.4: Verify Add files button of upload files popup");
		subjectDetailPage.verifyAddFilesBtnOnSourceUploadPopup();

		reportLog("8.5: Verify cancel button of upload files popup");
		subjectDetailPage.verifyCancelBtnOnSourceUploadPopup();

		reportLog("8.6: Click On cancel button of upload files popup");
		subjectDetailPage.clickOnSourceUploadCancelBtn();

		reportLog("8.7: Verify 'Upload Files' pop up is closed.");
		subjectDetailPage.verifyUploadFilesPopUpIsNotDisplayed();

		reportLog("8.8: Verify The attachment count displayed on the tab header becomes '1'");
		subjectDetailPage.verifyCountOnTabHeaderInSubjectCategorySection(Constants.SubjectCategoryFilter_Attachments,
				1);

		reportLog("9.1 click on uploaded " + Constants.PDFFileToUpload + " Source PDF file ");
		subjectDetailPage.clickOnUploadedFile(Constants.PDFFileToUpload);

		reportLog("9.2: Verify PDF template for form is opened in a new window");
		String ParrentWin = switchToChildWindow();
		subjectDetailPage.verifyFormOpenInPDFtemplate();

		reportLog("10.1: Close the popup window");
		switchParentWindowByClosingChild(ParrentWin);

		reportLog("11.1: Click on delete control for " + Constants.PDFFileToUpload + " Source PDF file");
		subjectDetailPage.clickOnDeleteControlForUploadedFile(Constants.PDFFileToUpload);

		reportLog("11.2: Verify Confirmation Window Displayed");
		subjectDetailPage.verifyConfirmationWindowDisplayedBeforeDeleteFile();

		reportLog("11.2: Verify Confirmation Window Displayed with 'Yes' option");
		subjectDetailPage.verifyYesOnConfirmationWindow();

		reportLog("11.3: Verify Confirmation Window Displayed with 'No' option");
		subjectDetailPage.verifyNoOnConfirmationWindow();

		reportLog("12.1: Click on 'No' option on Confirmation Window");
		subjectDetailPage.clickOnNOConfirmationWindow();

		reportLog("12.2: verify Confirmation Window closed for Delete File");
		subjectDetailPage.verifyConfirmationWindowClosedForDeleteFile();

		reportLog("12.3: the attached file is not deleted.");
		subjectDetailPage.verifyCountOnTabHeaderInSubjectCategorySection(Constants.SubjectCategoryFilter_Attachments,
				1);

		reportLog("13.1: Click on delete control for " + Constants.PDFFileToUpload + " Source PDF file");
		subjectDetailPage.clickOnDeleteControlForUploadedFile(Constants.PDFFileToUpload);

		reportLog("13.2: Verify Confirmation Window Displayed");
		subjectDetailPage.verifyConfirmationWindowDisplayedBeforeDeleteFile();

		reportLog("13.3: Verify Confirmation Window Displayed with 'Yes' option");
		subjectDetailPage.verifyYesOnConfirmationWindow();

		reportLog("13.4: Verify Confirmation Window Displayed with 'No' option");
		subjectDetailPage.verifyNoOnConfirmationWindow();

		reportLog("14.1: Click on 'Yes' option on Confirmation Window");
		subjectDetailPage.clickONYesOnConfirmationWindow();

		reportLog("14.2: verify Confirmation Window closed for Delete File");
		subjectDetailPage.verifyConfirmationWindowClosedForDeleteFile();

		reportLog("14.3: Verify No attachment count appears on the tab header");
		subjectDetailPage.verifyUnavailabilityOfCountOnTabHeaderInSubjectCategorySection(
				Constants.SubjectCategoryFilter_Attachments);

		reportLog("15.1: Click on the add attachment control");
		subjectDetailPage.clickOnAddAttachmentIcon();

		reportLog("15.2: Verify 'Upload Files' pop up is displayed.");
		subjectDetailPage.verifyUploadFilesPopUpIsDisplayed();

		reportLog("15.3: Verify Add files button of upload files popup");
		subjectDetailPage.verifyAddFilesBtnOnSourceUploadPopup();

		reportLog("15.4: Click on the add attachment control and add " + Constants.PDFFileToUpload + " file");
		 subjectDetailPage.uploadFile(Constants.PDFFileToUpload);

		reportLog("15.5: Click on the add attachment control");
		subjectDetailPage.clickOnAddAttachmentIcon();

		reportLog("15.6: Verify 'Upload Files' pop up is displayed.");
		subjectDetailPage.verifyUploadFilesPopUpIsDisplayed();

		reportLog("15.7: Verify Add files button of upload files popup");
		subjectDetailPage.verifyAddFilesBtnOnSourceUploadPopup();

		reportLog("15.8: Click on the add attachment control and add " + Constants.PDFFile2ToUpload + " file");
		 subjectDetailPage.uploadFile(Constants.PDFFile2ToUpload); 

		reportLog("15.9: Verify 'Upload Files' pop up is closed.");
		subjectDetailPage.verifyUploadFilesPopUpIsNotDisplayed();

		reportLog("15.10: Verify " + Constants.PDFFileToUpload + " Source PDF file is uploaded and saved successfully");
		subjectDetailPage.verifyDocumentIsUploaded(Constants.PDFFileToUpload);

		reportLog(
				"15.11: Verify " + Constants.PDFFile2ToUpload + " Source PDF file is uploaded and saved successfully");
		subjectDetailPage.verifyDocumentIsUploaded(Constants.PDFFile2ToUpload);

		reportLog("15.12: Verify The attachment count displayed on the tab header becomes '2'");
		subjectDetailPage.verifyCountOnTabHeaderInSubjectCategorySection(Constants.SubjectCategoryFilter_Attachments,
				2);

		reportLog("16.1: Logout application");
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog("16.2: Login to Site Portal");
		loginPage.sponsorLogin(AT_PRODSponsorUserTypeNew, AT_Password);

		reportLog("16.3: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("16.4: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("16.5: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("16.6: Select subject");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				Subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(Subject);

		reportLog("16.7: Verify Subject details page is opened");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("17.1: Verify Visit Category Filters are displayed.");
		subjectDetailPage.verifyVisitCategoryFiltersDisplayed();

		reportLog("17.2: Select All From Visit Category");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("17.3: Select visit row ");
		subjectDetailPage.clickOnCalendarVisitRow(visit);

		reportLog("17.4: Select Attachment tab in Subject category Filter list section");
		subjectDetailPage.selectFilterFromSubjectCategory(Constants.SubjectCategoryFilter_Attachments);

		reportLog("17.5: Verify " + Constants.PDFFileToUpload + " Source PDF file is uploaded and saved successfully");
		subjectDetailPage.verifyDocumentIsUploaded(Constants.PDFFileToUpload);

		reportLog("17.6: Verify " + Constants.PDFFile2ToUpload + " Source PDF file is uploaded and saved successfully");
		subjectDetailPage.verifyDocumentIsUploaded(Constants.PDFFile2ToUpload);

		reportLog("17.7: Verify control to add new attachments not displayed");
		subjectDetailPage.verifyOnAddAttachmentIconNotDisplayed();

		reportLog("17.8 Verify delete control is not available for " + Constants.PDFFileToUpload + " Source PDF file");
		subjectDetailPage.verifyDeleteControlNotDisplayedForUploadedFile(Constants.PDFFileToUpload);

		reportLog("18.1 click on uploaded " + Constants.PDFFile2ToUpload + " Source PDF file ");
		subjectDetailPage.clickOnUploadedFile(Constants.PDFFile2ToUpload);

		reportLog("18.2: Verify PDF template for form is opened in a new window");
		ParrentWin = switchToChildWindow();
		subjectDetailPage.verifyFormOpenInPDFtemplate();

		reportLog("19.1: Close the popup window");
		switchParentWindowByClosingChild(ParrentWin);

		reportLog("19.2: Logout application");
		loginPage.logoutApplication();

		reportLog("19.3: Verify user is Logout from application");
		loginPage.verifyUserLogout();

	}
}
