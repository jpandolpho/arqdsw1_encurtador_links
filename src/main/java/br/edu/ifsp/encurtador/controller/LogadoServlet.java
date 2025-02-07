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
import br.edu.ifsp.encurtador.controller.command.ListLinksCommand;
import br.edu.ifsp.encurtador.controller.command.ViewStatsCommand;
import br.edu.ifsp.encurtador.controller.command.LogoutCommand;
import br.edu.ifsp.encurtador.controller.command.EncurtarLogadoCommand;

@WebServlet("/logado")
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
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        
        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        request.setAttribute("user", user); 
        request.getRequestDispatcher("/home.jsp").forward(request, response); 
    
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
        }
        
        if (command != null) {
            command.execute(request, response);
        }
        
        String view = command.execute(request, response);
        
        if ("logado".equals(view)) {
            response.sendRedirect("logado"); 
        } else {
            var dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
        }
    }
}
