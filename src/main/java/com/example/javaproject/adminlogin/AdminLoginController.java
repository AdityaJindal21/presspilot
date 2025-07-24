package com.example.javaproject.adminlogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.javaproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtusername;

    void showMsg(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");


        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    @FXML
    void dologin(ActionEvent event) throws Exception{
        String username = txtusername.getText();
        String password = txtpassword.getText();

        BufferedReader rdr = new BufferedReader(new FileReader(new File("adminpwd.txt")));
        String pwd = rdr.readLine();

        if(username.equals("root") && password.equals(pwd))
        {
            try{
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboardd/DashboardView.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 856, 700);
                stage.setTitle("Admin Login Panel");
                stage.setScene(scene);
                stage.setMaximized(false);
                stage.setResizable(false);
                stage.show();

                Stage currentStage = (Stage) txtusername.getScene().getWindow();
                currentStage.close();

            }catch (Exception e)
            {
                e.printStackTrace();
                showMsg("Failed to load Dashboard. Please Try again Later");
            }
        }
        else{
            showMsg("Invalid Username or password");
        }

    }

    @FXML
    void initialize() {


    }

}
