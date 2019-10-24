
package com.medical.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.service.ProofUploadService;

/**
 * This class uploads the discharge document
 * 
 * @author shiva
 * @version 1.0
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "*", "*/" }, allowedHeaders = { "*", "*/" })
public class ProofUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProofUploadController.class);

	@Autowired
	ProofUploadService proofUploadService;

	/**
	 * This method used to upload the document for proof to the approver while
	 * approving/rejecting
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return string
	 */
	@PostMapping("/upload")
	public ResponseEntity<String> singleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		LOGGER.info("Proof upload Controller");
		return new ResponseEntity<>(proofUploadService.proofUpload(file, redirectAttributes), HttpStatus.OK);

	}

}
