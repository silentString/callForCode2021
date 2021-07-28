package com.boc.water.controller;

import com.boc.water.common.Constants;
import com.boc.water.common.Msg;
import com.boc.water.service.AppItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppItemsController {
    @Autowired
    private AppItemsService appItemsService;

    @GetMapping("/application/items")
    public Msg getItems(){
        List<String> items = appItemsService.getItemNames();
        if(items == null){
            return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "获取项目类别出错", null);
        }
        return Msg.getMsg(Constants.SUCESS_RET_CODE, "获取项目类别成功", items);
    }
}
