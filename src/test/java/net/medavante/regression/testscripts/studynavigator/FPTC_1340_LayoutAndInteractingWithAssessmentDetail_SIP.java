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

public class FPTC_1340_LayoutAndInteractingWithAssessmentDetail_SIP extends BaseTest {

	private String SubjectName2198, visit;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1340_LayoutAndInteractingWithAssessmentDetail_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		SubjectName2198 = properties.getProperty("Subject2198");
		visit = properties.getProperty("AutoRiskFirst");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1340 || Test Case Name: Layout and interacting with
	 * Assessment Detail
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1340_Layout and interacting with Assessment Detail ", groups = {})
	public void FPTC_1340_LayoutAndInteractingWithAssessmentDetail() {

		reportLog("1.1: Log in to the Portal as User in Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("2.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("2.3: Navigate to Assessments Listing");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("2.4: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("3.1: Search and click on assessment");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog14List1FormName, visit, SubjectName2198);

		reportLog("3.2: Verify assessment Details Page is opened");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("3.3: Verify Refresh Button Displayed in assessment Details Page");
		assessmentDetailPage.verifyRefreshBtnDisplayed();

		reportLog("3.4: Option to changes assessment is present In the Action drop-down");
		assessmentDetailPage.verifyOptionsIsPresentInActionDropDown(Constants.Assesment_ActionDropDown_ChangeAssessment);

		reportLog("3.5: Verify Assessment Details Section displayed");
		assessmentDetailPage.verifyAssessmentDetailsSectionDisplayed();

		reportLog("3.6: Verify Assessment PDF cover is available on the page");
		assessmentDetailPage.verifyAssessmentFormSourcePDFCanView();

		reportLog("3.7: Verify Version section is available on the page");
		assessmentDetailPage.verifyVersionsSectionDisplayed();

		reportLog("3.8 Verify Scores and Attachments tabs are displayed");
		//assessmentDetailPage.verifyScoreAndAttachmentsTabIsDisplayed();

		reportLog("3.9: Logout from application");
		loginPage.logoutApplication();

		reportLog("3.10: Verify User Logout from application");
		loginPage.verifyUserLogout();

	}
}
