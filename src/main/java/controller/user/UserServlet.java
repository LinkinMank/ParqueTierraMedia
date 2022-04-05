package controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.UserService;

@WebServlet("/user")
public class UserServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -9036830883091914321L;
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
		List<User> userList = userService.getList();
		req.setAttribute("userlist", userList);
		if(req.getSession().getAttribute("updateUser") != null) {
			String update = (String) req.getSession().getAttribute("updateUser");
			req.setAttribute("updateUser", update);
			req.getSession().removeAttribute("updateUser");
		}
		if(req.getSession().getAttribute("createdUser") != null) {
			String created = (String) req.getSession().getAttribute("createdUser");
			req.setAttribute("createdUser", created);
			req.getSession().removeAttribute("createdUser");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
