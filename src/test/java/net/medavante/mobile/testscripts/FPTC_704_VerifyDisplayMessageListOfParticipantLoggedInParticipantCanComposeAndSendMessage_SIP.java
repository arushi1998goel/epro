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

public class FPTC_704_VerifyDisplayMessageListOfParticipantLoggedInParticipantCanComposeAndSendMessage_SIP
		extends BaseTest {

	private String firstMessage = "Message_A" + generateRandomString(3),
			secondMessage = "Message_B" + generateRandomString(3),messageValue, message1 = "Message_1" + generateRandomString(3),
			message2 = "Message_2" + generateRandomString(3);
	private String emailBody = "message text";

	@BeforeClass
	public void executionOn() {
		exceutionOn = "MobileAndWebExecution";
	}

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_704_VerifyDisplayMessageListOfParticipantLoggedInParticipantCanComposeAndSendMessage_SIP(
			String driver) {
		super(driver);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");

		// create new subject
		reportLog("Go to Portal Side to Create Subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite("MobileTestStudy", Constants.ATAssignedRater_10,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("Select Messages from Drop down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("Verify 'Inbox' sub-tab displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.Messages_Inbox);

		reportLog("4: send at least 2 messages (Message A, Message B)");
		subjectDetailPage.clickOnsendMessageButton();
		subjectDetailPage.verifyEmailModalWindowInEditMode();
		subjectDetailPage.selectRecipientUnderModalWindow(Constants.Recipient_MessageModelWindow_Subject);
		subjectDetailPage.updateEmailSubjectUnderModalWindow(firstMessage);
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		subjectDetailPage.clickOnsendMessageButton();
		subjectDetailPage.verifyEmailModalWindowInEditMode();
		subjectDetailPage.selectRecipientUnderModalWindow(Constants.Recipient_MessageModelWindow_Subject);
		subjectDetailPage.updateEmailSubjectUnderModalWindow(secondMessage);
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		reportLog("Get the Registration Code From The Subject");
		subjectDetailPage.clickOnReportedOutComeMobileSubjectQrIcon();
		registrationCode = subjectDetailPage.getRegistrationCodeOfSubject();
		subjectDetailPage.clickOnSubjectResigtrationCrossControl();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-704 Test Case Name: To verify that logged in
	 * participant is displayed with his/her messages in message tab.
	 * Logged in participant shall be able to compose a new message and send it
	 * Logged in participant is able to reply to an adhoc message.
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-704_To verify that logged in participant is displayed with  his/her messages in message tab. Logged in participant shall be able to compose a new message and send it, Logged in participant is able to reply to an adhoc message. ", groups = {
			"Mobile" })
	public void FPTC_704_VerifyDisplayMessageListOfParticipantLoggedInParticipantCanComposeAndSendMessage()
			throws Exception {
		
		reportLog("1: MobileView Launch the application");
		mobileLoginPage = androidSetUp();

		reportLog("1.1: Enter valid pin in the sign in page.");
		dashborad = mobileLoginPage.scanCodeOrEnterPin(registrationCodeParticipant, Mobile_Pin);

		reportLog("1.2: MobileView Application is successfully launched and Home Page is displayed as per study configuration.");
		dashborad.verifyUserLogin();
		
		reportLog("2: Login as a participant and see the Home page details");
		dashborad.verifyHomePageDisplay();
		
		reportLog("3: Check the Messages tab Badge Alert value.");
		messageValue = dashborad.getMessageValue();
		dashborad.verifyHomeDashbaordValues("Unread", messageValue);
		
		reportLog("3.1: Based on the number of unread messages Messages tab alert shall display the right value.");
		dashborad.verifyMessageTabAlertShowingCorrectValue(messageValue);	
		
		//check the message timing in portal
		
		reportLog("Sent message listed under Sent Items on Portal Side");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_SentItems);

		reportLog("Verify the list of message threads in Sent Items");
		//subjectDetailPage.verifyPresenceOfMessageInMessageList();		
		
		reportLog("4: Click on Messages tab.");
		messgaePage=dashborad.clickOnMessageTab();
		
		reportLog("5: Check the Messages - Sent time stamp.");	
		messgaePage.verifyMessageListPresent();
		
		reportLog("5.1: It shall be the date and time the message was created and sent from portal.");
		
		
		reportLog("5.2: For messages received in current day (Today is 15-May) it shall display '{time} and {date}' in the format e.g. 05:08 PM 15-Jun ");

		reportLog("6: Tap to unread message from Message List.");
		
		reportLog("6.1: Details of the message shall be displayed.");
		
		reportLog("7: After reading the message, tap to back arrow.");
		
		reportLog("7.1: User shall be navigated back to the Messages tab. Alert status (Badge) should not be displayed any more.");
		
		reportLog("8: Select any message from message list and click to reply button, enter message text and click on send button.");
		
		reportLog("8.1: Logged in user shall be able to send reply to an adhoc message.");
		
		reportLog("9: Select compose new message button and enter text in subject and body and then click on send icon.");
		
		reportLog("9.1: Logged in user shall be able to send a new message to the portal.");
		
		reportLog("9.2: Message sent from the device shall be displayed in the subject detail page [message - Inbox folder] on portal side.");
	}

}
