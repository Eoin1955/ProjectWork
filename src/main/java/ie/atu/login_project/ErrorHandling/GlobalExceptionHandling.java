package ie.atu.login_project.ErrorHandling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDetails>> showErrorDetails(MethodArgumentNotValidException mae)
    {
        List<ExceptionDetails> errorList = new ArrayList<>();
        for(FieldError fieldError : mae.getBindingResult().getFieldErrors()){
            ExceptionDetails exceptionDetails = new ExceptionDetails();
            exceptionDetails.setFieldName(fieldError.getField());
            exceptionDetails.setFieldValue(fieldError.getDefaultMessage());
            errorList.add(exceptionDetails);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ExceptionDetails> showErrorDetails(DuplicateException de)
    {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setFieldName("personid");
        exceptionDetails.setFieldValue(de.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDetails);
    }

    @ExceptionHandler(PersonNotFound.class)
    public ResponseEntity<ExceptionDetails> showErrorDetails(PersonNotFound pnf){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setFieldName("passengerId");
        exceptionDetails.setFieldValue(pnf.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDetails);
    }
}

