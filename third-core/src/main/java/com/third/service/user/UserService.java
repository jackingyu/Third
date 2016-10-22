package com.third.service.user;

import com.third.model.User;

/**
 * @author i076218
 *
 */
public interface UserService {
	/**
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId);
	
    /**
     * @param user
     */
    public void saveUser(User user);
    
    /**
     * @return
     */
    public User getCurrentUser();
    
}
