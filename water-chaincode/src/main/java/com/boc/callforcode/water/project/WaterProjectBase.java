package com.boc.callforcode.water.project;

import com.boc.callforcode.ledgerapi.State;
import com.boc.callforcode.water.application.WaterFundApplicationBase;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

@DataType()
public class WaterProjectBase extends State {

    // Enumerate the use state of funds
    public final static String LOANING = "LOANING";
    public final static String LOANED = "LOANED";

    // Enumerate the legal use items of funds
    @Property()
    private List<String> projectLegalUseList;


    @Property()
    private String projectName = "";

    @Property()
    private String projectNumber;

    @Property()
    private String projcetTotalAmount;

    @Property()
    private String projectLoanAvaAmount;

    @Property()
    private String projectDonateAvaAmount;


    @Property
    private String loanAccountNo;

    @Property
    private String donateAccountNo;


    @Property
    public Map<String, WaterFundApplicationBase> EnterpriseList;


    @Property()
    private String state="";


    public String getState() {
        return state;
    }

    private WaterProjectBase setState(String state) {
        this.state = state;
        return this;
    }
    @JSONPropertyIgnore()
    public boolean isLoaning() {
        return this.state.equals(WaterProjectBase.LOANING);
    }
    @JSONPropertyIgnore()
    public boolean isLoaned() {
        return this.state.equals(WaterProjectBase.LOANED);
    }

    public WaterProjectBase setLoaning() {
        this.state = WaterProjectBase.LOANING;
        return this;
    }

    public WaterProjectBase setLoaned() {
        this.state = WaterProjectBase.LOANED;
        return this;
    }


    public WaterProjectBase() {
        super();
    }


    private WaterProjectBase setKey() {
        this.key = State.makeKey(new String[] { this.projectNumber });
        return this;
    }

    public List<String> getProjectLegalUseList() {
        return projectLegalUseList;
    }
    public boolean isInProjectLegalUseList(String useReason) {
        return projectLegalUseList.contains(useReason);
    }

    public WaterProjectBase addProjectLegalUseList(String useReason) {
        String[] useReasonList = useReason.split(":");
        for (String s : useReasonList) {
            this.projectLegalUseList.add(s);
        }
        return this;
    }

    public String getProjectName() {
        return projectName;
    }


    public WaterProjectBase setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public WaterProjectBase setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
        return this;
    }

    public String getProjcetTotalAmount() {
        return projcetTotalAmount;
    }

    public WaterProjectBase setProjcetTotalAmount(String projcetTotalAmount) {
        this.projcetTotalAmount = projcetTotalAmount;
        return this;
    }


    public String getProjectLoanAvaAmount() {
        return projectLoanAvaAmount;
    }


    public WaterProjectBase setProjectLoanAvaAmount(String projectLoanAvaAmount) {
        this.projectLoanAvaAmount = projectLoanAvaAmount;
        return this;
    }

    public String getProjectDonateAvaAmount() {
        return projectDonateAvaAmount;
    }

    public WaterProjectBase setProjectDonateAvaAmount(String projectDonateAvaAmount) {
        this.projectDonateAvaAmount = projectDonateAvaAmount;
        return this;
    }

    public String getLoanAccountNo() {
        return loanAccountNo;
    }

    public WaterProjectBase setLoanAccountNo(String loanAccountNo) {
        this.loanAccountNo = loanAccountNo;
        return this;
    }

    public String getDonateAccountNo() {
        return donateAccountNo;
    }

    public WaterProjectBase setDonateAccountNo(String donateAccountNo) {
        this.donateAccountNo = donateAccountNo;
        return this;
    }

    public Set getEnterpriseList() {
        return EnterpriseList.keySet();
    }
    public WaterFundApplicationBase getEnterprise(String applicationNumber) {
        return EnterpriseList.get(applicationNumber);
    }
    public boolean isInEnterpriseListNumber(String enterpriseNumber) {
        return EnterpriseList.keySet().contains(enterpriseNumber);
    }


    public WaterProjectBase addWaterFundApplicationBase(String applicationNumber, WaterFundApplicationBase waterFundApplicationBase) {
        this.EnterpriseList.put(applicationNumber, waterFundApplicationBase);
        return this;
    }



    @Override
    public String toString() {
        return "Project::" + this.key + "   ����ʱ�䣺" + this.getProjcetTotalAmount() + " " + this.getProjectLoanAvaAmount() + " ���ý�" + this.getDonateAccountNo() + " �ܽ�" + this.getLoanAccountNo();
    }

    /**
     * Deserialize a state data to commercial project
     *
     * @param {Buffer} data to form back into the object
     */
    public static WaterProjectBase deserialize(byte[] data) {
        JSONObject json = new JSONObject(new String(data, UTF_8));

        String projectName = json.getString("projectName");
        String projectLoanAvaAmount = json.getString("projectLoanAvaAmount");
        String projectDonateAvaAmount = json.getString("projectDonateAvaAmount");
        String projectNumber = json.getString("projectNumber");
        String projcetTotalAmount = json.getString("projcetTotalAmount");
        String loanAccountNo = json.getDouble("loanAccountNo");
        String donateAccountNo = json.getDouble("donateAccountNo");
        String useReason = json.getString("useReason");
        String state = json.getString("state");
        return createInstance(projectName, projectLoanAvaAmount, projectDonateAvaAmount, projectNumber, projcetTotalAmount, loanAccountNo, donateAccountNo,
                 useReason, state) ;
    }

    public static byte[] serialize(WaterProjectBase project) {
        return State.serialize(project);
    }

    /**
     * Factory method to create a commercial project object
     */
    public static WaterProjectBase createInstance(String projectName, String projectLoanAvaAmount, String projectDonateAvaAmount, String projectNumber, String projcetTotalAmount, String loanAccountNo,
                                                  String donateAccountNo, String useReason, String state) {
        return new WaterProjectBase().setProjectName(projectName).setProjectLoanAvaAmount(projectLoanAvaAmount).setProjectDonateAvaAmount(projectDonateAvaAmount).setProjectNumber(projectNumber).setProjcetTotalAmount(projcetTotalAmount).setLoanAccountNo(loanAccountNo)
                .setDonateAccountNo(donateAccountNo).setKey().setState(state).addProjectLegalUseList(useReason);
    }


}
