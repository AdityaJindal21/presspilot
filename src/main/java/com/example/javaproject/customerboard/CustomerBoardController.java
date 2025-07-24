package com.example.javaproject.customerboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.javaproject.billcollect.BillCollectBean;
import com.example.javaproject.jdbcc.MySqlDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.transform.Result;

public class CustomerBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboArea;

    @FXML
    private ComboBox<String> ComboHawker;

    @FXML
    private ComboBox<String> ComboPaper;

    @FXML
    private TableView<CustomerBoardBean> tbleview;

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
            File file  = new File("CustomerBoard.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text = "mobile,cname,emailid,address,dos\n";
            writer.write(text);
            for(CustomerBoardBean b:getArrayofObjects())
            {
                text = b.getMobile()+","+b.getCname()+","+b.getEmailid()+","+b.getAddress()+","+b.getDos()+"\n";
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
    void doshowData(ActionEvent event) {
        TableColumn<CustomerBoardBean, String> mobC = new TableColumn<CustomerBoardBean, String>("Mobile Number");
        mobC.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean, String>("mobile"));
        mobC.setMinWidth(100);

        TableColumn<CustomerBoardBean, String> nameC = new TableColumn<CustomerBoardBean, String>("Customer Name");
        nameC.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean, String>("cname"));
        nameC.setMinWidth(100);

        TableColumn<CustomerBoardBean, String> emailC = new TableColumn<CustomerBoardBean, String>("Email ID");
        emailC.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean, String>("emailid"));
        emailC.setMinWidth(100);

        TableColumn<CustomerBoardBean, String> addressC = new TableColumn<CustomerBoardBean, String>("Address");
        addressC.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean, String>("address"));
        addressC.setMinWidth(100);

        TableColumn<CustomerBoardBean, String> dosC = new TableColumn<CustomerBoardBean, String>("Start Date");
        dosC.setCellValueFactory(new PropertyValueFactory<CustomerBoardBean, String>("dos"));
        dosC.setMinWidth(100);

        tbleview.getColumns().clear();

        tbleview.getColumns().addAll(mobC,nameC,emailC,addressC,dosC);

        tbleview.setItems(null);
        tbleview.setItems(getArrayofObjects());

    }

    ObservableList<CustomerBoardBean> getArrayofObjects()
    {
        ObservableList<CustomerBoardBean> lst = FXCollections.observableArrayList();
        PreparedStatement pst = null;
        ResultSet rst = null;
        try{
            if(ComboArea.getValue().equals("None") &&
                    ComboHawker.getValue().equals("None") &&
                    ComboPaper.getValue().equals("None"))
            {
                pst = con.prepareStatement("select * from customers");
            }
            else if(!ComboArea.getValue().equals("None") &&
                    ComboHawker.getValue().equals("None") &&
                    ComboPaper.getValue().equals("None"))
            {
                pst = con.prepareStatement("select * from customers where area=?");
                pst.setString(1,ComboArea.getValue());
            }
            else if(ComboArea.getValue().equals("None") &&
                    ComboHawker.getValue().equals("None") &&
                    !ComboPaper.getValue().equals("None"))
            {
                pst = con.prepareStatement("select * from customers where papers like ?");
                pst.setString(1,"%"+ComboPaper.getValue()+"%");
            }
            else if(ComboArea.getValue().equals("None") &&
                    !ComboHawker.getValue().equals("None") &&
                    ComboPaper.getValue().equals("None"))
            {
                pst = con.prepareStatement("select * from customers where hawkerid=?");
                pst.setString(1,ComboHawker.getValue());
            }
            else if(!ComboArea.getValue().equals("None") &&
                    ComboHawker.getValue().equals("None") &&
                    !ComboPaper.getValue().equals("None"))
            {
                pst = con.prepareStatement("select * from customers where area=? and papers like ?");
                pst.setString(1,ComboArea.getValue());
                pst.setString(2, "%"+ComboPaper.getValue()+"%");
            }
            else if(!ComboArea.getValue().equals("None") &&
                    !ComboHawker.getValue().equals("None") &&
                    ComboPaper.getValue().equals("None"))
            {
                pst = con.prepareStatement("select * from customers where area=? and hawkerid=?");
                pst.setString(1,ComboArea.getValue());
                pst.setString(2, ComboHawker.getValue());
            }
            else if(ComboArea.getValue().equals("None") &&
                    !ComboHawker.getValue().equals("None") &&
                    !ComboPaper.getValue().equals("None"))
            {
                pst = con.prepareStatement("select * from customers where hawkerid=? and papers like ?");
                pst.setString(1,ComboHawker.getValue());
                pst.setString(2, "%"+ComboPaper.getValue()+"%");
            }
            else if(!ComboArea.getValue().equals("None") &&
                    !ComboHawker.getValue().equals("None") &&
                    !ComboPaper.getValue().equals("None"))
            {
                pst = con.prepareStatement("select * from customers where area=? and hawkerid=? and papers like ?");
                pst.setString(1,ComboArea.getValue());
                pst.setString(2,ComboHawker.getValue());
                pst.setString(3, "%"+ComboPaper.getValue()+"%");
            }
            if(pst!=null)
                rst = pst.executeQuery();
            if(rst!=null) {
                while (rst.next()) {
                    String mob = rst.getString("mobile");
                    String name = rst.getString("cname");
                    String email = rst.getString("emailid");
                    String address = rst.getString("address");
                    Date dos = rst.getDate("dos");
                    CustomerBoardBean cbb = new CustomerBoardBean(mob,name,email,address,dos.toString());
                    lst.add(cbb);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    Connection con;
    ArrayList<String> Papers;
    ArrayList<String> Areas;
    ArrayList<String> Hawkers;
    @FXML
    void initialize() {
        con = MySqlDBConnection.getMySqlDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }
        Papers = new ArrayList<>();
        Areas = new ArrayList<>();
        Hawkers = new ArrayList<>();
        getAreas();
        getPapers();
        getHawkers();
        ComboPaper.getItems().add("None");
        ComboHawker.getItems().add("None");
        ComboArea.getItems().add("None");
        for(String s:Papers)
        {
            ComboPaper.getItems().add(s);
        }
        for(String s:Areas)
        {
            ComboArea.getItems().add(s);
        }
        for(String s:Hawkers)
        {
            ComboHawker.getItems().add(s);
        }

    }

    void getPapers()
    {
        try{
            PreparedStatement pst = con.prepareStatement("select Papertitle from papermaster");
            ResultSet rst = pst.executeQuery();
            while(rst.next())
            {
                String papertitle = rst.getString("Papertitle");
                Papers.add(papertitle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void getAreas()
    {
        try{
            PreparedStatement pst = con.prepareStatement("select Areaname from areamaster");
            ResultSet rst = pst.executeQuery();
            while(rst.next())
            {
                String areaname = rst.getString("Areaname");
                Areas.add(areaname);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void getHawkers()
    {
        try{
            PreparedStatement pst = con.prepareStatement("select hawkerid from hawkers");
            ResultSet rst = pst.executeQuery();
            while(rst.next())
            {
                String hawkerid = rst.getString("hawkerid");
                Hawkers.add(hawkerid);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
