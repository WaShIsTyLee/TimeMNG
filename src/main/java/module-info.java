
module github.washistylee {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;

    opens github.washistylee.Model.Connection to java.xml.bind;

    exports github.washistylee;
    opens github.washistylee.Model.Test to java.xml.bind;

    opens github.washistylee.View to java.xml.bind, javafx.fxml;
    opens github.washistylee to java.xml.bind, javafx.fxml;

}
