package com.ibandorta.api.repository.service;

import com.ibandorta.api.entity.User;
import com.ibandorta.api.entity.UserModel;

public interface UserService {
	
	User createUser(UserModel user);
	
	User readUser(Long id);
	
	User updateUser(UserModel user, Long id);
	
	void deleteUser(Long id);

}
