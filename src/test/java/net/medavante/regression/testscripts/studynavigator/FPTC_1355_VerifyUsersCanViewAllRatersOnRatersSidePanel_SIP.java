package net.medavante.regression.testscripts.studynavigator;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;

public class FPTC_1355_VerifyUsersCanViewAllRatersOnRatersSidePanel_SIP extends BaseTest {

	private String studyName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1355_VerifyUsersCanViewAllRatersOnRatersSidePanel_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1355 Test Case Name: User can view all the raters on
	 * rater's side panel when the list goes outside of visible area
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1355_User can view all the raters on rater's side panel when the list goes outside of visible area ", groups = {})
	public void FPTC_1355_VerifyUsersCanViewAllRatersOnRatersSidePanel() {

		reportLog("1.1 :Login to Site Portal");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);

		reportLog("1.2 : Verify Login successful");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2 : Navigate to the study dashboard and select the study of Pr#1");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("2.1: Study Dashboard is visible");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("2.2: Rater's side panel control displays the raters count");
		studyNavigatorDashBoardPage.verifyRatersOptionDisplay();
		String ratersCounts = studyNavigatorDashBoardPage.ratersCountValue();
		System.out.println(ratersCounts);

		reportLog("3: Select the rater's side panel control");
		studyNavigatorDashBoardPage.clickOnRatersIcon();

		reportLog("3.1: Rater's count matches with that of Step");
		String ratersCountsOnSlide = studyNavigatorDashBoardPage.ratersCountValueOnSlide();
		System.out.println(ratersCountsOnSlide);

		reportLog("3.2: The list contains all the raters of Pr#4");
		
		studyNavigatorDashBoardPage.verifyRatersDetailsPanelIsOpened();
		studyNavigatorDashBoardPage.verifyRatersListCount(ratersCounts);
		
		reportLog("3.3:Logout application");
		loginPage.logoutApplication();

		reportLog("3.4:Verify user is logout");
		loginPage.verifyUserLogout();
		

	}

}
