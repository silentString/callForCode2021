package com.boc.callforcode.water.verify;


import com.boc.callforcode.ledgerapi.State;
import com.boc.callforcode.water.application.WaterFundApplicationBase;
import com.boc.callforcode.water.project.WaterProjectBase;
import com.boc.callforcode.water.project.WaterProjectContext;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;

import java.util.logging.Logger;

@Contract(name = "com.boc.water.AutomaticTransferContract", info = @Info(title = "Verity material contract", description = "", version = "0.0.1", license = @License(name = "SPDX-License-Identifier: Apache-2.0", url = ""), contact = @Contact(name = "java-contract", url = "http://java-contract.lrn")))
public class AutomaticTransferContract implements ContractInterface {
    // use the classname for the logger, this way you can refactor
    private final static Logger LOG = Logger.getLogger(AutomaticTransferContract.class.getName());



    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public String[] getMaterial(WaterProjectContext proCtx, String projectNumber,
                                String applicationNumber) {

        System.out.println(proCtx);
        String projectKey = State.makeKey(new String[]{projectNumber});
        WaterProjectBase project = proCtx.projectList.getWaterProjectBase(projectKey);

        String[] proofMaterias = new String[2];
        proofMaterias[0] = project.getEnterprise(applicationNumber).getApplicationUnit().getProofMateriasUrl();
        proofMaterias[1] = project.getEnterprise(applicationNumber).getApplicationUnit().getProofMateriasHash();
        return proofMaterias;
    }

    @Transaction
    public WaterFundApplicationBase SetVerifyValue(WaterProjectContext proCtx, String projectNumber,
                                                   String applicationNumber,
                                                   boolean isVerifyMaterialAvailable) {



        System.out.println(proCtx);
        String projectKey = State.makeKey(new String[]{projectNumber});
        WaterProjectBase project = proCtx.projectList.getWaterProjectBase(projectKey);

        project.getEnterprise(applicationNumber).setIsVerifyMaterialAvailable(isVerifyMaterialAvailable);
        proCtx.projectList.updateWaterProjectBase(project);
        return project.getEnterprise(applicationNumber);
    }

}
