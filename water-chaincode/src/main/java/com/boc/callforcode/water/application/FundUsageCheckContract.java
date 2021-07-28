package com.boc.callforcode.water.application;


import com.boc.callforcode.ledgerapi.State;
import com.boc.callforcode.water.project.WaterProjectBase;
import com.boc.callforcode.water.project.WaterProjectContext;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeStub;

import java.util.logging.Logger;

@Contract(name = "com.boc.water.FundUsageCheckContract", info = @Info(title = "Water Fund Application contract", description = "", version = "0.0.1", license = @License(name = "SPDX-License-Identifier: Apache-2.0", url = ""), contact = @Contact(name = "java-contract", url = "http://java-contract.lrn")))

public class FundUsageCheckContract implements ContractInterface{
    // use the classname for the logger, this way you can refactor
    private final static Logger LOG = Logger.getLogger(FundUsageCheckContract.class.getName());

    @Override
    public Context createContext(ChaincodeStub stub) {
        return new WaterFundApplicationContext(stub);
    }

    public FundUsageCheckContract() {
    }



    @Transaction
    public String publishAppliation(WaterFundApplicationContext ctx,
                                    WaterProjectContext proCtx, String projectNumber,
                                    String applicationNumber, String applicationUseOfFunds, String applicationDateTime,
                                    String enterpriseNumber, String enterpriseName, String enterpriseFundAccount, String proofMateriasUrl, String proofMateriasHash, Double applicationAmount, String trustedRecord) {

        System.out.println(ctx);
        String apptKey = State.makeKey(new String[] { applicationNumber });
        WaterFundApplicationBase app = ctx.applicationList.getWaterFundAppliactionBase(apptKey);
        WaterFundApplicationBase waterFundApplicationBase = WaterFundApplicationBase.createInstance(applicationNumber,  projectNumber,  applicationUseOfFunds,  applicationDateTime, false,
                enterpriseNumber,  enterpriseName,  enterpriseFundAccount,  proofMateriasUrl,  proofMateriasHash,  applicationAmount, trustedRecord);
        ctx.applicationList.updateWaterProjectBase(app);


        System.out.println(proCtx);
        String projectKey = State.makeKey(new String[] { projectNumber });
        WaterProjectBase project = proCtx.projectList.getWaterProjectBase(projectKey);
        project.addWaterFundApplicationBase(applicationNumber,waterFundApplicationBase);
        proCtx.projectList.updateWaterProjectBase(project);

//        return waterFundApplicationBase;
        String tansId =  proCtx.getStub().getTxId();
        return tansId;
    }



//    @Transaction
//    public String getHashValueOfApplication(WaterFundApplicationContext ctx, String applicationNumber){
//        System.out.println(ctx);
//        String apptKey = State.makeKey(new String[] { applicationNumber });
//        WaterFundApplicationBase app = ctx.applicationList.getWaterFundAppliactionBase(apptKey);
//    }


    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public String getInfoOfApplication(WaterFundApplicationContext ctx, String applicationNumber){
//        System.out.println(ctx);
//        String apptKey = State.makeKey(new String[] { applicationNumber });
//        WaterFundApplicationBase app = ctx.applicationList.getWaterFundAppliactionBase(apptKey);
//       return app.toString();
        return 123 + "";
    }
}
