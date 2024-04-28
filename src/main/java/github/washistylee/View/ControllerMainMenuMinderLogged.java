package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.Entitys.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerMainMenuMinderLogged extends Controller implements Initializable {

    @FXML
    Text text;
    @FXML
    Button buttonAddChildrenModal;
    @FXML
    Button deleteChildren;
    @FXML
    Button updateChildren;
    @FXML
    TableView tableView;
    @FXML
    TableColumn<Child, String> tableColumnName;
    @FXML
    TableColumn<Child, String> tableColumnSurname;
    @FXML
    TableColumn<Child, String> tableColumnIDTeacher;
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
    @FXML
    ImageView imageView;

    private ObservableList<Child> child;


    @FXML
    public void nameOfLogged() {
        Person personaux = Sesion.getInstancia().getUsuarioIniciado();
        if (personaux.getClass() == Minder.class) {
            Minder minder = (Minder) personaux;
            text.setText("Bienvenido " + Sesion.getInstancia().getUsuarioIniciado().getName() + " tu horario de trabajo " +
                    "es de " + minder.getHours() + " horas");

        }

    }

    @Override
    public void onOpen(Object input) throws IOException {
        ChildDAO cdao = new ChildDAO();
        List<Child> child = cdao.findChildByMinderMail(Sesion.getInstancia().getUsuarioIniciado().getEmail());
        this.child = FXCollections.observableArrayList(child);
        tableView.setItems(this.child);

        Person personaux = Sesion.getInstancia().getUsuarioIniciado();
        if (personaux.getClass() == Minder.class) {
            Minder minder = (Minder) personaux;
            System.out.println(minder);
            MinderDAO mdao = new MinderDAO();
            mdao.findByMail(minder.getEmail());

        } else {
            Teacher teacher = (Teacher) personaux;
            System.out.println(teacher);
        }


    }


    @Override
    public void onClose(Object output) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameOfLogged();
        tableView.setEditable(true);
        tableColumnName.setCellValueFactory(child ->
                new SimpleStringProperty(child.getValue().getName()));
        tableColumnSurname.setCellValueFactory(child ->
                new SimpleStringProperty(child.getValue().getSurname()));
        tableColumnIDTeacher.setCellValueFactory(child ->
                new SimpleStringProperty(child.getValue().getTeacher().getEmail()));
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
    private void updateUserLogged(){

    }

    @FXML
    public void addChildModal() throws IOException {
        App.currentController.openModal(Scenes.ADDCHILD, "Añadir un niño...", this, null);
    }

    @FXML
    public  void updateChildModal() throws IOException {
        App.currentController.openModal(Scenes.UPDATECHILD, "Actualizar un niño...", this, null);
    }

    @FXML
     public void deleteChildModal() throws IOException {
        App.currentController.openModal(Scenes.DELETECHILD, "Borrar un niño...", this, null);
    }

}
