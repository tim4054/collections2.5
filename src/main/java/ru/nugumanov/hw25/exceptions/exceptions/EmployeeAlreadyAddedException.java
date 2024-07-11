package ru.nugumanov.hw25.exceptions.exceptions;

public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException(){
        super("В фирме уже есть такой сотрудник");
    }
}

