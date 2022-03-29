package controller.promociones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.ItinerarioService;
import services.PromocionesService;
import services.UserService;

@WebServlet("/buyPromo")
public class ComprarPromoServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 3807955355317434397L;
	private PromocionesService promoService;
	private ItinerarioService itiService;
	private UserService userService;
	
	@Override
	public void init() {
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		promoService = new PromocionesService();
		itiService = new ItinerarioService();
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		int promoId = Integer.parseInt(req.getParameter("id"));
		User user = (User) req.getSession().getAttribute("user");
		promoService.comprarPromo(user.getId(), promoId);
		itiService.registarCompraPromo(user.getId(), promoId);
		User currentUser = userService.findById(user.getId());
		req.getSession().setAttribute("user", currentUser);
		req.setAttribute("success", "Gracias Por Su Compra!");
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
