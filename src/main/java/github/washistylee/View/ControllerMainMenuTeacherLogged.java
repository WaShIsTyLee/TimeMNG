package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Person;
import github.washistylee.Model.Entitys.Sesion;
import github.washistylee.Model.Entitys.Teacher;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerMainMenuTeacherLogged extends Controller implements Initializable {
    @FXML
    Button buttonAddChildrenModal;
    @FXML
    Button deleteChildren;
    @FXML
    Button updateChildren;
    @FXML
    Text text;
    @FXML
    TableView tableView;
    @FXML
    TableColumn<Child, String> tableColumnName;
    @FXML
    TableColumn<Child, String> tableColumnSurname;
    @FXML
    TableColumn<Child, String> tableColumnIDMinder;
    @FXML
    TableColumn<Child, String> tableColumnObservation;
    @FXML
    TableColumn<Child, String> tableColumnDiseases;
    @FXML
    TableColumn<Child, String> tableColumnIDchild;
    @FXML
    TableColumn<Child, String> tableColumnClass;
    @FXML
    TableColumn<Child, String> tableColumnAge;
    private ObservableList<Child> child;


    @FXML
    public void nameOfLogged() {
        Person personaux = Sesion.getInstancia().getUsuarioIniciado();
        if (personaux.getClass() == Teacher.class) {
            Teacher teacher = (Teacher) personaux;
            text.setText("Bienvenido " + Sesion.getInstancia().getUsuarioIniciado().getName() + " tu asignatura impartida es " +
                    teacher.getSubject());

        }
    }
        @Override
        public void onOpen (Object input) throws IOException {
            ChildDAO cdao = new ChildDAO();
            List<Child> child = cdao.findChildByTeacherMail(Sesion.getInstancia().getUsuarioIniciado().getEmail());
            this.child = FXCollections.observableArrayList(child);
            tableView.setItems(this.child);

        }

        @Override
        public void onClose (Object output) throws IOException {

        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            nameOfLogged();
            tableView.setEditable(true);
            tableColumnName.setCellValueFactory(child ->
                    new SimpleStringProperty(child.getValue().getName()));
            tableColumnSurname.setCellValueFactory(child ->
                    new SimpleStringProperty(child.getValue().getSurname()));
            tableColumnIDMinder.setCellValueFactory(child ->
                    new SimpleStringProperty(child.getValue().getMinder().getEmail()));
            tableColumnObservation.setCellValueFactory(child ->
                    new SimpleStringProperty(child.getValue().getObservation()));
            tableColumnDiseases.setCellValueFactory(child ->
                    new SimpleStringProperty(child.getValue().getDiseasesString()));
            tableColumnAge.setCellValueFactory(cellData ->
                    new SimpleStringProperty(String.valueOf(cellData.getValue().getAge())));
            tableColumnClass.setCellValueFactory(child ->
                    new SimpleStringProperty(child.getValue().getClassroom()));
            tableColumnIDchild.setCellValueFactory(cellData ->
                    new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));


        }
    @FXML
    private void logOut() throws IOException {
        Sesion.getInstancia().logOut();
        App.currentController.changeScene(Scenes.PANTALLALOGINREGISTER, null);
        System.out.println(Sesion.getInstancia().getUsuarioIniciado());

    }
    @FXML
    public void addChildModal() throws IOException {
        App.currentController.openModal(Scenes.ADDCHILDONMINDER, "Añadir un niño...", this, null);
    }

    @FXML
    public  void updateChildModal() throws IOException {
        App.currentController.openModal(Scenes.UPDATECHILDONTEACHER, "Actualizar un niño...", this, null);
    }

    @FXML
    public void deleteChildModal() throws IOException {
        App.currentController.openModal(Scenes.DELETECHILD, "Borrar un niño...", this, null);
    }



}
