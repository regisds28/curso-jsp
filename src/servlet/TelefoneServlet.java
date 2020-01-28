package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanTelefone;
import beans.BeanUsuario;
import dao.DaoTelefones;
import dao.DaoUsuario;

@WebServlet("/salvarTelefone")
public class TelefoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	private DaoTelefones daoTelefone = new DaoTelefones();

	public TelefoneServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String foneId = request.getParameter("foneId");
			String user = request.getParameter("user");

			BeanUsuario usuario = (BeanUsuario) request.getSession().getAttribute("userEscolhido");
			// if (acao != null) {
			if (acao.equalsIgnoreCase("addFone")) {
				BeanUsuario beanUsuario = daoUsuario.consultar(user);
				request.getSession().setAttribute("userEscolhido", beanUsuario);
				request.setAttribute("userEscolhido", beanUsuario);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefone.jsp");
				request.setAttribute("telefone", daoTelefone.listar(beanUsuario.getId()));
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("deleteFone")) {
				daoTelefone.delete(foneId);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefone.jsp");
				request.setAttribute("msg", "Telefone Exclu�do Com Sucesso!");
				request.setAttribute("telefone", daoTelefone.listar(usuario.getId()));
				view.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			BeanUsuario beanUsuario = (BeanUsuario) request.getSession().getAttribute("userEscolhido");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			BeanTelefone beanTelefone = new BeanTelefone();
			beanTelefone.setNumero(numero);
			beanTelefone.setTipo(tipo);
			beanTelefone.setUsuario(beanUsuario.getId());

			daoTelefone.salvar(beanTelefone);
			request.getSession().setAttribute("userEscolhido", beanUsuario);
			request.setAttribute("userEscolhido", beanUsuario);

			RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefone.jsp");
			request.setAttribute("telefone", daoTelefone.listar(beanUsuario.getId()));
			request.setAttribute("msg", "Telefone Salvo Com Sucesso!");
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
