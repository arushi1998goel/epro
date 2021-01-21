package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_2695AccessCRSourceClaim_SIP extends BaseTest {

	private String visitName,subjectName="AUTO1551"+generateRandomString(5);


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2695AccessCRSourceClaim_SIP(String browser) {
		super(browser);
	}
	
	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyForAssessmentListing = prop.getProperty("Test741Study");
	    visitName=prop.getProperty("VisitAccessSource");

		
		/* Creating Subject For Configuring Pre-requisite */
	    reportLog("Setting up PRs");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyForAssessmentListing, Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_12);
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Constants.ATAssignedRater_12);
		assessmentDetailPage=subjectDetailPage.clickOnBeforeNotAdministeredThumbnailImage();
		assessmentDetailPage.markAsNotAdministred(SuperAdminUN,SuperAdminPW);
		loginPage.logoutApplication();
		/* Subject Created Successfully */
		
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id : FP-TC-2695 Test Case Name : Access CR Source Claim
	 * 
	 * ====================================================================================================================
	 */



	@Test(description = "FPTC2695_AccessCRSourceClaim_SIP", groups = { "R1" })
	public void FPTC_2695verifyUserAccessCRSourceClaimsFlow() {


		reportLog("1: Login in to application  "+AT_PRODInvestigator+" ");
		dashBoardPage = loginPage.loginInApplication(AT_PRODInvestigator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("3: Select " + studyName + " study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyForAssessmentListing,Constants.ATAssignedRater_10);
		
		reportLog("4: Select Assesment from Assesment Navigator");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		
		reportLog("5: Select Assesment from theassessment list");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_CGI_S, visitName, subjectName);

		reportLog("6: Verify assessment detail page");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("7: Verify sighting of assessment form source PDF");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("8: Logout from the Application");
		loginPage.logoutApplication();

		reportLog("9: Verify User Logout from the system");
		loginPage.verifyUserLogout();	

		reportLog("1: Login in to The  application with  "+ AT_PRODSiteCoordinator+ " ");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("3: Select " + studyForAssessmentListing + " study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyForAssessmentListing,Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		
		reportLog("4: Select Assesment from Assesment Navigator");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("5: Select Assesment from the loaded assessment list");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_CGI_S, visitName, subjectName);
		
		reportLog("6: Verify assessment detail page");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("7: Verify sighting of assessment form source PDF");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("8: Logout from the Application");
		loginPage.logoutApplication();

		reportLog("9: Verify User Logout from the system");
		loginPage.verifyUserLogout();

		reportLog("1: Login in to application with  "+ AT_PRODAdminViewOnly+ " ");
		dashBoardPage = loginPage.loginInApplication(AT_PRODAdminViewOnly, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("3: Select " + studyForAssessmentListing + " study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyForAssessmentListing,Constants.ATAssignedRater_10);

		reportLog("4: Select Assesment from Assesment Navigator");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();

		reportLog("5: Select Assesment from the loaded assessment list");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByAssesmentNameAndVisitAndSubjectName(Constants.Assessment_CGI_S, visitName, subjectName);

		reportLog("6: Verify assessment detail page");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("7: Verify Form source PDF can not be viewed");
		assessmentDetailPage.verifyFormPdfCantView();

		reportLog("8: Logout from the Application");
		loginPage.logoutApplication();

		reportLog("9: Verify User Logout from the system");
		loginPage.verifyUserLogout();
	}
}
