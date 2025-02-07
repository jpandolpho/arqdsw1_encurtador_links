package br.edu.ifsp.encurtador.controller.command;

import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteLinkCommand implements Command {
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) {
		 	HttpSession session = request.getSession(false);
	        User user = (User) request.getSession().getAttribute("user");

	        String curto = request.getParameter("curto");

	        if (curto != null && !curto.isEmpty()) {
	            LinkDao linkDao = LinkDaoFactory.factory();
	            Link link = new Link(curto, "");
	            
	            if (user.getLinks().stream().anyMatch(l -> l.getLinkEncurtado().equals(curto))) {
	            	 LinkDao linkdao = LinkDaoFactory.factory();
	                 boolean success = linkdao.delete(link);

	                 if (success) {
	                     linkDao.retrieve(user);
	                     request.getSession().setAttribute("user", user);
	                     request.setAttribute("message", "Link excluído com sucesso!");
	                 } else {
	                     request.setAttribute("message", "Erro ao excluir o link.");
	                 }
	             } else {
	                 request.setAttribute("message", "Você não tem permissão para excluir este link.");
	             }
	         } else {
	             request.setAttribute("message", "Link inválido.");
	         }
	        return "/logado/home.jsp"; 
	    }
}
