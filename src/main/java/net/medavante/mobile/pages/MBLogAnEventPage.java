package net.medavante.mobile.pages;

import java.util.List;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import net.medavante.mobile.appium.core.MobileCoreFunctions;

public class MBLogAnEventPage extends MobileCoreFunctions{

	public MBLogAnEventPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	@AndroidFindBy(xpath="//android.widget.ListView//android.view.ViewGroup/android.view.View/following-sibling::android.widget.TextView")
	private List<MobileElement> eventList; 
	
	@AndroidFindBy(xpath="//android.view.ViewGroup//android.widget.Button")
	private MobileElement startBtn; 
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='4']")
	private MobileElement nextArrowIcon; 
	
	@AndroidFindBy(xpath= "//android.widget.ListView//android.view.View//android.view.View//android.widget.RadioButton[1]")
	private MobileElement selectRadioBtn;
	
	@AndroidFindBy(xpath="//android.widget.ListView[@index='2']//android.view.View[@index='2']")
	private MobileElement setHealthArrow; 
	
	@AndroidFindBy(xpath = "//android.widget.Button[@class='android.widget.Button']")
	private MobileElement continueBtn;

	
	public void clickOnEvent(String eventToBeOpen){
		for(MobileElement we:eventList){
			if(we.getText().equalsIgnoreCase(eventToBeOpen)){
				click(we);
				capturescreen("Screenshot");
				break;
			}
		}
	}
	
	public void clickStartBtn() {
		click(startBtn);
		_normalWait(DEFAULT_WAIT_ELEMENT);
	}
	
	public void completeEvent() {
		click(nextArrowIcon);
		click(nextArrowIcon);
		click(selectRadioBtn);
		click(nextArrowIcon);
		click(selectRadioBtn);
		click(nextArrowIcon);
		click(selectRadioBtn);
		click(nextArrowIcon);
		click(selectRadioBtn);
		click(nextArrowIcon);
		click(selectRadioBtn);
		click(nextArrowIcon);
		click(nextArrowIcon);
		click(setHealthArrow);
		click(nextArrowIcon);
		click(nextArrowIcon);
		capturescreen("Screenshot");

	}
	
	public void clickContinueBtn() {
		click(continueBtn);
	}

}
