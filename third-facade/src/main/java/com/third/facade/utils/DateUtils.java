package com.third.facade.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    public static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    
	public static Date getToday(){
	    return Date.from(Instant.now());
	}
	
	public static Date getDefaultDate() throws ParseException
	{
	    return fmt.parse("2000-01-01");
	}
	
	public static String formatYYYYMMDD(Date date) {
	    return fmt.format(date);
	   
	}

}
