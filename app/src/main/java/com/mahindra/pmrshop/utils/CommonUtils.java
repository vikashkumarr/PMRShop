package com.mahindra.pmrshop.utils;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    static CommonUtils commonUtils;

    public List<String> getSelectedComplaintList() {
        return selectedComplaintList;
    }

    public void setSelectedComplaintList(List<String> selectedComplaintList) {
        this.selectedComplaintList = selectedComplaintList;
    }

    List<String> selectedComplaintList = new ArrayList<>();
    String name ;

    public String getName() {
        return name;
    }

    public static CommonUtils getInstance() {
        if (commonUtils == null)
            commonUtils = new CommonUtils();
        return commonUtils;
    }

}
