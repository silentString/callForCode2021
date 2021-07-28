package com.boc.callforcode.water.Credit;

import com.boc.callforcode.ledgerapi.State;
import com.boc.callforcode.water.application.WaterFundApplicationBase;
import com.boc.callforcode.water.project.WaterProjectBase;
import com.boc.callforcode.water.project.WaterProjectContext;
import com.boc.callforcode.water.verify.AutomaticTransferContract;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;

import java.util.logging.Logger;


@Contract(name = "com.boc.water.AutoMaticAlterContract", info = @Info(title = "record trust thing   contract", description = "", version = "0.0.1", license = @License(name = "SPDX-License-Identifier: Apache-2.0", url = ""), contact = @Contact(name = "java-contract", url = "http://java-contract.lrn")))
public class AutoMaticAlterContract implements ContractInterface {
    // use the classname for the logger, this way you can refactor
    private final static Logger LOG = Logger.getLogger(AutomaticTransferContract.class.getName());



    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public String getTrustedRecord(WaterProjectContext proCtx, String projectNumber,
                                   String applicationNumber){

        System.out.println(proCtx);
        String projectKey = State.makeKey(new String[]{projectNumber});
        WaterProjectBase project = proCtx.projectList.getWaterProjectBase(projectKey);

        String trusetRecord = project.getEnterprise(applicationNumber).getApplicationUnit().getTrustedRecord();
        return trusetRecord;
    }

    @Transaction
    public WaterFundApplicationBase setTrustedRecord(WaterProjectContext proCtx, String projectNumber,
                                                     String applicationNumber,
                                                     String trusetRecord) {



        System.out.println(proCtx);
        String projectKey = State.makeKey(new String[]{projectNumber});
        WaterProjectBase project = proCtx.projectList.getWaterProjectBase(projectKey);
        project.getEnterprise(applicationNumber).getApplicationUnit().setTrustedRecord(trusetRecord);
        proCtx.projectList.updateWaterProjectBase(project);
        return project.getEnterprise(applicationNumber);
    }

}