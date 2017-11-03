package validation;

import java.util.*;
import java.util.Map.Entry;

import serialization.*;

public class StructureValidator implements iTxValidationRule {
	JsonMap template;
	
	public StructureValidator(JsonMap template) {
		this.template = template;
	}
	
	public JsonMap validate(JsonMap tx, JsonMap signatures, JsonMap extra) {
		JsonMap result = new JsonMap();
		Vector<String> missing = new Vector<String>();
		for( Entry<String,Object> e : template.entrySet()) {
			String key = e.getKey();
			if( !tx.containsKey(key)) {
				missing.add(key);
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
		result.put("status", "OK");
		
		return result;
	}
}
