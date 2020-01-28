package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanUsuario;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
@MultipartConfig
public class CadastrarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoUsuario daoUsuario = new DaoUsuario();

	public CadastrarUsuarioServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			if (acao != null) {

				if (acao.equalsIgnoreCase("cadastrar")) {
					RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
					request.setAttribute("usuarios", daoUsuario.listar());
					view.forward(request, response);
				} else if (acao.equalsIgnoreCase("listarTodos")) {
					RequestDispatcher view = request.getRequestDispatcher("/listarUsuario.jsp");
					request.setAttribute("usuarios", daoUsuario.listar());
					view.forward(request, response);
				}
				
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/listarUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String cep = request.getParameter("cep");
		String rua = request.getParameter("rua");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String uf = request.getParameter("uf");
		String sexo = request.getParameter("sexo");
		String perfil = request.getParameter("perfil");

		BeanUsuario usuario = new BeanUsuario();
		usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		usuario.setCep(cep);
		usuario.setRua(rua);
		usuario.setBairro(bairro);
		usuario.setCidade(cidade);
		usuario.setUf(uf);
		usuario.setSexo(sexo);
		usuario.setPerfil(perfil);
		
		if(request.getParameter("ativo") != null && request.getParameter("ativo").equalsIgnoreCase("on")) {
			usuario.setAtivo(true);
		}else {
			usuario.setAtivo(false);
		}

		try {

			/* INICIO file upload de imagens e PDF */

			if (ServletFileUpload.isMultipartContent(request)) {

				Part imagemFoto = request.getPart("foto");

				if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {

					String foto = new Base64().encodeBase64String(converterStreamParabyte(imagemFoto.getInputStream()));

					usuario.setFoto(foto);
					usuario.setContenttype(imagemFoto.getContentType());

					/* inicio miniatura imagem */

					/* transforma em um buffered */
					byte[] imageByteDecode = new Base64().decodeBase64(foto);
					BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));

					/* pegar o tipo da imagem */
					int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

					/* cria a imagem em miniatura */
					BufferedImage resizedImage = new BufferedImage(100, 100, type);
					Graphics2D g = resizedImage.createGraphics();
					g.drawImage(bufferedImage, 0, 0, 100, 100, null);
					g.dispose();

					/* escrever a imagem novamente */
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(resizedImage, "png", baos);

					String miniatura = "data:image/png;base64,"
							+ DatatypeConverter.printBase64Binary(baos.toByteArray());

					usuario.setFotoMiniatura(miniatura);

					/* fim miniatura imagem */
				} else {
					usuario.setFoto(request.getParameter("fotoTemp"));
					usuario.setContenttype(request.getParameter("contentTypeTemp"));
				}

				Part imagemDocumento = request.getPart("documento");

				if (imagemDocumento != null && imagemDocumento.getInputStream().available() > 0) {
					String documento = new Base64()
							.encodeBase64String(converterStreamParabyte(imagemDocumento.getInputStream()));

					usuario.setDocumento(documento);
					usuario.setContenttypedoc(imagemDocumento.getContentType());

				} else {
					usuario.setDocumento(request.getParameter("documentoTemp"));
				}

			}

			/* FIM file upload de imagens e PDF */

			if (id == null || id.isEmpty()) {
				if (!daoUsuario.validarLogin(login)) {
					request.setAttribute("user", usuario);
					request.setAttribute("usuarios", daoUsuario.listar());
					RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
					request.setAttribute("msg", "Usuário com o mesmo login já cadastrado!");
					view.forward(request, response);

				} else {
					daoUsuario.salvar(usuario);
					request.setAttribute("usuarios", daoUsuario.listar());
					RequestDispatcher view = request.getRequestDispatcher("/listarUsuario.jsp");
					request.setAttribute("msg", "Usuário cadastrado com sucesso!");
					view.forward(request, response);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// converte a entrada de fluxo de dados da imagem para byte[]
	private byte[] converterStreamParabyte(InputStream imagem) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int reads = imagem.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		return baos.toByteArray();
	}

}
