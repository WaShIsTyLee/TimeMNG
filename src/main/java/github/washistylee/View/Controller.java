package github.washistylee.View;

import github.washistylee.App;

import java.io.IOException;

public abstract class Controller {
    App app;
    public void setApp(App app){
        this.app=app;
    }

    public abstract void onOpen(Object input) throws IOException;
    public abstract void onClose(Object output) throws IOException;
}

