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

public class ControllerLoginTeacher extends Controller implements Initializable {
    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField passwordField;
    @FXML
    Button button;
    @FXML
    ImageView imageView;


    @FXML
    private void goToLoginRegisterView() throws IOException {
        App.currentController.changeScene(Scenes.PANTALLALOGINREGISTER, null);
    }

    @FXML
    private Teacher takeValueslogIn() {
        Teacher personaux = new Teacher();
        String email = textFieldUser.getText();
        String password = passwordField.getText();
        personaux.setEmail(email);
        personaux.setPassword(password);
        return personaux;
    }

    @FXML
    private Teacher logWithTeacher() throws IOException {
        TeacherDAO tdao = new TeacherDAO();
        Teacher teacher = new Teacher();
        if (verifyCredentialsTeacher(takeValueslogIn())) {
            teacher = tdao.findByMail(takeValueslogIn().getEmail());
            Sesion.getInstancia().logIn(teacher);
            App.currentController.changeScene(Scenes.MAINMENUTEACHERLOGGED, null);
        } else {
            AppController.showAlertForLogin();
        }
        return teacher;
    }


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
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
