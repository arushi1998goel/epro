package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.*;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1242_VerifyRaterAssignmentList_SIP extends BaseTest {

	private String studyName, visitName, screeningNum = "Auto921sqG" + generateRandomAlphanumericString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1242_VerifyRaterAssignmentList_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		visitName = properties.getProperty("Auto_NonCr_Visit1");

		/* Creating Subject For Configuring Pre-requisite */
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		loginPage.logoutApplication();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1242 Test Case Name:Rater's Assignment List
	 * ====================================================================================================================
	 * 
	 * @throws InterruptedException
	 */

	@Test(description = "FP-TC-1242_Verification of Rater's Assignment List", groups = { "R1" })
	public void FPTC_1242_verifyRaterAssignmentList() throws InterruptedException {
		reportLog("1.1 :Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.3 : Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.4 : select " + studyName + " study. and Site");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);

		reportLog("2.1: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("2.2: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("2.3: Select visit row ");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("2.4: Click on add visit icon");
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("2.5: Verify Visit is added");
		subjectDetailPage.verifyVisitStatus(visitName, Constants.Pending_Status);

		reportLog("2.6: Verify Forms for assessments are listed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("3.1: Click on drop-down list to assign rater to ClinRO form");
		subjectDetailPage.clickOnAssignRaterDropDown();

		reportLog("3.2: Verify Me is the first in the assignment list of raters for the logged in user");
		subjectDetailPage.verifyLoginUserDisplayedAtFirstPositionInScaleRaterList();

		reportLog("3.3: Verify Name of the Other Rater is displayed in rater drop down list");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(AT_PRODSiteUserName);

		reportLog("4.1: Logout application");
		loginPage.logoutApplication();

		reportLog("4.2: Log in as a rater 2");
		loginPage.loginInApplication(AT_PRODSiteUser, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("4.3: Navigate to study");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("4.4: select study from the list of studies");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("4.5: Select " + screeningNum + " from subject list");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);

		reportLog("4.6: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("4.7: Verify Visit list is displayed.");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("4.8: Click on visit row");
		subjectDetailPage.clickOnVisitRow(visitName);

		reportLog("4.9: Verify Visit status");
		subjectDetailPage.verifyVisitStatus(visitName, Constants.Pending_Status);

		reportLog("4.10: Verify Forms for assessments are listed");
		subjectDetailPage.verifyScaleAndScaleTypeConfigured(Constants.ClinRO_Form_Label);

		reportLog("5.1: Click on drop-down list to assign rater to ClinRO form");
		subjectDetailPage.clickOnAssignRaterDropDown();

		reportLog("5.2: Verify Me is the first in the assignment list of raters for the logged in user");
		subjectDetailPage.verifyLoginUserDisplayedAtFirstPositionInScaleRaterList();

		reportLog("5.3: Verify Name of the Other Rater is displayed in rater drop down list");
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(AT_PRODSiteCoordinatorCRSelfScheduleUserName);

		reportLog("5.4: Logout application");
		loginPage.logoutApplication();

		reportLog("5.5: Verify User is Logout from the application");
		loginPage.verifyUserLogout();
	}

}
