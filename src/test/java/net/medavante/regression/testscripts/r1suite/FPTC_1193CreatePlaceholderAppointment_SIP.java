package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_1193CreatePlaceholderAppointment_SIP extends BaseTest {

	protected String subjectName = "Subject1383"+generateRandomString(5), hourForAppointment = "01",
			minuteForAppointment = "30", timeMarker = "PM", time = hourForAppointment + ":" + minuteForAppointment;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1193CreatePlaceholderAppointment_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("studyWithCentralRating");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN,SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1193 Test Case Name:Create Placeholder appointment
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1193_Create Placeholder appointment ", groups = { "R1" })

	public void FPTC_1193_verifyCreatePlaceholderAppointment() {

		reportLog("1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.1:Click on study navigator tile");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("1.2:Click On Find Subject Icon");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("1.3:Find Subject");
		subjectDetailPage = centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(studyName,
				Constants.ATAssignedRater_10, subjectName);

		reportLog("1.4: Verify Configure Visit Is Displayed");
		subjectDetailPage.verifyVisitIsPresentInList();

		reportLog("2:Click On Scheduled Visit");
		centralRatingAppointmentPage = subjectDetailPage.clickOnVisitForScheduling();

		reportLog("2.1:Verify Study site and Visit Details");
		centralRatingAppointmentPage.verifyVisitInformation(studyName, Constants.ATAssignedRater_10);

		reportLog("3:Select Notification Date And Time");
		centralRatingAppointmentPage.setNotificationDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		reportLog("3.1:Select Appointment Date And Time");
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime(hourForAppointment, minuteForAppointment, timeMarker);

		reportLog("4:Save Option Is Available");
		centralRatingAppointmentPage.saveOptionEnabled();

		reportLog("4.1:Click On Pick Clinician");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("4.2:Select Clinician Time Slot");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician14Name, "01:30pm");

		reportLog("5:Save Option Is Disabled");
		centralRatingAppointmentPage.saveOptiondisabled();

		reportLog("5.1:Deselect Clinician");
		centralRatingAppointmentPage.deselectClinician();

		reportLog("5.2:Verify Save Option Available");
		centralRatingAppointmentPage.saveOptionEnabled();

		reportLog("6:Click On Save Option");
		centralRatingAppointmentPage.clickOnSave();

		reportLog("6.1:Apponitment Status Is Requested");
		centralRatingAppointmentPage.verifyVisitStatus(Constants.requested_Status);

		reportLog("6.2:Verify Study site and Visit Details");
		centralRatingAppointmentPage.verifyVisitInformation(studyName, Constants.ATAssignedRater_10);

		reportLog("6.3:Logout application");
		loginPage.logoutApplication();

		reportLog("6.4:Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
