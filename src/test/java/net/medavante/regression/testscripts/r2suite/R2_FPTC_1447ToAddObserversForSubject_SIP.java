package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;
import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_1447ToAddObserversForSubject_SIP extends BaseTest {

	Random rand = new Random();
	int n = rand.nextInt(10) + 1;
	protected String subjectName = "SUBJ_" + generateRandomString(5);
	protected String crStudyName, ObsROAss1, ObsROAss2;
	protected String Observer1 = "Ob1" + n;
	protected String Observer2 = "Ob2" + n;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1447ToAddObserversForSubject_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {

		Properties prop = Configuration.readTestData("RegressionTestData");
		crStudyName = prop.getProperty("AutomationStudyName");
		System.setProperty("className", getClass().getSimpleName());
		ObsROAss1 = prop.getProperty("Auto_Paper_OBSRO_Visit1");
		ObsROAss2 = prop.getProperty("Auto_Paper_OBSRO_Visit2");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC_1447 Test Case Name: To add observers for subject Check
	 * that if subject has only one active observer, he is automatically assigned to
	 * all ObsRO forms in visit
	 * 
	 * pre-qualification : 1. At least one subject exists with the following: 1.1.
	 * Subject doesn't have any observers 1.2. Subject has at least one visit with
	 * configured ObsRO forms 2. At least one subject exists with the following:
	 * 2.1. Subject already has at least one active observer 2.2. Subject has at
	 * least one visit with configured ObsRO forms
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "_FPTC_1447 --To add observers", groups = { "R2" })
	public void FPTC_1447verifyToAddObserversForSubject() throws Exception {

		reportLog("1. Navigate to Subject Details of the Subject identified in prerequisite");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.1: Select Study");
		studyNavigatorDashBoardPage.selectStudy(crStudyName, Constants.ATAssignedRater_10);

		reportLog("1.2: Add new Subject by control 'Add New Subject'");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();
		studyNavigatorDashBoardPage.clickOnAddSubjectBTN(Constants.ATAssignedRater_10);

		reportLog("1.4: Fill all required fields");
		studyNavigatorDashBoardPage.verifyAddSubjectPopUpIsDisplayed();
		studyNavigatorDashBoardPage.inputNotes(Constants.NoteText);
		studyNavigatorDashBoardPage.inputScreeningNum(subjectName);
		studyNavigatorDashBoardPage.selectSubjectLanguage(Constants.subjectLanguage);

		reportLog("1.5: Save the changes");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2: Select the control to edit Reported Outcomes");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("2.1: Reported Outcome Details screen is displayed ");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("2.2: Control to Add new Observer is available");
		subjectDetailPage.verifyAddObserverBtnIsDisplayed();

		reportLog("3: Select an action to add new Observer");
		subjectDetailPage.clickOnAddObserverBtN();

		reportLog("3.1: Required fields are highlighted.");
		subjectDetailPage.verifyObserverRelationAnAliasFieldsAreRequired();

		reportLog("3.2: Save (disabled) and Cancel options are available");
		subjectDetailPage.verifyObserverSaveButtonIsDisabledAndCancelEnable();

		reportLog("4: Fill in all required fields");
		subjectDetailPage.inputObserverRelationName(Observer1);
		subjectDetailPage.inputObserverAliasName(Observer1);
		subjectDetailPage.clickOnObserverSaveBTN();

		reportLog("4.1: Save and Cancel controls are enabled");
		subjectDetailPage.verifyObserverSaveAndCancelButtonAreEnable();

		reportLog("5: Select an action to cancel");
		subjectDetailPage.closeReportedOutComePopup();

		reportLog("6: Click on Add new observer again. Fill all required fields and Save changes");
		subjectDetailPage.clickOnReportedOutComeButton();
		subjectDetailPage.configureObserver(Observer1, Observer1);

		reportLog("6.1: Added Observer is saved and displayed");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		reportLog("7: ObsRO forms in visit are automatically assigned to Observer that was added in Step#5");
		subjectDetailPage.clickOnVisitRow(ObsROAss1);
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("7.1: Verify rater value in dropdown");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Observer1);

		reportLog("8: Navigate to subject grid and select the subject identified in the prerequisite #2");
		dashBoardPage = subjectDetailPage.selectHorizontalUpperNavMenuItem(MedAvantePortalPage.class,
				Constants.HomeNavText);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("8.1: Subject Details are displayed ");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("8.2: Existing observers are displayed ");

		reportLog("8.3: Control to edit Reported Outcomes section is available");
		subjectDetailPage.verifyReportedOutComeButtonIsDisplayed();

		reportLog("9: Select the control to edit Reported Outcomes");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("9.1: Reported Outcome Details screen is displayed ");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();

		reportLog("9.2: Control to Add new Observer is available");
		subjectDetailPage.verifyAddObserverBtnIsDisplayed();

		reportLog("10: Select an action to add new Observer");
		subjectDetailPage.clickOnAddObserverBtN();

		reportLog("10.1: Required fields are highlighted.");
		subjectDetailPage.verifyObserverRelationAnAliasFieldsAreRequired();

		reportLog("10.2: Save (disabled) and Cancel options are available");
		subjectDetailPage.verifyObserverSaveButtonIsDisabledAndCancelEnable();

		reportLog("11: Fill all required fields and Save changes");
		subjectDetailPage.inputObserverRelationName(Observer2);
		subjectDetailPage.inputObserverAliasName(Observer2);
		subjectDetailPage.clickOnObserverSaveBTN();

		reportLog("11.1: Added observer is saved and displayed in the list of Observers");
		subjectDetailPage.verifyObserverGridIsDisplayedWithListOfObserver();
		subjectDetailPage.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(Observer2);

		reportLog("11.2: Click on Save changes");
		subjectDetailPage.clickOnReportedOutComePopUpSaveBTN();

		reportLog("12: On visit list select an action to add visit identified in prerequisite #2.2");
		subjectDetailPage.clickOnVisitRow(ObsROAss2);
		subjectDetailPage.clickOnAddVisitIcon();

		reportLog("12.1: ObsRO forms in visit are not automatically assigned to Observer that was added in Step#9");
		subjectDetailPage.verifyRaterAssignmentDropDownSelectedValue(Constants.notAssignedText);

		reportLog("12.2: The list with the all available Observers is available");
		subjectDetailPage.clickOnAssignRaterDropDown();
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Observer1);
		subjectDetailPage.clickOnAssignRaterDropDown();
		subjectDetailPage.verifyRaterNameDisplayedInRaterDropDown(Observer2);

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
