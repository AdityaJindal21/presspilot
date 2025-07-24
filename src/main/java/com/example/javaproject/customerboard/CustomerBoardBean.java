package com.example.javaproject.customerboard;

public class CustomerBoardBean {
    String mobile,cname,emailid,address,dos;

    public CustomerBoardBean(String mobile, String cname, String emailid, String address, String dos) {
        this.mobile = mobile;
        this.cname = cname;
        this.emailid = emailid;
        this.address = address;
        this.dos = dos;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }
}
