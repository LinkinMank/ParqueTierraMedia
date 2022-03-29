package models;

import java.util.Objects;

public class Atraccion implements Ofrecible {

	private int id;
	private String nombre;
	private int costo;
	private double tiempo;
	private int cupo;
	private boolean esOfrecible = true;
	
	public Atraccion(int id, String nombre, int costo, double tiempo, int cupo) {
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public int getCosto() {
		return this.costo;
	}

	public double getTiempo() {
		return this.tiempo;
	}
	
	public int getCupo() {
		return this.cupo;
	}
	
	public boolean getEsOfrecible() {
		return this.esOfrecible && this.cupo > 0;
	}
	
	public void setEsOfrecible(boolean puedeComprar, boolean comproPreviamente) {
		if(puedeComprar & !comproPreviamente) {
			this.esOfrecible = puedeComprar;
		} else {
			this.esOfrecible = false;
		}
	}

	public boolean esPromocion() {
		return false;
	}
	
	public void reservar(){
		this.cupo--;
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
		Atraccion other = (Atraccion) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Atraccion [id=" + id + ", nombre=" + nombre + ", costo=" + costo + ", tiempo=" + tiempo + ", cupo="
				+ cupo + "]";
	}
	
	
}
