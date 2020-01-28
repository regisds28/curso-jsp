package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanUsuario;
import dao.DaoUsuario;

@WebServlet("/pesquisaServlet")
public class PesquisaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public PesquisaServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pesquisar = request.getParameter("pesquisar");

		if (pesquisar != null && !pesquisar.trim().isEmpty()) {

			try {
				List<BeanUsuario> listarPesquisa = daoUsuario.listar(pesquisar);
				RequestDispatcher view = request.getRequestDispatcher("/listarUsuario.jsp");
				request.setAttribute("usuarios", listarPesquisa);
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				
				RequestDispatcher view = request.getRequestDispatcher("/listarUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
