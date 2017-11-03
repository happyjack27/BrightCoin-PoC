package validation;

import java.util.*;
import java.util.Map.Entry;

import serialization.*;

public class TxValidator {
	//how to auto-accept money - without having to sign...?  and keep ledger intact?
	//send must include a strictly increasing timestamp and sequence and chain
	//sender can simply acknowledge reciepts before they send.  
	//  the money is subtracted from the sender when they sign, but not credited to the depositor until they acknowledge.
	//  receiver can also decline, in which case the money goes back to sender on the decline date.  meanwhile coins are in limbo.
	// the receipt transaction number is then chosen when signed by reciever.
	// how to ack/return a decline? 
	
	//bitcoin rules:
	//https://www.cryptocompare.com/coins/guides/how-does-a-bitcoin-node-verify-a-transaction/
	
	//internal: structure, math, signatures, approvals (needed signatures must say ok), all
	//chained: chronology, inter-tx sigs, inter-tx math, all
	//recurse: 
	//other: accounts
	
	//get signatures function, then confirm all, and approval states

	/*when requesting data:
	number of copies
	nubmer of replications
	number of trusted store copies
	number of trusted store replications 
	*/

	public String validateStructure(JsonMap tx, JsonMap template, String prefix) {
		Vector<String> missing = new Vector<String>();
		for( Entry<String,Object> e : template.entrySet()) {
			String key = e.getKey();
			if( !tx.containsKey(key)) {
				missing.add(prefix+key);
			} else {
				
				Object txo = tx.get(key);
				if(e.getValue() instanceof aJsonMap) {
					JsonMap o = (JsonMap)e.getValue();

				} else 
				if(e.getValue() instanceof Collection) {
					Collection<Object> v = (Collection<Object>)e.getValue();
				} else 
				if(e.getValue() instanceof String) {
					String o = (String)e.getValue();			
				} else {
					
				}
			}
		}
		
		if( missing.size() > 0) {
			
		}
		
		return "OK";
	}

}
