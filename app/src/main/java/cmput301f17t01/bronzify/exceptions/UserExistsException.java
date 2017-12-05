package cmput301f17t01.bronzify.exceptions;


/**
 * Created by kdehaan on 19/10/17.
 */

class UserExistsException extends UserException {
    public UserExistsException() {
        super("User already exists");
    }
}
