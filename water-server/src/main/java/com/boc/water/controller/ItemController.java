package com.boc.water.controller;

import com.boc.water.common.Constants;
import com.boc.water.common.Msg;
import com.boc.water.dao.dto.AccountDto;
import com.boc.water.dao.dto.ItemDto;
import com.boc.water.dao.entity.Item;
import com.boc.water.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/search/item")
    public Msg getItem(@RequestParam("item_id") int id){
        Item item = itemService.getItem(id);
        if(item == null){
            return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "查询项目信息失败", null);
        }
        ItemDto dto = new ItemDto(id, item.getTitle(), item.getTotal_amount(),
                item.getDonate_ava_amount()+item.getLoan_ava_amount(),
                item.getStatus());
        return Msg.getMsg(Constants.SUCESS_RET_CODE, "查询项目信息成功", dto);
    }

    @PostMapping("/search/account")
    public Msg getAcc(@RequestParam("item_id") int id){
        Item item = itemService.getItem(id);
        if(item == null){
            return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "查询项目信息失败", null);
        }
        AccountDto dto = new AccountDto(id, item.getLoan_account(), item.getDonate_account(),
                item.getLoan_ava_amount(), item.getDonate_ava_amount());
        return Msg.getMsg(Constants.SUCESS_RET_CODE, "查询账户信息成功", dto);
    }
}
