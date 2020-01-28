package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProdutos;

@WebServlet("/listarProduto")
public class ListarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoProdutos daoProdutos = new DaoProdutos();

	public ListarProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			// cria-se os parâmetros da URL
			String acao = request.getParameter("acao");
			String prod = request.getParameter("prod");

			if (acao.equalsIgnoreCase("delete")) {
				daoProdutos.delete(prod);
				RequestDispatcher view = request.getRequestDispatcher("/listarProduto.jsp");
				request.setAttribute("msg", "Produto Excluído Com Sucesso!");
				request.setAttribute("produtos", daoProdutos.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				BeanProduto beansProdutos = daoProdutos.consultar(prod);
				RequestDispatcher view = request.getRequestDispatcher("/editarProduto.jsp");
				request.setAttribute("prod", beansProdutos);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listarTodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/listarProduto.jsp");
				request.setAttribute("produtos", daoProdutos.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
