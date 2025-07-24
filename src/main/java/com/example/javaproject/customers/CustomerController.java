package com.example.javaproject.customers;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.javaproject.jdbcc.MySqlDBConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class CustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboArea;

    @FXML
    private ComboBox<String> ComboHawkerID;

    @FXML
    private DatePicker dos;

    @FXML
    private ListView<String> lstPapers;

    @FXML
    private ListView<String> lstPrices;

    @FXML
    private ListView<String> lstSelPapers;

    @FXML
    private ListView<String> lstSelPrices;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobno;

    @FXML
    private TextField txtName;

    void showMsg(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");


        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    @FXML
    void doChange(ActionEvent event) {
        String query = "update customers set cname=?, emailid=?, address=?, dos=?, area=?, hawkerid=?, papers=?, prices=? where mobile=?";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1,txtName.getText());
            pst.setString(2, txtEmail.getText());
            pst.setString(3,txtAddress.getText());
            LocalDate lcl = dos.getValue();
            java.sql.Date dt= java.sql.Date.valueOf(lcl);
            pst.setDate(4,dt);
            pst.setString(5,ComboArea.getValue());
            pst.setString(6,ComboHawkerID.getValue());

            ObservableList<String> lstpaper = lstSelPapers.getItems();
            ObservableList<String> lstprice = lstSelPrices.getItems();

            String joinedpaper = String.join(", ", lstpaper);
            String joinedprice = String.join(", ",lstprice);

            pst.setString(7,joinedpaper);
            pst.setString(8,joinedprice);
            pst.setString(9,txtMobno.getText());
            int rowchanged = pst.executeUpdate();
            if(rowchanged>0)
            {
                System.out.println("Record Updated");
                showMsg("Record Updated Successfully");
            }
            else {
                System.out.println("no record found");
                showMsg("No Record found with Such Details");
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }
        doClear();

    }
    @FXML
    void doDelPapers(MouseEvent event) {
        if(event.getClickCount()==2)
        {
            int indx = lstSelPapers.getSelectionModel().getSelectedIndex();
            lstSelPrices.getSelectionModel().select(indx);
            lstSelPapers.getItems().remove(indx);
            lstSelPrices.getItems().remove(indx);
        }

    }

    @FXML
    void doFetch(ActionEvent event) {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from customers where mobile=?");
            stmt.setString(1,txtMobno.getText());
            ResultSet res= stmt.executeQuery();
            if(res.next())
            {
                String name = res.getString("cname");
                String email=res.getString("emailid");
                String address=res.getString("address");
                Date d = res.getDate("dos");
                String area = res.getString("area");
                String Hid = res.getString("hawkerid");
                String papers = res.getString("papers");
                String prices = res.getString("prices");

                txtName.setText(name);
                txtEmail.setText(email);
                txtAddress.setText(address);
                txtEmail.setText(email);
                dos.setValue(d.toLocalDate());
                ComboArea.setValue(area);
                ComboHawkerID.setValue(Hid);

                String[] paperArray = papers.split(",");
                ArrayList<String> paperList = new ArrayList<>();
                for (String ar : paperArray) {
                    paperList.add(ar.trim());
                }
                lstSelPapers.getItems().addAll(paperList);

                String[] priceArray = prices.split(",");
                ArrayList<String> priceList = new ArrayList<>();
                for (String ar : priceArray) {
                    priceList.add(ar.trim());
                }
                lstSelPrices.getItems().addAll(priceList);
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

    @FXML
    void doNew(ActionEvent event) {
        doClear();
    }

    @FXML
    void doRemoveCustomer(ActionEvent event) {
        String query = "delete from customers where mobile=?";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1, txtMobno.getText());
            int rowchanged = pst.executeUpdate();
            if(rowchanged>0)
            {
                System.out.println("Record Deleted");
                showMsg("Record Deleted Successfully");
            }
            else {
                System.out.println("no record found");
                showMsg("No Record Found");
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }
        doClear();

    }

    @FXML
    void doSelArea(ActionEvent event) {

            try {
                ComboHawkerID.getItems().clear();

                PreparedStatement stmt = con.prepareStatement("select hawkerid, selareas from hawkers");
                ResultSet res = stmt.executeQuery();

                String selectedArea = ComboArea.getSelectionModel().getSelectedItem();
                System.out.println(selectedArea);

                while (res.next()) {
                    String name = res.getString("hawkerid");
                    String area = res.getString("selareas");

                    String[] areaArray = area.split(",");
                    ArrayList<String> areaList = new ArrayList<>();
                    for (String ar : areaArray) {
                        areaList.add(ar.trim());
                    }
                    System.out.println(areaList);
                    System.out.println(name);

                    System.out.println(area);

                    if (areaList.contains(selectedArea)) {
                        ComboHawkerID.getItems().add(name);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

    }


    @FXML
    void doSelPapers(MouseEvent event) {
        if(event.getClickCount()==2)
        {
            String selpaper = lstPapers.getSelectionModel().getSelectedItem();
            lstSelPapers.getItems().add(selpaper);

            int indx = lstPapers.getSelectionModel().getSelectedIndex();
            lstPrices.getSelectionModel().select(indx);

            lstSelPrices.getItems().add(lstPrices.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void doStartPaper(ActionEvent event) {
        String query = "insert into customers values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1, txtMobno.getText());
            pst.setString(2, txtName.getText());
            pst.setString(3,txtEmail.getText());
            pst.setString(4,txtAddress.getText());

            LocalDate lcl = dos.getValue();
            java.sql.Date dt= java.sql.Date.valueOf(lcl);
            pst.setDate(5,dt);

            pst.setString(6,ComboArea.getValue());
            pst.setString(7,ComboHawkerID.getValue());

            ObservableList<String> lstpaper = lstSelPapers.getItems();
            ObservableList<String> lstprice = lstSelPrices.getItems();

            String joinedpaper = String.join(", ", lstpaper);
            String joinedprice = String.join(", ",lstprice);

            pst.setString(8,joinedpaper);
            pst.setString(9,joinedprice);
            pst.setInt(10,1);
            pst.executeUpdate();
            System.out.println("Record Saved");
            showMsg("Data Saved Successfully");
        }catch (Exception e)
        {
            System.out.println(e.toString());
            showMsg("This Mobile Number already exists");
        }
        doClear();

    }
    Connection con;
    ArrayList<String> papers=new ArrayList<String>();
    ArrayList<String> prices = new ArrayList<String>();
    ArrayList<String> areas = new ArrayList<String>();

    @FXML
    void initialize() {
        con = MySqlDBConnection.getMySqlDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }
        getpapersandprices();
        getareas();
        lstPapers.getItems().addAll(papers);
        lstPrices.getItems().addAll(prices);
        ComboArea.getItems().addAll(areas);

    }
    void getpapersandprices()
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("select distinct Papertitle, price from papermaster");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String pap=res.getString("Papertitle");
                float price = res.getFloat("price");
                papers.add(pap);
                prices.add(String.valueOf(price));
            }
            System.out.println(papers);
            System.out.println(prices);
        }
        catch(Exception exp) {
            exp.printStackTrace();
        }
    }
    void getareas()
    {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from areamaster");
            ResultSet res = stmt.executeQuery();
            while(res.next())
            {
                String area = res.getString("Areaname");
                areas.add(area);
            }
            System.out.println(areas);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void doClear()
    {
        txtMobno.clear();
        txtName.clear();
        txtEmail.clear();
        txtAddress.clear();
        dos.setValue(null);

        ComboArea.getSelectionModel().clearSelection();
        ComboHawkerID.getSelectionModel().clearSelection();

        lstSelPrices.getItems().clear();
        lstSelPapers.getItems().clear();

    }



}
