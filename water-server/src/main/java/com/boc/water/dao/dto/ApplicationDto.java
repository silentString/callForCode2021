package com.boc.water.dao.dto;

import java.io.Serializable;
import java.util.Date;

public class ApplicationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int item_id;
    private int application_id;
    private String title;
    private Date date;
    private double amount;
    private String status;

    public ApplicationDto(int item_id, int application_id, String title, Date date,
                          double amount, String status) {
        this.item_id = item_id;
        this.application_id = application_id;
        this.title = title;
        this.date = date;
        this.amount = amount;
        this.status = status;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
