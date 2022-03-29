package models;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private double porcentajeDescuento;
	
	public PromocionPorcentual(int id, String nombre, double descuento, String atracciones, List<Atraccion> listaAtraccion) {
		super(id, nombre, atracciones, listaAtraccion);
		this.porcentajeDescuento = descuento;
		enlazarAtraccion();
		calcularTiempo();
		calcularCosto();
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
	
	@Override
	protected void calcularCosto() {
		for(Atraccion atrac : this.listaInterna) {
			this.costo += atrac.getCosto();
		}
		this.costo -= Math.round(this.costo * (this.porcentajeDescuento / 100));
	}

}
