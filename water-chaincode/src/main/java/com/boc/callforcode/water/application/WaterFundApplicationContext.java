package com.boc.callforcode.water.application;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;

public class WaterFundApplicationContext extends Context {

    public WaterFundApplicationContext(ChaincodeStub stub) {
        super(stub);
        this.applicationList = new WaterFundApplicationList(this);
    }

    public WaterFundApplicationList applicationList;

}