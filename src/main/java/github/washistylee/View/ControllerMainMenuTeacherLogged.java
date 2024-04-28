package github.washistylee.View;

import github.washistylee.Model.Entitys.Child;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMainMenuTeacherLogged extends Controller implements Initializable {
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
    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
