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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static github.washistylee.View.AppController.loadFXML;

public class ControllerMainMenu extends Controller implements Initializable {

    @FXML
    Text text;
    @FXML
    Button buttonAddChildrenModal;
    @FXML
    Button deleteChildren;
    @FXML
    Button updateChildren;

    @Override
    public void onOpen(Object input) throws IOException {
        Person personaux = Sesion.getInstancia().getUsuarioIniciado();
        if (personaux.getClass() == Minder.class) {
            Minder minder = (Minder) personaux;
            ChildDAO cdao = new ChildDAO();
            System.out.println(minder);
            MinderDAO mdao = new MinderDAO();
            mdao.findByMail(minder.getEmail());

        } else {
            Teacher teacher = (Teacher) personaux;
            System.out.println(teacher);
        }


    }


    public void onOpen(Sesion sesion) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    @FXML
    private void addChildModal() throws IOException {
        App.currentController.openModal(Scenes.ADDCHILD,"Añadir un niño...",this,null);
    }
}
