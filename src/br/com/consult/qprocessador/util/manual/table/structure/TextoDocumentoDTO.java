package br.com.consult.qprocessador.util.manual.table.structure;

public class TextoDocumentoDTO {

	private Integer pagina;
	private String texto;
	private String textoTraducao;
	private String sigla;
	private String tipo;

	public TextoDocumentoDTO() {
		super();
	}

	public TextoDocumentoDTO(Integer pagina, String texto, String textoTraducao, String sigla, String tipo) {
		super();
		this.pagina = pagina;
		this.texto = texto;
		this.textoTraducao = textoTraducao;
		this.sigla = sigla;
		this.tipo = tipo;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTextoTraducao() {
		return textoTraducao;
	}

	public void setTextoTraducao(String textoTraducao) {
		this.textoTraducao = textoTraducao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
