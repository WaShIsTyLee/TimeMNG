package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.ScheduleDAO;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Month;
import github.washistylee.Model.Entitys.Schedule;
import github.washistylee.Model.Entitys.Sesion;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerAddSchedule extends Controller implements Initializable {
    @FXML
    private TextField textFieldActivities;
    @FXML
    private TextField textFieldHour;
    @FXML
    private ComboBox<Integer> comboBoxDays;
    @FXML
    private ComboBox<String> comboBoxMonth;
    @FXML
    private TextField textFieldIDChild;
    @FXML
    private Button button;
    /**
     * Retrieves input values from UI fields to create a Schedule object for adding a new schedule.
     * @return The Schedule object created with the input values.
     */    public Schedule takeValuesAddSchedule() {
        Schedule aux;
        ChildDAO cdao = new ChildDAO();
        int childID = (textFieldIDChild.getText().equals("")) ? 0 : Integer.valueOf(textFieldIDChild.getText());
        Child child = cdao.findById(childID);
        Object month = comboBoxMonth.getSelectionModel().getSelectedItem();
        Month monthe = Month.valueOf(month.toString().toUpperCase());
        Integer day = comboBoxDays.getSelectionModel().getSelectedItem();
        String days = day.toString();
        String hour = textFieldHour.getText();
        if (!Schedule.isHour(hour)){
            hour = "";
        }
        List<String> activitiesList;
        String activities = textFieldActivities.getText();
        activitiesList = List.of(activities.split((",\\s*")));
        aux = new Schedule(monthe, days, hour, activitiesList, child);
        return aux;
    }

    /**
     * Adds the Schedule object to the database.
     */
    public void addScheduleToBD() {
        ScheduleDAO sdao = new ScheduleDAO();
        ChildDAO cdao = new ChildDAO();
        Schedule schedule = takeValuesAddSchedule();
        if (schedule.getHour().equals("")){
            AppController.showAlertForUpdateChild();
        }
        if (schedule.getChild() != null && cdao.findById(schedule.getChild().getId()) != null
                && schedule.getChild().getMinder().getEmail().equals(Sesion.getInstancia().getUsuarioIniciado().getEmail())) {
            sdao.save(schedule);

        } else {
            AppController.showAlertForUpdateChild();
        }
    }

    public void backToMainMenu(Event event) throws IOException {
        addScheduleToBD();
        App.currentController.changeScene(Scenes.MAINMENU, null);
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    @Override
    public void onOpen(Object input) throws IOException {

    }
    /**
     * Initializes the ComboBoxes for selecting month and day.
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            comboBoxMonth.setItems(FXCollections.observableArrayList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));
            comboBoxDays.setItems(FXCollections.observableArrayList(
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                    11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                    21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
            ));
        }
}
