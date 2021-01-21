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

public class R3_FPTC_3400_VisitTemplate_ProhibitTemplateOrEventInUseFromBeingDeleted_SIP extends BaseTest {

	protected static String studyName, visitTemp, visitTempWithTimepoint, calenderVisitTemp, calenderVisitTemp1, timePoints;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3400_VisitTemplate_ProhibitTemplateOrEventInUseFromBeingDeleted_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("Study3400");
		visitTemp = "TestVisitTemp" + generateRandomString(6);
		visitTempWithTimepoint = "TestTimePointTemp" + generateRandomString(6);
		calenderVisitTemp = "CalenderVisitTemp" + generateRandomString(6);
		calenderVisitTemp1 = "CalenderVisitTempl" + generateRandomString(6);
		timePoints = "TimePoint" + generateRandomString(6);

		reportLog("1. Creating at least 1 Visit Template, Visit Template with Timepoints activated");
		reportLog("Login in to application.");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
        reportLog("Navigate to Study setup.");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		reportLog("Navigate to Schedule tab under study setup.");
		adminStudiesSchedulePage = adminstrationStudiesPage.navigateToStudyScheduleTab();
		adminStudiesSchedulePage.verifyVisitTemplateTab();
		
		reportLog("Verify and create Visit template with valid details.");
		adminStudiesSchedulePage.clickOnCreateVisitTemplateIcon();
		adminStudiesSchedulePage
				.verifyAllFieldsCheckboxDropdownAndButtonOnCreateVisitTemplatePopUp(Constants.isEnabled);
		adminStudiesSchedulePage.fillAllFieldsWithValidDetailsOnCreateVisitTemplatePopUp(visitTemp);
		adminStudiesSchedulePage.clickOnSaveButtonOnCreateVisitTemplatePopUp();
		
		reportLog("Verify and create Visit Template with Timepoint.");
		adminStudiesSchedulePage.clickOnCreateVisitTemplateWithTimepointsIcon();
		adminStudiesSchedulePage.verifyAllFieldsButtonsAndDropdownOnCreateVisitTemplateWithTimepointsPopUp();
		adminStudiesSchedulePage.inputNameInCreateVisitTemplateWithTimepointsPopUp(visitTempWithTimepoint);
		adminStudiesSchedulePage.clickOnSaveButtonOnVisitTemplateWithTimepointsPopUp();
		
		reportLog("Create and verify Timepoint for added visit.");
		adminStudiesSchedulePage.clickOnCreateTimePointIcon(visitTempWithTimepoint);
		adminStudiesSchedulePage.fillAllFieldWithValidDetailsInCreateTimePointPopUp(timePoints);
		adminStudiesSchedulePage.saveButtonOnCreateTimePointPopUp();
		
		reportLog("Create visit template under Calender visit tab.");
		adminStudiesSchedulePage.verifyAllOptionsUnderCalendarTabInSchedule();
		adminStudiesSchedulePage.clickOnAddVisitTemplateIcon();
		adminStudiesSchedulePage.verifyAllFieldsDropdownAndButtonOnAddCalenderVisitTemplatePopUp();
		adminStudiesSchedulePage.fillAllFieldsAndDropdownOnCalenderVisitPopUp(calenderVisitTemp, visitTemp);
		adminStudiesSchedulePage.clickOnSaveCalenderVisitTemplateButton();
		adminStudiesSchedulePage.clickOnAddVisitTemplateIcon();
		adminStudiesSchedulePage.verifyAllFieldsDropdownAndButtonOnAddCalenderVisitTemplatePopUp();
		adminStudiesSchedulePage.fillAllFieldsAndDropdownOnCalenderVisitPopUp(calenderVisitTemp1,
				visitTempWithTimepoint);
		adminStudiesSchedulePage.clickOnSaveCalenderVisitTemplateButton();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
	}

	@Test(description = "FPTC_3400_VisitTemplate_ProhibitTemplateOrEventInUseFromBeingDeleted", groups = { "R3" })
	public void R3_FPTC_3400_VisitTemplate_ProhibitTemplateOrEventInUseFromBeingDeleted() {

		reportLog("2: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.1: Click On Adminstration Tile");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("3.2: Navigate To Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("3.3: Select Study");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("3.4: Navigate To Schedule Tab");
		adminStudiesSchedulePage = adminstrationStudiesPage.navigateToStudyScheduleTab();

		reportLog("3.5: Verify Calender tab.");
		adminStudiesSchedulePage.verifyAllOptionsUnderCalendarTabInSchedule();

		reportLog("4: Verify edit model displayed with selected visit template.");
		adminStudiesSchedulePage.clickOnEditButtonOnCalenderVisit(calenderVisitTemp);
		adminStudiesSchedulePage.verifyEditCalenderVisitTemplatePopUp(visitTemp);

		reportLog("5. Verify Visit Template list displayed.");
		adminStudiesSchedulePage.verifyVisitTemplateTab();

		reportLog("6. Verify visit template from PR#3 and verify delete confirmation message.");
		adminStudiesSchedulePage.verifyAddedVisittemplatesUnderVisitTemplateTable(visitTemp);
		adminStudiesSchedulePage.clickOnRemoveVisitTemplate(visitTemp);
		adminStudiesSchedulePage.verifyVisitDeleteConfirmationPopUp();

		reportLog("7. Verify Error message displayed that a Visit Template cannot be deleted as it is in use.");
		adminStudiesSchedulePage.clickOnYesButtonOnDeleteVisitConfirmationPopUp();
		adminStudiesSchedulePage.verifyDeleteVisitErrorMessageAndCloseMessage();

		reportLog("8: Verify calender visit edit pop up.");
		adminStudiesSchedulePage.verifyAllOptionsUnderCalendarTabInSchedule();
		adminStudiesSchedulePage.clickOnEditButtonOnCalenderVisit(calenderVisitTemp);

		reportLog("9. Change the selected visit template and save the calender visit.");
		adminStudiesSchedulePage.selectTemplateDropDown(visitTempWithTimepoint);
		adminStudiesSchedulePage.clickOnSaveCalenderVisitTemplateButton();

		reportLog("10. Verify visit template from PR#3 deleted.");
		adminStudiesSchedulePage.verifyVisitTemplateTab();
		adminStudiesSchedulePage.verifyAddedVisittemplatesUnderVisitTemplateTable(visitTemp);
		adminStudiesSchedulePage.clickOnRemoveVisitTemplate(visitTemp);
		adminStudiesSchedulePage.verifyVisitDeleteConfirmationPopUp();
		adminStudiesSchedulePage.clickOnYesButtonOnDeleteVisitConfirmationPopUp();

		reportLog("11: Verify deleted Visit Template from PR#3 is not displayed in the list");
		adminStudiesSchedulePage.verifyAllOptionsUnderCalendarTabInSchedule();
		adminStudiesSchedulePage.verifyVisitTemplateTab();
		adminStudiesSchedulePage.verifyVisitTemplateRemovedAfterDeleteFromList(visitTemp);

		reportLog("12: Verify Second Visit Template from PR#3 is deleted from the Calendar list");
		adminStudiesSchedulePage.verifyAllOptionsUnderCalendarTabInSchedule();
		adminStudiesSchedulePage.clickOnArrowButtonOnAddedCalendarTemplate(calenderVisitTemp1);
		adminStudiesSchedulePage.clickOnDeleteIconOnCalendarVisit(calenderVisitTemp1);
		adminStudiesSchedulePage.verifyVisitDeleteConfirmationPopUp();
		adminStudiesSchedulePage.clickOnYesButtonOnDeleteVisitConfirmationPopUp();

		reportLog("13. Verify Visit Template from PR#3 deleted.");
		adminStudiesSchedulePage.verifyVisitTemplateTab();
		adminStudiesSchedulePage.clickOnRemoveVisitTemplate(visitTempWithTimepoint);
		adminStudiesSchedulePage.verifyVisitDeleteConfirmationPopUp();
		adminStudiesSchedulePage.clickOnYesButtonOnDeleteVisitConfirmationPopUp();

		reportLog("14.1: Logout application");
		loginPage.logoutApplication();

		reportLog("14.2: Verify user is logout");
		loginPage.verifyUserLogout();
	}
}
