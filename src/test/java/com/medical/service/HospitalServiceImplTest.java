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

import com.medical.dto.HospitalResponseDto;
import com.medical.entity.Hospital;
import com.medical.exception.HospitalNotFoundException;
import com.medical.repository.HospitalRepository;

/**
 * @author Abhishek C
 * @apiNote test case for HospitalServiceImpl service
 */

@RunWith(MockitoJUnitRunner.class)
public class HospitalServiceImplTest {

	@InjectMocks
	HospitaServiceImpl hospitalServiceImpl;

	@Mock
	HospitalRepository hospitalRepository;

	Hospital hospital;
	HospitalResponseDto responseDto;
	List<Hospital> hospitals;
	List<HospitalResponseDto> list;

	/**
	 * Initial set up
	 */
	@Before
	public void setup() {
		hospital = new Hospital();
		hospital.setHospitalId(1);
		hospital.setHospitalName("AIIMS ( All India Institutes of Medical Sciences)");
		responseDto = new HospitalResponseDto();
		list = new ArrayList<HospitalResponseDto>();
		hospitals = new ArrayList<Hospital>();
		responseDto.setHospitalId(1);
		responseDto.setHospitalName("AIIMS ( All India Institutes of Medical Sciences)");
		list.add(responseDto);
		hospitals.add(hospital);
	}

	/**
	 * test case for GetHospitalList() method
	 * @param no parameters
	 * @return list of hospitals
	 */
	@Test
	public void testGetHospitalList() {
		Mockito.when(hospitalRepository.findAll()).thenReturn(hospitals);
		list = hospitalServiceImpl.getHospitalList();
		assertNotNull(list);
	}

	/**
	 * @apiNote negative test case for GetHospitalList() method
	 * @param no parameters
	 * @throws HospitalNotFoundException
	 */
	@Test(expected = HospitalNotFoundException.class)
	public void negativeTestGetHospitalList() {
		list = hospitalServiceImpl.getHospitalList();
	}
}
