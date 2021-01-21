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

public class FPTC_1263_InPortalDeactivatedRaterShouldNotBeListedInAssigneeDropdown_SIP extends BaseTest {
	private String subjectName = "Subj_" + generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1263_InPortalDeactivatedRaterShouldNotBeListedInAssigneeDropdown_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("StudyName2645");
		pendingNonIRVisit = prop.getProperty("VisitNotAssigned");

		reportLog("Creating a subject for this study");

		MedAvantePortalPage dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		StudyDashBoardPage studyDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPage = studyDashBoardPage.clickOnSaveBTN();

		reportLog("At least one non IR Visit exists for the Subject with one Pending status");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		subjectDetailPage.clickOnCalendarVisitRow(pendingNonIRVisit);
		subjectDetailPage.clickOnInitiateVisitIcon();
		subjectDetailPage.verifyAssesmentNotAssignedForCalenderVisit();
		subjectDetailPage.selectAssigneeDropDownOption(Constants.ATAssignedRater_10);
		subjectDetailPage.verifyCalendarVisitStatus(pendingNonIRVisit, Constants.Pending_Status);
		loginPage.logoutApplication();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1263 Test Case Name: In Portal - Deactivated Rater
	 * should not be listed in assignee drop down
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1263: In Portal - Deactivated Rater should not be listed in assignee drop down", groups = {
			"" })

	public void FPTC_1263_verifyDeactivatedRaterShouldNotBeListedInAssigneeDropdown() {

		reportLog("1.: Log in to portal as a user");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("2: Go to subject details page of subject in Pr#3");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("2.1: Subject details are displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("2.2: Visits in Pr#5 are visible in the visits list");
		subjectDetailPage.verifyCalendarVisitStatus(pendingNonIRVisit, Constants.Pending_Status);

		reportLog("4.2: Should not contain rater in Pr#7.1");
		reportLog("4.2: Should  contain rater in Pr#7.2");

		subjectDetailPage.clickOnCalendarVisitRow(pendingNonIRVisit);
		subjectDetailPage.verifyRaterIsPresentInCalenderVisitDropdown(Constants.ATAssignedRater_5);
		subjectDetailPage.verifyRaterIsNotPresentInCalenderVisitDropdown(Constants.RaterName_23);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
