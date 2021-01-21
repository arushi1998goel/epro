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

public class FPTC_2716_NotLastUnscheduledVisitDiscontinuedStatusCompletion_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2716_NotLastUnscheduledVisitDiscontinuedStatusCompletion_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("tabletFormSettingStudy");
		visitScreenedName = properties.getProperty("Auto_ScreenedStatusVisit");
		visitEnrolled = properties.getProperty("Auto_EnrolledStatusVisit");

		visitCompleted = properties.getProperty("Auto_CompletedStatusVisit");
		visitDiscontinued = properties.getProperty("Auto_DiscontinuesStatusVisit");

		reportLog("Create Subject for setting up the Pre-Requisite");

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:Homepage is displayed");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("Create subject");
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("Completing Screened Visit");
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitScreenedName, SuperAdminUN, SuperAdminPW);

		reportLog("Completing Enrolled Visit");
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitEnrolled, SuperAdminUN, SuperAdminPW);

		reportLog("Completing unscheduled Visit");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();
		subjectDetailPage.selectUnscheduledVisit(visitDiscontinued);
		subjectDetailPage.clickOnMarkAsNotCompletedLink();
		subjectDetailPage.verifyReasonForChangeOptionPopUpIsDisplayed();
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);
		subjectDetailPage.eSignReasonForChangeAndSubmit(SuperAdminUN, SuperAdminPW);
		subjectDetailPage.waitForProcessingVisitToBeCompleted();

		reportLog("Completing  Completed Visit");
		subjectDetailPage.completingVisitByMarkAsNotCompleting(visitCompleted, SuperAdminUN, SuperAdminPW);

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-2716 Test Case Name:Site portal: Subject status
	 * should be based on Visit order. Not last Unscheduled visit with
	 * Discontinued status completion.
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-2716_SubjectStatusShouldbeBasedOnVisitOrder")

	public void FPTC_2716_verifySubjectStatusShouldbeBasedOnVisitOrder() {

		reportLog("2.2: Subject Details page is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2.3: Subject Status is Discontinued");
		subjectDetailPage.verifySubjectStatusDisplayedAsReadOnlyLabel(Constants.Status_Discontinued);

		reportLog("3.1: Logout from the application");
		loginPage.logoutApplication();

		reportLog("3.2: Verify User is logout");
		loginPage.verifyUserLogout();

	}

}
