package com.medavante.testscripts;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import net.medavante.portal.datamodel.PeopleModel;
import net.medavante.portal.dataproviders.DataProviders;
import net.medavante.portal.pages.MedAvantePortalPage;
import net.medavante.portal.pages.PeoplePage;
import net.medavante.portal.selenium.core.BaseTest;
import net.medavante.portal.selenium.core.Configuration;

public class AddNewPeople extends BaseTest {

    @Factory(dataProvider = "Browsers", dataProviderClass = DataProviders.class)
    public AddNewPeople(String browser) {
        super(browser);
    }

    String className = getClass().getSimpleName();
    private PeoplePage peoplePage;
    private MedAvantePortalPage medAvantePortalPage;
    private PeopleModel peopleModel;

    @BeforeMethod
    public void getTestData() throws Exception {
        Properties properties = Configuration.readTestData("People");
        peopleModel = new PeopleModel.PeopleModelBuilder(properties.getProperty("firstName"), properties.getProperty(
                "lastName")).setNameAttributePrefix(properties.getProperty("nameAttributePrefix")).setDegree(properties
                        .getProperty("degree")).setUserid(properties.getProperty("useid")).setComments(properties
                                .getProperty("comments")).build();
        System.setProperty("className", getClass().getSimpleName());
    }

    @Test
    public void test_AddNewPeople() throws Exception {

        // login in to application
        reportLog("Login in to application");
        medAvantePortalPage = loginPage.loginInApplication(userName, password);
        ////captureScreenshot("Step_1");

        // Verify MedAvante portal and select Administration option.
        reportLog("Verify MedAvante portal and select Administration option.");
        medAvantePortalPage = medAvantePortalPage.verifyMedavantePortalPage();
        peoplePage = medAvantePortalPage.selectAdministrationPortal(PeoplePage.class);
        //captureScreenshot("Step_2");

        // Add new people
        reportLog("Add new people");
        peoplePage = peoplePage.addPeople(peopleModel);
        //captureScreenshot("Step_3");

        // Delete added people
        reportLog("Delete added people");
        peoplePage = peoplePage.deletePeople(peopleModel);
        //captureScreenshot("Step_4");

        // Logout application.
        loginPage = peoplePage.logoutApplication();
        reportLog("Logout application");
        //captureScreenshot("Step_5");
    }

}
