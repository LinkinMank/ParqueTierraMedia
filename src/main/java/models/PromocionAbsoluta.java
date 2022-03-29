package models;

import java.util.List;

public class PromocionAbsoluta extends Promocion {

	public PromocionAbsoluta(int id, String nombre, int costo, String atracciones, List<Atraccion> listaAtraccion) {
		super(id, nombre, atracciones, listaAtraccion);
		enlazarAtraccion();
		calcularTiempo();
		calcularCosto(costo);
	}

	@Override
	protected void enlazarAtraccion() {
		String[] spliteoAtrac = this.atracciones.split(",");
		for(String atracID : spliteoAtrac) {
			int id = Integer.parseInt(atracID);
			for(Atraccion atrac : this.listaAtraccion) {
				if(atrac.getId() == id) {
					listaInterna.add(atrac);
				}
			}
		}
	}

	private void calcularCosto(int costo) {
		this.costo = costo;
	}

}
