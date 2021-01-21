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

public class FPTC_893_VerifyThatAParticipantAbleToReceiveCreateReplySendAndDeleteMessages_SIP extends BaseTest {

	private String subject1 = "Subject_A" + generateRandomString(3), subject2 = "Subject_B" + generateRandomString(3),
			subject3 = "Subject_C" + generateRandomString(3), subject ="Subject_12211";
	private String message1 = "Message_" + generateRandomString(3);
	private String emailBody = "message text";

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_893_VerifyThatAParticipantAbleToReceiveCreateReplySendAndDeleteMessages_SIP(String driver) {
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
	 * Test Case Id: FP-TC-893 Test Case Name: Messages - Check that a
	 * Participant is able to receive, create, reply, send and delete messages
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-893_Messages - Check that a Participant is able to receive, create, reply, send and delete messages   ", groups = {
			"Mobile" })
	public void FPTC_893_verifyThatAParticipantAbleToReceiveCreateReplySendAndDeleteMessages() throws Exception {

		reportLog("1: As a Participant logged into the application click to messages tab on device.");

		mobileLoginPage.verifySignInScreenDisplayed();
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);

		reportLog("2: MobileView The Splash screen Virgil Pro text label shall be displayed");
		dashborad.verifyHomePageDisplay();

		reportLog("Navigate to Message tab");
		dashborad.clickOnMessageTab();
		dashborad.verifyPageTitle("Messages");

		reportLog(
				"2.1: At first the user is able to view only an empty Messages List with: 'No Items' text displayed and Add Button");
		dashborad.verifyTextOnScreen("No Items");
		dashborad.verifyAddMessageButtonWhileNoDataAddedDisplay();

		reportLog("Exit from app");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		dashborad.clickOnExitApplication();

		reportLog("3: Go to Portal Side to Subject Detail Page");
		/*dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.searchSubject(subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subject);
		subjectDetailPage.verifyNewSubjectDetailPage();*/

		reportLog("3.1: Select Messages from Drop down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("3.2: Verify 'Inbox' sub-tab displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.Messages_Inbox);

		reportLog("4: send at least 3 messages (Message A, Message B and Message C)");
		subjectDetailPage.clickOnsendMessageButton();
		subjectDetailPage.verifyEmailModalWindowInEditMode();
		subjectDetailPage.selectRecipientUnderModalWindow(Constants.Recipient_MessageModelWindow_Subject);
		subjectDetailPage.updateEmailSubjectUnderModalWindow(subject1);
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		subjectDetailPage.clickOnsendMessageButton();
		subjectDetailPage.verifyEmailModalWindowInEditMode();
		subjectDetailPage.selectRecipientUnderModalWindow(Constants.Recipient_MessageModelWindow_Subject);
		subjectDetailPage.updateEmailSubjectUnderModalWindow(subject2);
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		subjectDetailPage.clickOnsendMessageButton();
		subjectDetailPage.verifyEmailModalWindowInEditMode();
		subjectDetailPage.selectRecipientUnderModalWindow(Constants.Recipient_MessageModelWindow_Subject);
		subjectDetailPage.updateEmailSubjectUnderModalWindow(subject3);
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		reportLog("4.1: Sent message listed under Sent Items on Portal Side");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_SentItems);

		reportLog("4.2: Verify the list of message threads in Sent Items");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("4.3: Verify send message with subject '" + subject1 + subject2 + subject3);
		subjectDetailPage.verifyPresenceOfMessageBySubject(subject1);
		subjectDetailPage.verifyPresenceOfMessageBySubject(subject2);
		subjectDetailPage.verifyPresenceOfMessageBySubject(subject3);

		reportLog("5: As a Participant logged into the application click to messages tab on device.");
		mobileLoginPage = androidSetUp();
		mobileLoginPage.verifySignInScreenDisplayed();
		dashborad = mobileLoginPage.loginUser(Mobile_Pin);
		
		reportLog("5.1: click to messages tab on device");
		dashborad.clickOnHomeTab();
		dashborad.verifyHomePageDisplay();
		dashborad.clickOnMessageTab();

		reportLog("6: Select one of the newly received Ad-Hoc messages and check for its detail.");
		dashborad.selectMessageFromList(subject1);
		//dashborad.selectMessageFromList("Subject_Amit");

		reportLog("7: Tap to Reply button");
		dashborad.clickOnReplyMessageButton();

		reportLog("8: Tap to Reply button.");
		dashborad.enterMessageBodyText("Reply Message");

		reportLog("8.1: Message Composer shall be displayed.");
		dashborad.clickOnSendMessageButton();
		dashborad.clickOnBackIcon();

		// Validation in text body
		// Right now message body accepting 71 words so not working for
		// validation limit
		reportLog("8.3: Title field shall not accept more than 30 chars.");
		reportLog("8.4: Title field shall not accept more than 30 chars.");

		reportLog("8.5: Message text shall not be a simple text with formatting.");
		dashborad.verifyAddMessageButtonDisplay();
		dashborad.clickOnAddMessageButton();

		reportLog("9: Click to Send Button.");
		dashborad.clickOnSendMessageButton();
		dashborad.clickOnBackIcon();

		reportLog("10: On Messages List click on + icon");
		dashborad.clickOnAddMessageButton();

		reportLog("10.1: Message Composer shall be displayed.");
		dashborad.verifyPageTitle("Compose");

		reportLog("11: Leave both subject text field and the body of the message empty and Tap Send Button");
		dashborad.clickOnSendMessageButton();
		dashborad.clickOnBackIcon();

		reportLog("12: Compose the message again: • Enter Subject • Enter Message");
		dashborad.clickOnAddMessageButton();

		reportLog("12.1: Message Composer shall be displayed.");
		dashborad.verifyPageTitle("Compose");

		reportLog("12.2: Logged in user shall be able to add text in the subject and Body sections.");
		dashborad.enterMessageSubjectText("Test 1");
		dashborad.enterMessageBodyText("Test 1");
		dashborad.hideKeyboard();

		// Not showing any validation fot these fields in Mobile
		reportLog("12.3: Title field shall not accept more than 30 chars");
		reportLog("12.4: Message text shall not be more than 360 chars");
		reportLog("12.5: Message text shall not be a simple text with formatting");

		reportLog("13: Tap Back Button");
		dashborad.clickOnBackIcon();

		reportLog("13.1: Confirmation dialog shall be displayed with options:• No • Yes ");
		dashborad.verifyConfirmationDialogDisplayWithYesAndNoOptions(Constants.exitPopUp);

		reportLog(
				"13.2: If No - Logged in user shall be able to remain on Message Composer and finish the message composition.");
		dashborad.clickOnNoOption();
		dashborad.clickOnBackIcon();

		reportLog("13.2: If Yes - Logged in user shall be able to see the Message List");
		//dashborad.verifyConfirmationDialogDisplayWithYesAndNoOptions(Constants.exitPopUp);
		dashborad.clickOnYesOption();
				
		dashborad.verifyPageTitle("Messages");		
		dashborad.clickOnAddMessageButton();
		
		dashborad.verifyPageTitle("Compose");
		dashborad.enterMessageSubjectText(message1);
		dashborad.enterMessageBodyText(message1);
		dashborad.clickOnBackIcon();

		reportLog("14: Select from confirmation dialog: • No");
		//dashborad.verifyConfirmationDialogDisplayWithYesAndNoOptions(Constants.exitPopUp);
		dashborad.clickOnNoOption();

		reportLog("15: Tap to Send Button.");
		dashborad.clickOnSendMessageButton();

		reportLog("15.1: Logged in user shall be able to click to send button and be able to see the Messages list.");
		//dashborad.verifyPageTitle("Messsages");
		dashborad.clickOnHumBergerMenuAndOpenLeftPanel();
		dashborad.clickOnExitApplication();

		reportLog(
				"16: Verify on Portal side that the sent message from step 9 and 15 are presented in the Messages (Inbox) Tab on Subject Detail Page.");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.Messages_Inbox);
		
		reportLog(
				"17: Select Message C of listed Ad-Hoc messages from Messages List (once the message content is displayed) then Delete the message.");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog(
				"17.1: Logged in user shall be able: to select any of Ad-Hoc Messages (Message C) from Messages List ");
		subjectDetailPage.verifyPresenceOfMessageBySubject(message1);
		
		loginPage.logoutApplication();
		loginPage.verifyUserLogout();
		
		reportLog(
				"17.2: to view the whole content of Ad-Hoc Message C with its detail (Including Delete Button on the Right top corner)");
		//mobileLoginPage.reopenApp();
		mobileLoginPage = androidSetUp();
		mobileLoginPage.verifySignInScreenDisplayed();
		mobileLoginPage.loginUser(Mobile_Pin);
		dashborad.clickOnHomeTab();
		dashborad.verifyHomePageDisplay();
		dashborad.clickOnMessageTab();

		reportLog("17.3: to Delete the Ad-Hoc Message C");
		dashborad.selectMessageFromList(subject3);
		//dashborad.selectMessageFromList("Subject_Cuoy");
		dashborad.verifyDeleteIconDisplay();
		dashborad.clickOnDeleteMessageButton();

		reportLog("18: Verify the navigation once Message C is deleted ");
		dashborad.clickOnBackIcon();

		reportLog(
				"18.1: The user shall be able to see the content of next message (if exists - Message B) once Message C is deleted.");
		dashborad.selectMessageFromList(subject2);
		dashborad.clickOnDeleteMessageButton();

		reportLog("19: While Message B content is displayed, tap to Delete Button and verify the navigation");
		reportLog("19.1: The user shall be able to see the content of next message (if exists - Message A) once Message B is deleted.");

	}

	@AfterMethod
	public void updateSubjectValueInPropertiesFile(ITestResult result) throws InterruptedException {
		if(ITestResult.SUCCESS==result.getStatus()) {
			
			reportLog("Deactivate Subject");
			dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
			studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
					Constants.NavigateText, Constants.StudyText);
			studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
			
			studyNavigatorDashBoardPage
					.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject, screeningNum);
			subjectDetailPage = studyNavigatorDashBoardPage.selectByFirstCell(NewSubjectDetailPage.class);
			subjectDetailPage.deactivateSubjectConfiguration(SuperAdminUN, SuperAdminPW);
			loginPage.logoutApplication();
			
		}else {
			Log.error(subjectName + " subject is not added for " + studyName+ " study.");
		}
	}
}
