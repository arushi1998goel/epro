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

public class FPTC_1226_LayoutAndInteractingWithSubjectsList_SIP extends BaseTest {

	private String study;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1226_LayoutAndInteractingWithSubjectsList_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		study = properties.getProperty("AutomationStudyNameNonCR");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1226 Test Case Name: Layout and interacting with
	 * Subjects List
	 * ====================================================================================================================
	 * 
	 * @throws Exception
	 */

	@Test(description = "FP-TC-1226 : Layout and interacting with Subjects List   ", groups = { "" })

	public void FPTC_1226_LayoutAndInteractingWithSubjectsList() throws Exception {

		reportLog("1.1: Log in to the Site Portal");
		dashBoardPage = loginPage.siteLogin(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);


//		reportLog("1.3: Verify Option to select a study is available");
//		studyNavigatorDashBoardPage.verifyOptionToSelectStudyButtonIsDisplayed();

		reportLog("1.4:  Select study and Verify select button if it is enabled  ");
		studyNavigatorDashBoardPage.selectStudy(study,Constants.ATAssignedRater_10);

		reportLog("1.5: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Subject);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Subject);

		reportLog("1.6: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Language);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Language);

		reportLog("1.7: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.StudyDashBoard_columnName_Status);

		reportLog(
				"1.8: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Questionnaires_MenuItem
						+ "  with sub-column -" + Constants.StudyDashBoard_SubcolumnName_OnTime);
		studyNavigatorDashBoardPage.verifyColumnAndTheirContainingSubColumnsDisplayed(
				Constants.StudyDashBoard_columnName_Questionnaires_MenuItem,
				Constants.StudyDashBoard_SubcolumnName_OnTime);

		reportLog(
				"1.9: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Questionnaires_MenuItem
						+ "  with sub-column -" + Constants.StudyDashBoard_SubcolumnName_Late);
		studyNavigatorDashBoardPage.verifyColumnAndTheirContainingSubColumnsDisplayed(
				Constants.StudyDashBoard_columnName_Questionnaires_MenuItem,
				Constants.StudyDashBoard_SubcolumnName_Late);

		reportLog(
				"1.10: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Questionnaires_MenuItem
						+ "  with sub-column -" + Constants.StudyDashBoard_SubcolumnName_Expired);
		studyNavigatorDashBoardPage.verifyColumnAndTheirContainingSubColumnsDisplayed(
				Constants.StudyDashBoard_columnName_Questionnaires_MenuItem,
				Constants.StudyDashBoard_SubcolumnName_Expired);

		reportLog("1.11: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Visits
				+ "  with sub-column -" + Constants.StudyDashBoard_SubcolumnName_Complete);
		studyNavigatorDashBoardPage.verifyColumnAndTheirContainingSubColumnsDisplayed(
				Constants.StudyDashBoard_columnName_Visits, Constants.StudyDashBoard_SubcolumnName_Complete);

		reportLog("1.12: Verify Column Name Displayed as - " + Constants.StudyDashBoard_columnName_Visits
				+ "  with sub-column -" + Constants.StudyDashBoard_SubcolumnName_Editing);
		studyNavigatorDashBoardPage.verifyColumnAndTheirContainingSubColumnsDisplayed(
				Constants.StudyDashBoard_columnName_Visits, Constants.StudyDashBoard_SubcolumnName_Editing);

		reportLog("1.13: Verify Column Name Displayed as - " + Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.verifyColumnNameDisplayed(Constants.ColumnName_Site);

		reportLog("1.14: Verify 'Add new Subject' control is available ");
		studyNavigatorDashBoardPage.verifyAddSubjectBtnIsDisplayed();

		reportLog("1.15: Verify 'Refresh' control is available ");
		studyNavigatorDashBoardPage.verifyRefreshBtnIsDisplayed();

		reportLog("2.1: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Subject);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Subject);

		reportLog("2.2: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Language);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Language);

		reportLog("2.3: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_columnName_Status);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_columnName_Status);

		reportLog("2.4: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_SubcolumnName_OnTime);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_SubcolumnName_OnTime);

		reportLog("2.5: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_SubcolumnName_Late);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_SubcolumnName_Late);

		reportLog("2.6: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_SubcolumnName_Expired);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_SubcolumnName_Expired);

		reportLog("2.7: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_SubcolumnName_Complete);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_SubcolumnName_Complete);

		reportLog("2.8: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.StudyDashBoard_SubcolumnName_Editing);
		studyNavigatorDashBoardPage
				.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.StudyDashBoard_SubcolumnName_Editing);

		reportLog("2.9: Verify values can be sorted ascending/descending orders for column -"
				+ Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.verifyColumnValuesCanBeSortedInAscAndDesOrders(Constants.ColumnName_Site);

		reportLog(
				"3.1: Drag column " + Constants.StudyDashBoard_columnName_Language + " and drop to the grouping area");
		studyNavigatorDashBoardPage
				.dragColumnHeadersAndDropThemToGroupingArea(Constants.StudyDashBoard_columnName_Language);

		reportLog("3.2: Verify subjects are grouped by the selected column -"
				+ Constants.StudyDashBoard_columnName_Language);
		studyNavigatorDashBoardPage
				.verifyDraggedColumnIsGroupedInGroupingArea(Constants.StudyDashBoard_columnName_Language);

		reportLog("3.3: Drag column " + Constants.ColumnName_Site + " and drop to the grouping area");
		studyNavigatorDashBoardPage.dragColumnHeadersAndDropThemToGroupingArea(Constants.ColumnName_Site);

		reportLog("3.4: Verify subjects are grouped by the selected column -" + Constants.ColumnName_Site);
		studyNavigatorDashBoardPage.verifyDraggedColumnIsGroupedInGroupingArea(Constants.ColumnName_Site);

		reportLog("3.5: Delete grouped items " + Constants.ColumnName_Site + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.ColumnName_Site);

		reportLog("3.6: Delete grouped items " + Constants.StudyDashBoard_columnName_Language + " from grouping area");
		studyNavigatorDashBoardPage.removeGroupedColumnFromGroupingArea(Constants.StudyDashBoard_columnName_Language);

		reportLog("4.1: Verify Subject list is displayed in the default view");
		studyNavigatorDashBoardPage.verifyValuesUnderColumnDisplayedInDefaultView();
		
		reportLog("5.1: Navigate to Clinicians / Raters grid");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.virgilUniversitySubTab);
		
		reportLog("5.2:  Select study ");
		studyNavigatorDashBoardPage.selectStudy(study,Constants.ATAssignedRater_10);
		
		reportLog("5.3: Clinicians / Raters list Tracking grid is displayed");
		studyNavigatorDashBoardPage.verifyTrackingTabDisplayed();
		
		reportLog("5.4: Export and Columns filter options are displayed at the top navigation");
		studyNavigatorDashBoardPage.verifyExport_ColumnsfilterButtondisplayed();

		reportLog("5.5: Logout application");
		loginPage.logoutApplication();

		reportLog("5.6: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
