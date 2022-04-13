package services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Atraccion;
import models.Itinerario;
import models.Promocion;
import models.User;
import persistence.DAOAtraccion;
import persistence.DAOItinerario;
import persistence.DAOPromocion;
import persistence.DAOUser;
import persistence.commons.DAOFactory;

public class AtraccionesService {
	private DAOAtraccion daoAtraccion = DAOFactory.getDAOAtraccion();
	private DAOUser daoUser = DAOFactory.getDAOUser();
	private DAOItinerario daoItinerario = DAOFactory.getDAOItinerario();
	private DAOPromocion daoPromocion = DAOFactory.getDAOPromocion();

	public List<Atraccion> getList(int userId) {
		Set<Integer> ids = new HashSet<Integer>();
		User currentUser = daoUser.findById(userId);
		Itinerario itinerario = daoItinerario.findByUserId(userId);
		List<Promocion> listaPromo = daoPromocion.findAll();
		for (Promocion promo : listaPromo) { // cheackear si puedo cambiar este for por el getList de promocionService
			if (itinerario.comproPreviamente(promo.getId(), promo.esPromocion())) {
				for (Atraccion atrac : promo.getListaInterna()) {
					ids.add(atrac.getId());
				}
			}
		}
		List<Atraccion> listaFinalAtrac = daoAtraccion.findAll();
		for (Atraccion atrac : listaFinalAtrac) {
			atrac.setEsOfrecible(currentUser.tieneDineroTiempo(atrac.getCosto(), atrac.getTiempo()), itinerario.comproPreviamente(atrac.getId(), atrac.esPromocion()));
			if (ids != null && ids.contains(atrac.getId())) {
				atrac.setEsOfrecible(false, true);
			}
		}
		return listaFinalAtrac;
	}

	public void comprarAtrac(int userId, int atracId) {
		User user = daoUser.findById(userId);
		Atraccion atrac = this.findById(atracId);
		user.restarDineroTiempo(atrac.getCosto(), atrac.getTiempo());
		atrac.reservar();
		daoUser.registrarComprar(user);
		daoAtraccion.restarCupo(atrac);
	}
	
	public Atraccion findById(int atracId) {
		return daoAtraccion.findById(atracId);
	}
	
	public void updateAtraccion(int atracId, String nombre, int costo, double tiempo, int cupo) {
		Atraccion editAtrac = new Atraccion(atracId, nombre, costo, tiempo, cupo, 0);
		daoAtraccion.update(editAtrac);
	}
	
	public boolean createAtraccion(String nombre, int costo, double tiempo, int cupo) {
		for(Atraccion atracDB : daoAtraccion.findAll()) {
			if(atracDB.getNombre().equals(nombre)) {
				return false;
			}
		}
		Atraccion createAtraccion = new Atraccion (0, nombre, costo, tiempo, cupo, 0);
		daoAtraccion.insert(createAtraccion);
		return true;
	}
	
	public void darBaja(int id) {
		daoAtraccion.darBaja(id);
	}
}
