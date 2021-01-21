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

public class FPTC_1627_SubjectMessages_PossibilityToManageMessagesContent_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1627_SubjectMessages_PossibilityToManageMessagesContent_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1627 Test Case Name: Subject Messages - Possibility
	 * to manage messages content
	 * ====================================================================================================================
	 * @throws Exception 
	 */

	@Test(description = "FP-TC-1627_Subject Messages - Possibility to manage messages content ", groups = { "" })

	public void FPTC_1627_SubjectMessages_PossibilityToManageMessagesContent() throws Exception {

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

		reportLog("2.5: Select 'Messages' from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("2.6: Verify the list of message threads in inbox");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("2.7: Select Message from inbox");
		subjectDetailPage.selectFirstMessageRow();

		reportLog("2.8: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("2.9: Verify Message subject appear under message deltails");
		subjectDetailPage.verifyEmailSubjectUnderMessageAreaAfterSelectingMessage();

		reportLog("2.10: Verify Message text appear under message deltails");
		subjectDetailPage.verifyEmailBodyUnderMessageAreaAfterSelectingMessage();

		reportLog("3.1:Click on send new message on Subject Details");
		subjectDetailPage.clickOnsendMessageButton();

		reportLog("3.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("3.3: Select close Modal Window");
		subjectDetailPage.clickOnCloseEmailModalWindow();

		reportLog("4.1: click on reply button appear after selecting message");
		subjectDetailPage.clickOnReplyButton();

		reportLog("4.2: Verify email Modal displayed");
		subjectDetailPage.verifyEmailModalWindowInEditMode();

		reportLog("4.3: Select close Modal Window");
		subjectDetailPage.clickOnCloseEmailModalWindow();

		reportLog("5.1: click on Flag Icon");
		subjectDetailPage.clickOnFlagIconMessageAreaAfterSelectingMessage();

		reportLog("6.1: Logout application");
		loginPage.logoutApplication();

		reportLog("6.2: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		reportLog("6.3: Login in to application");
		dashBoardPage = loginPage.sponsorLogin(AT_PRODSponsorUserTypeNew, AT_Password);

		reportLog("7.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("7.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("7.3:Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("7.4:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("7.5: Select 'Messages' from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("7.6:Verify send new message not displayed on Subject Details");
		subjectDetailPage.verifyAbsenceOfsendNewMessageButton();

		reportLog("7.7: Verify the list of message threads in inbox");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("7.8: Select Message from inbox");
		subjectDetailPage.selectFirstMessageRow();

		reportLog("7.9: Verify message area appear after selecting message");
		subjectDetailPage.verifyMessageAreaAfterSelectingMessage();

		reportLog("7.10: Verify Reply option are not available");
		subjectDetailPage.verifyReplyButtonNOtDisplayed();

		reportLog("7.11: Verify Flag/Unflag Message options are not available");
		subjectDetailPage.verifyFlagIconNotDisplayedOnMessageAreaAfterSelectingMessage();

		reportLog("7.12: Logout application");
		loginPage.logoutApplication();

		reportLog("7.13: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
