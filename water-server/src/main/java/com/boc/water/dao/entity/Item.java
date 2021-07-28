package com.boc.water.dao.entity;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private double total_amount;
    private double loan_ava_amount;
    private double donate_ava_amount;
    private String loan_account;
    private String donate_account;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getLoan_ava_amount() {
        return loan_ava_amount;
    }

    public void setLoan_ava_amount(double loan_ava_amount) {
        this.loan_ava_amount = loan_ava_amount;
    }

    public double getDonate_ava_amount() {
        return donate_ava_amount;
    }

    public void setDonate_ava_amount(double donate_ava_amount) {
        this.donate_ava_amount = donate_ava_amount;
    }

    public String getLoan_account() {
        return loan_account;
    }

    public void setLoan_account(String loan_account) {
        this.loan_account = loan_account;
    }

    public String getDonate_account() {
        return donate_account;
    }

    public void setDonate_account(String donate_account) {
        this.donate_account = donate_account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
