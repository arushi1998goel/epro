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

public class FPTC_1293_InteractingWithAssessmentsList_MAP extends BaseTest {

	private String study;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1293_InteractingWithAssessmentsList_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("AutomationStudyName");
	} 

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1293 || Test Case Name: Layout and interacting with
	 * Assessments List
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1293 : Layout and interacting with Assessments List", groups = { "" })

	public void FPTC_1293_LayoutAndInteractingWithAssessmentsList() throws Exception {

		reportLog("1.1: Log in to the MA Portal");
		dashBoardPage = loginPage.loginInApplication(AT_PRODProjectCoordinator, AT_Password);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.4: Select study and Verify select button if it is enabled");
		studyNavigatorDashBoardPage.selectStudy(study,Constants.ATAssignedRater_10);

		reportLog("1.5: Navigate to Assessments list");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("1.6: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("1.7: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Assessment);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("1.8: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Type);

		reportLog("1.9: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Status);

		reportLog("1.10: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_AssignedTo);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_AssignedTo);

		reportLog("1.11: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Visit);

		reportLog("1.12: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Date);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Date);

		reportLog("1.13: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_SVID);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_SVID);

		reportLog("1.14: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Subject);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Subject);

		reportLog("1.15: Verify Column Name Displayed as - " + Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.ColumnName_Site);

		reportLog("1.16: Verify 'Refresh' option is available");
		studyNavigatorDashBoardPage.verifyRefreshBtnIsDisplayed();

		reportLog("2.1: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Assessment);
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("2.2: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Type);

		reportLog("2.3: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Status);

		reportLog("2.4: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_AssignedTo);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_AssignedTo);

		reportLog("2.5: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Visit);

		reportLog("2.6: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Date);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Date);

		reportLog("2.7: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_SVID);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_SVID);

		reportLog("2.8: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Subject);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Subject);

		reportLog("2.9: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();

		reportLog("3.1: Drag column " + Constants.StudyDashBoard_columnName_Assessment
				+ " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("3.2: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Assessment);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("3.3: Drag column " + Constants.StudyDashBoard_columnName_Visit + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog("3.4: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog("3.5: Drag column " + Constants.StudyDashBoard_columnName_Type + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Type);

		reportLog(
				"3.6: Verify subjects are grouped by the selected column -" + Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Type);

		reportLog("3.7: Drag column " + Constants.StudyDashBoard_columnName_Status + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("3.8: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("4.1: Delete grouped items " + Constants.StudyDashBoard_columnName_Status + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("4.2: Delete grouped items " + Constants.StudyDashBoard_columnName_Type + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Type);

		reportLog("4.3: Delete grouped items " + Constants.StudyDashBoard_columnName_Visit + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog(
				"4.3: Delete grouped items " + Constants.StudyDashBoard_columnName_Assessment + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("4.4: Verify Subject list is displayed in the default view");
		studyNavigatorDashBoardPage.verifyValuesUnderColumnDisplayedInDefaultView();

		reportLog("4.5: Logout application");
		loginPage.logoutApplication();

		reportLog("4.6: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		reportLog("5.1: Log in to the Sponsor Portal");
		loginPage.sponsorLogin(AT_PRODSponsorUserType3, AT_Password);

		reportLog("5.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("5.4: Select study and Verify select button if it is enabled");
		studyNavigatorDashBoardPage.selectStudy(study,Constants.ATAssignedRater_10);

		reportLog("5.5: Navigate to Assessments list");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("5.6: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("5.7: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Assessment);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("5.8: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Type);

		reportLog("5.9: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Status);

		reportLog("5.10: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_AssignedTo);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_AssignedTo);

		reportLog("5.11: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Visit);

		reportLog("5.12: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Date);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Date);

		reportLog("5.13: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_SVID);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_SVID);

		reportLog("5.14: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Subject);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Subject);

		reportLog("5.15: Verify Column Name Displayed as - " + Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.ColumnName_Site);

		reportLog("5.16: Verify 'Refresh' option is available");
		studyNavigatorDashBoardPage.verifyRefreshBtnIsDisplayed();

		reportLog("6.1: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Assessment);
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("6.2: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Type);

		reportLog("6.3: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Status);

		reportLog("6.4: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_AssignedTo);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_AssignedTo);

		reportLog("6.5: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Visit);

		reportLog("6.6: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Date);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Date);

		reportLog("6.7: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_SVID);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_SVID);

		reportLog("6.8: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Subject);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Subject);

		reportLog("6.9: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.ColumnName_Site);

		reportLog("7.1: Drag column " + Constants.StudyDashBoard_columnName_Assessment
				+ " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("7.2: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Assessment);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("7.5: Drag column " + Constants.StudyDashBoard_columnName_Type + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Type);

		reportLog(
				"7.6: Verify subjects are grouped by the selected column -" + Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Type);

		reportLog("7.7: Drag column " + Constants.StudyDashBoard_columnName_Status + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("7.8: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Status);
		
		reportLog("7.3: Drag column " + Constants.StudyDashBoard_columnName_Visit + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog("7.4: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Visit);


		reportLog("8.1: Delete grouped items " + Constants.StudyDashBoard_columnName_Status + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("8.2: Delete grouped items " + Constants.StudyDashBoard_columnName_Type + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Type);

		reportLog("8.3: Delete grouped items " + Constants.StudyDashBoard_columnName_Visit + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog("8.3: Delete grouped items " + Constants.StudyDashBoard_columnName_Assessment + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("8.4: Verify Subject list is displayed in the default view");
		studyNavigatorDashBoardPage.verifyValuesUnderColumnDisplayedInDefaultView();

		reportLog("8.5: Logout application");
		loginPage.logoutApplication();

		reportLog("8.6: Verify user is at logout Page");
		loginPage.verifyUserLogout();

		reportLog("9.1: Log in to the Site Portal");
		dashBoardPage=loginPage.siteLogin(AT_PRODSiteCoordinator, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();	

		reportLog("9.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("9.4: Select study and Verify select button if it is enabled");
		studyNavigatorDashBoardPage.selectStudy(study,Constants.ATAssignedRater_10);

		reportLog("9.5: Navigate to Assessments list");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("9.6: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("9.7: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Assessment);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("9.8: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Type);

		reportLog("9.9: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Status);

		reportLog("9.10: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_AssignedTo);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_AssignedTo);

		reportLog("9.11: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Visit);

		reportLog("9.12: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Date);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Date);

		reportLog("9.13: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_SVID);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_SVID);

		reportLog("9.14: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Subject);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Subject);

		reportLog("9.15: Verify Column Name Displayed as - " + Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.ColumnName_Site);

		reportLog("9.16: Verify 'Refresh' option is available");
		studyNavigatorDashBoardPage.verifyRefreshBtnIsDisplayed();

		reportLog("10.1: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Assessment);
		studyNavigatorDashBoardPage.expandAndCollapseSidePanel();
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("10.2: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Type);

		reportLog("10.3: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Status);

		reportLog("10.4: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_AssignedTo);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_AssignedTo);

		reportLog("10.5: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Visit);

		reportLog("10.6: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Date);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Date);

		reportLog("10.7: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_SVID);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_SVID);

		reportLog("10.8: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Subject);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Subject);

		reportLog("10.9: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.ColumnName_Site);

		reportLog("11.1: Drag column " + Constants.StudyDashBoard_columnName_Assessment
				+ " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("11.2: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Assessment);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("11.3: Drag column " + Constants.StudyDashBoard_columnName_Visit + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog("11.4: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Visit);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog("11.5: Drag column " + Constants.StudyDashBoard_columnName_Type + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Type);

		reportLog("11.6: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Type);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Type);

		reportLog("11.7: Drag column " + Constants.StudyDashBoard_columnName_Status + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("11.8: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("12.1: Delete grouped items " + Constants.StudyDashBoard_columnName_Status + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Status);

		reportLog("12.2: Delete grouped items " + Constants.StudyDashBoard_columnName_Type + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Type);

		reportLog("12.3: Delete grouped items " + Constants.StudyDashBoard_columnName_Visit + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Visit);

		reportLog("12.3: Delete grouped items " + Constants.StudyDashBoard_columnName_Assessment + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Assessment);

		reportLog("12.4: Verify Subject list is displayed in the default view");
		studyNavigatorDashBoardPage.verifyValuesUnderColumnDisplayedInDefaultView();

		reportLog("12.5: Logout application");
		loginPage.logoutApplication();

		reportLog("12.6: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
