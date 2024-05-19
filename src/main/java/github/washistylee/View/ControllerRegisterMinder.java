package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Person;
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
    private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldHours;
    @FXML
    private ImageView imageView;
    @FXML
    private Button buttonRegisterMinder;
    @FXML
    private PasswordField passwordField;


    @FXML
    private void goToLoginRegisterView() throws IOException {
        App.currentController.changeScene(Scenes.PANTALLALOGINREGISTER, null);
    }
    /**
     * Retrieves input values from UI fields to create a Minder object for login.
     * @return The Minder object created with the input values, or null if input validation fails.
     */
    @FXML
    private Minder takeValueslogIn() {
        Minder minderaux = null;
        String name = textFieldName.getText();
        String surname = textFieldSurname.getText();
        String email = textFieldEmail.getText();
        String password = passwordField.getText();
        String hours = textFieldHours.getText();
        if (Minder.isNumber(hours)) {
            Float hour = Float.valueOf(hours);
            minderaux = new Minder(name, surname, email, password, hour, null);
        } else {
            AppController.showAlertForRegisterHours();
        }
        return minderaux;
    }

    /**
     * Registers a Minder account.
     * @throws IOException If an I/O error occurs.
     * @return The registered Minder object, or null if registration fails.
     */
    @FXML
    private Minder registerMinder() throws IOException {
        Minder minderaux = takeValueslogIn();
        if (minderaux == null) {
            AppController.showAlertForRegister();
        }
        if (minderaux != null) {
            MinderDAO mdao = new MinderDAO();
            if (minderaux.getEmail().equals(mdao.verifyCredentialDAO(minderaux.getEmail()).getEmail())
                    || !minderaux.validatePassword(minderaux.getPassword())
                    || minderaux.getPassword().isEmpty()
                    || !minderaux.validateEmail(minderaux.getEmail())
            ) {
                AppController.showAlertForRegister();
            } else {
                minderaux.setPassword(Person.hashPassword(minderaux.getPassword()));
                mdao.save(minderaux);
                Sesion.getInstancia().logIn(minderaux);
                App.currentController.changeScene(Scenes.MAINMENU, null);

            }
        }
        return minderaux;
    }


    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
