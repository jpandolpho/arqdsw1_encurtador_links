package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import br.edu.ifsp.encurtador.model.entity.User;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	var session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
		return "front.do?action=home";
    }
}
