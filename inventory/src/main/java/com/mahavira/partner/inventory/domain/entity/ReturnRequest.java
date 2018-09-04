package com.mahavira.partner.inventory.domain.entity;

import java.util.Map;

/**
 * Created by norman on 31/07/18.
 */

public class ReturnRequest {

    String id;
    String productName;
    String from;
    Map<String, Boolean> checklist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Map<String, Boolean> getChecklist() {
        return checklist;
    }

    public void setChecklist(Map<String, Boolean> checklist) {
        this.checklist = checklist;
    }
}
