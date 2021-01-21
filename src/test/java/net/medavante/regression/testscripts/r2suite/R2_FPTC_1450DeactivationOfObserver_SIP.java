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
import net.medavante.portal.utilities.SubjectsModuleConstants;

public class R2_FPTC_1450DeactivationOfObserver_SIP extends BaseTest {

	protected String studyName, visitNameObsroForm, observerRelation1, observerRelation2, observerNameFirst,
			observerNameSecond, observerAlias = "Auto" + generateRandomString(2);
	protected String subjectName = "Subject894_" + generateRandomString(3),
			deactivateReason = "AutoReason" + generateRandomString(3);
	protected StudySubjectListingPage subjectList;
	protected NewSubjectDetailPage subjectDetailPageObj;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R2_FPTC_1450DeactivationOfObserver_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");;
		studyName = prop.getProperty("AutomationStudyName");
		visitNameObsroForm = prop.getProperty("Auto_Paper_OBSRO_Visit1");
		observerRelation1 = prop.getProperty("Auto_Observer_Relation1");
		observerRelation2 = prop.getProperty("Auto_Observer_Relation2");
		
		reportLog("1:Login to The Application And Setting Up The Prerequisite Add Observer");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(studyName, Constants.ATAssignedRater_10, subjectName);
		subjectDetailPageObj = studyNavigatorDashBoardPage.clickOnSaveBTN();
		subjectDetailPageObj.verifyNewSubjectDetailPage();
		subjectDetailPageObj.clickOnReportedOutComeButton();
		subjectDetailPageObj.clickOnAddObserverBtN();
		subjectDetailPageObj.inputObserverRelationName(observerRelation1);
		subjectDetailPageObj.inputObserverAliasName(observerAlias);
		observerNameFirst = observerAlias + " " + "(" + observerRelation1 + ")";
		subjectDetailPageObj.clickOnObserverSaveBTN();
		subjectDetailPageObj.clickOnAddObserverBtN();
		subjectDetailPageObj.inputObserverRelationName(observerRelation2);
		subjectDetailPageObj.inputObserverAliasName(observerAlias);
		observerNameSecond = observerAlias + " " + "(" + observerRelation2 + ")";
		subjectDetailPageObj.clickOnObserverSaveBTN();
		subjectDetailPageObj.clickOnReportedOutComePopUpSaveBTN();
	}

	/**
	 * ====================================================================================================================
	 * Test Case Id:FPTC_1450 Test Case Name:Deactivation of Observer
	 * ====================================================================================================================
	 */

	@Test(description = "FPTC_1450_ VerifyDeactivationOfObserver", groups = { "R2" })
	public void FPTC_1450_VerifyDeactivationOfObserver() {

		reportLog("1.3:click to schedule for assessment the visit identified in prerequisite #2");
		subjectDetailPageObj.clickOnVisitRow(visitNameObsroForm);
		subjectDetailPageObj.clickOnAddVisitIcon();
		subjectDetailPageObj.verifyRaterDropDownIsDisplayed();

		reportLog("2:Select any Observer for the assessment");
		subjectDetailPageObj.selectRaterFromDropDown(observerNameFirst);

		reportLog("2.1:Relation and alias of selected observer are displayed for the selected assessment");
		subjectDetailPageObj.verifyRaterAssignmentDropDownSelectedValue(observerNameFirst);

		reportLog("3:Open Reported Outcome Details");
		subjectDetailPageObj.clickOnReportedOutComeButton();

		reportLog("3.1:Observers general information is displayed");
		subjectDetailPageObj.verifyObserverInformation(observerRelation1,"RELATION", observerRelation1);
		subjectDetailPageObj.verifyObserverInformation(observerRelation2,"RELATION", observerRelation2);

		reportLog("3.2:On the list of observers, select the observer that was assigned to the form on the step #2");
		subjectDetailPageObj.selectObserverRelationAndVerifyEditBTNIsDisplayedAndDeleteBTNIsHidden(observerRelation1);

		reportLog("4:Click on Edit");
		subjectDetailPageObj.editObserverSaveAndCancelOptionDisplayedAndFliedsEditable(observerRelation1);

		reportLog("5:Select the check-box to make Observer Deactivated");
		subjectDetailPageObj.selectDeactiveCheckBox();

		reportLog("5.1:'Deactivated Reason' field becomes required");
		subjectDetailPageObj.verifyDeactivateReasonIsRequired();

		reportLog("6:Fill the Deactivated Reason and click on Save");
		subjectDetailPageObj.inputDeactivateReason(deactivateReason);
		subjectDetailPageObj.clickOnObserverSaveBTN();
		subjectDetailPageObj.clickOnReportedOutComePopUpSaveBTN();

		reportLog(
				"6.1:Message is appeared:This Observer is assigned to an assessment and cannot be deactivated with Close control");
		subjectDetailPageObj.verifyErrorMessage(SubjectsModuleConstants.subject_OserverDeactivateErrorMessage);		

		reportLog("7:Click on Close message");
		subjectDetailPageObj.closeErrorMessage();

		reportLog("7.1:Message is closed. Observer stays in edit-mode");
		subjectDetailPageObj.verifyReportedOutComeIsDisplayed();

		reportLog("8:Click on Cancel");
		subjectDetailPageObj.closeReportedOutComePopup();

		reportLog("8.1:Edit-mode is closed. Observer stays in 'Active' state");
		subjectDetailPageObj.observerStaysInActiveState();

		reportLog(
				"9:Navigate to visit created on step #1 and remove the observer assignment made on the step #2 by selecting Not Assigned in drop-down list​");
		subjectDetailPageObj.clickOnVisitRow(visitNameObsroForm);
		subjectDetailPageObj.verifyRaterDropDownIsDisplayed();
		subjectDetailPageObj.selectRaterFromDropDown(Constants.notAssignedText);

		reportLog("9.1:Not Assigned option is displayed for the selected assessment");
		subjectDetailPageObj.verifyRaterAssignmentDropDownSelectedValue(Constants.notAssignedText);
		subjectDetailPageObj.clickOnReportedOutComeButton();

		reportLog(
				"10:On the list of observers on Reported Outcome Details screen select the Observer that was unassigned from the form on the step #9 and Observer general information is displayed and Edit and Delete icons are enabled");
		subjectDetailPageObj.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(observerRelation1);
		subjectDetailPageObj.verifyObserverInformation(observerRelation1,"RELATION", observerRelation1);

		reportLog(
				"11:Click on Edit and select the check-box to make Observer 'Deactivated' and Options to Save and Cancel are displayed");
		subjectDetailPageObj.editObserverSaveAndCancelOptionDisplayedAndFliedsEditable(observerRelation1);
		subjectDetailPageObj.selectDeactiveCheckBox();

		reportLog("11.1:'Deactivated Reason' field becomes required");
		subjectDetailPageObj.verifyDeactivateReasonIsRequired();

		reportLog("12:Fill the reason of deactivation and click on Cancel");
		subjectDetailPageObj.inputDeactivateReason(deactivateReason);
		subjectDetailPageObj.clickOnCancelObserverButton();

		reportLog("12.1:verify Changes are canceled. Observer stays in Active state");
		subjectDetailPageObj.verifyObserverInformation(observerRelation1,"DEACTIVATED", "No");

		reportLog("13:Select the same observer again.");
		subjectDetailPageObj.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(observerRelation1);

		reportLog("13.1: Click on Edit");
		subjectDetailPageObj.editObserverSaveAndCancelOptionDisplayedAndFliedsEditable(observerRelation1);

		reportLog("13.2:activate check-box Deactivated, fill the reason of deactivation and click on Save");
		subjectDetailPageObj.selectDeactiveCheckBox();
		subjectDetailPageObj.inputDeactivateReason(deactivateReason);
		subjectDetailPageObj.clickOnObserverSaveBTN();

		reportLog(
				"13.3:'Deactivated Date, equal to current date, is appeared within 'Deactivated' column.Deactivated Reason is displayed");
		subjectDetailPageObj.verifyObserverInformation(observerRelation1,"DEACTIVATED", currentDate());
		subjectDetailPageObj.verifyObserverInformation(observerRelation1,"DEACTIVATED REASON", deactivateReason);

		reportLog("14:Select an action to save changes on Reported Outcome Details screen");
		subjectDetailPageObj.clickOnReportedOutComePopUpSaveBTN();

		reportLog("14.1:Reported Outcome Details screen is closed");
		subjectDetailPageObj.verifyNewSubjectDetailPage();

		reportLog("15:Navigate to visit created on step #1 and click on the assignment drop down of ObsRO form");
		subjectDetailPageObj.clickOnVisitRow(visitNameObsroForm);
		subjectDetailPageObj.clickOnAssignRaterDropDown();

		reportLog(
				"15.1:Deactivated Observer cannot be assigned for Assessments Deactivated observer disappears from 'Assign' drop down for ObsRO forms");
		subjectDetailPageObj.verifyRaterNameDisplayedInRaterDropDown(observerNameSecond);

		reportLog("16:In the observer list on Reported Outcome Details screen select the other active observer");
		subjectDetailPageObj.clickOnReportedOutComeButton();
		subjectDetailPageObj.selectObserverRelationAndVerifyEditAndDeleteBTNIsDisplayed(observerRelation2);

		reportLog(
				"16.1:Click on Edit, activate check-box Deactivated, fill the reason of deactivation and click on Save");
		subjectDetailPageObj.editObserverSaveAndCancelOptionDisplayedAndFliedsEditable(observerRelation2);
		subjectDetailPageObj.selectDeactiveCheckBox();
		subjectDetailPageObj.inputDeactivateReason(deactivateReason);
		subjectDetailPageObj.clickOnObserverSaveBTN();

		reportLog(
				"16.2:Observer becomes deactivated. Deactivated Date, equal to current date, is appeared within 'Deactivated' column.Deactivated Reason is displayed");
		subjectDetailPageObj.verifyObserverInformation(observerRelation2,"DEACTIVATED", currentDate());
		subjectDetailPageObj.verifyObserverInformation(observerRelation2,"DEACTIVATED REASON", deactivateReason);

		reportLog("17:Select an action to save changes on Reported Outcome Details screen");
		subjectDetailPageObj.clickOnReportedOutComePopUpSaveBTN();

		reportLog("17.1:Reported Outcome Details screen is closed");
		subjectDetailPageObj.verifyNewSubjectDetailPage();

		reportLog("18:Navigate to visit created on step #1 and click on the assignment drop down of ObsRO form");
		subjectDetailPageObj.clickOnVisitRow(visitNameObsroForm);
		subjectDetailPageObj.clickOnAssignRaterDropDown();
		
		reportLog("18.1:For the all ObsRO assessments only Not Assigned option is available");
		subjectDetailPageObj.verifyRaterAssignmentDropDownSelectedValue(Constants.notAssignedText);
               	
		reportLog("18.2:Logout from the application");
		loginPage.logoutApplication();

		reportLog("18.3:Verify user is logout");
		loginPage.verifyUserLogout();

	}

}
