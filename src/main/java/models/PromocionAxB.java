package models;

import java.util.ArrayList;
import java.util.List;

public class PromocionAxB extends Promocion {
	private String atraccionesGratis;

	public PromocionAxB(int id, String nombre, String tipo, int baja, String atracciones, String atraccionesGratis, List<Atraccion> listaAtraccion) {
		super(id, nombre, tipo, baja, atracciones, listaAtraccion);
		this.atraccionesGratis = atraccionesGratis;
		enlazarAtraccion();
		calcularTiempo();
		calcularCosto();
	}
	
	public PromocionAxB(int id, String nombre, String tipo, String atracciones, String atraccionesGratis) {
		super(id, nombre, tipo, atracciones);
		this.atraccionesGratis = atraccionesGratis;
	}

	@Override
	protected void enlazarAtraccion() {
		String[] spliteoAtrac = this.atracciones.split(",");
		String[] spliteoAtracGratis = this.atraccionesGratis.split(",");
		for(Integer atracId : this.unirEnLista(spliteoAtrac, spliteoAtracGratis)) {
			int id = Integer.valueOf(atracId);
			for(Atraccion atrac : this.listaAtraccion) {
				if(atrac.getId() == id) {
					listaInterna.add(atrac);
				}
			}
		}
	}
	
	private List<Integer> unirEnLista(String[] atrac, String[] atracGratis){
		List<Integer> listaID = new ArrayList<Integer>();
		for(String atracID : atrac) {
			int id = Integer.parseInt(atracID);
			listaID.add(id);
		}
		for(String atracGratisId : atracGratis) {
			int gratisId = Integer.parseInt(atracGratisId);
			listaID.add(gratisId);
		}
		return listaID;
	}
	
	@Override
	protected void calcularCosto() {
		String[] spliteoAtrac = this.atracciones.split(",");
		for(String atracID : spliteoAtrac) {
			int id = Integer.parseInt(atracID);
			for(Atraccion atrac : this.listaInterna) {
				if(id == atrac.getId()) {
					this.costo += atrac.getCosto();
				}
			}
		}
	}

}
