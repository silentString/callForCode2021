package com.boc.water.service.impl;

import com.boc.water.dao.dto.ApplicationDto;
import com.boc.water.dao.entity.Application;
import com.boc.water.dao.mapper.ApplicationMapper;
import com.boc.water.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public List<ApplicationDto> getApplication(int item_id) {
        List<ApplicationDto> apps;
        try {
            apps = applicationMapper.getApps(item_id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return apps;
    }

    @Override
    public int addApplication(Application app, String title, String detail, double amount, int apply_account_type,
                              String apply_account_no, String receipt_account_no,
                              String payee) {
        int ret = 0;
        try {
            ret = applicationMapper.addApplication(app, title, detail, amount, apply_account_type,
                    apply_account_no, receipt_account_no, payee);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return ret;
    }

    @Override
    public Application getApp(int application_id) {
        Application app;
        try {
            app = applicationMapper.getApp(application_id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return app;
    }

    @Override
    public int recall(int application_id) {
        int ret;
        try {
            ret = applicationMapper.recall(application_id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return ret;
    }

    @Override
    public int approve(int application_id, int r) {
        int ret;
        try {
            ret = applicationMapper.approve(application_id, r);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return ret;
    }

    @Override
    public List<Application> getSubmitApps() {
        List<Application> apps;
        try {
            apps = applicationMapper.getSubmitApps();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return apps;
    }

    @Override
    public Application getAppDetail(int app_id) {
        Application app;
        try {
            app = applicationMapper.getAppDetail(app_id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return app;
    }
}
