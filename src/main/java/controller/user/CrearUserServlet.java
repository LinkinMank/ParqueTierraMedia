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

@WebServlet("/createUser")
public class CrearUserServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 563674473352363876L;
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
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/crearUsuario.jsp");
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
		String nombre = req.getParameter("name");
		String contraseña = req.getParameter("password");
		int dinero = Integer.parseInt(req.getParameter("dinero"));
		double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		if(userService.createUser(nombre, contraseña, dinero, tiempo)) {
			req.getSession().setAttribute("createdUser", "Nuevo usuario creado");
			try {
				resp.sendRedirect("user");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			req.setAttribute("flash", "El nombre del usuario ya esta ocupado, reintente con otro");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/crearUsuario.jsp");
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
