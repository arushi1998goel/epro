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

public class FPTC_2767_SubjectStatus_LabelDisplayingForScreenFailedStatus_SIP extends BaseTest {

	private String subject, visitScreenFailed;

	@Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
	public FPTC_2767_SubjectStatus_LabelDisplayingForScreenFailedStatus_SIP(String browser) {
		super(browser);
	}

	@BeforeMethod
	public void getTestData() throws Exception {
		System.setProperty("className", getClass().getSimpleName());
		Properties prop = Configuration.readTestData("RegressionTestData");
		studyName = prop.getProperty("Study2224New");
		subject = prop.getProperty("SubjectWithScreenedFailedStaus");
		visitScreenFailed = prop.getProperty("VisitWithStatusCompleted");

	}

	/**
	 * ===================================================================================================
	 * Test Case Id : FP-TC-2767 || Test Case Name : Subject Status - Label
	 * Displaying for Screen Failed status
	 * ===================================================================================================
	 * 
	 * @throws Exception
	 */
	@Test(description = "FP-TC-2767 : Subject Status - Label Displaying for Screen Failed status", groups = { "" })
	public void FPTC_2767_SubjectStatus_LabelDisplayingForScreenFailedStatus() throws Exception {

		reportLog("1.1:Login in to application");
		dashBoardPage = loginPage.loginInApplication(AT_PRODSiteCoordinatorCRSelfSchedule, AT_Password);

		reportLog("1.2: Navigate to a Subject Detail screen");
		studyNavigatorDashBoardPage = dashBoardPage.selectHorizontalUpperNavMenuItem(StudyDashBoardPage.class,
				Constants.NavigateText, Constants.StudyText);

		reportLog("1.3: Select study on study navigator");
		studyNavigatorDashBoardPage.selectStudy(studyName,Constants.ATAssignedRater_10);

		reportLog("1.4: Verify read only Label is displayed under " + Constants.StudyDashBoard_columnName_Status
				+ " Column");
		studyNavigatorDashBoardPage
				.verifyReadOnlyLabelIsDisplayedUnderDesiredColumn(Constants.StudyDashBoard_columnName_Status);

		reportLog("2.1:Search and click on Subject #1");
		studyNavigatorDashBoardPage.searchFilterValueByColumnNameAndValue(Constants.StudyDashBoard_columnName_Subject,
				subject);
		subjectDetailPage = studyNavigatorDashBoardPage.clickOnSearchedSubject(subject);

		reportLog("2.2:Verify Subject Details Page is opened");
		subjectDetailPage.verifysubjectDetailsLabelIsDisplayed();

		reportLog("2.3: Verify subject Status is displayed as Read-only label with status - "
				+ Constants.SubjectStatus_ScreenFailed);
		subjectDetailPage.verifySubjectStatusDisplayedAsReadOnlyLabel(Constants.SubjectStatus_ScreenFailed);

		reportLog("3.1: Navigate back to study navigator");
		subjectDetailPage.navigateBack();

		reportLog("3.2: Navigate to Visit list");
		studyNavigatorDashBoardPage.navigateToVisitsListing();

		reportLog("3.3: Verify Visit list is displayed");
		studyNavigatorDashBoardPage.verifyVisitListIsOpened();

		reportLog("3.4: Verify read only Label is displayed under " + Constants.StudyDashBoard_columnName_Status
				+ " Column");
		studyNavigatorDashBoardPage
				.verifyReadOnlyLabelIsDisplayedUnderDesiredColumn(Constants.StudyDashBoard_columnName_Status);

		reportLog("4.1: Select Visit from Pr.#2");
		visitDetaiLPage = studyNavigatorDashBoardPage.clickOnVisitByVisitAndSubjectName(visitScreenFailed, subject);

		reportLog("4.2: Select Visit from Pr.#2");
		visitDetaiLPage.verifyVisitPageIsDisplayed();

		reportLog("4.3: Verify subject Status is displayed as Read-only label with status - "
				+ Constants.SubjectStatus_ScreenFailed);
		visitDetaiLPage.verifySubjectStatus(Constants.SubjectStatus_ScreenFailed);

		reportLog("5.1: Navigate back to study navigator");
		subjectDetailPage.navigateBack();

		reportLog("5.2: Navigate to Assessment list");
		studyNavigatorDashBoardPage.navigateToAssessmentsListing();

		reportLog("5.3: Verify Assessment list is displayed");
		studyNavigatorDashBoardPage.verifyAssessmentListIsOpened();

		reportLog("5.4: Verify read only Label is displayed under " + Constants.StudyDashBoard_columnName_Status
				+ " Column");
		studyNavigatorDashBoardPage
				.verifyReadOnlyLabelIsDisplayedUnderDesiredColumn(Constants.StudyDashBoard_columnName_Status);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}
}