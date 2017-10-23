package cmput301f17t01.bronzify;

import java.util.Calendar;
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

    public HabitEvent(HabitType type, Date date) {
        this.habitType = type;
        this.time = date;
        this.checkPriority();
    }

    private void checkPriority() {
        Date tomorrow = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(tomorrow);
        c.add(Calendar.DATE, 1);
        tomorrow = c.getTime();
        if (time.before(tomorrow) & time.after(new Date())) {
            priority = Boolean.TRUE;
        } else {
            priority = Boolean.FALSE;
        }
    }

    private void checkComplete() {
        Date now = new Date();
        if (time.before(now)) {
            occured = Boolean.TRUE;
        } else {
            occured = Boolean.FALSE;
        }
    }

    // Getters and Setters below

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
