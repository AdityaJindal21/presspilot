package com.example.javaproject.dashboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.javaproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void handelArea(ActionEvent event) {
        try{
            Stage stage  = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("areamasterr/AreaView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 856, 700);
            stage.setTitle("Insert Region");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleAddCustomer(ActionEvent event) {
        try{
            Stage stage  = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerss/CustomerView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 856, 700);
            stage.setTitle("Add Client");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleBillBoard(ActionEvent event) {
        try{
            Stage stage  = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billboardd/BillBoardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 856, 700);
            stage.setTitle("Bill Board");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleBillCollector(ActionEvent event) {
        try{
            Stage stage  = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billcollectt/BillCollectorView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 856, 700);
            stage.setTitle("Bill Collector");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void handleBillGeneration(ActionEvent event) {
        try{
            Stage stage  = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billingg/BillingView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 856, 700);
            stage.setTitle("Bill Generation");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleGraphicalAnalysis(ActionEvent event) {
        try{
            Stage stage  = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("analysiss/AnalysisView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 856, 700);
            stage.setTitle("Graphical Analysis");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void handleCustomerBoard(ActionEvent event) {
        try{
            Stage stage  = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerboardd/CustomerBoardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 856, 700);
            stage.setTitle("Customer Board");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void handleHawkerManager(ActionEvent event) {
        try{
            Stage stage  = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hawkerss/HawkersView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 856, 700);
            stage.setTitle("Hawker Manager");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handlePaperManager(ActionEvent event) {
        try{
            Stage stage  = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("papermasterr/PaperMasterView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 856, 700);
            stage.setTitle("Paper Master");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {

    }

}
