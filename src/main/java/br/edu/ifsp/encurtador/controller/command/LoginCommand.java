package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import br.edu.ifsp.encurtador.model.dao.UserDaoFactory;
import br.edu.ifsp.encurtador.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var login = request.getParameter("textLogin");
		var senha = request.getParameter("textPassword");
		
		var dao = new UserDaoFactory().factory();
		var user = dao.findByLogin(login);
		
		var authorized = User.authenticate(user, login, senha);
		
		//String view;
		
		if(authorized) {
			var session = request.getSession(true);
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(24*60*60);
			
			return "logado";
			//response.sendRedirect("LogadoServlet");
			//view = "user.do?action=logged";
			//return null;
		}else {
			request.setAttribute("msg", "Erro ao fazer login.");
			return "front.do?action=formUser&login=true";
			//view = "front.do?action=formUser&login=true";
		}
		//return view;
	}
}
