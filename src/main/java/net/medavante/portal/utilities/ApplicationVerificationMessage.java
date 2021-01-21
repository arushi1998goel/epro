package net.medavante.portal.utilities;

public interface ApplicationVerificationMessage {
  String duplicateSubjectErrorMessage="Failed to create subject. Subject with the same temporary id already exists.";
  String invalidCredentialsMessage="Please check username and password."; 
  String scheduleCentralRatingAssesmentMessage="are you sure you want to schedule this assessment for";
  String doubleBookingCentralRatingFirstMessage="Are you sure you want to schedule this assessment for";
  String doubleBookingCentralRatingEndMessage="? Warning: You are double-booking.";
  String cancelCentralRatingAppointmentMessage="Are you sure you want to cancel this appointment?";
  String recallCentralRatingAppointmentMessage="The assessment will be recalled. Are you sure you want to proceed?";
  String initiateCentralRatingPopUpMessage="You have selected to initiate the appointment. Are you sure you want to continue?";
  String moveVisitSuccessfulMessage="The visit has been changed successfully";
}
