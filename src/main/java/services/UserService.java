package services;

import models.User;
import persistence.DAOUser;
import persistence.commons.DAOFactory;

public class UserService {
	private DAOUser daoUser = DAOFactory.getDAOUser();
	
	public User login(String username, String password) {
		return daoUser.findByUsername(username, password);
	}
	
	public User findById(int userId) {
		return daoUser.findById(userId);
	}
}
