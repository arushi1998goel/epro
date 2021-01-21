package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1629_SubjectQuestionnaireComplianceRateOfTheSubject_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1629_SubjectQuestionnaireComplianceRateOfTheSubject_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("StudyName2645");
		subjectName = properties.getProperty("SubjectEventName");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1629 Test Case Name:Subject Questionnaire -Compliance Rate of the subject
	 * 
	 * ====================================================================================================================
	 * 
	 */
	@Test(description = "FP-TC-1629 Test Case Name:Subject Questionnaire -Compliance Rate of the subject", groups = {})
	public void FPTC_1629_SubjectQuestionnaireComplianceRateOfTheSubject() {

		reportLog("1: Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSystemAdministrator, AT_Password);

		reportLog("2: Navigate to Study Navigator -> Study Pr.#1 Subject Listing screen -> Select Subject Pr.#3");
		studyNavigatorDashBoardPage = dashBoardPage.navigateToStudyNavigator();
		
		studyNavigatorDashBoardPage.selectStudy(studyName);
		studyNavigatorDashBoardPage.selectSite(Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();
		
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("2.1: Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();	
		
		reportLog("3: Select Questionnaires from Subject categories drop-down list");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_Questionnaires);
		
		reportLog("3.1: 'Past Week' filter displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.CategoryFilter_PastWeek);
		
		reportLog("3.2: 'Subject' filter displayed by default");
		
		reportLog("3.3: Compliance Rate of the subject is shown");
		subjectDetailPage.verifyComplianceSectionDisplay();
		
		reportLog("4.1: Compliance Rate of the subject is shown:  On time: shown correct ration in % and underlined in green");
		subjectDetailPage.verifyComplianceAreaByBlockName(Constants.Compliance_TimeBlock);
		
		reportLog("4.1: Compliance Rate of the subject is shown:  On time: Late: shown correct ration in % and underlined in yellow");
		subjectDetailPage.verifyComplianceAreaByBlockName(Constants.Compliance_LateBlock);
		
		reportLog("4.1: Compliance Rate of the subject is shown:  On time: Expired: shown correct ration in % and underlined in red");
		subjectDetailPage.verifyComplianceAreaByBlockName(Constants.Compliance_ExpiredBlock);
		
		reportLog("5: Logout application");
		loginPage.logoutApplication();

		reportLog("6: Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}

}
