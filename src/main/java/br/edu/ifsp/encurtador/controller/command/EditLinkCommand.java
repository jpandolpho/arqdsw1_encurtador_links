package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;
import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EditLinkCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	User user = (User) session.getAttribute("user");
    	
        String oldShort = request.getParameter("curto"); 
        String newShort = request.getParameter("novoCurto");
        String newOriginal = request.getParameter("novoOriginal");

        Link updatedLink = new Link(newShort, newOriginal);

        LinkDao dao = new LinkDaoFactory().factory();
        boolean success = dao.update(updatedLink, oldShort);

        if (success) {
        	dao.retrieve(user);
        	session.setAttribute("user", user);
            request.setAttribute("message", "Link atualizado com sucesso!");
        } else {
            request.setAttribute("message", "Erro ao atualizar o link.");
        }
        return "LogadoServlet?command=ListLinks";
    }
}