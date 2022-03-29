package controller.atracciones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.AtraccionesService;
import services.ItinerarioService;
import services.UserService;

@WebServlet("/buyAtrac")
public class ComprarAtracServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 63212501705057927L;
	private AtraccionesService atracService;
	private ItinerarioService itiService;
	private UserService userService;
	
	@Override
	public void init(){
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		atracService = new AtraccionesService();
		itiService = new ItinerarioService();
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		int atracId = Integer.parseInt(req.getParameter("id"));
		User user = (User) req.getSession().getAttribute("user");
		atracService.comprarAtrac(user.getId(), atracId);
		itiService.registrarCompraAtrac(user.getId(), atracId);
		User currentUser = userService.findById(user.getId());
		req.getSession().setAttribute("user", currentUser);
		req.setAttribute("success", "Gracias Por Su Compra!");
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
