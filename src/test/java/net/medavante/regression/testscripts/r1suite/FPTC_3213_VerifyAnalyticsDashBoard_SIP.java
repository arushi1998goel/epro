package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.*;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_3213_VerifyAnalyticsDashBoard_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3213_VerifyAnalyticsDashBoard_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties userCredentials = Configuration.readTestData("userClaimsCredentials");
		userName = userCredentials.getProperty("PRODAdminOps");
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("DashBoardStudy");
		dashBoard1 = prop.getProperty("DashBoardName1");
		dashBoard2 = prop.getProperty("DashBoardName_2");
		investigatorRoleAccess = prop.getProperty("InvestigatorRole");
		sponsorUserType1Access = prop.getProperty("SponsorUserType1Role");
		dashBoardVersion = prop.getProperty("DashBoardVersion");
		siteRaterAccess = prop.getProperty("SiteRaterRole");
		MedUserType1Access = prop.getProperty("MedUserType1Role");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-3213 Test Case Name: Configure Analytics Dashboard
	 * to Study
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-3213 --Configure Analytics Dashboard to Study", groups = { "R1" })
	public void FPTC_3213_verifyDashboardManagementScreenAddDashboardGeneralFlow() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("1.2: Verify user is Login in to application");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2.1: Navigate to Administration ");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("2.2: Navigate to study");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("2.3:  Select " + studyName + " from study list");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("2.4: Navigate to Analytics tab");
		adminstrationStudiesAnalytics = adminstrationStudiesPage.navigateToStudyAnalyticsTab();

		reportLog("2.5: Delete the existing configured dashboard to set the test pre requiste");
		adminstrationStudiesAnalytics.deleteConfiguredDashBoard();

		reportLog("2.6: verify Analytics tab is displayed");
		adminstrationStudiesAnalytics.verifyAnalyticsPageIsDisplayed();

		reportLog("3.1: Select an action to add a new Dashboard");
		adminstrationStudiesAnalytics.clickOnAddDashBoardBTN();

		reportLog("3.2: Verify modal window displayed with the items");
		adminstrationStudiesAnalytics.verifyAddDashBoardPopUpIsDisplayed();

		reportLog("4.1 Select Dashboard As Item Type");
		adminstrationStudiesAnalytics.selectItemTypeRadioButton(Constants.ItemType_Dashboard);
		
		reportLog("4.2: Select control to save Dashboard and Verify save button is disabled");
		adminstrationStudiesAnalytics.verifySaveButtonIsDisabled();

		reportLog("4.3: Verify All required fields are highlighted: - Item Type drop down  -Role access drop-down ");
		adminstrationStudiesAnalytics.verifyAllRequiredFieldsValidationMessagesAreDisplayed();
		
		reportLog("4.4: verify Selected DashBoard Is Not Saved");
		adminstrationStudiesAnalytics.verifySelectedDashBoardIsNotSaved(dashBoardName);

		reportLog("5.1: Select an action to close the modal window");
		adminstrationStudiesAnalytics.closeAddNewDashBoardPopUP();

		reportLog("5.2: verify Analytics tab displayed without changes");
		adminstrationStudiesAnalytics.verifyAnalyticsPageIsDisplayed();

		reportLog("6.1: Select an action to add a new Dashboard");
		adminstrationStudiesAnalytics.clickOnAddDashBoardBTN();

		reportLog("6.2: Verify A modal window to add a new Dashboard displayed");
		adminstrationStudiesAnalytics.verifyAddDashBoardPopUpIsDisplayed();

		reportLog("6.3: select a cancel control");
		adminstrationStudiesAnalytics.clickOnCancelBTN();

		reportLog("6.4: verify Analytics tab displayed without changes");
		adminstrationStudiesAnalytics.verifyAnalyticsPageIsDisplayed();

		reportLog("7.1: Select an action to add a new Dashboard");
		adminstrationStudiesAnalytics.clickOnAddDashBoardBTN();
		
		reportLog("7.2 Select Dashboard As Item Type");
		adminstrationStudiesAnalytics.selectItemTypeRadioButton(Constants.ItemType_Dashboard);

		reportLog("7.3: select any Dashboard name from the drop-down list");
		adminstrationStudiesAnalytics.selectDashBoard(dashBoard1);

		reportLog("7.4: Verify A modal window to add a new Dashboard displayed ");
		adminstrationStudiesAnalytics.verifyAddDashBoardPopUpIsDisplayed();

		reportLog("7.5: select a cancel control");
		adminstrationStudiesAnalytics.clickOnCancelBTN();

		reportLog("7.6: Verify selected dashboard not added");
		adminstrationStudiesAnalytics.verifySelectedDashBoardIsNotSaved(dashBoard1);

		reportLog("7.7: Analytics tab displayed without changes");
		adminstrationStudiesAnalytics.verifyAnalyticsPageIsDisplayed();

		reportLog("7.8: Select an action to add a new Dashboard");
		adminstrationStudiesAnalytics.clickOnAddDashBoardBTN();
		
		reportLog("8.1 Select Dashboard As Item Type");
		adminstrationStudiesAnalytics.selectItemTypeRadioButton(Constants.ItemType_Dashboard);

		reportLog("8.2: select a Deactivation date");
		adminstrationStudiesAnalytics.selectDeactivationDate();

		reportLog("8.3: Verify A modal window to add a new Dashboard displayed");
		adminstrationStudiesAnalytics.verifyAddDashBoardPopUpIsDisplayed();

		reportLog("8.4: select a cancel control");
		adminstrationStudiesAnalytics.clickOnCancelBTN();

		reportLog("8.9: Verify selected dashboard not added");
		adminstrationStudiesAnalytics.verifySelectedDashBoardIsNotSaved(dashBoard1);

		reportLog("8.10: verify Analytics tab displayed without changes ");
		adminstrationStudiesAnalytics.verifyAnalyticsPageIsDisplayed();

		reportLog("9.1: Select an action to add a new Dashboard");
		adminstrationStudiesAnalytics.clickOnAddDashBoardBTN();
		
		reportLog("9.2 Select Dashboard As Item Type");
		adminstrationStudiesAnalytics.selectItemTypeRadioButton(Constants.ItemType_Dashboard);

		reportLog("9.3: fill in the Role access field");
		adminstrationStudiesAnalytics.selectRoleAccess(investigatorRoleAccess);

		reportLog("9.4: verify A modal window to add a new Dashboard displayed");
		adminstrationStudiesAnalytics.verifyAddDashBoardPopUpIsDisplayed();

		reportLog("9.5: select a cancel control");
		adminstrationStudiesAnalytics.clickOnCancelBTN();

		reportLog("9.6: Verify selected dashboard not added");
		adminstrationStudiesAnalytics.verifySelectedDashBoardIsNotSaved(dashBoard1);

		reportLog("9.7: verify Analytics tab displayed without changes");
		adminstrationStudiesAnalytics.verifyAnalyticsPageIsDisplayed();

		reportLog("10.1: Select an action to add a new Dashboard");
		adminstrationStudiesAnalytics.clickOnAddDashBoardBTN();
		
		reportLog("10.2 Select Dashboard As Item Type");
		adminstrationStudiesAnalytics.selectItemTypeRadioButton(Constants.ItemType_Dashboard);

		reportLog("10.3: verify A modal window to add a new Dashboard displayed ");
		adminstrationStudiesAnalytics.verifyAddDashBoardPopUpIsDisplayed();

		reportLog("11.1: Select any Dashboard from the drop-down list");
		adminstrationStudiesAnalytics.selectDashBoard(dashBoard1);

		reportLog("11.2: verify Selected Dashboard name displayed");
		adminstrationStudiesAnalytics.verifyDashBoardIsSelected(dashBoard1);
		
		reportLog("11.3: verify Version is required");
		adminstrationStudiesAnalytics.verifyVersionIsRequired();
		
		reportLog("12.1: Select Version");
		adminstrationStudiesAnalytics.selectVersion(dashBoardVersion);

		reportLog("12.2: verify " + dashBoardVersion + " Version displayed");
		adminstrationStudiesAnalytics.verifyVersionIsSelected(dashBoardVersion);

		 reportLog("12: verify Alias field is filled in with a display name by default" ); 
		 adminstrationStudiesAnalytics.verifyAliasIsSelectedByDefaulted(dashBoard1);
		 
		reportLog("13.1: select a Deactivation date ");
		adminstrationStudiesAnalytics.selectDeactivationDate();

		reportLog("13.2: Select " + investigatorRoleAccess + " Role access");
		adminstrationStudiesAnalytics.selectRoleAccess(investigatorRoleAccess);

		reportLog("13.4: verify Deactivation fields are filled in");
		adminstrationStudiesAnalytics.verifyDeactivationIsFilled();

		reportLog("13.5: verify " + investigatorRoleAccess + " access is selected");
		adminstrationStudiesAnalytics.verifyRoleAccessIsFilled(investigatorRoleAccess);

		reportLog("13.6: verify Save control becomes active");
		adminstrationStudiesAnalytics.verifySaveButtonIsEnabled();

		reportLog("14.1: Select a control to save");
		adminstrationStudiesAnalytics.clickOnSaveBTN();

		reportLog("14.2: Verify " + dashBoard1 + "  Dashboard is displayed");
		adminstrationStudiesAnalytics.verifySelectedDashBoardIsSaved(dashBoard1);

		reportLog("15.1: Select an action to add a new Dashboard");
		adminstrationStudiesAnalytics.clickOnAddDashBoardBTN();

		reportLog("15.2: verify A modal window to add a new Dashboard displayed ");
		adminstrationStudiesAnalytics.verifyAddDashBoardPopUpIsDisplayed();
		
		reportLog("16.1 Select Dashboard As Item Type");
		adminstrationStudiesAnalytics.selectItemTypeRadioButton(Constants.ItemType_Dashboard);

		reportLog("16.2: Select to open the Dashboard drop-down- Select " + dashBoard2 + " Dashboard");
		adminstrationStudiesAnalytics.selectDashBoard(dashBoard2);

		reportLog("16.3 : verify The Dashboard drop-down list displayed with Dashboards that have not been added to "
				+ "the study yet");
		adminstrationStudiesAnalytics.verifySelectedDashBoardNotInDropDown(dashBoard1);
	
		reportLog("17.1: Select to open the Version drop-down list and select " + dashBoardVersion + " Version");
		adminstrationStudiesAnalytics.selectVersion(dashBoardVersion);

		reportLog("17.2: verify " + dashBoardVersion
				+ "  Versions for the selected Dashboard are displayed in the drop-down list");
		adminstrationStudiesAnalytics.verifyVersionIsSelected(dashBoardVersion);

		reportLog("18.1: Select " + investigatorRoleAccess + "  Role in access field");
		adminstrationStudiesAnalytics.selectRoleAccess(investigatorRoleAccess);

		reportLog("18.2: Select " + sponsorUserType1Access + " Role in access field");
		adminstrationStudiesAnalytics.selectRoleAccess(sponsorUserType1Access);

		reportLog("18.3: Select " + MedUserType1Access + " Role in access field");
		adminstrationStudiesAnalytics.selectRoleAccess(MedUserType1Access);

		reportLog("18.4: verify " + investigatorRoleAccess + " Role is selected in access field");
		adminstrationStudiesAnalytics.verifyRoleAccessIsFilled(investigatorRoleAccess);

		reportLog("18.5: verify " + sponsorUserType1Access + "  Role is selected in access field");
		adminstrationStudiesAnalytics.verifyRoleAccessIsFilled(sponsorUserType1Access);

		reportLog("18.6: verify " + MedUserType1Access + " Role is selected in access field");
		adminstrationStudiesAnalytics.verifyRoleAccessIsFilled(MedUserType1Access);

		reportLog("18.7: verify Save control becomes active");
		adminstrationStudiesAnalytics.verifySaveButtonIsEnabled();

		reportLog("19.1: Select " + siteRaterAccess + " Roles in access field");
		adminstrationStudiesAnalytics.selectRoleAccess(siteRaterAccess);
		
		reportLog("19.3: Select a control to save");
		adminstrationStudiesAnalytics.clickOnSaveBTN();

		reportLog("19.4: Verify " + dashBoard2 + " Dashboard displayed in the list with the corresponding data");
		adminstrationStudiesAnalytics.verifySelectedDashBoardIsSaved(dashBoard2);
		
		adminstrationStudiesAnalytics.verifySelectedRolesAreDisplayedInDashboard(siteRaterAccess,investigatorRoleAccess,sponsorUserType1Access,MedUserType1Access);

		reportLog("20.1: Logout from the system");
		loginPage.logoutApplication();

		reportLog("20.2: Log in to the Portal by " + AT_PRODAdminViewOnly);
		dashBoardPage = loginPage.loginInApplication(AT_PRODAdminViewOnly, AT_Password);

		reportLog("20.3: Verify " + maAdmin1UserName + " has been logged in to the system");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("21.1: Navigate to Administration ");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("21.2: Navigate to study");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("21.3:  Select " + studyName + " from the study list");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("21.4: Navigate to Analytics tab");
		adminstrationStudiesAnalytics = adminstrationStudiesPage.navigateToStudyAnalyticsTab();

		reportLog("21.5: Verify Add Dashboard control is disabled");
		adminstrationStudiesAnalytics.verifyAddDashBoardButtonIsDisabled();

		reportLog("21.6: Logout from the system");
		loginPage.logoutApplication();

		reportLog("21.7: Verify User Logout");
		loginPage.verifyUserLogout();
	}

}
