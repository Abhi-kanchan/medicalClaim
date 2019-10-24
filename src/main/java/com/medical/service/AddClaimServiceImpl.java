package com.medical.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.dto.ClaimDTO;
import com.medical.dto.ResponseDto;
import com.medical.entity.Claim;
import com.medical.entity.Disease;
import com.medical.entity.User;
import com.medical.exception.MedicalClaimException;
import com.medical.repository.ClaimRepository;
import com.medical.repository.DiseaseRepository;
import com.medical.repository.UserRepository;
import com.medical.util.MedicalClaimConstants;

/**
 * @author Shiva
 * @version 1.0 This class adds the claims
 *
 */
@Service
public class AddClaimServiceImpl implements AddClaimService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddClaimServiceImpl.class);

	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	DiseaseRepository diseaseRepository;

	@Autowired
	UserRepository userRepository;

	Random random = new Random();

	/**
	 * This method validates the claim details if they are valid then the claim will
	 * be successfully added to DB.
	 * 
	 * @param ClaimDTO
	 * @return ResponseDto
	 * @exception MedicalClaimException
	 */
	@Override

	public ResponseDto addClaim(ClaimDTO claimDTO) throws MedicalClaimException {

		LOGGER.info("Add claim Service impl");

		Integer claimNumber = random.nextInt(2524);
		Claim claim = new Claim();

		BeanUtils.copyProperties(claimDTO, claim);
		claim.setClaimNo(claimNumber);
		ResponseDto responseDTO = new ResponseDto();

		Integer diseaseId = claim.getDiseaseId();

		LOGGER.info("Disease ID:{}", diseaseId);

		Optional<User> optUser = userRepository.findById(claim.getUserId());

		if (optUser.isPresent()) {
			Optional<Disease> optDisease = diseaseRepository.findById(diseaseId);
			if (optDisease.isPresent()) {
				Disease disease = optDisease.get();
				LOGGER.info("user ID:{} Admission Date :{} Discharge Date:{} Claim Amount:{} Disease Amount:{}",
						claim.getUserId(), claim.getAdmissionDate(), claim.getDischargedDate(), claim.getClaimAmount(),
						disease.getLimitAmount());
				if ((claim.getAdmissionDate().isBefore(claim.getDischargedDate()))
						|| (claim.getDischargedDate().isBefore(claim.getAdmissionDate()))
						|| (claim.getAdmissionDate().isAfter(LocalDate.now()))
						|| (claim.getDischargedDate().isAfter(LocalDate.now()))) {

					if (claim.getClaimAmount() <= disease.getLimitAmount()) {

						claim.setApprStatus(MedicalClaimConstants.CLAIM_REQUEST_STATUS);
						claim.setClaimReqDate(LocalDate.now());
						claimRepository.save(claim);
						responseDTO.setMessage(MedicalClaimConstants.CLAIM_REQUEST_PASS);
						responseDTO.setStatusCode(MedicalClaimConstants.CLAIM_REQUEST_SUCCESS_CODE);

					} else {
						throw new MedicalClaimException(MedicalClaimConstants.INVALID_CLAIM_AMOUNT);
					}
				}

				else {
					throw new MedicalClaimException(MedicalClaimConstants.INVALID_DATE);
				}

			} else {
				throw new MedicalClaimException(MedicalClaimConstants.NO_DISEASE_FOUND);
			}
		}

		else {
			throw new MedicalClaimException(MedicalClaimConstants.NO_USER);
		}

		return responseDTO;
	}

}
