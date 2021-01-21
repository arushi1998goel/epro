package net.medavante.smoke.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.centralrating.CentralRatingAssessmentsListingPage;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_2828_SubjectDetailsAndRatingDetailsPage_MAP extends BaseTest {

	
	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2828_SubjectDetailsAndRatingDetailsPage_MAP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("TestDataFile");
		studyName = properties.getProperty("CRStudy1928");
		subjectName=properties.getProperty("Rand001");
		visitName=properties.getProperty("visitName");
		formName=properties.getProperty("Form");
		
	}

	/**
	 * =========================================================================
	 * Test Case Id: FP-TC-2828 Test Case Name: Name: Verify Subject and Rating
	 * Pages in MA Portal
	 * =========================================================================
	 * 
	 */

	@Test(description = "FP-TC-2828_Name: Verify Subject and Rating Pages in MA Portal ", groups = { "smoke" })
	public void FPTC_2828_verifySubjectDetailsAndRatingDetailsPage() {

		reportLog("1. Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODITSupport, AT_Password);
		
		reportLog("1.1 Home page is displayed");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2. Go to NAVIGATE>>Study");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("3. Select " + studyName + " study from the pop up window");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.VTAssignedRater_21);
		
		reportLog("4. Verify Subject Listing Page By Default Opened");
		studyNavigatorDashBoardPage.verifySubjectListIsOpened();

		reportLog("5. Verify Subject, Visit, Assessment options display in left side menu");
		studyNavigatorDashBoardPage.verifySubjectVisitAndAssesmentInLeftMenu();

		reportLog("6. Go to NAVIGATE >> Ratings");
		centralRatingAssesmentListingPage = studyNavigatorDashBoardPage.selectHorizontalUpperNavMenuItem(
				CentralRatingAssessmentsListingPage.class, Constants.NavigateText, Constants.RatingsText);

		reportLog("7. Verify CR Assesments list should load properly with the data.");
		centralRatingAssesmentListingPage.loadCompletePage();

		reportLog("8. Click on Find Subject icon");
		centralRatingAssesmentListingPage.clickOnFindSubjectIcon();

		reportLog("9. Select Study from Study Drop down, Site from Site Drop down and then select Subject");
		centralRatingAssesmentListingPage.selectingStudyDropDownSiteDropDownAndSubject(studyName, Constants.ATAssignedRater_10,
				subjectName);

		reportLog("9.1 Verify the reflection after selection of Study,Site and Subject ");
		centralRatingAssesmentListingPage.verifyChangeReflectionAfterSelectingStudyAndSubject(subjectName);
		
		reportLog("10. Go back to CR assessment list and Remove scheduled Date filter");
		centralRatingAssesmentListingPage.selectHorizontalUpperNavMenuItem(CentralRatingAssessmentsListingPage.class,
				Constants.NavigateText, Constants.RatingsText);

		reportLog("Load all the visit");
		centralRatingAssesmentListingPage.loadCompletePage();

		reportLog("10.1 Remove scheduled Date filter");
		centralRatingAssesmentListingPage.clickOnSchedularCancelIcon();

		reportLog("11. Search " + subjectName + "in CR Assessments List by entering subject number");		
		//centralRatingAssesmentListingPage.sortBySiteName(Constants.ATAssignedRater_10);
		centralRatingAssesmentListingPage.sortByStudyName(studyName);
		subjectDetailPage = centralRatingAssesmentListingPage.clickOnSearchSubjects(subjectName);

		reportLog("12. Verify the reflection after selection of Subject");
		centralRatingAssesmentListingPage.verifyChangeReflectionAfterSelectingStudyAndSubject(subjectName);

		reportLog("13. Verify the Selected subject detail page");
		subjectDetailPage.verifySubjectPageTitle(subjectName);

		reportLog("14. Select one completed Visit");
		subjectDetailPage.clickOnVisitRow("crvisitfirst");

		reportLog("14.1 Select one completed Assessment associated to that Visit");
		assessmentDetailPage = subjectDetailPage.selectThumbnailIMGFromMultipleConfiguredFormType(formName);

		reportLog("14.2: Assessment details page is displayed");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("15: Click on Thumb nail image of the completed assessment in the assessment page");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		/*reportLog("16. Verify the generated PDF file with footer");
		assessmentDetailPage.verifyFooterDataOnEachPage(footerData);*/
		
		reportLog("16. Click on cancel button");
		assessmentDetailPage.clickOnCancelButton();
		
		reportLog("17. Log Out Application");
		loginPage.logoutApplication();

		reportLog("17.1 Verify Log Out Page of the application");
		loginPage.verifyUserLogout();
		
	}

	
}
