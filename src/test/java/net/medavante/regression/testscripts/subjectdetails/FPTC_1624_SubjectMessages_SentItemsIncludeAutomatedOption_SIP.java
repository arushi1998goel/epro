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

public class FPTC_1624_SubjectMessages_SentItemsIncludeAutomatedOption_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1624_SubjectMessages_SentItemsIncludeAutomatedOption_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1624 Test Case Name: Subject Messages - Sent Items.
	 * 'Include Automated' option
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1624_Subject Messages - Sent Items. 'Include Automated' option", groups = { "" })

	public void FPTC_1624_SubjectMessages_SentItemsIncludeAutomatedOption() {

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

		reportLog("2.4:Verify send new message displayed on Subject Details");
		subjectDetailPage.verifysendMessageButton();

		reportLog("3.1: Select 'Messages' from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("3.2: Verify 'Inbox' sub-tab displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.Messages_Inbox);

		reportLog("4.1: Select 'Sent Items' sub-tab");
		subjectDetailPage.selectCategorySubTab(Constants.Messages_SentItems);

		reportLog("4.2: Verify 'Include Automated' filter selected by default");
		subjectDetailPage.verifyIncludeAutomatedCheckedByDefault();

		reportLog("4.3: Verify the list of message threads in Sent Items");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("5.1: Click on 'Include Automated' Check Box to Unselect 'Include Automated' option");
		subjectDetailPage.clickOnIncludeAutomatedCheckBox();

		reportLog("6.1: Click on 'Include Automated' Check Box to select 'Include Automated' option");
		subjectDetailPage.clickOnIncludeAutomatedCheckBox();

		reportLog("6.2: Logout application");
		loginPage.logoutApplication();

		reportLog("6.3: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
