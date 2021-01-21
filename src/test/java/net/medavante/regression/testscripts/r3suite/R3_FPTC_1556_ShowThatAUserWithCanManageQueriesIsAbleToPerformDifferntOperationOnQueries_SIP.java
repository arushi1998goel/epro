/**
* @author siddharth
* @date 25/11/2019
* =========================================================================
* Test Case Id: FP-TC-1559 
* Test Case Description: 
Show that a User with 'canManageQueries' is able to close query
Show that closed query can not be responded, edited or deleted
Show that Open, Responded, All Queries filters are selected by default
Show that closed queries are displayed in the list when Closed filter is selected
* ========================================================================= 
*/

package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;


import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1556_ShowThatAUserWithCanManageQueriesIsAbleToPerformDifferntOperationOnQueries_SIP extends BaseTest{

	
	@Factory(dataProvider = "Browsers" , dataProviderClass = DataProviders.class)
	public R3_FPTC_1556_ShowThatAUserWithCanManageQueriesIsAbleToPerformDifferntOperationOnQueries_SIP(String Browser) {
		super(Browser);
	}
		
	@BeforeMethod
	public void getData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop=Configuration.readTestData("RegressionTestData");
		studyName=prop.getProperty("AutomationStudyNameNonCR");
		subjectName=prop.getProperty("SubjectName1556");
		queryName="Query"+generateRandomString(5);
		queryReply="QueryReply"+generateRandomString(5);
	}
	
	@Test(description = "ShowThatAUserWithCanManageQueriesIsAbleToPerformDifferntOperationOnQueries" , groups = {"R3"})
	public void R3_FPTC_1556_testShowThatAUserWithCanManageQueriesIsAbleToPerformDifferntOperationOnQueries() {
	
		reportLog("1.1	Log in to Portal as user Pr #1");	
		dashBoardPage=loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog(" 1.2 User successfully logged in ");
		dashBoardPage.verifyMedavantePortalPage();
		
		reportLog(" 2.1	Select Study with completed assessment (Pr #2) .Navigate to Complete filter for Assessments ");
		studyNavigatorDashBoardPage=dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);
		
		reportLog(" 2.2	Navigate to Study Navigator-> Study PR#2 ");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);
		
		reportLog("2.3 Open Queries side panel");
		studyNavigatorDashBoardPage.clickOnQueriesButtonToOpen();
		
		reportLog("2.4 List of queries is displayed ");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();
		
		reportLog("2.5 Open, Responded, All Queries filters are selected by default");
		studyNavigatorDashBoardPage.verifyFiltersAreSelected(Constants.OpenQueryCheckBox,Constants.RespondedCheckBox,Constants.AllQueriesCheckBox);
		
		reportLog("3.1 Select Closed filter");
		studyNavigatorDashBoardPage.selectFiltersOnQueriesTab(Constants.ClosedQueryCheckBox);
		
		reportLog("3.2 Closed queries Pr#3 are displayed in the list	");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();
		
		reportLog("Create a new Query which has reply");
		createQueryWithReply();
		
		reportLog("4.1 Find and expand the query which has reply ");
		studyNavigatorDashBoardPage.clickOnQueryWhichHasReply();
		
		reportLog("4.2 'Close Query' control is displayed (not active) ");
		studyNavigatorDashBoardPage.closeQueryButtonStatus(Constants.isDisabled);
		
		reportLog("5.1	Enter text into comment text box ");
		studyNavigatorDashBoardPage.clickOnQueryWhichHasReply();
		studyNavigatorDashBoardPage.EnterReplyTextInNewQueryTextBox(queryReply);
		
		reportLog("5.2 'Close Query' control is active ");
		studyNavigatorDashBoardPage.closeQueryButtonStatus(Constants.isEnabled);
		
		reportLog("6.1	Select to Close Query	");
		studyNavigatorDashBoardPage.clickOnCloseQueryButton();
		
		reportLog("6.2 Confirmation pop-up is displayed -'Are you sure you want to close this query?'. ");
		studyNavigatorDashBoardPage.ConfirmationPopUpDisplayedWithMessage(Constants.confirmationPopUpMessage,Constants.isEnabled);
		
		reportLog("6.3 'Yes' and 'No' controls are displayed (active)");
		studyNavigatorDashBoardPage.verifyYesAndNoControlsAreActiveAndDisplayed();
		
		reportLog("7.1	Select not to close Query	");
		studyNavigatorDashBoardPage.clickYesOrNo(Constants.isDisabled);
		
		reportLog("7.2 Confirmation pop-up is closed");
		studyNavigatorDashBoardPage.ConfirmationPopUpDisplayedWithMessage(Constants.confirmationPopUpMessage,Constants.isDisabled);
		
		reportLog("8.1 Select to Close Query");
		studyNavigatorDashBoardPage.clickOnCloseQueryButton();
		
		reportLog("8.2 Select to Confirm ");
		studyNavigatorDashBoardPage.clickYesOrNo(Constants.isEnabled);
		
		reportLog("8.3 Filter by Closed queries ");
		studyNavigatorDashBoardPage.verifyFiltersAreSelected(Constants.ClosedQueryCheckBox);
		
		reportLog("8.4 Find closed query");
		studyNavigatorDashBoardPage.verifyQueriesAssociatedWithTheFilters();
		
		reportLog("8.5 Query is marked as closed ");
		studyNavigatorDashBoardPage.verifyTheQueryIsMarkedAsClosed(queryName);
		
		reportLog("8.6 Close comment, date stamp and user name are displayed as last item in the replies list");
		studyNavigatorDashBoardPage.verifyclosedQueryDetails(queryReply,currentDateWithMonthName(),Constants.CurrentUser);
		
		reportLog("9.1	Expand query closed in step #8	 ");
		studyNavigatorDashBoardPage.expandTheClosedQuery(queryName);
		
		reportLog("9.2 Edit, Send, Close, Delete buttons, reply textbox are no longer visible for this query ");
		studyNavigatorDashBoardPage.verifyEditCloseDeleteReplyButtonsAreNotAvailable();
		
		reportLog("9.3 logout from  the application");
		loginPage=loginPage.logoutApplication();
		
		reportLog("9.4 Verify User logout");
		loginPage.verifyUserLogout();
	}
	
	private void createQueryWithReply() {
		StudyDashBoardPage studyNavigatorDashBoardPage=new StudyDashBoardPage(getWebDriver());
		studyNavigatorDashBoardPage=PageFactory.initElements(getWebDriver(), StudyDashBoardPage.class);
		studyNavigatorDashBoardPage.createNewQueryWithReply(queryReply, subjectName, queryName);	
	}

}
