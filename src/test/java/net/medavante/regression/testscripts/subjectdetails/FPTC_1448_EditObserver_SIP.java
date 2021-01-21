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

public class FPTC_1448_EditObserver_SIP extends BaseTest {

	protected String observerAlias, observerName, observerNameToBeInserted = generateRandomString(2),
			aliasToBeInserted = generateRandomString(3);

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_1448_EditObserver_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("RegressionTestData");
		studyName = properties.getProperty("newStudyName");
		subjectName = properties.getProperty("SubjectName777");
		observerName = properties.getProperty("Auto_Observer_Relation1");
		observerAlias = properties.getProperty("observer77Alias");
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FP-TC-1448 Test Case Name:Edit Observer
	 * ====================================================================================================================
	 */

	@Test(description = "FP-TC-1448_Edit Observer", groups = { "" })

	public void FPTC_1448_verifyEditObserver() {

		reportLog("1.1: Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinator, AT_Password);

		reportLog("1.2: Navigate to study navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);


		reportLog("1.3: Select configured study from the drop down");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.5: Verify Subject Listing Page is displayed");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("1.6:Select Subject Pr#3");
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSubject(subjectName);

		reportLog("1.7:Verify New Subject Detail Page");
		subjectDetailPage.verifyNewSubjectDetailPage();

		reportLog("1.8:Click On Reported Outcome Edit Button");
		subjectDetailPage.clickOnReportedOutComeButton();

		reportLog("1.9:Verify Reported Outcome Pop Up And  Select the Observer");
		subjectDetailPage.verifyReportedOutComeIsDisplayed();
		subjectDetailPage.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(observerName);

		reportLog("1.10:Observer General Information Dispalyed");
		subjectDetailPage.verifyObserverInformation(observerName,Constants.AliasText, observerAlias);

		reportLog(
				"2:Click on EditObserver and Observer attributes are editable Options to Save and Cancel are displayed");
		subjectDetailPage.editObserverSaveAndCancelOptionDisplayedAndFliedsEditable(observerName);

		reportLog("3.Make changes in Relation or Alias for selected observer and click on Cancel");
		subjectDetailPage.inputObserverRelationName(generateRandomString(2));
		subjectDetailPage.inputObserverAliasName(generateRandomString(3));
		subjectDetailPage.clickOnCancelObserverButton();

		reportLog("3.1:Changes are not saved");
		subjectDetailPage.verifyObserverInformation(observerName,Constants.AliasText, observerAlias);

		reportLog("4:Activate edit-mode again, make changes and click on Save");
		subjectDetailPage.editObserverSaveAndCancelOptionDisplayedAndFliedsEditable(observerName);
		subjectDetailPage.inputObserverRelationName(observerNameToBeInserted);
		subjectDetailPage.inputObserverAliasName(aliasToBeInserted);
		subjectDetailPage.clickOnObserverSaveBTN();

		reportLog("4.1:Changes are saved");
		subjectDetailPage.verifyObserverInformation(observerNameToBeInserted,Constants.AliasText,aliasToBeInserted);
		subjectDetailPage.closeReportedOutComePopup();

		reportLog("4.2: Logout from the application");
		loginPage.logoutApplication();

		reportLog("4.3: Verify User is logout");
		loginPage.verifyUserLogout();

	}
}
