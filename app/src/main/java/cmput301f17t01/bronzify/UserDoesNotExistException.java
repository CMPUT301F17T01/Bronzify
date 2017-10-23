package cmput301f17t01.bronzify;


/**
 * Created by kdehaan on 19/10/17.
 */

public class UserDoesNotExistException extends UserException {
    public UserDoesNotExistException() {
        super("User does not exist");
    }
}