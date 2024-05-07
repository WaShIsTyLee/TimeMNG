package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.Entitys.Child;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteChildController extends Controller implements Initializable {
    private Controller controller1;
    @FXML
    Button buttonDeleteChild;
    @FXML
    TextField textFieldIdChild;


    @FXML
    public Child getValuesTextField(){
        Child childaux = new Child();
        int idChild = ( textFieldIdChild.getText().equals("")) ? 0 : Integer.valueOf(textFieldIdChild.getText());
        childaux.setId(idChild);
        return childaux;
    }

    @FXML
    public void deleteChildFromBD() {
        Child childaux = getValuesTextField();
        ChildDAO cdao = new ChildDAO();
        if (cdao.findById(childaux.getId())!=null){
            cdao.delete(childaux);
        }else {
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
    public void backToMainMenu(Event event) throws IOException {
        System.out.println(Scenes.MAINMENUMINDERLOGGED);
        deleteChildFromBD();
        App.currentController.changeScene(Scenes.MAINMENUMINDERLOGGED, null);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
