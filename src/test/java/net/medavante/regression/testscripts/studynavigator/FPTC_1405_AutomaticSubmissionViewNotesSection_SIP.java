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

public class FPTC_1405_AutomaticSubmissionViewNotesSection_SIP extends BaseTest {
	private String subjectAutomaticSubmissionName, visitAllowAutomaticSubmissionFirst;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1405_AutomaticSubmissionViewNotesSection_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		subjectAutomaticSubmissionName = properties.getProperty("SubjectAutomaticSubmission");
		visitAllowAutomaticSubmissionFirst = properties.getProperty("Auto_UnAssigned_Visit1");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1405 Test Case Name: Automatic submission - View Notes Section 
	 * 
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */
	@Test(description = "FP-TC-1405_Verify AutomaticSubmission View Notes Section ", groups = {})
	public void FPTC_1405_VerifyAutomaticSubmissionViewNotesSection() throws Exception {

		/*-----------Login With Site person who can view Assessment Details page ----------*/

		reportLog("1.1: Log in to the Site Portal as User in Pr#3");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Select the Study in Pr#1 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);

		reportLog("2.2:Navigate to Assessment Details page from Pr#2 ");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);

		reportLog("2.3:	Notes section is displayed on Assessment Details page");
		assessmentDetailPage.verifyNotesSectionIsAppeared();

		reportLog("Logout application");
		loginPage.logoutApplication();

		/*-----------Login With MA person who can view Assessment Details page ----------*/

		reportLog("3.1:Log in to the MA Portal as User in Pr#4");
		dashBoardPage = loginPage.maLogin(AT_PRODProjectManager, AT_Password);

		reportLog("3.2:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("4.1:Select the Study in Pr#1 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);

		reportLog("4.2:Navigate to Assessment Details page from Pr#2 ");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);

		reportLog("4.3:	Notes section is displayed on Assessment Details page");
		assessmentDetailPage.verifyNotesSectionIsAppeared();

		reportLog("Logout application");
		loginPage.logoutApplication();

		/*-----------Login With Sponsor person  who can view Assessment Details page ----------*/

		reportLog("5.1:Log in to the sponser Portal as User in Pr#5");
		dashBoardPage = loginPage.sponsorLogin(AT_PRODSponsorUserType3, AT_Password);

		reportLog("5.2:Login is successful.");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("6.1:Select the Study in Pr#1 ");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);

		reportLog("6.2:Navigate to Assessment Details page from Pr#2 ");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(
				Constants.Assesment_AdasCog13List1FormName, visitAllowAutomaticSubmissionFirst,
				subjectAutomaticSubmissionName);

		reportLog("6.3:	Notes section is displayed on Assessment Details page");
		assessmentDetailPage.verifyNotesSectionIsAppeared();

		reportLog("Logout application");
		loginPage.logoutApplication();

	}
}
