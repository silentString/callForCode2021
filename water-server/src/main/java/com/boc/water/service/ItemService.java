package com.boc.water.service;

import com.boc.water.dao.entity.Item;

public interface ItemService {

    Item getItem(int id);

    int updateAva(int type, double diff);
}
