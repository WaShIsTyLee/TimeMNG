package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.Sesion;
import github.washistylee.Model.DAO.TeacherDAO;
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
    TextField textFieldName;
    @FXML
    TextField textFieldSurname;
    @FXML
    TextField textFieldEmail;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField textFieldSubject;
    @FXML
    ImageView imageView;
    @FXML
    Button buttonRegisterTeacher;

    @FXML
    private void goToLoginRegisterView() throws IOException {
        System.out.println(Scenes.PANTALLALOGINREGISTER);
        App.currentController.changeScene(Scenes.PANTALLALOGINREGISTER, null);
    }

    @FXML
    private Teacher takeValueslogIn() {
        String name = textFieldName.getText();
        String surname = textFieldSurname.getText();
        String email = textFieldEmail.getText();
        String password = passwordField.getText();
        String subject = textFieldSubject.getText();
        Teacher teacheraux = new Teacher(name, surname, email, password, subject);
        return teacheraux;
    }


    @FXML
    private Teacher registerTeacher() throws IOException {
        Teacher teacheraux = takeValueslogIn();
        TeacherDAO tdao = new TeacherDAO();
        System.out.println(teacheraux.validateEmail(teacheraux.getEmail()));
        System.out.println(teacheraux.validatePassword(teacheraux.getPassword()));
        if (teacheraux.getEmail().equals(tdao.verifyCredentialDAO(teacheraux.getEmail()).getEmail())
                || !teacheraux.validatePassword(teacheraux.getPassword())
                || teacheraux.getPassword().isEmpty()
                || !teacheraux.validateEmail(teacheraux.getEmail())
        ){
            System.out.println("Error");
        } else {
            tdao.save(teacheraux);
            Sesion.getInstancia().logIn(teacheraux);
            App.currentController.changeScene(Scenes.MAINMENU, null);
        }


        return teacheraux;
    }




    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
