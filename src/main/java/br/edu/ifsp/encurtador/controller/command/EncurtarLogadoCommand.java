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
    	User user = (User) request.getSession().getAttribute("user");
        
        String textLink = request.getParameter("textLink");
        String textCurto = request.getParameter("textCurto");

        LinkDao dao = new LinkDaoFactory().factory();
        Link link = dao.retrieve(textLink,user);

        boolean retrieved = false;
        boolean created = false;

        if (link == null && textCurto.length()>=5) {
        	if(textCurto == null || textCurto.isEmpty()) {
        		link = new Link(textLink);
        	}else {
        		link = new Link(textCurto,textLink);
        	}
            created = dao.create(user, link);
        } else if(textCurto.isEmpty() || textCurto.length()>=5) {
            retrieved = true;
        }


        if(created || retrieved) {
			request.setAttribute("link", link.getLinkEncurtado());
		} else {
        	if(textCurto.length()<5) {
        		request.setAttribute("msg", "O link personalizado deve ter no mínimo 5 caracteres.");
        	}else {
        		request.setAttribute("msg", "Erro ao criar o link curto.");
        	}
        }
        return "logado.do?command=Home"; 
    }
}
