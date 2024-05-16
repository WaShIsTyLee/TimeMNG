package github.washistylee.View;

import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.ScheduleDAO;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Month;
import github.washistylee.Model.Entitys.Schedule;
import github.washistylee.Model.Entitys.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

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

    /**
     * Initializes the view with schedules associated with the logged-in Minder.
     * @param input The input object (not used in this method).
     * @throws IOException If an I/O error occurs.
     */
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
    /**
     * Initializes the table view with editable columns and sets up cell factories for each column.
     * @param url The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources for the root object, or null if none.
     */
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

        tableColumnActivities.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnActivities.setOnEditCommit(event -> {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Schedule schedule = event.getRowValue();
                ArrayList<String> newActivities = new ArrayList<>();
                newActivities.add(event.getNewValue());
                schedule.setActivitys(newActivities);
                ScheduleDAO sdao = new ScheduleDAO();
                sdao.update(schedule);
            }
        });

        tableColumnHour.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnHour.setOnEditCommit(event -> {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Schedule schedule = event.getRowValue();

                if (Schedule.isHour(event.getNewValue())) {
                    schedule.setHour(event.getNewValue());
                    ScheduleDAO sdao = new ScheduleDAO();
                    sdao.update(schedule);
                } else {
                    AppController.showAlertForUpdateSchedule();
                    event.getTableView().refresh();
                }
            }
        });

        tableColumnDays.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnDays.setOnEditCommit(event -> {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Schedule schedule = event.getRowValue();
                if (Schedule.isDay(event.getNewValue())) {
                    schedule.setDay(event.getNewValue());
                    ScheduleDAO sdao = new ScheduleDAO();
                    sdao.update(schedule);
                } else {
                    AppController.showAlertForUpdateSchedule();
                    event.getTableView().refresh();


                }
            }
        });
        tableColumnMonth.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMonth.setOnEditCommit(event ->{
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Schedule schedule = event.getRowValue();
                if (Schedule.isValidMonth(event.getNewValue())) {
                    String months = event.getNewValue();
                    Month month = Month.valueOf(months.toUpperCase());
                    schedule.setMonth(month);
                    ScheduleDAO sdao = new ScheduleDAO();
                    sdao.update(schedule);
                } else {
                    AppController.showAlertForUpdateSchedule();
                    event.getTableView().refresh();

                }
            }
        });
        }
    }


