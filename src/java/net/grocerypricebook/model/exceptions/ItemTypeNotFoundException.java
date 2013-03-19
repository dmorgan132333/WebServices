package net.grocerypricebook.model.exceptions;

public class ItemTypeNotFoundException extends Exception{
    public ItemTypeNotFoundException() { super(); }
    public ItemTypeNotFoundException(String message) { super(message); }
    public ItemTypeNotFoundException(String message, Throwable cause) { super(message, cause); }
    public ItemTypeNotFoundException(Throwable cause) { super(cause); }
}
