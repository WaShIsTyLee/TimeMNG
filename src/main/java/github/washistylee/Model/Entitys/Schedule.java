package github.washistylee.Model.Entitys;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Schedule {
    private Month month;
    private LocalDate day;
    private LocalTime hour;
    private List <String> activitys;
    private Child child;
    private int ID;

    public Schedule(Month month, LocalDate day, LocalTime hour, List<String> activitys, Child child, Integer ID) {
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.activitys = activitys;
        this.child = child;
        this.ID = ID;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public Schedule (){

    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public List<String> getActivitys() {
        return activitys;
    }

    public void setActivitys(List<String> activitys) {
        this.activitys = activitys;
    }

    public String getActivitiesAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < activitys.size(); i++) {
            sb.append(activitys.get(i));
            if (i < activitys.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
