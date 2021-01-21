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

public class FPTC_1623_SubjectMessages_SentItems_SIP extends BaseTest {

	private String emailSubject_Reply = "Subject_" + generateRandomString(3);
	private String emailSubject_SavedReply = "Subject_" + generateRandomString(3);
	private String emailBody = "message text";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1623_SubjectMessages_SentItems_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1623 Test Case Name: Subject Messages - Sent Items
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1623_Subject Messages - Sent Items", groups = { "" })

	public void FPTC_1623_SubjectMessages_SentItems() {

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

		reportLog("3.1: Select 'Messages' from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("3.2: Verify 'Inbox' sub-tab displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.Messages_Inbox);

		reportLog("4.1: Select 'Sent Items' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_SentItems);

		reportLog("4.2: Verify the list of message threads in Sent Items");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("4.3: Verify Messages ordering is by sent date with the newest first");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();

		reportLog("5: Verify 'Include Automated' filter is not selected by default");
		subjectDetailPage.verifyIncludeAutomatedCheckedByDefault();

		reportLog("6: Select 'Include Automated' option");
		subjectDetailPage.clickOnIncludeAutomatedCheckBox();

		reportLog("6.1: Select message from Sent Items");
		subjectDetailPage.selectFirstMessageRow();

		reportLog("6.2: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("6.3: Verify reply button appear after selecting message");
		subjectDetailPage.verifyReplyButtonDisplayed();

		reportLog("6.4: Verify Set Flag Icon appear after selecting message");
		subjectDetailPage.verifyFlagIconDisplayedOnMessageAreaAfterSelectingMessage();

		reportLog("6.5: Verify Time Stamp appear after selecting message");
		subjectDetailPage.verifyTimeStampUnderMessageAreaAfterSelectingMessage();

		reportLog("6.6: Verify Message subject appear under message deltails");
		subjectDetailPage.verifyEmailSubjectUnderMessageAreaAfterSelectingMessage();

		reportLog("6.7: Verify Message text appear under message deltails");
		subjectDetailPage.verifyEmailBodyUnderMessageAreaAfterSelectingMessage();

		reportLog("6.8: Verify Notes Text Box appear under message deltails");
		subjectDetailPage.verifynoteTextBoxAppearUnderMessageAreaAfterSelectingMessage();

		reportLog("7.1: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("7.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("7.3: Verify Subject text field under 'New message' modal.");
		subjectDetailPage.verifyEmailSubjectUnderModalWindow();

		reportLog("7.4: Verify Message text field under 'New message' modal.");
		subjectDetailPage.verifyEmailBodyUnderModalWindow();

		reportLog("7.5: Select close Modal Window");
		subjectDetailPage.clickOnCloseEmailModalWindow();

		reportLog("8.1: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("8.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("8.3: Enter value in Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject_Reply);

		reportLog("8.4: Enter value in Message Body");
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);

		reportLog("8.5: Click on Send button");
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		reportLog("8.6: Select Refresh button");
		subjectDetailPage.refreshSubjectDetailPage();

		reportLog("8.7: Verify Sent message displayed at the top of Sent messages list");
		subjectDetailPage.verifyMessageDisplayedAtTheTopOfMessageList(emailSubject_Reply);

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

		reportLog("9.9: Verify Updated message displayed");
		subjectDetailPage.verifyPresenceOfMessageBySubject(emailSubject_SavedReply);
		
		reportLog("9.10: Select 'Sent Items' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_SentItems);
		
		reportLog("9.11: Un-select 'Include Automated' option");
		subjectDetailPage.clickOnIncludeAutomatedCheckBox();

		reportLog("9.12: Logout application");
		loginPage.logoutApplication();

		reportLog("9.13: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
