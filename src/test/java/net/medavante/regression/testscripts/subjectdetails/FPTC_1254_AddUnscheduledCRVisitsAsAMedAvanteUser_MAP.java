package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.CentralRatingModuleConstants;
import net.medavante.portal.utilities.Constants;

public class FPTC_1254_AddUnscheduledCRVisitsAsAMedAvanteUser_MAP extends BaseTest {

	private String visitNameUnScheduledCompleted, visitNameUnScheduledAdded,
			subjectName = "subject1795"+generateRandomAlphanumericString(5),visitNormal;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1254_AddUnscheduledCRVisitsAsAMedAvanteUser_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyCRStatus");
		visitNameUnScheduledCompleted = properties.getProperty("CRUnscheduledCompleteVisit");
		visitNameUnScheduledAdded = properties.getProperty("visitUnScheduledAdded1795");
		visitNormal = properties.getProperty("visitNormalFor1795");
		
		/*Creating Subject For Configuring Pre-requisite */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,AT_PRODSiteCoordinatorUserName,subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnVisitRow(visitNormal);
		subjectDetailPage.clickOnAddVisitIcon();
		loginPage.logoutApplication();
		/*Subject Created Successfully*/
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1254 Test Case Name:Add Unscheduled CR visits as a
	 * MedAvante User
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1254__Add unscheduled CR visits as a MedAvante User", groups = { "" })
	public void FPTC_1254_AddUnscheduledCRVisitsAsAMedAvanteUser() {

		reportLog("1.1:	Login to portal application as the Medavante User");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSystemAdministrator, AT_Password);

		reportLog("2.1:Navigate to the Subject details page of Pr#4");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.2: Select study and site on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);

		reportLog("2.3: Select the subject");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.4:Verify Subject Details Displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2.5:Option to add Unscheduled visits is available");
		subjectDetailPage.verifyUnscheduledAddVisitBTNIsDisplayed();

		reportLog("3.1:Select the option to add the unscheduled visit");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("3.2:The list of unscheduled visits are visible");
		subjectDetailPage.verifyUnscheduledVisitListDisplayed();

		reportLog("3.3:The list includes the visits of Pr#3 and Pr#5");
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(visitNameUnScheduledAdded);
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(visitNameUnScheduledCompleted);

		reportLog("4.1:Select the visit of Pr#3");
		centralRatingAppointmentPage=subjectDetailPage.selectCRVisitFromUnscheduledVisitList(visitNameUnScheduledAdded);
		
		reportLog("4.2:	The CR Appointment details page is visible");
		centralRatingAppointmentPage.verifyAppointmentDetailsDisplayed();

		reportLog("5.1:Complete The Cr Appointment Set appointment date and time");
		centralRatingAppointmentPage.setAppointmentDate();
		centralRatingAppointmentPage.setStartedTime("11", "00", "AM");

		reportLog("5.2 :Pick Clinician");
		centralRatingAppointmentPage.clickOnPickClinician();

		reportLog("5.3:Select clinician time slot");
		centralRatingAppointmentPage.clickOnRowByColumnAndRowValue("Clinician",
				CentralRatingModuleConstants.clinician15Name, "11:00am");

		reportLog("5.4:Click to schedule appointment");
		centralRatingAppointmentPage.clickOnScheduleAppointment();
		centralRatingAppointmentPage.confirmationOfPopUpMessage();

		reportLog("5.5:	Scheduling is completed");
		centralRatingAppointmentPage.verifyVisitStatus(Constants.scheduled_Status);

		reportLog("6.1:Select the option to go back to the Subject details page");
		subjectDetailPage = centralRatingAppointmentPage.navigateToSubjectDetailsPage();

		reportLog("6.2:Subject details page is visible");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("6.3:The unscheduled visit of Step#4 is added to the visit list with the Scheduled status");
		subjectDetailPage.verifyVisitStatus(visitNameUnScheduledAdded, Constants.scheduled_Status);

		reportLog("7.1:Select the option to add the unscheduled visit");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("7.2:The list of unscheduled visits are visible");
		subjectDetailPage.verifyUnscheduledVisitListDisplayed();

		reportLog("7.3:The list doesn't include the visits of Step#4");
		subjectDetailPage.verifyVisitNotContainsInUnscheduledVisitList(visitNameUnScheduledAdded);

		reportLog("7.4: Repeating the Repeat Step#3 to Step#7 for Pr#5 Select the visit of Pr#5");
		centralRatingAppointmentPage=subjectDetailPage.selectCRVisitFromUnscheduledVisitList(visitNameUnScheduledAdded);
		
		reportLog("7.2:	The CR Appointment details page is visible");
		centralRatingAppointmentPage.verifyAppointmentDetailsDisplayed();

		reportLog("7.3: Logout application");
		loginPage.logoutApplication();

		reportLog("7.4: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

	}

}