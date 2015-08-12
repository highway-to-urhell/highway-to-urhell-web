package com.highway2urhell.service.helper;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
public class ThunderAppHelper {
	
	private static Map<String,String> mapTypeConversion ;
	
	
	public static Map<String, String> getMapTypeConversion() {
		if(mapTypeConversion == null){
			synchronized (ThunderAppHelper.class) {
				mapTypeConversion = new HashMap<String,String>();
				mapTypeConversion.put("java", ".java");
				mapTypeConversion.put("php", ".php");
				mapTypeConversion.put("nodejs", ".js");
			}
		}
		return mapTypeConversion;
	}

}
