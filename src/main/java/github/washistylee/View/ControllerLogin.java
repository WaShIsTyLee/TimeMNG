package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.DAO.TeacherDAO;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Person;
import github.washistylee.Model.Entitys.Sesion;
import github.washistylee.Model.Entitys.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin extends Controller implements Initializable {
    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField passwordField;


    /**
     * Changes the scene to the login/register view.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void goToLoginRegisterView() throws IOException {
        App.currentController.changeScene(Scenes.PANTALLALOGINREGISTER, null);
    }

    /**
     * Retrieves input values from UI fields to create a Person object for login.
     * @return The Person object created with the input values.
     */
    @FXML
    private Person takeValueslogIn() {
        Person person = new Person();
        String email = textFieldUser.getText();
        String password = passwordField.getText();
        person.setEmail(email);
        person.setPassword(Person.hashPassword(password));
        return person;
    }

    /**
     * Logs in using a Minder account.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void logWithMinder() throws IOException {
        MinderDAO mdao = new MinderDAO();
        TeacherDAO tdao = new TeacherDAO();
        if (verifyCredentialsMinder(takeValueslogIn())) {
            Minder minder = mdao.findByMail(takeValueslogIn().getEmail());
            Sesion.getInstancia().logIn(minder);
            App.currentController.changeScene(Scenes.MAINMENU, null);
        } else if (verifyCredentialsTeacher(takeValueslogIn())) {
            Teacher teacher = tdao.findByMail(takeValueslogIn().getEmail());
            Sesion.getInstancia().logIn(teacher);
            App.currentController.changeScene(Scenes.MAINMENU, null);
        }else {
            AppController.showAlertForLogin();
        }
    }

    /**
     * Verifies the credentials for a Minder account.
     *
     * @param person The Person object containing the email and password to be verified.
     * @return True if the credentials are valid, false otherwise.
     */
    private static boolean verifyCredentialsMinder(Person person) {
        boolean aux = false;
        MinderDAO md = new MinderDAO();
        Minder minderVer = md.verifyCredentialDAO(person.getEmail());
        if (minderVer.getEmail() != null && minderVer.getEmail().equals(person.getEmail())
                && minderVer.getPassword() != null
                && minderVer.getPassword().equals(person.getPassword())) {
            aux = true;
        }
        return aux;
    }
    /**
     * Verifies the credentials for a Teacher account.
     *
     * @param person The Person object containing the email and password to be verified.
     * @return True if the credentials are valid, false otherwise.
     */
    private boolean verifyCredentialsTeacher(Person person) {
        boolean aux = false;
        TeacherDAO td = new TeacherDAO();
        Teacher teacher = td.verifyCredentialDAO(person.getEmail());
        System.out.println(teacher);
        if (teacher.getEmail() != null && teacher.getEmail().equals(person.getEmail())
                && teacher.getPassword() != null
                && teacher.getPassword().equals(person.getPassword())) {
            aux = true;
        }
        return aux;
    }

    @Override
    public void onOpen(Object input) throws IOException {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
