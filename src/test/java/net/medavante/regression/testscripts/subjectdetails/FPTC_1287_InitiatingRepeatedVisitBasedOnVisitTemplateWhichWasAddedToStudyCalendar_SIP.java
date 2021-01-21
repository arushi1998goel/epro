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

public class FPTC_1287_InitiatingRepeatedVisitBasedOnVisitTemplateWhichWasAddedToStudyCalendar_SIP extends BaseTest {


	private String visitName, visitPending, visitCompleted = "UnscheduledVisit";


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1287_InitiatingRepeatedVisitBasedOnVisitTemplateWhichWasAddedToStudyCalendar_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");

		subjectName=properties.getProperty("Subject1287");	
		visitName = properties.getProperty("UnscheduledVisitCompleted");
		visitPending=properties.getProperty("UnscheduledVisitPending");

	}

	/**
	 * =============================================================================================================================================
	 * Test Case Id: FP-TC-1287 Test Case Name:Initiating Repeated Visit based
	 * on Visit template which was added to Study Calendar
	 * 
	 * =============================================================================================================================================
	 * 
	 * @throws InterruptedException
	 * 
	 */
	@Test(description = "FP-TC-1287_InitiatingRepeatedVisitBasedOnVisitTemplateWhichWasAddedToStudyCalendar", groups = {})
	public void FPTC_1287_VerifyInitiatingRepeatedVisitBasedOnVisitTemplateWhichWasAddedToStudyCalendar()
			throws InterruptedException {

		reportLog("1.1:Log in to the Portal as User in Pr#5");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2:	User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Navigate to the Subject Details page of Pr#4");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		
		reportLog("2.2:Subject Details page is displayed ");
		subjectDetailPage.verifyNewSubjectDetailPage();	
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);
		
		reportLog("2.3:Add Unscheduled visits control is visible");
		subjectDetailPage.verifyUnscheduledAddVisitBTNIsDisplayed();
		
		reportLog("3.1:Select an action to view Unscheduled visits list");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();
		
		reportLog("3.2:The visit of Pr#4 is displayed in Unscheduled visits list");
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(visitName);
		
		reportLog("4.1:Select the Visit of Pr#4 in Unscheduled visits list");
		subjectDetailPage.selectUnscheduledVisit(visitName);
		
		reportLog("4.2:The visit is added to the Visits list ");
		subjectDetailPage.verifyVisitPresentInCalenderVisitRow(visitPending);
		
		reportLog("4.3:The Visit is initiated");
		subjectDetailPage.verifyVisitIsInitiated();
		
		reportLog("Configuring Pre-Requisite");
		subjectDetailPage.clickOnCalenderVisitCancelIcon();
		
		reportLog("2.2:Subject Details page is displayed ");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("2.3:Add Unscheduled visits control is visible");
		subjectDetailPage.verifyUnscheduledAddVisitBTNIsDisplayed();

		reportLog("3.1:Select an action to view Unscheduled visits list");
		subjectDetailPage.clickOnUnscheduledAddVisitBTN();

		reportLog("3.2:The visit of Pr#4 is displayed in Unscheduled visits list");
		subjectDetailPage.verifyVisitContainsInUnscheduledVisitList(visitCompleted);

		reportLog("4.1:Select the Visit of Pr#4 in Unscheduled visits list");
		subjectDetailPage.selectUnscheduledVisit(visitCompleted);

		reportLog("4.2:The visit is added to the Visits list ");
		subjectDetailPage.verifyVisitPresentInCalenderVisitRow(visitPending);

		reportLog("4.3:The Visit is initiated");
		subjectDetailPage.verifyVisitIsInitiated();

		reportLog("Configuring Pre-Requisite");
		subjectDetailPage.clickOnCalenderVisitCancelIcon();


		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

		

	}
}
