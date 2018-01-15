package com.third.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SmsConfig {
	private Properties props = null;// config.properties
	private static Logger log = Logger.getLogger(SmsConfig.class);
	private static volatile SmsConfig conf;

	private SmsConfig() {
		props = new Properties();
		loadConfigProps();
	}

	public static SmsConfig getInstance() {
		if (conf == null) {
			synchronized (SmsConfig.class) {
				if (conf == null) {
					conf = new SmsConfig();
				}
			}
		}
		return conf;
	}

	public void loadConfigProps() {
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream(
					"/WEB-INF/classes/weixin.properties");
			if (is == null) {
				is = getClass().getResourceAsStream("/weixin.properties");
			}
			InputStreamReader reader = new InputStreamReader(is, "UTF-8");
			props.load(reader);
			Iterator<String> iter = props.stringPropertyNames().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				props.setProperty(key, props.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("load config.properties error!please check the file!", e);
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getProperty(String key) {
		String tmp = props.getProperty(key);
		if ( tmp != null ) {
			return tmp.trim();
		}
		return tmp;
	}

	public String getProperty(String key, String defaultValue) {
		String tmp = props.getProperty(key, defaultValue);
		if ( tmp != null ) {
			return tmp.trim();
		}
		return tmp;
	}

	public int getPropertyInt(String key) {
		String tmp = props.getProperty(key);
		if ( tmp != null ) {
			return Integer.parseInt(tmp.trim());
		}
		return 0;

	}

	public int getPropertyInt(String key, int defaultValue) {
		String tmp = props.getProperty(key);
		if ( tmp != null ) {
			return Integer.parseInt(tmp.trim());
		}
		return defaultValue;
	}

	public long getPropertyLong(String key, long defaultValue) {
		String tmp = props.getProperty(key);
		if ( tmp != null ) {
			return Integer.parseInt(tmp.trim());
		}
		return defaultValue;
	}
}