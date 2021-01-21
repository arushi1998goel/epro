package net.medavante.mobile.testscripts;

import java.util.Properties;

import org.apache.maven.model.plugin.ReportConfigurationExpander;
import org.jfree.util.Log;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import App.MultiLingual;
import App.PropReader;
import net.medavante.mobile.pages.MobileDashBoardPage;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class Handheld_Translation_SIP extends BaseTest {

	private String subjectName = "AutomationEpro" + generateRandomString(5), questionnairesValue;

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public Handheld_Translation_SIP(String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		
		String abc=	MultiLingual.locallang("Accept");
				
		System.out.println(abc);
		
//		PropReader.updateproprty("locale.country", "RU");
//		PropReader.updateproprty("locale.language", "ru");
//
//        String abc1=	MultiLingual.locallang("App.Home.001");
//		
//		System.out.println(abc1);
	
		
//		System.setProperty("className", getClass().getSimpleName());
//		Properties properties = Configuration.readTestData("RegressionTestData");
//		studyName = properties.getProperty("HandheldTranslationStudy");
//		visitName = properties.getProperty("EPROMandatoryVisit");
//
//		reportLog("Go to Portal Side to Create Subject and complete visit for displaying questionnaires");
//		dashBoardPage = loginPage.loginInApplication(FormUserName, Form_Password);
//		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
//				Constants.NavigateText, Constants.StudyText);
//		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.testSite,
//				subjectName);
//		System.out.println(subjectName);
//		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
//		subjectDetailPage.verifyNewSubjectDetailPage();
//		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
//		registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
//		subjectDetailPage.clickOnSubjectRegistrationPopUpCloseButton();
//		
//		
//		loginPage.logoutApplication();
//		loginPage.verifyUserLogout();	
		
		/* Subject Created Successfully */
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-794 Test Case Name: Questionnaires Navigation
	 * ====================================================================================================================
	 * 
	 * 
	 */

	@Test(description = "Accept Terms and Condition", groups = { "Mobile" })

	public void AcceptTermsConditions() throws Exception {

		reportLog("1.1:MobileView As a Participant logged into the application");
		mobileLoginPage = androidSetUp();
		
		reportLog(
				"1.2:MobileView Application launch and verify Register screen with instruction message,register the subject");
		mobileLoginPage.verifyInstructionMessageText(registerScreenInstruction);
		mobileLoginPage.configurationForRegisterTheSubject("4206-5CF2-4278-B9C9");
		
		reportLog("2: MobileView Create Identity' screen is displayed with instruction message.");
		mobileLoginPage.verifyTextOnScreen();
		mobileLoginPage.verifyInstructionMessageText(createIdentityInstructionMessage);
		
		reportLog("2.1: MobileView Pin and Confirm Pin edit boxes shall be displayed.");
		mobileLoginPage.verifySignInScreenWithPINEditBox();
		
		reportLog("2.2: Click I forgt PIN link.");
		mobileLoginPage.clickOnForgetPINLink();
		
		
		
		
		reportLog("13: MobileView - Select ‘Questionnaires’ tab. Questionnaires page is displayed.");
		questionnairesPage = dashborad.clickOnQuestionnairesTab();
		
		reportLog("14: Mobile View - Select the questionnaire");
		questionnairesPage.clickOnFirstQuestionnairesInTheList();
		
		reportLog("15: Mobile View - Select ‘Start’ button and The questionnaire is displayed to complete.");
		questionnairesPage.clickOnStartQuestion();
		
		reportLog("16: Mobile View - Thank you message is displayed for completing the questionnaire along with continue option.");
		//questionnairesPage.
		
		
		reportLog("21: MobileView - Select ‘Messages’ and refresh");
		messgaePage = dashborad.clickOnMessageTab();
		
		reportLog("23: MobileView - Refresh Messages screen. ‘{0} new messages’ pop-up message is displayed if there’s a new message.");
		messgaePage.verifyMessageListPresent();
		
		reportLog("24: MobileView - Select a ‘Messages’. User is navigated to the ‘Messages’ page and message is displayed with subject and message body and time stamp.");
		messgaePage.verifyMessageListPresent();
		
		reportLog("25: MobileView - Select (+) compose icon. ‘Compose’ message page is displayed.");
		messgaePage.clickOnComposeMessageIcon();
		messgaePage.verifyComposeMsgScreen();
		
		reportLog("26: MobileView - Type ‘abcd’ in the message body and select back icon. Exit warning message is displayed along with yes/no options.");
		messgaePage.inputTxtInMsgSubjectField("abcd");
		messgaePage.clickOnBackbtnOnComposeScreen();
		messgaePage.verifyExitPopUp();
		
		reportLog("27.1. MobileView - Select ‘No’.");
		messgaePage.clickOnNoOnExitPopUp();
		
		reportLog("27.2. MobileView - The user stays in Compose message page.");
		messgaePage.verifyComposeMsgScreen();
		
		reportLog("28.1. MobileView - Select ‘Yes’.");
		messgaePage.clickOnYesOnExitPopUp();
		
		reportLog("28.2. MobileView - The user is navigated back to the Messages page.");
		messgaePage.verifyMessageListPresent();
		
		reportLog("29. MobileView - Select (+) compose icon and write ‘abcd’ in the subject and select send icon. Message content cannot be empty text is displayed.");
		messgaePage.clickOnComposeMessageIcon();
		messgaePage.verifyComposeMsgScreen();
		messgaePage.inputTxtInMsgSubjectField("abcd");
		messgaePage.clickOnSendMsgBtn();
		
		reportLog("30.1: MobileView - Select (+) compose icon and write ‘abcd’ in the subject and ‘efgh’ in the body and select send icon.");
		messgaePage.inputTxtInMsgSubjectField("abcd");
		messgaePage.inputTxtInMsgBodyField("efgh");
		messgaePage.clickOnSendMsgBtn();
		
		reportLog("30.2: MobileView - Sending message pop-up message is displayed and subsequently Message successfully sent pop-up message is displayed and the user is navigated back to the Messages page. ");
		messgaePage.verifyMessageListPresent();
		
		reportLog("31: MobileView - Select a message from the message list and select the delete icon ");
		messgaePage.clickDeletebtnOnMessageListScreen();
		
		reportLog("33: MobileView - Select a message and select the reply icon");
		messgaePage.clickReplyIcon();
		messgaePage.verifyReplyInputAndSendButton();
		
		reportLog("34.1: MobileView - Select back icon");
		messgaePage.clickOnBackbtnOnComposeScreen();
		
		reportLog("34.2: MobileView - Verify that Exit warning message is displayed along with yes/no options");
		messgaePage.verifyWarningpopupWithButtons();
		
		reportLog("37: MobileView - Select ‘Log an Event");
		logAnEvent= dashborad.clickOnLogAnEventTab();
		
		reportLog("38: MobileView- Select an event’");
		logAnEvent.clickOnEvent("EQ-5D-5L");
		
		reportLog("39: MobileView- Select Start");
		logAnEvent.clickStartBtn();
		
		reportLog("40: MobileView- Complete the Event");
		logAnEvent.clickStartBtn();
		
		reportLog("41: MobileView- Complete the Event");
		logAnEvent.clickContinueBtn();
		
		
		//		reportLog("2.2: MobileView Next button shall be displayed in disabled state");
//		mobileLoginPage.verifyNextButtonIsDisabled();
//		
//		reportLog("2.3: MobileView Avatar and Participant version label shall be displayed");
//		mobileLoginPage.verifyAvatarAndVersionLabelIsDisplay();
//		
//		reportLog("3: MobileView Enter different identity. ( pin and non-matching confirm pin details).");
//		mobileLoginPage.enterPINCode(Mobile_Pin);
//		mobileLoginPage.enterConfirmPINCode(Mobile_PinInCorrect);
//		
//		reportLog("3.1: MobileView Next button shall be displayed in disabled state");
//		mobileLoginPage.verifyNextButtonIsDisabled();
//
//		reportLog("1.5:MobileView Login with configured Pin");
//		mobileLoginPage.verifySignInScreenDisplayed();
//		dashborad = mobileLoginPage.loginUser(Mobile_Pin);
//
//		reportLog("1.6:MobileView Check top menu configured with home,questionnaries,message,log an event");
//		dashborad.verifyUserLogin();
//		dashborad.verifyTopMenuOptions();
	}

		
	@AfterMethod
	public void deactivateUser(ITestResult result) throws InterruptedException {

		if (ITestResult.SUCCESS == result.getStatus()) {

			reportLog("Deactivate Subject");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
					Constants.NavigateText, Constants.StudyText);
			studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, subjectName);
			subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
			subjectDetailPage.deactivateSubjectConfiguration(SuperAdminUN, SuperAdminPW);
			loginPage.logoutApplication();
			loginPage.verifyUserLogout();
		} else {
			Log.error("Deactivation Not needed");
		}
	}
}
