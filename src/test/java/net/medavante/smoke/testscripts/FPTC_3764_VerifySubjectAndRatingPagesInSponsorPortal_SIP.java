package net.medavante.smoke.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.studynavigator.StudyDashBoardPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;
import net.medavante.portal.utilities.Constants;

public class FPTC_3764_VerifySubjectAndRatingPagesInSponsorPortal_SIP extends BaseTest {
	
	private String siteName, nonCRVisitName;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_3764_VerifySubjectAndRatingPagesInSponsorPortal_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties properties = Configuration.readTestData("TestDataFile");
		studyName=properties.getProperty("TestStudyName");
		subjectName=properties.getProperty("AutoTestSubjectName");
		nonCRVisitName=properties.getProperty("NonCRVisit");
		
	}

	/**
	 * Test Case Id: FP-TC-3764 -Verify Subject and Rating Pages in Sponsor Portal
	 * @throws Exception 
	 */
	@Test(description = "FP-TC-3764 -Verify Subject and Rating Pages in Sponsor Portal", groups = { "smoke" })

	public void FPTC_3764_VerifySubjectAndRatingPagesInSponsorPortal() throws Exception {

		reportLog("1.Login in to application");
		dashBoardPage = loginPage.sponsorLogin(AT_PRODSponsorUserType3, AT_Password);
		
		reportLog("1.1 Home page is displayed");
		dashBoardPage.verifyMedavantePortalPage();

		reportLog("2. Go to NAVIGATE>>Study");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("3. Select " + studyName + " study  and " + siteName + " From pop up window");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("4. Search subject with completed visit");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subjectName);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subjectName);

		reportLog("4.1. Verify Subject detail page should display with data");
		subjectDetailPage.verifySubjectDetailAndScreeningNumberIsDisplayed(subjectName);

		reportLog("5. Select Completed Visit from All visits");
		subjectDetailPage.clickOnVisitRow(nonCRVisitName);

		reportLog("6. Click on Thumb nail image of the assessment");
		assessmentDetailPage = subjectDetailPage.verifyAndClickOnAfterSubmittedThumbnailImage();

		reportLog("6.1 Verify Assessment page display");
		assessmentDetailPage.verifyAssessmentDetailsDisplayed();

		reportLog("7. Click on Thumb nail image of the completed assessment in the assessment page");
		assessmentDetailPage.openAssessmentFormSourcePDFView();

		reportLog("8. Verify the generated PDF file");
		assessmentDetailPage.verifyFooterDataOnEachPage(studyName);

		reportLog("9. Go back to the study details page");
		assessmentDetailPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("9.1 Click on Visits from side menu");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("9.2 Visit grid is displayed with all visit data related to the study");
		studyNavigatorDashBoardPage.verifyVisitListIsOpened();

		reportLog("10. Select one completed Visit and click on it.");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(nonCRVisitName, subjectName);

		reportLog("10.1 Visit details page is displayed with all assessments associated with that visit");
		visitDetaiLPage.verifyVisitDetails(Constants.studyLabelText, studyName);

		reportLog("11. Go back to the study details page");
		visitDetaiLPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,Constants.NavigateText, Constants.StudyText);

		reportLog("11.2 Click on Assessment option from side menu to open the Assessment list");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("11.3 Assessment grid is displayed with all data");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("12. Select one completed Assessment and click on it.");
		assessmentDetailPage = studyNavigatorDashBoardPage.selectByVisitAndSubjectName(nonCRVisitName, subjectName);

		reportLog("12.1 Assessment details page is displayed with Versions");
		assessmentDetailPage.verifyAssessmentDetails(Constants.studyLabelText, studyName);

		reportLog("13. Verify Subject on Assessment Detail page");
		assessmentDetailPage.verifyDisplayedSubject(subjectName);

		reportLog("14. Logout application");
		loginPage.logoutApplication();

		reportLog("14.1 Verify user is logout");
		loginPage.verifyUserLogout();

		
	}

	
}

