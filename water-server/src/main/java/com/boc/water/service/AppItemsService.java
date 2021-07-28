package com.boc.water.service;

import java.util.List;

public interface AppItemsService {
    List<String> getItemNames();

    int getApproveRet(String name);
}
