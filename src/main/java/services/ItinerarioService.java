package services;

import models.Itinerario;
import persistence.DAOAtraccion;
import persistence.DAOItinerario;
import persistence.DAOPromocion;
import persistence.commons.DAOFactory;

public class ItinerarioService {
	private DAOItinerario daoItinerario = DAOFactory.getDAOItinerario();
	private DAOAtraccion daoAtraccion = DAOFactory.getDAOAtraccion();
	private DAOPromocion daoPromocion = DAOFactory.getDAOPromocion();
	/*
	public boolean tieneComprasPrevias(int userId) {
		return daoItinerario.checkearComprasPrevias(userId);
	}*/
	
	public Itinerario traerItinerario(int userId){
		Itinerario itinerario = daoItinerario.findByUserId(userId);
		itinerario.listarCompras(daoPromocion.findAll(), daoAtraccion.findAll());
		return itinerario;
	}
	
	public void registrarCompraAtrac(int userId, int atracId) {
		Itinerario itinerario = daoItinerario.findByUserId(userId);
		itinerario.comprarAtraccion(atracId);
		if(itinerario.isNull()) {
			daoItinerario.insert(userId, itinerario);
		} else {
			daoItinerario.updateAtrac(userId, itinerario);
		}
	}
	
	public void registarCompraPromo(int userId, int promoId) {
		Itinerario itinerario = daoItinerario.findByUserId(userId);
		itinerario.comprarPromocion(promoId);
		if(itinerario.isNull()) {
			daoItinerario.insert(userId, itinerario);
		} else {
			daoItinerario.updatePromo(userId, itinerario);
		}
	}
}
