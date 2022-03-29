package controller.inicio;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Ofrecible;
import services.InicioService;

@WebServlet("/inicio")
public class InicioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 6545465234185547366L;
	private InicioService iniService;

	public void init() {
		try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		iniService = new InicioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		List<Ofrecible> ofrecibles = iniService.getLista();
		req.setAttribute("ofrecibles", ofrecibles);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/inicio.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
