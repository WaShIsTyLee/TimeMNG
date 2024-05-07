package github.washistylee.View;

import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.ScheduleDAO;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Schedule;
import github.washistylee.Model.Entitys.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerScheduleTable extends Controller implements Initializable {
    @FXML
    TableView tableView;
    @FXML
    TableColumn<Schedule, String> tableColumnIDChild;
    @FXML
    TableColumn<Schedule, String> tableColumnHour;
    @FXML
    TableColumn<Schedule, String> tableColumnMonth;
    @FXML
    TableColumn<Schedule, String> tableColumnDays;
    @FXML
    TableColumn<Schedule, String> tableColumnActivities;
    @FXML
    TableColumn<Schedule, String> tableColumnChildName;
    @FXML
    TableColumn<Schedule, String> tableColumnID;
    private ObservableList<Schedule> schedule;

    @Override
    public void onOpen(Object input) throws IOException {
        ScheduleDAO sdao = new ScheduleDAO();
        ChildDAO cdao = new ChildDAO();
        List<Child> children = cdao.findChildByMinderMail(Sesion.getInstancia().getUsuarioIniciado().getEmail());
        List<Schedule> allSchedules = new ArrayList<>();

        for (Child child : children) {
            List<Schedule> childSchedules = sdao.findAllSchedulesByChild(child);
            allSchedules.addAll(childSchedules);
        }

        ObservableList<Schedule> scheduleObservableList = FXCollections.observableArrayList(allSchedules);
        tableView.setItems(scheduleObservableList);

    }

    @Override
    public void onClose(Object output) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);
        tableColumnIDChild.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getChild().getId())));
        tableColumnHour.setCellValueFactory(schedule ->
                new SimpleStringProperty(schedule.getValue().getHour()));
        tableColumnMonth.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getMonth())));
        tableColumnDays.setCellValueFactory(schedule ->
                new SimpleStringProperty(schedule.getValue().getDay()));
        tableColumnActivities.setCellValueFactory(schedule ->
                new SimpleStringProperty(schedule.getValue().getActivitiesAsString()));
        tableColumnID.setCellValueFactory(schedule ->
                new SimpleStringProperty(String.valueOf(schedule.getValue().getID())));
        tableColumnChildName.setCellValueFactory(schedule ->
                new SimpleStringProperty(schedule.getValue().getChild().getName()));
    }
}
