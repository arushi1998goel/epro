package net.medavante.portal.pages.administration;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import net.medavante.portal.selenium.core.BasePage;

public class AdministrationStudiesVirgilUniversityPage extends BasePage{



	/**
     * ====================================
     *             LOCATORS
     * ====================================
     */

	
	@FindBy(xpath="//h1[contains(text(),'Qualification & Training')]")
    private WebElement qualificationAndTraining;
	
	@FindBy(xpath="//h1[contains(text(),'General Training')]")
    private WebElement generalTraining;
	
//	@FindBy(xpath="//span[text()='on-boarding']")
//    private WebElement trackingOnBoarding;

//    @FindBy(xpath="//span[text()='Activation']")
//    private WebElement trackingActivation;

	@FindBy(xpath="//input[@id='manage-site-raters-checkbox' and @disabled='disabled']")
	private WebElement disableCheckbox;
	
	@FindBy(xpath="//div[contains(text(),'eLearning Brief:')]/strong[text()='Disabled']")
	private WebElement elearningBreifDisabled;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'prerequisite training')]/ancestor::div[@class='widget-frame']//span[@class='collapse-opener ng-binding']")
	private WebElement preRequisiteActivationArrow;
	
	@FindBy(how=How.XPATH,using="//span[text()='prerequisite training']/preceding-sibling::button")
	private WebElement preRequisiteButton;
	
	@FindBy(xpath="//h4[contains(text(),'Manage Prerequisite Training')]")
	private WebElement managePreRequisitePopUpTitle;
	
	@FindBy(xpath="//div[@dataitems='eLearningOptions']/button/span[@id='selectedStudy']")
	private WebElement eLearningDropDown;
	
	@FindBy(xpath="//div[@class='inner-col-title separated']/span")
	private WebElement informationText;
	
    @FindBy(xpath="//div[@class='modal-dialog dialog-md']//button[@class='btn btn-active btn-without-icon']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//div[@class='modal-dialog dialog-md']//button[@class='btn btn-default btn-without-icon']")
	private WebElement cancelBtn;
	
	@FindBy(xpath="//div[@class='modal-dialog dialog-md']//span[contains(text(),'×')]")
    private WebElement closeControl;

    @FindBy(xpath="//form[@name='prerequisiteTrainings']//div[@class='input-group']/input")
    private WebElement searchfield;

    @FindBy(xpath="//span[text()='In Use for Other Studies']/..//label//span[contains(@class,'ng-binding')]")
    private List<WebElement> trainingListGroupByuseinstudy;

    @FindBy(xpath="//span[text()='Not In Use']/..//label")
    private WebElement notInUsetraining;

    @FindBy(xpath="//label[contains(text(),'Are you sure you want to leave this page? The chan')]")
    private WebElement confrmatnmsg;

    @FindBy(xpath="//div[text()='Yes, close form']")
    private WebElement yesCloseformButton;

    @FindBy(xpath="//div[text()='No, back to form']")
    private WebElement noBackformButton;

    @FindBy(xpath="//span[text()='prerequisite training']/ancestor::div[2]//span[@class='collapse-opener collapsed ng-binding']")
    private WebElement collapsedDeativatedSection;

    @FindBy(xpath="//h1[contains(text(),'Qualification & Training')]")
    private WebElement virgilUniversityHeader;

    @FindBy(xpath="//label[@class='chk-item-frame ng-scope has-focus']//span")
    private List<WebElement> trainingList;
    

    @FindBy(xpath="//button[@class='remove-item-action']")
    private WebElement removeTrainingButton;
    
       /**=====================================
        *                Constructor
        * =====================================
        * @param driver
        */
    
      public AdministrationStudiesVirgilUniversityPage(WebDriver driver) {
        	super(driver);
        }


      
      
      /**
      * ====================================
      *              METHODS
      * ====================================
      */

    /**
    *  verify deactivated e learning section is collapsed 
    */
     public void verifyDeactivatedELearningSectionTrainingCollapsedDisplayed()
      {
	      moveToElement(collapsedDeativatedSection);
	      Assert.assertTrue(isElementDisplayed(collapsedDeativatedSection));
	      reportInfo();
       }
   /**
    *  verify e learning activated training displayed
    * @param trainingName
    */
    public void verifyActivatedTrainingIsDisplayedInPreRequisiteSection(String trainingName)
    {
    	WebElement trainingToBeVerified = 
			driver.findElement(ByLocator("//span[contains(text(),'Activated')]/following::div//span[text()='"+trainingName+"']"));
	    moveToElement(trainingToBeVerified);
	    Assert.assertTrue(isElementDisplayed(trainingToBeVerified));
	    reportInfo();
    }
   /**
    *  NO back to form button displayed
    */
    public void verifyNoBackToFormButtonDisplayed()
    {
    	moveToElement(noBackformButton);
	    Assert.assertTrue(isElementDisplayed(noBackformButton));
	    reportInfo();
    }
    /*
     *  select No Back form Option
     */
    public void selectNoBackToFormOption()
    {
    	clickOn(noBackformButton);
	    reportInfo();
    }
    /*
     *  select yes option
     */
    public void selectYesCloseformOption()
    {
    	clickOn(yesCloseformButton);
	    reportInfo();
    }
    /**
     *  yes close form button displayed
     */
    public void verifyYesCloseFormBUttonDisplayed()
    {
    	moveToElement(yesCloseformButton);
    	Assert.assertTrue(isElementDisplayed(yesCloseformButton));
	    reportInfo();
     }
    /**
     *  verify cancellation msg on  after cancel popup
     */
    public void verifyConfirmationMessageOnCancelPopUp()
    {
    	moveToElement(confrmatnmsg);
	    Assert.assertTrue(isElementDisplayed(confrmatnmsg));
	    reportInfo();
    }
    /**
     *  search training By Search Field
     * @param trainingName
     */
    public void populateTrainngNameinSearchField(String trainingName)
    {
    	inputText(searchfield, trainingName);
	    reportInfo();
    }
    /*
     *  verify training with header not in used Displayed
     */
    public void verifyNOtInUseTrainingIsDisplayed()
    {
    	moveToElement(notInUsetraining);
	    Assert.assertTrue(isElementDisplayed(notInUsetraining));
	    reportInfo();
    }
    /**
     * verify in use training is displayed
     * @param trainingName
     */
    public void verifyInUseTrainingIsDisplayed(String trainingName)
    {
    	WebElement trainingtobeverified =
			driver.findElement(ByLocator("//span[text()='In Use']/..//span[text()='"+trainingName+"']"));
	    moveToElement(trainingtobeverified);
	    Assert.assertTrue(isElementDisplayed(trainingtobeverified));
	    reportInfo();
    }
    /**
     *  verify Training not displayed
     * @param trainingTobeVerified
     */
    public void verifyTrainingIsNotDisplayed(String trainingTobeVerified)
    {
    	boolean flag=true;
	    try {
	    	for(WebElement web:trainingListGroupByuseinstudy)
	    	{
	    		if (web.getText().trim().contains(trainingTobeVerified)) {
	    			flag=false;
	    		}
	    	}
	    } catch (Exception e) {
	    }
	    Assert.assertTrue(flag);
	    reportInfo();
      }
    /**
     *  mouse hover on selected training info bar
     * @param trainingName
     */
    public void mouseHoverOnTrainingInfoIcon(String trainingName)
    {
    	WebElement infobartomousehover = driver.findElement(ByLocator
	("//span[text()='In Use']/..//span[text()='"+trainingName+"']/../following-sibling::div//span[@class='icon-small icon-info']"));
    	moveToElement(infobartomousehover);
	    Actions actn = new Actions(driver);
	    actn.moveToElement(managePreRequisitePopUpTitle).build().perform();
    }
    /**
     *  verify Training Is Selected
     * @param trainingName
     */
    public void verifyTrainingIsSelected(String trainingName)
    {
    	WebElement trainingtobeverified = driver.findElement(ByLocator(""
    			+ "//span[text()='In Use']/..//span[text()='"+trainingName+"']"));
        Assert.assertTrue(isElementDisplayed(trainingtobeverified));
        reportInfo();
    }
    /**
     *  select Training
     * @param trainingTobeVerified
     */
    public void selectTraining(String trainingTobeVerified)
    {
    	for(WebElement web:trainingList)
    	{
    		if (web.getText().trim().contains(trainingTobeVerified)) {
			clickOn(web);
			break;
    		}
	    }
     }
    /**
     *  verify Training is Displayed
     * @param trainingTobeVerified
     */
    public void verifyTrainingIsDisplayed(String trainingTobeVerified)
    {
    	boolean flag=true;
    	try {
    		for(WebElement web:trainingListGroupByuseinstudy)
    		{
    			if (web.getText().trim().contains(trainingTobeVerified)) {
			    flag=true;
    			}
	        }
    	} catch (Exception e) {
    	}
	    Assert.assertTrue(flag);
	    reportInfo();
    }
    /**
     *  verify Training list ,grouped by use in study displayed
     */
     public void verifyTrainingListGroupedByUseInStudyDisplayed()
     {
    	 boolean flag=true;
    	 try {
    		 if (trainingListGroupByuseinstudy.size()==0) {
    			 flag=false;
    		 }
    	 } catch (Exception e) {
    	 }
    	 Assert.assertTrue(flag);
    	 reportInfo();
     }
     /*
      *  verify search field is displayed
      */
      public void verifySearchFieldIsDisplayed()
     {
    	  moveToElement(searchfield);
	      Assert.assertTrue(isElementDisplayed(searchfield));
	      reportInfo();
     }
      /**
       *  verify close Control is Displayed
       */
      public void verifyCloseControlIDisplayed()
      {
    	  moveToElement(closeControl);
          Assert.assertTrue(isElementDisplayed(closeControl));
      }
    /**
     *  verify Cancel Button is displayed
     */
    public void verifyCancelBtnIsDisplayed()
    {
   	 moveToElement(cancelBtn);
   	 Assert.assertTrue(isElementDisplayed(cancelBtn));
    }
    /*
     *  select Cancel Control
     */
    public void selectCancelControl()
    {
    	clickOn(cancelBtn);
    	waitUntillFinishProcessSpinnerDisable();
    	reportInfo();
    }
    /*
     *  verify information text on manage e learning brief pop up window
     */
     public void verifyInformationTextIsdisplayed()
     {
    	 moveToElement(informationText);
    	 Assert.assertTrue(isElementDisplayed(informationText));
     }
     /*
      * verify Save button Is displayed
      */
     public void verifySaveButtonIsDisplayed()
     {
    	 moveToElement(saveBtn);
    	 Assert.assertTrue(isElementDisplayed(saveBtn));
	     reportInfo();
     }
     /*
      *  verify save button is disabled
      */
     public void verifySaveButtonIsDisabled()
     {
    	 Assert.assertFalse(saveBtn.isEnabled());
	     reportInfo();
     }
     /*
      *  select save control
      */
     public void selectSaveControl()
     {
    	 clickOn(saveBtn);
	     waitUntillFinishProcessSpinnerDisable();
	     reportInfo();
     }
     /**
      *  verify E Learning Drop down Displayed
      */
     public void verifyELearningDropDownDisplayed()
     {
    	 moveToElement(eLearningDropDown);
	     Assert.assertTrue(isElementDisplayed(eLearningDropDown));
     }
     /**
      *  verify e learning drop down is displayed & get selected value
      * @param expected
      */
     public void verifyELearningDropDownSelectedValue(String expected)
     {
    	 String actual = eLearningDropDown.getText();
    	 Assert.assertEquals(actual, expected);
     }

     /**
      *  select Enable option
      * @param optionToSelect
      */
     public void selectOptionFromELearningDropDown(String optionToSelect)
     {
    	 waitAndClick(eLearningDropDown);
    	 WebElement eLearningDropDown =findElement(By.xpath("//span[text()='"+optionToSelect+"']"));
	     waitAndClick(eLearningDropDown);
         reportInfo();
      }
     /*
      *  VERIFY MANAGE E-LEARNING POP UP is DISPLAYED
      */
     public void verifyManagePreRequisitePopupDisplayed()
     {
    	 waitUntillFinishProcessSpinnerDisable();
	     moveToElement(managePreRequisitePopUpTitle);
	     Assert.assertTrue(isElementDisplayed(managePreRequisitePopUpTitle));
     }
     /*
      *  VERIFY MANAGE E-LEARNING POP UP is NOT DISPLAYED
      */
     public void verifyManagePreRequisitePopupNotDisplayed()
     {
    	 waitUntillFinishProcessSpinnerDisable();
    	 boolean flag=true;
    	 try {
    		 if (managePreRequisitePopUpTitle.isDisplayed()) {
    			 flag=false;
    		 }
    	 } catch (Exception e) {
    	 }
    	 Assert.assertTrue(flag);
    	 reportInfo();
     }
     /*
      *  click on elearning button
      */
     public void selectPreRequisiteEditControl()
     {
    	 clickOn(preRequisiteButton);
    	 reportInfo();
     }
     /**
      *  verify e learning brief section is displayed Disable
      */
     public void verifyPreRequisiteWithNoActivatedtrainingDisplayed()
     {
    	 Assert.assertTrue(isElementDisplayed(preRequisiteActivationArrow));
	     reportInfo();
     }
     /**
      *  verify e learning brief section is displayed enable
      */
     public void verifyELearningBreifSectionDisplayedEnabled()
     {
//    	 Assert.assertTrue(isElementDisplayed(elearningBreifEnabled));
    	 reportInfo();
     }
     /*
      * @function: Verification of Identity Page
      *
      */
      public void verifyAdministrationStudiesUniversityPage() {
    	  Assert.assertTrue(isElementPresent(qualificationAndTraining) && isElementPresent(generalTraining));
      }
      /***
       * Verify rater tracking tab is in view mode
       */
       public void verifyRaterTrackingPageInViewMode() 
       {
	      String checkbox=disableCheckbox.getAttribute("disable");
	      if(checkbox.contains("disable")) {
		      Assert.assertTrue(true); }
       }
       /**
        *  verify virgil university tab Displayed
        */
       public void verifyVirgilUniversityTabIsDisplayed()
       {
    	   Assert.assertTrue(isElementDisplayed(virgilUniversityHeader));
      	 reportInfo(); 
       }
       public void removeSelectedTraining()
       {
    	   waitUntillFinishProcessSpinnerDisable();
    	   clickOn(removeTrainingButton);
    	   reportInfo();
       }
}
