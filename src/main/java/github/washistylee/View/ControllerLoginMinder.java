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
        System.out.println(Scenes.PANTALLALOGINREGISTER);
        App.currentController.changeScene(Scenes.PANTALLALOGINREGISTER, null);
    }

    @FXML
    private Minder takeValueslogIn() {
        Minder personaux = new Minder();
        String email = textFieldUser.getText();
        String password = passwordField.getText();
        personaux.setEmail(email);
        personaux.setPassword(password);
        return personaux;
    }

    @FXML
    private  Minder logWithMinder() throws IOException {
        MinderDAO mdao = new MinderDAO();
        Minder minder = new Minder();
        if (verifyCredentialsMinder(takeValueslogIn())) {
            minder = mdao.findByMail(takeValueslogIn().getEmail());
            Sesion.getInstancia().logIn(minder);
            App.currentController.changeScene(Scenes.MAINMENU, null);

        } else {

            AppController.showAlertForLogin();
        }
        return minder;
    }


    private static boolean verifyCredentialsMinder(Person person) {
        boolean aux = false;
        MinderDAO md = new MinderDAO();
        Minder minder = md.verifyCredentialDAO(person.getEmail());

        if (minder.getEmail() != null && minder.getEmail().equals(person.getEmail()) && minder.getPassword() != null && minder.getPassword().equals(person.getPassword())) {
            aux = true;
        }
        System.out.println(aux);
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
