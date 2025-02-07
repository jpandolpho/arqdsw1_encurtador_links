package br.edu.ifsp.encurtador.controller;

import java.io.IOException;

import br.edu.ifsp.encurtador.controller.command.Command;
import br.edu.ifsp.encurtador.controller.command.EncurtarCommand;
import br.edu.ifsp.encurtador.controller.command.FormUserCommand;
import br.edu.ifsp.encurtador.controller.command.HomeCommand;
import br.edu.ifsp.encurtador.controller.command.LoginCommand;
import br.edu.ifsp.encurtador.controller.command.SaveUserCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/front.do")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = null;
		String action = request.getParameter("action");
		
		if("home".equals(action)) {
			command = new HomeCommand();
		}else if("encurtar".equals(action)) {
			command = new EncurtarCommand();
		}else if("formUser".equals(action)) {
			command = new FormUserCommand();
		}else if("saveUser".equals(action)) {
			command = new SaveUserCommand();
		}else if("login".equals(action)) {
			command = new LoginCommand();
		}
		
		String view = command.execute(request, response);
		
		if ("logado".equals(view)) {

            response.sendRedirect("LogadoServlet");
        } else {
		var dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
        }
	}
}
