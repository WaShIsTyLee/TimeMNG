package github.washistylee.Model.Entitys;


import github.washistylee.App;
import github.washistylee.View.Controller;
import github.washistylee.View.Scenes;

import java.io.IOException;

public class Sesion  {

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

    public static Person getUsuarioIniciado() {
        return userLoged;
    }
}
