package net.medavante.regression.testscripts.r1suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.preqqualification.PreQualificationDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_4523PreQualificationRaterScaleListTest_MAP extends BaseTest {
	
	String raterName,siteName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_4523PreQualificationRaterScaleListTest_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop=Configuration.readTestData("RegressionTestData");
		studyName=prop.getProperty("PreQualificationStudy");
		raterName=prop.getProperty("RaterName1670");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: SFPTC_4523 Test Case Name:View Pre-Qualification Rater
	 * Scale List
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_4523_Pre-Qualification Rater Scale List", groups = { "R1" })
	
	public void FPTC_4523_verifyPrequalificationRaterScaleList() {
		reportLog("1:Login in to application");
	    dashBoardPage = loginPage.loginInApplication(AT_PRODProjectManager, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1:Click on Pre-Qualifications tile");
		preQualificationDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(PreQualificationDashBoardPage.class, Constants.NavigateText, Constants.QualificationText);

		reportLog("2:Click on mode view button to to view Pre-qualification as Rater Scale List");
		preQualificationDashBoardPage.changeViewMode();

		reportLog("3:Select " + studyName + " from the study drop down");
		preQualificationDashBoardPage.selectStudy(studyName);

		reportLog("3:Select siteName from the the drop down");
		preQualificationDashBoardPage.selectSite(Constants.ATAssignedRater_10);
        preQualificationDashBoardPage.clickOnRefreshButton();
		
		reportLog("2:Verify Pre Qualification Rater Scale List view Is Displayed");
		preQualificationDashBoardPage.verifyPreQualificationRaterScaleListIsDisplayed();

		reportLog("4:Verify Lables In The Page");
		preQualificationDashBoardPage.verifyPreQualificationRaterLabelPresent();

		reportLog("5:Verify Rater Column Values");
		preQualificationDashBoardPage.verifyPreQualificationRaterScaleListRaterColumnValues(raterName);

		reportLog("6:Verify Site Column Values");
		preQualificationDashBoardPage.verifyPreQualificationRaterScaleListSiteColumnValues(Constants.ATAssignedRater_10);

		reportLog("7:Verify the results showing for individual scales");
		preQualificationDashBoardPage.verifyraterScaleResultValues();

		reportLog("8:Verify The Filters");
		preQualificationDashBoardPage.verifyRaterFiltersPresent();

		reportLog("9:Verify The Scale DropDowns");
		preQualificationDashBoardPage.verifyRaterScaleDropdownValues();

		reportLog("10:Select Multiple Checkboxes and Verify Multiple CheckBoxe Is Selected");
		preQualificationDashBoardPage.selectRaterCheckBoxesCAndVerifyMultipleCheckBoxesIsSelected();

		reportLog("11:Rearrange Parent Scale");
		preQualificationDashBoardPage.reArrangeRaterScales();

		reportLog("12:Verify Refresh Button Visible");
		preQualificationDashBoardPage.raterRefreshtButtonIsPresent();

		reportLog("13:Verify Reset Button Visible");
		preQualificationDashBoardPage.raterResetButtonIsPresent();

		reportLog("14:Logout application");
		loginPage.logoutApplication();

		reportLog("15:Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
