package com.boc.callforcode.water.application;

import com.boc.callforcode.ledgerapi.StateList;
import org.hyperledger.fabric.contract.Context;


public class WaterFundApplicationList {
    private StateList stateList;

    public WaterFundApplicationList(Context ctx) {
        this.stateList = StateList.getStateList(ctx, WaterFundApplicationList.class.getSimpleName(), WaterFundApplicationBase::deserialize);
    }

    public WaterFundApplicationList addWaterFundAppliactionBase(WaterFundApplicationBase waterFundApplicationBase) {
        stateList.addState(waterFundApplicationBase);
        return this;
    }

    public WaterFundApplicationBase getWaterFundAppliactionBase(String waterFundApplicationBaseKey) {
        return (WaterFundApplicationBase) this.stateList.getState(waterFundApplicationBaseKey);
    }

    public WaterFundApplicationList updateWaterProjectBase(WaterFundApplicationBase waterFundApplicationBase) {
        this.stateList.updateState(waterFundApplicationBase);
        return this;
    }
}
