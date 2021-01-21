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

public class FPTC_835_MenuApplyApplicationPreferencesButtons_SIP extends BaseTest {

	private String medication = "Medication" + generateRandomString(2);

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_835_MenuApplyApplicationPreferencesButtons_SIP(String driver) {
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
		System.out.println(screeningNum);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Get the Registration Code From The Subject");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
		Configuration.updatePropertyTestData("RegressionTestData", "SubjectRegistrationCode",
				registrationCode);
		subjectDetailPage.clickOnSubjectRegistrationPopUpCloseButton();
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();		

		reportLog("1.1: MobileView As a Participant logged into the application");
		mobileLoginPage = androidSetUp();

		reportLog("1.2: MobileView Application launch and verify Register screen with instruction message,register the subject");
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
	 * Test Case Id: FP-TC-835 Test Case Name: Menu - Apply application
	 * preferences - Buttons (Participant)
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-835_Menu - Apply application preferences - Buttons (Participant)", groups = { "Mobile" })
	public void FPTC_835_MenuApplyApplicationPreferencesButtons() throws Exception {

		reportLog("1: MobileView Launch the application and Login as a participant");
		mobileLoginPage.verifySignInScreenDisplayed();
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);

		reportLog("2: MobileView The Splash screen Virgil Pro text label shall be displayed");
		dashborad.verifyHomePageDisplay();

		reportLog(
				"2.1: MobileView The Home Screen shall be displayed for the user with its detail as per configuration");
		dashborad.clickOnHomeTab();
		mobileLoginPage.verifyTextOnScreen(welcomeMessage);

		reportLog("3:MobileView Tap to Side Menu Icon");
		sideMenu = dashborad.clickOnHumBergerMenuAndOpenLeftPanel();

		reportLog("3.1:MobileView Select Settings");
		sideMenu.clickOnSettings();

		reportLog("3.2:MobileView Settings details shall be displayed for the user with its details");
		sideMenu.verifyPageTitle("Settings");
		
		reportLog("3.3: MobileView click on Back icon");
		sideMenu.clickOnBackIcon();
		
		reportLog("3.4: MobileView: Verify home page display");
		sideMenu.verifyHomePageDisplay();

		reportLog("4:MobileView Set the following:");
		reportLog("5:MobileView Tap to Questionnaires Tab then tap to Not Started and verify the following: ");
		dashborad.clickOnQuestionnairesTab();

		reportLog(
				"6:MobileView Tap to Log An Event Tab then tap to one of the listed Log an Event[s] and verify the following");
		logAnEvent = dashborad.clickOnLogAnEventTab();

		reportLog("7:MobileView Tab to Messages Tab and verify the following:");
		dashborad.clickOnMessageTab();

		reportLog("8:MobileView Tap to Side Menu Icon and select Medication and verify the following");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		
		reportLog("8.1: MobileView: Click on Medications option");
		sideMenu.clickOnMedications();

		reportLog("9:MobileView Tap to Add Medication Button");
		sideMenu.clickOnAddIcon();
		
		reportLog("9.1: MobileView: Enter the Medication Name");
		sideMenu.enterNameAndClickOnSave(medication);
		
		reportLog("9.2: MobileView: Click on back icon");
		sideMenu.clickOnBackIcon();

		reportLog("10: MobileView Navigate back and Tap to Side Menu Icon");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();

		reportLog("10.1: MobileView select Exit Application");
		dashborad.clickOnExitApplication();

		reportLog("10.3: MobileView Launch the application again");
		mobileLoginPage.reopenApp();
		
		reportLog("11.1: MobileView User is able to sign in successfully to the application.");
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);

		reportLog("11.2: MobileView: Click on Home tab and verify home page");
		dashborad.clickOnHomeTab();
		dashborad.verifyHomePageDisplay();

		reportLog("12: MobileView: Naviagate to Medication page");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnMedications();

		reportLog("13: MobileView: Delete the medication with cancel option");
		sideMenu.selectMedicationFromList(medication);
		
		reportLog("13.1: MobileView: click on Delete option");
		sideMenu.clickOnDeleteIcon();
		
		reportLog("13.2:MobileView: Verify that confirmation dialog display");
		sideMenu.verifyConfirmationDialogDisplayWithOkAndCancelOptions();
		
		reportLog("13.3: MobileView: Click on Cancel option");
		sideMenu.clickOnCancelOption();

		reportLog("14: MobileView: Delete Medication with Ok selection");
		sideMenu.clickOnDeleteIcon();
		
		reportLog("14.1:MobileView: Verify that confirmation dialog display");
		sideMenu.verifyConfirmationDialogDisplayWithOkAndCancelOptions();
		
		reportLog("14.2: MobileView: Click on OK option");
		sideMenu.clickOnOKOption();
		
		reportLog("14.3:MobileView: click on back icon");
		sideMenu.clickOnBackIcon();

		reportLog("15: MobileView Click on exit icon and close the application");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		
		reportLog("15.1: MobileView: click on exit");
		dashborad.clickOnExitApplication();
		clickOnConnectAppIcon();
		

	}

	
	@AfterMethod
	public void updateSubjectValueInPropertiesFile(ITestResult result) throws InterruptedException {
		if(ITestResult.SUCCESS==result.getStatus()) {
			
			reportLog("Deactivate Subject");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
					Constants.NavigateText, Constants.StudyText);
			//studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
			
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
