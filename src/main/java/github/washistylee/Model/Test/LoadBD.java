package github.washistylee.Model.Test;

import github.washistylee.Model.Connection.ConnectionProperties;
import github.washistylee.Model.Utils.XMLManager;

public class LoadBD {
    public static void main(String[] args) {

        ConnectionProperties conn = XMLManager.readXML(new ConnectionProperties(),"connection.xml");
        System.out.println(conn);
    }
}
