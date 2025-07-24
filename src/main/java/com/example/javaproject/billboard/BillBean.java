package com.example.javaproject.billboard;

public class BillBean {
    String mobilenumber,dos,days,bill;

    public BillBean(String mobilenumber, String dos, String days, String bill) {
        this.mobilenumber = mobilenumber;
        this.dos = dos;
        this.days = days;
        this.bill = bill;
    }


    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
