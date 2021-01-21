package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1158_AddSubjectWithMask_SIP extends BaseTest {

	private String LoginUrl, ParentWin, ScreeningNoWithMask;
	private String MaskForTC1 = "######";
	private String MaskForTC2 = "SN-####";
	private String MaskForTC3 = "y_m_d_h_m_s_#";
	private String MaskForTC4 = "m*d*h*m*y*#";
	private String MaskForTC5 = "SN-###";

	private String ScreeningWithNumericLength3 = GenerateRandomNumber(3);
	private String ScreeningWithNumericLength6 = GenerateRandomNumber(6);
	private String ScreeningWithNumericLength1 = GenerateRandomNumber(3);
	private String ScreeningWithNumericLength4 = GenerateRandomNumber(4);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1158_AddSubjectWithMask_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutoStudyWithMasking");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1158 Test Case Name: Add Subject with mask
	 * =========================================================================
	 * 
	 * 
	 */
	

	@Test(description = "FP-TC-1158_Add Subject with mask  ", groups = { "" })
	public void FPTC_1158_AddSubjectWithMask() throws InterruptedException {

		reportLog("1.1: Login in to application to verify TC#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		LoginUrl = loginPage.getURL();

		reportLog("1.2: Click On Adminstration Tile");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("1.3: Navigate To Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("1.4: Search study name in the search input");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("2.1: Navigate To Studies Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("2.2: Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("2.3: Configuration to Add masking to Screening# as of TC#1");
		adminstrationStudiesCustomPage.inputScreening(MaskForTC1);

		reportLog("3.1: Open new browser tab");
		ParentWin = dashBoardPage.openApplicationInNewWindowTab(LoginUrl);

		reportLog("3.2 : Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("3.3 : select study.");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.RaterName_21);

		reportLog("3.4 : Verify add subject button is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		
		reportLog("4.1: Click on add subject button");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("4.2: Verify New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("4.3: Verify Required fields are highlighted");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("5.1: Verify Screening# field is Masked");
		studyNavigatorDashBoardPage.verifyScreeningFieldIsInMaskFormat();

		reportLog("6.1: Enter screening no for TC#1");
		studyNavigatorDashBoardPage.inputScreeningNum(ScreeningWithNumericLength6);

		reportLog("6.2: Get Entered screening no with mask for TC#1");
		ScreeningNoWithMask = studyNavigatorDashBoardPage.getValueEnteredInScreeningNum();

		
		reportLog("6.3: Select language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("6.4: Select save button");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("6.5: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(ScreeningNoWithMask);
		subjectDetailPage.deleteSubject();
		
		reportLog("7.1.1: Switch to parent browser tab");
		switchParentWindow(ParentWin);

		reportLog("7.1.2: Configuration to Add masking to Screening# as of TC#1");
		adminstrationStudiesCustomPage.inputScreening(MaskForTC3);

		reportLog("7.1.3: Switch to child browser tab");
		switchToChildWindow();

		reportLog("7.1.4: Navigate to study dashboard");
		//subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("7.1.5: Verify add subject button is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("7.1.6: Click on add subject button");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("7.1.7: Verify New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("7.1.8: Verify Required fields are highlighted");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("7.1.9: Verify Screening# field is Masked");
		studyNavigatorDashBoardPage.verifyScreeningFieldIsInMaskFormat();

		reportLog("7.1.10: Enter screening no for TC#3");
		studyNavigatorDashBoardPage.inputScreeningNum(ScreeningWithNumericLength1);

		reportLog("7.1.11: Get Entered screening no with mask for TC#1");
		ScreeningNoWithMask = studyNavigatorDashBoardPage.getValueEnteredInScreeningNum();

		reportLog("7.1.12: Select language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("7.1.13: Select save button");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("7.1.14: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(ScreeningNoWithMask);
		subjectDetailPage.deleteSubject();
		
		reportLog("7.2.1: Switch to parent browser tab");
		switchParentWindow(ParentWin);

		reportLog("7.2.2: Configuration to Add masking to Screening# as of TC#1");
		adminstrationStudiesCustomPage.inputScreening(MaskForTC4);

		reportLog("7.2.3: Switch to child browser tab");
		switchToChildWindow();

		reportLog("7.2.4: Navigate to study dashboard");
		//subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("7.2.5: Verify add subject button is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("7.2.6: Click on add subject button");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("7.2.7: Verify New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("7.2.8: Verify Required fields are highlighted");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("7.2.9: Verify Screening# field is Masked");
		studyNavigatorDashBoardPage.verifyScreeningFieldIsInMaskFormat();

		reportLog("7.2.10: Enter screening no for TC#3");
		studyNavigatorDashBoardPage.inputScreeningNum(ScreeningWithNumericLength1);

		reportLog("7.2.11: Get Entered screening no with mask for TC#1");
		ScreeningNoWithMask = studyNavigatorDashBoardPage.getValueEnteredInScreeningNum();

		reportLog("7.2.12: Select language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("7.2.13: Select save button");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("7.2.14: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(ScreeningNoWithMask);
		subjectDetailPage.deleteSubject();

		reportLog("8.1: Switch to parent browser tab");
		switchParentWindow(ParentWin);

		reportLog("8.2:Navigate To Studies Site Tab for getting site user detail");
		administrationStudiesSitePage = adminstrationStudiesPage.navigateToStudySitesTab();

		reportLog("8.3: Verify Study site tab is visible ");
		administrationStudiesSitePage.verifyAdministrationStudiesSitesPage();

		reportLog("8.4: Configuration to change site no as of TC#2");
		administrationStudiesSitePage.changeSitesNumber(Constants.RaterName_21, ScreeningWithNumericLength1);

		reportLog("8.5: Navigate To Studies Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("8.6: Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("8.7: Configuration to Add masking to Screening# as of TC#2");
		adminstrationStudiesCustomPage.inputScreening(MaskForTC2);

		reportLog("9.1: Switch to child browser tab");
		switchToChildWindow();

		reportLog("9.2: Navigate to study dashboard");
		//subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("9.3: Verify add subject button is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("9.4: Click on add subject button");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("9.5: Verify New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("9.6: Verify Required fields are highlighted");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("9.7: Verify Screening# field is Masked");
		studyNavigatorDashBoardPage.verifyScreeningFieldIsInMaskFormat();

		reportLog("9.8: Enter screening no for TC#3");
		studyNavigatorDashBoardPage.inputScreeningNum(ScreeningWithNumericLength4);

		reportLog("9.9: Get Entered screening no with mask for TC#1");
		ScreeningNoWithMask = studyNavigatorDashBoardPage.getValueEnteredInScreeningNum();

		reportLog("9.10: Select language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("9.11: Select save button");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("9.12: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(ScreeningNoWithMask);
		subjectDetailPage.deleteSubject();

		reportLog("10.1: Switch to parent browser tab");
		switchParentWindow(ParentWin);

		reportLog("10.2:Navigate To Studies Site Tab for getting site user detail");
		administrationStudiesSitePage = adminstrationStudiesPage.navigateToStudySitesTab();

		reportLog("10.3: Verify Study site tab is visible ");
		administrationStudiesSitePage.verifyAdministrationStudiesSitesPage();

		reportLog("10.4: Configuration to change site no as of TC#2");
		administrationStudiesSitePage.changeSitesNumber(Constants.RaterName_21, ScreeningWithNumericLength1);

		reportLog("10.5: Navigate To Studies Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("10.6: Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("10.7: Configuration to Add masking to Screening# as of TC#2");
		adminstrationStudiesCustomPage.inputScreening(MaskForTC5);

		reportLog("10.8: Switch to child browser tab");
		switchToChildWindow();

		reportLog("10.9: Navigate to study dashboard");
		//subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("10.10: Verify add subject button is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("10.11: Click on add subject button");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("10.12: Verify New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("10.13: Verify Required fields are highlighted");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("10.14: Verify Screening# field is Masked");
		studyNavigatorDashBoardPage.verifyScreeningFieldIsInMaskFormat();

		reportLog("10.15: Enter screening no for TC#3");
		studyNavigatorDashBoardPage.inputScreeningNum(ScreeningWithNumericLength3);

		reportLog("10.16: Get Entered screening no with mask for TC#1");
		ScreeningNoWithMask = studyNavigatorDashBoardPage.getValueEnteredInScreeningNum();

		reportLog("10.17: Select language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("10.18: Select save button");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("10.19: Verify Subject details page is opened");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(ScreeningNoWithMask);
		subjectDetailPage.deleteSubject();
		
		reportLog("10.20: Logout application");
		loginPage.logoutApplication();

		reportLog("10.21: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
