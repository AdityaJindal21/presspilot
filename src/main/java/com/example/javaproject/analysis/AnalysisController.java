package com.example.javaproject.analysis;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.example.javaproject.jdbcc.MySqlDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class AnalysisController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<String, Number> linechartid;

    @FXML
    private PieChart PieChart;

    Connection con;
    @FXML
    void initialize() {
        con = MySqlDBConnection.getMySqlDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }
        getCustomerChart();
        getPiechart();
    }

    void getCustomerChart()
    {
        try{
            PreparedStatement stmt = con.prepareStatement("select area, COUNT(*) as 'count' from customers group by area");
            ResultSet rst = stmt.executeQuery();

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Customers by Area");

            while(rst.next())
            {
                String area = rst.getString("area");
                int count = rst.getInt("count");

                series.getData().add(new XYChart.Data<>(area, count));
            }
            linechartid.getData().clear();
            linechartid.getData().add(series);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getPiechart()
    {
        ObservableList<PieChart.Data> list= FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT Papertitle, price FROM papermaster");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String title = res.getString("Papertitle");
                float price = res.getFloat("price");
                PieChart.Data ref=new PieChart.Data(title,price);
                list.add(ref);

            }
            PieChart.setData(list);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
    }
}
