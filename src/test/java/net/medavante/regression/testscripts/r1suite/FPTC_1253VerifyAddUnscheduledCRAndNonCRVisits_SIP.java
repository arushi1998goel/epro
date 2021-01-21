package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;
import org.testng.annotations.*;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1253VerifyAddUnscheduledCRAndNonCRVisits_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1253VerifyAddUnscheduledCRAndNonCRVisits_SIP(String browser) {
		super(browser);
	}

	private String  studyName,visitName, pendingUnscheduledCRVisit, pendingUnscheduledNonCRVisit,
			completedUnscheduledCRVisit, completedUnscheduledNonCRVisit;

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("Auto_NonCr_Visit1");
		pendingUnscheduledCRVisit = properties.getProperty("pendingUnscheduledCRVisit");
		pendingUnscheduledNonCRVisit = properties.getProperty("pendingUnscheduledNonCRVisit");
		completedUnscheduledCRVisit = properties.getProperty("completedUnscheduledCRVisit");
		completedUnscheduledNonCRVisit = properties.getProperty("completedUnscheduledNonCRVisit");

		reportLog(
				"1.1  Login to the portal as the user exists having claim canManageVisits and canManageSelfServeCRAppointments and not having claim canManageCRAppointments i.e. with "
						+ AT_PRODSiteCoordinatorCRSelfSchedule);
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("1.2: Verify User Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);

		reportLog("1.5: Select an action to add Subject by selecting the site"); 
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();
		
		reportLog("2.1: Enter "+ screeningNum +" Screening# number");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);

		reportLog("2.2: Select language from the configured language list");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("2.3: select an action to save and verify subject detail page is display with "+ screeningNum+" screening num");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
		
		reportLog("2.4: Click on visit row and click on add visit icon");
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.clickOnAddVisitIcon(); 

		reportLog("2.5: Click on Unscheduled Add Visit Button to configure one 1 unscheduled non-CR visit is in Complete "
				+ "state to configure test case pre requiste");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("2.6: Select the " + completedUnscheduledNonCRVisit
				+ " to configure one 1 unscheduled non-CR visit is in Complete state to configure test case pre requiste");
		subjectDetailPage.selectUnscheduledVisit(completedUnscheduledNonCRVisit);

		reportLog("2.7: click on " + completedUnscheduledNonCRVisit
				+ " visit row to configure one 1 unscheduled non-CR visit is in Complete state to configure test case pre requiste");
		subjectDetailPage.clickOnVisitRow(completedUnscheduledNonCRVisit);
		
		reportLog("2.8: click on mark as not completed to configure one 1 unscheduled non-CR visit is in Complete state to configure test case pre requiste");
		subjectDetailPage.clickOnMarkAsNotCompletedLink();

		reportLog("2.9: Select " + Constants.TechincalDifficulties_Reason_for_Change + " reason for change to configure one 1 unscheduled non-CR visit is in Complete state to configure test case pre requiste");
		subjectDetailPage.selectReasonForChangeOption(Constants.TechincalDifficulties_Reason_for_Change);

		reportLog("2.10: E-sign to select reason for change to configure one 1 unscheduled non-CR visit is in Complete state"
				+ " to configure test case pre requiste");
		subjectDetailPage.setCurrentDate();
		subjectDetailPage.eSignForPaperTranscription(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		
		reportLog(
				"2.11: Refresh the page until scale processing get completed to configure one 1 unscheduled non-CR visit is in Complete state to configure test case pre requiste");
		subjectDetailPage.waitForProcessingVisitToBeCompleted();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1253
	 * Test Case Name: Add unscheduled CR and non-CR
	 * visits
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FPTC_1253 --Add unscheduled CR and non-CR visits", groups = { "R1" })
	public void FPTC_1253_verifyAddUnscheduledCRAndNonCRVisits() throws Exception {

		reportLog("2.12: Verify Option to add Unscheduled visits is available");
		subjectDetailPage.verifyUnscheduledAddVisitBTNIsDisplayed();

		reportLog("3.1: Select the option to add the unscheduled visit");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("3.2: verify Unscheduled Visit List is Displayed");
		subjectDetailPage.verifyUnscheduledVisitListDisplayed();

		reportLog("3.3: verify Unscheduled Visit List contains " + pendingUnscheduledCRVisit);
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(pendingUnscheduledCRVisit);

		reportLog("3.4: verify Unscheduled Visit List contains " + pendingUnscheduledNonCRVisit);
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(pendingUnscheduledNonCRVisit);

		reportLog("3.5: verify Unscheduled Visit List contains " + completedUnscheduledCRVisit);
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(completedUnscheduledCRVisit);

		reportLog("3.6: verify Unscheduled Visit List contains " + completedUnscheduledNonCRVisit);
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(completedUnscheduledNonCRVisit);

		reportLog("4.1: Select the " + pendingUnscheduledCRVisit);
		subjectDetailPage.selectUnscheduledVisit(pendingUnscheduledCRVisit);

		reportLog("4.2: Verify The Self Server CR Calendar control appears");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();
		
		reportLog("Step 5.1: Select visit appointment date and time, Click on Search, Select a time slot and Schedule an appointment and confirm."); 
		String selectedTime=subjectDetailPage.selectAppointmentDateAndTime(clinicianTimeToBeSelect);
		
		reportLog("5.2: Verify A confirmation message is displayed on the modal:"); 
		subjectDetailPage.verifyCRAppointmentScheduledMessageIsDisplayed("Thank you!" + "\n"+"Your appointment has been scheduled for " ,selectedTime);

		reportLog("5.3: Close Schedule Appointment control by clicking X button at the top right side.");
		subjectDetailPage.closeAppointmentPopup();

		reportLog("5.4: Verify Subject details page is visible");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("5.5: Verify The unscheduled visit of " + pendingUnscheduledCRVisit
				+ " is added to the Visits list with the Scheduled status");
		subjectDetailPage.verifyVisitStatus(pendingUnscheduledCRVisit, Constants.scheduled_Status);

		reportLog("6.1: Select the option to add the unscheduled visit");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("6.2: Verify The list of unscheduled visits are visible");
		subjectDetailPage.verifyUnscheduledVisitListDisplayed();

		reportLog("6.3: Verify The list of unscheduled visits not contain " + pendingUnscheduledCRVisit + " visit");
		subjectDetailPage.verifyVisitNotContainsInUnscheduledVisitList(pendingUnscheduledCRVisit);

		reportLog("7.1: Select the " + pendingUnscheduledNonCRVisit);
		subjectDetailPage.selectUnscheduledVisit(pendingUnscheduledNonCRVisit);

		reportLog("7.2: Verify The unscheduled visit " + pendingUnscheduledNonCRVisit
				+ " is added to the Visits list with the " + Constants.Pending_Status + " status");
		subjectDetailPage.verifyVisitStatus(pendingUnscheduledNonCRVisit, Constants.Pending_Status);

		reportLog("8.1: Select the option to add the unscheduled visit");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("8.2: Verify The list of unscheduled visits are visible");
		subjectDetailPage.verifyUnscheduledVisitListDisplayed();

		reportLog("8.3: Verify The list of unscheduled visits not contain " + pendingUnscheduledNonCRVisit);
		subjectDetailPage.verifyVisitNotContainsInUnscheduledVisitList(pendingUnscheduledNonCRVisit);

		reportLog("9.1: Select the " + completedUnscheduledNonCRVisit);
		subjectDetailPage.selectUnscheduledVisit(completedUnscheduledNonCRVisit);

		reportLog("9.2: Verify " + completedUnscheduledNonCRVisit + " visit is added to the Visits list with the "
				+ Constants.Pending_Status + " status");
		subjectDetailPage.verifyVisitStatus(completedUnscheduledNonCRVisit, Constants.Pending_Status);

		reportLog("10.1: Logout from application");
		loginPage.logoutApplication();

		reportLog("10.2: Verify User Logout from application");
		loginPage.verifyUserLogout();
	}

}
