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

public class FPTC_1621_SubjectMessages_GeneralFlow_SIP extends BaseTest {

	private String emailSubject_Saved = "Subject_" + generateRandomString(3);
	private String emailSubject_Send = "Subject_" + generateRandomString(3);
	private String emailBody = "message text";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1621_SubjectMessages_GeneralFlow_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		subjectName = properties.getProperty("SubjectWithMessages");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1621 Test Case Name:Subject Messages - General flow
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1621_Subject Messages - General flow ", groups = { "" })

	public void FPTC_1621_SubjectMessages_GeneralFlow() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.3:Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.4:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("3.1:Click on send new message on Subject Details");
		subjectDetailPage.clickOnsendMessageButton();

		reportLog("3.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("3.3: Verify Recipient selector with options: " + Constants.Recipient_MessageModelWindow_Subject
				+ " displayed under 'New message' modal.");
		subjectDetailPage.verifyRecipientDisplayedUnderModalWindow(Constants.Recipient_MessageModelWindow_Subject);

		reportLog("3.4: Verify Recipient selector with options: " + Constants.Recipient_MessageModelWindow_Observer
				+ " displayed under 'New message' modal.");
		subjectDetailPage.verifyRecipientDisplayedUnderModalWindow(Constants.Recipient_MessageModelWindow_Observer);

		reportLog("3.5: Verify Subject text field under 'New message' modal.");
		subjectDetailPage.verifyEmailSubjectUnderModalWindow();

		reportLog("3.6: Verify Message text field under 'New message' modal.");
		subjectDetailPage.verifyEmailBodyUnderModalWindow();

		reportLog("3.7: Verify Disabled Send button under 'New message' modal.");
		subjectDetailPage.verifySaveButtonIsDisabledForDraftedEmail();

		reportLog("3.8: Verify Disabled Save control under 'New message' modal.");
		subjectDetailPage.verifysendButtonIsDisabledForDraftedEmail();

		reportLog("3.9: Verify Active Cancel control under 'New message' modal.");
		subjectDetailPage.verifyCancelButtonIsEnabledForDraftedEmail();

		reportLog("4.1: Select Recipient : " + Constants.Recipient_MessageModelWindow_Observer
				+ " under 'New message' modal.");
		subjectDetailPage.selectRecipientUnderModalWindow(Constants.Recipient_MessageModelWindow_Observer);

		reportLog("4.2: Enter value in Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject_Saved);

		reportLog("4.3: Enter value in Message Body");
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);

		reportLog("4.4: Select save button");
		subjectDetailPage.clickOnSaveDraftedEmail();

		reportLog("4.5: Select 'Messages' from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("4.6: Select 'Drafts' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_Drafts);

		reportLog("4.7: Verify the list of message threads in Draft");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("4.8: Verify saved message with subject '" + emailSubject_Saved);
		subjectDetailPage.verifyPresenceOfMessageBySubject(emailSubject_Saved);

		reportLog("5.1:Click on send new message on Subject Details");
		subjectDetailPage.clickOnsendMessageButton();

		reportLog("5.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("5.3: Select Recipient : " + Constants.Recipient_MessageModelWindow_Subject
				+ " under 'New message' modal.");
		subjectDetailPage.selectRecipientUnderModalWindow(Constants.Recipient_MessageModelWindow_Subject);

		reportLog("5.4: Enter value in Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject_Send);

		reportLog("5.5: Enter value in Message Body");
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);

		reportLog("5.6: Click on Send button");
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		reportLog("5.7: Select 'Sent' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_SentItems);

		reportLog("5.8: Verify the list of message threads in Sent Items");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("5.9: Verify saved message with subject '" + emailSubject_Send);
		subjectDetailPage.verifyPresenceOfMessageBySubject(emailSubject_Send);

		reportLog("6.1: Select 'Inbox' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_Inbox);

		reportLog("6.2: Verify the list of message threads in inbox");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("6.3: Verify messages are Sorted by received most recent updates in the thread");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();

		reportLog("6.4: Verify Message Area displayed without any message content");
		subjectDetailPage.verifyEmptyMessageAreaDisplayedWithoutAnyMessageContent();

		reportLog("7.1: Verify presence of Unread message");
		subjectDetailPage.verifyPresenceOfUnreadMessageInMessageList();

		reportLog("7.2: Select a unread message on the thread");
		subjectDetailPage.selectUnreadMessageFromMessageList();

		reportLog("7.3: Verify message area displayed with the message content");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("7.4:  Verify selected message with read visual indication");
		subjectDetailPage.verifySelectedMessageWithReadVisualIndication();

		reportLog("9.1: Verify Set Flag Icon appear after selecting message");
		subjectDetailPage.verifyFlagIconDisplayedOnMessageAreaAfterSelectingMessage();

		reportLog("9.2: click on 'Set Flag' icon");
		subjectDetailPage.clickOnFlagIconMessageAreaAfterSelectingMessage();

		reportLog("9.3: Verify Flag icon displayed on the Message list");
		subjectDetailPage.verifySelectedMessageDisplayedWithFlagIconOnMessageList();

		reportLog("10.1: Verify Set Flag Icon appear after selecting message");
		subjectDetailPage.verifyFlagIconDisplayedOnMessageAreaAfterSelectingMessage();

		reportLog("10.2: click on 'Clear Flag' icon");
		subjectDetailPage.clickOnFlagIconMessageAreaAfterSelectingMessage();

		reportLog("10.3: Verify Flag icon is hidden from the Message list");
		subjectDetailPage.verifyFlagIconHiddenForSelectedMessage();

		reportLog("11.1: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("11.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("11.3: Enter value in Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject_Send);

		reportLog("11.4: Enter value in Message Body");
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);

		reportLog("11.5: Click on Send button");
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		reportLog("11.6: Verify email Modal closed");
		subjectDetailPage.verifyEmailModalWindowNotDisplayed();

		reportLog("11.7: Logout application");
		loginPage.logoutApplication();

		reportLog("11.8: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
