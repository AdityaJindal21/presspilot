package com.example.javaproject.billboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.javaproject.jdbcc.MySqlDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> billstatus;

    @FXML
    private DatePicker enddate;

    @FXML
    private DatePicker startdate;

    @FXML
    private TableView<BillBean> tablevieu;

    @FXML
    private TextField txtAmt;
    void showMsg(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information Dialog");


        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    @FXML
    void doExport(ActionEvent event) {
        try{
            writeExcel();
            System.out.println("Exported");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void writeExcel() throws Exception
    {
        Writer writer = null;
        try{
            File file  = new File("BillBoard.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text = "mobilenumber,dos,days.bill\n";
            writer.write(text);
            for(BillBean b:getArrayofObjects())
            {
                text = b.getMobilenumber()+","+b.getDos()+","+b.getDays()+","+b.getBill()+"\n";
                writer.write(text);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    @FXML
    void doFind(ActionEvent event) {
        if(startdate.getValue()==null || enddate.getValue()==null || billstatus.getValue()==null || Objects.equals(billstatus.getValue(), "Select"))
        {
            showMsg("Please Fill all the fields");
            return;
        }


        TableColumn<BillBean, String> mobC = new TableColumn<BillBean, String>("Customer Mobile No.");
        mobC.setCellValueFactory(new PropertyValueFactory<BillBean, String>("mobilenumber"));
        mobC.setMinWidth(100);

        TableColumn<BillBean, String> dosC = new TableColumn<BillBean, String>("Start Date");
        dosC.setCellValueFactory(new PropertyValueFactory<BillBean, String>("dos"));
        dosC.setMinWidth(100);

        TableColumn<BillBean, String> daysC = new TableColumn<BillBean, String>("Billing Days");
        daysC.setCellValueFactory(new PropertyValueFactory<BillBean, String>("days"));
        daysC.setMinWidth(100);

        TableColumn<BillBean, String> billC = new TableColumn<BillBean, String>("Bill ");
        billC.setCellValueFactory(new PropertyValueFactory<BillBean, String>("bill"));
        billC.setMinWidth(100);

        tablevieu.getColumns().clear();

        tablevieu.getColumns().addAll(mobC,dosC,daysC,billC);

        tablevieu.setItems(null);
        tablevieu.setItems(getArrayofObjects());



    }

    ObservableList<BillBean> getArrayofObjects()
    {
        ObservableList<BillBean> lst= FXCollections.observableArrayList();
        float totbill = 0;
        try{
            LocalDate dt = startdate.getValue();
            java.sql.Date ddt = java.sql.Date.valueOf(dt);

            LocalDate dt1 = enddate.getValue();
            java.sql.Date ddt1 = java.sql.Date.valueOf(dt1);
            PreparedStatement pst;
            ResultSet rst;
            String qry;
            if(billstatus.getValue().equals("All Bills"))
               qry="select * from billing where dos between ? and ?";

            else if(billstatus.getValue().equals("Paid Bills"))
                qry = "select * from billing where dos between ? and ? and statuss=0";
            else
                qry = "select * from billing where dos between ? and ? and statuss=1";

            pst = con.prepareStatement(qry);
            pst.setDate(1,ddt);
            pst.setDate(2,ddt1);
            rst = pst.executeQuery();


            while(rst.next())
            {
                String rid = String.valueOf(rst.getInt("rid"));
                String Mobno = rst.getString("mobilenumber");
                Date dtt = rst.getDate("dos");
                String days = String.valueOf(rst.getInt("days"));
                float billing = rst.getFloat("bill");
                totbill += billing;
                String bill = String.valueOf(billing);

                BillBean bbn = new BillBean(Mobno,dtt.toString(),days,bill);
                lst.add(bbn);
            }

            txtAmt.setText(String.valueOf(totbill));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
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

        billstatus.getItems().add("Select");
        billstatus.getItems().add("All Bills");
        billstatus.getItems().add("Paid Bills");
        billstatus.getItems().add("Unpaid Bills");

        billstatus.getSelectionModel().select(0);



    }

}
