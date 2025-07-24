package com.example.javaproject.billcollect;

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
import java.util.ResourceBundle;


import com.example.javaproject.billboard.BillBean;
import com.example.javaproject.jdbcc.MySqlDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillCollectorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BillCollectBean> tablevu;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txtpendingAmt;
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
            File file  = new File("BillCollector.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text = "dos,doe,days.bill\n";
            writer.write(text);
            for(BillCollectBean b:getArrayofObjects())
            {
                text = b.getDos()+","+b.getDoe()+","+b.getDays()+","+b.getBill()+"\n";
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
    void dopay(ActionEvent event) {
        String query = "update billing set statuss = 0 where mobilenumber=?";
        try {
            PreparedStatement pst =  con.prepareStatement(query);
            pst.setString(1,txtmob.getText());
            int rowchanged = pst.executeUpdate();
            if(rowchanged>0)
            {
                System.out.println("Record Updated");
                showMsg("Bill Payed Successfully");
            }
            else {
                System.out.println("no record found");
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }

    @FXML
    void doshowpending(ActionEvent event) {
        if(txtmob.getText()==null || txtmob.getText().isEmpty()) {
            showMsg("Please Fill the Mobile number");
            return;
        }

        TableColumn<BillCollectBean, String> dosC = new TableColumn<BillCollectBean, String>("Start Date");
        dosC.setCellValueFactory(new PropertyValueFactory<BillCollectBean, String>("dos"));
        dosC.setMinWidth(100);

        TableColumn<BillCollectBean, String> doeC = new TableColumn<BillCollectBean, String>("End Date");
        doeC.setCellValueFactory(new PropertyValueFactory<BillCollectBean, String>("doe"));
        doeC.setMinWidth(100);

        TableColumn<BillCollectBean, String> daysC = new TableColumn<BillCollectBean, String>("Billing Days");
        daysC.setCellValueFactory(new PropertyValueFactory<BillCollectBean, String>("days"));
        daysC.setMinWidth(100);

        TableColumn<BillCollectBean, String> billC = new TableColumn<BillCollectBean, String>("Bill");
        billC.setCellValueFactory(new PropertyValueFactory<BillCollectBean, String>("bill"));
        billC.setMinWidth(100);

        tablevu.getColumns().clear();

        tablevu.getColumns().addAll(dosC,doeC,daysC,billC);

        tablevu.setItems(null);
        tablevu.setItems(getArrayofObjects());


    }

    ObservableList<BillCollectBean> getArrayofObjects()
    {
        ObservableList<BillCollectBean> lst = FXCollections.observableArrayList();
        float totbill = 0;
        try{
            PreparedStatement pst = con.prepareStatement("select * from billing where mobilenumber=? and statuss=1");
            pst.setString(1,txtmob.getText());
            ResultSet rst = pst.executeQuery();
            while(rst.next())
            {
                Date dos= rst.getDate("dos");
                Date doe= rst.getDate("doe");
                String days = String.valueOf(rst.getInt("days"));
                float billing = rst.getFloat("bill");
                totbill += billing;
                String bill = String.valueOf(billing);

                BillCollectBean bcb = new BillCollectBean(dos.toString(),doe.toString(),days,bill);
                lst.add(bcb);
            }

            txtpendingAmt.setText(String.valueOf(totbill));

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

    }

}
