package net.medavante.mobile.testscripts;

import java.util.Properties;

import org.jfree.util.Log;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_931_VerifyMedicationsAddDeleteEditForParticipantOnPortalSide_SIP extends BaseTest {

	private String medication1 = "Medication1" + generateRandomString(3),
			medication2 = "Medication2" + generateRandomString(3);
	
	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_931_VerifyMedicationsAddDeleteEditForParticipantOnPortalSide_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		
		reportLog("Go to Portal Side to Create Subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10,
				screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Get the Registration Code From The Subject");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
		subjectDetailPage.clickOnSubjectRegistrationPopUpCloseButton();

		reportLog("1.1: MobileView As a Participant logged into the application");
		mobileLoginPage = androidSetUp();

		reportLog(
				"1.2: MobileView Application launch and verify Register screen with instruction message,register the subject");
		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
		mobileLoginPage.clickOnEnterTheCode();
		
		mobileLoginPage.enterTheRegistrationCode(registrationCode);
		mobileLoginPage.clickOnSubmitButton();
		mobileLoginPage.clickOnContinueButton();
		mobileLoginPage.verifyTermsAndConditionPageIsDisplay(termsAndCondtionInstructionMessage);
		mobileLoginPage.clickOnAcceptBtn();

		mobileLoginPage.verifyInstructionMessageText(createIdentityInstructionMessage);
		mobileLoginPage.enterPINCode(Mobile_Pin);
		mobileLoginPage.enterConfirmPINCode(Mobile_Pin);

		mobileLoginPage.clickOnNextButton();

		mobileLoginPage.verifyInstructionMessageText(createIdentityQuestionMessage);
		mobileLoginPage.verifyChooseAQuestionDisplay();

		mobileLoginPage.chooseAQuestion(Choose_QuestionPin);
		mobileLoginPage.enterAnAnswer(Choose_QuestionAnswer);
		mobileLoginPage.clickOnNextButton();
		mobileLoginPage.clickOnContinueButton();
		
		


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

	@Test(description = "FP-TC-931_Medications - Add/Delete/Edit Medications Online/Offline and display their detail (Time Zone) for the Participant on Portal Side", groups = {
			"Mobile" })
	public void FPTC_931_verifyMedicationsAddDeleteEditForParticipantOnPortalSide() throws Exception {

		reportLog("1: MobileView Logged in user is the Participant");
		
		reportLog("1.1: MobileView: Verify Sign-In screen display");
		mobileLoginPage.verifySignInScreenDisplayed();
		
		reportLog("1.2: MobileView: Login to the app with valid PIN");
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);

		reportLog("2: MobileView As a logged in participant, tap to 'Side Menu' Icon and select 'Medications'");
		dashborad.verifyHomePageDisplay();
		
		reportLog("2.1: MobileView: click on Humberger Menu");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		
		reportLog("2.2: MobileView: Click on Medications");
		sideMenu.clickOnMedications();
		
		reportLog("2.3: MobileView: Verify Medications page display");
		sideMenu.verifyPageTitle("Medications");

		reportLog("2.4: MobileView At first the user is able to view only an empty Medications List with: 'No Items' text displayed");
		sideMenu.verifyTextOnScreen("No Items");

		reportLog("2.5: MobileView At first the user is able to view only an empty Medications List with: 'Add Medication' Button");
		sideMenu.verifyAddMedicationButtonDisplay();

		reportLog("3: MobileView Tap to 'Add Medication' button and add at least 2 new medication (Medication 1 and Medication 2) then Save them.");
		sideMenu.createMedication(medication1);
		
		reportLog("3.1: MobileView: click on Back icon");
		sideMenu.clickOnBackIcon();

		reportLog("3.2: MobileView: Navigate to Medications");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnMedications();
		sideMenu.verifyAddMedicationButtonDisplay();
		
		reportLog("3.3: MobileView: Create second Medication");
		sideMenu.createSecondMedication(medication2);

		reportLog("3.4: MobileView Then see the Medication list containing both newly saved Medications");
		sideMenu.clickOnBackIcon();
		
		reportLog("3.5: MobileView: Exit from the app");
		sideMenu.exitApplication();
		clickOnConnectAppIcon();
		
		reportLog("4: Go to Portal and verify the medication history for the participant(Current Medications listed under 'Current' Sub-tab)");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("4.1: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("4.2: Navigate to Medication Listing");
		studyNavigatorDashBoardPage.navigateToMedicationListing();

		reportLog("4.3: Verify that both newly saved Medications are listed there with their details including Date & Time");
		studyNavigatorDashBoardPage.searchMedication(medication1);

		reportLog("5: Click to Medication 2 on Portal Side and verify its detail including Date & Time");
		studyNavigatorDashBoardPage.searchMedication(medication2);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		
		reportLog("6: MobileView: Reopen the app");
		mobileLoginPage = androidSetUp();
		
		reportLog("6.1: MobileView: Verify Sign In page display");
		dashborad=mobileLoginPage.loginUser(Mobile_Pin);
		dashborad.clickOnHomeTab();

		reportLog("6.3: MobileView Navigate to Medication page");
		sideMenu=dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnMedications();
		sideMenu.verifyPageTitle("Medications");

		reportLog("6.4: MobileView delete First Medication");
		sideMenu.selectMedicationListItem(medication1);
		sideMenu.clickOnDeleteIcon();
		sideMenu.selectConfirmationOption("OK");

		reportLog("6.5: MobileView: Exit from the app");
		sideMenu.clickOnBackIcon();
		sideMenu.exitApplication();	
		clickOnConnectAppIcon();
		
		reportLog("7: MobileView Go back to Portal and refresh the Subject Detail Page then verify the medication history for the participant");
		studyNavigatorDashBoardPage.navigateToMedicationListing();
		studyNavigatorDashBoardPage.searchMedication(medication1);
		studyNavigatorDashBoardPage.clickOnFirstCell();

		reportLog("8: Still on Portal Side click to Deleted Medications listed under 'Deleted' Sub-tab");
		studyNavigatorDashBoardPage.verifyMedicationLastActionStatus("DELETED");
		
		reportLog("8.1: Verify Medication last Action status and close the Medication pop up");
		studyNavigatorDashBoardPage.clickOnMedicationCloseButton();
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
		reportLog("9: MobileView: Go back to device and reopen the app");
		clickOnConnectAppIcon();
		mobileLoginPage.reopenApp();
	
		reportLog("9.1: MobileView User is able to sign in successfully to the application.");
		dashborad=mobileLoginPage.loginUser(Mobile_Pin);
		dashborad.verifyHomePageDisplay();
		
		reportLog("9.2: MobileView Navigate to Medication page");
		sideMenu=dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnMedications();
		sideMenu.verifyPageTitle("Medications");

		reportLog("9.3: MobileView delete Medication 2");
		sideMenu.selectMedicationListItem(medication2);
		sideMenu.clickOnDeleteIcon();
		sideMenu.selectConfirmationOption("OK");
		sideMenu.clickOnBackIcon();

		reportLog("9.4: MobileView exit from the app");
		sideMenu.clickOnBackIcon();
		sideMenu.exitApplication();
		clickOnConnectAppIcon();
	}
	
	@AfterMethod
	public void updateSubjectValueInPropertiesFile(ITestResult result) throws InterruptedException {
		if(ITestResult.SUCCESS==result.getStatus()) {
			
			reportLog("Deactivate Subject");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
					Constants.NavigateText, Constants.StudyText);
		//	studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
			
			studyNavigatorDashBoardPage
					.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, screeningNum);
			subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
			subjectDetailPage.deactivateSubjectConfiguration(SuperAdminUN, SuperAdminPW);
			loginPage.logoutApplication();
			loginPage.verifyUserLogout();
			
			
		}else {
			Log.error(subjectName + " subject is not added for " + studyName+ " study.");
		}
	}

}
