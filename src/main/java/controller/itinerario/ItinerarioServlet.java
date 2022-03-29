package controller.itinerario;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Itinerario;
import models.Ofrecible;
import models.User;
import services.ItinerarioService;

@WebServlet("/itinerario")
public class ItinerarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 832049716418227395L;
	private ItinerarioService itiService;
	
	@Override
	public void init(){
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		itiService = new ItinerarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		User currentUser = (User) req.getSession().getAttribute("user");
		Itinerario itinerario = itiService.traerItinerario(currentUser.getId());
		req.setAttribute("itinerario", itinerario);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/itinerario.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
