package github.washistylee.View;

public enum Scenes {
    ROOT("View/Layout.fxml"),
    PANTALLALOGINREGISTER("View/MenuLoginRegister.fxml"),
    MENULOGINMINDER("View/MenuLoginMinder.fxml"),
    MENUREGISTERMINDER("View/MenuRegisterMinder.fxml"),
    MENUREGISTERTEACHER("View/MenuRegisterTeacher.fxml"),
    ADDCHILD("View/AddChild.fxml"),
    MENULOGINTEACHER("View/MenuLoginTeacher.fxml"),
    MAINMENU("View/MainMenu.fxml");

    private String url;
    Scenes(String url){
        this.url=url;
    }
    public String getURL(){
        return url;
    }
}
