package models;

public class User {
	private int id;
	private String nombre;
	private String password;
	private int dinero;
	private double tiempo;
	private int admin;
	private int baja;
	
	public User(int id, String nombre, String password, int dinero, double tiempo, int admin, int baja) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.dinero = dinero;
		this.tiempo = tiempo;
		this.admin = admin;
		this.baja = baja;
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
	
	public boolean isAdmin() {
		return this.admin == 1;
	}
	
	public boolean estaDeBaja() {
		return this.baja == 1;
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
