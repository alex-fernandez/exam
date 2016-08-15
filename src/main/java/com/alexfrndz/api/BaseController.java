package com.alexfrndz.api;

import com.alexfrndz.pojo.exceptions.DataException;
import com.alexfrndz.pojo.exceptions.Error;
import com.alexfrndz.pojo.exceptions.ErrorResponse;
import com.alexfrndz.pojo.exceptions.NotFoundException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by alexfernandezwhiteskylabs on 15/08/2016.
 */
@Slf4j
public class BaseController {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Exception> handleIllegalArgumentException(
            IllegalArgumentException nre) {
        log.error("> handleIllegalArgumentException");
        log.error("- IllegalArgumentException: ", nre);
        log.error("< handleIllegalArgumentException");
        return new ResponseEntity<Exception>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleException(Exception e) {
        log.error("> handleException");
        log.error("- Exception: ", e);
        log.error("< handleException");
        return new ResponseEntity<Exception>(e,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundError(HttpServletRequest req, NotFoundException exception) {
        List<Error> errors = Lists.newArrayList();
        errors.add(new Error(String.valueOf(exception.getCode()), exception.getMsg()));
        return new ErrorResponse(errors);
    }

    @ExceptionHandler(DataException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotFoundError(HttpServletRequest req, DataException exception) {
        List<Error> errors = Lists.newArrayList();
        errors.add(new Error(String.valueOf(exception.getCode()), exception.getMsg()));
        return new ErrorResponse(errors);
    }
}
