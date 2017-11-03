package validation;

import java.util.*;
import java.util.Map.Entry;

import serialization.*;

public class TxValidator {
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
