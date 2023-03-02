package com.manager.service;

import com.manager.LoginException;
import com.manager.model.User;
import com.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	public User login(String username, String password) {

		if (StringUtils.isEmpty(username)) {
			throw new LoginException("Please enter your user name.");
		}

		if (StringUtils.isEmpty(password)) {
			throw new LoginException("Please enter your password.");
		}

		User user = userRepository.findById(username).orElseThrow(() -> new LoginException("Username does not exist"));

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean checkPassword = encoder.matches(password, user.getPassword());
		if (checkPassword) {
			return user;
		} else {
			throw new LoginException("Please check your password.");
		}
	}
}
