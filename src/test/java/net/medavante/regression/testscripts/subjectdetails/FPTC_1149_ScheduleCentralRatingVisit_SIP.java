package net.medavante.regression.testscripts.subjectdetails;

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

public class FPTC_1149_ScheduleCentralRatingVisit_SIP extends BaseTest {

	private String studyName, visitName;
	private String subjectName = "Subj_" + generateRandomString(3);
	private String timeSlot1 = "8:00 AM";
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1149_ScheduleCentralRatingVisit_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("StudyCRStatus");
		visitName = prop.getProperty("Auto_CR_Visit1");

		reportLog("Creating a subject as per Prerequisite");
		MedAvantePortalPage dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication(); 
		loginPage.verifyUserLogout();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1149 Test Case Name: Schedule Independent Rating visit 
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1149: Schedule Independent Rating visit ", groups = { "" })

	public void FPTC_1149_verifyUserCanScheduleNewCentralRatingVisitForSubject() {

		reportLog("1.Navigate to Study Dashboard.");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("1.2: Select subject");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("1.3: Verify subject detail page display");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("1.4: Select visit with at least 1 form marked as CR ");
		subjectDetailPage.clickOnVisitRow(visitName);
		subjectDetailPage.verifyAddVisitIconIsDisplayed();
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("2.1:Scheduled CR visit by selecting time slot");
		subjectDetailPage.selectAppointmentDateAndTime(timeSlot1);

		reportLog("2.4: Close Schedule Appointment control by clicking X button at the top right side.");
		subjectDetailPage.closeAppointmentPopup();

		reportLog("2.5: Verify Reschedule & Cancel controls are displayed for the visit");
		subjectDetailPage.verifyRescheduleAndCancelBtnIsDisplayedForSelectedVisit(visitName);

		reportLog("2.6: Logout from the application");
		loginPage.logoutApplication();

		reportLog("2.7: Verify User is logout");
		loginPage.verifyUserLogout();

	}
}
