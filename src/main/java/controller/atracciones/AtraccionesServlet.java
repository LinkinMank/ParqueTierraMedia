package controller.atracciones;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Atraccion;
import models.User;
import services.AtraccionesService;

@WebServlet("/atracciones")
public class AtraccionesServlet extends HttpServlet implements Servlet{
	private static final long serialVersionUID = -1691588880480313264L;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		User currentUser = (User) req.getSession().getAttribute("user");
		List<Atraccion> atrac = atracService.getList(currentUser.getId());
		req.setAttribute("atrac", atrac);
		if(req.getSession().getAttribute("updateAtrac") != null) {
			String update = (String) req.getSession().getAttribute("updateAtrac");
			req.setAttribute("updateAtrac", update);
			req.getSession().removeAttribute("updateAtrac");
		}
		if(req.getSession().getAttribute("createdAtrac") != null) {
			String created = (String) req.getSession().getAttribute("createdAtrac");
			req.setAttribute("createdAtrac", created);
			req.getSession().removeAttribute("createdAtrac");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atracciones.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
