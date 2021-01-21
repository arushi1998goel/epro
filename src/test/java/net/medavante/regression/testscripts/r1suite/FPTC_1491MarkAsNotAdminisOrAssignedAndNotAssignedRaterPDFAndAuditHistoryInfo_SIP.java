package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1491MarkAsNotAdminisOrAssignedAndNotAssignedRaterPDFAndAuditHistoryInfo_SIP
		extends BaseTest {
	protected String studyName, userNameWithClaimToCreateSubject, passwordWithClaim, visitName1,subjectName = generateRandomString(5);
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1491MarkAsNotAdminisOrAssignedAndNotAssignedRaterPDFAndAuditHistoryInfo_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("newStudyName");
		visitName1 = prop.getProperty("Auto_ClinRo");
		
	    /*------Creating Subject and assigning rater for Pre-Requisite-------------*/
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitName1);
		subjectDetailPage.clickOnAddVisitIcon();
		subjectDetailPage.selectRaterFromDropDown(Constants.ATAssignedRater_11);
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		loginPage.logoutApplication();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id:  FPTC_1491 Test Case Name:Mark As NotAdministered For
-	 * Assigned And NotAssigned Rater PDF And AuditHistory Information
	 * ====================================================================================================================
	 * 
	 * 
	 * 
	 */

	@Test(description = " FPTC_1491_ Verify MarkAsNotAdministeredForAssignedAndNotAssignedRaterPDFAndAuditHistoryInformation", groups = {
			"R1" })
	public void  FPTC_1491_verifyMarkAsNotAdministeredForAssignedAndNotAssignedRaterPDFAndAuditHistoryInfo() throws InterruptedException {

		reportLog("1:Login to The Application from pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.1: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.4 Navigate To Assesment Listing Page");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();

		reportLog("1.4 Navigate To Assesment Details Page");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(visitName1, subjectName);

		reportLog("1.5 Verify Assesment Detail Page Displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("2 Set CheckBox As Not Administered");
		assessmentDetailPage.clickOnCheckBoxInAssesmentDetailPage();

		reportLog("3 Verify CheckBox Is Set");
		assessmentDetailPage.verifyMarkAsNotAdministeredCheckBoxIsSet();

		reportLog("3.1 Confirm Button Appears");
		assessmentDetailPage.conifrmButtonAppears();

		reportLog("3.2:Click On Confirm Button");
		assessmentDetailPage.clickOnConfirmButton();

		reportLog("3.3 Confirmation Window Appears");
		assessmentDetailPage.verifyNotAdministeredPopUpPresent();

		reportLog("4:Press Yes Control From Confirmation");
		assessmentDetailPage.clickOnYesButtonOfConfirmationWindow();

		reportLog("4.1:Reason for Change window is displayed");
		assessmentDetailPage.verifyReasonForChangePopUpDisplayed();

		reportLog("5:Select 'Reason for Change', enter username and password and press OK control");
		assessmentDetailPage.setStartedDate();
		assessmentDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		assessmentDetailPage.eSignForReasonForChange(SuperAdminUN, SuperAdminPW);

		reportLog(
				"5.1: Verify Assessment receives 'Complete' status  Assessment form thumbnail changed accordingly to 'Not administered' option");
		assessmentDetailPage.clickOnRefreshIconUntillElementNotVisibleAndVerifyStatus(Constants.Complete_Status);

		reportLog("5.2  Verify CheckBox Span Text Changed As Not Administered");
		assessmentDetailPage.verifyAssesmentCheckBoxChangedAccordinglyNotAdministered();

		reportLog("6:Open PDF and Check The Data");
		assessmentDetailPage.clickOnPdfImage();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}

}
