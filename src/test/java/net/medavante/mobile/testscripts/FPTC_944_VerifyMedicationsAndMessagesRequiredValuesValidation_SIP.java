package net.medavante.mobile.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_944_VerifyMedicationsAndMessagesRequiredValuesValidation_SIP extends BaseTest {

	private String medication1 = "Medication1" + generateRandomString(3);
	private String subject = "Test_7Jan1";
	private String message1 = "Message_" + generateRandomString(3);

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_944_VerifyMedicationsAndMessagesRequiredValuesValidation_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-931 Test Case Name: Messages - Medications -
	 * Add/Delete/Edit Medications Online/Offline and display their detail (Time
	 * Zone) for the Participant on Portal Side
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-944_Medications and Messages - Required Values validation", groups = { "Mobile" })
	public void FPTC_944_VerifyMedicationsAndMessagesRequiredValuesValidation() throws Exception {

		reportLog("1: MobileView Logged in user is the Participant");
		mobileLoginPage = androidSetUp();

		reportLog("1.1: MobileView: Verify Sign-In screen display");
		mobileLoginPage.verifySignInScreenDisplayed();

		reportLog("1.2: MobileView: Login to the app with valid PIN");
		dashborad = mobileLoginPage.loginUser(mobilePin);

		reportLog("2: MobileView As a logged in participant, tap to 'Side Menu' Icon and select 'Medications'");
		dashborad.verifyHomePageDisplay();

		reportLog("2.1: MobileView: click on Humberger Menu");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();

		reportLog("2.2: MobileView: Click on Medications");
		sideMenu.clickOnMedications();

		reportLog("2.3: MobileView: Verify Medications page display");
		sideMenu.verifyPageTitle("Medications");

		reportLog(
				"2.4: MobileView At first the user is able to view only an empty Medications List with: 'No Items' text displayed");
		sideMenu.verifyTextOnScreen("No Items");

		reportLog(
				"2.5: MobileView At first the user is able to view only an empty Medications List with: 'Add Medication' Button");
		sideMenu.verifyAddMedicationButtonDisplay();

		reportLog("3: MobileView Tap to 'Add Medication'");
		sideMenu.clickOnAddIcon();

		reportLog("3.1:  MobileView tap to 'Save' button leave all fields empty");
		sideMenu.clickOnSaveMedicationIcon();

		reportLog("3.2:MobileView User shall NOT be able to save Medication");
		sideMenu.verifyCreateMedicationIconDisplay();

		reportLog("3.3: MobileView 'Please fill in required fields' pop-up message displayed for a while.");
		//sideMenu.verifyTextOnScreen("Please fill in required fields");

		reportLog("4.1: MobileView Fill in the required (Mandatory) field");
		sideMenu.enterMedicationName(medication1);

		reportLog("4.2:  tap to Save Button.");
		sideMenu.clickOnSaveMedicationIcon();
		
		reportLog("4.3: MobileView: click on Back icon");
		sideMenu.clickOnBackIcon();

		reportLog("5: As a logged in participant, tap to 'Main Menu' 'Messages Tab'.");
		dashborad.clickOnHomeTab();
		dashborad.clickOnMessageTab();

		reportLog("5.1: Messages page should displayed");
		dashborad.verifyPageTitle("Messages");

		reportLog("6.1 : Tap to '+' Button");
		dashborad.verifyAddMessageButtonWhileNoDataAddedDisplay();
		dashborad.clickOnAddMessageButtonWhenNoDataPresent();

		reportLog("6.2: Message Composer shall be displayed.");
		dashborad.verifyPageTitle("Compose");

		reportLog(
				"6.3:  tap to 'Send' button without filling both subject text field and the body of the message empty");
		dashborad.clickOnSendMessageButton();

		reportLog(
				"6.4: Verify the Warning Message 'Message content can not be empty.' shall be displayed pointing to fill in");
		//dashborad.verifyWarningMessage("Message content can not be empty.");

		reportLog("7.1: Fill in the the 'Body' field then tap 'Send' Button");
		dashborad.enterMessageSubjectText(message1);
		dashborad.enterMessageBodyText(message1);
		dashborad.hideKeyboard();
		dashborad.clickOnSendMessageButton();

		reportLog("7.2: Exit from the app");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		dashborad.clickOnExitApplication();

		reportLog("8: Go to Portal and verify the following: ");
		reportLog("Login to the web portal as an Admin user");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("8.1: Go to Subject Detail page");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.searchSubject(subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subject);

		reportLog("8.2: Subject Detail page should display.");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("8.3: Select Medication");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Medication);

		reportLog(
				"8.4: under Medication, All medication history for participant shall be displayed with added medication details including Date & Time");
		subjectDetailPage.verifyMedicationPresentInRow(medication1);

		reportLog("8.5: Select Messages");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.Messages_Inbox);

		reportLog(
				"8.6: Under Messages, All Messages history for participant shall be displayed with added message with its details");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();
		subjectDetailPage.verifyPresenceOfMessageBySubject(message1);

		// delete medication and messages

		reportLog("9: MobileView: Reopen the app");
		androidSetUp();
		
		reportLog("9.1: MobileView: Verify Sign In page display");
		mobileLoginPage.verifySignInScreenDisplayed();

		reportLog("9.2: MobileView User is able to sign in successfully to the application.");
		mobileLoginPage.loginUser(mobilePin);
		dashborad.clickOnHomeTab();
		dashborad.verifyHomePageDisplay();

		reportLog("9.3: MobileView Navigate to Medication page");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnMedications();
		sideMenu.verifyPageTitle("Medications");

		reportLog("9.4: MobileView delete First Medication");
		sideMenu.selectMedicationListItem(medication1);
		sideMenu.clickOnDeleteIcon();
		sideMenu.selectConfirmationOption("OK");

		reportLog("9.5: MobileView: Exit from the app");
		sideMenu.clickOnBackIcon();

		reportLog("10.1: Move to message tab");
		dashborad.clickOnHomeTab();
		
		reportLog("10.3: MobileView exit from the app");
		sideMenu.exitApplication();

	}

}
