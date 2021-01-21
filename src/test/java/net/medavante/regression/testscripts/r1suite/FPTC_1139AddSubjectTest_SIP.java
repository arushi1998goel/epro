package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.jfree.util.Log;
import org.testng.ITestResult;
import org.testng.annotations.*;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;
import net.medavante.portal.utilities.SubjectsModuleConstants;

public class FPTC_1139AddSubjectTest_SIP extends BaseTest {

	private String studyName;
	private String notesText = generateRandomString(3);
	private String screeningNumNotToBeSaved = "0_Auto_" + generateRandomString(5),
			screeningNum = "Automation_" + generateRandomString(4);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1139AddSubjectTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AddSubjectStudy");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id:FPTC_1139 Test Case Name:Add Subject
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1139_Add Subject", groups = { "R1" })

	public void FPTC_1139_verifyAddSubject() {
		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("1.2: Verify User Login successful");
		dashBoardPage.verifyMedavantePortalPage();


		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.3: Select configured study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("1.4: Verify Option to add Subject is available");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("2.1: Select an action to add Subject by selecting the site");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);

		reportLog("2.2: Verify New Subject entry screen is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("2.3: Verify Required fields are highlighted​");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("3.1: Enter not all required Subject data i.e notes");
		studyNavigatorDashBoardPage.inputNotes(notesText);

		reportLog("3.2: Verify Data are entered ");
		studyNavigatorDashBoardPage.verifyNotesTextIsEntered(notesText);

		reportLog("3.3: Verify Required fields are highlighted​");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("3.4: Verify Save options is not enabled​");
		studyNavigatorDashBoardPage.verifySaveButtonIsDisabled();

		reportLog("4.1: Enter a number on Screening# field​");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);

		reportLog("4.2: Verify Text box updates to show requirement has been met");
		studyNavigatorDashBoardPage.verifyScreeningNumIsEntered(screeningNum);

		reportLog("5.1: Delete the number from Screening# field");
		studyNavigatorDashBoardPage.clearScreeningInp();

		reportLog("5.2: Verify Screening#  and temp field is highlighted and shows required​");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("6.1: Enter required Subject data");
		studyNavigatorDashBoardPage.inputNotes(notesText);

		reportLog("6.2: Verify Save options is not enabled");
		studyNavigatorDashBoardPage.verifySaveButtonIsDisabled();

		reportLog("7.1: Enter correct number on Screening# field");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNum);

		reportLog("7.2: Verify Text box updates to show requirement has been met");
		studyNavigatorDashBoardPage.verifyScreeningNumIsEntered(screeningNum);

		reportLog("7.3:Temp id is locked for filling");
		studyNavigatorDashBoardPage.verifyTempIdLockedForFilling();

		reportLog("8.1: Fill Initials for Subject ");
		studyNavigatorDashBoardPage.fillSubjectInitials(generateRandomString(4));

		reportLog("8.2: Fill language from the configured language list");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("8.3: Fill Randomization# for Subject");
		studyNavigatorDashBoardPage.inputRandomizationNum(generateRandomAlphanumericString(4));

		reportLog("8.4:Fill the Date of Birth for Subject");
		studyNavigatorDashBoardPage.fillDateOfBirth(Constants.Date,Constants.Month,Constants.Year);

		reportLog("8.5:Fill Gender for Subject");
		studyNavigatorDashBoardPage.selectGender(SubjectsModuleConstants.gender);

		reportLog("8.6: Verify Data entered into Subject entry form are validated");
		studyNavigatorDashBoardPage.verifyDateOfBirth(Constants.Date,Constants.Month,Constants.Year);
		studyNavigatorDashBoardPage.verifyLanguageIsSelected(studyLanguage);

		reportLog("8.7: select an action to save");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();

		reportLog("8.8: Verify New Subject record is created ");
		subjectDetailPage.verifySubjectDetailAndScreeningIsDisplayed(screeningNum);

		reportLog("8.9: Verify List of available visits is displayed along with option to add Subject visit");
		subjectDetailPage.verifyVisitGridDisplayed();

		reportLog("8.10: Verify Option to edit Subject details is available");
		subjectDetailPage.verifySubjectEdtingIconIsDisplayed();

		reportLog("9.1: Navigate Back to subject listing page.");
		subjectDetailPage.navigateBackFromSubjectDetailsPage();

		reportLog("9.1: Select an action to add Subject and select site to create Subject for again");
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);

		reportLog("9.2: Verify New Subject entry screen is displayed");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();

		reportLog("9.3: Verify Required fields are highlighted​");
		studyNavigatorDashBoardPage.verifyRequiredFieldsAreHighlighted​();

		reportLog("10.1​: Enter Screening Num");
		studyNavigatorDashBoardPage.inputScreeningNum(screeningNumNotToBeSaved);

		reportLog("10.2​: Select language from drop down options");
		studyNavigatorDashBoardPage.selectSubjectLanguage(studyLanguage);

		reportLog("10.​3: select an action to cancel");
		studyNavigatorDashBoardPage.clickOnCancelBTN();

		reportLog("10.4: ​Verify New Subject entry screen is closed");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsNotDisplayed();

		reportLog("10.5: Verify User redirected back to Subjects Listing screen");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("10.6: Verify New Subject record is not added");
		studyNavigatorDashBoardPage.verifySubjectIsNotDisplayedInSubjectList(screeningNumNotToBeSaved);

		reportLog("10.7: User Logout the application");
		loginPage.logoutApplication();

		reportLog("10.8: Verify User Logout the application");
		loginPage.verifyUserLogout();

	}

	@AfterMethod
	public void updateSubjectValueInPropertiesFile(ITestResult result) {
		if (ITestResult.SUCCESS == result.getStatus()) {
			Configuration.updatePropertyTestData("RegressionTestData", "AutomationSubjectName", screeningNum);
		} else {
			Log.error(screeningNum + " subject is not added for " + studyName + " study.");
		}
	}
}