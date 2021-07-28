# callForCode2021
# Creative Introduce
As a part of the potable water supervision platform, this project provides the whole life cycle monitoring of water quality improvement project establishment, fund raising, enterprise fund application and capital flow.

# Project Structure
Includes three parts: the first is the contract works, mainly defines the contract itself.The second is the backend, which implements the call contract and provides the interface to the front end.The third is the front page, which mainly shows the terminal operation platform.The latter two parts have clear functions and will not be detailed here.The parts of the contract project are described below.


## 1. Contract Project
It mainly includes the following four contracts

### 1.1 WaterProjectContract
The WaterProjectContractt supports the project approval and management of water quality improvement related projects, the initial setting of fund use, the size of the new project fund pool, and the tracking and recording of various information status of each project, such as the use of funds, the application enterprise information, etc.

### 1.2 FundUsageCheckContract
The fundUsageCheckContract support the establishment of fund application and track the flow of relevant application funds throughout the process.

### 1.3 AutomaticTransferContract
The AutomaticTransferContract allows the bank to pull and review the electronic version of the application, update the status of the fund application, and complete the transfer from the trigger contract.

### 1.4 AutoMaticAlterContract
The AutoMaticAlterContract supports the invocation of purpose of funds checking contracts to trigger alarms based on, for example, credit information.It also supports the AutomaticTransferContract material review stage. If the material is falsified, the bank role invokes the AutoMaticAlterContract through AutomaticTransferContract to update the relevant credit status.

## 2. Project process
begin
	Enterprise application for funds
	if pass
	   Automatic transfer
	   Banks lend money to businesses
	else 
	   Automatic alarm
	   Synchronize warning messages to the bank
end
## 3. Functions of each role
The above outline describes the overall problem flow. This section shows the dependencies between roles and contracts in terms of each participating role.

### 3.1 Government

####Function: 
The government has the right to create a specific water quality improvement project according to objective needs and raise funds.The government that initiates the project at the same time gets the right to suspend or stop the project.
####Related Contract: 
WaterProjectContract

### 3.2 Personal users,Common weal organizations

####Function: 
Donations made by individuals or public welfare organizations to designated projects are the source of the fund pool, and the size of the fund pool will be updated according to the contract.You can look at the cash flow records.Check the organization or personal credit information automatically. If it is abnormal, it will report to the police that the source of funds is suspicious. If it is normal, it will be automatically entered into the fund pool.
####Related Contracts: 
WaterProjectContract，AutoMaticAlterContract，AutomaticTransferContract

### 3.3 Enterprises

####Function: 
The enterprise inquire all the water quality improvement projects, initiate the application for the money for the interested projects and submit the electronic material to the chain, and automatically check the enterprise credit investigation. If there is any abnormality, it will alarm the abnormal credit investigation of the enterprise.
####Related Contracts: 
WaterProjectContract，FundUsageCheckContract，AutoMaticAlterContract

### 3.4 Banks

####Function: 
The bank can obtain the application materials of related enterprises through the chain and review, and put the audit results on the chain. If the results pass, the bank will call the contract to realize automatic transfer. Otherwise, the bank will alarm the abnormal application data.
####Related Contracts: 
FundUsageCheckContract，AutomaticTransferContract，AutoMaticAlterContract
