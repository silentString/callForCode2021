package com.boc.callforcode.water.project;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;


public class WaterProjectContext extends Context {
    public WaterProjectContext(ChaincodeStub stub) {
        super(stub);
        this.projectList = new WaterProjectList(this);
    }
    public WaterProjectList projectList;
}
