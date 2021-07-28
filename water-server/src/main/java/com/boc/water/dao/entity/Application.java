package com.boc.water.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class Application implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int item_id;
    private String title;
    private String detail;
    private Date submit_time;
    private Date operate_time;
    private double amount;
    private int apply_account_type;
    private String apply_account_no;
    private String receipt_account_no;
    private String payee;
    private int status;

    public Application(){}

    public Application(int id, int item_id, String title, String detail, Date submit_time,
                       Date operate_time, double amount, int apply_account_type,
                       String apply_account_no, String receipt_account_no, String payee, int status) {
        this.id = id;
        this.item_id = item_id;
        this.title = title;
        this.detail = detail;
        this.submit_time = submit_time;
        this.operate_time = operate_time;
        this.amount = amount;
        this.apply_account_type = apply_account_type;
        this.apply_account_no = apply_account_no;
        this.receipt_account_no = receipt_account_no;
        this.payee = payee;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }

    public Date getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(Date operate_time) {
        this.operate_time = operate_time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getApply_account_type() {
        return apply_account_type;
    }

    public void setApply_account_type(int apply_account_type) {
        this.apply_account_type = apply_account_type;
    }

    public String getApply_account_no() {
        return apply_account_no;
    }

    public void setApply_account_no(String apply_account_no) {
        this.apply_account_no = apply_account_no;
    }

    public String getReceipt_account_no() {
        return receipt_account_no;
    }

    public void setReceipt_account_no(String receipt_account_no) {
        this.receipt_account_no = receipt_account_no;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
