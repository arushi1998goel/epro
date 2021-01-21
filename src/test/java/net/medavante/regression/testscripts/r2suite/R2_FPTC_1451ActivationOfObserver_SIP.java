package net.medavante.regression.testscripts.r2suite;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.NewSubjectDetailPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.pages.studynavigator.StudySubjectListingPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R2_FPTC_1451ActivationOfObserver_SIP extends BaseTest {

	protected String userName, password, studyName, subjectName = "Auto895" + generateRandomString(4),
			visitNameObsroForm, observerRelation1, observerAlias = "Auto" + generateRandomString(2),
			deactivateReason = "AutoReason" + generateRandomString(3);
	protected NewSubjectDetailPage subjectDetailPageObj;
	protected StudySubjectListingPage subjectList;
	protected String observerNameInDropDown;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1451ActivationOfObserver_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("AutomationStudyName");
		visitNameObsroForm = prop.getProperty("Auto_Paper_OBSRO_Visit1");
		observerRelation1 = prop.getProperty("Auto_Observer_Relation1");

	
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		reportLog("1:Navigate to Subject Details of the Subject and Setting up the prerequisite #1");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);

		subjectDetailPageObj = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPageObj.verifyNewSubjectDetailPage();
		subjectDetailPageObj.verifyReportedOutComeButtonIsDisplayed();
		subjectDetailPageObj.clickOnReportedOutComeButton();
		subjectDetailPageObj.verifyReportedOutComeIsDisplayed();
		subjectDetailPageObj.clickOnAddObserverBtN();
		subjectDetailPageObj.inputObserverRelationName(observerRelation1);
		subjectDetailPageObj.inputObserverAliasName(observerAlias);
		observerNameInDropDown = observerAlias + " " + "(" + observerRelation1 + ")";
		subjectDetailPageObj.clickOnObserverSaveBTN();
		subjectDetailPageObj.clickOnReportedOutComePopUpSaveBTN();
		reportLog("1.1:Click On Reported Outcome Button After Setting Up PreRequisite");
		subjectDetailPageObj.clickOnReportedOutComeButton();
		reportLog("1.2:Edit Observer Added and verify save cnacel option displayed");
		subjectDetailPageObj.editObserverSaveAndCancelOptionDisplayedAndFliedsEditable(observerRelation1);
		reportLog("1.3:select To deactive checkbox and give reason for deactivate the observer and save");
		subjectDetailPageObj.selectDeactiveCheckBox();
		subjectDetailPageObj.inputDeactivateReason(deactivateReason);
		subjectDetailPageObj.clickOnObserverSaveBTN();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id: FPTC1451 Test Case Name:Activation of Observer
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC1451_ VerifyActivationOfObserver ", groups = { "R2" })
	public void FPTC1451_VerifyActivationOfObserver() {

		reportLog(
				"1.4:Observer general information with date of deactivation and the deactivated reason is displayed.");
		subjectDetailPageObj.verifyObserverInformation(observerRelation1,Constants.deactivateReason, deactivateReason);

		reportLog("1.5:Select the Observer identified in prerequisites #1.1 and Veridy Edit Control Enabled");
		subjectDetailPageObj.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(observerRelation1);

		reportLog(
				"2:Select an action to edit the Observer and Observer attributes are editable.Options to Save and Cancel are displayed.");
		subjectDetailPageObj.editObserverSaveAndCancelOptionDisplayedAndFliedsEditable(observerRelation1);

		reportLog("3:Unselect check-box Deactivated and Deactivated Reason field is cleared ");
		subjectDetailPageObj.unselectDeactiveCheckBoxAndVerifyDeactiveReasonInputBoxIsNotDisplayed();

		reportLog("4:Select an action to cancel changes");
		subjectDetailPageObj.clickOnCancelObserverButton();

		reportLog("4.1:Verify Observer stays in Deactivate state");
		subjectDetailPageObj.ObserverStaysInDeactivatestateInReportedOutComePopUp();
		
		reportLog("5:Select Observer again ");
		subjectDetailPageObj.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(observerRelation1);

		reportLog("5.1:Select an action to edit the Observer ");
		subjectDetailPageObj.editObserverSaveAndCancelOptionDisplayedAndFliedsEditable(observerRelation1);

		reportLog("5.2 Unselect check-box Deactivated");
		subjectDetailPageObj.unselectDeactiveCheckBoxAndVerifyDeactiveReasonInputBoxIsNotDisplayed();

		reportLog("5.3 Save Changes");
		subjectDetailPageObj.clickOnObserverSaveBTN();

		reportLog("5.4: Verify Observer Active ");
		subjectDetailPageObj.verifyObserverInformation(observerRelation1,Constants.observerDeactiveText, "No");

		reportLog("6: Click On Confirm Save Chnages For Reported Outcome");
		subjectDetailPageObj.clickOnReportedOutComePopUpSaveBTN();

		reportLog("6.1:Reported Outcome Details screen is closed");
		subjectDetailPageObj.verifyNewSubjectDetailPage();

		reportLog(
				"7:Add the visit identified in prerequisite#1.2 and click on the assignment drop down for the ObsRO form");
		subjectDetailPageObj.clickOnVisitRow(visitNameObsroForm);
		subjectDetailPageObj.clickOnAddVisitIcon();
		subjectDetailPageObj.verifyRaterDropDownIsDisplayed();

		reportLog("7.1:Observer activated in Step #5 is available in the assignment dropdown");
		subjectDetailPageObj.verifyRaterAssignmentDropDownSelectedValue(observerNameInDropDown);

		reportLog("Logout from the application");
		loginPage.logoutApplication();

		reportLog("Verify user is logout");
		loginPage.verifyUserLogout();

	}
}
