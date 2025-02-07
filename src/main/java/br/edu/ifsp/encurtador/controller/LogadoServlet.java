package br.edu.ifsp.encurtador.controller;

import java.io.IOException;
import br.edu.ifsp.encurtador.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import br.edu.ifsp.encurtador.controller.command.Command;
import br.edu.ifsp.encurtador.controller.command.DeleteLinkCommand;
import br.edu.ifsp.encurtador.controller.command.EditLinkCommand;
import br.edu.ifsp.encurtador.controller.command.ListLinksCommand;
import br.edu.ifsp.encurtador.controller.command.HomeLogadoCommand;
import br.edu.ifsp.encurtador.controller.command.LogoutCommand;
import br.edu.ifsp.encurtador.controller.command.EncurtarLogadoCommand;

@WebServlet("/logado.do")
public class LogadoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Command command = null;
        String action = request.getParameter("command");
        
        if ("ListLinks".equals(action)) {
            command = new ListLinksCommand(); 
        } else if ("ViewStats".equals(action)) {
            //command = new ViewStatsCommand(); 
        } else if ("Logout".equals(action)) {
            command = new LogoutCommand(); 
        } else if ("EncurtarLink".equals(action)) {
            command = new EncurtarLogadoCommand(); 
        } else if ("Home".equals(action)) {
        	command = new HomeLogadoCommand();
        } else if ("DeleteLink".equals(action)) {
            command = new DeleteLinkCommand();
        } else if ("EditLink".equals(action)) {
            command = new EditLinkCommand();
        }
        
        String view = command.execute(request, response);
        var dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
