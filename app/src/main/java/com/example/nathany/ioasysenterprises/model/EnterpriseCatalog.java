package com.example.nathany.ioasysenterprises.model;

import java.util.ArrayList;

public class EnterpriseCatalog {

    ArrayList<Enterprise> enterprises;

    public EnterpriseCatalog(ArrayList<Enterprise> enterprises) {
        this.enterprises = enterprises;
    }

    public ArrayList<Enterprise> getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(ArrayList<Enterprise> enterprises) {
        this.enterprises = enterprises;
    }
}
