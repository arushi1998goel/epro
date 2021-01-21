package net.medavante.smoke.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class CentralRatingTest_MAP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public CentralRatingTest_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("TestDataFile");
		studyName = properties.getProperty("CRStudy1928");
		subjectName=properties.getProperty("Rand001");
		
	}

	/**
	 * =========================================================================
	 * Test Case Id: TC_04 
	 * Test Case Name: CR Ratings List load and Filters displayed
	 * Inputs: User Name, Password,Subject Name
	 * 
	 * =========================================================================
	 *
	 */
	@Test(description = "Smoke TC-04--CR Ratings List load and Filters  displayed ", groups = { "smoke" })
	public void verifyCRRatingsListLoadAndFiltersdisplayed() {

		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);

		reportLog("Click on central rating tile");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("Load all the visit");
		centralRatingAssesmentListingPage.loadCompletePage();

		reportLog("Click on find subject icon ");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("Select Stdy from Study Dropdown, Site from SIte Dropdown and Subject ");
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(studyName, Constants.ATAssignedRater_10,
				subjectName);

		reportLog("Verify the reflection after selection of Study,Site and Subject ");
		centralRatingAssesmentListingPage.verifyChangeReflectionAfterSelectingStudyAndSubject(subjectName);

		reportLog("Log Out Application");
		loginPage.logoutApplication();

		reportLog("Verify Log Out Page of the application");
		loginPage.verifyUserLogout();

	}

	/**
	 * ========================================================================================
	 * Test Case Id: TC-05 Test Case Name: Central Ratings Task List Displayed
	 * Expected Condition: CR Task List Should load and open successfully.
	 * Inputs: User Name, Password,Subject Name
	 * 
	 * ========================================================================================
	 */
	@Test(description = "Smoke TC-05--Central Ratings Task  List Displayed", groups = { "smoke" })
	public void verifyCentralRatingListOpenAndDisplayed() {
		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport,AT_Password);

		reportLog("Click on central rating tile");
		centralRatingAssesmentListingPage = dashBoardPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("Load all the visit");
		centralRatingAssesmentListingPage.loadCompletePage();

		reportLog("Search " + subjectName + " in CR Assessments List");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();
		centralRatingAssesmentListingPage.clickOnSearchSubjects(subjectName);

		reportLog("Verify the Selected subject detail page");
		centralRatingAssesmentListingPage.verifyChangeReflectionAfterSelectingStudyAndSubject(subjectName);

		reportLog("Log Out Application");
		loginPage.logoutApplication();

		reportLog("Verify Log Out Page of the application");
		loginPage.verifyUserLogout();
	}

}
