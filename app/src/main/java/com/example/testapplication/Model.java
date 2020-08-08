package com.example.testapplication;

public class Model {

    String name;
    String mobile;
    String companyName;



    public Model(String name, String mobile, String companyName) {
        this.name = name;
        this.mobile = mobile;
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }





}
