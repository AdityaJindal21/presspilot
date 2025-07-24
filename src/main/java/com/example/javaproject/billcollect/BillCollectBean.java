package com.example.javaproject.billcollect;

public class BillCollectBean {
    String dos,doe,days,bill;

    public BillCollectBean(String dos, String doe, String days, String bill) {
        this.dos = dos;
        this.doe = doe;
        this.days = days;
        this.bill = bill;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getDoe() {
        return doe;
    }

    public void setDoe(String doe) {
        this.doe = doe;
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
