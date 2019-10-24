package com.medical.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.dto.DiseaseResponseDto;
import com.medical.service.DiseaseService;

/**
 * <<<<<<< HEAD DiseaseControllerTest is used to test DiseaseController.class
 * 
 * @author Abhishek C
 *
 *         =======
 * @author Abhishek C >>>>>>> 770488ca8e65d327fc89718fa4754180cafc0c4f
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = DiseaseController.class)
public class DiseaseControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	DiseaseService diseaseService;

	DiseaseResponseDto responseDto;
	List<DiseaseResponseDto> list;

	/**
	 * Initial set up
	 */
	@Before
	public void setup() {
		responseDto = new DiseaseResponseDto();
		list = new ArrayList<DiseaseResponseDto>();
		responseDto.setDiseaseId(1);
		;
		responseDto.setDiseaseName("Heart Stroke");
		responseDto.setLimitAmount(10000.00);
		list.add(responseDto);
	}

	/**
	 * testGetDiseasesList is used to test getDiseaseList() method in
	 * DiseaseController.class
	 * @Param no parameters
	 * @return nothing
	 * @throws JsonProcessingException
	 */
	@Test
	public void testGetDiseasesList() throws JsonProcessingException, Exception {
		Mockito.when(diseaseService.getDiseaseList()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/diseases/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(list))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Method that can be used to serialize any Java value as a String
	 * @Param object parameter
	 * @return String
	 * @Throws JsonProcessingException
	 */
	public static String asJsonString(final Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);

	}
}
