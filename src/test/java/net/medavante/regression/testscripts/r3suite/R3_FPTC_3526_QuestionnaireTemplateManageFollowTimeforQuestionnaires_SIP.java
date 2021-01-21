/**
 *@author 
* @date 03/03/2020
* =========================================================================
*  Test Case Id: FP-TC-3526 || Test Case Name: Questionnaire Template: Manage Follow Time for Questionnaires
	 * pre-qualification :1. At least one Study with the Product Type selected is Mobile PRO exists for the test
                          2. At least one User who can manage Study schedule exists
                          3. At least one Questionnaire Template with the corresponding Event Template is created within the Study Calendar Pr.#1	
* ========================================================================= 
*/
package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.administration.AdministrationStudiesSchedulePage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3526_QuestionnaireTemplateManageFollowTimeforQuestionnaires_SIP extends BaseTest {
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3526_QuestionnaireTemplateManageFollowTimeforQuestionnaires_SIP(String browser) {
		super(browser);
	}

	
	@BeforeMethod
	public void GetTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
	}
	
	
	@Test(description="FP-TC-3526 --Questionnaire Template: Manage Follow Time for Questionnaires")
	public void R3_FPTC_3526_QuestionnaireTemplate()
	{
		
		reportLog("2.0 Login to Portal as the user of Pr#2");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0 Navigate to Administration");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("3.0.1 Navigate to Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("3.0.2 Search " + studyName + " in Study list ");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		
		reportLog("3.0.3: Navigate To Schedule subTab");
		adminStudiesSchedulePage=adminstrationStudiesPage.navigateToSubTabs(Constants.SubTabSchedule,AdministrationStudiesSchedulePage.class);	
		
		reportLog("3.0.4: Calendar sub-tab");
		adminStudiesSchedulePage.verifyAdministrationStudiesSchedulePage();
		
		reportLog("3.1: Calendar screen is displayed");
		adminStudiesSchedulePage.verifyCalendarScreenDisplayed();
		
		reportLog("4.0: Select control to open the 'Questionnaire Settings' Template Pr.#3");
		adminStudiesSchedulePage.clickToOpenSettingOfEvent(Constants.EventName, Constants.EventformName);
		
		reportLog("4.1: Questionnaire Settings modal window with the following information is displayed:");
		reportLog("Strict Protocol Adherence check-box selected by default");
		reportLog("Follow drop-down with items: 'Device Time' selected by default and 'Site Time' are displayed");
		adminStudiesSchedulePage.verifyAllOptionOnQuestionnaireSettingPopup();
		adminStudiesSchedulePage.verifyFollowupDropDownValue("Device (Local) Time");
		adminStudiesSchedulePage.verifyFollowupdropDownOption();
		
		reportLog("4.2: Save button is disabled");
		adminStudiesSchedulePage.verifySavebtnDisabledonsettingPopup();
		
		reportLog("4.3: Cancel button is enabled");
		adminStudiesSchedulePage.verifyCancelbtnEnabledOnsettingPopup();
		
		reportLog("5.0: Select action to Cancel changes");
		adminStudiesSchedulePage.clickOnCancelBtnOnQuestionnairePopup();
		
		reportLog("5.1: Questionnaire Settings modal window is closed");
		adminStudiesSchedulePage.verifyQuestionnaireSettingPopupNotDisplayed();
		
		reportLog("5.2: Calendar screen is displayed");
		adminStudiesSchedulePage.verifyCalendarScreenDisplayed();
		
		reportLog("5.3: Protocol Adherence for Questionnaires shown as 'Strict'");
		adminStudiesSchedulePage.verifyProtocolAdherenceForQuestionnaire(Constants.EventformName, Constants.Adherence);
		
		reportLog("6.0: Select control to open the 'Questionnaire Settings' Template Pr.#3");
		adminStudiesSchedulePage.refreshPage();
		adminStudiesSchedulePage.clickToOpenSettingOfEvent(Constants.EventName, Constants.EventformName);


		reportLog("6.1: Questionnaire Settings modal window with the following information is displayed:");
		reportLog("Strict Protocol Adherence check-box selected by default");
		reportLog("Follow drop-down with 'Device Time' selected by default");
		adminStudiesSchedulePage.verifyAllOptionOnQuestionnaireSettingPopup();
		adminStudiesSchedulePage.verifyFollowupDropDownValue("Device (Local) Time");

		reportLog("6.2: Save button is disabled");
		adminStudiesSchedulePage.verifySavebtnDisabledonsettingPopup();
		
		reportLog("6.3: Cancel button is enabled");
		adminStudiesSchedulePage.verifyCancelbtnEnabledOnsettingPopup();
		
		reportLog("7.0: Change value from Follow drop-down to 'Site Time'");
		adminStudiesSchedulePage.selectFollowUpoDropDownValueInQuestionnaireSettingpopup("Site Time");
		
		reportLog("7.1:  'Site Time' is selected");
		adminStudiesSchedulePage.verifyFollowupDropDownValue("Site Time");
		
		reportLog("7.2: Save button is enabled");
		adminStudiesSchedulePage.verifySaveButtonisEnablede();
		
		reportLog("7.3: Cancel button is enabled");
		adminStudiesSchedulePage.verifyCancelbtnEnabledOnsettingPopup();
		
		reportLog("8.0: Select action to Cancel changes");
		adminStudiesSchedulePage.clickOnCancelBtnOnQuestionnairePopup();

		reportLog("8.1:  Questionnaire Settings modal window is closed");
		adminStudiesSchedulePage.verifyQuestionnaireSettingPopupNotDisplayed();

		reportLog("8.2: Calendar screen is displayed");
		adminStudiesSchedulePage.verifyCalendarScreenDisplayed();

		reportLog("8.3: Protocol Adherence for Questionnaires shown as 'Strict'");
		adminStudiesSchedulePage.verifyProtocolAdherenceForQuestionnaire(Constants.EventformName, Constants.Adherence);

		reportLog("8.4:  Follow 'Device Time' is set for Questionnaire Settings (displayed in Edit mode)");
		adminStudiesSchedulePage.refreshPage();
		adminStudiesSchedulePage.clickToOpenSettingOfEvent(Constants.EventName, Constants.EventformName);
		adminStudiesSchedulePage.verifyFollowupDropDownValue("Device (Local) Time");

		reportLog("9.0: Change value from Follow drop-down to 'Site Time'");
		adminStudiesSchedulePage.selectFollowUpoDropDownValueInQuestionnaireSettingpopup("Site Time");

		reportLog("9.1:  'Site Time' is selected");
		adminStudiesSchedulePage.verifyFollowupDropDownValue("Site Time");

		reportLog("9.2: Save button is enabled");
		adminStudiesSchedulePage.verifySaveButtonisEnablede();

		reportLog("9.3: Cancel button is enabled");
		adminStudiesSchedulePage.verifyCancelbtnEnabledOnsettingPopup();

		reportLog("10.0: Save changes");
		adminStudiesSchedulePage.clickOnSaveButtonQuestionnaireSettingPopUp();
		
		reportLog("10.1: Questionnaire Settings modal window is closed");
		adminStudiesSchedulePage.verifyQuestionnaireSettingPopupNotDisplayed();

		reportLog("10.2: Calendar screen is displayed");
		adminStudiesSchedulePage.verifyCalendarScreenDisplayed();

        reportLog("10.3:  Protocol Adherence for Questionnaires shown as 'Strict'");
        adminStudiesSchedulePage.selectEventDropArrow(Constants.EventName);
		adminStudiesSchedulePage.verifyProtocolAdherenceForQuestionnaire(Constants.EventformName, Constants.Adherence);

		reportLog("10.4: Follow 'Site Time' is set for Questionnaire Settings (displayed in Edit mode)");
		adminStudiesSchedulePage.refreshPage();
		adminStudiesSchedulePage.clickToOpenSettingOfEvent(Constants.EventName, Constants.EventformName);
		adminStudiesSchedulePage.verifyFollowupDropDownValue("Site Time");
 
		reportLog("Restoring to Previou State");
		adminStudiesSchedulePage.selectFollowUpoDropDownValueInQuestionnaireSettingpopup("Device (Local) Time");
		adminStudiesSchedulePage.clickOnSaveButtonQuestionnaireSettingPopUp();
		
		reportLog(" LogOut Application");
		loginPage.logoutApplication();
		
		reportLog(" Verify LogOut ");
		loginPage.verifyUserLogout();
		
	}
	
}
