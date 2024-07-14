package com.mangel.startcms.controller.error;

import com.mangel.startcms.logic.util.ApiError;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice(basePackages = {"com.mangel.startcms.controller.rest"})
public class GlobalRestErrorController {
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> getEmptyResultDataAccessException(EmptyResultDataAccessException ex, ServletWebRequest webRequest){
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setMethod(webRequest.getHttpMethod().name());
        apiError.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }


}
