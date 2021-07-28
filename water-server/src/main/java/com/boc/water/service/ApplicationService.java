package com.boc.water.service;

import com.boc.water.dao.dto.ApplicationDto;
import com.boc.water.dao.entity.Application;

import java.util.List;

public interface ApplicationService {

    List<ApplicationDto> getApplication(int item_id);

    int addApplication(Application app, String title, String detail, double amount,
                       int apply_account_type, String apply_account_no,
                       String receipt_account_no, String payee);

    Application getApp(int application_id);

    int recall(int application_id);

    int approve(int application_id, int r);

    List<Application> getSubmitApps();

    Application getAppDetail(int app_id);
}
