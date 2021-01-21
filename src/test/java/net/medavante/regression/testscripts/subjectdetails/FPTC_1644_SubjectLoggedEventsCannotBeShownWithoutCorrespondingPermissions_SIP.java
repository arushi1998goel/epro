package net.medavante.regression.testscripts.subjectdetails;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.administration.AdministrationOrganizationPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_1644_SubjectLoggedEventsCannotBeShownWithoutCorrespondingPermissions_SIP extends BaseTest {
	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1644_SubjectLoggedEventsCannotBeShownWithoutCorrespondingPermissions_SIP(String browser) {
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
	 * Test Case Id: FP-TC-1644 Test Case Name:Subject Logged Events Cannot Be
	 * Shown Without Corresponding Permissions
	 * 
	 * ====================================================================================================================
	 * 
	 */

	@Test(description = "FP-TC-1644_SubjectLoggedEventsCannotBeShownWithoutCorrespondingPermissions", groups = {})
	public void FPTC_1644_SubjectLoggedEventsCannotBeShownWithoutCorrespondingPermissions() {

		reportLog("1.1:Log in to Portal as a User Pr.#2");
		dashBoardPage = loginPage.loginInApplication(AT_ProdAdminOps, AT_Password);
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("1.2:Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.3:Study Pr.#1 Subject Listing screen");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("1.4:Select Subject Pr.#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("1.5:Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("2.1:Select Logged Events from Subject categories drop-down list ");
		subjectDetailPage.selectOptionFromSubjectCategoriesDropDownList(Constants.SubjectCategory_LoggedEvents);

		reportLog("2.2:List of Logged Events Pr.#4 is displayed ");
		subjectDetailPage.verifyEventListDisplayed();

		reportLog("2.4:All filter displayed by default");
		subjectDetailPage.verifyByDefaultSelectedSubTab(Constants.CategoryFilter_All);

		reportLog("2.5:The newest records shown in the top of the list");
		subjectDetailPage.verifyDateOfActionWithLatestOnTop();
		dashBoardPage = subjectDetailPage.navigateToHomePage();

		reportLog("3.1:	Navigate to Administration");
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("3.2: General");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesPage.verifyAdministrationStudiesGeneralPageIsOpen(studyName);
		adminstrationStudiesPage.editStudy();

		reportLog("3.3: Unset 'Mobile PRO' settings");
		adminstrationStudiesPage.unSelectProductTypeCheckBox(Constants.ProductType_Mobile_Pro);

		reportLog("3.4: 'Mobile PRO' settings unsetted from Study Pr.#1");
		adminstrationStudiesPage.verifyProductTypeCheckBoxIsNotSelected(Constants.ProductType_Mobile_Pro);
		adminstrationStudiesPage.clickOnSaveBTN();
		dashBoardPage = adminstrationStudiesPage.navigateToHomePage();

		reportLog("3.5: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("4.2:  Study Pr.#1");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);


		reportLog("4.3: Subject Listing screen");
		studyNavigatorDashBoardPage.navigateToSubjectsListing();

		reportLog("4.4: Select Subject Pr.#3");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("4.5: Subject Pr.#3 Detail screen is displayed");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("4.6: Only Visit list displayed");
		subjectDetailPage.verifyOnlyVisitListDisplayed();

		reportLog("4.7:Categories drop-down list unavailable for selection");
		subjectDetailPage.verifyCategoriesDropdownListUnavailabelForSelection();

		reportLog("Setting Up The Pre-Requiste and Select Mobile Pro Type for Further Verification");
		dashBoardPage = subjectDetailPage.navigateToHomePage();
		adminstrationOrganizationPage = dashBoardPage.selectHorizontalUpperNavMenuItem(
				AdministrationOrganizationPage.class, Constants.ConfigureNavText, Constants.StudySetupText);
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);
		adminstrationStudiesPage.verifyAdministrationStudiesGeneralPageIsOpen(studyName);
		adminstrationStudiesPage.selectProductTypeCheckBox(Constants.ProductType_Mobile_Pro);
		adminstrationStudiesPage.selectProductTypeCheckBox(Constants.ProductType_Observer);
		adminstrationStudiesPage.clickOnSaveBTN();

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is at logout Page");
		loginPage.verifyUserLogout();

	}
}
