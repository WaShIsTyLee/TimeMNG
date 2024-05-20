package github.washistylee.View;

import github.washistylee.App;
import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.DAO.TeacherDAO;
import github.washistylee.Model.Entitys.*;
import github.washistylee.Model.Utils.Bytes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainControllerMenu extends Controller implements Initializable {
    @FXML
    private Text text;
    @FXML
    private Button buttonAddChildrenModal;
    @FXML
    private Button buttonConsultActivities;
    @FXML
    private Button buttonAddSchedule;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Child, String> tableColumnName;
    @FXML
    private TableColumn<Child, String> tableColumnSurname;
    @FXML
    private TableColumn<Child, String> tableColumnID;
    @FXML
    private TableColumn<Child, String> tableColumnObservation;
    @FXML
    private TableColumn<Child, String> tableColumnDiseases;
    @FXML
    private TableColumn<Child, String> tableColumnIDchild;
    @FXML
    private TableColumn<Child, String> tableColumnClass;
    @FXML
    private TableColumn<Child, String> tableColumnAge;
    @FXML
    private TableColumn<Child, Void> deleteColumn = new TableColumn<>("Delete");

    private File imageFile;
    @FXML
    private ImageView photoImageView;
    @FXML
    private Button button;
    @FXML
    private Circle photoClip;

    TeacherDAO tDao = new TeacherDAO();
    ChildDAO cDao = new ChildDAO();
    private ObservableList<Child> child;




    @FXML
    private void loadImage() {
        MinderDAO mdao = new MinderDAO();
        Minder minder = new Minder();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        if (photoImageView != null && photoImageView.getScene() != null) {
            Stage stage = (Stage) photoImageView.getScene().getWindow();
            File imageFile = fileChooser.showOpenDialog(stage);
            if (imageFile != null) {
                try (InputStream is = new FileInputStream(imageFile)) {
                    Image image = new Image(is);
                    photoImageView.setImage(image);
                    minder.setPhoto(Bytes.convertImageToBytes(imageFile));
                    minder.setEmail(Sesion.getInstancia().getUsuarioIniciado().getEmail());
                    mdao.updatePhoto(minder);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("photoImageView o su escena son nulos");
        }
    }



    /**
     * This method is called when stage is opened. It retrieves the currently logged-in user,
     * checks if the user is a Minder or a Teacher, and sets appropriate text messages and loads
     * data into a JavaFX TableView accordingly.
     *
     * @param input
     * @throws IOException
     */
    @Override
    public void onOpen(Object input) throws IOException {
        Person personaux = Sesion.getInstancia().getUsuarioIniciado();
        List<Child> child;
        if (personaux.getClass() == Minder.class) {
            Minder minder = (Minder) personaux;
            text.setText("Bienvenido " + Sesion.getInstancia().getUsuarioIniciado().getName() + " tu horario de trabajo " +
                    "es de " + minder.getHours() + " horas");
            child = cDao.findChildByMinderMail(Sesion.getInstancia().getUsuarioIniciado().getEmail());
            this.child = FXCollections.observableArrayList(child);
            tableView.setItems(this.child);
            MinderDAO mdao = new MinderDAO();
            Image image = null;
            byte[] imageBytes = mdao.getPhotoByEmail((Minder)Sesion.getInstancia().getUsuarioIniciado());
            image = new Image(new ByteArrayInputStream(imageBytes));
            photoImageView.setImage(image);
        } else {
            Teacher teacher = (Teacher) personaux;
            text.setText("Bienvenido " + Sesion.getInstancia().getUsuarioIniciado().getName() + " tu asignatura impartida es " +
                    teacher.getSubject());
            child = cDao.findChildByTeacherMail(Sesion.getInstancia().getUsuarioIniciado().getEmail());
            this.child = FXCollections.observableArrayList(child);
            tableView.setItems(this.child);
            photoImageView.setDisable(true);
            photoImageView.setOpacity(0);
            button.setOpacity(0);
            button.setDisable(true);

        }
    }

    /**
     * Initializes the controller upon loading the corresponding view. It checks the type of the
     * logged-in user and calls specific initialization methods accordingly.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Sesion.getInstancia().getUsuarioIniciado() instanceof Minder) {
            initializeMinder();
        } else if (Sesion.getInstancia().getUsuarioIniciado() instanceof Teacher) {
            initializeTeacher();
        }

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
    public void consultScheduleModal() throws IOException {
        App.currentController.openModal(Scenes.CONSULTSCHEDULE, "Consultar el horario de un niño...", this, null);
    }


    @FXML
    public void addScheduledModal() throws IOException {
        App.currentController.openModal(Scenes.ADDSCHEDULE, "Añadir un horario...", this, null);
    }

    /**
     * Initializes the UI components and behavior specific to a Teacher user.
     */
    private void initializeTeacher() {
        buttonAddChildrenModal.setVisible(false);
        buttonAddChildrenModal.setDisable(true);
        buttonAddSchedule.setVisible(false);
        buttonAddSchedule.setDisable(true);
        buttonConsultActivities.setVisible(false);
        buttonConsultActivities.setDisable(true);
        tableColumnID.setText("IDCuidador");
        tableView.getColumns().remove(deleteColumn);
        tableView.setEditable(true);
        tableColumnName.setCellValueFactory(child ->
                new SimpleStringProperty(child.getValue().getName()));
        tableColumnSurname.setCellValueFactory(child ->
                new SimpleStringProperty(child.getValue().getSurname()));
        tableColumnID.setCellValueFactory(child ->
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
        tableColumnObservation.setCellFactory(TextFieldTableCell.forTableColumn());

        tableColumnObservation.setOnEditCommit(event ->
        {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Child child = event.getRowValue();
                child.setObservation(event.getNewValue());
                cDao.update(child);
            }
        });

    }

    /**
     * Initializes the UI components and behavior specific to a Teacher user.
     */
    private void initializeMinder() {
        photoClip.setRadius(photoImageView.getFitWidth() / 2);
        photoClip.setCenterX(photoImageView.getFitWidth() / 2);
        photoClip.setCenterY(photoImageView.getFitHeight() / 2);
        tableView.setEditable(true);
        tableColumnName.setCellValueFactory(child ->
                new SimpleStringProperty(child.getValue().getName()));
        tableColumnSurname.setCellValueFactory(child ->
                new SimpleStringProperty(child.getValue().getSurname()));
        tableColumnID.setCellValueFactory(child ->
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

        deleteColumn.setMinWidth(60);
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("❌");

            {
                deleteButton.setOnAction(event -> {
                    if (AppController.showAlertForUConfirmate()) {
                        Child child = getTableView().getItems().get(getIndex());
                        cDao.delete(child);
                        tableView.getItems().remove(child);
                    }
                });
            }

            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        tableColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnName.setOnEditCommit(event -> {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Child child = event.getRowValue();
                if (Child.isString(event.getNewValue())) {
                    child.setName(event.getNewValue());
                    cDao.update(child);
                } else {
                    AppController.showAlertForUpdateChild();
                    event.getTableView().refresh();
                }

            }
        });

        tableColumnSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnSurname.setOnEditCommit(event -> {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Child child = event.getRowValue();
                if (Child.isString(event.getNewValue())) {
                    child.setSurname(event.getNewValue());
                    cDao.update(child);
                } else {
                    AppController.showAlertForUpdateChild();
                    event.getTableView().refresh();
                }

            }
        });

        tableColumnAge.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnAge.setOnEditCommit(event ->

        {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Child child = event.getRowValue();
                if (Child.isNumber(event.getNewValue())) {
                    String age = event.getNewValue();
                    int agec = Integer.parseInt(age);
                    child.setAge(agec);
                    cDao.update(child);
                } else {
                    AppController.showAlertForUpdateChild();
                    event.getTableView().refresh();
                }

            }
        });
        tableColumnClass.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnClass.setOnEditCommit(event ->
        {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Child child = event.getRowValue();
                child.setClassroom(event.getNewValue());
                cDao.update(child);
            }
        });
        tableColumnObservation.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnObservation.setOnEditCommit(event ->
        {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Child child = event.getRowValue();
                child.setObservation(event.getNewValue());
                cDao.update(child);
            }
        });
        tableColumnDiseases.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnDiseases.setOnEditCommit(event ->
        {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {
                Child child = event.getRowValue();
                ArrayList<String> newDiseases = new ArrayList<>();
                newDiseases.add(event.getNewValue());
                child.setDiseases(newDiseases);
                cDao.update(child);
            }
        });
        tableColumnID.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnID.setOnEditCommit(event ->
        {
            if (event.getNewValue() == event.getOldValue()) {
                return;
            }
            if (event.getNewValue().length() <= 60) {

                Teacher teacher = tDao.verifyTeacherEmailIfExist(event.getNewValue());
                if (teacher != null) {
                    Child child = event.getRowValue();
                    child.setTeacher(teacher);
                    cDao.update(child);
                } else {
                    AppController.showAlertForUpdateChild();
                    event.getTableView().refresh();
                }

            }
        });

    }
}
