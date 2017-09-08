package com.third.service.user;

public interface SessionService {
	public void save(final String key, final Object value);

	public Object get(final String key);

	void clear(String key);

	boolean contains(String key);
}
