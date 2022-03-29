package models;

public class User {
	private int id;
	private String nombre;
	private String password;
	private int dinero;
	private double tiempo;
	
	public User(int id, String nombre, String password, int dinero, double tiempo) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.dinero = dinero;
		this.tiempo = tiempo;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getPassword() {
		return password;
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
	
	public boolean tieneDineroTiempo(int costo, double tiempo) {
		return this.dinero >= costo && this.tiempo >= tiempo;
	}
	
	public void restarDineroTiempo(int costo, double tiempo) {
		this.dinero -= costo;
		this.tiempo -= tiempo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", dinero=" + dinero + ", tiempo=" + tiempo + "]";
	}


}
