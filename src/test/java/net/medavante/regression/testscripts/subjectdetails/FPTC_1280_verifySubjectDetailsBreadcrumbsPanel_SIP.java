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

public class FPTC_1280_verifySubjectDetailsBreadcrumbsPanel_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1280_verifySubjectDetailsBreadcrumbsPanel_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("newStudyName");
		subjectName = properties.getProperty("Subject2609Test");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1280 Test Case Name:Subject details - breadcrumbs
	 * panel
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1280_SubjectDetailsBreadcrumbsPanel", groups = { "" })

	public void FPTC_1280_verifySubjectDetailsBreadcrumbsPanel() {

		reportLog("1.Log in to the Portal as User Pr#1.");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);

		reportLog("2:Navigate to the Subject Details page from Pr#1");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.1: Select study and site on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);


		reportLog("2.2: Select the subject");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.3:Verify Subject Details Displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.subjectScreeingNumber, subjectName);
		subjectDetailPage.verifyDetailStatus(Constants.siteLabelText,AT_PRODSiteCoordinatorUserName);

		reportLog(
				"3:Check the BreadCrumbs Panel and  verify Home page link, Breadcrumbs, Study Profile, Raters, Queries displayed");
		subjectDetailPage.verifyBreadCrumbsPanelIsDisplayed();

		reportLog("4:Select the Home page link");
		dashBoardPage = subjectDetailPage.navigateToHomePage();

		reportLog("4.1:	Home page is displayed");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("5.1:Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();

		reportLog("5.2: Select study PR#1  and site on study navigator ");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);
	

		reportLog("5.3:Navigate to the Subject Details page from Pr#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("5.4:Verify Subject details from PR#3 are displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.subjectScreeingNumber, subjectName);
		subjectDetailPage.verifyDetailStatus(Constants.siteLabelText,AT_PRODSiteCoordinatorUserName);

		reportLog("6.1:Select the Study Profile icon");
		subjectDetailPage.clickOnStudyProfileIcon();

		reportLog("6.2:Study Profile PR#1 details are displayed");
		subjectDetailPage.verifyStudyProfileDisplayedInSubjectDetailPage();

		reportLog("7:Select to close Study Profile details");
		subjectDetailPage.clickOnStudyProfileCollpaseButton();

		reportLog("7.1:	Subject details are displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.subjectScreeingNumber, subjectName);
		subjectDetailPage.verifyDetailStatus(Constants.siteLabelText,AT_PRODSiteCoordinatorUserName);

		reportLog("8.1:Select Raters icon");
		subjectDetailPage.clickOnRatersIcon();

		reportLog("8.2:	Raters PR#1 details are displayed");
		subjectDetailPage.verifyRatersDetailsPanelIsOpened();

		reportLog("9.1:	Select to close Raters details");
		subjectDetailPage.clickOnRatersCollpaseIcon();

		reportLog("9.2:	Subject details are displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.subjectScreeingNumber, subjectName);
		subjectDetailPage.verifyDetailStatus(Constants.siteLabelText,AT_PRODSiteCoordinatorUserName);

		reportLog("10.1:Select Queries icon");
		subjectDetailPage.clickOnQueriesIcon();

		reportLog("10.2:Queries PR#1 details are displayed");
		subjectDetailPage.verifyQueriesDetailsPanelIsOpened();

		reportLog("10.3:Select to close Queries");
		subjectDetailPage.clickOnQueriesCollpaseIcon();

		reportLog("10.4:Subject details are displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.verifyDetailStatus(Constants.subjectScreeingNumber, subjectName);
		subjectDetailPage.verifyDetailStatus(Constants.siteLabelText,AT_PRODSiteCoordinatorUserName);

		reportLog("10.5: Logout application");
		loginPage.logoutApplication();

		reportLog("10.6: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

	}
}
