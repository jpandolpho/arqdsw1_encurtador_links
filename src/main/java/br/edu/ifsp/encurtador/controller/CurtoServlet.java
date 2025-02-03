package br.edu.ifsp.encurtador.controller;

import java.io.IOException;

import br.edu.ifsp.encurtador.model.dao.AccessDaoFactory;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Access;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/curto/*")
public class CurtoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String curto = request.getRequestURL().toString().split("/")[5];
		
		var linkDao = new LinkDaoFactory().factory();
		String original = linkDao.retrieve_original(curto);
		
		if(original==null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Link curto não encontrado.");
		}else {
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
			response.sendRedirect(original);
		}
	}
}
