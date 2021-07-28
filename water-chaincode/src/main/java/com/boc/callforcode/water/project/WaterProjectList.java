package com.boc.callforcode.water.project;

import com.boc.callforcode.ledgerapi.StateList;
import org.hyperledger.fabric.contract.Context;

public class WaterProjectList {

    private StateList stateList;

    public WaterProjectList(Context ctx) {
        this.stateList = StateList.getStateList(ctx, WaterProjectList.class.getSimpleName(), WaterProjectBase::deserialize);
    }

    public WaterProjectList addWaterProjectBase(WaterProjectBase waterProjectBase) {
        stateList.addState(waterProjectBase);
        return this;
    }

    public WaterProjectBase getWaterProjectBase(String waterProjectBaseKey) {
        return (WaterProjectBase) this.stateList.getState(waterProjectBaseKey);
    }

    public WaterProjectList updateWaterProjectBase(WaterProjectBase waterProjectBase) {
        this.stateList.updateState(waterProjectBase);
        return this;
    }
}
