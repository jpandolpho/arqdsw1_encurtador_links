package br.edu.ifsp.encurtador.controller;

import java.io.IOException;

import br.edu.ifsp.encurtador.controller.command.Command;
import br.edu.ifsp.encurtador.controller.command.RedirecionaCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/curto/*")
public class CurtoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = new RedirecionaCommand();
		
		String view = command.execute(request, response);
		if(view==null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Link curto não encontrado.");
		}else {
			response.sendRedirect(view);
		}
	}
}
