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

public class FPTC_1622_SubjectMessages_Inbox_SIP extends BaseTest {

	private String SubjectOfSelectedMessage;
	private String emailSubject_Reply = "Subject_" + generateRandomString(3);
	private String emailSubject_SavedReply = "Subject_" + generateRandomString(3);
	private String emailBody = "message text";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1622_SubjectMessages_Inbox_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1622 Test Case Name:Subject Messages - Inbox
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1622_SubjectMessages_Inbox", groups = { "" })

	public void FPTC_1622_SubjectMessages_Inbox() {

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

		reportLog("2.4:Verify send new message displayed on Subject Details");
		subjectDetailPage.verifysendMessageButton();

		reportLog("3.1: Select 'Messages' from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("3.2: Verify 'Inbox' sub-tab displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.Messages_Inbox);

		reportLog("3.3: Verify the list of message threads in inbox");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("3.4: Verify messages are Sorted by received most recent updates in the thread");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();

		reportLog("3.5: Verify Messages sent by Observer are indicated with the green 'person' icon");
		subjectDetailPage.verifyColorOfMessageIconForObserverIsGreen();

		reportLog("3.6: Verify Messages sent by subject are indicated with the red 'person' icon");
		subjectDetailPage.verifyColorOfMessageIconForSubjectIsRed();

		reportLog("3.7: Verify presence of Unread message");
		subjectDetailPage.verifyPresenceOfUnreadMessageInMessageList();

		reportLog("4.1: Select a unread message on the thread");
		subjectDetailPage.selectUnreadMessageFromMessageList();

		reportLog("4.2: Get Subject of selected message");
		SubjectOfSelectedMessage = subjectDetailPage.getSubjectContainsForSelectedMessageFromMessageList();

		reportLog("4.3: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("4.4: Verify Message subject appear under message deltails");
		subjectDetailPage.verifyEmailSubjectUnderMessageAreaAfterSelectingMessage();

		reportLog("4.5: Verify Message text appear under message deltails");
		subjectDetailPage.verifyEmailBodyUnderMessageAreaAfterSelectingMessage();

		reportLog("4.6: Verify Message text appear with " + Constants.ColorCode_Gray + " background");
		subjectDetailPage.verifyBodyTextColorForSelectedMessageFromMessageList(Constants.ColorCode_Gray);

		reportLog("5.1: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("5.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("5.3: Verify Subject field values displayed in model window");
		subjectDetailPage.verifyEmailSubjectUnderModalWindow();

		reportLog("5.4: Verify body text field displayed in model window");
		subjectDetailPage.verifyEmailBodyUnderModalWindow();

		reportLog("5.5: click on cancel button.");
		subjectDetailPage.clickOnCancelButtonOnModelWindow();

		reportLog("5.6: Verify Modal window is closed.");
		subjectDetailPage.verifyEmailModalWindowNotDisplayed();

		reportLog("6.1: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("6.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("6.3: Enter value in Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject_Reply);

		reportLog("6.4: Enter value in Message Body");
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);

		reportLog("6.4: Click on Send button");
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		reportLog("6.5: Select Refresh button");
		subjectDetailPage.refreshSubjectDetailPage();

		reportLog("6.6: Verify Replied message displayed at the top of Inbox messages");
		subjectDetailPage.verifyMessageDisplayedAtTheTopOfMessageList(SubjectOfSelectedMessage);

		reportLog("6.6: Verify Sent message displayed under thread if replied message in Inbox");
		subjectDetailPage.verifyPresenceOfReplyMessageUnderCollapsedMessageThread(SubjectOfSelectedMessage,
				emailSubject_Reply);

		reportLog("6.7: Select 'Sent Items' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_SentItems);

		reportLog("6.8: Verify the list of message threads in Sent Items");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("6.9: Verify Sent message displayed at the top of Sent messages list");
		subjectDetailPage.verifyMessageDisplayedAtTheTopOfMessageList(emailSubject_Reply);

		reportLog("7.1: Select 'Sent Items' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_Inbox);

		reportLog("7.2: Verify the list of message threads in Inbox");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("7.3: Select message from Inbox");
		subjectDetailPage.selectMessageBySubject(SubjectOfSelectedMessage);

		reportLog("7.4: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("7.5: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("7.6: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("7.7: Enter value in Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject_SavedReply);

		reportLog("7.8: Enter value in Message Body");
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);

		reportLog("7.9: Select save button");
		subjectDetailPage.clickOnSaveDraftedEmail();

		reportLog("7.10: Select 'Drafts' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_Drafts);

		reportLog("7.11: Verify the list of message threads in Draft");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("7.12: Verify Updated message displayed");
		subjectDetailPage.verifyPresenceOfMessageBySubject(emailSubject_SavedReply);

		reportLog("7.13: Logout application");
		loginPage.logoutApplication();

		reportLog("7.14: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
