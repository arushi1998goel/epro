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

public class FPTC_1151_SubjectDetailsViewVisits_SIP extends BaseTest {

	private String visitName, subjectName = "Auto2751" + generateRandomAlphanumericString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1151_SubjectDetailsViewVisits_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
		visitName = properties.getProperty("Visit2148AttachmentCompleted");

		/* Creating Subject For Configuring Pre-requisite */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,AT_PRODSiteCoordinatorUserName,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		/* Subject Created Successfully */
	}

	/**
	 * =============================================================================================================================================
	 * Test Case Id: FP-TC-1151 Test Case Name Subject Details - View Visits
	 * =============================================================================================================================================
	 * 
	 * @throws InterruptedException
	 * 
	 */
	@Test(description = "FP-TC-1151_Subject Details - View Visits ", groups = {})
	public void FPTC_1151_VerifySubjectDetailsViewVisits() {

		reportLog("1.1:	Login to Portal as User PR#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2:	User successfully logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1:Navigate to Subject PR#1 Details page");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATSiteAssignedRater_10);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("2.2:	Subject Details page displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		
		reportLog("3.1:	Select Visits tab -> All");
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("3.2:	All configured Visits displayed under the Visits-All sub-tab");
		subjectDetailPage.verifyAllConfiguredVisitPresent();
		
		reportLog("4.1:	Select any Visit");
		subjectDetailPage.clickOnCalendarVisitRow(visitName);
		
		reportLog("4.2:	All Assessments for a Visit displayed in the right panel");
		subjectDetailPage.VerifyFormThumbnailImageBeforeNotAdminsiteredIsPresent();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();
		
		

	}
}
