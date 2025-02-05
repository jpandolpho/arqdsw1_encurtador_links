package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import br.edu.ifsp.encurtador.model.dao.AccessDaoFactory;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Access;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirecionaCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String curto = request.getRequestURL().toString().split("/")[5];
		
		var linkDao = new LinkDaoFactory().factory();
		String original = linkDao.retrieve_original(curto);
		
		if(original!=null) {
			if(linkDao.hasUser(curto)) {
				var accessDao = new AccessDaoFactory().factory();
				
				String ipAddr;
				ipAddr = request.getHeader("X-FORWARDED-FOR");
	            if (ipAddr == null || "".equals(ipAddr)) {
	            	ipAddr = request.getRemoteAddr();
	            }
	            
	            Access acesso = new Access(curto,ipAddr);
	            accessDao.create(acesso);
			}
			
		}
		
		return original;
	}

}
