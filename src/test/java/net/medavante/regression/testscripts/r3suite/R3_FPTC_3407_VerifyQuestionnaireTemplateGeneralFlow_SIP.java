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

public class R3_FPTC_3407_VerifyQuestionnaireTemplateGeneralFlow_SIP extends BaseTest {

	protected String studyName, temp, temp1, event, event1;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_3407_VerifyQuestionnaireTemplateGeneralFlow_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("StudyMobileEPROVirigilAPK");
		temp = "DemoTemp" + generateRandomString(6);
		temp1 = "DemoTemp" + generateRandomString(6);
		event = "DemoEvent" + generateRandomString(6);
		event1 = "DemoEvent" + generateRandomString(6);
	}

	@Test(description = "FPTC_3407_VerifyQuestionnaireTemplate_GeneralFlow", groups = { "R3" })
	public void R3_FPTC_3407_VerifyQuestionnaireTemplateGeneralFlow() {

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

		reportLog("3.5: Verify sub- tab at Schedule page.");
		adminStudiesSchedulePage.verifyAdministrationStudiesSchedulePage();

		reportLog("3.6: Click on Questionnaire Templates tab.");
		adminStudiesSchedulePage.verifyQuestionaireTemplatesScreen();

		reportLog("4: Click on Create Questionnaire Templates icon and Assert pop up window for new template.");
		adminStudiesSchedulePage.clickonCreateQuestionnaireTemplateAndAssertPopUp();

		reportLog("5.1: Assert save button is displayed disabled.");
		adminStudiesSchedulePage.verifySaveButtonDisplayedDisabled();

		reportLog("5.2: Assert name field is displayed as required.");
		adminStudiesSchedulePage.verifyNameFieldDisplayedAsRequired();

		reportLog("6: Click on close icon and assert new template not added.");
		adminStudiesSchedulePage.clickOnCloseIconOnPopUpAndAssertTemplateNotAdded(temp);

		reportLog("7: Click on Create Questionnaire Templates icon and Assert pop up window for new template.");
		adminStudiesSchedulePage.clickonCreateQuestionnaireTemplateAndAssertPopUp();

		reportLog("8: Fill Name field, click on close icon and assert new template not added.");
		adminStudiesSchedulePage.clickOnCloseIconOnPopUpAndAssertTemplateNotAdded(temp);

		reportLog("9: Click on Create Questionnaire Templates icon and Assert pop up window for new template.");
		adminStudiesSchedulePage.clickonCreateQuestionnaireTemplateAndAssertPopUp();

		reportLog("10: Fill required field and click on save button on pop up. Assert new template added.");
		adminStudiesSchedulePage.fillNameFieldAndClickOnSaveButton(temp);
		
		reportLog("11: Click on Create Questionnaire Templates icon and Assert pop up window for new template.");
		adminStudiesSchedulePage.clickonCreateQuestionnaireTemplateAndAssertPopUp();

		reportLog(
				"12: Assert new template is added and new Questionnaire pop up opens when click on create another button.");
		adminStudiesSchedulePage.fillNameFieldAndClickOnCreateAnotherButton(temp1);

		reportLog("13: Click on Create Event icon and assert Create Event pop up.");
		adminStudiesSchedulePage.clickOnCreateNewEvent(temp1);
		adminStudiesSchedulePage.verifyCreateEventPopUp();

		reportLog("14.1: Verify save button is displayed as disabled on Create Event pop up.");
		adminStudiesSchedulePage.verifySaveButtonDisabledOnCreateEventPopUp();

		reportLog("14.2: Verify All required fields are displayed highlighted on Create Event pop up.");
		adminStudiesSchedulePage.verifyNameFieldAndScaleCheckboxesDisplayedHighlighted();

		reportLog("14.3: click on close icon on create Event pop up and verify event not added.");
		adminStudiesSchedulePage.clickOnCloseIconOnCreateEventPopUp();
		adminStudiesSchedulePage.verifyEventNotAddedInList(temp1);

		reportLog("15.1: Fill all required field and Click on Cancel button on create Eevent pop up.");
		adminStudiesSchedulePage.clickOnCreateNewEvent(temp1);
		adminStudiesSchedulePage.verifyCreateEventPopUp();
		adminStudiesSchedulePage.fillEventNameField(event);
		adminStudiesSchedulePage.clickOnCancelButtonOnCreateEventPopUp();

		reportLog("15.2: Assert new Event not added.");
		adminStudiesSchedulePage.verifyEventNotAddedInList(temp1);

		reportLog("16: Click on Create Event icon.");
		adminStudiesSchedulePage.clickOnCreateNewEvent(temp1);
		adminStudiesSchedulePage.verifyCreateEventPopUp();

		reportLog("17.1: Fill required field and click on close icon on create event pop up");
		adminStudiesSchedulePage.fillEventNameField(event);
		adminStudiesSchedulePage.clickOnCloseIconOnCreateEventPopUp();

		reportLog("17.2: Assert new Event not added.");
		adminStudiesSchedulePage.verifyEventNotAddedInList(temp1);

		reportLog("18: Click on Create Event Icon and assert Create Event pop up.");
		adminStudiesSchedulePage.clickOnCreateNewEvent(temp1);
		adminStudiesSchedulePage.verifyCreateEventPopUp();

		reportLog("19.1: Fill all required field and click on save button on create event pop up.");
		adminStudiesSchedulePage.fillEventNameField(event);
		adminStudiesSchedulePage.selectCheckBoxAndClickOnSaveButtonOnCreateEventPopUp();

		reportLog("19.2: Assert new Event is added.");
		adminStudiesSchedulePage.clickOnExpandIconOnQuestionnaireTemplate(temp1);
		adminStudiesSchedulePage.verifyAddedEvent(event);

		reportLog("20: Click on Create Event Icon and assert Create Event pop up.");
		adminStudiesSchedulePage.clickOnCreateNewEvent(temp1);
		adminStudiesSchedulePage.verifyCreateEventPopUp();

		reportLog("21.1: Fill all required field and click on Create Another button on create event pop up.");
		adminStudiesSchedulePage.fillEventNameField(event1);
		adminStudiesSchedulePage.selectcheckboxAndClickOnCreateAnotherButton(event);
		adminStudiesSchedulePage.clickOnCancelButtonOnCreateEventPopUp();

		reportLog("22.2: Assert Newly event is created.");
		adminStudiesSchedulePage.clickOnExpandIconOnQuestionnaireTemplate(temp1);
		adminStudiesSchedulePage.verifyAddedEvent(event1);

		reportLog("23.1: logout Appliication");
		loginPage = loginPage.logoutApplication();

		reportLog("23.2: verify user is logout");
		loginPage.verifyUserLogout();
	}
}
