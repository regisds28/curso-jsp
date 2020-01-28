package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCategoria;
import beans.BeanProduto;
import dao.DaoProdutos;

@WebServlet("/salvarProduto")
public class CadastrarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoProdutos daoProdutos = new DaoProdutos();

	public CadastrarProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// cria-se os parâmetros da URL
			String acao = request.getParameter("acao");
			
			if (acao != null) {
				if (acao.equalsIgnoreCase("cadastrar")) {
					RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
					request.setAttribute("produtos", daoProdutos.listar());
					request.setAttribute("categoria", daoProdutos.listarCategoria());
					view.forward(request, response);
				} else if (acao.equalsIgnoreCase("listarTodos")) {
					RequestDispatcher view = request.getRequestDispatcher("/listarProduto.jsp");
					request.setAttribute("produtos", daoProdutos.listar());
					view.forward(request, response);
				}

			}else {
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

		// define o parâmetro de cada input
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String valor = request.getParameter("valor");
		String quantidade = request.getParameter("quantidade");
		String categoria = request.getParameter("categoria_id");

		try {
			// atribui os parâmetros com os dados do BeanProdutos, associado às
			// inserções no banco
			
			BeanProduto produtos = new BeanProduto();
			produtos.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produtos.setNome(nome);
			produtos.setDescricao(descricao);
			produtos.setCategoria_id(Long.parseLong(categoria));
			if (valor != null && !valor.isEmpty()) {
				String valorParse = valor.replaceAll("\\.", "");
				valorParse = valorParse.replaceAll("\\,", ".");
				produtos.setValor(Double.parseDouble(valorParse));
			}
			produtos.setQuantidade(Double.parseDouble(quantidade));
			

			if (id == null || id.isEmpty()) {
				request.setAttribute("msg", "Produto cadastrado com sucesso!");
				daoProdutos.salvar(produtos);
			}

			RequestDispatcher view = request.getRequestDispatcher("/listarProduto.jsp");
			
			request.setAttribute("produtos", daoProdutos.listar());
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
