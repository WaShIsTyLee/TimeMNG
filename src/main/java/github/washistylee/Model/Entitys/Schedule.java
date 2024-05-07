package github.washistylee.Model.Entitys;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Schedule {
    private Month month;
    private String day;
    private String hour;
    private List <String> activitys;
    private Child child;
    private int ID;

    public Schedule(Month month, String day, String hour, List<String> activitys, Child child ) {
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.activitys = activitys;
        this.child = child;
    }

    public Schedule(Month month, String day, String hour, List<String> activitys, Child child, int ID) {
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public List<String> getActivitys() {
        return activitys;
    }

    public void setActivitys(List<String> activitys) {
        this.activitys = activitys;
    }

    public String getActivitiesAsString() {
            String activiesAsString = "";
            for (int i = 0; i < activitys.size(); i++) {
                activiesAsString += activitys.get(i);
                if (i < activitys.size() - 1) {
                    activiesAsString += ", ";
                }
            }

            return activiesAsString;
        }

}
