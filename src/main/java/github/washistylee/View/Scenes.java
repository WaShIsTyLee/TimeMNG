package github.washistylee.View;

public enum Scenes {
    ROOT("View/Layout.fxml"),
    PANTALLALOGINREGISTER("View/MenuLoginRegister.fxml"),
    MENULOGINMINDER("View/MenuLoginMinder.fxml"),
    MENUREGISTERMINDER("View/MenuRegisterMinder.fxml"),
    MENUREGISTERTEACHER("View/MenuRegisterTeacher.fxml"),
    ADDCHILDONMINDER("View/AddChildOnMinder.fxml"),
    DELETECHILD("View/DeleteChild.fxml"),
    UPDATECHILDONMINDER("View/UpdateChildOnMinder.fxml"),
    UPDATECHILDONTEACHER("View/UpdateChildOnTeacher.fxml"),
    ADDSCHEDULE("View/AddSchedule.fxml"),
    UPDATESCHEDULE("View/UpdateSchedule.fxml"),
    MENULOGINTEACHER("View/MenuLoginTeacher.fxml"),
    MAINMENUMINDERLOGGED("View/MainMenuMinderLogged.fxml"),
    CONSULTSCHEDULE("View/SchedulesTable.fxml"),

    MAINMENUTEACHERLOGGED("View/MainMenuTeacherLogged.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }
}
