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

public class FPTC_1141_SubjectDetailsRegistrationCodeIsntDisplayed_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1141_SubjectDetailsRegistrationCodeIsntDisplayed_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("newStudyName");
		subjectName = properties.getProperty("2389SubjectName");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1141 Test Case Name: Subject Details - Registration
	 * Code isn't displayed
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1141_Subject Details - Registration Code isn't displayed ", groups = { "" })

	public void FPTC_1141_verifySubjectDetailsRegistrationCodeIsntDisplayed() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("2.1: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("2.2: Select configured study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("2.4: Verify Subject Listing Page is displayed");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("3.1:Select Subject Pr#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("3.2:Verify Subject Details Page");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("3.3:QR code isn't displayed in Reported Outcomes section");
		subjectDetailPage.verifyReportedOutComeQRCodeForMobileSubjectRegistrationDialogIsNotDisplayed();

		reportLog("3.4Logout from the application");
		loginPage.logoutApplication();

		reportLog("3.5Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
