package pl.tbs.exception;

public class NoTicketsLeftException extends Exception {
    public NoTicketsLeftException() {
        super("Unfortunately, tickets for this event sold out. Check out other shows!");
    }
}
