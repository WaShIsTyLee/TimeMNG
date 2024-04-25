package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.DAO.Sesion;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerRegisterMinder extends Controller implements Initializable {

    @FXML
    TextField textFieldName = new TextField();
    @FXML
    TextField textFieldSurname = new TextField();
    @FXML
    TextField textFieldEmail = new TextField();
    @FXML
    TextField textFieldPassword = new TextField();
    @FXML
    TextField textFieldHours = new TextField();
    @FXML
    ImageView imageView;
    @FXML
    Button buttonRegisterMinder=new Button();
    @FXML
    PasswordField passwordField=new PasswordField();

    @FXML
    private void goToLoginRegisterView() throws IOException {
        System.out.println(Scenes.PANTALLALOGINREGISTER);
        App.currentController.changeScene(Scenes.PANTALLALOGINREGISTER,null);
    }

    @FXML
    private Minder takeValueslogIn() {
        String name = textFieldName.getText();
        String surname = textFieldSurname.getText();
        String email = textFieldEmail.getText();
        String password = passwordField.getText();
        String hours = textFieldHours.getText();
        Minder minderaux = new Minder(name,surname,email,password,hours,null);
        return minderaux;
    }



    @FXML
    private Minder registerMinder() throws IOException {
        Minder minderaux = takeValueslogIn();
        MinderDAO mdao = new MinderDAO();
        System.out.println(minderaux.validateEmail(minderaux.getEmail()));
        System.out.println(minderaux.validatePassword(minderaux.getPassword()));
        if (minderaux.getEmail().equals(mdao.verifyCredentialDAO(minderaux.getEmail()).getEmail())
                || !minderaux.validatePassword(minderaux.getPassword())
                || minderaux.getPassword().isEmpty()
                || !minderaux.validateEmail(minderaux.getEmail())
        ){
            System.out.println("Error");
        }else {
            mdao.save(minderaux);
            Sesion.getInstancia().logIn(minderaux);
            App.currentController.changeScene(Scenes.MAINMENU, null);
        }



        return minderaux;}





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
