package com.example.javaproject.hawkers;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.javaproject.jdbcc.MySqlDBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class HawkersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboAllAreas;

    @FXML
    private ComboBox<String> ComboHawkerId;

    @FXML
    private DatePicker doj;

    @FXML
    private ImageView prev;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSelArea;

    @FXML
    void doAddinSelectedArea(ActionEvent event) {
        String selectedArea = ComboAllAreas.getSelectionModel().getSelectedItem();
        String currentText = txtSelArea.getText().trim();

        if (!currentText.isEmpty()) {
            txtSelArea.setText(currentText + ", " + selectedArea);
        } else {
            txtSelArea.setText(selectedArea);
        }

    }

    String Picpath;
    @FXML
    void doBrowse(ActionEvent event) {
        FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.jpg","*.png") );
        File file= chooser.showOpenDialog(null);
        Picpath=file.getAbsolutePath();
        System.out.println(Picpath);

        try {

            prev.setImage(new Image(new FileInputStream(file)));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void doClear(ActionEvent event) {
        doclear();
    }

    @FXML
    void doDelete(ActionEvent event) {
        String query = "delete from hawkers where hawkerid=?";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1,ComboHawkerId.getValue());
            int rowchanged = pst.executeUpdate();
            if(rowchanged>0)
            {
                System.out.println("Record Deleted");
                showMsg("Record Deleted Successfully");
                ComboHawkerId.getItems().remove(ComboHawkerId.getSelectionModel().getSelectedItem());
                ComboHawkerId.getSelectionModel().clearSelection();
            }
            else {
                System.out.println("no record found");
                showMsg("No Record Found");
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }
        doclear();

    }

    @FXML
    void doFetch(ActionEvent event) {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from hawkers where hawkerid=?");
            stmt.setString(1,ComboHawkerId.getValue());
            ResultSet res= stmt.executeQuery();
            if(res.next()==true)
            {
                String name = res.getString("Hawkername");
                String contact=res.getString("contact");
                String address=res.getString("address");
                String email=res.getString("email");
                Date d = res.getDate("doj");
                String pp = res.getString("picpath");
                String sarea = res.getString("selareas");

                txtName.setText(name);
                txtContact.setText(contact);
                txtAddress.setText(address);
                txtEmail.setText(email);
                doj.setValue(d.toLocalDate());
                prev.setImage(new Image(new FileInputStream(new File(pp))));
                txtSelArea.setText(sarea);
            }
            else
                System.out.println("Invalid ID");

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }

    }

    @FXML
    void doModify(ActionEvent event) {
        String query = "update hawkers set Hawkername=?, contact=?, address=?, email=?, doj=?, picpath=?, selareas=? where hawkerid=?";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1,txtName.getText());
            pst.setString(2, txtContact.getText());
            pst.setString(3,txtAddress.getText());
            pst.setString(4,txtEmail.getText());
            LocalDate lcl = doj.getValue();
            java.sql.Date dt= java.sql.Date.valueOf(lcl);
            pst.setDate(5,dt);
            pst.setString(6,Picpath);
            pst.setString(7,txtSelArea.getText());

            pst.setString(8,ComboHawkerId.getSelectionModel().getSelectedItem());


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
        doclear();



    }

    @FXML
    void doRecruit(ActionEvent event) {
        String query = "insert into hawkers values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1,txtName.getText());
            pst.setString(2, txtContact.getText());
            String name = txtName.getText().trim();
            String contact = txtContact.getText().trim();
            String namePart = name.length() >= 5 ? name.substring(0, 5) : name;
            String contactPart = contact.length() >= 5 ? contact.substring(contact.length() - 5) : contact;

            String hawkerId = namePart + contactPart;

            pst.setString(3,hawkerId);
            pst.setString(4,txtAddress.getText());
            pst.setString(5,txtEmail.getText());

            LocalDate lcl = doj.getValue();
            java.sql.Date dt= java.sql.Date.valueOf(lcl);
            pst.setDate(6,dt);

            pst.setString(7,Picpath);
            pst.setString(8,txtSelArea.getText());
            pst.executeUpdate();
            System.out.println("Record Saved");
            ComboHawkerId.getItems().add(hawkerId);
            showMsg("Record Saved Successfully");

        }catch (Exception e) {
            System.out.println(e.toString());
        }
        doclear();


    }
    void doclear()
    {
        txtName.clear();
        txtContact.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtSelArea.clear();
        doj.setValue(LocalDate.now());
        ComboHawkerId.getSelectionModel().clearSelection();
        ComboAllAreas.getSelectionModel().clearSelection();
        prev.setImage(temp);
    }
    void showMsg(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");


        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    Connection con;
    Image temp;

    @FXML
    void initialize() {
        con = MySqlDBConnection.getMySqlDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }
        ArrayList<String> artLst=getAreas();
        for(String s : artLst)
        {
            ComboAllAreas.getItems().add(s);
        }

        ArrayList<String> Hid = getHawkerIDs();
        for(String s : Hid)
        {
            ComboHawkerId.getItems().add(s);
        }
        doj.setValue(LocalDate.now());
        temp = prev.getImage();
    }
    ArrayList<String> getAreas()
    {
        ArrayList<String> areas=new ArrayList<String>();

        try
        {
            PreparedStatement stmt = con.prepareStatement("select distinct Areaname from areamaster");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String areaname=res.getString("Areaname");
                areas.add(String.valueOf(areaname));

            }
            System.out.println(areas);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return areas;
    }
    ArrayList<String> getHawkerIDs()
    {
        ArrayList<String> Hid=new ArrayList<String>();

        try
        {
            PreparedStatement stmt = con.prepareStatement("select distinct hawkerid from hawkers");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String hawkerid=res.getString("hawkerid");
                Hid.add(String.valueOf(hawkerid));

            }
            System.out.println(Hid);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return Hid;
    }


}
