package com.mahindra.pmrshop.models;

public class ComplaintModel {

    private String serviceType;

    public ComplaintModel(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
