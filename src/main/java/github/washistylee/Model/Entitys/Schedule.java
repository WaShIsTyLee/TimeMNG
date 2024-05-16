package github.washistylee.Model.Entitys;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        public static boolean isHour(String text){
            boolean aux = false;
            Pattern textP= Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
            Matcher textM = textP.matcher(text);
            if (textM.matches()) {
                aux = true;
            }
            return aux;
        }
    public static boolean isDay(String text) {
        boolean aux = false;
        Pattern textP = Pattern.compile("^(0?[1-9]|[1-9])$");
        Matcher textM = textP.matcher(text);
        if (textM.matches()) {
            aux = true;
        }
        return aux;
    }
    public static boolean isValidMonth(String text) {
        boolean aux = false;
        Pattern textP = Pattern.compile("^(ENERO|FEBRERO|MARZO|ABRIL|MAYO|JUNIO|JULIO|AGOSTO|SEPTIEMBRE|OCTUBRE|NOVIEMBRE|DICIEMBRE)$");
        Matcher textM = textP.matcher(text.toUpperCase());
        if (textM.matches()) {
            aux = true;
        }
        return aux;
    }

}


