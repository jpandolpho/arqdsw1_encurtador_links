package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.edu.ifsp.encurtador.model.dao.LinkDao;
import br.edu.ifsp.encurtador.model.dao.LinkDaoFactory;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;

public class ListLinksCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//não precisa fazer verificação da sessão, eu criei o filtro agora
        User user = (User) request.getSession().getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("index.jsp");
            return null;
        }

        LinkDao linkDao = LinkDaoFactory.factory();

        List<Link> userLinks = linkDao.retrieveLinksByUser(user);

        request.setAttribute("userLinks", userLinks);
        
        //retorna o caminho da página pra qual vc quer mandar o usuário, por exemplo "/logado/meusLinks.jsp"
        try {
            request.getRequestDispatcher("/meusLinks.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
}
