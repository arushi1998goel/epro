/**
 *@author 
* @date 17/03/2020
* ======================================================================================================
*  Test Case Id: FP-TC-1565 || Test Case Name: View Form-based Queries: roles/claims restrictions- V4
	 * pre-qualification :1. User has 'canManageQueries' and 'canReplyToQueries' claims
                          2. User is Site person with 'canViewQueries' claim and without 'canManageQueries' and 'canReplyToQueries' claims
                          3. At least two Form-based queries (which have replies from Site Person or Study Person) exist
                          4. At least two open Form-based queries (WITHOUT replies from Site Person or Study Person) exist
* ========================================================================= 
*/package net.medavante.regression.testscripts.r3suite;

import java.util.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class R3_FPTC_1565_ViewFormBasedQueriesRoles_ClaimsRestrictions_SIP extends BaseTest {

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public R3_FPTC_1565_ViewFormBasedQueriesRoles_ClaimsRestrictions_SIP(String browser) {
		super(browser);
	}
	
	private String study,SubjectName = "AutomationSUBJ_" + generateRandomString(5);;
	
	@BeforeMethod
	public void GetTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties=Configuration.readTestData("RegressionTestData");
        study=properties.getProperty("Study1565");
		
		reportLog("1.1: Creating pre-requisites");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		dashBoardPage.verifyMedavantePortalPage();
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog(" Creating a subject");
		
		studyNavigatorDashBoardPage.creatingSubjectForPreRequisite(study, Constants.ATAssignedRater_10, SubjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSaveBTN();
		
		reportLog(" At least two open Form-based queries (WITHOUT replies from Site Person or Study Person) exist");
		subjectDetailPage.clickOnQueriesIcon();
		subjectDetailPage.addNewQuery(SubjectName);
		subjectDetailPage.clickOnQueriesCollpaseIcon();
	}
	
	@Test(description="FP-TC-1565---View Form-based Queries: roles/claims restrictions")
	public void R3_FPTC_1565_ViewFormBasedQueriesRoles_ClaimsRestrictions()
	{
		
		reportLog("2.0: Log in to the Portal as User Pr#1");
		dashBoardPage = loginPage.loginInApplication(SuperAdminUN, SuperAdminPW);
		
		reportLog("2.1: User is logged in");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("3.0: Navigate to Study Navigator");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);
		
		reportLog("3.1: Open Queries side panel");
		subjectDetailPage.clickOnQueriesIcon();
		
		reportLog("3.2: List of queries is displayed");
		
		reportLog("4.0: Find and expand the query Pr.#3");
		reportLog("4.2: Accept and Close control is displayed (active)");
		
		
		
		reportLog("5.0: Select Accept and Close control -> Confirm close action");
	}

}
