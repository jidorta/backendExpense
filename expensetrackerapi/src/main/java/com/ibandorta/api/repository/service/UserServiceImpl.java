package com.ibandorta.api.repository.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibandorta.api.entity.User;
import com.ibandorta.api.entity.UserModel;
import com.ibandorta.api.exceptions.ItemAlreadyExistsException;
import com.ibandorta.api.exceptions.ResourceNotFounException;
import com.ibandorta.api.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired 
	private UserRepository userRepo;

	@Override
	public User createUser(UserModel user) {
		
		if (userRepo.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsException("User is already register with email" + user.getEmail() );
		}
		User newuser = new User();
		BeanUtils.copyProperties(user, newuser);
		return userRepo.save(newuser);
		
	
	}

	@Override
	public User readUser(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFounException("User not found for the id " + id));
	}

	@Override
	public User updateUser(UserModel user, Long id) {
		
		User existingUser = readUser(id);
		
		existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
		existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
		existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
		existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
		return userRepo.save(existingUser);
	}

	@Override
	public void deleteUser(Long id) {
		User existingUser  = readUser(id);
		userRepo.delete(existingUser);
		
	}
	
	
	

}
