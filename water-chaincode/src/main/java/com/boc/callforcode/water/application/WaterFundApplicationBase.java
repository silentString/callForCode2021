package com.boc.callforcode.water.application;

import com.boc.callforcode.ledgerapi.State;
import com.boc.callforcode.water.ApplicationUnit;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

import static java.nio.charset.StandardCharsets.UTF_8;

@DataType()
public class WaterFundApplicationBase extends State {

    // Enumerate the  state of application
    public final static String ACCEPTTED = "ACCEPTTED";
    public final static String CONSIDERING = "CONSIDERING";
    public final static String REFUSEED = "REFUSEED";


    @Property()
    private String applicationNumber;


    @Property()
    private String projectNumber;


    @Property()
    private String  applicationUseOfFunds;


    @Property()
    private ApplicationUnit applicationUnit;

    @Property()
    private String applicationDateTime;


    @Property()
    private String isVerifyMaterialAvailable = REFUSEED;
    // Enumerate the  state of application
    public final static String AVAILABLE = "AVAILABLE";
    public final static String UNAVILABLE = "UNAVILABLE";





    @Property()
    private String state="";


    public String getState() {
        return state;
    }

    private WaterFundApplicationBase setState(String state) {
        this.state = state;
        return this;
    }
    @JSONPropertyIgnore()
    public boolean isAccepted() {
        return this.state.equals(WaterFundApplicationBase.ACCEPTTED);
    }
    @JSONPropertyIgnore()
    public boolean isConsidering() {
        return this.state.equals(WaterFundApplicationBase.CONSIDERING);
    }
    @JSONPropertyIgnore()
    public boolean isRefused() {
        return this.state.equals(WaterFundApplicationBase.REFUSEED);
    }

    public WaterFundApplicationBase setAccepted() {
        this.state = WaterFundApplicationBase.ACCEPTTED;
        return this;
    }

    public WaterFundApplicationBase setConsidering() {
        this.state = WaterFundApplicationBase.CONSIDERING;
        return this;
    }

    public WaterFundApplicationBase setRefused() {
        this.state = WaterFundApplicationBase.REFUSEED;
        return this;
    }

    public WaterFundApplicationBase() {
        super();
    }

    private WaterFundApplicationBase setKey() {
        this.key = State.makeKey(new String[] { applicationNumber});
        return this;
    }


    public String getApplicationNumber() {
        return applicationNumber;
    }

    public WaterFundApplicationBase setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
        return this;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public WaterFundApplicationBase setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
        return this;
    }

    public String getApplicationUseOfFunds() {
        return applicationUseOfFunds;
    }

    public WaterFundApplicationBase setApplicationUseOfFunds(String applicationUseOfFunds) {
        this.applicationUseOfFunds = applicationUseOfFunds;
        return this;
    }

    public ApplicationUnit getApplicationUnit() {
        return applicationUnit;
    }

    public WaterFundApplicationBase setApplicationUnit(String enterpriseNumber, String enterpriseName, String enterpriseFundAccount, String proofMateriasUrl, String proofMateriasHash, Double applicationAmount, String trustedRecord) {
        this.applicationUnit = new ApplicationUnit(enterpriseNumber, enterpriseName, enterpriseFundAccount, proofMateriasUrl, proofMateriasHash,applicationAmount,trustedRecord);
        return this;
    }
    public ApplicationUnit getEnterprise() {
        return this.applicationUnit;
    }



    public String getApplicationDateTime() {
        return applicationDateTime;
    }

    public WaterFundApplicationBase setApplicationDateTime(String applicationDateTime) {
        this.applicationDateTime = applicationDateTime;
        return this;
    }


    public String getIsVerifyMaterialAvailable() {
        return isVerifyMaterialAvailable;
    }


    public WaterFundApplicationBase setIsVerifyMaterialAvailable(boolean isVerifyMaterialAvailable) {
        if(isVerifyMaterialAvailable)
            this.isVerifyMaterialAvailable = AVAILABLE;
        else
            this.isVerifyMaterialAvailable = UNAVILABLE;
        return this;
    }



    @Override
    public String toString() {
        return "Application:" + this.key + "  ---" + this.getApplicationDateTime() + " :" + this.applicationUnit.getEnterpriseName() + " ----" + this.applicationUnit.getEnterpriseFundAccount() + "----" + this.getState();
    }

    /**
     * Deserialize a state data to commercial project
     *
     * @param {Buffer} data to form back into the object
     */
    public static WaterFundApplicationBase deserialize(byte[] data) {
        JSONObject json = new JSONObject(new String(data, UTF_8));

        String applicationNumber = json.getString("applicationNumber");
        String projectNumber = json.getString("projectNumber");
        String applicationUseOfFunds = json.getString("applicationUseOfFunds");
        String applicationDateTime = json.getString("applicationDateTime");
        boolean isVerifyMaterialAvailable = json.getBoolean("isVerifyMaterialAvailable");
        //ApplicationUnit
        String enterpriseNumber = json.getString("EnterpriseNumber");
        String enterpriseName = json.getString("EnterpriseName");
        String enterpriseFundAccount = json.getString("EnterpriseFundAccount");
        Double applicationAmount = json.getDouble("applicationFundAmount");
        String trustedRecord = json.getString("trustedRecord");
        String proofMateriasUrl = json.getString("ProofMateriasUrl");
        String proofMateriasHash = json.getString("ProofMateriasHash");

        return createInstance(  applicationNumber,  projectNumber,  applicationUseOfFunds,  applicationDateTime,isVerifyMaterialAvailable,
                enterpriseNumber,  enterpriseName,  enterpriseFundAccount,  proofMateriasUrl,  proofMateriasHash,  applicationAmount, trustedRecord) ;
    }

    public static byte[] serialize(WaterFundApplicationBase project) {
        return State.serialize(project);
    }

    /**
     * Factory method to create a commercial project object
     */
    public static WaterFundApplicationBase createInstance(String applicationNumber, String projectNumber, String applicationUseOfFunds, String applicationDateTime, boolean isVerifyMaterialAvailable,
                                                          String enterpriseNumber, String enterpriseName, String enterpriseFundAccount, String proofMateriasUrl, String proofMateriasHash, Double applicationAmount, String trustedRecord) {
        return new WaterFundApplicationBase().setApplicationNumber(applicationNumber).setProjectNumber(projectNumber).setApplicationUseOfFunds(applicationUseOfFunds).setApplicationDateTime(applicationDateTime).setIsVerifyMaterialAvailable(isVerifyMaterialAvailable).
                setApplicationUnit(enterpriseNumber, enterpriseName, enterpriseFundAccount, proofMateriasUrl, proofMateriasHash,applicationAmount, trustedRecord);
    }
}
