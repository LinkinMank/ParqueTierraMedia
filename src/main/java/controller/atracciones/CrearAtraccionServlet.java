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

@WebServlet("/createAtraccion")
public class CrearAtraccionServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1803785961166384233L;
	private AtraccionesService atracService;
	
	@Override
	public void init(){
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		atracService = new AtraccionesService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CrearAtraccion.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String nombre = req.getParameter("name");
		int costo = Integer.parseInt(req.getParameter("costo"));
		double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		int cupo = Integer.parseInt(req.getParameter("cupo"));
		if(atracService.createAtraccion(nombre, costo, tiempo, cupo)) {
			req.getSession().setAttribute("createdAtrac", "Nueva Atraccion Creada");
			try {
				resp.sendRedirect("atracciones");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			req.setAttribute("flashAT", "El nombre de atraccion ya esta en uso, reintente con otro");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CrearAtraccion.jsp");
			try {
				dispatcher.forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
