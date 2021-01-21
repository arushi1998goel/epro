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

public class FPTC_1626_SubjectMessages_Flagged_SIP extends BaseTest {

	private String SubjectOfSelectedMessage;
	private String emailSubject_Reply = "Subject_" + generateRandomString(3);
	private String emailSubject_SavedReply = "Subject_" + generateRandomString(3);
	private String emailBody = "message text";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1626_SubjectMessages_Flagged_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		subjectName = properties.getProperty("SubjectWithMessages");

		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();
		subjectDetailPage.verifysendMessageButton();
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("PR 4:at least 2 Flagged messages exist");

		subjectDetailPage.selectFirstUnFlaggedMessageRow();
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();
		subjectDetailPage.clickOnFlagIconMessageAreaAfterSelectingMessage();

		subjectDetailPage.selectFirstUnFlaggedMessageRow();
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();
		subjectDetailPage.clickOnFlagIconMessageAreaAfterSelectingMessage();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1626 Test Case Name: Subject Messages - Flagged
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1626_Subject Messages - Flagged ", groups = { "" })

	public void FPTC_1626_SubjectMessages_Flagged() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);

		reportLog("2.3:Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.4:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("3.1: Select 'Messages' from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("3.2: Verify 'Inbox' sub-tab displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.Messages_Inbox);

		reportLog("4.1: Select 'Flagged' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_Flagged);

		reportLog("4.2: Verify the list of message threads in Sent Items");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("5.1: Select message from Flagged message");
		subjectDetailPage.selectFirstMessageRow();

		reportLog("5.2: Get Subject of selected message");
		SubjectOfSelectedMessage = subjectDetailPage.getSubjectContainsForSelectedMessageFromMessageList();

		reportLog("5.3: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("5.4: Verify Message subject appear under message deltails");
		subjectDetailPage.verifyEmailSubjectUnderMessageAreaAfterSelectingMessage();

		reportLog("5.5: Verify Message text appear under message deltails");
		subjectDetailPage.verifyEmailBodyUnderMessageAreaAfterSelectingMessage();

		reportLog("6.1: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("6.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("6.3: Enter value in Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject_Reply);

		reportLog("6.4: Enter value in Message Body");
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);

		reportLog("6.5: Select close Modal Window");
		subjectDetailPage.clickOnCloseEmailModalWindow();

		reportLog("6.6: Verify model window closed");
		subjectDetailPage.verifyEmailModalWindowNotDisplayed();

		reportLog("7.1: click on clear Flag Icon");
		subjectDetailPage.clickOnFlagIconMessageAreaAfterSelectingMessage();

		reportLog("7.2: Select Refresh button");
		subjectDetailPage.refreshSubjectDetailPage();

		reportLog("7.3: Verify Absence Of Message with Subject '" + SubjectOfSelectedMessage + "' In Message List");
		subjectDetailPage.verifyAbsenceOfMessageBySubject(SubjectOfSelectedMessage);

		reportLog("8.1: Select message from Flagged message");
		subjectDetailPage.selectFirstMessageRow();

		reportLog("8.2: Get Subject of selected message");
		SubjectOfSelectedMessage = subjectDetailPage.getSubjectContainsForSelectedMessageFromMessageList();

		reportLog("8.3: Verify Message text appear under message deltails");
		subjectDetailPage.verifyEmailBodyUnderMessageAreaAfterSelectingMessage();

		reportLog("8.4: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("8.5: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("8.6: Enter value in Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject_Reply);

		reportLog("8.7: Enter value in Message Body");
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);

		reportLog("8.8: Click on Send button");
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		reportLog("8.9: Select Refresh button");
		subjectDetailPage.refreshSubjectDetailPage();

		reportLog("8.10: Verify presence Of Message with Subject '" + SubjectOfSelectedMessage
				+ "' In flagged Message List");
		subjectDetailPage.verifyPresenceOfMessageBySubject(SubjectOfSelectedMessage);

		reportLog("9.1: Select message from Sent Items");
		subjectDetailPage.selectFirstMessageRow();

		reportLog("9.2: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("9.3: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("9.4: Enter value in Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject_SavedReply);

		reportLog("9.5: Enter value in Message Body");
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);

		reportLog("9.6: Select save button");
		subjectDetailPage.clickOnSaveDraftedEmail();

		reportLog("9.7: Select 'Drafts' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_Drafts);

		reportLog("9.8: Verify the list of message threads in Draft");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("9.9: Verify saved message with subject '" + emailSubject_SavedReply
				+ "' displayed at the top of Drafts list");
		subjectDetailPage.verifyMessageDisplayedAtTheTopOfMessageList(emailSubject_SavedReply);

		reportLog("9.10: Logout application");
		loginPage.logoutApplication();

		reportLog("9.11: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
