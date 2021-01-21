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

public class FPTC_1403_ViewAutomaticallySubmittedAssessment_MAP extends BaseTest {	

	private String subjectAutomaticSubmissionName, visitAllowAutomaticSubmissionFirst,
			visitAllowAutomaticSubmissionSecond, startedDate,submitted="Submitted Date (UTC)";
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)

	public FPTC_1403_ViewAutomaticallySubmittedAssessment_MAP(String browser) {
		super(browser);
		
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		subjectAutomaticSubmissionName = properties.getProperty("CompletedAutomaticSubject");
		visitAllowAutomaticSubmissionFirst = properties.getProperty("Auto_UnAssigned_Visit1");
		visitAllowAutomaticSubmissionSecond = properties.getProperty("NonCRCompletedVisit2193");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1403 Test Case Name: Automatic submission - View automatically submitted assessment 
	 * ====================================================================================================================
	 * @throws Exception 
	 * 
	 * 
	 */

	@Test(description = "FP-TC-1403_View automatically submitted assessment", groups = {})
	public void FPTC_1403_VerifyViewAutomaticallySubmittedAssessment() throws Exception {
		
		/*-----------Login With  MA User who has the claim to allow automatic submission and associated with the Study in Pr#1----------*/

		reportLog("1: Log in to the Portal as User in Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
				
		reportLog("1.1.1: Navigate to assessment details page in Pr#4.");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);
		
		reportLog("1.2: Assessment details page of Pr#4 is visible.");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("1.3: PDF preview image is available on the page.");
		assessmentDetailPage.verifyAssessmentFormSourcePDFCanView();
		
		reportLog("1.4: Version section is available on the page");
		assessmentDetailPage.verifyVersionsSectionDisplayed();
		startedDate = assessmentDetailPage.getStartedDate();
		
//		reportLog("1.5: Notes section is available on the page.");
//		assessmentDetailPage.verifyNotesSectionIsAppeared();
		
		reportLog("2: Check that corresponding information is available in Version section ");
		reportLog("2.1: Such information is available: '- Submission date'");
		assessmentDetailPage.verifyAssessmentDetailsUnderVersionsSection("1", startedDate);
		
		reportLog("2.2: The rater who conducted Assessment.");
		assessmentDetailPage.verifyAssessmentDetailsUnderVersionsSection("1", Constants.ChooseSite_VPTester21);
		
//		reportLog("3: Check that corresponding information is available in Notes section");
//		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.TabletRecord,Constants.Automatic_SubmissionConfimrationRecord,"Submitted" );
		
		reportLog("3.1: Date and time when it was submitted.");
//		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.dateRecord,startedDate.toLowerCase(), submitted);
//		
		reportLog("4: Open PDF file and check that all fields are filled.");
		assessmentDetailPage.clickOnPdfImage();
		
		reportLog("4.1: Rater's name and last name on the Title page ");
		assessmentDetailPage.verifyPdfAssignedRaterIspresent(Constants.RaterName_21);
		
		reportLog("4.2: Assessment data.");	
		reportLog("4.3: Signed by field is NOT filled on the Signature History page");
		assessmentDetailPage.verifySignedByFieldNOTFilledOnSignatureHistoryPage(Constants.RaterName_21);
		
		reportLog("4.4: Certified Copy field contains the corresponding date when the PDF was generated");
		assessmentDetailPage.verifyCertifiedCopyFieldContainsCorrespondingDateWhenPDFWasGenerated(startedDate);
		
		reportLog("4.5: Audit History page contains 'Released By' record");
		assessmentDetailPage.changeViewMode(Constants.AssesmentPage_ViewMode_AllDetails);
		assessmentDetailPage.refreshPage();
		assessmentDetailPage.clickOnPdfImage();
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(Constants.RaterName_21);
		
		reportLog("5: Navigate to Assessments details page in Pr#5");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);
		
		reportLog("5.1: Assessment details page of Pr#4 is visible.");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();
		
		reportLog("5.2: PDF preview image is available on the page.");
		assessmentDetailPage.verifyAssessmentFormSourcePDFCanView();
		
		reportLog("5.3: Version section is available on the page");
		assessmentDetailPage.verifyVersionsSectionDisplayed();
		
//		reportLog("5.4: Notes section is available on the page.");
//		assessmentDetailPage.verifyNotesSectionIsAppeared();
		
		reportLog("6: Check that corresponding information is available in Notes section");
		
		reportLog("6.1: Such information is available: '- Submission date'");
//		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.TabletRecord, Constants.Automatic_SubmissionConfimrationRecord,"submitted");
		
		reportLog("6.2: Date and time when it was submitted.");
//		assessmentDetailPage.verifyUpdatedRecordOnAllowAutomaticSubmissionNotesSection(Constants.dateRecord,startedDate.toLowerCase(),submitted);
		
		reportLog("7: Check that corresponding information is available in Version section");
		assessmentDetailPage.verifyAssessmentDetailsUnderVersionsSection("1", startedDate);
		
		reportLog("7.2: The rater who conducted Assessment.");
		assessmentDetailPage.verifyAssessmentDetailsUnderVersionsSection("1", Constants.ChooseSite_VPTester21);
		
		reportLog("8: Open PDF file and check that all fields are filled.");
		assessmentDetailPage.clickOnPdfImage();
		
		reportLog("8.1: Rater's name and last name on the Title page ");
		assessmentDetailPage.verifyPdfAssignedRaterIspresent(Constants.RaterName_21);
		
		reportLog("8.2: Assessment data.");				
		reportLog("8.3: Signed by field is NOT filled on the Signature History page");
		assessmentDetailPage.verifySignedByFieldNOTFilledOnSignatureHistoryPage(Constants.RaterName_21);
		
		reportLog("8.4: Certified Copy field contains the corresponding date when the PDF was generated");
		assessmentDetailPage.changeViewMode(Constants.AssesmentPage_ViewMode_AllDetails);
		assessmentDetailPage.refreshPage();
		assessmentDetailPage.clickOnPdfImage();
		assessmentDetailPage.verifyCertifiedCopyFieldContainsCorrespondingDateWhenPDFWasGenerated(startedDate);
		
		reportLog("8.5: Audit History page contains 'Released By' record");
		assessmentDetailPage.verifyInformationIsPresentInAuditHistory(Constants.RaterName_21);
		
		reportLog("9: Check, that overrides are added to required items in the PDF");		
		reportLog("9.1: Canned override corresponds to each required field that is not filled.");
		
		loginPage.logoutApplication();
		
		/*-----------Login With Site User who has the claim to release automatic submission and associated with the Study in Pr#1----------*/
		
		reportLog("10: Log in to the Portal as User in Pr#3 and navigate to the Assessment details page in Pr#4");
		loginPage.siteLogin(AT_PRODSiteCoordinator, AT_Password);
		
		reportLog("navigate to the Assessment details page in Pr#4");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);
		
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();		
		
		reportLog("10.1: The Notes section is available for the user in Pr#3 on the Assessment details page.");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("navigate to the Assessment details page in Pr#5");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);
		
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();		
//		assessmentDetailPage.verifyNotesSectionIsAppeared();
				
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
		/*-----------Login With User who can view Assessment details and associated with the Study in Pr#1 without claims related to automatic submission, exists----------*/
		
		reportLog("11: Log in to the Portal as User in Pr#6");
		loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("navigate to the Assessment details page in Pr#4");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);
		
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();		
		
		reportLog("11.1: The Notes section is available for the user in Pr#6 on the Assessment details page.");
//		assessmentDetailPage.verifyNotesSectionIsAppeared();		
		
		reportLog("navigate to the Assessment details page in Pr#5");
		dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
				
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);
		
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();		
//		assessmentDetailPage.verifyNotesSectionIsAppeared();
				
		reportLog("Logout application");
		loginPage.logoutApplication();;

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
