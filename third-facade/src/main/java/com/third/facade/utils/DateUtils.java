package com.third.facade.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
	
	public Date getToday(){
	    return Date.from(Instant.now());
	}

}
