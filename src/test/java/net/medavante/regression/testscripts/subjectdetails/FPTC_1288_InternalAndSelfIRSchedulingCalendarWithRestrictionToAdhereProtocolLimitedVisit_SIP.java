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

public class FPTC_1288_InternalAndSelfIRSchedulingCalendarWithRestrictionToAdhereProtocolLimitedVisit_SIP
		extends BaseTest {

	private String slectionDate, selectionAfterDate;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1288_InternalAndSelfIRSchedulingCalendarWithRestrictionToAdhereProtocolLimitedVisit_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyInitatingProtocolAdherenceLimited");
		visitName1 = properties.getProperty("IR_Completed_2666");
		visitName2 = properties.getProperty("IRthird");
		subjectName = properties.getProperty("Subj_2666");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1288 Test Case Name:Internal and Self IR scheduling
	 * calendar with restriction to adhere protocol - Limited Visit
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1288_calendar with restriction to adhere protocol -  Limited Visit ", groups = {
			"" })
	public void FPTC_1288_verifyInternalAndSelfIRSchedulingCalendarWithRestrictionToAdhereProtocolLimitedVisit() {

		reportLog("1.1:Log in to the Portal as a user of PR#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2:User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2:Open Study Navigator-> Select a Study and a Site from PR#1");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);
		

		reportLog("2.1: Navigate to the Subject details page -> Visits -> All from PR#4");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("2.2:Visit Detail page from PR#4 is displayed with projection dates in the Visits list");
		subjectDetailPage.verifyCalenderVisitPresentWithProjectionDates(visitName1);

		reportLog("3.1:	Select an action to Initiate a Visit from PR#5");
		subjectDetailPage.clickOnCalendarVisitRow(visitName2);
		centralRatingAppointmentPage = subjectDetailPage.clickOnInitiateVisitIconForIRVisit();

		reportLog("3.2:IR Scheduling page displayed");
		centralRatingAppointmentPage.verifyAppointmentPage();

		reportLog("4.1:Select to open Scheduled Date calendar");
		centralRatingAppointmentPage.selectToOpenScheduleDateCalender();

		reportLog("4.2:The dates of the visit projection window are indicated");
		centralRatingAppointmentPage.verifyScheduledCalenderDateofVisitProjectionWindowDisplayed();

		reportLog("5.1:Select any date on the projection start date");
		centralRatingAppointmentPage.selectAnyDateOnProjectWindow();

		reportLog("5.2: Selected date displayed in the Scheduled Date field");
		String dateSelectedToday = currentDate();
		centralRatingAppointmentPage.verifySelectedDateDisplayedOnScheduledCalenderField(dateSelectedToday);

		reportLog("5.3:Time table displayed automatically");
		centralRatingAppointmentPage.verifyTimeTableDisplayedAutomatically();

		reportLog("6.1:	Select any date after the projection start date");
		centralRatingAppointmentPage.selectToOpenScheduleDateCalender();
		centralRatingAppointmentPage.selectFurtureDateFromCalender();

		reportLog("6.2:Selected date displayed in the Scheduled Date field");
		String dateAfterSelection = getFutureDate();
		centralRatingAppointmentPage.verifySelectedDateDisplayedOnScheduledCalenderField(dateAfterSelection);

		reportLog("6.3:Time table displayed automatically");
		centralRatingAppointmentPage.verifyTimeTableDisplayedAutomatically();

		reportLog("7.1:	Select any date prior to the earliest date from selection");
		centralRatingAppointmentPage.selectToOpenScheduleDateCalender();
		centralRatingAppointmentPage.selectAnyDateOnProjectWindow();

		reportLog("7.2:Selected date displayed in the Scheduled Date field");
		centralRatingAppointmentPage.verifySelectedDateDisplayedOnScheduledCalenderField(dateSelectedToday);

		reportLog("7.3:Time table displayed automatically");
		centralRatingAppointmentPage.verifyTimeTableDisplayedAutomatically();

		reportLog("8.1:Logout from the application");
		loginPage.logoutApplication();

		reportLog("8.2:Verify user is logout");
		loginPage.verifyUserLogout();

		reportLog("9.1:Log in to the Portal as a user of PR#3 ");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("9.2:User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("9.3:Open Study Navigator-> Select a Study and a Site from PR#1");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);
	

		reportLog("10.1: Navigate to the Subject details page -> Visits -> All from PR#4");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("10.2:Visit Detail page from PR#4 is displayed with projection dates in the Visits list");
		subjectDetailPage.verifyCalenderVisitPresentWithProjectionDates(visitName1);

		reportLog("11.1:Select an action to Add a Visit from PR#5");
		subjectDetailPage.clickOnCalendarVisitRow(visitName2);
		subjectDetailPage.clickOnCalenderVisitSelfServeCRVisitAddIcon();

		reportLog("11.2:IR Scheduling modal displayed");
		subjectDetailPage.verifyAppointmentPopUpIsDisplayed();

		reportLog("11.3:The dates of the visit projection window are indicated");
		subjectDetailPage.verifySelfCrScheduledDateCalenderWindowDisplayed();

		reportLog("11.4:The dates prior to the earliest date from selection in the Calendar are disabled");
		subjectDetailPage.verifyPastDateIsDisabledFromSelectedDate();

		reportLog(
				"12.1:Select any date on the projection start date (except days prior to the earliest date from selection)");
		slectionDate=subjectDetailPage.currentOnlyDate();
		selectionAfterDate=subjectDetailPage.getFutureDate();
		subjectDetailPage.selectTodayDateOnCrSelfCalenderPopUp(slectionDate);

		reportLog("12.2:Selected date displayed in the Selected Date calendar");
		subjectDetailPage.verifyCrSelfScheduledSelectedDate(slectionDate);

		reportLog("13.1:Select any date after the projection start date");
		subjectDetailPage.selectTodayDateOnCrSelfCalenderPopUp(selectionAfterDate);

		reportLog("13.2:Selected date displayed in the Selected Date calendar");
		subjectDetailPage.verifyCrSelfScheduledSelectedDate(selectionAfterDate);

		reportLog("14.1:try to select prior date:All dates prior to the earliest date from selection are disabled");
		subjectDetailPage.selectTodayDateOnCrSelfCalenderPopUp(slectionDate);
		subjectDetailPage.verifyPastDateIsDisabledFromSelectedDate();
		subjectDetailPage.closeAppointmentPopup();

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
