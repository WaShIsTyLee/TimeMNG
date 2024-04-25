package github.washistylee.View;

import github.washistylee.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLoginRegister extends Controller implements Initializable {
    @FXML
    Button buttonLoginMinder;
    @FXML
    Button buttonRegisterMinder;
    @FXML
    Button buttonLoginTeacher;
    @FXML
    Button buttonRegisterTeacher;



    @FXML
    private void goToLoginMinder() throws IOException {
        System.out.println(Scenes.MENULOGINMINDER);
        App.currentController.changeScene(Scenes.MENULOGINMINDER,null);
    }

    @FXML
    private void goToLoginTeacher() throws IOException {
        App.currentController.changeScene(Scenes.MENULOGINTEACHER,null);
    }
    @FXML
    private void goToRegisterMinder() throws IOException {
        System.out.println(Scenes.MENUREGISTERMINDER);
        App.currentController.changeScene(Scenes.MENUREGISTERMINDER,null);
    }
    @FXML
    private void goToRegisterTeacher() throws IOException {
        System.out.println(Scenes.MENUREGISTERTEACHER);
        App.currentController.changeScene(Scenes.MENUREGISTERTEACHER,null);
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
