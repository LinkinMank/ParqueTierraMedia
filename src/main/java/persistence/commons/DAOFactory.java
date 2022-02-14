package persistence.commons;

import persistence.DAOUser;

public class DAOFactory {
	
	public static DAOUser getDAOUser() {
		return new DAOUser();
	}
}
