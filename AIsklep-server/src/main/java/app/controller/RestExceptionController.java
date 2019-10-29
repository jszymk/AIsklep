package app.controller;

import app.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
@RequestMapping("/api/error")
public class RestExceptionController extends Controller {
    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity validationError(ConstraintViolationException exc) {
        log.debug("validation failed", exc);
        return respond(HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity typeError(MethodArgumentTypeMismatchException exc) {
        log.debug("type conversion failed", exc);
        return respond(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(value = NOT_FOUND)
    @ResponseBody
    public ResponseEntity notFoundError(EmptyResultDataAccessException exc) {
        log.debug("resource not found", exc);
        return respond(NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = FORBIDDEN)
    @ResponseBody
    public ResponseEntity accessDeniedError(AccessDeniedException exc) {
        log.debug(exc.getMessage());
        return respond(FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity badCredentialsError(BadCredentialsException exc) {
        log.debug(exc.getMessage());
        return respond(UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public ResponseEntity badRequestError(BadRequestException exc) {
        log.debug(exc.getMessage());
        return respond(BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity genericError(Exception exc) {
        log.error("internal server error", exc);
        return respond(INTERNAL_SERVER_ERROR);
    }
}
