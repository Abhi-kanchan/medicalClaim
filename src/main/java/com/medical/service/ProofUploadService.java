/**
 * 
 */
package com.medical.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Shiva
 *
 */
@Service
public interface ProofUploadService {

	public String proofUpload(MultipartFile file,RedirectAttributes redirectAttributes);

}
