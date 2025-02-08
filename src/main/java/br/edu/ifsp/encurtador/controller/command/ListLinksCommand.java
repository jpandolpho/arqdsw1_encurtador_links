package br.edu.ifsp.encurtador.controller.command;

import java.util.List;

import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListLinksCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        LinkDao linkDao = new LinkDaoFactory().factory();
        
        List<Link> links = linkDao.retrieveLinksByUser(user);
        session.setAttribute("links", links);
        request.setAttribute("links", links);

        return "/logado/meusLinks.jsp"; 
    }
}