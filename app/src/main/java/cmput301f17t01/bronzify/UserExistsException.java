package cmput301f17t01.bronzify;


/**
 * Created by kdehaan on 19/10/17.
 */

public class UserExistsException extends Exception {
    public UserExistsException() {
        super("User already exists");
    }
}
