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

public class FPTC_1625_verifySubjectMessages_Drafts_SIP extends BaseTest {

	private String emailSubject1 = "Auto Subject " + generateRandomAlphanumericString(3);
	private String emailSubject2 = "Auto Subject " + generateRandomAlphanumericString(3);
	private String emailSubject3 = "Auto Subject " + generateRandomAlphanumericString(3), emailBody = "message text";


	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1625_verifySubjectMessages_Drafts_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyMobileEPROVirigilAPK");
		subjectName = properties.getProperty("SubjectWithMessages");

		reportLog("PR1: Login and redirecting to subject");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("PR2: Creating email 1 and save it in draft");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnsendMessageButton();
		subjectDetailPage.verifyEmailModalWindowInEditMode();
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject1);
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);
		subjectDetailPage.clickOnSaveDraftedEmail();

		reportLog("PR3: Creating email 2 and save it in draft");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.clickOnsendMessageButton();
		subjectDetailPage.verifyEmailModalWindowInEditMode();
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject2);
		subjectDetailPage.updateEmailBodyUnderModalWindow(emailBody);
		subjectDetailPage.clickOnSaveDraftedEmail();

		loginPage.logoutApplication();
		loginPage.verifyUserLogout();

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1625 Test Case Name:Subject Messages - Drafts
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1625_SubjectMessages_Drafts", groups = { "" })

	public void FPTC_1625_verifySubjectMessages_Drafts() {

		reportLog("1.1: Login in to application");
		loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("2.3:Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.4:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("3.1: Select 'Messages' from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("3.2: Verify 'Inbox' sub-tab displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.Messages_Inbox);

		reportLog("4.1: Select 'Drafts' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_Drafts);

		reportLog("4.2: Verify the list of message threads in Draft");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("5.1: Select message from draft");
		subjectDetailPage.selectMessageBySubject(emailSubject1);

		reportLog("5.2: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("5.3: Verify Open control appear to Send/Update a Message");
		subjectDetailPage.verifyOpenButtonUnderMessageAreaAfterSelectingMessage();

		reportLog("5.4: Verify Delete control appear to delete message");
		subjectDetailPage.verifyDeleteButtonUnderMessageAreaAfterSelectingMessage();

		reportLog("5.5: Verify Message subject appear under message deltails");
		subjectDetailPage.verifyEmailSubjectUnderMessageAreaAfterSelectingMessage();

		reportLog("5.6: Verify Message text appear under message deltails");
		subjectDetailPage.verifyEmailBodyUnderMessageAreaAfterSelectingMessage();

		reportLog("5.7: Verify Notes Text Box appear under message deltails");
		subjectDetailPage.verifynoteTextBoxAppearUnderMessageAreaAfterSelectingMessage();

		reportLog("6.1: Open email message in edit mode");
		subjectDetailPage.clickOnOpenButtonUnderMessageArea();

		reportLog("6.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("6.3 : Update Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject3);

		reportLog("6.4: Select close Modal Window");
		subjectDetailPage.clickOnCloseEmailModalWindow();

		reportLog("6.5: Verify message is in draft list and changes not updated in subject field");
		subjectDetailPage.verifyPresenceOfMessageBySubject(emailSubject1);

		reportLog("7.1: Select message from draft");
		subjectDetailPage.selectMessageBySubject(emailSubject1);

		reportLog("7.2: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("7.3: Select delete button");
		subjectDetailPage.clickOnDeleteButtonUnderMessageAreaAfterSelectingMessage();

		reportLog("7.4: Verify Confirmation Modal Window appears");
		subjectDetailPage.verifyConfirmationWindowBeforeDeleteMessage();

		reportLog("7.5: Select yes under Modal window");
		subjectDetailPage.clickOnYesEmailModalWindow();

		reportLog("7.6: Verify message is deleted from list");
		subjectDetailPage.verifyMessageIsDeletedFromList(emailSubject1);

		reportLog("8.1: Select message from draft");
		subjectDetailPage.selectMessageBySubject(emailSubject2);

		reportLog("8.2: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("8.3: Verify Open control appear to Send/Update a Message");
		subjectDetailPage.verifyOpenButtonUnderMessageAreaAfterSelectingMessage();

		reportLog("8.4: Open email message in edit mode");
		subjectDetailPage.clickOnOpenButtonUnderMessageArea();

		reportLog("8.5: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("8.6: Update Message subject");
		subjectDetailPage.updateEmailSubjectUnderModalWindow(emailSubject3);

		reportLog("8.7: Select save button");
		subjectDetailPage.clickOnSaveDraftedEmail();

		reportLog("8.8: Verify Updated message displayed");
		subjectDetailPage.verifyPresenceOfMessageBySubject(emailSubject3);

		reportLog("9.1: Select message from draft");
		subjectDetailPage.selectMessageBySubject(emailSubject3);

		reportLog("9.2: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("9.3: Verify Open control appear to Send/Update a Message");
		subjectDetailPage.verifyOpenButtonUnderMessageAreaAfterSelectingMessage();

		reportLog("9.4: Open email message in edit mode");
		subjectDetailPage.clickOnOpenButtonUnderMessageArea();

		reportLog("9.5: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("9.6: Select Recipient : " + Constants.Recipient_MessageModelWindow_Subject
				+ " under 'New message' modal.");
		subjectDetailPage.selectRecipientUnderModalWindow(Constants.Recipient_MessageModelWindow_Subject);

		reportLog("9.7: Click on Send button");
		subjectDetailPage.clickOnSendButtonOnModelWindow();

		reportLog("9.8: Verify email Modal closed");
		subjectDetailPage.verifyEmailModalWindowNotDisplayed();

		reportLog("9.9: Verify Message Area displayed without any message content");
		subjectDetailPage.verifyEmptyMessageAreaDisplayedWithoutAnyMessageContent();

		reportLog("9.10: Logout application");
		loginPage.logoutApplication();

		reportLog("9.11: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
