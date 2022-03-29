package models;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {

	private int id;
	private int userId;
	private String promocionId;
	private String atraccionId;
	private List<Ofrecible> listaCompras = new ArrayList<Ofrecible>();

	public Itinerario(int id, int userId, String promocionId, String atraccionId) {
		this.id = id;
		this.userId = userId;
		this.promocionId = promocionId;
		this.atraccionId = atraccionId;
	}
	
	public String getPromocionId() {
		return promocionId;
	}

	public String getAtraccionId() {
		return atraccionId;
	}

	public List<Ofrecible> getListaCompras() {
		return listaCompras;
	}

	public boolean isNull() {
		return this.id == 0;
	}

	public void listarCompras(List<Promocion> listPromociones, List<Atraccion> listAtracciones) {
		if (this.getPromocionId() != null) {
			String[] spliteoPromocion = this.getPromocionId().split(",");
			for (String promoID : spliteoPromocion) {
				int id = Integer.parseInt(promoID);
				for (Promocion promo : listPromociones) {
					if (promo.getId() == id) {
						this.listaCompras.add(promo);
					}
				}
			}
		}
		if (this.getAtraccionId() != null) {
			String[] spliteoAtraccion = this.getAtraccionId().split(",");
			for (String atracId : spliteoAtraccion) {
				int id = Integer.parseInt(atracId);
				for (Atraccion atrac : listAtracciones) {
					if (atrac.getId() == id) {
						this.listaCompras.add(atrac);
					}
				}
			}
		}
	}
	
	public void comprarPromocion(int promoId) {
		String promoIdS = Integer.toString(promoId);
		if(this.getPromocionId() != null) {
			this.promocionId += "," + promoIdS;
		} else {
			this.promocionId = promoIdS;
		}
	}
	
	public void comprarAtraccion(int atracId) {
		String atracIdS = Integer.toString(atracId);
		if(this.getAtraccionId() != null) {
			this.atraccionId += "," + atracIdS;
		} else {
			this.atraccionId = atracIdS;
		}
	}
	
	public boolean comproPreviamente(int id, boolean esPromocion) {
		boolean compro = false;
		if(esPromocion && this.getPromocionId() != null) {
			String[] spliteoPromo = this.getPromocionId().split(",");
			for(int i = 0; i < spliteoPromo.length; i++) {
				int idP = Integer.parseInt(spliteoPromo[i]);
				if(idP == id) {
					compro = true;
					i = spliteoPromo.length + 1;
				}
			}
		} 
		if (!esPromocion && this.getAtraccionId() != null) {
			String[] spliteoAtrac = this.getAtraccionId().split(",");
			for(int i = 0; i < spliteoAtrac.length; i++) {
				int idA = Integer.parseInt(spliteoAtrac[i]);
				if(idA == id) {
					compro = true;
					i = spliteoAtrac.length + 1;
				}
			}
		}	
		return compro;
	}
	/*
	public Integer[] comproAtracciones() {
		Integer[] atracciones = null;
		if(this.getAtraccionId() != null) {
			String[] spliteoAtrac = this.getAtraccionId().split(",");
		}
		return atracciones;
	}
	*/
}
