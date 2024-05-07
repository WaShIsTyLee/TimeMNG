package github.washistylee.View;
import github.washistylee.App;
import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Sesion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegisterMinder extends Controller implements Initializable {

    @FXML
    TextField textFieldName;
    @FXML
    TextField textFieldSurname;
    @FXML
    TextField textFieldEmail;

    @FXML
    TextField textFieldHours;
    @FXML
    ImageView imageView;
    @FXML
    Button buttonRegisterMinder;
    @FXML
    PasswordField passwordField;


    @FXML
    private void goToLoginRegisterView() throws IOException {
        App.currentController.changeScene(Scenes.PANTALLALOGINREGISTER, null);
    }

    @FXML
    private Minder takeValueslogIn() {
        String name = textFieldName.getText();
        String surname = textFieldSurname.getText();
        String email = textFieldEmail.getText();
        String password = passwordField.getText();
        String hours = textFieldHours.getText();
        Minder minderaux = new Minder(name, surname, email, password, hours, null);
        return minderaux;
    }


    @FXML
    private Minder registerMinder() throws IOException {
        Minder minderaux = takeValueslogIn();
        MinderDAO mdao = new MinderDAO();
        if (minderaux.getEmail().equals(mdao.verifyCredentialDAO(minderaux.getEmail()).getEmail())
                || !minderaux.validatePassword(minderaux.getPassword())
                ||  minderaux.getPassword().isEmpty()
                || !minderaux.validateEmail(minderaux.getEmail())
        ) {
            AppController.showAlertForRegister();
        } else {
            mdao.save(minderaux);
            Sesion.getInstancia().logIn(minderaux);
            App.currentController.changeScene(Scenes.MAINMENUMINDERLOGGED, null);
        }
        return minderaux;
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
