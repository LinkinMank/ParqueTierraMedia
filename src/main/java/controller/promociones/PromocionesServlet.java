package controller.promociones;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Promocion;
import models.User;
import services.PromocionesService;

@WebServlet("/promociones")
public class PromocionesServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 7224537237636487419L;
	private PromocionesService promosService;

	@Override
	public void init(){
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		promosService = new PromocionesService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		User currentUser = (User) req.getSession().getAttribute("user");
		List<Promocion> promo = promosService.getList(currentUser.getId());
		req.setAttribute("promo", promo);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
