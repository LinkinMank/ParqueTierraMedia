package models;

public class User {
	private int id;
	private String nombre;
	private int dinero;
	private double tiempo;
	
	public User(int id, String nombre, int dinero, double tiempo) {
		this.id = id;
		this.nombre = nombre;
		this.dinero = dinero;
		this.tiempo = tiempo;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDinero() {
		return dinero;
	}

	public double getTiempo() {
		return tiempo;
	}
	
	public boolean isNull() {
		return this.id == 0;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", dinero=" + dinero + ", tiempo=" + tiempo + "]";
	}


}
