package com.boc.water.controller;

import com.boc.water.common.Constants;
import com.boc.water.common.Msg;
import com.boc.water.dao.dto.ApplicationDto;
import com.boc.water.dao.entity.Application;
import com.boc.water.service.AppItemsService;
import com.boc.water.service.ApplicationService;
import com.boc.water.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private AppItemsService appItemsService;

    @Autowired
    private ItemService itemService;

    @PostMapping("/search/application")
    public Msg getApps(@RequestParam("item_id")int item_id){
        List<ApplicationDto> apps = applicationService.getApplication(item_id);
        if(apps == null){
            return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "获取审批列表失败", null);
        }
        return Msg.getMsg(Constants.SUCESS_RET_CODE, "获取审批列表成功", apps);
    }

    @PostMapping("/add/application")
    public Msg addApp(@RequestParam("title")String title, @RequestParam("detail") String detail,
                      @RequestParam("amount")double amount,
                      @RequestParam("apply_account_type")int apply_account_type,
                      @RequestParam("apply_account_no")String apply_account_no,
                      @RequestParam("receipt_account_no")String receipt_account_no,
                      @RequestParam("payee")String payee){
        Application app = new Application();
        int ret = applicationService.addApplication(app, title, detail, amount, apply_account_type,
                apply_account_no, receipt_account_no, payee);
        if(ret == -1){
            return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "新增申请出错", null);
        } else if (ret == 0){
            return Msg.getMsg(Constants.FAIL_RET_CODE, "新增申请失败", null);
        }else {
            return Msg.getMsg(Constants.SUCESS_RET_CODE, "新增申请成功", app.getId());
        }
    }

    @PostMapping("/recall/application")
    @Transactional
    public Msg recall(@RequestParam("application_id")int application_id){
        int ret = applicationService.recall(application_id);
        if(ret == -1){
            return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "撤回申请出错", null);
        } else if (ret == 0){
            return Msg.getMsg(Constants.FAIL_RET_CODE, "撤回申请失败", null);
        }else {
            return Msg.getMsg(Constants.SUCESS_RET_CODE, "撤回申请成功", null);
        }
    }

    @PostMapping("/approve")
    @Transactional
    public Msg approve(@RequestParam("application_id")int application_id,
                       @RequestParam("application_title")String application_title,
                       @RequestParam("acc_type")int acc_type, @RequestParam("amount")double amount){
        int aprv = appItemsService.getApproveRet(application_title);
        if(aprv == -1){
            return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "审批申请出错", null);
        }
        int ret = applicationService.approve(application_id, aprv==0?3:2);
        if(ret != 1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "审批申请出错", null);
        }
        if(aprv == 1){
            int update = itemService.updateAva(acc_type, 0-amount);
            if(update != 1){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "审批申请出错", null);
            }
        }
        Application app = applicationService.getAppDetail(application_id);
        if(app == null){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "审批申请出错", null);
        }
        return Msg.getMsg(Constants.SUCESS_RET_CODE, "审批已结束", app);
    }

    @PostMapping("/application/detail")
    public Msg appDetail(@RequestParam("app_id")int app_id){
        Application app = applicationService.getAppDetail(app_id);
        if(app == null){
            return Msg.getMsg(Constants.INNER_ERROR_RET_CODE, "查询详情失败", null);
        }
        return Msg.getMsg(Constants.SUCESS_RET_CODE, "查询详情成功", app);
    }
}
