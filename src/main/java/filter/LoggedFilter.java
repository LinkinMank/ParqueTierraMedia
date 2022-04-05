package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import models.User;

@WebFilter(urlPatterns = "*.do")
public class LoggedFilter implements Filter {

	@Override // para despues, intentar ver como funciona
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		User currentUser = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
		if(!currentUser.isNull()) {
			try {
				chain.doFilter(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("login.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
