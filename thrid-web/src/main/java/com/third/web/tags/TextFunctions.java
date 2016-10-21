package com.third.web.tags;

import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.functions.Functions;


/**
 * Extended Functions used in tag or jsp
 */
public class TextFunctions extends Functions
{
	private static final String STOREURL_PREFIX = "/s/";

	
	public static String seoStoreUrl(final String storeId)
	{
		return STOREURL_PREFIX+storeId;
	}

}
