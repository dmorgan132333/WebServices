package net.grocerypricebook.model.exceptions;

public class InvalidUserEdit extends Exception{
    public InvalidUserEdit() { super(); }
    public InvalidUserEdit(String message) { super(message); }
    public InvalidUserEdit(String message, Throwable cause) { super(message, cause); }
    public InvalidUserEdit(Throwable cause) { super(cause); }
}
