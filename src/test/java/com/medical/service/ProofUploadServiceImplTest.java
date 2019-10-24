package com.medical.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.util.MedicalClaimConstants;

@RunWith(MockitoJUnitRunner.class)
public class ProofUploadServiceImplTest {

	@Mock
	MedicalClaimConstants medicalClaimConstants;

	@InjectMocks
	ProofUploadServiceImpl proofUploadServiceImpl;

	RedirectAttributes redirectAttributes = null;

	/*
	 * @Test public void testProofUpload() throws FileNotFoundException, IOException
	 * { MultipartFile multipartFile = new MockMultipartFile("Selenium Code.docx",
	 * new FileInputStream(new
	 * File("C://Users//user1//Desktop//mahesh//Selenium Code.docx")));
	 * Mockito.when(MedicalClaimConstants.proofUpload(multipartFile,
	 * redirectAttributes)) .thenReturn("You successfully uploaded"); String
	 * actualValue = proofUploadServiceImpl.proofUpload(multipartFile,
	 * redirectAttributes); assertEquals("You successfully uploaded", actualValue);
	 * }
	 */

	@Test(expected = FileNotFoundException.class)
	public void testProofUploadNegative() throws FileNotFoundException, IOException {
		MultipartFile multipartFile = new MockMultipartFile("",
				new FileInputStream(new File("C://Users//user1//Desktop//mahesh//")));
		Mockito.when(MedicalClaimConstants.proofUpload(Mockito.any(), Mockito.any()))
				.thenReturn("You successfully uploaded");
		String actualValue = proofUploadServiceImpl.proofUpload(multipartFile, redirectAttributes);
		assertEquals("You successfully uploaded", actualValue);
	}
}
