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

    public static boolean isString(String text) {
        boolean aux = false;
        Pattern textP= Pattern.compile("^[a-zA-Z]+$");
        Matcher textM = textP.matcher(text);
        if (textM.matches()) {
            aux = true;
        }
        return aux;
    }
    @Override
    public String toString() {
        return super.toString() + "Teacher{" +
                "myChilds=" + myChilds +
                ", subject='" + subject + '\'' +
                '}';
    }

}

