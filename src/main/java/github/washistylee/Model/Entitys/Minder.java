package github.washistylee.Model.Entitys;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Minder extends Person{

    private String hours;
    private List<Child> myChilds;

    public Minder(String name, String surname, String email, String password, String hours, List<Child> myChilds) {
        super(name, surname, email, password);
        this.hours = hours;
        this.myChilds = myChilds;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public List<Child> getMyChilds() {
        return myChilds;
    }

    public void setMyChilds(List<Child> myChilds) {
        this.myChilds = myChilds;
    }

    public Minder(){

    }

    @Override
    public String toString() {
        return
                super.toString() + "Minder{" +
                "hours=" + hours +
                ", myChilds=" + myChilds +
                '}';
    }


    public boolean validatePassword(String contrasena) {
        boolean result = false;
        Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!.#_()%?&])[A-Za-z\\d@$!.#_()%?&]{8,}$");
        Matcher contrasenaMatcher = passwordPattern.matcher(contrasena);
        if (contrasenaMatcher.matches()) {
            result = true;
        }
        return result;
    }

    public boolean validateEmail(String mail) {
        boolean result = false;
        Pattern mailPattern = Pattern.compile("[A-Za-z0-9]+@+(gmail|outlook|hotmail)\\.(com|es)");
        Matcher mailMatcher = mailPattern.matcher(mail);
        if (mailMatcher.matches()) {
            result = true;
        }
        return result;
    }

}
