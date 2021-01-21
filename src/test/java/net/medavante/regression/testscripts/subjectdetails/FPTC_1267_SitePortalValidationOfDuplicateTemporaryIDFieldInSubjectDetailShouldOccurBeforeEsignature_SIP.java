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
import net.medavante.portal.utilities.SubjectsModuleConstants;

public class FPTC_1267_SitePortalValidationOfDuplicateTemporaryIDFieldInSubjectDetailShouldOccurBeforeEsignature_SIP
		extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1267_SitePortalValidationOfDuplicateTemporaryIDFieldInSubjectDetailShouldOccurBeforeEsignature_SIP(
			String browser) {
		super(browser);
	}

	@BeforeMethod
	public void testSetUp() throws Exception {

		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");
		Subject01 = properties.getProperty("Subject_TempId01");
		Subject02 = properties.getProperty("Subject_TempId02");

	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-1267 Test Case Name: Site Portal: Site Portal:
	 * Validation of duplicate 'TemporaryID' field in subject detail should
	 * occur before e-signature.
	 * =========================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1267_Site Portal: Validation of duplicate 'TemporaryID' field in subject detail should occur before e-signature.", groups = {
			"" })
	public void FPTC_1267_SitePortalValidationOfDuplicateTemporaryID() {

		reportLog("1.2: Login to Site Portal");
		dashBoardPage =loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("1.3: Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.4: Navigate to study dashboard");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
		
		reportLog("1.5: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.5: Verify Existence of Subject #1");
		studyNavigatorDashBoardPage.verifyExistingOfSubjectNo(Subject01);

		reportLog("2.1: Select subject #2");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(Subject02);

		reportLog("2.2: Verify Subject details page is opened");
		subjectDetailPage.verifyTempIdIsDisplayed(Subject02);

		reportLog("2.3: Verify Subject Edit Btn is enable");
		subjectDetailPage.verifySubjectEditBtnIsDisplayed();

		reportLog("2.4: Click on Subject details edit icon");
		subjectDetailPage.clickOnSubjectEdtingIcon();

		reportLog("3.1: Update Temporary ID in Edit subject modal window");
		subjectDetailPage.inputAutogenEncriptedTemporaryID(Subject01);

		reportLog("3.2: Click Edit subject Save Button");
		subjectDetailPage.clickOnSaveBtn();

		reportLog("3.3: Verify appeared error message");
		subjectDetailPage.verifyErrorMessage(SubjectsModuleConstants.editDuplicateTemporaryIDErrorMessage);

		reportLog("3.4:Click on Close message");
		subjectDetailPage.closeErrorMessage();

		reportLog("3.5: Verify 'Subjects' page is in the Edit mode");
		subjectDetailPage.verifySubjectEdtingPopUpDisplayed();

		reportLog("3.6: Click on cancel button to close the edit screening popup");
		subjectDetailPage.clickOnCancelBtn();

		reportLog("3.7: Verify Subject changes is not applied.");
		subjectDetailPage.verifyTempIdIsDisplayed(Subject02);

		reportLog("3.8: Logout application");
		loginPage.logoutApplication();

		reportLog("3.9: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

	}
}
