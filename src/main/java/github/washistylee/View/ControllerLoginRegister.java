package github.washistylee.View;

import github.washistylee.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLoginRegister extends Controller implements Initializable {
    @FXML
    private Button buttonLoginMinder;
    @FXML
    private Button buttonRegisterMinder;
    @FXML
    private Button buttonRegisterTeacher;


    /**
     * Navigates to the login page for a Minder account.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void goToLoginMinder() throws IOException {
        System.out.println(Scenes.MENULOGIN);
        App.currentController.changeScene(Scenes.MENULOGIN,null);
    }
    /**
     * Navigates to the registration page for a Minder account.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void goToRegisterMinder() throws IOException {
        System.out.println(Scenes.MENUREGISTERMINDER);
        App.currentController.changeScene(Scenes.MENUREGISTERMINDER,null);
    }
    /**
     * Navigates to the registration page for a Teacher account.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    private void goToRegisterTeacher() throws IOException {
        System.out.println(Scenes.MENUREGISTERTEACHER);
        App.currentController.changeScene(Scenes.MENUREGISTERTEACHER,null);
    }


    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
