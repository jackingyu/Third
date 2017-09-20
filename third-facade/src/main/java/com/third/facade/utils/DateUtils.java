package com.third.facade.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
	
	public static Date getToday(){
	    return Date.from(Instant.now());
	}

}
