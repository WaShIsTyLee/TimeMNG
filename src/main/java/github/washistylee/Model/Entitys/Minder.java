package github.washistylee.Model.Entitys;

import java.util.List;

public class Minder extends Person {

    private float hours;
    private List<Child> myChilds;
    private byte[] photo;

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Minder(String name, String surname, String email, String password, float hours, List<Child> myChilds) {
        super(name, surname, email, password);
        this.hours = hours;
        this.myChilds = myChilds;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public List<Child> getMyChilds() {
        return myChilds;
    }

    public void setMyChilds(List<Child> myChilds) {
        this.myChilds = myChilds;
    }

    public Minder() {

    }

    public static boolean isNumber(String text) {
        boolean aux = false;
        String regex = "\\d+(\\.\\d+)?";
        if (text.matches(regex)) {
            aux = true;
        }
        return aux;
    }

    @Override
    public String toString() {
        return
                super.toString() + "Minder{" +
                        "hours=" + hours +
                        ", myChilds=" + myChilds +
                        '}';
    }


}
