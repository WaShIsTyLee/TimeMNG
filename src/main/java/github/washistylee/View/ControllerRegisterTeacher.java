package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.TeacherDAO;
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

public class ControllerRegisterTeacher extends Controller implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField textFieldSubject;
    @FXML
    private ImageView imageView;
    @FXML
    private Button buttonRegisterTeacher;

    /**
     * Navigates to the login/register view.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void goToLoginRegisterView() throws IOException {
        App.currentController.changeScene(Scenes.PANTALLALOGINREGISTER, null);
    }
    /**
     * Retrieves input values from UI fields to create a Teacher object for login.
     * @return The Teacher object created with the input values, or null if input validation fails.
     */
    @FXML
    private Teacher takeValueslogIn() {
        Teacher teacheraux = null;
        String name = textFieldName.getText();
        String surname = textFieldSurname.getText();
        String email = textFieldEmail.getText();
        String password = passwordField.getText();
        String subject = textFieldSubject.getText();
        if (Teacher.isString(subject)){
            teacheraux = new Teacher(name, surname, email, password, subject);
        }

        return teacheraux;
    }

    /**
     * Registers a Teacher account.
     * @throws IOException If an I/O error occurs.
     * @return The registered Teacher object, or null if registration fails.
     */

    @FXML
    private Teacher registerTeacher() throws IOException {
        Teacher teacheraux = takeValueslogIn();
        if (teacheraux == null){
            AppController.showAlertForRegister();
        }
        TeacherDAO tdao = new TeacherDAO();
        if (teacheraux.getEmail().equals(tdao.verifyCredentialDAO(teacheraux.getEmail()).getEmail())
                || !teacheraux.validatePassword(teacheraux.getPassword())
                || teacheraux.getPassword().isEmpty()
                || !teacheraux.validateEmail(teacheraux.getEmail())
        ){
            AppController.showAlertForRegister();
        } else {
            teacheraux.setPassword(Person.hashPassword(teacheraux.getPassword()));
            tdao.save(teacheraux);
            Sesion.getInstancia().logIn(teacheraux);
            System.out.println(teacheraux);
            App.currentController.changeScene(Scenes.MAINMENU, null);
        }


        return teacheraux;
    }




    @Override
    public void onOpen(Object input) throws IOException {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
