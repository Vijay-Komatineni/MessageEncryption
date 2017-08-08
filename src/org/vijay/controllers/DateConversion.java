package org.vijay.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConversion {
	
	public String convertDate(Calendar dateTime){

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		String dateFormat = format.format(dateTime.getTime());
		StringBuilder date = new StringBuilder(dateFormat);
		
		StringBuilder time =new StringBuilder(date.substring(11,19));
		
		date.replace(11, 19, "");
		
		
		String hours = time.substring(0,2);
		String secs = time.substring(5,8);
		if(Integer.parseInt(hours)>=12)
		{
			String h= null;
			if((Integer.parseInt(hours)-12)<10){
			 h= "0"+String.valueOf(Integer.parseInt(hours)-12);
			}else{
				h = String.valueOf(Integer.parseInt(hours)-12);	
			}
			String suffix =" PM";
			time.replace(0,2, h);
			time.replace(5,8,suffix);
			
			return date.toString()+time.toString();
		}
	else{
		String suffix =" AM";
		time.replace(5,8, suffix);
		
		return date.toString()+time.toString();
	}
		
	}

}
