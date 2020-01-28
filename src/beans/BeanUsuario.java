package beans;

public class BeanUsuario {

	private Long id;

	private String login;

	private String senha;

	private String nome;

	private String cep;

	private String rua;

	private String bairro;

	private String cidade;

	private String uf;

	private String foto;

	private String contenttype;
	
	private String documento;

	private String contenttypedoc;
	
	private String tempFotoUser;
	
	private String fotoMiniatura;
	
	private boolean atualizaImg = true;
	
	private boolean atualizaPdf = true;
	
	private boolean ativo;
	
	private String sexo;
	
	private String perfil; 
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getTempFotoUser() {
		
		tempFotoUser = "data:" + contenttype + ";base64," + foto;
				
		return tempFotoUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setId(String id) {

	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getContenttypedoc() {
		return contenttypedoc;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public void setContenttypedoc(String contenttypedoc) {
		this.contenttypedoc = contenttypedoc;
	}

	public String getFotoMiniatura() {
		return fotoMiniatura;
	}

	public boolean isAtualizaImg() {
		return atualizaImg;
	}

	public void setAtualizaImg(boolean atualizaImg) {
		this.atualizaImg = atualizaImg;
	}

	public boolean isAtualizaPdf() {
		return atualizaPdf;
	}

	public void setAtualizaPdf(boolean atualizaPdf) {
		this.atualizaPdf = atualizaPdf;
	}

	public void setFotoMiniatura(String fotoMiniatura) {
		this.fotoMiniatura = fotoMiniatura;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}
