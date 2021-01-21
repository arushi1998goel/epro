package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1291_LayoutAndInteractingWithVisitsList_SIP extends BaseTest {

	private String study;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1291_LayoutAndInteractingWithVisitsList_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("AutomationStudyNameCR");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1291 Test Case Name: Layout and interacting with
	 * Visits List
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1291 : Layout and interacting with Visits List", groups = { "" })

	public void FPTC_1291_LayoutAndInteractingWithVisitsList() throws Exception {

		reportLog("1.1: Log in to the Site Portal");
		dashBoardPage = loginPage.siteLogin(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);
//		reportLog("1.3: Verify Option to select a study is available");
//		studyNavigatorDashBoardPage.verifyOptionToSelectStudyButtonIsDisplayed();

		reportLog("1.4: Select study and Verify select button if it is enabled");
		studyNavigatorDashBoardPage.selectStudy(study,Constants.ATAssignedRater_10);

		reportLog("1.5: Navigate to Visit list");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("1.6: Verify Visit list is displayed");
		studyNavigatorDashBoardPage.verifyVisitListIsOpened();

		reportLog("1.7: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Visit);

		reportLog("1.8: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_SVID);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_SVID);

		reportLog("1.9: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Date);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Date);

		reportLog("1.10: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Status);

		reportLog("1.11: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Subject);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Subject);

		reportLog("1.12: Verify Column Name Displayed as - " + Constants.siteLabelText);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.siteLabelText);

		reportLog("1.13: Verify 'Refresh' option is available");
		studyNavigatorDashBoardPage.verifyRefreshBtnIsDisplayed();

		reportLog("2.1: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Visit);

		reportLog("2.2: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_SVID);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_SVID);

		reportLog("2.3: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Date);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Date);

		reportLog("2.4: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Status);

		reportLog("2.5: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Subject);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Subject);

		reportLog(
				"2.6: Verify values can be sorted ascending/descending orders for column -" + Constants.siteLabelText);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.siteLabelText);

		reportLog("3.1: Drag column " + Constants.StudyDashBoard_columnName_Visit + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog("3.2: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog("3.4: Drag column " + Constants.StudyDashBoard_columnName_Date + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Date);

		reportLog(
				"3.5: Verify subjects are grouped by the selected column -" + Constants.StudyDashBoard_columnName_Date);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Date);

		reportLog("3.6: Drag column " + Constants.StudyDashBoard_columnName_Status + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("3.7: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("4.1: Delete grouped items " + Constants.StudyDashBoard_columnName_Status + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("4.2: Delete grouped items " + Constants.StudyDashBoard_columnName_Date + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Date);

		reportLog("4.3: Delete grouped items " + Constants.StudyDashBoard_columnName_Visit + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog("4.4: Verify Subject list is displayed in the default view");
		studyNavigatorDashBoardPage.verifyValuesUnderColumnDisplayedInDefaultView();

		reportLog("4.5: Logout application");
		loginPage.logoutApplication();

		reportLog("4.6: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
