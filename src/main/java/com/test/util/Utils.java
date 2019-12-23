package com.test.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class Utils {
	
	static Logger log = Logger.getLogger(Utils.class);
    
	public static Date StringtoDate(String strdate, String format) {
		
		log.info("Utils strdate:"+strdate+", format:"+format);
		SimpleDateFormat formatter1=new SimpleDateFormat(format);
		Date date1= null;
		
		try {
				date1 = formatter1.parse(strdate);  
				log.info("Utils date:"+date1);
				
		} catch(ParseException ex) {
			ex.printStackTrace();
		}
		return date1;
	}
}
