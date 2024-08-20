package ru.nugumanov.hw25.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeBadRequest extends RuntimeException {
    public EmployeeBadRequest() {
    }
}
