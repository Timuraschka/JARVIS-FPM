package edu.fra.uas.user.service;

import edu.fra.uas.token.service.TokenService;
import edu.fra.uas.user.model.User;
import edu.fra.uas.user.repository.UserRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TokenService tokenS;

	public User getUserWithToken(String token) {
		UUID uuid = UUID.fromString(token);
		long userID = tokenS.getUserIdByToken(uuid);
		
		User user = userRepository.findById(userID);
		
		return user;
	}

	public User getUserWithEmail(String email) {

		for (User user : userRepository.findAll()) {
			if (user.getEmail().equals(email)) {
				return user;
			}

		}
		return null;
	}

	@Override
	public Boolean loginUser(String email, String password) {
		for (User user : userRepository.findAll()) {

			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {

				return true;
			}
		}
		return false;
	}

	@Override
	public void createUser(User user) {
		
		userRepository.save(user);
	}

	@Override
	public User deleteUser(long userId) {

		userRepository.delete(userRepository.findById(userId));
		return null;
	}

	@Override
	public User getUser(long userId) {
		for (User user : userRepository.findAll()) {

			if (user.getId() == userId) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User assignRole(long UserId) {
		return null;
	}

}
