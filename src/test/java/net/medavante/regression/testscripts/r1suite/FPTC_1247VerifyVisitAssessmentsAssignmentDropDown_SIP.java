package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.*;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1247VerifyVisitAssessmentsAssignmentDropDown_SIP extends BaseTest {

	private String subjectName,requestedVisit,scheduledVisit,initiatedVisit,inProgressVisit,completedVisit,canceledVisit,editingVisit;
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1247VerifyVisitAssessmentsAssignmentDropDown_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		subjectName=prop.getProperty("SubjectCRStatus1532");
		requestedVisit = prop.getProperty("requestedVisit");
		scheduledVisit = prop.getProperty("scheduledVisit");
		initiatedVisit = prop.getProperty("initiatedVisit");
		inProgressVisit = prop.getProperty("inProgressVisit");
		completedVisit= prop.getProperty("completedVisit");
		canceledVisit= prop.getProperty("canceledVisit");
		editingVisit= prop.getProperty("AutoEditVisitNew");
				
	}

	/**
	 * =========================================================================
	 * Test Case Id: FPTC_1247
	 * Test Case Name: Visit assessments - Assignment drop-down
	 * 
	 * =========================================================================
	 * 
	 */

	@Test(description = "FPTC_1247_Visit assessments - Assignment drop-down", groups = { "R1" })
	public void FPTC_1247_verifyVisitAssessmentsAssignmentDropDown() {


		reportLog("1.1: Login to portal as user with canManageCRAppointments claim and verify user login");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport,AT_Password);
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("1.2: navigate to the central rating list");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		
		reportLog("1.3: Clear calendar selected from and to date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("1.4: Select Requested visit Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(requestStatus);

		reportLog("1.5: search Subject name");
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		
		reportLog("1.6: Clicked on searched subject link");
		subjectDetailPage=centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(subjectNumberColumnLabel, subjectName);
		
		reportLog("1.7: Verify Screening Number and Subject detail page is displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(subjectName);
		
		reportLog("1.8: Verify Requested status is configured for selected visit");
		subjectDetailPage.verifyVisitStatus(requestedVisit, requestStatus);
		
		reportLog("1.9: Verify Scheduled status is configured for selected visit");
		subjectDetailPage.verifyVisitStatus(scheduledVisit, scheduledStatus);
		
		reportLog("1.10: Verify Initiated status is configured for selected visit");
		subjectDetailPage.verifyVisitStatus(initiatedVisit, initiatedStatus);
		
		reportLog("1.11: Verify In Progress status is configured for selected visit");
		subjectDetailPage.verifyVisitStatus(inProgressVisit, inProgressStatus);
		
		reportLog("1.12: Verify Complete status is configured for selected visit");
		subjectDetailPage.verifyVisitStatus(completedVisit, completeStatus);
		
		reportLog("1.13: Verify Cancelled status is configured for selected visit");
		subjectDetailPage.verifyVisitStatus(canceledVisit, cancelledStatus);
		
		reportLog("1.14: Verify Editing status is configured for selected visit");
		subjectDetailPage.verifyVisitStatus(editingVisit, editingStatus);
		
		reportLog("2.1: Click on requested visit");
		subjectDetailPage.clickOnVisitRow(requestedVisit);
		
		reportLog("2.2: Verify configured scale is displayed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		
		reportLog("2.3: Verify rater drop down is not displayed");
		subjectDetailPage.verifyRaterDropIsNotPresent(); 

		reportLog("3.1: Click on home button and Navigate to Medavante dashBoard");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		
		reportLog("3.2: Navigate to central rating listing");
		dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		
		reportLog("3.3: Clear calendar selected from and to date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("3.4: Select Scheduled visit Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(scheduledStatus);
		
		reportLog("3.5: Search Subject name");
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		
		reportLog("3.6: Click on searched subject link");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(subjectNumberColumnLabel, subjectName);
		
		reportLog("3.7: Click on Scheduled visit");
		subjectDetailPage.clickOnVisitRow(scheduledVisit);
		
		reportLog("3.8: Verify configured scale is displayed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		
		reportLog("3.9: Verify rater drop down is not displayed");
		subjectDetailPage.verifyRaterDropIsNotPresent();

		reportLog("4.1: Click on home button and Navigate to Medavante dashBoard");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		
        reportLog("4.2: Navigate to central rating listing");
    	dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		
		reportLog("4.3: Clear calendar selected from and to date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("4.4: Select Initiated visit Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(initiatedStatus);
		
		reportLog("4.5: search Subject name");
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		
		reportLog("4.6: Click on searched subject link");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(subjectNumberColumnLabel, subjectName);
		
		reportLog("4.7: Click on Initiated visit");
		subjectDetailPage.clickOnVisitRow(initiatedVisit);
		
		reportLog("4.8: Verify configured scale is displayed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		
		reportLog("4.9: Verify rater drop down is not displayed");
		subjectDetailPage.verifyRaterDropIsNotPresent();

		reportLog("5.1: Click on home button and Navigate to Medavante dashBoard");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		
        reportLog("5.2: Navigate to central rating listing");
    	dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		
		reportLog("5.3: Clear calendar selected from and to date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("5.4: Select In Progress visit Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(inProgressStatus);
		
		reportLog("5.5: search Subject name");
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		
		reportLog("5.6: Click on searched subject link");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(subjectNumberColumnLabel, subjectName);
		
		reportLog("5.7: Click on Progress visit");
		subjectDetailPage.clickOnVisitRow(inProgressVisit);
		
		reportLog("5.8: Verify configured scale is displayed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		
		reportLog("5.9: Verify rater drop down is not displayed");
		subjectDetailPage.verifyRaterDropIsNotPresent();
		
		reportLog("6.1: Click on home button and Navigate to Medavante dashBoard");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		
        reportLog("6.2: Navigate to central rating listing");
    	dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		
		reportLog("6.3: Clear calendar selected from and to date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("6.4: Select Complete visit Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(completeStatus);
		
		reportLog("6.5: search Subject name");
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		
		reportLog("6.6: Click on searched subject link");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(subjectNumberColumnLabel, subjectName);
		
		reportLog("6.7: Click on Completed visit");
		subjectDetailPage.clickOnVisitRow(completedVisit);
		
		reportLog("6.8: Verify configured scale is displayed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		
		reportLog("6.9: Verify rater drop down is not displayed");
		subjectDetailPage.verifyRaterDropIsNotPresent();
		
		reportLog("7.1: Click on home button and Navigate to Medavante dashBoard");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		
        reportLog("7.2: Navigate to central rating listing");
    	dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		
		reportLog("7.3: Clear calendar selected from and to date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("7.4: Select Cancelled visit Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(cancelledStatus);
		
		reportLog("7.5: search Subject name");
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		
		reportLog("7.6: Click on searched subject link");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(subjectNumberColumnLabel, subjectName);
		
		reportLog("7.7: Click on canceled visit");
		subjectDetailPage.clickOnVisitRow(canceledVisit);
		
		reportLog("7.8: Verify configured scale is displayed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		
		reportLog("7.9: Verify rater drop down is not displayed");
		subjectDetailPage.verifyRaterDropIsNotPresent();
		
		reportLog("8.1: Click on home button and Navigate to Medavante dashBoard");
		subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,Constants.HomeNavText);
		
        reportLog("8.2: Navigate to central rating listing");
    	centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		
		reportLog("8.3: Clear calendar selected from and to date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("8.4: Select Editing visit Status");
		centralRatingAssesmentListingPage.searchByVisitStatus(editingStatus);
		
		reportLog("8.5: search Subject name");
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		
		reportLog("8.6: Click on searched subject link");
		centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(subjectNumberColumnLabel, subjectName);
		
		reportLog("8.7: Click on Editing visit");
		subjectDetailPage.clickOnVisitRow(editingVisit);
		
		reportLog("8.8: Verify configured scale is displayed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		
		reportLog("8.9: Verify rater drop down is displayed");
		subjectDetailPage.verifyRaterDropDownIsDisplayed();
		
		reportLog("9.1: Click on rater drop down");
		subjectDetailPage.clickOnAssignRaterDropDown();
		
		reportLog("9.2: Verify Rater name displayed in rater list");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(clinician15UserName); 
		
		reportLog("9.3 Verify Rater drop down is enable to select the rater");
		subjectDetailPage.verifyRaterDropIsEnable(); 
		
		reportLog("9.4: Logout application");
		loginPage.logoutApplication(); 
		
		reportLog("9.5: Verify user is Logout from application");
		loginPage.verifyUserLogout();
		
		reportLog("10.1: Re-login to portal as user without canManageCRAppointments claim");
		dashBoardPage = loginPage.loginInApplication(AT_PRODAdminViewOnly,AT_Password);

		reportLog("10.2: Navigate to CR Assessments");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);
		
		reportLog("10.3: Clear calendar selected from and to date");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("10.4: Select visit with Editing status");
		centralRatingAssesmentListingPage.searchByVisitStatus(editingStatus);

		reportLog("10.5: search Subject name");
		centralRatingAssesmentListingPage.sortBySubjectName(subjectName);
		
		reportLog("10.6: Clicked on search subject link");
		subjectDetailPage=centralRatingAssesmentListingPage.clickOnRowByColumnAndRowValue(subjectNumberColumnLabel, subjectName);
		
		reportLog("10.7: Click on visit in editing status to view details");
		subjectDetailPage.clickOnVisitRow(editingVisit);

		reportLog("10.8: Verify configured scale is displayed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);
		
		reportLog("10.9: Verify rater drop down is not displayed");
		subjectDetailPage.verifyRaterDropIsNotPresent();
		
		reportLog("10.10: Logout application");
		loginPage.logoutApplication(); 
		
		reportLog("10.11: Verify user is Logout from application");
		loginPage.verifyUserLogout();

	}

}