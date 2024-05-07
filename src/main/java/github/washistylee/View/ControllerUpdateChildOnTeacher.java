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

public class ControllerUpdateChildOnTeacher extends Controller implements Initializable {

    @FXML
    TextField textFieldObservation;
    @FXML
    TextField textFieldIDChild;
    @FXML
    Button buton;
    @FXML
    Button buttonUpdateChildtoBD;

    @FXML
    public Child takeValuesChild() {
        Child childaux = new Child();
        Teacher teacher = (Teacher) Sesion.getInstancia().getUsuarioIniciado();
        String childObservation = textFieldObservation.getText();
        int childID = (textFieldIDChild.getText().equals("")) ? 0 : Integer.valueOf(textFieldIDChild.getText());
        childaux.setObservation(childObservation);
        childaux.setId(childID);
        childaux.setTeacher(teacher);
        return childaux;

    }

    @FXML
    public void updateChildToBD() {
        Child child = takeValuesChild();
        ChildDAO cdao = new ChildDAO();
        if (cdao.findById(child.getId()) != null){
            cdao.update(child);
        }else {
            AppController.showAlertForUpdateChild();        }

    }

    public void backToMainMenu(Event event) throws IOException {
        System.out.println(Scenes.MAINMENUTEACHERLOGGED);
        updateChildToBD();
        App.currentController.changeScene(Scenes.MAINMENUTEACHERLOGGED, null);
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
