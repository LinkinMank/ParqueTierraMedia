package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class Promocion implements Ofrecible {
	protected int id;
	protected String nombre;
	protected String atracciones;
	protected String tipo;
	protected int costo = 0;
	protected double tiempo = 0;
	protected boolean esOfrecible = true;
	protected int baja = 0;
	protected List<Atraccion> listaAtraccion;
	protected List<Atraccion> listaInterna = new ArrayList<Atraccion>();
	
	public Promocion(int id, String nombre, String tipo, int baja, String atracciones, List<Atraccion> listaAtraccion) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.baja = baja;
		this.atracciones = atracciones;
		this.listaAtraccion = listaAtraccion;
	}
	
	public Promocion(int id, String nombre, String tipo, String atracciones) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.atracciones = atracciones;
	}
	
	protected abstract void enlazarAtraccion();
	
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getTipo() {
		return this.tipo;
	}

	public int getCosto() {
		return this.costo;
	}
	
	protected void calcularCosto() {
		for(Atraccion atrac : this.listaInterna) {
			this.costo += atrac.getCosto();
		}
	}

	public double getTiempo() {
		return this.tiempo;
	}
	
	protected void calcularTiempo() {
		for(Atraccion atrac : this.listaInterna) {
			this.tiempo += atrac.getTiempo();
		}
	}
	
	public boolean estaDeBaja() {
		Iterator<Atraccion> itr = this.listaInterna.iterator();
		while(itr.hasNext() && this.baja == 0) {
			this.baja = itr.next().estaDeBaja() ? 1 : 0;
		}
		return this.baja == 1;
	}
	
	public List<Atraccion> getListaInterna(){
		return this.listaInterna;	
		}
	
	public boolean getEsOfrecible() {
		Iterator<Atraccion> itr = this.listaInterna.iterator();
		while(itr.hasNext() && this.esOfrecible) {
			this.esOfrecible = itr.next().getEsOfrecible();
		}
		return this.esOfrecible;
	}
	
	public void setEsOfrecible(boolean puedeComprar, boolean comproPreviamente) {
		if(puedeComprar && !comproPreviamente) {
			this.esOfrecible = puedeComprar;
		} else {
			this.esOfrecible = false;
			this.comproPreviamente();
		}
	}
	
	private void comproPreviamente() {
		for(Atraccion atrac : this.listaInterna) {
			atrac.setEsOfrecible(false, true);
		}
	}
	
	public boolean esPromocion() {
		return true;
	}
	
	public void reservar() {
		for(Atraccion atrac : this.listaInterna) {
			atrac.reservar();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Promocion [id=" + id + ", nombre=" + nombre + ", costo=" + costo + ", tiempo=" + tiempo
				+ ", listaInterna=" + listaInterna + "]";
	}
	
	
}
