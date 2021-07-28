package com.boc.water.dao.dto;

import java.io.Serializable;

public class ItemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int item_id;
    private String title;
    private double total_amount;
    private double ava_amount;
    private String status;

    public ItemDto(int item_id, String title, double total_amount, double ava_amount,
                   String status) {
        this.item_id = item_id;
        this.title = title;
        this.total_amount = total_amount;
        this.ava_amount = ava_amount;
        this.status = status;
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

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getAva_amount() {
        return ava_amount;
    }

    public void setAva_amount(double ava_amount) {
        this.ava_amount = ava_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
