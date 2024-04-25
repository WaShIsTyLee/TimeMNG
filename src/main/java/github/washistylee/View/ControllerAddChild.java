package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.TeacherDAO;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Sesion;
import github.washistylee.Model.Entitys.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerAddChild extends Controller implements Initializable {
    private Controller controller1;

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
    Button buton;
    @FXML
    Button buttonAddChildtoBD;


    @FXML
    public Child takeValuesChild() {
        Child childaux;
        Minder minder = (Minder) Sesion.getInstancia().getUsuarioIniciado();
        TeacherDAO tdao = new TeacherDAO();
        String childName = textFieldName.getText();
        String childSurname = textFieldSurname.getText();
        String childAgeString = textFieldAge.getText();
        int childAge = Integer.valueOf(childAgeString);
        String stringDiseases = textFieldDiseases.getText();
        List<String> diseasesList = List.of(stringDiseases.split((",\\s*")));
        String childObservation = textFieldObservation.getText();
        String childClass = textFielClass.getText();
        String childEmailTeacher = textFieldEmailProfesor.getText();
        childaux = new Child(childName, childSurname, childClass, childObservation, diseasesList, childAge, minder, tdao.findByMail(childEmailTeacher));
        System.out.println(childaux);
        return childaux;

    }

    @FXML
    public void addChildToBD() {
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
        this.controller1 = (Controller) input;

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
