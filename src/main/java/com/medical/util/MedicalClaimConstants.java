/**
 * 
 */
package com.medical.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author user1
 *
 */
public class MedicalClaimConstants {

	private static final Logger LOGGER = LoggerFactory.getLogger(MedicalClaimConstants.class);

	private MedicalClaimConstants() {
	}

	public static final String CREDENTIALS_EMPTY = "UserName or Password cannot be empty";
	public static final String LOGIN_SUCCESS = "Logged In Successfully";
	public static final String LOGIN_FAILURE = "Incorrect Username or password";
	public static final Integer LOGIN_SUCCESS_CODE = 201;
	public static final Integer LOGIN_FAILURE_CODE = 401;

	public static final String NO_DISEASE_FOUND = "No Disease found with given Id";
	public static final String INVALID_CLAIM_AMOUNT = "Your Claim Amount is greater than the permitted amount ";
	public static final String INVALID_DATE = "Admitted date can't be after Discharge Date ";
	public static final String NO_USER = "Invalid UserId";
	public static final String CLAIM_REQUEST_STATUS = "PENDING";
	public static final String CLAIM_REQUEST_PASS = "Your Claim Request Is passed";
	public static final Integer CLAIM_REQUEST_SUCCESS_CODE = 201;
	public static final String UPLOADED_FOLDER = "c://tempo//";

	public static final Integer APPROVER_ID = 1;
	public static final String RECORD_NOT_FOUND = "Record not found";
	public static final String PENDING = "PENDING";
	public static final String REJECTED = "REJECTED";
	public static final String FORWARD = "FORWARD";
	public static final String APPROVED = "APPROVED";
	public static final String UPDATED = "updated successfully";

	public static final String HOSPITAL_NOT_FOUND = "Hospital not found";
	public static final String DISEASE_NOT_FOUND = "Disease not found";

	public static final Integer POLICY_STATUS_CODE = 201;
	public static final String POLICY_MESSAGE = "Your Welcome!";
	public static final String USER_NOT_EXIST = "User not exist";

	public static final String INVALID_AADHAR_NUMBER = "Please Check your Aadhar Number";
	public static final String INVALID_DOB_NUMBER = "Please Check your Date-of-birth";

	public static String proofUpload(MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		try {

			byte[] bytes = file.getBytes();
			Path path = Paths.get(MedicalClaimConstants.UPLOADED_FOLDER + file.getOriginalFilename());

			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded");

		} catch (IOException e) {
			LOGGER.info("Exception");
		}

		return "redirect:/uploadStatus";

	}

}
