package com.boc.water.service.impl;

import com.boc.water.dao.mapper.AppItemsMapper;
import com.boc.water.service.AppItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppItemsServiceImpl implements AppItemsService {

    @Autowired
    private AppItemsMapper appItemsMapper;

    @Override
    public List<String> getItemNames() {
        List<String> items;
        try {
            items = appItemsMapper.getItemNames();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return items;
    }

    @Override
    public int getApproveRet(String name) {
        int ret;
        try {
            ret = appItemsMapper.getApproveRet(name);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return ret;
    }
}
