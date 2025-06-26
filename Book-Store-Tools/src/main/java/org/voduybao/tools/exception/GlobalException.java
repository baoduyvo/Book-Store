package org.voduybao.tools.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.voduybao.tools.exception.error.ResponseErrors;
import org.voduybao.tools.exception.error.ResponseException;
import org.voduybao.tools.response.http.Result;

@ControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(value = ResponseException.class)
    public Result handleResponseException(ResponseException ex) {
        if (ex.isCustom() == true) {
            return Result.failure(ex.getMessage(), ex.getCode(), HttpStatus.valueOf(ex.getStatus().value()));
        }
        ResponseErrors errors = ex.getErrors();
        return Result.failure(errors);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result handleValidationException(ConstraintViolationException ex) {
        return Result.failure(ResponseErrors.NOT_SUPPORT_AUTH_TYPE);
    }

    @ExceptionHandler(value = Exception.class)
    public Result handleGenericException(Exception e) {
        return Result.failure(ResponseErrors.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        String mainMessage = bindingResult.hasErrors()
                ? bindingResult.getFieldError().getDefaultMessage()
                : "Validation error";

        Result response = Result.failure(
                mainMessage,
                ResponseErrors.BAD_REQUEST.getCode(),
                ResponseErrors.BAD_REQUEST.getStatus()
        );

        return response;
    }
}
