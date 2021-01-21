package net.medavante.regression.testscripts.studynavigator;

import java.awt.AWTException;
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

public class FPTC_1227_SystemValidatesSubjectNumberWhileCreatingANewSubject_SIP extends BaseTest {

	private String LoginUrl, ParentWin;
	private String MaskInputfield = "######";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1227_SystemValidatesSubjectNumberWhileCreatingANewSubject_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1227 || Test Case Name: System validates subject
	 * number while creating a new subject
	 * =========================================================================
	 * 
	 * @throws InterruptedException
	 * @throws AWTException 
	 */

	@Test(description = "FP-TC-1227_System validates subject number while creating a new subject", groups = { "" })
	public void FPTC_1227_SystemValidatesSubjectNumberWhileCreatingANewSubject() throws InterruptedException, AWTException {

		reportLog("1.1: Login in to application to verify TC#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		LoginUrl = loginPage.getURL();

		reportLog("1.2: Click On Adminstration Tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("1.3: Navigate To Studies");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("1.4: Search study name in the search input");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("1.5: Navigate To Studies Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("1.6: Verify Study Custome tab is visible ");
		adminstrationStudiesCustomPage.verifyCustomPage();

		reportLog("2.1: Configuration to check Randomization Number Check Box as of TC#1");
		adminstrationStudiesCustomPage.checkRandomizationNumberCheckBox();

		reportLog("2.2: Configuration to check Temporary Id Check Box as of TC#1");
		adminstrationStudiesCustomPage.checkTemporaryIdCheckBox();

		reportLog("2.3: Configuration to Add masking to Screening Number, Randomization Number and Temporary ID as of TC#1");
		adminstrationStudiesCustomPage.inputScreeningRandomizationAndTempID(MaskInputfield, MaskInputfield,MaskInputfield);

		reportLog("3.1: Open new browser tab");
		ParentWin = dashBoardPage.openApplicationInNewWindowTab(LoginUrl);

		reportLog("3.2 : Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("3.3 : select study.");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("3.4 : Verify add subject button is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("4.1: Click on add subject button");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("4.2: Verify New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("4.3: Verify Screening Number,Randomization Number and Temp Id displayed");
		studyNavigatorDashBoardPage.verifyScreeningNumberRandomizationTempIdIsDisplayed();

		reportLog("5.1: Select language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("5.2: Verify Save button is disabled and Subject can't be created");
		studyNavigatorDashBoardPage.verifySaveButtonIsDisabled();

		reportLog("5.3: click on cancel button to close the add new subject popup");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("6.1.1: Switch to parent browser tab");
		switchParentWindow(ParentWin);

		reportLog("6.1.2: Configuration to Un-check Randomization Number Check Box as of TC#2");
		adminstrationStudiesCustomPage.uncheckRandomizationNumberCheckBox();

		reportLog("6.1.3: Configuration to Add masking to Screening Number and Temporary ID and removing mask from Randomization Number as of TC#2");
		adminstrationStudiesCustomPage.inputScreeningRandomizationAndTempID(MaskInputfield, "", MaskInputfield);

		reportLog("6.1.4: Switch to child browser tab");
		switchToChildWindow();

		reportLog("6.1.5: Verify add subject button is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("6.1.6: Click on add subject button");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("6.1.7: Verify New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("6.1.8: Verify Screening Number displayed");
		studyNavigatorDashBoardPage.verifyScreeningNumbeIsDisplayed();

		reportLog("6.1.9: Verify Temp Id displayed");
		studyNavigatorDashBoardPage.verifyTempIdIsDisplayed();

		reportLog("6.1.9: Select language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("6.1.10: Verify Save button is disabled and Subject can't be created");
		studyNavigatorDashBoardPage.verifySaveButtonIsDisabled();

		reportLog("6.1.11: click on cancel button to close the add new subject popup");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("6.2.1: Switch to parent browser tab");
		switchParentWindow(ParentWin);

		reportLog("6.2.2: Configuration to Add masking to Temporary ID and removing mask from Screening Number and Randomization Number as of TC#3");
		adminstrationStudiesCustomPage.inputScreeningRandomizationAndTempID("", "", MaskInputfield);

		reportLog("6.2.3: Switch to child browser tab");
		switchToChildWindow();

		reportLog("6.2.4: Verify add subject button is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("6.2.5: Click on add subject button");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("6.2.6: Verify New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("6.2.7: Verify Screening Number displayed");
		studyNavigatorDashBoardPage.verifyScreeningNumbeIsDisplayed();

		reportLog("6.2.8: Verify Temp Id displayed");
		studyNavigatorDashBoardPage.verifyTempIdIsDisplayed();

		reportLog("6.2.8: Select language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("6.2.9: Verify Save button is disabled and Subject can't be created");
		studyNavigatorDashBoardPage.verifySaveButtonIsDisabled();

		reportLog("6.2.10: click on cancel button to close the add new subject popup");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("6.3.1: Switch to parent browser tab");
		switchParentWindow(ParentWin);

		reportLog("6.3.2: Configuration to remove mask from Screening Number, Randomization Number and Temporary ID as of TC#4");
		adminstrationStudiesCustomPage.inputScreeningRandomizationAndTempID("", "", "");

		reportLog("6.3.3: Switch to child browser tab");
		switchToChildWindow();

		reportLog("6.3.4: Verify add subject button is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("6.3.5: Click on add subject button");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("6.3.6: Verify New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("6.3.7: Verify Screening Number displayed");
		studyNavigatorDashBoardPage.verifyScreeningNumbeIsDisplayed();

		reportLog("6.3.8: Verify Temp Id displayed");
		studyNavigatorDashBoardPage.verifyTempIdIsDisplayed();

		reportLog("6.3.8: Select language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("6.3.9: Verify Save button is disabled and Subject can't be created");
		studyNavigatorDashBoardPage.verifySaveButtonIsDisabled();

		reportLog("6.3.10: click on cancel button to close the add new subject popup");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("6.4.1: Switch to parent browser tab");
		switchParentWindow(ParentWin);

		reportLog("6.4.2: Configuration to check Randomization Number Check Box as of TC#1");
		adminstrationStudiesCustomPage.checkRandomizationNumberCheckBox();

		reportLog("6.4.2: Configuration to remove mask from Screening Number, Randomization Number and Temporary ID as of TC#4");
		adminstrationStudiesCustomPage.inputScreeningRandomizationAndTempID("", "", "");

		reportLog("6.4.3: Switch to child browser tab");
		switchToChildWindow();

		reportLog("6.4.4: Verify add subject button is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("6.4.5: Click on add subject button");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN();

		reportLog("6.4.6: Verify New subject entry screen is displayed ");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("6.4.7: Verify Screening Number,Randomization Number and Temp Id displayed");
		studyNavigatorDashBoardPage.verifyScreeningNumberRandomizationTempIdIsDisplayed();

		reportLog("6.4.8: Select language");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("6.4.9: Verify Save button is disabled and Subject can't be created");
		studyNavigatorDashBoardPage.verifySaveButtonIsDisabled();

		reportLog("6.4.10: click on cancel button to close the add new subject popup");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("6.5: Logout application");
		loginPage.logoutApplication();

		reportLog("6.6: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
