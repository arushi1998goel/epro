package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1231_SubjectsList_Pagination_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1231_SubjectsList_Pagination_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("AutomationStudyName");

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1231 || Test Case Name: Subjects List >> Pagination
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1231 : Subjects List >> Pagination", groups = { "" })

	public void FPTC_1231_SubjectsList_Pagination() throws Exception {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("1.3: Select study");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("1.4: Verify page is displayed with pagination");
		studyNavigatorDashBoardPage.verifyPageDisplayedWithPagination();

		reportLog("1.5: Verify page is displayed with page count based on default count value: "
				+ Constants.FooterPageNumber_1);
		studyNavigatorDashBoardPage.verifyPageDisplayedWithPageCountValue(Constants.FooterPageNumber_1);

		reportLog("2.1: Click on particular page number: " + Constants.FooterPageNumber_2);
		studyNavigatorDashBoardPage.selectPageNumber(Constants.FooterPageNumber_2);

		reportLog("2.2: Verify page displayed with its data in grid table");
		studyNavigatorDashBoardPage.verifyDataAvailableUnderTableList();

		reportLog("3.1: Click on single forward arrow (Next page) and verify Next page  is displayed");
		studyNavigatorDashBoardPage.clickOnSingleForwardArrowAndVerifyNextPageDisplayed();

		reportLog("3.2: Verify page displayed with its data in grid table");
		studyNavigatorDashBoardPage.verifyDataAvailableUnderTableList();

		reportLog("3.3: Click on single backward arrow (Previous page) and verify Previous page  is displayed");
		studyNavigatorDashBoardPage.clickOnSingleBackwardArrowAndVerifyPreviousPageDisplayed();

		reportLog("3.3: Verify page displayed with its data in grid table");
		studyNavigatorDashBoardPage.verifyDataAvailableUnderTableList();

		reportLog("4.1: Select option with the number of subjects to display: " + "30");
		studyNavigatorDashBoardPage.selectOptionWithNumberOfSubjectsToDisplay("30");

		reportLog("4.2: Verify Subjects List page is displayed with selected number of subjects to display");
		studyNavigatorDashBoardPage.verifySubjectListDisplayedInGridEqualsToSelectedItemsPerPage();

		reportLog("4.3: Logout application");
		loginPage.logoutApplication();

		reportLog("4.4: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
