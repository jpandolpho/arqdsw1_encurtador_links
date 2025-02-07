package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;
import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EncurtarLogadoCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//verifica usuario
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("../index.jsp");
            return null;
        }


        String textLink = request.getParameter("textLink");

        if (textLink == null || textLink.isEmpty()) {
            request.setAttribute("msg", "O link não pode estar vazio.");
            return "home.jsp"; //
        }

        LinkDao dao = new LinkDaoFactory().factory();
        Link link = dao.retrieve(textLink);

        boolean created = false;

        if (link == null) {
            link = new Link(textLink);
            created = dao.create(user, link);
        } else {
            created = true;
        }


        if (created) {
            request.setAttribute("link", link.getLinkEncurtado()); 
        } else {
            request.setAttribute("msg", "Erro ao criar o link curto.");
        }

        return "home.jsp"; 
    }
}
