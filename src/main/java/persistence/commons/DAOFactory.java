package persistence.commons;

import persistence.DAOAtraccion;
import persistence.DAOItinerario;
import persistence.DAOPromocion;
import persistence.DAOUser;

public class DAOFactory {
	
	public static DAOUser getDAOUser() {
		return new DAOUser();
	}
	
	public static DAOAtraccion getDAOAtraccion() {
		return new DAOAtraccion();
	}
	
	public static DAOPromocion getDAOPromocion() {
		return new DAOPromocion();
	}
	
	public static DAOItinerario getDAOItinerario() {
		return new DAOItinerario();
	}
}
