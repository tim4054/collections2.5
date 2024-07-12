package ru.nugumanov.hw25.exceptions.exceptions;

public class PersonsIsEmptyExceptoin extends RuntimeException {
    public PersonsIsEmptyExceptoin() {
        super("В фирме еще никого нет");
    }
}
