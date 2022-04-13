package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 5433299521146771162L;
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User currentUser = userService.login(username, password);
		if (!currentUser.isNull() && !currentUser.estaDeBaja()) {
			req.getSession().setAttribute("user", currentUser);
				try {
					resp.sendRedirect("inicio");
				} catch (IOException e) {
					e.printStackTrace();
				}
		} else if(currentUser.estaDeBaja()) {
			req.setAttribute("bajaUser", "Este usuario esta dado de baja");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			try {
				dispatcher.forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			req.setAttribute("flash", "Nombre de usuario o contraseña incorrectos");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
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
