package services;

import java.util.Iterator;
import java.util.List;

import models.Atraccion;
import models.Itinerario;
import models.Promocion;
import models.User;
import persistence.DAOAtraccion;
import persistence.DAOItinerario;
import persistence.DAOPromocion;
import persistence.DAOUser;
import persistence.commons.DAOFactory;

public class PromocionesService {
	private DAOPromocion daoPromocion = DAOFactory.getDAOPromocion();
	private DAOUser daoUser = DAOFactory.getDAOUser();
	private DAOAtraccion daoAtraccion = DAOFactory.getDAOAtraccion();
	private DAOItinerario daoItinerario = DAOFactory.getDAOItinerario();
	
	public List<Promocion> getList(int userId){
		User currentUser = daoUser.findById(userId);
		Itinerario itinerario = daoItinerario.findByUserId(userId);
		List<Promocion> listaFinalPromo = daoPromocion.findAll();
		for(Promocion promo : listaFinalPromo) {
			promo.setEsOfrecible(currentUser.tieneDineroTiempo(promo.getCosto(), promo.getTiempo()), itinerario.comproPreviamente(promo.getId(), promo.esPromocion()));
			for(Atraccion atrac : promo.getListaInterna()) {
				if(itinerario.comproPreviamente(atrac.getId(), atrac.esPromocion())) {
					atrac.setEsOfrecible(false, true);
				}
			}
		}
		return listaFinalPromo;
	}
	
	public void comprarPromo(int userId, int promoId) {
		boolean seguirBuscando = true;
		User user = daoUser.findById(userId);
		Iterator<Promocion> itr = daoPromocion.findAll().iterator();
		while(itr.hasNext() && seguirBuscando) {
			Promocion promo = itr.next();
			if(promoId == promo.getId()) {
				user.restarDineroTiempo(promo.getCosto(), promo.getTiempo());
				promo.reservar();
				daoUser.registrarComprar(user);
				for(Atraccion atrac : promo.getListaInterna()) {
					daoAtraccion.restarCupo(atrac);
				}
				seguirBuscando = false;
			}
		}
	}
}
