package com.example.javaproject.billing;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.javaproject.jdbcc.MySqlDBConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BillingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker doe;

    @FXML
    private DatePicker dos;

    @FXML
    private TextField txtFinalAmount;

    @FXML
    private TextField txtLessNoDays;

    @FXML
    private TextField txtMob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTotPrices;

    void showMsg(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");


        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    @FXML
    void doFetch(ActionEvent event) {
        try {
            PreparedStatement stmt = con.prepareStatement("select cname, dos, prices from customers where mobile=?");
            stmt.setString(1,txtMob.getText());
            ResultSet res= stmt.executeQuery();
            if(res.next())
            {
                String name = res.getString("cname");
                Date d = res.getDate("dos");
                String prices = res.getString("prices");

                txtName.setText(name);
                dos.setValue(d.toLocalDate());

                String[] priceArray = prices.split(",");
                float sum=0;
                for (String ar : priceArray) {
                    sum+= Float.parseFloat(ar);
                }
                txtTotPrices.setText(String.valueOf(sum));
            }
            else{
                System.out.println("Invalid Number");
                showMsg("Invalid Number");
            }

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }

    }


    int FinalBillingDays;
    @FXML
    void doGenerateBill(ActionEvent event) {
        LocalDate startDate = dos.getValue();
        LocalDate endDate = doe.getValue();

        int days;
        if (startDate != null && endDate != null) {
            days = Math.toIntExact(ChronoUnit.DAYS.between(startDate, endDate));
            System.out.println("Days between: " + days);
        } else {
            System.out.println("Please select End dates.");
            showMsg("Please Select End date");
            return;
        }
        int lessdays = Integer.parseInt(txtLessNoDays.getText());

        FinalBillingDays = days-lessdays;
        float FinalBillingAmount = Float.parseFloat(txtTotPrices.getText()) * FinalBillingDays;
        txtFinalAmount.setText(String.valueOf(FinalBillingAmount));

    }

    @FXML
    void doSaveBill(ActionEvent event) {
        String query = "insert into billing (mobilenumber, dos, doe, days, bill, statuss) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1, txtMob.getText());

            LocalDate lcl = dos.getValue();
            java.sql.Date dt= java.sql.Date.valueOf(lcl);
            pst.setDate(2,dt);

            LocalDate lcl1 = doe.getValue();
            java.sql.Date dt1= java.sql.Date.valueOf(lcl1);
            pst.setDate(3,dt1);

            pst.setInt(4,FinalBillingDays);
            pst.setFloat(5,Float.parseFloat(txtFinalAmount.getText()));
            pst.setInt(6,1);

            pst.executeUpdate();
            System.out.println("Record Saved");
            showMsg("Bill Saved Successfully");
        }catch (Exception e)
        {
            System.out.println(e.toString());
            showMsg("Error while Saving Bill, Please Check details again!");
        }
        doClear();

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
    void doClear()
    {
        txtMob.clear();
        txtName.clear();
        txtTotPrices.clear();
        dos.setValue(null);
        doe.setValue(null);
        txtLessNoDays.clear();
        txtFinalAmount.clear();
    }

}
