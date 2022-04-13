package controller.atracciones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AtraccionesService;

@WebServlet("/darBajaAtrac")
public class DarBajaAtracServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1647864424946189336L;
	private AtraccionesService atracService;
	
	@Override
	public void init() {
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		atracService = new AtraccionesService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		int atracId = Integer.parseInt(req.getParameter("id"));
		atracService.darBaja(atracId);
		req.setAttribute("bajaAtrac", "Atraccion id = " + atracId + ", dada de baja");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atracciones");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
