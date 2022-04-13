package models;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private double porcentajeDescuento;
	
	public PromocionPorcentual(int id, String nombre, String tipo, int baja, double descuento, String atracciones, List<Atraccion> listaAtraccion) {
		super(id, nombre, tipo, baja, atracciones, listaAtraccion);
		this.porcentajeDescuento = descuento;
		enlazarAtraccion();
		calcularTiempo();
		calcularCosto();
	}
	
	public PromocionPorcentual(int id, String nombre, String tipo, double descuento, String atracciones) {
		super(id, nombre, tipo, atracciones);
		this.porcentajeDescuento = descuento;
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

	public double getDescuento() {
		return this.porcentajeDescuento;
	}
	
	

}
