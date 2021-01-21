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

public class FPTC_1166_SubjectDetailsStudyNameOpensStudyProfile_SIP extends BaseTest {

	protected String subjectName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1166_SubjectDetailsStudyNameOpensStudyProfile_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("studyForEnrolledStatus");
		subjectName=properties.getProperty("Subject_2385");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1166 Test Case Name:Subject details - Study name opens Study Profile
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1166_SubjectDetailsStudyNameOpensStudyProfile", groups = { "" })

	public void FPTC_1166_verifySubjectDetailsStudyNameOpensStudyProfile() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("2.2: Select configured study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName, Constants.ATAssignedRater_10);
		
		reportLog("2.3:Select Subject Pr#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.4:Verify Subject Details Page");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2.5:Subject details displayed and Study name is displayed as active link");
		subjectDetailPage.verifyDetailStatus(Constants.subjectScreeingNumber, subjectName);
		subjectDetailPage.verifyStudyNameIsDisplayedAsActiveLink();

		reportLog("3.1:Select the Study name");
		subjectDetailPage.clickOnStudyActiveLink();

		reportLog("3.2:Study Profile for the Study from PR#1 is displayed on the Subject details page");
		subjectDetailPage.verifyStudyProfileDisplayedInSubjectDetailPage();

		reportLog("4.1:Refresh a page");
		subjectDetailPage.refreshPage();

		reportLog("4.2:Subject Details from PR#3 are displayed");
		subjectDetailPage.verifyDetailStatus(Constants.subjectScreeingNumber, subjectName);

		reportLog("5.1:Select the Study name");
		subjectDetailPage.clickOnStudyActiveLink();
		
		reportLog("5.2:Study Profile for the Study from PR#1 is displayed");
		subjectDetailPage.verifyStudyProfileDisplayedInSubjectDetailPage();
		
		reportLog("6.1:Select to collapse the Study Profile");
		subjectDetailPage.clickOnStudyProfileCollpaseButton();
		
		reportLog("6.2:	Subject Details from PR#3 are displayed");
		subjectDetailPage.verifyDetailStatus(Constants.subjectScreeingNumber, subjectName);

		reportLog("6.3: Logout application");
		loginPage.logoutApplication();

		reportLog("6.4: Verify User is Logout from the application");
		loginPage.verifyUserLogout();

	}

}
