package net.medavante.regression.testscripts.r1suite;

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

public class FPTC_2773AdministrationCustomizeSubjectStatusTest_SIP extends BaseTest {

	protected String newStatus = "Auto_" + " " + generateRandomString(93);
	protected String screenedStatus = "Auto_" + " " + generateRandomString(93);
	protected String screenFailedStatus = "Auto_" + " " + generateRandomString(93);
	protected String enrolledStatus = "Auto_" + " " + generateRandomString(93);
	protected String completedStatus = "Auto_" + " " + generateRandomString(93);
	protected String discontinueStatus = "Auto_" + " " + generateRandomString(93);
	protected String defaultNewStatus = "Subject has not completed any visits.";
	protected String defaultScreenedStatus = "Subject has successfully completed the screening visit.";
	protected String defaultScreenFailedStatus = "Subject is not eligible to participate in the study.";
	protected String defaultDiscontinueStatus = "Subject has withdrawn from study participation.";

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2773AdministrationCustomizeSubjectStatusTest_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("studyCustomTabCustomizedSubjectStatus");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FB-TC-2773 Test Case Name: Customize subject status
	 * description for different subject statuses
	 * 
	 * ====================================================================================================================
	 */

	@Test(description = "FB-TC-2773_Customize subject status description for different subject statuses", groups = {
			"R1" })
	public void verifyCustomizeSubjectStatusDescription() {

		reportLog("Login in to application");
		dashBoardPage = loginPage.loginInApplication(userName, password);

		reportLog("Click on Adminstration  tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("Click on Studies Tab");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("Select Study For Status Description Editing");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("Navigate To Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("Added Subject Status Description");
		adminstrationStudiesCustomPage.editSubjectDescription(newStatus, screenedStatus, screenFailedStatus,
				enrolledStatus, completedStatus, discontinueStatus);

		reportLog("Click on study navigator tile");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("Select " + studyName + " study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("Verify Selected dashboard is displayed in chart view");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();

		reportLog("Verify ToolTip Subject Status Description");
		studyNavigatorDashBoardPage.verifyToolTipSubjectStatusDescription(newStatus, screenedStatus, screenFailedStatus,
				discontinueStatus);

		reportLog("click on view button to change the view mode as card.");
		studyNavigatorDashBoardPage.changeStudyViewMode();

		reportLog("Verify Selected dashboard is displayed in card view mode");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardCardViewIsDisplayed();

		reportLog("Verify Card Subject Status Description");
		studyNavigatorDashBoardPage.verifyCardSubjectStatusDescription(newStatus, screenedStatus, screenFailedStatus, discontinueStatus);

		reportLog("Navigate To Home Page");
		studyNavigatorDashBoardPage.navigateToHome();

		reportLog("Click on Adminstration  tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("Click on Studies Tab");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("Select Study For Status Description Editing");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("Navigate To Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("Change Status Of Subject Description To Empty");
		adminstrationStudiesCustomPage.emptySubjectDescription();

		reportLog("Click on study navigator tile");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("Select " + studyName + " study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("Verify Selected dashboard is displayed in card view mode");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardCardViewIsDisplayed();

		reportLog("Verify Card Subject Status Description Is Empty");
		studyNavigatorDashBoardPage.verifyCardSubjectStatusEmpty();

		reportLog("click on view button to change the view mode as card.");
		studyNavigatorDashBoardPage.changeStudyViewMode();

		reportLog("Verify Selected dashboard is displayed in chart view");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();

		reportLog("Verify ToolTip Subject Status Description Is Empty");
		studyNavigatorDashBoardPage.verifyToolTipSubjectStatusEmpty();

		reportLog("Navigate To Home Page");
		studyNavigatorDashBoardPage.navigateToHome();

		reportLog("Click on Adminstration  tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("Click on Studies Tab");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("Select Study For Status Description Editing");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("Navigate To Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("Subject Status Description Set To Default");
		adminstrationStudiesCustomPage.setSubjectDescriptionToDefault();

		reportLog("Click on study navigator tile");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("Select " + studyName + " study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("Verify Selected dashboard is displayed in chart view");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();

		reportLog("Verify ToolTip Subject deafult Status");
		studyNavigatorDashBoardPage.verifyToolTipDefaultStatusDescription(defaultNewStatus, defaultScreenedStatus,
				defaultScreenFailedStatus, defaultDiscontinueStatus);

		reportLog("click on view button to change the view mode as card.");
		studyNavigatorDashBoardPage.changeStudyViewMode();

		reportLog("Verify Selected dashboard is displayed in card view mode");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardCardViewIsDisplayed();

		reportLog("Verify Card Subject deafult Status");
		studyNavigatorDashBoardPage.verifyCardDefaultStatusDescription(defaultNewStatus, defaultScreenedStatus,
				defaultScreenFailedStatus, defaultDiscontinueStatus);

		reportLog("Navigate To Home Page");
		studyNavigatorDashBoardPage.navigateToHome();

		reportLog("Click on Adminstration  tile");
		adminstrationOrganizationPage=dashBoardPage.selectHorizontalUpperNavMenuItem(AdministrationOrganizationPage.class,Constants.ConfigureNavText, Constants.StudySetupText);

		reportLog("Click on Studies Tab");
		adminstrationStudiesPage = adminstrationOrganizationPage.navigateToStudies();

		reportLog("Select Study For Status Description Editing");
		adminstrationStudiesPage.searchAndClickOnStudy(studyName);

		reportLog("Navigate To Custom Tab");
		adminstrationStudiesCustomPage = adminstrationStudiesPage.navigateToStudyCustomTab();

		reportLog("Added Subject Status Description To Max Length");
		adminstrationStudiesCustomPage.editSubjectDescription(newStatus, screenedStatus, screenFailedStatus,
				enrolledStatus, completedStatus, discontinueStatus);

		reportLog("Click on study navigator tile");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class, Constants.NavigateText, Constants.StudyText);

		reportLog("Select " + studyName + " study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName);

		reportLog("Verify Selected dashboard is displayed in card view mode");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardCardViewIsDisplayed();

		reportLog("Verify Card Subject Status Description Upto Max Length");
		studyNavigatorDashBoardPage.verifyCardStatusDescriptionWithMaxLength(newStatus, screenedStatus,
				screenFailedStatus, discontinueStatus);

		reportLog("click on view button to change the view mode as card.");
		studyNavigatorDashBoardPage.changeStudyViewMode();

		reportLog("Verify Selected dashboard is displayed in chart view");
		studyNavigatorDashBoardPage.verifySelectedStudyDashBoardChartViewIsDisplayed();

		reportLog("Verify ToolTip Subject Status Description Upto Max Length");
		studyNavigatorDashBoardPage.verifyToolTipStatusDescriptionWithMaxLength(newStatus, screenedStatus,
				screenFailedStatus, discontinueStatus);

		reportLog("Logout application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}