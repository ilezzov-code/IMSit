package ru.ilezzov.iMSit.model;

public class Error {
    private final Exception e;
    private final boolean isException;

    public Error(Exception e) {
        this.e = e;
        isException = true;
    }

    public Error() {
        e = null;
        isException = false;
    }

    public Exception getExeption() {
        return e;
    }

    public boolean isException() {
        return isException;
    }
}
