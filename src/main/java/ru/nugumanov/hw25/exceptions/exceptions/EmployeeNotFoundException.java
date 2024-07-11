package ru.nugumanov.hw25.exceptions.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(){
        super("Нет такого сотрудника");
    }
}

