package controller.atracciones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Atraccion;
import services.AtraccionesService;

@WebServlet("/editAtraccion")
public class EditarAtraccionServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 6518955157412133238L;
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
		Atraccion editAtrac = atracService.findById(atracId);
		req.setAttribute("editAtrac", editAtrac);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/EditarAtraccion.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		int id = Integer.parseInt(req.getParameter("atracId"));
		String nombre = req.getParameter("newname");
		int costo = Integer.parseInt(req.getParameter("newcosto"));
		double tiempo = Double.parseDouble(req.getParameter("newtiempo"));
		int cupo = Integer.parseInt(req.getParameter("newcupo"));
		atracService.updateAtraccion(id, nombre, costo, tiempo, cupo);
		req.getSession().setAttribute("updateAtrac", "Atraccion: " + nombre + ", id: " + id + ", actualizada");
		try {
			resp.sendRedirect("atracciones");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
