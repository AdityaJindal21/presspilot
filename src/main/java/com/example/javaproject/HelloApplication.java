package com.example.javaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("papermasterr/PaperMasterView.fxml"));
//                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hawkerss/HawkersView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("areamasterr/AreaView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerss/CustomerView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billingg/BillingView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billboardd/BillBoardView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billcollectt/BillCollectorView.fxml"));

//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customerboardd/CustomerBoardView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("analysiss/AnalysisView.fxml"));
//
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboardd/DashboardView.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminloginn/AdminLoginView.fxml"));


        Scene scene = new Scene(fxmlLoader.load(), 856, 700);
        stage.setTitle("User Dashboard Panel");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}