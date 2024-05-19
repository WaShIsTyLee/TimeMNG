package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.TeacherDAO;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Sesion;
import github.washistylee.Model.Entitys.Teacher;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerAddChild extends Controller implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private TextField textFieldAge;
    @FXML
    private TextField textFieldDiseases;
    @FXML
    private TextField textFieldObservation;
    @FXML
    private TextField textFielClass;
    @FXML
    private TextField textFieldEmailProfesor;
    @FXML
    private Button buttonAddChildtoBD;


    /**
     * Takes input values from the UI fields to create a Child object.
     * @return The Child object created with the input values.
     */
    @FXML
    public Child takeValuesChild() {
        Child childaux;
        Minder minder = (Minder) Sesion.getInstancia().getUsuarioIniciado();
        TeacherDAO tdao = new TeacherDAO();
        String childName = textFieldName.getText();
        String childSurname = textFieldSurname.getText();
        if (!Child.isString(childSurname) || !Child.isString(childName)) {
            childName = "";
            childSurname = "";

        }
        int childAge;
        if (textFieldAge.getText().equals("")) {
            childAge = -1;
        } else {
            if (!Child.isNumber(textFieldAge.getText())) {
                AppController.showAlertForAddChild();
                childAge = -1;
            } else {
                childAge = Integer.valueOf(textFieldAge.getText());
            }
        }

        String stringDiseases = textFieldDiseases.getText();
        List<String> diseasesList = new ArrayList<>();
        String childObservation = textFieldObservation.getText();
        if (stringDiseases.isEmpty() || childObservation.isEmpty()) {
            stringDiseases = "Ninguna";
            childObservation = "Ninguna";
            diseasesList.add(stringDiseases);
        } else {
            diseasesList = List.of(stringDiseases.split((",\\s*")));
            childObservation = textFieldObservation.getText();
        }

        String childClass = textFielClass.getText();
        String childEmailTeacher = textFieldEmailProfesor.getText();
        childaux = new

                Child(childName, childSurname, childClass, childObservation, diseasesList, childAge, minder, tdao.findByMail(childEmailTeacher));
        System.out.println(childaux);
        return childaux;

    }
    /**
     * Adds the Child object to the database.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void addChildToBD() throws IOException {
        Child child = takeValuesChild();
        ChildDAO cdao = new ChildDAO();
        TeacherDAO tdao = new TeacherDAO();
        Teacher teacher = tdao.verifyTeacherEmailIfExist(child.getTeacher().getEmail());
        if (teacher != null && !child.getName().isEmpty()
                && !child.getSurname().isEmpty()
                && child.getAge() > 0
                && !child.getObservation().isEmpty()
                && !child.getClassroom().isEmpty()
                && !child.getTeacher().getEmail().isEmpty()
        ) {
            cdao.save(child);
        } else {
            AppController.showAlertForAddChild();
        }
    }


    @Override
    public void onOpen(Object input) throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /**
     * Navigates back to the main menu scene after adding a child to the database.
     * @param event The event triggered for navigating back to the main menu.
     * @throws IOException If an I/O error occurs.
     */

    public void backToMainMenu(Event event) throws IOException {
        addChildToBD();
        App.currentController.changeScene(Scenes.MAINMENU, null);
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
