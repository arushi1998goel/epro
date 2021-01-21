package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.AssessmentsDetailsPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1306_NavigateToAssessmentDetailsPageAndListView_SIP extends BaseTest {

	private String studyWithClinroForms, studyWithClinroObsroProForms, visit_Pro;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1306_NavigateToAssessmentDetailsPageAndListView_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyWithClinroForms = properties.getProperty("StudyOnlyForClinroForms");
		studyWithClinroObsroProForms = properties.getProperty("AutomationStudyNameNonCR");
		subjectName = properties.getProperty("SubjectName2095");
		visit_Pro = properties.getProperty("Auto_Visit_ProForms");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1306 || Test Case Name: Navigate to the Assessment
	 * Details page and Assessment list view
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1306 : Navigate to the Assessment Details page and Assessment list view", groups = {
			"" })

	public void FPTC_1306_NavigateToTheAssessmentDetailsPageAndAssessmentListView() throws Exception {

		reportLog("1.1: Log in to the MA Portal");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);



		reportLog("1.3: Verify Option to select a study is available");
		//studyNavigatorDashBoardPage.verifyOptionToSelectStudyButtonIsDisplayed();


		reportLog("1.4: Select study -  + studyWithClinroForms and Verify select button if it is enabled");
		studyNavigatorDashBoardPage.selectStudy(studyWithClinroForms,Constants.ATAssignedRater_10);

		reportLog("1.5: Navigate to Assessments list");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("1.6: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("1.7: Verify Filter option item " + Constants.CategoryFilter_All + "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected("",
				Constants.CategoryFilter_All);

		reportLog("1.8: Verify Filter option item " + Constants.Complete_Status + "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.AssessmentStatus_FilterBy, Constants.Complete_Status);

		reportLog("1.9: Verify Filter option item " + Constants.CategoryFilter_WithOverrides
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.AssessmentStatus_FilterBy, Constants.CategoryFilter_WithOverrides);

		reportLog("1.10: Verify Filter option item " + Constants.CategoryFilter_PaperTranscription
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.AssessmentStatus_FilterBy, Constants.CategoryFilter_PaperTranscription);

		reportLog("1.11: Verify Filter option item " + Constants.CategoryFilter_PaperTranscriptionWithoutSource
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.AssessmentStatus_FilterBy, Constants.CategoryFilter_PaperTranscriptionWithoutSource);

		reportLog("1.12: Verify Filter option item " + Constants.CategoryFilter_NotAdministered
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.AssessmentStatus_FilterBy, Constants.CategoryFilter_NotAdministered);

		reportLog("1.13: Verify Filter option item " + Constants.CategoryFilter_Queries + "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.Review_FilterBy, Constants.CategoryFilter_Queries);

		reportLog("1.14: Verify Filter option item " + Constants.CategoryFilter_UnreadFeedback
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.Review_FilterBy, Constants.CategoryFilter_UnreadFeedback);

		reportLog("1.15: Verify Filter option item " + Constants.CategoryFilter_AnyFeedback
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.Review_FilterBy, Constants.CategoryFilter_AnyFeedback);

		reportLog("1.16: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Type);

		reportLog("2.1: Select study - " + studyWithClinroObsroProForms);
		studyNavigatorDashBoardPage.refreshPage();
		studyNavigatorDashBoardPage.reSelectstudy(studyWithClinroForms);
		studyNavigatorDashBoardPage.selectStudy(studyWithClinroObsroProForms, Constants.ATAssignedRater_10);

		reportLog("2.2: Navigate to Assessments list");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("2.3: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("2.4: Verify Filter option item " + Constants.CategoryFilter_All + "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected("",
				Constants.CategoryFilter_All);

		reportLog("2.5: Verify Filter option item " + Constants.Complete_Status + "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.AssessmentStatus_FilterBy, Constants.Complete_Status);

		reportLog("2.6: Verify Filter option item " + Constants.CategoryFilter_WithOverrides
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.AssessmentStatus_FilterBy, Constants.CategoryFilter_WithOverrides);

		reportLog("2.7: Verify Filter option item " + Constants.CategoryFilter_PaperTranscription
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.AssessmentStatus_FilterBy, Constants.CategoryFilter_PaperTranscription);

		reportLog("2.8: Verify Filter option item " + Constants.CategoryFilter_PaperTranscriptionWithoutSource
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.AssessmentStatus_FilterBy, Constants.CategoryFilter_PaperTranscriptionWithoutSource);

		reportLog("2.9: Verify Filter option item " + Constants.CategoryFilter_NotAdministered
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.AssessmentStatus_FilterBy, Constants.CategoryFilter_NotAdministered);

		reportLog("2.10: Verify Filter option item " + Constants.CategoryFilter_Queries + "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.Review_FilterBy, Constants.CategoryFilter_Queries);

		reportLog("2.11: Verify Filter option item " + Constants.CategoryFilter_UnreadFeedback
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.Review_FilterBy, Constants.CategoryFilter_UnreadFeedback);

		reportLog("2.12: Verify Filter option item " + Constants.CategoryFilter_AnyFeedback
				+ "  is able to be selected ");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterOptionItemIsAbleToBeSelected(
				Constants.Review_FilterBy, Constants.CategoryFilter_AnyFeedback);

		reportLog("2.13: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Type);

		reportLog("3.1: Select Assessment");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectValueUnderDesiredColumn(AssessmentsDetailsPage.class,
				Constants.StudyDashBoard_columnName_Assessment, "");

		reportLog("3.2: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.3: Verify Assessment Details page is opened in " + Constants.AssesmentPage_ViewMode_AllDetails
				+ " mode by default");
		assessmentDetailPage.verifySelectedViewMode(Constants.AssesmentPage_ViewMode_AllDetails);

		reportLog("4.1: Navigate to DashBoard");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("4.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);


		reportLog("4.3: Navigate to Visits Listing");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("4.3: Search and click on visit");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Status,
				Constants.Complete_Status);
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visit_Pro, subjectName);

		reportLog("4.4: Verify Visit Detail page Is Displayed");
		visitDetaiLPage.verifyVisitDetailIsDisplayed();

		reportLog("4.5: Verify Assigned Rater thumbnail Image Is Displayed");
		visitDetaiLPage.verifyNotAdministeredFormThumbnailImageIsPresent();

		reportLog("4.6: Click On Assigned Rater thumbnail Image");
		assessmentDetailPage=visitDetaiLPage.clickOnAfterNotAdministeredThumbnailImage();

		reportLog("4.7: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("4.8: Verify Assessment Details page is opened in " + Constants.AssesmentPage_ViewMode_AllDetails
				+ " mode by default");
		assessmentDetailPage.verifySelectedViewMode(Constants.AssesmentPage_ViewMode_AllDetails);

		reportLog("5.1: Navigate to DashBoard");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);

		reportLog("5.2: Navigate to study navigator");
//		dashBoardPage.navigateToStudyNavigator();
		dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		

		reportLog("5.3: Navigate to Subjects Listing");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("5.4: Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("5.6: Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("5.7: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("5.8: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visit_Pro);

		reportLog("5.9: Verify thumbnail Image");
		subjectDetailPage.verifyAfterSubmissionNotAdministeredThumbnailImage();

		reportLog("5.10: select the form thumbnail");
		assessmentDetailPage = subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();

		reportLog("5.11: Assessment details page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("5.12: Verify Assessment Details page is opened in " + Constants.AssesmentPage_ViewMode_AllDetails
				+ " mode by default");
		assessmentDetailPage.verifySelectedViewMode(Constants.AssesmentPage_ViewMode_AllDetails);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is Logout from application");
		loginPage.verifyUserLogout();

	}
}