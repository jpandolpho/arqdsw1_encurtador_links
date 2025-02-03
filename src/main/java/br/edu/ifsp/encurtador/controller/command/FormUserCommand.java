package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FormUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean login = Boolean.parseBoolean(request.getParameter("login"));
		request.setAttribute("login", login);
		
		return "/formUser.jsp";
	}

}
