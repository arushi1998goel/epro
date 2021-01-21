package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1435_StudyNavigator_Messages_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1435_StudyNavigator_Messages_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1435 Test Case Name: Study Navigator - Messages
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1435_Study Navigator - Messages ", groups = { "" })

	public void FPTC_1435_StudyNavigator_Messages() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("2.2: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("3.1:Select Messages menu item");
		studyNavigatorDashBoardPage.navigateToMessagesListing();

		reportLog("4.1: Verify 'Filter by' block is displayed with " + Constants.Messages_Inbox
				+ "  filter with following Item title " + Constants.CategoryFilter_All);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterList(Constants.Messages_Inbox,
				Constants.CategoryFilter_All);

		reportLog("4.2: Verify " + Constants.Messages_Inbox + " filter is displyed with default selected item "
				+ Constants.CategoryFilter_All);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsSelectedFilterList(Constants.Messages_Inbox,
				Constants.CategoryFilter_All);

		reportLog("4.3: Verify 'Filter by' block is displayed with " + Constants.Messages_Inbox
				+ "  filter with following Item title " + Constants.CategoryFilter_Today);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterList(Constants.Messages_Inbox,
				Constants.CategoryFilter_Today);

		reportLog("4.4: Verify 'Filter by' block is displayed with " + Constants.Messages_Inbox
				+ "  filter with following Item title " + Constants.CategoryFilter_PastWeek);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterList(Constants.Messages_Inbox,
				Constants.CategoryFilter_PastWeek);

		reportLog("4.5: Verify 'Filter by' block is displayed with " + Constants.Messages_Inbox
				+ "  filter with following Item title " + Constants.MessageCategoryFilter_Unread);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterList(Constants.Messages_Inbox,
				Constants.MessageCategoryFilter_Unread);

		reportLog("4.6: Verify 'Filter by' block is displayed with " + Constants.Messages_SentItems
				+ "  filter with following Item title " + Constants.CategoryFilter_All);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterList(Constants.Messages_SentItems,
				Constants.CategoryFilter_All);

		// reportLog("4.7: Verify " + Constants.Messages_SentItems + " filter is
		// displyed with default selected item "
		// + Constants.MedicationCategoryFilter_All);
		// studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsSelectedFilterList(Constants.Messages_SentItems,
		// Constants.MedicationCategoryFilter_All);

		reportLog("4.8: Verify 'Filter by' block is displayed with " + Constants.Messages_SentItems
				+ "  filter with following Item title " + Constants.CategoryFilter_Today);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterList(Constants.Messages_SentItems,
				Constants.CategoryFilter_Today);

		reportLog("4.9: Verify 'Filter by' block is displayed with " + Constants.Messages_SentItems
				+ "  filter with following Item title " + Constants.CategoryFilter_PastWeek);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterList(Constants.Messages_SentItems,
				Constants.CategoryFilter_PastWeek);

		reportLog(
				"5.1:Select " + Constants.CategoryFilter_Today + " option for " + Constants.Messages_Inbox + " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Messages_Inbox,
				Constants.CategoryFilter_Today);

		reportLog("5.2: Verify List header is displayed " + Constants.CategoryFilter_Today);
		studyNavigatorDashBoardPage
				.verifyBlockHeaderIsDisplayedAccordingToFilterSelected(Constants.CategoryFilter_Today);

		reportLog("5.3: Verify List block is displayed according to the selected " + Constants.CategoryFilter_Today
				+ " filter");
		studyNavigatorDashBoardPage
				.verifyAvailableListBlockDisplayedUnderSelectedTimeSpan(Constants.CategoryFilter_Today);

		reportLog("6.1:Select " + Constants.CategoryFilter_PastWeek + " option for " + Constants.Messages_Inbox
				+ " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Messages_Inbox,
				Constants.CategoryFilter_PastWeek);

		reportLog("6.2: Verify List header is displayed " + Constants.CategoryFilter_PastWeek);
		studyNavigatorDashBoardPage
				.verifyBlockHeaderIsDisplayedAccordingToFilterSelected(Constants.CategoryFilter_PastWeek);

		reportLog("6.3: Verify List block is displayed according to the selected " + Constants.CategoryFilter_PastWeek
				+ " filter");
		studyNavigatorDashBoardPage
				.verifyAvailableListBlockDisplayedUnderSelectedTimeSpan(Constants.CategoryFilter_PastWeek);

		reportLog("7.1:Select " + Constants.MessageCategoryFilter_Unread + " option for " + Constants.Messages_Inbox
				+ " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Messages_Inbox,
				Constants.MessageCategoryFilter_Unread);

		reportLog("7.2: Verify List header is displayed " + Constants.MessageCategoryFilter_Unread);
		studyNavigatorDashBoardPage
				.verifyBlockHeaderIsDisplayedAccordingToFilterSelected(Constants.MessageCategoryFilter_Unread);

		reportLog("8.1: Refresh the page");
		studyNavigatorDashBoardPage.refreshPage();

		reportLog("8.2: Verify " + Constants.Messages_Inbox + " filter is displyed with selected item "
				+ Constants.MessageCategoryFilter_Unread);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsSelectedFilterList(Constants.Messages_Inbox,
				Constants.MessageCategoryFilter_Unread);

		reportLog("9.1:Select " + Constants.CategoryFilter_Today + " option for " + Constants.Messages_SentItems
				+ " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Messages_SentItems,
				Constants.CategoryFilter_Today);

		reportLog("9.2: Verify List header is displayed " + Constants.CategoryFilter_Today);
		studyNavigatorDashBoardPage
				.verifyBlockHeaderIsDisplayedAccordingToFilterSelected(Constants.CategoryFilter_Today);

		reportLog("9.3: Verify List block is displayed according to the selected " + Constants.CategoryFilter_Today
				+ " filter");
		studyNavigatorDashBoardPage
				.verifyAvailableListBlockDisplayedUnderSelectedTimeSpan(Constants.CategoryFilter_Today);

		reportLog("10.1:Select " + Constants.CategoryFilter_PastWeek + " option for " + Constants.Messages_SentItems
				+ " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Messages_SentItems,
				Constants.CategoryFilter_PastWeek);

		reportLog("10.2: Verify List header is displayed " + Constants.CategoryFilter_PastWeek);
		studyNavigatorDashBoardPage
				.verifyBlockHeaderIsDisplayedAccordingToFilterSelected(Constants.CategoryFilter_PastWeek);

		reportLog("10.3: Verify List block is displayed according to the selected " + Constants.CategoryFilter_PastWeek
				+ " filter");
		studyNavigatorDashBoardPage
				.verifyAvailableListBlockDisplayedUnderSelectedTimeSpan(Constants.CategoryFilter_PastWeek);

		reportLog("11.1: Verify Column Name Displayed as - " + Constants.ColumnName_Message);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.ColumnName_Message);

		reportLog("11.2: Verify Column Name Displayed as - " + Constants.ColumnName_DateTime);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.ColumnName_DateTime);

		reportLog("11.3: Verify Column Name Displayed as - " + Constants.Recipient_MessageModelWindow_Subject);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.Recipient_MessageModelWindow_Subject);

		reportLog("11.4: Verify Column Name Displayed as - " + Constants.ColumnName_From);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.ColumnName_From);

		reportLog("11.5: Verify Column Name Displayed as - " + Constants.ColumnName_Flag);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.ColumnName_Flag);

		reportLog("11.6: Verify Column Name Displayed as - " + Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.ColumnName_Site);

		reportLog("12.1: Select Message title present in the first row");
		studyNavigatorDashBoardPage.selectValueUnderDesiredColumn(StudyDashBoardPage.class,
				Constants.ColumnName_Message, "");

		reportLog("12.2: Verify Message details screen is displayed");
		studyNavigatorDashBoardPage.verifyEmailModalWindow();

		reportLog("12.3: Click on model window close button");
		
		studyNavigatorDashBoardPage.clickOnEmailModalWindowCloseButton();

		reportLog("13.1: Select Subject");
  		subjectDetailPage = studyNavigatorDashBoardPage.selectValueUnderDesiredColumn(NewSubjectDetailPage.class,
				Constants.Recipient_MessageModelWindow_Subject, "");

		reportLog("13.4:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("13.5: Select 'Messages' from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Message);

		reportLog("13.6: Verify the list of message threads in inbox");
		subjectDetailPage.verifyPresenceOfMessageInMessageList();

		reportLog("14.1: Navigate to study navigator");
		subjectDetailPage.navigateBack();

		reportLog("14.2: Verify " + Constants.Messages_SentItems + " filter is displyed with selected item "
				+ Constants.CategoryFilter_PastWeek);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsSelectedFilterList(Constants.Messages_SentItems,
				Constants.CategoryFilter_PastWeek);

		reportLog("14.3: Logout application");
		loginPage.logoutApplication();

		reportLog("14.4: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
