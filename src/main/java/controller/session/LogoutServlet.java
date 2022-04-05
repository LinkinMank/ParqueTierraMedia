package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 132276158438139880L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("user");
		try {
			resp.sendRedirect("login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
