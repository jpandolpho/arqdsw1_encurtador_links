package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EncurtarCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var textLink = request.getParameter("textLink");
		Link link = new Link(textLink);
		
		LinkDao dao = new LinkDaoFactory().factory();
		
		boolean saved = dao.create(null, link);
		
		if(saved) {
			System.out.println("salvou");
		}
		
		return "front.do?action=home";
	}

}
