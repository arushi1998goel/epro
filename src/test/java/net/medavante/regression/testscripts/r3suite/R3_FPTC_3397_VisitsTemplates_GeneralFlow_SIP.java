package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_3397_VisitsTemplates_GeneralFlow_SIP extends BaseTest {

	protected String studyName;
	protected String visitTemplate;
	protected String visitTemplateWithTimepoints;
	protected String visitTemplateName;
	protected String visitTemplateName1;
	protected String visitTemplateWithTimepoint;
	protected String visitTemplateWithTimepoint1;
	protected String timePoint;
	protected String timePoint1;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3397_VisitsTemplates_GeneralFlow_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("AutomationStudyNew");
		visitTemplate = prop.getProperty("Visit_Template_Automation");
		visitTemplateWithTimepoints = prop.getProperty("Visit_Template_With_Timepoints");
		visitTemplateName = "VisitTemp" + generateRandomString(6);
		visitTemplateName1 = "VisitTemp" + generateRandomString(6);
		visitTemplateWithTimepoint = "VisitWithTimepoint" + generateRandomString(6);
		visitTemplateWithTimepoint1 = "VisitWithTimepoint1" + generateRandomString(6);
		timePoint = "TimePoint" + generateRandomString(6);
		timePoint1 = "TPoint" + generateRandomString(6);
	}

	@Test(description = "FPTC_3397_VisitsTemplates_GeneralFlow", groups = { "R3" })
	public void FPTC_3397_VisitsTemplates_GeneralFlow() {

		reportLog("2: Login in to application.");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.1: Click On Adminstration Tile.");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("3.2: Navigate To Studies.");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("3.3: Select Study.");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("3.4: Navigate To Schedule Tab.");
		adminStudiesSchedulePage = adminstrationStudiesPage.navigateToStudyScheduleTab();

		reportLog("3.5: Navigate visit template tab.");
		adminStudiesSchedulePage.verifyVisitTemplateTab();

		reportLog("3.6: Verify Added Visit Template, Visit template with Timepoints and delete/edit button on both templates.");
		adminStudiesSchedulePage.verifyAddedVisitTemplateInList(visitTemplate);
		adminStudiesSchedulePage.verifyAddedVisitTemplateInList(visitTemplateWithTimepoints);
		
		reportLog("4.1: Click on create Visit Template icon.");
		adminStudiesSchedulePage.clickOnCreateVisitTemplateIcon();
		
		reportLog("4.2: Verify All fields, check box, drop down and buttons on create visit template pop up");
		adminStudiesSchedulePage.verifyAllFieldsCheckboxDropdownAndButtonOnCreateVisitTemplatePopUp(Constants.isEnabled);
		
		reportLog("5.1: Fill all fields on Create visit template pop up.");
		adminStudiesSchedulePage.fillAllFieldsWithValidDetailsOnCreateVisitTemplatePopUp(visitTemplateName);
		adminStudiesSchedulePage.clickOnCreateAnotherButtonOnCreateVisitTemplatePopUp();
		adminStudiesSchedulePage.verifyCreateVisitTemplateModelTitle();
		adminStudiesSchedulePage.fillAllFieldsWithValidDetailsOnCreateVisitTemplatePopUp(visitTemplateName1);
		adminStudiesSchedulePage.clickOnSaveButtonOnCreateVisitTemplatePopUp();
		
		reportLog("5.2: Verify added visit templates shown under template table");
		adminStudiesSchedulePage.verifyAddedVisittemplatesUnderVisitTemplateTable(visitTemplateName);
		adminStudiesSchedulePage.verifyAddedVisittemplatesUnderVisitTemplateTable(visitTemplateName1);
		
		reportLog("6.Verify all fields, checkbox, drop down and buttons on Create Visit Template with Timepoints pop up.");
		adminStudiesSchedulePage.clickOnCreateVisitTemplateWithTimepointsIcon();
		adminStudiesSchedulePage.verifyAllFieldsButtonsAndDropdownOnCreateVisitTemplateWithTimepointsPopUp();
		
		reportLog("7.1: Input visit template name and click on create another button on pop up.");
		adminStudiesSchedulePage.inputNameInCreateVisitTemplateWithTimepointsPopUp(visitTemplateWithTimepoint);
		adminStudiesSchedulePage.clickOnCreateAnotherButtonOnVisitTemplateWithTimepointsPopUp();
		
		reportLog("7.2: Input visit template name and click on save button on pop up.");
		adminStudiesSchedulePage.verifyAllFieldsButtonsAndDropdownOnCreateVisitTemplateWithTimepointsPopUp();
		adminStudiesSchedulePage.inputNameInCreateVisitTemplateWithTimepointsPopUp(visitTemplateWithTimepoint1);
		adminStudiesSchedulePage.clickOnSaveButtonOnVisitTemplateWithTimepointsPopUp();
		
		reportLog("7.3: Verify added visittemplate shown under template table.");
		adminStudiesSchedulePage.verifyVisitTemplateTab();
		adminStudiesSchedulePage.verifyAddedVisittemplatesUnderVisitTemplateTable(visitTemplateWithTimepoint);
		adminStudiesSchedulePage.verifyAddedVisittemplatesUnderVisitTemplateTable(visitTemplateWithTimepoint1);
	
		reportLog("8.1: Click on Create Timepoints icon added "+visitTemplateWithTimepoints+" visit template with timepoints.");
		adminStudiesSchedulePage.clickOnCreateTimePointIcon(visitTemplateWithTimepoints);
		
		reportLog("8.2: Verify all items on create Timepoints pop up.");
		adminStudiesSchedulePage.verifyAllItemsOnCreateTimepointsPopUp();
		
		reportLog("9: Verify all configuration option under By Event option.");
		adminStudiesSchedulePage.clickOnByEventCheckbox();
		adminStudiesSchedulePage.verifyAllConfigurationsUnderByEventOption();
		
		reportLog("10: Verify all configuration option under By Time option.");
		adminStudiesSchedulePage.clickOnByTimeCheckbox();
		adminStudiesSchedulePage.verifyTimepointDropdownAndEntryFieldUnderByTimeOption();
		adminStudiesSchedulePage.verifyAllTimeDifference(Constants.time1);
		
		reportLog("11: Verify all configuration option under Count option.");
		adminStudiesSchedulePage.verifyAllConfigurationUnderCountOption();
		
		reportLog("12: Verify all configuration option under Minute by minute option.");
		adminStudiesSchedulePage.clickOnMinuteByMinuteCheckbox();
		adminStudiesSchedulePage.verifyAllConfigurationUnderMinuteByMinuteOrHourlyOption();
		
		reportLog("13: Verify all configuration option under By Hourly option.");
		adminStudiesSchedulePage.clickOnHourlyCheckbox();
		adminStudiesSchedulePage.verifyAllConfigurationUnderMinuteByMinuteOrHourlyOption();
		
		reportLog("14.1: Create timepoint template with valid details.");
		adminStudiesSchedulePage.fillAllFieldWithValidDetailsInCreateTimePointPopUp(timePoint);
		adminStudiesSchedulePage.clickOnCreateAnotherTimePointButton();
		adminStudiesSchedulePage.fillAllFieldWithValidDetailsInCreateTimePointPopUp(timePoint1);
		adminStudiesSchedulePage.saveButtonOnCreateTimePointPopUp();
		
		reportLog("14.2: Verify Added Timepoint templates.");
		adminStudiesSchedulePage.verifyAllAddedTimePointTemplate(visitTemplateWithTimepoints, timePoint);
		//adminStudiesSchedulePage.verifyVisitTemplateTab();
		adminStudiesSchedulePage.verifyAllAddedTimePointTemplate(visitTemplateWithTimepoints, timePoint1);
		
		adminStudiesSchedulePage.deleteVisit(visitTemplateName,visitTemplateName1,visitTemplateWithTimepoint, visitTemplateWithTimepoint1);
		
		
		reportLog("14.3: Logout application");
		loginPage.logoutApplication();

		reportLog("14.4: Verify user is logout");
		loginPage.verifyUserLogout();
	}
}
