package com.medavante.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.datamodel.StudyModel;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.StudiesPage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;

public class AddNewStudy extends BaseTest {

    @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
    public AddNewStudy(String browser) {
        super(browser);
    }

    String className = getClass().getSimpleName();
    private StudiesPage studiesPage;
    private MedAvantePortalPage medAvantePortalPage;
    private StudyModel studyModel;

    private String language;
    private String country;
    private String countryCode;
    private String site;
    private String facility;
    private String systemRole;
    private String studyName;

    @BeforeMethod
    public void getTestData() throws Exception {
        Properties properties = Configuration.readTestData("Study");
        studyName = properties.getProperty("studyName") + getRandomInteger(1000, 9999);
        studyModel = new StudyModel.StudyModelBuilder(studyName, properties.getProperty("abbreviation"), properties
                .getProperty("phase"), properties.getProperty("sponsor")).setDrugName(properties.getProperty(
                        "drugName")).build();
        language = properties.getProperty("language");
        country = properties.getProperty("country");
        countryCode = properties.getProperty("countryCode");
        site = properties.getProperty("site");
        facility = properties.getProperty("facility");
        systemRole = properties.getProperty("systemRole");
        System.setProperty("className", getClass().getSimpleName());
    }

    @Test
    public void test_AddNewStudy() throws Exception {

        // login in to application
        reportLog("Login in to application");
        medAvantePortalPage = loginPage.loginInApplication(userName, password);
        //captureScreenshot("Step_1");

        // Verify MedAvante portal and select Administration option.
        reportLog("Verify MedAvante portal and select Administration option.");
        medAvantePortalPage = medAvantePortalPage.verifyMedavantePortalPage();
        studiesPage = medAvantePortalPage.selectAdministrationPortal(StudiesPage.class);
        //captureScreenshot("Step_2");

        // Add new people
        reportLog("Add new people");
        studiesPage = studiesPage.addNewStudy(studyModel);
        //captureScreenshot("Step_3");

        // Search added study
        reportLog("Delete added people");
        studiesPage = studiesPage.searchStudy(studyModel);
        //captureScreenshot("Step_4");

        // Select language for added study.
        reportLog("Select language for added study.");
        studiesPage = studiesPage.selectLanguage(language);
        //captureScreenshot("Step_5");

        // Select country and add required code in it.
        reportLog("Select country and add required code in it.");
        studiesPage = studiesPage.selectCountry(country, countryCode);
        //captureScreenshot("Step_6");

        // And site, facility, people and language in study.
        reportLog("And site, facility, people and language in study.");
        studiesPage = studiesPage.enterSitesData(site, facility, systemRole, language);
        //captureScreenshot("Step_7");

        // Logout application.
        reportLog("Logout application");
        loginPage = studiesPage.logoutApplication();
        //captureScreenshot("Step_8");

    }

}
