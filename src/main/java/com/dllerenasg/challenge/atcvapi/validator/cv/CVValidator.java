package com.dllerenasg.challenge.atcvapi.validator.cv;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.dllerenasg.challenge.atcvapi.exception.GlobalExceptionBody.ErrorDetails;
import com.dllerenasg.challenge.atcvapi.model.cv.*;
import com.dllerenasg.challenge.atcvapi.validator.Validator;
import static com.dllerenasg.challenge.atcvapi.validator.ValidationUtils.*;

@Service
public class CVValidator implements Validator<CV> {
	
	 private static final String REQUIRED_FIELD = "is a required field";
	 private static final String INVALID_FORMAT = "is in an invalid format";

	 public void validate(CV cv) {
		 List<ErrorDetails> errorDetails = new ArrayList<>();
		 validateName(cv.getFirstName(), cv.getLastName(), errorDetails);
		 validateEmail(cv.getEmail(), errorDetails);
		 validatePhoneNumber(cv.getPhoneNumber(), errorDetails);
		 validateCity(cv.getCity(), errorDetails);
		 validateCountry(cv.getCountry(), errorDetails);
		 validateSkills(cv.getSkills(), errorDetails);
		 validateLanguages(cv.getLanguages(), errorDetails);
		 validateWorkExperience(cv.getWorkexperience(), errorDetails);
		 validateEducation(cv.getEducation(), errorDetails);
		 validateChallenges(cv.getChallenges(), errorDetails);
	    }

	 private void validateName(String firstName, String lastName, List<ErrorDetails> errorDetails) {
		 if (!isValidString(firstName))
			 errorDetails.add(newErrorDetail(
					 REQUIRED_FIELD, "First name"));
		 if (!isValidString(lastName))
			 errorDetails.add(newErrorDetail(
					 REQUIRED_FIELD, "Second name"));
	 }

	 private void validateEmail(String email, List<ErrorDetails> errorDetails) {
		 if (!isValidString(email))
			 errorDetails.add(newErrorDetail(
					 REQUIRED_FIELD, "Email"));
		 else if (!isValidEmail(email))
			 errorDetails.add(newErrorDetail(
					 INVALID_FORMAT, "Email"));
	 }

	 private void validatePhoneNumber(String phoneNumber, List<ErrorDetails> errorDetails) {
		 if (!isValidString(phoneNumber))
			 errorDetails.add(newErrorDetail(
					 REQUIRED_FIELD, "Phone number"));
		 else if (!isValidPhoneNumber(phoneNumber))
			 errorDetails.add(newErrorDetail(
					 INVALID_FORMAT, "Phone number"));
	 }

	 private void validateCity(String city, List<ErrorDetails> errorDetails) {
		 if (!isValidString(city))
			 errorDetails.add(newErrorDetail(
					 REQUIRED_FIELD, "City"));
	 }
	 
	 private void validateCountry(String country, List<ErrorDetails> errorDetails) {
		 if (!isValidString(country))
			 errorDetails.add(newErrorDetail(
					 REQUIRED_FIELD, "Country"));
	 }

	 private void validateSkills(List<Skill> skills, List<ErrorDetails> errorDetails) {
		 if (skills != null) {
			 for (Skill s: skills) {
				 if (!isValidString(s.getName()))
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Skill name"));
				 else if (s.getPercentage() <= 0 || s.getPercentage() > 100)
					 errorDetails.add(newErrorDetail(
							 INVALID_FORMAT, "Percentage for skill " + s.getName()));
			 }
		 }
	 }

	 private void validateLanguages(List<Language> languages, List<ErrorDetails> errorDetails) {
		 if (languages != null) {
			 for (Language l: languages) {
				 if (!isValidString(l.getName()))
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Language name"));
				 else if (l.getPercentage() <= 0 || l.getPercentage() > 100)
					 errorDetails.add(newErrorDetail(
							 INVALID_FORMAT, "Percentage for language " + l.getName()));
	         }
	     }
	 }

	 private void validateWorkExperience(List<WorkExperience> workExperiences, List<ErrorDetails> errorDetails) {
		 if (workExperiences != null) {
			 for (WorkExperience we: workExperiences) {
				 if (!isValidString(we.getTitle()))
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Work title"));
				 else if (!isValidString(we.getCompany()))
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Company name for work " + we.getTitle()));
				 else if (we.getFrom() == null)
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Beginning date for work " + we.getTitle() + 
							 " at " + we.getCompany()));
				 else if (we.getCurrent().equals(false)) {
					 if (we.getTo() == null)
						 errorDetails.add(newErrorDetail(
								 REQUIRED_FIELD, "Ending date for work " + we.getTitle() + 
								 " at " + we.getCompany()));
					 else if (!we.getTo().after(we.getFrom()))
						 errorDetails.add(newErrorDetail(
								 REQUIRED_FIELD, "Ending date for work " + we.getTitle() + 
								 " must be greater than beginning date"));
				 }
			 }
		 }
	 }

	 private void validateEducation(List<Education> educations, List<ErrorDetails> errorDetails) {
		 if (educations != null) {
			 for (Education e: educations) {
				 if (!isValidString(e.getSchoolName()))
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "School name"));
				 else if (!isValidString(e.getCareer()))
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Career name at " + e.getSchoolName()));
				 else if (!isValidString(e.getDegree()))
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Degree of your career at " + e.getSchoolName()));
				 else if (!e.getDegree().equals("Bachelor") && !e.getDegree().equals("Master") && !e.getDegree().equals("Ph"))
					 errorDetails.add(newErrorDetail(
							 INVALID_FORMAT, "Degree of you career at " + e.getSchoolName() + 
							 " should be either 'Bachelor', 'Master', or 'Ph'"));
				 else if (e.getFrom() == null)
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Beginning date of your career at " + e.getSchoolName()));
				 else if (e.getTo() == null)
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "(Actual or expected) Ending date for your " + 
					 "career at " + e.getSchoolName()));
				 else if (!e.getTo().after(e.getFrom()))
					 errorDetails.add(newErrorDetail(
							 INVALID_FORMAT, "(Actual or expected) Ending date for your " + 
					 "career at " + e.getSchoolName() + " must be greater than beginning date"));
			 }
		 }
	 }

	 private void validateChallenges(List<Challenge> challenges, List<ErrorDetails> errorDetails) {
		 if (challenges != null) {
			 for (Challenge c: challenges) {
				 if (!isValidString(c.getName()))
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Name of challenge"));
				 else if (!isValidString(c.getDescription()))
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Description of challenge " + c.getName()));
				 else if (c.getDate() == null)
					 errorDetails.add(newErrorDetail(
							 REQUIRED_FIELD, "Date for completion of challenge " + c.getName()));
			 }
		 }
	 }

	 private ErrorDetails newErrorDetail(String errorMessage, String fieldName) {
		 ErrorDetails error = new ErrorDetails();
		 error.setErrorMessage(errorMessage);
		 error.setFieldName(fieldName);
		 return error;
	 }
	 
}