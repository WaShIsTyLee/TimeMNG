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

public class ControllerUpdateChildOnMinder extends Controller implements Initializable {
    @FXML
    TextField textFieldName;
    @FXML
    TextField textFieldSurname;
    @FXML
    TextField textFieldAge;
    @FXML
    TextField textFieldDiseases;
    @FXML
    TextField textFieldObservation;
    @FXML
    TextField textFielClass;
    @FXML
    TextField textFieldEmailProfesor;
    @FXML
    TextField textFieldIDChild;
    @FXML
    Button buton;
    @FXML
    Button buttonUpdateChildtoBD;

    @FXML
    public Child takeValuesChild() {
        Child childaux = new Child();
        Teacher teacher = new Teacher();
        Minder minder = (Minder) Sesion.getInstancia().getUsuarioIniciado();
        ChildDAO cdao = new ChildDAO();
        TeacherDAO tdao = new TeacherDAO();
        int childID = (textFieldIDChild.getText().equals("")) ? 0 : Integer.valueOf(textFieldIDChild.getText());
        String childName = textFieldName.getText();
        String childSurname = textFieldSurname.getText();
        int childAge = (textFieldAge.getText().equals("")) ? 0 : Integer.valueOf(textFieldAge.getText());
        String stringDiseases = textFieldDiseases.getText();
        List<String> diseasesList = new ArrayList<>();
        if (stringDiseases.isEmpty()) {
            stringDiseases = "";
            diseasesList.add(stringDiseases);
        } else {
            diseasesList = List.of(stringDiseases.split((",\\s*")));
        }
        String childObservation = textFieldObservation.getText();
        String childClass = textFielClass.getText();
        String childEmailTeacher = textFieldEmailProfesor.getText();
        if (childEmailTeacher.isEmpty()){
                Child teacherOfChild = cdao.findById(childID);
                childEmailTeacher = teacherOfChild.getTeacher().getEmail();
                teacher = tdao.findByMail(childEmailTeacher);
        }else {
            teacher = tdao.findByMail(childEmailTeacher);
        }
        childaux = new Child(childName, childSurname, childClass, childObservation, diseasesList, childAge, childID, minder, teacher);
        System.out.println(childaux);
        return childaux;

    }

    @FXML
    public void updateChildToBD() {
        Child child = takeValuesChild();
        ChildDAO cdao = new ChildDAO();
        TeacherDAO tdao = new TeacherDAO();
        if (tdao.verifyTeacherEmailIfExist(child.getTeacher().getEmail()) != null && cdao.findById(child.getId()) != null){
            cdao.update(child);
        }else {
            AppController.showAlertForUpdateChild();        }

    }

    public void backToMainMenu(Event event) throws IOException {
        System.out.println(Scenes.MAINMENUMINDERLOGGED);
        updateChildToBD();
        App.currentController.changeScene(Scenes.MAINMENUMINDERLOGGED, null);
        ((Node) (event.getSource())).getScene().getWindow().hide();
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
