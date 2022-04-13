package controller.promociones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.PromocionesService;

@WebServlet("/darBajaPromo")
public class DarBajaPromoServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 4791185308810646079L;
	private PromocionesService promoService;
	
	@Override
	public void init() {
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		promoService = new PromocionesService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		int promoId = Integer.parseInt(req.getParameter("id"));
		promoService.darBaja(promoId);
		req.setAttribute("bajaPromo", "Promocion id = " + promoId + ", dada de baja");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

}
