package com.medical.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.medical.dto.DiseaseResponseDto;
import com.medical.entity.Disease;
import com.medical.exception.DiseaseNotFoundException;
import com.medical.exception.HospitalNotFoundException;
import com.medical.repository.DiseaseRepository;

/**
 * Unit test case for DiseaseServiceImpl service
 * @author Abhishek C
 */

@RunWith(MockitoJUnitRunner.class)
public class DiseaseServiceImplTest {

	@InjectMocks
	DiseaseServiceImpl diseaseServiceImpl;

	@Mock
	DiseaseRepository diseaseRepository;

	Disease disease;
	DiseaseResponseDto diseaseResponseDto;
	List<Disease> list;
	List<DiseaseResponseDto> diseases;

	/**
	 * Initial set up
	 */
	@Before
	public void setup() {
		disease = new Disease();
		diseaseResponseDto = new DiseaseResponseDto();
		list = new ArrayList<Disease>();
		diseases = new ArrayList<DiseaseResponseDto>();
		disease.setDiseaseId(1);
		disease.setDiseaseName("Heart Stroke");
		disease.setLimitAmount(10000.00);
		BeanUtils.copyProperties(disease, diseaseResponseDto);
		list.add(disease);
		diseases.add(diseaseResponseDto);
	}

	/**
	 * test case for testGetDiseasesList() method
	 * @param no parameters
	 * @return nothing
	 * @return list of diseases
	 */
	@Test
	public void testGetDiseasesList() {
		Mockito.when(diseaseRepository.findAll()).thenReturn(list);
		diseases = diseaseServiceImpl.getDiseaseList();
		assertNotNull(list);
	}

	/**
	 * negative test case for GetHospitalList() method
	 * @param no parameters
	 * @return nothing
	 * @throws HospitalNotFoundException
	 */
	@Test(expected = DiseaseNotFoundException.class)
	public void negativeTestGetDiseasesList() {
		diseases = diseaseServiceImpl.getDiseaseList();
	}
}
