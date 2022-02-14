package services;

import models.User;
import persistence.DAOUser;
import persistence.commons.DAOFactory;

public class UserService {
	
	public User login(String username) {
		DAOUser DaoUser = DAOFactory.getDAOUser();
		User currentUser = DaoUser.findByUsername(username);
		
		return currentUser;
	};
}
