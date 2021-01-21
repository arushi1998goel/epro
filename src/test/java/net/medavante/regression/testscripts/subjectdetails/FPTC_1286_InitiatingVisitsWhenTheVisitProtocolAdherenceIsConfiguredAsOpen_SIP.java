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

public class FPTC_1286_InitiatingVisitsWhenTheVisitProtocolAdherenceIsConfiguredAsOpen_SIP extends BaseTest {

	private String visitName1, visitName2,subjectName="AUTO_2645" + generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1286_InitiatingVisitsWhenTheVisitProtocolAdherenceIsConfiguredAsOpen_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
		visitName1 = properties.getProperty("Visit12645");
		visitName2 = properties.getProperty("VisitSecond2645");
		
		/*Creating Subject For Configuring Pre-requisite*/
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName,AT_PRODSiteCoordinatorUserName, subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPage.verifyNewSubjectDetailPage();
		loginPage.logoutApplication();
		/*Subject Created Successfully*/

	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1286 Test Case Name:Initiating Visits when the Visit
	 * Protocol Adherence is configured as Open
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1286_Initiating Visits when the Visit Protocol Adherence is configured as Open", groups = {
			"" })
	public void FPTC_1286_verifyInitiatingVisitsWhenTheVisitProtocolAdherenceIsConfiguredAsOpen() {

		reportLog("1.1:	Login to portal application as the Medavante User");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2:Navigate to the Subject details page of Pr#4");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.3: Select study and site on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName,AT_PRODSiteCoordinatorUserName);


		reportLog("1.4: Select the subject");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("1.5:Verify Subject Details Displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();
		subjectDetailPage.selectFilterFromVisitCategory(Constants.VisitCategoryFilter_All);

		reportLog("1.6:Visits of Pr#2 are displayed in the list");
		subjectDetailPage.verifyVisitPresentInCalenderVisitRow(visitName1);
		subjectDetailPage.verifyVisitPresentInCalenderVisitRow(visitName2);

		reportLog("2.1:Select the Visit of Pr#2.2");
		subjectDetailPage.clickOnCalendarVisitRow(visitName2);

		reportLog("2.2: Visit details section is displayed ");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();

		reportLog("2.3:Control to initiate visit is displayed");
		subjectDetailPage.verifyInitiateButtonIsDisplayed();

		reportLog("3.1:Select an action to initiate the visit of Pr#2.2");
		subjectDetailPage.clickOnInitiateVisitIcon();

		reportLog("3.2:	Visit of Pr#2.2 is initiated");
		subjectDetailPage.verifyVisitIsInitiated();

		reportLog("4.1:Select the Visit of PR#2.1");
		subjectDetailPage.clickOnCalendarVisitRow(visitName1);

		reportLog("4.2:Visit details section is displayed ");
		subjectDetailPage.verifyDetailsSectionIsdisplayed();

		reportLog("4.3:Control to initiate visit is displayed");
		subjectDetailPage.verifyInitiateButtonIsDisplayed();

		reportLog("5.1:Select an action to initiate the visit of Pr#2.1");
		subjectDetailPage.clickOnInitiateVisitIcon();

		reportLog("5.2:	Visit of Pr#2.1 is initiated");
		subjectDetailPage.verifyVisitIsInitiated();

		reportLog("5.3:Logout from the application");
		loginPage.logoutApplication();

		reportLog("5.4:Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
