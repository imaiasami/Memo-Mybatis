package org.example.exception;

public class DuplicateMemberEmailException extends Exception {
    public DuplicateMemberEmailException(String message) {
        super(message);
    }
}
