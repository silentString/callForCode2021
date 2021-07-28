package com.boc.water.service.impl;

import com.boc.water.dao.entity.Item;
import com.boc.water.dao.mapper.ItemMapper;
import com.boc.water.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Item getItem(int id) {
        Item item;
        try {
            item = itemMapper.getItem(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return item;
    }

    @Override
    public int updateAva(int type, double diff) {
        int ret;
        try {
            ret = itemMapper.updateAva(type, diff);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return ret;
    }
}
