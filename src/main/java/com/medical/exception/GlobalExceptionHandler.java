package com.medical.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.medical.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MedicalClaimException.class)
	public ResponseEntity<ResponseDto> lmsExceptionHandler(MedicalClaimException ex, WebRequest request) {

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ex.getMessage());
		responseDto.setStatusCode(601);
		return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);

	}
	
	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<ResponseDto> lmsExceptionHandler(HttpStatusCodeException ex, WebRequest request) {

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ex.getMessage());
		responseDto.setStatusCode(601);
		return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);

	}

}