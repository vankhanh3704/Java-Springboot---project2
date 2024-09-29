package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import custumeExeption.FieldRequiredException;
import model.ErrorResponseDTO;


@ControllerAdvice // khi  gặp lỗi trong debug nó sẽ tự động nhảy vô đây để xử lý exception
//  kế thừa ResponseEntityExceptionHandler để gặp lỗi sử lý hàm bên 
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	// @ExceptionHandler mô tả với cái lỗi nào(ví dụ: lỗi toán học) thì trả về cái responsebody vs http method
	@ExceptionHandler(ArithmeticException.class)
	// sau đấy nhảy vô hàm để hiện ra những lỗi đúng 
    public ResponseEntity<Object> handleArithmeticException(
            ArithmeticException ex, WebRequest request) {
		//ArithmeticException ex: để lấy cái message ra (response body)
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> detail = new ArrayList<>();
		detail.add("số làm sao mà chia được cho 0 !");
		errorResponseDTO.setDetail(detail);

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	@ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> handleFieldRequireException(
            FieldRequiredException ex, WebRequest request) {
		//ArithmeticException ex: để lấy cái message ra (response body)
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> detail = new ArrayList<>();
		detail.add("kiểm tra lại name hoặc numberofbasement đi bởi vì đang bị null(trống) ");
		errorResponseDTO.setDetail(detail);

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_GATEWAY);
    }
}
