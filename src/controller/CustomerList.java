/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Gillian
 */
public class CustomerList {

    private String id;
    private String fname;
    private String lname;
    private String address;
    private String mobile_num;

    public CustomerList(String id, String fname, String lname, String address, String mobile_num) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.mobile_num = mobile_num;
    }

    public CustomerList(String fname, String lname, String address, String mobile_num) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.mobile_num = mobile_num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile_num() {
        return mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }

}
