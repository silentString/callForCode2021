package com.boc.water.dao.mapper;

import com.boc.water.dao.entity.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMapper{

    public Item getItem(int id);

    public int updateAva(int type, double diff);
}
