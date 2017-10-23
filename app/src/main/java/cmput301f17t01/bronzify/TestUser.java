package cmput301f17t01.bronzify;

/**
 * Allows for unit testing without server responses
 *
 * Created by kdehaan on 22/10/17.
 */
public class TestUser extends User {

    TestUser(String userID, String password) throws UserExistsException {
        super(userID, password);
    }

    @Override
    public void register() {}

    @Override
    public void unRegister() {}

    @Override
    public void update() {}
}
