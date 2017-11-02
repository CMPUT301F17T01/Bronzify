package cmput301f17t01.bronzify;

import android.media.Image;
import android.widget.Button;

/**
 * Created by jblazusi on 2017-11-01.
 */

public class MyProfileActivity {

    private String name;
    private String userID;
    private Image picture;
    private Button editProfile;
    private Button sideBar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    public void displayUserInfo(){
        return;
    }

    public void editProfile(){
        return;
    }

}
