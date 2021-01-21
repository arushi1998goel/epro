package net.medavante.portal.utilities;

import java.util.Arrays;
import java.util.List;

public class StudyModuleConstants {

	public static final String analyticsTab = "Analytics";

	public static final List<String> productType = Arrays.asList("Training", "Pre-Qualification", "Site Rating",
			"Independent Rating", "Independent Review", "Mobile PRO", "Subject", "Observer", "Analytics");

	
	/***
	 * This is not implemented on test environment.
	 */
	
	/*public static final List<String> sidePanelOptions = Arrays.asList("Subjects", "Visits", "Assessments",
		"Questionnaires", "Messages", "Medications", "Logged Events", "Clinicians / Raters",
		"Review Training Appeal", "Review Qualification Appeal", "Review Training Dispencation",
			"Assign Qualification Status", "Assign Certification Status", "Override Qualification Status",
		"Override Certification Status", "Appeal Qualification Status", "Appeal Training Status",
		"Request Training Dispensation", "Security Confirmation Demo", "Qualification Card Demo");
*/
	
	
	/***
	 * This is the updated list on test environment.
	 */
	public static final List<String> sidePanelOptions = Arrays.asList("Subjects", "Visits", "Assessments",
			"Questionnaires", "Messages", "Medications", "Logged Events");


	public static final List<String> subjectsFilterByTitleText = Arrays.asList("Subject Status", "Visit Status",
			"Assessment Status", "Review", "Other", "Inbox", "Sent Items", "Dates");
	
}
