package ru.nugumanov.hw25.exceptions.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException() {
        super("Превышен лимит количества сотрудников в фирме");
    }
}

