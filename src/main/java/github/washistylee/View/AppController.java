package github.washistylee.View;

import github.washistylee.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public  class AppController extends Controller implements Initializable {


    @FXML
    private BorderPane borderPane;
    private Controller centerController;

    @FXML
    static Alert alertError = new Alert(Alert.AlertType.ERROR);
    @FXML
    static Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Shows an error alert for updating schedules with specific guidelines.
     * The alert provides information on the required format for updating schedule information, including:
     * - If updating the time, it should be in HH:MM format.
     * - If updating the month, it should be a month of the year in Spanish.
     * - If updating the day, it should be a day from 1 to 31.
     */

    @FXML
    public static void showAlertForUpdateSchedule() {
        alertError.setTitle("❌ Error al actualizar ❌");
        alertError.setHeaderText(null);
        alertError.getDialogPane().setPrefWidth(700);
        alertError.getDialogPane().setPrefHeight(160);
        alertError.setContentText("Por favor, revise la información proporcionada, recuerde que:\n\n" +
                "- Si actualiza la hora debe ser en formato HH:MM \n" +
                "- Si actualiza un Mes debe de ser un mes del año en español.\n" +
                "- Si actualiza el dia debe de ser un dia del 1 al 31 \n "
        );
        alertError.showAndWait();
    }

    /**
     * Shows an error alert for registration with specific guidelines.
     * The alert provides information on the required criteria for registration, including:
     * - Not leaving any field empty.
     * - The email address should be a valid Gmail, Outlook, or Hotmail address.
     * - The password should contain at least 8 characters and include at least one uppercase letter,
     *   one lowercase letter, one number, and one special character.
     */
    @FXML
    public static void showAlertForRegister() {
        alertError.setTitle("❌ Error al registrarse ❌");
        alertError.setHeaderText(null);
        alertError.getDialogPane().setPrefWidth(700);
        alertError.getDialogPane().setPrefHeight(160);
        alertError.setContentText("Por favor, revise la información proporcionada, recuerde que:\n\n" +
                "- No deje ningún campo vacío \n" +
                "- El correo electrónico debe ser una dirección válida de Gmail, Outlook o Hotmail.\n" +
                "- La contraseña debe contener al menos 8 caracteres y debe incluir al menos una letra mayúscula, \n " +
                "una letra minúscula, un número y un carácter especial.");
        alertError.showAndWait();
    }
    /**
     * Shows an error alert for registering hours with specific guidelines.
     * The alert warns against entering letters or symbols in the hours field.
     */
    @FXML
    public static void showAlertForRegisterHours() {
        alertError.setTitle("❌ Error al registrarse ❌");
        alertError.setHeaderText(null);
        alertError.getDialogPane().setPrefWidth(600);
        alertError.getDialogPane().setPrefHeight(150);
        alertError.setContentText("No introduzca letras ni símbolos en la casilla de horas.");
        alertError.showAndWait();
    }

    /**
     * Shows an error alert for login.
     * The alert indicates that the password or email does not match, prompting the user to try again.
     */
    @FXML
    public static void showAlertForLogin() {
        alertError.setTitle("❌ Error al registrarse ❌");
        alertError.setHeaderText(null);
        alertError.setContentText("La contraseña o el Email no coinciden, inténtelo de nuevo.");
        alertError.showAndWait();
    }
    /**
     * Shows an error alert for adding a child.
     * The alert reminds the user that only the fields for Diseases and Observation can be left blank,
     * and it notifies that the provided email may not correspond to any teacher.
     */
    @FXML
    public static void showAlertForAddChild() {
        alertError.setTitle("❌ Error al añadir un niño ❌");
        alertError.setHeaderText(null);
        alertError.setContentText(" \n" + "Recuerde que solo puede quedar en blanco los campos de Enfermedades y Observación" +
                "Puede que el email no corresponda a ningún profesor.");
        alertError.showAndWait();
    }
    /**
     * Shows an error alert for updating a child's information.
     * The alert provides guidelines for updating information, including:
     * - If updating the name or surname, it should be a text string without numbers.
     * - If updating the age, it should be a positive real number.
     * - If updating the day, it should be a day from 1 to 31.
     * - If updating the teacher's email, it should be a teacher registered in our app.
     */
    @FXML
    public static void showAlertForUpdateChild() {
        alertError.setTitle("❌ Error al actualizar ❌");
        alertError.setHeaderText(null);
        alertError.setContentText(("Por favor, revise la información proporcionada, recuerde que:\n\n" +
                "- Si actualiza el nombre o el apellido debe ser en una cadena de texto sin números \n" +
                "- Si actualiza la edad debe de ser un número real positivo.\n" +
                "- Si actualiza el dia debe de ser un dia del 1 al 31 \n " +
                "- Si actualiza el email del profesor debe de ser un profesor que este registrado en nuestra app \n "
        ));
        alertError.showAndWait();
    }
    /**
     * Shows a confirmation alert for a user action.
     * The alert prompts the user to confirm a deletion action, indicating that the action is irreversible.
     * @return  true if the user confirms the action, false otherwise.
     */
    @FXML
    public static boolean showAlertForUConfirmate() {
        alertInfo.setTitle("Confirmación");
        alertInfo.setHeaderText("¿Estás seguro que deseas borrar este elemento?");
        alertInfo.setContentText("Esta acción no se puede deshacer.");
        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alertInfo.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alertInfo.showAndWait();

        if (alertInfo.getResult() == buttonTypeYes) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Loads an FXML file corresponding to the specified scene.
     * @param scenes The enum value representing the scene.
     * @return A View object containing the loaded scene and its associated controller.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene = p;
        view.controller = c;
        return view;
    }

    @Override
    public void onOpen(Object input) throws IOException {
        changeScene(Scenes.PANTALLALOGINREGISTER, null);
    }

    /**
     * Opens a modal window with the specified scene, title, parent controller, and data.
     * @param scene  The scene to be displayed in the modal window.
     * @param title  The title of the modal window.
     * @param parent The parent controller.
     * @param data   The data to be passed to the modal window.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    public void openModal(Scenes scene, String title, Controller parent, Object data) throws IOException {
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
    /**
     * Changes the main scene of the application to the specified scene with the provided data.
     * @param scene The scene to be displayed.
     * @param data  The data to be passed to the new scene.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    public void changeScene(Scenes scene, Object data) throws IOException {
        View view = loadFXML(scene);
        borderPane.setCenter(view.scene);
        this.centerController = view.controller;
        this.centerController.onOpen(data);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
