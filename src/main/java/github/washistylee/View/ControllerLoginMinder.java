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

public class ControllerLoginMinder extends Controller implements Initializable {
    @FXML
    private  TextField textFieldUser;
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
    private Minder takeValueslogIn() {
        Minder minderaux = new Minder();
        String email = textFieldUser.getText();
        String password = passwordField.getText();
        minderaux.setEmail(email);
        minderaux.setPassword(password);
        return minderaux;
    }

    @FXML
    private  Minder logWithMinder() throws IOException {
        MinderDAO mdao = new MinderDAO();
        Minder minder = new Minder();
        if (verifyCredentialsMinder(takeValueslogIn())) {
            minder = mdao.findByMail(takeValueslogIn().getEmail());
            Sesion.getInstancia().logIn(minder);
            App.currentController.changeScene(Scenes.MAINMENUMINDERLOGGED, null);
        } else {
            AppController.showAlertForLogin();
        }
        return minder;
    }


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
