package com.boc.water.dao.dto;

import java.io.Serializable;

public class AccountDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int item_id;
    private String loan_acc;
    private String donate_acc;
    private double loan;
    private double donate;

    public AccountDto(int item_id, String loan_acc, String donate_acc, double loan,
                      double donate) {
        this.item_id = item_id;
        this.loan_acc = loan_acc;
        this.donate_acc = donate_acc;
        this.loan = loan;
        this.donate = donate;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getLoan_acc() {
        return loan_acc;
    }

    public void setLoan_acc(String loan_acc) {
        this.loan_acc = loan_acc;
    }

    public String getDonate_acc() {
        return donate_acc;
    }

    public void setDonate_acc(String donate_acc) {
        this.donate_acc = donate_acc;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public double getDonate() {
        return donate;
    }

    public void setDonate(double donate) {
        this.donate = donate;
    }
}
