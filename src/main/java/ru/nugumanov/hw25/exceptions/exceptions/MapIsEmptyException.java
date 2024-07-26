package ru.nugumanov.hw25.exceptions.exceptions;

public class MapIsEmptyException extends RuntimeException {
    public MapIsEmptyException() {
        super("В фирме еще никого нет");
    }
}
