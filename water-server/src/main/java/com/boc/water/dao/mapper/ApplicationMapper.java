package com.boc.water.dao.mapper;

import com.boc.water.dao.dto.ApplicationDto;
import com.boc.water.dao.entity.Application;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationMapper {

    public List<ApplicationDto> getApps(int item_id);

    public int addApplication(Application app, String title, String detail, double amount, int apply_account_type,
                              String apply_account_no, String receipt_account_no,
                              String payee);

    public Application getApp(int application_id);

    public int recall(int application_id);

    public int approve(int application_id, int ret);

    public List<Application> getSubmitApps();

    public Application getAppDetail(int app_id);
}
