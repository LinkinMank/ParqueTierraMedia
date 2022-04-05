package controller.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UserService;

@WebServlet("/darBaja")
public class DarBajaServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 5725640716740488581L;
	private UserService userService;
	
	@Override
	public void init() {
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		int userId = Integer.parseInt(req.getParameter("id"));
		userService.darBaja(userId);
		req.setAttribute("baja", "Usuario id = " + userId + ", dado de baja");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
