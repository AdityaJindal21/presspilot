package com.example.javaproject.areamaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.example.javaproject.jdbcc.MySqlDBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AreaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAreaName;

    @FXML
    void doSave(ActionEvent event) {
        String query = "insert into areamaster values(?)";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1,txtAreaName.getText().toLowerCase().trim());
            pst.executeUpdate();
            System.out.println("Record Saved");
            showMsg("Data Saved Successfully");
        }catch (Exception e)
        {
            System.out.println(e.toString());
            showMsg("This Area already exists");
        }
        txtAreaName.clear();


    }
    void showMsg(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");


        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    Connection con;
    @FXML
    void initialize() {
        con = MySqlDBConnection.getMySqlDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }

    }

}
