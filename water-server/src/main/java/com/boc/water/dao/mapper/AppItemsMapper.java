package com.boc.water.dao.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppItemsMapper {
    public List<String> getItemNames();

    public int getApproveRet(String name);
}
