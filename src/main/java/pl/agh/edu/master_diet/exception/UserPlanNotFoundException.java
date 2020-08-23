package pl.agh.edu.master_diet.exception;

public class UserPlanNotFoundException extends RuntimeException {

    public UserPlanNotFoundException(String message) {
        super(message);
    }
}
