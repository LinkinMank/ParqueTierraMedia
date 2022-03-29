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
		String sql = "SELECT Promocion.id, Promocion.nombre, Promocion.descuento, group_concat(promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.tipo = \"Porcentual\"\r\n"
				+ "GROUP BY Promocion.id;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				promocionList.add(new PromocionPorcentual(result.getInt(1), result.getString(2), result.getDouble(3), result.getString(4), atraccionList));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void promoAbsoluta(List<Promocion> promocionList, List<Atraccion> atraccionList) {
		String sql = "SELECT Promocion.id, Promocion.nombre, Promocion.costo, group_concat(promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.tipo = \"Absoluta\"\r\n"
				+ "GROUP BY Promocion.id;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				promocionList.add(new PromocionAbsoluta(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), atraccionList));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void promoAxB(List<Promocion> promocionList, List<Atraccion> atraccionList) {
		String sql = "SELECT promo_AxB.id, promo_AxB.nombre, promo_AxB.atracciones, group_concat(promoGratuita_axb.atraccion_id) as atracciones_gratis\r\n"
				+ "FROM (\r\n"
				+ "SELECT Promocion.id, Promocion.nombre, group_concat(promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.tipo = \"AxB\"\r\n"
				+ "GROUP by Promocion.id\r\n"
				+ ") as \"promo_AxB\"\r\n"
				+ "INNER JOIN promoGratuita_axb on promoGratuita_axb.promocion_id = promo_AxB.id\r\n"
				+ "GROUP By promo_AxB.id;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				promocionList.add(new PromocionAxB(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), atraccionList));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
