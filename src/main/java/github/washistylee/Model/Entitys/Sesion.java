package github.washistylee.Model.Entitys;


public class Sesion {

    private static Sesion _instance;
    private static Person userLoged;

    private Sesion() {
    }

    public static Sesion getInstancia() {
        if (_instance == null) {
            _instance = new Sesion();
            _instance.logIn(userLoged);
        }
        return _instance;
    }

    public void logIn(Person user) {
        userLoged = user;
    }

    public Person getUsuarioIniciado() {
        return userLoged;
    }

    public void logOut() {
        userLoged = null;
    }
}
