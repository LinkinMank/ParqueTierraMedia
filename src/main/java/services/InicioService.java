package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Atraccion;
import models.Ofrecible;
import models.Promocion;
import persistence.DAOAtraccion;
import persistence.DAOPromocion;
import persistence.commons.DAOFactory;
import utils.OfrecibleComparator;

public class InicioService {
	private DAOAtraccion daoAtraccion = DAOFactory.getDAOAtraccion();
	private DAOPromocion daoPromocion = DAOFactory.getDAOPromocion();
	private List<Ofrecible> listaOfrecible = new ArrayList<Ofrecible>();
	
	public List<Ofrecible> getLista(){
		for(Atraccion atrac : this.daoAtraccion.findAll()) {
			if(!listaOfrecible.contains(atrac)) {
				this.listaOfrecible.add(atrac);
			}
		}
		for(Promocion promo : this.daoPromocion.findAll()) {
			if(!listaOfrecible.contains(promo)) {
				this.listaOfrecible.add(promo);
			}
		}
		Collections.sort(listaOfrecible, new OfrecibleComparator());
		return listaOfrecible;
	}
}
