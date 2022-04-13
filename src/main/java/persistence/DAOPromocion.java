package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Atraccion;
import models.Promocion;
import models.PromocionAbsoluta;
import models.PromocionAxB;
import models.PromocionPorcentual;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;

public class DAOPromocion {
	
	public List<Promocion> findAll(){
		DAOAtraccion daoAtraccion = DAOFactory.getDAOAtraccion();
		List<Atraccion> atraccionList = daoAtraccion.findAll();
		List<Promocion> promocionList = new ArrayList<Promocion>();
		promoPorcentual(promocionList, atraccionList);
		promoAbsoluta(promocionList, atraccionList);
		promoAxB(promocionList, atraccionList);
		return promocionList;
	}
	
	private void promoPorcentual(List<Promocion> promocionList, List<Atraccion> atraccionList) {
		String sql = "SELECT Promocion.id, Promocion.nombre, Promocion.tipo, Promocion.baja, Promocion.descuento, group_concat(Promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN Promocion_atraccion on Promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.tipo = \"Porcentual\"\r\n"
				+ "GROUP BY Promocion.id;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				promocionList.add(new PromocionPorcentual(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getDouble(5), result.getString(6), atraccionList));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void promoAbsoluta(List<Promocion> promocionList, List<Atraccion> atraccionList) {
		String sql = "SELECT Promocion.id, Promocion.nombre, Promocion.tipo, Promocion.baja, Promocion.costo, group_concat(Promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN Promocion_atraccion on Promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.tipo = \"Absoluta\"\r\n"
				+ "GROUP BY Promocion.id;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				promocionList.add(new PromocionAbsoluta(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5), result.getString(6), atraccionList));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void promoAxB(List<Promocion> promocionList, List<Atraccion> atraccionList) {
		String sql = "SELECT promo_AxB.id, promo_AxB.nombre, promo_AxB.tipo, promo_AxB.baja, promo_AxB.atracciones, group_concat(PromoGratuita_axb.atraccion_id) as atracciones_gratis\r\n"
				+ "FROM (\r\n"
				+ "SELECT Promocion.id, Promocion.nombre, Promocion.tipo, Promocion.baja, group_concat(Promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN Promocion_atraccion on Promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.tipo = \"AxB\"\r\n"
				+ "GROUP by Promocion.id\r\n"
				+ ") as \"promo_AxB\"\r\n"
				+ "INNER JOIN PromoGratuita_axb on PromoGratuita_axb.promocion_id = promo_AxB.id\r\n"
				+ "GROUP By promo_AxB.id;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				promocionList.add(new PromocionAxB(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6), atraccionList));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Promocion findById(int id, String tipo) {
		if(tipo.equals("Porcentual")) {
			return findPorcentual(id);
		} else if(tipo.equals("Absoluta")) {
			return findAbsoluta(id);
		} else {
			return findAxB(id);
		}
	}
	
	private Promocion findPorcentual(int id) {
		Promocion promoPor = new PromocionPorcentual(0, "", "", 0, "");
		String sql ="SELECT Promocion.id, Promocion.nombre, Promocion.tipo, Promocion.descuento, group_concat(Promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN Promocion_atraccion on Promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.id = ?\r\n"
				+ "GROUP BY Promocion.id";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				promoPor = new PromocionPorcentual(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4), result.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return promoPor;
	}
	
	private Promocion findAbsoluta(int id) {
		Promocion promoAbs = new PromocionAbsoluta(0, "", "", 0, "");
		String sql = "SELECT Promocion.id, Promocion.nombre, Promocion.tipo, Promocion.costo, group_concat(promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.id = ?\r\n"
				+ "GROUP BY Promocion.id";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				promoAbs = new PromocionAbsoluta(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return promoAbs;
	}
	
	private Promocion findAxB(int id) {
		Promocion promoAxB = new PromocionAxB(0, "", "", "", "");
		String sql = "SELECT promo_AxB.id, promo_AxB.nombre, promo_AxB.tipo, promo_AxB.atracciones, group_concat(promoGratuita_axb.atraccion_id) as atracciones_gratis\r\n"
				+ "FROM (\r\n"
				+ "SELECT Promocion.id, Promocion.nombre, Promocion.tipo, group_concat(promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.id = ?\r\n"
				+ "GROUP by Promocion.id\r\n"
				+ ") as \"promo_AxB\"\r\n"
				+ "INNER JOIN promoGratuita_axb on promoGratuita_axb.promocion_id = promo_AxB.id\r\n"
				+ "GROUP By promo_AxB.id;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				promoAxB = new PromocionAxB(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return promoAxB;
	}
	
	public boolean darBaja(int id) {
		int rows = 0;
		String sql = "UPDATE Promocion SET baja = ? WHERE id = ?";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, id);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
}
