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

public class FPTC_736_VerifySubjectProfileMyAccountParticipant_SIP extends BaseTest {
	
	private String pendingQuestionnairesValue, nextQuestionnairesValue, unreadMessages, 
	recentMessages, nextVisit, deviceID, appVersion, deviceModel;
	
	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_736_VerifySubjectProfileMyAccountParticipant_SIP(String driver) {
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
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

		reportLog("MobileView As a Participant logged into the application");
		mobileLoginPage = androidSetUp();

		reportLog(
				"MobileView Application launch and verify Register screen with instruction message,register the subject");
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
	 * Test Case Id: FP-TC-736: Test Case Name: To verify that the user is able to found and see (not edit) information aboutt their profile and the device. 
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-736_To verify that the user is able to found and see (not edit) information aboutt their profile and the device.", groups = { "Mobile" })
	public void FPTC_736_VerifySubjectProfileMyAccountParticipant() throws Exception {

		reportLog("1.1: MobileView: Signin page shall be displayed with an instruction message.");
		mobileLoginPage.verifySignInScreenDisplayed();
		
		reportLog("1.2: MobileView Enter valid pin and then click on submit button.");
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);
				
		reportLog("1.1: MobileView The Home Page shall be displayed for the user. Based on the number of already configured:");
		dashborad.verifyHomePageDisplay();

		reportLog("1.2: MobileView Check Pending Questionnaire[s]");
		pendingQuestionnairesValue = dashborad.getPendingQuestionnairesValueFromHomeScreen();
		dashborad.verifyHomeDashbaordValues("Pending", pendingQuestionnairesValue);
		
		reportLog("1.2.1: MobileView Check Next Questionnaire[s]");
		nextQuestionnairesValue = dashborad.getNextQuestionnairesValueFromHomeScreen();
		dashborad.verifyHomeDashbaordValues("Next", nextQuestionnairesValue);
		
		reportLog("1.2.2: MobileView Check Unread Message[s]");
		unreadMessages = dashborad.getUnreadMessagesValueFromHomeScreen();
		dashborad.verifyHomeDashbaordValues("Unread", unreadMessages);		
		
		reportLog("1.2.3: MobileView Check Recent message[s]");
		recentMessages = dashborad.getRecentMessagesValueFromHomeScreen();
		dashborad.verifyHomeDashbaordValues("Recent", recentMessages);		

		reportLog("1.2.4: MobileView Check Next Visit");
		nextVisit = dashborad.getNextVisitValueFromHomeScreen();
		dashborad.verifyHomeDashbaordValues("Next", nextVisit);
				
		reportLog("1.3: MobileView Check top menu configured with home,questionnaries,message,log an event");
		dashborad.verifyTopMenuOptions();
				
		reportLog("2: MobileView Tap to 'Side Menu' Icon");
		sideMenu=dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
				
		reportLog("2.2: MobileView select 'My Account'");
		sideMenu.clickOnMyAccount();

		reportLog("2.3: MobileView verify the Subject Profile details");
		sideMenu.verifyPageTitle("My Account");
		
		reportLog("3.1: User shall be able to only view (not edit) all subject related information on Subject Profile Screen (My Account):");
		reportLog("3.2: MobileView Verify Subject ID (Patient) ");
		sideMenu.verifyTextOnScreen(screeningNum);		
		
		reportLog("3.2: MobileView Verify Registration ID (Patient) ");
		sideMenu.verifyTextOnScreen(registrationCode);	
		
		reportLog("3.2: MobileView Verify Registration Date (Patient) ");
		sideMenu.verifyTextOnScreen(currentDate().toUpperCase());
		
		reportLog("3.2: MobileView Verify QR Code (Patient)");
		sideMenu.verifyQRCodeOnScreen();
		
		reportLog("3.2: MobileView Verify Application Version of the installed application");
		appVersion=sideMenu.getAppVersion();
		sideMenu.verifyTextOnScreen(appVersion);	
				
		reportLog("3.2: MobileView Verify Device ID (IMEI) ");
		deviceID=sideMenu.getDeviceIDValue();
		sideMenu.verifyTextOnScreen(deviceID);	
		
		reportLog("3.3: MobileView Verify Device Manufacturer & Model");
		deviceModel=sideMenu.getDeviceModel();
		sideMenu.verifyTextOnScreen(deviceModel);	
		
		reportLog("3.4: MobileView Avatar associated to the device (config for Patient)");
		sideMenu.verifyAvatarAndVersionLabelIsDisplay();
		
		reportLog("3.5: MobileView Click on back icon");
		sideMenu.clickOnBackIcon();
		
		reportLog("3.6: MobileView Exit Application");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		sideMenu.clickOnExitApplication();
		sideMenu.verifyApplicationExit();
		clickOnConnectAppIcon();
	}
	
	@AfterMethod
	public void updateSubjectValueInPropertiesFile(ITestResult result) throws InterruptedException {
		if(ITestResult.SUCCESS==result.getStatus()) {
			
			reportLog("Deactivate Subject");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
					Constants.NavigateText, Constants.StudyText);
			
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
