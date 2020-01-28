package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanUsuario;
import dao.DaoUsuario;

@WebServlet("/listarUsuario")
public class ListarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoUsuario daoUsuario = new DaoUsuario();

	public ListarUsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/listarUsuario.jsp");
				request.setAttribute("msg", "Usuário Excluído Com Sucesso!");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				BeanUsuario beanUsuario = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("/editarUsuario.jsp");
				request.setAttribute("user", beanUsuario);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listarTodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/listarUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("download")) {
				BeanUsuario usuario = daoUsuario.consultar(user);
				if (usuario != null) {

					String tipo = request.getParameter("tipo");

					byte[] fileBytes = null;
					String contentType = "";

					if (tipo.equalsIgnoreCase("imagem")) {
						contentType = usuario.getContenttype();
						fileBytes = new Base64().decodeBase64(usuario.getFoto());
					} else if (tipo.equalsIgnoreCase("documento")) {
						contentType = usuario.getContenttypedoc();
						fileBytes = new Base64().decodeBase64(usuario.getDocumento());
					}

					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);

					// coloca os bytes em um objeto de entrada para processar
					InputStream is = new ByteArrayInputStream(fileBytes);

					// início da resposta
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}

					os.flush();
					os.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
