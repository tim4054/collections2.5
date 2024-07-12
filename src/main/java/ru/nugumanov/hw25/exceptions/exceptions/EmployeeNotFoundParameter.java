package ru.nugumanov.hw25.exceptions.exceptions;

public class EmployeeNotFoundParameter extends RuntimeException {
    public EmployeeNotFoundParameter() {
        super("Один из параметров отсутствует");
    }
}
