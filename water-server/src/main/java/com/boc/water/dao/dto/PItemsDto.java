package com.boc.water.dao.dto;

import java.io.Serializable;

public class PItemsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    public PItemsDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
