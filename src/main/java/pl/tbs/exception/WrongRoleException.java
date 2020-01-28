package pl.tbs.exception;

public class WrongRoleException extends Exception {
    public WrongRoleException() {
        super("Available roles: user, admin");
    }
}
