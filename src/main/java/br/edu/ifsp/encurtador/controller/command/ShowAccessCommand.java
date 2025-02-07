package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import br.edu.ifsp.encurtador.model.dao.AccessDao;
import br.edu.ifsp.encurtador.model.dao.AccessDaoFactory;
import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;
import br.edu.ifsp.encurtador.model.entity.Access;

public class ShowAccessCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            return "login.jsp"; 
        }

        String curto = request.getParameter("curto");

        if (curto != null && !curto.isEmpty()) {
            LinkDao linkDao = LinkDaoFactory.factory();
            Link link = new Link(curto, ""); 

            AccessDao accessDao = AccessDaoFactory.factory();
            accessDao.retrieve(link);

            Map<Access, Integer> accessMap = link.getAcessos();
            List<Access> accesses = new ArrayList<>();

            for (Map.Entry<Access, Integer> entry : accessMap.entrySet()) {
                Access access = entry.getKey();
                access.setCount(entry.getValue()); 
                accesses.add(access);
            }

            request.setAttribute("accesses", accesses);
        }

        return "/logado/meusLinks.jsp";
    }
}