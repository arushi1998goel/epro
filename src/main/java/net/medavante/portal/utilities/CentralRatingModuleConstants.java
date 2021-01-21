package net.medavante.portal.utilities;

public interface CentralRatingModuleConstants {

	
//========================= CR Assessment's Coloumns =================//
	String visitStatus = "Visit Status";
	String subjectStatus="Subject Status";
	
//==========================CR Status==========================//
	String requestStatus="Requested"; 
	String scheduledStatus="Scheduled"; 
	String initiatedStatus="Initiated"; 
	String inProgressStatus="In Progress"; 
	String completeStatus="Complete"; 
	String cancelledStatus="Cancelled"; 
	String editingStatus="Editing"; 
	String pendingStatus="Pending";
	
	
	
//======================clinician UserName and appoontment to select======//
	String clinician14Name="VP Tester14", clinician15Name="VP Tester15",clinician16Name="VP Tester16",clinicianTimeToBeSelect="11:30 AM",ReScheduleTimeSlotToBeSelect="12:30 PM",
	clinicianTimeToBeSelect1="3:45 PM",ReScheduleTimeSlotToBeSelect1="4:30 PM"; 
	
//======Cr Listing Filter Column label ================//
	
	String subjectNumberColumnLabel="Subject Number"; 
	String differentTimeSlotMessage="We could not find an appointment for your selected time. Please choose from the suggested times below or select a different date/time.";
	String selectDifferentTime="Sorry, the time you selected has been taken. Please choose from the times below or select a different date."; 
	String clinicansNotAvailableMesssage="Clinicians are not available on the selected time/date. Please select a different time/date.";
	
	
}