package cmput301f17t01.bronzify;

import java.util.Date;

/**
 * Created by noahkryzanowski on 2017-10-20.
 */

public class HabitEvent {
    private String name;
    private HabitType habitType;
    private User user;
    private Date time;
    private String comment;
    //private ImageFormat image;
    //private Place location;
    private Boolean priority;
    private Boolean occured;
    private Boolean completed;

    public User getUser() {
        return user;
    }

    public Date getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
