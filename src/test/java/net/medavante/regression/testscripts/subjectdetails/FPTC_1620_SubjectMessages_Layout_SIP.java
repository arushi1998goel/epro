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

public class FPTC_1620_SubjectMessages_Layout_SIP extends BaseTest {

	private String emailSubject_Sent, emailSubject_draft, emailSubject_flagged;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1620_SubjectMessages_Layout_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		subjectName = properties.getProperty("SubjectWithMessages");

		emailSubject_Sent = properties.getProperty("EmailSubject_Sent");
		emailSubject_draft = properties.getProperty("EmailSubject_draft");
		emailSubject_flagged = properties.getProperty("EmailSubject_flagged");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1620 Test Case Name:Subject Messages - Layout
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1620_SubjectMessages_Drafts", groups = { "" })

	public void FPTC_1620_SubjectMessages_Layout() {

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

		reportLog("4.1: Select Refresh button");
		subjectDetailPage.refreshSubjectDetailPage();

		reportLog("4.2: Verify the list of message threads in inbox");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("5.1: Verify Presence Of Message In collapsed view");
		subjectDetailPage.verifyPresenceOfMessageInCollapsedView();

		reportLog("5.2: Verify Messages sent by Observer are indicated with the green 'person' icon");
		subjectDetailPage.verifyColorOfMessageIconForObserverIsGreen();

		reportLog("5.3: Verify Messages sent by subject are indicated with the red 'person' icon");
		subjectDetailPage.verifyColorOfMessageIconForSubjectIsRed();

		reportLog("5.4: Select message from inbox");
		subjectDetailPage.selectMessageBySubject(emailSubject_flagged);

		reportLog("5.5: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("5.6: Verify reply button appear after selecting message");
		subjectDetailPage.verifyReplyButtonDisplayed();

		reportLog("5.7: Verify Set Flag Icon appear after selecting message");
		subjectDetailPage.verifyFlagIconDisplayedOnMessageAreaAfterSelectingMessage();

		reportLog("5.8: Verify Time Stamp appear after selecting message");
		subjectDetailPage.verifyTimeStampUnderMessageAreaAfterSelectingMessage();

		reportLog("5.9: Verify Message subject appear under message deltails");
		subjectDetailPage.verifyEmailSubjectUnderMessageAreaAfterSelectingMessage();

		reportLog("5.10: Verify Message text appear under message deltails");
		subjectDetailPage.verifyEmailBodyUnderMessageAreaAfterSelectingMessage();

		reportLog("5.11: Verify Notes Text Box appear under message deltails");
		subjectDetailPage.verifynoteTextBoxAppearUnderMessageAreaAfterSelectingMessage();

		reportLog("6.1: Select 'Sent Items' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_SentItems);

		reportLog("6.2: Verify the list of message threads in Sent Items");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("6.3: Verify messages are Sorted by received most recent updates in the thread");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();

		reportLog("6.4: Verify 'Include Automated' filter is not selected by default");
		subjectDetailPage.verifyIncludeAutomatedCheckedByDefault();

		reportLog("6.5: Select message from Sent Items");
		subjectDetailPage.selectMessageBySubject(emailSubject_Sent);

		reportLog("6.6: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("6.7: Verify reply button appear after selecting message");
		subjectDetailPage.verifyReplyButtonDisplayed();

		reportLog("6.8: Verify Set Flag Icon appear after selecting message");
		subjectDetailPage.verifyFlagIconDisplayedOnMessageAreaAfterSelectingMessage();

		reportLog("6.9: Verify Time Stamp appear after selecting message");
		subjectDetailPage.verifyTimeStampUnderMessageAreaAfterSelectingMessage();

		reportLog("6.10: Verify Message subject appear under message deltails");
		subjectDetailPage.verifyEmailSubjectUnderMessageAreaAfterSelectingMessage();

		reportLog("6.11: Verify Message text appear under message deltails");
		subjectDetailPage.verifyEmailBodyUnderMessageAreaAfterSelectingMessage();

		reportLog("6.12: Verify Notes Text Box appear under message deltails");
		subjectDetailPage.verifynoteTextBoxAppearUnderMessageAreaAfterSelectingMessage();

		reportLog("7.1: Select 'Drafts' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_Drafts);

		reportLog("7.2: Verify the list of message threads in Drafts");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("7.3: Select message from draft");
		subjectDetailPage.selectMessageBySubject(emailSubject_draft);

		reportLog("7.4: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("7.5: Verify Open control appear to Send/Update a Message");
		subjectDetailPage.verifyOpenButtonUnderMessageAreaAfterSelectingMessage();

		reportLog("7.6: Verify Delete control appear to delete message");
		subjectDetailPage.verifyDeleteButtonUnderMessageAreaAfterSelectingMessage();

		reportLog("7.7: Select delete button");
		subjectDetailPage.clickOnDeleteButtonUnderMessageAreaAfterSelectingMessage();

		reportLog("7.8: Verify Confirmation Modal Window appears");
		subjectDetailPage.verifyConfirmationWindowBeforeDeleteMessage();

		reportLog("7.9: Click on Cancel button on Confirmation Modal Window");
		subjectDetailPage.clickOnNOConfirmationWindow();

		reportLog("7.10: Verify Message subject appear under message deltails");
		subjectDetailPage.verifyEmailSubjectUnderMessageAreaAfterSelectingMessage();

		reportLog("7.11: Verify Message text appear under message deltails");
		subjectDetailPage.verifyEmailBodyUnderMessageAreaAfterSelectingMessage();

		reportLog("7.12: Verify Notes Text Box appear under message deltails");
		subjectDetailPage.verifynoteTextBoxAppearUnderMessageAreaAfterSelectingMessage();

		reportLog("8.1: Select 'Flagged' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_Flagged);

		reportLog("8.2: Verify the list of message threads in Sent Items");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("8.3: Verify messages are Sorted by received most recent updates in the thread");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();

		reportLog("8.4: Select message from Sent Items");
		subjectDetailPage.selectMessageBySubject(emailSubject_flagged);

		reportLog("8.5: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("8.6: Verify reply button appear after selecting message");
		subjectDetailPage.verifyReplyButtonDisplayed();

		reportLog("8.7: Verify Set Flag Icon appear after selecting message");
		subjectDetailPage.verifyFlagIconDisplayedOnMessageAreaAfterSelectingMessage();

		reportLog("8.8: Verify Time Stamp appear after selecting message");
		subjectDetailPage.verifyTimeStampUnderMessageAreaAfterSelectingMessage();

		reportLog("8.9: Verify Message subject appear under message deltails");
		subjectDetailPage.verifyEmailSubjectUnderMessageAreaAfterSelectingMessage();

		reportLog("8.10: Verify Message text appear under message deltails");
		subjectDetailPage.verifyEmailBodyUnderMessageAreaAfterSelectingMessage();

		reportLog("8.11: Verify Notes Text Box appear under message deltails");
		subjectDetailPage.verifynoteTextBoxAppearUnderMessageAreaAfterSelectingMessage();

		reportLog("9.1: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("9.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("9.3: Verify Subject text field under 'New message' modal.");
		subjectDetailPage.verifyEmailSubjectUnderModalWindow();

		reportLog("9.4: Verify Message text field under 'New message' modal.");
		subjectDetailPage.verifyEmailBodyUnderModalWindow();

		reportLog("9.5: Verify Disabled Send button under 'New message' modal.");
		subjectDetailPage.verifySaveButtonIsDisabledForDraftedEmail();

		reportLog("9.6: Verify Disabled Save control under 'New message' modal.");
		subjectDetailPage.verifysendButtonIsDisabledForDraftedEmail();

		reportLog("9.7: Verify Active Cancel control under 'New message' modal.");
		subjectDetailPage.verifyCancelButtonIsEnabledForDraftedEmail();

		reportLog("9.8: Select close Modal Window");
		subjectDetailPage.clickOnCloseEmailModalWindow();

		reportLog("9.10: Logout application");
		loginPage.logoutApplication();

		reportLog("9.11: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
