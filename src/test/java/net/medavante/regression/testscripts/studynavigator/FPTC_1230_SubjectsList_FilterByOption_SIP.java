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

public class FPTC_1230_SubjectsList_FilterByOption_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1230_SubjectsList_FilterByOption_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("questionnaireStudy");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1230 || Test Case Name: Subjects List >> Filter By
	 * option
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1230 : Subjects List >> Filter By option ", groups = { "" })

	public void FPTC_1230_SubjectsList_FilterByOption() throws Exception {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("1.3: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog(
				"1.4: Verify 'Filter by' block is displayed with " + Constants.CategoryFilter_All + "  filter title.");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterList("", Constants.CategoryFilter_All);

		reportLog("1.5: Verify " + Constants.CategoryFilter_All + " filter is selected by default.");
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsSelectedFilterList("", Constants.CategoryFilter_All);

		reportLog("1.6: Verify 'Filter by' block is displayed with " + Constants.Subjects_FilterBlock_SubjectStatus
				+ "  filter with filter Item lists - " + Constants.Subjects_FilterListUnderSubjectStatus);
		studyNavigatorDashBoardPage.verifyFilterBlockNameAndContainingAllItsFilterList(
				Constants.Subjects_FilterBlock_SubjectStatus, Constants.Subjects_FilterListUnderSubjectStatus);

//		reportLog("1.7: Verify 'Filter by' block is displayed with " + Constants.Dates_FilterBy
//				+ "  filter with filter Item lists - " + Constants.Subjects_FilterListUnderDates);
//		studyNavigatorDashBoardPage.verifyFilterBlockNameAndContainingAllItsFilterList(Constants.Dates_FilterBy,
//				Constants.Subjects_FilterListUnderDates);
//
//		reportLog("1.8: Verify 'Filter by' block is displayed with " + Constants.Other_FilterBy
//				+ "  filter with filter Item - " + Constants.CategoryFilter_PINLocked);
//		studyNavigatorDashBoardPage.verifyFilterBlockNameAndItsFilterList(Constants.Other_FilterBy,
//				Constants.CategoryFilter_PINLocked);

		reportLog(
				"2.1: Verify count of subjects that are displayed match the count of subjects in brackets near the filter option - "
						+ Constants.CategoryFilter_All);
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("2.2: Select " + Constants.Subjects_FilterBlock_SubjectStatus + " option for "
				+ Constants.Subjects_FilterListUnderSubjectStatus.get(0) + " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Subjects_FilterBlock_SubjectStatus,
				Constants.Subjects_FilterListUnderSubjectStatus.get(0));

		reportLog(
				"2.3: Verify count of subjects that are displayed match the count of subjects in brackets near the filter option - "
						+ Constants.Subjects_FilterListUnderSubjectStatus.get(0));
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("2.4: Select " + Constants.Subjects_FilterBlock_SubjectStatus + " option for "
				+ Constants.Subjects_FilterListUnderSubjectStatus.get(1) + " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Subjects_FilterBlock_SubjectStatus,
				Constants.Subjects_FilterListUnderSubjectStatus.get(1));

		reportLog(
				"2.5: Verify count of subjects that are displayed match the count of subjects in brackets near the filter option - "
						+ Constants.Subjects_FilterListUnderSubjectStatus.get(1));
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("2.6: Select " + Constants.Subjects_FilterBlock_SubjectStatus + " option for "
				+ Constants.Subjects_FilterListUnderSubjectStatus.get(2) + " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Subjects_FilterBlock_SubjectStatus,
				Constants.Subjects_FilterListUnderSubjectStatus.get(2));

		reportLog(
				"2.7: Verify count of subjects that are displayed match the count of subjects in brackets near the filter option - "
						+ Constants.Subjects_FilterListUnderSubjectStatus.get(2));
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("2.8: Select " + Constants.Subjects_FilterBlock_SubjectStatus + " option for "
				+ Constants.Subjects_FilterListUnderSubjectStatus.get(3) + " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Subjects_FilterBlock_SubjectStatus,
				Constants.Subjects_FilterListUnderSubjectStatus.get(3));

		reportLog(
				"2.9: Verify count of subjects that are displayed match the count of subjects in brackets near the filter option - "
						+ Constants.Subjects_FilterListUnderSubjectStatus.get(3));
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("2.10: Select " + Constants.Subjects_FilterBlock_SubjectStatus + " option for "
				+ Constants.Subjects_FilterListUnderSubjectStatus.get(4) + " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Subjects_FilterBlock_SubjectStatus,
				Constants.Subjects_FilterListUnderSubjectStatus.get(4));

		reportLog(
				"2.11: Verify count of subjects that are displayed match the count of subjects in brackets near the filter option - "
						+ Constants.Subjects_FilterListUnderSubjectStatus.get(4));
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("2.12: Select " + Constants.Subjects_FilterBlock_SubjectStatus + " option for "
				+ Constants.Subjects_FilterListUnderSubjectStatus.get(5) + " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Subjects_FilterBlock_SubjectStatus,
				Constants.Subjects_FilterListUnderSubjectStatus.get(5));

		reportLog(
				"2.13: Verify count of subjects that are displayed match the count of subjects in brackets near the filter option - "
						+ Constants.Subjects_FilterListUnderSubjectStatus.get(5));
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("2.14: Select " + Constants.Dates_FilterBy + " option for "
				+ Constants.Subjects_FilterListUnderDates.get(0) + " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Dates_FilterBy,
				Constants.Subjects_FilterListUnderDates.get(0));

		reportLog(
				"2.15: Verify count of subjects that are displayed match the count of subjects in brackets near the filter option - "
						+ Constants.Subjects_FilterListUnderDates.get(0));
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("2.16: Select " + Constants.Dates_FilterBy + " option for "
				+ Constants.Subjects_FilterListUnderDates.get(1) + " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Dates_FilterBy,
				Constants.Subjects_FilterListUnderDates.get(1));

		reportLog(
				"2.17: Verify count of subjects that are displayed match the count of subjects in brackets near the filter option - "
						+ Constants.Subjects_FilterListUnderDates.get(1));
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("2.18: Select " + Constants.Other_FilterBy + " option for " + Constants.CategoryFilter_PINLocked
				+ " filter");
		studyNavigatorDashBoardPage.selectFilterFromListsBlock(Constants.Other_FilterBy,
				Constants.CategoryFilter_PINLocked);

		reportLog(
				"2.19: Verify count of subjects that are displayed match the count of subjects in brackets near the filter option - "
						+ Constants.CategoryFilter_PINLocked);
		studyNavigatorDashBoardPage.verifyCountOfSelectedFilterOptionEqualsToTotalCountAvailableInGrid();

		reportLog("2.20: Logout application");
		loginPage.logoutApplication();

		reportLog("2.21: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
