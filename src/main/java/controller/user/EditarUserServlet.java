package controller.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.UserService;

@WebServlet("/editUser")
public class EditarUserServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -6555774907188002555L;
	private UserService userService;
	
	@Override
	public void init(){
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		int userId = Integer.parseInt(req.getParameter("id"));
		User editUser = userService.findById(userId);
		req.setAttribute("edituser", editUser);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editarUsuario.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		int id = Integer.parseInt(req.getParameter("userId"));
		String nombre = req.getParameter("newname");
		String password = req.getParameter("newpassword");
		int dinero = Integer.parseInt(req.getParameter("newdinero"));
		double tiempo = Double.parseDouble(req.getParameter("newtiempo"));
		userService.updateUser(id, nombre, password, dinero, tiempo);
		req.getSession().setAttribute("updateUser", "usuario: " + nombre + ", id: " + id + " actualizado");
		try {
			resp.sendRedirect("user");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
