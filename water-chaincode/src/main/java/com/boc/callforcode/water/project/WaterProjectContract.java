package com.boc.callforcode.water.project;


import com.boc.callforcode.ledgerapi.State;
import com.boc.callforcode.water.application.WaterFundApplicationBase;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeStub;

import java.util.logging.Logger;

import static com.boc.callforcode.water.application.WaterFundApplicationBase.AVAILABLE;
import static com.boc.callforcode.water.application.WaterFundApplicationBase.UNAVILABLE;
import static org.hyperledger.fabric.contract.annotation.Transaction.TYPE.EVALUATE;

@Contract(name = "com.boc.water.WaterProjectContract", info = @Info(title = "Water project contract", description = "", version = "0.0.1", license = @License(name = "SPDX-License-Identifier: Apache-2.0", url = ""), contact = @Contact(name = "java-contract", url = "http://java-contract.lrn")))
@Default
public class WaterProjectContract implements ContractInterface {
    // use the classname for the logger, this way you can refactor
    private final static Logger LOG = Logger.getLogger(WaterProjectContract.class.getName());

    @Override
    public Context createContext(ChaincodeStub stub) {
        return new WaterProjectContext(stub);
    }

    public WaterProjectContract() {
    }

    @Transaction
    public String  publishProject(WaterProjectContext ctx, String projectNumber, String projectName, String projcetTotalAmount, String projectLoanAvaAmount, String projectDonateAvaAmount,  String loanAccountNo,
                                  String donateAccountNo, String state, String useReason) {

        System.out.println(ctx);

        // create an instance of the project
        WaterProjectBase waterProjectBase = WaterProjectBase.createInstance(projectName, projectLoanAvaAmount, projectDonateAvaAmount, projectNumber, projcetTotalAmount, loanAccountNo, donateAccountNo,
                useReason, state);

        // Must return a serialized paper to caller of smart contract
       String tansId =  ctx.getStub().getTxId();
       return tansId;
//        return waterProjectBase;
    }


//    @Transaction
//    public ApplicationUnit addApplication(){
//        ApplicationUnit applicationUnit =new ApplicationUnit();
//        return applicationUnit;
//    }
//

//    @Transaction
//    public WaterProjectBase judgeApplicationner(WaterProjectContext ctx, String applicationNumber, String projectNumber,String enterpriseNumber, String enterpriseName, String enterpriseFundAccount,
//                               Double fundAmount, String fundUseReason, String proofMateriasUrl, String proofMateriasHash) {
//
//        // Retrieve the current project using key fields provided
//        String projectKey = State.makeKey(new String[] { projectNumber });
//        WaterProjectBase project = ctx.projectList.getWaterProjectBase(projectKey);
//

//        if(project.getEnterprise(applicationNumber).getIsHandle()){
//            throw new RuntimeException("Application :" + applicationNumber + "-" + projectNumber + " has been handled!" );
//        }
//

//        if (project.isLoaning()) {
//            throw new RuntimeException("Projcet :" + projectNumber + "-" +project.getProjectName() + " has been suspended!" );
//        }
//

//        if ((project.getDonateAccountNo() + fundAmount) <= project.getLoanAccountNo()) {
//            throw new RuntimeException("Projcet  has not enough money!" );
//        }

//        if (project.isInProjectLegalUseList(fundUseReason)) {
//            throw new RuntimeException("The reason of application has been refused!" );
//        }

////        if (!judgeContract.isAvailable()) {
////            throw new RuntimeException("The material of application has not been acceptted!" );
////        } else{

////            project.addApplicationUnit( enterpriseNumber,  enterpriseName,  enterpriseFundAccount,  proofMateriasUrl,  proofMateriasHash);

////        }
//

//        project.getEnterprise(applicationNumber).setIsHandle(true);
//        // Update the paper
//        ctx.projectList.updateWaterProjectBase(project);
//        return project;
//    }




    @Transaction
    public String  judgeApplicationner(WaterProjectContext proCtx, String projectNumber,
                                       String applicationNumber) {

        // Retrieve the current project using key fields provided
        String projectKey = State.makeKey(new String[] { projectNumber });
        WaterProjectBase project = proCtx.projectList.getWaterProjectBase(projectKey);


        WaterFundApplicationBase waterFundApplicationBase = project.getEnterprise(applicationNumber);


        if(waterFundApplicationBase.getApplicationUnit().getIsHandle()){
            throw new RuntimeException("Application :" + waterFundApplicationBase.getApplicationNumber()  + " has been handled!" );
        }


        if (project.isLoaning()) {
            throw new RuntimeException("Projcet :" + projectNumber + "-" +project.getProjectName() + " has been suspended!" );
        }


//        if ((project.getDonateAccountNo() + waterFundApplicationBase.getApplicationUnit().getApplicationFundAmount()) <= project.getLoanAccountNo()) {
//            throw new RuntimeException("Projcet  has not enough money!" );
//        }

        if (project.isInProjectLegalUseList(waterFundApplicationBase.getApplicationUseOfFunds())) {
            throw new RuntimeException("The reason of application has been refused!" );
        }

        if (waterFundApplicationBase.getIsVerifyMaterialAvailable() != AVAILABLE) {
            if(waterFundApplicationBase.getIsVerifyMaterialAvailable() == UNAVILABLE)
                throw new RuntimeException("The material of application has not been acceptted!" );
            throw new RuntimeException("The material of application has not been processed!" );
        } else{

            project.addWaterFundApplicationBase( applicationNumber, waterFundApplicationBase);

        }


        project.getEnterprise(applicationNumber).getApplicationUnit().setIsHandle(true);
        // Update the paper
        proCtx.projectList.updateWaterProjectBase(project);
//        return project;
        String tansId =  proCtx.getStub().getTxId();
        return tansId;
    }



    @Transaction(intent = EVALUATE)
    public String getInfoOfProject(WaterProjectContext ctx, String projectNumber){
        // Retrieve the current project using key fields provided
        String projectKey = State.makeKey(new String[] { projectNumber });
        WaterProjectBase project = ctx.projectList.getWaterProjectBase(projectKey);

        return project.toString();
    }

}
