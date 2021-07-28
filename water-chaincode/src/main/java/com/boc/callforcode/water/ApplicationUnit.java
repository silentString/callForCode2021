package com.boc.callforcode.water;

public class ApplicationUnit {
    String EnterpriseNumber;
    String EnterpriseName;
    String EnterpriseFundAccount;
    Double ApplicationFundAmount;
    String TrustedRecord;

    String ProofMateriasUrl;
    String ProofMateriasHash;
    boolean isHandle;

    public ApplicationUnit(String enterpriseNumber, String enterpriseName, String enterpriseFundAccount, String proofMateriasUrl, String proofMateriasHash, Double applicationFundAmount,String trustedRecord) {
        EnterpriseNumber = enterpriseNumber;
        EnterpriseName = enterpriseName;
        EnterpriseFundAccount = enterpriseFundAccount;
        ApplicationFundAmount = applicationFundAmount;
        trustedRecord =trustedRecord;

        ProofMateriasUrl = proofMateriasUrl;
        ProofMateriasHash = proofMateriasHash;

        isHandle = false;
    }

    public ApplicationUnit(){

    }

    public String getEnterpriseNumber() {
        return EnterpriseNumber;
    }

    public void setEnterpriseNumber(String enterpriseNumber) {
        EnterpriseNumber = enterpriseNumber;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public String getEnterpriseFundAccount() {
        return EnterpriseFundAccount;
    }

    public void setEnterpriseFundAccount(String enterpriseFundAccount) {
        EnterpriseFundAccount = enterpriseFundAccount;
    }

    public String getProofMateriasUrl() {
        return ProofMateriasUrl;
    }

    public void setProofMateriasUrl(String proofMateriasUrl) {
        ProofMateriasUrl = proofMateriasUrl;
    }

    public String getProofMateriasHash() {
        return ProofMateriasHash;
    }

    public void setProofMateriasHash(String proofMateriasHash) {
        ProofMateriasHash = proofMateriasHash;
    }

    public boolean getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(boolean isHandle) {
        isHandle = isHandle;
    }

    public String getTrustedRecord() {
        return TrustedRecord;
    }

    public void setTrustedRecord(String TrustedRecord) {
        TrustedRecord = TrustedRecord;
    }


    public void setApplicationFundAmount(Double applicationFundAmount) {
        ApplicationFundAmount = applicationFundAmount;
    }

    public Double getApplicationFundAmount() {
        return ApplicationFundAmount;
    }
}
