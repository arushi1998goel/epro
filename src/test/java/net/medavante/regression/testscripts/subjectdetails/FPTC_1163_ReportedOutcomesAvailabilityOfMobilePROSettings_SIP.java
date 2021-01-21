package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.*; 
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;
import net.medavante.portal.utilities.StudyModuleConstants;

public class FPTC_1163_ReportedOutcomesAvailabilityOfMobilePROSettings_SIP extends BaseTest {


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1163_ReportedOutcomesAvailabilityOfMobilePROSettings_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties testData = Configuration.readTestData("RegressionTestData");		
		studyName = testData.getProperty("AutomationStudyName");
		
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog(" Verify user login successful");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("Navigate to Administration");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
		
		reportLog("Navigate to Studies");
		adminstrationStudiesPage=adminstrationOrganizationPage.navigateToStudies();
		
		reportLog("Search and select "+studyName+" study");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		
		reportLog("Edit General tab");
		adminstrationStudiesPage.editStudy();
		
		reportLog("Un Select Mobile PRO setting to configure the test pre requiste");
		adminstrationStudiesPage.unSelectProductTypeCheckBox(StudyModuleConstants.productType.get(5));
		
		reportLog("Save changes");
		adminstrationStudiesPage.clickOnSaveBTN();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog(" Verify user login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		
		reportLog("Click on add subject button to create new subject to configure the test prerequiste");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("Input " + screeningNum + " screening number to configure the test prerequiste");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);

		reportLog("Select " + studyLanguage
				+ " language from the configured study language options to configure the test prerequiste");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("Click on save button to configure the test prerequiste");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		
	}
	
	
	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1163 Test Case Name: Reported Outcomes - Availability of Mobile PRO settings
	 * =========================================================================
	 * 
	 */

	@Test(description = "FP-TC-1163_Reported Outcomes - Availability of Mobile PRO settings", groups = {
			"SubjectModule" })
	public void FPTC_1163_VerifyReportedOutcomesAvailabilityOfMobilePROSettings() {
		
		reportLog("Verify Subject Details page is displayed");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum); 
		
		reportLog("Verify Reported Outcomes section is available on the page");
		subjectDetailPage.verifyReportedOutcomeSectionIsDisplayed();
		
		reportLog("Verify Mobile section is not displayed at Reported Outcomes section");
		subjectDetailPage.verifyReportedOutComesMobileSectionGridIsNotDisplayed();

		reportLog("Select an action to edit Reported Outcomes");
		subjectDetailPage.clickOnReportedOutComeButton();
		
		reportLog("Reported Outcomes Details screen is displayed ");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();
		
		reportLog("Verify Mobile PRO section with Subject and Observer settings is not available on the screen");
		subjectDetailPage.verifyMobileProSectionWithSubjectAndObserverSettingsIsNotDisplayedInReportedOutcomePopup();
		
		reportLog("Click on cancel button to close the reported outcome popup");
		subjectDetailPage.clickOnReportedOutComePopUpCancelBTN();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog(" Verify user login successful");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("Navigate to Administration");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
				
		reportLog("Navigate to Studies");
		adminstrationStudiesPage=adminstrationOrganizationPage.navigateToStudies();
		
		reportLog("Search and select "+studyName+" study");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		
		reportLog("Edit General tab");
		adminstrationStudiesPage.editStudy();
		
		reportLog("Select Mobile PRO setting");
		adminstrationStudiesPage.selectProductTypeCheckBox(StudyModuleConstants.productType.get(5));
		
		reportLog("General tab is displayed in edit mode ");
		adminstrationStudiesPage.verifyGeneralTabIsInEditMode();
		
		reportLog("Verify Mobile PRO is seleted ");
		adminstrationStudiesPage.verifyProductTypeCheckBoxIsSelected(StudyModuleConstants.productType.get(5));
		
		reportLog("Verify Subject checkbox is pre-selected");
		adminstrationStudiesPage.verifyProductTypeCheckBoxIsSelected(StudyModuleConstants.productType.get(6));	
		
		reportLog("Verify Observer checkbox is not selected");
		adminstrationStudiesPage.verifyProductTypeCheckBoxIsNotSelected(StudyModuleConstants.productType.get(7));
		
		reportLog("Save changes");
		adminstrationStudiesPage.clickOnSaveBTN();
		
		reportLog("Verify Changes are saved");
		adminstrationStudiesPage.verifyProductTypeConfigured(StudyModuleConstants.productType.get(5));
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog(" Verify user login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		
		reportLog("Select and click on " + screeningNum + " subject");
		studyNavigatorDashBoardPage.searchSubject(screeningNum);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSearchedSubject(screeningNum); 
		
		reportLog("Verify Subject Details page is displayed ");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
		
		reportLog("Verify Reported Outcomes section is available on the page ");
		subjectDetailPage.verifyReportedOutcomeSectionIsDisplayed();
		
		reportLog("Verify Mobile section is displayed at Reported Outcomes section");
		subjectDetailPage.verifyReportedOutComesMobileSectionGridIsDisplayed();
		
		reportLog("Verify Subject value is displayed");
		subjectDetailPage.verifyReportedOutcomeMobileSubjectValueText(StudyModuleConstants.productType.get(6));
		
		reportLog("Select an action to edit Reported Outcomes");
		subjectDetailPage.clickOnReportedOutComeButton();
		
		reportLog("Verify Reported Outcomes Details screen is displayed ");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();
		
		reportLog("Verify Mobile PRO section with Subject setting is available on the screen");
		subjectDetailPage.verifyMobileProSectionWithSubjectSettingsIsDisplayedInReportedOutcomePopup();
		
		reportLog("Click on cancel button to close the reported outcome popup");
		subjectDetailPage.clickOnReportedOutComePopUpCancelBTN();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog(" Verify user login successful");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog("Navigate to Administration");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);
				
		reportLog("Navigate to Studies");
		adminstrationStudiesPage=adminstrationOrganizationPage.navigateToStudies();
		
		reportLog("Search and select "+studyName+" study");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		
		reportLog("Edit General tab");
		adminstrationStudiesPage.editStudy();
		
		reportLog("Select Observer setting");
		adminstrationStudiesPage.selectProductTypeCheckBox(StudyModuleConstants.productType.get(7));
		
		reportLog("General tab is displayed in edit mode ");
		adminstrationStudiesPage.verifyGeneralTabIsInEditMode();
		
		reportLog("Verify Observer is seleted ");
		adminstrationStudiesPage.verifyProductTypeCheckBoxIsSelected(StudyModuleConstants.productType.get(7));
		
		reportLog("Save changes");
		adminstrationStudiesPage.clickOnSaveBTN();
		
		reportLog("Verify Changes are saved");
		adminstrationStudiesPage.verifyProductTypeConfigured(StudyModuleConstants.productType.get(7));
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
		
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog(" Verify user login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("Select " + studyName + " study");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		
		reportLog("Select and click on " + screeningNum + " subject");
		studyNavigatorDashBoardPage.searchSubject(screeningNum);
		subjectDetailPage=studyNavigatorDashBoardPage.clickOnSearchedSubject(screeningNum); 
		
		reportLog("Verify Subject Details page is displayed ");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);
		
		reportLog("Verify Reported Outcomes section is available on the page ");
		subjectDetailPage.verifyReportedOutcomeSectionIsDisplayed();
		
		reportLog("Verify Mobile section is displayed at Reported Outcomes section");
		subjectDetailPage.verifyReportedOutComesMobileSectionGridIsDisplayed();
		
		reportLog("Verify Subject value is displayed");
		subjectDetailPage.verifyReportedOutcomeMobileSubjectValueText(StudyModuleConstants.productType.get(6));
		
		reportLog("Verify Observer value is displayed");
		subjectDetailPage.verifyReportedOutcomeMobileObserverValueText(StudyModuleConstants.productType.get(7));
		
		reportLog("Select an action to edit Reported Outcomes");
		subjectDetailPage.clickOnReportedOutComeButton();
		
		reportLog("Verify Reported Outcomes Details screen is displayed ");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();
		
		reportLog("Verify Mobile PRO section with Subject and Observer settings is available on the screen");
		subjectDetailPage.verifyMobileProSectionWithSubjectAndObserverSettingsIsDisplayedInReportedOutcomePopup();
		
		reportLog("Click on cancel button to close the reported outcome popup");
		subjectDetailPage.clickOnReportedOutComePopUpCancelBTN();
		
		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();
	}
	
}
