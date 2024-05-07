package github.washistylee.View;

import github.washistylee.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static github.washistylee.App.scene;

public class AppController extends Controller implements Initializable {


    @FXML
    BorderPane borderPane;
    private Controller centerController;

    @FXML
    static Alert alertRegister=new Alert(Alert.AlertType.ERROR);

    @FXML
    public static void showAlertForRegister() {
        alertRegister.setTitle("Error al registrarse");
        alertRegister.setHeaderText(null);
        alertRegister.getDialogPane().setPrefWidth(600);
        alertRegister.getDialogPane().setPrefHeight(150);
        alertRegister.setContentText("Por favor, revise la información proporcionada, recuerde que:\n\n" +
                "- El correo electrónico debe ser una dirección válida de Gmail, Outlook o Hotmail.\n" +
                "- La contraseña debe contener al menos 8 caracteres y debe incluir al menos una letra mayúscula, " +
                "una letra minúscula, un número y un carácter especial.");
        alertRegister.showAndWait();
    }
    @FXML
    public static void showAlertForLogin() {
        alertRegister.setTitle("Error al registrarse");
        alertRegister.setHeaderText(null);
        alertRegister.setContentText("La contraseña o el Email no coinciden, inténtelo de nuevo.");
        alertRegister.showAndWait();
    }
    @FXML
    public static void showAlertForAddChild() {
        alertRegister.setTitle("Error al registrarse");
        alertRegister.setHeaderText(null);
        alertRegister.setContentText("Ningún campo puede estar vacío. \n" +
                "Puede que el email no corresponda a ningún usuario.");
        alertRegister.showAndWait();
    }
    @FXML
    public static void showAlertForUpdateChild() {
        alertRegister.setTitle("Error al actualizar");
        alertRegister.setHeaderText(null);
        alertRegister.setContentText("El email del profesor no corresponde con ninguno. \n" +
                "No olvides proporcionar la ID del niño que deseas modificar");
        alertRegister.showAndWait();
    }


    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene=p;
        view.controller=c;
        return view;
    }
    @Override
    public void onOpen(Object input) throws IOException {
        changeScene(Scenes.PANTALLALOGINREGISTER,null);
    }
    public  void openModal(Scenes scene, String title,Controller parent, Object data) throws IOException {
        View view = loadFXML(scene);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.stage);
        Scene _scene = new Scene(view.scene);
        stage.setScene(_scene);
        view.controller.onOpen(parent);
        stage.showAndWait();
    }

    public void changeScene(Scenes scene,Object data) throws IOException {
        View view = loadFXML(scene);
        borderPane.setCenter(view.scene);
        this.centerController = view.controller;
        this.centerController.onOpen(data);
    }


    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
