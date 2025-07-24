package com.example.javaproject.papermaster;

import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class PaperMasterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboId;

    @FXML
    private TextField txtLangauge;

    @FXML
    private TextField txtPrice;

    @FXML
    void doDelete(ActionEvent event) {
        String query = "delete from papermaster where Papertitle=? and Paperlanguage=?";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1,ComboId.getValue().toLowerCase().trim());
            pst.setString(2,txtLangauge.getText().toLowerCase().trim());
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
        doclear();

    }

    @FXML
    void doFind(ActionEvent event) {
        try {
            PreparedStatement stmt = con.prepareStatement("select price from papermaster where Papertitle = ? and Paperlanguage = ?");
            stmt.setString(1,ComboId.getValue().toLowerCase().trim());
            stmt.setString(2,txtLangauge.getText().toLowerCase().trim());
            ResultSet res= stmt.executeQuery();
            if(res.next()==true)
            {
                Float prc=res.getFloat("price");
                txtPrice.setText(String.valueOf(prc));
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
    void doNew(ActionEvent event) {
        doclear();

    }
    void showMsg(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");


        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    @FXML
    void doSave(ActionEvent event) {
        String query = "insert into papermaster values(?,?,?)";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1, ComboId.getValue().toLowerCase().trim());
            pst.setString(2, txtLangauge.getText().toLowerCase().trim());
            pst.setFloat(3,Float.parseFloat(txtPrice.getText()));
            pst.executeUpdate();
            System.out.println("Record Saved");
            showMsg("Data Saved Successfully");
        }catch (Exception e)
        {
            System.out.println(e.toString());
            showMsg("This Combination already exists");
        }

        doclear();
    }

    @FXML
    void doUpdate(ActionEvent event) {
        String query = "update papermaster set price=? where Papertitle=? and Paperlanguage=?";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setFloat(1,Float.parseFloat(txtPrice.getText()));
            pst.setString(2, ComboId.getValue().toLowerCase().trim());
            pst.setString(3,txtLangauge.getText().toLowerCase().trim());
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

    Connection con;
    @FXML
    void initialize() {
        con = MySqlDBConnection.getMySqlDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }
        ArrayList<String> artLst=getPapers();
        for(String s : artLst)
        {
            ComboId.getItems().add(s);
        }

    }
    ArrayList<String> getPapers()
    {
        ArrayList<String> papers=new ArrayList<String>();

        try
        {
            PreparedStatement stmt = con.prepareStatement("select distinct Papertitle from papermaster");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String papertitle=res.getString("Papertitle");
                papers.add(String.valueOf(papertitle));

            }
            System.out.println(papers);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return papers;
    }
    void doclear()
    {
        ComboId.getItems().clear();
        ComboId.setValue("");
        txtPrice.clear();
        txtLangauge.clear();
        ArrayList<String> artLst = getPapers();
        for(String s : artLst)
        {
            ComboId.getItems().add(s);
        }

    }

}
