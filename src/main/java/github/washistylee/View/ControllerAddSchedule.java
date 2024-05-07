package github.washistylee.View;

import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.ScheduleDAO;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Month;
import github.washistylee.Model.Entitys.Schedule;
import github.washistylee.Model.Entitys.Sesion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerAddSchedule extends Controller implements Initializable {
    @FXML
    TextField textFieldActivities;
    @FXML
    TextField textFieldHour;
    @FXML
    ComboBox<Integer> comboBoxDays;
    @FXML
    ComboBox<String> comboBoxMonth;
    @FXML
    TextField textFieldIDChild;
    @FXML
    Button button;

    public Schedule takeValuesAddSchedule() {
        Schedule aux;
        ChildDAO cdao = new ChildDAO();
        int childID = (textFieldIDChild.getText().equals("")) ? 0 : Integer.valueOf(textFieldIDChild.getText());
        Child child = cdao.findById(childID);
        Object month = comboBoxMonth.getSelectionModel().getSelectedItem();
        Month monthe = Month.valueOf(month.toString().toUpperCase());
        Integer day = comboBoxDays.getSelectionModel().getSelectedItem();
        String days = day.toString();
        String hour = textFieldHour.getText();
        List<String> activitiesList;
        String activities = textFieldActivities.getText();
        activitiesList = List.of(activities.split((",\\s*")));
        aux = new Schedule(monthe, days, hour, activitiesList, child);
        return aux;
    }

    public void addScheduleToBD() {
        ScheduleDAO sdao = new ScheduleDAO();
        ChildDAO cdao = new ChildDAO();
        Schedule schedule = takeValuesAddSchedule();
        if (schedule.getChild() != null && cdao.findById(schedule.getChild().getId()) != null
                && schedule.getChild().getMinder().getEmail().equals(Sesion.getInstancia().getUsuarioIniciado().getEmail())) {
            sdao.save(schedule);
        } else {
            AppController.showAlertForUpdateChild();
            //cambiar esta alerta
        }
    }

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) throws IOException {

    }

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
