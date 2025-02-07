package br.edu.ifsp.encurtador.controller.command;

import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteLinkCommand implements Command {
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) {
	        User user = (User) request.getSession().getAttribute("user");

	        String curto = request.getParameter("curto");

	        if (curto != null && !curto.isEmpty()) {
	            LinkDao linkDao = LinkDaoFactory.factory();
	            Link link = new Link(curto, "");
	            
	            if (user.getLinks().stream().anyMatch(l -> l.getLinkEncurtado().equals(curto))) {
	                linkDao.delete(link);
	            }
	        }

	        return "/logado/home.jsp"; 
	    }
}
