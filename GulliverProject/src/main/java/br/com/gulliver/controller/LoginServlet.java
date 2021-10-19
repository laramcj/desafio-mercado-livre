package br.com.gulliver.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gulliver.beans.Usuario;
import br.com.gulliver.dao.DataSource;
import br.com.gulliver.dao.UsuarioDAO;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String pagina="";
		try {
			DataSource dataSource = new DataSource();
			UsuarioDAO usuarioDao = new UsuarioDAO(dataSource);
			
			Usuario userLogin = new Usuario();
			userLogin.setNome_usuario(request.getParameter("nome_usuario"));
			userLogin.setSenha(request.getParameter("senha"));
			Usuario user = usuarioDao.read(userLogin);
			
				if(user != null) {
					request.getSession().setAttribute("Usuario", user);
					pagina = "/usuariologado.jsp";
				} else {
					request.setAttribute("ErroMSG", "Usuário não encontrado");
					pagina = "/index.jsp";
				}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
		} catch(Exception ex) {
			System.out.println("Erro no LoginServlet " + ex.getMessage());
		}
		
			
	}
}
