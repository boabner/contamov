/**
 * @author abner
*/
package com.contamov.handler;

import com.contamov.error.ResourceNotFoundDetails;
import com.contamov.error.ResourceNotFoundException;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
  
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
    ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder
        .newBuilder()
        .timestamp(new Date().getTime())
        .status(HttpStatus.NOT_FOUND.value())
        .title("Resource Not Found.")
        .detail(rfnException.getMessage())
        .developerMessage(rfnException.getClass().getName())
        .build();
    return new ResponseEntity<>(rfnException, HttpStatus.NOT_FOUND);
  }
  
}
