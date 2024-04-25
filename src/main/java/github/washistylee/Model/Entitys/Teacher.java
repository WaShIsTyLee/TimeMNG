package github.washistylee.Model.Entitys;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Teacher extends Person {
    private List<Child> myChilds;
    private String subject;

    public Teacher(String name, String surname, String email, String password, List<Child> myChilds, String subject) {
        super(name, surname, email, password);
        this.myChilds = myChilds;
        this.subject = subject;
    }
    public Teacher(String name, String surname, String email, String password, String subject) {
        super(name, surname, email, password);
        this.subject = subject;
    }
    public Teacher() {
    }

    public List<Child> getMyChilds() {
        return myChilds;
    }

    public void setMyChilds(List<Child> myChilds) {
        this.myChilds = myChilds;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return super.toString() + "Teacher{" +
                "myChilds=" + myChilds +
                ", subject='" + subject + '\'' +
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

