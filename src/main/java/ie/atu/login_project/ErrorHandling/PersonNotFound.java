package ie.atu.login_project.ErrorHandling;

public class PersonNotFound extends RuntimeException {

    private String message;
    private String field;

    public PersonNotFound(String field, String message) {
        this.field = field;
    }

    public PersonNotFound(String message) {
        super(message);
    }
}

