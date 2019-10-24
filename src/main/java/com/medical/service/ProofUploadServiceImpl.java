
package com.medical.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.controller.ProofUploadController;
import com.medical.util.MedicalClaimConstants;

/**
 * @author Shiva
 * @since 1.0
 *
 */
@Service
public class ProofUploadServiceImpl implements ProofUploadService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProofUploadController.class);

	/**
	 * This method used to upload the document for proof to the approver while
	 * approving/rejecting
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return string
	 */
	@Override
	public String proofUpload(MultipartFile file, RedirectAttributes redirectAttributes) {

		LOGGER.info("Proof upload service");

		return MedicalClaimConstants.proofUpload(file, redirectAttributes);

	}

}
