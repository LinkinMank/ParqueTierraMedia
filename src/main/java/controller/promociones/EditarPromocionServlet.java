package controller.promociones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Promocion;
import services.PromocionesService;

@WebServlet("/editPromocion")
public class EditarPromocionServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1501614201983629659L;
	private PromocionesService promoService;
	
	@Override
	public void init(){
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
		String tipo = req.getParameter("tipo");
		Promocion promo = promoService.find(promoId, tipo);
		req.setAttribute("editPromo", promo);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/EditarPromocion.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
