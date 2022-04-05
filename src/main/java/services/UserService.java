package services;

import java.util.List;

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
	
	public List<User> getList(){
		return daoUser.listAll();
	}
	
	public void updateUser(int id, String nombre, String password, int dinero, double tiempo) {
		User updateUser = new User(id, nombre, password, dinero, tiempo, 0, 0);
		daoUser.update(updateUser);
	}
	
	public boolean createUser(String nombre, String password, int dinero, double tiempo) {
		for(User userDB : this.getList()) {
			if(userDB.getNombre().equals(nombre)) {
				return false;
			}
		}
		User createUser = new User(0, nombre, password, dinero, tiempo, 0, 0);
		daoUser.insert(createUser);
		return true;
	}
	
	public void darBaja(int id) {
		daoUser.darBaja(id);
	}
}
