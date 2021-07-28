/*
 * Copyright IBM Corp. All Rights Reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

// Running TestApp: 
// gradle runApp 

package application.java;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;


public class App {

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

	// helper function for getting connected to the gateway
	public static Gateway connect() throws Exception{
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallets.newFileSystemWallet(walletPath);
		// load a CCP
		Path networkConfigPath = Paths.get("..", "..", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "appUser1").networkConfig(networkConfigPath).discovery(true);
		return builder.connect();
	}

	public static void main(String[] args) throws Exception {
		// enrolls the admin and registers the user
		try {
//			EnrollAdmin.main(null);
//			RegisterUser.main(null);
		} catch (Exception e) {
			System.err.println(e);
		}

		// connect to the network and invoke the smart contract
		try (Gateway gateway = connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
//			Contract contract = network.getContract("basic");
			Contract contract = network.getContract("test","com.boc.project.WaterProjectContract");

			byte[] result;

//			System.out.println("Submit Transaction: InitLedger creates the initial set of assets on the ledger.");
//			contract.submitTransaction("InitLedger");

			System.out.println("\n");
			String useReason = "Ascending biological aerated filter:Biological turnateable:Submerged bioreactor:Oxidation ditch:Traditional activated sludge:Anaerobic sequencing batch reactor";
			result = contract.evaluateTransaction("publishProject","1", "Jinqiao village water quality improvement project ", "2000000", "1500000", "500000",
													"6217546388771042", "4218666574902344", "normal",
													"Ascending biological aerated filter:Biological turnateable:Submerged bioreactor:Oxidation ditch:Traditional activated sludge:Anaerobic sequencing batch reactor");
			System.out.println("Project is already created and successes on the chain. The transaction ID is " + result);
			System.out.println("Evaluate Transaction: getInfoOfApplication, result: " + new String(result));

//			System.out.println("\n");
//			System.out.println("Submit Transaction: CreateAsset asset132");
//			//CreateAsset creates an asset with ID asset13, color yellow, owner Tom, size 5 and appraisedValue of 1300
//			result = contract.submitTransaction("CreateAsset", "asset132", "yellow", "5", "Tom", "1300");
//			System.out.println("result: " + new String(result));
//
//			System.out.println("\n");
//			System.out.println("Evaluate Transaction: ReadAsset asset131");
//			// ReadAsset returns an asset with given assetID
//			result = contract.evaluateTransaction("ReadAsset", "asset131");
//			System.out.println("result: " + new String(result));
//
//			System.out.println("\n");
//			System.out.println("Evaluate Transaction: AssetExists asset1");
//			// AssetExists returns "true" if an asset with given assetID exist
//			result = contract.evaluateTransaction("AssetExists", "asset1");
//			System.out.println("result: " + new String(result));
//
//			System.out.println("\n");
//			System.out.println("Submit Transaction: UpdateAsset asset1, new AppraisedValue : 350");
//			// UpdateAsset updates an existing asset with new properties. Same args as CreateAsset
//			result = contract.submitTransaction("UpdateAsset", "asset1", "blue", "5", "Tomoko", "350");
//			System.out.println("result: " + new String(result));
//
//			System.out.println("\n");
//			System.out.println("Evaluate Transaction: ReadAsset asset1");
//			result = contract.evaluateTransaction("ReadAsset", "asset1");
//			System.out.println("result: " + new String(result));
//
//			try {
//				System.out.println("\n");
//				System.out.println("Submit Transaction: UpdateAsset asset70");
//				//Non existing asset asset70 should throw Error
//				contract.submitTransaction("UpdateAsset", "asset70", "blue", "5", "Tomoko", "300");
//			} catch (Exception e) {
//				System.err.println("Expected an error on UpdateAsset of non-existing Asset: " + e);
//			}
//
//			System.out.println("\n");
//			System.out.println("Submit Transaction: TransferAsset asset1 from owner Tomoko > owner Tom");
//			// TransferAsset transfers an asset with given ID to new owner Tom
//			contract.submitTransaction("TransferAsset", "asset1", "Tom");
//
//			System.out.println("\n");
//			System.out.println("Evaluate Transaction: ReadAsset asset1");
//			result = contract.evaluateTransaction("ReadAsset", "asset1");
//			System.out.println("result: " + new String(result));
		}
		catch(Exception e){
			System.err.println(e);
		}

	}
}
