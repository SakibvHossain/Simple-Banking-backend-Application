package com.example.bankingapplication.exception.global_exception;

import com.example.bankingapplication.exception.AccountHolderNotFound;
import com.example.bankingapplication.exception.HolderAlreadyExist;
import com.example.bankingapplication.exception.model.ExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /*
    ResponseEntityExceptionHandler: simplifies the process of handling exceptions
    in a Spring MVC application by providing a centralized mechanism for defining
    exception handling logic and generating customized responses.

    Simple words: We used ResponseEntityExceptionHandler on this project for handling
    validation
     */

    /*
    @ControllerAdvice: is like a guardian angel for your Spring MVC controllers.
    It's a special annotation that allows you to define global exception handling
    logic for all your controllers in one central place. It's like having a safety
    net that catches exceptions before they disrupt your application's flow, making
    your code more organized and resilient.
     */
    @ExceptionHandler(AccountHolderNotFound.class) //Set which class going to handle exception
    public ResponseEntity<ExceptionDetails> accountHolderNotFoundException(AccountHolderNotFound holderNotFound, WebRequest request){

        //The model responsible for holding customized value
        ExceptionDetails details = new ExceptionDetails(
                LocalDateTime.now(),
                holderNotFound.getMessage(),
                request.getDescription(false),
                "User Not Found"
        );

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HolderAlreadyExist.class)
    public ResponseEntity<ExceptionDetails> accountHolderExistException(HolderAlreadyExist alreadyExist, WebRequest request){

        //The model responsible for holding customized value
        ExceptionDetails details = new ExceptionDetails(
                LocalDateTime.now(),
                alreadyExist.getMessage(),
                request.getDescription(false),
                "User Already Exist"
        );

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    //If none of the class handling exception this one to the rescue
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> accountHolderExistException(Exception exception, WebRequest request){

        //The model responsible for holding customized value
        ExceptionDetails details = new ExceptionDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "Bad Request"
        );

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    // Handling customized validation error
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach(
                (error) -> {
                    String filename = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(filename,message);
                }
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
