package github.washistylee.View;

public enum Scenes {
    ROOT("View/Layout.fxml"),
    PANTALLALOGINREGISTER("View/MenuLoginRegister.fxml"),
    MENULOGIN("View/MenuLogin.fxml"),
    MENUREGISTERMINDER("View/MenuRegisterMinder.fxml"),
    MENUREGISTERTEACHER("View/MenuRegisterTeacher.fxml"),
    ADDCHILDONMINDER("View/AddChildOnMinder.fxml"),
    MAINMENU("View/MainMenu.fxml"),
    ADDSCHEDULE("View/AddSchedule.fxml"),
    CONSULTSCHEDULE("View/SchedulesTable.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }
}
